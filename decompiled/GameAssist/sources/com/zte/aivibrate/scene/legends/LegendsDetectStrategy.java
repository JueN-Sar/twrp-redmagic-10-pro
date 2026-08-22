package com.zte.aivibrate.scene.legends;

import android.content.Context;
import com.zte.aivibrate.IDetectStrategy;

/* loaded from: classes.dex */
public class LegendsDetectStrategy implements IDetectStrategy {

    /* renamed from: a, reason: collision with root package name */
    private int f16259a = c();

    public LegendsDetectStrategy(Context context) {
    }

    @Override // com.zte.aivibrate.IDetectStrategy
    public void a(int i2) {
        if (i2 >= g()) {
            this.f16259a = g();
        } else if (i2 >= c()) {
            this.f16259a = i2;
        }
    }

    @Override // com.zte.aivibrate.IDetectStrategy
    public int c() {
        return 3;
    }

    @Override // com.zte.aivibrate.IDetectStrategy
    public int d() {
        return this.f16259a;
    }

    @Override // com.zte.aivibrate.IDetectStrategy
    public int e() {
        return 7;
    }

    public int g() {
        return 4;
    }
}
