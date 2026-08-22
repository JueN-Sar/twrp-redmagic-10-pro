package com.google.mlkit.vision.text.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.mlkit_vision_text_common.zzbk;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import java.util.List;

@KeepForSdk
/* loaded from: classes.dex */
public class TextRegistrar implements ComponentRegistrar {
    @Override // com.google.firebase.components.ComponentRegistrar
    public final List a() {
        return zzbk.l(Component.a(zzp.class).b(Dependency.g(MlKitContext.class)).d(new ComponentFactory() { // from class: com.google.mlkit.vision.text.internal.zzs
            @Override // com.google.firebase.components.ComponentFactory
            public final Object a(ComponentContainer componentContainer) {
                return new zzp((MlKitContext) componentContainer.a(MlKitContext.class));
            }
        }).c(), Component.a(zzo.class).b(Dependency.g(zzp.class)).b(Dependency.g(ExecutorSelector.class)).d(new ComponentFactory() { // from class: com.google.mlkit.vision.text.internal.zzt
            @Override // com.google.firebase.components.ComponentFactory
            public final Object a(ComponentContainer componentContainer) {
                return new zzo((zzp) componentContainer.a(zzp.class), (ExecutorSelector) componentContainer.a(ExecutorSelector.class));
            }
        }).c());
    }
}
