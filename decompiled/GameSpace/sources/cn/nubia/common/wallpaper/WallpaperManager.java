package cn.nubia.common.wallpaper;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import cn.nubia.common.CommonApplication;
import cn.nubia.common.util.FileOperator;
import cn.nubia.common.util.SharedPreferencesUtil;
import cn.nubia.common.util.WorkThread;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class WallpaperManager {
    public static final String ASSETS_PREFIX = "file:///android_asset/";
    public static final int ATMOSPHERE_DYNAMIC = 12;
    public static final int ATMOSPHERE_IMAGE = 11;
    private static final String CUSTOM_WALLPAPER_DIR = "wallpaper_gallery";
    public static final int START_ANIM = 99;
    public static final String TAG = "wallpaper";
    public static final int WALLPAPER_GALLERY = 0;
    public static final int WALLPAPER_IMAGE = 2;
    public static final int WALLPAPER_LIVE = 1;
    private final int DEFAULT_ID;
    final String START_ANIM_URL;
    private final int WALLPAPER_COUNT;
    private int mAtmosphereType;
    String mAtmosphereUrl;
    WeakReference<Runnable> mCallbackRef;
    private Context mContext;
    private Bitmap mCropBitmap;
    private int mCurrentType;
    private int mCurrentWallpaperId;
    private Bitmap mGalleryBitmap;
    private int mLastWallpaperId;
    CopyOnWriteArrayList<OnWallpaperChangedListener> mListeners;
    CopyOnWriteArrayList<WallpaperSwitcher> mSwitchers;
    private File mWallpaperFile;
    ArrayList<WallpaperItemBean> mWallpaperList;
    private int mWallpaperType;

    public interface OnWallpaperChangedListener {
        void onWallpaperChanged();
    }

    private static class WallpaperManagerHolder {
        public static final WallpaperManager INSTANCE = new WallpaperManager();

        private WallpaperManagerHolder() {
        }
    }

    public interface WallpaperSwitcher {
        void switchWallpaper(int i, String str, boolean z);
    }

    private WallpaperManager() {
        this.mCurrentType = 2;
        this.mWallpaperType = 2;
        this.mAtmosphereType = 11;
        this.DEFAULT_ID = 1;
        this.WALLPAPER_COUNT = 5;
        this.mCurrentWallpaperId = 1;
        this.mLastWallpaperId = 1;
        this.mGalleryBitmap = null;
        this.mCropBitmap = null;
        this.mListeners = new CopyOnWriteArrayList<>();
        this.mWallpaperList = new ArrayList<>();
        this.mSwitchers = new CopyOnWriteArrayList<>();
        this.START_ANIM_URL = "asset:///start_animation.mp4";
        this.mContext = CommonApplication.getInstance().getAppContext();
        this.mCurrentWallpaperId = getRecordWallpaperId();
        Log.d("wallpaper", "WallpaperManager() mCurrentWallpaperId : " + this.mCurrentWallpaperId);
        getWallpaperList();
        updateWallpaperType();
    }

    private void forceStopStartIfNeed(boolean z) {
        if (z) {
            Log.d("wallpaper", "forceStopStartIfNeed()");
            notifyAnimPlayEnd();
        }
    }

    public static WallpaperManager getInstance() {
        return WallpaperManagerHolder.INSTANCE;
    }

    private int getRecordWallpaperId() {
        int wallpaperId = SharedPreferencesUtil.getInstance(this.mContext).getWallpaperId(1);
        Log.d("wallpaper", "getRecordWallpaperId() sp id : " + wallpaperId);
        return (wallpaperId < 0 || wallpaperId >= 5) ? this.mCurrentWallpaperId : wallpaperId;
    }

    private File getWallpaperFile() {
        if (this.mWallpaperFile == null) {
            this.mWallpaperFile = this.mContext.getApplicationContext().getDir(CUSTOM_WALLPAPER_DIR, 0);
        }
        if (!this.mWallpaperFile.exists()) {
            this.mWallpaperFile.mkdirs();
        }
        return this.mWallpaperFile;
    }

    private boolean isDisplayingAtmosphere() {
        int i = this.mCurrentType;
        return i == 12 || i == 11;
    }

    private boolean isSelected(int i) {
        return this.mCurrentWallpaperId == i;
    }

    private void saveCropGalleryIfNeed() {
        Log.d("wallpaper", "saveCropGalleryIfNeed()");
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.common.wallpaper.WallpaperManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                WallpaperManager.this.saveBitmapToStorage();
            }
        });
    }

    private void updateWallpaperType() {
        this.mWallpaperType = getWallpaperItem(this.mCurrentWallpaperId).getType();
        Log.d("wallpaper", "updateWallpaperType() mWallpaperType : " + this.mWallpaperType + ", mCurrentType : " + getType());
    }

    public void clearAtmosphereUrl() {
        this.mAtmosphereUrl = null;
    }

    public void clearCropBitmap() {
        Bitmap bitmap = this.mCropBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mCropBitmap = null;
        }
    }

    public String getAtmosphereUrl() {
        return TextUtils.isEmpty(this.mAtmosphereUrl) ? WallpaperList.LOCAL_WALLPAPER_LIST.get(0) : this.mAtmosphereUrl;
    }

    public Bitmap getCropBitmap() {
        return this.mCropBitmap;
    }

    public String getCurrentUrl() {
        return isStartAnim() ? "asset:///start_animation.mp4" : isDisplayingAtmosphere() ? getAtmosphereUrl() : getWallpaperUrl();
    }

    public int getCurrentWallpaperId() {
        return this.mCurrentWallpaperId;
    }

    public Bitmap getGalleryBitmap() {
        return this.mGalleryBitmap;
    }

    public String getGalleryWallpaperUrl() {
        return getWallpaperFile().getAbsolutePath() + "/gallery_wallpaper.png";
    }

    public int getLastWallpaperId() {
        return this.mLastWallpaperId;
    }

    public int getType() {
        return this.mCurrentType;
    }

    public WallpaperItemBean getWallpaperItem(int i) {
        if (i >= getWallpaperList().size()) {
            i = getWallpaperList().size() - 1;
        }
        return getWallpaperList().get(i);
    }

    public ArrayList<WallpaperItemBean> getWallpaperList() {
        if (this.mWallpaperList.size() > 0) {
            return this.mWallpaperList;
        }
        ArrayList<WallpaperItemBean> arrayList = new ArrayList<>();
        this.mWallpaperList = arrayList;
        arrayList.add(new WallpaperItemBean(0, this.mWallpaperList.size(), "file:///android_asset/wallpaper_gallery.png", getGalleryWallpaperUrl(), isSelected(this.mWallpaperList.size())));
        Iterator<String> it = WallpaperList.LOCAL_WALLPAPER_LIST.iterator();
        while (it.hasNext()) {
            this.mWallpaperList.add(new WallpaperItemBean(2, this.mWallpaperList.size(), null, it.next(), isSelected(this.mWallpaperList.size())));
        }
        return this.mWallpaperList;
    }

    public int getWallpaperType() {
        return this.mWallpaperType;
    }

    public String getWallpaperUrl() {
        return this.mWallpaperList.get(this.mCurrentWallpaperId).getWallpaperUrl();
    }

    public boolean hasWallpaperBitmap() {
        return this.mCurrentWallpaperId == 0 && getGalleryBitmap() != null;
    }

    public boolean isAtmosphereImage() {
        return getType() == 11;
    }

    public boolean isAtmosphereUrlNull() {
        return this.mAtmosphereUrl == null;
    }

    public boolean isImageWallpaper() {
        return getWallpaperType() == 2;
    }

    public boolean isStartAnim() {
        return getType() == 99;
    }

    public void notifyAnimPlayEnd() {
        Log.d("wallpaper", "notifyAnimPlayEnd()");
        WeakReference<Runnable> weakReference = this.mCallbackRef;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.mCallbackRef.get().run();
    }

    public void notifyWallpaperChanged() {
        if (this.mListeners.size() == 0) {
            return;
        }
        Iterator<OnWallpaperChangedListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onWallpaperChanged();
        }
    }

    public void registerWallpaperChangedListener(OnWallpaperChangedListener onWallpaperChangedListener) {
        if (this.mListeners.contains(onWallpaperChangedListener)) {
            return;
        }
        this.mListeners.add(onWallpaperChangedListener);
    }

    public void removeStartAnimCallback() {
        Log.d("wallpaper", "removeStartAnimCallback()");
        this.mCallbackRef = null;
    }

    public void removeWallpaperSwitcher(WallpaperSwitcher wallpaperSwitcher) {
        this.mSwitchers.remove(wallpaperSwitcher);
    }

    public void resetWallpaperType() {
        this.mCurrentType = this.mWallpaperType;
    }

    public String saveBitmapToStorage() {
        Bitmap galleryBitmap = getGalleryBitmap();
        FileOperator.deleteDir(getWallpaperFile().getAbsolutePath());
        File file = new File(getWallpaperFile(), "gallery_wallpaper.png");
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            if (galleryBitmap != null) {
                Bitmap.createBitmap(galleryBitmap, 0, 0, galleryBitmap.getWidth(), galleryBitmap.getHeight()).compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String absolutePath = file.getAbsolutePath();
        Log.d("wallpaper", "saveBitmapToStorage() path : " + absolutePath + ", url : " + getGalleryWallpaperUrl());
        getWallpaperItem(0).setWallpaperUrl(absolutePath);
        return absolutePath;
    }

    public void setCropBitmap(Bitmap bitmap) {
        this.mCropBitmap = bitmap;
    }

    public void setGalleryBitmap(Bitmap bitmap) {
        this.mGalleryBitmap = bitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            this.mGalleryBitmap = getCropBitmap();
            Log.d("wallpaper", "setGalleryBitmap() but crop bm exception ");
        }
        Log.d("wallpaper", "setGalleryBitmap() mCurrentWallpaperId : " + this.mCurrentWallpaperId);
        saveCropGalleryIfNeed();
    }

    public void setStartAnimCallback(Runnable runnable) {
        this.mCallbackRef = new WeakReference<>(runnable);
        Log.d("wallpaper", "setStartAnimCallback() runnable : " + runnable);
    }

    public void setWallpaperId(int i) {
        if (i < 0 || i >= getWallpaperList().size()) {
            return;
        }
        Log.d("wallpaper", "setWallpaperId() id : " + i);
        getWallpaperItem(this.mCurrentWallpaperId).setSelected(false);
        this.mLastWallpaperId = this.mCurrentWallpaperId;
        this.mCurrentWallpaperId = i;
        getWallpaperItem(i).setSelected(true);
        updateWallpaperType();
        if (this.mCurrentWallpaperId >= 0 && this.mCurrentType < 11) {
            resetWallpaperType();
        }
        notifyWallpaperChanged();
        SharedPreferencesUtil.getInstance(this.mContext).setWallpaperId(this.mCurrentWallpaperId);
    }

    public void setWallpaperSwitcher(WallpaperSwitcher wallpaperSwitcher) {
        this.mSwitchers.clear();
        this.mSwitchers.add(wallpaperSwitcher);
    }

    public void switchToAtmosphere() {
        Log.d("wallpaper", "switchToAtmosphere()");
        switchWallpaper(this.mAtmosphereType, null);
    }

    public void switchToStartAnim() {
        Log.d("wallpaper", "switchToStartAnim()");
        switchWallpaper(99, null);
    }

    public void switchToWallpaper() {
        Log.d("wallpaper", "switchToWallpaper()");
        switchWallpaper(this.mWallpaperType, null);
    }

    public void switchWallpaper(int i, String str) {
        boolean z;
        Log.d("wallpaper", "switchWallpaper(" + i + "," + str + ") mCurrentType : " + this.mCurrentType + ", mWallpaperType : " + this.mWallpaperType);
        if (this.mSwitchers.isEmpty()) {
            Log.d("wallpaper", "switchWallpaper() but mSwitchers is empty !");
            return;
        }
        WallpaperSwitcher wallpaperSwitcher = this.mSwitchers.get(0);
        if (wallpaperSwitcher == null) {
            Log.d("wallpaper", "switchWallpaper() but switcher is null !");
            return;
        }
        if (this.mCurrentType == i && i == 12 && str != null && str.equals(this.mAtmosphereUrl)) {
            Log.d("wallpaper", "switchWallpaper() but url is same !");
            z = true;
        } else {
            z = false;
        }
        if (i == 0 || i == 1 || i == 2) {
            forceStopStartIfNeed(this.mCurrentType == 99);
            this.mCurrentType = this.mWallpaperType;
        } else if (i == 11 || i == 12) {
            if (str != null) {
                this.mAtmosphereUrl = str;
            } else {
                Log.d("wallpaper", "switchWallpaper() but url is null, is from tab ?");
            }
            forceStopStartIfNeed(this.mCurrentType == 99);
            this.mAtmosphereType = i;
            this.mCurrentType = i;
        } else if (i == 99) {
            this.mCurrentType = i;
        }
        wallpaperSwitcher.switchWallpaper(i, str, z);
    }

    public void unregisterWallpaperChangedListener(OnWallpaperChangedListener onWallpaperChangedListener) {
        if (this.mListeners.contains(onWallpaperChangedListener)) {
            this.mListeners.remove(onWallpaperChangedListener);
        }
    }
}
