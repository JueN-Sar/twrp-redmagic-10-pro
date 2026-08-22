package com.google.mlkit.common.internal.model;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzlm;
import com.google.android.gms.internal.mlkit_common.zzmh;
import com.google.android.gms.internal.mlkit_common.zzmv;
import com.google.android.gms.internal.mlkit_common.zzmw;
import com.google.android.gms.internal.mlkit_common.zzne;
import com.google.android.gms.internal.mlkit_common.zzsh;
import com.google.android.gms.internal.mlkit_common.zzsk;
import com.google.android.gms.internal.mlkit_common.zzss;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import com.google.mlkit.common.sdkinternal.model.ModelInfoRetrieverInterop;
import com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager;
import com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager;
import com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface;

/* loaded from: classes.dex */
public final class zzg implements RemoteModelManagerInterface {

    /* renamed from: a, reason: collision with root package name */
    private final MlKitContext f15906a;

    /* renamed from: b, reason: collision with root package name */
    private final zzsh f15907b;

    public zzg(MlKitContext mlKitContext) {
        zzsh b2 = zzss.b("common");
        this.f15906a = mlKitContext;
        this.f15907b = b2;
    }

    private final RemoteModelDownloadManager e(CustomRemoteModel customRemoteModel) {
        RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(this.f15906a, customRemoteModel, null, new ModelFileHelper(this.f15906a), new zza(this.f15906a, customRemoteModel.e()));
        MlKitContext mlKitContext = this.f15906a;
        return RemoteModelDownloadManager.g(this.f15906a, customRemoteModel, new ModelFileHelper(mlKitContext), remoteModelFileManager, (ModelInfoRetrieverInterop) mlKitContext.a(ModelInfoRetrieverInterop.class));
    }

    final /* synthetic */ Boolean a(CustomRemoteModel customRemoteModel) {
        return Boolean.valueOf(e(customRemoteModel).h());
    }

    final /* synthetic */ void b(CustomRemoteModel customRemoteModel, TaskCompletionSource taskCompletionSource) {
        try {
            new ModelFileHelper(this.f15906a).a(ModelType.CUSTOM, (String) Preconditions.i(customRemoteModel.b()));
            taskCompletionSource.c(null);
        } catch (RuntimeException e2) {
            taskCompletionSource.b(new MlKitException("Internal error has occurred when executing ML Kit tasks", 13, e2));
        }
    }

    final /* synthetic */ void c(Task task) {
        boolean l2 = task.l();
        zzmw zzmwVar = new zzmw();
        zzlm zzlmVar = new zzlm();
        zzlmVar.b(zzne.CUSTOM);
        zzlmVar.a(Boolean.valueOf(l2));
        zzmwVar.e(zzlmVar.c());
        this.f15907b.d(zzsk.e(zzmwVar), zzmv.REMOTE_MODEL_DELETE_ON_DEVICE);
    }

    final /* synthetic */ void d(Task task) {
        Boolean bool = (Boolean) task.i();
        bool.booleanValue();
        zzmw zzmwVar = new zzmw();
        zzmh zzmhVar = new zzmh();
        zzmhVar.b(zzne.CUSTOM);
        zzmhVar.a(bool);
        zzmwVar.g(zzmhVar.c());
        this.f15907b.d(zzsk.e(zzmwVar), zzmv.REMOTE_MODEL_IS_DOWNLOADED);
    }
}
