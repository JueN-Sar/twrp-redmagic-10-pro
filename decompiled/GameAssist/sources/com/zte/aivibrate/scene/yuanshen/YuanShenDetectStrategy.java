package com.zte.aivibrate.scene.yuanshen;

import android.content.Context;
import com.zte.aivibrate.IDetectStrategy;

/* loaded from: classes.dex */
public class YuanShenDetectStrategy implements IDetectStrategy {
    public YuanShenDetectStrategy(Context context) {
    }

    @Override // com.zte.aivibrate.IDetectStrategy
    public float b() {
        return 0.8f;
    }

    @Override // com.zte.aivibrate.IDetectStrategy
    public int c() {
        return 2;
    }

    @Override // com.zte.aivibrate.IDetectStrategy
    public int d() {
        return 2;
    }

    @Override // com.zte.aivibrate.IDetectStrategy
    public int e() {
        return 4;
    }
}
