package com.zte.gameassist.lowsugar.detect.scene.Wildrift;

import android.content.Context;
import android.view.MotionEvent;
import com.zte.gameassist.lowsugar.detect.ISceneDetect;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class WildriftDetect implements ISceneDetect {

    /* renamed from: d, reason: collision with root package name */
    private static int f16914d = 3;

    /* renamed from: a, reason: collision with root package name */
    private final Context f16915a;

    /* renamed from: b, reason: collision with root package name */
    private Wildrift f16916b;

    /* renamed from: c, reason: collision with root package name */
    private int f16917c;

    public WildriftDetect(Context context) {
        this.f16915a = context;
        this.f16916b = new Wildrift(context);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public boolean b(int i2, MotionEvent motionEvent) {
        return this.f16916b.b(i2, motionEvent);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public void c() {
        this.f16917c = 0;
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public boolean d(int i2, MotionEvent motionEvent) {
        return this.f16916b.h(i2, motionEvent);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public int e(List list, Map map) {
        return this.f16916b.g(list, map);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public boolean f() {
        int i2 = this.f16917c;
        if (i2 < f16914d) {
            this.f16917c = i2 + 1;
            return false;
        }
        this.f16917c = 0;
        return true;
    }
}
