package cn.nubia.gamecenter.settings.about;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
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
import cn.nubia.gamecenter.settings.preference.GameCenterPreference;
import cn.nubia.gamecenter.settings.preference.GameCenterPreferenceICP;
import cn.nubia.gamecenter.settings.preference.GameCenterPreferenceWithUpdateMsg;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import java.util.List;

/* loaded from: classes.dex */
public class AboutFragment extends AnimationPreferenceFragment implements FragmentInterface, SwitchPreference.OnPreferenceChangeListener {
    private static final String ACTION_GAMELAUNCHER_UPGRADE = "cn.nubia.gamelauncher.upgrade";
    private static final String TAG = "AboutFragment";
    private Context mContext;
    private RecyclerView mDashboard;
    private GameCenterPreferenceICP mICPInfoPreference;
    private GameCenterPreference mPrivacyPolicyPreference;
    private GameCenterPreferenceWithUpdateMsg mSystemUpdatePreference;
    private String m_tag;
    private static final String KEY_SYSTEM_UPDATE = "gcs_system_update";
    private static final String KEY_ICP_INFO = "gcs_icp";
    private static final String KEY_ABOUT_UPDATE = "gcs_about_update";
    private static final String KEY_PRIVACY_POLICY = "gcs_privacy_policy";
    private static final String[] PREFERENCE_ITEMS = {KEY_SYSTEM_UPDATE, KEY_ICP_INFO, KEY_ABOUT_UPDATE, KEY_PRIVACY_POLICY};

    public static CategoryInfo getCategoryInfo() {
        return new CategoryInfo(AboutFragment.class, R.drawable.about_gamespace, R.string.gcs_gamecenter_menu_about);
    }

    private void init() {
        List<String> gameCenterAbout = FeatureUtil.getGameCenterAbout();
        for (String str : PREFERENCE_ITEMS) {
            if (!gameCenterAbout.contains(str)) {
                removePreference(str);
            }
        }
    }

    private void noBrowserToast() {
        Toast.makeText(this.mContext, R.string.gcs_gamecenter_no_browser, 0).show();
    }

    private void showICPInfo() {
        try {
            this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.beian_miit_url))));
        } catch (ActivityNotFoundException unused) {
            noBrowserToast();
        }
    }

    private void showPrivacyPolicy() {
        int i = R.string.zte_privacy_policy_url;
        if (CommonUtil.isNubiaChina() && CommonUtil.isAndroidU()) {
            LogUtil.i(TAG, "AIGC大模型隐私政策");
            i = R.string.redmagic_ai_privacy_policy_url;
        }
        try {
            this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(i))));
        } catch (ActivityNotFoundException unused) {
            noBrowserToast();
        }
    }

    private void smoothScrollToPosition() {
        Intent intent = requireActivity().getIntent();
        if (intent != null && intent.hasExtra("view_id") && KEY_PRIVACY_POLICY.equals(intent.getStringExtra("view_id"))) {
            getListView().smoothScrollToPosition(getPreferenceScreen().getPreferenceCount());
        }
    }

    private void upgrade() {
        Intent intent = new Intent();
        intent.setAction(ACTION_GAMELAUNCHER_UPGRADE);
        this.mContext.startActivity(intent);
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
        this.mDashboard = (RecyclerView) getView().findViewById(R.id.recycler_view);
        this.mDashboard.setPadding(0, this.mContext.getResources().getDimensionPixelSize(R.dimen.gcs_recyclerview_padding_top), 0, 0);
        this.mDashboard.addItemDecoration(new GameCenterDividerGridItemDecoration(this.mContext));
        smoothScrollToPosition();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
        addPreferencesFromResource(R.xml.gcs_about);
        this.mSystemUpdatePreference = (GameCenterPreferenceWithUpdateMsg) findPreference(KEY_SYSTEM_UPDATE);
        this.mICPInfoPreference = (GameCenterPreferenceICP) findPreference(KEY_ICP_INFO);
        this.mPrivacyPolicyPreference = (GameCenterPreference) findPreference(KEY_PRIVACY_POLICY);
        init();
    }

    @Override // cn.nubia.gamecenter.settings.compatible.SwitchPreference.OnPreferenceChangeListener
    public boolean onPreferenceChange(SwitchPreference switchPreference, Object obj) {
        return false;
    }

    @Override // cn.nubia.gamecenter.settings.compatible.PreferenceFragment
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference == this.mPrivacyPolicyPreference) {
            showPrivacyPolicy();
        } else if (preference == this.mICPInfoPreference) {
            showICPInfo();
        } else if (preference == this.mSystemUpdatePreference) {
            upgrade();
        }
        return super.onPreferenceTreeClick(preference);
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setInfoTag(String str) {
        this.m_tag = str;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setTestMode() {
    }
}
