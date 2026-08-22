package com.google.mlkit.vision.text.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;

@KeepForSdk
/* loaded from: classes.dex */
public final class TextOptionalModuleUtils {
    static Feature[] a(TextRecognizerOptionsInterface textRecognizerOptionsInterface) {
        if (textRecognizerOptionsInterface.c()) {
            return OptionalModuleUtils.f15951a;
        }
        switch (textRecognizerOptionsInterface.h()) {
            case 2:
                return new Feature[]{OptionalModuleUtils.f15957g};
            case 3:
                return new Feature[]{OptionalModuleUtils.f15959i};
            case 4:
                return new Feature[]{OptionalModuleUtils.f15960j};
            case 5:
                return new Feature[]{OptionalModuleUtils.f15961k};
            case 6:
            case 7:
            case 8:
                return new Feature[]{OptionalModuleUtils.f15958h};
            default:
                return new Feature[]{OptionalModuleUtils.f15956f};
        }
    }
}
