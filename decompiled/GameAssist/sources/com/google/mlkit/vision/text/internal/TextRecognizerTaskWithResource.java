package com.google.mlkit.vision.text.internal;

import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.internal.mlkit_vision_text_common.zzep;
import com.google.android.gms.internal.mlkit_vision_text_common.zzeq;
import com.google.android.gms.internal.mlkit_vision_text_common.zzes;
import com.google.android.gms.internal.mlkit_vision_text_common.zznw;
import com.google.android.gms.internal.mlkit_vision_text_common.zzoa;
import com.google.android.gms.internal.mlkit_vision_text_common.zzob;
import com.google.android.gms.internal.mlkit_vision_text_common.zzoh;
import com.google.android.gms.internal.mlkit_vision_text_common.zzot;
import com.google.android.gms.internal.mlkit_vision_text_common.zzou;
import com.google.android.gms.internal.mlkit_vision_text_common.zzov;
import com.google.android.gms.internal.mlkit_vision_text_common.zzow;
import com.google.android.gms.internal.mlkit_vision_text_common.zzrx;
import com.google.android.gms.internal.mlkit_vision_text_common.zzrz;
import com.google.android.gms.internal.mlkit_vision_text_common.zzsa;
import com.google.android.gms.internal.mlkit_vision_text_common.zztr;
import com.google.android.gms.internal.mlkit_vision_text_common.zzub;
import com.google.android.gms.internal.mlkit_vision_text_common.zzuc;
import com.google.android.gms.internal.mlkit_vision_text_common.zzue;
import com.google.android.gms.internal.mlkit_vision_text_common.zzuf;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.TaskQueue;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class TextRecognizerTaskWithResource extends MLTask<Text, InputImage> {

    @VisibleForTesting
    static boolean zza = true;

    /* renamed from: d, reason: collision with root package name */
    private final zzm f16111d;

    /* renamed from: e, reason: collision with root package name */
    private final zzuc f16112e;

    /* renamed from: f, reason: collision with root package name */
    private final zzue f16113f;

    /* renamed from: g, reason: collision with root package name */
    private final TextRecognizerOptionsInterface f16114g;

    /* renamed from: i, reason: collision with root package name */
    private static final ImageUtils f16110i = ImageUtils.b();

    /* renamed from: h, reason: collision with root package name */
    private static final TaskQueue f16109h = new TaskQueue();

    TextRecognizerTaskWithResource(zzuc zzucVar, zzm zzmVar, TextRecognizerOptionsInterface textRecognizerOptionsInterface) {
        super((textRecognizerOptionsInterface.h() == 8 || textRecognizerOptionsInterface.h() == 7) ? new TaskQueue() : f16109h);
        this.f16112e = zzucVar;
        this.f16111d = zzmVar;
        this.f16113f = zzue.a(MlKitContext.c().b());
        this.f16114g = textRecognizerOptionsInterface;
    }

    private final void l(final zzou zzouVar, long j2, final InputImage inputImage) {
        final long elapsedRealtime = SystemClock.elapsedRealtime() - j2;
        this.f16112e.e(new zzub() { // from class: com.google.mlkit.vision.text.internal.zzq
            @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzub
            public final zztr zza() {
                return TextRecognizerTaskWithResource.this.i(elapsedRealtime, zzouVar, inputImage);
            }
        }, zzov.ON_DEVICE_TEXT_DETECT);
        zzeq zzeqVar = new zzeq();
        zzeqVar.a(zzouVar);
        zzeqVar.b(Boolean.valueOf(zza));
        zzsa zzsaVar = new zzsa();
        zzsaVar.a(LoggingUtils.a(this.f16114g.h()));
        zzeqVar.c(zzsaVar.c());
        final zzes d2 = zzeqVar.d();
        final zzr zzrVar = new zzr(this);
        final zzov zzovVar = zzov.AGGREGATED_ON_DEVICE_TEXT_DETECTION;
        Executor e2 = MLTaskExecutor.e();
        final zzuc zzucVar = this.f16112e;
        e2.execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzua
            @Override // java.lang.Runnable
            public final void run() {
                zzuc.this.g(zzovVar, d2, elapsedRealtime, zzrVar);
            }
        });
        long currentTimeMillis = System.currentTimeMillis();
        this.f16113f.c(this.f16114g.d(), zzouVar.zza(), currentTimeMillis - elapsedRealtime, currentTimeMillis);
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final synchronized void c() {
        zza = true;
        this.f16111d.zzc();
    }

    final /* synthetic */ zztr i(long j2, zzou zzouVar, InputImage inputImage) {
        zzrx zzrxVar = new zzrx();
        zzoh zzohVar = new zzoh();
        zzohVar.c(Long.valueOf(j2));
        zzohVar.d(zzouVar);
        zzohVar.e(Boolean.valueOf(zza));
        Boolean bool = Boolean.TRUE;
        zzohVar.a(bool);
        zzohVar.b(bool);
        zzrxVar.d(zzohVar.f());
        ImageUtils imageUtils = f16110i;
        int c2 = imageUtils.c(inputImage);
        int d2 = imageUtils.d(inputImage);
        zzoa zzoaVar = new zzoa();
        zzoaVar.a(c2 != -1 ? c2 != 35 ? c2 != 842094169 ? c2 != 16 ? c2 != 17 ? zzob.UNKNOWN_FORMAT : zzob.NV21 : zzob.NV16 : zzob.YV12 : zzob.YUV_420_888 : zzob.BITMAP);
        zzoaVar.b(Integer.valueOf(d2));
        zzrxVar.c(zzoaVar.d());
        zzsa zzsaVar = new zzsa();
        zzsaVar.a(LoggingUtils.a(this.f16114g.h()));
        zzrxVar.e(zzsaVar.c());
        zzrz f2 = zzrxVar.f();
        zzow zzowVar = new zzow();
        zzowVar.e(this.f16114g.c() ? zzot.TYPE_THICK : zzot.TYPE_THIN);
        zzowVar.h(f2);
        return zzuf.d(zzowVar);
    }

    final /* synthetic */ zztr j(zzes zzesVar, int i2, zznw zznwVar) {
        zzow zzowVar = new zzow();
        zzowVar.e(this.f16114g.c() ? zzot.TYPE_THICK : zzot.TYPE_THIN);
        zzep zzepVar = new zzep();
        zzepVar.a(Integer.valueOf(i2));
        zzepVar.c(zzesVar);
        zzepVar.b(zznwVar);
        zzowVar.d(zzepVar.e());
        return zzuf.d(zzowVar);
    }

    @Override // com.google.mlkit.common.sdkinternal.MLTask
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public final synchronized Text h(InputImage inputImage) {
        Text a2;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        try {
            a2 = this.f16111d.a(inputImage);
            l(zzou.NO_ERROR, elapsedRealtime, inputImage);
            zza = false;
        } catch (MlKitException e2) {
            l(e2.a() == 14 ? zzou.MODEL_NOT_DOWNLOADED : zzou.UNKNOWN_ERROR, elapsedRealtime, inputImage);
            throw e2;
        }
        return a2;
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final synchronized void load() {
        this.f16111d.zzb();
    }
}
