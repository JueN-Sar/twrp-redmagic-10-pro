package cn.nubia.studio;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemProperties;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.nubia.gamecenter.settings.helper.AnimatorHelper;
import cn.nubia.gamecenter.settings.widget.ViewPager;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.xgravitation.util.LogUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class TouPingActivity extends Activity {
    public static final int REQUEST_FROM_PC = 103;
    public static final int REQUEST_FROM_TV = 102;
    private static final String TAG = "TouPingActivity";
    private float density;
    private int densityDpi;
    private View mHelpView;
    private View mOperationView;
    private View mSettingView;
    private Button mStudioHDMIBtn;
    private List<ImageView> mTouPingIndicator;
    private ViewPager mTouPingPager;
    private List<View> mTouPingViewList;

    private void alpha(View view, float f, float f2) {
        PathInterpolator pathInterpolator = new PathInterpolator(0.42f, 0.0f, 0.58f, 1.0f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, AnimatorHelper.Item.ALPHA, f, f2);
        ofFloat.setDuration(250L);
        ofFloat.setInterpolator(pathInterpolator);
        ofFloat.setRepeatCount(0);
        ofFloat.start();
    }

    private void initTouPing() {
        if (CommonUtil.isInternalVersion()) {
            findViewById(R.id.studio_mili_wave).setVisibility(8);
        }
        initTouPingIndicator();
        initTouPingViewList();
        this.mTouPingPager = (ViewPager) findViewById(R.id.touping_pager);
        this.mTouPingPager.setAdapter(new TouPingAdapter(this.mTouPingViewList));
        this.mTouPingPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: cn.nubia.studio.TouPingActivity.1
            @Override // cn.nubia.gamecenter.settings.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // cn.nubia.gamecenter.settings.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // cn.nubia.gamecenter.settings.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                TouPingActivity.this.updateTouPingIndicator(i);
            }
        });
        this.mHelpView = findViewById(R.id.help);
        this.mSettingView = findViewById(R.id.touping_settings);
        this.mOperationView = findViewById(R.id.operation);
        this.mStudioHDMIBtn = (Button) findViewById(R.id.studio_HDMI);
        if (isSupportHDMI()) {
            this.mStudioHDMIBtn.setVisibility(0);
        } else {
            this.mStudioHDMIBtn.setVisibility(8);
        }
    }

    private void initTouPingIndicator() {
        ArrayList arrayList = new ArrayList();
        this.mTouPingIndicator = arrayList;
        arrayList.add((ImageView) findViewById(R.id.touping_indicator_1));
        this.mTouPingIndicator.add((ImageView) findViewById(R.id.touping_indicator_2));
    }

    private void initTouPingViewList() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View inflate = layoutInflater.inflate(R.layout.touping_computer, (ViewGroup) null);
        if (CommonUtil.isInternalVersion()) {
            ((TextView) inflate.findViewById(R.id.touping_pc_summary)).setText(R.string.setting_homdeactivityscancontent_inter);
        }
        View inflate2 = layoutInflater.inflate(R.layout.touping_tv, (ViewGroup) null);
        if (CommonUtil.isInternalVersion()) {
            ((TextView) inflate2.findViewById(R.id.touping_tv_summary)).setText(R.string.setting_homdeactivity_forsearch_inter);
        }
        ArrayList arrayList = new ArrayList();
        this.mTouPingViewList = arrayList;
        arrayList.add(inflate);
        this.mTouPingViewList.add(inflate2);
    }

    public static boolean isSupportHDMI() {
        try {
            return "true".equals(SystemProperties.get("persist.sys.usb.dp"));
        } catch (Exception unused) {
            return true;
        }
    }

    private void onExit() {
        translationExit();
        new Handler().postDelayed(new Runnable() { // from class: cn.nubia.studio.TouPingActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                TouPingActivity.this.m338lambda$onExit$0$cnnubiastudioTouPingActivity();
            }
        }, 250L);
    }

    private void resetDensity() {
        if (this.density == 0.0f || this.densityDpi == 0) {
            return;
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        LogUtils.d(TAG, " displayMetrics  density = " + this.density + " ;; densityDpi = " + this.densityDpi);
        displayMetrics.density = this.density;
        displayMetrics.densityDpi = this.densityDpi;
    }

    private void startTouping(int i) {
        Intent intent = new Intent();
        intent.setClassName("cn.nubia.touping", "cn.nubia.touping.HomeActivity");
        intent.putExtra("from", i);
        intent.putExtra("fromotherapp_key", "FROMOTHERAPP_GAMESPACE");
        startActivity(intent);
    }

    private void startToupingActivity(String str) {
        Intent intent = new Intent();
        intent.setClassName("cn.nubia.touping", str);
        intent.putExtra("fromotherapp_key", "FROMOTHERAPP_GAMESPACE");
        startActivity(intent);
    }

    private void translationEnter() {
        translationX(this.mTouPingPager, 200.0f, 0.0f);
        translationX(this.mHelpView, -100.0f, 0.0f);
        translationX(this.mOperationView, -200.0f, 0.0f);
        translationX(this.mSettingView, -300.0f, 0.0f);
        alpha(this.mTouPingPager, 0.0f, 1.0f);
        alpha(this.mHelpView, 0.0f, 1.0f);
        alpha(this.mOperationView, 0.0f, 1.0f);
        alpha(this.mSettingView, 0.0f, 1.0f);
    }

    private void translationExit() {
        translationX(this.mTouPingPager, 0.0f, 200.0f);
        translationX(this.mHelpView, 0.0f, -100.0f);
        translationX(this.mOperationView, 0.0f, -200.0f);
        translationX(this.mSettingView, 0.0f, -300.0f);
        alpha(this.mTouPingPager, 1.0f, 0.0f);
        alpha(this.mHelpView, 1.0f, 0.0f);
        alpha(this.mOperationView, 1.0f, 0.0f);
        alpha(this.mSettingView, 1.0f, 0.0f);
    }

    private void translationX(View view, float f, float f2) {
        PathInterpolator pathInterpolator = new PathInterpolator(0.42f, 0.0f, 0.58f, 1.0f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, AnimatorHelper.Item.TRANSLATIONX, f, f2);
        ofFloat.setDuration(250L);
        ofFloat.setInterpolator(pathInterpolator);
        ofFloat.setRepeatCount(0);
        ofFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTouPingIndicator(int i) {
        Iterator<ImageView> it = this.mTouPingIndicator.iterator();
        while (it.hasNext()) {
            it.next().setSelected(false);
        }
        this.mTouPingIndicator.get(i).setSelected(true);
    }

    /* renamed from: lambda$onExit$0$cn-nubia-studio-TouPingActivity, reason: not valid java name */
    /* synthetic */ void m338lambda$onExit$0$cnnubiastudioTouPingActivity() {
        finish();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.left_name) {
            onExit();
        }
        if (id == R.id.setting_find) {
            startToupingActivity("cn.nubia.touping.WirelessSearchHelpActivity");
            return;
        }
        if (id == R.id.touping_settings) {
            startToupingActivity("cn.nubia.touping.WiredlessAndWiredSettingActivity");
            return;
        }
        switch (id) {
            case R.id.mirrorPCBtn /* 2131362780 */:
                startTouping(103);
                break;
            case R.id.mirrorTVBtn /* 2131362781 */:
                startTouping(102);
                break;
            default:
                switch (id) {
                    case R.id.studio_HDMI /* 2131363309 */:
                        startToupingActivity("cn.nubia.touping.WiredHelpActivity");
                        break;
                    case R.id.studio_mili_wave /* 2131363310 */:
                        startToupingActivity("cn.nubia.touping.MiliWaveHelpActivity");
                        break;
                    case R.id.studio_mili_wave_mirror /* 2131363311 */:
                        startToupingActivity("cn.nubia.touping.USBHelpTouPingActivity");
                        break;
                    case R.id.studio_scan /* 2131363312 */:
                        startToupingActivity("cn.nubia.touping.WirelessHelpActivity");
                        break;
                    case R.id.studio_use_tell /* 2131363313 */:
                        startToupingActivity("cn.nubia.touping.UseTellActivity");
                        break;
                }
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        getWindow().setAttributes(attributes);
        getWindowManager().getDefaultDisplay().getRealMetrics(new DisplayMetrics());
        if (Math.max(r4.widthPixels, r4.heightPixels) / Math.min(r4.widthPixels, r4.heightPixels) <= 1.6f) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            this.density = displayMetrics.density;
            this.densityDpi = displayMetrics.densityDpi;
            displayMetrics.density = Math.min(r4.widthPixels, r4.heightPixels) / 800.0f;
            displayMetrics.densityDpi = (int) (displayMetrics.density * 320.0f);
        }
        setContentView(R.layout.activity_touping);
        getWindow().getDecorView().setSystemUiVisibility(4102);
        initTouPing();
        translationEnter();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        resetDensity();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        updateTouPingIndicator(this.mTouPingPager.getCurrentItem());
    }
}
