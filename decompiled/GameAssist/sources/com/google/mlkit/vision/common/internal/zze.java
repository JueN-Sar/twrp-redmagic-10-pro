package com.google.mlkit.vision.common.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.odml.image.MlImage;

/* loaded from: classes.dex */
public final /* synthetic */ class zze implements OnCompleteListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ MlImage f16084a;

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void a(Task task) {
        MlImage mlImage = this.f16084a;
        int i2 = MobileVisionBase.f16063m;
        mlImage.close();
    }
}
