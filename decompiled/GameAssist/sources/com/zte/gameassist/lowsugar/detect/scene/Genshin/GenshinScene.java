package com.zte.gameassist.lowsugar.detect.scene.Genshin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextUtils;
import com.zte.gameassist.lowsugar.R;
import com.zte.gameassist.lowsugar.detect.scene.GameBaseScene;
import com.zte.gameassist.lowsugar.utils.LowSugarUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/* loaded from: classes2.dex */
public class GenshinScene extends GameBaseScene {

    /* renamed from: e, reason: collision with root package name */
    private GameBaseScene.CropBitmapSize f16858e;

    /* renamed from: f, reason: collision with root package name */
    private GameBaseScene.CropBitmapSize f16859f;

    /* renamed from: g, reason: collision with root package name */
    private ArrayList f16860g;

    public GenshinScene(Context context, String str) {
        super(context, str);
        this.f16858e = new GameBaseScene.CropBitmapSize(this.f16844b.getResources().getDimensionPixelOffset(R.dimen.low_sugar_genshin_wish_crop_startX), this.f16844b.getResources().getDimensionPixelOffset(R.dimen.low_sugar_genshin_wish_crop_startY), this.f16844b.getResources().getDimensionPixelOffset(R.dimen.low_sugar_genshin_wish_crop_width), this.f16844b.getResources().getDimensionPixelOffset(R.dimen.low_sugar_genshin_wish_crop_height));
        this.f16859f = new GameBaseScene.CropBitmapSize(this.f16844b.getResources().getDimensionPixelOffset(R.dimen.low_sugar_genshin_calendar_crop_startX), this.f16844b.getResources().getDimensionPixelOffset(R.dimen.low_sugar_genshin_calendar_crop_startY), this.f16844b.getResources().getDimensionPixelOffset(R.dimen.low_sugar_genshin_calendar_crop_width), this.f16844b.getResources().getDimensionPixelOffset(R.dimen.low_sugar_genshin_calendar_crop_height));
        this.f16860g = new ArrayList(Arrays.asList("规则说明", "推荐任务", "活动说明", "前往任务", "奖励一览", "前往区域", "前置任务", "查看角色", "快速体验", "角色预览", "开始试用", "试用"));
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.GameBaseScene
    public Bitmap a(Bitmap bitmap, int i2) {
        if (i2 == 1) {
            GameBaseScene.CropBitmapSize cropBitmapSize = this.f16859f;
            return Bitmap.createBitmap(bitmap, cropBitmapSize.f16847a, cropBitmapSize.f16848b, cropBitmapSize.f16849c, cropBitmapSize.f16850d);
        }
        if (i2 != 2) {
            return Bitmap.createBitmap(bitmap);
        }
        GameBaseScene.CropBitmapSize cropBitmapSize2 = this.f16858e;
        return Bitmap.createBitmap(bitmap, cropBitmapSize2.f16847a, cropBitmapSize2.f16848b, cropBitmapSize2.f16849c, cropBitmapSize2.f16850d);
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.GameBaseScene
    public Rect b(Bitmap bitmap, int i2) {
        if (i2 == 1) {
            GameBaseScene.CropBitmapSize cropBitmapSize = this.f16859f;
            int i3 = cropBitmapSize.f16847a;
            int i4 = cropBitmapSize.f16848b;
            return new Rect(i3, i4, cropBitmapSize.f16849c + i3, cropBitmapSize.f16850d + i4);
        }
        if (i2 != 2) {
            return null;
        }
        GameBaseScene.CropBitmapSize cropBitmapSize2 = this.f16858e;
        int i5 = cropBitmapSize2.f16847a;
        int i6 = cropBitmapSize2.f16848b;
        return new Rect(i5, i6, cropBitmapSize2.f16849c + i5, cropBitmapSize2.f16850d + i6);
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.GameBaseScene
    public String c(int i2) {
        return "原神";
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.GameBaseScene
    public boolean d(String str, int i2) {
        return !TextUtils.isEmpty(str) && i2 == 1 && this.f16860g.contains(str);
    }

    @Override // com.zte.gameassist.lowsugar.detect.scene.GameBaseScene
    public boolean e(String str, int i2) {
        if (!LowSugarUtils.G) {
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!"zh".equals(Locale.getDefault().getLanguage())) {
            return true;
        }
        if (i2 == 1 || i2 == 2) {
            return str.contains("剩余时间");
        }
        return true;
    }
}
