package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.MenuPopupWindow;

/* loaded from: classes.dex */
final class StandardMenuPopup extends MenuPopup implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, MenuPresenter, View.OnKeyListener {
    private static final int B = R.layout.abc_popup_menu_item_layout;
    private boolean A;

    /* renamed from: h, reason: collision with root package name */
    private final Context f620h;

    /* renamed from: i, reason: collision with root package name */
    private final MenuBuilder f621i;

    /* renamed from: j, reason: collision with root package name */
    private final MenuAdapter f622j;

    /* renamed from: k, reason: collision with root package name */
    private final boolean f623k;

    /* renamed from: l, reason: collision with root package name */
    private final int f624l;

    /* renamed from: m, reason: collision with root package name */
    private final int f625m;

    /* renamed from: n, reason: collision with root package name */
    private final int f626n;

    /* renamed from: o, reason: collision with root package name */
    final MenuPopupWindow f627o;

    /* renamed from: r, reason: collision with root package name */
    private PopupWindow.OnDismissListener f630r;

    /* renamed from: s, reason: collision with root package name */
    private View f631s;
    View t;
    private MenuPresenter.Callback u;
    ViewTreeObserver v;
    private boolean w;
    private boolean x;
    private int y;

    /* renamed from: p, reason: collision with root package name */
    final ViewTreeObserver.OnGlobalLayoutListener f628p = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: androidx.appcompat.view.menu.StandardMenuPopup.1
        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!StandardMenuPopup.this.isShowing() || StandardMenuPopup.this.f627o.isModal()) {
                return;
            }
            View view = StandardMenuPopup.this.t;
            if (view == null || !view.isShown()) {
                StandardMenuPopup.this.dismiss();
            } else {
                StandardMenuPopup.this.f627o.show();
            }
        }
    };

    /* renamed from: q, reason: collision with root package name */
    private final View.OnAttachStateChangeListener f629q = new View.OnAttachStateChangeListener() { // from class: androidx.appcompat.view.menu.StandardMenuPopup.2
        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = StandardMenuPopup.this.v;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    StandardMenuPopup.this.v = view.getViewTreeObserver();
                }
                StandardMenuPopup standardMenuPopup = StandardMenuPopup.this;
                standardMenuPopup.v.removeGlobalOnLayoutListener(standardMenuPopup.f628p);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    };
    private int z = 0;

    public StandardMenuPopup(Context context, MenuBuilder menuBuilder, View view, int i2, int i3, boolean z) {
        this.f620h = context;
        this.f621i = menuBuilder;
        this.f623k = z;
        this.f622j = new MenuAdapter(menuBuilder, LayoutInflater.from(context), z, B);
        this.f625m = i2;
        this.f626n = i3;
        Resources resources = context.getResources();
        this.f624l = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.f631s = view;
        this.f627o = new MenuPopupWindow(context, null, i2, i3);
        menuBuilder.c(this, context);
    }

    private boolean u() {
        View view;
        if (isShowing()) {
            return true;
        }
        if (this.w || (view = this.f631s) == null) {
            return false;
        }
        this.t = view;
        this.f627o.setOnDismissListener(this);
        this.f627o.setOnItemClickListener(this);
        this.f627o.setModal(true);
        View view2 = this.t;
        boolean z = this.v == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.v = viewTreeObserver;
        if (z) {
            viewTreeObserver.addOnGlobalLayoutListener(this.f628p);
        }
        view2.addOnAttachStateChangeListener(this.f629q);
        this.f627o.setAnchorView(view2);
        this.f627o.setDropDownGravity(this.z);
        if (!this.x) {
            this.y = MenuPopup.j(this.f622j, null, this.f620h, this.f624l);
            this.x = true;
        }
        this.f627o.setContentWidth(this.y);
        this.f627o.setInputMethodMode(2);
        this.f627o.setEpicenterBounds(i());
        this.f627o.show();
        ListView listView = this.f627o.getListView();
        listView.setOnKeyListener(this);
        if (this.A && this.f621i.z() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.f620h).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) listView, false);
            TextView textView = (TextView) frameLayout.findViewById(android.R.id.title);
            if (textView != null) {
                textView.setText(this.f621i.z());
            }
            frameLayout.setEnabled(false);
            listView.addHeaderView(frameLayout, null, false);
        }
        this.f627o.setAdapter(this.f622j);
        this.f627o.show();
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void a(MenuBuilder menuBuilder, boolean z) {
        if (menuBuilder != this.f621i) {
            return;
        }
        dismiss();
        MenuPresenter.Callback callback = this.u;
        if (callback != null) {
            callback.a(menuBuilder, z);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void c(MenuPresenter.Callback callback) {
        this.u = callback;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean d(SubMenuBuilder subMenuBuilder) {
        if (subMenuBuilder.hasVisibleItems()) {
            MenuPopupHelper menuPopupHelper = new MenuPopupHelper(this.f620h, subMenuBuilder, this.t, this.f623k, this.f625m, this.f626n);
            menuPopupHelper.j(this.u);
            menuPopupHelper.g(MenuPopup.s(subMenuBuilder));
            menuPopupHelper.i(this.f630r);
            this.f630r = null;
            this.f621i.e(false);
            int horizontalOffset = this.f627o.getHorizontalOffset();
            int verticalOffset = this.f627o.getVerticalOffset();
            if ((Gravity.getAbsoluteGravity(this.z, this.f631s.getLayoutDirection()) & 7) == 5) {
                horizontalOffset += this.f631s.getWidth();
            }
            if (menuPopupHelper.n(horizontalOffset, verticalOffset)) {
                MenuPresenter.Callback callback = this.u;
                if (callback == null) {
                    return true;
                }
                callback.b(subMenuBuilder);
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void dismiss() {
        if (isShowing()) {
            this.f627o.dismiss();
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void g(MenuBuilder menuBuilder) {
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public ListView getListView() {
        return this.f627o.getListView();
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public boolean isShowing() {
        return !this.w && this.f627o.isShowing();
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void k(View view) {
        this.f631s = view;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void m(boolean z) {
        this.f622j.d(z);
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void n(int i2) {
        this.z = i2;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void o(int i2) {
        this.f627o.setHorizontalOffset(i2);
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.w = true;
        this.f621i.close();
        ViewTreeObserver viewTreeObserver = this.v;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.v = this.t.getViewTreeObserver();
            }
            this.v.removeGlobalOnLayoutListener(this.f628p);
            this.v = null;
        }
        this.t.removeOnAttachStateChangeListener(this.f629q);
        PopupWindow.OnDismissListener onDismissListener = this.f630r;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i2 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable) {
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        return null;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void p(PopupWindow.OnDismissListener onDismissListener) {
        this.f630r = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void q(boolean z) {
        this.A = z;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void r(int i2) {
        this.f627o.setVerticalOffset(i2);
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void show() {
        if (!u()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        this.x = false;
        MenuAdapter menuAdapter = this.f622j;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }
}
