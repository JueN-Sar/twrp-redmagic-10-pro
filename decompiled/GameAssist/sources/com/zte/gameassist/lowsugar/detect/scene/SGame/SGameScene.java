package com.zte.gameassist.lowsugar.detect.scene.SGame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import com.zte.gameassist.lowsugar.R;
import com.zte.gameassist.lowsugar.detect.scene.GameBaseScene;

/* loaded from: classes2.dex */
public class SGameScene extends GameBaseScene {

    /* renamed from: e, reason: collision with root package name */
    private int f16897e;

    /* renamed from: f, reason: collision with root package name */
    private int f16898f;

    public SGameScene(Context context, String str) {
        super(context, str);
        this.f16897e = this.f16844b.getResources().getDimensionPixelOffset(R.dimen.low_sugar_sgame_wish_crop_startX);
        this.f16898f = this.f16844b.getResources().getDimensionPixelOffset(R.dimen.low_sugar_sgame_events_crop_startX);
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.GameBaseScene
    public Bitmap a(Bitmap bitmap, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (i2 == 3) {
            int i3 = this.f16898f;
            return Bitmap.createBitmap(bitmap, i3, 0, width - i3, height);
        }
        if (i2 != 4) {
            return Bitmap.createBitmap(bitmap);
        }
        int i4 = this.f16897e;
        return Bitmap.createBitmap(bitmap, i4, 0, width - i4, height);
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.GameBaseScene
    public Rect b(Bitmap bitmap, int i2) {
        if (i2 == 3) {
            return new Rect(this.f16898f, 0, bitmap.getWidth(), bitmap.getHeight());
        }
        if (i2 == 4) {
            return new Rect(this.f16897e, 0, bitmap.getWidth(), bitmap.getHeight());
        }
        return null;
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.GameBaseScene
    public String c(int i2) {
        return "王者荣耀";
    }
}
