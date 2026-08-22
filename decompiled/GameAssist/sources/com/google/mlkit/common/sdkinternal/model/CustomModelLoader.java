package com.google.mlkit.common.sdkinternal.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzsh;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.LocalModel;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@KeepForSdk
/* loaded from: classes.dex */
public class CustomModelLoader {

    /* renamed from: f, reason: collision with root package name */
    private static final GmsLogger f15976f = new GmsLogger("CustomModelLoader", "");

    /* renamed from: g, reason: collision with root package name */
    private static final Map f15977g = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    private final MlKitContext f15978a;

    /* renamed from: b, reason: collision with root package name */
    private final CustomRemoteModel f15979b;

    /* renamed from: c, reason: collision with root package name */
    private final RemoteModelDownloadManager f15980c;

    /* renamed from: d, reason: collision with root package name */
    private final RemoteModelFileManager f15981d;

    /* renamed from: e, reason: collision with root package name */
    private final zzsh f15982e;

    @KeepForSdk
    public interface CustomModelLoaderHelper {
    }

    private final File a() {
        String d2 = ((RemoteModelFileManager) Preconditions.i(this.f15981d)).d();
        if (d2 == null) {
            f15976f.b("CustomModelLoader", "No existing model file");
            return null;
        }
        File file = new File(d2);
        File[] listFiles = file.listFiles();
        return ((File[]) Preconditions.i(listFiles)).length == 1 ? listFiles[0] : file;
    }

    private final void b() {
        ((RemoteModelDownloadManager) Preconditions.i(this.f15980c)).j();
    }

    private static final LocalModel c(File file) {
        if (file.isDirectory()) {
            LocalModel.Builder builder = new LocalModel.Builder();
            builder.c(new File(file.getAbsolutePath(), "manifest.json").toString());
            return builder.a();
        }
        LocalModel.Builder builder2 = new LocalModel.Builder();
        builder2.b(file.getAbsolutePath());
        return builder2.a();
    }

    @VisibleForTesting
    @Nullable
    @KeepForSdk
    @WorkerThread
    public synchronized LocalModel createLocalModelByLatestExistingModel() {
        f15976f.b("CustomModelLoader", "Try to get the latest existing model file.");
        File a2 = a();
        if (a2 == null) {
            return null;
        }
        return c(a2);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x009f A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00a1 A[Catch: all -> 0x0030, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0030, blocks: (B:3:0x0001, B:7:0x0023, B:9:0x002b, B:15:0x00a1, B:19:0x0033, B:21:0x004c, B:24:0x0055, B:25:0x006e, B:27:0x0076, B:28:0x0092), top: B:2:0x0001 }] */
    @androidx.annotation.VisibleForTesting
    @androidx.annotation.Nullable
    @com.google.android.gms.common.annotation.KeepForSdk
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized com.google.mlkit.common.model.LocalModel createLocalModelByNewlyDownloadedModel() {
        /*
            r7 = this;
            monitor-enter(r7)
            com.google.android.gms.common.internal.GmsLogger r0 = com.google.mlkit.common.sdkinternal.model.CustomModelLoader.f15976f     // Catch: java.lang.Throwable -> L30
            java.lang.String r1 = "CustomModelLoader"
            java.lang.String r2 = "Try to get newly downloaded model file."
            r0.b(r1, r2)     // Catch: java.lang.Throwable -> L30
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r1 = r7.f15980c     // Catch: java.lang.Throwable -> L30
            java.lang.Object r1 = com.google.android.gms.common.internal.Preconditions.i(r1)     // Catch: java.lang.Throwable -> L30
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r1 = (com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager) r1     // Catch: java.lang.Throwable -> L30
            java.lang.Long r1 = r1.c()     // Catch: java.lang.Throwable -> L30
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r2 = r7.f15980c     // Catch: java.lang.Throwable -> L30
            java.lang.String r2 = r2.d()     // Catch: java.lang.Throwable -> L30
            r3 = 0
            if (r1 == 0) goto L92
            if (r2 != 0) goto L23
            goto L92
        L23:
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r4 = r7.f15980c     // Catch: java.lang.Throwable -> L30
            java.lang.Integer r4 = r4.e()     // Catch: java.lang.Throwable -> L30
            if (r4 != 0) goto L33
            r7.b()     // Catch: java.lang.Throwable -> L30
        L2e:
            r1 = r3
            goto L9d
        L30:
            r0 = move-exception
            goto La7
        L33:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L30
            java.lang.String r5 = "Download Status code: "
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L30
            java.lang.String r5 = r5.concat(r6)     // Catch: java.lang.Throwable -> L30
            java.lang.String r6 = "CustomModelLoader"
            r0.b(r6, r5)     // Catch: java.lang.Throwable -> L30
            int r5 = r4.intValue()     // Catch: java.lang.Throwable -> L30
            r6 = 8
            if (r5 != r6) goto L6e
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r1 = r7.f15980c     // Catch: java.lang.Throwable -> L30
            java.io.File r1 = r1.t(r2)     // Catch: java.lang.Throwable -> L30
            if (r1 != 0) goto L55
            goto L2e
        L55:
            java.lang.String r4 = r1.getParent()     // Catch: java.lang.Throwable -> L30
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch: java.lang.Throwable -> L30
            java.lang.String r5 = "Moved the downloaded model to private folder successfully: "
            java.lang.String r6 = "CustomModelLoader"
            java.lang.String r4 = r5.concat(r4)     // Catch: java.lang.Throwable -> L30
            r0.b(r6, r4)     // Catch: java.lang.Throwable -> L30
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r0 = r7.f15980c     // Catch: java.lang.Throwable -> L30
            r0.k(r2)     // Catch: java.lang.Throwable -> L30
            goto L9d
        L6e:
            int r0 = r4.intValue()     // Catch: java.lang.Throwable -> L30
            r2 = 16
            if (r0 != r2) goto L2e
            com.google.android.gms.internal.mlkit_common.zzsh r0 = r7.f15982e     // Catch: java.lang.Throwable -> L30
            com.google.mlkit.common.model.CustomRemoteModel r2 = r7.f15979b     // Catch: java.lang.Throwable -> L30
            com.google.android.gms.internal.mlkit_common.zzry r4 = com.google.android.gms.internal.mlkit_common.zzsk.f()     // Catch: java.lang.Throwable -> L30
            java.lang.Object r2 = com.google.android.gms.common.internal.Preconditions.i(r2)     // Catch: java.lang.Throwable -> L30
            com.google.mlkit.common.model.RemoteModel r2 = (com.google.mlkit.common.model.RemoteModel) r2     // Catch: java.lang.Throwable -> L30
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r5 = r7.f15980c     // Catch: java.lang.Throwable -> L30
            int r1 = r5.f(r1)     // Catch: java.lang.Throwable -> L30
            r5 = 0
            r0.e(r4, r2, r5, r1)     // Catch: java.lang.Throwable -> L30
            r7.b()     // Catch: java.lang.Throwable -> L30
            goto L2e
        L92:
            java.lang.String r1 = "CustomModelLoader"
            java.lang.String r2 = "No new model is downloading."
            r0.b(r1, r2)     // Catch: java.lang.Throwable -> L30
            r7.b()     // Catch: java.lang.Throwable -> L30
            goto L2e
        L9d:
            if (r1 != 0) goto La1
            monitor-exit(r7)
            return r3
        La1:
            com.google.mlkit.common.model.LocalModel r0 = c(r1)     // Catch: java.lang.Throwable -> L30
            monitor-exit(r7)
            return r0
        La7:
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.CustomModelLoader.createLocalModelByNewlyDownloadedModel():com.google.mlkit.common.model.LocalModel");
    }

    @KeepForSdk
    @VisibleForTesting
    @WorkerThread
    public void deleteLatestExistingModel() {
        File a2 = a();
        if (a2 != null) {
            ((RemoteModelFileManager) Preconditions.i(this.f15981d)).e(a2);
            SharedPrefManager.f(this.f15978a).b((RemoteModel) Preconditions.i(this.f15979b));
        }
    }

    @KeepForSdk
    @VisibleForTesting
    @WorkerThread
    public void deleteOldModels(@NonNull LocalModel localModel) {
        File parentFile = new File((String) Preconditions.i(localModel.a())).getParentFile();
        if (!((RemoteModelFileManager) Preconditions.i(this.f15981d)).f((File) Preconditions.i(parentFile))) {
            f15976f.c("CustomModelLoader", "Failed to delete old models");
        } else {
            f15976f.b("CustomModelLoader", "All old models are deleted.");
            this.f15981d.c(parentFile);
        }
    }
}
