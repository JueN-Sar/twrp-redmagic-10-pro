package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* loaded from: classes.dex */
class zbwh extends AbstractMap {

    /* renamed from: c, reason: collision with root package name */
    private Object[] f13043c;

    /* renamed from: h, reason: collision with root package name */
    private int f13044h;

    /* renamed from: j, reason: collision with root package name */
    private boolean f13046j;

    /* renamed from: k, reason: collision with root package name */
    private volatile zbwf f13047k;

    /* renamed from: i, reason: collision with root package name */
    private Map f13045i = Collections.emptyMap();

    /* renamed from: l, reason: collision with root package name */
    private Map f13048l = Collections.emptyMap();

    /* synthetic */ zbwh(zbwg zbwgVar) {
    }

    private final int l(Comparable comparable) {
        int i2 = this.f13044h;
        int i3 = i2 - 1;
        int i4 = 0;
        if (i3 >= 0) {
            int compareTo = comparable.compareTo(((zbwb) this.f13043c[i3]).c());
            if (compareTo > 0) {
                return -(i2 + 1);
            }
            if (compareTo == 0) {
                return i3;
            }
        }
        while (i4 <= i3) {
            int i5 = (i4 + i3) / 2;
            int compareTo2 = comparable.compareTo(((zbwb) this.f13043c[i5]).c());
            if (compareTo2 < 0) {
                i3 = i5 - 1;
            } else {
                if (compareTo2 <= 0) {
                    return i5;
                }
                i4 = i5 + 1;
            }
        }
        return -(i4 + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object m(int i2) {
        o();
        Object value = ((zbwb) this.f13043c[i2]).getValue();
        Object[] objArr = this.f13043c;
        System.arraycopy(objArr, i2 + 1, objArr, i2, (this.f13044h - i2) - 1);
        this.f13044h--;
        if (!this.f13045i.isEmpty()) {
            Iterator it = n().entrySet().iterator();
            Object[] objArr2 = this.f13043c;
            int i3 = this.f13044h;
            Map.Entry entry = (Map.Entry) it.next();
            objArr2[i3] = new zbwb(this, (Comparable) entry.getKey(), entry.getValue());
            this.f13044h++;
            it.remove();
        }
        return value;
    }

    private final SortedMap n() {
        o();
        if (this.f13045i.isEmpty() && !(this.f13045i instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.f13045i = treeMap;
            this.f13048l = treeMap.descendingMap();
        }
        return (SortedMap) this.f13045i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void o() {
        if (this.f13046j) {
            throw new UnsupportedOperationException();
        }
    }

    public void a() {
        if (this.f13046j) {
            return;
        }
        this.f13045i = this.f13045i.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.f13045i);
        this.f13048l = this.f13048l.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.f13048l);
        this.f13046j = true;
    }

    public final int c() {
        return this.f13044h;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        o();
        if (this.f13044h != 0) {
            this.f13043c = null;
            this.f13044h = 0;
        }
        if (this.f13045i.isEmpty()) {
            return;
        }
        this.f13045i.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return l(comparable) >= 0 || this.f13045i.containsKey(comparable);
    }

    public final Iterable d() {
        return this.f13045i.isEmpty() ? Collections.emptySet() : this.f13045i.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        if (this.f13047k == null) {
            this.f13047k = new zbwf(this, null);
        }
        return this.f13047k;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zbwh)) {
            return super.equals(obj);
        }
        zbwh zbwhVar = (zbwh) obj;
        int size = size();
        if (size != zbwhVar.size()) {
            return false;
        }
        int i2 = this.f13044h;
        if (i2 != zbwhVar.f13044h) {
            return entrySet().equals(zbwhVar.entrySet());
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (!g(i3).equals(zbwhVar.g(i3))) {
                return false;
            }
        }
        if (i2 != size) {
            return this.f13045i.equals(zbwhVar.f13045i);
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public final Object put(Comparable comparable, Object obj) {
        o();
        int l2 = l(comparable);
        if (l2 >= 0) {
            return ((zbwb) this.f13043c[l2]).setValue(obj);
        }
        o();
        if (this.f13043c == null) {
            this.f13043c = new Object[16];
        }
        int i2 = -(l2 + 1);
        if (i2 >= 16) {
            return n().put(comparable, obj);
        }
        if (this.f13044h == 16) {
            zbwb zbwbVar = (zbwb) this.f13043c[15];
            this.f13044h = 15;
            n().put(zbwbVar.c(), zbwbVar.getValue());
        }
        Object[] objArr = this.f13043c;
        int length = objArr.length;
        System.arraycopy(objArr, i2, objArr, i2 + 1, 15 - i2);
        this.f13043c[i2] = new zbwb(this, comparable, obj);
        this.f13044h++;
        return null;
    }

    public final Map.Entry g(int i2) {
        if (i2 < this.f13044h) {
            return (zbwb) this.f13043c[i2];
        }
        throw new ArrayIndexOutOfBoundsException(i2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int l2 = l(comparable);
        return l2 >= 0 ? ((zbwb) this.f13043c[l2]).getValue() : this.f13045i.get(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int i2 = this.f13044h;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += this.f13043c[i4].hashCode();
        }
        return this.f13045i.size() > 0 ? i3 + this.f13045i.hashCode() : i3;
    }

    public final boolean j() {
        return this.f13046j;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        o();
        Comparable comparable = (Comparable) obj;
        int l2 = l(comparable);
        if (l2 >= 0) {
            return m(l2);
        }
        if (this.f13045i.isEmpty()) {
            return null;
        }
        return this.f13045i.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f13044h + this.f13045i.size();
    }
}
