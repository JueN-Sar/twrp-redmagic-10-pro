package com.zte.aivibrate;

import android.graphics.Rect;
import cn.nubia.componentcenter.service.AI4DVibrateComService;
import com.zte.aivibrate.entity.Skill;
import com.zte.aivibrate.scene.VibrateSceneState;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AI4DVibrateComServiceImpl implements AI4DVibrateComService {
    private final Vibrate4DController mVibrate4DController;

    public AI4DVibrateComServiceImpl(Vibrate4DController vibrate4DController) {
        this.mVibrate4DController = vibrate4DController;
    }

    @Override // cn.nubia.componentcenter.service.AI4DVibrateComService
    public boolean aI4DVibrateThreadAlive() {
        return this.mVibrate4DController.G();
    }

    @Override // cn.nubia.componentcenter.service.AI4DVibrateComService
    public void closePlugin(String str) {
        this.mVibrate4DController.l();
    }

    @Override // cn.nubia.componentcenter.service.AI4DVibrateComService
    public boolean gameLabHasDetected() {
        return this.mVibrate4DController.p().q();
    }

    @Override // cn.nubia.componentcenter.service.AI4DVibrateComService
    public Rect getAttack() {
        return this.mVibrate4DController.s().getAttack().a();
    }

    @Override // cn.nubia.componentcenter.service.AI4DVibrateComService
    public List<Rect> getSmallSkillList() {
        List<Skill> a2 = this.mVibrate4DController.s().a();
        ArrayList arrayList = new ArrayList();
        for (Skill skill : a2) {
            if (skill.f16207d == VibrateSceneState.SMALL_SKILL) {
                arrayList.add(skill.a());
            }
        }
        return arrayList;
    }

    @Override // cn.nubia.componentcenter.service.AI4DVibrateComService
    public Rect getUltimateSkill() {
        for (Skill skill : this.mVibrate4DController.s().a()) {
            if (skill.d()) {
                return skill.a();
            }
        }
        return new Rect();
    }

    @Override // cn.nubia.componentcenter.service.AI4DVibrateComService
    public boolean hasGaming() {
        return this.mVibrate4DController.x();
    }

    @Override // cn.nubia.componentcenter.service.AI4DVibrateComService
    public void openPlugin(String str) {
        this.mVibrate4DController.D();
    }

    @Override // cn.nubia.componentcenter.service.AI4DVibrateComService
    public boolean yoloHasDetected() {
        return this.mVibrate4DController.s().o();
    }
}
