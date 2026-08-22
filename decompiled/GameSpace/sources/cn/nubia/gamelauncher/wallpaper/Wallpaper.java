package cn.nubia.gamelauncher.wallpaper;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import cn.nubia.common.helper.ExoPlayerManager;
import cn.nubia.common.helper.ImageCache;
import cn.nubia.common.util.BitmapUtils;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.common.view.DynamicView;
import cn.nubia.common.wallpaper.WallpaperManager;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.atmosphere.Atmosphere;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gamelauncher.util.Util;
import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class Wallpaper extends FrameLayout implements WallpaperManager.OnWallpaperChangedListener {
    public static PathInterpolator ANIM_BG_INTERPOLATOR = new PathInterpolator(0.42f, 0.0f, 0.58f, 1.0f);
    public static final String TAG = "wallpaper";
    private boolean isStartAnimPlaying;
    ObjectAnimator mBgAnim;
    int mBgAnimDuration;
    float mBgMaxScale;
    Context mContext;
    DynamicView mDynamicView;
    ImageView mImageView;
    View mLastView;
    RequestListener mListener;

    public Wallpaper(Context context) {
        this(context, null);
    }

    public Wallpaper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBgMaxScale = 1.25f;
        this.mBgAnimDuration = 300;
        this.isStartAnimPlaying = false;
        this.mListener = new RequestListener<String, GlideDrawable>() { // from class: cn.nubia.gamelauncher.wallpaper.Wallpaper.1
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onException(Exception exc, String str, Target<GlideDrawable> target, boolean z) {
                Log.d("wallpaper", "glide load(url) -> onException e : " + exc);
                WallpaperManager.getInstance().setWallpaperId(1);
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(GlideDrawable glideDrawable, String str, Target<GlideDrawable> target, boolean z, boolean z2) {
                Log.d("wallpaper", "glide load(url) -> onResourceReady()");
                return false;
            }
        };
        this.mContext = context;
        WallpaperManager.getInstance().setWallpaperSwitcher(new Wallpaper$$ExternalSyntheticLambda0(this));
    }

    private void addViewToWallpaper(View view) {
        Log.d("wallpaper", "addViewToWallpaper(s) view : " + view + ", last : " + this.mLastView);
        if (view.getParent() != null || view == this.mLastView) {
            return;
        }
        Log.d("wallpaper", "addViewToWallpaper(g) view : " + view + ", last : " + this.mLastView);
        releaseLastView();
        addView(view, 0);
        this.mLastView = view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkUrl(String str) {
        if (str == null) {
            return true;
        }
        return str.equals(WallpaperManager.getInstance().getCurrentUrl());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap cropResourceAtmosphere(Bitmap bitmap) {
        int i;
        int i2;
        int width = Util.getDisplayRect(this.mContext).width();
        int height = Util.getDisplayRect(this.mContext).height();
        if (width < height) {
            i2 = height;
            i = width;
        } else {
            i = height;
            i2 = width;
        }
        Log.d("wallpaper", "cropResourceAtmosphere() --- onResourceReady() W = " + i2 + " H = " + i);
        return BitmapUtils.getRoundCropBitmapByShader(bitmap, i2, i, 0, 0, 0);
    }

    private Bitmap cropResourceBg(Bitmap bitmap) {
        int i;
        int i2;
        int width = Util.getDisplayRect(this.mContext).width();
        int height = Util.getDisplayRect(this.mContext).height();
        if (width < height) {
            i2 = height;
            i = width;
        } else {
            i = height;
            i2 = width;
        }
        Log.d("wallpaper", "cropResourceBg() --- onResourceReady() W = " + i2 + " H = " + i);
        return BitmapUtils.getRoundCropBitmapByShader(bitmap, i2, i, 0, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doUpdateBgAnim(View view) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_X, this.mBgMaxScale, 1.0f), PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_Y, this.mBgMaxScale, 1.0f));
        this.mBgAnim = ofPropertyValuesHolder;
        ofPropertyValuesHolder.setInterpolator(ANIM_BG_INTERPOLATOR);
        this.mBgAnim.setDuration(this.mBgAnimDuration);
        this.mBgAnim.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doVibrate() {
        Util.doMyOsVibrateOfHe(R.raw.start_animation_vibrate);
    }

    private Drawable getFullMask() {
        return getResources().getDrawable(R.drawable.wallpaper_mask, null);
    }

    private FrameLayout.LayoutParams getParams() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.height = Util.getDisplayLandscapeHeight(getContext());
        layoutParams.width = Util.getDisplayLandscapeWidth(getContext());
        Log.d("wallpaper", "getParams() params (" + layoutParams.width + ", " + layoutParams.height + ")");
        return layoutParams;
    }

    private void initDynamicView() {
        if (this.mDynamicView == null) {
            DynamicView dynamicView = new DynamicView(this.mContext);
            this.mDynamicView = dynamicView;
            dynamicView.setLayoutParams(getParams());
        }
        String currentUrl = WallpaperManager.getInstance().getCurrentUrl();
        if (isStartAnim()) {
            this.isStartAnimPlaying = true;
            this.mDynamicView.setRepeated(false);
            if (Build.DEVICE.contains("PQ84P01") || Build.DEVICE.contains("PQ85P01")) {
                postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.wallpaper.Wallpaper.2
                    @Override // java.lang.Runnable
                    public void run() {
                        Wallpaper.this.doVibrate();
                    }
                }, 150L);
            } else {
                doVibrate();
            }
            this.mDynamicView.setMute(false);
        } else {
            this.mDynamicView.setRepeated(true);
            this.mDynamicView.setMute(true);
            this.mDynamicView.setForeground(getFullMask());
        }
        this.mDynamicView.setUri(currentUrl);
        Log.d("wallpaper", "initDynamicView() url : " + currentUrl);
    }

    private void initImageView() {
        if (this.mImageView != null) {
            return;
        }
        Log.d("wallpaper", "initImageView()");
        ImageView imageView = new ImageView(this.mContext);
        this.mImageView = imageView;
        imageView.setLayoutParams(getParams());
    }

    private static boolean is2748_1172(Context context) {
        try {
            int width = Util.getDisplayRect(context).width();
            int height = Util.getDisplayRect(context).height();
            if (Util.SCREEN_WIDTH_OR_HEIGHT_IS_2748 != width || height != Util.SCREEN_WIDTH_OR_HEIGHT_IS_1172) {
                if (Util.SCREEN_WIDTH_OR_HEIGHT_IS_1172 != width) {
                    return false;
                }
                if (height != Util.SCREEN_WIDTH_OR_HEIGHT_IS_2748) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isDynamicPlaying() {
        DynamicView dynamicView = this.mDynamicView;
        if (dynamicView == null || dynamicView.getPlayer() == null) {
            return false;
        }
        return this.mDynamicView.getPlayer().isPlaying();
    }

    private boolean isStartAnim() {
        return WallpaperManager.getInstance().isStartAnim();
    }

    private void loadAtmosphere() {
        Log.d("wallpaper", "loadAtmosphere()");
        initImageView();
        addViewToWallpaper(this.mImageView);
        this.mImageView.setForeground(getFullMask());
        if (isContextValid(this.mContext)) {
            final String atmosphereUrl = WallpaperManager.getInstance().getAtmosphereUrl();
            Log.d("wallpaper", "loadAtmosphere() url : " + atmosphereUrl);
            if (atmosphereUrl == null) {
                return;
            }
            ObjectAnimator objectAnimator = this.mBgAnim;
            if (objectAnimator != null && objectAnimator.isRunning()) {
                this.mBgAnim.cancel();
                this.mImageView.setScaleX(1.0f);
                this.mImageView.setScaleY(1.0f);
            }
            Bitmap bitmap = ImageCache.getInstance().get(atmosphereUrl);
            Log.d("wallpaper", "loadAtmosphere() url : " + atmosphereUrl);
            if (bitmap != null) {
                try {
                    if (!bitmap.isRecycled()) {
                        if (isContextValid(this.mContext)) {
                            realUpdateAtmosphere(cropResourceBg(bitmap), true);
                            Log.d("wallpaper", "loadAtmosphere() set bitmap to imageView and bm : " + bitmap);
                            return;
                        }
                        return;
                    }
                } catch (IllegalArgumentException unused) {
                    Log.d("wallpaper", "<--IllegalArgumentException-->");
                }
            }
            if (is2748_1172(this.mContext) && !TextUtils.isEmpty(atmosphereUrl) && (atmosphereUrl.contains("wallpaper_0") || atmosphereUrl.contains("wallpaper_9900"))) {
                Glide.with(this.mContext).load(atmosphereUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).skipMemoryCache(false).override(Util.getDisplayLandscapeWidth(this.mContext), Util.getDisplayLandscapeHeight(this.mContext)).centerCrop().listener((RequestListener<? super String, GlideDrawable>) new RequestListener<String, GlideDrawable>() { // from class: cn.nubia.gamelauncher.wallpaper.Wallpaper.3
                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onException(Exception exc, String str, Target<GlideDrawable> target, boolean z) {
                        return false;
                    }

                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onResourceReady(GlideDrawable glideDrawable, String str, Target<GlideDrawable> target, boolean z, boolean z2) {
                        Wallpaper wallpaper = Wallpaper.this;
                        wallpaper.doUpdateBgAnim(wallpaper.mImageView);
                        return false;
                    }
                }).into(this.mImageView);
                return;
            }
            this.mImageView.setImageResource(R.mipmap.wallpaper_0);
            Log.d("wallpaper", "loadAtmosphere() wallpaper cache is null");
            if (Util.isContextValid(this.mContext)) {
                Glide.with(this.mContext).load(atmosphereUrl).asBitmap().placeholder(R.mipmap.wallpaper_0).into((BitmapRequestBuilder<String, Bitmap>) new SimpleTarget<Bitmap>() { // from class: cn.nubia.gamelauncher.wallpaper.Wallpaper.4
                    public void onResourceReady(Bitmap bitmap2, GlideAnimation<? super Bitmap> glideAnimation) {
                        Log.d("wallpaper", "onResourceReady() url : " + atmosphereUrl);
                        if (Wallpaper.this.checkUrl(atmosphereUrl)) {
                            Wallpaper.this.realUpdateAtmosphere(Wallpaper.this.cropResourceAtmosphere(bitmap2), true);
                        }
                    }

                    @Override // com.bumptech.glide.request.target.Target
                    public /* bridge */ /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                        onResourceReady((Bitmap) obj, (GlideAnimation<? super Bitmap>) glideAnimation);
                    }
                });
            }
        }
    }

    private void loadImageWallpaper(boolean z) {
        initImageView();
        addViewToWallpaper(this.mImageView);
        this.mImageView.setForeground(getFullMask());
        Log.d("wallpaper", "loadImageWallpaper() mImageView(" + this.mImageView.getWidth() + ", " + this.mImageView.getHeight() + ")");
        Bitmap galleryBitmap = WallpaperManager.getInstance().getGalleryBitmap();
        if (galleryBitmap != null && !galleryBitmap.isRecycled() && z) {
            this.mImageView.setImageBitmap(galleryBitmap);
            Log.d("wallpaper", "loadImageWallpaper() set image bitmap to wallpaper ");
            return;
        }
        String wallpaperUrl = WallpaperManager.getInstance().getWallpaperUrl();
        Log.d("wallpaper", "loadImageWallpaper() url : " + wallpaperUrl);
        showExternalStorageStateIfNeed();
        if (isContextValid(this.mContext) && getRootView().getVisibility() == 0) {
            Glide.with(this.mContext).load(wallpaperUrl).diskCacheStrategy(z ? DiskCacheStrategy.NONE : DiskCacheStrategy.SOURCE).skipMemoryCache(z).override(Util.getDisplayLandscapeWidth(this.mContext), Util.getDisplayLandscapeHeight(this.mContext)).centerCrop().listener(this.mListener).into(this.mImageView);
        }
    }

    private void loadLiveWallpaper() {
        Log.d("wallpaper", "loadLiveWallpaper()");
        if (isStartAnim() && this.isStartAnimPlaying) {
            return;
        }
        releaseLastView();
        initDynamicView();
        addViewToWallpaper(this.mDynamicView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void realUpdateAtmosphere(Bitmap bitmap, boolean z) {
        Log.i("wallpaper", "realUpdateAtmosphere() doAnim : " + z);
        if (FeatureUtil.isMtk() || FeatureUtil.isSprd()) {
            LogUtil.i("wallpaper", "mtk sprd !");
        } else {
            Drawable drawable = this.mImageView.getDrawable();
            BitmapDrawable bitmapDrawable = drawable instanceof BitmapDrawable ? (BitmapDrawable) drawable : null;
            if (bitmapDrawable != null) {
                this.mImageView.setImageDrawable(null);
                Bitmap bitmap2 = bitmapDrawable.getBitmap();
                if (bitmap2 != null && !bitmap2.isRecycled()) {
                    bitmap2.recycle();
                }
            }
        }
        this.mImageView.setImageBitmap(bitmap);
        if (z) {
            doUpdateBgAnim(this.mImageView);
            ImageCache.getInstance().put(Atmosphere.TYPE_CURRENT, bitmap);
        }
    }

    private void recordWallpaperChanged() {
        int wallpaperType = WallpaperManager.getInstance().getWallpaperType();
        String wallpaperUrl = WallpaperManager.getInstance().getWallpaperUrl();
        if (wallpaperType == 0) {
            NubiaTrackManager.getInstance().sendEventEveryDay("cn.nubia.gamelauncher", "background_setting", "option", Atmosphere.TYPE_GALLERY);
            return;
        }
        if (wallpaperType == 1) {
            if (wallpaperUrl == null) {
                return;
            }
            NubiaTrackManager.getInstance().sendEventEveryDay("cn.nubia.gamelauncher", "background_setting", "option", wallpaperUrl.replace(".mp4", ""));
            return;
        }
        if (wallpaperType == 2 && wallpaperUrl != null) {
            NubiaTrackManager.getInstance().sendEventEveryDay("cn.nubia.gamelauncher", "background_setting", "option", wallpaperUrl.replace(WallpaperManager.ASSETS_PREFIX, "").replace(".png", ""));
        }
    }

    private void releaseLastView() {
        View view = this.mLastView;
        if (view == null) {
            return;
        }
        if (view instanceof DynamicView) {
            ((DynamicView) view).release();
        }
        removeView(this.mLastView);
        this.mLastView = null;
    }

    private void resetStartAnimFlag() {
        if (this.isStartAnimPlaying && !WallpaperManager.getInstance().isStartAnim()) {
            Log.d("wallpaper", "resetStartAnimFlag()");
            this.isStartAnimPlaying = false;
        }
    }

    private void showExternalStorageStateIfNeed() {
        if (WallpaperManager.getInstance().getWallpaperType() != 0) {
            return;
        }
        Log.d("wallpaper", "showExternalStorageStateIfNeed() isReady : " + Environment.getExternalStorageState().equals("mounted"));
    }

    public void exit() {
        Log.d("wallpaper", "exit()");
        WallpaperManager.getInstance().unregisterWallpaperChangedListener(this);
        WallpaperManager.getInstance().removeWallpaperSwitcher(new Wallpaper$$ExternalSyntheticLambda0(this));
        releaseDynamic();
    }

    public boolean isContextValid(Context context) {
        if (context == null) {
            return false;
        }
        if (!(context instanceof Activity)) {
            return true;
        }
        Activity activity = (Activity) context;
        return (activity.isDestroyed() || activity.isFinishing()) ? false : true;
    }

    public void load() {
        Log.d("wallpaper", "load()");
        WallpaperManager.getInstance().registerWallpaperChangedListener(this);
        WallpaperManager.getInstance().setWallpaperSwitcher(new Wallpaper$$ExternalSyntheticLambda0(this));
    }

    public void loadWallpaper() {
        int type = WallpaperManager.getInstance().getType();
        Log.d("wallpaper", "loadWallpaper() type : " + type);
        if (type != 0) {
            if (type != 1) {
                if (type != 2) {
                    if (type == 11) {
                        loadAtmosphere();
                        ExoPlayerManager.getInstance().releasePlayer(null);
                        return;
                    } else if (type != 12 && type != 99) {
                        return;
                    }
                }
            }
            loadLiveWallpaper();
            return;
        }
        loadImageWallpaper(type == 0);
        ExoPlayerManager.getInstance().releasePlayer(null);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.d("wallpaper", "onConfigurationChanged() orientation : " + configuration.orientation);
        if (configuration.orientation == 2) {
            loadWallpaper();
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 0 && WallpaperManager.getInstance().getType() == 2) {
            loadWallpaper();
        }
    }

    @Override // cn.nubia.common.wallpaper.WallpaperManager.OnWallpaperChangedListener
    public void onWallpaperChanged() {
        Log.d("wallpaper", "onWallpaperChanged()");
        loadWallpaper();
        recordWallpaperChanged();
    }

    public void releaseDynamic() {
        DynamicView dynamicView = this.mDynamicView;
        if (dynamicView == null) {
            return;
        }
        dynamicView.release();
        this.mDynamicView = null;
    }

    public void setVibratorMotor() {
        try {
            Vibrator vibrator = (Vibrator) getContext().getSystemService("vibrator");
            Method declaredMethod = Vibrator.class.getDeclaredMethod("dualVibrate", int[].class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(vibrator, new int[]{2, 2});
            Log.d("wallpaper", "======   setVibratorMotor  success  ======");
        } catch (Exception e) {
            Log.d("wallpaper", "died setMotor = " + e.toString());
            e.printStackTrace();
        }
    }

    public void startAnimEnd() {
        this.isStartAnimPlaying = false;
        WallpaperManager.getInstance().notifyAnimPlayEnd();
    }

    public void switchWallpaper(int i, String str, boolean z) {
        if (z && isDynamicPlaying()) {
            return;
        }
        setVisibility(0);
        resetStartAnimFlag();
        loadWallpaper();
    }
}
