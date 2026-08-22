package cn.nubia.gamelauncher.recycler;

import android.content.Context;
import android.graphics.Bitmap;
import cn.nubia.common.util.BitmapUtils;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.R;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/* loaded from: classes.dex */
public class BannerCardTransformation extends BitmapTransformation {
    public static final int TYPE_BANNER = 1;
    public static final int TYPE_HANDHELD = 7;
    public static final int TYPE_HOST = 5;
    public static final int TYPE_HOST_RECENT = 6;
    public static final int TYPE_MEDIUM = 2;
    public static final int TYPE_RECENT = 4;
    public static final int TYPE_SMALL = 3;
    private int mType;

    public BannerCardTransformation(int i) {
        super(GameLauncherApplication.getAppContext());
        this.mType = i;
    }

    private Context getContext() {
        return GameLauncherApplication.getAppContext();
    }

    public int getCardHeight(int i) {
        switch (i) {
            case 1:
                return getContext().getResources().getDimensionPixelSize(R.dimen.game_card_height);
            case 2:
            case 3:
                return getContext().getResources().getDimensionPixelSize(R.dimen.game_card_height_small);
            case 4:
                return getContext().getResources().getDimensionPixelSize(R.dimen.recent_card_height);
            case 5:
                return getContext().getResources().getDimensionPixelSize(R.dimen.host_item_banner_height);
            case 6:
                return getContext().getResources().getDimensionPixelSize(R.dimen.host_recent_height);
            default:
                return 1;
        }
    }

    public int getCardWidth(int i) {
        switch (i) {
            case 1:
                return getContext().getResources().getDimensionPixelSize(R.dimen.game_card_width);
            case 2:
                return getContext().getResources().getDimensionPixelSize(R.dimen.game_card_width_medium);
            case 3:
                return getContext().getResources().getDimensionPixelSize(R.dimen.game_card_width_small);
            case 4:
                return getContext().getResources().getDimensionPixelSize(R.dimen.recent_card_width);
            case 5:
                return getContext().getResources().getDimensionPixelSize(R.dimen.host_item_banner_width);
            case 6:
                return getContext().getResources().getDimensionPixelSize(R.dimen.host_recent_width);
            default:
                return 1;
        }
    }

    public int getCropTranslateY() {
        switch (this.mType) {
            case 1:
            case 5:
            case 6:
                return getContext().getResources().getDimensionPixelSize(R.dimen.game_card_crop_translate_y);
            case 2:
            case 3:
            case 4:
                return 0;
            default:
                return -1;
        }
    }

    @Override // com.bumptech.glide.load.Transformation
    public String getId() {
        return "BannerCardTransformation";
    }

    public Bitmap transform(Bitmap bitmap, int i) {
        switch (i) {
            case 1:
                return BitmapUtils.getRoundCropBitmapByShader(bitmap, getCardWidth(i), getCardHeight(i), 0, 0, getCropTranslateY());
            case 2:
            case 3:
            case 4:
                return BitmapUtils.getCropBitmapTop(bitmap, getCardWidth(this.mType), getCardHeight(this.mType), 20, 0);
            case 5:
                return BitmapUtils.getCropBitmapCenter(bitmap, getCardWidth(i), getCardHeight(i), 5, 0);
            case 6:
                return BitmapUtils.getCropBitmapTop(bitmap, getCardWidth(i), getCardHeight(i), 10, 0);
            default:
                return bitmap;
        }
    }

    @Override // com.bumptech.glide.load.resource.bitmap.BitmapTransformation
    protected Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        return transform(bitmap, this.mType);
    }
}
