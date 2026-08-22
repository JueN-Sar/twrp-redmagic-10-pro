package cn.nubia.gamelauncher.gamecontrolpanel;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Vibrator;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.common.app.AlertDialogCenter;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.gamecenter.settings.applearning.AppDbSchema;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.AnimationUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.ControlPanelFeatureHelper;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.FunctionAllocationHelper;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.virtual.HandleHelpView;
import cn.nubia.gamelauncher.gamecontrolpanel.virtual.VirtualTypeAdapter;
import cn.nubia.gamelauncher.gamecontrolpanel.virtual.db.AppGameHandleItem;
import cn.nubia.gamelauncher.gamecontrolpanel.virtual.db.GameHandleDbUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.virtual.db.Utils;
import cn.nubia.gamelauncher.gamecontrolpanel.widget.CustomSeekBar;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gamelauncher.util.OwlSysHelper;
import cn.nubia.gamelauncher.util.ToastUtil;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.plug.Constant;
import cn.nubia.settings.trackclient.Track;
import com.zte.gameassist.ai.AIFlickerTips;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class GameFunctionAllocationView extends FrameLayout implements View.OnClickListener, VirtualTypeAdapter.ICustomizePositionListener, GameControlDialog.ISetViewAnimation, CustomSeekBar.ChangeListener {
    private static final int COLOR_MENU_NORMAL = 2131099884;
    private static final int COLOR_MENU_SELECTED = 2131099886;
    private static final String CUSTOMIZE_TYPE = "1";
    private static final String DB_GAMES_CHICKEN_MODE = "game_chicken_mode_switch";
    private static final String DB_GAMES_DEATH_VIDEO = "persist_sys_nubia_death_video_switch";
    private static final String DB_GAMES_FULL_VIDEO = "persist_sys_nubia_full_video_switch";
    private static final String DB_GAMES_REAL_TIME_DEATH_VIDEO = "persist_sys_nubia_real_time_death_switch";
    private static final String DB_GAMES_SWITCH = "persist_sys_nubia_redmagic_time_switch";
    private static final String DB_GUIDE = "settings_gcs_game_guide";
    private static final ArrayList<String> DEFAULT_LIST = new ArrayList<>(Arrays.asList("0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60"));
    private static final String DEFAULT_ROLLBACK_VALUE = "5";
    private static final String DEFAULT_ZERO_VALUE = "0";
    private static final String GAMES_BLZY = "com.epicgames.fortnite";
    private static final String GAMES_CJZC = "com.tencent.tmgp.pubgmhd";
    private static final String GAMES_PUBG = "com.tencent.ig";
    private static final String GAMES_WZRY = "com.tencent.tmgp.sgame";
    private static final String GAME_MODE_MANUAL_DURATION_DB_NAME = "nubia_game_manual_record_time";
    private static final String GAME_MODE_MANUAL_DURATION_DB_NAME_NEW = "nubia_game_manual_record_time_new";
    private static final String GAME_MODE_VIDEO_QUALITY_DB_NAME = "db_game_video_quality";
    private static final String KEYS_SWITCH_MAIN_LAMP_PREFERENCE = "switch_main_lamp_enable";
    private static final String KEY_DISPLAY_STATUS = "virtual_handle_key_display";
    private static final int MSG_DELETE_CUSTOMIZE_HANDLE = 10004;
    private static final int MSG_PARSE_DATA_FROM_DB = 10001;
    private static final int MSG_QUERY_AUTO_OPEN_FAN_STATUS_HANDLE = 10006;
    private static final int MSG_QUERY_AUTO_OPEN_LIQUID_STATUS_HANDLE = 10010;
    private static final int MSG_QUERY_RESOURCE_PRE_DOWNLOAD_STATUS_HANDLE = 10008;
    private static final int MSG_RENAME_CUSTOMIZE_HANDLE = 10005;
    private static final int MSG_UPDATE_AUTO_OPEN_FAN_STATUS_HANDLE = 10007;
    private static final int MSG_UPDATE_AUTO_OPEN_LIQUID_STATUS_HANDLE = 10011;
    private static final int MSG_UPDATE_RESOURCE_PRE_DOWNLOAD_STATUS_HANDLE = 10009;
    private static final int MSG_USE_CUSTOMIZE_HANDLE = 10003;
    private static final int MSG_USE_OFFICIAL_HANDLE = 10002;
    private static final String NUBIA_TENCENT_LAMP_ENABLE = "switch_tencent_lamp_enable";
    private static final String OFFICIAL_TYPE = "0";
    private static final String OPEN_SUGGEST_STATUS = "virtual_handle_open_suggest";
    private static final String PERFORMANCE_MODE_VALUE = "performance_mode_value";
    private static final String SHAKE_FEEDBACK_STATUS = "virtual_handle_shake_feedback";
    private static final String TAG = "GameFunctionAllocationView";
    private static final int VIDEO_QUALITY_HIGH = 1;
    private static final int VIDEO_QUALITY_LOW = 0;
    private final String DB_GAME_CHICKEN_VALUE;
    private ColorStateList colorStateListNormal;
    private ColorStateList colorStateListSelected;
    private ContentResolver contentResolver;
    private boolean isInternalVersion;
    private boolean isKeyDisplayCheck;
    private boolean isOpenSuggestCheck;
    private boolean isRedmagicTimeCheckboxOpen;
    private boolean isShakeFeedbackCheck;
    private boolean isTencentLampEnable;
    private View mAutoOpenFanLayout;
    private int mAutoOpenFanStatus;
    private ImageView mAutoOpenFanSwitchImage;
    private View mAutoOpenLiquidLayout;
    private int mAutoOpenLiquidStatus;
    private ImageView mAutoOpenLiquidSwitchImage;
    private boolean mBiaBio;
    private final ContentObserver mChickenModeChangedObserver;
    private Context mContext;
    private String mCurrentPackageName;
    private AppGameHandleItem mDeleteCustomizeItem;
    private AlertDialogCenter mDeleteDialog;
    private TextView mDeleteDialogBtn;
    private View mDeleteDialogView;
    private String mDeleteId;
    private FunctionAllocationHelper mFunctionAllocationHelper;
    private View mFunctionLayoutView;
    private LinearLayout mFunctionRedMagicTimeLayout;
    protected IGameStrengthSelectedListener mGameStrengthSelectedListener;
    private HandleHelpView mHandleHelpView;
    private List<AppGameHandleItem> mHandleItemAll;
    private List<AppGameHandleItem> mHandleItemCustomize;
    private HandlerThread mHandlerThread;
    private List<Drawable> mImageCustomize;
    private boolean mIsAddView;
    private WindowManager.LayoutParams mLayoutParams;
    private Handler mMainHandler;
    private Button mOkBtn;
    private ContentObserver mPerformanceLowObserver;
    private CustomSeekBar mPostRecordingSeekbar;
    private String mPostRecordingSeekbarProgress;
    private int mRealPosition;
    private View mRecordAsYouLikeLayout;
    private TextView mRecordAsYouLikeQualityHigh;
    private View mRecordAsYouLikeQualityLayout;
    private TextView mRecordAsYouLikeQualityStandard;
    private String mRecordingSeekBarProgress;
    private CustomSeekBar mRecordingSeekbar;
    private VirtualTypeAdapter mRecyclerAdapter;
    private AppGameHandleItem mRenameCustomizeItem;
    private AlertDialogCenter mRenameDialog;
    private EditText mRenameDialogEdit;
    private TextView mRenameDialogText;
    private View mRenameDialogView;
    private View mResourcePreDownloadLayout;
    private int mResourcePreDownloadStatus;
    private ImageView mResourcePreDownloadSwitchImage;
    private ScrollView mScrollView;
    private int mSelectedIndex;
    private String mStartType;
    private List<String> mTitleList;
    private AppGameHandleItem mUseCustomizeItem;
    private View mVirtualControllerLayout;
    private WindowManager mWindowManager;
    private Handler mWorkHandler;
    private int openDeath;
    private int openFullVideo;
    private int openRealTimeDeath;
    View.OnClickListener redMagicChekboxClickListener;
    private ImageView redmagicTimeCheckbox;
    private ImageView redmagicTimeDieVideoCheckbox;
    private RelativeLayout redmagicTimeDieVideoLayout;
    private TextView redmagicTimeDieVideoText;
    private ImageView redmagicTimeLampCheckbox;
    private RelativeLayout redmagicTimeLampLayout;
    private TextView redmagicTimeLampText;
    private TextView redmagicTimeQualityHigh;
    private RelativeLayout redmagicTimeQualityLayout;
    private TextView redmagicTimeQualityStandard;
    private TextView redmagicTimeQualityText;
    private ImageView redmagicTimeRecordAllCheckbox;
    private RelativeLayout redmagicTimeRecordAllLayout;
    private TextView redmagicTimeRecordAllText;
    private ImageView redmagicTimeRecordDieCheckbox;
    private RelativeLayout redmagicTimeRecordDieLayout;
    private TextView redmagicTimeRecordDieText;
    private RelativeLayout redmagicTimeRecordLayout;
    private TextView redmagicTimeRecordText;
    private TextView redmagicTimeRecord_15s;
    private TextView redmagicTimeRecord_30s;
    private TextView redmagicTimeRecord_45s;
    private TextView redmagicTimeRecord_60s;
    private TextView redmagicTimeText;
    private List<GameStrengthenVoiceItemView> vVoiceItems;
    private RecyclerView virtualControllerRecycleview;
    private RelativeLayout virtualKeyShow;
    private ImageView virtualKeyShowCheckbox;
    private RelativeLayout virtualOpenSuggestion;
    private ImageView virtualOpenSuggestionCheckbox;
    private ImageView virtualSettingImage;
    private ImageView virtualSettingImageBtnDelete;
    private ImageView virtualSettingImageBtnRename;
    private LinearLayout virtualSettingLayout;
    private TextView virtualSettingLayoutText;
    private RelativeLayout virtualShakeFeedback;
    private ImageView virtualShakeFeedbackCheckbox;
    private RelativeLayout virtualVideoCourseLayout;

    public GameFunctionAllocationView(Context context) {
        this(context, null);
    }

    public GameFunctionAllocationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GameFunctionAllocationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHandleItemCustomize = new ArrayList();
        this.mHandleItemAll = new ArrayList();
        this.mImageCustomize = new ArrayList();
        this.mTitleList = new ArrayList();
        this.mDeleteId = "-1";
        this.mSelectedIndex = -1;
        this.DB_GAME_CHICKEN_VALUE = "db_game_chicken_value";
        this.mChickenModeChangedObserver = new ContentObserver(new Handler()) { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                LogUtil.d(GameFunctionAllocationView.TAG, "*******mChickenModeChangedObserver selfChange*******");
                GameFunctionAllocationView.this.startChickenModeAnim();
            }
        };
        this.redMagicChekboxClickListener = new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ToastUtil.showGamemodeToast(GameFunctionAllocationView.this.getContext().getResources().getString(R.string.performance_low_red_magic_toast));
            }
        };
        this.mContext = context;
        this.mPerformanceLowObserver = new ContentObserver(this.mWorkHandler) { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView.2
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                if (GameFunctionAllocationView.this.isInternalVersion) {
                    return;
                }
                int i2 = Settings.Global.getInt(GameFunctionAllocationView.this.contentResolver, GameFunctionAllocationView.PERFORMANCE_MODE_VALUE, 2);
                GameFunctionAllocationView.this.startChickenModeAnim();
                LogUtil.d(GameFunctionAllocationView.TAG, " performanceMode = " + i2);
                if (i2 == 1) {
                    GameFunctionAllocationView.this.disableRedMagicTime();
                } else if (i2 > 1) {
                    GameFunctionAllocationView.this.enableRedMagicTime();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeDbConfig(AppGameHandleItem appGameHandleItem) {
        if (appGameHandleItem.getCurrentConfig() == 1) {
            LogUtil.i(TAG, "this handle is default");
        } else {
            LogUtil.i(TAG, "change db config : " + appGameHandleItem);
            GameHandleDbUtil.resetCurrentConfig(this.contentResolver, appGameHandleItem.getPackageName());
            appGameHandleItem.setCurrentConfig(1);
            GameHandleDbUtil.updateDataToDb(this.contentResolver, appGameHandleItem, appGameHandleItem.getTitle());
        }
        ArrayList arrayList = new ArrayList(this.mHandleItemCustomize);
        for (int i = 0; i < arrayList.size(); i++) {
            if (this.mRealPosition == i) {
                this.mHandleItemCustomize.get(i).setCurrentConfig(1);
            } else {
                this.mHandleItemCustomize.get(i).setCurrentConfig(0);
            }
        }
        notifyDataSetChanged();
    }

    private Boolean checkDurationValue(String str) {
        boolean z;
        if (TextUtils.equals("0", str) || TextUtils.equals("1", str) || TextUtils.equals("2", str) || TextUtils.equals("3", str)) {
            z = true;
        } else {
            LogUtil.i(TAG, " checkDurationValue---- duration is invalid, duration : " + str);
            z = false;
        }
        return Boolean.valueOf(z);
    }

    private void checkboxStatusBackup() {
        LogUtil.d(TAG, this.mStartType + "_checkbox_bak :" + this.isRedmagicTimeCheckboxOpen);
        Settings.Global.putInt(this.mContext.getContentResolver(), this.mStartType + "_checkbox_bak", this.isRedmagicTimeCheckboxOpen ? 1 : 0);
    }

    private int checkboxStatusRestore() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), this.mStartType + "_checkbox_bak", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: clearCheckboxBackup, reason: merged with bridge method [inline-methods] */
    public void m258x59065fdc() {
        Settings.Global.putInt(this.mContext.getContentResolver(), this.mStartType + "_checkbox_bak", 0);
    }

    private WindowManager.LayoutParams createDefaultLayoutParams() {
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        if (layoutParams != null) {
            return layoutParams;
        }
        WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(-1, -1);
        layoutParams2.setTitle("InstructionState");
        layoutParams2.format = -2;
        layoutParams2.type = 2038;
        layoutParams2.layoutInDisplayCutoutMode = 1;
        layoutParams2.flags = 1280;
        layoutParams2.screenOrientation = 6;
        layoutParams2.systemUiVisibility = 5638;
        this.mLayoutParams = layoutParams2;
        return layoutParams2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteDbData() {
        LogUtil.i(TAG, "deleteDbData : " + this.mHandleItemCustomize + ", mRealPosition: " + this.mRealPosition + ",size :" + this.mHandleItemCustomize.size());
        this.mHandleItemCustomize.get(0).setCurrentConfig(1);
        GameHandleDbUtil.deleteDbItem(this.contentResolver, this.mDeleteId);
        List<AppGameHandleItem> list = this.mHandleItemCustomize;
        if (list == null || this.mRealPosition >= list.size()) {
            return;
        }
        LogUtil.i(TAG, "delete id and image : " + this.mDeleteId + ", " + this.mHandleItemCustomize.get(this.mRealPosition).getImageUrl());
        Utils.deleteImage(this.mHandleItemCustomize.get(this.mRealPosition).getImageUrl(), this.mContext);
        this.mHandleItemCustomize.remove(this.mRealPosition);
        this.mImageCustomize.remove(this.mRealPosition);
        notifyDataSetChanged();
        if (this.mHandleItemCustomize.size() == 0) {
            virtualViewSetChanged();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0040, code lost:
    
        if (r8 == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0035, code lost:
    
        if (r8 != null) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0045, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0042, code lost:
    
        r8.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.graphics.Bitmap getBitmap(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 0
            android.content.ContentResolver r1 = r8.contentResolver     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3f
            android.net.Uri r2 = cn.nubia.gamelauncher.gamecontrolpanel.virtual.db.DBConstant.URI_SWITCH_GAME_IMAGE     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3f
            r8 = 1
            java.lang.String[] r3 = new java.lang.String[r8]     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3f
            java.lang.String r4 = "data"
            r7 = 0
            r3[r7] = r4     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3f
            java.lang.String r4 = "name=?"
            java.lang.String[] r5 = new java.lang.String[r8]     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3f
            r5[r7] = r9     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3f
            r6 = 0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3f
            if (r8 == 0) goto L35
            r8.moveToFirst()     // Catch: java.lang.Throwable -> L32 java.lang.Exception -> L40
            byte[] r9 = r8.getBlob(r7)     // Catch: java.lang.Throwable -> L32 java.lang.Exception -> L40
            if (r9 == 0) goto L35
            int r1 = r9.length     // Catch: java.lang.Throwable -> L32 java.lang.Exception -> L40
            if (r1 <= 0) goto L35
            int r1 = r9.length     // Catch: java.lang.Throwable -> L32 java.lang.Exception -> L40
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeByteArray(r9, r7, r1)     // Catch: java.lang.Throwable -> L32 java.lang.Exception -> L40
            if (r8 == 0) goto L31
            r8.close()
        L31:
            return r9
        L32:
            r9 = move-exception
            r0 = r8
            goto L39
        L35:
            if (r8 == 0) goto L45
            goto L42
        L38:
            r9 = move-exception
        L39:
            if (r0 == 0) goto L3e
            r0.close()
        L3e:
            throw r9
        L3f:
            r8 = r0
        L40:
            if (r8 == 0) goto L45
        L42:
            r8.close()
        L45:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView.getBitmap(java.lang.String):android.graphics.Bitmap");
    }

    private int getGameManualDurationValue() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), GAME_MODE_MANUAL_DURATION_DB_NAME, 1);
    }

    private String getGameModeManualDurationDbNameNew() {
        return Settings.Global.getString(this.mContext.getContentResolver(), GAME_MODE_MANUAL_DURATION_DB_NAME_NEW);
    }

    private int getGameVideoQualityValue() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), GAME_MODE_VIDEO_QUALITY_DB_NAME + this.mStartType, 0);
    }

    private int getManualDurationByPkgName(String str) {
        String gameModeManualDurationDbNameNew = getGameModeManualDurationDbNameNew();
        int gameManualDurationValue = getGameManualDurationValue();
        LogUtil.i(TAG, " getManualDurationByPkgName originalDuration : " + gameManualDurationValue + "  ;; pkgName = " + str);
        if (TextUtils.isEmpty(gameModeManualDurationDbNameNew) || TextUtils.isEmpty(str) || !gameModeManualDurationDbNameNew.contains(str)) {
            return gameManualDurationValue;
        }
        for (String str2 : gameModeManualDurationDbNameNew.split(",")) {
            if (!TextUtils.isEmpty(str2) && str2.contains(str) && str2.contains("_")) {
                String substring = str2.substring(str2.lastIndexOf("_") + 1);
                if (!checkDurationValue(substring).booleanValue()) {
                    return gameManualDurationValue;
                }
                int parseInt = Integer.parseInt(substring);
                LogUtil.i(TAG, " getManualDurationByPkgName DesDuration : " + parseInt);
                return parseInt;
            }
        }
        return gameManualDurationValue;
    }

    private View getShowFlickerView(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        str.hashCode();
        switch (str) {
        }
        return null;
    }

    private int getSubDB(Context context, String str, String str2, int i) {
        return Settings.Global.getInt(context.getContentResolver(), str + str2, i);
    }

    private int getSubDB(String str, int i) {
        return getSubDB(getContext(), str, this.mStartType, i);
    }

    private int getSubDB(String str, String str2, int i) {
        return Settings.Global.getInt(getContext().getContentResolver(), str + str2, i);
    }

    private boolean hasGuideShowed() {
        int i = Settings.Global.getInt(getContext().getContentResolver(), DB_GUIDE, 0);
        LogUtil.i(TAG, " hasGuideShowed : " + i);
        return i == 1;
    }

    private void initData() {
        this.contentResolver = this.mContext.getContentResolver();
        this.mWindowManager = (WindowManager) this.mContext.getSystemService("window");
        this.mMainHandler = new Handler();
        if (this.mHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread(TAG);
            this.mHandlerThread = handlerThread;
            handlerThread.start();
        }
        if (this.mWorkHandler == null) {
            this.mWorkHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView.3
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    switch (message.what) {
                        case GameFunctionAllocationView.MSG_PARSE_DATA_FROM_DB /* 10001 */:
                            GameFunctionAllocationView.this.parseDataFromDb();
                            break;
                        case GameFunctionAllocationView.MSG_USE_CUSTOMIZE_HANDLE /* 10003 */:
                            GameFunctionAllocationView gameFunctionAllocationView = GameFunctionAllocationView.this;
                            gameFunctionAllocationView.changeDbConfig(gameFunctionAllocationView.mUseCustomizeItem);
                            break;
                        case GameFunctionAllocationView.MSG_DELETE_CUSTOMIZE_HANDLE /* 10004 */:
                            GameFunctionAllocationView.this.deleteDbData();
                            break;
                        case GameFunctionAllocationView.MSG_RENAME_CUSTOMIZE_HANDLE /* 10005 */:
                            GameFunctionAllocationView.this.renameDbData();
                            break;
                        case GameFunctionAllocationView.MSG_QUERY_AUTO_OPEN_FAN_STATUS_HANDLE /* 10006 */:
                            if (cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.isShortcut()) {
                                GameFunctionAllocationView gameFunctionAllocationView2 = GameFunctionAllocationView.this;
                                gameFunctionAllocationView2.mAutoOpenFanStatus = cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.getShortcutAutoOpenFanStatus(gameFunctionAllocationView2.getContext(), cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.getShortCutLabel());
                            } else {
                                GameFunctionAllocationView gameFunctionAllocationView3 = GameFunctionAllocationView.this;
                                gameFunctionAllocationView3.mAutoOpenFanStatus = cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.getCurPkgAutoOpenFanStatus(gameFunctionAllocationView3.getContext(), GameFunctionAllocationView.this.mCurrentPackageName);
                            }
                            GameFunctionAllocationView.this.updateAutoOpenFanUI();
                            break;
                        case GameFunctionAllocationView.MSG_UPDATE_AUTO_OPEN_FAN_STATUS_HANDLE /* 10007 */:
                            if (!cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.isShortcut()) {
                                cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.updateCurPkgAutoOpenFanStatus(GameFunctionAllocationView.this.getContext(), GameFunctionAllocationView.this.mCurrentPackageName, GameFunctionAllocationView.this.mAutoOpenFanStatus);
                                break;
                            } else {
                                cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.updateShortcutAutoOpenFanStatus(GameFunctionAllocationView.this.getContext(), cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.getShortCutLabel(), GameFunctionAllocationView.this.mAutoOpenFanStatus);
                                break;
                            }
                        case GameFunctionAllocationView.MSG_QUERY_RESOURCE_PRE_DOWNLOAD_STATUS_HANDLE /* 10008 */:
                            GameFunctionAllocationView.this.mResourcePreDownloadStatus = FunctionAllocationHelper.getInstance().getResourcePreDownloadSwitchByCurPkg(GameFunctionAllocationView.this.mContext, GameFunctionAllocationView.this.mCurrentPackageName);
                            GameFunctionAllocationView.this.updateResourcePreDownloadUI();
                            break;
                        case GameFunctionAllocationView.MSG_UPDATE_RESOURCE_PRE_DOWNLOAD_STATUS_HANDLE /* 10009 */:
                            FunctionAllocationHelper.getInstance().saveResourcePreDownloadSwitchStatus(GameFunctionAllocationView.this.mContext, GameFunctionAllocationView.this.mCurrentPackageName, GameFunctionAllocationView.this.mResourcePreDownloadStatus);
                            break;
                        case GameFunctionAllocationView.MSG_QUERY_AUTO_OPEN_LIQUID_STATUS_HANDLE /* 10010 */:
                            if (cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.isShortcut()) {
                                GameFunctionAllocationView gameFunctionAllocationView4 = GameFunctionAllocationView.this;
                                gameFunctionAllocationView4.mAutoOpenLiquidStatus = cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.getShortcutAutoOpenLiquidStatus(gameFunctionAllocationView4.getContext(), cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.getShortCutLabel());
                            } else {
                                GameFunctionAllocationView gameFunctionAllocationView5 = GameFunctionAllocationView.this;
                                gameFunctionAllocationView5.mAutoOpenLiquidStatus = cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.getCurPkgAutoOpenLiquidStatus(gameFunctionAllocationView5.getContext(), GameFunctionAllocationView.this.mCurrentPackageName);
                            }
                            GameFunctionAllocationView.this.updateAutoOpenLiquidUI();
                            break;
                        case GameFunctionAllocationView.MSG_UPDATE_AUTO_OPEN_LIQUID_STATUS_HANDLE /* 10011 */:
                            if (!cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.isShortcut()) {
                                cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.updateCurPkgAutoOpenLiquidStatus(GameFunctionAllocationView.this.getContext(), GameFunctionAllocationView.this.mCurrentPackageName, GameFunctionAllocationView.this.mAutoOpenLiquidStatus);
                                break;
                            } else {
                                cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.updateShortcutAutoOpenLiquidStatus(GameFunctionAllocationView.this.getContext(), cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.getShortCutLabel(), GameFunctionAllocationView.this.mAutoOpenLiquidStatus);
                                break;
                            }
                    }
                }
            };
        }
    }

    private void initDialog() {
        View inflate = View.inflate(this.mContext, R.layout.virtual_settings_rename_dialog_layout, null);
        this.mRenameDialogView = inflate;
        this.mRenameDialogEdit = (EditText) inflate.findViewById(R.id.virtual_handle_rename);
        this.mRenameDialogText = (TextView) this.mRenameDialogView.findViewById(R.id.virtual_handle_rename_check);
        View inflate2 = View.inflate(this.mContext, R.layout.virtual_settings_delete_dialog_layout, null);
        this.mDeleteDialogView = inflate2;
        this.mDeleteDialogBtn = (TextView) inflate2.findViewById(R.id.virtual_handle_delete_btn);
        this.virtualSettingImageBtnRename.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameFunctionAllocationView gameFunctionAllocationView = GameFunctionAllocationView.this;
                gameFunctionAllocationView.showRenameDialog(gameFunctionAllocationView.mContext, ((AppGameHandleItem) GameFunctionAllocationView.this.mHandleItemCustomize.get(GameFunctionAllocationView.this.mRealPosition)).getTitle(), GameFunctionAllocationView.this.mRealPosition);
            }
        });
        this.virtualSettingImageBtnDelete.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameFunctionAllocationView gameFunctionAllocationView = GameFunctionAllocationView.this;
                gameFunctionAllocationView.mDeleteId = ((AppGameHandleItem) gameFunctionAllocationView.mHandleItemCustomize.get(GameFunctionAllocationView.this.mRealPosition)).getId();
                GameFunctionAllocationView gameFunctionAllocationView2 = GameFunctionAllocationView.this;
                gameFunctionAllocationView2.showDeleteDialog(gameFunctionAllocationView2.mContext, GameFunctionAllocationView.this.mRealPosition);
            }
        });
    }

    private void initView() {
        Resources resources;
        int i;
        this.mWorkHandler.sendEmptyMessage(MSG_PARSE_DATA_FROM_DB);
        LayoutInflater.from(getContext()).inflate(GameControlOrientationManager.getInstance().isPortrait() ? R.layout.nubia_game_strengthen_view_function_port : R.layout.nubia_game_strengthen_view_function, this);
        this.mScrollView = (ScrollView) findViewById(R.id.scroll_view);
        this.mFunctionLayoutView = findViewById(R.id.nubia_game_strength_function_layout);
        this.mFunctionRedMagicTimeLayout = (LinearLayout) findViewById(R.id.function_redmagic_time_layout);
        this.redmagicTimeLampLayout = (RelativeLayout) findViewById(R.id.function_redmagic_time_lamp_layout);
        this.redmagicTimeText = (TextView) findViewById(R.id.function_redmagic_time_text);
        this.redmagicTimeLampText = (TextView) findViewById(R.id.function_redmagic_time_lamp_text);
        this.redmagicTimeLampCheckbox = (ImageView) findViewById(R.id.function_redmagic_time_lamp_checkbox);
        this.isTencentLampEnable = switchSubByDB(NUBIA_TENCENT_LAMP_ENABLE);
        this.isInternalVersion = cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.isInternalVersion();
        LogUtil.d(TAG, "isTencentLampEnable: " + this.isTencentLampEnable);
        this.mAutoOpenFanLayout = findViewById(R.id.nubia_auto_open_fan_layout);
        ImageView imageView = (ImageView) findViewById(R.id.auto_open_fan_checkbox);
        this.mAutoOpenFanSwitchImage = imageView;
        imageView.setOnClickListener(this);
        this.mWorkHandler.sendEmptyMessage(MSG_QUERY_AUTO_OPEN_FAN_STATUS_HANDLE);
        if (!CommonUtil.isRedMagicLegacyProject() && (!cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.isSupportFan(this.mContext) || !ControlPanelFeatureHelper.getGameFan())) {
            this.mAutoOpenFanLayout.setVisibility(8);
        }
        this.mAutoOpenLiquidLayout = findViewById(R.id.nubia_auto_open_liquid_cooling_layout);
        ImageView imageView2 = (ImageView) findViewById(R.id.auto_open_liquid_cooling_checkbox);
        this.mAutoOpenLiquidSwitchImage = imageView2;
        imageView2.setOnClickListener(this);
        if (cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.isHighVersion() || ControlPanelFeatureHelper.getLiquidCooling()) {
            this.mWorkHandler.sendEmptyMessage(MSG_QUERY_AUTO_OPEN_LIQUID_STATUS_HANDLE);
            this.mAutoOpenLiquidLayout.setVisibility(0);
        } else {
            this.mAutoOpenLiquidLayout.setVisibility(8);
        }
        this.mResourcePreDownloadLayout = findViewById(R.id.function_resource_pre_download_layout);
        ImageView imageView3 = (ImageView) findViewById(R.id.resource_pre_download_checkbox);
        this.mResourcePreDownloadSwitchImage = imageView3;
        imageView3.setOnClickListener(this);
        if (!FunctionAllocationHelper.getInstance().supportResourcePreDownload().booleanValue()) {
            this.mResourcePreDownloadLayout.setVisibility(8);
        } else if (FunctionAllocationHelper.getInstance().supportResourcePreDownloadByCurPkg(this.mCurrentPackageName)) {
            this.mWorkHandler.sendEmptyMessage(MSG_QUERY_RESOURCE_PRE_DOWNLOAD_STATUS_HANDLE);
        } else {
            this.mResourcePreDownloadLayout.setVisibility(8);
        }
        this.mRecordAsYouLikeQualityLayout = findViewById(R.id.record_as_you_like_quality_layout);
        this.mRecordAsYouLikeQualityStandard = (TextView) findViewById(R.id.random_record__quality_standard);
        this.mRecordAsYouLikeQualityHigh = (TextView) findViewById(R.id.random_record_quality_high);
        if (this.mFunctionAllocationHelper.isOnlySupportRandomManualRecord() && (!this.mFunctionAllocationHelper.supportGameHighLight(this.mCurrentPackageName) || this.isInternalVersion)) {
            this.mRecordAsYouLikeQualityLayout.setVisibility(0);
            this.mRecordAsYouLikeQualityStandard.setOnClickListener(this);
            this.mRecordAsYouLikeQualityHigh.setOnClickListener(this);
        }
        this.redmagicTimeLampLayout.setVisibility(8);
        ImageView imageView4 = (ImageView) findViewById(R.id.function_redmagic_time_checkbox);
        this.redmagicTimeCheckbox = imageView4;
        imageView4.setOnClickListener(this);
        if (this.isInternalVersion) {
            this.redmagicTimeText.setText(getResources().getText(R.string.gcs_game_manual_title));
            this.redmagicTimeCheckbox.setVisibility(8);
            this.isRedmagicTimeCheckboxOpen = true;
        } else if (this.mFunctionAllocationHelper.supportGameHighLight(this.mCurrentPackageName)) {
            TextView textView = this.redmagicTimeText;
            if (this.mFunctionAllocationHelper.isRedMagicDevice()) {
                resources = getResources();
                i = R.string.mode_redmiagic_time;
            } else {
                resources = getResources();
                i = R.string.mode_wonderful_time;
            }
            textView.setText(resources.getText(i));
            this.redmagicTimeCheckbox.setVisibility(0);
            boolean isGameSwitchOn = isGameSwitchOn();
            this.isRedmagicTimeCheckboxOpen = isGameSwitchOn;
            setChecked(this.redmagicTimeCheckbox, isGameSwitchOn);
        } else {
            this.redmagicTimeText.setText(getResources().getText(R.string.gcs_game_manual_title));
            this.redmagicTimeCheckbox.setVisibility(8);
            this.isRedmagicTimeCheckboxOpen = true;
        }
        if (!this.mFunctionAllocationHelper.supportGameHighLight().booleanValue()) {
            LogUtil.i(TAG, " not support gameHighLight ");
            this.mFunctionRedMagicTimeLayout.setVisibility(8);
        }
        if (this.isInternalVersion) {
            if (this.mFunctionAllocationHelper.isOnlySupportRandomManualRecord()) {
                this.mFunctionRedMagicTimeLayout.setVisibility(8);
            }
        } else if (this.mFunctionAllocationHelper.isOnlySupportRandomManualRecord() && !this.mFunctionAllocationHelper.supportGameHighLight(this.mCurrentPackageName)) {
            this.mFunctionRedMagicTimeLayout.setVisibility(8);
        }
        this.redmagicTimeQualityLayout = (RelativeLayout) findViewById(R.id.function_redmagic_time_quality_layout);
        this.redmagicTimeQualityText = (TextView) findViewById(R.id.function_redmagic_time_quality_text);
        this.redmagicTimeQualityStandard = (TextView) findViewById(R.id.function_redmagic_time_quality_standard);
        this.redmagicTimeQualityHigh = (TextView) findViewById(R.id.function_redmagic_time_quality_high);
        this.redmagicTimeQualityStandard.setOnClickListener(this);
        this.redmagicTimeQualityHigh.setOnClickListener(this);
        this.redmagicTimeRecordAllLayout = (RelativeLayout) findViewById(R.id.function_redmagic_time_record_all_layout);
        this.redmagicTimeRecordAllCheckbox = (ImageView) findViewById(R.id.function_redmagic_time_record_all_checkbox);
        this.redmagicTimeRecordAllText = (TextView) findViewById(R.id.function_redmagic_time_record_all_text);
        this.redmagicTimeRecordAllCheckbox.setOnClickListener(this);
        this.redmagicTimeDieVideoLayout = (RelativeLayout) findViewById(R.id.function_redmagic_time_die_video_layout);
        this.redmagicTimeDieVideoCheckbox = (ImageView) findViewById(R.id.function_redmagic_time_die_video_checkbox);
        this.redmagicTimeDieVideoText = (TextView) findViewById(R.id.function_redmagic_time_die_video_text);
        this.redmagicTimeDieVideoCheckbox.setOnClickListener(this);
        this.redmagicTimeRecordDieLayout = (RelativeLayout) findViewById(R.id.function_redmagic_time_record_die_layout);
        this.redmagicTimeRecordDieCheckbox = (ImageView) findViewById(R.id.function_redmagic_time_record_die_checkbox);
        this.redmagicTimeRecordDieText = (TextView) findViewById(R.id.function_redmagic_time_record_die_text);
        this.redmagicTimeRecordDieCheckbox.setOnClickListener(this);
        this.redmagicTimeRecordLayout = (RelativeLayout) findViewById(R.id.function_redmagic_time_record_layout);
        this.redmagicTimeRecordText = (TextView) findViewById(R.id.function_redmagic_time_record_text);
        this.redmagicTimeRecord_15s = (TextView) findViewById(R.id.function_redmagic_time_record_15s);
        this.redmagicTimeRecord_30s = (TextView) findViewById(R.id.function_redmagic_time_record_30s);
        this.redmagicTimeRecord_45s = (TextView) findViewById(R.id.function_redmagic_time_record_45s);
        this.redmagicTimeRecord_60s = (TextView) findViewById(R.id.function_redmagic_time_record_60s);
        if (!this.mFunctionAllocationHelper.isOnlySupportRandomManualRecord()) {
            this.redmagicTimeRecordLayout.setVisibility(0);
            this.redmagicTimeRecord_15s.setOnClickListener(this);
            this.redmagicTimeRecord_30s.setOnClickListener(this);
            this.redmagicTimeRecord_45s.setOnClickListener(this);
            this.redmagicTimeRecord_60s.setOnClickListener(this);
        }
        this.mRecordAsYouLikeLayout = findViewById(R.id.record_as_you_like_layout);
        this.mRecordingSeekbar = (CustomSeekBar) findViewById(R.id.record_time_seekbar);
        this.mPostRecordingSeekbar = (CustomSeekBar) findViewById(R.id.post_recording_seekbar);
        if (this.mFunctionAllocationHelper.isOnlySupportRandomManualRecord()) {
            this.mRecordAsYouLikeLayout.setVisibility(0);
            String nubiaGameRandomBackRecordTime = this.mFunctionAllocationHelper.getNubiaGameRandomBackRecordTime(this.mContext, this.mCurrentPackageName);
            this.mRecordingSeekBarProgress = nubiaGameRandomBackRecordTime;
            CustomSeekBar customSeekBar = this.mRecordingSeekbar;
            ArrayList<String> arrayList = DEFAULT_LIST;
            initializeSeekBar(customSeekBar, arrayList, nubiaGameRandomBackRecordTime);
            String nubiaGameRandomPositiveRecordTime = this.mFunctionAllocationHelper.getNubiaGameRandomPositiveRecordTime(this.mContext, this.mCurrentPackageName);
            this.mPostRecordingSeekbarProgress = nubiaGameRandomPositiveRecordTime;
            initializeSeekBar(this.mPostRecordingSeekbar, arrayList, nubiaGameRandomPositiveRecordTime);
        }
        this.mVirtualControllerLayout = findViewById(R.id.virtual_controller_layout);
        if (GameControlOrientationManager.getInstance().isPortrait()) {
            this.mVirtualControllerLayout.setVisibility(8);
        }
        this.virtualSettingLayoutText = (TextView) findViewById(R.id.function_virtual_controller_layout_text);
        this.virtualSettingLayout = (LinearLayout) findViewById(R.id.function_virtual_controller_layout);
        this.virtualSettingImage = (ImageView) findViewById(R.id.virtual_setting_image);
        this.virtualSettingImageBtnDelete = (ImageView) findViewById(R.id.virtual_setting_image_btn_delete);
        this.virtualSettingImageBtnRename = (ImageView) findViewById(R.id.virtual_setting_image_btn_rename);
        this.virtualControllerRecycleview = (RecyclerView) findViewById(R.id.function_virtual_controller_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        linearLayoutManager.setOrientation(1);
        this.virtualControllerRecycleview.setLayoutManager(linearLayoutManager);
        VirtualTypeAdapter virtualTypeAdapter = new VirtualTypeAdapter(this.mContext, this.mHandleItemCustomize);
        this.mRecyclerAdapter = virtualTypeAdapter;
        this.virtualControllerRecycleview.setAdapter(virtualTypeAdapter);
        this.mRecyclerAdapter.setOnPositionListener(this);
        this.virtualShakeFeedback = (RelativeLayout) findViewById(R.id.function_virtual_shake_feedback);
        this.virtualShakeFeedbackCheckbox = (ImageView) findViewById(R.id.function_virtual_shake_feedback_checkbox);
        boolean isShakeFeedbackOpen = isShakeFeedbackOpen();
        this.isShakeFeedbackCheck = isShakeFeedbackOpen;
        setChecked(this.virtualShakeFeedbackCheckbox, isShakeFeedbackOpen);
        this.virtualShakeFeedbackCheckbox.setOnClickListener(this);
        Vibrator vibrator = (Vibrator) this.mContext.getSystemService("vibrator");
        if (vibrator == null || !vibrator.hasVibrator()) {
            this.virtualShakeFeedback.setVisibility(8);
        } else {
            LogUtil.d(TAG, "hasVibrator");
        }
        this.virtualKeyShow = (RelativeLayout) findViewById(R.id.function_virtual_key_show);
        this.virtualKeyShowCheckbox = (ImageView) findViewById(R.id.function_virtual_key_show_checkbox);
        boolean isKeyDisplayOpen = isKeyDisplayOpen();
        this.isKeyDisplayCheck = isKeyDisplayOpen;
        setChecked(this.virtualKeyShowCheckbox, isKeyDisplayOpen);
        this.virtualKeyShowCheckbox.setOnClickListener(this);
        this.virtualOpenSuggestion = (RelativeLayout) findViewById(R.id.function_virtual_open_suggestion);
        this.virtualOpenSuggestionCheckbox = (ImageView) findViewById(R.id.function_virtual_open_suggestion_checkbox);
        setChecked(this.virtualOpenSuggestionCheckbox, isOpenSuggestOpen());
        this.virtualOpenSuggestionCheckbox.setOnClickListener(this);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.function_virtual_video_course_layout);
        this.virtualVideoCourseLayout = relativeLayout;
        relativeLayout.setOnClickListener(this);
        if (!this.isInternalVersion) {
            int i2 = Settings.Global.getInt(this.contentResolver, PERFORMANCE_MODE_VALUE, 2);
            LogUtil.d(TAG, " performanceMode = " + i2);
            if (i2 == 1 && !isSupportManualRecord()) {
                this.isRedmagicTimeCheckboxOpen = false;
                setChecked(this.redmagicTimeCheckbox, false);
                setEnabled(false);
            }
        }
        if (this.isInternalVersion) {
            this.virtualVideoCourseLayout.setVisibility(8);
            this.virtualOpenSuggestion.setVisibility(8);
        }
        if (Util.isZte() || !ControlPanelFeatureHelper.isTouchGameKeySupported()) {
            this.virtualVideoCourseLayout.setVisibility(8);
        }
        final String highLightViewId = cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.getHighLightViewId();
        if (TextUtils.isEmpty(highLightViewId)) {
            return;
        }
        this.mScrollView.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                GameFunctionAllocationView.this.m260xea75847e(highLightViewId);
            }
        });
        showFlicker(highLightViewId);
    }

    private void initializeSeekBar(CustomSeekBar customSeekBar, List<String> list, String str) {
        customSeekBar.setDataList(list);
        customSeekBar.selectValue(str);
        customSeekBar.setChangeListener(this);
    }

    private boolean isKeyDisplayOpen() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "virtual_handle_key_display", 1) == 1;
    }

    private boolean isShakeFeedbackOpen() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "virtual_handle_shake_feedback", 1) == 1;
    }

    private void isShowMoreVideo(boolean z) {
        TextView textView;
        TextView textView2;
        this.colorStateListSelected = getContext().getResources().getColorStateList(R.color.gcs_gamecenter_menu_text_checked);
        this.colorStateListNormal = getContext().getResources().getColorStateList(R.color.gcs_gamecenter_menu_text);
        LogUtil.d(TAG, " isShowMoreVideo : " + z);
        if (z) {
            TextView textView3 = this.redmagicTimeQualityText;
            if (textView3 != null && this.redmagicTimeQualityStandard != null && this.redmagicTimeQualityHigh != null) {
                textView3.setTextColor(this.colorStateListSelected);
                int gameVideoQualityValue = getGameVideoQualityValue();
                if (gameVideoQualityValue == 0) {
                    this.redmagicTimeQualityStandard.setTextColor(this.colorStateListSelected);
                    this.redmagicTimeQualityHigh.setTextColor(this.colorStateListNormal);
                    this.mRecordAsYouLikeQualityStandard.setTextColor(this.colorStateListSelected);
                    this.mRecordAsYouLikeQualityHigh.setTextColor(this.colorStateListNormal);
                } else if (gameVideoQualityValue == 1) {
                    this.redmagicTimeQualityHigh.setTextColor(this.colorStateListSelected);
                    this.redmagicTimeQualityStandard.setTextColor(this.colorStateListNormal);
                    this.mRecordAsYouLikeQualityHigh.setTextColor(this.colorStateListSelected);
                    this.mRecordAsYouLikeQualityStandard.setTextColor(this.colorStateListNormal);
                }
            }
            TextView textView4 = this.redmagicTimeRecordAllText;
            if (textView4 != null && this.redmagicTimeRecordAllCheckbox != null) {
                textView4.setTextColor(this.colorStateListSelected);
                this.redmagicTimeRecordAllCheckbox.setEnabled(true);
                int subDB = getSubDB(DB_GAMES_FULL_VIDEO, 0);
                this.openFullVideo = subDB;
                setChecked(this.redmagicTimeRecordAllCheckbox, subDB != 0);
            }
            TextView textView5 = this.redmagicTimeDieVideoText;
            if (textView5 != null && this.redmagicTimeDieVideoCheckbox != null) {
                textView5.setTextColor(this.colorStateListSelected);
                this.redmagicTimeDieVideoCheckbox.setEnabled(true);
                int subDB2 = getSubDB(DB_GAMES_DEATH_VIDEO, 1);
                this.openDeath = subDB2;
                setChecked(this.redmagicTimeDieVideoCheckbox, subDB2 != 0);
            }
            if ((this.mStartType.contains("wzry") || this.mStartType.contains("lol")) && (textView2 = this.redmagicTimeRecordDieText) != null && this.redmagicTimeRecordDieCheckbox != null) {
                textView2.setTextColor(this.colorStateListSelected);
                this.redmagicTimeRecordDieCheckbox.setEnabled(true);
                int subDB3 = getSubDB(DB_GAMES_REAL_TIME_DEATH_VIDEO, 1);
                this.openRealTimeDeath = subDB3;
                setChecked(this.redmagicTimeRecordDieCheckbox, subDB3 != 0);
            }
            TextView textView6 = this.redmagicTimeRecordText;
            if (textView6 != null && this.redmagicTimeRecord_15s != null) {
                textView6.setTextColor(this.colorStateListSelected);
                this.redmagicTimeRecord_15s.setTextColor(this.colorStateListSelected);
                this.redmagicTimeRecord_30s.setTextColor(this.colorStateListSelected);
                this.redmagicTimeRecord_45s.setTextColor(this.colorStateListSelected);
                this.redmagicTimeRecord_60s.setTextColor(this.colorStateListSelected);
                int manualDurationByPkgName = getManualDurationByPkgName(this.mCurrentPackageName);
                if (manualDurationByPkgName == 0) {
                    this.redmagicTimeRecord_15s.setTextColor(this.colorStateListSelected);
                    this.redmagicTimeRecord_30s.setTextColor(this.colorStateListNormal);
                    this.redmagicTimeRecord_45s.setTextColor(this.colorStateListNormal);
                    this.redmagicTimeRecord_60s.setTextColor(this.colorStateListNormal);
                } else if (manualDurationByPkgName == 1) {
                    this.redmagicTimeRecord_15s.setTextColor(this.colorStateListNormal);
                    this.redmagicTimeRecord_30s.setTextColor(this.colorStateListSelected);
                    this.redmagicTimeRecord_45s.setTextColor(this.colorStateListNormal);
                    this.redmagicTimeRecord_60s.setTextColor(this.colorStateListNormal);
                } else if (manualDurationByPkgName == 2) {
                    this.redmagicTimeRecord_15s.setTextColor(this.colorStateListNormal);
                    this.redmagicTimeRecord_30s.setTextColor(this.colorStateListNormal);
                    this.redmagicTimeRecord_45s.setTextColor(this.colorStateListSelected);
                    this.redmagicTimeRecord_60s.setTextColor(this.colorStateListNormal);
                } else if (manualDurationByPkgName == 3) {
                    this.redmagicTimeRecord_15s.setTextColor(this.colorStateListNormal);
                    this.redmagicTimeRecord_30s.setTextColor(this.colorStateListNormal);
                    this.redmagicTimeRecord_45s.setTextColor(this.colorStateListNormal);
                    this.redmagicTimeRecord_60s.setTextColor(this.colorStateListSelected);
                }
            }
        } else {
            TextView textView7 = this.redmagicTimeQualityText;
            if (textView7 != null && this.redmagicTimeQualityStandard != null && this.redmagicTimeQualityHigh != null) {
                textView7.setTextColor(this.colorStateListNormal);
                this.redmagicTimeQualityStandard.setTextColor(this.colorStateListNormal);
                this.redmagicTimeQualityHigh.setTextColor(this.colorStateListNormal);
                this.mRecordAsYouLikeQualityStandard.setTextColor(this.colorStateListNormal);
                this.mRecordAsYouLikeQualityHigh.setTextColor(this.colorStateListNormal);
            }
            TextView textView8 = this.redmagicTimeRecordAllText;
            if (textView8 != null && this.redmagicTimeRecordAllCheckbox != null) {
                textView8.setTextColor(this.colorStateListNormal);
                setChecked(this.redmagicTimeRecordAllCheckbox, false);
                this.redmagicTimeRecordAllCheckbox.setEnabled(false);
            }
            TextView textView9 = this.redmagicTimeDieVideoText;
            if (textView9 != null && this.redmagicTimeDieVideoCheckbox != null) {
                textView9.setTextColor(this.colorStateListNormal);
                setChecked(this.redmagicTimeDieVideoCheckbox, false);
                this.redmagicTimeDieVideoCheckbox.setEnabled(false);
            }
            if ((this.mStartType.contains("wzry") || this.mStartType.contains("lol")) && (textView = this.redmagicTimeRecordDieText) != null && this.redmagicTimeRecordDieCheckbox != null) {
                textView.setTextColor(this.colorStateListNormal);
                setChecked(this.redmagicTimeRecordDieCheckbox, false);
                this.redmagicTimeRecordDieCheckbox.setEnabled(false);
            }
            TextView textView10 = this.redmagicTimeRecordText;
            if (textView10 != null && this.redmagicTimeRecord_15s != null) {
                textView10.setTextColor(this.colorStateListNormal);
                this.redmagicTimeRecord_15s.setTextColor(this.colorStateListNormal);
                this.redmagicTimeRecord_30s.setTextColor(this.colorStateListNormal);
                this.redmagicTimeRecord_45s.setTextColor(this.colorStateListNormal);
                this.redmagicTimeRecord_60s.setTextColor(this.colorStateListNormal);
            }
        }
        if (this.isRedmagicTimeCheckboxOpen) {
            this.redmagicTimeRecord_15s.setClickable(true);
            this.redmagicTimeRecord_30s.setClickable(true);
            this.redmagicTimeRecord_45s.setClickable(true);
            this.redmagicTimeRecord_60s.setClickable(true);
            this.redmagicTimeQualityHigh.setClickable(true);
            this.redmagicTimeQualityStandard.setClickable(true);
            this.mRecordAsYouLikeQualityStandard.setClickable(true);
            this.mRecordAsYouLikeQualityHigh.setClickable(true);
            return;
        }
        this.redmagicTimeRecord_15s.setClickable(false);
        this.redmagicTimeRecord_30s.setClickable(false);
        this.redmagicTimeRecord_45s.setClickable(false);
        this.redmagicTimeRecord_60s.setClickable(false);
        this.redmagicTimeQualityHigh.setClickable(false);
        this.redmagicTimeQualityStandard.setClickable(false);
        this.mRecordAsYouLikeQualityStandard.setClickable(false);
        this.mRecordAsYouLikeQualityHigh.setClickable(false);
    }

    private boolean isSupportManualRecord() {
        return FunctionAllocationHelper.getInstance().isOnlySupportManualRecord() || !FunctionAllocationHelper.getInstance().supportGameHighLight(this.mCurrentPackageName);
    }

    static /* synthetic */ void lambda$showDeleteDialog$6(DialogInterface dialogInterface, int i) {
    }

    static /* synthetic */ void lambda$showRenameDialog$3(DialogInterface dialogInterface, int i) {
    }

    private void notifyDataSetChanged() {
        this.mMainHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView.8
            @Override // java.lang.Runnable
            public void run() {
                GameFunctionAllocationView.this.mRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNameChanged(String str) {
        if (TextUtils.isEmpty(str.trim())) {
            this.mRenameDialogText.setText("");
            this.mOkBtn.setEnabled(false);
        } else if (!this.mTitleList.contains(str) || str.equals(this.mHandleItemCustomize.get(this.mRealPosition).getTitle())) {
            this.mRenameDialogText.setText("");
            this.mOkBtn.setEnabled(true);
        } else {
            this.mRenameDialogText.setText(this.mContext.getResources().getString(R.string.nubia_touch_game_key_save_dialog_text));
            this.mOkBtn.setEnabled(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0144  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void parseDataFromDb() {
        /*
            Method dump skipped, instructions count: 390
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView.parseDataFromDb():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void renameDbData() {
        LogUtil.i(TAG, "rename item : " + this.mRenameCustomizeItem);
        ContentResolver contentResolver = this.contentResolver;
        AppGameHandleItem appGameHandleItem = this.mRenameCustomizeItem;
        GameHandleDbUtil.updateDataToDb(contentResolver, appGameHandleItem, appGameHandleItem.getTitle());
        notifyDataSetChanged();
    }

    private void reportAutoOpenFan() {
        Bundle bundle = new Bundle();
        bundle.putString("switch_mode", this.mAutoOpenFanStatus == 1 ? "on" : "off");
        try {
            CharSequence shortCutLabel = cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.isShortcut() ? cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils.getShortCutLabel() : this.mContext.getPackageManager().getApplicationLabel(this.mContext.getPackageManager().getApplicationInfo(this.mCurrentPackageName, 128));
            LogUtil.d(TAG, "appName = " + ((Object) shortCutLabel));
            bundle.putCharSequence("app_name", shortCutLabel);
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e(TAG, "Failed to get application name", e);
        }
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "game_auto_fan_switch", bundle);
    }

    private static void reportDataByDay(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("package_name", "cn.nubia.gamelauncher");
        bundle.putString("event_name", str);
        bundle.putString("action_type", str2);
        bundle.putString(AppDbSchema.AppTable.OneDayCols.ACTION_VALUE, str3);
        bundle.putInt(AppDbSchema.AppTable.OneDayCols.REPORT_INTERVAL, 1);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
    }

    private void saveManualDurationNewValueToDB(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String gameModeManualDurationDbNameNew = getGameModeManualDurationDbNameNew();
        LogUtil.d(TAG, "oldDuration " + gameModeManualDurationDbNameNew);
        if (!TextUtils.isEmpty(gameModeManualDurationDbNameNew) && gameModeManualDurationDbNameNew.contains(str)) {
            String[] split = gameModeManualDurationDbNameNew.split(",");
            int length = split.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                String str2 = split[i2];
                if (!TextUtils.isEmpty(str2) && str2.contains(str)) {
                    gameModeManualDurationDbNameNew = gameModeManualDurationDbNameNew.replace(str2, str + "_" + i);
                    break;
                }
                i2++;
            }
        } else {
            gameModeManualDurationDbNameNew = gameModeManualDurationDbNameNew != null ? gameModeManualDurationDbNameNew + str + "_" + i + "," : str + "_" + i + ",";
        }
        LogUtil.i(TAG, "newDuration " + gameModeManualDurationDbNameNew);
        Settings.Global.putString(this.mContext.getContentResolver(), GAME_MODE_MANUAL_DURATION_DB_NAME_NEW, gameModeManualDurationDbNameNew);
    }

    private void setChoiceGameSettings(boolean z) {
        LogUtil.i(TAG, "setChoiceGameSettings: value = " + z);
        setSubDB(z, "persist_sys_nubia_redmagic_time_switch");
        setChecked(this.redmagicTimeCheckbox, z);
        isShowMoreVideo(z);
    }

    private void setGameManualDurationValue(int i) {
        Settings.Global.putInt(this.mContext.getContentResolver(), GAME_MODE_MANUAL_DURATION_DB_NAME, i);
    }

    private void setGameVideoQualityValue(int i) {
        Settings.Global.putInt(this.mContext.getContentResolver(), GAME_MODE_VIDEO_QUALITY_DB_NAME + this.mStartType, i);
    }

    private void setKeyDisplayStatus(boolean z) {
        Settings.Global.putInt(this.mContext.getContentResolver(), "virtual_handle_key_display", z ? 1 : 0);
    }

    private void setOpenSuggestStatus(boolean z) {
        Settings.Global.putInt(this.mContext.getContentResolver(), "virtual_handle_open_suggest", z ? 1 : 0);
    }

    private void setShakeFeedbackStatus(boolean z) {
        Settings.Global.putInt(this.mContext.getContentResolver(), "virtual_handle_shake_feedback", z ? 1 : 0);
    }

    private void setSubDB(boolean z, String str) {
        Settings.Global.putInt(this.mContext.getContentResolver(), str + this.mStartType, z ? 1 : 0);
        if (str == null || !str.contains("persist_sys_nubia_redmagic_time_switch")) {
            return;
        }
        String str2 = this.mStartType;
        if (str2 != null && str2.contains("wzry")) {
            if (z) {
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_WZRY_switch_click", "switch_status", "on");
            } else {
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_WZRY_switch_click", "switch_status", "off");
            }
            OwlSysHelper.insertOwlDayCv("cn.nubia.gamelauncher", "redmagic_time_WZRY_switch_status", "switch_status video_quality full_video death_video live_death_video", (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_redmagic_time_switch_wzry", 0) != 1 ? "off" : "on") + (Settings.Global.getInt(this.mContext.getContentResolver(), "db_game_video_quality_wzry", 0) != 1 ? " SD" : " HD") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_full_video_switch_wzry", 0) == 1 ? " on" : " off") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_death_video_switch_wzry", 1) == 1 ? " on" : " off") + (Settings.Global.getInt(this.mContext.getContentResolver(), DB_GAMES_REAL_TIME_DEATH_VIDEO, 1) != 1 ? " off" : " on"));
            return;
        }
        String str3 = this.mStartType;
        if (str3 != null && str3.contains("hpjy")) {
            if (z) {
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_CJZC_switch_click", "switch_status", "on");
            } else {
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_CJZC_switch_click", "switch_status", "off");
            }
            OwlSysHelper.insertOwlDayCv("cn.nubia.gamelauncher", "redmagic_time_CJZC_switch_status", "switch_status video_quality full_video death_video", (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_redmagic_time_switch_hpjy", 0) != 1 ? "off" : "on") + (Settings.Global.getInt(this.mContext.getContentResolver(), "db_game_video_quality_hpjy", 0) != 1 ? " SD" : " HD") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_full_video_switch_hpjy", 0) == 1 ? " on" : " off") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_death_video_switch_hpjy", 1) != 1 ? " off" : " on"));
            return;
        }
        String str4 = this.mStartType;
        if (str4 != null && str4.contains(Constant.GAME_TAG_PUBG)) {
            if (z) {
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_PUBG_switch_click", "switch_on", "on");
            } else {
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_PUBG_switch_click", "switch_on", "off");
            }
            OwlSysHelper.insertOwlDayCv("cn.nubia.gamelauncher", "redmagic_time_PUBG_switch_status", "switch_status video_quality full_video death_video", (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_redmagic_time_switch_pubg", 0) != 1 ? "off" : "on") + (Settings.Global.getInt(this.mContext.getContentResolver(), "db_game_video_quality_pubg", 0) != 1 ? " SD" : " HD") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_full_video_switch_pubg", 0) == 1 ? " on" : " off") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_death_video_switch_pubg", 1) != 1 ? " off" : " on"));
            return;
        }
        String str5 = this.mStartType;
        if (str5 != null && str5.contains("blzy")) {
            if (z) {
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_BLZY_switch_click", "switch_on", "on");
            } else {
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_BLZY_switch_click", "switch_on", "off");
            }
            OwlSysHelper.insertOwlDayCv("cn.nubia.gamelauncher", "redmagic_time_BLZY_switch_status", "switch_status video_quality full_video death_video", (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_redmagic_time_switch_blzy", 0) != 1 ? "off" : "on") + (Settings.Global.getInt(this.mContext.getContentResolver(), "db_game_video_quality_blzy", 0) != 1 ? " SD" : " HD") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_full_video_switch_blzy", 0) == 1 ? " on" : " off") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_death_video_switch_blzy", 1) != 1 ? " off" : " on"));
            return;
        }
        String str6 = this.mStartType;
        if (str6 == null || !str6.contains("lol")) {
            return;
        }
        if (z) {
            NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_YXLM_switch_click", "switch_status", "on");
        } else {
            NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_YXLM_switch_click", "switch_status", "off");
        }
        OwlSysHelper.insertOwlDayCv("cn.nubia.gamelauncher", "redmagic_time_YXLM_switch_status", "switch_status video_quality full_video death_video live_death_video", (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_redmagic_time_switch_lol", 0) != 1 ? "off" : "on") + (Settings.Global.getInt(this.mContext.getContentResolver(), "db_game_video_quality_lol", 0) != 1 ? " SD" : " HD") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_full_video_switch_lol", 0) == 1 ? " on" : " off") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_death_video_switch_lol", 1) == 1 ? " on" : " off") + (Settings.Global.getInt(this.mContext.getContentResolver(), "persist_sys_nubia_real_time_death_switch_lol", 1) != 1 ? " off" : " on"));
    }

    private void setSubDBForTimeLamp(boolean z, String str) {
        Settings.Global.putInt(getContext().getContentResolver(), str, z ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDeleteDialog(Context context, int i) {
        AlertDialogCenter alertDialogCenter = this.mDeleteDialog;
        if (alertDialogCenter != null) {
            alertDialogCenter.show();
            this.mDeleteDialogBtn.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView$$ExternalSyntheticLambda12
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GameFunctionAllocationView.this.m262x44c50aa1(view);
                }
            });
            return;
        }
        AlertDialogCenter.Builder builder = new AlertDialogCenter.Builder(context, 2131952382);
        builder.setTitle(this.mContext.getResources().getString(R.string.nubia_customize_handle_delete_dialog_title));
        builder.setView(this.mDeleteDialogView).setNegativeButton(R.string.nubia_touch_game_key_save_dialog_cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView$$ExternalSyntheticLambda10
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                GameFunctionAllocationView.lambda$showDeleteDialog$6(dialogInterface, i2);
            }
        });
        AlertDialogCenter create = builder.create();
        this.mDeleteDialog = create;
        create.setButtonTextColor(-2, this.mContext.getColor(R.color.nubia_primary_text_default_material_light));
        this.mDeleteDialog.getWindow().setType(2038);
        this.mDeleteDialog.show();
        this.mDeleteDialogBtn.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameFunctionAllocationView.this.m261x69038ee0(view);
            }
        });
    }

    private void showFlicker(String str) {
        View showFlickerView;
        if (TextUtils.isEmpty(str) || (showFlickerView = getShowFlickerView(str)) == null) {
            return;
        }
        AIFlickerTips.setFlickerName(showFlickerView, str);
        AIFlickerTips.setFlickerPadding(showFlickerView, 3, 3, 3, 3);
        AIFlickerTips.showFlicker(str);
    }

    private void showGuide() {
        if (hasGuideShowed()) {
            showOpenRedMagicTimeAlertDialog();
            return;
        }
        GuideListDialog guideListDialog = new GuideListDialog(getContext());
        guideListDialog.getWindow().setType(2038);
        WindowManager.LayoutParams attributes = guideListDialog.getWindow().getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        guideListDialog.getWindow().setAttributes(attributes);
        guideListDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView.6
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                GameFunctionAllocationView.this.showOpenRedMagicTimeAlertDialog();
            }
        });
        guideListDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showOpenRedMagicTimeAlertDialog() {
        LogUtil.i(TAG, "showOpenRedMagicTimeAlertDialog: ");
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext, 2131952382);
        builder.setMessage(this.mContext.getString(R.string.gcs_game_video_remind)).setPositiveButton(R.string.gamekeys_dialog_ok, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                GameFunctionAllocationView.this.m263x8e2c7b64(dialogInterface, i);
            }
        }).setNegativeButton(R.string.gamemode_account_login_cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                GameFunctionAllocationView.this.m264x69edf725(dialogInterface, i);
            }
        });
        AlertDialog create = builder.create();
        create.getWindow().setType(2038);
        create.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRenameDialog(Context context, String str, final int i) {
        if (str != null) {
            this.mRenameDialogEdit.setText(str);
        }
        AlertDialogCenter alertDialogCenter = this.mRenameDialog;
        if (alertDialogCenter != null) {
            alertDialogCenter.setButton(-1, this.mContext.getResources().getString(R.string.nubia_touch_game_key_save_dialog_save), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView$$ExternalSyntheticLambda5
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    GameFunctionAllocationView.this.m266x34f69531(i, dialogInterface, i2);
                }
            });
            this.mRenameDialog.show();
            return;
        }
        this.mRenameDialogText.setText("");
        this.mRenameDialogEdit.addTextChangedListener(new TextWatcher() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView.9
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                GameFunctionAllocationView.this.onNameChanged(editable.toString());
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }
        });
        AlertDialogCenter.Builder builder = new AlertDialogCenter.Builder(context, 2131952382);
        builder.setTitle(this.mContext.getResources().getString(R.string.nubia_customize_handle_rename_dialog_title));
        builder.setView(this.mRenameDialogView).setNegativeButton(R.string.nubia_touch_game_key_save_dialog_cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                GameFunctionAllocationView.lambda$showRenameDialog$3(dialogInterface, i2);
            }
        }).setPositiveButton(R.string.nubia_touch_game_key_save_dialog_save, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                GameFunctionAllocationView.this.m265x59351970(i, dialogInterface, i2);
            }
        });
        AlertDialogCenter create = builder.create();
        this.mRenameDialog = create;
        create.getWindow().setType(2038);
        this.mRenameDialog.show();
        this.mOkBtn = this.mRenameDialog.getButton(-1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startChickenModeAnim() {
        boolean z = false;
        int i = Settings.Global.getInt(getContext().getContentResolver(), DB_GAMES_CHICKEN_MODE, 0);
        int i2 = Settings.Global.getInt(getContext().getContentResolver(), PERFORMANCE_MODE_VALUE, 2);
        String string = Settings.Global.getString(getContext().getContentResolver(), "db_game_chicken_value");
        this.mBiaBio = (i2 & 4) > 0;
        LogUtil.i(TAG, "startChickenModeAnim chickendModePanel: " + i + " ; performanceMode: " + i2 + " ; mBiaBio: " + this.mBiaBio + " ; chicken_Mode: " + string + " ; mCurrentPackageName: " + this.mCurrentPackageName);
        if (!cn.nubia.gamelauncher.util.CommonUtil.isAndroidVersionAtLeastVanillaIceCream() ? this.mBiaBio || i != 0 : !TextUtils.isEmpty(string) && string.contains(this.mCurrentPackageName)) {
            z = true;
        }
        updateUI(z);
    }

    private boolean switchSubByDB(String str) {
        return 1 == Settings.Global.getInt(getContext().getContentResolver(), str, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAutoOpenFanUI() {
        this.mMainHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                GameFunctionAllocationView.this.m267x3d75bf3f();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAutoOpenLiquidUI() {
        this.mMainHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                GameFunctionAllocationView.this.m268x135f9b99();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateResourcePreDownloadUI() {
        this.mMainHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                GameFunctionAllocationView.this.m269x88d6d7be();
            }
        });
    }

    private void updateUI(boolean z) {
        LogUtil.d(TAG, " --- updateUI ---- isChickenMode = " + z);
        this.redmagicTimeCheckbox.setClickable(!z);
        this.redmagicTimeRecord_15s.setClickable(!z);
        this.redmagicTimeRecord_30s.setClickable(!z);
        this.redmagicTimeRecord_45s.setClickable(!z);
        this.redmagicTimeRecord_60s.setClickable(!z);
        this.redmagicTimeQualityHigh.setClickable(!z);
        this.redmagicTimeQualityStandard.setClickable(!z);
        this.redmagicTimeCheckbox.setEnabled(!z);
        this.redmagicTimeRecord_15s.setEnabled(!z);
        this.redmagicTimeRecord_30s.setEnabled(!z);
        this.redmagicTimeRecord_45s.setEnabled(!z);
        this.redmagicTimeRecord_60s.setEnabled(!z);
        this.redmagicTimeQualityHigh.setEnabled(!z);
        this.redmagicTimeQualityStandard.setEnabled(!z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void virtualViewChanged() {
        LogUtil.e(TAG, "virtualViewChanged");
        TextView textView = this.virtualSettingLayoutText;
        if (textView != null) {
            textView.setVisibility(8);
        }
        LinearLayout linearLayout = this.virtualSettingLayout;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        RelativeLayout relativeLayout = this.virtualShakeFeedback;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
        RelativeLayout relativeLayout2 = this.virtualKeyShow;
        if (relativeLayout2 != null) {
            relativeLayout2.setVisibility(8);
        }
        RelativeLayout relativeLayout3 = this.virtualOpenSuggestion;
        if (relativeLayout3 != null) {
            relativeLayout3.setVisibility(8);
        }
        RelativeLayout relativeLayout4 = this.virtualVideoCourseLayout;
        if (relativeLayout4 != null) {
            relativeLayout4.setVisibility(8);
        }
    }

    private void virtualViewSetChanged() {
        this.mMainHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView.7
            @Override // java.lang.Runnable
            public void run() {
                GameFunctionAllocationView.this.virtualViewChanged();
            }
        });
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog.ISetViewAnimation
    public void animationSelf(final boolean z) {
        new Handler().post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView.11
            @Override // java.lang.Runnable
            public void run() {
                if (!z) {
                    GameFunctionAllocationView.this.mFunctionLayoutView.setAlpha(0.0f);
                } else {
                    AnimationUtil.setGpuTranslationY(GameFunctionAllocationView.this.mFunctionLayoutView);
                    AnimationUtil.setGcsRedItemAlpha(GameFunctionAllocationView.this.mFunctionLayoutView);
                }
            }
        });
    }

    public void disableRedMagicTime() {
        checkboxStatusBackup();
        if (isSupportManualRecord()) {
            LogUtil.d(TAG, " disableRedMagicTime is support manual record , nothing to do");
        } else if (!this.isRedmagicTimeCheckboxOpen) {
            this.mMainHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    GameFunctionAllocationView.this.m255x89f551af();
                }
            });
        } else {
            this.isRedmagicTimeCheckboxOpen = false;
            this.mMainHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    GameFunctionAllocationView.this.m256x8c214069();
                }
            });
        }
    }

    public void dismissView() {
        if (this.mIsAddView) {
            this.mWindowManager.removeView(this.mHandleHelpView);
            this.mIsAddView = false;
        }
    }

    public void enableRedMagicTime() {
        if (isSupportManualRecord()) {
            this.mMainHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    GameFunctionAllocationView.this.m257x7d44e41b();
                }
            });
        } else {
            this.mMainHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    GameFunctionAllocationView.this.m259x34c7db9d();
                }
            });
        }
    }

    public void initStartType(String str) {
        FunctionAllocationHelper functionAllocationHelper = FunctionAllocationHelper.getInstance();
        this.mFunctionAllocationHelper = functionAllocationHelper;
        this.mCurrentPackageName = str;
        if (functionAllocationHelper.supportGameHighLight(str)) {
            this.mStartType = this.mFunctionAllocationHelper.getDbKeyPres().get(this.mFunctionAllocationHelper.getDbPackageNamePres().indexOf(str));
        } else {
            this.mStartType = this.mCurrentPackageName;
        }
        initData();
        initView();
        if (!this.mFunctionAllocationHelper.supportGameHighLight(str) || this.isInternalVersion) {
            this.redmagicTimeRecordAllLayout.setVisibility(8);
            this.redmagicTimeDieVideoLayout.setVisibility(8);
            this.redmagicTimeRecordDieLayout.setVisibility(8);
        } else {
            this.redmagicTimeRecordAllLayout.setVisibility(0);
            this.redmagicTimeDieVideoLayout.setVisibility(0);
            if (this.mFunctionAllocationHelper.supportRecordDie(str)) {
                this.redmagicTimeRecordDieLayout.setVisibility(0);
            }
        }
        isShowMoreVideo(this.isRedmagicTimeCheckboxOpen);
        initDialog();
        startChickenModeAnim();
    }

    public boolean isGameSwitchOn() {
        return isGameSwitchOn(this.mStartType);
    }

    public boolean isGameSwitchOn(String str) {
        return getSubDB("persist_sys_nubia_redmagic_time_switch", str, 0) == 1;
    }

    public boolean isOpenSuggestOpen() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "virtual_handle_open_suggest", 0) == 1;
    }

    /* renamed from: lambda$disableRedMagicTime$10$cn-nubia-gamelauncher-gamecontrolpanel-GameFunctionAllocationView, reason: not valid java name */
    /* synthetic */ void m255x89f551af() {
        setEnabled(false);
    }

    /* renamed from: lambda$disableRedMagicTime$9$cn-nubia-gamelauncher-gamecontrolpanel-GameFunctionAllocationView, reason: not valid java name */
    /* synthetic */ void m256x8c214069() {
        setChecked(this.redmagicTimeCheckbox, false);
        isShowMoreVideo(false);
        setEnabled(false);
    }

    /* renamed from: lambda$enableRedMagicTime$11$cn-nubia-gamelauncher-gamecontrolpanel-GameFunctionAllocationView, reason: not valid java name */
    /* synthetic */ void m257x7d44e41b() {
        setEnabled(true);
    }

    /* renamed from: lambda$enableRedMagicTime$13$cn-nubia-gamelauncher-gamecontrolpanel-GameFunctionAllocationView, reason: not valid java name */
    /* synthetic */ void m259x34c7db9d() {
        int checkboxStatusRestore = checkboxStatusRestore();
        this.mWorkHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                GameFunctionAllocationView.this.m258x59065fdc();
            }
        });
        setEnabled(true);
        LogUtil.d(TAG, "preStatus:" + checkboxStatusRestore);
        if (checkboxStatusRestore == 1) {
            this.redmagicTimeCheckbox.callOnClick();
        }
    }

    /* renamed from: lambda$initView$0$cn-nubia-gamelauncher-gamecontrolpanel-GameFunctionAllocationView, reason: not valid java name */
    /* synthetic */ void m260xea75847e(String str) {
        View showFlickerView = getShowFlickerView(str);
        if (showFlickerView != null) {
            showFlickerView.requestRectangleOnScreen(new Rect(0, 0, showFlickerView.getWidth(), showFlickerView.getHeight()), true);
        }
    }

    /* renamed from: lambda$showDeleteDialog$7$cn-nubia-gamelauncher-gamecontrolpanel-GameFunctionAllocationView, reason: not valid java name */
    /* synthetic */ void m261x69038ee0(View view) {
        this.mWorkHandler.sendEmptyMessage(MSG_DELETE_CUSTOMIZE_HANDLE);
        this.mDeleteDialog.dismiss();
    }

    /* renamed from: lambda$showDeleteDialog$8$cn-nubia-gamelauncher-gamecontrolpanel-GameFunctionAllocationView, reason: not valid java name */
    /* synthetic */ void m262x44c50aa1(View view) {
        this.mWorkHandler.sendEmptyMessage(MSG_DELETE_CUSTOMIZE_HANDLE);
        this.mDeleteDialog.dismiss();
    }

    /* renamed from: lambda$showOpenRedMagicTimeAlertDialog$1$cn-nubia-gamelauncher-gamecontrolpanel-GameFunctionAllocationView, reason: not valid java name */
    /* synthetic */ void m263x8e2c7b64(DialogInterface dialogInterface, int i) {
        setChoiceGameSettings(true);
        dialogInterface.dismiss();
    }

    /* renamed from: lambda$showOpenRedMagicTimeAlertDialog$2$cn-nubia-gamelauncher-gamecontrolpanel-GameFunctionAllocationView, reason: not valid java name */
    /* synthetic */ void m264x69edf725(DialogInterface dialogInterface, int i) {
        this.isRedmagicTimeCheckboxOpen = false;
        dialogInterface.dismiss();
    }

    /* renamed from: lambda$showRenameDialog$4$cn-nubia-gamelauncher-gamecontrolpanel-GameFunctionAllocationView, reason: not valid java name */
    /* synthetic */ void m265x59351970(int i, DialogInterface dialogInterface, int i2) {
        String obj = this.mRenameDialogEdit.getText().toString();
        AppGameHandleItem appGameHandleItem = this.mHandleItemCustomize.get(i);
        this.mRenameCustomizeItem = appGameHandleItem;
        appGameHandleItem.setTitle(obj);
        this.mWorkHandler.sendEmptyMessage(MSG_RENAME_CUSTOMIZE_HANDLE);
    }

    /* renamed from: lambda$showRenameDialog$5$cn-nubia-gamelauncher-gamecontrolpanel-GameFunctionAllocationView, reason: not valid java name */
    /* synthetic */ void m266x34f69531(int i, DialogInterface dialogInterface, int i2) {
        String obj = this.mRenameDialogEdit.getText().toString();
        AppGameHandleItem appGameHandleItem = this.mHandleItemCustomize.get(i);
        this.mRenameCustomizeItem = appGameHandleItem;
        appGameHandleItem.setTitle(obj);
        this.mWorkHandler.sendEmptyMessage(MSG_RENAME_CUSTOMIZE_HANDLE);
    }

    /* renamed from: lambda$updateAutoOpenFanUI$14$cn-nubia-gamelauncher-gamecontrolpanel-GameFunctionAllocationView, reason: not valid java name */
    /* synthetic */ void m267x3d75bf3f() {
        reportAutoOpenFan();
        setChecked(this.mAutoOpenFanSwitchImage, this.mAutoOpenFanStatus == 1);
    }

    /* renamed from: lambda$updateAutoOpenLiquidUI$15$cn-nubia-gamelauncher-gamecontrolpanel-GameFunctionAllocationView, reason: not valid java name */
    /* synthetic */ void m268x135f9b99() {
        setChecked(this.mAutoOpenLiquidSwitchImage, this.mAutoOpenLiquidStatus == 1);
    }

    /* renamed from: lambda$updateResourcePreDownloadUI$16$cn-nubia-gamelauncher-gamecontrolpanel-GameFunctionAllocationView, reason: not valid java name */
    /* synthetic */ void m269x88d6d7be() {
        setChecked(this.mResourcePreDownloadSwitchImage, this.mResourcePreDownloadStatus == 1);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtil.d(TAG, "*******onAttachedToWindow*******");
        registerObserver();
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.widget.CustomSeekBar.ChangeListener
    public void onChange(CustomSeekBar customSeekBar, String str) {
        int id = customSeekBar.getId();
        if (id == R.id.post_recording_seekbar) {
            LogUtil.d(TAG, " post_recording_seekbar ---- onChange ---- data1 : " + str);
            if ("0".equals(str) && "0".equals(this.mRecordingSeekBarProgress)) {
                this.mPostRecordingSeekbarProgress = "5";
                this.mPostRecordingSeekbar.selectValue("5");
                ToastUtil.showGamemodeToast(this.mContext.getString(R.string.record_as_you_like_title_waring_text));
            } else {
                this.mPostRecordingSeekbarProgress = str;
            }
            this.mFunctionAllocationHelper.saveNubiaGameRandomPositiveRecordTime(this.mContext, this.mCurrentPackageName, this.mPostRecordingSeekbarProgress);
            return;
        }
        if (id != R.id.record_time_seekbar) {
            return;
        }
        LogUtil.d(TAG, "  record_time_seekbar ---- onChange ---- data1 : " + str);
        if ("0".equals(this.mPostRecordingSeekbarProgress) && "0".equals(str)) {
            this.mRecordingSeekBarProgress = "5";
            this.mRecordingSeekbar.selectValue("5");
            ToastUtil.showGamemodeToast(this.mContext.getString(R.string.record_as_you_like_title_waring_text));
        } else {
            this.mRecordingSeekBarProgress = str;
        }
        this.mFunctionAllocationHelper.saveNubiaGameRandomBackRecordTime(this.mContext, this.mCurrentPackageName, this.mRecordingSeekBarProgress);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v29, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v32 */
    /* JADX WARN: Type inference failed for: r4v35 */
    /* JADX WARN: Type inference failed for: r4v36, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v39 */
    /* JADX WARN: Type inference failed for: r4v42 */
    /* JADX WARN: Type inference failed for: r4v43, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v46 */
    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String str;
        int id = view.getId();
        if (id == R.id.function_redmagic_time_lamp_checkbox) {
            boolean z = !this.isTencentLampEnable;
            this.isTencentLampEnable = z;
            setChecked(this.redmagicTimeLampCheckbox, z);
            setSubDBForTimeLamp(this.isTencentLampEnable, NUBIA_TENCENT_LAMP_ENABLE);
            boolean z2 = this.isTencentLampEnable;
            if (z2) {
                setSubDBForTimeLamp(z2, "switch_main_lamp_enable");
                str = "on";
            } else {
                str = "off";
            }
            reportDataByDay(Track.MOMENT_LIGHT_EFFECT_STATUS, "option", str);
            return;
        }
        if (id == R.id.function_redmagic_time_checkbox) {
            this.isRedmagicTimeCheckboxOpen = !this.isRedmagicTimeCheckboxOpen;
            LogUtil.i(TAG, " redmagicTimeCheckBox status : " + this.isRedmagicTimeCheckboxOpen);
            if (this.isRedmagicTimeCheckboxOpen) {
                showGuide();
                return;
            } else {
                setChoiceGameSettings(false);
                return;
            }
        }
        if (id == R.id.function_redmagic_time_quality_standard) {
            if (this.isRedmagicTimeCheckboxOpen || this.isInternalVersion) {
                this.redmagicTimeQualityStandard.setTextColor(this.colorStateListSelected);
                this.redmagicTimeQualityHigh.setTextColor(this.colorStateListNormal);
                setGameVideoQualityValue(0);
                return;
            }
            return;
        }
        if (id == R.id.random_record__quality_standard) {
            if (this.isRedmagicTimeCheckboxOpen || this.isInternalVersion) {
                this.mRecordAsYouLikeQualityStandard.setTextColor(this.colorStateListSelected);
                this.mRecordAsYouLikeQualityHigh.setTextColor(this.colorStateListNormal);
                setGameVideoQualityValue(0);
                return;
            }
            return;
        }
        if (id == R.id.function_redmagic_time_quality_high) {
            if (this.isRedmagicTimeCheckboxOpen || this.isInternalVersion) {
                this.redmagicTimeQualityStandard.setTextColor(this.colorStateListNormal);
                this.redmagicTimeQualityHigh.setTextColor(this.colorStateListSelected);
                setGameVideoQualityValue(1);
                return;
            }
            return;
        }
        if (id == R.id.random_record_quality_high) {
            if (this.isRedmagicTimeCheckboxOpen || this.isInternalVersion) {
                this.mRecordAsYouLikeQualityStandard.setTextColor(this.colorStateListNormal);
                this.mRecordAsYouLikeQualityHigh.setTextColor(this.colorStateListSelected);
                setGameVideoQualityValue(1);
                return;
            }
            return;
        }
        if (id == R.id.function_redmagic_time_record_15s) {
            if (this.isRedmagicTimeCheckboxOpen || this.isInternalVersion) {
                this.redmagicTimeRecord_15s.setTextColor(this.colorStateListSelected);
                this.redmagicTimeRecord_30s.setTextColor(this.colorStateListNormal);
                this.redmagicTimeRecord_45s.setTextColor(this.colorStateListNormal);
                this.redmagicTimeRecord_60s.setTextColor(this.colorStateListNormal);
                saveManualDurationNewValueToDB(0, this.mCurrentPackageName);
                return;
            }
            return;
        }
        if (id == R.id.function_redmagic_time_record_30s) {
            if (this.isRedmagicTimeCheckboxOpen || this.isInternalVersion) {
                this.redmagicTimeRecord_15s.setTextColor(this.colorStateListNormal);
                this.redmagicTimeRecord_30s.setTextColor(this.colorStateListSelected);
                this.redmagicTimeRecord_45s.setTextColor(this.colorStateListNormal);
                this.redmagicTimeRecord_60s.setTextColor(this.colorStateListNormal);
                saveManualDurationNewValueToDB(1, this.mCurrentPackageName);
                return;
            }
            return;
        }
        if (id == R.id.function_redmagic_time_record_45s) {
            if (this.isRedmagicTimeCheckboxOpen || this.isInternalVersion) {
                this.redmagicTimeRecord_15s.setTextColor(this.colorStateListNormal);
                this.redmagicTimeRecord_30s.setTextColor(this.colorStateListNormal);
                this.redmagicTimeRecord_45s.setTextColor(this.colorStateListSelected);
                this.redmagicTimeRecord_60s.setTextColor(this.colorStateListNormal);
                saveManualDurationNewValueToDB(2, this.mCurrentPackageName);
                return;
            }
            return;
        }
        if (id == R.id.function_redmagic_time_record_60s) {
            if (this.isRedmagicTimeCheckboxOpen || this.isInternalVersion) {
                this.redmagicTimeRecord_15s.setTextColor(this.colorStateListNormal);
                this.redmagicTimeRecord_30s.setTextColor(this.colorStateListNormal);
                this.redmagicTimeRecord_45s.setTextColor(this.colorStateListNormal);
                this.redmagicTimeRecord_60s.setTextColor(this.colorStateListSelected);
                saveManualDurationNewValueToDB(3, this.mCurrentPackageName);
                return;
            }
            return;
        }
        if (id == R.id.function_redmagic_time_record_all_checkbox) {
            if (this.isRedmagicTimeCheckboxOpen) {
                ?? r4 = this.openFullVideo == 0 ? 1 : 0;
                this.openFullVideo = r4;
                setChecked(this.redmagicTimeRecordAllCheckbox, r4);
                setSubDB(this.openFullVideo != 0, DB_GAMES_FULL_VIDEO);
                return;
            }
            return;
        }
        if (id == R.id.function_redmagic_time_die_video_checkbox) {
            if (this.isRedmagicTimeCheckboxOpen) {
                ?? r42 = this.openDeath == 0 ? 1 : 0;
                this.openDeath = r42;
                setChecked(this.redmagicTimeDieVideoCheckbox, r42);
                setSubDB(this.openDeath != 0, DB_GAMES_DEATH_VIDEO);
                return;
            }
            return;
        }
        if (id == R.id.function_redmagic_time_record_die_checkbox) {
            if (this.isRedmagicTimeCheckboxOpen) {
                ?? r43 = this.openRealTimeDeath == 0 ? 1 : 0;
                this.openRealTimeDeath = r43;
                setChecked(this.redmagicTimeRecordDieCheckbox, r43);
                setSubDB(this.openRealTimeDeath != 0, DB_GAMES_REAL_TIME_DEATH_VIDEO);
                return;
            }
            return;
        }
        if (id == R.id.function_virtual_shake_feedback_checkbox) {
            boolean z3 = !this.isShakeFeedbackCheck;
            this.isShakeFeedbackCheck = z3;
            setChecked(this.virtualShakeFeedbackCheckbox, z3);
            setShakeFeedbackStatus(this.isShakeFeedbackCheck);
            return;
        }
        if (id == R.id.function_virtual_key_show_checkbox) {
            boolean z4 = !this.isKeyDisplayCheck;
            this.isKeyDisplayCheck = z4;
            setChecked(this.virtualKeyShowCheckbox, z4);
            setKeyDisplayStatus(this.isKeyDisplayCheck);
            return;
        }
        if (id == R.id.function_virtual_open_suggestion_checkbox) {
            boolean z5 = !this.isOpenSuggestCheck;
            this.isOpenSuggestCheck = z5;
            setChecked(this.virtualOpenSuggestionCheckbox, z5);
            setOpenSuggestStatus(this.isOpenSuggestCheck);
            return;
        }
        if (id == R.id.function_virtual_video_course_layout) {
            showVideoView();
            return;
        }
        if (id == R.id.auto_open_fan_checkbox) {
            int i = this.mAutoOpenFanStatus == 1 ? 0 : 1;
            this.mAutoOpenFanStatus = i;
            setChecked(this.mAutoOpenFanSwitchImage, i == 1);
            this.mWorkHandler.sendEmptyMessage(MSG_UPDATE_AUTO_OPEN_FAN_STATUS_HANDLE);
            reportAutoOpenFan();
            return;
        }
        if (id == R.id.auto_open_liquid_cooling_checkbox) {
            int i2 = this.mAutoOpenLiquidStatus == 1 ? 0 : 1;
            this.mAutoOpenLiquidStatus = i2;
            setChecked(this.mAutoOpenLiquidSwitchImage, i2 == 1);
            this.mWorkHandler.sendEmptyMessage(MSG_UPDATE_AUTO_OPEN_LIQUID_STATUS_HANDLE);
            return;
        }
        if (id == R.id.resource_pre_download_checkbox) {
            int i3 = this.mResourcePreDownloadStatus == 1 ? 0 : 1;
            this.mResourcePreDownloadStatus = i3;
            setChecked(this.mResourcePreDownloadSwitchImage, i3 == 1);
            this.mWorkHandler.sendEmptyMessage(MSG_UPDATE_RESOURCE_PRE_DOWNLOAD_STATUS_HANDLE);
        }
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.virtual.VirtualTypeAdapter.ICustomizePositionListener
    public void onCustomizePosition(String str, int i, String str2) {
        this.mRealPosition = i;
        if (i >= this.mHandleItemCustomize.size()) {
            return;
        }
        try {
            AppGameHandleItem appGameHandleItem = this.mHandleItemCustomize.get(i);
            this.mUseCustomizeItem = appGameHandleItem;
            if ("1".equals(appGameHandleItem.getType())) {
                this.virtualSettingImageBtnDelete.setVisibility(0);
                this.virtualSettingImageBtnRename.setVisibility(0);
            } else {
                this.virtualSettingImageBtnDelete.setVisibility(8);
                this.virtualSettingImageBtnRename.setVisibility(8);
            }
        } catch (Exception e) {
            LogUtil.e(TAG, " onCustomizePosition Exception" + e.toString());
        }
        if (i < 0 || i >= this.mImageCustomize.size()) {
            this.virtualSettingImage.setImageDrawable(this.mImageCustomize.get(0));
        } else if (this.mImageCustomize.get(i) == null) {
            this.virtualSettingImage.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.bg_wangzhe3));
        } else {
            this.virtualSettingImage.setImageDrawable(this.mImageCustomize.get(i));
        }
        if (str.equals("visible")) {
            return;
        }
        this.mWorkHandler.sendEmptyMessage(MSG_USE_CUSTOMIZE_HANDLE);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        unregisterObserver();
        AlertDialogCenter alertDialogCenter = this.mDeleteDialog;
        if (alertDialogCenter != null) {
            alertDialogCenter.dismiss();
            this.mDeleteDialog = null;
        }
        AlertDialogCenter alertDialogCenter2 = this.mRenameDialog;
        if (alertDialogCenter2 != null) {
            alertDialogCenter2.dismiss();
            this.mRenameDialog = null;
        }
        super.onDetachedFromWindow();
    }

    public void registerObserver() {
        this.contentResolver.registerContentObserver(Settings.Global.getUriFor(PERFORMANCE_MODE_VALUE), false, this.mPerformanceLowObserver);
        this.contentResolver.registerContentObserver(Settings.Global.getUriFor(DB_GAMES_CHICKEN_MODE), true, this.mChickenModeChangedObserver);
        this.contentResolver.registerContentObserver(Settings.Global.getUriFor("db_game_chicken_value"), true, this.mChickenModeChangedObserver);
    }

    public void setChecked(ImageView imageView, boolean z) {
        imageView.setImageResource(z ? R.drawable.function_toggle_on : R.drawable.function_toggle_off);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        LogUtil.d(TAG, " isEnabled = " + z);
        if (FunctionAllocationHelper.getInstance().supportGameHighLight(this.mCurrentPackageName)) {
            if (z) {
                this.redmagicTimeCheckbox.setOnClickListener(this);
            } else {
                this.redmagicTimeCheckbox.setOnClickListener(this.redMagicChekboxClickListener);
            }
        }
        this.redmagicTimeRecord_15s.setEnabled(z);
        this.redmagicTimeRecord_30s.setEnabled(z);
        this.redmagicTimeRecord_45s.setEnabled(z);
        this.redmagicTimeRecord_60s.setEnabled(z);
        this.redmagicTimeQualityHigh.setEnabled(z);
        this.redmagicTimeQualityStandard.setEnabled(z);
        this.mRecordAsYouLikeQualityHigh.setEnabled(z);
        this.mRecordAsYouLikeQualityStandard.setEnabled(z);
    }

    public void showVideoView() {
        if (this.mIsAddView) {
            return;
        }
        HandleHelpView handleHelpView = (HandleHelpView) LayoutInflater.from(this.mContext).inflate(R.layout.nubia_handle_help_view, (ViewGroup) null);
        this.mHandleHelpView = handleHelpView;
        handleHelpView.setSystemUiVisibility(4);
        this.mHandleHelpView.setFocusable(true);
        this.mHandleHelpView.setFocusableInTouchMode(true);
        this.mHandleHelpView.requestFocus();
        this.mHandleHelpView.requestFocusFromTouch();
        this.mWindowManager.addView(this.mHandleHelpView, createDefaultLayoutParams());
        this.mHandleHelpView.setViewCtrl(this, 1);
        this.mIsAddView = true;
    }

    public void unregisterObserver() {
        this.contentResolver.unregisterContentObserver(this.mPerformanceLowObserver);
        this.contentResolver.unregisterContentObserver(this.mChickenModeChangedObserver);
    }
}
