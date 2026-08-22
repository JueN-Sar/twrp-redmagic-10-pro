package androidx.appcompat.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.core.content.ContextCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ActionProvider;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestrictTo
/* loaded from: classes.dex */
public class MenuBuilder implements SupportMenu {
    private static final int[] A = {1, 4, 5, 3, 2, 0};

    /* renamed from: a, reason: collision with root package name */
    private final Context f553a;

    /* renamed from: b, reason: collision with root package name */
    private final Resources f554b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f555c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f556d;

    /* renamed from: e, reason: collision with root package name */
    private Callback f557e;

    /* renamed from: m, reason: collision with root package name */
    private ContextMenu.ContextMenuInfo f565m;

    /* renamed from: n, reason: collision with root package name */
    CharSequence f566n;

    /* renamed from: o, reason: collision with root package name */
    Drawable f567o;

    /* renamed from: p, reason: collision with root package name */
    View f568p;
    private MenuItemImpl x;
    private boolean z;

    /* renamed from: l, reason: collision with root package name */
    private int f564l = 0;

    /* renamed from: q, reason: collision with root package name */
    private boolean f569q = false;

    /* renamed from: r, reason: collision with root package name */
    private boolean f570r = false;

    /* renamed from: s, reason: collision with root package name */
    private boolean f571s = false;
    private boolean t = false;
    private boolean u = false;
    private ArrayList v = new ArrayList();
    private CopyOnWriteArrayList w = new CopyOnWriteArrayList();
    private boolean y = false;

    /* renamed from: f, reason: collision with root package name */
    private ArrayList f558f = new ArrayList();

    /* renamed from: g, reason: collision with root package name */
    private ArrayList f559g = new ArrayList();

    /* renamed from: h, reason: collision with root package name */
    private boolean f560h = true;

    /* renamed from: i, reason: collision with root package name */
    private ArrayList f561i = new ArrayList();

    /* renamed from: j, reason: collision with root package name */
    private ArrayList f562j = new ArrayList();

    /* renamed from: k, reason: collision with root package name */
    private boolean f563k = true;

    @RestrictTo
    public interface Callback {
        boolean a(MenuBuilder menuBuilder, MenuItem menuItem);

        void b(MenuBuilder menuBuilder);
    }

    @RestrictTo
    public interface ItemInvoker {
        boolean b(MenuItemImpl menuItemImpl);
    }

    public MenuBuilder(Context context) {
        this.f553a = context;
        this.f554b = context.getResources();
        g0(true);
    }

    private static int D(int i2) {
        int i3 = ((-65536) & i2) >> 16;
        if (i3 >= 0) {
            int[] iArr = A;
            if (i3 < iArr.length) {
                return (i2 & 65535) | (iArr[i3] << 16);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    private void Q(int i2, boolean z) {
        if (i2 < 0 || i2 >= this.f558f.size()) {
            return;
        }
        this.f558f.remove(i2);
        if (z) {
            N(true);
        }
    }

    private void b0(int i2, CharSequence charSequence, int i3, Drawable drawable, View view) {
        Resources E = E();
        if (view != null) {
            this.f568p = view;
            this.f566n = null;
            this.f567o = null;
        } else {
            if (i2 > 0) {
                this.f566n = E.getText(i2);
            } else if (charSequence != null) {
                this.f566n = charSequence;
            }
            if (i3 > 0) {
                this.f567o = ContextCompat.e(w(), i3);
            } else if (drawable != null) {
                this.f567o = drawable;
            }
            this.f568p = null;
        }
        N(false);
    }

    private MenuItemImpl g(int i2, int i3, int i4, int i5, CharSequence charSequence, int i6) {
        return new MenuItemImpl(this, i2, i3, i4, i5, charSequence, i6);
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0019, code lost:
    
        if (androidx.core.view.ViewConfigurationCompat.j(android.view.ViewConfiguration.get(r2.f553a), r2.f553a) != false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void g0(boolean r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L1c
            android.content.res.Resources r3 = r2.f554b
            android.content.res.Configuration r3 = r3.getConfiguration()
            int r3 = r3.keyboard
            r0 = 1
            if (r3 == r0) goto L1c
            android.content.Context r3 = r2.f553a
            android.view.ViewConfiguration r3 = android.view.ViewConfiguration.get(r3)
            android.content.Context r1 = r2.f553a
            boolean r3 = androidx.core.view.ViewConfigurationCompat.j(r3, r1)
            if (r3 == 0) goto L1c
            goto L1d
        L1c:
            r0 = 0
        L1d:
            r2.f556d = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.MenuBuilder.g0(boolean):void");
    }

    private void i(boolean z) {
        if (this.w.isEmpty()) {
            return;
        }
        i0();
        Iterator it = this.w.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
            if (menuPresenter == null) {
                this.w.remove(weakReference);
            } else {
                menuPresenter.updateMenuView(z);
            }
        }
        h0();
    }

    private void j(Bundle bundle) {
        Parcelable parcelable;
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:presenters");
        if (sparseParcelableArray == null || this.w.isEmpty()) {
            return;
        }
        Iterator it = this.w.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
            if (menuPresenter == null) {
                this.w.remove(weakReference);
            } else {
                int id = menuPresenter.getId();
                if (id > 0 && (parcelable = (Parcelable) sparseParcelableArray.get(id)) != null) {
                    menuPresenter.onRestoreInstanceState(parcelable);
                }
            }
        }
    }

    private void k(Bundle bundle) {
        Parcelable onSaveInstanceState;
        if (this.w.isEmpty()) {
            return;
        }
        SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
        Iterator it = this.w.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
            if (menuPresenter == null) {
                this.w.remove(weakReference);
            } else {
                int id = menuPresenter.getId();
                if (id > 0 && (onSaveInstanceState = menuPresenter.onSaveInstanceState()) != null) {
                    sparseArray.put(id, onSaveInstanceState);
                }
            }
        }
        bundle.putSparseParcelableArray("android:menu:presenters", sparseArray);
    }

    private boolean l(SubMenuBuilder subMenuBuilder, MenuPresenter menuPresenter) {
        if (this.w.isEmpty()) {
            return false;
        }
        boolean d2 = menuPresenter != null ? menuPresenter.d(subMenuBuilder) : false;
        Iterator it = this.w.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            MenuPresenter menuPresenter2 = (MenuPresenter) weakReference.get();
            if (menuPresenter2 == null) {
                this.w.remove(weakReference);
            } else if (!d2) {
                d2 = menuPresenter2.d(subMenuBuilder);
            }
        }
        return d2;
    }

    private static int p(ArrayList arrayList, int i2) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (((MenuItemImpl) arrayList.get(size)).f() <= i2) {
                return size + 1;
            }
        }
        return 0;
    }

    public View A() {
        return this.f568p;
    }

    public ArrayList B() {
        t();
        return this.f562j;
    }

    boolean C() {
        return this.t;
    }

    Resources E() {
        return this.f554b;
    }

    public MenuBuilder F() {
        return this;
    }

    public ArrayList G() {
        if (!this.f560h) {
            return this.f559g;
        }
        this.f559g.clear();
        int size = this.f558f.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.f558f.get(i2);
            if (menuItemImpl.isVisible()) {
                this.f559g.add(menuItemImpl);
            }
        }
        this.f560h = false;
        this.f563k = true;
        return this.f559g;
    }

    public boolean H() {
        return !this.f569q;
    }

    public boolean I() {
        return this.y;
    }

    boolean J() {
        return this.f555c;
    }

    public boolean K() {
        return this.f556d;
    }

    void L(MenuItemImpl menuItemImpl) {
        this.f563k = true;
        N(true);
    }

    void M(MenuItemImpl menuItemImpl) {
        this.f560h = true;
        N(true);
    }

    public void N(boolean z) {
        if (this.f569q) {
            this.f570r = true;
            if (z) {
                this.f571s = true;
                return;
            }
            return;
        }
        if (z) {
            this.f560h = true;
            this.f563k = true;
        }
        i(z);
    }

    public boolean O(MenuItem menuItem, int i2) {
        return P(menuItem, null, i2);
    }

    public boolean P(MenuItem menuItem, MenuPresenter menuPresenter, int i2) {
        MenuItemImpl menuItemImpl = (MenuItemImpl) menuItem;
        if (menuItemImpl == null || !menuItemImpl.isEnabled()) {
            return false;
        }
        boolean k2 = menuItemImpl.k();
        ActionProvider a2 = menuItemImpl.a();
        boolean z = a2 != null && a2.a();
        if (menuItemImpl.j()) {
            k2 |= menuItemImpl.expandActionView();
            if (k2) {
                e(true);
            }
        } else if (menuItemImpl.hasSubMenu() || z) {
            if ((i2 & 4) == 0) {
                e(false);
            }
            if (!menuItemImpl.hasSubMenu()) {
                menuItemImpl.w(new SubMenuBuilder(w(), this, menuItemImpl));
            }
            SubMenuBuilder subMenuBuilder = (SubMenuBuilder) menuItemImpl.getSubMenu();
            if (z) {
                a2.f(subMenuBuilder);
            }
            k2 |= l(subMenuBuilder, menuPresenter);
            if (!k2) {
                e(true);
            }
        } else if ((i2 & 1) == 0) {
            e(true);
        }
        return k2;
    }

    public void R(MenuPresenter menuPresenter) {
        Iterator it = this.w.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            MenuPresenter menuPresenter2 = (MenuPresenter) weakReference.get();
            if (menuPresenter2 == null || menuPresenter2 == menuPresenter) {
                this.w.remove(weakReference);
            }
        }
    }

    public void S(Bundle bundle) {
        MenuItem findItem;
        if (bundle == null) {
            return;
        }
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(v());
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = getItem(i2);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((SubMenuBuilder) item.getSubMenu()).S(bundle);
            }
        }
        int i3 = bundle.getInt("android:menu:expandedactionview");
        if (i3 <= 0 || (findItem = findItem(i3)) == null) {
            return;
        }
        findItem.expandActionView();
    }

    public void T(Bundle bundle) {
        j(bundle);
    }

    public void U(Bundle bundle) {
        int size = size();
        SparseArray<? extends Parcelable> sparseArray = null;
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = getItem(i2);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((SubMenuBuilder) item.getSubMenu()).U(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(v(), sparseArray);
        }
    }

    public void V(Bundle bundle) {
        k(bundle);
    }

    public void W(Callback callback) {
        this.f557e = callback;
    }

    public MenuBuilder X(int i2) {
        this.f564l = i2;
        return this;
    }

    void Y(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.f558f.size();
        i0();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.f558f.get(i2);
            if (menuItemImpl.getGroupId() == groupId && menuItemImpl.m() && menuItemImpl.isCheckable()) {
                menuItemImpl.r(menuItemImpl == menuItem);
            }
        }
        h0();
    }

    protected MenuBuilder Z(int i2) {
        b0(0, null, i2, null, null);
        return this;
    }

    protected MenuItem a(int i2, int i3, int i4, CharSequence charSequence) {
        int D = D(i4);
        MenuItemImpl g2 = g(i2, i3, i4, D, charSequence, this.f564l);
        ContextMenu.ContextMenuInfo contextMenuInfo = this.f565m;
        if (contextMenuInfo != null) {
            g2.u(contextMenuInfo);
        }
        ArrayList arrayList = this.f558f;
        arrayList.add(p(arrayList, D), g2);
        N(true);
        return g2;
    }

    protected MenuBuilder a0(Drawable drawable) {
        b0(0, null, 0, drawable, null);
        return this;
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i2, int i3, int i4, ComponentName componentName, Intent[] intentArr, Intent intent, int i5, MenuItem[] menuItemArr) {
        int i6;
        PackageManager packageManager = this.f553a.getPackageManager();
        List<ResolveInfo> queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = queryIntentActivityOptions != null ? queryIntentActivityOptions.size() : 0;
        if ((i5 & 1) == 0) {
            removeGroup(i2);
        }
        for (int i7 = 0; i7 < size; i7++) {
            ResolveInfo resolveInfo = queryIntentActivityOptions.get(i7);
            int i8 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i8 < 0 ? intent : intentArr[i8]);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            MenuItem intent3 = add(i2, i3, i4, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && (i6 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i6] = intent3;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public void b(MenuPresenter menuPresenter) {
        c(menuPresenter, this.f553a);
    }

    public void c(MenuPresenter menuPresenter, Context context) {
        this.w.add(new WeakReference(menuPresenter));
        menuPresenter.f(context, this);
        this.f563k = true;
    }

    protected MenuBuilder c0(int i2) {
        b0(i2, null, 0, null, null);
        return this;
    }

    @Override // android.view.Menu
    public void clear() {
        MenuItemImpl menuItemImpl = this.x;
        if (menuItemImpl != null) {
            f(menuItemImpl);
        }
        this.f558f.clear();
        N(true);
    }

    public void clearHeader() {
        this.f567o = null;
        this.f566n = null;
        this.f568p = null;
        N(false);
    }

    @Override // android.view.Menu
    public void close() {
        e(true);
    }

    public void d() {
        Callback callback = this.f557e;
        if (callback != null) {
            callback.b(this);
        }
    }

    protected MenuBuilder d0(CharSequence charSequence) {
        b0(0, charSequence, 0, null, null);
        return this;
    }

    public final void e(boolean z) {
        if (this.u) {
            return;
        }
        this.u = true;
        Iterator it = this.w.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
            if (menuPresenter == null) {
                this.w.remove(weakReference);
            } else {
                menuPresenter.a(this, z);
            }
        }
        this.u = false;
    }

    protected MenuBuilder e0(View view) {
        b0(0, null, 0, null, view);
        return this;
    }

    public boolean f(MenuItemImpl menuItemImpl) {
        boolean z = false;
        if (!this.w.isEmpty() && this.x == menuItemImpl) {
            i0();
            Iterator it = this.w.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.w.remove(weakReference);
                } else {
                    z = menuPresenter.e(this, menuItemImpl);
                    if (z) {
                        break;
                    }
                }
            }
            h0();
            if (z) {
                this.x = null;
            }
        }
        return z;
    }

    public void f0(boolean z) {
        this.z = z;
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i2) {
        MenuItem findItem;
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.f558f.get(i3);
            if (menuItemImpl.getItemId() == i2) {
                return menuItemImpl;
            }
            if (menuItemImpl.hasSubMenu() && (findItem = menuItemImpl.getSubMenu().findItem(i2)) != null) {
                return findItem;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i2) {
        return (MenuItem) this.f558f.get(i2);
    }

    boolean h(MenuBuilder menuBuilder, MenuItem menuItem) {
        Callback callback = this.f557e;
        return callback != null && callback.a(menuBuilder, menuItem);
    }

    public void h0() {
        this.f569q = false;
        if (this.f570r) {
            this.f570r = false;
            N(this.f571s);
        }
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        if (this.z) {
            return true;
        }
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((MenuItemImpl) this.f558f.get(i2)).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public void i0() {
        if (this.f569q) {
            return;
        }
        this.f569q = true;
        this.f570r = false;
        this.f571s = false;
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i2, KeyEvent keyEvent) {
        return r(i2, keyEvent) != null;
    }

    public boolean m(MenuItemImpl menuItemImpl) {
        boolean z = false;
        if (this.w.isEmpty()) {
            return false;
        }
        i0();
        Iterator it = this.w.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
            if (menuPresenter == null) {
                this.w.remove(weakReference);
            } else {
                z = menuPresenter.b(this, menuItemImpl);
                if (z) {
                    break;
                }
            }
        }
        h0();
        if (z) {
            this.x = menuItemImpl;
        }
        return z;
    }

    public int n(int i2) {
        return o(i2, 0);
    }

    public int o(int i2, int i3) {
        int size = size();
        if (i3 < 0) {
            i3 = 0;
        }
        while (i3 < size) {
            if (((MenuItemImpl) this.f558f.get(i3)).getGroupId() == i2) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i2, int i3) {
        return O(findItem(i2), i3);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i2, KeyEvent keyEvent, int i3) {
        MenuItemImpl r2 = r(i2, keyEvent);
        boolean O = r2 != null ? O(r2, i3) : false;
        if ((i3 & 2) != 0) {
            e(true);
        }
        return O;
    }

    public int q(int i2) {
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            if (((MenuItemImpl) this.f558f.get(i3)).getItemId() == i2) {
                return i3;
            }
        }
        return -1;
    }

    MenuItemImpl r(int i2, KeyEvent keyEvent) {
        ArrayList arrayList = this.v;
        arrayList.clear();
        s(arrayList, i2, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return (MenuItemImpl) arrayList.get(0);
        }
        boolean J = J();
        for (int i3 = 0; i3 < size; i3++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) arrayList.get(i3);
            char alphabeticShortcut = J ? menuItemImpl.getAlphabeticShortcut() : menuItemImpl.getNumericShortcut();
            char[] cArr = keyData.meta;
            if ((alphabeticShortcut == cArr[0] && (metaState & 2) == 0) || ((alphabeticShortcut == cArr[2] && (metaState & 2) != 0) || (J && alphabeticShortcut == '\b' && i2 == 67))) {
                return menuItemImpl;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public void removeGroup(int i2) {
        int n2 = n(i2);
        if (n2 >= 0) {
            int size = this.f558f.size() - n2;
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                if (i3 >= size || ((MenuItemImpl) this.f558f.get(n2)).getGroupId() != i2) {
                    break;
                }
                Q(n2, false);
                i3 = i4;
            }
            N(true);
        }
    }

    @Override // android.view.Menu
    public void removeItem(int i2) {
        Q(q(i2), true);
    }

    void s(List list, int i2, KeyEvent keyEvent) {
        boolean J = J();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i2 == 67) {
            int size = this.f558f.size();
            for (int i3 = 0; i3 < size; i3++) {
                MenuItemImpl menuItemImpl = (MenuItemImpl) this.f558f.get(i3);
                if (menuItemImpl.hasSubMenu()) {
                    ((MenuBuilder) menuItemImpl.getSubMenu()).s(list, i2, keyEvent);
                }
                char alphabeticShortcut = J ? menuItemImpl.getAlphabeticShortcut() : menuItemImpl.getNumericShortcut();
                if ((modifiers & 69647) == ((J ? menuItemImpl.getAlphabeticModifiers() : menuItemImpl.getNumericModifiers()) & 69647) && alphabeticShortcut != 0) {
                    char[] cArr = keyData.meta;
                    if ((alphabeticShortcut == cArr[0] || alphabeticShortcut == cArr[2] || (J && alphabeticShortcut == '\b' && i2 == 67)) && menuItemImpl.isEnabled()) {
                        list.add(menuItemImpl);
                    }
                }
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i2, boolean z, boolean z2) {
        int size = this.f558f.size();
        for (int i3 = 0; i3 < size; i3++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.f558f.get(i3);
            if (menuItemImpl.getGroupId() == i2) {
                menuItemImpl.s(z2);
                menuItemImpl.setCheckable(z);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.y = z;
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i2, boolean z) {
        int size = this.f558f.size();
        for (int i3 = 0; i3 < size; i3++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.f558f.get(i3);
            if (menuItemImpl.getGroupId() == i2) {
                menuItemImpl.setEnabled(z);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i2, boolean z) {
        int size = this.f558f.size();
        boolean z2 = false;
        for (int i3 = 0; i3 < size; i3++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.f558f.get(i3);
            if (menuItemImpl.getGroupId() == i2 && menuItemImpl.x(z)) {
                z2 = true;
            }
        }
        if (z2) {
            N(true);
        }
    }

    public void setOptionalIconsVisible(boolean z) {
        this.t = z;
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z) {
        this.f555c = z;
        N(false);
    }

    @Override // android.view.Menu
    public int size() {
        return this.f558f.size();
    }

    public void t() {
        ArrayList G = G();
        if (this.f563k) {
            Iterator it = this.w.iterator();
            boolean z = false;
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.w.remove(weakReference);
                } else {
                    z |= menuPresenter.flagActionItems();
                }
            }
            if (z) {
                this.f561i.clear();
                this.f562j.clear();
                int size = G.size();
                for (int i2 = 0; i2 < size; i2++) {
                    MenuItemImpl menuItemImpl = (MenuItemImpl) G.get(i2);
                    if (menuItemImpl.l()) {
                        this.f561i.add(menuItemImpl);
                    } else {
                        this.f562j.add(menuItemImpl);
                    }
                }
            } else {
                this.f561i.clear();
                this.f562j.clear();
                this.f562j.addAll(G());
            }
            this.f563k = false;
        }
    }

    public ArrayList u() {
        t();
        return this.f561i;
    }

    protected String v() {
        return "android:menu:actionviewstates";
    }

    public Context w() {
        return this.f553a;
    }

    public MenuItemImpl x() {
        return this.x;
    }

    public Drawable y() {
        return this.f567o;
    }

    public CharSequence z() {
        return this.f566n;
    }

    @Override // android.view.Menu
    public MenuItem add(int i2) {
        return a(0, 0, 0, this.f554b.getString(i2));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2) {
        return addSubMenu(0, 0, 0, this.f554b.getString(i2));
    }

    @Override // android.view.Menu
    public MenuItem add(int i2, int i3, int i4, CharSequence charSequence) {
        return a(i2, i3, i4, charSequence);
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        MenuItemImpl menuItemImpl = (MenuItemImpl) a(i2, i3, i4, charSequence);
        SubMenuBuilder subMenuBuilder = new SubMenuBuilder(this.f553a, this, menuItemImpl);
        menuItemImpl.w(subMenuBuilder);
        return subMenuBuilder;
    }

    @Override // android.view.Menu
    public MenuItem add(int i2, int i3, int i4, int i5) {
        return a(i2, i3, i4, this.f554b.getString(i5));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2, int i3, int i4, int i5) {
        return addSubMenu(i2, i3, i4, this.f554b.getString(i5));
    }
}
