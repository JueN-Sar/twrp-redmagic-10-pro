package com.zte.aivibrate.processor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import cn.nubia.yolox.SkillReleaseModel;
import cn.nubia.yolox.YOLOXWindow;
import cn.nubia.yolox.YOLOXncnn;
import com.zte.aivibrate.DetectStrategyFactory;
import com.zte.aivibrate.IDetectStrategy;
import com.zte.aivibrate.IYoloDataProvider;
import com.zte.aivibrate.Vibrate4DController;
import com.zte.aivibrate.entity.Attack;
import com.zte.aivibrate.entity.Skill;
import com.zte.aivibrate.scene.BaseScene;
import com.zte.aivibrate.scene.VibrateSceneState;
import com.zte.aivibrate.util.AIVibrateLog;
import com.zte.gameassist.common.RotationMgr;
import com.zte.zscreenshot.ZScreenshot;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class YoloDataProcessor implements IYoloDataProvider {

    /* renamed from: b, reason: collision with root package name */
    public Context f16220b;

    /* renamed from: c, reason: collision with root package name */
    private YOLOXncnn f16221c;

    /* renamed from: d, reason: collision with root package name */
    private YOLOXWindow f16222d;

    /* renamed from: e, reason: collision with root package name */
    private Handler f16223e;

    /* renamed from: f, reason: collision with root package name */
    private BaseScene f16224f;

    /* renamed from: g, reason: collision with root package name */
    private final Vibrate4DController f16225g;

    /* renamed from: h, reason: collision with root package name */
    private IDetectStrategy f16226h;

    /* renamed from: k, reason: collision with root package name */
    private SkillReleaseModel f16229k;

    /* renamed from: l, reason: collision with root package name */
    private Attack f16230l;

    /* renamed from: q, reason: collision with root package name */
    private boolean f16235q;

    /* renamed from: a, reason: collision with root package name */
    private final List f16219a = Arrays.asList("lol_attack", "lol_skill", "attack", "skill", "ys_attack", "ys_skill1", "ys_skill2", "legends_attack", "legends_skill");

    /* renamed from: i, reason: collision with root package name */
    private final List f16227i = new ArrayList();

    /* renamed from: j, reason: collision with root package name */
    private final ZScreenshot f16228j = new ZScreenshot();

    /* renamed from: m, reason: collision with root package name */
    private boolean f16231m = false;

    /* renamed from: n, reason: collision with root package name */
    private boolean f16232n = false;

    /* renamed from: o, reason: collision with root package name */
    private int f16233o = 0;

    /* renamed from: p, reason: collision with root package name */
    private int f16234p = 0;

    /* renamed from: r, reason: collision with root package name */
    private final ZScreenshot.OnBufferCallback f16236r = new ZScreenshot.OnBufferCallback() { // from class: com.zte.aivibrate.processor.YoloDataProcessor.2
        @Override // com.zte.zscreenshot.ZScreenshot.OnBufferCallback
        public void a(Bitmap bitmap) {
            if (bitmap == null || bitmap.isRecycled() || YoloDataProcessor.this.f16223e == null) {
                AIVibrateLog.d(".Yolo", "full screen shot fail");
                return;
            }
            if (!YoloDataProcessor.this.f16225g.A()) {
                AIVibrateLog.d(".Yolo", "not game scene stop full screen shot");
                YoloDataProcessor.this.f16223e.removeMessages(2);
            } else {
                if (YoloDataProcessor.this.A() || !YoloDataProcessor.this.f16235q) {
                    AIVibrateLog.b(".Yolo", "should end detect data");
                    YoloDataProcessor.this.f16223e.removeMessages(2);
                    return;
                }
                Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
                Message obtain = Message.obtain();
                obtain.obj = copy;
                obtain.what = 1;
                YoloDataProcessor.this.f16223e.sendMessage(obtain);
            }
        }
    };

    public YoloDataProcessor(Context context, Vibrate4DController vibrate4DController) {
        this.f16220b = context;
        this.f16225g = vibrate4DController;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean A() {
        return this.f16232n || this.f16233o >= 100;
    }

    private void C() {
        this.f16231m = true;
        this.f16223e.removeMessages(2);
        this.f16223e.sendEmptyMessage(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E() {
        Bundle bundle = new Bundle();
        bundle.putString("name", "AIVibrate4D");
        bundle.putLong("interval", 0L);
        bundle.putParcelable("rect", new Rect(0, 0, RotationMgr.f(), RotationMgr.g()));
        this.f16228j.d(bundle, this.f16236r);
        this.f16223e.sendEmptyMessageDelayed(2, 1500L);
    }

    private void j() {
        if (this.f16233o >= 3) {
            if (this.f16227i.size() >= this.f16226h.d() && this.f16230l != null) {
                if (this.f16227i.size() > this.f16226h.d()) {
                    this.f16227i.sort(new Comparator() { // from class: com.zte.aivibrate.processor.a
                        @Override // java.util.Comparator
                        public final int compare(Object obj, Object obj2) {
                            int q2;
                            q2 = YoloDataProcessor.q((Skill) obj, (Skill) obj2);
                            return q2;
                        }
                    });
                    ArrayList arrayList = new ArrayList(this.f16227i.subList(0, this.f16226h.d()));
                    this.f16227i.clear();
                    this.f16227i.addAll(arrayList);
                }
                this.f16227i.sort(new Comparator() { // from class: com.zte.aivibrate.processor.b
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        int r2;
                        r2 = YoloDataProcessor.r((Skill) obj, (Skill) obj2);
                        return r2;
                    }
                });
                if ("com.miHoYo.Yuanshen".equals(this.f16225g.q())) {
                    List list = this.f16227i;
                    ((Skill) list.get(list.size() - 1)).f16207d = VibrateSceneState.ULTIMATE_SKILL;
                } else {
                    ((Skill) this.f16227i.get(0)).f16207d = VibrateSceneState.ULTIMATE_SKILL;
                }
                this.f16232n = true;
                this.f16224f.i();
            }
            if (A()) {
                x();
                D();
            }
        }
    }

    private void m(YOLOXncnn.Obj[] objArr) {
        String str;
        int indexOf;
        if (this.f16231m) {
            AIVibrateLog.b(".Yolo", "detect data:" + objArr.length);
            int e2 = this.f16226h.e();
            ArrayList arrayList = new ArrayList();
            for (YOLOXncnn.Obj obj : objArr) {
                if (obj.prob >= this.f16226h.b() && (str = obj.label) != null && (indexOf = this.f16219a.indexOf(str)) >= e2) {
                    if (indexOf - e2 == 0) {
                        Attack attack = this.f16230l;
                        if (attack == null) {
                            this.f16230l = new Attack(obj.a(), obj.prob);
                        } else if (obj.prob > attack.f9235b) {
                            attack.f9234a = obj.a();
                            this.f16230l.f9235b = obj.prob;
                        }
                    } else {
                        arrayList.add(new Skill(obj.a(), obj.prob));
                    }
                }
            }
            if (arrayList.size() >= this.f16226h.c()) {
                Iterator it = this.f16227i.iterator();
                float f2 = 0.0f;
                float f3 = 0.0f;
                while (it.hasNext()) {
                    f3 += ((Skill) it.next()).f9235b;
                }
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    f2 += ((Skill) it2.next()).f9235b;
                }
                if (f2 > f3) {
                    this.f16227i.clear();
                    this.f16227i.addAll(arrayList);
                    this.f16226h.a(this.f16227i.size());
                }
            }
            AIVibrateLog.b(".Yolo", "handle batch count:" + this.f16233o + ",skl:" + this.f16227i.size() + ",npc:" + this.f16234p + ",bsl:" + arrayList.size() + ",ak:" + this.f16230l);
            if (arrayList.size() < 2) {
                this.f16234p++;
            } else {
                this.f16234p = 0;
            }
            if (this.f16234p <= 3) {
                if (this.f16233o >= 100) {
                    this.f16225g.H(false, getClass().getSimpleName());
                    return;
                } else {
                    j();
                    return;
                }
            }
            if (this.f16230l == null) {
                this.f16225g.H(false, getClass().getSimpleName());
                return;
            }
            this.f16234p = 0;
            this.f16230l = null;
            AIVibrateLog.b(".Yolo", "detect attack but not detect skill");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int q(Skill skill, Skill skill2) {
        return Float.compare(skill2.f9235b, skill.f9235b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int r(Skill skill, Skill skill2) {
        return Float.compare(skill2.f9234a.right, skill.f9234a.right);
    }

    private void w() {
        this.f16231m = false;
        this.f16233o = 0;
        this.f16234p = 0;
        YOLOXWindow yOLOXWindow = this.f16222d;
        if (yOLOXWindow != null) {
            yOLOXWindow.e();
            this.f16222d = null;
        }
        Handler handler = this.f16223e;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private String x() {
        StringBuilder sb = new StringBuilder();
        sb.append("result:");
        sb.append("\n");
        sb.append("batchCount:");
        sb.append(this.f16233o);
        sb.append("\n");
        sb.append("AttachKey:");
        sb.append(this.f16230l);
        sb.append("\n");
        sb.append("SkillKey:");
        for (Skill skill : this.f16227i) {
            sb.append("\n");
            sb.append(skill);
        }
        AIVibrateLog.b(".Yolo", "startDetect finish " + ((Object) sb));
        return sb.toString();
    }

    public void B() {
        StringBuilder sb = new StringBuilder();
        sb.append("start data processor ");
        sb.append(p());
        sb.append(",init:");
        sb.append(this.f16224f != null);
        AIVibrateLog.b(".Yolo", sb.toString());
        if (this.f16224f == null) {
            IDetectStrategy c2 = DetectStrategyFactory.c(this.f16220b, this.f16225g.q());
            this.f16226h = c2;
            if (c2 == null) {
                AIVibrateLog.b(".Yolo", "yolo data processor not support " + this.f16225g.q());
                return;
            }
            if (this.f16229k == null) {
                SkillReleaseModel skillReleaseModel = new SkillReleaseModel();
                this.f16229k = skillReleaseModel;
                this.f16235q = skillReleaseModel.a(this.f16220b.getAssets());
                AIVibrateLog.b(".Yolo", "init skill model " + this.f16235q);
            }
            this.f16224f = DetectStrategyFactory.b(this.f16220b, this, this.f16229k, this.f16226h, this.f16225g.q(), this.f16225g.r());
        }
        if (this.f16235q && this.f16221c == null) {
            YOLOXncnn yOLOXncnn = new YOLOXncnn("vibrate.param", "vibrate.bin", (String[]) this.f16219a.toArray(new String[0]));
            this.f16221c = yOLOXncnn;
            this.f16235q = yOLOXncnn.b(this.f16220b.getAssets());
            AIVibrateLog.b(".Yolo", "init yolo " + this.f16235q + "," + this.f16225g.q() + "," + this.f16224f);
        }
        if (!this.f16235q || p()) {
            return;
        }
        C();
    }

    public void D() {
        AIVibrateLog.b(".Yolo", "stop task");
        w();
        YOLOXncnn yOLOXncnn = this.f16221c;
        if (yOLOXncnn != null) {
            yOLOXncnn.d();
            this.f16221c = null;
            this.f16235q = false;
        }
    }

    @Override // com.zte.aivibrate.IYoloDataProvider
    public List a() {
        return this.f16227i;
    }

    @Override // com.zte.aivibrate.scene.I4DVibrateScene
    public void b(VibrateSceneState vibrateSceneState) {
        this.f16225g.E(vibrateSceneState);
        if (vibrateSceneState == VibrateSceneState.GAME_END) {
            this.f16225g.H(false, getClass().getSimpleName());
        }
    }

    @Override // com.zte.aivibrate.IYoloDataProvider
    public Attack getAttack() {
        return this.f16230l;
    }

    public void k(PrintWriter printWriter) {
        printWriter.println(".Yolo");
        printWriter.println("  data:" + x());
        printWriter.println("  has detected:" + this.f16231m);
        printWriter.println("  has init:" + this.f16235q);
        printWriter.println("  has detect rect:" + this.f16232n);
        printWriter.println("  not pass count:" + this.f16234p);
        BaseScene baseScene = this.f16224f;
        if (baseScene != null) {
            baseScene.b(printWriter);
        }
    }

    public void l() {
        D();
        y();
        AIVibrateLog.b(".Yolo", "end:" + this.f16224f);
        BaseScene baseScene = this.f16224f;
        if (baseScene != null) {
            baseScene.c();
            this.f16224f = null;
        }
        SkillReleaseModel skillReleaseModel = this.f16229k;
        if (skillReleaseModel != null) {
            skillReleaseModel.c();
            this.f16229k = null;
        }
    }

    public void n(Bitmap bitmap) {
        if (this.f16221c == null) {
            return;
        }
        AIVibrateLog.b(".Yolo", "handle detect msg");
        YOLOXncnn.Obj[] a2 = this.f16221c.a(bitmap, false);
        this.f16233o++;
        if (a2 == null) {
            AIVibrateLog.d(".Yolo", "not detect data");
        } else {
            m(a2);
        }
    }

    public boolean o() {
        return this.f16231m;
    }

    public boolean p() {
        return this.f16232n;
    }

    public void s(VibrateSceneState vibrateSceneState) {
        BaseScene baseScene = this.f16224f;
        if (baseScene != null) {
            baseScene.d(vibrateSceneState);
        }
    }

    public void t() {
        z();
    }

    public void u() {
        w();
        BaseScene baseScene = this.f16224f;
        if (baseScene != null) {
            baseScene.c();
        }
    }

    public void v() {
        this.f16223e = new Handler(this.f16225g.r()) { // from class: com.zte.aivibrate.processor.YoloDataProcessor.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                int i2 = message.what;
                if (i2 == 1) {
                    YoloDataProcessor.this.n((Bitmap) message.obj);
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    YoloDataProcessor.this.E();
                }
            }
        };
    }

    public void y() {
        this.f16231m = false;
        this.f16232n = false;
        this.f16227i.clear();
        this.f16230l = null;
    }

    public void z() {
        boolean p2 = p();
        BaseScene baseScene = this.f16224f;
        if (p2 && (baseScene != null)) {
            baseScene.i();
        } else {
            B();
        }
    }
}
