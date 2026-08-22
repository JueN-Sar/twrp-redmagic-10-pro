package com.zte.aivibrate.scene;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.MotionEvent;
import cn.nubia.yolox.SkillReleaseModel;
import com.zte.aivibrate.IDetectStrategy;
import com.zte.aivibrate.IYoloDataProvider;
import com.zte.aivibrate.OCRController;
import com.zte.aivibrate.entity.Skill;
import com.zte.aivibrate.util.AIVibrateLog;
import com.zte.zscreenshot.ZScreenshot;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class SkillScene extends BaseScene implements OCRController.Callback {

    /* renamed from: i, reason: collision with root package name */
    private final Context f16245i;

    /* renamed from: j, reason: collision with root package name */
    private final ZScreenshot f16246j;

    /* renamed from: k, reason: collision with root package name */
    private final ZScreenshot f16247k;

    /* renamed from: l, reason: collision with root package name */
    private final ZScreenshot f16248l;

    /* renamed from: m, reason: collision with root package name */
    private final Handler f16249m;

    /* renamed from: n, reason: collision with root package name */
    private String f16250n;

    /* renamed from: o, reason: collision with root package name */
    private final IYoloDataProvider f16251o;

    /* renamed from: p, reason: collision with root package name */
    private final IDetectStrategy f16252p;

    /* renamed from: q, reason: collision with root package name */
    private final SkillReleaseModel f16253q;

    /* renamed from: r, reason: collision with root package name */
    private boolean f16254r;

    /* renamed from: s, reason: collision with root package name */
    private boolean f16255s;
    private int t;
    private Skill u;
    private long v;
    protected int w;
    private final ZScreenshot.OnBufferCallback x;
    private final ZScreenshot.OnBufferCallback y;
    private final ZScreenshot.OnBufferCallback z;

    public SkillScene(Context context, IYoloDataProvider iYoloDataProvider, SkillReleaseModel skillReleaseModel, IDetectStrategy iDetectStrategy, String str, Looper looper) {
        super(iYoloDataProvider);
        this.f16246j = new ZScreenshot();
        this.f16247k = new ZScreenshot();
        this.f16248l = new ZScreenshot();
        this.f16254r = false;
        this.f16255s = false;
        this.t = 0;
        this.u = new Skill(new RectF(), 0.0f);
        this.x = new ZScreenshot.OnBufferCallback() { // from class: com.zte.aivibrate.scene.a
            @Override // com.zte.zscreenshot.ZScreenshot.OnBufferCallback
            public final void a(Bitmap bitmap) {
                SkillScene.this.q(bitmap);
            }
        };
        this.y = new ZScreenshot.OnBufferCallback() { // from class: com.zte.aivibrate.scene.b
            @Override // com.zte.zscreenshot.ZScreenshot.OnBufferCallback
            public final void a(Bitmap bitmap) {
                SkillScene.this.E(bitmap);
            }
        };
        this.z = new ZScreenshot.OnBufferCallback() { // from class: com.zte.aivibrate.scene.b
            @Override // com.zte.zscreenshot.ZScreenshot.OnBufferCallback
            public final void a(Bitmap bitmap) {
                SkillScene.this.E(bitmap);
            }
        };
        this.f16253q = skillReleaseModel;
        this.f16245i = context;
        this.f16251o = iYoloDataProvider;
        this.f16250n = str;
        this.f16252p = iDetectStrategy;
        this.f16249m = new Handler(looper) { // from class: com.zte.aivibrate.scene.SkillScene.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                int i2 = message.what;
                if (i2 == 1) {
                    SkillScene.this.s((Skill) message.obj);
                    return;
                }
                if (i2 == 2) {
                    SkillScene.this.t((Bitmap) message.obj);
                } else if (i2 == 3) {
                    SkillScene.this.H();
                } else {
                    if (i2 != 4) {
                        return;
                    }
                    SkillScene.this.w();
                }
            }
        };
    }

    private void A(MotionEvent motionEvent) {
        if (this.t == motionEvent.getPointerId(motionEvent.getActionIndex())) {
            B();
        }
        this.f16255s = false;
    }

    private void B() {
        Skill skill = this.u;
        if (skill.f16206c) {
            G(skill.f16207d);
        } else if (this.f16254r) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.v;
            AIVibrateLog.b(".SkillScene", "handleUpEvent " + elapsedRealtime);
            F(this.u, Math.max(0L, 30 - elapsedRealtime));
        }
        this.f16249m.removeMessages(4);
        this.f16255s = false;
        this.t = 0;
    }

    private boolean C(float f2, float f3) {
        if (this.f16251o.getAttack() == null || !this.f16251o.getAttack().f9234a.contains(f2, f3)) {
            return false;
        }
        G(VibrateSceneState.ATTACK);
        return true;
    }

    private boolean D(float f2, float f3) {
        List<Skill> a2 = this.f16251o.a();
        if (a2 == null || a2.isEmpty()) {
            return false;
        }
        for (Skill skill : a2) {
            if (skill.f9234a.contains(f2, f3)) {
                this.u = skill.c();
                this.v = SystemClock.elapsedRealtime();
                this.f16249m.sendEmptyMessageDelayed(4, 1000L);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            AIVibrateLog.d(".SkillScene", "click screen shot fail");
            return;
        }
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Message obtain = Message.obtain();
        obtain.obj = copy;
        obtain.what = 2;
        this.f16249m.sendMessage(obtain);
    }

    private void F(Skill skill, long j2) {
        this.f16254r = false;
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = skill;
        AIVibrateLog.b(".SkillScene", "sendScreenShotMsg: " + j2);
        this.f16249m.sendMessageDelayed(obtain, j2);
    }

    private void G(VibrateSceneState vibrateSceneState) {
        h(vibrateSceneState);
        this.u.f16206c = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H() {
        if (this.f16252p.f().isEmpty()) {
            return;
        }
        this.f16249m.removeMessages(3);
        Bundle bundle = new Bundle();
        bundle.putString("name", "AIVibrate4D");
        bundle.putLong("interval", 0L);
        bundle.putParcelable("rect", this.f16252p.f());
        this.f16247k.d(bundle, this.x);
        this.f16249m.sendEmptyMessageDelayed(3, 20000L);
    }

    private void I(Skill skill, long j2) {
        Bundle bundle = new Bundle();
        bundle.putString("name", "AIVibrate4D");
        bundle.putLong("interval", j2);
        bundle.putParcelable("rect", skill.b());
        if (skill.d()) {
            this.f16248l.d(bundle, this.y);
        } else {
            this.f16246j.d(bundle, this.z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(Bitmap bitmap) {
        OCRController.e().c(bitmap.copy(Bitmap.Config.ARGB_8888, true));
    }

    private void r() {
        this.f16249m.removeMessages(3);
        this.w = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(Skill skill) {
        I(skill, 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t(Bitmap bitmap) {
        this.u.f16206c = this.f16253q.b(bitmap);
        boolean hasMessages = this.f16249m.hasMessages(4);
        AIVibrateLog.b(".SkillScene", "screen shot callback " + this.u.f16206c + "," + hasMessages + ",state:" + this.u.f16207d);
        Skill skill = this.u;
        if (!skill.f16206c || hasMessages) {
            return;
        }
        G(skill.f16207d);
    }

    private void u(MotionEvent motionEvent) {
        boolean C = C(motionEvent.getRawX(), motionEvent.getRawY());
        this.f16255s = C;
        if (C) {
            return;
        }
        this.f16254r = D(motionEvent.getRawX(), motionEvent.getRawY());
    }

    private void v(MotionEvent motionEvent) {
        B();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        AIVibrateLog.b(".SkillScene", "long press " + this.f16254r + "," + this.u.f16206c);
        if (this.f16254r) {
            Skill skill = this.u;
            skill.f16207d = VibrateSceneState.LONG_PRESS_SKILL;
            F(skill, 0L);
        } else if (this.u.f16206c) {
            G(VibrateSceneState.LONG_PRESS_SKILL);
        }
    }

    private void x(MotionEvent motionEvent) {
        z();
    }

    private void y(MotionEvent motionEvent) {
        if (this.f16255s || this.f16254r) {
            return;
        }
        int actionIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(actionIndex);
        float rawX = motionEvent.getRawX(actionIndex);
        float rawY = motionEvent.getRawY(actionIndex);
        boolean C = C(rawX, rawY);
        this.f16255s = C;
        if (!C) {
            this.f16254r = D(rawX, rawY);
        }
        if (this.f16254r) {
            this.t = pointerId;
        }
    }

    private void z() {
        if (this.f16254r) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.v;
            AIVibrateLog.b(".SkillScene", "handleOtherPointEvent: " + elapsedRealtime);
            F(this.u, Math.max(0L, 30 - elapsedRealtime));
        }
    }

    @Override // com.zte.aivibrate.OCRController.Callback
    public void a(List list) {
        if (!list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (str != null && str.matches("(?i).*(?:\\d|vs).*")) {
                    this.w = 0;
                    break;
                }
            }
        }
        this.w++;
        AIVibrateLog.b(".SkillScene", "ocr callback " + this.w);
        if (this.w > 3) {
            AIVibrateLog.b(".SkillScene", "not check attack exist, game end");
            h(VibrateSceneState.GAME_END);
            r();
        }
    }

    @Override // com.zte.aivibrate.scene.BaseScene
    public void b(PrintWriter printWriter) {
        printWriter.println(".SkillScene");
        printWriter.println("  down skill:" + this.u);
        printWriter.println("  no gaming:" + this.w);
        printWriter.println("  register monitor:" + this.f16239c);
    }

    @Override // com.zte.aivibrate.scene.BaseScene
    public void c() {
        AIVibrateLog.b(".SkillScene", "endScene");
        OCRController.e().l(this);
        r();
        j();
        Handler handler = this.f16249m;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.zte.aivibrate.scene.BaseScene
    public void d(VibrateSceneState vibrateSceneState) {
        if (vibrateSceneState != VibrateSceneState.ATTACK) {
            this.w = 0;
        }
    }

    @Override // com.zte.aivibrate.scene.BaseScene, com.zte.gameassist.input.InterfaceEventListener
    public void f(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            u(motionEvent);
            return;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                x(motionEvent);
                return;
            }
            if (actionMasked != 3) {
                if (actionMasked == 5) {
                    y(motionEvent);
                    return;
                } else {
                    if (actionMasked != 6) {
                        return;
                    }
                    A(motionEvent);
                    return;
                }
            }
        }
        v(motionEvent);
    }

    @Override // com.zte.aivibrate.scene.BaseScene
    public void i() {
        AIVibrateLog.b(".SkillScene", "startScene");
        OCRController.e().a(this);
        H();
        g(this.f16245i);
    }

    @Override // com.zte.gameassist.input.InterfaceEventListener
    public void onDispose() {
        j();
    }
}
