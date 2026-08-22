package com.zte.gameassist.lowsugar.detect.scene.SGameGlobal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.util.ArrayMap;
import android.view.MotionEvent;
import cn.nubia.yolox.YOLOXncnn;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.lowsugar.R;
import com.zte.gameassist.lowsugar.detect.SceneConfig;
import com.zte.gameassist.lowsugar.detect.scene.IYoloXScene;
import com.zte.gameassist.lowsugar.utils.LowSugarUtils;
import com.zte.gameassist.utils.GaLog;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class SGameGlobal implements IYoloXScene {

    /* renamed from: b, reason: collision with root package name */
    private int f16899b;

    /* renamed from: c, reason: collision with root package name */
    private RectF f16900c;

    /* renamed from: d, reason: collision with root package name */
    private RectF f16901d;

    /* renamed from: e, reason: collision with root package name */
    private YOLOXncnn.Obj f16902e;

    /* renamed from: f, reason: collision with root package name */
    private RectF f16903f;

    public SGameGlobal(Context context) {
        Resources resources = context.getResources();
        this.f16900c = new RectF(LowSugarUtils.I, resources.getInteger(R.integer.low_sguar_sgame_global_events_startY), resources.getInteger(R.integer.low_sguar_sgame_global_events_endX) + LowSugarUtils.I, resources.getInteger(R.integer.low_sguar_sgame_global_events_endY));
        this.f16901d = new RectF(LowSugarUtils.I, resources.getInteger(R.integer.low_sguar_sgame_global_events_touch_startY), resources.getInteger(R.integer.low_sguar_sgame_global_events_touch_endX) + LowSugarUtils.I, Math.max(RotationMgr.f(), RotationMgr.g()));
        this.f16903f = new RectF(0.0f, 0.0f, LowSugarUtils.I, resources.getInteger(R.integer.low_sguar_sgame_global_events_touch_startY));
    }

    private boolean e(List list, Map map) {
        if (this.f16899b == 14) {
            GaLog.a("LowSugarGameplay.SGameGlobal", "isInEventsScene mSenceIndex is in SGAME_GLOBAL_EVENTS_SCENE_INDEX and return!");
            return true;
        }
        SceneConfig.GameFrameScene b2 = SceneConfig.c().b("sgame_global_events_scene");
        if (b2 == null) {
            GaLog.a("LowSugarGameplay.SGameGlobal", "isInEventsScene frameScene is null and return!");
            return false;
        }
        Iterator it = list.iterator();
        YOLOXncnn.Obj obj = null;
        YOLOXncnn.Obj obj2 = null;
        YOLOXncnn.Obj obj3 = null;
        YOLOXncnn.Obj obj4 = null;
        while (it.hasNext()) {
            SceneConfig.GameFrameScene.YOLOLabel yOLOLabel = (SceneConfig.GameFrameScene.YOLOLabel) it.next();
            if (("global_go_back_icon".equals(yOLOLabel.f16828a) && yOLOLabel.f16833f.prob > 0.8d) || (("global_operation_icon".equals(yOLOLabel.f16828a) && yOLOLabel.f16833f.prob > 0.8d) || (("global_gift_icon".equals(yOLOLabel.f16828a) && yOLOLabel.f16833f.prob > 0.8d) || ("global_telegramr_icon".equals(yOLOLabel.f16828a) && yOLOLabel.f16833f.prob > 0.8d)))) {
                GaLog.a("LowSugarGameplay.SGameGlobal", "isInEventsScene prob over 0.75 and label:" + yOLOLabel);
                if ("global_go_back_icon".equals(yOLOLabel.f16828a) && b2.c(yOLOLabel)) {
                    obj = yOLOLabel.f16833f;
                } else if ("global_operation_icon".equals(yOLOLabel.f16828a) && b2.c(yOLOLabel)) {
                    obj2 = yOLOLabel.f16833f;
                } else if ("global_gift_icon".equals(yOLOLabel.f16828a) && b2.c(yOLOLabel)) {
                    obj3 = yOLOLabel.f16833f;
                } else if ("global_telegramr_icon".equals(yOLOLabel.f16828a) && b2.c(yOLOLabel)) {
                    obj4 = yOLOLabel.f16833f;
                }
            }
        }
        if (obj == null || (obj2 == null && obj3 == null && obj4 == null)) {
            return false;
        }
        this.f16902e = obj;
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x005e, code lost:
    
        if (r9.f16833f.prob <= 0.8d) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean f(java.util.List r19, java.util.Map r20) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.gameassist.lowsugar.detect.scene.SGameGlobal.SGameGlobal.f(java.util.List, java.util.Map):boolean");
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.IYoloXScene
    public boolean b(int i2, MotionEvent motionEvent) {
        if (c(this.f16902e, motionEvent) || a(this.f16903f, motionEvent)) {
            return true;
        }
        return i2 == 14 && a(this.f16901d, motionEvent);
    }

    public int g(List list, Map map) {
        GaLog.a("LowSugarGameplay.SGameGlobal", "onDetect labels:" + list);
        if (list == null || list.size() == 0) {
            GaLog.a("LowSugarGameplay.SGameGlobal", "onDetect is in UNKNOWN_SCENE_INDEX in sgame!");
            return 0;
        }
        if (map == null) {
            map = new ArrayMap();
        }
        if (f(list, map)) {
            this.f16899b = 13;
            return 13;
        }
        if (e(list, map)) {
            this.f16899b = 14;
            return 14;
        }
        this.f16899b = 0;
        return 0;
    }

    public boolean h(int i2, MotionEvent motionEvent) {
        GaLog.a("LowSugarGameplay.SGameGlobal", "onPointerDown sceneIndex:" + i2 + ", mSenceIndex = " + this.f16899b);
        if (i2 == 13 || i2 == 0) {
            GaLog.a("LowSugarGameplay.SGameGlobal", "onPointerDown mMainSceneTouchEventsRect:" + this.f16900c);
            if (a(this.f16900c, motionEvent)) {
                return true;
            }
            if (i2 == 0 && (c(this.f16902e, motionEvent) || a(this.f16903f, motionEvent))) {
                this.f16899b = 0;
                return true;
            }
        } else if (c(this.f16902e, motionEvent) || a(this.f16903f, motionEvent)) {
            GaLog.a("LowSugarGameplay.SGameGlobal", "onPointerDown click back icon not in main should takeshot!");
            this.f16899b = 0;
            return true;
        }
        return false;
    }
}
