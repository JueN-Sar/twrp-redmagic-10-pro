package com.google.android.material.color;

import android.content.Context;
import android.util.Pair;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
final class ColorResourcesTableCreator {

    /* renamed from: a, reason: collision with root package name */
    private static byte f14225a;

    /* renamed from: b, reason: collision with root package name */
    private static final PackageInfo f14226b = new PackageInfo(1, "android");

    /* renamed from: c, reason: collision with root package name */
    private static final Comparator f14227c = new Comparator<ColorResource>() { // from class: com.google.android.material.color.ColorResourcesTableCreator.1
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(ColorResource colorResource, ColorResource colorResource2) {
            return colorResource.f14230c - colorResource2.f14230c;
        }
    };

    static class ColorResource {

        /* renamed from: a, reason: collision with root package name */
        private final byte f14228a;

        /* renamed from: b, reason: collision with root package name */
        private final byte f14229b;

        /* renamed from: c, reason: collision with root package name */
        private final short f14230c;

        /* renamed from: d, reason: collision with root package name */
        private final String f14231d;

        /* renamed from: e, reason: collision with root package name */
        private final int f14232e;

        ColorResource(int i2, String str, int i3) {
            this.f14231d = str;
            this.f14232e = i3;
            this.f14230c = (short) (65535 & i2);
            this.f14229b = (byte) ((i2 >> 16) & 255);
            this.f14228a = (byte) ((i2 >> 24) & 255);
        }
    }

    private static class PackageChunk {

        /* renamed from: a, reason: collision with root package name */
        private final ResChunkHeader f14233a;

        /* renamed from: b, reason: collision with root package name */
        private final PackageInfo f14234b;

        /* renamed from: c, reason: collision with root package name */
        private final StringPoolChunk f14235c = new StringPoolChunk(false, "?1", "?2", "?3", "?4", "?5", "color");

        /* renamed from: d, reason: collision with root package name */
        private final StringPoolChunk f14236d;

        /* renamed from: e, reason: collision with root package name */
        private final TypeSpecChunk f14237e;

        PackageChunk(PackageInfo packageInfo, List list) {
            this.f14234b = packageInfo;
            String[] strArr = new String[list.size()];
            for (int i2 = 0; i2 < list.size(); i2++) {
                strArr[i2] = ((ColorResource) list.get(i2)).f14231d;
            }
            this.f14236d = new StringPoolChunk(true, strArr);
            this.f14237e = new TypeSpecChunk(list);
            this.f14233a = new ResChunkHeader((short) 512, (short) 288, a());
        }

        int a() {
            return this.f14235c.a() + 288 + this.f14236d.a() + this.f14237e.b();
        }

        void b(ByteArrayOutputStream byteArrayOutputStream) {
            this.f14233a.a(byteArrayOutputStream);
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(this.f14234b.f14238a));
            char[] charArray = this.f14234b.f14239b.toCharArray();
            for (int i2 = 0; i2 < 128; i2++) {
                if (i2 < charArray.length) {
                    byteArrayOutputStream.write(ColorResourcesTableCreator.h(charArray[i2]));
                } else {
                    byteArrayOutputStream.write(ColorResourcesTableCreator.h((char) 0));
                }
            }
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(288));
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(0));
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(this.f14235c.a() + 288));
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(0));
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(0));
            this.f14235c.c(byteArrayOutputStream);
            this.f14236d.c(byteArrayOutputStream);
            this.f14237e.c(byteArrayOutputStream);
        }
    }

    static class PackageInfo {

        /* renamed from: a, reason: collision with root package name */
        private final int f14238a;

        /* renamed from: b, reason: collision with root package name */
        private final String f14239b;

        PackageInfo(int i2, String str) {
            this.f14238a = i2;
            this.f14239b = str;
        }
    }

    private static class ResChunkHeader {

        /* renamed from: a, reason: collision with root package name */
        private final short f14240a;

        /* renamed from: b, reason: collision with root package name */
        private final short f14241b;

        /* renamed from: c, reason: collision with root package name */
        private final int f14242c;

        ResChunkHeader(short s2, short s3, int i2) {
            this.f14240a = s2;
            this.f14241b = s3;
            this.f14242c = i2;
        }

        void a(ByteArrayOutputStream byteArrayOutputStream) {
            byteArrayOutputStream.write(ColorResourcesTableCreator.k(this.f14240a));
            byteArrayOutputStream.write(ColorResourcesTableCreator.k(this.f14241b));
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(this.f14242c));
        }
    }

    private static class ResEntry {

        /* renamed from: a, reason: collision with root package name */
        private final int f14243a;

        /* renamed from: b, reason: collision with root package name */
        private final int f14244b;

        ResEntry(int i2, int i3) {
            this.f14243a = i2;
            this.f14244b = i3;
        }

        void a(ByteArrayOutputStream byteArrayOutputStream) {
            byteArrayOutputStream.write(ColorResourcesTableCreator.k((short) 8));
            byteArrayOutputStream.write(ColorResourcesTableCreator.k((short) 2));
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(this.f14243a));
            byteArrayOutputStream.write(ColorResourcesTableCreator.k((short) 8));
            byteArrayOutputStream.write(new byte[]{0, 28});
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(this.f14244b));
        }
    }

    private static class ResTable {

        /* renamed from: a, reason: collision with root package name */
        private final ResChunkHeader f14245a;

        /* renamed from: b, reason: collision with root package name */
        private final int f14246b;

        /* renamed from: d, reason: collision with root package name */
        private final List f14248d = new ArrayList();

        /* renamed from: c, reason: collision with root package name */
        private final StringPoolChunk f14247c = new StringPoolChunk(new String[0]);

        ResTable(Map map) {
            this.f14246b = map.size();
            for (Map.Entry entry : map.entrySet()) {
                List list = (List) entry.getValue();
                Collections.sort(list, ColorResourcesTableCreator.f14227c);
                this.f14248d.add(new PackageChunk((PackageInfo) entry.getKey(), list));
            }
            this.f14245a = new ResChunkHeader((short) 2, (short) 12, a());
        }

        private int a() {
            Iterator it = this.f14248d.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                i2 += ((PackageChunk) it.next()).a();
            }
            return this.f14247c.a() + 12 + i2;
        }

        void b(ByteArrayOutputStream byteArrayOutputStream) {
            this.f14245a.a(byteArrayOutputStream);
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(this.f14246b));
            this.f14247c.c(byteArrayOutputStream);
            Iterator it = this.f14248d.iterator();
            while (it.hasNext()) {
                ((PackageChunk) it.next()).b(byteArrayOutputStream);
            }
        }
    }

    private static class StringPoolChunk {

        /* renamed from: a, reason: collision with root package name */
        private final ResChunkHeader f14249a;

        /* renamed from: b, reason: collision with root package name */
        private final int f14250b;

        /* renamed from: c, reason: collision with root package name */
        private final int f14251c;

        /* renamed from: d, reason: collision with root package name */
        private final int f14252d;

        /* renamed from: e, reason: collision with root package name */
        private final int f14253e;

        /* renamed from: f, reason: collision with root package name */
        private final List f14254f;

        /* renamed from: g, reason: collision with root package name */
        private final List f14255g;

        /* renamed from: h, reason: collision with root package name */
        private final List f14256h;

        /* renamed from: i, reason: collision with root package name */
        private final List f14257i;

        /* renamed from: j, reason: collision with root package name */
        private final boolean f14258j;

        /* renamed from: k, reason: collision with root package name */
        private final int f14259k;

        /* renamed from: l, reason: collision with root package name */
        private final int f14260l;

        StringPoolChunk(String... strArr) {
            this(false, strArr);
        }

        private Pair b(String str) {
            return new Pair(this.f14258j ? ColorResourcesTableCreator.m(str) : ColorResourcesTableCreator.l(str), Collections.emptyList());
        }

        int a() {
            return this.f14260l;
        }

        void c(ByteArrayOutputStream byteArrayOutputStream) {
            this.f14249a.a(byteArrayOutputStream);
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(this.f14250b));
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(this.f14251c));
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(this.f14258j ? 256 : 0));
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(this.f14252d));
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(this.f14253e));
            Iterator it = this.f14254f.iterator();
            while (it.hasNext()) {
                byteArrayOutputStream.write(ColorResourcesTableCreator.j(((Integer) it.next()).intValue()));
            }
            Iterator it2 = this.f14255g.iterator();
            while (it2.hasNext()) {
                byteArrayOutputStream.write(ColorResourcesTableCreator.j(((Integer) it2.next()).intValue()));
            }
            Iterator it3 = this.f14256h.iterator();
            while (it3.hasNext()) {
                byteArrayOutputStream.write((byte[]) it3.next());
            }
            int i2 = this.f14259k;
            if (i2 > 0) {
                byteArrayOutputStream.write(new byte[i2]);
            }
            Iterator it4 = this.f14257i.iterator();
            while (it4.hasNext()) {
                Iterator it5 = ((List) it4.next()).iterator();
                while (it5.hasNext()) {
                    ((StringStyledSpan) it5.next()).b(byteArrayOutputStream);
                }
                byteArrayOutputStream.write(ColorResourcesTableCreator.j(-1));
            }
        }

        StringPoolChunk(boolean z, String... strArr) {
            this.f14254f = new ArrayList();
            this.f14255g = new ArrayList();
            this.f14256h = new ArrayList();
            this.f14257i = new ArrayList();
            this.f14258j = z;
            int i2 = 0;
            for (String str : strArr) {
                Pair b2 = b(str);
                this.f14254f.add(Integer.valueOf(i2));
                Object obj = b2.first;
                i2 += ((byte[]) obj).length;
                this.f14256h.add((byte[]) obj);
                this.f14257i.add((List) b2.second);
            }
            int i3 = 0;
            for (List<StringStyledSpan> list : this.f14257i) {
                for (StringStyledSpan stringStyledSpan : list) {
                    this.f14254f.add(Integer.valueOf(i2));
                    i2 += stringStyledSpan.f14261a.length;
                    this.f14256h.add(stringStyledSpan.f14261a);
                }
                this.f14255g.add(Integer.valueOf(i3));
                i3 += (list.size() * 12) + 4;
            }
            int i4 = i2 % 4;
            int i5 = i4 == 0 ? 0 : 4 - i4;
            this.f14259k = i5;
            int size = this.f14256h.size();
            this.f14250b = size;
            this.f14251c = this.f14256h.size() - strArr.length;
            boolean z2 = this.f14256h.size() - strArr.length > 0;
            if (!z2) {
                this.f14255g.clear();
                this.f14257i.clear();
            }
            int size2 = (size * 4) + 28 + (this.f14255g.size() * 4);
            this.f14252d = size2;
            int i6 = i2 + i5;
            this.f14253e = z2 ? size2 + i6 : 0;
            int i7 = size2 + i6 + (z2 ? i3 : 0);
            this.f14260l = i7;
            this.f14249a = new ResChunkHeader((short) 1, (short) 28, i7);
        }
    }

    private static class StringStyledSpan {

        /* renamed from: a, reason: collision with root package name */
        private byte[] f14261a;

        /* renamed from: b, reason: collision with root package name */
        private int f14262b;

        /* renamed from: c, reason: collision with root package name */
        private int f14263c;

        /* renamed from: d, reason: collision with root package name */
        private int f14264d;

        void b(ByteArrayOutputStream byteArrayOutputStream) {
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(this.f14262b));
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(this.f14263c));
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(this.f14264d));
        }
    }

    private static class TypeChunk {

        /* renamed from: a, reason: collision with root package name */
        private final ResChunkHeader f14265a;

        /* renamed from: b, reason: collision with root package name */
        private final int f14266b;

        /* renamed from: c, reason: collision with root package name */
        private final byte[] f14267c;

        /* renamed from: d, reason: collision with root package name */
        private final int[] f14268d;

        /* renamed from: e, reason: collision with root package name */
        private final ResEntry[] f14269e;

        TypeChunk(List list, Set set, int i2) {
            byte[] bArr = new byte[64];
            this.f14267c = bArr;
            this.f14266b = i2;
            bArr[0] = 64;
            this.f14269e = new ResEntry[list.size()];
            for (int i3 = 0; i3 < list.size(); i3++) {
                this.f14269e[i3] = new ResEntry(i3, ((ColorResource) list.get(i3)).f14232e);
            }
            this.f14268d = new int[i2];
            int i4 = 0;
            for (short s2 = 0; s2 < i2; s2 = (short) (s2 + 1)) {
                if (set.contains(Short.valueOf(s2))) {
                    this.f14268d[s2] = i4;
                    i4 += 16;
                } else {
                    this.f14268d[s2] = -1;
                }
            }
            this.f14265a = new ResChunkHeader((short) 513, (short) 84, a());
        }

        private int b() {
            return c() + 84;
        }

        private int c() {
            return this.f14268d.length * 4;
        }

        int a() {
            return b() + (this.f14269e.length * 16);
        }

        void d(ByteArrayOutputStream byteArrayOutputStream) {
            this.f14265a.a(byteArrayOutputStream);
            byteArrayOutputStream.write(new byte[]{ColorResourcesTableCreator.f14225a, 0, 0, 0});
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(this.f14266b));
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(b()));
            byteArrayOutputStream.write(this.f14267c);
            for (int i2 : this.f14268d) {
                byteArrayOutputStream.write(ColorResourcesTableCreator.j(i2));
            }
            for (ResEntry resEntry : this.f14269e) {
                resEntry.a(byteArrayOutputStream);
            }
        }
    }

    private static class TypeSpecChunk {

        /* renamed from: a, reason: collision with root package name */
        private final ResChunkHeader f14270a;

        /* renamed from: b, reason: collision with root package name */
        private final int f14271b;

        /* renamed from: c, reason: collision with root package name */
        private final int[] f14272c;

        /* renamed from: d, reason: collision with root package name */
        private final TypeChunk f14273d;

        TypeSpecChunk(List list) {
            this.f14271b = ((ColorResource) list.get(list.size() - 1)).f14230c + 1;
            HashSet hashSet = new HashSet();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                hashSet.add(Short.valueOf(((ColorResource) it.next()).f14230c));
            }
            this.f14272c = new int[this.f14271b];
            for (short s2 = 0; s2 < this.f14271b; s2 = (short) (s2 + 1)) {
                if (hashSet.contains(Short.valueOf(s2))) {
                    this.f14272c[s2] = 1073741824;
                }
            }
            this.f14270a = new ResChunkHeader((short) 514, (short) 16, a());
            this.f14273d = new TypeChunk(list, hashSet, this.f14271b);
        }

        private int a() {
            return (this.f14271b * 4) + 16;
        }

        int b() {
            return a() + this.f14273d.a();
        }

        void c(ByteArrayOutputStream byteArrayOutputStream) {
            this.f14270a.a(byteArrayOutputStream);
            byteArrayOutputStream.write(new byte[]{ColorResourcesTableCreator.f14225a, 0, 0, 0});
            byteArrayOutputStream.write(ColorResourcesTableCreator.j(this.f14271b));
            for (int i2 : this.f14272c) {
                byteArrayOutputStream.write(ColorResourcesTableCreator.j(i2));
            }
            this.f14273d.d(byteArrayOutputStream);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] h(char c2) {
        return new byte[]{(byte) (c2 & 255), (byte) ((c2 >> '\b') & 255)};
    }

    static byte[] i(Context context, Map map) {
        PackageInfo packageInfo;
        if (map.entrySet().isEmpty()) {
            throw new IllegalArgumentException("No color resources provided for harmonization.");
        }
        PackageInfo packageInfo2 = new PackageInfo(127, context.getPackageName());
        HashMap hashMap = new HashMap();
        ColorResource colorResource = null;
        for (Map.Entry entry : map.entrySet()) {
            ColorResource colorResource2 = new ColorResource(((Integer) entry.getKey()).intValue(), context.getResources().getResourceName(((Integer) entry.getKey()).intValue()), ((Integer) entry.getValue()).intValue());
            if (!context.getResources().getResourceTypeName(((Integer) entry.getKey()).intValue()).equals("color")) {
                throw new IllegalArgumentException("Non color resource found: name=" + colorResource2.f14231d + ", typeId=" + Integer.toHexString(colorResource2.f14229b & 255));
            }
            if (colorResource2.f14228a == 1) {
                packageInfo = f14226b;
            } else {
                if (colorResource2.f14228a != Byte.MAX_VALUE) {
                    throw new IllegalArgumentException("Not supported with unknown package id: " + ((int) colorResource2.f14228a));
                }
                packageInfo = packageInfo2;
            }
            if (!hashMap.containsKey(packageInfo)) {
                hashMap.put(packageInfo, new ArrayList());
            }
            ((List) hashMap.get(packageInfo)).add(colorResource2);
            colorResource = colorResource2;
        }
        byte b2 = colorResource.f14229b;
        f14225a = b2;
        if (b2 == 0) {
            throw new IllegalArgumentException("No color resources found for harmonization.");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new ResTable(hashMap).b(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] j(int i2) {
        return new byte[]{(byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 24) & 255)};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] k(short s2) {
        return new byte[]{(byte) (s2 & 255), (byte) ((s2 >> 8) & 255)};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] l(String str) {
        char[] charArray = str.toCharArray();
        int length = charArray.length * 2;
        byte[] bArr = new byte[length + 4];
        byte[] k2 = k((short) charArray.length);
        bArr[0] = k2[0];
        bArr[1] = k2[1];
        for (int i2 = 0; i2 < charArray.length; i2++) {
            byte[] h2 = h(charArray[i2]);
            int i3 = i2 * 2;
            bArr[i3 + 2] = h2[0];
            bArr[i3 + 3] = h2[1];
        }
        bArr[length + 2] = 0;
        bArr[length + 3] = 0;
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] m(String str) {
        byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
        byte length = (byte) bytes.length;
        int length2 = bytes.length;
        byte[] bArr = new byte[length2 + 3];
        System.arraycopy(bytes, 0, bArr, 2, length);
        bArr[1] = length;
        bArr[0] = length;
        bArr[length2 + 2] = 0;
        return bArr;
    }
}
