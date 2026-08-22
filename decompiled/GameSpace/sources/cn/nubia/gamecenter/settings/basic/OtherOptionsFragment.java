package cn.nubia.gamecenter.settings.basic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceScreen;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.CategoryInfo;
import cn.nubia.gamecenter.settings.FragmentInterface;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.AnimationPreferenceFragment;
import cn.nubia.gamecenter.settings.compatible.SwitchPreference;
import cn.nubia.gamecenter.settings.other.FiberCatcherItem;
import cn.nubia.gamecenter.settings.other.GameAssistStartAnimItem;
import cn.nubia.gamecenter.settings.other.GameSpaceStartAnimItem;
import cn.nubia.gamecenter.settings.other.HideGamesIconItem;
import cn.nubia.gamecenter.settings.other.HighLightsItem;
import cn.nubia.gamecenter.settings.other.IdentifyItem;
import cn.nubia.gamecenter.settings.other.Item;
import cn.nubia.gamecenter.settings.other.LearnedBehaviorItem;
import cn.nubia.gamecenter.settings.other.LiteModeItem;
import cn.nubia.gamecenter.settings.other.PeriConnWinItem;
import cn.nubia.gamecenter.settings.other.QuickInfoItem;
import cn.nubia.gamecenter.settings.other.RecommendedContentItem;
import cn.nubia.gamecenter.settings.other.ShoulderKeyItem;
import cn.nubia.gamecenter.settings.other.TimeRemindItem;
import cn.nubia.gamecenter.settings.preference.GameCenterDividerGridItemDecoration;
import cn.nubia.gamecenter.settings.preference.GameCenterSwitchPreference;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class OtherOptionsFragment extends AnimationPreferenceFragment implements FragmentInterface, SwitchPreference.OnPreferenceChangeListener {
    private static final String TAG = "OtherOptionsFragment";
    private Context mContext;
    private Map<String, Item> mItemMap;
    private String m_tag;

    private void addPreferences(PreferenceScreen preferenceScreen) {
        this.mItemMap = getItems();
        List<String> gameCenterOtherOptions = FeatureUtil.getGameCenterOtherOptions();
        for (Item item : this.mItemMap.values()) {
            if (item.enable(gameCenterOtherOptions)) {
                GameCenterSwitchPreference gameCenterSwitchPreference = new GameCenterSwitchPreference(this.mContext, null);
                gameCenterSwitchPreference.setKey(item.getKey());
                gameCenterSwitchPreference.setTitle(item.getTitle());
                if (item.getSummary() != 0) {
                    gameCenterSwitchPreference.setSummary(item.getSummary());
                }
                gameCenterSwitchPreference.setDefaultValue(Boolean.valueOf(item.getSettings(this.mContext)));
                gameCenterSwitchPreference.setOnPreferenceChangeListener(this);
                preferenceScreen.addPreference(gameCenterSwitchPreference);
            }
        }
    }

    public static CategoryInfo getCategoryInfo() {
        return new CategoryInfo(OtherOptionsFragment.class, R.drawable.other_options, R.string.gcs_gamecenter_menu_other_options);
    }

    private Map<String, Item> getItems() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(ShoulderKeyItem.KEY, new ShoulderKeyItem());
        linkedHashMap.put(QuickInfoItem.KEY, new QuickInfoItem());
        linkedHashMap.put(HighLightsItem.KEY, new HighLightsItem());
        linkedHashMap.put(HideGamesIconItem.KEY, new HideGamesIconItem());
        linkedHashMap.put(LearnedBehaviorItem.KEY, new LearnedBehaviorItem());
        linkedHashMap.put(PeriConnWinItem.KEY, new PeriConnWinItem());
        linkedHashMap.put(GameSpaceStartAnimItem.KEY, new GameSpaceStartAnimItem());
        linkedHashMap.put(GameAssistStartAnimItem.KEY, new GameAssistStartAnimItem());
        linkedHashMap.put("identify", new IdentifyItem());
        linkedHashMap.put(LiteModeItem.KEY, new LiteModeItem());
        linkedHashMap.put(TimeRemindItem.KEY, new TimeRemindItem());
        linkedHashMap.put(RecommendedContentItem.KEY, new RecommendedContentItem());
        linkedHashMap.put(FiberCatcherItem.KEY, new FiberCatcherItem());
        return linkedHashMap;
    }

    private void removePreferences() {
        if (GameSpaceConfig.supportRelevant()) {
            return;
        }
        removePreference(RecommendedContentItem.KEY);
    }

    private void smoothScrollToPosition() {
        Intent intent = requireActivity().getIntent();
        if (intent == null || !intent.hasExtra("view_id")) {
            return;
        }
        String stringExtra = intent.getStringExtra("view_id");
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int i = 0;
        while (true) {
            if (i >= preferenceScreen.getPreferenceCount()) {
                i = -1;
                break;
            } else if (stringExtra.equals(preferenceScreen.getPreference(i).getKey())) {
                break;
            } else {
                i++;
            }
        }
        if (i > 0) {
            getListView().smoothScrollToPosition(i);
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
        smoothScrollToPosition();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
        PreferenceScreen createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this.mContext);
        setPreferenceScreen(createPreferenceScreen);
        addPreferences(createPreferenceScreen);
        removePreferences();
    }

    @Override // cn.nubia.gamecenter.settings.compatible.SwitchPreference.OnPreferenceChangeListener
    public boolean onPreferenceChange(SwitchPreference switchPreference, Object obj) {
        LogUtil.i(TAG, switchPreference.getKey() + " " + obj);
        boolean booleanValue = ((Boolean) obj).booleanValue();
        Item item = this.mItemMap.get(switchPreference.getKey());
        item.setSettings(this.mContext, booleanValue);
        item.track(booleanValue);
        switchPreference.setChecked(booleanValue);
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LogUtil.i(TAG, "onResume");
        for (Item item : this.mItemMap.values()) {
            GameCenterSwitchPreference gameCenterSwitchPreference = (GameCenterSwitchPreference) findPreference(item.getKey());
            if (gameCenterSwitchPreference != null) {
                gameCenterSwitchPreference.setChecked(item.getSettings(this.mContext));
            }
        }
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setInfoTag(String str) {
        this.m_tag = str;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setTestMode() {
    }
}
