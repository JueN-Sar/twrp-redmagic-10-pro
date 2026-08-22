package cn.nubia.gamecenter.settings.screen;

import android.content.Context;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.CategoryInfo;
import cn.nubia.gamecenter.settings.FragmentInterface;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.AnimationPreferenceFragment;
import cn.nubia.gamecenter.settings.compatible.SwitchPreference;
import cn.nubia.gamecenter.settings.preference.GameCenterDividerGridItemDecoration;
import cn.nubia.gamecenter.settings.preference.GameCenterSwitchPreference;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import cn.nubia.settings.owlsysaciton.OwlSysHelper;
import cn.nubia.settings.trackclient.NubiaTrackManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/* loaded from: classes.dex */
public class ScreenSettingsFragment extends AnimationPreferenceFragment implements FragmentInterface, SwitchPreference.OnPreferenceChangeListener {
    private static final String SOCKET_NAME = "pps";
    private static final String TAG = "ScreenSettingsFragment";
    private Context mContext;
    private RecyclerView mDashboard;
    private GameCenterSwitchPreference mGameModeColorEnhanced;
    private GameCenterSwitchPreference mGameModeFixLight;
    private GameCenterSwitchPreference mGameModeSaveEnergy;
    private InputStream mInLtm;
    private OutputStream mOutLtm;
    private LocalSocket mSocket;
    private String m_tag;
    private static final String KEY_GAME_MODE_FIX_LIGHT = "gamemode_fix_light";
    private static final String KEY_GAME_MODE_SAVE_ENERGY = "gamemode_save_energy";
    private static final String KEY_GAME_COLOR_ENHANCED = "gamemode_color_enhanced";
    private static final String[] PREFERENCE_ITEMS = {KEY_GAME_MODE_FIX_LIGHT, KEY_GAME_MODE_SAVE_ENERGY, KEY_GAME_COLOR_ENHANCED};
    private byte[] ltmOpen = "Ltm:On:Primary:Auto\n".getBytes(StandardCharsets.UTF_8);
    private byte[] ltmClose = "Ltm:Off:Primary\n".getBytes(StandardCharsets.UTF_8);

    public static CategoryInfo getCategoryInfo() {
        return new CategoryInfo(ScreenSettingsFragment.class, R.drawable.screen_settings, R.string.gcs_gamecenter_menu_screen);
    }

    private void iniAllPerferences() {
        this.mGameModeFixLight.setChecked(SettingUtil.getGameMode(this.mContext, 256));
        this.mGameModeColorEnhanced.setChecked(SettingUtil.getColorEnhanced(this.mContext, 1));
        this.mGameModeSaveEnergy.setChecked(SettingUtil.getScreenSaveEnergy(this.mContext));
    }

    private void init() {
        removePreference(KEY_GAME_MODE_SAVE_ENERGY);
        List<String> gameCenterScreenSettings = FeatureUtil.getGameCenterScreenSettings();
        for (String str : PREFERENCE_ITEMS) {
            if (!gameCenterScreenSettings.contains(str)) {
                removePreference(str);
            }
        }
    }

    public void closeLtm() throws IOException {
        writeLtm(this.ltmClose);
    }

    public void connectSocket() {
        try {
            this.mSocket = new LocalSocket();
            LocalSocketAddress localSocketAddress = new LocalSocketAddress(SOCKET_NAME, LocalSocketAddress.Namespace.RESERVED);
            LogUtil.i(TAG, "connectSocket1: ");
            this.mSocket.connect(localSocketAddress);
            LogUtil.i(TAG, "connectSocket2: ");
            this.mInLtm = this.mSocket.getInputStream();
            this.mOutLtm = this.mSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnectSocket() {
        try {
            OutputStream outputStream = this.mOutLtm;
            if (outputStream != null) {
                outputStream.flush();
                this.mOutLtm.close();
                this.mInLtm.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public Fragment getFragment() {
        return this;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public String getInfoTag() {
        return this.m_tag;
    }

    @Override // cn.nubia.gamecenter.settings.compatible.PreferenceFragment
    public int getMetricsCategory() {
        return 5;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mDashboard = (RecyclerView) getView().findViewById(R.id.recycler_view);
        this.mDashboard.setPadding(0, this.mContext.getResources().getDimensionPixelSize(R.dimen.gcs_recyclerview_padding_top), 0, 0);
        this.mDashboard.addItemDecoration(new GameCenterDividerGridItemDecoration(this.mContext));
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
        addPreferencesFromResource(R.xml.gcs_screen_settings);
        this.mGameModeFixLight = (GameCenterSwitchPreference) findPreference(KEY_GAME_MODE_FIX_LIGHT);
        this.mGameModeColorEnhanced = (GameCenterSwitchPreference) findPreference(KEY_GAME_COLOR_ENHANCED);
        this.mGameModeSaveEnergy = (GameCenterSwitchPreference) findPreference(KEY_GAME_MODE_SAVE_ENERGY);
        this.mGameModeFixLight.setOnPreferenceChangeListener(this);
        this.mGameModeColorEnhanced.setOnPreferenceChangeListener(this);
        this.mGameModeSaveEnergy.setOnPreferenceChangeListener(this);
        connectSocket();
        init();
        iniAllPerferences();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        disconnectSocket();
        super.onDestroy();
    }

    @Override // cn.nubia.gamecenter.settings.compatible.SwitchPreference.OnPreferenceChangeListener
    public boolean onPreferenceChange(SwitchPreference switchPreference, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        if (switchPreference == this.mGameModeFixLight) {
            NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "gamespace_constant_brightness_protection_switch", "switch_on", booleanValue);
            OwlSysHelper.insertOwlDayCv("cn.nubia.gamelauncher", "pers_center_basic_brightness_protect_status", "switch_status", booleanValue ? "on" : "off");
            SettingUtil.setGameMode(this.mContext, 256, booleanValue);
            this.mGameModeFixLight.setChecked(booleanValue);
            return false;
        }
        if (switchPreference == this.mGameModeSaveEnergy) {
            NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "screen_power_saving_status", "option", booleanValue ? "on" : "off");
            SettingUtil.setScreenSaveEnergy(this.mContext, booleanValue);
            this.mGameModeSaveEnergy.setChecked(booleanValue);
            return false;
        }
        if (switchPreference != this.mGameModeColorEnhanced) {
            return false;
        }
        SettingUtil.setColorEnhanced(this.mContext, 1, booleanValue);
        this.mGameModeColorEnhanced.setChecked(booleanValue);
        OwlSysHelper.getInstance(getContext()).insertOwlGameLTMColorEnhanced();
        return false;
    }

    public void openLtm() throws IOException {
        writeLtm(this.ltmOpen);
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setInfoTag(String str) {
        this.m_tag = str;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setTestMode() {
    }

    public String writeLtm(byte[] bArr) throws IOException {
        this.mOutLtm.write(this.ltmOpen);
        this.mOutLtm.flush();
        byte[] bArr2 = new byte[1024];
        this.mInLtm.read(bArr2);
        return new String(bArr2, StandardCharsets.UTF_8);
    }
}
