package com.zte.aivibrate.scene.sgame;

import android.content.Context;
import android.graphics.Rect;
import com.zte.aivibrate.IDetectStrategy;
import com.zte.aivibrate.R;

/* loaded from: classes.dex */
public class SGameDetectStrategy implements IDetectStrategy {

    /* renamed from: a, reason: collision with root package name */
    private final Rect f16273a;

    /* renamed from: b, reason: collision with root package name */
    private int f16274b = c();

    public SGameDetectStrategy(Context context) {
        this.f16273a = new Rect(context.getResources().getDimensionPixelSize(R.dimen.sgame_vs_rect_left), context.getResources().getDimensionPixelSize(R.dimen.sgame_vs_rect_top), context.getResources().getDimensionPixelSize(R.dimen.sgame_vs_rect_right), context.getResources().getDimensionPixelSize(R.dimen.sgame_vs_rect_bottom));
    }

    @Override // com.zte.aivibrate.IDetectStrategy
    public void a(int i2) {
        if (i2 >= g()) {
            this.f16274b = g();
        } else if (i2 >= c()) {
            this.f16274b = i2;
        }
    }

    @Override // com.zte.aivibrate.IDetectStrategy
    public int c() {
        return 3;
    }

    @Override // com.zte.aivibrate.IDetectStrategy
    public int d() {
        return this.f16274b;
    }

    @Override // com.zte.aivibrate.IDetectStrategy
    public int e() {
        return 2;
    }

    @Override // com.zte.aivibrate.IDetectStrategy
    public Rect f() {
        return this.f16273a;
    }

    public int g() {
        return 4;
    }
}
