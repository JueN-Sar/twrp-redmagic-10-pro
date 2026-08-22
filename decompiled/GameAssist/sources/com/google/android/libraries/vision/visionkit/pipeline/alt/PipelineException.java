package com.google.android.libraries.vision.visionkit.pipeline.alt;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.apps.common.proguard.UsedByNative;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbki;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbko;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkx;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtp;
import com.google.android.libraries.vision.visionkit.pipeline.zbad;
import com.google.android.libraries.vision.visionkit.pipeline.zber;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Keep
@UsedByNative("pipeline_jni.cc")
/* loaded from: classes.dex */
public class PipelineException extends Exception {
    private static final String ROOT_CAUSE_DELIMITER = "#vk ";
    private final zbd statusCode;
    private final String statusMessage;

    @Nullable
    private final zber visionkitStatus;

    public PipelineException(int i2, @NonNull String str) {
        super(zbd.values()[i2].c() + ": " + str);
        this.statusCode = zbd.values()[i2];
        this.statusMessage = str;
        this.visionkitStatus = null;
    }

    @NonNull
    public List<zbad> getComponentStatuses() {
        zber zberVar = this.visionkitStatus;
        return zberVar != null ? zberVar.J() : zbkx.k();
    }

    public zbki<String> getRootCauseMessage() {
        Object next;
        Object obj;
        if (!this.statusMessage.contains(ROOT_CAUSE_DELIMITER)) {
            return zbki.d();
        }
        List b2 = zbko.a(ROOT_CAUSE_DELIMITER).b(this.statusMessage);
        if (!(b2 instanceof List)) {
            Iterator it = b2.iterator();
            do {
                next = it.next();
            } while (it.hasNext());
            obj = next;
        } else {
            if (b2.isEmpty()) {
                throw new NoSuchElementException();
            }
            obj = b2.get(b2.size() - 1);
        }
        return zbki.e((String) obj);
    }

    public zbd getStatusCode() {
        return this.statusCode;
    }

    @NonNull
    public String getStatusMessage() {
        return this.statusMessage;
    }

    private PipelineException(zber zberVar) {
        super(zbd.values()[zberVar.E()].c() + ": " + zberVar.I());
        this.statusCode = zbd.values()[zberVar.E()];
        this.statusMessage = zberVar.I();
        this.visionkitStatus = zberVar;
    }

    @Keep
    @UsedByNative("pipeline_jni.cc")
    PipelineException(byte[] bArr) {
        this(zber.H(bArr, zbtp.a()));
    }
}
