package com.zte.aivibrate.scene.yuanshen;

import android.os.Bundle;
import cn.nubia.gamelab.IToyService;
import com.zte.aivibrate.scene.GameLabBaseScene;
import com.zte.aivibrate.scene.I4DVibrateScene;
import com.zte.aivibrate.scene.VibrateSceneState;
import com.zte.aivibrate.util.AIVibrateLog;

/* loaded from: classes.dex */
public class YuanShenGameLabScene extends GameLabBaseScene {

    /* renamed from: m, reason: collision with root package name */
    private long f16281m;

    /* renamed from: n, reason: collision with root package name */
    private long f16282n;

    /* renamed from: o, reason: collision with root package name */
    private long f16283o;

    /* renamed from: p, reason: collision with root package name */
    private long f16284p;

    public YuanShenGameLabScene(IToyService iToyService, I4DVibrateScene i4DVibrateScene) {
        super(iToyService, i4DVibrateScene, "com.miHoYo.Yuanshen");
        this.f16281m = 0L;
        this.f16282n = 0L;
        this.f16283o = 0L;
        this.f16284p = 0L;
    }

    @Override // com.zte.aivibrate.scene.GameLabBaseScene, com.zte.aivibrate.scene.BaseScene
    public void i() {
        super.i();
        k(18142143185240L);
    }

    @Override // com.zte.aivibrate.scene.GameLabBaseScene
    protected void l(Bundle bundle) {
        super.l(bundle);
        long j2 = bundle.getLong("time");
        long j3 = bundle.getLong("flag");
        AIVibrateLog.b(".YuanShenScene", "unpackYuanShenData: " + j3 + ",bundle:" + bundle);
        if (j3 == 18141941858312L && j2 - this.f16282n > 10000) {
            AIVibrateLog.b(".YuanShenScene", "飞行");
            h(VibrateSceneState.YS_FLYING);
            this.f16282n = j2;
            return;
        }
        if (j3 == 18141941858320L) {
            if (j2 - this.f16281m > 10000) {
                AIVibrateLog.b(".YuanShenScene", "冲刺");
                h(VibrateSceneState.YS_RUNNING);
                this.f16281m = j2;
                return;
            }
            return;
        }
        if (j3 == 18142008967168L) {
            AIVibrateLog.b(".YuanShenScene", "进入游戏");
            h(VibrateSceneState.YS_ENTER_GAMING);
            return;
        }
        if (j3 == 18142076076032L) {
            AIVibrateLog.b(".YuanShenScene", "退出游戏");
            h(VibrateSceneState.YS_EXIT_GAMING);
            return;
        }
        if (j3 == 18141941858560L && j2 - this.f16284p > 10000) {
            AIVibrateLog.b(".YuanShenScene", "抽到五星英雄");
            h(VibrateSceneState.YS_FIVE_START);
            this.f16284p = j2;
        } else {
            if (j3 != 18141941858368L || j2 - this.f16283o <= 10000) {
                return;
            }
            AIVibrateLog.b(".YuanShenScene", "死亡");
            h(VibrateSceneState.YS_PERSON_DIED);
            this.f16283o = j2;
        }
    }

    @Override // com.zte.gameassist.input.InterfaceEventListener
    public void onDispose() {
        j();
    }
}
