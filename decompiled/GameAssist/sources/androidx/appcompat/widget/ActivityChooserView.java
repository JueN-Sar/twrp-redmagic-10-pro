package androidx.appcompat.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.appcompat.widget.ActivityChooserModel;
import androidx.core.view.ActionProvider;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.gms.common.api.Api;
import com.zte.shared.wrapper.WindowManagerWrapper;

@RestrictTo
/* loaded from: classes.dex */
public class ActivityChooserView extends ViewGroup implements ActivityChooserModel.ActivityChooserModelClient {
    private final View mActivityChooserContent;
    private final Drawable mActivityChooserContentBackground;
    final ActivityChooserViewAdapter mAdapter;
    private final Callbacks mCallbacks;
    private int mDefaultActionButtonContentDescription;
    final FrameLayout mDefaultActivityButton;
    private final ImageView mDefaultActivityButtonImage;
    final FrameLayout mExpandActivityOverflowButton;
    private final ImageView mExpandActivityOverflowButtonImage;
    int mInitialActivityCount;
    private boolean mIsAttachedToWindow;
    boolean mIsSelectingDefaultActivity;
    private final int mListPopupMaxWidth;
    private ListPopupWindow mListPopupWindow;
    final DataSetObserver mModelDataSetObserver;
    PopupWindow.OnDismissListener mOnDismissListener;
    private final ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;
    ActionProvider mProvider;

    private class ActivityChooserViewAdapter extends BaseAdapter {

        /* renamed from: c, reason: collision with root package name */
        private ActivityChooserModel f692c;

        /* renamed from: h, reason: collision with root package name */
        private int f693h = 4;

        /* renamed from: i, reason: collision with root package name */
        private boolean f694i;

        /* renamed from: j, reason: collision with root package name */
        private boolean f695j;

        /* renamed from: k, reason: collision with root package name */
        private boolean f696k;

        ActivityChooserViewAdapter() {
        }

        public int a() {
            return this.f692c.f();
        }

        public ActivityChooserModel b() {
            return this.f692c;
        }

        public ResolveInfo c() {
            return this.f692c.h();
        }

        public int d() {
            return this.f692c.i();
        }

        public boolean e() {
            return this.f694i;
        }

        public int f() {
            int i2 = this.f693h;
            this.f693h = Api.BaseClientBuilder.API_PRIORITY_OTHER;
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
            int count = getCount();
            int i3 = 0;
            View view = null;
            for (int i4 = 0; i4 < count; i4++) {
                view = getView(i4, view, null);
                view.measure(makeMeasureSpec, makeMeasureSpec2);
                i3 = Math.max(i3, view.getMeasuredWidth());
            }
            this.f693h = i2;
            return i3;
        }

        public void g(ActivityChooserModel activityChooserModel) {
            ActivityChooserModel b2 = ActivityChooserView.this.mAdapter.b();
            if (b2 != null && ActivityChooserView.this.isShown()) {
                b2.unregisterObserver(ActivityChooserView.this.mModelDataSetObserver);
            }
            this.f692c = activityChooserModel;
            if (activityChooserModel != null && ActivityChooserView.this.isShown()) {
                activityChooserModel.registerObserver(ActivityChooserView.this.mModelDataSetObserver);
            }
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int f2 = this.f692c.f();
            if (!this.f694i && this.f692c.h() != null) {
                f2--;
            }
            int min = Math.min(f2, this.f693h);
            return this.f696k ? min + 1 : min;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            int itemViewType = getItemViewType(i2);
            if (itemViewType != 0) {
                if (itemViewType == 1) {
                    return null;
                }
                throw new IllegalArgumentException();
            }
            if (!this.f694i && this.f692c.h() != null) {
                i2++;
            }
            return this.f692c.e(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i2) {
            return (this.f696k && i2 == getCount() - 1) ? 1 : 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            int itemViewType = getItemViewType(i2);
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    throw new IllegalArgumentException();
                }
                if (view != null && view.getId() == 1) {
                    return view;
                }
                View inflate = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(R.layout.abc_activity_chooser_view_list_item, viewGroup, false);
                inflate.setId(1);
                ((TextView) inflate.findViewById(R.id.title)).setText(ActivityChooserView.this.getContext().getString(R.string.abc_activity_chooser_view_see_all));
                return inflate;
            }
            if (view == null || view.getId() != R.id.list_item) {
                view = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(R.layout.abc_activity_chooser_view_list_item, viewGroup, false);
            }
            PackageManager packageManager = ActivityChooserView.this.getContext().getPackageManager();
            ImageView imageView = (ImageView) view.findViewById(R.id.icon);
            ResolveInfo resolveInfo = (ResolveInfo) getItem(i2);
            imageView.setImageDrawable(resolveInfo.loadIcon(packageManager));
            ((TextView) view.findViewById(R.id.title)).setText(resolveInfo.loadLabel(packageManager));
            if (this.f694i && i2 == 0 && this.f695j) {
                view.setActivated(true);
            } else {
                view.setActivated(false);
            }
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 3;
        }

        public void h(int i2) {
            if (this.f693h != i2) {
                this.f693h = i2;
                notifyDataSetChanged();
            }
        }

        public void i(boolean z, boolean z2) {
            if (this.f694i == z && this.f695j == z2) {
                return;
            }
            this.f694i = z;
            this.f695j = z2;
            notifyDataSetChanged();
        }

        public void j(boolean z) {
            if (this.f696k != z) {
                this.f696k = z;
                notifyDataSetChanged();
            }
        }
    }

    private class Callbacks implements AdapterView.OnItemClickListener, View.OnClickListener, View.OnLongClickListener, PopupWindow.OnDismissListener {
        Callbacks() {
        }

        private void a() {
            PopupWindow.OnDismissListener onDismissListener = ActivityChooserView.this.mOnDismissListener;
            if (onDismissListener != null) {
                onDismissListener.onDismiss();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view != activityChooserView.mDefaultActivityButton) {
                if (view != activityChooserView.mExpandActivityOverflowButton) {
                    throw new IllegalArgumentException();
                }
                activityChooserView.mIsSelectingDefaultActivity = false;
                activityChooserView.d(activityChooserView.mInitialActivityCount);
                return;
            }
            activityChooserView.a();
            Intent b2 = ActivityChooserView.this.mAdapter.b().b(ActivityChooserView.this.mAdapter.b().g(ActivityChooserView.this.mAdapter.c()));
            if (b2 != null) {
                b2.addFlags(WindowManagerWrapper.LayoutParams.SYSTEM_FLAG_HIDE_NON_SYSTEM_OVERLAY_WINDOWS);
                ActivityChooserView.this.getContext().startActivity(b2);
            }
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            a();
            ActionProvider actionProvider = ActivityChooserView.this.mProvider;
            if (actionProvider != null) {
                actionProvider.k(false);
            }
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            int itemViewType = ((ActivityChooserViewAdapter) adapterView.getAdapter()).getItemViewType(i2);
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    throw new IllegalArgumentException();
                }
                ActivityChooserView.this.d(Api.BaseClientBuilder.API_PRIORITY_OTHER);
                return;
            }
            ActivityChooserView.this.a();
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (activityChooserView.mIsSelectingDefaultActivity) {
                if (i2 > 0) {
                    activityChooserView.mAdapter.b().o(i2);
                    return;
                }
                return;
            }
            if (!activityChooserView.mAdapter.e()) {
                i2++;
            }
            Intent b2 = ActivityChooserView.this.mAdapter.b().b(i2);
            if (b2 != null) {
                b2.addFlags(WindowManagerWrapper.LayoutParams.SYSTEM_FLAG_HIDE_NON_SYSTEM_OVERLAY_WINDOWS);
                ActivityChooserView.this.getContext().startActivity(b2);
            }
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view != activityChooserView.mDefaultActivityButton) {
                throw new IllegalArgumentException();
            }
            if (activityChooserView.mAdapter.getCount() > 0) {
                ActivityChooserView activityChooserView2 = ActivityChooserView.this;
                activityChooserView2.mIsSelectingDefaultActivity = true;
                activityChooserView2.d(activityChooserView2.mInitialActivityCount);
            }
            return true;
        }
    }

    @RestrictTo
    public static class InnerLayout extends LinearLayout {
        private static final int[] TINT_ATTRS = {android.R.attr.background};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TintTypedArray u = TintTypedArray.u(context, attributeSet, TINT_ATTRS);
            setBackgroundDrawable(u.g(0));
            u.x();
        }
    }

    public ActivityChooserView(Context context) {
        this(context, null);
    }

    public boolean a() {
        if (!b()) {
            return true;
        }
        getListPopupWindow().dismiss();
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (!viewTreeObserver.isAlive()) {
            return true;
        }
        viewTreeObserver.removeGlobalOnLayoutListener(this.mOnGlobalLayoutListener);
        return true;
    }

    public boolean b() {
        return getListPopupWindow().isShowing();
    }

    public boolean c() {
        if (b() || !this.mIsAttachedToWindow) {
            return false;
        }
        this.mIsSelectingDefaultActivity = false;
        d(this.mInitialActivityCount);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [boolean, int] */
    void d(int i2) {
        if (this.mAdapter.b() == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
        ?? r0 = this.mDefaultActivityButton.getVisibility() == 0 ? 1 : 0;
        int a2 = this.mAdapter.a();
        if (i2 == Integer.MAX_VALUE || a2 <= i2 + r0) {
            this.mAdapter.j(false);
            this.mAdapter.h(i2);
        } else {
            this.mAdapter.j(true);
            this.mAdapter.h(i2 - 1);
        }
        ListPopupWindow listPopupWindow = getListPopupWindow();
        if (listPopupWindow.isShowing()) {
            return;
        }
        if (this.mIsSelectingDefaultActivity || r0 == 0) {
            this.mAdapter.i(true, r0);
        } else {
            this.mAdapter.i(false, false);
        }
        listPopupWindow.setContentWidth(Math.min(this.mAdapter.f(), this.mListPopupMaxWidth));
        listPopupWindow.show();
        ActionProvider actionProvider = this.mProvider;
        if (actionProvider != null) {
            actionProvider.k(true);
        }
        listPopupWindow.getListView().setContentDescription(getContext().getString(R.string.abc_activitychooserview_choose_application));
        listPopupWindow.getListView().setSelector(new ColorDrawable(0));
    }

    void e() {
        if (this.mAdapter.getCount() > 0) {
            this.mExpandActivityOverflowButton.setEnabled(true);
        } else {
            this.mExpandActivityOverflowButton.setEnabled(false);
        }
        int a2 = this.mAdapter.a();
        int d2 = this.mAdapter.d();
        if (a2 == 1 || (a2 > 1 && d2 > 0)) {
            this.mDefaultActivityButton.setVisibility(0);
            ResolveInfo c2 = this.mAdapter.c();
            PackageManager packageManager = getContext().getPackageManager();
            this.mDefaultActivityButtonImage.setImageDrawable(c2.loadIcon(packageManager));
            if (this.mDefaultActionButtonContentDescription != 0) {
                this.mDefaultActivityButton.setContentDescription(getContext().getString(this.mDefaultActionButtonContentDescription, c2.loadLabel(packageManager)));
            }
        } else {
            this.mDefaultActivityButton.setVisibility(8);
        }
        if (this.mDefaultActivityButton.getVisibility() == 0) {
            this.mActivityChooserContent.setBackgroundDrawable(this.mActivityChooserContentBackground);
        } else {
            this.mActivityChooserContent.setBackgroundDrawable(null);
        }
    }

    @RestrictTo
    public ActivityChooserModel getDataModel() {
        return this.mAdapter.b();
    }

    ListPopupWindow getListPopupWindow() {
        if (this.mListPopupWindow == null) {
            ListPopupWindow listPopupWindow = new ListPopupWindow(getContext());
            this.mListPopupWindow = listPopupWindow;
            listPopupWindow.setAdapter(this.mAdapter);
            this.mListPopupWindow.setAnchorView(this);
            this.mListPopupWindow.setModal(true);
            this.mListPopupWindow.setOnItemClickListener(this.mCallbacks);
            this.mListPopupWindow.setOnDismissListener(this.mCallbacks);
        }
        return this.mListPopupWindow;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ActivityChooserModel b2 = this.mAdapter.b();
        if (b2 != null) {
            b2.registerObserver(this.mModelDataSetObserver);
        }
        this.mIsAttachedToWindow = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActivityChooserModel b2 = this.mAdapter.b();
        if (b2 != null) {
            b2.unregisterObserver(this.mModelDataSetObserver);
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.mOnGlobalLayoutListener);
        }
        if (b()) {
            a();
        }
        this.mIsAttachedToWindow = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        this.mActivityChooserContent.layout(0, 0, i4 - i2, i5 - i3);
        if (b()) {
            return;
        }
        a();
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        View view = this.mActivityChooserContent;
        if (this.mDefaultActivityButton.getVisibility() != 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i3), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
        }
        measureChild(view, i2, i3);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    @RestrictTo
    public void setActivityChooserModel(ActivityChooserModel activityChooserModel) {
        this.mAdapter.g(activityChooserModel);
        if (b()) {
            a();
            c();
        }
    }

    public void setDefaultActionButtonContentDescription(int i2) {
        this.mDefaultActionButtonContentDescription = i2;
    }

    public void setExpandActivityOverflowButtonContentDescription(int i2) {
        this.mExpandActivityOverflowButtonImage.setContentDescription(getContext().getString(i2));
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.mExpandActivityOverflowButtonImage.setImageDrawable(drawable);
    }

    public void setInitialActivityCount(int i2) {
        this.mInitialActivityCount = i2;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    @RestrictTo
    public void setProvider(ActionProvider actionProvider) {
        this.mProvider = actionProvider;
    }

    public ActivityChooserView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActivityChooserView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mModelDataSetObserver = new DataSetObserver() { // from class: androidx.appcompat.widget.ActivityChooserView.1
            @Override // android.database.DataSetObserver
            public void onChanged() {
                super.onChanged();
                ActivityChooserView.this.mAdapter.notifyDataSetChanged();
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                super.onInvalidated();
                ActivityChooserView.this.mAdapter.notifyDataSetInvalidated();
            }
        };
        this.mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: androidx.appcompat.widget.ActivityChooserView.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (ActivityChooserView.this.b()) {
                    if (!ActivityChooserView.this.isShown()) {
                        ActivityChooserView.this.getListPopupWindow().dismiss();
                        return;
                    }
                    ActivityChooserView.this.getListPopupWindow().show();
                    ActionProvider actionProvider = ActivityChooserView.this.mProvider;
                    if (actionProvider != null) {
                        actionProvider.k(true);
                    }
                }
            }
        };
        this.mInitialActivityCount = 4;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ActivityChooserView, i2, 0);
        ViewCompat.g0(this, context, R.styleable.ActivityChooserView, attributeSet, obtainStyledAttributes, i2, 0);
        this.mInitialActivityCount = obtainStyledAttributes.getInt(R.styleable.ActivityChooserView_initialActivityCount, 4);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.ActivityChooserView_expandActivityOverflowButtonDrawable);
        obtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(R.layout.abc_activity_chooser_view, (ViewGroup) this, true);
        Callbacks callbacks = new Callbacks();
        this.mCallbacks = callbacks;
        View findViewById = findViewById(R.id.activity_chooser_view_content);
        this.mActivityChooserContent = findViewById;
        this.mActivityChooserContentBackground = findViewById.getBackground();
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.default_activity_button);
        this.mDefaultActivityButton = frameLayout;
        frameLayout.setOnClickListener(callbacks);
        frameLayout.setOnLongClickListener(callbacks);
        this.mDefaultActivityButtonImage = (ImageView) frameLayout.findViewById(R.id.image);
        FrameLayout frameLayout2 = (FrameLayout) findViewById(R.id.expand_activities_button);
        frameLayout2.setOnClickListener(callbacks);
        frameLayout2.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: androidx.appcompat.widget.ActivityChooserView.3
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                AccessibilityNodeInfoCompat.O0(accessibilityNodeInfo).e0(true);
            }
        });
        frameLayout2.setOnTouchListener(new ForwardingListener(frameLayout2) { // from class: androidx.appcompat.widget.ActivityChooserView.4
            @Override // androidx.appcompat.widget.ForwardingListener
            public ShowableListMenu b() {
                return ActivityChooserView.this.getListPopupWindow();
            }

            @Override // androidx.appcompat.widget.ForwardingListener
            protected boolean c() {
                ActivityChooserView.this.c();
                return true;
            }

            @Override // androidx.appcompat.widget.ForwardingListener
            protected boolean d() {
                ActivityChooserView.this.a();
                return true;
            }
        });
        this.mExpandActivityOverflowButton = frameLayout2;
        ImageView imageView = (ImageView) frameLayout2.findViewById(R.id.image);
        this.mExpandActivityOverflowButtonImage = imageView;
        imageView.setImageDrawable(drawable);
        ActivityChooserViewAdapter activityChooserViewAdapter = new ActivityChooserViewAdapter();
        this.mAdapter = activityChooserViewAdapter;
        activityChooserViewAdapter.registerDataSetObserver(new DataSetObserver() { // from class: androidx.appcompat.widget.ActivityChooserView.5
            @Override // android.database.DataSetObserver
            public void onChanged() {
                super.onChanged();
                ActivityChooserView.this.e();
            }
        });
        Resources resources = context.getResources();
        this.mListPopupMaxWidth = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
    }
}
