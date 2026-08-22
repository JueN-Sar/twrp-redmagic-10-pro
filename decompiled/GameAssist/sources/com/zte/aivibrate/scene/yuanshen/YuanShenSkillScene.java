package com.zte.aivibrate.scene.yuanshen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
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
import com.zte.aivibrate.entity.Skill;
import com.zte.aivibrate.scene.BaseScene;
import com.zte.aivibrate.scene.VibrateSceneState;
import com.zte.aivibrate.util.AIVibrateLog;
import com.zte.zscreenshot.ZScreenshot;

/* loaded from: classes.dex */
public class YuanShenSkillScene extends BaseScene {

    /* renamed from: i, reason: collision with root package name */
    private final Context f16285i;

    /* renamed from: j, reason: collision with root package name */
    private final IDetectStrategy f16286j;

    /* renamed from: k, reason: collision with root package name */
    private final IYoloDataProvider f16287k;

    /* renamed from: l, reason: collision with root package name */
    private final Handler f16288l;

    /* renamed from: m, reason: collision with root package name */
    private final ZScreenshot f16289m;

    /* renamed from: n, reason: collision with root package name */
    private final SkillReleaseModel f16290n;

    /* renamed from: o, reason: collision with root package name */
    private Skill f16291o;

    /* renamed from: p, reason: collision with root package name */
    private boolean f16292p;

    /* renamed from: q, reason: collision with root package name */
    private int f16293q;

    /* renamed from: r, reason: collision with root package name */
    private long f16294r;

    /* renamed from: s, reason: collision with root package name */
    private Rect f16295s;
    private Rect t;
    private Rect u;
    private final ZScreenshot.OnBufferCallback v;

    public YuanShenSkillScene(Context context, IYoloDataProvider iYoloDataProvider, SkillReleaseModel skillReleaseModel, IDetectStrategy iDetectStrategy, Looper looper) {
        super(iYoloDataProvider);
        this.f16289m = new ZScreenshot();
        this.f16291o = new Skill(new RectF(), 0.0f);
        this.f16292p = true;
        this.f16293q = 0;
        this.f16294r = 0L;
        this.v = new ZScreenshot.OnBufferCallback() { // from class: com.zte.aivibrate.scene.yuanshen.a
            @Override // com.zte.zscreenshot.ZScreenshot.OnBufferCallback
            public final void a(Bitmap bitmap) {
                YuanShenSkillScene.this.B(bitmap);
            }
        };
        this.f16285i = context;
        this.f16290n = skillReleaseModel;
        this.f16286j = iDetectStrategy;
        this.f16287k = iYoloDataProvider;
        this.f16288l = new Handler(looper) { // from class: com.zte.aivibrate.scene.yuanshen.YuanShenSkillScene.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                int i2 = message.what;
                if (i2 == 1) {
                    YuanShenSkillScene.this.v();
                } else if (i2 == 2) {
                    YuanShenSkillScene.this.q();
                } else {
                    if (i2 != 3) {
                        return;
                    }
                    YuanShenSkillScene.this.r((Bitmap) message.obj);
                }
            }
        };
    }

    private void A(VibrateSceneState vibrateSceneState) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.f16294r > 1000) {
            h(vibrateSceneState);
            this.f16294r = elapsedRealtime;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            AIVibrateLog.d(".YuanShenSkillScene", "click screen shot fail");
            return;
        }
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Message obtain = Message.obtain();
        obtain.obj = copy;
        obtain.what = 3;
        this.f16288l.sendMessage(obtain);
    }

    private void C() {
        this.f16288l.sendEmptyMessage(2);
    }

    private void D(VibrateSceneState vibrateSceneState) {
        if (vibrateSceneState == VibrateSceneState.YS_ULTIMATE_SKILL || vibrateSceneState == VibrateSceneState.YS_SMALL_SKILL) {
            A(vibrateSceneState);
        } else {
            h(vibrateSceneState);
        }
        this.f16291o.f16206c = false;
        this.f16293q = 0;
    }

    private void E(Rect rect, long j2) {
        Bundle bundle = new Bundle();
        bundle.putString("name", "AIVibrate4D");
        bundle.putLong("interval", j2);
        bundle.putParcelable("rect", rect);
        this.f16289m.d(bundle, this.v);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        E(this.f16291o.b(), 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(Bitmap bitmap) {
        this.f16291o.f16206c = this.f16290n.b(bitmap);
        boolean hasMessages = this.f16288l.hasMessages(1);
        AIVibrateLog.b(".YuanShenSkillScene", "screen shot callback " + this.f16291o.f16206c + "," + hasMessages + ",state:" + this.f16291o.f16207d);
        Skill skill = this.f16291o;
        if (!skill.f16206c || hasMessages) {
            return;
        }
        D(skill.f16207d);
    }

    private void s(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(actionIndex);
        int rawX = (int) motionEvent.getRawX(actionIndex);
        int rawY = (int) motionEvent.getRawY(actionIndex);
        if (this.u.contains(rawX, rawY)) {
            Skill skill = this.f16291o;
            skill.f16207d = VibrateSceneState.YS_ULTIMATE_SKILL;
            skill.f9234a = new RectF(this.u);
            C();
            return;
        }
        if (o(rawX, rawY) || p(rawX, rawY)) {
            this.f16288l.sendEmptyMessageDelayed(1, 1000L);
            this.f16293q = pointerId;
            C();
        }
    }

    private void t(MotionEvent motionEvent) {
        s(motionEvent);
    }

    private void u(MotionEvent motionEvent) {
        z();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        if (this.f16291o.f16206c) {
            D(VibrateSceneState.YS_LONG_PRESS_SKILL);
        }
    }

    private void w(MotionEvent motionEvent) {
    }

    private void x(MotionEvent motionEvent) {
        s(motionEvent);
    }

    private void y(MotionEvent motionEvent) {
        if (this.f16293q == motionEvent.getPointerId(motionEvent.getActionIndex())) {
            z();
        }
    }

    private void z() {
        if (this.f16291o.f16206c) {
            AIVibrateLog.b("AIVibrate4D", "handle up ");
            D(this.f16291o.f16207d);
        }
        this.f16288l.removeMessages(1);
    }

    @Override // com.zte.aivibrate.scene.BaseScene
    public void c() {
        this.f16292p = false;
        j();
    }

    @Override // com.zte.aivibrate.scene.BaseScene
    public void d(VibrateSceneState vibrateSceneState) {
        if (vibrateSceneState == VibrateSceneState.YS_ENTER_GAMING) {
            this.f16292p = true;
        } else if (vibrateSceneState == VibrateSceneState.YS_EXIT_GAMING) {
            this.f16292p = false;
        }
    }

    @Override // com.zte.aivibrate.scene.BaseScene, com.zte.gameassist.input.InterfaceEventListener
    public void f(MotionEvent motionEvent) {
        super.f(motionEvent);
        if (this.f16292p) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                t(motionEvent);
                return;
            }
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    w(motionEvent);
                    return;
                }
                if (actionMasked != 3) {
                    if (actionMasked == 5) {
                        x(motionEvent);
                        return;
                    } else {
                        if (actionMasked != 6) {
                            return;
                        }
                        y(motionEvent);
                        return;
                    }
                }
            }
            u(motionEvent);
        }
    }

    @Override // com.zte.aivibrate.scene.BaseScene
    public void i() {
        this.f16295s = this.f16287k.getAttack().a();
        for (Skill skill : this.f16287k.a()) {
            if (skill.d()) {
                this.u = skill.a();
            } else {
                this.t = skill.a();
            }
        }
        g(this.f16285i);
    }

    protected boolean o(int i2, int i3) {
        boolean contains = this.f16295s.contains(i2, i3);
        if (contains) {
            Skill skill = this.f16291o;
            skill.f16207d = VibrateSceneState.YS_ATTACK;
            skill.f9234a = new RectF(this.f16295s);
        }
        return contains;
    }

    protected boolean p(int i2, int i3) {
        boolean contains = this.t.contains(i2, i3);
        if (contains) {
            Skill skill = this.f16291o;
            skill.f16207d = VibrateSceneState.YS_SMALL_SKILL;
            skill.f9234a = new RectF(this.t);
        }
        return contains;
    }
}
