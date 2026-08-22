package com.zte.gameassist.lowsugar.detect.scene.SGameGlobal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import com.zte.gameassist.lowsugar.R;
import com.zte.gameassist.lowsugar.detect.scene.GameBaseScene;
import com.zte.gameassist.lowsugar.utils.LowSugarUtils;

/* loaded from: classes2.dex */
public class SGameGlobalScene extends GameBaseScene {

    /* renamed from: e, reason: collision with root package name */
    private int f16908e;

    /* renamed from: f, reason: collision with root package name */
    private int f16909f;

    public SGameGlobalScene(Context context, String str) {
        super(context, str);
        this.f16908e = this.f16844b.getResources().getDimensionPixelOffset(R.dimen.low_sugar_sgame_wish_crop_startX) + LowSugarUtils.I;
        this.f16909f = this.f16844b.getResources().getDimensionPixelOffset(R.dimen.low_sugar_sgame_events_crop_startX) + LowSugarUtils.I;
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.GameBaseScene
    public Bitmap a(Bitmap bitmap, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (i2 == 7) {
            int i3 = this.f16909f;
            return Bitmap.createBitmap(bitmap, i3, 0, width - i3, height);
        }
        if (i2 != 8) {
            return Bitmap.createBitmap(bitmap);
        }
        int i4 = this.f16908e;
        return Bitmap.createBitmap(bitmap, i4, 0, width - i4, height);
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.GameBaseScene
    public Rect b(Bitmap bitmap, int i2) {
        if (i2 != 7 || bitmap == null) {
            return null;
        }
        return new Rect(this.f16909f, 0, bitmap.getWidth(), bitmap.getHeight());
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.GameBaseScene
    public String c(int i2) {
        return "国际版王者荣耀";
    }
}
