package com.zte.gameassist.lowsugar.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import com.zte.gameassist.utils.GaLog;
import java.lang.reflect.Array;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class ImageDeduplicationUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final Map f17000a = new LinkedHashMap<String, String>(16, 0.75f, true) { // from class: com.zte.gameassist.lowsugar.utils.ImageDeduplicationUtil.1
        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<String, String> entry) {
            return size() > 1000;
        }
    };

    public static class BitmapWithId {

        /* renamed from: a, reason: collision with root package name */
        private final String f17001a;

        public String a() {
            return this.f17001a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.f17001a.equals(((BitmapWithId) obj).a());
        }

        public int hashCode() {
            return this.f17001a.hashCode();
        }
    }

    public enum ImageHashAlgorithm {
        AHASH,
        DHASH,
        PHASH
    }

    private static int a(String str, String str2) {
        if (str == null || str2 == null || str.length() != 64 || str2.length() != 64) {
            GaLog.b("ImageDedup", "Invalid hash format: hash1=" + str + ", hash2=" + str2);
            return -1;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 64; i3++) {
            if (str.charAt(i3) != str2.charAt(i3)) {
                i2++;
            }
        }
        return i2;
    }

    private static double[][] b(double[][] dArr) {
        int i2 = 32;
        int i3 = 0;
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 32, 32);
        double[] dArr3 = new double[32];
        int i4 = 0;
        while (i4 < 32) {
            dArr3[i4] = i4 == 0 ? 1.0d / Math.sqrt(32) : Math.sqrt(2.0d / 32);
            i4++;
        }
        int i5 = 0;
        while (i5 < i2) {
            int i6 = i3;
            while (i6 < i2) {
                double d2 = 0.0d;
                int i7 = i3;
                while (i7 < i2) {
                    int i8 = i3;
                    while (i8 < i2) {
                        double d3 = 64;
                        d2 += dArr[i7][i8] * Math.cos(((((i7 * 2) + 1) * i5) * 3.141592653589793d) / d3) * Math.cos(((((i8 * 2) + 1) * i6) * 3.141592653589793d) / d3);
                        i8++;
                        dArr3 = dArr3;
                        i2 = 32;
                    }
                    i7++;
                    i2 = 32;
                    i3 = 0;
                }
                double[] dArr4 = dArr3;
                dArr2[i5][i6] = dArr4[i5] * dArr4[i6] * d2;
                i6++;
                dArr3 = dArr4;
                i2 = 32;
                i3 = 0;
            }
            i5++;
            i2 = 32;
            i3 = 0;
        }
        return dArr2;
    }

    private static String c(Bitmap bitmap) {
        int[] h2 = h(bitmap, 8, 8);
        if (h2 == null) {
            return null;
        }
        int i2 = 0;
        for (int i3 : h2) {
            i2 += Color.red(i3);
        }
        int i4 = i2 / 64;
        StringBuilder sb = new StringBuilder();
        for (int i5 : h2) {
            sb.append(Color.red(i5) >= i4 ? "1" : "0");
        }
        if (sb.length() == 64) {
            return sb.toString();
        }
        GaLog.b("ImageDedup", "aHash extraction failed: invalid length, actual=" + sb.length());
        return null;
    }

    private static String d(Bitmap bitmap) {
        int[] h2 = h(bitmap, 9, 8);
        if (h2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < 8; i2++) {
            for (int i3 = 0; i3 < 8; i3++) {
                int i4 = (i2 * 9) + i3;
                sb.append(Color.red(h2[i4]) < Color.red(h2[i4 + 1]) ? "1" : "0");
            }
        }
        if (sb.length() == 64) {
            return sb.toString();
        }
        GaLog.b("ImageDedup", "dHash extraction failed: invalid length, actual=" + sb.length());
        return null;
    }

    public static String e(Bitmap bitmap, ImageHashAlgorithm imageHashAlgorithm) {
        String c2;
        if (bitmap == null || bitmap.isRecycled() || imageHashAlgorithm == null) {
            GaLog.b("ImageDedup", "Hash extraction failed: Bitmap is null/recycled or algorithm is invalid");
            return null;
        }
        String str = bitmap.hashCode() + "_" + imageHashAlgorithm.name();
        Map map = f17000a;
        synchronized (map) {
            try {
                String str2 = (String) map.get(str);
                if (str2 != null) {
                    return str2;
                }
                int ordinal = imageHashAlgorithm.ordinal();
                if (ordinal == 0) {
                    c2 = c(bitmap);
                } else if (ordinal == 1) {
                    c2 = d(bitmap);
                } else {
                    if (ordinal != 2) {
                        GaLog.b("ImageDedup", "Unsupported hash algorithm: " + imageHashAlgorithm);
                        return null;
                    }
                    c2 = f(bitmap);
                }
                if (c2 != null) {
                    synchronized (map) {
                        map.put(str, c2);
                    }
                }
                return c2;
            } finally {
            }
        }
    }

    private static String f(Bitmap bitmap) {
        if (h(bitmap, 32, 32) == null) {
            return null;
        }
        try {
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 32, 32);
            for (int i2 = 0; i2 < 32; i2++) {
                for (int i3 = 0; i3 < 32; i3++) {
                    dArr[i2][i3] = Color.red(r12[(i2 * 32) + i3]);
                }
            }
            double[][] b2 = b(dArr);
            double d2 = 0.0d;
            for (int i4 = 0; i4 < 8; i4++) {
                for (int i5 = 0; i5 < 8; i5++) {
                    if (i5 != 0 || i4 != 0) {
                        d2 += b2[i4][i5];
                    }
                }
            }
            double d3 = d2 / 63;
            StringBuilder sb = new StringBuilder();
            for (int i6 = 0; i6 < 8; i6++) {
                for (int i7 = 0; i7 < 8; i7++) {
                    if (i7 == 0 && i6 == 0) {
                        sb.append("0");
                    } else {
                        sb.append(b2[i6][i7] >= d3 ? "1" : "0");
                    }
                }
            }
            if (sb.length() == 64) {
                return sb.toString();
            }
            GaLog.b("ImageDedup", "pHash extraction failed: invalid length, actual=" + sb.length());
            return null;
        } catch (Exception e2) {
            GaLog.c("ImageDedup", "pHash extraction failed", e2);
            return null;
        }
    }

    public static boolean g(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            GaLog.k("ImageDedup", "isSimilarImage: Bitmap parameter is null");
            return false;
        }
        int a2 = a(str, str2);
        GaLog.a("ImageDedup", "isSimilarImage DHash Hamming distance: " + a2 + " (threshold=6)");
        return a2 != -1 && a2 <= 6;
    }

    private static int[] h(Bitmap bitmap, int i2, int i3) {
        if (bitmap == null || bitmap.isRecycled()) {
            GaLog.b("ImageDedup", "Preprocessing failed: Bitmap is null or recycled");
            return null;
        }
        try {
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i2, i3, true);
            int i4 = i2 * i3;
            int[] iArr = new int[i4];
            createScaledBitmap.getPixels(iArr, 0, i2, 0, 0, i2, i3);
            int[] iArr2 = new int[i4];
            for (int i5 = 0; i5 < i4; i5++) {
                int i6 = iArr[i5];
                int red = (int) ((Color.red(i6) * 0.299d) + (Color.green(i6) * 0.587d) + (Color.blue(i6) * 0.114d));
                iArr2[i5] = Color.rgb(red, red, red);
            }
            createScaledBitmap.recycle();
            return iArr2;
        } catch (OutOfMemoryError e2) {
            GaLog.d("ImageDedup", "Preprocessing failed: Out of memory", e2);
            return null;
        }
    }
}
