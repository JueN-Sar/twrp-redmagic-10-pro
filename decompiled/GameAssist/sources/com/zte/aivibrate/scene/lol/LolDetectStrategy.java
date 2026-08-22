package com.zte.aivibrate.scene.lol;

import android.content.Context;
import android.graphics.Rect;
import com.zte.aivibrate.IDetectStrategy;
import com.zte.aivibrate.R;

/* loaded from: classes.dex */
public class LolDetectStrategy implements IDetectStrategy {

    /* renamed from: a, reason: collision with root package name */
    private final Rect f16266a;

    public LolDetectStrategy(Context context) {
        this.f16266a = new Rect(context.getResources().getDimensionPixelSize(R.dimen.lol_vs_rect_left), context.getResources().getDimensionPixelSize(R.dimen.lol_vs_rect_top), context.getResources().getDimensionPixelSize(R.dimen.lol_vs_rect_right), context.getResources().getDimensionPixelSize(R.dimen.lol_vs_rect_bottom));
    }

    @Override // com.zte.aivibrate.IDetectStrategy
    public int c() {
        return d();
    }

    @Override // com.zte.aivibrate.IDetectStrategy
    public int d() {
        return 4;
    }

    @Override // com.zte.aivibrate.IDetectStrategy
    public int e() {
        return 0;
    }

    @Override // com.zte.aivibrate.IDetectStrategy
    public Rect f() {
        return this.f16266a;
    }
}
