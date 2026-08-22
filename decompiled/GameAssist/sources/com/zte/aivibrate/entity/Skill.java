package com.zte.aivibrate.entity;

import android.graphics.Rect;
import android.graphics.RectF;
import cn.nubia.yolox.YoloObject;
import com.zte.aivibrate.scene.VibrateSceneState;

/* loaded from: classes.dex */
public class Skill extends YoloObject {

    /* renamed from: c, reason: collision with root package name */
    public boolean f16206c;

    /* renamed from: d, reason: collision with root package name */
    public VibrateSceneState f16207d;

    public Skill(RectF rectF, float f2) {
        super(rectF, f2);
        this.f16207d = VibrateSceneState.SMALL_SKILL;
    }

    public Rect b() {
        int i2;
        int i3;
        Rect a2 = a();
        int width = a2.width();
        int height = a2.height();
        float f2 = width;
        float f3 = height;
        if (f2 / f3 > 0.85714287f) {
            i3 = (int) (f3 * 0.85714287f);
            i2 = height;
        } else {
            i2 = (int) (f2 / 0.85714287f);
            i3 = width;
        }
        int min = Math.min(i3, i2);
        int i4 = ((width - i3) / 2) + ((i3 - min) / 2);
        int i5 = ((height - i2) / 2) + ((i2 - min) / 2);
        int i6 = a2.left;
        int i7 = a2.top;
        a2.set(i6 + i4, i7 + i5, i6 + i4 + min, i7 + i5 + min);
        return a2;
    }

    public Skill c() {
        Skill skill = new Skill(this.f9234a, this.f9235b);
        skill.f16207d = this.f16207d;
        return skill;
    }

    public boolean d() {
        return this.f16207d == VibrateSceneState.ULTIMATE_SKILL;
    }

    @Override // cn.nubia.yolox.YoloObject
    public String toString() {
        return "Skill{state=" + this.f16207d.d() + ", canRelease=" + this.f16206c + ", rectF=" + this.f9234a + ", prob=" + this.f9235b + '}';
    }
}
