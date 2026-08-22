package androidx.core.provider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Consumer;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
class FontRequestWorker {

    /* renamed from: a, reason: collision with root package name */
    static final LruCache f3144a = new LruCache(16);

    /* renamed from: b, reason: collision with root package name */
    private static final ExecutorService f3145b = RequestExecutor.a("fonts-androidx", 10, 10000);

    /* renamed from: c, reason: collision with root package name */
    static final Object f3146c = new Object();

    /* renamed from: d, reason: collision with root package name */
    static final SimpleArrayMap f3147d = new SimpleArrayMap();

    private static String a(FontRequest fontRequest, int i2) {
        return fontRequest.d() + "-" + i2;
    }

    private static int b(FontsContractCompat.FontFamilyResult fontFamilyResult) {
        int i2 = 1;
        if (fontFamilyResult.c() != 0) {
            return fontFamilyResult.c() != 1 ? -3 : -2;
        }
        FontsContractCompat.FontInfo[] b2 = fontFamilyResult.b();
        if (b2 != null && b2.length != 0) {
            i2 = 0;
            for (FontsContractCompat.FontInfo fontInfo : b2) {
                int b3 = fontInfo.b();
                if (b3 != 0) {
                    if (b3 < 0) {
                        return -3;
                    }
                    return b3;
                }
            }
        }
        return i2;
    }

    static TypefaceResult c(String str, Context context, FontRequest fontRequest, int i2) {
        LruCache lruCache = f3144a;
        Typeface typeface = (Typeface) lruCache.d(str);
        if (typeface != null) {
            return new TypefaceResult(typeface);
        }
        try {
            FontsContractCompat.FontFamilyResult e2 = FontProvider.e(context, fontRequest, null);
            int b2 = b(e2);
            if (b2 != 0) {
                return new TypefaceResult(b2);
            }
            Typeface b3 = TypefaceCompat.b(context, null, e2.b(), i2);
            if (b3 == null) {
                return new TypefaceResult(-3);
            }
            lruCache.e(str, b3);
            return new TypefaceResult(b3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new TypefaceResult(-1);
        }
    }

    static Typeface d(final Context context, final FontRequest fontRequest, final int i2, Executor executor, final CallbackWithHandler callbackWithHandler) {
        final String a2 = a(fontRequest, i2);
        Typeface typeface = (Typeface) f3144a.d(a2);
        if (typeface != null) {
            callbackWithHandler.b(new TypefaceResult(typeface));
            return typeface;
        }
        Consumer<TypefaceResult> consumer = new Consumer<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.2
            @Override // androidx.core.util.Consumer
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void accept(TypefaceResult typefaceResult) {
                if (typefaceResult == null) {
                    typefaceResult = new TypefaceResult(-3);
                }
                CallbackWithHandler.this.b(typefaceResult);
            }
        };
        synchronized (f3146c) {
            try {
                SimpleArrayMap simpleArrayMap = f3147d;
                ArrayList arrayList = (ArrayList) simpleArrayMap.get(a2);
                if (arrayList != null) {
                    arrayList.add(consumer);
                    return null;
                }
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(consumer);
                simpleArrayMap.put(a2, arrayList2);
                Callable<TypefaceResult> callable = new Callable<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.3
                    @Override // java.util.concurrent.Callable
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public TypefaceResult call() {
                        try {
                            return FontRequestWorker.c(a2, context, fontRequest, i2);
                        } catch (Throwable unused) {
                            return new TypefaceResult(-3);
                        }
                    }
                };
                if (executor == null) {
                    executor = f3145b;
                }
                RequestExecutor.b(executor, callable, new Consumer<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.4
                    @Override // androidx.core.util.Consumer
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void accept(TypefaceResult typefaceResult) {
                        synchronized (FontRequestWorker.f3146c) {
                            try {
                                SimpleArrayMap simpleArrayMap2 = FontRequestWorker.f3147d;
                                ArrayList arrayList3 = (ArrayList) simpleArrayMap2.get(a2);
                                if (arrayList3 == null) {
                                    return;
                                }
                                simpleArrayMap2.remove(a2);
                                for (int i3 = 0; i3 < arrayList3.size(); i3++) {
                                    ((Consumer) arrayList3.get(i3)).accept(typefaceResult);
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                });
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    static Typeface e(final Context context, final FontRequest fontRequest, CallbackWithHandler callbackWithHandler, final int i2, int i3) {
        final String a2 = a(fontRequest, i2);
        Typeface typeface = (Typeface) f3144a.d(a2);
        if (typeface != null) {
            callbackWithHandler.b(new TypefaceResult(typeface));
            return typeface;
        }
        if (i3 == -1) {
            TypefaceResult c2 = c(a2, context, fontRequest, i2);
            callbackWithHandler.b(c2);
            return c2.f3158a;
        }
        try {
            TypefaceResult typefaceResult = (TypefaceResult) RequestExecutor.c(f3145b, new Callable<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.1
                @Override // java.util.concurrent.Callable
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public TypefaceResult call() {
                    return FontRequestWorker.c(a2, context, fontRequest, i2);
                }
            }, i3);
            callbackWithHandler.b(typefaceResult);
            return typefaceResult.f3158a;
        } catch (InterruptedException unused) {
            callbackWithHandler.b(new TypefaceResult(-3));
            return null;
        }
    }

    static void f() {
        f3144a.c();
    }

    static final class TypefaceResult {

        /* renamed from: a, reason: collision with root package name */
        final Typeface f3158a;

        /* renamed from: b, reason: collision with root package name */
        final int f3159b;

        TypefaceResult(int i2) {
            this.f3158a = null;
            this.f3159b = i2;
        }

        boolean a() {
            return this.f3159b == 0;
        }

        TypefaceResult(Typeface typeface) {
            this.f3158a = typeface;
            this.f3159b = 0;
        }
    }
}
