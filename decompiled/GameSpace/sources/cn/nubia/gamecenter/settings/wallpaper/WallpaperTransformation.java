package cn.nubia.gamecenter.settings.wallpaper;

import android.content.Context;
import android.graphics.Bitmap;
import cn.nubia.common.CommonApplication;
import cn.nubia.common.util.BitmapUtils;
import cn.nubia.gamecenter.settings.R;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/* loaded from: classes.dex */
public class WallpaperTransformation extends BitmapTransformation {
    private Context mContext;
    private int mType;

    public WallpaperTransformation(Context context) {
        super(context);
        this.mContext = context.getApplicationContext();
    }

    private int getCardHeight(int i) {
        return getContext().getResources().getDimensionPixelSize(R.dimen.wallpaper_preview_height);
    }

    private int getCardWidth(int i) {
        return getContext().getResources().getDimensionPixelSize(R.dimen.wallpaper_preview_width);
    }

    private Context getContext() {
        return CommonApplication.getInstance().getAppContext();
    }

    @Override // com.bumptech.glide.load.Transformation
    public String getId() {
        return "WallpaperTransformation";
    }

    @Override // com.bumptech.glide.load.resource.bitmap.BitmapTransformation
    protected Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        return BitmapUtils.getCropBitmapCenter(bitmap, getCardWidth(i), getCardHeight(i2), this.mContext.getResources().getDimensionPixelSize(R.dimen.wallpaper_radius), 0);
    }
}
