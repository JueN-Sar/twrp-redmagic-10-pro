package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.annotation.AttrRes;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.core.widget.PopupWindowCompat;
import com.google.android.gms.common.api.Api;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class ListPopupWindow implements ShowableListMenu {
    private static final boolean DEBUG = false;
    static final int EXPAND_LIST_TIMEOUT = 250;
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int MATCH_PARENT = -1;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    private static final String TAG = "ListPopupWindow";
    public static final int WRAP_CONTENT = -2;
    private static Method sGetMaxAvailableHeightMethod;
    private static Method sSetClipToWindowEnabledMethod;
    private static Method sSetEpicenterBoundsMethod;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible;
    private View mDropDownAnchorView;
    private int mDropDownGravity;
    private int mDropDownHeight;
    private int mDropDownHorizontalOffset;
    DropDownListView mDropDownList;
    private Drawable mDropDownListHighlight;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    private int mDropDownWidth;
    private int mDropDownWindowLayoutType;
    private Rect mEpicenterBounds;
    private boolean mForceIgnoreOutsideTouch;
    final Handler mHandler;
    private final ListSelectorHider mHideSelector;
    private AdapterView.OnItemClickListener mItemClickListener;
    private AdapterView.OnItemSelectedListener mItemSelectedListener;
    int mListItemExpandMaximum;
    private boolean mModal;
    private DataSetObserver mObserver;
    private boolean mOverlapAnchor;
    private boolean mOverlapAnchorSet;
    PopupWindow mPopup;
    private int mPromptPosition;
    private View mPromptView;
    final ResizePopupRunnable mResizePopupRunnable;
    private final PopupScrollListener mScrollListener;
    private Runnable mShowDropDownRunnable;
    private final Rect mTempRect;
    private final PopupTouchInterceptor mTouchInterceptor;

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static int a(PopupWindow popupWindow, View view, int i2, boolean z) {
            return popupWindow.getMaxAvailableHeight(view, i2, z);
        }
    }

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static void a(PopupWindow popupWindow, Rect rect) {
            popupWindow.setEpicenterBounds(rect);
        }

        @DoNotInline
        static void b(PopupWindow popupWindow, boolean z) {
            popupWindow.setIsClippedToScreen(z);
        }
    }

    private class ListSelectorHider implements Runnable {
        ListSelectorHider() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ListPopupWindow.this.clearListSelection();
        }
    }

    private class PopupDataSetObserver extends DataSetObserver {
        PopupDataSetObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    private class PopupScrollListener implements AbsListView.OnScrollListener {
        PopupScrollListener() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (i2 != 1 || ListPopupWindow.this.isInputMethodNotNeeded() || ListPopupWindow.this.mPopup.getContentView() == null) {
                return;
            }
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            listPopupWindow.mHandler.removeCallbacks(listPopupWindow.mResizePopupRunnable);
            ListPopupWindow.this.mResizePopupRunnable.run();
        }
    }

    private class PopupTouchInterceptor implements View.OnTouchListener {
        PopupTouchInterceptor() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = ListPopupWindow.this.mPopup) != null && popupWindow.isShowing() && x >= 0 && x < ListPopupWindow.this.mPopup.getWidth() && y >= 0 && y < ListPopupWindow.this.mPopup.getHeight()) {
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                listPopupWindow.mHandler.postDelayed(listPopupWindow.mResizePopupRunnable, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            ListPopupWindow listPopupWindow2 = ListPopupWindow.this;
            listPopupWindow2.mHandler.removeCallbacks(listPopupWindow2.mResizePopupRunnable);
            return false;
        }
    }

    private class ResizePopupRunnable implements Runnable {
        ResizePopupRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DropDownListView dropDownListView = ListPopupWindow.this.mDropDownList;
            if (dropDownListView == null || !dropDownListView.isAttachedToWindow() || ListPopupWindow.this.mDropDownList.getCount() <= ListPopupWindow.this.mDropDownList.getChildCount()) {
                return;
            }
            int childCount = ListPopupWindow.this.mDropDownList.getChildCount();
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            if (childCount <= listPopupWindow.mListItemExpandMaximum) {
                listPopupWindow.mPopup.setInputMethodMode(2);
                ListPopupWindow.this.show();
            }
        }
    }

    public ListPopupWindow(Context context) {
        this(context, null, R.attr.listPopupWindowStyle);
    }

    private int c() {
        int i2;
        int i3;
        int makeMeasureSpec;
        int i4;
        if (this.mDropDownList == null) {
            Context context = this.mContext;
            this.mShowDropDownRunnable = new Runnable() { // from class: androidx.appcompat.widget.ListPopupWindow.2
                @Override // java.lang.Runnable
                public void run() {
                    View anchorView = ListPopupWindow.this.getAnchorView();
                    if (anchorView == null || anchorView.getWindowToken() == null) {
                        return;
                    }
                    ListPopupWindow.this.show();
                }
            };
            DropDownListView createDropDownListView = createDropDownListView(context, !this.mModal);
            this.mDropDownList = createDropDownListView;
            Drawable drawable = this.mDropDownListHighlight;
            if (drawable != null) {
                createDropDownListView.setSelector(drawable);
            }
            this.mDropDownList.setAdapter(this.mAdapter);
            this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
            this.mDropDownList.setFocusable(true);
            this.mDropDownList.setFocusableInTouchMode(true);
            this.mDropDownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: androidx.appcompat.widget.ListPopupWindow.3
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView adapterView, View view, int i5, long j2) {
                    DropDownListView dropDownListView;
                    if (i5 == -1 || (dropDownListView = ListPopupWindow.this.mDropDownList) == null) {
                        return;
                    }
                    dropDownListView.setListSelectionHidden(false);
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView adapterView) {
                }
            });
            this.mDropDownList.setOnScrollListener(this.mScrollListener);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.mItemSelectedListener;
            if (onItemSelectedListener != null) {
                this.mDropDownList.setOnItemSelectedListener(onItemSelectedListener);
            }
            View view = this.mDropDownList;
            View view2 = this.mPromptView;
            if (view2 != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i5 = this.mPromptPosition;
                if (i5 == 0) {
                    linearLayout.addView(view2);
                    linearLayout.addView(view, layoutParams);
                } else if (i5 != 1) {
                    Log.e(TAG, "Invalid hint position " + this.mPromptPosition);
                } else {
                    linearLayout.addView(view, layoutParams);
                    linearLayout.addView(view2);
                }
                int i6 = this.mDropDownWidth;
                if (i6 >= 0) {
                    i4 = Integer.MIN_VALUE;
                } else {
                    i6 = 0;
                    i4 = 0;
                }
                view2.measure(View.MeasureSpec.makeMeasureSpec(i6, i4), 0);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                i2 = view2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
                view = linearLayout;
            } else {
                i2 = 0;
            }
            this.mPopup.setContentView(view);
        } else {
            View view3 = this.mPromptView;
            if (view3 != null) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view3.getLayoutParams();
                i2 = view3.getMeasuredHeight() + layoutParams3.topMargin + layoutParams3.bottomMargin;
            } else {
                i2 = 0;
            }
        }
        Drawable background = this.mPopup.getBackground();
        if (background != null) {
            background.getPadding(this.mTempRect);
            Rect rect = this.mTempRect;
            int i7 = rect.top;
            i3 = rect.bottom + i7;
            if (!this.mDropDownVerticalOffsetSet) {
                this.mDropDownVerticalOffset = -i7;
            }
        } else {
            this.mTempRect.setEmpty();
            i3 = 0;
        }
        int d2 = d(getAnchorView(), this.mDropDownVerticalOffset, this.mPopup.getInputMethodMode() == 2);
        if (this.mDropDownAlwaysVisible || this.mDropDownHeight == -1) {
            return d2 + i3;
        }
        int i8 = this.mDropDownWidth;
        if (i8 == -2) {
            int i9 = this.mContext.getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = this.mTempRect;
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i9 - (rect2.left + rect2.right), Integer.MIN_VALUE);
        } else if (i8 != -1) {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
        } else {
            int i10 = this.mContext.getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = this.mTempRect;
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i10 - (rect3.left + rect3.right), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
        }
        int d3 = this.mDropDownList.d(makeMeasureSpec, 0, -1, d2 - i2, -1);
        if (d3 > 0) {
            i2 += i3 + this.mDropDownList.getPaddingTop() + this.mDropDownList.getPaddingBottom();
        }
        return d3 + i2;
    }

    private int d(View view, int i2, boolean z) {
        return Api24Impl.a(this.mPopup, view, i2, z);
    }

    private static boolean e(int i2) {
        return i2 == 66 || i2 == 23;
    }

    private void f() {
        View view = this.mPromptView;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.mPromptView);
            }
        }
    }

    private void g(boolean z) {
        Api29Impl.b(this.mPopup, z);
    }

    public void clearListSelection() {
        DropDownListView dropDownListView = this.mDropDownList;
        if (dropDownListView != null) {
            dropDownListView.setListSelectionHidden(true);
            dropDownListView.requestLayout();
        }
    }

    public View.OnTouchListener createDragToOpenListener(View view) {
        return new ForwardingListener(view) { // from class: androidx.appcompat.widget.ListPopupWindow.1
            @Override // androidx.appcompat.widget.ForwardingListener
            /* renamed from: k, reason: merged with bridge method [inline-methods] */
            public ListPopupWindow b() {
                return ListPopupWindow.this;
            }
        };
    }

    @NonNull
    DropDownListView createDropDownListView(Context context, boolean z) {
        return new DropDownListView(context, z);
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void dismiss() {
        this.mPopup.dismiss();
        f();
        this.mPopup.setContentView(null);
        this.mDropDownList = null;
        this.mHandler.removeCallbacks(this.mResizePopupRunnable);
    }

    @Nullable
    public View getAnchorView() {
        return this.mDropDownAnchorView;
    }

    @StyleRes
    public int getAnimationStyle() {
        return this.mPopup.getAnimationStyle();
    }

    @Nullable
    public Drawable getBackground() {
        return this.mPopup.getBackground();
    }

    @Nullable
    public Rect getEpicenterBounds() {
        if (this.mEpicenterBounds != null) {
            return new Rect(this.mEpicenterBounds);
        }
        return null;
    }

    public int getHeight() {
        return this.mDropDownHeight;
    }

    public int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }

    public int getInputMethodMode() {
        return this.mPopup.getInputMethodMode();
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    @Nullable
    public ListView getListView() {
        return this.mDropDownList;
    }

    public int getPromptPosition() {
        return this.mPromptPosition;
    }

    @Nullable
    public Object getSelectedItem() {
        if (isShowing()) {
            return this.mDropDownList.getSelectedItem();
        }
        return null;
    }

    public long getSelectedItemId() {
        if (isShowing()) {
            return this.mDropDownList.getSelectedItemId();
        }
        return Long.MIN_VALUE;
    }

    public int getSelectedItemPosition() {
        if (isShowing()) {
            return this.mDropDownList.getSelectedItemPosition();
        }
        return -1;
    }

    @Nullable
    public View getSelectedView() {
        if (isShowing()) {
            return this.mDropDownList.getSelectedView();
        }
        return null;
    }

    public int getSoftInputMode() {
        return this.mPopup.getSoftInputMode();
    }

    public int getVerticalOffset() {
        if (this.mDropDownVerticalOffsetSet) {
            return this.mDropDownVerticalOffset;
        }
        return 0;
    }

    public int getWidth() {
        return this.mDropDownWidth;
    }

    @RestrictTo
    public boolean isDropDownAlwaysVisible() {
        return this.mDropDownAlwaysVisible;
    }

    public boolean isInputMethodNotNeeded() {
        return this.mPopup.getInputMethodMode() == 2;
    }

    public boolean isModal() {
        return this.mModal;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public boolean isShowing() {
        return this.mPopup.isShowing();
    }

    public boolean onKeyDown(int i2, @NonNull KeyEvent keyEvent) {
        int i3;
        int i4;
        if (isShowing() && i2 != 62 && (this.mDropDownList.getSelectedItemPosition() >= 0 || !e(i2))) {
            int selectedItemPosition = this.mDropDownList.getSelectedItemPosition();
            boolean z = !this.mPopup.isAboveAnchor();
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                boolean areAllItemsEnabled = listAdapter.areAllItemsEnabled();
                i3 = areAllItemsEnabled ? 0 : this.mDropDownList.lookForSelectablePosition(0, true);
                i4 = areAllItemsEnabled ? listAdapter.getCount() - 1 : this.mDropDownList.lookForSelectablePosition(listAdapter.getCount() - 1, false);
            } else {
                i3 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
                i4 = Integer.MIN_VALUE;
            }
            if ((z && i2 == 19 && selectedItemPosition <= i3) || (!z && i2 == 20 && selectedItemPosition >= i4)) {
                clearListSelection();
                this.mPopup.setInputMethodMode(1);
                show();
                return true;
            }
            this.mDropDownList.setListSelectionHidden(false);
            if (this.mDropDownList.onKeyDown(i2, keyEvent)) {
                this.mPopup.setInputMethodMode(2);
                this.mDropDownList.requestFocusFromTouch();
                show();
                if (i2 == 19 || i2 == 20 || i2 == 23 || i2 == 66) {
                    return true;
                }
            } else if (z && i2 == 20) {
                if (selectedItemPosition == i4) {
                    return true;
                }
            } else if (!z && i2 == 19 && selectedItemPosition == i3) {
                return true;
            }
        }
        return false;
    }

    public boolean onKeyPreIme(int i2, @NonNull KeyEvent keyEvent) {
        if (i2 != 4 || !isShowing()) {
            return false;
        }
        View view = this.mDropDownAnchorView;
        if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
            KeyEvent.DispatcherState keyDispatcherState = view.getKeyDispatcherState();
            if (keyDispatcherState != null) {
                keyDispatcherState.startTracking(keyEvent, this);
            }
            return true;
        }
        if (keyEvent.getAction() != 1) {
            return false;
        }
        KeyEvent.DispatcherState keyDispatcherState2 = view.getKeyDispatcherState();
        if (keyDispatcherState2 != null) {
            keyDispatcherState2.handleUpEvent(keyEvent);
        }
        if (!keyEvent.isTracking() || keyEvent.isCanceled()) {
            return false;
        }
        dismiss();
        return true;
    }

    public boolean onKeyUp(int i2, @NonNull KeyEvent keyEvent) {
        if (!isShowing() || this.mDropDownList.getSelectedItemPosition() < 0) {
            return false;
        }
        boolean onKeyUp = this.mDropDownList.onKeyUp(i2, keyEvent);
        if (onKeyUp && e(i2)) {
            dismiss();
        }
        return onKeyUp;
    }

    public boolean performItemClick(int i2) {
        if (!isShowing()) {
            return false;
        }
        if (this.mItemClickListener == null) {
            return true;
        }
        DropDownListView dropDownListView = this.mDropDownList;
        this.mItemClickListener.onItemClick(dropDownListView, dropDownListView.getChildAt(i2 - dropDownListView.getFirstVisiblePosition()), i2, dropDownListView.getAdapter().getItemId(i2));
        return true;
    }

    public void postShow() {
        this.mHandler.post(this.mShowDropDownRunnable);
    }

    public void setAdapter(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.mObserver;
        if (dataSetObserver == null) {
            this.mObserver = new PopupDataSetObserver();
        } else {
            ListAdapter listAdapter2 = this.mAdapter;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.mAdapter = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.mObserver);
        }
        DropDownListView dropDownListView = this.mDropDownList;
        if (dropDownListView != null) {
            dropDownListView.setAdapter(this.mAdapter);
        }
    }

    public void setAnchorView(@Nullable View view) {
        this.mDropDownAnchorView = view;
    }

    public void setAnimationStyle(@StyleRes int i2) {
        this.mPopup.setAnimationStyle(i2);
    }

    public void setBackgroundDrawable(@Nullable Drawable drawable) {
        this.mPopup.setBackgroundDrawable(drawable);
    }

    public void setContentWidth(int i2) {
        Drawable background = this.mPopup.getBackground();
        if (background == null) {
            setWidth(i2);
            return;
        }
        background.getPadding(this.mTempRect);
        Rect rect = this.mTempRect;
        this.mDropDownWidth = rect.left + rect.right + i2;
    }

    @RestrictTo
    public void setDropDownAlwaysVisible(boolean z) {
        this.mDropDownAlwaysVisible = z;
    }

    public void setDropDownGravity(int i2) {
        this.mDropDownGravity = i2;
    }

    public void setEpicenterBounds(@Nullable Rect rect) {
        this.mEpicenterBounds = rect != null ? new Rect(rect) : null;
    }

    @RestrictTo
    public void setForceIgnoreOutsideTouch(boolean z) {
        this.mForceIgnoreOutsideTouch = z;
    }

    public void setHeight(int i2) {
        if (i2 < 0 && -2 != i2 && -1 != i2) {
            throw new IllegalArgumentException("Invalid height. Must be a positive value, MATCH_PARENT, or WRAP_CONTENT.");
        }
        this.mDropDownHeight = i2;
    }

    public void setHorizontalOffset(int i2) {
        this.mDropDownHorizontalOffset = i2;
    }

    public void setInputMethodMode(int i2) {
        this.mPopup.setInputMethodMode(i2);
    }

    void setListItemExpandMax(int i2) {
        this.mListItemExpandMaximum = i2;
    }

    public void setListSelector(Drawable drawable) {
        this.mDropDownListHighlight = drawable;
    }

    public void setModal(boolean z) {
        this.mModal = z;
        this.mPopup.setFocusable(z);
    }

    public void setOnDismissListener(@Nullable PopupWindow.OnDismissListener onDismissListener) {
        this.mPopup.setOnDismissListener(onDismissListener);
    }

    public void setOnItemClickListener(@Nullable AdapterView.OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public void setOnItemSelectedListener(@Nullable AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.mItemSelectedListener = onItemSelectedListener;
    }

    @RestrictTo
    public void setOverlapAnchor(boolean z) {
        this.mOverlapAnchorSet = true;
        this.mOverlapAnchor = z;
    }

    public void setPromptPosition(int i2) {
        this.mPromptPosition = i2;
    }

    public void setPromptView(@Nullable View view) {
        boolean isShowing = isShowing();
        if (isShowing) {
            f();
        }
        this.mPromptView = view;
        if (isShowing) {
            show();
        }
    }

    public void setSelection(int i2) {
        DropDownListView dropDownListView = this.mDropDownList;
        if (!isShowing() || dropDownListView == null) {
            return;
        }
        dropDownListView.setListSelectionHidden(false);
        dropDownListView.setSelection(i2);
        if (dropDownListView.getChoiceMode() != 0) {
            dropDownListView.setItemChecked(i2, true);
        }
    }

    public void setSoftInputMode(int i2) {
        this.mPopup.setSoftInputMode(i2);
    }

    public void setVerticalOffset(int i2) {
        this.mDropDownVerticalOffset = i2;
        this.mDropDownVerticalOffsetSet = true;
    }

    public void setWidth(int i2) {
        this.mDropDownWidth = i2;
    }

    public void setWindowLayoutType(int i2) {
        this.mDropDownWindowLayoutType = i2;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void show() {
        int c2 = c();
        boolean isInputMethodNotNeeded = isInputMethodNotNeeded();
        PopupWindowCompat.b(this.mPopup, this.mDropDownWindowLayoutType);
        if (this.mPopup.isShowing()) {
            if (getAnchorView().isAttachedToWindow()) {
                int i2 = this.mDropDownWidth;
                if (i2 == -1) {
                    i2 = -1;
                } else if (i2 == -2) {
                    i2 = getAnchorView().getWidth();
                }
                int i3 = this.mDropDownHeight;
                if (i3 == -1) {
                    if (!isInputMethodNotNeeded) {
                        c2 = -1;
                    }
                    if (isInputMethodNotNeeded) {
                        this.mPopup.setWidth(this.mDropDownWidth == -1 ? -1 : 0);
                        this.mPopup.setHeight(0);
                    } else {
                        this.mPopup.setWidth(this.mDropDownWidth == -1 ? -1 : 0);
                        this.mPopup.setHeight(-1);
                    }
                } else if (i3 != -2) {
                    c2 = i3;
                }
                this.mPopup.setOutsideTouchable((this.mForceIgnoreOutsideTouch || this.mDropDownAlwaysVisible) ? false : true);
                this.mPopup.update(getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, i2 < 0 ? -1 : i2, c2 < 0 ? -1 : c2);
                return;
            }
            return;
        }
        int i4 = this.mDropDownWidth;
        if (i4 == -1) {
            i4 = -1;
        } else if (i4 == -2) {
            i4 = getAnchorView().getWidth();
        }
        int i5 = this.mDropDownHeight;
        if (i5 == -1) {
            c2 = -1;
        } else if (i5 != -2) {
            c2 = i5;
        }
        this.mPopup.setWidth(i4);
        this.mPopup.setHeight(c2);
        g(true);
        this.mPopup.setOutsideTouchable((this.mForceIgnoreOutsideTouch || this.mDropDownAlwaysVisible) ? false : true);
        this.mPopup.setTouchInterceptor(this.mTouchInterceptor);
        if (this.mOverlapAnchorSet) {
            PopupWindowCompat.a(this.mPopup, this.mOverlapAnchor);
        }
        Api29Impl.a(this.mPopup, this.mEpicenterBounds);
        PopupWindowCompat.c(this.mPopup, getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
        this.mDropDownList.setSelection(-1);
        if (!this.mModal || this.mDropDownList.isInTouchMode()) {
            clearListSelection();
        }
        if (this.mModal) {
            return;
        }
        this.mHandler.post(this.mHideSelector);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2) {
        this(context, attributeSet, i2, 0);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        this.mDropDownHeight = -2;
        this.mDropDownWidth = -2;
        this.mDropDownWindowLayoutType = 1002;
        this.mDropDownGravity = 0;
        this.mDropDownAlwaysVisible = false;
        this.mForceIgnoreOutsideTouch = false;
        this.mListItemExpandMaximum = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.mPromptPosition = 0;
        this.mResizePopupRunnable = new ResizePopupRunnable();
        this.mTouchInterceptor = new PopupTouchInterceptor();
        this.mScrollListener = new PopupScrollListener();
        this.mHideSelector = new ListSelectorHider();
        this.mTempRect = new Rect();
        this.mContext = context;
        this.mHandler = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ListPopupWindow, i2, i3);
        this.mDropDownHorizontalOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.mDropDownVerticalOffset = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.mDropDownVerticalOffsetSet = true;
        }
        obtainStyledAttributes.recycle();
        AppCompatPopupWindow appCompatPopupWindow = new AppCompatPopupWindow(context, attributeSet, i2, i3);
        this.mPopup = appCompatPopupWindow;
        appCompatPopupWindow.setInputMethodMode(1);
    }
}
