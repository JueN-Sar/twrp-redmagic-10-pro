package com.google.mlkit.common.sdkinternal.model;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.File;

@KeepForSdk
/* loaded from: classes.dex */
public abstract class LegacyModelMigrator {

    /* renamed from: a, reason: collision with root package name */
    private final TaskCompletionSource f15983a;

    /* renamed from: b, reason: collision with root package name */
    private final Context f15984b;

    protected static void a(File file) {
        File[] listFiles = file.listFiles();
        if ((listFiles == null || listFiles.length == 0) && !file.delete()) {
            Log.e("MlKitLegacyMigration", "Error deleting model directory ".concat(String.valueOf(file)));
        }
    }

    @KeepForSdk
    @VisibleForTesting
    public static void migrateFile(@NonNull File file, @NonNull File file2) {
        if (file.exists()) {
            if (!file2.exists() && !file.renameTo(file2)) {
                Log.e("MlKitLegacyMigration", "Error moving model file " + String.valueOf(file) + " to " + String.valueOf(file2));
            }
            if (!file.exists() || file.delete()) {
                return;
            }
            Log.e("MlKitLegacyMigration", "Error deleting model file ".concat(String.valueOf(file)));
        }
    }

    protected abstract void b(File file);

    final /* synthetic */ void c() {
        File legacyRootDir = getLegacyRootDir();
        File[] listFiles = legacyRootDir.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                b(file);
            }
            a(legacyRootDir);
        }
        this.f15983a.c(null);
    }

    @NonNull
    @KeepForSdk
    @VisibleForTesting
    protected abstract String getLegacyModelDirName();

    @NonNull
    @KeepForSdk
    @VisibleForTesting
    public File getLegacyRootDir() {
        Context context = this.f15984b;
        return new File(context.getNoBackupFilesDir(), getLegacyModelDirName());
    }
}
