package com.google.mlkit.common.sdkinternal.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.LongSparseArray;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.mlkit_common.zzmu;
import com.google.android.gms.internal.mlkit_common.zzna;
import com.google.android.gms.internal.mlkit_common.zzry;
import com.google.android.gms.internal.mlkit_common.zzsh;
import com.google.android.gms.internal.mlkit_common.zzsi;
import com.google.android.gms.internal.mlkit_common.zzsj;
import com.google.android.gms.internal.mlkit_common.zzsk;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MlKitContext;

@WorkerThread
/* loaded from: classes.dex */
final class zzc extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private final long f16016a;

    /* renamed from: b, reason: collision with root package name */
    private final TaskCompletionSource f16017b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ RemoteModelDownloadManager f16018c;

    /* synthetic */ zzc(RemoteModelDownloadManager remoteModelDownloadManager, long j2, TaskCompletionSource taskCompletionSource, zzb zzbVar) {
        this.f16018c = remoteModelDownloadManager;
        this.f16016a = j2;
        this.f16017b = taskCompletionSource;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        GmsLogger gmsLogger;
        LongSparseArray longSparseArray;
        LongSparseArray longSparseArray2;
        zzsh zzshVar;
        RemoteModel remoteModel;
        zzsh zzshVar2;
        RemoteModel remoteModel2;
        RemoteModel remoteModel3;
        zzsh zzshVar3;
        RemoteModel remoteModel4;
        MlKitException w;
        MlKitContext mlKitContext;
        long longExtra = intent.getLongExtra("extra_download_id", -1L);
        if (longExtra != this.f16016a) {
            return;
        }
        RemoteModelDownloadManager remoteModelDownloadManager = this.f16018c;
        Integer e2 = remoteModelDownloadManager.e();
        synchronized (remoteModelDownloadManager) {
            try {
                mlKitContext = this.f16018c.f15995c;
                mlKitContext.b().unregisterReceiver(this);
            } catch (IllegalArgumentException e3) {
                gmsLogger = RemoteModelDownloadManager.f15991m;
                gmsLogger.h("ModelDownloadManager", "Exception thrown while trying to unregister the broadcast receiver for the download", e3);
            }
            longSparseArray = this.f16018c.f15993a;
            longSparseArray.remove(this.f16016a);
            longSparseArray2 = this.f16018c.f15994b;
            longSparseArray2.remove(this.f16016a);
        }
        if (e2 != null) {
            if (e2.intValue() == 16) {
                RemoteModelDownloadManager remoteModelDownloadManager2 = this.f16018c;
                zzshVar3 = remoteModelDownloadManager2.f15999g;
                zzry f2 = zzsk.f();
                remoteModel4 = remoteModelDownloadManager2.f15997e;
                Long valueOf = Long.valueOf(longExtra);
                zzshVar3.e(f2, remoteModel4, false, remoteModelDownloadManager2.f(valueOf));
                TaskCompletionSource taskCompletionSource = this.f16017b;
                w = this.f16018c.w(valueOf);
                taskCompletionSource.b(w);
                return;
            }
            if (e2.intValue() == 8) {
                RemoteModelDownloadManager remoteModelDownloadManager3 = this.f16018c;
                zzshVar2 = remoteModelDownloadManager3.f15999g;
                zzry f3 = zzsk.f();
                remoteModel2 = remoteModelDownloadManager3.f15997e;
                zzsi h2 = zzsj.h();
                h2.b(zzmu.NO_ERROR);
                h2.e(true);
                remoteModel3 = this.f16018c.f15997e;
                h2.d(remoteModel3.d());
                h2.a(zzna.SUCCEEDED);
                zzshVar2.g(f3, remoteModel2, h2.g());
                this.f16017b.c(null);
                return;
            }
        }
        RemoteModelDownloadManager remoteModelDownloadManager4 = this.f16018c;
        zzshVar = remoteModelDownloadManager4.f15999g;
        zzry f4 = zzsk.f();
        remoteModel = remoteModelDownloadManager4.f15997e;
        zzshVar.e(f4, remoteModel, false, 0);
        this.f16017b.b(new MlKitException("Model downloading failed", 13));
    }
}
