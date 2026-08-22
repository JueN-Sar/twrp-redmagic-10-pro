package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.AttributeSet;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: classes.dex */
public abstract class Key {

    /* renamed from: f, reason: collision with root package name */
    public static int f2122f = -1;

    /* renamed from: a, reason: collision with root package name */
    int f2123a;

    /* renamed from: b, reason: collision with root package name */
    int f2124b;

    /* renamed from: c, reason: collision with root package name */
    String f2125c;

    /* renamed from: d, reason: collision with root package name */
    protected int f2126d;

    /* renamed from: e, reason: collision with root package name */
    HashMap f2127e;

    public Key() {
        int i2 = f2122f;
        this.f2123a = i2;
        this.f2124b = i2;
        this.f2125c = null;
    }

    public abstract void a(HashMap hashMap);

    @Override // 
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public abstract Key clone();

    public Key c(Key key) {
        this.f2123a = key.f2123a;
        this.f2124b = key.f2124b;
        this.f2125c = key.f2125c;
        this.f2126d = key.f2126d;
        this.f2127e = key.f2127e;
        return this;
    }

    abstract void d(HashSet hashSet);

    abstract void e(Context context, AttributeSet attributeSet);

    boolean f(String str) {
        String str2 = this.f2125c;
        if (str2 == null || str == null) {
            return false;
        }
        return str.matches(str2);
    }

    public void g(int i2) {
        this.f2123a = i2;
    }

    public void h(HashMap hashMap) {
    }

    public Key i(int i2) {
        this.f2124b = i2;
        return this;
    }

    boolean j(Object obj) {
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : Boolean.parseBoolean(obj.toString());
    }

    float k(Object obj) {
        return obj instanceof Float ? ((Float) obj).floatValue() : Float.parseFloat(obj.toString());
    }

    int l(Object obj) {
        return obj instanceof Integer ? ((Integer) obj).intValue() : Integer.parseInt(obj.toString());
    }
}
