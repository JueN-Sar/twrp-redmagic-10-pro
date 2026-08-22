package cn.nubia.gamelauncher.helper;

import android.util.Log;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.gamelauncher.activity.GameSpaceActivity;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.model.AppAddModel;
import cn.nubia.gamelauncher.util.Util;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class Controller {
    public static final String TAG = "Controller";
    Runnable mCloseBgmRunnable;
    Runnable mOpenBgmRunnable;
    AppListItemBean mSelectedItem;
    CopyOnWriteArrayList<Runnable> mCallbacks = new CopyOnWriteArrayList<>();
    private boolean isFullMode = false;
    private String mCurrentTag = GameSpaceActivity.TAG_SPLASH;
    boolean isResumed = false;
    boolean isSpaceResumed = false;

    private static class ModeHolder {
        public static final Controller INSTANCE = new Controller();

        private ModeHolder() {
        }
    }

    public static Controller getInstance() {
        return ModeHolder.INSTANCE;
    }

    private void notifyCallbacks() {
        if (this.mCallbacks.isEmpty()) {
            return;
        }
        Log.d("Controller", "notifyCallbacks() callbacks size : " + this.mCallbacks.size());
        Iterator<Runnable> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
    }

    public void addBgmCallback(Runnable runnable, Runnable runnable2) {
        this.mOpenBgmRunnable = runnable;
        this.mCloseBgmRunnable = runnable2;
    }

    public void addSelectedChangedListener(Runnable runnable) {
        this.mCallbacks.add(runnable);
    }

    public void clearBgmCallback() {
        this.mOpenBgmRunnable = null;
        this.mCloseBgmRunnable = null;
    }

    public void clearSelectedChangedListener() {
        if (Util.isGameSpaceForeground()) {
            return;
        }
        this.mCallbacks.clear();
    }

    public void closeBgm() {
        if (this.mCloseBgmRunnable == null || !isSpaceResumed()) {
            BgmHelper.getInstance().closeBgm();
        } else {
            this.mCloseBgmRunnable.run();
        }
    }

    public AppListItemBean getSelectedItem() {
        if (supportSelected()) {
            return this.mSelectedItem;
        }
        return null;
    }

    public String getSelectedItemName() {
        AppListItemBean selectedItem = getSelectedItem();
        if (selectedItem != null) {
            return selectedItem.getName();
        }
        return null;
    }

    public String getSelectedItemPackage() {
        AppListItemBean selectedItem = getSelectedItem();
        if (selectedItem != null) {
            return selectedItem.getPackageName();
        }
        return null;
    }

    public boolean hasGameCard() {
        return isStayInLobby() || isHandheld();
    }

    public boolean hasSelected() {
        return getSelectedItem() != null;
    }

    public boolean isFullMode() {
        return this.isFullMode;
    }

    public boolean isHandheld() {
        String str = this.mCurrentTag;
        return str != null && str.equals(GameSpaceActivity.TAG_HAND_HELD);
    }

    public boolean isPureMode() {
        return Util.isPureMode();
    }

    public boolean isSpaceResumed() {
        return this.isSpaceResumed;
    }

    public boolean isStayInFullLobby() {
        return GameSpaceActivity.TAG_GAME_LOBBY.equals(this.mCurrentTag) && isFullMode();
    }

    public boolean isStayInLobby() {
        return GameSpaceActivity.TAG_GAME_LOBBY.equals(this.mCurrentTag);
    }

    public void notifyChanged() {
        Log.d("Controller", "notifyChanged()");
        updateSelected();
    }

    public void openBgm() {
        Log.d("assist", "Controller --- openBgm() mOpenBgmRunnable : " + this.mOpenBgmRunnable + " isSpaceResumed() : " + isSpaceResumed());
        if (this.mOpenBgmRunnable == null || !isSpaceResumed()) {
            BgmHelper.getInstance().openBgm();
        } else {
            this.mOpenBgmRunnable.run();
        }
    }

    public void relevantChanged(String str) {
        Log.d("Controller", "relevantChanged() pkg : " + str);
        if (str == null) {
            Log.d("Controller", "relevantChanged() pkg is null!");
            return;
        }
        if (!hasSelected()) {
            Log.d("Controller", "relevantChanged() hasSelected false!");
            return;
        }
        if (this.mSelectedItem.isShortcut()) {
            Log.d("Controller", "relevantChanged() mSelectedItem.isShortcut()");
        } else if (str.equals(this.mSelectedItem.getPackageName())) {
            selectedChanged();
        } else {
            Log.d("Controller", "relevantChanged() selected pkg : " + this.mSelectedItem.getPackageName());
        }
    }

    public void removeSelectedChangedListener(Runnable runnable) {
        this.mCallbacks.remove(runnable);
    }

    public void selectedChanged() {
        Log.d("Controller", "selectedChanged()");
        notifyChanged();
        notifyCallbacks();
    }

    public void setResumed(boolean z) {
        this.isResumed = z;
    }

    public void setSpaceResumed(boolean z) {
        this.isSpaceResumed = z;
    }

    public boolean supportOneMoreThing() {
        return isStayInLobby() && !isHandheld();
    }

    public boolean supportRelevant() {
        if (!GameSpaceConfig.supportRelevant()) {
            Log.d("Controller", "supportRelevant() config not support!");
            return false;
        }
        if (!hasSelected()) {
            Log.d("Controller", "supportRelevant() hasSelected false!");
            return false;
        }
        if (!this.mSelectedItem.hasRelevant()) {
            Log.d("Controller", "supportRelevant() hasRelevant false!");
            return false;
        }
        if (this.isResumed) {
            return true;
        }
        Log.d("Controller", "supportRelevant() isResumed false!");
        return false;
    }

    public boolean supportSelected() {
        return isStayInLobby() && isFullMode();
    }

    public void switchDisplayMode() {
        switchDisplayMode(!this.isFullMode);
    }

    public void switchDisplayMode(boolean z) {
        if (this.isFullMode == z) {
            return;
        }
        Log.d("Controller", "switchDisplayMode(" + this.isFullMode + ") toFullMode : " + z);
        this.isFullMode = z;
        selectedChanged();
    }

    public void switchTag(String str) {
        Log.d("Controller", "switchTag( " + this.mCurrentTag + ") tag : " + str);
        if (str == null || str.equals(this.mCurrentTag)) {
            return;
        }
        this.mCurrentTag = str;
        selectedChanged();
    }

    public void updateSelected() {
        this.mSelectedItem = AppAddModel.getInstance().getSelectedItem();
        StringBuilder sb = new StringBuilder("updateSelected() selected : ");
        AppListItemBean appListItemBean = this.mSelectedItem;
        Log.d("Controller", sb.append(appListItemBean == null ? null : appListItemBean.getName()).toString());
    }
}
