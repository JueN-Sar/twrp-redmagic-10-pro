package com.google.android.gms.internal.mlkit_common;

import com.zte.shared.wrapper.VirtualHandleWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* loaded from: classes.dex */
final class zzaq extends zzai {
    static final zzai zza = new zzaq(null, new Object[0], 0);
    final transient Object[] zzb;

    @CheckForNull
    private final transient Object zzc;
    private final transient int zzd;

    private zzaq(@CheckForNull Object obj, Object[] objArr, int i2) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r5v12, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r5v2, types: [int[]] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.Object[]] */
    static zzaq g(int i2, Object[] objArr, zzah zzahVar) {
        int i3;
        short[] sArr;
        char c2;
        char c3;
        byte[] bArr;
        int i4 = i2;
        Object[] objArr2 = objArr;
        if (i4 == 0) {
            return (zzaq) zza;
        }
        Object obj = null;
        int i5 = 1;
        if (i4 == 1) {
            Object obj2 = objArr2[0];
            Objects.requireNonNull(obj2);
            Object obj3 = objArr2[1];
            Objects.requireNonNull(obj3);
            zzw.a(obj2, obj3);
            return new zzaq(null, objArr2, 1);
        }
        zzt.b(i4, objArr2.length >> 1, VirtualHandleWrapper.KEY_INDEX);
        int max = Math.max(i4, 2);
        if (max < 751619276) {
            i3 = Integer.highestOneBit(max - 1);
            do {
                i3 += i3;
            } while (i3 * 0.7d < max);
        } else {
            i3 = WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME;
            if (max >= 1073741824) {
                throw new IllegalArgumentException("collection too large");
            }
        }
        if (i4 == 1) {
            Object obj4 = objArr2[0];
            Objects.requireNonNull(obj4);
            Object obj5 = objArr2[1];
            Objects.requireNonNull(obj5);
            zzw.a(obj4, obj5);
            i4 = 1;
            c2 = 1;
            c3 = 2;
        } else {
            int i6 = i3 - 1;
            char c4 = 65535;
            if (i3 <= 128) {
                byte[] bArr2 = new byte[i3];
                Arrays.fill(bArr2, (byte) -1);
                int i7 = 0;
                int i8 = 0;
                while (i7 < i4) {
                    int i9 = i8 + i8;
                    int i10 = i7 + i7;
                    Object obj6 = objArr2[i10];
                    Objects.requireNonNull(obj6);
                    Object obj7 = objArr2[i10 ^ i5];
                    Objects.requireNonNull(obj7);
                    zzw.a(obj6, obj7);
                    int a2 = zzy.a(obj6.hashCode());
                    while (true) {
                        int i11 = a2 & i6;
                        int i12 = bArr2[i11] & 255;
                        if (i12 == 255) {
                            bArr2[i11] = (byte) i9;
                            if (i8 < i7) {
                                objArr2[i9] = obj6;
                                objArr2[i9 ^ 1] = obj7;
                            }
                            i8++;
                        } else {
                            if (obj6.equals(objArr2[i12])) {
                                int i13 = i12 ^ 1;
                                Object obj8 = objArr2[i13];
                                Objects.requireNonNull(obj8);
                                zzag zzagVar = new zzag(obj6, obj7, obj8);
                                objArr2[i13] = obj7;
                                obj = zzagVar;
                                break;
                            }
                            a2 = i11 + 1;
                        }
                    }
                    i7++;
                    i5 = 1;
                }
                if (i8 == i4) {
                    bArr = bArr2;
                } else {
                    bArr = new Object[]{bArr2, Integer.valueOf(i8), obj};
                    c3 = 2;
                    c2 = 1;
                    obj = bArr;
                }
            } else if (i3 <= 32768) {
                sArr = new short[i3];
                Arrays.fill(sArr, (short) -1);
                int i14 = 0;
                for (int i15 = 0; i15 < i4; i15++) {
                    int i16 = i14 + i14;
                    int i17 = i15 + i15;
                    Object obj9 = objArr2[i17];
                    Objects.requireNonNull(obj9);
                    Object obj10 = objArr2[i17 ^ 1];
                    Objects.requireNonNull(obj10);
                    zzw.a(obj9, obj10);
                    int a3 = zzy.a(obj9.hashCode());
                    while (true) {
                        int i18 = a3 & i6;
                        char c5 = (char) sArr[i18];
                        if (c5 == 65535) {
                            sArr[i18] = (short) i16;
                            if (i14 < i15) {
                                objArr2[i16] = obj9;
                                objArr2[i16 ^ 1] = obj10;
                            }
                            i14++;
                        } else {
                            if (obj9.equals(objArr2[c5])) {
                                int i19 = c5 ^ 1;
                                Object obj11 = objArr2[i19];
                                Objects.requireNonNull(obj11);
                                zzag zzagVar2 = new zzag(obj9, obj10, obj11);
                                objArr2[i19] = obj10;
                                obj = zzagVar2;
                                break;
                            }
                            a3 = i18 + 1;
                        }
                    }
                }
                if (i14 != i4) {
                    c3 = 2;
                    obj = new Object[]{sArr, Integer.valueOf(i14), obj};
                    c2 = 1;
                }
                bArr = sArr;
            } else {
                int i20 = 1;
                sArr = new int[i3];
                Arrays.fill((int[]) sArr, -1);
                int i21 = 0;
                int i22 = 0;
                while (i21 < i4) {
                    int i23 = i22 + i22;
                    int i24 = i21 + i21;
                    Object obj12 = objArr2[i24];
                    Objects.requireNonNull(obj12);
                    Object obj13 = objArr2[i24 ^ i20];
                    Objects.requireNonNull(obj13);
                    zzw.a(obj12, obj13);
                    int a4 = zzy.a(obj12.hashCode());
                    while (true) {
                        int i25 = a4 & i6;
                        ?? r15 = sArr[i25];
                        if (r15 == c4) {
                            sArr[i25] = i23;
                            if (i22 < i21) {
                                objArr2[i23] = obj12;
                                objArr2[i23 ^ 1] = obj13;
                            }
                            i22++;
                        } else {
                            if (obj12.equals(objArr2[r15])) {
                                int i26 = r15 ^ 1;
                                Object obj14 = objArr2[i26];
                                Objects.requireNonNull(obj14);
                                zzag zzagVar3 = new zzag(obj12, obj13, obj14);
                                objArr2[i26] = obj13;
                                obj = zzagVar3;
                                break;
                            }
                            a4 = i25 + 1;
                            c4 = 65535;
                        }
                    }
                    i21++;
                    i20 = 1;
                    c4 = 65535;
                }
                if (i22 != i4) {
                    c2 = 1;
                    c3 = 2;
                    obj = new Object[]{sArr, Integer.valueOf(i22), obj};
                }
                bArr = sArr;
            }
            c3 = 2;
            c2 = 1;
            obj = bArr;
        }
        boolean z = obj instanceof Object[];
        Object obj15 = obj;
        if (z) {
            Object[] objArr3 = (Object[]) obj;
            zzag zzagVar4 = (zzag) objArr3[c3];
            if (zzahVar == null) {
                throw zzagVar4.a();
            }
            zzahVar.f11422c = zzagVar4;
            Object obj16 = objArr3[0];
            int intValue = ((Integer) objArr3[c2]).intValue();
            objArr2 = Arrays.copyOf(objArr2, intValue + intValue);
            obj15 = obj16;
            i4 = intValue;
        }
        return new zzaq(obj15, objArr2, i4);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzai
    final zzab a() {
        return new zzap(this.zzb, 1, this.zzd);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzai
    final zzaj d() {
        return new zzan(this, this.zzb, 0, this.zzd);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzai
    final zzaj e() {
        return new zzao(this, new zzap(this.zzb, 0, this.zzd));
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x009e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x009f A[RETURN] */
    @Override // com.google.android.gms.internal.mlkit_common.zzai, java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object get(java.lang.Object r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 != 0) goto L6
        L3:
            r8 = r0
            goto L9c
        L6:
            int r1 = r8.zzd
            java.lang.Object[] r2 = r8.zzb
            r3 = 1
            if (r1 != r3) goto L20
            r8 = 0
            r8 = r2[r8]
            java.util.Objects.requireNonNull(r8)
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L3
            r8 = r2[r3]
            java.util.Objects.requireNonNull(r8)
            goto L9c
        L20:
            java.lang.Object r8 = r8.zzc
            if (r8 != 0) goto L25
            goto L3
        L25:
            boolean r1 = r8 instanceof byte[]
            r4 = -1
            if (r1 == 0) goto L51
            r1 = r8
            byte[] r1 = (byte[]) r1
            int r8 = r1.length
            int r5 = r8 + (-1)
            int r8 = r9.hashCode()
            int r8 = com.google.android.gms.internal.mlkit_common.zzy.a(r8)
        L38:
            r8 = r8 & r5
            r4 = r1[r8]
            r6 = 255(0xff, float:3.57E-43)
            r4 = r4 & r6
            if (r4 != r6) goto L41
            goto L3
        L41:
            r6 = r2[r4]
            boolean r6 = r9.equals(r6)
            if (r6 == 0) goto L4e
            r8 = r4 ^ 1
            r8 = r2[r8]
            goto L9c
        L4e:
            int r8 = r8 + 1
            goto L38
        L51:
            boolean r1 = r8 instanceof short[]
            if (r1 == 0) goto L7d
            r1 = r8
            short[] r1 = (short[]) r1
            int r8 = r1.length
            int r5 = r8 + (-1)
            int r8 = r9.hashCode()
            int r8 = com.google.android.gms.internal.mlkit_common.zzy.a(r8)
        L63:
            r8 = r8 & r5
            short r4 = r1[r8]
            char r4 = (char) r4
            r6 = 65535(0xffff, float:9.1834E-41)
            if (r4 != r6) goto L6d
            goto L3
        L6d:
            r6 = r2[r4]
            boolean r6 = r9.equals(r6)
            if (r6 == 0) goto L7a
            r8 = r4 ^ 1
            r8 = r2[r8]
            goto L9c
        L7a:
            int r8 = r8 + 1
            goto L63
        L7d:
            int[] r8 = (int[]) r8
            int r1 = r8.length
            int r1 = r1 + r4
            int r5 = r9.hashCode()
            int r5 = com.google.android.gms.internal.mlkit_common.zzy.a(r5)
        L89:
            r5 = r5 & r1
            r6 = r8[r5]
            if (r6 != r4) goto L90
            goto L3
        L90:
            r7 = r2[r6]
            boolean r7 = r9.equals(r7)
            if (r7 == 0) goto La0
            r8 = r6 ^ 1
            r8 = r2[r8]
        L9c:
            if (r8 != 0) goto L9f
            return r0
        L9f:
            return r8
        La0:
            int r5 = r5 + 1
            goto L89
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_common.zzaq.get(java.lang.Object):java.lang.Object");
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }
}
