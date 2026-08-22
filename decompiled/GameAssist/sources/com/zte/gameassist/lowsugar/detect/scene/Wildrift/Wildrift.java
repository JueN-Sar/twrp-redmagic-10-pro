package com.zte.gameassist.lowsugar.detect.scene.Wildrift;

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
public class Wildrift implements IYoloXScene {

    /* renamed from: b, reason: collision with root package name */
    private int f16910b;

    /* renamed from: c, reason: collision with root package name */
    private RectF f16911c;

    /* renamed from: d, reason: collision with root package name */
    private RectF f16912d;

    /* renamed from: e, reason: collision with root package name */
    private RectF f16913e;

    public Wildrift(Context context) {
        Resources resources = context.getResources();
        this.f16911c = new RectF(LowSugarUtils.I, resources.getInteger(R.integer.low_sguar_wildrift_events_startY), resources.getInteger(R.integer.low_sguar_wildrift_events_endX) + LowSugarUtils.I, resources.getInteger(R.integer.low_sguar_wildrift_events_endY));
        this.f16912d = new RectF(LowSugarUtils.I, resources.getInteger(R.integer.low_sguar_wildrift_events_touch_startY), resources.getInteger(R.integer.low_sguar_wildrift_events_touch_endX) + LowSugarUtils.I, Math.max(RotationMgr.f(), RotationMgr.g()));
        this.f16913e = new RectF(0.0f, 0.0f, resources.getInteger(R.integer.low_sguar_wildrift_goback_area_touch_endX) + LowSugarUtils.I, resources.getInteger(R.integer.low_sguar_wildrift_goback_area_touch_endY));
    }

    private boolean e(List list, Map map) {
        if (this.f16910b == 9) {
            GaLog.a("LowSugarGameplay.Wildrift", "isInEventsScene mSenceIndex is in WILDRIFT_EVENTS_SCENE_INDEX and return!");
            return true;
        }
        SceneConfig.GameFrameScene b2 = SceneConfig.c().b("wildrift_events_scene");
        if (b2 == null) {
            GaLog.a("LowSugarGameplay.Wildrift", "isInEventsScene frameScene is null and return!");
            return false;
        }
        Iterator it = list.iterator();
        YOLOXncnn.Obj obj = null;
        YOLOXncnn.Obj obj2 = null;
        while (it.hasNext()) {
            SceneConfig.GameFrameScene.YOLOLabel yOLOLabel = (SceneConfig.GameFrameScene.YOLOLabel) it.next();
            if (("34_go_back_icon".equals(yOLOLabel.f16828a) && yOLOLabel.f16833f.prob > 0.8d) || ("reminder_icon".equals(yOLOLabel.f16828a) && yOLOLabel.f16833f.prob > 0.6d)) {
                GaLog.a("LowSugarGameplay.Wildrift", "isInEventsScene prob over 0.75 and label:" + yOLOLabel);
                if ("34_go_back_icon".equals(yOLOLabel.f16828a) && b2.c(yOLOLabel)) {
                    obj = yOLOLabel.f16833f;
                }
                if ("reminder_icon".equals(yOLOLabel.f16828a) && b2.c(yOLOLabel)) {
                    obj2 = yOLOLabel.f16833f;
                }
            }
        }
        return (obj == null || obj2 == null) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0047, code lost:
    
        if (r8.f16833f.prob <= 0.8d) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean f(java.util.List r17, java.util.Map r18) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.gameassist.lowsugar.detect.scene.Wildrift.Wildrift.f(java.util.List, java.util.Map):boolean");
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.IYoloXScene
    public boolean b(int i2, MotionEvent motionEvent) {
        if (a(this.f16913e, motionEvent)) {
            return true;
        }
        return i2 == 9 && a(this.f16912d, motionEvent);
    }

    public int g(List list, Map map) {
        GaLog.a("LowSugarGameplay.Wildrift", "onDetect labels:" + list);
        if (list == null || list.size() == 0) {
            GaLog.a("LowSugarGameplay.Wildrift", "onDetect is in UNKNOWN_SCENE_INDEX in sgame!");
            return 0;
        }
        if (map == null) {
            map = new ArrayMap();
        }
        if (f(list, map)) {
            this.f16910b = 8;
            return 8;
        }
        if (e(list, map)) {
            this.f16910b = 9;
            return 9;
        }
        this.f16910b = 0;
        return 0;
    }

    public boolean h(int i2, MotionEvent motionEvent) {
        GaLog.a("LowSugarGameplay.Wildrift", "onPointerDown sceneIndex:" + i2 + ", mSenceIndex = " + this.f16910b);
        if (i2 == 8 || i2 == 0) {
            GaLog.a("LowSugarGameplay.Wildrift", "onPointerDown mMainSceneTouchEventsRect:" + this.f16911c);
            if (a(this.f16911c, motionEvent)) {
                return true;
            }
            if (i2 == 0 && a(this.f16913e, motionEvent)) {
                this.f16910b = 0;
                return true;
            }
        } else if (a(this.f16913e, motionEvent)) {
            GaLog.a("LowSugarGameplay.Wildrift", "onPointerDown click back icon not in main should takeshot!");
            this.f16910b = 0;
            return true;
        }
        return false;
    }
}
