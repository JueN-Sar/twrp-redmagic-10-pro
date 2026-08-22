package com.google.mlkit.common.sdkinternal.model;

import android.app.DownloadManager;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.ParcelFileDescriptor;
import android.util.LongSparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzmu;
import com.google.android.gms.internal.mlkit_common.zzna;
import com.google.android.gms.internal.mlkit_common.zzsh;
import com.google.android.gms.internal.mlkit_common.zzsk;
import com.google.android.gms.internal.mlkit_common.zzss;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelInfo;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@KeepForSdk
/* loaded from: classes.dex */
public class RemoteModelDownloadManager {

    /* renamed from: m, reason: collision with root package name */
    private static final GmsLogger f15991m = new GmsLogger("ModelDownloadManager", "");

    /* renamed from: n, reason: collision with root package name */
    private static final Map f15992n = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    private final LongSparseArray f15993a = new LongSparseArray();

    /* renamed from: b, reason: collision with root package name */
    private final LongSparseArray f15994b = new LongSparseArray();

    /* renamed from: c, reason: collision with root package name */
    private final MlKitContext f15995c;

    /* renamed from: d, reason: collision with root package name */
    private final DownloadManager f15996d;

    /* renamed from: e, reason: collision with root package name */
    private final RemoteModel f15997e;

    /* renamed from: f, reason: collision with root package name */
    private final ModelType f15998f;

    /* renamed from: g, reason: collision with root package name */
    private final zzsh f15999g;

    /* renamed from: h, reason: collision with root package name */
    private final SharedPrefManager f16000h;

    /* renamed from: i, reason: collision with root package name */
    private final ModelFileHelper f16001i;

    /* renamed from: j, reason: collision with root package name */
    private final ModelInfoRetrieverInterop f16002j;

    /* renamed from: k, reason: collision with root package name */
    private final RemoteModelFileManager f16003k;

    /* renamed from: l, reason: collision with root package name */
    private DownloadConditions f16004l;

    @VisibleForTesting
    RemoteModelDownloadManager(@NonNull MlKitContext mlKitContext, @NonNull RemoteModel remoteModel, @NonNull ModelFileHelper modelFileHelper, @NonNull RemoteModelFileManager remoteModelFileManager, @Nullable ModelInfoRetrieverInterop modelInfoRetrieverInterop, @NonNull zzsh zzshVar) {
        this.f15995c = mlKitContext;
        this.f15998f = remoteModel.d();
        this.f15997e = remoteModel;
        DownloadManager downloadManager = (DownloadManager) mlKitContext.b().getSystemService("download");
        this.f15996d = downloadManager;
        this.f15999g = zzshVar;
        if (downloadManager == null) {
            f15991m.b("ModelDownloadManager", "Download manager service is not available in the service.");
        }
        this.f16001i = modelFileHelper;
        this.f16000h = SharedPrefManager.f(mlKitContext);
        this.f16002j = modelInfoRetrieverInterop;
        this.f16003k = remoteModelFileManager;
    }

    public static synchronized RemoteModelDownloadManager g(MlKitContext mlKitContext, RemoteModel remoteModel, ModelFileHelper modelFileHelper, RemoteModelFileManager remoteModelFileManager, ModelInfoRetrieverInterop modelInfoRetrieverInterop) {
        RemoteModelDownloadManager remoteModelDownloadManager;
        synchronized (RemoteModelDownloadManager.class) {
            try {
                Map map = f15992n;
                if (!map.containsKey(remoteModel)) {
                    map.put(remoteModel, new RemoteModelDownloadManager(mlKitContext, remoteModel, modelFileHelper, remoteModelFileManager, modelInfoRetrieverInterop, zzss.b("common")));
                }
                remoteModelDownloadManager = (RemoteModelDownloadManager) map.get(remoteModel);
            } catch (Throwable th) {
                throw th;
            }
        }
        return remoteModelDownloadManager;
    }

    private final Task u(long j2) {
        MlKitContext mlKitContext = this.f15995c;
        ContextCompat.k(mlKitContext.b(), x(j2), new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"), null, MLTaskExecutor.b().a(), 2);
        return v(j2).a();
    }

    private final synchronized TaskCompletionSource v(long j2) {
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) this.f15994b.get(j2);
        if (taskCompletionSource != null) {
            return taskCompletionSource;
        }
        TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        this.f15994b.put(j2, taskCompletionSource2);
        return taskCompletionSource2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MlKitException w(Long l2) {
        DownloadManager downloadManager = this.f15996d;
        Cursor cursor = null;
        if (downloadManager != null && l2 != null) {
            cursor = downloadManager.query(new DownloadManager.Query().setFilterById(l2.longValue()));
        }
        int i2 = 13;
        String str = "Model downloading failed";
        if (cursor != null && cursor.moveToFirst()) {
            int i3 = cursor.getInt(cursor.getColumnIndex("reason"));
            if (i3 == 1006) {
                str = "Model downloading failed due to insufficient space on the device.";
                i2 = 101;
            } else {
                str = "Model downloading failed due to error code: " + i3 + " from Android DownloadManager";
            }
        }
        return new MlKitException(str, i2);
    }

    private final synchronized zzc x(long j2) {
        zzc zzcVar = (zzc) this.f15993a.get(j2);
        if (zzcVar != null) {
            return zzcVar;
        }
        zzc zzcVar2 = new zzc(this, j2, v(j2), null);
        this.f15993a.put(j2, zzcVar2);
        return zzcVar2;
    }

    private final synchronized Long y(DownloadManager.Request request, ModelInfo modelInfo) {
        DownloadManager downloadManager = this.f15996d;
        if (downloadManager == null) {
            return null;
        }
        long enqueue = downloadManager.enqueue(request);
        f15991m.b("ModelDownloadManager", "Schedule a new downloading task: " + enqueue);
        this.f16000h.l(enqueue, modelInfo);
        this.f15999g.f(zzsk.f(), this.f15997e, zzmu.NO_ERROR, false, modelInfo.c(), zzna.SCHEDULED);
        return Long.valueOf(enqueue);
    }

    private final synchronized Long z(ModelInfo modelInfo, DownloadConditions downloadConditions) {
        try {
            Preconditions.j(downloadConditions, "DownloadConditions can not be null");
            String c2 = this.f16000h.c(this.f15997e);
            Integer e2 = e();
            if (c2 != null && c2.equals(modelInfo.a()) && e2 != null) {
                Integer e3 = e();
                if (e3 != null) {
                    if (e3.intValue() != 8 && e3.intValue() != 16) {
                    }
                    f15991m.b("ModelDownloadManager", "New model is already in downloading, do nothing.");
                    return null;
                }
                zzsh zzshVar = this.f15999g;
                RemoteModel remoteModel = this.f15997e;
                zzshVar.f(zzsk.f(), remoteModel, zzmu.NO_ERROR, false, remoteModel.d(), zzna.DOWNLOADING);
                f15991m.b("ModelDownloadManager", "New model is already in downloading, do nothing.");
                return null;
            }
            GmsLogger gmsLogger = f15991m;
            gmsLogger.b("ModelDownloadManager", "Need to download a new model.");
            j();
            DownloadManager.Request request = new DownloadManager.Request(modelInfo.d());
            if (this.f16001i.h(modelInfo.b(), modelInfo.c())) {
                gmsLogger.b("ModelDownloadManager", "Model update is enabled and have a previous downloaded model, use download condition");
                this.f15999g.f(zzsk.f(), this.f15997e, zzmu.NO_ERROR, false, modelInfo.c(), zzna.UPDATE_AVAILABLE);
            }
            request.setRequiresCharging(downloadConditions.a());
            if (downloadConditions.b()) {
                request.setAllowedNetworkTypes(2);
            }
            return y(request, modelInfo);
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00af, code lost:
    
        r1 = z(r1, r13.f16004l);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00b5, code lost:
    
        if (r1 == null) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00bf, code lost:
    
        return u(r1.longValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00c0, code lost:
    
        com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.f15991m.e("ModelDownloadManager", "Didn't schedule download for the updated model");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.android.gms.tasks.Task a() {
        /*
            r13 = this;
            com.google.android.gms.internal.mlkit_common.zzsh r0 = r13.f15999g
            com.google.android.gms.internal.mlkit_common.zzry r1 = com.google.android.gms.internal.mlkit_common.zzsk.f()
            com.google.mlkit.common.model.RemoteModel r2 = r13.f15997e
            com.google.android.gms.internal.mlkit_common.zzmu r3 = com.google.android.gms.internal.mlkit_common.zzmu.NO_ERROR
            com.google.mlkit.common.sdkinternal.ModelType r5 = com.google.mlkit.common.sdkinternal.ModelType.UNKNOWN
            com.google.android.gms.internal.mlkit_common.zzna r6 = com.google.android.gms.internal.mlkit_common.zzna.EXPLICITLY_REQUESTED
            r4 = 0
            r0.f(r1, r2, r3, r4, r5, r6)
            r0 = 0
            com.google.mlkit.common.sdkinternal.ModelInfo r1 = r13.r()     // Catch: com.google.mlkit.common.MlKitException -> L19
            r2 = r0
            goto L1c
        L19:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L1c:
            r3 = 13
            java.lang.Integer r4 = r13.e()     // Catch: com.google.mlkit.common.MlKitException -> L38
            java.lang.Long r5 = r13.c()     // Catch: com.google.mlkit.common.MlKitException -> L38
            boolean r6 = r13.i()     // Catch: com.google.mlkit.common.MlKitException -> L38
            if (r6 != 0) goto Lad
            if (r4 == 0) goto L3b
            int r6 = r4.intValue()     // Catch: com.google.mlkit.common.MlKitException -> L38
            r7 = 8
            if (r6 != r7) goto L3b
            goto Lad
        L38:
            r13 = move-exception
            goto Lce
        L3b:
            if (r4 == 0) goto L51
            int r6 = r4.intValue()     // Catch: com.google.mlkit.common.MlKitException -> L38
            r7 = 16
            if (r6 != r7) goto L51
            com.google.mlkit.common.MlKitException r0 = r13.w(r5)     // Catch: com.google.mlkit.common.MlKitException -> L38
            r13.j()     // Catch: com.google.mlkit.common.MlKitException -> L38
            com.google.android.gms.tasks.Task r13 = com.google.android.gms.tasks.Tasks.b(r0)     // Catch: com.google.mlkit.common.MlKitException -> L38
            return r13
        L51:
            if (r4 == 0) goto L8d
            int r6 = r4.intValue()     // Catch: com.google.mlkit.common.MlKitException -> L38
            r7 = 4
            if (r6 == r7) goto L68
            int r6 = r4.intValue()     // Catch: com.google.mlkit.common.MlKitException -> L38
            r7 = 2
            if (r6 == r7) goto L68
            int r4 = r4.intValue()     // Catch: com.google.mlkit.common.MlKitException -> L38
            r6 = 1
            if (r4 != r6) goto L8d
        L68:
            if (r5 == 0) goto L8d
            java.lang.String r4 = r13.d()     // Catch: com.google.mlkit.common.MlKitException -> L38
            if (r4 == 0) goto L8d
            com.google.android.gms.internal.mlkit_common.zzsh r6 = r13.f15999g     // Catch: com.google.mlkit.common.MlKitException -> L38
            com.google.android.gms.internal.mlkit_common.zzry r7 = com.google.android.gms.internal.mlkit_common.zzsk.f()     // Catch: com.google.mlkit.common.MlKitException -> L38
            com.google.mlkit.common.model.RemoteModel r8 = r13.f15997e     // Catch: com.google.mlkit.common.MlKitException -> L38
            com.google.android.gms.internal.mlkit_common.zzmu r9 = com.google.android.gms.internal.mlkit_common.zzmu.NO_ERROR     // Catch: com.google.mlkit.common.MlKitException -> L38
            com.google.mlkit.common.sdkinternal.ModelType r11 = r8.d()     // Catch: com.google.mlkit.common.MlKitException -> L38
            com.google.android.gms.internal.mlkit_common.zzna r12 = com.google.android.gms.internal.mlkit_common.zzna.DOWNLOADING     // Catch: com.google.mlkit.common.MlKitException -> L38
            r10 = 0
            r6.f(r7, r8, r9, r10, r11, r12)     // Catch: com.google.mlkit.common.MlKitException -> L38
            long r0 = r5.longValue()     // Catch: com.google.mlkit.common.MlKitException -> L38
            com.google.android.gms.tasks.Task r13 = r13.u(r0)     // Catch: com.google.mlkit.common.MlKitException -> L38
            return r13
        L8d:
            if (r1 != 0) goto L90
            goto L96
        L90:
            com.google.mlkit.common.model.DownloadConditions r0 = r13.f16004l     // Catch: com.google.mlkit.common.MlKitException -> L38
            java.lang.Long r0 = r13.z(r1, r0)     // Catch: com.google.mlkit.common.MlKitException -> L38
        L96:
            if (r0 != 0) goto La4
            com.google.mlkit.common.MlKitException r13 = new com.google.mlkit.common.MlKitException     // Catch: com.google.mlkit.common.MlKitException -> L38
            java.lang.String r0 = "Failed to schedule the download task"
            r13.<init>(r0, r3, r2)     // Catch: com.google.mlkit.common.MlKitException -> L38
            com.google.android.gms.tasks.Task r13 = com.google.android.gms.tasks.Tasks.b(r13)     // Catch: com.google.mlkit.common.MlKitException -> L38
            return r13
        La4:
            long r0 = r0.longValue()     // Catch: com.google.mlkit.common.MlKitException -> L38
            com.google.android.gms.tasks.Task r13 = r13.u(r0)     // Catch: com.google.mlkit.common.MlKitException -> L38
            return r13
        Lad:
            if (r1 == 0) goto Lc9
            com.google.mlkit.common.model.DownloadConditions r2 = r13.f16004l     // Catch: com.google.mlkit.common.MlKitException -> L38
            java.lang.Long r1 = r13.z(r1, r2)     // Catch: com.google.mlkit.common.MlKitException -> L38
            if (r1 == 0) goto Lc0
            long r0 = r1.longValue()     // Catch: com.google.mlkit.common.MlKitException -> L38
            com.google.android.gms.tasks.Task r13 = r13.u(r0)     // Catch: com.google.mlkit.common.MlKitException -> L38
            return r13
        Lc0:
            com.google.android.gms.common.internal.GmsLogger r13 = com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.f15991m     // Catch: com.google.mlkit.common.MlKitException -> L38
            java.lang.String r1 = "ModelDownloadManager"
            java.lang.String r2 = "Didn't schedule download for the updated model"
            r13.e(r1, r2)     // Catch: com.google.mlkit.common.MlKitException -> L38
        Lc9:
            com.google.android.gms.tasks.Task r13 = com.google.android.gms.tasks.Tasks.c(r0)     // Catch: com.google.mlkit.common.MlKitException -> L38
            return r13
        Lce:
            com.google.mlkit.common.MlKitException r0 = new com.google.mlkit.common.MlKitException
            java.lang.String r1 = "Failed to ensure the model is downloaded."
            r0.<init>(r1, r3, r13)
            com.google.android.gms.tasks.Task r13 = com.google.android.gms.tasks.Tasks.b(r0)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.a():com.google.android.gms.tasks.Task");
    }

    public synchronized ParcelFileDescriptor b() {
        DownloadManager downloadManager = this.f15996d;
        Long c2 = c();
        ParcelFileDescriptor parcelFileDescriptor = null;
        if (downloadManager == null || c2 == null) {
            return null;
        }
        try {
            parcelFileDescriptor = downloadManager.openDownloadedFile(c2.longValue());
        } catch (FileNotFoundException unused) {
            f15991m.c("ModelDownloadManager", "Downloaded file is not found");
        }
        return parcelFileDescriptor;
    }

    public synchronized Long c() {
        return this.f16000h.d(this.f15997e);
    }

    public synchronized String d() {
        return this.f16000h.c(this.f15997e);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x006b, code lost:
    
        if (r2.intValue() != 16) goto L36;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0049 A[Catch: all -> 0x003c, TRY_ENTER, TryCatch #2 {all -> 0x003c, blocks: (B:39:0x0027, B:41:0x002d, B:14:0x0049, B:16:0x0050, B:18:0x0057, B:20:0x005d, B:22:0x0065), top: B:38:0x0027, outer: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized java.lang.Integer e() {
        /*
            r8 = this;
            r0 = 1
            monitor-enter(r8)
            android.app.DownloadManager r1 = r8.f15996d     // Catch: java.lang.Throwable -> L47
            java.lang.Long r2 = r8.c()     // Catch: java.lang.Throwable -> L47
            r3 = 0
            if (r1 == 0) goto L7d
            if (r2 != 0) goto Lf
            goto L7d
        Lf:
            android.app.DownloadManager$Query r4 = new android.app.DownloadManager$Query     // Catch: java.lang.Throwable -> L47
            r4.<init>()     // Catch: java.lang.Throwable -> L47
            long r5 = r2.longValue()     // Catch: java.lang.Throwable -> L47
            long[] r2 = new long[r0]     // Catch: java.lang.Throwable -> L47
            r7 = 0
            r2[r7] = r5     // Catch: java.lang.Throwable -> L47
            android.app.DownloadManager$Query r2 = r4.setFilterById(r2)     // Catch: java.lang.Throwable -> L47
            android.database.Cursor r1 = r1.query(r2)     // Catch: java.lang.Throwable -> L47
            if (r1 == 0) goto L3e
            boolean r2 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L3c
            if (r2 == 0) goto L3e
            java.lang.String r2 = "status"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L3c
            int r2 = r1.getInt(r2)     // Catch: java.lang.Throwable -> L3c
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L3c
            goto L3f
        L3c:
            r0 = move-exception
            goto L74
        L3e:
            r2 = r3
        L3f:
            if (r2 != 0) goto L49
            if (r1 == 0) goto L7d
            r1.close()     // Catch: java.lang.Throwable -> L47
            goto L7d
        L47:
            r0 = move-exception
            goto L7f
        L49:
            int r4 = r2.intValue()     // Catch: java.lang.Throwable -> L3c
            r5 = 2
            if (r4 == r5) goto L6e
            int r4 = r2.intValue()     // Catch: java.lang.Throwable -> L3c
            r5 = 4
            if (r4 == r5) goto L6e
            int r4 = r2.intValue()     // Catch: java.lang.Throwable -> L3c
            if (r4 == r0) goto L6e
            int r0 = r2.intValue()     // Catch: java.lang.Throwable -> L3c
            r4 = 8
            if (r0 == r4) goto L6e
            int r0 = r2.intValue()     // Catch: java.lang.Throwable -> L3c
            r4 = 16
            if (r0 == r4) goto L6e
            goto L6f
        L6e:
            r3 = r2
        L6f:
            r1.close()     // Catch: java.lang.Throwable -> L47
            monitor-exit(r8)
            return r3
        L74:
            r1.close()     // Catch: java.lang.Throwable -> L78
            goto L7c
        L78:
            r1 = move-exception
            r0.addSuppressed(r1)     // Catch: java.lang.Throwable -> L47
        L7c:
            throw r0     // Catch: java.lang.Throwable -> L47
        L7d:
            monitor-exit(r8)
            return r3
        L7f:
            monitor-exit(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.e():java.lang.Integer");
    }

    public int f(Long l2) {
        int columnIndex;
        DownloadManager downloadManager = this.f15996d;
        Cursor cursor = null;
        if (downloadManager != null && l2 != null) {
            cursor = downloadManager.query(new DownloadManager.Query().setFilterById(l2.longValue()));
        }
        if (cursor == null || !cursor.moveToFirst() || (columnIndex = cursor.getColumnIndex("reason")) == -1) {
            return 0;
        }
        return cursor.getInt(columnIndex);
    }

    public boolean h() {
        try {
            if (i()) {
                return true;
            }
        } catch (MlKitException unused) {
            f15991m.b("ModelDownloadManager", "Failed to check if the model exist locally.");
        }
        Long c2 = c();
        String d2 = d();
        if (c2 == null || d2 == null) {
            f15991m.b("ModelDownloadManager", "No new model is downloading.");
            j();
            return false;
        }
        Integer e2 = e();
        f15991m.b("ModelDownloadManager", "Download Status code: ".concat(String.valueOf(e2)));
        if (e2 != null) {
            return Objects.a(e2, 8) && t(d2) != null;
        }
        j();
        return false;
    }

    public boolean i() {
        return this.f16001i.h(this.f15997e.e(), this.f15998f);
    }

    public synchronized void j() {
        try {
            DownloadManager downloadManager = this.f15996d;
            Long c2 = c();
            if (downloadManager != null && c2 != null) {
                f15991m.b("ModelDownloadManager", "Cancel or remove existing downloading task: ".concat(c2.toString()));
                if (this.f15996d.remove(c2.longValue()) <= 0) {
                    if (e() == null) {
                    }
                }
                ModelFileHelper modelFileHelper = this.f16001i;
                RemoteModel remoteModel = this.f15997e;
                modelFileHelper.c(remoteModel.e(), remoteModel.d());
                this.f16000h.a(this.f15997e);
            }
        } finally {
        }
    }

    public synchronized void k(String str) {
        this.f16000h.n(this.f15997e, str);
        j();
    }

    final synchronized ModelInfo r() {
        boolean z;
        try {
            boolean i2 = i();
            if (i2) {
                zzsh zzshVar = this.f15999g;
                RemoteModel remoteModel = this.f15997e;
                zzshVar.f(zzsk.f(), remoteModel, zzmu.NO_ERROR, false, remoteModel.d(), zzna.LIVE);
            }
            ModelInfoRetrieverInterop modelInfoRetrieverInterop = this.f16002j;
            if (modelInfoRetrieverInterop == null) {
                throw new MlKitException("Please include com.google.mlkit:linkfirebase sdk as your dependency when you try to download from Firebase.", 14);
            }
            ModelInfo a2 = modelInfoRetrieverInterop.a(this.f15997e);
            if (a2 == null) {
                return null;
            }
            MlKitContext mlKitContext = this.f15995c;
            RemoteModel remoteModel2 = this.f15997e;
            String a3 = a2.a();
            SharedPrefManager f2 = SharedPrefManager.f(mlKitContext);
            boolean equals = a3.equals(f2.e(remoteModel2));
            boolean z2 = false;
            if (equals && CommonUtils.a(mlKitContext.b()).equals(f2.k())) {
                f15991m.c("ModelDownloadManager", "The model is incompatible with TFLite and the app is not upgraded, do not download");
                z = false;
            } else {
                z = true;
            }
            if (!i2) {
                this.f16000h.b(this.f15997e);
            }
            boolean z3 = !a2.a().equals(SharedPrefManager.f(this.f15995c).g(this.f15997e));
            if (!z) {
                z2 = z3;
            } else if (!i2 || z3) {
                return a2;
            }
            if (i2 && (z2 ^ z)) {
                return null;
            }
            throw new MlKitException("The model " + this.f15997e.b() + " is incompatible with TFLite runtime", 100);
        } catch (Throwable th) {
            throw th;
        }
    }

    public final File t(String str) {
        GmsLogger gmsLogger = f15991m;
        gmsLogger.b("ModelDownloadManager", "Model downloaded successfully");
        this.f15999g.f(zzsk.f(), this.f15997e, zzmu.NO_ERROR, true, this.f15998f, zzna.SUCCEEDED);
        ParcelFileDescriptor b2 = b();
        if (b2 == null) {
            j();
            return null;
        }
        gmsLogger.b("ModelDownloadManager", "moving downloaded model from external storage to private folder.");
        try {
            return this.f16003k.b(b2, str, this.f15997e);
        } finally {
            j();
        }
    }
}
