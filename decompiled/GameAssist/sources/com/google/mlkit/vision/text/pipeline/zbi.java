package com.google.mlkit.vision.text.pipeline;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbabj;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbbb;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbbe;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbiu;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbix;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbki;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbku;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkx;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zblc;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbnx;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zboe;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbog;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zboi;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbok;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zboo;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbpb;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbpf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbpg;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbpi;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbpk;
import com.google.android.libraries.vision.visionkit.pipeline.AndroidAssetUtil;
import com.google.android.libraries.vision.visionkit.pipeline.alt.PipelineException;
import com.google.android.libraries.vision.visionkit.pipeline.zbbz;
import com.google.android.libraries.vision.visionkit.pipeline.zbca;
import com.google.android.libraries.vision.visionkit.pipeline.zbct;
import com.google.android.libraries.vision.visionkit.pipeline.zbcv;
import com.google.android.libraries.vision.visionkit.pipeline.zbcw;
import com.google.android.libraries.vision.visionkit.pipeline.zbcz;
import com.google.android.libraries.vision.visionkit.pipeline.zbdl;
import com.google.android.libraries.vision.visionkit.pipeline.zbdo;
import com.google.android.libraries.vision.visionkit.pipeline.zbfb;
import com.google.android.libraries.vision.visionkit.pipeline.zbfc;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.common.internal.ImageConvertUtils;
import com.google.mlkit.vision.common.internal.ImageUtils;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@WorkerThread
/* loaded from: classes.dex */
public final class zbi {

    /* renamed from: a, reason: collision with root package name */
    private final Context f16162a;

    /* renamed from: b, reason: collision with root package name */
    private final VkpTextRecognizerOptions f16163b;

    /* renamed from: c, reason: collision with root package name */
    zbh f16164c;

    /* renamed from: d, reason: collision with root package name */
    boolean f16165d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f16166e = true;

    private zbi(Context context, VkpTextRecognizerOptions vkpTextRecognizerOptions) {
        this.f16162a = context;
        this.f16163b = vkpTextRecognizerOptions;
    }

    public static zbi a(Context context, VkpTextRecognizerOptions vkpTextRecognizerOptions) {
        return new zbi(context, vkpTextRecognizerOptions);
    }

    public final zbn b(IObjectWrapper iObjectWrapper, zbnx zbnxVar, boolean z) {
        zbki e2;
        zbku zbkuVar;
        zbku zbkuVar2;
        zbku zbkuVar3;
        zbo c2 = c();
        if (!c2.d()) {
            return zbn.e(c2);
        }
        try {
            int i2 = 3;
            int i3 = 1;
            if (zbnxVar.P() == -1) {
                Log.d("PipelineManager", "Start process bitmap");
                Bitmap bitmap = (Bitmap) Preconditions.i((Bitmap) ObjectWrapper.unwrap(iObjectWrapper));
                Bitmap.Config config = bitmap.getConfig();
                Bitmap.Config config2 = Bitmap.Config.ARGB_8888;
                if (config != config2) {
                    Log.d("PipelineManager", "Input bitmap is not ARGB_8888 config. Converting it to ARGB_8888 from " + String.valueOf(bitmap.getConfig()));
                    bitmap = bitmap.copy(config2, bitmap.isMutable());
                }
                e2 = ((zbh) Preconditions.i(this.f16164c)).i(SystemClock.elapsedRealtime() * 1000, bitmap, zbj.b(zbnxVar.R()));
            } else if (zbnxVar.P() == 35) {
                Log.d("PipelineManager", "Start process YUV");
                Image.Plane[] planes = ((Image) Preconditions.i(ObjectWrapper.unwrap(iObjectWrapper))).getPlanes();
                e2 = ((zbh) Preconditions.i(this.f16164c)).j(SystemClock.elapsedRealtime() * 1000, ((Image.Plane) Preconditions.i(planes[0])).getBuffer(), ((Image.Plane) Preconditions.i(planes[1])).getBuffer(), ((Image.Plane) Preconditions.i(planes[2])).getBuffer(), zbnxVar.T(), zbnxVar.G(), ((Image.Plane) Preconditions.i(planes[0])).getRowStride(), ((Image.Plane) Preconditions.i(planes[1])).getRowStride(), ((Image.Plane) Preconditions.i(planes[1])).getPixelStride(), zbj.b(zbnxVar.R()));
            } else if (zbnxVar.P() == 17) {
                Log.d("PipelineManager", "Start process NV21");
                e2 = ((zbh) Preconditions.i(this.f16164c)).e(zbj.a(ImageConvertUtils.a((ByteBuffer) Preconditions.i((ByteBuffer) ObjectWrapper.unwrap(iObjectWrapper))), zbnxVar));
            } else {
                if (zbnxVar.P() != 842094169) {
                    throw new MlKitException("Unsupported image format: " + zbnxVar.P(), 3);
                }
                Log.d("PipelineManager", "Start process YV12");
                e2 = ((zbh) Preconditions.i(this.f16164c)).e(zbj.a(ImageConvertUtils.i((ByteBuffer) Preconditions.i(ObjectWrapper.unwrap(iObjectWrapper)), true), zbnxVar));
            }
            if (!e2.c()) {
                return zbn.e(zbo.c(3, new RemoteException("VisionKit pipeline returns empty result.")));
            }
            Log.d("PipelineManager", "OCR process succeeded via visionkit pipeline.");
            zbcz zbczVar = (zbcz) e2.a();
            Matrix e3 = ImageUtils.b().e(zbnxVar.T(), zbnxVar.G(), zbnxVar.R());
            boolean z2 = this.f16166e;
            zbb zbbVar = new zbb(0, zbki.d());
            List<zbabj> I = zbczVar.J().I();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            for (zbabj zbabjVar : I) {
                if (zbabjVar.H() == 6) {
                    zbpb b2 = zbf.b(zbabjVar.K());
                    List c3 = zbf.c(b2);
                    zboo zbooVar = new zboo(zbabjVar.F(), zbf.a(c3, e3), c3, zbabjVar.I(), b2.E());
                    Integer valueOf = Integer.valueOf(zbabjVar.J());
                    if (hashMap2.containsKey(valueOf)) {
                        zbkuVar3 = (zbku) hashMap2.get(valueOf);
                    } else {
                        zbku zbkuVar4 = new zbku();
                        hashMap2.put(valueOf, zbkuVar4);
                        zbkuVar3 = zbkuVar4;
                    }
                    ((zbku) Preconditions.i(zbkuVar3)).a(zbooVar);
                }
            }
            int i4 = 0;
            while (i4 < I.size()) {
                zbabj zbabjVar2 = (zbabj) I.get(i4);
                if (zbabjVar2.H() == i3) {
                    zbpb b3 = zbf.b(zbabjVar2.K());
                    List c4 = zbf.c(b3);
                    Integer valueOf2 = Integer.valueOf(i4);
                    zbog zbogVar = new zbog(zbabjVar2.F(), zbf.a(c4, e3), c4, zbg.a(zbabjVar2.L().H()), zbabjVar2.I(), b3.E(), (List) Preconditions.i(hashMap2.containsKey(valueOf2) ? ((zbku) Preconditions.i((zbku) hashMap2.get(valueOf2))).b() : zbkx.k()));
                    Integer valueOf3 = Integer.valueOf(zbabjVar2.J());
                    if (hashMap.containsKey(valueOf3)) {
                        zbkuVar2 = (zbku) hashMap.get(valueOf3);
                    } else {
                        zbku zbkuVar5 = new zbku();
                        hashMap.put(valueOf3, zbkuVar5);
                        zbkuVar2 = zbkuVar5;
                    }
                    ((zbku) Preconditions.i(zbkuVar2)).a(zbogVar);
                }
                i4++;
                i3 = 1;
            }
            int i5 = 0;
            while (i5 < I.size()) {
                zbabj zbabjVar3 = (zbabj) I.get(i5);
                if (zbabjVar3.H() == i2) {
                    zbpb b4 = zbf.b(zbabjVar3.K());
                    List c5 = zbf.c(b4);
                    Integer valueOf4 = Integer.valueOf(i5);
                    zboi zboiVar = new zboi(zbabjVar3.F(), zbf.a(c5, e3), c5, zbg.a(zbabjVar3.L().H()), (List) Preconditions.i(hashMap.containsKey(valueOf4) ? ((zbku) Preconditions.i((zbku) hashMap.get(valueOf4))).b() : zbkx.k()), zbabjVar3.I(), b4.E());
                    Integer valueOf5 = Integer.valueOf(zbabjVar3.J());
                    if (hashMap3.containsKey(valueOf5)) {
                        zbkuVar = (zbku) hashMap3.get(valueOf5);
                    } else {
                        zbku zbkuVar6 = new zbku();
                        hashMap3.put(Integer.valueOf(zbabjVar3.J()), zbkuVar6);
                        zbkuVar = zbkuVar6;
                    }
                    ((zbku) Preconditions.i(zbkuVar)).a(zboiVar);
                }
                i5++;
                i2 = 3;
            }
            zbku zbkuVar7 = new zbku();
            for (int i6 = 0; i6 < I.size(); i6++) {
                zbabj zbabjVar4 = (zbabj) I.get(i6);
                if (zbabjVar4.H() == 4) {
                    List c6 = zbf.c(zbf.b(zbabjVar4.K()));
                    zbkx k2 = zbkx.k();
                    Integer valueOf6 = Integer.valueOf(i6);
                    if (hashMap3.containsKey(valueOf6)) {
                        k2 = ((zbku) Preconditions.i((zbku) hashMap3.get(valueOf6))).b();
                        hashMap3.remove(valueOf6);
                    }
                    zbkuVar7.a(new zboe(zbm.zba.b(zblc.a(k2, new zbkf() { // from class: com.google.mlkit.vision.text.pipeline.zbk
                        @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkf
                        public final Object a(Object obj) {
                            return ((zboi) obj).R();
                        }
                    })), zbf.a(c6, e3), c6, zbg.a(zbabjVar4.L().H()), (List) Preconditions.i(k2)));
                }
            }
            Iterator it = hashMap3.values().iterator();
            while (it.hasNext()) {
                zbkx b5 = ((zbku) it.next()).b();
                int size = b5.size();
                int i7 = 0;
                while (i7 < size) {
                    zboi zboiVar2 = (zboi) b5.get(i7);
                    zbkuVar7.a(new zboe(zboiVar2.R(), zboiVar2.G(), zboiVar2.T(), zboiVar2.P(), zbkx.l(zboiVar2)));
                    i7++;
                    it = it;
                }
            }
            zbkx b6 = zbkuVar7.b();
            zba zbaVar = new zba(zbbVar, new zbok(zbm.zba.b(zblc.a(b6, new zbkf() { // from class: com.google.mlkit.vision.text.pipeline.zbl
                @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkf
                public final Object a(Object obj) {
                    return ((zboe) obj).G();
                }
            })), b6), zbkx.k(), z2);
            this.f16166e = false;
            return zbaVar;
        } catch (MlKitException e4) {
            return zbn.e(zbo.c(2, new RemoteException("Failed to process input image.".concat(String.valueOf(e4.getMessage())))));
        }
    }

    public final zbo c() {
        if (this.f16165d) {
            return new zbb(0, zbki.d());
        }
        if (this.f16164c == null) {
            if (!AndroidAssetUtil.a(this.f16162a)) {
                Log.d("PipelineManager", "Failed to initiate native asset manager.");
            }
            VkpTextRecognizerOptions vkpTextRecognizerOptions = this.f16163b;
            String b2 = vkpTextRecognizerOptions.b();
            String d2 = vkpTextRecognizerOptions.d();
            String c2 = vkpTextRecognizerOptions.c();
            boolean e2 = vkpTextRecognizerOptions.e();
            zbbz F = zbca.F();
            int i2 = e2 ? 4 : 0;
            zbdl F2 = zbdo.F();
            zbbb E = zbbe.E();
            E.s(d2);
            E.p(b2);
            E.t(true);
            E.q(true);
            if (!c2.isEmpty()) {
                zbpf E2 = zbpg.E();
                zbpi E3 = zbpk.E();
                E3.p(c2);
                E2.p(E3);
                E.r(E2);
            }
            F2.r(E);
            int a2 = zbcv.a(i2);
            zbct E4 = zbcw.E();
            E4.p(a2);
            F2.s(E4);
            zbiu E5 = zbix.E();
            E5.p("PassThroughCoarseClassifier");
            F2.q(E5);
            F.p(F2);
            zbfb E6 = zbfc.E();
            E6.p(2);
            F.q(E6);
            this.f16164c = new zbh((zbca) F.d(), this.f16163b.b(), "mlkit_google_ocr_pipeline");
        }
        try {
            ((zbh) Preconditions.i(this.f16164c)).g();
            this.f16165d = true;
            return new zbb(0, zbki.d());
        } catch (PipelineException e3) {
            return zbo.c(1, new RemoteException("Failed to initialize detector. ".concat((String) e3.getRootCauseMessage().b(""))));
        }
    }

    public final void d() {
        zbh zbhVar = this.f16164c;
        if (zbhVar != null) {
            if (this.f16165d) {
                zbhVar.h();
            }
            this.f16164c.f();
            this.f16164c = null;
        }
        this.f16165d = false;
        this.f16166e = true;
    }
}
