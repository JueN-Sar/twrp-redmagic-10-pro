package com.google.mlkit.common.sdkinternal.model;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelType;
import java.io.File;

@KeepForSdk
/* loaded from: classes.dex */
public class ModelFileHelper {

    /* renamed from: b, reason: collision with root package name */
    private static final GmsLogger f15985b = new GmsLogger("ModelFileHelper", "");

    @NonNull
    @VisibleForTesting
    public static final String zza = String.format("com.google.mlkit.%s.models", "translate");

    @NonNull
    @VisibleForTesting
    public static final String zzb = String.format("com.google.mlkit.%s.models", "custom");

    @VisibleForTesting
    static final String zzc = String.format("com.google.mlkit.%s.models", "base");

    /* renamed from: a, reason: collision with root package name */
    private final MlKitContext f15986a;

    public ModelFileHelper(MlKitContext mlKitContext) {
        this.f15986a = mlKitContext;
    }

    private final File k(String str, ModelType modelType, boolean z) {
        File f2 = f(str, modelType, z);
        if (!f2.exists()) {
            f15985b.b("ModelFileHelper", "model folder does not exist, creating one: ".concat(String.valueOf(f2.getAbsolutePath())));
            if (!f2.mkdirs()) {
                throw new MlKitException("Failed to create model folder: ".concat(String.valueOf(f2)), 13);
            }
        } else if (!f2.isDirectory()) {
            throw new MlKitException("Can not create model folder, since an existing file has the same name: ".concat(String.valueOf(f2)), 6);
        }
        return f2;
    }

    public synchronized void a(ModelType modelType, String str) {
        b(f(str, modelType, false));
        b(f(str, modelType, true));
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x002a, code lost:
    
        if (r5 != false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean b(java.io.File r8) {
        /*
            r7 = this;
            r0 = 0
            if (r8 != 0) goto L4
            return r0
        L4:
            boolean r1 = r8.isDirectory()
            r2 = 1
            if (r1 == 0) goto L2c
            java.io.File[] r1 = r8.listFiles()
            java.lang.Object r1 = com.google.android.gms.common.internal.Preconditions.i(r1)
            java.io.File[] r1 = (java.io.File[]) r1
            int r3 = r1.length
            r4 = r0
            r5 = r2
        L18:
            if (r4 >= r3) goto L2a
            r6 = r1[r4]
            if (r5 == 0) goto L26
            boolean r5 = r7.b(r6)
            if (r5 == 0) goto L26
            r5 = r2
            goto L27
        L26:
            r5 = r0
        L27:
            int r4 = r4 + 1
            goto L18
        L2a:
            if (r5 == 0) goto L33
        L2c:
            boolean r7 = r8.delete()
            if (r7 == 0) goto L33
            return r2
        L33:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.ModelFileHelper.b(java.io.File):boolean");
    }

    public void c(String str, ModelType modelType) {
        File k2 = k(str, modelType, true);
        if (b(k2)) {
            return;
        }
        f15985b.c("ModelFileHelper", "Failed to delete the temp labels file directory: ".concat(String.valueOf(k2 != null ? k2.getAbsolutePath() : null)));
    }

    public int d(File file) {
        File[] listFiles = file.listFiles();
        int i2 = -1;
        if (listFiles != null && (listFiles.length) != 0) {
            for (File file2 : listFiles) {
                try {
                    i2 = Math.max(i2, Integer.parseInt(file2.getName()));
                } catch (NumberFormatException unused) {
                    f15985b.b("ModelFileHelper", "Contains non-integer file name ".concat(String.valueOf(file2.getName())));
                }
            }
        }
        return i2;
    }

    public File e(String str, ModelType modelType) {
        return k(str, modelType, false);
    }

    public File f(String str, ModelType modelType, boolean z) {
        String str2;
        ModelType modelType2 = ModelType.UNKNOWN;
        int ordinal = modelType.ordinal();
        if (ordinal == 1) {
            str2 = zzc;
        } else if (ordinal == 2) {
            str2 = zza;
        } else {
            if (ordinal != 4) {
                throw new IllegalArgumentException("Unknown model type " + modelType.name() + ". Cannot find a dir to store the downloaded model.");
            }
            str2 = zzb;
        }
        File file = new File(this.f15986a.b().getNoBackupFilesDir(), str2);
        if (z) {
            file = new File(file, "temp");
        }
        return new File(file, str);
    }

    public File g(String str, ModelType modelType, String str2) {
        File k2 = k(str, modelType, true);
        if (k2.exists() && k2.isFile() && !k2.delete()) {
            throw new MlKitException("Failed to delete the temp labels file: ".concat(String.valueOf(k2.getAbsolutePath())), 13);
        }
        if (!k2.exists()) {
            f15985b.b("ModelFileHelper", "Temp labels folder does not exist, creating one: ".concat(String.valueOf(k2.getAbsolutePath())));
            if (!k2.mkdirs()) {
                throw new MlKitException("Failed to create a directory to hold the AutoML model's labels file.", 13);
            }
        }
        return new File(k2, str2);
    }

    public boolean h(String str, ModelType modelType) {
        String j2;
        if (modelType == ModelType.UNKNOWN || (j2 = j(str, modelType)) == null) {
            return false;
        }
        File file = new File(j2);
        if (!file.exists()) {
            return false;
        }
        File file2 = new File(file, "model.tflite");
        f15985b.e("ModelFileHelper", "Model file path: ".concat(String.valueOf(file2.getAbsolutePath())));
        return file2.exists();
    }

    public final File i(String str, ModelType modelType) {
        return k(str, modelType, true);
    }

    public final String j(String str, ModelType modelType) {
        File e2 = e(str, modelType);
        int d2 = d(e2);
        if (d2 == -1) {
            return null;
        }
        return e2.getAbsolutePath() + "/" + d2;
    }
}
