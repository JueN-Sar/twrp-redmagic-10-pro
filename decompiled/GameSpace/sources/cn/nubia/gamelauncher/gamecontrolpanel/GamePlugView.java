package cn.nubia.gamelauncher.gamecontrolpanel;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import cn.nubia.gamecenter.settings.utils.Utils;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog;
import cn.nubia.gamelauncher.gamecontrolpanel.config.PluginConfig;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.AnimationUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.virtual.Constants;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.gamepad.utils.GamepadContentHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class GamePlugView extends FrameLayout implements GameControlDialog.ISetViewAnimation {
    private static final String PERFORMANCE_MODE_VALUE = "performance_mode_value";
    private static final String TAG = "GamePlugView";
    private final String ACTION_GAMEPADSERVICE;
    private final String ACTION_LKM_MAP;
    private String DB_OPERATION_DEVICE_STATE;
    private List<GamePlugData> gamePlugDataList;
    private GridView gridView;
    private HandlerThread handlerThread;
    private PlugAdapter mBaseAdapter;
    private Context mContext;
    private Intent mIntent;
    private String mPackageName;
    private ContentObserver mPerformanceLowObserver;
    private String mPlugeName;
    private Handler mUiHandler;
    private Handler mWorkHandler;
    private List<Integer> performanceLowDisabledPlug;

    public GamePlugView(Context context) {
        this(context, null);
    }

    public GamePlugView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GamePlugView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.DB_OPERATION_DEVICE_STATE = GamepadContentHelper.DB_GAME_DEVICES_STATE;
        this.ACTION_GAMEPADSERVICE = "cn.nubia.gamepad.startGamepadService";
        this.ACTION_LKM_MAP = "cn.nubia.keymapcenter.intent.action.LKM_MAP";
        this.performanceLowDisabledPlug = new ArrayList();
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.handlerThread = handlerThread;
        this.mContext = context;
        handlerThread.start();
        this.mWorkHandler = new Handler(this.handlerThread.getLooper());
        this.mUiHandler = new Handler(Looper.getMainLooper());
        this.mPerformanceLowObserver = new ContentObserver(this.mWorkHandler) { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GamePlugView.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                int i2 = Settings.Global.getInt(GamePlugView.this.mContext.getContentResolver(), GamePlugView.PERFORMANCE_MODE_VALUE, 2);
                if (i2 == 1) {
                    GamePlugView.this.disableHighPowerConsumptionPlug();
                } else if (i2 > 1) {
                    GamePlugView.this.enableHighPowerConsumptionPlug();
                }
            }
        };
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.nubia_game_strengthen_view_plug, this);
        this.gridView = (GridView) findViewById(R.id.grid_view);
        this.gamePlugDataList = getPlugList();
        int i = Settings.Global.getInt(getContext().getContentResolver(), PERFORMANCE_MODE_VALUE, 2);
        for (GamePlugData gamePlugData : this.gamePlugDataList) {
            if (i == 1 && this.performanceLowDisabledPlug.contains(Integer.valueOf(gamePlugData.getContentId()))) {
                gamePlugData.isEnabled = false;
            }
        }
        PlugAdapter plugAdapter = new PlugAdapter(getContext(), R.layout.game_plug_gridview_item, this.gamePlugDataList);
        this.mBaseAdapter = plugAdapter;
        this.gridView.setAdapter((ListAdapter) plugAdapter);
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog.ISetViewAnimation
    public void animationSelf(final boolean z) {
        new Handler().post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GamePlugView.2
            @Override // java.lang.Runnable
            public void run() {
                if (!z) {
                    GamePlugView.this.gridView.setAlpha(0.0f);
                } else {
                    AnimationUtil.setResourceItemTranslationX(GamePlugView.this.gridView, 0);
                    AnimationUtil.setGcsRedItemAlpha(GamePlugView.this.gridView);
                }
            }
        });
    }

    public void disableHighPowerConsumptionPlug() {
        for (int i = 0; i < this.gamePlugDataList.size(); i++) {
            GamePlugData gamePlugData = this.gamePlugDataList.get(i);
            if (this.performanceLowDisabledPlug.contains(Integer.valueOf(gamePlugData.getContentId()))) {
                int i2 = Settings.Global.getInt(getContext().getContentResolver(), gamePlugData.getKey(), 0);
                Settings.Global.putInt(getContext().getContentResolver(), gamePlugData.getKey(), 0);
                Settings.Global.putInt(getContext().getContentResolver(), gamePlugData.getKey() + "_backup", i2);
                LogUtil.d(TAG, gamePlugData.getKey() + " disable plug:" + getContext().getResources().getString(gamePlugData.getContentId()) + " current status:" + i2);
                gamePlugData.deactivatePluginFunction(getContext());
                gamePlugData.isEnabled = false;
                this.mUiHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GamePlugView$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        GamePlugView.this.m270xe9e5e393();
                    }
                });
            }
        }
    }

    public void enableHighPowerConsumptionPlug() {
        for (int i = 0; i < this.gamePlugDataList.size(); i++) {
            GamePlugData gamePlugData = this.gamePlugDataList.get(i);
            if (this.performanceLowDisabledPlug.contains(Integer.valueOf(gamePlugData.getContentId()))) {
                int i2 = Settings.Global.getInt(getContext().getContentResolver(), gamePlugData.getKey() + "_backup", 0);
                LogUtil.d(TAG, gamePlugData.getKey() + " enable plug:" + getContext().getResources().getString(gamePlugData.getContentId()) + " previous status:" + i2);
                Settings.Global.putInt(getContext().getContentResolver(), gamePlugData.getKey(), i2);
                gamePlugData.isEnabled = true;
                this.mUiHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GamePlugView$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        GamePlugView.this.m271x7882f649();
                    }
                });
            }
        }
    }

    public List<GamePlugData> getPlugList() {
        ArrayList arrayList = new ArrayList();
        boolean z = SystemProperties.getInt("sys.nubia_internal_version_flag", 0) != 1;
        boolean isZte = Util.isZte();
        if (!isZte && PluginConfig.isPluginEnable(getContext(), PluginConfig.PLUGIN_SIGHT, this.mPackageName, z)) {
            Intent intent = new Intent("cn.nubia.gamelauncher.action.delay_close_aim_helper_for_package");
            intent.setPackage("cn.nubia.gamelauncher");
            intent.putExtra("packagename", this.mPackageName);
            arrayList.add(new GamePlugData(R.drawable.sight_assist, R.string.plug_sight_help_content, this.mPackageName + "_sight_assist_plugin_enable", intent));
        }
        if (!isZte && PluginConfig.isPluginEnable(getContext(), PluginConfig.PLUGIN_KEYLINK, this.mPackageName, z)) {
            Intent intent2 = new Intent(Constants.STOP_KEYLINK_PLUGIN);
            intent2.setPackage(Constants.KEYLINK_PLUG_PACKAGE);
            intent2.putExtra("packagename", this.mPackageName);
            arrayList.add(new GamePlugData(R.drawable.key_link, R.string.plug_keylink_mode_content, this.mPackageName + "_keylink_plugin_enable", intent2));
        }
        if (!isZte && PluginConfig.isPluginEnable(getContext(), PluginConfig.PLUGIN_BROADCAST, this.mPackageName, z)) {
            arrayList.add(new GamePlugData(R.drawable.broad_cast, R.string.plug_broadcast_mode_content, this.mPackageName + "_redmagic_broadcast_plugin_enable", null));
        }
        if (PluginConfig.isPluginEnable(getContext(), PluginConfig.PLUGIN_HUNT, this.mPackageName, z)) {
            arrayList.add(new GamePlugData(R.drawable.hunting_mode, R.string.plug_hunting_mode_content, this.mPackageName + "_hunting_mode_plugin_enable", null));
        }
        if (!isZte && PluginConfig.isPluginEnable(getContext(), PluginConfig.PLUGIN_4D, this.mPackageName, z)) {
            arrayList.add(new Vibration4dGamePlugData(R.drawable.vibrate, R.string.plug_fd_shock_content, this.mPackageName + "_vibrate_plugin_enable", null));
            this.performanceLowDisabledPlug.add(Integer.valueOf(R.string.fd_shock_title));
        }
        if (PluginConfig.isPluginEnable(getContext(), PluginConfig.PLUGIN_INVESTIGATE, this.mPackageName, z)) {
            arrayList.add(new GamePlugData(R.drawable.investigation_mode, R.string.plug_detect_mode_content, this.mPackageName + "_investigation_mode_plugin_enable", null));
        }
        if (!isZte && PluginConfig.isPluginEnable(getContext(), PluginConfig.PLUGIN_CHAT, this.mPackageName, z) && !Utils.isNotSupportMoJiHeiHuaPlug()) {
            arrayList.add(new GamePlugData(R.drawable.chat_assit, R.string.plug_chat_help_content, this.mPackageName + "_chat_assit_plugin_enable", null));
        }
        if (!isZte && PluginConfig.isPluginEnable(getContext(), PluginConfig.PLUGIN_KEYPOSISTION, this.mPackageName, z)) {
            Intent intent3 = new Intent();
            intent3.setAction(Constants.STOP_KEYPOSITION_ASSIST);
            intent3.setPackage(Constants.KEYPOSITION_ASSIST_PACKAGE);
            intent3.putExtra("package_name", this.mPackageName);
            intent3.putExtra("reason", "remove_plugin");
            arrayList.add(new GamePlugData(R.drawable.keyposition_assist, R.string.plug_free_change_key_content, this.mPackageName + "_keyposition_assist_plugin_enable", intent3));
        }
        if (!isZte && PluginConfig.isPluginEnable(getContext(), PluginConfig.PLUGIN_SOUND, this.mPackageName, z)) {
            Intent intent4 = new Intent();
            intent4.setAction(Constants.STOP_SOUND_EFFECT);
            intent4.setPackage(Constants.PKG_SOUND_EFFECT);
            intent4.putExtra("packagename", this.mPackageName);
            intent4.putExtra("reason", 5);
            arrayList.add(new GamePlugData(R.drawable.sound_effect, R.string.plug_sound_equalizer_content, this.mPackageName + "_sound_effect_plugin_enable", intent4));
        }
        if (PluginConfig.isPluginEnable(getContext(), PluginConfig.PLUGIN_SIGHT, this.mPackageName, z)) {
            arrayList.add(new GamePlugData(R.drawable.fast_stop_watch, R.string.plug_fast_stop_watch_content, this.mPackageName + "_timer_plugin_enable", null));
        }
        if (!isZte && PluginConfig.isPluginEnable(getContext(), "help", this.mPackageName, z)) {
            arrayList.add(new GamePlugData(R.drawable.helper, R.string.plug_magic_helper_content, this.mPackageName + "_help_plugin_enable", null));
        }
        if (!isZte && PluginConfig.isPluginEnable(getContext(), PluginConfig.PLUGIN_RANGE, this.mPackageName, z)) {
            Intent intent5 = new Intent(Constants.STOP_RANGE_LINE);
            intent5.setPackage(Constants.RANGE_LINE_PACKAGE);
            intent5.putExtra("packagename", this.mPackageName);
            arrayList.add(new GamePlugData(R.drawable.guide, R.string.plug_guide_mode_content, this.mPackageName + "_range_line_plugin_enable", intent5));
        }
        if (!isZte && PluginConfig.isPluginEnable(getContext(), PluginConfig.PLUGIN_CONTROL, this.mPackageName, z)) {
            Intent intent6 = new Intent();
            if (Settings.Global.getInt(this.mContext.getContentResolver(), this.DB_OPERATION_DEVICE_STATE, 0) == 1) {
                intent6.setAction("cn.nubia.gamepad.startGamepadService");
                intent6.putExtra("action_type", 1);
                intent6.putExtra("packagename", this.mPackageName);
                intent6.setPackage("cn.nubia.gamepad");
                arrayList.add(new GamePlugData(R.drawable.control_center, R.string.plug_control_center, this.mPackageName + "_operation_devices_plugin_enable", intent6));
            } else if (Settings.Global.getInt(this.mContext.getContentResolver(), this.DB_OPERATION_DEVICE_STATE, 0) == 2) {
                intent6.setAction("cn.nubia.keymapcenter.intent.action.LKM_MAP");
                intent6.putExtra("reason", "disable_local_key_mouse");
                intent6.putExtra("package_name", this.mPackageName);
                intent6.setPackage(Constants.KEYPOSITION_ASSIST_PACKAGE);
                arrayList.add(new GamePlugData(R.drawable.control_center, R.string.plug_control_center, this.mPackageName + "_operation_devices_plugin_enable", intent6));
            } else {
                arrayList.add(new GamePlugData(R.drawable.control_center, R.string.plug_control_center, this.mPackageName + "_operation_devices_plugin_enable", null));
            }
        }
        Collections.reverse(arrayList);
        return arrayList;
    }

    /* renamed from: lambda$disableHighPowerConsumptionPlug$0$cn-nubia-gamelauncher-gamecontrolpanel-GamePlugView, reason: not valid java name */
    /* synthetic */ void m270xe9e5e393() {
        this.mBaseAdapter.notifyDataSetChanged();
    }

    /* renamed from: lambda$enableHighPowerConsumptionPlug$1$cn-nubia-gamelauncher-gamecontrolpanel-GamePlugView, reason: not valid java name */
    /* synthetic */ void m271x7882f649() {
        this.mBaseAdapter.notifyDataSetChanged();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        registerObserver();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unregisterObserver();
    }

    public void registerObserver() {
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor(PERFORMANCE_MODE_VALUE), false, this.mPerformanceLowObserver);
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
        LogUtil.d(TAG, "mPackageName: " + this.mPackageName);
        initView();
    }

    public void unregisterObserver() {
        this.mContext.getContentResolver().unregisterContentObserver(this.mPerformanceLowObserver);
    }
}
