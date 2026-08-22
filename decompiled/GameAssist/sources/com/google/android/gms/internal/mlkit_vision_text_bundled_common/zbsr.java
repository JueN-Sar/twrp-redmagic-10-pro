package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.google.mlkit.common.MlKitException;

/* loaded from: classes.dex */
final class zbsr {

    /* renamed from: a, reason: collision with root package name */
    private static volatile int f12940a = 100;

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f12941b = 0;

    static int a(byte[] bArr, int i2, zbsq zbsqVar) {
        int k2 = k(bArr, i2, zbsqVar);
        int i3 = zbsqVar.f12935a;
        if (i3 < 0) {
            throw new zbuq("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        if (i3 > bArr.length - k2) {
            throw new zbuq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if (i3 == 0) {
            zbsqVar.f12937c = zbtc.zbb;
            return k2;
        }
        zbsqVar.f12937c = zbtc.l(bArr, k2, i3);
        return k2 + i3;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    static int b(int i2, byte[] bArr, int i3, int i4, zbub zbubVar, zbud zbudVar, zbwl zbwlVar, zbsq zbsqVar) {
        int i5;
        zbtu zbtuVar = zbubVar.zbb;
        zbww zbwwVar = zbudVar.f12980b.f12978h;
        Object obj = null;
        if (zbwwVar == zbww.zbn) {
            k(bArr, i3, zbsqVar);
            throw null;
        }
        switch (zbwwVar.ordinal()) {
            case 0:
                i5 = i3 + 8;
                obj = Double.valueOf(Double.longBitsToDouble(r(bArr, i3)));
                i3 = i5;
                zbtuVar.j(zbudVar.f12980b, obj);
                return i3;
            case 1:
                i5 = i3 + 4;
                obj = Float.valueOf(Float.intBitsToFloat(c(bArr, i3)));
                i3 = i5;
                zbtuVar.j(zbudVar.f12980b, obj);
                return i3;
            case 2:
            case 3:
                i3 = n(bArr, i3, zbsqVar);
                obj = Long.valueOf(zbsqVar.f12936b);
                zbtuVar.j(zbudVar.f12980b, obj);
                return i3;
            case 4:
            case 12:
                i3 = k(bArr, i3, zbsqVar);
                obj = Integer.valueOf(zbsqVar.f12935a);
                zbtuVar.j(zbudVar.f12980b, obj);
                return i3;
            case 5:
            case 15:
                i5 = i3 + 8;
                obj = Long.valueOf(r(bArr, i3));
                i3 = i5;
                zbtuVar.j(zbudVar.f12980b, obj);
                return i3;
            case 6:
            case 14:
                i5 = i3 + 4;
                obj = Integer.valueOf(c(bArr, i3));
                i3 = i5;
                zbtuVar.j(zbudVar.f12980b, obj);
                return i3;
            case 7:
                i3 = n(bArr, i3, zbsqVar);
                obj = Boolean.valueOf(zbsqVar.f12936b != 0);
                zbtuVar.j(zbudVar.f12980b, obj);
                return i3;
            case 8:
                i3 = h(bArr, i3, zbsqVar);
                obj = zbsqVar.f12937c;
                zbtuVar.j(zbudVar.f12980b, obj);
                return i3;
            case 9:
                int i6 = ((i2 >>> 3) << 3) | 4;
                zbvx b2 = zbvu.a().b(zbudVar.f12979a.getClass());
                Object f2 = zbtuVar.f(zbudVar.f12980b);
                if (f2 == null) {
                    f2 = b2.b();
                    zbtuVar.j(zbudVar.f12980b, f2);
                }
                return o(f2, b2, bArr, i3, i4, i6, zbsqVar);
            case 10:
                zbvx b3 = zbvu.a().b(zbudVar.f12979a.getClass());
                Object f3 = zbtuVar.f(zbudVar.f12980b);
                if (f3 == null) {
                    f3 = b3.b();
                    zbtuVar.j(zbudVar.f12980b, f3);
                }
                return p(f3, b3, bArr, i3, i4, zbsqVar);
            case 11:
                i3 = a(bArr, i3, zbsqVar);
                obj = zbsqVar.f12937c;
                zbtuVar.j(zbudVar.f12980b, obj);
                return i3;
            case 13:
                throw new IllegalStateException("Shouldn't reach here.");
            case 16:
                i3 = k(bArr, i3, zbsqVar);
                obj = Integer.valueOf(zbtg.a(zbsqVar.f12935a));
                zbtuVar.j(zbudVar.f12980b, obj);
                return i3;
            case MlKitException.NETWORK_ISSUE /* 17 */:
                i3 = n(bArr, i3, zbsqVar);
                obj = Long.valueOf(zbtg.b(zbsqVar.f12936b));
                zbtuVar.j(zbudVar.f12980b, obj);
                return i3;
            default:
                zbtuVar.j(zbudVar.f12980b, obj);
                return i3;
        }
    }

    static int c(byte[] bArr, int i2) {
        int i3 = bArr[i2] & 255;
        int i4 = bArr[i2 + 1] & 255;
        int i5 = bArr[i2 + 2] & 255;
        return ((bArr[i2 + 3] & 255) << 24) | (i4 << 8) | i3 | (i5 << 16);
    }

    static int d(zbvx zbvxVar, byte[] bArr, int i2, int i3, int i4, zbsq zbsqVar) {
        Object b2 = zbvxVar.b();
        int o2 = o(b2, zbvxVar, bArr, i2, i3, i4, zbsqVar);
        zbvxVar.g(b2);
        zbsqVar.f12937c = b2;
        return o2;
    }

    static int e(zbvx zbvxVar, byte[] bArr, int i2, int i3, zbsq zbsqVar) {
        Object b2 = zbvxVar.b();
        int p2 = p(b2, zbvxVar, bArr, i2, i3, zbsqVar);
        zbvxVar.g(b2);
        zbsqVar.f12937c = b2;
        return p2;
    }

    static int f(zbvx zbvxVar, int i2, byte[] bArr, int i3, int i4, zbun zbunVar, zbsq zbsqVar) {
        int e2 = e(zbvxVar, bArr, i3, i4, zbsqVar);
        zbunVar.add(zbsqVar.f12937c);
        while (e2 < i4) {
            int k2 = k(bArr, e2, zbsqVar);
            if (i2 != zbsqVar.f12935a) {
                break;
            }
            e2 = e(zbvxVar, bArr, k2, i4, zbsqVar);
            zbunVar.add(zbsqVar.f12937c);
        }
        return e2;
    }

    static int g(byte[] bArr, int i2, zbun zbunVar, zbsq zbsqVar) {
        zbug zbugVar = (zbug) zbunVar;
        int k2 = k(bArr, i2, zbsqVar);
        int i3 = zbsqVar.f12935a + k2;
        while (k2 < i3) {
            k2 = k(bArr, k2, zbsqVar);
            zbugVar.g(zbsqVar.f12935a);
        }
        if (k2 == i3) {
            return k2;
        }
        throw new zbuq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static int h(byte[] bArr, int i2, zbsq zbsqVar) {
        int k2 = k(bArr, i2, zbsqVar);
        int i3 = zbsqVar.f12935a;
        if (i3 < 0) {
            throw new zbuq("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        if (i3 == 0) {
            zbsqVar.f12937c = "";
            return k2;
        }
        zbsqVar.f12937c = new String(bArr, k2, i3, zbuo.f12984a);
        return k2 + i3;
    }

    static int i(byte[] bArr, int i2, zbsq zbsqVar) {
        int i3;
        int k2 = k(bArr, i2, zbsqVar);
        int i4 = zbsqVar.f12935a;
        if (i4 < 0) {
            throw new zbuq("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        if (i4 == 0) {
            zbsqVar.f12937c = "";
            return k2;
        }
        int i5 = zbwv.f13064a;
        int length = bArr.length;
        if ((((length - k2) - i4) | k2 | i4) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(length), Integer.valueOf(k2), Integer.valueOf(i4)));
        }
        int i6 = k2 + i4;
        char[] cArr = new char[i4];
        int i7 = 0;
        while (k2 < i6) {
            byte b2 = bArr[k2];
            if (!zbwt.d(b2)) {
                break;
            }
            k2++;
            cArr[i7] = (char) b2;
            i7++;
        }
        int i8 = i7;
        while (k2 < i6) {
            int i9 = k2 + 1;
            byte b3 = bArr[k2];
            if (zbwt.d(b3)) {
                cArr[i8] = (char) b3;
                i8++;
                k2 = i9;
                while (k2 < i6) {
                    byte b4 = bArr[k2];
                    if (zbwt.d(b4)) {
                        k2++;
                        cArr[i8] = (char) b4;
                        i8++;
                    }
                }
            } else {
                if (b3 < -32) {
                    if (i9 >= i6) {
                        throw new zbuq("Protocol message had invalid UTF-8.");
                    }
                    i3 = i8 + 1;
                    k2 += 2;
                    zbwt.c(b3, bArr[i9], cArr, i8);
                } else if (b3 < -16) {
                    if (i9 >= i6 - 1) {
                        throw new zbuq("Protocol message had invalid UTF-8.");
                    }
                    i3 = i8 + 1;
                    int i10 = k2 + 2;
                    k2 += 3;
                    zbwt.b(b3, bArr[i9], bArr[i10], cArr, i8);
                } else {
                    if (i9 >= i6 - 2) {
                        throw new zbuq("Protocol message had invalid UTF-8.");
                    }
                    byte b5 = bArr[i9];
                    int i11 = k2 + 3;
                    byte b6 = bArr[k2 + 2];
                    k2 += 4;
                    zbwt.a(b3, b5, b6, bArr[i11], cArr, i8);
                    i8 += 2;
                }
                i8 = i3;
            }
        }
        zbsqVar.f12937c = new String(cArr, 0, i8);
        return i6;
    }

    static int j(int i2, byte[] bArr, int i3, int i4, zbwm zbwmVar, zbsq zbsqVar) {
        if ((i2 >>> 3) == 0) {
            throw new zbuq("Protocol message contained an invalid tag (zero).");
        }
        int i5 = i2 & 7;
        if (i5 == 0) {
            int n2 = n(bArr, i3, zbsqVar);
            zbwmVar.j(i2, Long.valueOf(zbsqVar.f12936b));
            return n2;
        }
        if (i5 == 1) {
            zbwmVar.j(i2, Long.valueOf(r(bArr, i3)));
            return i3 + 8;
        }
        if (i5 == 2) {
            int k2 = k(bArr, i3, zbsqVar);
            int i6 = zbsqVar.f12935a;
            if (i6 < 0) {
                throw new zbuq("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            if (i6 > bArr.length - k2) {
                throw new zbuq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
            if (i6 == 0) {
                zbwmVar.j(i2, zbtc.zbb);
            } else {
                zbwmVar.j(i2, zbtc.l(bArr, k2, i6));
            }
            return k2 + i6;
        }
        if (i5 != 3) {
            if (i5 != 5) {
                throw new zbuq("Protocol message contained an invalid tag (zero).");
            }
            zbwmVar.j(i2, Integer.valueOf(c(bArr, i3)));
            return i3 + 4;
        }
        int i7 = (i2 & (-8)) | 4;
        zbwm f2 = zbwm.f();
        int i8 = zbsqVar.f12939e + 1;
        zbsqVar.f12939e = i8;
        s(i8);
        int i9 = 0;
        while (true) {
            if (i3 >= i4) {
                break;
            }
            int k3 = k(bArr, i3, zbsqVar);
            i9 = zbsqVar.f12935a;
            if (i9 == i7) {
                i3 = k3;
                break;
            }
            i3 = j(i9, bArr, k3, i4, f2, zbsqVar);
        }
        zbsqVar.f12939e--;
        if (i3 > i4 || i9 != i7) {
            throw new zbuq("Failed to parse the message.");
        }
        zbwmVar.j(i2, f2);
        return i3;
    }

    static int k(byte[] bArr, int i2, zbsq zbsqVar) {
        int i3 = i2 + 1;
        byte b2 = bArr[i2];
        if (b2 < 0) {
            return l(b2, bArr, i3, zbsqVar);
        }
        zbsqVar.f12935a = b2;
        return i3;
    }

    static int l(int i2, byte[] bArr, int i3, zbsq zbsqVar) {
        byte b2 = bArr[i3];
        int i4 = i3 + 1;
        int i5 = i2 & 127;
        if (b2 >= 0) {
            zbsqVar.f12935a = i5 | (b2 << 7);
            return i4;
        }
        int i6 = i5 | ((b2 & Byte.MAX_VALUE) << 7);
        int i7 = i3 + 2;
        byte b3 = bArr[i4];
        if (b3 >= 0) {
            zbsqVar.f12935a = i6 | (b3 << 14);
            return i7;
        }
        int i8 = i6 | ((b3 & Byte.MAX_VALUE) << 14);
        int i9 = i3 + 3;
        byte b4 = bArr[i7];
        if (b4 >= 0) {
            zbsqVar.f12935a = i8 | (b4 << 21);
            return i9;
        }
        int i10 = i8 | ((b4 & Byte.MAX_VALUE) << 21);
        int i11 = i3 + 4;
        byte b5 = bArr[i9];
        if (b5 >= 0) {
            zbsqVar.f12935a = i10 | (b5 << 28);
            return i11;
        }
        int i12 = i10 | ((b5 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i13 = i11 + 1;
            if (bArr[i11] >= 0) {
                zbsqVar.f12935a = i12;
                return i13;
            }
            i11 = i13;
        }
    }

    static int m(int i2, byte[] bArr, int i3, int i4, zbun zbunVar, zbsq zbsqVar) {
        zbug zbugVar = (zbug) zbunVar;
        int k2 = k(bArr, i3, zbsqVar);
        zbugVar.g(zbsqVar.f12935a);
        while (k2 < i4) {
            int k3 = k(bArr, k2, zbsqVar);
            if (i2 != zbsqVar.f12935a) {
                break;
            }
            k2 = k(bArr, k3, zbsqVar);
            zbugVar.g(zbsqVar.f12935a);
        }
        return k2;
    }

    static int n(byte[] bArr, int i2, zbsq zbsqVar) {
        long j2 = bArr[i2];
        int i3 = i2 + 1;
        if (j2 >= 0) {
            zbsqVar.f12936b = j2;
            return i3;
        }
        int i4 = i2 + 2;
        byte b2 = bArr[i3];
        long j3 = (j2 & 127) | ((b2 & Byte.MAX_VALUE) << 7);
        int i5 = 7;
        while (b2 < 0) {
            int i6 = i4 + 1;
            i5 += 7;
            j3 |= (r10 & Byte.MAX_VALUE) << i5;
            b2 = bArr[i4];
            i4 = i6;
        }
        zbsqVar.f12936b = j3;
        return i4;
    }

    static int o(Object obj, zbvx zbvxVar, byte[] bArr, int i2, int i3, int i4, zbsq zbsqVar) {
        zbvp zbvpVar = (zbvp) zbvxVar;
        int i5 = zbsqVar.f12939e + 1;
        zbsqVar.f12939e = i5;
        s(i5);
        int z = zbvpVar.z(obj, bArr, i2, i3, i4, zbsqVar);
        zbsqVar.f12939e--;
        zbsqVar.f12937c = obj;
        return z;
    }

    static int p(Object obj, zbvx zbvxVar, byte[] bArr, int i2, int i3, zbsq zbsqVar) {
        int i4 = i2 + 1;
        int i5 = bArr[i2];
        if (i5 < 0) {
            i4 = l(i5, bArr, i4, zbsqVar);
            i5 = zbsqVar.f12935a;
        }
        int i6 = i4;
        if (i5 < 0 || i5 > i3 - i6) {
            throw new zbuq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        int i7 = zbsqVar.f12939e + 1;
        zbsqVar.f12939e = i7;
        s(i7);
        int i8 = i5 + i6;
        zbvxVar.f(obj, bArr, i6, i8, zbsqVar);
        zbsqVar.f12939e--;
        zbsqVar.f12937c = obj;
        return i8;
    }

    static int q(int i2, byte[] bArr, int i3, int i4, zbsq zbsqVar) {
        if ((i2 >>> 3) == 0) {
            throw new zbuq("Protocol message contained an invalid tag (zero).");
        }
        int i5 = i2 & 7;
        if (i5 == 0) {
            return n(bArr, i3, zbsqVar);
        }
        if (i5 == 1) {
            return i3 + 8;
        }
        if (i5 == 2) {
            return k(bArr, i3, zbsqVar) + zbsqVar.f12935a;
        }
        if (i5 != 3) {
            if (i5 == 5) {
                return i3 + 4;
            }
            throw new zbuq("Protocol message contained an invalid tag (zero).");
        }
        int i6 = (i2 & (-8)) | 4;
        int i7 = 0;
        while (i3 < i4) {
            i3 = k(bArr, i3, zbsqVar);
            i7 = zbsqVar.f12935a;
            if (i7 == i6) {
                break;
            }
            i3 = q(i7, bArr, i3, i4, zbsqVar);
        }
        if (i3 > i4 || i7 != i6) {
            throw new zbuq("Failed to parse the message.");
        }
        return i3;
    }

    static long r(byte[] bArr, int i2) {
        return (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16) | ((bArr[i2 + 3] & 255) << 24) | ((bArr[i2 + 4] & 255) << 32) | ((bArr[i2 + 5] & 255) << 40) | ((bArr[i2 + 6] & 255) << 48) | ((bArr[i2 + 7] & 255) << 56);
    }

    private static void s(int i2) {
        if (i2 >= f12940a) {
            throw new zbuq("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
        }
    }
}
