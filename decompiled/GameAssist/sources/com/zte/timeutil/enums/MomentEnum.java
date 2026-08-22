package com.zte.timeutil.enums;

/* loaded from: classes2.dex */
public enum MomentEnum {
    day_break(0),
    early_morning(6),
    morning(8),
    noon(11),
    afternoon(13),
    night(18),
    lateNight(20),
    midNight(22);

    private int hourTime;

    MomentEnum(int i2) {
        this.hourTime = i2;
    }

    public int d() {
        return this.hourTime;
    }
}
