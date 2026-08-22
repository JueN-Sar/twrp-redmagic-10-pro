package cn.nubia.gamelauncher.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.common.helper.AppUsageStatsHelper;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.helper.Controller;
import cn.nubia.gamelauncher.recycler.BannerManager;
import cn.nubia.gamelauncher.redmagicplanet.util.CommonUtil;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.Util;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class GameLobbyFragment extends Fragment {
    public static final String READ_STORAGE_STATUS = "read_storage_status";
    private static final int REQUEST_PERMISSION_EXTERNAL_STORAGE = 2777;
    public static final String SHARED_PREFERENCES_NAME = "data";
    private static final String TAG = "GameLobby";
    private BannerManager mBannerManager;
    private Context mContext;
    private Bundle mSavedInstanceState;
    private static String[] PERMISSIONS_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE"};
    public static int mLastReadStorageStatus = 0;
    private Handler mHandler = new Handler();
    private boolean mIsSinglePlayMode = false;
    private int mCurrentReadStorageStatus = 0;
    private boolean mHasCheckHostMode = false;

    private void checkPermission() {
        int checkSelfPermission = this.mContext.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE");
        this.mCurrentReadStorageStatus = checkSelfPermission;
        if (checkSelfPermission != 0) {
            requestPermissions(PERMISSIONS_STORAGE, 2777);
        }
    }

    private void doBannerStartAnim() {
        LogUtil.i("GameLobby", "------>doBannerStartAnim()");
        this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.fragment.GameLobbyFragment.3
            @Override // java.lang.Runnable
            public void run() {
                if (GameLobbyFragment.this.mBannerManager == null) {
                    return;
                }
                GameLobbyFragment.this.mBannerManager.startAnimator();
            }
        }, 300L);
    }

    private int getLastReadStoragePermission() {
        int i = getContext().getSharedPreferences("data", 0).getInt(READ_STORAGE_STATUS, 0);
        mLastReadStorageStatus = i;
        return i;
    }

    private int getSavedCenterItemPosition(Bundle bundle) {
        if (bundle == null) {
            return -1;
        }
        return bundle.getInt("CurrentCenterItemPosition");
    }

    private boolean hasReadStoragePermission() {
        int checkSelfPermission = this.mContext.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE");
        this.mCurrentReadStorageStatus = checkSelfPermission;
        return checkSelfPermission == 0;
    }

    private void initBannerView(View view) {
        this.mBannerManager = new BannerManager(getContext(), view);
        doBannerStartAnim();
    }

    private void initView(View view) {
        initBannerView(view);
    }

    private boolean isReCreate(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        LogUtil.i("GameLobby", "------------->isReCreate()");
        return bundle.getBoolean("reCreate");
    }

    private void showHostModeDialog() {
        Context context = this.mContext;
        if (context == null) {
            return;
        }
        int i = Settings.Global.getInt(context.getContentResolver(), "setting_gamebox_can_enter", 0);
        LogUtil.d("GameLobby", "showHostModeDialog() canEnter : " + i);
        if (i == 0) {
            return;
        }
        AlertDialog create = new AlertDialog.Builder(this.mContext, 2131952382).setTitle(R.string.host_mode_dialog_title).setMessage(R.string.host_mode_dialog_message).setNegativeButton(this.mContext.getString(android.R.string.cancel), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.fragment.GameLobbyFragment.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
            }
        }).setPositiveButton(this.mContext.getString(R.string.host_mode_dialog_active), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.fragment.GameLobbyFragment.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                GameLobbyFragment gameLobbyFragment = GameLobbyFragment.this;
                gameLobbyFragment.startHostMode(gameLobbyFragment.mContext);
            }
        }).create();
        create.show();
        create.setCanceledOnTouchOutside(false);
        create.setCancelable(false);
    }

    private void showHostModeDialogIfNeed() {
        LogUtil.i("GameLobby", "showHostModeDialogIfNeed() mHasCheckHostMode : " + this.mHasCheckHostMode + ", GameKey : " + FeatureUtil.supportGameKey());
        if (this.mHasCheckHostMode || FeatureUtil.supportGameKey()) {
            return;
        }
        showHostModeDialog();
        this.mHasCheckHostMode = true;
    }

    public static void showWarningDialog(final Activity activity) {
        LogUtil.i("GameLobby", "showWarningDialog: ");
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(activity).inflate(R.layout.request_permission_custom_dialog, (ViewGroup) null);
        ((TextView) linearLayout.findViewById(R.id.title)).setText(R.string.game_lobby_cts_warning_title);
        AlertDialog create = new AlertDialog.Builder(activity, 2131952382).setView(linearLayout).setNegativeButton(activity.getString(android.R.string.cancel), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.fragment.GameLobbyFragment.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setPositiveButton(activity.getString(R.string.go_permission_setting), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.fragment.GameLobbyFragment.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                activity.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + activity.getPackageName())));
            }
        }).create();
        create.show();
        create.setCanceledOnTouchOutside(false);
        create.setCancelable(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startHostMode(Context context) {
        try {
            DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
            Method method = displayManager.getClass().getMethod("setCmdToDisplay", Integer.TYPE, Integer.TYPE, Integer.TYPE, Bundle.class);
            method.setAccessible(true);
            method.invoke(displayManager, 4, -1, 0, null);
            LogUtil.d("GameLobby", "startHostMode() end");
        } catch (IllegalAccessException e) {
            LogUtil.d("GameLobby", "startHostMode() IllegalAccessException : " + e.getMessage());
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e2) {
            LogUtil.d("GameLobby", "startHostMode() NoSuchMethodException : " + e2.getMessage());
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            LogUtil.d("GameLobby", "startHostMode() InvocationTargetException : " + e3.getMessage());
            throw new RuntimeException(e3);
        }
    }

    private void updateBigBannerAdapter() {
        BannerManager bannerManager = this.mBannerManager;
        if (bannerManager != null) {
            bannerManager.updateBannerAdapterUI();
        }
    }

    private void updateCenterBanner() {
        BannerManager bannerManager = this.mBannerManager;
        if (bannerManager == null) {
            return;
        }
        bannerManager.doResume(this.mIsSinglePlayMode);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        LogUtil.i("GameLobby", "---->onActivityCreated()");
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.i("GameLobby", "---->onCreate()");
        this.mSavedInstanceState = bundle;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.i("GameLobby", "---->onCreateView()");
        View inflate = layoutInflater.inflate(Util.isPureMode() ? R.layout.game_lobby_layout_pure : R.layout.game_lobby_layout, viewGroup, false);
        this.mContext = getActivity();
        initView(inflate);
        CommonUtil.isInternalVersion();
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.i("GameLobby", "---->onDestroyView()");
        BannerManager bannerManager = this.mBannerManager;
        if (bannerManager != null) {
            bannerManager.exit();
            this.mBannerManager.cleanup();
            this.mBannerManager = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        Log.d("wallpaper", "Lobby -- onHiddenChanged() hidden : " + z);
        BannerManager bannerManager = this.mBannerManager;
        if (bannerManager != null) {
            bannerManager.setHiddenChanged(z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(context, attributeSet, bundle);
        LogUtil.i("GameLobby", "---->onInflate()");
    }

    public void onNewIntent(Intent intent) {
        String stringExtra = intent.getStringExtra("mode");
        LogUtil.i("GameLobby", "---->onNewIntent() mode : " + stringExtra);
        BannerManager bannerManager = this.mBannerManager;
        if (bannerManager == null || stringExtra == null) {
            return;
        }
        this.mIsSinglePlayMode = true;
        bannerManager.enterSingleButtonMode("mouse".equals(stringExtra));
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 2777) {
            if (iArr != null && iArr.length > 0) {
                LogUtil.i("GameLobby", "onRequestPermissionsResult: " + iArr[0] + " ;; backResult : true");
                recordReadStoragePermission(iArr[0]);
                if (iArr[0] == 0) {
                    LogUtil.e("GameLobby", "permission granted!");
                    updateBigBannerAdapter();
                }
            }
            boolean shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale("android.permission.READ_EXTERNAL_STORAGE");
            LogUtil.e("GameLobby", "External Storage permissions need to be granted ! request : " + shouldShowRequestPermissionRationale);
            if (!shouldShowRequestPermissionRationale && !CommonUtil.showPermissionWaringDialog(this.mContext)) {
                CommonUtil.setDisplayPermissionDialog(this.mContext);
                return;
            }
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LogUtil.i("GameLobby", "---->onResume()");
        AppUsageStatsHelper.getInstance().updateAppUsageStat();
        updateCenterBanner();
        if (!CommonUtil.isInternalVersion()) {
            hasReadStoragePermission();
            if (this.mCurrentReadStorageStatus != getLastReadStoragePermission()) {
                LogUtil.i("GameLobby", "---->onResume() mCurrentReadStorageStatus : " + this.mCurrentReadStorageStatus + " ;; mLastReadStorageStatus : " + mLastReadStorageStatus);
                recordReadStoragePermission(this.mCurrentReadStorageStatus);
                updateBigBannerAdapter();
            }
        }
        this.mIsSinglePlayMode = false;
        Controller.getInstance().setResumed(true);
        showHostModeDialogIfNeed();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        LogUtil.i("GameLobby", "---->onSaveInstanceState()");
        BannerManager bannerManager = this.mBannerManager;
        if (bannerManager != null) {
            bundle.putInt("CurrentCenterItemPosition", bannerManager.getSelectedItemPosition());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mIsSinglePlayMode = false;
        if (this.mBannerManager == null) {
            return;
        }
        Controller.getInstance().setResumed(false);
    }

    public void recordReadStoragePermission(int i) {
        SharedPreferences.Editor edit = getContext().getSharedPreferences("data", 0).edit();
        edit.putInt(READ_STORAGE_STATUS, i);
        edit.apply();
    }
}
