package cn.nubia.gamecenter.settings.about;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.CategoryInfo;
import cn.nubia.gamecenter.settings.FragmentInterface;
import cn.nubia.gamecenter.settings.GcsAnimationUtil;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.Preference;
import cn.nubia.gamecenter.settings.compatible.PreferenceFragment;
import cn.nubia.gamecenter.settings.utils.FlickerUtils;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import cn.nubia.settings.trackclient.Track;

/* loaded from: classes.dex */
public class RaceKeyOffFragment extends PreferenceFragment implements FragmentInterface {
    public static final String DB_RECEKEY_OFF_STATUS_CHECK_CHANGED = "db_game_app_status_game_switch_changed";
    public static final int RACEKEY_OFF_STATUS_CHECK_BAOCHIXIANZHUANG = 2;
    public static final int RACEKEY_OFF_STATUS_CHECK_FANHUIZHUOMIAN = 1;
    public static final int RACEKEY_OFF_STATUS_CHECK_XIAOCHUANGGUAJI = 0;
    private static final String TAG = "RaceKeyOffFragment";
    private CheckBox mBaochixianzhuangCheckBox;
    private View mBaochixianzhuangWidgetLayout;
    private Context mContext;
    private CheckBox mFanhuizhuomianCheckBox;
    private View mFanhuizhuomianWidgetLayout;
    private View mRaceKeyStatusView;
    private View mRootView;
    private CheckBox mXiaochuanguajiCheckBox;
    private TextView mXiaochuanguajiTextView;
    private View mXiaochuanguajiWidgetLayout;
    private String m_tag;

    public static CategoryInfo getCategoryInfo() {
        return new CategoryInfo(RaceKeyOffFragment.class, R.drawable.competitive_off, R.string.gcs_gamecenter_menu_competitive_off);
    }

    private int raceKeyOffToCheck(int i) {
        return i == 0 ? R.id.check_xiaochuangguaji : i == 1 ? R.id.check_fanhuizhuomian : i == 2 ? R.id.check_baochixianzhuang : R.id.check_xiaochuangguaji;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckBoxChecked(int i) {
        this.mXiaochuanguajiCheckBox.setChecked(i == R.id.check_xiaochuangguaji);
        this.mFanhuizhuomianCheckBox.setChecked(i == R.id.check_fanhuizhuomian);
        this.mBaochixianzhuangCheckBox.setChecked(i == R.id.check_baochixianzhuang);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRaceKeyOffStatusV(int i) {
        SettingUtil.putInt(this.mContext, DB_RECEKEY_OFF_STATUS_CHECK_CHANGED, i);
        String str = "small_window";
        if (i != 0) {
            if (i == 1) {
                str = "launcher";
            } else if (i == 2) {
                str = "exit";
            }
        }
        Track.eventEveryDay("game_center_athletic_switch_off_status", "option", str);
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public Fragment getFragment() {
        return this;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public String getInfoTag() {
        return this.m_tag;
    }

    public int getRaceKeyOffStatus() {
        return SettingUtil.getInt(this.mContext, DB_RECEKEY_OFF_STATUS_CHECK_CHANGED, !FeatureUtil.windowReplyEnable() ? 1 : 0);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.gcs_race_key_switch_preference, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            new Handler().post(new Runnable() { // from class: cn.nubia.gamecenter.settings.about.RaceKeyOffFragment.4
                @Override // java.lang.Runnable
                public void run() {
                    RaceKeyOffFragment.this.mRaceKeyStatusView.setAlpha(0.0f);
                }
            });
        } else {
            GcsAnimationUtil.setGcsItemTranslationY(this.mRaceKeyStatusView);
            GcsAnimationUtil.setGcsItemAlpha(this.mRaceKeyStatusView);
        }
    }

    @Override // cn.nubia.gamecenter.settings.compatible.PreferenceFragment
    public boolean onPreferenceTreeClick(Preference preference) {
        return super.onPreferenceTreeClick(preference);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updateRaceKeyOffStatusValues();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mRootView = view;
        View findViewById = view.findViewById(R.id.gsc_linear_race_key_status);
        this.mRaceKeyStatusView = findViewById;
        GcsAnimationUtil.setGcsItemTranslationY(findViewById);
        this.mXiaochuanguajiWidgetLayout = view.findViewById(R.id.gsc_linear_xiaochuangguaji);
        this.mFanhuizhuomianWidgetLayout = view.findViewById(R.id.gsc_linear_fanhuizhuomian);
        this.mBaochixianzhuangWidgetLayout = view.findViewById(R.id.gsc_linear_baochixianzhuang);
        this.mXiaochuanguajiCheckBox = (CheckBox) view.findViewById(R.id.check_xiaochuangguaji);
        this.mFanhuizhuomianCheckBox = (CheckBox) view.findViewById(R.id.check_fanhuizhuomian);
        this.mBaochixianzhuangCheckBox = (CheckBox) view.findViewById(R.id.check_baochixianzhuang);
        this.mXiaochuanguajiTextView = (TextView) view.findViewById(R.id.text_xiaochuangguaji);
        this.mXiaochuanguajiWidgetLayout.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.about.RaceKeyOffFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RaceKeyOffFragment.this.mXiaochuanguajiCheckBox.setClickable(true);
                RaceKeyOffFragment.this.mFanhuizhuomianCheckBox.setClickable(false);
                RaceKeyOffFragment.this.mBaochixianzhuangCheckBox.setClickable(false);
                RaceKeyOffFragment.this.setCheckBoxChecked(R.id.check_xiaochuangguaji);
                RaceKeyOffFragment.this.setRaceKeyOffStatusV(0);
            }
        });
        this.mFanhuizhuomianWidgetLayout.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.about.RaceKeyOffFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RaceKeyOffFragment.this.mXiaochuanguajiCheckBox.setClickable(false);
                RaceKeyOffFragment.this.mFanhuizhuomianCheckBox.setClickable(true);
                RaceKeyOffFragment.this.mBaochixianzhuangCheckBox.setClickable(false);
                RaceKeyOffFragment.this.setCheckBoxChecked(R.id.check_fanhuizhuomian);
                RaceKeyOffFragment.this.setRaceKeyOffStatusV(1);
            }
        });
        this.mBaochixianzhuangWidgetLayout.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.about.RaceKeyOffFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RaceKeyOffFragment.this.mXiaochuanguajiCheckBox.setClickable(false);
                RaceKeyOffFragment.this.mFanhuizhuomianCheckBox.setClickable(false);
                RaceKeyOffFragment.this.mBaochixianzhuangCheckBox.setClickable(true);
                RaceKeyOffFragment.this.setCheckBoxChecked(R.id.check_baochixianzhuang);
                RaceKeyOffFragment.this.setRaceKeyOffStatusV(2);
            }
        });
        if (!FeatureUtil.windowReplyEnable()) {
            this.mXiaochuanguajiWidgetLayout.setVisibility(8);
            this.mXiaochuanguajiCheckBox.setVisibility(8);
            this.mXiaochuanguajiTextView.setVisibility(8);
        }
        FlickerUtils.setFlickerName(view.findViewById(R.id.auto_summary), "gcs_race_key_switch_preference");
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setInfoTag(String str) {
        this.m_tag = str;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setTestMode() {
    }

    public void updateRaceKeyOffStatusValues() {
        if (this.mRootView != null) {
            setCheckBoxChecked(raceKeyOffToCheck(getRaceKeyOffStatus()));
        }
    }
}
