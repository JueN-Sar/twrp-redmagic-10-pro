package cn.nubia.gamecenter.settings.watermark;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.CategoryInfo;
import cn.nubia.gamecenter.settings.FragmentInterface;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.applearning.AppDbSchema;
import cn.nubia.gamecenter.settings.compatible.AnimationPreferenceFragment;
import cn.nubia.gamecenter.settings.compatible.Preference;
import cn.nubia.gamecenter.settings.compatible.SwitchPreference;
import cn.nubia.gamecenter.settings.preference.GameCenterDividerGridItemDecoration;
import cn.nubia.gamecenter.settings.preference.GameCenterPreference;
import cn.nubia.gamecenter.settings.preference.GameCenterSwitchPreference;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import cn.nubia.gamecenter.settings.utils.Utils;
import cn.nubia.settings.trackclient.NubiaTrackManager;
import cn.nubia.settings.trackclient.Track;

/* loaded from: classes.dex */
public class WatermarkFragment extends AnimationPreferenceFragment implements FragmentInterface, SwitchPreference.OnPreferenceChangeListener {
    public static final String DB_GAMES_WATERMARK_MESSAGE = "persist_sys_nubia_logo_message";
    public static final String DB_GAMES_WATERMARK_SWITCH = "persist_sys_nubia_logo_switch";
    private static final String KEY_GAME_KEYS_WATERMARK_MESSAGE = "gcs_watermark_message";
    private static final String KEY_GAME_KEYS_WATERMARK_SWITCH = "gcs_watermark_switch";
    private static final String TAG = "WatermarkFragment";
    private Context mContext;
    private RecyclerView mDashboard;
    private GameCenterPreference mGameKeysWatermarkMessage;
    private GameCenterSwitchPreference mGameKeysWatermarkSwitch;
    private String m_tag;

    public static CategoryInfo getCategoryInfo() {
        return new CategoryInfo(WatermarkFragment.class, R.drawable.screenshot_mark, R.string.gcs_gamecenter_menu_screenshot_mark);
    }

    private String getWatermarkMessage() {
        return SettingUtil.getString(this.mContext, DB_GAMES_WATERMARK_MESSAGE);
    }

    private boolean getWatermarkSwitch() {
        return SettingUtil.getBoolean(this.mContext, DB_GAMES_WATERMARK_SWITCH, true);
    }

    private void initAllPreferences() {
        this.mGameKeysWatermarkSwitch = (GameCenterSwitchPreference) findPreference(KEY_GAME_KEYS_WATERMARK_SWITCH);
        GameCenterPreference gameCenterPreference = (GameCenterPreference) findPreference(KEY_GAME_KEYS_WATERMARK_MESSAGE);
        this.mGameKeysWatermarkMessage = gameCenterPreference;
        gameCenterPreference.setSummary(getWatermarkMessage());
        this.mGameKeysWatermarkMessage.setShouldDisableView(true);
        boolean watermarkSwitch = getWatermarkSwitch();
        this.mGameKeysWatermarkMessage.setEnabled(watermarkSwitch);
        boolean ztFeatureGameRandomRecord = FeatureUtil.getZtFeatureGameRandomRecord();
        this.mGameKeysWatermarkSwitch.setSummary(Utils.isInternal(this.mContext) ? ztFeatureGameRandomRecord ? R.string.gcs_watermark_switch_title_message_internal_random_record : R.string.gcs_watermark_switch_title_message_internal : ztFeatureGameRandomRecord ? R.string.gcs_watermark_switch_title_message_random_record : R.string.gcs_watermark_switch_title_message);
        this.mGameKeysWatermarkSwitch.setChecked(watermarkSwitch);
        this.mGameKeysWatermarkSwitch.setOnPreferenceChangeListener(this);
    }

    private void initMessageEdit(EditText editText) {
        if (editText == null) {
            return;
        }
        editText.setText(getWatermarkMessage());
        editText.setSelectAllOnFocus(true);
        editText.setSelected(true);
        editText.setSelection(editText.getText().length());
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        ((InputMethodManager) this.mContext.getSystemService("input_method")).showSoftInput(editText, 2);
    }

    private static boolean isEmpty(String str) {
        return str == null || str.replace(" ", "").equals("");
    }

    private void setWatermarkMessage(String str) {
        Context context = this.mContext;
        if (isEmpty(str)) {
            str = null;
        }
        SettingUtil.putString(context, DB_GAMES_WATERMARK_MESSAGE, str);
    }

    private void setWatermarkSwitch(boolean z) {
        SettingUtil.putBoolean(this.mContext, DB_GAMES_WATERMARK_SWITCH, z);
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public Fragment getFragment() {
        return this;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public String getInfoTag() {
        return this.m_tag;
    }

    /* renamed from: lambda$onPreferenceTreeClick$1$cn-nubia-gamecenter-settings-watermark-WatermarkFragment, reason: not valid java name */
    /* synthetic */ void m215xd54ec22f(EditText editText, DialogInterface dialogInterface, int i) {
        setWatermarkMessage(editText.getText().toString());
        this.mGameKeysWatermarkMessage.setSummary(getWatermarkMessage());
        dialogInterface.dismiss();
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
        addPreferencesFromResource(R.xml.gcs_watermark_settings);
        initAllPreferences();
    }

    @Override // cn.nubia.gamecenter.settings.compatible.SwitchPreference.OnPreferenceChangeListener
    public boolean onPreferenceChange(SwitchPreference switchPreference, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        if (switchPreference != this.mGameKeysWatermarkSwitch) {
            return false;
        }
        setWatermarkSwitch(booleanValue);
        Bundle bundle = new Bundle();
        bundle.putString("package_name", "cn.nubia.gamelauncher");
        bundle.putString("event_name", "red_magic_time_custom_watermark_switch");
        bundle.putString("action_type", "switch_status");
        bundle.putString(AppDbSchema.AppTable.OneDayCols.ACTION_VALUE, booleanValue ? "on" : "off");
        bundle.putInt(AppDbSchema.AppTable.OneDayCols.REPORT_INTERVAL, 1);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
        Track.switchStatus(Track.REDMAGIC_WATERMARK_SWITCH_CLICK, booleanValue);
        this.mGameKeysWatermarkSwitch.setChecked(getWatermarkSwitch());
        GameCenterPreference gameCenterPreference = this.mGameKeysWatermarkMessage;
        if (gameCenterPreference == null) {
            return false;
        }
        gameCenterPreference.setEnabled(getWatermarkSwitch());
        return false;
    }

    @Override // cn.nubia.gamecenter.settings.compatible.PreferenceFragment
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference != this.mGameKeysWatermarkMessage || !getWatermarkSwitch()) {
            return super.onPreferenceTreeClick(preference);
        }
        View inflate = View.inflate(getActivity(), R.layout.gcs_watermark_alert_dialog_layout, null);
        final EditText editText = (EditText) inflate.findViewById(R.id.gcs_watermark_dialog_edit);
        editText.addTextChangedListener(new WaterMarkWatcher(editText, 10));
        initMessageEdit(editText);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext, R.style.Theme_Nubia_Dialog_Alert);
        builder.setView(inflate);
        AlertDialog create = builder.create();
        create.setButton(-2, getString(R.string.gamemode_account_login_cancel), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.watermark.WatermarkFragment$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        create.setButton(-1, getString(R.string.gamekeys_dialog_ok), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.watermark.WatermarkFragment$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                WatermarkFragment.this.m215xd54ec22f(editText, dialogInterface, i);
            }
        });
        create.show();
        return true;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setInfoTag(String str) {
        this.m_tag = str;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setTestMode() {
    }
}
