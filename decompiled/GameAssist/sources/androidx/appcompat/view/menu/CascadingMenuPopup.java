package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.MenuItemHoverListener;
import androidx.appcompat.widget.MenuPopupWindow;
import androidx.core.view.GravityCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
final class CascadingMenuPopup extends MenuPopup implements MenuPresenter, View.OnKeyListener, PopupWindow.OnDismissListener {
    private static final int H = R.layout.abc_cascading_menu_item_layout;
    private int A;
    private boolean C;
    private MenuPresenter.Callback D;
    ViewTreeObserver E;
    private PopupWindow.OnDismissListener F;
    boolean G;

    /* renamed from: h, reason: collision with root package name */
    private final Context f513h;

    /* renamed from: i, reason: collision with root package name */
    private final int f514i;

    /* renamed from: j, reason: collision with root package name */
    private final int f515j;

    /* renamed from: k, reason: collision with root package name */
    private final int f516k;

    /* renamed from: l, reason: collision with root package name */
    private final boolean f517l;

    /* renamed from: m, reason: collision with root package name */
    final Handler f518m;
    private View u;
    View v;
    private boolean x;
    private boolean y;
    private int z;

    /* renamed from: n, reason: collision with root package name */
    private final List f519n = new ArrayList();

    /* renamed from: o, reason: collision with root package name */
    final List f520o = new ArrayList();

    /* renamed from: p, reason: collision with root package name */
    final ViewTreeObserver.OnGlobalLayoutListener f521p = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: androidx.appcompat.view.menu.CascadingMenuPopup.1
        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!CascadingMenuPopup.this.isShowing() || CascadingMenuPopup.this.f520o.size() <= 0 || ((CascadingMenuInfo) CascadingMenuPopup.this.f520o.get(0)).f532a.isModal()) {
                return;
            }
            View view = CascadingMenuPopup.this.v;
            if (view == null || !view.isShown()) {
                CascadingMenuPopup.this.dismiss();
                return;
            }
            Iterator it = CascadingMenuPopup.this.f520o.iterator();
            while (it.hasNext()) {
                ((CascadingMenuInfo) it.next()).f532a.show();
            }
        }
    };

    /* renamed from: q, reason: collision with root package name */
    private final View.OnAttachStateChangeListener f522q = new View.OnAttachStateChangeListener() { // from class: androidx.appcompat.view.menu.CascadingMenuPopup.2
        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = CascadingMenuPopup.this.E;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    CascadingMenuPopup.this.E = view.getViewTreeObserver();
                }
                CascadingMenuPopup cascadingMenuPopup = CascadingMenuPopup.this;
                cascadingMenuPopup.E.removeGlobalOnLayoutListener(cascadingMenuPopup.f521p);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    };

    /* renamed from: r, reason: collision with root package name */
    private final MenuItemHoverListener f523r = new MenuItemHoverListener() { // from class: androidx.appcompat.view.menu.CascadingMenuPopup.3
        @Override // androidx.appcompat.widget.MenuItemHoverListener
        public void a(final MenuBuilder menuBuilder, final MenuItem menuItem) {
            CascadingMenuPopup.this.f518m.removeCallbacksAndMessages(null);
            int size = CascadingMenuPopup.this.f520o.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    i2 = -1;
                    break;
                } else if (menuBuilder == ((CascadingMenuInfo) CascadingMenuPopup.this.f520o.get(i2)).f533b) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 == -1) {
                return;
            }
            int i3 = i2 + 1;
            final CascadingMenuInfo cascadingMenuInfo = i3 < CascadingMenuPopup.this.f520o.size() ? (CascadingMenuInfo) CascadingMenuPopup.this.f520o.get(i3) : null;
            CascadingMenuPopup.this.f518m.postAtTime(new Runnable() { // from class: androidx.appcompat.view.menu.CascadingMenuPopup.3.1
                @Override // java.lang.Runnable
                public void run() {
                    CascadingMenuInfo cascadingMenuInfo2 = cascadingMenuInfo;
                    if (cascadingMenuInfo2 != null) {
                        CascadingMenuPopup.this.G = true;
                        cascadingMenuInfo2.f533b.e(false);
                        CascadingMenuPopup.this.G = false;
                    }
                    if (menuItem.isEnabled() && menuItem.hasSubMenu()) {
                        menuBuilder.O(menuItem, 4);
                    }
                }
            }, menuBuilder, SystemClock.uptimeMillis() + 200);
        }

        @Override // androidx.appcompat.widget.MenuItemHoverListener
        public void b(MenuBuilder menuBuilder, MenuItem menuItem) {
            CascadingMenuPopup.this.f518m.removeCallbacksAndMessages(menuBuilder);
        }
    };

    /* renamed from: s, reason: collision with root package name */
    private int f524s = 0;
    private int t = 0;
    private boolean B = false;
    private int w = y();

    private static class CascadingMenuInfo {

        /* renamed from: a, reason: collision with root package name */
        public final MenuPopupWindow f532a;

        /* renamed from: b, reason: collision with root package name */
        public final MenuBuilder f533b;

        /* renamed from: c, reason: collision with root package name */
        public final int f534c;

        public CascadingMenuInfo(MenuPopupWindow menuPopupWindow, MenuBuilder menuBuilder, int i2) {
            this.f532a = menuPopupWindow;
            this.f533b = menuBuilder;
            this.f534c = i2;
        }

        public ListView a() {
            return this.f532a.getListView();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface HorizPosition {
    }

    public CascadingMenuPopup(Context context, View view, int i2, int i3, boolean z) {
        this.f513h = context;
        this.u = view;
        this.f515j = i2;
        this.f516k = i3;
        this.f517l = z;
        Resources resources = context.getResources();
        this.f514i = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.f518m = new Handler();
    }

    private void A(MenuBuilder menuBuilder) {
        CascadingMenuInfo cascadingMenuInfo;
        View view;
        LayoutInflater from = LayoutInflater.from(this.f513h);
        MenuAdapter menuAdapter = new MenuAdapter(menuBuilder, from, this.f517l, H);
        if (!isShowing() && this.B) {
            menuAdapter.d(true);
        } else if (isShowing()) {
            menuAdapter.d(MenuPopup.s(menuBuilder));
        }
        int j2 = MenuPopup.j(menuAdapter, null, this.f513h, this.f514i);
        MenuPopupWindow u = u();
        u.setAdapter(menuAdapter);
        u.setContentWidth(j2);
        u.setDropDownGravity(this.t);
        if (this.f520o.size() > 0) {
            List list = this.f520o;
            cascadingMenuInfo = (CascadingMenuInfo) list.get(list.size() - 1);
            view = x(cascadingMenuInfo, menuBuilder);
        } else {
            cascadingMenuInfo = null;
            view = null;
        }
        if (view != null) {
            u.k(false);
            u.h(null);
            int z = z(j2);
            boolean z2 = z == 1;
            this.w = z;
            u.setAnchorView(view);
            if ((this.t & 5) != 5) {
                j2 = z2 ? view.getWidth() : 0 - j2;
            } else if (!z2) {
                j2 = 0 - view.getWidth();
            }
            u.setHorizontalOffset(j2);
            u.setOverlapAnchor(true);
            u.setVerticalOffset(0);
        } else {
            if (this.x) {
                u.setHorizontalOffset(this.z);
            }
            if (this.y) {
                u.setVerticalOffset(this.A);
            }
            u.setEpicenterBounds(i());
        }
        this.f520o.add(new CascadingMenuInfo(u, menuBuilder, this.w));
        u.show();
        ListView listView = u.getListView();
        listView.setOnKeyListener(this);
        if (cascadingMenuInfo == null && this.C && menuBuilder.z() != null) {
            FrameLayout frameLayout = (FrameLayout) from.inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) listView, false);
            TextView textView = (TextView) frameLayout.findViewById(android.R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(menuBuilder.z());
            listView.addHeaderView(frameLayout, null, false);
            u.show();
        }
    }

    private MenuPopupWindow u() {
        MenuPopupWindow menuPopupWindow = new MenuPopupWindow(this.f513h, null, this.f515j, this.f516k);
        menuPopupWindow.j(this.f523r);
        menuPopupWindow.setOnItemClickListener(this);
        menuPopupWindow.setOnDismissListener(this);
        menuPopupWindow.setAnchorView(this.u);
        menuPopupWindow.setDropDownGravity(this.t);
        menuPopupWindow.setModal(true);
        menuPopupWindow.setInputMethodMode(2);
        return menuPopupWindow;
    }

    private int v(MenuBuilder menuBuilder) {
        int size = this.f520o.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (menuBuilder == ((CascadingMenuInfo) this.f520o.get(i2)).f533b) {
                return i2;
            }
        }
        return -1;
    }

    private MenuItem w(MenuBuilder menuBuilder, MenuBuilder menuBuilder2) {
        int size = menuBuilder.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = menuBuilder.getItem(i2);
            if (item.hasSubMenu() && menuBuilder2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    private View x(CascadingMenuInfo cascadingMenuInfo, MenuBuilder menuBuilder) {
        MenuAdapter menuAdapter;
        int i2;
        int firstVisiblePosition;
        MenuItem w = w(cascadingMenuInfo.f533b, menuBuilder);
        if (w == null) {
            return null;
        }
        ListView a2 = cascadingMenuInfo.a();
        ListAdapter adapter = a2.getAdapter();
        int i3 = 0;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            i2 = headerViewListAdapter.getHeadersCount();
            menuAdapter = (MenuAdapter) headerViewListAdapter.getWrappedAdapter();
        } else {
            menuAdapter = (MenuAdapter) adapter;
            i2 = 0;
        }
        int count = menuAdapter.getCount();
        while (true) {
            if (i3 >= count) {
                i3 = -1;
                break;
            }
            if (w == menuAdapter.getItem(i3)) {
                break;
            }
            i3++;
        }
        if (i3 != -1 && (firstVisiblePosition = (i3 + i2) - a2.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < a2.getChildCount()) {
            return a2.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    private int y() {
        return this.u.getLayoutDirection() == 1 ? 0 : 1;
    }

    private int z(int i2) {
        List list = this.f520o;
        ListView a2 = ((CascadingMenuInfo) list.get(list.size() - 1)).a();
        int[] iArr = new int[2];
        a2.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.v.getWindowVisibleDisplayFrame(rect);
        return this.w == 1 ? (iArr[0] + a2.getWidth()) + i2 > rect.right ? 0 : 1 : iArr[0] - i2 < 0 ? 1 : 0;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void a(MenuBuilder menuBuilder, boolean z) {
        int v = v(menuBuilder);
        if (v < 0) {
            return;
        }
        int i2 = v + 1;
        if (i2 < this.f520o.size()) {
            ((CascadingMenuInfo) this.f520o.get(i2)).f533b.e(false);
        }
        CascadingMenuInfo cascadingMenuInfo = (CascadingMenuInfo) this.f520o.remove(v);
        cascadingMenuInfo.f533b.R(this);
        if (this.G) {
            cascadingMenuInfo.f532a.i(null);
            cascadingMenuInfo.f532a.setAnimationStyle(0);
        }
        cascadingMenuInfo.f532a.dismiss();
        int size = this.f520o.size();
        if (size > 0) {
            this.w = ((CascadingMenuInfo) this.f520o.get(size - 1)).f534c;
        } else {
            this.w = y();
        }
        if (size != 0) {
            if (z) {
                ((CascadingMenuInfo) this.f520o.get(0)).f533b.e(false);
                return;
            }
            return;
        }
        dismiss();
        MenuPresenter.Callback callback = this.D;
        if (callback != null) {
            callback.a(menuBuilder, true);
        }
        ViewTreeObserver viewTreeObserver = this.E;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.E.removeGlobalOnLayoutListener(this.f521p);
            }
            this.E = null;
        }
        this.v.removeOnAttachStateChangeListener(this.f522q);
        this.F.onDismiss();
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void c(MenuPresenter.Callback callback) {
        this.D = callback;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean d(SubMenuBuilder subMenuBuilder) {
        for (CascadingMenuInfo cascadingMenuInfo : this.f520o) {
            if (subMenuBuilder == cascadingMenuInfo.f533b) {
                cascadingMenuInfo.a().requestFocus();
                return true;
            }
        }
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        g(subMenuBuilder);
        MenuPresenter.Callback callback = this.D;
        if (callback != null) {
            callback.b(subMenuBuilder);
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void dismiss() {
        int size = this.f520o.size();
        if (size > 0) {
            CascadingMenuInfo[] cascadingMenuInfoArr = (CascadingMenuInfo[]) this.f520o.toArray(new CascadingMenuInfo[size]);
            for (int i2 = size - 1; i2 >= 0; i2--) {
                CascadingMenuInfo cascadingMenuInfo = cascadingMenuInfoArr[i2];
                if (cascadingMenuInfo.f532a.isShowing()) {
                    cascadingMenuInfo.f532a.dismiss();
                }
            }
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void g(MenuBuilder menuBuilder) {
        menuBuilder.c(this, this.f513h);
        if (isShowing()) {
            A(menuBuilder);
        } else {
            this.f519n.add(menuBuilder);
        }
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public ListView getListView() {
        if (this.f520o.isEmpty()) {
            return null;
        }
        return ((CascadingMenuInfo) this.f520o.get(r1.size() - 1)).a();
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    protected boolean h() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public boolean isShowing() {
        return this.f520o.size() > 0 && ((CascadingMenuInfo) this.f520o.get(0)).f532a.isShowing();
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void k(View view) {
        if (this.u != view) {
            this.u = view;
            this.t = GravityCompat.b(this.f524s, view.getLayoutDirection());
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void m(boolean z) {
        this.B = z;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void n(int i2) {
        if (this.f524s != i2) {
            this.f524s = i2;
            this.t = GravityCompat.b(i2, this.u.getLayoutDirection());
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void o(int i2) {
        this.x = true;
        this.z = i2;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        CascadingMenuInfo cascadingMenuInfo;
        int size = this.f520o.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                cascadingMenuInfo = null;
                break;
            }
            cascadingMenuInfo = (CascadingMenuInfo) this.f520o.get(i2);
            if (!cascadingMenuInfo.f532a.isShowing()) {
                break;
            } else {
                i2++;
            }
        }
        if (cascadingMenuInfo != null) {
            cascadingMenuInfo.f533b.e(false);
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
        this.F = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void q(boolean z) {
        this.C = z;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void r(int i2) {
        this.y = true;
        this.A = i2;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void show() {
        if (isShowing()) {
            return;
        }
        Iterator it = this.f519n.iterator();
        while (it.hasNext()) {
            A((MenuBuilder) it.next());
        }
        this.f519n.clear();
        View view = this.u;
        this.v = view;
        if (view != null) {
            boolean z = this.E == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.E = viewTreeObserver;
            if (z) {
                viewTreeObserver.addOnGlobalLayoutListener(this.f521p);
            }
            this.v.addOnAttachStateChangeListener(this.f522q);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        Iterator it = this.f520o.iterator();
        while (it.hasNext()) {
            MenuPopup.t(((CascadingMenuInfo) it.next()).a().getAdapter()).notifyDataSetChanged();
        }
    }
}
