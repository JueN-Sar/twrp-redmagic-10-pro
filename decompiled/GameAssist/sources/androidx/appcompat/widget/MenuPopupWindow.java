package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.transition.Transition;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.ListMenuItemView;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;

@RestrictTo
/* loaded from: classes.dex */
public class MenuPopupWindow extends ListPopupWindow implements MenuItemHoverListener {

    /* renamed from: c, reason: collision with root package name */
    private MenuItemHoverListener f917c;

    @RequiresApi
    static class Api23Impl {
        @DoNotInline
        static void a(PopupWindow popupWindow, Transition transition) {
            popupWindow.setEnterTransition(transition);
        }

        @DoNotInline
        static void b(PopupWindow popupWindow, Transition transition) {
            popupWindow.setExitTransition(transition);
        }
    }

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static void a(PopupWindow popupWindow, boolean z) {
            popupWindow.setTouchModal(z);
        }
    }

    @RestrictTo
    public static class MenuDropDownListView extends DropDownListView {
        final int mAdvanceKey;
        private MenuItemHoverListener mHoverListener;
        private MenuItem mHoveredMenuItem;
        final int mRetreatKey;

        public MenuDropDownListView(Context context, boolean z) {
            super(context, z);
            if (1 == context.getResources().getConfiguration().getLayoutDirection()) {
                this.mAdvanceKey = 21;
                this.mRetreatKey = 22;
            } else {
                this.mAdvanceKey = 22;
                this.mRetreatKey = 21;
            }
        }

        @Override // androidx.appcompat.widget.DropDownListView
        public /* bridge */ /* synthetic */ int d(int i2, int i3, int i4, int i5, int i6) {
            return super.d(i2, i3, i4, i5, i6);
        }

        @Override // androidx.appcompat.widget.DropDownListView
        public /* bridge */ /* synthetic */ boolean e(MotionEvent motionEvent, int i2) {
            return super.e(motionEvent, i2);
        }

        @Override // androidx.appcompat.widget.DropDownListView, android.view.ViewGroup, android.view.View
        public /* bridge */ /* synthetic */ boolean hasFocus() {
            return super.hasFocus();
        }

        @Override // androidx.appcompat.widget.DropDownListView, android.view.View
        public /* bridge */ /* synthetic */ boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        @Override // androidx.appcompat.widget.DropDownListView, android.view.View
        public /* bridge */ /* synthetic */ boolean isFocused() {
            return super.isFocused();
        }

        @Override // androidx.appcompat.widget.DropDownListView, android.view.View
        public /* bridge */ /* synthetic */ boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        @Override // androidx.appcompat.widget.DropDownListView
        public /* bridge */ /* synthetic */ int lookForSelectablePosition(int i2, boolean z) {
            return super.lookForSelectablePosition(i2, z);
        }

        @Override // androidx.appcompat.widget.DropDownListView, android.view.View
        public boolean onHoverEvent(MotionEvent motionEvent) {
            MenuAdapter menuAdapter;
            int i2;
            int pointToPosition;
            int i3;
            if (this.mHoverListener != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    i2 = headerViewListAdapter.getHeadersCount();
                    menuAdapter = (MenuAdapter) headerViewListAdapter.getWrappedAdapter();
                } else {
                    menuAdapter = (MenuAdapter) adapter;
                    i2 = 0;
                }
                MenuItemImpl item = (motionEvent.getAction() == 10 || (pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) == -1 || (i3 = pointToPosition - i2) < 0 || i3 >= menuAdapter.getCount()) ? null : menuAdapter.getItem(i3);
                MenuItem menuItem = this.mHoveredMenuItem;
                if (menuItem != item) {
                    MenuBuilder b2 = menuAdapter.b();
                    if (menuItem != null) {
                        this.mHoverListener.b(b2, menuItem);
                    }
                    this.mHoveredMenuItem = item;
                    if (item != null) {
                        this.mHoverListener.a(b2, item);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i2, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i2 == this.mAdvanceKey) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            }
            if (listMenuItemView == null || i2 != this.mRetreatKey) {
                return super.onKeyDown(i2, keyEvent);
            }
            setSelection(-1);
            ListAdapter adapter = getAdapter();
            (adapter instanceof HeaderViewListAdapter ? (MenuAdapter) ((HeaderViewListAdapter) adapter).getWrappedAdapter() : (MenuAdapter) adapter).b().e(false);
            return true;
        }

        @Override // androidx.appcompat.widget.DropDownListView, android.widget.AbsListView, android.view.View
        public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }

        public void setHoverListener(MenuItemHoverListener menuItemHoverListener) {
            this.mHoverListener = menuItemHoverListener;
        }

        @Override // androidx.appcompat.widget.DropDownListView, android.widget.AbsListView
        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }
    }

    public MenuPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    @Override // androidx.appcompat.widget.MenuItemHoverListener
    public void a(MenuBuilder menuBuilder, MenuItem menuItem) {
        MenuItemHoverListener menuItemHoverListener = this.f917c;
        if (menuItemHoverListener != null) {
            menuItemHoverListener.a(menuBuilder, menuItem);
        }
    }

    @Override // androidx.appcompat.widget.MenuItemHoverListener
    public void b(MenuBuilder menuBuilder, MenuItem menuItem) {
        MenuItemHoverListener menuItemHoverListener = this.f917c;
        if (menuItemHoverListener != null) {
            menuItemHoverListener.b(menuBuilder, menuItem);
        }
    }

    @Override // androidx.appcompat.widget.ListPopupWindow
    DropDownListView createDropDownListView(Context context, boolean z) {
        MenuDropDownListView menuDropDownListView = new MenuDropDownListView(context, z);
        menuDropDownListView.setHoverListener(this);
        return menuDropDownListView;
    }

    public void h(Object obj) {
        Api23Impl.a(this.mPopup, (Transition) obj);
    }

    public void i(Object obj) {
        Api23Impl.b(this.mPopup, (Transition) obj);
    }

    public void j(MenuItemHoverListener menuItemHoverListener) {
        this.f917c = menuItemHoverListener;
    }

    public void k(boolean z) {
        Api29Impl.a(this.mPopup, z);
    }
}
