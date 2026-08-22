package com.zte.gameassist.lowsugar.detect.scene.SGame;

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
import com.zte.gameassist.utils.GaLog;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class SGame implements IYoloXScene {

    /* renamed from: l, reason: collision with root package name */
    private static YOLOXncnn.Obj f16872l;

    /* renamed from: b, reason: collision with root package name */
    private YOLOXncnn.Obj f16873b;

    /* renamed from: c, reason: collision with root package name */
    private int f16874c;

    /* renamed from: d, reason: collision with root package name */
    private RectF f16875d;

    /* renamed from: e, reason: collision with root package name */
    private RectF f16876e;

    /* renamed from: f, reason: collision with root package name */
    private RectF f16877f;

    /* renamed from: g, reason: collision with root package name */
    private RectF f16878g;

    /* renamed from: h, reason: collision with root package name */
    private RectF f16879h;

    /* renamed from: i, reason: collision with root package name */
    private RectF f16880i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f16881j;

    /* renamed from: k, reason: collision with root package name */
    private boolean f16882k = false;

    public SGame(Context context) {
        Resources resources = context.getResources();
        this.f16875d = new RectF(resources.getInteger(R.integer.low_sguar_sgame_events_startX), resources.getInteger(R.integer.low_sguar_sgame_events_startY), resources.getInteger(R.integer.low_sguar_sgame_events_endX), resources.getInteger(R.integer.low_sguar_sgame_events_endY));
        this.f16876e = new RectF(resources.getInteger(R.integer.low_sguar_sgame_wish_startX), resources.getInteger(R.integer.low_sguar_sgame_wish_startY), resources.getInteger(R.integer.low_sguar_sgame_wish_endX), resources.getInteger(R.integer.low_sguar_sgame_wish_endY));
        this.f16877f = new RectF(resources.getInteger(R.integer.low_sguar_sgame_events_startX), resources.getInteger(R.integer.low_sguar_sgame_events_without_wish_startY), resources.getInteger(R.integer.low_sguar_sgame_events_endX), resources.getInteger(R.integer.low_sguar_sgame_events_without_wish_endY));
        this.f16878g = new RectF(resources.getInteger(R.integer.low_sguar_sgame_events_touch_startX), 0.0f, resources.getInteger(R.integer.low_sguar_sgame_events_touch_endX), Math.max(RotationMgr.f(), RotationMgr.g()));
        this.f16879h = new RectF(resources.getInteger(R.integer.low_sguar_sgame_wish_touch_startX), 0.0f, resources.getInteger(R.integer.low_sguar_sgame_wish_touch_endX), Math.max(RotationMgr.f(), RotationMgr.g()));
        this.f16880i = new RectF(0.0f, 0.0f, resources.getInteger(R.integer.low_sguar_sgame_goback_area_touch_endX), resources.getInteger(R.integer.low_sguar_sgame_goback_area_touch_endY));
    }

    private boolean f(List list, Map map) {
        if (this.f16874c != 5) {
            GaLog.a("LowSugarGameplay.SGame", "isInEventsScene mSenceIndex is not SGAME_EVENTS_SCENE_INDEX and return!");
            return false;
        }
        SceneConfig.GameFrameScene b2 = SceneConfig.c().b("sgame_events_scene");
        if (b2 == null) {
            GaLog.a("LowSugarGameplay.SGame", "isInEventsScene frameScene is null and return!");
            return false;
        }
        Iterator it = list.iterator();
        YOLOXncnn.Obj obj = null;
        while (it.hasNext()) {
            SceneConfig.GameFrameScene.YOLOLabel yOLOLabel = (SceneConfig.GameFrameScene.YOLOLabel) it.next();
            if (("go_back_icon".equals(yOLOLabel.f16828a) && yOLOLabel.f16833f.prob > 0.7d) || ("global_go_back_icon".equals(yOLOLabel.f16828a) && yOLOLabel.f16833f.prob > 0.65d)) {
                GaLog.a("LowSugarGameplay.SGame", "isInEventsScene prob over 0.60 and label:" + yOLOLabel);
                if (("go_back_icon".equals(yOLOLabel.f16828a) && b2.c(yOLOLabel)) || ("global_go_back_icon".equals(yOLOLabel.f16828a) && b2.c(yOLOLabel))) {
                    obj = yOLOLabel.f16833f;
                }
            }
        }
        if (obj == null) {
            return false;
        }
        this.f16873b = obj;
        f16872l = obj;
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0065, code lost:
    
        if (r13.f16833f.prob <= 0.7d) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean g(java.util.List r28, java.util.Map r29) {
        /*
            Method dump skipped, instructions count: 626
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.gameassist.lowsugar.detect.scene.SGame.SGame.g(java.util.List, java.util.Map):boolean");
    }

    private boolean h(List list, Map map) {
        SceneConfig.GameFrameScene b2 = SceneConfig.c().b("sgame_other_has_goback_scene");
        if (b2 == null) {
            GaLog.a("LowSugarGameplay.SGame", "isInOtherHasGobackScene frameScene is null and return!");
            return false;
        }
        Iterator it = list.iterator();
        YOLOXncnn.Obj obj = null;
        while (it.hasNext()) {
            SceneConfig.GameFrameScene.YOLOLabel yOLOLabel = (SceneConfig.GameFrameScene.YOLOLabel) it.next();
            if (("go_back_icon".equals(yOLOLabel.f16828a) && yOLOLabel.f16833f.prob > 0.7d) || ("global_go_back_icon".equals(yOLOLabel.f16828a) && yOLOLabel.f16833f.prob > 0.65d)) {
                GaLog.a("LowSugarGameplay.SGame", "isInOtherHasGobackScene prob over 0.60 and label:" + yOLOLabel);
                if (("go_back_icon".equals(yOLOLabel.f16828a) && b2.c(yOLOLabel)) || ("global_go_back_icon".equals(yOLOLabel.f16828a) && b2.c(yOLOLabel))) {
                    obj = yOLOLabel.f16833f;
                }
            }
        }
        if (obj == null) {
            return false;
        }
        this.f16873b = obj;
        f16872l = obj;
        return true;
    }

    private boolean i(List list, Map map) {
        if (this.f16874c != 6) {
            GaLog.a("LowSugarGameplay.SGame", "isInWishScene mSenceIndex is not SGAME_WISH_SCENE_INDEX and return!");
            return false;
        }
        SceneConfig.GameFrameScene b2 = SceneConfig.c().b("sgame_wish_scene");
        if (b2 == null) {
            GaLog.a("LowSugarGameplay.SGame", "isInWishScene frameScene is null and return!");
            return false;
        }
        Iterator it = list.iterator();
        YOLOXncnn.Obj obj = null;
        while (it.hasNext()) {
            SceneConfig.GameFrameScene.YOLOLabel yOLOLabel = (SceneConfig.GameFrameScene.YOLOLabel) it.next();
            if (("go_back_icon".equals(yOLOLabel.f16828a) && yOLOLabel.f16833f.prob > 0.7d) || ("global_go_back_icon".equals(yOLOLabel.f16828a) && yOLOLabel.f16833f.prob > 0.65d)) {
                GaLog.a("LowSugarGameplay.SGame", "isInWishScene prob over 0.60 and label:" + yOLOLabel);
                if (("go_back_icon".equals(yOLOLabel.f16828a) && b2.c(yOLOLabel)) || ("global_go_back_icon".equals(yOLOLabel.f16828a) && b2.c(yOLOLabel))) {
                    obj = yOLOLabel.f16833f;
                }
            }
        }
        if (obj == null) {
            return false;
        }
        this.f16873b = obj;
        f16872l = obj;
        return true;
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.IYoloXScene
    public boolean b(int i2, MotionEvent motionEvent) {
        if (a(this.f16880i, motionEvent)) {
            return true;
        }
        if (i2 == 5) {
            if (!c(this.f16873b, motionEvent)) {
                return a(this.f16878g, motionEvent);
            }
            this.f16881j = true;
            return true;
        }
        if (i2 != 6) {
            return false;
        }
        if (!c(this.f16873b, motionEvent)) {
            return a(this.f16879h, motionEvent);
        }
        this.f16881j = true;
        return true;
    }

    public int e() {
        return this.f16874c;
    }

    public boolean j() {
        boolean z = this.f16881j;
        this.f16881j = false;
        return z;
    }

    public int k(List list, Map map) {
        GaLog.a("LowSugarGameplay.SGame", "onDetect labels:" + list);
        if (list == null || list.size() == 0) {
            GaLog.a("LowSugarGameplay.SGame", "onDetect is in UNKNOWN_SCENE_INDEX in sgame!");
            return 0;
        }
        if (map == null) {
            map = new ArrayMap();
        }
        if (g(list, map)) {
            this.f16874c = 4;
            return 4;
        }
        if (f(list, map)) {
            return 5;
        }
        if (i(list, map)) {
            return 6;
        }
        if (h(list, map)) {
            this.f16874c = 7;
            return 7;
        }
        this.f16874c = 0;
        return 0;
    }

    public boolean l(int i2, MotionEvent motionEvent) {
        GaLog.a("LowSugarGameplay.SGame", "onPointerDown sceneIndex:" + i2 + ", mSenceIndex = " + this.f16874c);
        if (i2 == 4) {
            GaLog.a("LowSugarGameplay.SGame", "onPointerDown mMainSceneTouchEventsRect:" + this.f16875d + ", mMainSceneTouchWishRect = " + this.f16876e + ", mMainSceneTouchEventsWithoutWishRect = " + this.f16877f + ", mHasWish = " + this.f16882k);
            int i3 = this.f16874c;
            if (i3 != 5 && i3 != 6 && i3 != 7) {
                if (!this.f16882k) {
                    if (a(this.f16877f, motionEvent)) {
                        this.f16874c = 5;
                        return true;
                    }
                    this.f16874c = 7;
                    return true;
                }
                if (a(this.f16875d, motionEvent)) {
                    this.f16874c = 5;
                    return true;
                }
                if (a(this.f16876e, motionEvent)) {
                    this.f16874c = 6;
                    return true;
                }
                this.f16874c = 7;
                return true;
            }
        } else if ((i2 == 5 || i2 == 6 || i2 == 7) && c(this.f16873b, motionEvent)) {
            this.f16881j = true;
            this.f16874c = 0;
            return true;
        }
        int i4 = this.f16874c;
        if (i4 == 7 || i4 == 0) {
            YOLOXncnn.Obj obj = this.f16873b;
            if (obj == null) {
                obj = f16872l;
            }
            if (c(obj, motionEvent)) {
                GaLog.a("LowSugarGameplay.SGame", "onPointerDown click back icon in SGAME_OTHER_HAS_GOBACK_SCENE_INDEX should takeshot!");
                this.f16881j = true;
                this.f16874c = 0;
                return true;
            }
        }
        if (!a(this.f16880i, motionEvent)) {
            return false;
        }
        GaLog.a("LowSugarGameplay.SGame", "onPointerDown click back icon not in main should takeshot!");
        this.f16874c = 0;
        return true;
    }
}
