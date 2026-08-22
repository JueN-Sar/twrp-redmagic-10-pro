package com.zte.gameassist.lowsugar.detect.scene.Genshin;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.util.ArrayMap;
import android.view.MotionEvent;
import cn.nubia.yolox.YOLOXncnn;
import com.zte.gameassist.lowsugar.R;
import com.zte.gameassist.lowsugar.detect.SceneConfig;
import com.zte.gameassist.lowsugar.detect.scene.IYoloXScene;
import com.zte.gameassist.utils.GaLog;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class Genshin implements IYoloXScene {

    /* renamed from: b, reason: collision with root package name */
    private YOLOXncnn.Obj f16852b;

    /* renamed from: c, reason: collision with root package name */
    private YOLOXncnn.Obj f16853c;

    /* renamed from: d, reason: collision with root package name */
    private YOLOXncnn.Obj f16854d;

    /* renamed from: e, reason: collision with root package name */
    private RectF f16855e;

    public Genshin(Context context) {
        Resources resources = context.getResources();
        this.f16855e = new RectF(resources.getInteger(R.integer.low_sguar_genshin_operation_wish_startX), 0.0f, resources.getInteger(R.integer.low_sguar_genshin_operation_wish_endX), resources.getInteger(R.integer.low_sguar_genshin_operation_wish_endY));
    }

    private boolean e(List list, Map map) {
        SceneConfig.GameFrameScene b2 = SceneConfig.c().b("genshin_main_scene");
        if (b2 == null) {
            return false;
        }
        Iterator it = list.iterator();
        YOLOXncnn.Obj obj = null;
        YOLOXncnn.Obj obj2 = null;
        while (it.hasNext()) {
            SceneConfig.GameFrameScene.YOLOLabel yOLOLabel = (SceneConfig.GameFrameScene.YOLOLabel) it.next();
            if (("operation_icon".equals(yOLOLabel.f16828a) && yOLOLabel.f16833f.prob > 0.85d) || ("wish_icon".equals(yOLOLabel.f16828a) && yOLOLabel.f16833f.prob > 0.65d)) {
                GaLog.a("LowSugarGameplay.Genshin", "isInMainScene obj:" + yOLOLabel);
                if ("operation_icon".equals(yOLOLabel.f16828a) && b2.c(yOLOLabel)) {
                    obj = yOLOLabel.f16833f;
                } else if ("wish_icon".equals(yOLOLabel.f16828a) && b2.c(yOLOLabel)) {
                    obj2 = yOLOLabel.f16833f;
                }
            }
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        this.f16853c = obj;
        this.f16854d = obj2;
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0045, code lost:
    
        if (r9.f16833f.prob <= 0.65d) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0059, code lost:
    
        if (r9.f16833f.prob > 0.65d) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x006f, code lost:
    
        if (r9.f16833f.prob > 0.85d) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0080, code lost:
    
        if (r9.f16833f.prob > 0.85d) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean f(java.util.List r19, java.util.Map r20) {
        /*
            Method dump skipped, instructions count: 292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.gameassist.lowsugar.detect.scene.Genshin.Genshin.f(java.util.List, java.util.Map):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0044, code lost:
    
        if (r8.f16833f.prob <= 0.85d) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x005d, code lost:
    
        if (r8.f16833f.prob > 0.55d) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x006e, code lost:
    
        if (r8.f16833f.prob > 0.85d) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x007f, code lost:
    
        if (r8.f16833f.prob > 0.85d) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean g(java.util.List r20, java.util.Map r21) {
        /*
            Method dump skipped, instructions count: 339
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.gameassist.lowsugar.detect.scene.Genshin.Genshin.g(java.util.List, java.util.Map):boolean");
    }

    public int h(List list, Map map) {
        if (list != null && list.size() != 0) {
            if (map == null) {
                map = new ArrayMap();
            }
            if (e(list, map)) {
                return 1;
            }
            if (f(list, map)) {
                return 2;
            }
            if (g(list, map)) {
                return 3;
            }
        }
        return 0;
    }

    public boolean i(int i2, MotionEvent motionEvent) {
        return (i2 == 0 || i2 == 1) ? c(this.f16853c, motionEvent) || c(this.f16854d, motionEvent) || a(this.f16855e, motionEvent) : (i2 == 2 || i2 == 3) && c(this.f16852b, motionEvent);
    }
}
