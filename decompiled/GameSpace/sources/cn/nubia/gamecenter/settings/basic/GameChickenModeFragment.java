package cn.nubia.gamecenter.settings.basic;

import android.app.Dialog;
import android.database.ContentObserver;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.CheckBoxPreference;
import cn.nubia.gamecenter.settings.compatible.PreferenceFragment;
import cn.nubia.gamecenter.settings.compatible.SwitchPreference;
import cn.nubia.gamecenter.settings.preference.GameCenterSwitchPreference;
import cn.nubia.settings.owlsysaciton.OwlSysHelper;

/* loaded from: classes.dex */
public class GameChickenModeFragment extends PreferenceFragment implements SwitchPreference.OnPreferenceChangeListener, CheckBoxPreference.OnCheckedChangeListener {
    private static final String DB_GAMES_CHICKEN_MODE = "game_chicken_mode_switch";
    private static final String DB_GAMES_CHICKEN_MODE_TYPE = "game_chicken_mode_type";
    private static final int GAME_CHICKEN_MODE_CHOOSE_CHAOQIANG = 1;
    private static final int GAME_CHICKEN_MODE_CHOOSE_JIQIANG = 2;
    private static final String KEY_SWITCH_GAME_CHICKEN_MODE = "gcs_game_chicken_mode";
    private static final String KEY_SWITCH_GAME_CHICKEN_MODE_CHAOQIANG = "gcs_game_chicken_mode_chaoqiang_type";
    private static final String KEY_SWITCH_GAME_CHICKEN_MODE_JIQIANG = "gcs_game_chicken_mode_jiqiang_type";
    private static final int SWITCH_CLOSED_STATUS = 0;
    private static final int SWITCH_OPENED_STATUS = 2;
    private static final int SWITCH_OPENED_STATUS_OLD = 1;
    private static final String TAG = "GameChickenModeActivity";
    private CheckBoxPreference mCheckBoxChaoQiang;
    private CheckBoxPreference mCheckBoxJiQiang;
    private final ContentObserver mChickenModeChangeObserver = new ContentObserver(new Handler()) { // from class: cn.nubia.gamecenter.settings.basic.GameChickenModeFragment.1
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            GameChickenModeFragment.this.enableGameChickenOptions(GameChickenModeFragment.this.enableGameChicken());
        }
    };
    private GameCenterSwitchPreference mGameKeysChickenMode;

    /* JADX INFO: Access modifiers changed from: private */
    public boolean enableGameChicken() {
        return Settings.Global.getInt(getContentResolver(), DB_GAMES_CHICKEN_MODE, 0) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableGameChickenOptions(boolean z) {
        try {
            this.mGameKeysChickenMode.setChecked(z);
            getGameChickenType();
            Settings.Global.putInt(getContentResolver(), DB_GAMES_CHICKEN_MODE, z ? 2 : 0);
            Settings.Global.putInt(getContentResolver(), DB_GAMES_CHICKEN_MODE_TYPE, z ? 1 : 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getGameChickenType() {
        return Settings.Global.getInt(getContentResolver(), DB_GAMES_CHICKEN_MODE_TYPE, 1);
    }

    private void initGameChickenModeView() {
        this.mGameKeysChickenMode = (GameCenterSwitchPreference) findPreference(KEY_SWITCH_GAME_CHICKEN_MODE);
        this.mCheckBoxChaoQiang = (CheckBoxPreference) findPreference(KEY_SWITCH_GAME_CHICKEN_MODE_CHAOQIANG);
        this.mCheckBoxJiQiang = (CheckBoxPreference) findPreference(KEY_SWITCH_GAME_CHICKEN_MODE_JIQIANG);
        this.mGameKeysChickenMode.setOnPreferenceChangeListener(this);
        this.mCheckBoxChaoQiang.setOnCheckedChangeWidgetListener(this);
        this.mCheckBoxJiQiang.setOnCheckedChangeWidgetListener(this);
        enableGameChickenOptions(enableGameChicken());
        removePreference(KEY_SWITCH_GAME_CHICKEN_MODE_CHAOQIANG);
        removePreference(KEY_SWITCH_GAME_CHICKEN_MODE_JIQIANG);
    }

    private void insertOwlDayCvForGameChickenOptions(boolean z, int i) {
        OwlSysHelper.insertOwlDayCv("roast_chicken_mode", "switch_status option", z ? "on" : "off ".concat(i == 2 ? "extra_high" : "super_high"));
    }

    private boolean isDarkTheme() {
        return (getResources().getConfiguration().uiMode & 48) == 32;
    }

    private void setGameChickenType(int i) {
        try {
            if (i == 1) {
                this.mCheckBoxChaoQiang.setChecked(true);
                this.mCheckBoxJiQiang.setChecked(false);
            } else if (i == 2) {
                this.mCheckBoxChaoQiang.setChecked(false);
                this.mCheckBoxJiQiang.setChecked(true);
            }
            Settings.Global.putInt(getContentResolver(), DB_GAMES_CHICKEN_MODE_TYPE, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showGameChickenModeDialog() {
        final Dialog dialog = new Dialog(getActivity(), R.style.Theme_Nubia_Dialog_Alert);
        View inflate = LayoutInflater.from(getActivity()).inflate(isDarkTheme() ? R.layout.gcs_game_chicken_mode_dialog : R.layout.gcs_game_chicken_mode_dialog_day_mode, (ViewGroup) null);
        inflate.findViewById(R.id.gcs_game_chicken_mode_dialog_confirm).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.basic.GameChickenModeFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameChickenModeFragment.this.enableGameChickenOptions(true);
                dialog.dismiss();
            }
        });
        inflate.findViewById(R.id.gcs_game_chicken_mode_dialog_cancel).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.basic.GameChickenModeFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        if (FeatureUtil.getZtFeatureGameRandomRecord()) {
            ((TextView) inflate.findViewById(R.id.gcs_game_chicken_mode_dialog_content)).setText(R.string.gcs_game_chicken_mode_close_prompt_random_record);
        }
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        attributes.flags = 268435456;
        attributes.screenOrientation = 3;
        window.setAttributes(attributes);
        dialog.setContentView(inflate);
        window.setGravity(80);
        window.getDecorView().setSystemUiVisibility(5638);
        window.setFlags(1024, 1024);
        window.setLayout(-1, -1);
        window.setBackgroundDrawable(new ColorDrawable(0));
        dialog.show();
    }

    @Override // cn.nubia.gamecenter.settings.compatible.PreferenceFragment
    public int getMetricsCategory() {
        return 5;
    }

    @Override // cn.nubia.gamecenter.settings.compatible.CheckBoxPreference.OnCheckedChangeListener
    public boolean onCheckedChanged(CheckBoxPreference checkBoxPreference, Object obj) {
        if (checkBoxPreference == this.mCheckBoxChaoQiang) {
            setGameChickenType(1);
            insertOwlDayCvForGameChickenOptions(true, 1);
        } else if (checkBoxPreference == this.mCheckBoxJiQiang) {
            setGameChickenType(2);
            insertOwlDayCvForGameChickenOptions(true, 2);
        }
        return ((Boolean) obj).booleanValue();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.gcs_game_chicken_mode);
        initGameChickenModeView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        getContentResolver().unregisterContentObserver(this.mChickenModeChangeObserver);
    }

    @Override // cn.nubia.gamecenter.settings.compatible.SwitchPreference.OnPreferenceChangeListener
    public boolean onPreferenceChange(SwitchPreference switchPreference, Object obj) {
        ((Boolean) obj).booleanValue();
        if (switchPreference == this.mGameKeysChickenMode) {
            if (enableGameChicken()) {
                enableGameChickenOptions(false);
            } else {
                showGameChickenModeDialog();
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getContentResolver().registerContentObserver(Settings.Global.getUriFor(DB_GAMES_CHICKEN_MODE), true, this.mChickenModeChangeObserver);
    }
}
