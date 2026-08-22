package com.google.mlkit.vision.text.bundled.common;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbnx;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbnz;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbok;
import com.google.mlkit.vision.text.pipeline.VkpTextRecognizerOptions;
import com.google.mlkit.vision.text.pipeline.zbi;
import com.google.mlkit.vision.text.pipeline.zbn;
import com.google.mlkit.vision.text.pipeline.zbo;

/* loaded from: classes.dex */
final class zba extends zbnz {
    private final Context zba;
    private final String zbb;
    private final boolean zbc;

    @Nullable
    private final String zbd;

    @Nullable
    private final String zbe;

    @Nullable
    private zbi zbf;

    zba(Context context, String str, @Nullable String str2, @Nullable String str3, boolean z) {
        this.zba = context;
        this.zbb = str;
        this.zbd = str2;
        this.zbe = str3;
        this.zbc = z;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zboa
    public final zbok zbb(IObjectWrapper iObjectWrapper, zbnx zbnxVar) {
        zbi zbiVar = this.zbf;
        if (zbiVar == null) {
            throw new RemoteException("Process is started without initiation.");
        }
        zbn b2 = ((zbi) Preconditions.i(zbiVar)).b(iObjectWrapper, zbnxVar, true);
        zbo c2 = b2.c();
        if (c2.d()) {
            return b2.b();
        }
        throw ((RemoteException) c2.b().a());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zboa
    public final void zbc() {
        if (this.zbf == null) {
            System.loadLibrary("mlkit_google_ocr_pipeline");
            String str = this.zbe;
            String str2 = (str == null || str.isEmpty()) ? "" : this.zbe;
            String str3 = this.zbb;
            String str4 = this.zbd;
            boolean z = this.zbc;
            VkpTextRecognizerOptions.Builder a2 = VkpTextRecognizerOptions.a(str3, str4, str2);
            a2.b(z);
            zbi a3 = zbi.a(this.zba, a2.a());
            this.zbf = a3;
            zbo c2 = a3.c();
            if (!c2.d()) {
                throw ((RemoteException) c2.b().a());
            }
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zboa
    public final void zbd() {
        zbi zbiVar = this.zbf;
        if (zbiVar != null) {
            zbiVar.d();
            this.zbf = null;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zboa
    public final zbf[] zbe(IObjectWrapper iObjectWrapper, zbnx zbnxVar) {
        throw new RemoteException("#recognizeBitmap should not be triggered from text thick client.");
    }
}
