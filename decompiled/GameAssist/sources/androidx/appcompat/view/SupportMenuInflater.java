package androidx.appcompat.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuItemWrapperICS;
import androidx.appcompat.widget.DrawableUtils;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ActionProvider;
import androidx.core.view.MenuItemCompat;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo
/* loaded from: classes.dex */
public class SupportMenuInflater extends MenuInflater {

    /* renamed from: e, reason: collision with root package name */
    static final Class[] f442e;

    /* renamed from: f, reason: collision with root package name */
    static final Class[] f443f;

    /* renamed from: a, reason: collision with root package name */
    final Object[] f444a;

    /* renamed from: b, reason: collision with root package name */
    final Object[] f445b;

    /* renamed from: c, reason: collision with root package name */
    Context f446c;

    /* renamed from: d, reason: collision with root package name */
    private Object f447d;

    private static class InflatedOnMenuItemClickListener implements MenuItem.OnMenuItemClickListener {

        /* renamed from: c, reason: collision with root package name */
        private static final Class[] f448c = {MenuItem.class};

        /* renamed from: a, reason: collision with root package name */
        private Object f449a;

        /* renamed from: b, reason: collision with root package name */
        private Method f450b;

        public InflatedOnMenuItemClickListener(Object obj, String str) {
            this.f449a = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f450b = cls.getMethod(str, f448c);
            } catch (Exception e2) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e2);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f450b.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f450b.invoke(this.f449a, menuItem)).booleanValue();
                }
                this.f450b.invoke(this.f449a, menuItem);
                return true;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    private class MenuState {
        ActionProvider A;
        private CharSequence B;
        private CharSequence C;
        private ColorStateList D = null;
        private PorterDuff.Mode E = null;

        /* renamed from: a, reason: collision with root package name */
        private Menu f451a;

        /* renamed from: b, reason: collision with root package name */
        private int f452b;

        /* renamed from: c, reason: collision with root package name */
        private int f453c;

        /* renamed from: d, reason: collision with root package name */
        private int f454d;

        /* renamed from: e, reason: collision with root package name */
        private int f455e;

        /* renamed from: f, reason: collision with root package name */
        private boolean f456f;

        /* renamed from: g, reason: collision with root package name */
        private boolean f457g;

        /* renamed from: h, reason: collision with root package name */
        private boolean f458h;

        /* renamed from: i, reason: collision with root package name */
        private int f459i;

        /* renamed from: j, reason: collision with root package name */
        private int f460j;

        /* renamed from: k, reason: collision with root package name */
        private CharSequence f461k;

        /* renamed from: l, reason: collision with root package name */
        private CharSequence f462l;

        /* renamed from: m, reason: collision with root package name */
        private int f463m;

        /* renamed from: n, reason: collision with root package name */
        private char f464n;

        /* renamed from: o, reason: collision with root package name */
        private int f465o;

        /* renamed from: p, reason: collision with root package name */
        private char f466p;

        /* renamed from: q, reason: collision with root package name */
        private int f467q;

        /* renamed from: r, reason: collision with root package name */
        private int f468r;

        /* renamed from: s, reason: collision with root package name */
        private boolean f469s;
        private boolean t;
        private boolean u;
        private int v;
        private int w;
        private String x;
        private String y;
        private String z;

        public MenuState(Menu menu) {
            this.f451a = menu;
            h();
        }

        private char c(String str) {
            if (str == null) {
                return (char) 0;
            }
            return str.charAt(0);
        }

        private Object e(String str, Class[] clsArr, Object[] objArr) {
            try {
                Constructor<?> constructor = Class.forName(str, false, SupportMenuInflater.this.f446c.getClassLoader()).getConstructor(clsArr);
                constructor.setAccessible(true);
                return constructor.newInstance(objArr);
            } catch (Exception e2) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e2);
                return null;
            }
        }

        private void i(MenuItem menuItem) {
            boolean z = false;
            menuItem.setChecked(this.f469s).setVisible(this.t).setEnabled(this.u).setCheckable(this.f468r >= 1).setTitleCondensed(this.f462l).setIcon(this.f463m);
            int i2 = this.v;
            if (i2 >= 0) {
                menuItem.setShowAsAction(i2);
            }
            if (this.z != null) {
                if (SupportMenuInflater.this.f446c.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new InflatedOnMenuItemClickListener(SupportMenuInflater.this.getRealOwner(), this.z));
            }
            if (this.f468r >= 2) {
                if (menuItem instanceof MenuItemImpl) {
                    ((MenuItemImpl) menuItem).s(true);
                } else if (menuItem instanceof MenuItemWrapperICS) {
                    ((MenuItemWrapperICS) menuItem).h(true);
                }
            }
            String str = this.x;
            if (str != null) {
                menuItem.setActionView((View) e(str, SupportMenuInflater.f442e, SupportMenuInflater.this.f444a));
                z = true;
            }
            int i3 = this.w;
            if (i3 > 0) {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                } else {
                    menuItem.setActionView(i3);
                }
            }
            ActionProvider actionProvider = this.A;
            if (actionProvider != null) {
                MenuItemCompat.a(menuItem, actionProvider);
            }
            MenuItemCompat.c(menuItem, this.B);
            MenuItemCompat.g(menuItem, this.C);
            MenuItemCompat.b(menuItem, this.f464n, this.f465o);
            MenuItemCompat.f(menuItem, this.f466p, this.f467q);
            PorterDuff.Mode mode = this.E;
            if (mode != null) {
                MenuItemCompat.e(menuItem, mode);
            }
            ColorStateList colorStateList = this.D;
            if (colorStateList != null) {
                MenuItemCompat.d(menuItem, colorStateList);
            }
        }

        public void a() {
            this.f458h = true;
            i(this.f451a.add(this.f452b, this.f459i, this.f460j, this.f461k));
        }

        public SubMenu b() {
            this.f458h = true;
            SubMenu addSubMenu = this.f451a.addSubMenu(this.f452b, this.f459i, this.f460j, this.f461k);
            i(addSubMenu.getItem());
            return addSubMenu;
        }

        public boolean d() {
            return this.f458h;
        }

        public void f(AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = SupportMenuInflater.this.f446c.obtainStyledAttributes(attributeSet, R.styleable.MenuGroup);
            this.f452b = obtainStyledAttributes.getResourceId(R.styleable.MenuGroup_android_id, 0);
            this.f453c = obtainStyledAttributes.getInt(R.styleable.MenuGroup_android_menuCategory, 0);
            this.f454d = obtainStyledAttributes.getInt(R.styleable.MenuGroup_android_orderInCategory, 0);
            this.f455e = obtainStyledAttributes.getInt(R.styleable.MenuGroup_android_checkableBehavior, 0);
            this.f456f = obtainStyledAttributes.getBoolean(R.styleable.MenuGroup_android_visible, true);
            this.f457g = obtainStyledAttributes.getBoolean(R.styleable.MenuGroup_android_enabled, true);
            obtainStyledAttributes.recycle();
        }

        public void g(AttributeSet attributeSet) {
            TintTypedArray u = TintTypedArray.u(SupportMenuInflater.this.f446c, attributeSet, R.styleable.MenuItem);
            this.f459i = u.n(R.styleable.MenuItem_android_id, 0);
            this.f460j = (u.k(R.styleable.MenuItem_android_menuCategory, this.f453c) & (-65536)) | (u.k(R.styleable.MenuItem_android_orderInCategory, this.f454d) & 65535);
            this.f461k = u.p(R.styleable.MenuItem_android_title);
            this.f462l = u.p(R.styleable.MenuItem_android_titleCondensed);
            this.f463m = u.n(R.styleable.MenuItem_android_icon, 0);
            this.f464n = c(u.o(R.styleable.MenuItem_android_alphabeticShortcut));
            this.f465o = u.k(R.styleable.MenuItem_alphabeticModifiers, 4096);
            this.f466p = c(u.o(R.styleable.MenuItem_android_numericShortcut));
            this.f467q = u.k(R.styleable.MenuItem_numericModifiers, 4096);
            if (u.s(R.styleable.MenuItem_android_checkable)) {
                this.f468r = u.a(R.styleable.MenuItem_android_checkable, false) ? 1 : 0;
            } else {
                this.f468r = this.f455e;
            }
            this.f469s = u.a(R.styleable.MenuItem_android_checked, false);
            this.t = u.a(R.styleable.MenuItem_android_visible, this.f456f);
            this.u = u.a(R.styleable.MenuItem_android_enabled, this.f457g);
            this.v = u.k(R.styleable.MenuItem_showAsAction, -1);
            this.z = u.o(R.styleable.MenuItem_android_onClick);
            this.w = u.n(R.styleable.MenuItem_actionLayout, 0);
            this.x = u.o(R.styleable.MenuItem_actionViewClass);
            String o2 = u.o(R.styleable.MenuItem_actionProviderClass);
            this.y = o2;
            boolean z = o2 != null;
            if (z && this.w == 0 && this.x == null) {
                this.A = (ActionProvider) e(o2, SupportMenuInflater.f443f, SupportMenuInflater.this.f445b);
            } else {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.A = null;
            }
            this.B = u.p(R.styleable.MenuItem_contentDescription);
            this.C = u.p(R.styleable.MenuItem_tooltipText);
            if (u.s(R.styleable.MenuItem_iconTintMode)) {
                this.E = DrawableUtils.d(u.k(R.styleable.MenuItem_iconTintMode, -1), this.E);
            } else {
                this.E = null;
            }
            if (u.s(R.styleable.MenuItem_iconTint)) {
                this.D = u.c(R.styleable.MenuItem_iconTint);
            } else {
                this.D = null;
            }
            u.x();
            this.f458h = false;
        }

        public void h() {
            this.f452b = 0;
            this.f453c = 0;
            this.f454d = 0;
            this.f455e = 0;
            this.f456f = true;
            this.f457g = true;
        }
    }

    static {
        Class[] clsArr = {Context.class};
        f442e = clsArr;
        f443f = clsArr;
    }

    public SupportMenuInflater(Context context) {
        super(context);
        this.f446c = context;
        Object[] objArr = {context};
        this.f444a = objArr;
        this.f445b = objArr;
    }

    private Object findRealOwner(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? findRealOwner(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0048, code lost:
    
        if (r15 == 2) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x004b, code lost:
    
        if (r15 == 3) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004f, code lost:
    
        r15 = r13.getName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0053, code lost:
    
        if (r7 == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0059, code lost:
    
        if (r15.equals(r8) == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005b, code lost:
    
        r7 = false;
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00b9, code lost:
    
        r15 = r13.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0062, code lost:
    
        if (r15.equals("group") == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0064, code lost:
    
        r0.h();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006c, code lost:
    
        if (r15.equals(com.zte.distbus.basetransfer.Constants.EXTRA_ITEM) == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0072, code lost:
    
        if (r0.d() != false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0074, code lost:
    
        r15 = r0.A;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0076, code lost:
    
        if (r15 == null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007c, code lost:
    
        if (r15.a() == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007e, code lost:
    
        r0.b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0082, code lost:
    
        r0.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x008a, code lost:
    
        if (r15.equals("menu") == false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x008c, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x008e, code lost:
    
        if (r7 == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0091, code lost:
    
        r15 = r13.getName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0099, code lost:
    
        if (r15.equals("group") == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x009b, code lost:
    
        r0.f(r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00a3, code lost:
    
        if (r15.equals(com.zte.distbus.basetransfer.Constants.EXTRA_ITEM) == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00a5, code lost:
    
        r0.g(r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00ad, code lost:
    
        if (r15.equals("menu") == false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00af, code lost:
    
        parseMenu(r13, r14, r0.b());
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00b7, code lost:
    
        r8 = r15;
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00c5, code lost:
    
        throw new java.lang.RuntimeException("Unexpected end of document");
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00c6, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x003b, code lost:
    
        r6 = false;
        r7 = false;
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0040, code lost:
    
        if (r6 != false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0042, code lost:
    
        if (r15 == 1) goto L61;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void parseMenu(org.xmlpull.v1.XmlPullParser r13, android.util.AttributeSet r14, android.view.Menu r15) {
        /*
            r12 = this;
            androidx.appcompat.view.SupportMenuInflater$MenuState r0 = new androidx.appcompat.view.SupportMenuInflater$MenuState
            r0.<init>(r15)
            int r15 = r13.getEventType()
        L9:
            r1 = 2
            java.lang.String r2 = "menu"
            r3 = 1
            if (r15 != r1) goto L35
            java.lang.String r15 = r13.getName()
            boolean r4 = r15.equals(r2)
            if (r4 == 0) goto L1e
            int r15 = r13.next()
            goto L3b
        L1e:
            java.lang.RuntimeException r12 = new java.lang.RuntimeException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "Expecting menu, got "
            r13.append(r14)
            r13.append(r15)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            throw r12
        L35:
            int r15 = r13.next()
            if (r15 != r3) goto L9
        L3b:
            r4 = 0
            r5 = 0
            r6 = r4
            r7 = r6
            r8 = r5
        L40:
            if (r6 != 0) goto Lc6
            if (r15 == r3) goto Lbe
            java.lang.String r9 = "item"
            java.lang.String r10 = "group"
            if (r15 == r1) goto L8e
            r11 = 3
            if (r15 == r11) goto L4f
            goto Lb9
        L4f:
            java.lang.String r15 = r13.getName()
            if (r7 == 0) goto L5e
            boolean r11 = r15.equals(r8)
            if (r11 == 0) goto L5e
            r7 = r4
            r8 = r5
            goto Lb9
        L5e:
            boolean r10 = r15.equals(r10)
            if (r10 == 0) goto L68
            r0.h()
            goto Lb9
        L68:
            boolean r9 = r15.equals(r9)
            if (r9 == 0) goto L86
            boolean r15 = r0.d()
            if (r15 != 0) goto Lb9
            androidx.core.view.ActionProvider r15 = r0.A
            if (r15 == 0) goto L82
            boolean r15 = r15.a()
            if (r15 == 0) goto L82
            r0.b()
            goto Lb9
        L82:
            r0.a()
            goto Lb9
        L86:
            boolean r15 = r15.equals(r2)
            if (r15 == 0) goto Lb9
            r6 = r3
            goto Lb9
        L8e:
            if (r7 == 0) goto L91
            goto Lb9
        L91:
            java.lang.String r15 = r13.getName()
            boolean r10 = r15.equals(r10)
            if (r10 == 0) goto L9f
            r0.f(r14)
            goto Lb9
        L9f:
            boolean r9 = r15.equals(r9)
            if (r9 == 0) goto La9
            r0.g(r14)
            goto Lb9
        La9:
            boolean r9 = r15.equals(r2)
            if (r9 == 0) goto Lb7
            android.view.SubMenu r15 = r0.b()
            r12.parseMenu(r13, r14, r15)
            goto Lb9
        Lb7:
            r8 = r15
            r7 = r3
        Lb9:
            int r15 = r13.next()
            goto L40
        Lbe:
            java.lang.RuntimeException r12 = new java.lang.RuntimeException
            java.lang.String r13 = "Unexpected end of document"
            r12.<init>(r13)
            throw r12
        Lc6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.SupportMenuInflater.parseMenu(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.view.Menu):void");
    }

    Object getRealOwner() {
        if (this.f447d == null) {
            this.f447d = findRealOwner(this.f446c);
        }
        return this.f447d;
    }

    @Override // android.view.MenuInflater
    public void inflate(int i2, Menu menu) {
        if (!(menu instanceof SupportMenu)) {
            super.inflate(i2, menu);
            return;
        }
        XmlResourceParser xmlResourceParser = null;
        boolean z = false;
        try {
            try {
                xmlResourceParser = this.f446c.getResources().getLayout(i2);
                AttributeSet asAttributeSet = Xml.asAttributeSet(xmlResourceParser);
                if (menu instanceof MenuBuilder) {
                    MenuBuilder menuBuilder = (MenuBuilder) menu;
                    if (menuBuilder.H()) {
                        menuBuilder.i0();
                        z = true;
                    }
                }
                parseMenu(xmlResourceParser, asAttributeSet, menu);
                if (z) {
                    ((MenuBuilder) menu).h0();
                }
                if (xmlResourceParser != null) {
                    xmlResourceParser.close();
                }
            } catch (IOException e2) {
                throw new InflateException("Error inflating menu XML", e2);
            } catch (XmlPullParserException e3) {
                throw new InflateException("Error inflating menu XML", e3);
            }
        } catch (Throwable th) {
            if (z) {
                ((MenuBuilder) menu).h0();
            }
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
            throw th;
        }
    }
}
