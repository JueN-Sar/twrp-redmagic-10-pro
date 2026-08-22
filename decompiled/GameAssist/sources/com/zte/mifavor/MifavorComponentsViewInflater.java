package com.zte.mifavor;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import com.google.android.material.theme.MaterialComponentsViewInflater;

@Keep
/* loaded from: classes2.dex */
public class MifavorComponentsViewInflater extends MaterialComponentsViewInflater {
    @Override // com.google.android.material.theme.MaterialComponentsViewInflater, androidx.appcompat.app.AppCompatViewInflater
    @NonNull
    protected AppCompatButton createButton(Context context, AttributeSet attributeSet) {
        return new AppCompatButton(context, attributeSet);
    }
}
