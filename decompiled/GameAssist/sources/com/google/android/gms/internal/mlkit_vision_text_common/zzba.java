package com.google.android.gms.internal.mlkit_vision_text_common;

import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;

/* loaded from: classes.dex */
final class zzba extends AbstractMap implements Serializable {
    private static final Object zzd = new Object();

    @CheckForNull
    transient int[] zza;

    @CheckForNull
    transient Object[] zzb;

    @CheckForNull
    transient Object[] zzc;

    @CheckForNull
    private transient Object zze;
    private transient int zzf;
    private transient int zzg;

    @CheckForNull
    private transient Set zzh;

    @CheckForNull
    private transient Set zzi;

    @CheckForNull
    private transient Collection zzj;

    zzba() {
        s(3);
    }

    private final int A(int i2, int i3, int i4, int i5) {
        int i6 = i3 - 1;
        Object d2 = zzbb.d(i3);
        if (i5 != 0) {
            zzbb.e(d2, i4 & i6, i5 + 1);
        }
        Object obj = this.zze;
        Objects.requireNonNull(obj);
        int[] a2 = a();
        for (int i7 = 0; i7 <= i2; i7++) {
            int c2 = zzbb.c(obj, i7);
            while (c2 != 0) {
                int i8 = c2 - 1;
                int i9 = a2[i8];
                int i10 = ((~i2) & i9) | i7;
                int i11 = i10 & i6;
                int c3 = zzbb.c(d2, i11);
                zzbb.e(d2, i11, c2);
                a2[i8] = ((~i6) & i10) | (c3 & i6);
                c2 = i9 & i2;
            }
        }
        this.zze = d2;
        C(i6);
        return i6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object B(Object obj) {
        if (!u()) {
            int y = y();
            Object obj2 = this.zze;
            Objects.requireNonNull(obj2);
            int b2 = zzbb.b(obj, null, y, obj2, a(), b(), null);
            if (b2 != -1) {
                Object obj3 = c()[b2];
                t(b2, y);
                this.zzg--;
                r();
                return obj3;
            }
        }
        return zzd;
    }

    private final void C(int i2) {
        this.zzf = ((32 - Integer.numberOfLeadingZeros(i2)) & 31) | (this.zzf & (-32));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int[] a() {
        int[] iArr = this.zza;
        Objects.requireNonNull(iArr);
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object[] b() {
        Object[] objArr = this.zzb;
        Objects.requireNonNull(objArr);
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object[] c() {
        Object[] objArr = this.zzc;
        Objects.requireNonNull(objArr);
        return objArr;
    }

    static /* synthetic */ Object j(zzba zzbaVar, int i2) {
        return zzbaVar.b()[i2];
    }

    static /* synthetic */ Object l(zzba zzbaVar) {
        Object obj = zzbaVar.zze;
        Objects.requireNonNull(obj);
        return obj;
    }

    static /* synthetic */ Object m(zzba zzbaVar, int i2) {
        return zzbaVar.c()[i2];
    }

    static /* synthetic */ void q(zzba zzbaVar, int i2, Object obj) {
        zzbaVar.c()[i2] = obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int y() {
        return (1 << (this.zzf & 31)) - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int z(Object obj) {
        if (u()) {
            return -1;
        }
        int a2 = zzbc.a(obj);
        int y = y();
        Object obj2 = this.zze;
        Objects.requireNonNull(obj2);
        int c2 = zzbb.c(obj2, a2 & y);
        if (c2 != 0) {
            int i2 = ~y;
            int i3 = a2 & i2;
            do {
                int i4 = c2 - 1;
                int i5 = a()[i4];
                if ((i5 & i2) == i3 && zzw.a(obj, b()[i4])) {
                    return i4;
                }
                c2 = i5 & y;
            } while (c2 != 0);
        }
        return -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        if (u()) {
            return;
        }
        r();
        Map o2 = o();
        if (o2 != null) {
            this.zzf = zzcq.a(size(), 3, 1073741823);
            o2.clear();
            this.zze = null;
            this.zzg = 0;
            return;
        }
        Arrays.fill(b(), 0, this.zzg, (Object) null);
        Arrays.fill(c(), 0, this.zzg, (Object) null);
        Object obj = this.zze;
        Objects.requireNonNull(obj);
        if (obj instanceof byte[]) {
            Arrays.fill((byte[]) obj, (byte) 0);
        } else if (obj instanceof short[]) {
            Arrays.fill((short[]) obj, (short) 0);
        } else {
            Arrays.fill((int[]) obj, 0);
        }
        Arrays.fill(a(), 0, this.zzg, 0);
        this.zzg = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Map o2 = o();
        return o2 != null ? o2.containsKey(obj) : z(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object obj) {
        Map o2 = o();
        if (o2 != null) {
            return o2.containsValue(obj);
        }
        for (int i2 = 0; i2 < this.zzg; i2++) {
            if (zzw.a(obj, c()[i2])) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        Set set = this.zzi;
        if (set != null) {
            return set;
        }
        zzau zzauVar = new zzau(this);
        this.zzi = zzauVar;
        return zzauVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Map o2 = o();
        if (o2 != null) {
            return o2.get(obj);
        }
        int z = z(obj);
        if (z == -1) {
            return null;
        }
        return c()[z];
    }

    final int h() {
        return isEmpty() ? -1 : 0;
    }

    final int i(int i2) {
        int i3 = i2 + 1;
        if (i3 < this.zzg) {
            return i3;
        }
        return -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        Set set = this.zzh;
        if (set != null) {
            return set;
        }
        zzax zzaxVar = new zzax(this);
        this.zzh = zzaxVar;
        return zzaxVar;
    }

    final Map o() {
        Object obj = this.zze;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        int min;
        if (u()) {
            zzx.d(u(), "Arrays already allocated");
            int i2 = this.zzf;
            int max = Math.max(i2 + 1, 2);
            int highestOneBit = Integer.highestOneBit(max);
            if (max > highestOneBit && (highestOneBit = highestOneBit + highestOneBit) <= 0) {
                highestOneBit = WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME;
            }
            int max2 = Math.max(4, highestOneBit);
            this.zze = zzbb.d(max2);
            C(max2 - 1);
            this.zza = new int[i2];
            this.zzb = new Object[i2];
            this.zzc = new Object[i2];
        }
        Map o2 = o();
        if (o2 != null) {
            return o2.put(obj, obj2);
        }
        int[] a2 = a();
        Object[] b2 = b();
        Object[] c2 = c();
        int i3 = this.zzg;
        int i4 = i3 + 1;
        int a3 = zzbc.a(obj);
        int y = y();
        int i5 = a3 & y;
        Object obj3 = this.zze;
        Objects.requireNonNull(obj3);
        int c3 = zzbb.c(obj3, i5);
        if (c3 != 0) {
            int i6 = ~y;
            int i7 = a3 & i6;
            int i8 = 0;
            while (true) {
                int i9 = c3 - 1;
                int i10 = a2[i9];
                int i11 = i10 & i6;
                if (i11 == i7 && zzw.a(obj, b2[i9])) {
                    Object obj4 = c2[i9];
                    c2[i9] = obj2;
                    return obj4;
                }
                int i12 = i10 & y;
                i8++;
                if (i12 != 0) {
                    c3 = i12;
                } else {
                    if (i8 >= 9) {
                        LinkedHashMap linkedHashMap = new LinkedHashMap(y() + 1, 1.0f);
                        int h2 = h();
                        while (h2 >= 0) {
                            linkedHashMap.put(b()[h2], c()[h2]);
                            h2 = i(h2);
                        }
                        this.zze = linkedHashMap;
                        this.zza = null;
                        this.zzb = null;
                        this.zzc = null;
                        r();
                        return linkedHashMap.put(obj, obj2);
                    }
                    if (i4 > y) {
                        y = A(y, zzbb.a(y), a3, i3);
                    } else {
                        a2[i9] = (i4 & y) | i11;
                    }
                }
            }
        } else if (i4 > y) {
            y = A(y, zzbb.a(y), a3, i3);
        } else {
            Object obj5 = this.zze;
            Objects.requireNonNull(obj5);
            zzbb.e(obj5, i5, i4);
        }
        int length = a().length;
        if (i4 > length && (min = Math.min(1073741823, (Math.max(1, length >>> 1) + length) | 1)) != length) {
            this.zza = Arrays.copyOf(a(), min);
            this.zzb = Arrays.copyOf(b(), min);
            this.zzc = Arrays.copyOf(c(), min);
        }
        a()[i3] = (~y) & a3;
        b()[i3] = obj;
        c()[i3] = obj2;
        this.zzg = i4;
        r();
        return null;
    }

    final void r() {
        this.zzf += 32;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        Map o2 = o();
        if (o2 != null) {
            return o2.remove(obj);
        }
        Object B = B(obj);
        if (B == zzd) {
            return null;
        }
        return B;
    }

    final void s(int i2) {
        this.zzf = zzcq.a(i2, 1, 1073741823);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        Map o2 = o();
        return o2 != null ? o2.size() : this.zzg;
    }

    final void t(int i2, int i3) {
        Object obj = this.zze;
        Objects.requireNonNull(obj);
        int[] a2 = a();
        Object[] b2 = b();
        Object[] c2 = c();
        int size = size();
        int i4 = size - 1;
        if (i2 >= i4) {
            b2[i2] = null;
            c2[i2] = null;
            a2[i2] = 0;
            return;
        }
        int i5 = i2 + 1;
        Object obj2 = b2[i4];
        b2[i2] = obj2;
        c2[i2] = c2[i4];
        b2[i4] = null;
        c2[i4] = null;
        a2[i2] = a2[i4];
        a2[i4] = 0;
        int a3 = zzbc.a(obj2) & i3;
        int c3 = zzbb.c(obj, a3);
        if (c3 == size) {
            zzbb.e(obj, a3, i5);
            return;
        }
        while (true) {
            int i6 = c3 - 1;
            int i7 = a2[i6];
            int i8 = i7 & i3;
            if (i8 == size) {
                a2[i6] = ((~i3) & i7) | (i5 & i3);
                return;
            }
            c3 = i8;
        }
    }

    final boolean u() {
        return this.zze == null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        Collection collection = this.zzj;
        if (collection != null) {
            return collection;
        }
        zzaz zzazVar = new zzaz(this);
        this.zzj = zzazVar;
        return zzazVar;
    }

    zzba(int i2) {
        s(12);
    }
}
