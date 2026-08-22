package com.google.android.gms.dynamite;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import dalvik.system.DelegateLastClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@KeepForSdk
/* loaded from: classes.dex */
public final class DynamiteModule {

    /* renamed from: h, reason: collision with root package name */
    private static Boolean f11346h = null;

    /* renamed from: i, reason: collision with root package name */
    private static String f11347i = null;

    /* renamed from: j, reason: collision with root package name */
    private static boolean f11348j = false;

    /* renamed from: k, reason: collision with root package name */
    private static int f11349k = -1;

    /* renamed from: l, reason: collision with root package name */
    private static Boolean f11350l;

    /* renamed from: q, reason: collision with root package name */
    private static zzq f11355q;

    /* renamed from: r, reason: collision with root package name */
    private static zzr f11356r;

    /* renamed from: a, reason: collision with root package name */
    private final Context f11357a;

    /* renamed from: m, reason: collision with root package name */
    private static final ThreadLocal f11351m = new ThreadLocal();

    /* renamed from: n, reason: collision with root package name */
    private static final ThreadLocal f11352n = new zzd();

    /* renamed from: o, reason: collision with root package name */
    private static final VersionPolicy.IVersions f11353o = new zze();

    /* renamed from: b, reason: collision with root package name */
    public static final VersionPolicy f11340b = new zzf();

    /* renamed from: c, reason: collision with root package name */
    public static final VersionPolicy f11341c = new zzg();

    /* renamed from: d, reason: collision with root package name */
    public static final VersionPolicy f11342d = new zzh();

    /* renamed from: e, reason: collision with root package name */
    public static final VersionPolicy f11343e = new zzi();

    /* renamed from: f, reason: collision with root package name */
    public static final VersionPolicy f11344f = new zzj();

    /* renamed from: g, reason: collision with root package name */
    public static final VersionPolicy f11345g = new zzk();

    /* renamed from: p, reason: collision with root package name */
    public static final VersionPolicy f11354p = new zzl();

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {

        @Nullable
        @GuardedBy
        public static ClassLoader sClassLoader;
    }

    @KeepForSdk
    public static class LoadingException extends Exception {
        /* synthetic */ LoadingException(String str, zzp zzpVar) {
            super(str);
        }

        /* synthetic */ LoadingException(String str, Throwable th, zzp zzpVar) {
            super(str, th);
        }
    }

    public interface VersionPolicy {

        @KeepForSdk
        public interface IVersions {
            int a(Context context, String str, boolean z);

            int b(Context context, String str);
        }

        @KeepForSdk
        public static class SelectionResult {

            /* renamed from: a, reason: collision with root package name */
            public int f11358a = 0;

            /* renamed from: b, reason: collision with root package name */
            public int f11359b = 0;

            /* renamed from: c, reason: collision with root package name */
            public int f11360c = 0;
        }

        SelectionResult a(Context context, String str, IVersions iVersions);
    }

    private DynamiteModule(Context context) {
        Preconditions.i(context);
        this.f11357a = context;
    }

    public static int a(Context context, String str) {
        try {
            Class<?> loadClass = context.getApplicationContext().getClassLoader().loadClass("com.google.android.gms.dynamite.descriptors." + str + ".ModuleDescriptor");
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (Objects.a(declaredField.get(null), str)) {
                return declaredField2.getInt(null);
            }
            Log.e("DynamiteModule", "Module descriptor id '" + String.valueOf(declaredField.get(null)) + "' didn't match expected id '" + str + "'");
            return 0;
        } catch (ClassNotFoundException unused) {
            Log.w("DynamiteModule", "Local module descriptor class for " + str + " not found.");
            return 0;
        } catch (Exception e2) {
            Log.e("DynamiteModule", "Failed to load module descriptor class: ".concat(String.valueOf(e2.getMessage())));
            return 0;
        }
    }

    public static int c(Context context, String str) {
        return f(context, str, false);
    }

    /* JADX WARN: Not initialized variable reg: 17, insn: 0x0226: MOVE (r6 I:??[OBJECT, ARRAY]) = (r17 I:??[OBJECT, ARRAY]), block:B:168:0x0226 */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x027b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.android.gms.dynamite.DynamiteModule e(android.content.Context r18, com.google.android.gms.dynamite.DynamiteModule.VersionPolicy r19, java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 776
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.e(android.content.Context, com.google.android.gms.dynamite.DynamiteModule$VersionPolicy, java.lang.String):com.google.android.gms.dynamite.DynamiteModule");
    }

    public static int f(Context context, String str, boolean z) {
        Field declaredField;
        Throwable th;
        RemoteException e2;
        Cursor cursor;
        try {
            synchronized (DynamiteModule.class) {
                Boolean bool = f11346h;
                int i2 = 0;
                if (bool == null) {
                    try {
                        declaredField = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName()).getDeclaredField("sClassLoader");
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e3) {
                        Log.w("DynamiteModule", "Failed to load module via V2: " + e3.toString());
                        bool = Boolean.FALSE;
                    }
                    synchronized (declaredField.getDeclaringClass()) {
                        ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                        if (classLoader == ClassLoader.getSystemClassLoader()) {
                            bool = Boolean.FALSE;
                        } else if (classLoader != null) {
                            try {
                                i(classLoader);
                            } catch (LoadingException unused) {
                            }
                            bool = Boolean.TRUE;
                        } else {
                            if (!k(context)) {
                                return 0;
                            }
                            if (!f11348j) {
                                Boolean bool2 = Boolean.TRUE;
                                if (!bool2.equals(null)) {
                                    try {
                                        int g2 = g(context, str, z, true);
                                        String str2 = f11347i;
                                        if (str2 != null && !str2.isEmpty()) {
                                            ClassLoader a2 = zzb.a();
                                            if (a2 == null) {
                                                String str3 = f11347i;
                                                Preconditions.i(str3);
                                                a2 = new DelegateLastClassLoader(str3, ClassLoader.getSystemClassLoader());
                                            }
                                            i(a2);
                                            declaredField.set(null, a2);
                                            f11346h = bool2;
                                            return g2;
                                        }
                                        return g2;
                                    } catch (LoadingException unused2) {
                                        declaredField.set(null, ClassLoader.getSystemClassLoader());
                                        bool = Boolean.FALSE;
                                    }
                                }
                            }
                            declaredField.set(null, ClassLoader.getSystemClassLoader());
                            bool = Boolean.FALSE;
                        }
                        f11346h = bool;
                    }
                }
                if (bool.booleanValue()) {
                    try {
                        return g(context, str, z, false);
                    } catch (LoadingException e4) {
                        Log.w("DynamiteModule", "Failed to retrieve remote module version: " + e4.getMessage());
                        return 0;
                    }
                }
                zzq l2 = l(context);
                if (l2 != null) {
                    try {
                        try {
                            int zze = l2.zze();
                            if (zze >= 3) {
                                zzn zznVar = (zzn) f11351m.get();
                                if (zznVar == null || (cursor = zznVar.f11363a) == null) {
                                    Cursor cursor2 = (Cursor) ObjectWrapper.unwrap(l2.zzk(ObjectWrapper.wrap(context), str, z, ((Long) f11352n.get()).longValue()));
                                    if (cursor2 != null) {
                                        try {
                                            if (cursor2.moveToFirst()) {
                                                int i3 = cursor2.getInt(0);
                                                r2 = (i3 <= 0 || !j(cursor2)) ? cursor2 : null;
                                                if (r2 != null) {
                                                    r2.close();
                                                }
                                                i2 = i3;
                                            }
                                        } catch (RemoteException e5) {
                                            e2 = e5;
                                            r2 = cursor2;
                                            Log.w("DynamiteModule", "Failed to retrieve remote module version: " + e2.getMessage());
                                            if (r2 != null) {
                                                r2.close();
                                            }
                                            return i2;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            r2 = cursor2;
                                            if (r2 != null) {
                                                r2.close();
                                            }
                                            throw th;
                                        }
                                    }
                                    Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                                    if (cursor2 != null) {
                                        cursor2.close();
                                    }
                                } else {
                                    i2 = cursor.getInt(0);
                                }
                            } else if (zze == 2) {
                                Log.w("DynamiteModule", "IDynamite loader version = 2, no high precision latency measurement.");
                                i2 = l2.zzg(ObjectWrapper.wrap(context), str, z);
                            } else {
                                Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
                                i2 = l2.zzf(ObjectWrapper.wrap(context), str, z);
                            }
                        } catch (RemoteException e6) {
                            e2 = e6;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                return i2;
            }
        } catch (Throwable th4) {
            CrashUtils.a(context, th4);
            throw th4;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00a5, code lost:
    
        r8.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00e0  */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int g(android.content.Context r8, java.lang.String r9, boolean r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.g(android.content.Context, java.lang.String, boolean, boolean):int");
    }

    private static DynamiteModule h(Context context, String str) {
        Log.i("DynamiteModule", "Selected local version of ".concat(String.valueOf(str)));
        return new DynamiteModule(context);
    }

    private static void i(ClassLoader classLoader) {
        zzr zzrVar;
        zzp zzpVar = null;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(null).newInstance(null);
            if (iBinder == null) {
                zzrVar = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                zzrVar = queryLocalInterface instanceof zzr ? (zzr) queryLocalInterface : new zzr(iBinder);
            }
            f11356r = zzrVar;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e2) {
            throw new LoadingException("Failed to instantiate dynamite loader", e2, zzpVar);
        }
    }

    private static boolean j(Cursor cursor) {
        zzn zznVar = (zzn) f11351m.get();
        if (zznVar == null || zznVar.f11363a != null) {
            return false;
        }
        zznVar.f11363a = cursor;
        return true;
    }

    private static boolean k(Context context) {
        ApplicationInfo applicationInfo;
        Boolean bool = Boolean.TRUE;
        if (bool.equals(null) || bool.equals(f11350l)) {
            return true;
        }
        boolean z = false;
        if (f11350l == null) {
            ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.chimera", 0);
            if (GoogleApiAvailabilityLight.h().j(context, 10000000) == 0 && resolveContentProvider != null && "com.google.android.gms".equals(resolveContentProvider.packageName)) {
                z = true;
            }
            f11350l = Boolean.valueOf(z);
            if (z && (applicationInfo = resolveContentProvider.applicationInfo) != null && (applicationInfo.flags & 129) == 0) {
                Log.i("DynamiteModule", "Non-system-image GmsCore APK, forcing V1");
                f11348j = true;
            }
        }
        if (!z) {
            Log.e("DynamiteModule", "Invalid GmsCore APK, remote loading disabled.");
        }
        return z;
    }

    private static zzq l(Context context) {
        zzq zzqVar;
        synchronized (DynamiteModule.class) {
            zzq zzqVar2 = f11355q;
            if (zzqVar2 != null) {
                return zzqVar2;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzqVar = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    zzqVar = queryLocalInterface instanceof zzq ? (zzq) queryLocalInterface : new zzq(iBinder);
                }
                if (zzqVar != null) {
                    f11355q = zzqVar;
                    return zzqVar;
                }
            } catch (Exception e2) {
                Log.e("DynamiteModule", "Failed to load IDynamiteLoader from GmsCore: " + e2.getMessage());
            }
            return null;
        }
    }

    public Context b() {
        return this.f11357a;
    }

    public IBinder d(String str) {
        try {
            return (IBinder) this.f11357a.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e2) {
            throw new LoadingException("Failed to instantiate module class: ".concat(String.valueOf(str)), e2, null);
        }
    }
}
