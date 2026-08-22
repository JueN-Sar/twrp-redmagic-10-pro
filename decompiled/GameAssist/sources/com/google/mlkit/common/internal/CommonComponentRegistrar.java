package com.google.mlkit.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.mlkit_common.zzaf;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.common.sdkinternal.Cleaner;
import com.google.mlkit.common.sdkinternal.CloseGuard;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.MlKitThreadPool;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import java.util.List;

@KeepForSdk
/* loaded from: classes.dex */
public class CommonComponentRegistrar implements ComponentRegistrar {
    @Override // com.google.firebase.components.ComponentRegistrar
    public final List a() {
        return zzaf.l(SharedPrefManager.f15970b, Component.a(ModelFileHelper.class).b(Dependency.g(MlKitContext.class)).d(new ComponentFactory() { // from class: com.google.mlkit.common.internal.zza
            @Override // com.google.firebase.components.ComponentFactory
            public final Object a(ComponentContainer componentContainer) {
                return new ModelFileHelper((MlKitContext) componentContainer.a(MlKitContext.class));
            }
        }).c(), Component.a(MlKitThreadPool.class).d(new ComponentFactory() { // from class: com.google.mlkit.common.internal.zzb
            @Override // com.google.firebase.components.ComponentFactory
            public final Object a(ComponentContainer componentContainer) {
                return new MlKitThreadPool();
            }
        }).c(), Component.a(RemoteModelManager.class).b(Dependency.i(RemoteModelManager.RemoteModelManagerRegistration.class)).d(new ComponentFactory() { // from class: com.google.mlkit.common.internal.zzc
            @Override // com.google.firebase.components.ComponentFactory
            public final Object a(ComponentContainer componentContainer) {
                return new RemoteModelManager(componentContainer.c(RemoteModelManager.RemoteModelManagerRegistration.class));
            }
        }).c(), Component.a(ExecutorSelector.class).b(Dependency.h(MlKitThreadPool.class)).d(new ComponentFactory() { // from class: com.google.mlkit.common.internal.zzd
            @Override // com.google.firebase.components.ComponentFactory
            public final Object a(ComponentContainer componentContainer) {
                return new ExecutorSelector(componentContainer.d(MlKitThreadPool.class));
            }
        }).c(), Component.a(Cleaner.class).d(new ComponentFactory() { // from class: com.google.mlkit.common.internal.zze
            @Override // com.google.firebase.components.ComponentFactory
            public final Object a(ComponentContainer componentContainer) {
                return Cleaner.a();
            }
        }).c(), Component.a(CloseGuard.Factory.class).b(Dependency.g(Cleaner.class)).d(new ComponentFactory() { // from class: com.google.mlkit.common.internal.zzf
            @Override // com.google.firebase.components.ComponentFactory
            public final Object a(ComponentContainer componentContainer) {
                return new CloseGuard.Factory((Cleaner) componentContainer.a(Cleaner.class));
            }
        }).c(), Component.a(com.google.mlkit.common.internal.model.zzg.class).b(Dependency.g(MlKitContext.class)).d(new ComponentFactory() { // from class: com.google.mlkit.common.internal.zzg
            @Override // com.google.firebase.components.ComponentFactory
            public final Object a(ComponentContainer componentContainer) {
                return new com.google.mlkit.common.internal.model.zzg((MlKitContext) componentContainer.a(MlKitContext.class));
            }
        }).c(), Component.g(RemoteModelManager.RemoteModelManagerRegistration.class).b(Dependency.h(com.google.mlkit.common.internal.model.zzg.class)).d(new ComponentFactory() { // from class: com.google.mlkit.common.internal.zzh
            @Override // com.google.firebase.components.ComponentFactory
            public final Object a(ComponentContainer componentContainer) {
                return new RemoteModelManager.RemoteModelManagerRegistration(CustomRemoteModel.class, componentContainer.d(com.google.mlkit.common.internal.model.zzg.class));
            }
        }).c());
    }
}
