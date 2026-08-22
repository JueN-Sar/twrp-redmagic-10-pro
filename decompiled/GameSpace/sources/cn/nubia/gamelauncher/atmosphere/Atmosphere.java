package cn.nubia.gamelauncher.atmosphere;

import android.content.pm.ShortcutInfo;
import android.text.TextUtils;
import android.util.Log;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.wallpaper.WallpaperList;
import cn.nubia.gamelauncher.helper.ShortCutHelper;
import cn.nubia.gamelauncher.model.AppAddModel;

/* loaded from: classes.dex */
public class Atmosphere {
    public static final String CROP_DIR = "/storage/emulated/0/Android/data/cn.nubia.gamelauncher/files/custom_image/";
    public static final String CUSTOM_DIR = "custom_image";
    public static final String TAG = "Atmosphere";
    public static final String TYPE_CROP = "crop";
    public static final String TYPE_CURRENT = "current";
    public static final String TYPE_GALLERY = "gallery";
    public static final String TYPE_HIGHLIGHT = "highlight";
    public static final String TYPE_LOCAL = "local";
    public static final String TYPE_NET = "net";
    private String mCropUrl;
    private String mHighLightUrl;
    private String mName;
    private String mNetUrl;
    private String mPackage;
    private ShortcutInfo mShortcutInfo;
    String mType;

    public Atmosphere() {
        this(TYPE_NET);
    }

    public Atmosphere(String str) {
        this.mPackage = "";
        this.mName = "";
        this.mShortcutInfo = null;
        this.mType = str;
    }

    private void initByType(String str, String str2) {
        Log.d("Atmosphere", "initByType(" + this.mName + ")");
        setType(str, true);
        initUrlByType(str, str2);
    }

    private void initByUrl(String str) {
        if (str.contains(CUSTOM_DIR)) {
            setType(TYPE_CROP, true);
            setCropUrl(str);
        } else if (str.startsWith("http")) {
            setType(TYPE_NET, true);
            setNetUrl(str);
        } else if (str.endsWith(".mp4")) {
            setHighLightUrl(str);
        } else {
            Log.d("Atmosphere", "initByUrl(" + this.mName + ") error, url : " + str);
        }
    }

    private void initUrlByType(String str, String str2) {
        setUrl(str, str2, true);
    }

    public static boolean isDefaultUrl(String str) {
        return WallpaperList.LOCAL_WALLPAPER_LIST.get(0).equals(str);
    }

    public String getCropUrl() {
        if (CommonUtil.isSecurePath(this.mCropUrl)) {
            return this.mCropUrl;
        }
        return null;
    }

    public String getCurrentDisplayUrl() {
        String urlByType = getUrlByType();
        return TextUtils.isEmpty(urlByType) ? getDefaultUrl() : urlByType;
    }

    public String getCurrentUrl() {
        String urlByType = getUrlByType();
        if (!TextUtils.isEmpty(urlByType) || isCurrentHighLightType()) {
            Log.d("Atmosphere", "getCurrentUrl(" + this.mName + ") mType : " + this.mType + ", url : " + urlByType);
            return urlByType;
        }
        String defaultUrl = getDefaultUrl();
        Log.d("Atmosphere", "getCurrentUrl(" + this.mName + ") mType : " + this.mType + ", url is null, return the default : " + defaultUrl);
        return defaultUrl;
    }

    public String getDefaultUrl() {
        return WallpaperList.LOCAL_WALLPAPER_LIST.get(0);
    }

    public String getGridUrl() {
        return !isCurrentHighLightType() ? getCurrentDisplayUrl() : TextUtils.isEmpty(this.mNetUrl) ? this.mNetUrl : getDefaultUrl();
    }

    public String getHighLightUrl() {
        return LiveAtmosphereManager.getInstance().findLiveAtmosphereUrl(this.mPackage);
    }

    public String getName() {
        return this.mName;
    }

    public String getNetUrl() {
        return this.mNetUrl;
    }

    public String getPackageName() {
        return this.mPackage;
    }

    public ShortcutInfo getShortcutInfo() {
        return this.mShortcutInfo;
    }

    public String getType() {
        return this.mType;
    }

    public String getUrlByType() {
        String str = this.mType;
        str.hashCode();
        switch (str) {
            case "highlight":
                return getHighLightUrl();
            case "net":
                return getNetUrl();
            case "crop":
                return getCropUrl();
            default:
                return null;
        }
    }

    public void initTypeAndUrl(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            initByType(str, str2);
        } else if (TextUtils.isEmpty(str2)) {
            Log.d("Atmosphere", "initTypeAndUrl(" + getType() + ") type : " + str + ", url : " + str2);
        } else {
            initByUrl(str2);
        }
    }

    public boolean isCurrentHighLightType() {
        return isHighLightType(getType());
    }

    public boolean isCurrentNetType() {
        return isNetType(getType());
    }

    public boolean isHighLightAtmosphereValid() {
        if (TYPE_HIGHLIGHT.equals(getType())) {
            return LiveAtmosphereManager.getInstance().hasValidUrl(getPackageName());
        }
        return false;
    }

    public boolean isHighLightType(String str) {
        return TYPE_HIGHLIGHT.equals(str);
    }

    public boolean isNetType(String str) {
        return TYPE_NET.equals(str);
    }

    public boolean isShortcut() {
        return this.mShortcutInfo != null;
    }

    public void onAtmosphereChanged() {
        Log.d("Atmosphere", "onAtmosphereChanged(" + getType() + ") url : " + getCurrentUrl());
    }

    public void setCropUrl(String str) {
        Log.d("Atmosphere", "setCropUrl() url : " + str);
        setCropUrl(str, false);
    }

    public void setCropUrl(String str, boolean z) {
        if (TextUtils.isEmpty(str) || str.equals(this.mCropUrl)) {
            return;
        }
        Log.d("Atmosphere", "setCropUrl() url : " + str);
        this.mCropUrl = str;
        if (z) {
            return;
        }
        updateDbUrl();
    }

    public void setHighLightUrl(String str) {
        Log.d("Atmosphere", "setHighLightUrl() url : " + str);
        if (str != null && LiveAtmosphereManager.getInstance().isAtmosphereUrlInvalid(str)) {
            this.mHighLightUrl = str;
        }
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setNetUrl(String str) {
        setNetUrl(str, false);
    }

    public void setNetUrl(String str, boolean z) {
        Log.d("Atmosphere", "setNetUrl() url : " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if ((str.contains("http") || str.contains("https")) && !str.equals(this.mNetUrl)) {
            this.mNetUrl = str;
            if (z) {
                return;
            }
            updateDbUrl();
        }
    }

    public void setPackageName(String str) {
        this.mPackage = str;
    }

    public void setShortcutInfo(ShortcutInfo shortcutInfo) {
        this.mShortcutInfo = shortcutInfo;
        Log.d("Atmosphere", "setShortcutInfo() info : " + ((Object) (isShortcut() ? shortcutInfo.getShortLabel() : null)));
    }

    public void setType(String str, boolean z) {
        Log.d("Atmosphere", "setType() type : " + str + ", current : " + getType());
        if (TextUtils.isEmpty(str) || str.equals(this.mType)) {
            return;
        }
        if (str.equals(TYPE_NET) || str.equals(TYPE_HIGHLIGHT) || str.equals(TYPE_CROP)) {
            this.mType = str;
            if (!z) {
                updateDbUrl();
            }
            onAtmosphereChanged();
        }
    }

    public void setUrl(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
        }
        str.hashCode();
        switch (str) {
            case "highlight":
                setHighLightUrl(str2);
                break;
            case "net":
                setNetUrl(str2, z);
                break;
            case "crop":
                setCropUrl(str2, z);
                break;
        }
    }

    public String toString() {
        return "\nAtmosphere{(" + this.mName + ") -> \n mPackage : " + getPackageName() + "\n isShortcut = " + isShortcut() + "\n type.current = " + getType() + "\n url.current = " + getCurrentUrl() + "\n url.net = " + getNetUrl() + "\n url.crop = " + getCropUrl() + "\n url.highlight = " + getHighLightUrl() + "\n}";
    }

    public void updateDbUrl() {
        Log.d("Atmosphere", "updateDbUrl() this : " + this);
        if (isShortcut()) {
            Log.d("Atmosphere", "updateDbUrl(" + getName() + ") -------> updateShortcutInDB()");
            ShortCutHelper.getInstance().updateShortcutInDB(getShortcutInfo());
        } else {
            Log.d("Atmosphere", "updateDbUrl(" + getPackageName() + ") -------> updateAppItemBeanInAppAddDB()");
            AppAddModel.getInstance().updateAppItemBeanInAppAddDB(getPackageName());
        }
    }
}
