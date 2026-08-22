package com.zte.gameassist.lowsugar.detect.scene.SGameGlobal;

import android.content.Context;
import android.view.MotionEvent;
import com.zte.gameassist.lowsugar.detect.ISceneDetect;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class SGameGlobalDetect implements ISceneDetect {

    /* renamed from: d, reason: collision with root package name */
    private static int f16904d = 3;

    /* renamed from: a, reason: collision with root package name */
    private final Context f16905a;

    /* renamed from: b, reason: collision with root package name */
    private SGameGlobal f16906b;

    /* renamed from: c, reason: collision with root package name */
    private int f16907c;

    public SGameGlobalDetect(Context context) {
        this.f16905a = context;
        this.f16906b = new SGameGlobal(context);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public boolean b(int i2, MotionEvent motionEvent) {
        return this.f16906b.b(i2, motionEvent);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public void c() {
        this.f16907c = 0;
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public boolean d(int i2, MotionEvent motionEvent) {
        return this.f16906b.h(i2, motionEvent);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public int e(List list, Map map) {
        return this.f16906b.g(list, map);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public boolean f() {
        int i2 = this.f16907c;
        if (i2 < f16904d) {
            this.f16907c = i2 + 1;
            return false;
        }
        this.f16907c = 0;
        return true;
    }
}
