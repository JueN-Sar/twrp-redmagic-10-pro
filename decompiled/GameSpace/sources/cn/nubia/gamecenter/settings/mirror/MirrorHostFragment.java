package cn.nubia.gamecenter.settings.mirror;

import android.content.Context;
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
import java.util.List;

/* loaded from: classes.dex */
public class MirrorHostFragment extends AnimationPreferenceFragment implements FragmentInterface, SwitchPreference.OnPreferenceChangeListener {
    private static final String KEY_XR_MIRROR = "xr_mirror";
    private static final String TAG = "MirrorHostFragment";
    private Context mContext;
    private RecyclerView mDashboard;
    private GameCenterSwitchPreference mGameModeMirrorHostMode;
    private GameCenterSwitchPreference mHandHeldModePreference;
    private GameCenterSwitchPreference mXrMirrorPreference;
    private String m_tag;
    private static final String KEY_GAME_MIRROR_HOST_MODE = "key_mirror_host_mode";
    private static final String KEY_GAME_HAND_HELD_MODE = "key_hand_held_mode";
    private static final String[] PREFERENCE_ITEMS = {KEY_GAME_MIRROR_HOST_MODE, KEY_GAME_HAND_HELD_MODE};

    public static CategoryInfo getCategoryInfo() {
        return new CategoryInfo(MirrorHostFragment.class, R.drawable.mirror_host, R.string.gcs_mode);
    }

    private void iniAllPerferences() {
        this.mGameModeMirrorHostMode.setChecked(SettingUtil.getMirrorHostMode(this.mContext));
        this.mHandHeldModePreference.setChecked(SettingUtil.getHandHeldMode(this.mContext));
        this.mXrMirrorPreference.setChecked(SettingUtil.getXrMirrorHostMode(this.mContext));
    }

    private void init() {
        List<String> gameCenterMode = FeatureUtil.getGameCenterMode();
        for (String str : PREFERENCE_ITEMS) {
            if (!gameCenterMode.contains(str)) {
                removePreference(str);
            }
        }
        if (FeatureUtil.isSupportScreen3D()) {
            return;
        }
        LogUtil.e(TAG, "not support xr mirror host mode");
        removePreference(KEY_XR_MIRROR);
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
        addPreferencesFromResource(R.xml.gcs_mirror_host_settings);
        GameCenterSwitchPreference gameCenterSwitchPreference = (GameCenterSwitchPreference) findPreference(KEY_GAME_MIRROR_HOST_MODE);
        this.mGameModeMirrorHostMode = gameCenterSwitchPreference;
        gameCenterSwitchPreference.setOnPreferenceChangeListener(this);
        GameCenterSwitchPreference gameCenterSwitchPreference2 = (GameCenterSwitchPreference) findPreference(KEY_GAME_HAND_HELD_MODE);
        this.mHandHeldModePreference = gameCenterSwitchPreference2;
        gameCenterSwitchPreference2.setOnPreferenceChangeListener(this);
        GameCenterSwitchPreference gameCenterSwitchPreference3 = (GameCenterSwitchPreference) findPreference(KEY_XR_MIRROR);
        this.mXrMirrorPreference = gameCenterSwitchPreference3;
        gameCenterSwitchPreference3.setOnPreferenceChangeListener(this);
        init();
    }

    @Override // cn.nubia.gamecenter.settings.compatible.SwitchPreference.OnPreferenceChangeListener
    public boolean onPreferenceChange(SwitchPreference switchPreference, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        if (switchPreference == this.mGameModeMirrorHostMode) {
            SettingUtil.setMirrorHostMode(this.mContext, booleanValue);
            this.mGameModeMirrorHostMode.setChecked(booleanValue);
            OwlSysHelper.getInstance(getContext()).insertOwlMirrorHostMode();
            return false;
        }
        if (switchPreference == this.mHandHeldModePreference) {
            SettingUtil.setHandHeldMode(this.mContext, booleanValue);
            this.mHandHeldModePreference.setChecked(booleanValue);
            return false;
        }
        if (switchPreference != this.mXrMirrorPreference) {
            return false;
        }
        SettingUtil.setXrMirrorHostMode(this.mContext, booleanValue);
        this.mXrMirrorPreference.setChecked(booleanValue);
        return false;
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
