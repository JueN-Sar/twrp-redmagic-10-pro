package cn.nubia.gamelauncher.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.ContentObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.FragmentTransaction;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.fragment.HostModeGameLobbyFragment;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.gamelauncher.view.MarqueeTextView;
import cn.nubia.gamelauncher.xgravitation.util.LogUtils;
import cn.nubia.studio.TouPingGravitationActivity;

/* loaded from: classes.dex */
public class ExperienceHostMode extends BaseFragmentActivity implements View.OnClickListener {
    private static final String SCREEN_OFF_TOUPIN = "nubia_screen_off_tp";
    private static final String TAG = "ExperienceHostMode";
    private HostModeContentObserver mHostModeContentObserver;

    private class HostModeContentObserver extends ContentObserver {
        public HostModeContentObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            if (Settings.Global.getInt(ExperienceHostMode.this.getContentResolver(), "gamebox_mirror_displayid", -1) == 0) {
                LogUtil.i(ExperienceHostMode.TAG, "HostMode - onChange(HOST_MODE_STATE) - finish!");
                ExperienceHostMode.this.finish();
            }
        }

        public void register() {
            ExperienceHostMode.this.getContentResolver().registerContentObserver(Settings.Global.getUriFor("gamebox_mirror_displayid"), false, this);
        }

        public void unregister() {
            ExperienceHostMode.this.getContentResolver().unregisterContentObserver(this);
        }
    }

    private void addFragment() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(R.id.fragment_container, new HostModeGameLobbyFragment());
        beginTransaction.commit();
    }

    private void initView() {
        findViewById(R.id.mode_back).setOnClickListener(this);
        findViewById(R.id.experience_mirror).setOnClickListener(this);
        updateText();
    }

    private void registerObserver() {
        HostModeContentObserver hostModeContentObserver = new HostModeContentObserver(new Handler());
        this.mHostModeContentObserver = hostModeContentObserver;
        hostModeContentObserver.register();
    }

    private void startMirror() {
        try {
            startActivity(new Intent(this, (Class<?>) TouPingGravitationActivity.class));
        } catch (ActivityNotFoundException e) {
            LogUtils.e(TAG, "startProjectionGravitation error", e);
        }
    }

    private void unregisterObserver() {
        HostModeContentObserver hostModeContentObserver = this.mHostModeContentObserver;
        if (hostModeContentObserver != null) {
            hostModeContentObserver.unregister();
        }
    }

    private void updateText() {
        MarqueeTextView marqueeTextView = (MarqueeTextView) findViewById(R.id.mode_introduction);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        SpannableString spannableString = new SpannableString(getString(R.string.experience_string_title));
        spannableString.setSpan(new AbsoluteSizeSpan(12, true), 0, spannableString.length(), 33);
        spannableStringBuilder.append((CharSequence) spannableString);
        spannableStringBuilder.append((CharSequence) getString(R.string.experience_string_content));
        marqueeTextView.setText(spannableStringBuilder);
        MarqueeTextView marqueeTextView2 = (MarqueeTextView) findViewById(R.id.mode_introduction_note);
        SpannableString spannableString2 = new SpannableString(getString(R.string.experience_string_introduction));
        spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#B2FFFFFF")), 0, spannableString2.length(), 33);
        marqueeTextView2.setText(spannableString2);
    }

    public void doHome() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(268435456);
        startActivity(intent);
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        Log.d(TAG, "finish() ->: " + Log.getStackTraceString(new Throwable()));
    }

    public void goKeyguard() {
        boolean z = Settings.Global.getInt(getApplicationContext().getContentResolver(), SCREEN_OFF_TOUPIN, 0) > 0;
        Log.d(TAG, "goKeyguard: screenOffEnable= " + z);
        try {
            Class.forName("com.redmagic.os.RedMagicAppManager$Trigger").getMethod("openScreenOffTP", Boolean.TYPE).invoke(null, Boolean.valueOf(!z));
        } catch (Exception e) {
            Log.e(TAG, "goKeyguard: error", e);
        }
    }

    @Override // cn.nubia.gamelauncher.activity.BaseFragmentActivity
    protected boolean isHostMode() {
        return true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.experience_mirror) {
            startMirror();
        } else {
            if (id != R.id.mode_back) {
                return;
            }
            finish();
        }
    }

    @Override // cn.nubia.gamelauncher.activity.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.game_space_experience);
        registerObserver();
        addFragment();
        initView();
    }

    @Override // cn.nubia.gamelauncher.activity.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterObserver();
        LogUtil.i(TAG, "onDestroy()");
    }

    @Override // cn.nubia.gamelauncher.activity.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (isHostMode()) {
            Util.updateHostModeGameSpace(true);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        if (isHostMode()) {
            Util.updateHostModeGameSpace(false);
        }
    }

    public void setPointerSpeed() {
        Settings.System.putInt(getContentResolver(), "pointer_speed", 0);
    }
}
