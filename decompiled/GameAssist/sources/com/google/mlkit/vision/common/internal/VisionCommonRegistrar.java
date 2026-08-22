package com.google.mlkit.vision.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.mlkit_vision_common.zzp;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;
import java.util.List;

@KeepForSdk
/* loaded from: classes.dex */
public class VisionCommonRegistrar implements ComponentRegistrar {
    @Override // com.google.firebase.components.ComponentRegistrar
    public final List a() {
        return zzp.l(Component.a(MultiFlavorDetectorCreator.class).b(Dependency.i(MultiFlavorDetectorCreator.Registration.class)).d(new ComponentFactory() { // from class: com.google.mlkit.vision.common.internal.zzf
            @Override // com.google.firebase.components.ComponentFactory
            public final Object a(ComponentContainer componentContainer) {
                return new MultiFlavorDetectorCreator(componentContainer.c(MultiFlavorDetectorCreator.Registration.class));
            }
        }).c());
    }
}
