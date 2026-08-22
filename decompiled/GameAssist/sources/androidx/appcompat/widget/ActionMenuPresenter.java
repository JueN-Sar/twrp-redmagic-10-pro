package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.BaseMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.appcompat.widget.ActionMenuView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ActionProvider;
import java.util.ArrayList;

/* loaded from: classes.dex */
class ActionMenuPresenter extends BaseMenuPresenter implements ActionProvider.SubUiVisibilityListener {
    private boolean A;
    private boolean B;
    private int C;
    private final SparseBooleanArray D;
    OverflowPopup E;
    ActionButtonSubmenu F;
    OpenOverflowRunnable G;
    private ActionMenuPopupCallback H;
    final PopupPresenterCallback I;
    int J;

    /* renamed from: q, reason: collision with root package name */
    OverflowMenuButton f645q;

    /* renamed from: r, reason: collision with root package name */
    private Drawable f646r;

    /* renamed from: s, reason: collision with root package name */
    private boolean f647s;
    private boolean t;
    private boolean u;
    private int v;
    private int w;
    private int x;
    private boolean y;
    private boolean z;

    private class ActionButtonSubmenu extends MenuPopupHelper {
        public ActionButtonSubmenu(Context context, SubMenuBuilder subMenuBuilder, View view) {
            super(context, subMenuBuilder, view, false, R.attr.actionOverflowMenuStyle);
            if (!((MenuItemImpl) subMenuBuilder.getItem()).l()) {
                View view2 = ActionMenuPresenter.this.f645q;
                f(view2 == null ? (View) ((BaseMenuPresenter) ActionMenuPresenter.this).f508o : view2);
            }
            j(ActionMenuPresenter.this.I);
        }

        @Override // androidx.appcompat.view.menu.MenuPopupHelper
        protected void e() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            actionMenuPresenter.F = null;
            actionMenuPresenter.J = 0;
            super.e();
        }
    }

    private class ActionMenuPopupCallback extends ActionMenuItemView.PopupCallback {
        ActionMenuPopupCallback() {
        }

        @Override // androidx.appcompat.view.menu.ActionMenuItemView.PopupCallback
        public ShowableListMenu a() {
            ActionButtonSubmenu actionButtonSubmenu = ActionMenuPresenter.this.F;
            if (actionButtonSubmenu != null) {
                return actionButtonSubmenu.c();
            }
            return null;
        }
    }

    private class OpenOverflowRunnable implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        private OverflowPopup f650c;

        public OpenOverflowRunnable(OverflowPopup overflowPopup) {
            this.f650c = overflowPopup;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (((BaseMenuPresenter) ActionMenuPresenter.this).f502i != null) {
                ((BaseMenuPresenter) ActionMenuPresenter.this).f502i.d();
            }
            View view = (View) ((BaseMenuPresenter) ActionMenuPresenter.this).f508o;
            if (view != null && view.getWindowToken() != null && this.f650c.m()) {
                ActionMenuPresenter.this.E = this.f650c;
            }
            ActionMenuPresenter.this.G = null;
        }
    }

    private class OverflowMenuButton extends AppCompatImageView implements ActionMenuView.ActionMenuChildView {
        public OverflowMenuButton(Context context) {
            super(context, null, R.attr.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            TooltipCompat.a(this, getContentDescription());
            setOnTouchListener(new ForwardingListener(this) { // from class: androidx.appcompat.widget.ActionMenuPresenter.OverflowMenuButton.1
                @Override // androidx.appcompat.widget.ForwardingListener
                public ShowableListMenu b() {
                    OverflowPopup overflowPopup = ActionMenuPresenter.this.E;
                    if (overflowPopup == null) {
                        return null;
                    }
                    return overflowPopup.c();
                }

                @Override // androidx.appcompat.widget.ForwardingListener
                public boolean c() {
                    ActionMenuPresenter.this.I();
                    return true;
                }

                @Override // androidx.appcompat.widget.ForwardingListener
                public boolean d() {
                    ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
                    if (actionMenuPresenter.G != null) {
                        return false;
                    }
                    actionMenuPresenter.z();
                    return true;
                }
            });
        }

        @Override // androidx.appcompat.widget.ActionMenuView.ActionMenuChildView
        public boolean a() {
            return false;
        }

        @Override // androidx.appcompat.widget.ActionMenuView.ActionMenuChildView
        public boolean b() {
            return false;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            ActionMenuPresenter.this.I();
            return true;
        }

        @Override // android.widget.ImageView
        protected boolean setFrame(int i2, int i3, int i4, int i5) {
            boolean frame = super.setFrame(i2, i3, i4, i5);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int max = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                DrawableCompat.l(background, paddingLeft - max, paddingTop - max, paddingLeft + max, paddingTop + max);
            }
            return frame;
        }
    }

    private class OverflowPopup extends MenuPopupHelper {
        public OverflowPopup(Context context, MenuBuilder menuBuilder, View view, boolean z) {
            super(context, menuBuilder, view, z, R.attr.actionOverflowMenuStyle);
            h(8388613);
            j(ActionMenuPresenter.this.I);
        }

        @Override // androidx.appcompat.view.menu.MenuPopupHelper
        protected void e() {
            if (((BaseMenuPresenter) ActionMenuPresenter.this).f502i != null) {
                ((BaseMenuPresenter) ActionMenuPresenter.this).f502i.close();
            }
            ActionMenuPresenter.this.E = null;
            super.e();
        }
    }

    private class PopupPresenterCallback implements MenuPresenter.Callback {
        PopupPresenterCallback() {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public void a(MenuBuilder menuBuilder, boolean z) {
            if (menuBuilder instanceof SubMenuBuilder) {
                menuBuilder.F().e(false);
            }
            MenuPresenter.Callback k2 = ActionMenuPresenter.this.k();
            if (k2 != null) {
                k2.a(menuBuilder, z);
            }
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public boolean b(MenuBuilder menuBuilder) {
            if (menuBuilder == ((BaseMenuPresenter) ActionMenuPresenter.this).f502i) {
                return false;
            }
            ActionMenuPresenter.this.J = ((SubMenuBuilder) menuBuilder).getItem().getItemId();
            MenuPresenter.Callback k2 = ActionMenuPresenter.this.k();
            if (k2 != null) {
                return k2.b(menuBuilder);
            }
            return false;
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    private static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: androidx.appcompat.widget.ActionMenuPresenter.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: c, reason: collision with root package name */
        public int f656c;

        SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f656c);
        }

        SavedState(Parcel parcel) {
            this.f656c = parcel.readInt();
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, R.layout.abc_action_menu_layout, R.layout.abc_action_menu_item_layout);
        this.D = new SparseBooleanArray();
        this.I = new PopupPresenterCallback();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private View x(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.f508o;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if ((childAt instanceof MenuView.ItemView) && ((MenuView.ItemView) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public boolean A() {
        ActionButtonSubmenu actionButtonSubmenu = this.F;
        if (actionButtonSubmenu == null) {
            return false;
        }
        actionButtonSubmenu.b();
        return true;
    }

    public boolean B() {
        return this.G != null || C();
    }

    public boolean C() {
        OverflowPopup overflowPopup = this.E;
        return overflowPopup != null && overflowPopup.d();
    }

    public void D(Configuration configuration) {
        if (!this.y) {
            this.x = ActionBarPolicy.b(this.f501h).d();
        }
        MenuBuilder menuBuilder = this.f502i;
        if (menuBuilder != null) {
            menuBuilder.N(true);
        }
    }

    public void E(boolean z) {
        this.B = z;
    }

    public void F(ActionMenuView actionMenuView) {
        this.f508o = actionMenuView;
        actionMenuView.a(this.f502i);
    }

    public void G(Drawable drawable) {
        OverflowMenuButton overflowMenuButton = this.f645q;
        if (overflowMenuButton != null) {
            overflowMenuButton.setImageDrawable(drawable);
        } else {
            this.f647s = true;
            this.f646r = drawable;
        }
    }

    public void H(boolean z) {
        this.t = z;
        this.u = true;
    }

    public boolean I() {
        MenuBuilder menuBuilder;
        if (!this.t || C() || (menuBuilder = this.f502i) == null || this.f508o == null || this.G != null || menuBuilder.B().isEmpty()) {
            return false;
        }
        OpenOverflowRunnable openOverflowRunnable = new OpenOverflowRunnable(new OverflowPopup(this.f501h, this.f502i, this.f645q, true));
        this.G = openOverflowRunnable;
        ((View) this.f508o).post(openOverflowRunnable);
        return true;
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter, androidx.appcompat.view.menu.MenuPresenter
    public void a(MenuBuilder menuBuilder, boolean z) {
        w();
        super.a(menuBuilder, z);
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter, androidx.appcompat.view.menu.MenuPresenter
    public boolean d(SubMenuBuilder subMenuBuilder) {
        boolean z = false;
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
        while (subMenuBuilder2.j0() != this.f502i) {
            subMenuBuilder2 = (SubMenuBuilder) subMenuBuilder2.j0();
        }
        View x = x(subMenuBuilder2.getItem());
        if (x == null) {
            return false;
        }
        this.J = subMenuBuilder.getItem().getItemId();
        int size = subMenuBuilder.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            MenuItem item = subMenuBuilder.getItem(i2);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
            i2++;
        }
        ActionButtonSubmenu actionButtonSubmenu = new ActionButtonSubmenu(this.f501h, subMenuBuilder, x);
        this.F = actionButtonSubmenu;
        actionButtonSubmenu.g(z);
        this.F.k();
        super.d(subMenuBuilder);
        return true;
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter, androidx.appcompat.view.menu.MenuPresenter
    public void f(Context context, MenuBuilder menuBuilder) {
        super.f(context, menuBuilder);
        Resources resources = context.getResources();
        ActionBarPolicy b2 = ActionBarPolicy.b(context);
        if (!this.u) {
            this.t = b2.h();
        }
        if (!this.A) {
            this.v = b2.c();
        }
        if (!this.y) {
            this.x = b2.d();
        }
        int i2 = this.v;
        if (this.t) {
            if (this.f645q == null) {
                OverflowMenuButton overflowMenuButton = new OverflowMenuButton(this.f500c);
                this.f645q = overflowMenuButton;
                if (this.f647s) {
                    overflowMenuButton.setImageDrawable(this.f646r);
                    this.f646r = null;
                    this.f647s = false;
                }
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.f645q.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i2 -= this.f645q.getMeasuredWidth();
        } else {
            this.f645q = null;
        }
        this.w = i2;
        this.C = (int) (resources.getDisplayMetrics().density * 56.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v12 */
    @Override // androidx.appcompat.view.menu.BaseMenuPresenter, androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        ArrayList arrayList;
        int i2;
        int i3;
        int i4;
        boolean z;
        int i5;
        ActionMenuPresenter actionMenuPresenter = this;
        MenuBuilder menuBuilder = actionMenuPresenter.f502i;
        View view = null;
        ?? r3 = 0;
        if (menuBuilder != null) {
            arrayList = menuBuilder.G();
            i2 = arrayList.size();
        } else {
            arrayList = null;
            i2 = 0;
        }
        int i6 = actionMenuPresenter.x;
        int i7 = actionMenuPresenter.w;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) actionMenuPresenter.f508o;
        boolean z2 = false;
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < i2; i10++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) arrayList.get(i10);
            if (menuItemImpl.requiresActionButton()) {
                i8++;
            } else if (menuItemImpl.n()) {
                i9++;
            } else {
                z2 = true;
            }
            if (actionMenuPresenter.B && menuItemImpl.isActionViewExpanded()) {
                i6 = 0;
            }
        }
        if (actionMenuPresenter.t && (z2 || i9 + i8 > i6)) {
            i6--;
        }
        int i11 = i6 - i8;
        SparseBooleanArray sparseBooleanArray = actionMenuPresenter.D;
        sparseBooleanArray.clear();
        if (actionMenuPresenter.z) {
            int i12 = actionMenuPresenter.C;
            i4 = i7 / i12;
            i3 = i12 + ((i7 % i12) / i4);
        } else {
            i3 = 0;
            i4 = 0;
        }
        int i13 = 0;
        int i14 = 0;
        while (i13 < i2) {
            MenuItemImpl menuItemImpl2 = (MenuItemImpl) arrayList.get(i13);
            if (menuItemImpl2.requiresActionButton()) {
                View l2 = actionMenuPresenter.l(menuItemImpl2, view, viewGroup);
                if (actionMenuPresenter.z) {
                    i4 -= ActionMenuView.K(l2, i3, i4, makeMeasureSpec, r3);
                } else {
                    l2.measure(makeMeasureSpec, makeMeasureSpec);
                }
                int measuredWidth = l2.getMeasuredWidth();
                i7 -= measuredWidth;
                if (i14 == 0) {
                    i14 = measuredWidth;
                }
                int groupId = menuItemImpl2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                menuItemImpl2.t(true);
                z = r3;
                i5 = i2;
            } else if (menuItemImpl2.n()) {
                int groupId2 = menuItemImpl2.getGroupId();
                boolean z3 = sparseBooleanArray.get(groupId2);
                boolean z4 = (i11 > 0 || z3) && i7 > 0 && (!actionMenuPresenter.z || i4 > 0);
                boolean z5 = z4;
                i5 = i2;
                if (z4) {
                    View l3 = actionMenuPresenter.l(menuItemImpl2, null, viewGroup);
                    if (actionMenuPresenter.z) {
                        int K = ActionMenuView.K(l3, i3, i4, makeMeasureSpec, 0);
                        i4 -= K;
                        if (K == 0) {
                            z5 = false;
                        }
                    } else {
                        l3.measure(makeMeasureSpec, makeMeasureSpec);
                    }
                    boolean z6 = z5;
                    int measuredWidth2 = l3.getMeasuredWidth();
                    i7 -= measuredWidth2;
                    if (i14 == 0) {
                        i14 = measuredWidth2;
                    }
                    z4 = z6 & (!actionMenuPresenter.z ? i7 + i14 <= 0 : i7 < 0);
                }
                if (z4 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z3) {
                    sparseBooleanArray.put(groupId2, false);
                    for (int i15 = 0; i15 < i13; i15++) {
                        MenuItemImpl menuItemImpl3 = (MenuItemImpl) arrayList.get(i15);
                        if (menuItemImpl3.getGroupId() == groupId2) {
                            if (menuItemImpl3.l()) {
                                i11++;
                            }
                            menuItemImpl3.t(false);
                        }
                    }
                }
                if (z4) {
                    i11--;
                }
                menuItemImpl2.t(z4);
                z = false;
            } else {
                z = r3;
                i5 = i2;
                menuItemImpl2.t(z);
            }
            i13++;
            r3 = z;
            i2 = i5;
            view = null;
            actionMenuPresenter = this;
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter
    public void h(MenuItemImpl menuItemImpl, MenuView.ItemView itemView) {
        itemView.c(menuItemImpl, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) itemView;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.f508o);
        if (this.H == null) {
            this.H = new ActionMenuPopupCallback();
        }
        actionMenuItemView.setPopupCallback(this.H);
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter
    public boolean j(ViewGroup viewGroup, int i2) {
        if (viewGroup.getChildAt(i2) == this.f645q) {
            return false;
        }
        return super.j(viewGroup, i2);
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter
    public View l(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        View actionView = menuItemImpl.getActionView();
        if (actionView == null || menuItemImpl.j()) {
            actionView = super.l(menuItemImpl, view, viewGroup);
        }
        actionView.setVisibility(menuItemImpl.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter
    public MenuView m(ViewGroup viewGroup) {
        MenuView menuView = this.f508o;
        MenuView m2 = super.m(viewGroup);
        if (menuView != m2) {
            ((ActionMenuView) m2).setPresenter(this);
        }
        return m2;
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter
    public boolean o(int i2, MenuItemImpl menuItemImpl) {
        return menuItemImpl.l();
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable) {
        int i2;
        MenuItem findItem;
        if ((parcelable instanceof SavedState) && (i2 = ((SavedState) parcelable).f656c) > 0 && (findItem = this.f502i.findItem(i2)) != null) {
            d((SubMenuBuilder) findItem.getSubMenu());
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState();
        savedState.f656c = this.J;
        return savedState;
    }

    @Override // androidx.core.view.ActionProvider.SubUiVisibilityListener
    public void onSubUiVisibilityChanged(boolean z) {
        if (z) {
            super.d(null);
            return;
        }
        MenuBuilder menuBuilder = this.f502i;
        if (menuBuilder != null) {
            menuBuilder.e(false);
        }
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter, androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        int size;
        super.updateMenuView(z);
        ((View) this.f508o).requestLayout();
        MenuBuilder menuBuilder = this.f502i;
        if (menuBuilder != null) {
            ArrayList u = menuBuilder.u();
            int size2 = u.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ActionProvider a2 = ((MenuItemImpl) u.get(i2)).a();
                if (a2 != null) {
                    a2.i(this);
                }
            }
        }
        MenuBuilder menuBuilder2 = this.f502i;
        ArrayList B = menuBuilder2 != null ? menuBuilder2.B() : null;
        if (!this.t || B == null || ((size = B.size()) != 1 ? size <= 0 : !(!((MenuItemImpl) B.get(0)).isActionViewExpanded()))) {
            OverflowMenuButton overflowMenuButton = this.f645q;
            if (overflowMenuButton != null) {
                Object parent = overflowMenuButton.getParent();
                Object obj = this.f508o;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.f645q);
                }
            }
        } else {
            if (this.f645q == null) {
                this.f645q = new OverflowMenuButton(this.f500c);
            }
            ViewGroup viewGroup = (ViewGroup) this.f645q.getParent();
            if (viewGroup != this.f508o) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.f645q);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.f508o;
                actionMenuView.addView(this.f645q, actionMenuView.E());
            }
        }
        ((ActionMenuView) this.f508o).setOverflowReserved(this.t);
    }

    public boolean w() {
        return A() | z();
    }

    public Drawable y() {
        OverflowMenuButton overflowMenuButton = this.f645q;
        if (overflowMenuButton != null) {
            return overflowMenuButton.getDrawable();
        }
        if (this.f647s) {
            return this.f646r;
        }
        return null;
    }

    public boolean z() {
        Object obj;
        OpenOverflowRunnable openOverflowRunnable = this.G;
        if (openOverflowRunnable != null && (obj = this.f508o) != null) {
            ((View) obj).removeCallbacks(openOverflowRunnable);
            this.G = null;
            return true;
        }
        OverflowPopup overflowPopup = this.E;
        if (overflowPopup == null) {
            return false;
        }
        overflowPopup.b();
        return true;
    }
}
