package com.zte.gameassist.lowsugar.detect.scene.Wildrift;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import com.zte.gameassist.lowsugar.R;
import com.zte.gameassist.lowsugar.detect.scene.GameBaseScene;
import com.zte.gameassist.lowsugar.utils.LowSugarUtils;

/* loaded from: classes2.dex */
public class WildriftScene extends GameBaseScene {

    /* renamed from: e, reason: collision with root package name */
    private int f16918e;

    /* renamed from: f, reason: collision with root package name */
    private int f16919f;

    public WildriftScene(Context context, String str) {
        super(context, str);
        this.f16918e = this.f16844b.getResources().getDimensionPixelOffset(R.dimen.low_sugar_wildrift_events_crop_startX) + LowSugarUtils.I;
        this.f16919f = this.f16844b.getResources().getDimensionPixelOffset(R.dimen.low_sugar_wildrift_events_crop_startY);
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.GameBaseScene
    public Bitmap a(Bitmap bitmap, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (i2 != 5) {
            return Bitmap.createBitmap(bitmap);
        }
        int i3 = this.f16918e;
        int i4 = this.f16919f;
        return Bitmap.createBitmap(bitmap, i3, i4, width - i3, height - i4);
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.GameBaseScene
    public Rect b(Bitmap bitmap, int i2) {
        if (i2 != 5 || bitmap == null) {
            return null;
        }
        return new Rect(this.f16918e, this.f16919f, bitmap.getWidth(), bitmap.getHeight());
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.GameBaseScene
    public String c(int i2) {
        return "国际版英雄联盟";
    }
}
