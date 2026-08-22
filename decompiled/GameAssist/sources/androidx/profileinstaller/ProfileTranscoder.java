package androidx.profileinstaller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes.dex */
class ProfileTranscoder {

    /* renamed from: a, reason: collision with root package name */
    static final byte[] f4825a = {112, 114, 111, 0};

    /* renamed from: b, reason: collision with root package name */
    static final byte[] f4826b = {112, 114, 109, 0};

    private static void A(byte[] bArr, int i2, int i3, DexProfileData dexProfileData) {
        int m2 = m(i2, i3, dexProfileData.f4819g);
        int i4 = m2 / 8;
        bArr[i4] = (byte) ((1 << (m2 % 8)) | bArr[i4]);
    }

    private static void B(InputStream inputStream) {
        Encoding.h(inputStream);
        int j2 = Encoding.j(inputStream);
        if (j2 == 6 || j2 == 7) {
            return;
        }
        while (j2 > 0) {
            Encoding.j(inputStream);
            for (int j3 = Encoding.j(inputStream); j3 > 0; j3--) {
                Encoding.h(inputStream);
            }
            j2--;
        }
    }

    static boolean C(OutputStream outputStream, byte[] bArr, DexProfileData[] dexProfileDataArr) {
        if (Arrays.equals(bArr, ProfileVersion.f4838a)) {
            P(outputStream, dexProfileDataArr);
            return true;
        }
        if (Arrays.equals(bArr, ProfileVersion.f4839b)) {
            O(outputStream, dexProfileDataArr);
            return true;
        }
        if (Arrays.equals(bArr, ProfileVersion.f4841d)) {
            M(outputStream, dexProfileDataArr);
            return true;
        }
        if (Arrays.equals(bArr, ProfileVersion.f4840c)) {
            N(outputStream, dexProfileDataArr);
            return true;
        }
        if (!Arrays.equals(bArr, ProfileVersion.f4842e)) {
            return false;
        }
        L(outputStream, dexProfileDataArr);
        return true;
    }

    private static void D(OutputStream outputStream, DexProfileData dexProfileData) {
        int[] iArr = dexProfileData.f4820h;
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = iArr[i2];
            Encoding.p(outputStream, i4 - i3);
            i2++;
            i3 = i4;
        }
    }

    private static WritableFileSection E(DexProfileData[] dexProfileDataArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Encoding.p(byteArrayOutputStream, dexProfileDataArr.length);
            int i2 = 2;
            for (DexProfileData dexProfileData : dexProfileDataArr) {
                Encoding.q(byteArrayOutputStream, dexProfileData.f4815c);
                Encoding.q(byteArrayOutputStream, dexProfileData.f4816d);
                Encoding.q(byteArrayOutputStream, dexProfileData.f4819g);
                String j2 = j(dexProfileData.f4813a, dexProfileData.f4814b, ProfileVersion.f4838a);
                int k2 = Encoding.k(j2);
                Encoding.p(byteArrayOutputStream, k2);
                i2 = i2 + 14 + k2;
                Encoding.n(byteArrayOutputStream, j2);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (i2 == byteArray.length) {
                WritableFileSection writableFileSection = new WritableFileSection(FileSectionType.DEX_FILES, i2, byteArray, false);
                byteArrayOutputStream.close();
                return writableFileSection;
            }
            throw Encoding.c("Expected size " + i2 + ", does not match actual size " + byteArray.length);
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    static void F(OutputStream outputStream, byte[] bArr) {
        outputStream.write(f4825a);
        outputStream.write(bArr);
    }

    private static void G(OutputStream outputStream, DexProfileData dexProfileData) {
        K(outputStream, dexProfileData);
        D(outputStream, dexProfileData);
        I(outputStream, dexProfileData);
    }

    private static void H(OutputStream outputStream, DexProfileData dexProfileData, String str) {
        Encoding.p(outputStream, Encoding.k(str));
        Encoding.p(outputStream, dexProfileData.f4817e);
        Encoding.q(outputStream, dexProfileData.f4818f);
        Encoding.q(outputStream, dexProfileData.f4815c);
        Encoding.q(outputStream, dexProfileData.f4819g);
        Encoding.n(outputStream, str);
    }

    private static void I(OutputStream outputStream, DexProfileData dexProfileData) {
        byte[] bArr = new byte[k(dexProfileData.f4819g)];
        for (Map.Entry entry : dexProfileData.f4821i.entrySet()) {
            int intValue = ((Integer) entry.getKey()).intValue();
            int intValue2 = ((Integer) entry.getValue()).intValue();
            if ((intValue2 & 2) != 0) {
                A(bArr, 2, intValue, dexProfileData);
            }
            if ((intValue2 & 4) != 0) {
                A(bArr, 4, intValue, dexProfileData);
            }
        }
        outputStream.write(bArr);
    }

    private static void J(OutputStream outputStream, int i2, DexProfileData dexProfileData) {
        byte[] bArr = new byte[l(i2, dexProfileData.f4819g)];
        for (Map.Entry entry : dexProfileData.f4821i.entrySet()) {
            int intValue = ((Integer) entry.getKey()).intValue();
            int intValue2 = ((Integer) entry.getValue()).intValue();
            int i3 = 0;
            for (int i4 = 1; i4 <= 4; i4 <<= 1) {
                if (i4 != 1 && (i4 & i2) != 0) {
                    if ((i4 & intValue2) == i4) {
                        int i5 = (dexProfileData.f4819g * i3) + intValue;
                        int i6 = i5 / 8;
                        bArr[i6] = (byte) ((1 << (i5 % 8)) | bArr[i6]);
                    }
                    i3++;
                }
            }
        }
        outputStream.write(bArr);
    }

    private static void K(OutputStream outputStream, DexProfileData dexProfileData) {
        int i2 = 0;
        for (Map.Entry entry : dexProfileData.f4821i.entrySet()) {
            int intValue = ((Integer) entry.getKey()).intValue();
            if ((((Integer) entry.getValue()).intValue() & 1) != 0) {
                Encoding.p(outputStream, intValue - i2);
                Encoding.p(outputStream, 0);
                i2 = intValue;
            }
        }
    }

    private static void L(OutputStream outputStream, DexProfileData[] dexProfileDataArr) {
        Encoding.p(outputStream, dexProfileDataArr.length);
        for (DexProfileData dexProfileData : dexProfileDataArr) {
            String j2 = j(dexProfileData.f4813a, dexProfileData.f4814b, ProfileVersion.f4842e);
            Encoding.p(outputStream, Encoding.k(j2));
            Encoding.p(outputStream, dexProfileData.f4821i.size());
            Encoding.p(outputStream, dexProfileData.f4820h.length);
            Encoding.q(outputStream, dexProfileData.f4815c);
            Encoding.n(outputStream, j2);
            Iterator it = dexProfileData.f4821i.keySet().iterator();
            while (it.hasNext()) {
                Encoding.p(outputStream, ((Integer) it.next()).intValue());
            }
            for (int i2 : dexProfileData.f4820h) {
                Encoding.p(outputStream, i2);
            }
        }
    }

    private static void M(OutputStream outputStream, DexProfileData[] dexProfileDataArr) {
        Encoding.r(outputStream, dexProfileDataArr.length);
        for (DexProfileData dexProfileData : dexProfileDataArr) {
            int size = dexProfileData.f4821i.size() * 4;
            String j2 = j(dexProfileData.f4813a, dexProfileData.f4814b, ProfileVersion.f4841d);
            Encoding.p(outputStream, Encoding.k(j2));
            Encoding.p(outputStream, dexProfileData.f4820h.length);
            Encoding.q(outputStream, size);
            Encoding.q(outputStream, dexProfileData.f4815c);
            Encoding.n(outputStream, j2);
            Iterator it = dexProfileData.f4821i.keySet().iterator();
            while (it.hasNext()) {
                Encoding.p(outputStream, ((Integer) it.next()).intValue());
                Encoding.p(outputStream, 0);
            }
            for (int i2 : dexProfileData.f4820h) {
                Encoding.p(outputStream, i2);
            }
        }
    }

    private static void N(OutputStream outputStream, DexProfileData[] dexProfileDataArr) {
        byte[] b2 = b(dexProfileDataArr, ProfileVersion.f4840c);
        Encoding.r(outputStream, dexProfileDataArr.length);
        Encoding.m(outputStream, b2);
    }

    private static void O(OutputStream outputStream, DexProfileData[] dexProfileDataArr) {
        byte[] b2 = b(dexProfileDataArr, ProfileVersion.f4839b);
        Encoding.r(outputStream, dexProfileDataArr.length);
        Encoding.m(outputStream, b2);
    }

    private static void P(OutputStream outputStream, DexProfileData[] dexProfileDataArr) {
        Q(outputStream, dexProfileDataArr);
    }

    private static void Q(OutputStream outputStream, DexProfileData[] dexProfileDataArr) {
        int length;
        ArrayList arrayList = new ArrayList(3);
        ArrayList arrayList2 = new ArrayList(3);
        arrayList.add(E(dexProfileDataArr));
        arrayList.add(c(dexProfileDataArr));
        arrayList.add(d(dexProfileDataArr));
        long length2 = ProfileVersion.f4838a.length + f4825a.length + 4 + (arrayList.size() * 16);
        Encoding.q(outputStream, arrayList.size());
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            WritableFileSection writableFileSection = (WritableFileSection) arrayList.get(i2);
            Encoding.q(outputStream, writableFileSection.f4845a.d());
            Encoding.q(outputStream, length2);
            if (writableFileSection.f4848d) {
                byte[] bArr = writableFileSection.f4847c;
                long length3 = bArr.length;
                byte[] b2 = Encoding.b(bArr);
                arrayList2.add(b2);
                Encoding.q(outputStream, b2.length);
                Encoding.q(outputStream, length3);
                length = b2.length;
            } else {
                arrayList2.add(writableFileSection.f4847c);
                Encoding.q(outputStream, writableFileSection.f4847c.length);
                Encoding.q(outputStream, 0L);
                length = writableFileSection.f4847c.length;
            }
            length2 += length;
        }
        for (int i3 = 0; i3 < arrayList2.size(); i3++) {
            outputStream.write((byte[]) arrayList2.get(i3));
        }
    }

    private static int a(DexProfileData dexProfileData) {
        Iterator it = dexProfileData.f4821i.entrySet().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 |= ((Integer) ((Map.Entry) it.next()).getValue()).intValue();
        }
        return i2;
    }

    private static byte[] b(DexProfileData[] dexProfileDataArr, byte[] bArr) {
        int i2 = 0;
        int i3 = 0;
        for (DexProfileData dexProfileData : dexProfileDataArr) {
            i3 += Encoding.k(j(dexProfileData.f4813a, dexProfileData.f4814b, bArr)) + 16 + (dexProfileData.f4817e * 2) + dexProfileData.f4818f + k(dexProfileData.f4819g);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i3);
        if (Arrays.equals(bArr, ProfileVersion.f4840c)) {
            int length = dexProfileDataArr.length;
            while (i2 < length) {
                DexProfileData dexProfileData2 = dexProfileDataArr[i2];
                H(byteArrayOutputStream, dexProfileData2, j(dexProfileData2.f4813a, dexProfileData2.f4814b, bArr));
                G(byteArrayOutputStream, dexProfileData2);
                i2++;
            }
        } else {
            for (DexProfileData dexProfileData3 : dexProfileDataArr) {
                H(byteArrayOutputStream, dexProfileData3, j(dexProfileData3.f4813a, dexProfileData3.f4814b, bArr));
            }
            int length2 = dexProfileDataArr.length;
            while (i2 < length2) {
                G(byteArrayOutputStream, dexProfileDataArr[i2]);
                i2++;
            }
        }
        if (byteArrayOutputStream.size() == i3) {
            return byteArrayOutputStream.toByteArray();
        }
        throw Encoding.c("The bytes saved do not match expectation. actual=" + byteArrayOutputStream.size() + " expected=" + i3);
    }

    private static WritableFileSection c(DexProfileData[] dexProfileDataArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        for (int i3 = 0; i3 < dexProfileDataArr.length; i3++) {
            try {
                DexProfileData dexProfileData = dexProfileDataArr[i3];
                Encoding.p(byteArrayOutputStream, i3);
                Encoding.p(byteArrayOutputStream, dexProfileData.f4817e);
                i2 = i2 + 4 + (dexProfileData.f4817e * 2);
                D(byteArrayOutputStream, dexProfileData);
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (i2 == byteArray.length) {
            WritableFileSection writableFileSection = new WritableFileSection(FileSectionType.CLASSES, i2, byteArray, true);
            byteArrayOutputStream.close();
            return writableFileSection;
        }
        throw Encoding.c("Expected size " + i2 + ", does not match actual size " + byteArray.length);
    }

    private static WritableFileSection d(DexProfileData[] dexProfileDataArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        for (int i3 = 0; i3 < dexProfileDataArr.length; i3++) {
            try {
                DexProfileData dexProfileData = dexProfileDataArr[i3];
                int a2 = a(dexProfileData);
                byte[] e2 = e(a2, dexProfileData);
                byte[] f2 = f(dexProfileData);
                Encoding.p(byteArrayOutputStream, i3);
                int length = e2.length + 2 + f2.length;
                Encoding.q(byteArrayOutputStream, length);
                Encoding.p(byteArrayOutputStream, a2);
                byteArrayOutputStream.write(e2);
                byteArrayOutputStream.write(f2);
                i2 = i2 + 6 + length;
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (i2 == byteArray.length) {
            WritableFileSection writableFileSection = new WritableFileSection(FileSectionType.METHODS, i2, byteArray, true);
            byteArrayOutputStream.close();
            return writableFileSection;
        }
        throw Encoding.c("Expected size " + i2 + ", does not match actual size " + byteArray.length);
    }

    private static byte[] e(int i2, DexProfileData dexProfileData) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            J(byteArrayOutputStream, i2, dexProfileData);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static byte[] f(DexProfileData dexProfileData) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            K(byteArrayOutputStream, dexProfileData);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static String g(String str, String str2) {
        return "!".equals(str2) ? str.replace(":", "!") : ":".equals(str2) ? str.replace("!", ":") : str;
    }

    private static String h(String str) {
        int indexOf = str.indexOf("!");
        if (indexOf < 0) {
            indexOf = str.indexOf(":");
        }
        return indexOf > 0 ? str.substring(indexOf + 1) : str;
    }

    private static DexProfileData i(DexProfileData[] dexProfileDataArr, String str) {
        if (dexProfileDataArr.length <= 0) {
            return null;
        }
        String h2 = h(str);
        for (int i2 = 0; i2 < dexProfileDataArr.length; i2++) {
            if (dexProfileDataArr[i2].f4814b.equals(h2)) {
                return dexProfileDataArr[i2];
            }
        }
        return null;
    }

    private static String j(String str, String str2, byte[] bArr) {
        String a2 = ProfileVersion.a(bArr);
        if (str.length() <= 0) {
            return g(str2, a2);
        }
        if (str2.equals("classes.dex")) {
            return str;
        }
        if (str2.contains("!") || str2.contains(":")) {
            return g(str2, a2);
        }
        if (str2.endsWith(".apk")) {
            return str2;
        }
        return str + ProfileVersion.a(bArr) + str2;
    }

    private static int k(int i2) {
        return z(i2 * 2) / 8;
    }

    private static int l(int i2, int i3) {
        return z(Integer.bitCount(i2 & (-2)) * i3) / 8;
    }

    private static int m(int i2, int i3, int i4) {
        if (i2 == 1) {
            throw Encoding.c("HOT methods are not stored in the bitmap");
        }
        if (i2 == 2) {
            return i3;
        }
        if (i2 == 4) {
            return i3 + i4;
        }
        throw Encoding.c("Unexpected flag: " + i2);
    }

    private static int[] n(InputStream inputStream, int i2) {
        int[] iArr = new int[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += Encoding.h(inputStream);
            iArr[i4] = i3;
        }
        return iArr;
    }

    private static int o(BitSet bitSet, int i2, int i3) {
        int i4 = bitSet.get(m(2, i2, i3)) ? 2 : 0;
        return bitSet.get(m(4, i2, i3)) ? i4 | 4 : i4;
    }

    static byte[] p(InputStream inputStream, byte[] bArr) {
        if (Arrays.equals(bArr, Encoding.d(inputStream, bArr.length))) {
            return Encoding.d(inputStream, ProfileVersion.f4839b.length);
        }
        throw Encoding.c("Invalid magic");
    }

    private static void q(InputStream inputStream, DexProfileData dexProfileData) {
        int available = inputStream.available() - dexProfileData.f4818f;
        int i2 = 0;
        while (inputStream.available() > available) {
            i2 += Encoding.h(inputStream);
            dexProfileData.f4821i.put(Integer.valueOf(i2), 1);
            for (int h2 = Encoding.h(inputStream); h2 > 0; h2--) {
                B(inputStream);
            }
        }
        if (inputStream.available() != available) {
            throw Encoding.c("Read too much data during profile line parse");
        }
    }

    static DexProfileData[] r(InputStream inputStream, byte[] bArr, byte[] bArr2, DexProfileData[] dexProfileDataArr) {
        if (Arrays.equals(bArr, ProfileVersion.f4843f)) {
            if (Arrays.equals(ProfileVersion.f4838a, bArr2)) {
                throw Encoding.c("Requires new Baseline Profile Metadata. Please rebuild the APK with Android Gradle Plugin 7.2 Canary 7 or higher");
            }
            return s(inputStream, bArr, dexProfileDataArr);
        }
        if (Arrays.equals(bArr, ProfileVersion.f4844g)) {
            return u(inputStream, bArr2, dexProfileDataArr);
        }
        throw Encoding.c("Unsupported meta version");
    }

    static DexProfileData[] s(InputStream inputStream, byte[] bArr, DexProfileData[] dexProfileDataArr) {
        if (!Arrays.equals(bArr, ProfileVersion.f4843f)) {
            throw Encoding.c("Unsupported meta version");
        }
        int j2 = Encoding.j(inputStream);
        byte[] e2 = Encoding.e(inputStream, (int) Encoding.i(inputStream), (int) Encoding.i(inputStream));
        if (inputStream.read() > 0) {
            throw Encoding.c("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(e2);
        try {
            DexProfileData[] t = t(byteArrayInputStream, j2, dexProfileDataArr);
            byteArrayInputStream.close();
            return t;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static DexProfileData[] t(InputStream inputStream, int i2, DexProfileData[] dexProfileDataArr) {
        if (inputStream.available() == 0) {
            return new DexProfileData[0];
        }
        if (i2 != dexProfileDataArr.length) {
            throw Encoding.c("Mismatched number of dex files found in metadata");
        }
        String[] strArr = new String[i2];
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int h2 = Encoding.h(inputStream);
            iArr[i3] = Encoding.h(inputStream);
            strArr[i3] = Encoding.f(inputStream, h2);
        }
        for (int i4 = 0; i4 < i2; i4++) {
            DexProfileData dexProfileData = dexProfileDataArr[i4];
            if (!dexProfileData.f4814b.equals(strArr[i4])) {
                throw Encoding.c("Order of dexfiles in metadata did not match baseline");
            }
            int i5 = iArr[i4];
            dexProfileData.f4817e = i5;
            dexProfileData.f4820h = n(inputStream, i5);
        }
        return dexProfileDataArr;
    }

    static DexProfileData[] u(InputStream inputStream, byte[] bArr, DexProfileData[] dexProfileDataArr) {
        int h2 = Encoding.h(inputStream);
        byte[] e2 = Encoding.e(inputStream, (int) Encoding.i(inputStream), (int) Encoding.i(inputStream));
        if (inputStream.read() > 0) {
            throw Encoding.c("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(e2);
        try {
            DexProfileData[] v = v(byteArrayInputStream, bArr, h2, dexProfileDataArr);
            byteArrayInputStream.close();
            return v;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static DexProfileData[] v(InputStream inputStream, byte[] bArr, int i2, DexProfileData[] dexProfileDataArr) {
        if (inputStream.available() == 0) {
            return new DexProfileData[0];
        }
        if (i2 != dexProfileDataArr.length) {
            throw Encoding.c("Mismatched number of dex files found in metadata");
        }
        for (int i3 = 0; i3 < i2; i3++) {
            Encoding.h(inputStream);
            String f2 = Encoding.f(inputStream, Encoding.h(inputStream));
            long i4 = Encoding.i(inputStream);
            int h2 = Encoding.h(inputStream);
            DexProfileData i5 = i(dexProfileDataArr, f2);
            if (i5 == null) {
                throw Encoding.c("Missing profile key: " + f2);
            }
            i5.f4816d = i4;
            int[] n2 = n(inputStream, h2);
            if (Arrays.equals(bArr, ProfileVersion.f4842e)) {
                i5.f4817e = h2;
                i5.f4820h = n2;
            }
        }
        return dexProfileDataArr;
    }

    private static void w(InputStream inputStream, DexProfileData dexProfileData) {
        BitSet valueOf = BitSet.valueOf(Encoding.d(inputStream, Encoding.a(dexProfileData.f4819g * 2)));
        int i2 = 0;
        while (true) {
            int i3 = dexProfileData.f4819g;
            if (i2 >= i3) {
                return;
            }
            int o2 = o(valueOf, i2, i3);
            if (o2 != 0) {
                Integer num = (Integer) dexProfileData.f4821i.get(Integer.valueOf(i2));
                if (num == null) {
                    num = 0;
                }
                dexProfileData.f4821i.put(Integer.valueOf(i2), Integer.valueOf(o2 | num.intValue()));
            }
            i2++;
        }
    }

    static DexProfileData[] x(InputStream inputStream, byte[] bArr, String str) {
        if (!Arrays.equals(bArr, ProfileVersion.f4839b)) {
            throw Encoding.c("Unsupported version");
        }
        int j2 = Encoding.j(inputStream);
        byte[] e2 = Encoding.e(inputStream, (int) Encoding.i(inputStream), (int) Encoding.i(inputStream));
        if (inputStream.read() > 0) {
            throw Encoding.c("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(e2);
        try {
            DexProfileData[] y = y(byteArrayInputStream, str, j2);
            byteArrayInputStream.close();
            return y;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static DexProfileData[] y(InputStream inputStream, String str, int i2) {
        if (inputStream.available() == 0) {
            return new DexProfileData[0];
        }
        DexProfileData[] dexProfileDataArr = new DexProfileData[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int h2 = Encoding.h(inputStream);
            int h3 = Encoding.h(inputStream);
            dexProfileDataArr[i3] = new DexProfileData(str, Encoding.f(inputStream, h2), Encoding.i(inputStream), 0L, h3, (int) Encoding.i(inputStream), (int) Encoding.i(inputStream), new int[h3], new TreeMap());
        }
        for (int i4 = 0; i4 < i2; i4++) {
            DexProfileData dexProfileData = dexProfileDataArr[i4];
            q(inputStream, dexProfileData);
            dexProfileData.f4820h = n(inputStream, dexProfileData.f4817e);
            w(inputStream, dexProfileData);
        }
        return dexProfileDataArr;
    }

    private static int z(int i2) {
        return (i2 + 7) & (-8);
    }
}
