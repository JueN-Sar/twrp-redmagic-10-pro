package com.facebook.rebound;

/* loaded from: classes.dex */
public class SpringSystem extends BaseSpringSystem {
    private SpringSystem(SpringLooper springLooper) {
        super(springLooper);
    }

    public static SpringSystem h() {
        return new SpringSystem(AndroidSpringLooperFactory.a());
    }
}
