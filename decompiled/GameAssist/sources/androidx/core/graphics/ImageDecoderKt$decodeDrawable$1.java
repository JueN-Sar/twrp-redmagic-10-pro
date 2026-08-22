package androidx.core.graphics;

import android.graphics.ImageDecoder;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class ImageDecoderKt$decodeDrawable$1 implements ImageDecoder.OnHeaderDecodedListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Function3 f2918a;

    @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        this.f2918a.u(imageDecoder, imageInfo, source);
    }
}
