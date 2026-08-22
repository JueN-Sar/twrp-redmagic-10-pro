package cn.nubia.gamelauncher.bean;

import android.content.pm.ShortcutInfo;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import cn.nubia.gamelauncher.atmosphere.Atmosphere;
import cn.nubia.gamelauncher.model.AppAddModel;
import cn.nubia.gamelauncher.model.AppAddModelHelper;
import cn.nubia.gamelauncher.util.CommonUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
public class AppListItemBean extends ItemBean {
    private String cardId;
    private String componentName;
    private NeoIconDownloadInfo downloadInfo;
    public boolean hasGift;
    public Bitmap icon;
    private boolean isDownloadItem;
    public boolean isFocus;
    private boolean isGame;
    public boolean isHandheldGame;
    public boolean isHide;
    public boolean isVip;
    Atmosphere mAtmosphere;
    public Bitmap mCustomImage;
    Runnable mCustomUpdateRunnable;
    private String mFrameRate;
    private String mFuncUrl;
    private boolean mHasVerify;
    private String mJumpUrl;
    private long mLastTimeUsed;
    private long mLastUpdateUrlTime;
    private String mMediumUrl;
    private int mPositionWithStartTime;
    public int mRelevantPosition;
    public ShortcutInfo mShortcutInfo;
    private long mTotalTimeInForeground;
    private long mUpdateAtmosphereTime;
    private String mUpdateTime;
    private String mWidgetUrl;
    public String name;
    private int position;
    public List<RelevantBean> relevantList;
    public boolean select;

    public AppListItemBean() {
        this.cardId = null;
        this.mPositionWithStartTime = -1;
        this.mAtmosphere = new Atmosphere();
        this.mFrameRate = "90";
        this.mLastTimeUsed = 0L;
        this.mTotalTimeInForeground = 0L;
        this.mLastUpdateUrlTime = 0L;
        this.mUpdateAtmosphereTime = 0L;
        this.mHasVerify = false;
        this.isGame = false;
        this.isDownloadItem = false;
        this.isVip = false;
        this.hasGift = false;
        this.isHide = false;
        this.isHandheldGame = false;
        this.relevantList = new ArrayList();
        this.mRelevantPosition = -1;
    }

    public AppListItemBean(Bitmap bitmap, String str, String str2, boolean z, String str3, String str4) {
        this.cardId = null;
        this.mPositionWithStartTime = -1;
        this.mAtmosphere = new Atmosphere();
        this.mFrameRate = "90";
        this.mLastTimeUsed = 0L;
        this.mTotalTimeInForeground = 0L;
        this.mLastUpdateUrlTime = 0L;
        this.mUpdateAtmosphereTime = 0L;
        this.mHasVerify = false;
        this.isGame = false;
        this.isDownloadItem = false;
        this.isVip = false;
        this.hasGift = false;
        this.isHide = false;
        this.isHandheldGame = false;
        this.relevantList = new ArrayList();
        this.mRelevantPosition = -1;
        this.icon = bitmap;
        this.name = str;
        this.select = z;
        this.componentName = str2;
        this.mAtmosphere.initTypeAndUrl(str4, str3);
        this.mAtmosphere.setPackageName(getPackageName());
        this.mAtmosphere.setName(str);
        this.mAtmosphere.setShortcutInfo(getShortcutInfo());
    }

    public AppListItemBean(String str, String str2, int i, String str3, String str4) {
        this.cardId = null;
        this.mPositionWithStartTime = -1;
        this.mAtmosphere = new Atmosphere();
        this.mFrameRate = "90";
        this.mLastTimeUsed = 0L;
        this.mTotalTimeInForeground = 0L;
        this.mLastUpdateUrlTime = 0L;
        this.mUpdateAtmosphereTime = 0L;
        this.mHasVerify = false;
        this.isGame = false;
        this.isDownloadItem = false;
        this.isVip = false;
        this.hasGift = false;
        this.isHide = false;
        this.isHandheldGame = false;
        this.relevantList = new ArrayList();
        this.mRelevantPosition = -1;
        this.cardId = str2;
        this.name = str;
        this.position = i;
        this.mAtmosphere.initTypeAndUrl(str4, str3);
        this.mAtmosphere.setName(str);
        this.mAtmosphere.setShortcutInfo(getShortcutInfo());
    }

    public AppListItemBean(String str, String str2, String str3, String str4) {
        this(null, str, str2, false, str3, str4);
    }

    public AppListItemBean(String str, String str2, String str3, String str4, ShortcutInfo shortcutInfo) {
        this.cardId = null;
        this.mPositionWithStartTime = -1;
        this.mAtmosphere = new Atmosphere();
        this.mFrameRate = "90";
        this.mLastTimeUsed = 0L;
        this.mTotalTimeInForeground = 0L;
        this.mLastUpdateUrlTime = 0L;
        this.mUpdateAtmosphereTime = 0L;
        this.mHasVerify = false;
        this.isGame = false;
        this.isDownloadItem = false;
        this.isVip = false;
        this.hasGift = false;
        this.isHide = false;
        this.isHandheldGame = false;
        this.relevantList = new ArrayList();
        this.mRelevantPosition = -1;
        this.name = str;
        this.componentName = str2;
        this.mShortcutInfo = shortcutInfo;
        this.mAtmosphere.initTypeAndUrl(str4, str3);
        this.mAtmosphere.setPackageName(getPackageName());
        this.mAtmosphere.setName(this.name);
        this.mAtmosphere.setShortcutInfo(shortcutInfo);
    }

    public AppListItemBean(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.cardId = null;
        this.mPositionWithStartTime = -1;
        this.mAtmosphere = new Atmosphere();
        this.mFrameRate = "90";
        this.mLastTimeUsed = 0L;
        this.mTotalTimeInForeground = 0L;
        this.mLastUpdateUrlTime = 0L;
        this.mUpdateAtmosphereTime = 0L;
        this.mHasVerify = false;
        this.isGame = false;
        this.isDownloadItem = false;
        this.isVip = false;
        this.hasGift = false;
        this.isHide = false;
        this.isHandheldGame = false;
        this.relevantList = new ArrayList();
        this.mRelevantPosition = -1;
        this.name = str;
        this.cardId = str2;
        this.mJumpUrl = str6;
        this.mFuncUrl = str7;
        this.mMediumUrl = str5;
        this.mAtmosphere.initTypeAndUrl(str4, str3);
        this.mAtmosphere.setName(str);
        this.mAtmosphere.setShortcutInfo(getShortcutInfo());
    }

    public Atmosphere getAtmosphere() {
        return this.mAtmosphere;
    }

    public String getAtmosphereType() {
        return this.mAtmosphere.getType();
    }

    public String getAtmosphereUrl() {
        return getAtmosphere().getCurrentDisplayUrl();
    }

    public String getCardId() {
        return this.cardId;
    }

    public String getComponentName() {
        return this.componentName;
    }

    public String getCropUrl() {
        return this.mAtmosphere.getCropUrl();
    }

    public RelevantBean getCurrentRelevant() {
        List<RelevantBean> list;
        int i = this.mRelevantPosition;
        if (i >= 0 && (list = this.relevantList) != null && i < list.size()) {
            return this.relevantList.get(this.mRelevantPosition);
        }
        return null;
    }

    public Bitmap getCustomBitmap() {
        return this.mCustomImage;
    }

    public String getDefaultUrl() {
        return this.mAtmosphere.getDefaultUrl();
    }

    public NeoIconDownloadInfo getDownloadInfo() {
        return this.downloadInfo;
    }

    public String getFrameRate() {
        return this.mFrameRate;
    }

    public String getHighLightUrl() {
        return this.mAtmosphere.getHighLightUrl();
    }

    public Bitmap getIcon() {
        return this.icon;
    }

    public long getLastStartTime() {
        return this.mLastTimeUsed;
    }

    public long getLastUpdateUrlTime() {
        return this.mLastUpdateUrlTime;
    }

    public String getMediumUrl() {
        return !isAddItem() ? getAtmosphere().getGridUrl() : this.mMediumUrl;
    }

    public String getName() {
        return this.name;
    }

    public String getNetUrl() {
        return this.mAtmosphere.getNetUrl();
    }

    public String getNextRelevantIconUrl() {
        if (!hasRelevant()) {
            return null;
        }
        int i = this.mRelevantPosition + 1;
        if (i >= this.relevantList.size()) {
            i = 0;
        }
        RelevantBean relevantBean = this.relevantList.get(i);
        this.mRelevantPosition = i;
        return relevantBean.url;
    }

    public String getPackageName() {
        return CommonUtil.convertPackageName(getComponentName());
    }

    public List<RelevantBean> getRelevantList() {
        return this.relevantList;
    }

    public int getRelevantPosition() {
        return this.mRelevantPosition;
    }

    public String getShortcutId() {
        if (isShortcut()) {
            return getShortcutInfo().getId();
        }
        return null;
    }

    public ShortcutInfo getShortcutInfo() {
        return this.mShortcutInfo;
    }

    public String getShortcutLabel() {
        if (isShortcut()) {
            return ((CharSequence) Objects.requireNonNull(getShortcutInfo().getShortLabel())).toString();
        }
        return null;
    }

    public long getTotalTimeHour() {
        if (getTotalTimeInForeground() < 60) {
            return 0L;
        }
        return getTotalTimeInForeground() / 60;
    }

    public long getTotalTimeInForeground() {
        return getTotalTimeMillisecond() / 60000;
    }

    public long getTotalTimeMillisecond() {
        return this.mTotalTimeInForeground;
    }

    public String getUpdateTime() {
        return this.mUpdateTime;
    }

    public String getWidgetUrl() {
        return this.mWidgetUrl;
    }

    public boolean hasGift() {
        return this.hasGift;
    }

    public boolean hasRelevant() {
        List<RelevantBean> list;
        return (isShortcut() || (list = this.relevantList) == null || list.size() <= 0) ? false : true;
    }

    public boolean isAddItem() {
        String componentName = getComponentName();
        return componentName != null && componentName.equals("cn.nubia.gamelauncher,cn.nubia.gamelauncher.activity.AppAddActivity");
    }

    public boolean isDownloadItem() {
        boolean z = this.downloadInfo != null;
        this.isDownloadItem = z;
        return z;
    }

    public boolean isFocusItem() {
        return this.isFocus;
    }

    public boolean isGame() {
        return this.isGame;
    }

    public boolean isHandheldGame() {
        return this.isHandheldGame;
    }

    public boolean isIntervalOverStepDay() {
        long currentTimeMillis = System.currentTimeMillis();
        Log.d("Atmosphere", "isIntervalOverStepDay(" + getName() + ") mUpdateAtmosphereTime : " + this.mUpdateAtmosphereTime);
        return currentTimeMillis - this.mUpdateAtmosphereTime > AppAddModelHelper.MIN_INTERVAL;
    }

    public boolean isLocalImage() {
        return getAtmosphereUrl() != null && getAtmosphereUrl().contains("storage");
    }

    public boolean isLoopUpdateRelevantIcon() {
        List<RelevantBean> list = this.relevantList;
        return list != null && list.size() > 1;
    }

    public boolean isOperationItem() {
        return getComponentName() == null;
    }

    public boolean isSameItem(AppListItemBean appListItemBean) {
        if (appListItemBean == null) {
            return false;
        }
        if (isShortcut() && !appListItemBean.isShortcut()) {
            return false;
        }
        if (isShortcut() || !appListItemBean.isShortcut()) {
            return isShortcut() ? isSameShortcut(appListItemBean.getShortcutInfo()) : appListItemBean.getComponentName().equals(getComponentName());
        }
        return false;
    }

    public boolean isSameItem(String str, ShortcutInfo shortcutInfo) {
        if (getComponentName().equals(str)) {
            return isSameShortcut(shortcutInfo);
        }
        return false;
    }

    public boolean isSameShortcut(ShortcutInfo shortcutInfo) {
        if (shortcutInfo == null && getShortcutInfo() == null) {
            return true;
        }
        if (shortcutInfo != null && getShortcutInfo() != null) {
            String id = shortcutInfo.getId();
            String obj = ((CharSequence) Objects.requireNonNull(shortcutInfo.getShortLabel())).toString();
            if (!id.isEmpty() && !obj.isEmpty()) {
                return id.equals(getShortcutId()) && obj.equals(getShortcutLabel());
            }
        }
        return false;
    }

    public boolean isSelect() {
        return this.select;
    }

    public boolean isShortcut() {
        return getShortcutInfo() != null;
    }

    public boolean isVip() {
        return this.isVip;
    }

    public void setAtmosphereType(String str) {
        this.mAtmosphere.setType(str, false);
    }

    public void setComponentName(String str) {
        this.componentName = str;
        this.mAtmosphere.setPackageName(getPackageName());
    }

    public void setCropUrl(String str) {
        this.mAtmosphere.setCropUrl(str);
    }

    public void setCustomBitmap(Bitmap bitmap) {
        Log.d("Full", "setCustomBitmap(" + bitmap + ") mCustomUpdateRunnable : " + this.mCustomUpdateRunnable);
        this.mCustomImage = bitmap;
        if (bitmap == null) {
            this.mCustomUpdateRunnable = null;
            return;
        }
        Runnable runnable = this.mCustomUpdateRunnable;
        if (runnable != null) {
            runnable.run();
        }
    }

    public void setCustomUpdateRunnable(Runnable runnable) {
        this.mCustomUpdateRunnable = runnable;
    }

    public void setDownloadInfo(NeoIconDownloadInfo neoIconDownloadInfo) {
        this.downloadInfo = neoIconDownloadInfo;
    }

    public void setFocus(boolean z) {
        this.isFocus = z;
    }

    public void setGame(boolean z) {
        this.isGame = z;
    }

    public void setHighLightUrl(String str) {
        this.mAtmosphere.setHighLightUrl(str);
    }

    public void setIcon(Bitmap bitmap) {
        this.icon = bitmap;
    }

    public void setImageUrl(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            str2 = Atmosphere.TYPE_NET;
        }
        this.mAtmosphere.setUrl(str2, str, false);
    }

    public void setLastStartTime(long j) {
        if (isAddItem()) {
            this.mLastTimeUsed = -1L;
        } else if (this.mLastTimeUsed != j) {
            this.mLastTimeUsed = j;
            AppAddModel.getInstance().updateAppItemBeanInAppAddDB(this);
        }
    }

    public void setLastUpdateUrlTime(long j) {
        this.mLastUpdateUrlTime = j;
    }

    public void setMediumUrl(String str) {
        this.mMediumUrl = str;
    }

    public void setName(String str) {
        this.name = str;
        this.mAtmosphere.setName(str);
    }

    public void setNetUrl(String str) {
        this.mAtmosphere.setNetUrl(str);
    }

    public void setSelect(boolean z) {
        this.select = z;
    }

    public void setShortcut(ShortcutInfo shortcutInfo) {
        this.mShortcutInfo = shortcutInfo;
        this.mAtmosphere.setShortcutInfo(shortcutInfo);
    }

    public boolean setTotalTimeInForeground(long j) {
        long totalTimeHour = getTotalTimeHour();
        boolean z = 0 == this.mTotalTimeInForeground && 0 != j;
        this.mTotalTimeInForeground = j;
        if (getTotalTimeHour() != totalTimeHour) {
            return true;
        }
        return z;
    }

    public void setWidgetUrl(String str) {
        this.mWidgetUrl = str;
    }

    public String showTimeString() {
        return "AppListItemBean{, name = " + this.name + "', pkg = " + getPackageName() + ", mLastTimeUsed = " + this.mLastTimeUsed + ", mPositionWithStartTime = " + this.mPositionWithStartTime + ", total = " + getTotalTimeMillisecond() + '}';
    }

    public String toString() {
        return "AppListItemBean{cardId='" + this.cardId + "', position=" + this.position + ", icon=" + this.icon + ", name='" + this.name + "', select=" + this.select + ", componentName='" + this.componentName + "', mMediumUrl='" + this.mMediumUrl + "', mWidgetUrl='" + this.mWidgetUrl + "', mUpdateTime='" + this.mUpdateTime + "', mJumpUrl='" + this.mJumpUrl + "', mFuncUrl='" + this.mFuncUrl + "', mFrameRate='" + this.mFrameRate + "', mLastTimeUsed=" + this.mLastTimeUsed + ", mTotalTimeInForeground=" + this.mTotalTimeInForeground + ", mLastUpdateUrlTime=" + this.mLastUpdateUrlTime + ", mHasVerify=" + this.mHasVerify + ", isGame=" + this.isGame + ", isDownloadItem=" + this.isDownloadItem + ", downloadInfo=" + this.downloadInfo + ", atmosphere = " + this.mAtmosphere + ", shortcutInfo = " + this.mShortcutInfo + '}';
    }

    public void updateAtmosphereTime() {
        Log.d("Atmosphere", "updateAtmosphereTime(" + getName() + ")");
        this.mUpdateAtmosphereTime = System.currentTimeMillis();
    }
}
