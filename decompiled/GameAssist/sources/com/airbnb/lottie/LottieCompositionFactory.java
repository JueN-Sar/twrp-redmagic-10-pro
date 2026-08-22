package com.airbnb.lottie;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Base64;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.LottieCompositionCache;
import com.airbnb.lottie.parser.LottieCompositionMoshiParser;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import okio.BufferedSource;
import okio.Okio;

/* loaded from: classes.dex */
public class LottieCompositionFactory {

    /* renamed from: a, reason: collision with root package name */
    private static final Map f9279a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private static final Set f9280b = new HashSet();

    /* renamed from: c, reason: collision with root package name */
    private static final byte[] f9281c = {80, 75, 3, 4};

    /* renamed from: d, reason: collision with root package name */
    private static final byte[] f9282d = {31, -117, 8};

    private static LottieResult A(Context context, ZipInputStream zipInputStream, String str) {
        LottieComposition a2;
        FileOutputStream fileOutputStream;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        if (str == null) {
            a2 = null;
        } else {
            try {
                a2 = LottieCompositionCache.b().a(str);
            } catch (IOException e2) {
                return new LottieResult((Throwable) e2);
            }
        }
        if (a2 != null) {
            return new LottieResult(a2);
        }
        ZipEntry nextEntry = zipInputStream.getNextEntry();
        LottieComposition lottieComposition = null;
        while (nextEntry != null) {
            String name = nextEntry.getName();
            if (name.contains("__MACOSX")) {
                zipInputStream.closeEntry();
            } else if (nextEntry.getName().equalsIgnoreCase("manifest.json")) {
                zipInputStream.closeEntry();
            } else if (nextEntry.getName().contains(".json")) {
                lottieComposition = (LottieComposition) r(JsonReader.B(Okio.a(Okio.c(zipInputStream))), null, false).b();
            } else {
                if (!name.contains(".png") && !name.contains(".webp") && !name.contains(".jpg") && !name.contains(".jpeg")) {
                    if (!name.contains(".ttf") && !name.contains(".otf")) {
                        zipInputStream.closeEntry();
                    }
                    String[] split = name.split("/");
                    String str2 = split[split.length - 1];
                    String str3 = str2.split("\\.")[0];
                    File file = new File(context.getCacheDir(), str2);
                    new FileOutputStream(file);
                    try {
                        fileOutputStream = new FileOutputStream(file);
                    } catch (Throwable th) {
                        Logger.d("Unable to save font " + str3 + " to the temporary file: " + str2 + ". ", th);
                    }
                    try {
                        byte[] bArr = new byte[4096];
                        while (true) {
                            int read = zipInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        Typeface createFromFile = Typeface.createFromFile(file);
                        if (!file.delete()) {
                            Logger.c("Failed to delete temp font file " + file.getAbsolutePath() + ".");
                        }
                        hashMap2.put(str3, createFromFile);
                    } catch (Throwable th2) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th3) {
                            th2.addSuppressed(th3);
                        }
                        throw th2;
                    }
                }
                String[] split2 = name.split("/");
                hashMap.put(split2[split2.length - 1], BitmapFactory.decodeStream(zipInputStream));
            }
            nextEntry = zipInputStream.getNextEntry();
        }
        if (lottieComposition == null) {
            return new LottieResult((Throwable) new IllegalArgumentException("Unable to parse composition"));
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            LottieImageAsset i2 = i(lottieComposition, (String) entry.getKey());
            if (i2 != null) {
                i2.g(Utils.l((Bitmap) entry.getValue(), i2.f(), i2.d()));
            }
        }
        for (Map.Entry entry2 : hashMap2.entrySet()) {
            boolean z = false;
            for (Font font : lottieComposition.g().values()) {
                if (font.a().equals(entry2.getKey())) {
                    font.e((Typeface) entry2.getValue());
                    z = true;
                }
            }
            if (!z) {
                Logger.c("Parsed font for " + ((String) entry2.getKey()) + " however it was not found in the animation.");
            }
        }
        if (hashMap.isEmpty()) {
            Iterator it = lottieComposition.j().entrySet().iterator();
            while (it.hasNext()) {
                LottieImageAsset lottieImageAsset = (LottieImageAsset) ((Map.Entry) it.next()).getValue();
                if (lottieImageAsset == null) {
                    return null;
                }
                String c2 = lottieImageAsset.c();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inScaled = true;
                options.inDensity = 160;
                if (c2.startsWith("data:") && c2.indexOf("base64,") > 0) {
                    try {
                        byte[] decode = Base64.decode(c2.substring(c2.indexOf(44) + 1), 0);
                        lottieImageAsset.g(BitmapFactory.decodeByteArray(decode, 0, decode.length, options));
                    } catch (IllegalArgumentException e3) {
                        Logger.d("data URL did not have correct base64 format.", e3);
                        return null;
                    }
                }
            }
        }
        if (str != null) {
            LottieCompositionCache.b().c(str, lottieComposition);
        }
        return new LottieResult(lottieComposition);
    }

    private static Boolean B(BufferedSource bufferedSource) {
        return L(bufferedSource, f9282d);
    }

    private static boolean C(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    private static Boolean D(BufferedSource bufferedSource) {
        return L(bufferedSource, f9281c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void E(String str, AtomicBoolean atomicBoolean, LottieComposition lottieComposition) {
        Map map = f9279a;
        map.remove(str);
        atomicBoolean.set(true);
        if (map.size() == 0) {
            M(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void F(String str, AtomicBoolean atomicBoolean, Throwable th) {
        Map map = f9279a;
        map.remove(str);
        atomicBoolean.set(true);
        if (map.size() == 0) {
            M(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ LottieResult J(WeakReference weakReference, Context context, int i2, String str) {
        Context context2 = (Context) weakReference.get();
        if (context2 != null) {
            context = context2;
        }
        return v(context, i2, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ LottieResult K(Context context, String str, String str2) {
        LottieResult c2 = L.i(context).c(context, str, str2);
        if (str2 != null && c2.b() != null) {
            LottieCompositionCache.b().c(str2, (LottieComposition) c2.b());
        }
        return c2;
    }

    private static Boolean L(BufferedSource bufferedSource, byte[] bArr) {
        try {
            BufferedSource peek = bufferedSource.peek();
            for (byte b2 : bArr) {
                if (peek.readByte() != b2) {
                    return Boolean.FALSE;
                }
            }
            peek.close();
            return Boolean.TRUE;
        } catch (Exception e2) {
            Logger.b("Failed to check zip file header", e2);
            return Boolean.FALSE;
        } catch (NoSuchMethodError unused) {
            return Boolean.FALSE;
        }
    }

    private static void M(boolean z) {
        ArrayList arrayList = new ArrayList(f9280b);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            ((LottieTaskIdleListener) arrayList.get(i2)).a(z);
        }
    }

    private static String N(Context context, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append("rawRes");
        sb.append(C(context) ? "_night_" : "_day_");
        sb.append(i2);
        return sb.toString();
    }

    private static LottieTask h(final String str, Callable callable, Runnable runnable) {
        LottieComposition a2 = str == null ? null : LottieCompositionCache.b().a(str);
        LottieTask lottieTask = a2 != null ? new LottieTask(a2) : null;
        if (str != null) {
            Map map = f9279a;
            if (map.containsKey(str)) {
                lottieTask = (LottieTask) map.get(str);
            }
        }
        if (lottieTask != null) {
            if (runnable != null) {
                runnable.run();
            }
            return lottieTask;
        }
        LottieTask lottieTask2 = new LottieTask(callable);
        if (str != null) {
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            lottieTask2.d(new LottieListener() { // from class: com.airbnb.lottie.i
                @Override // com.airbnb.lottie.LottieListener
                public final void onResult(Object obj) {
                    LottieCompositionFactory.E(str, atomicBoolean, (LottieComposition) obj);
                }
            });
            lottieTask2.c(new LottieListener() { // from class: com.airbnb.lottie.j
                @Override // com.airbnb.lottie.LottieListener
                public final void onResult(Object obj) {
                    LottieCompositionFactory.F(str, atomicBoolean, (Throwable) obj);
                }
            });
            if (!atomicBoolean.get()) {
                Map map2 = f9279a;
                map2.put(str, lottieTask2);
                if (map2.size() == 1) {
                    M(false);
                }
            }
        }
        return lottieTask2;
    }

    private static LottieImageAsset i(LottieComposition lottieComposition, String str) {
        for (LottieImageAsset lottieImageAsset : lottieComposition.j().values()) {
            if (lottieImageAsset.c().equals(str)) {
                return lottieImageAsset;
            }
        }
        return null;
    }

    public static LottieTask j(Context context, String str) {
        return k(context, str, "asset_" + str);
    }

    public static LottieTask k(Context context, final String str, final String str2) {
        final Context applicationContext = context.getApplicationContext();
        return h(str2, new Callable() { // from class: com.airbnb.lottie.h
            @Override // java.util.concurrent.Callable
            public final Object call() {
                LottieResult m2;
                m2 = LottieCompositionFactory.m(applicationContext, str, str2);
                return m2;
            }
        }, null);
    }

    public static LottieResult l(Context context, String str) {
        return m(context, str, "asset_" + str);
    }

    public static LottieResult m(Context context, String str, String str2) {
        LottieComposition a2 = str2 == null ? null : LottieCompositionCache.b().a(str2);
        if (a2 != null) {
            return new LottieResult(a2);
        }
        try {
            BufferedSource a3 = Okio.a(Okio.c(context.getAssets().open(str)));
            return D(a3).booleanValue() ? y(context, new ZipInputStream(a3.g0()), str2) : B(a3).booleanValue() ? o(new GZIPInputStream(a3.g0()), str2) : o(a3.g0(), str2);
        } catch (IOException e2) {
            return new LottieResult((Throwable) e2);
        }
    }

    public static LottieTask n(final InputStream inputStream, final String str) {
        return h(str, new Callable() { // from class: com.airbnb.lottie.f
            @Override // java.util.concurrent.Callable
            public final Object call() {
                LottieResult o2;
                o2 = LottieCompositionFactory.o(inputStream, str);
                return o2;
            }
        }, new Runnable() { // from class: com.airbnb.lottie.g
            @Override // java.lang.Runnable
            public final void run() {
                Utils.c(inputStream);
            }
        });
    }

    public static LottieResult o(InputStream inputStream, String str) {
        return p(inputStream, str, true);
    }

    public static LottieResult p(InputStream inputStream, String str, boolean z) {
        return q(JsonReader.B(Okio.a(Okio.c(inputStream))), str, z);
    }

    public static LottieResult q(JsonReader jsonReader, String str, boolean z) {
        return r(jsonReader, str, z);
    }

    private static LottieResult r(JsonReader jsonReader, String str, boolean z) {
        LottieComposition a2;
        try {
            if (str == null) {
                a2 = null;
            } else {
                try {
                    a2 = LottieCompositionCache.b().a(str);
                } catch (Exception e2) {
                    LottieResult lottieResult = new LottieResult((Throwable) e2);
                    if (z) {
                        Utils.c(jsonReader);
                    }
                    return lottieResult;
                }
            }
            if (a2 != null) {
                LottieResult lottieResult2 = new LottieResult(a2);
                if (z) {
                    Utils.c(jsonReader);
                }
                return lottieResult2;
            }
            LottieComposition a3 = LottieCompositionMoshiParser.a(jsonReader);
            if (str != null) {
                LottieCompositionCache.b().c(str, a3);
            }
            LottieResult lottieResult3 = new LottieResult(a3);
            if (z) {
                Utils.c(jsonReader);
            }
            return lottieResult3;
        } catch (Throwable th) {
            if (z) {
                Utils.c(jsonReader);
            }
            throw th;
        }
    }

    public static LottieTask s(Context context, int i2) {
        return t(context, i2, N(context, i2));
    }

    public static LottieTask t(Context context, final int i2, final String str) {
        final WeakReference weakReference = new WeakReference(context);
        final Context applicationContext = context.getApplicationContext();
        return h(str, new Callable() { // from class: com.airbnb.lottie.k
            @Override // java.util.concurrent.Callable
            public final Object call() {
                LottieResult J;
                J = LottieCompositionFactory.J(weakReference, applicationContext, i2, str);
                return J;
            }
        }, null);
    }

    public static LottieResult u(Context context, int i2) {
        return v(context, i2, N(context, i2));
    }

    public static LottieResult v(Context context, int i2, String str) {
        LottieComposition a2 = str == null ? null : LottieCompositionCache.b().a(str);
        if (a2 != null) {
            return new LottieResult(a2);
        }
        try {
            BufferedSource a3 = Okio.a(Okio.c(context.getResources().openRawResource(i2)));
            if (D(a3).booleanValue()) {
                return y(context, new ZipInputStream(a3.g0()), str);
            }
            if (!B(a3).booleanValue()) {
                return o(a3.g0(), str);
            }
            try {
                return o(new GZIPInputStream(a3.g0()), str);
            } catch (IOException e2) {
                return new LottieResult((Throwable) e2);
            }
        } catch (Resources.NotFoundException e3) {
            return new LottieResult((Throwable) e3);
        }
    }

    public static LottieTask w(Context context, String str) {
        return x(context, str, "url_" + str);
    }

    public static LottieTask x(final Context context, final String str, final String str2) {
        return h(str2, new Callable() { // from class: com.airbnb.lottie.e
            @Override // java.util.concurrent.Callable
            public final Object call() {
                LottieResult K;
                K = LottieCompositionFactory.K(context, str, str2);
                return K;
            }
        }, null);
    }

    public static LottieResult y(Context context, ZipInputStream zipInputStream, String str) {
        return z(context, zipInputStream, str, true);
    }

    public static LottieResult z(Context context, ZipInputStream zipInputStream, String str, boolean z) {
        try {
            return A(context, zipInputStream, str);
        } finally {
            if (z) {
                Utils.c(zipInputStream);
            }
        }
    }
}
