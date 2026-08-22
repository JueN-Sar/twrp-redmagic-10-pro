package cn.nubia.gamelauncher.gamecontrolpanel;

import android.content.Context;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.AnimationUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.ControlPanelFeatureHelper;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils;
import cn.nubia.gamelauncher.util.OwlSysHelper;
import com.zte.gameassist.ai.AIFlickerTips;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class GameNetSettingsView extends FrameLayout implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, GameControlDialog.ISetViewAnimation {
    private static final String DATA_LOW_LATENCY = "gsc_data_low_latency_mode";
    private static final String DB_GAME_PERFORMANCE_MODE_ALL_LIST = "NubiaperformanceMode";
    private static final String TAG = "GameNetSettingsView";
    private static final String WIFI_LOW_LATENCY = "gsc_wifi_low_latency_mode";
    private boolean iswifiLowLatencySwitch;
    private Context mContext;
    private String mCurrentPackageName;
    private String mCurrentPkg;
    private ImageView mDataLowLatencyCheckbox;
    private RelativeLayout mDataLowLatencyLayout;
    private boolean mIsDataLowLatencySwitch;
    private View mLayoutView;
    private String m_tag;
    private ImageView wifiLowLatencyCheckbox;
    private RelativeLayout wifiLowLatencyLayout;
    private static final boolean SUPPORT_DATA_LOW_LETANCY = ControlPanelFeatureHelper.getZteFeatureRedMagicGameLatencyDataSwitch().booleanValue();
    private static List<String> mPackageWhiteList = Arrays.asList(HighLightsUtils.WZRY_PACKAGE_NAME, "com.tencent.inotool.full", HighLightsUtils.CJZC_PACKAGE_NAME, HighLightsUtils.PUBG_PACKAGE_NAME, "com.rekoo.pubgm", "com.pubg.krmobile", HighLightsUtils.SMZH_PACKAGE_NAME, "com.tencent.tmgp.cf", "com.tencent.tmgp.speedmobile", HighLightsUtils.LOL_PACKAGE_NAME, HighLightsUtils.YS_PACKAGE_NAME, "com.miHoYo.ys.mi", "com.miHoYo.ys.bilibili", "com.miHoYo.GenshinImpact", "com.pwrd.hotta.laohu", "com.games.hotta.nubia", "com.tencent.tmgp.hotta", "com.hottagames.hotta.mi", "com.hottagames.hotta.aligames", "com.tencent.tmgp.djsy", "com.tencent.KiHan", HighLightsUtils.XQTD_PACKAGE_NAME, "com.netease.nshm");
    private static List<String> mDataLowLetancyPackageWhiteList = new ArrayList(Arrays.asList(HighLightsUtils.WZRY_PACKAGE_NAME, "com.tencent.inotool.full", HighLightsUtils.CJZC_PACKAGE_NAME, HighLightsUtils.PUBG_PACKAGE_NAME, "com.rekoo.pubgm", "com.pubg.krmobile", HighLightsUtils.SMZH_PACKAGE_NAME, "com.tencent.tmgp.cf", "com.tencent.tmgp.speedmobile", HighLightsUtils.LOL_PACKAGE_NAME, HighLightsUtils.YS_PACKAGE_NAME, "com.miHoYo.ys.mi", "com.miHoYo.ys.bilibili", "com.miHoYo.GenshinImpact", "com.pwrd.hotta.laohu", "com.games.hotta.nubia", "com.tencent.tmgp.hotta", "com.hottagames.hotta.mi", "com.hottagames.hotta.aligames", "com.tencent.tmgp.djsy", "com.tencent.KiHan", HighLightsUtils.XQTD_PACKAGE_NAME, "com.netease.nshm"));

    public GameNetSettingsView(Context context) {
        this(context, null);
    }

    public GameNetSettingsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GameNetSettingsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    private String getGameStrengthenValue(String str, String str2) {
        String string;
        if (!TextUtils.isEmpty(str) && (string = Settings.Global.getString(this.mContext.getContentResolver(), str2)) != null && string.indexOf(str) != -1) {
            for (String str3 : string.split(",")) {
                String trim = str3.trim();
                if (!trim.isEmpty() && trim.indexOf(str) != -1) {
                    return trim;
                }
            }
        }
        return null;
    }

    private View getShowFlickerView(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        str.hashCode();
        if (str.equals("data_low_latency_layout")) {
            return this.mDataLowLatencyLayout;
        }
        if (str.equals("wifi_low_latency_layout")) {
            return this.wifiLowLatencyLayout;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int getSpecialValue(java.lang.String r5, java.lang.String r6, int r7) {
        /*
            r4 = this;
            java.lang.String r0 = "GameNetSettingsView"
            java.lang.String r1 = "value: "
            r2 = -1
            java.lang.String r5 = r4.getGameStrengthenValue(r6, r5)     // Catch: java.lang.Exception -> L2c
            if (r5 == 0) goto L2a
            java.lang.String r6 = "+"
            int r6 = r5.indexOf(r6)     // Catch: java.lang.Exception -> L2c
            r3 = 1
            int r4 = r4.getSpecificGameStrengthenParam(r5, r6, r3)     // Catch: java.lang.Exception -> L2c
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L28
            r5.<init>(r1)     // Catch: java.lang.Exception -> L28
            java.lang.StringBuilder r5 = r5.append(r4)     // Catch: java.lang.Exception -> L28
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> L28
            cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil.d(r0, r5)     // Catch: java.lang.Exception -> L28
            goto L44
        L28:
            r5 = move-exception
            goto L2e
        L2a:
            r4 = r2
            goto L44
        L2c:
            r5 = move-exception
            r4 = r2
        L2e:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r1 = " getSpecialValue err :  "
            r6.<init>(r1)
            java.lang.String r5 = r5.toString()
            java.lang.StringBuilder r5 = r6.append(r5)
            java.lang.String r5 = r5.toString()
            cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil.e(r0, r5)
        L44:
            if (r4 != r2) goto L47
            goto L48
        L47:
            r7 = r4
        L48:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.gamecontrolpanel.GameNetSettingsView.getSpecialValue(java.lang.String, java.lang.String, int):int");
    }

    private int getSpecificGameStrengthenParam(String str, int i, int i2) {
        try {
            return Integer.parseInt(String.valueOf(str.charAt(i + i2)));
        } catch (StringIndexOutOfBoundsException | Exception unused) {
            return -1;
        }
    }

    private boolean isDataLowLatencyOpen() {
        int specialValue = mDataLowLetancyPackageWhiteList.contains(this.mCurrentPkg) ? getSpecialValue(DATA_LOW_LATENCY, this.mCurrentPkg, 1) : getSpecialValue(DATA_LOW_LATENCY, this.mCurrentPkg, 0);
        LogUtil.i(TAG, "isDataLowLatencyOpen: wifiLow = " + specialValue);
        return specialValue == 1;
    }

    private boolean isSavingModeForPerformance() {
        if (TextUtils.isEmpty(this.mCurrentPkg)) {
            return true;
        }
        String string = Settings.Global.getString(this.mContext.getContentResolver(), DB_GAME_PERFORMANCE_MODE_ALL_LIST);
        if (TextUtils.isEmpty(string) || !string.contains(this.mCurrentPkg)) {
            return true;
        }
        for (String str : string.split(",")) {
            if (!TextUtils.isEmpty(str) && str.contains(this.mCurrentPkg)) {
                int specificGameStrengthenParam = getSpecificGameStrengthenParam(str, str.indexOf("+"), 1);
                LogUtil.d(TAG, "performanceMode = " + specificGameStrengthenParam);
                return specificGameStrengthenParam != 1;
            }
        }
        return true;
    }

    private boolean isWifiLowLatencyOpen() {
        int specialValue = Utils.isWhiteListDefaultOnWifi() ? mPackageWhiteList.contains(this.mCurrentPkg) ? getSpecialValue(WIFI_LOW_LATENCY, this.mCurrentPkg, 1) : getSpecialValue(WIFI_LOW_LATENCY, this.mCurrentPkg, 0) : getSpecialValue(WIFI_LOW_LATENCY, this.mCurrentPkg, 1);
        LogUtil.i(TAG, "isWifiLowLatencyOpen: wifiLow = " + specialValue);
        return specialValue == 1;
    }

    private void saveGameStrengthenNewValueToDB(int i, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        String string = Settings.Global.getString(this.mContext.getContentResolver(), str);
        LogUtil.d(TAG, "oldvle " + string);
        if (!TextUtils.isEmpty(string) && string.contains(str2 + "+")) {
            String[] split = string.split(",");
            int length = split.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                String str3 = split[i2];
                if (!TextUtils.isEmpty(str3) && str3.contains(str2 + "+")) {
                    string = string.replace(str3, str2 + "+" + i);
                    break;
                }
                i2++;
            }
        } else {
            string = string != null ? string + str2 + "+" + i + "," : str2 + "+" + i + ",";
        }
        LogUtil.i(TAG, "newvle " + string);
        Settings.Global.putString(this.mContext.getContentResolver(), str, string);
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog.ISetViewAnimation
    public void animationSelf(final boolean z) {
        new Handler().post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameNetSettingsView.2
            @Override // java.lang.Runnable
            public void run() {
                if (!z) {
                    GameNetSettingsView.this.mLayoutView.setAlpha(0.0f);
                } else {
                    AnimationUtil.setGpuTranslationY(GameNetSettingsView.this.mLayoutView);
                    AnimationUtil.setGcsRedItemAlpha(GameNetSettingsView.this.mLayoutView);
                }
            }
        });
    }

    public void initStartType(String str) {
        this.mCurrentPkg = str;
        initView();
    }

    public void initView() {
        LayoutInflater.from(getContext()).inflate(GameControlOrientationManager.getInstance().isPortrait() ? R.layout.game_net_settings_layout_port : R.layout.game_net_settings_layout, this);
        this.mLayoutView = findViewById(R.id.nubia_wifi_low_latency_layout);
        new Handler().post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameNetSettingsView.1
            @Override // java.lang.Runnable
            public void run() {
                AnimationUtil.setGpuTranslationY(GameNetSettingsView.this.mLayoutView);
            }
        });
        this.wifiLowLatencyLayout = (RelativeLayout) findViewById(R.id.wifi_low_latency_layout);
        this.wifiLowLatencyCheckbox = (ImageView) findViewById(R.id.wifi_low_latency_checkbox);
        this.mDataLowLatencyLayout = (RelativeLayout) findViewById(R.id.data_low_latency_layout);
        this.mDataLowLatencyCheckbox = (ImageView) findViewById(R.id.data_low_latency_checkbox);
        boolean isWifiLowLatencyOpen = isWifiLowLatencyOpen();
        this.iswifiLowLatencySwitch = isWifiLowLatencyOpen;
        setSwitched(this.wifiLowLatencyCheckbox, isWifiLowLatencyOpen);
        this.wifiLowLatencyCheckbox.setOnClickListener(this);
        setWifiSwitchEnable();
        StringBuilder sb = new StringBuilder("initView: SUPPORT_DATA_LOW_LETANCY = ");
        boolean z = SUPPORT_DATA_LOW_LETANCY;
        LogUtil.i(TAG, sb.append(z).toString());
        if (z) {
            this.mDataLowLatencyLayout.setVisibility(0);
            boolean isDataLowLatencyOpen = isDataLowLatencyOpen();
            this.mIsDataLowLatencySwitch = isDataLowLatencyOpen;
            setSwitched(this.mDataLowLatencyCheckbox, isDataLowLatencyOpen);
            this.mDataLowLatencyCheckbox.setOnClickListener(this);
            setDataSwitchEnable();
        } else {
            this.mDataLowLatencyLayout.setVisibility(8);
            this.mDataLowLatencyCheckbox.setVisibility(8);
        }
        showFlicker();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.wifi_low_latency_checkbox) {
            boolean z = !this.iswifiLowLatencySwitch;
            this.iswifiLowLatencySwitch = z;
            setSwitched(this.wifiLowLatencyCheckbox, z);
            saveGameStrengthenNewValueToDB(this.iswifiLowLatencySwitch ? 1 : 0, WIFI_LOW_LATENCY, this.mCurrentPkg);
            OwlSysHelper.insertOwlDayCv("WiFi_low_delay_switch_status", "switch_status", this.iswifiLowLatencySwitch ? "on" : "off");
            return;
        }
        if (id == R.id.data_low_latency_checkbox) {
            boolean z2 = !this.mIsDataLowLatencySwitch;
            this.mIsDataLowLatencySwitch = z2;
            setSwitched(this.mDataLowLatencyCheckbox, z2);
            saveGameStrengthenNewValueToDB(this.mIsDataLowLatencySwitch ? 1 : 0, DATA_LOW_LATENCY, this.mCurrentPkg);
            OwlSysHelper.insertOwlDayCv("DATA_low_delay_switch_status", "switch_status", this.mIsDataLowLatencySwitch ? "on" : "off");
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void setDataSwitchEnable() {
        if (isSavingModeForPerformance()) {
            this.mDataLowLatencyCheckbox.setEnabled(true);
            this.mDataLowLatencyCheckbox.setAlpha(1.0f);
            this.mDataLowLatencyLayout.setAlpha(1.0f);
        } else {
            this.mDataLowLatencyCheckbox.setEnabled(false);
            this.mDataLowLatencyCheckbox.setAlpha(0.5f);
            this.mDataLowLatencyLayout.setAlpha(0.5f);
        }
    }

    public void setSwitched(ImageView imageView, boolean z) {
        imageView.setImageResource(z ? R.drawable.function_toggle_on : R.drawable.function_toggle_off);
    }

    public void setWifiSwitchEnable() {
        if (isSavingModeForPerformance()) {
            this.wifiLowLatencyCheckbox.setEnabled(true);
            this.wifiLowLatencyCheckbox.setAlpha(1.0f);
            this.wifiLowLatencyLayout.setAlpha(1.0f);
        } else {
            this.wifiLowLatencyCheckbox.setEnabled(false);
            this.wifiLowLatencyCheckbox.setAlpha(0.5f);
            this.wifiLowLatencyLayout.setAlpha(0.5f);
        }
    }

    public void showFlicker() {
        View showFlickerView;
        String highLightViewId = Utils.getHighLightViewId();
        if (TextUtils.isEmpty(highLightViewId) || (showFlickerView = getShowFlickerView(highLightViewId)) == null) {
            return;
        }
        AIFlickerTips.setFlickerName(showFlickerView, highLightViewId);
        AIFlickerTips.setFlickerPadding(showFlickerView, 3, 3, 3, 3);
        AIFlickerTips.showFlicker(highLightViewId);
    }
}
