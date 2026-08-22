package com.zte.gameassist.lowsugar.detect.scene.PubgGlobal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import com.zte.gameassist.lowsugar.R;
import com.zte.gameassist.lowsugar.detect.scene.GameBaseScene;

/* loaded from: classes2.dex */
public class PubgGlobalScene extends GameBaseScene {

    /* renamed from: e, reason: collision with root package name */
    private int f16871e;

    public PubgGlobalScene(Context context, String str) {
        super(context, str);
        this.f16871e = this.f16844b.getResources().getDimensionPixelOffset(R.dimen.low_sugar_pubg_events_crop_width);
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.GameBaseScene
    public Bitmap a(Bitmap bitmap, int i2) {
        return i2 == 9 ? Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth() - this.f16871e, bitmap.getHeight()) : Bitmap.createBitmap(bitmap);
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.GameBaseScene
    public Rect b(Bitmap bitmap, int i2) {
        if (i2 != 9 || bitmap == null) {
            return null;
        }
        return new Rect(0, 0, bitmap.getWidth() - this.f16871e, bitmap.getHeight());
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.GameBaseScene
    public String c(int i2) {
        return "国际版和平精英";
    }
}
