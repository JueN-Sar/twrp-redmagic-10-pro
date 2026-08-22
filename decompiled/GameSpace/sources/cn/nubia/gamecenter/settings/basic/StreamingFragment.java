package cn.nubia.gamecenter.settings.basic;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.CategoryInfo;
import cn.nubia.gamecenter.settings.FragmentInterface;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.AnimationPreferenceFragment;
import cn.nubia.gamecenter.settings.compatible.Preference;
import cn.nubia.gamecenter.settings.compatible.SwitchPreference;
import cn.nubia.gamecenter.settings.preference.GameCenterDividerGridItemDecoration;
import cn.nubia.gamecenter.settings.preference.GameCenterSwitchPreference;
import cn.nubia.gamecenter.settings.preference.GamePreference;
import cn.nubia.gamecenter.settings.utils.IStreamGameMgr;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import java.util.List;

/* loaded from: classes.dex */
public class StreamingFragment extends AnimationPreferenceFragment implements FragmentInterface, SwitchPreference.OnPreferenceChangeListener {
    private static final String KEY_AUTO_DISSIPATING = "auto_dissipating";
    private static final String TAG = "StreamingFragment";
    private GameCenterSwitchPreference autoDissipatePreference;
    private GameCenterSwitchPreference autoStartPreference;
    private GameCenterSwitchPreference blackScreenPreference;
    private GamePreference devicePreference;
    private GameCenterSwitchPreference lockScreenPreference;
    private Context mContext;
    private String m_tag;
    private GameCenterSwitchPreference mutePreference;
    private boolean[] privacyBitSet;
    private static final String KEY_DEVICE = "device";
    private static final String KEY_LOCK_SCREEN = "lock_screen";
    private static final String KEY_BLACK_SCREEN = "black_screen";
    private static final String KEY_AUTO_START = "auto_start";
    private static final String KEY_MUTE = "mute";
    private static final String[] PREFERENCE_ITEMS = {KEY_DEVICE, KEY_LOCK_SCREEN, KEY_BLACK_SCREEN, KEY_AUTO_START, KEY_MUTE};

    public static CategoryInfo getCategoryInfo() {
        return new CategoryInfo(StreamingFragment.class, R.drawable.stream_game, R.string.streaming_games);
    }

    private void getPrivacySettings() {
        String string = SettingUtil.getString(this.mContext, SettingUtil.STREAMING_GAMES_PRIVACY_SETTINGS);
        if (TextUtils.isEmpty(string)) {
            string = "0,0,1,1";
        }
        String[] split = string.split(",");
        this.privacyBitSet = new boolean[split.length];
        for (int i = 0; i < this.privacyBitSet.length; i++) {
            if (split[i].equals("1")) {
                this.privacyBitSet[i] = true;
            }
        }
    }

    private void iniAllPerferences() {
        getPrivacySettings();
        this.autoDissipatePreference.setChecked(SettingUtil.getInt(this.mContext, SettingUtil.STREAMING_GAMES_AUTO_DISSIPATING, 1) == 1);
        this.lockScreenPreference.setChecked(this.privacyBitSet[0]);
        this.blackScreenPreference.setChecked(this.privacyBitSet[1]);
        this.autoStartPreference.setChecked(this.privacyBitSet[2]);
        this.mutePreference.setChecked(this.privacyBitSet[3]);
    }

    private void init() {
        List<String> streaming = FeatureUtil.getStreaming();
        for (String str : PREFERENCE_ITEMS) {
            if (!streaming.contains(str)) {
                removePreference(str);
            }
        }
        if (FeatureUtil.supportFan() || CommonUtil.isHighVersion()) {
            return;
        }
        removePreference(KEY_AUTO_DISSIPATING);
    }

    private void setPrivacySettings(int i, boolean z) {
        this.privacyBitSet[i] = z;
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (true) {
            boolean[] zArr = this.privacyBitSet;
            if (i2 >= zArr.length) {
                SettingUtil.putString(this.mContext, SettingUtil.STREAMING_GAMES_PRIVACY_SETTINGS, sb.toString());
                IStreamGameMgr.getInstance(this.mContext.getApplicationContext()).request("doSetting");
                return;
            } else {
                sb.append(zArr[i2] ? "1" : "0");
                if (i2 < this.privacyBitSet.length - 1) {
                    sb.append(",");
                }
                i2++;
            }
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

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        recyclerView.setPadding(0, this.mContext.getResources().getDimensionPixelSize(R.dimen.gcs_recyclerview_padding_top), 0, 0);
        recyclerView.addItemDecoration(new GameCenterDividerGridItemDecoration(this.mContext));
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
        addPreferencesFromResource(R.xml.gcs_streaming);
        this.devicePreference = (GamePreference) findPreference(KEY_DEVICE);
        GameCenterSwitchPreference gameCenterSwitchPreference = (GameCenterSwitchPreference) findPreference(KEY_AUTO_DISSIPATING);
        this.autoDissipatePreference = gameCenterSwitchPreference;
        gameCenterSwitchPreference.setOnPreferenceChangeListener(this);
        GameCenterSwitchPreference gameCenterSwitchPreference2 = (GameCenterSwitchPreference) findPreference(KEY_LOCK_SCREEN);
        this.lockScreenPreference = gameCenterSwitchPreference2;
        gameCenterSwitchPreference2.setOnPreferenceChangeListener(this);
        GameCenterSwitchPreference gameCenterSwitchPreference3 = (GameCenterSwitchPreference) findPreference(KEY_BLACK_SCREEN);
        this.blackScreenPreference = gameCenterSwitchPreference3;
        gameCenterSwitchPreference3.setOnPreferenceChangeListener(this);
        GameCenterSwitchPreference gameCenterSwitchPreference4 = (GameCenterSwitchPreference) findPreference(KEY_AUTO_START);
        this.autoStartPreference = gameCenterSwitchPreference4;
        gameCenterSwitchPreference4.setOnPreferenceChangeListener(this);
        GameCenterSwitchPreference gameCenterSwitchPreference5 = (GameCenterSwitchPreference) findPreference(KEY_MUTE);
        this.mutePreference = gameCenterSwitchPreference5;
        gameCenterSwitchPreference5.setOnPreferenceChangeListener(this);
        init();
    }

    @Override // cn.nubia.gamecenter.settings.compatible.SwitchPreference.OnPreferenceChangeListener
    public boolean onPreferenceChange(SwitchPreference switchPreference, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        if (switchPreference == this.lockScreenPreference) {
            setPrivacySettings(0, booleanValue);
            this.lockScreenPreference.setChecked(booleanValue);
        } else if (switchPreference == this.blackScreenPreference) {
            setPrivacySettings(1, booleanValue);
            this.blackScreenPreference.setChecked(booleanValue);
        } else if (switchPreference == this.autoStartPreference) {
            setPrivacySettings(2, booleanValue);
            this.autoStartPreference.setChecked(booleanValue);
        } else if (switchPreference == this.mutePreference) {
            setPrivacySettings(3, booleanValue);
            this.mutePreference.setChecked(booleanValue);
        } else if (switchPreference == this.autoDissipatePreference) {
            SettingUtil.putInt(this.mContext, SettingUtil.STREAMING_GAMES_AUTO_DISSIPATING, booleanValue ? 1 : 0);
            this.autoDissipatePreference.setChecked(booleanValue);
        }
        return false;
    }

    @Override // cn.nubia.gamecenter.settings.compatible.PreferenceFragment
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference == this.devicePreference) {
            try {
                Intent intent = new Intent();
                intent.setClassName("com.zte.streamgame", "com.zte.streamgame.ComputerActivity");
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                LogUtil.e(e);
            }
        }
        return super.onPreferenceTreeClick(preference);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        iniAllPerferences();
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setInfoTag(String str) {
        this.m_tag = str;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setTestMode() {
    }
}
