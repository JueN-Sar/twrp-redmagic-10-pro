package com.google.mlkit.vision.text.bundled.common;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zboc;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbom;

@KeepForSdk
@DynamiteApi
/* loaded from: classes.dex */
public class BundledTextRecognizerCreator extends zboc {
    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbod
    public zba newTextRecognizer(IObjectWrapper iObjectWrapper) {
        throw new RemoteException("Please use newTextRecognizerWithOptions instead.");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbod
    public zba newTextRecognizerWithOptions(IObjectWrapper iObjectWrapper, zbom zbomVar) {
        return new zba((Context) Preconditions.i((Context) ObjectWrapper.unwrap(iObjectWrapper)), zbomVar.G(), zbomVar.R(), zbomVar.P(), zbomVar.T());
    }
}
