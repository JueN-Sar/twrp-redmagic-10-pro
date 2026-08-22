package com.zte.gameassist.lowsugar.detect.scene.Genshin;

import android.content.Context;
import android.view.MotionEvent;
import com.zte.gameassist.lowsugar.detect.ISceneDetect;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class GenshinDetect implements ISceneDetect {

    /* renamed from: a, reason: collision with root package name */
    private Context f16856a;

    /* renamed from: b, reason: collision with root package name */
    private Genshin f16857b;

    public GenshinDetect(Context context) {
        this.f16856a = context;
        this.f16857b = new Genshin(context);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public boolean b(int i2, MotionEvent motionEvent) {
        return this.f16857b.b(i2, motionEvent);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public boolean d(int i2, MotionEvent motionEvent) {
        return this.f16857b.i(i2, motionEvent);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public int e(List list, Map map) {
        return this.f16857b.h(list, map);
    }
}
