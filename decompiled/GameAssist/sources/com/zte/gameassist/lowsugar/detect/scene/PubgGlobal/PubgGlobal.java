package com.zte.gameassist.lowsugar.detect.scene.PubgGlobal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.util.ArrayMap;
import android.view.MotionEvent;
import cn.nubia.yolox.YOLOXncnn;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.lowsugar.R;
import com.zte.gameassist.lowsugar.detect.scene.IYoloXScene;
import com.zte.gameassist.lowsugar.utils.LowSugarUtils;
import com.zte.gameassist.utils.GaLog;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class PubgGlobal implements IYoloXScene {

    /* renamed from: b, reason: collision with root package name */
    private int f16862b;

    /* renamed from: c, reason: collision with root package name */
    private RectF f16863c;

    /* renamed from: d, reason: collision with root package name */
    private RectF f16864d;

    /* renamed from: e, reason: collision with root package name */
    private YOLOXncnn.Obj f16865e;

    /* renamed from: f, reason: collision with root package name */
    private YOLOXncnn.Obj f16866f;

    public PubgGlobal(Context context) {
        Resources resources = context.getResources();
        this.f16863c = new RectF(LowSugarUtils.I + resources.getInteger(R.integer.low_sguar_pubg_events_startX), resources.getInteger(R.integer.low_sguar_pubg_events_startY), LowSugarUtils.I + resources.getInteger(R.integer.low_sguar_pubg_events_endX), resources.getInteger(R.integer.low_sguar_pubg_events_endY));
        this.f16864d = new RectF(LowSugarUtils.I + resources.getInteger(R.integer.low_sguar_pubg_events_touch_startX), 0.0f, Math.max(RotationMgr.f(), RotationMgr.g()), Math.max(RotationMgr.f(), RotationMgr.g()));
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0067, code lost:
    
        if (r13.f16833f.prob <= 0.8d) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean e(java.util.List r23, java.util.Map r24) {
        /*
            Method dump skipped, instructions count: 434
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.gameassist.lowsugar.detect.scene.PubgGlobal.PubgGlobal.e(java.util.List, java.util.Map):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x005b, code lost:
    
        if (r8.f16833f.prob <= 0.8d) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean f(java.util.List r20, java.util.Map r21) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.gameassist.lowsugar.detect.scene.PubgGlobal.PubgGlobal.f(java.util.List, java.util.Map):boolean");
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.IYoloXScene
    public boolean b(int i2, MotionEvent motionEvent) {
        GaLog.a("LowSugarGameplay.PubgGlobal", "needDetectPointerDown sceneIndex = " + i2 + ", event = " + motionEvent);
        if (c(this.f16865e, motionEvent)) {
            return true;
        }
        if (i2 != 17) {
            return false;
        }
        GaLog.a("LowSugarGameplay.PubgGlobal", "needDetectPointerDown mEventsTouchRect = " + this.f16864d);
        return a(this.f16864d, motionEvent);
    }

    public int g(List list, Map map) {
        GaLog.a("LowSugarGameplay.PubgGlobal", "onDetect labels:" + list);
        if (list == null || list.size() == 0) {
            GaLog.a("LowSugarGameplay.PubgGlobal", "onDetect is in UNKNOWN_SCENE_INDEX in sgame!");
            return 0;
        }
        if (map == null) {
            map = new ArrayMap();
        }
        if (f(list, map)) {
            this.f16862b = 16;
            return 16;
        }
        if (e(list, map)) {
            this.f16862b = 17;
            return 17;
        }
        this.f16862b = 0;
        return 0;
    }

    public boolean h(int i2, MotionEvent motionEvent) {
        GaLog.a("LowSugarGameplay.PubgGlobal", "onPointerDown sceneIndex:" + i2 + ", mSenceIndex = " + this.f16862b);
        if (i2 == 16 || i2 == 0) {
            GaLog.a("LowSugarGameplay.PubgGlobal", "onPointerDown mMainSceneTouchEventsRect:" + this.f16863c);
            YOLOXncnn.Obj obj = this.f16866f;
            if (obj != null && c(obj, motionEvent)) {
                return true;
            }
            if (this.f16866f == null && a(this.f16863c, motionEvent)) {
                return true;
            }
            if (i2 == 0 && c(this.f16865e, motionEvent)) {
                this.f16862b = 0;
                return true;
            }
        } else if (c(this.f16865e, motionEvent)) {
            GaLog.a("LowSugarGameplay.PubgGlobal", "onPointerDown click back icon not in main should takeshot!");
            this.f16862b = 0;
            return true;
        }
        return false;
    }
}
