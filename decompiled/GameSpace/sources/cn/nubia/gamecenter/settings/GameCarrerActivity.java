package cn.nubia.gamecenter.settings;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.TextView;
import cn.nubia.gamecenter.settings.compatible.GameKeysHelper;
import cn.nubia.gamecenter.settings.summary.entities.OneGameTimeAndLaunchTimesInfo;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamecenter.settings.widget.HistogramChartView;
import cn.nubia.settings.trackclient.NubiaTrackManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class GameCarrerActivity extends Activity {
    private static final String HAS_NEW_VERSION = "hasNewVersion";
    private static final String KEY_START_TYPE = "gcs_start_type";
    private static final String TAG = "GameCarrerActivity";
    private Context mContext;
    private GameCarrerHelper m_helper = null;
    private String m_pkgName = null;
    private HandlerThread mWorkHandlerThread = null;
    private Handler mWorkHandler = null;

    private float adjustTotalTime(float f, OneGameTimeAndLaunchTimesInfo oneGameTimeAndLaunchTimesInfo) {
        Float valueOf = Float.valueOf(new BigDecimal(Float.valueOf(((f / 1000.0f) / 60.0f) / 60.0f).floatValue()).setScale(1, 5).floatValue());
        if (oneGameTimeAndLaunchTimesInfo.totalTimeInForeground == oneGameTimeAndLaunchTimesInfo.totalTimeInForeground7Days) {
            valueOf = Float.valueOf(getHistomChartTotalTime(oneGameTimeAndLaunchTimesInfo));
        }
        return valueOf.floatValue();
    }

    private float getHistomChartTotalTime(OneGameTimeAndLaunchTimesInfo oneGameTimeAndLaunchTimesInfo) {
        Iterator<Long> it = oneGameTimeAndLaunchTimesInfo.mDayTimesIn7Days.iterator();
        long j = 0;
        while (it.hasNext()) {
            j = (long) (j + (Float.valueOf(new BigDecimal(Float.valueOf(((it.next().longValue() / 1000.0f) / 60.0f) / 60.0f).floatValue()).setScale(1, 5).floatValue()).floatValue() * 10.0f));
        }
        if (j <= 0) {
            return j;
        }
        return (float) ((j / 10) + ((j % 10) * 0.1d));
    }

    private void hideNavigationBar() {
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    private void testHistomChartView(OneGameTimeAndLaunchTimesInfo oneGameTimeAndLaunchTimesInfo) {
        ArrayList arrayList = new ArrayList();
        Iterator<Long> it = oneGameTimeAndLaunchTimesInfo.mDayTimesIn7Days.iterator();
        while (it.hasNext()) {
            LogUtil.d(TAG, "testHistomChartView " + it.next() + " f:" + Float.valueOf(((r1.longValue() / 1000.0f) / 60.0f) / 60.0f));
            arrayList.add(Float.valueOf(new BigDecimal(r2.floatValue()).setScale(1, 5).floatValue()));
        }
        ((HistogramChartView) findViewById(R.id.histogramChartView)).setData(arrayList, this.mContext.getResources().getColor(R.color.game_carrer_bg));
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.gcs_gamecarrer_main);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        String stringExtra = getIntent().getStringExtra("pkgName");
        this.m_pkgName = stringExtra;
        String stringExtra2 = getIntent().getStringExtra("label");
        LogUtil.i(TAG, "onCreate pkgName = " + stringExtra);
        TextView textView = (TextView) findViewById(R.id.titlebar_text);
        if (textView != null) {
            textView.setText(((Object) textView.getText()) + "-" + stringExtra2);
        }
        this.m_helper = new GameCarrerHelper(this, stringExtra, R.id.gcs_main_content);
        if (stringExtra != null) {
            stringExtra.isEmpty();
        }
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mWorkHandlerThread = handlerThread;
        handlerThread.start();
        this.mWorkHandler = new Handler(this.mWorkHandlerThread.getLooper());
        this.mContext = getApplicationContext();
        NubiaTrackManager.getInstance().init(this.mContext);
        hideNavigationBar();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        HandlerThread handlerThread = this.mWorkHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
        }
        GameCarrerHelper gameCarrerHelper = this.m_helper;
        if (gameCarrerHelper != null) {
            gameCarrerHelper.onDestroy();
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String stringExtra = intent.getStringExtra("pkgName");
        if (this.m_helper == null || stringExtra == null) {
            return;
        }
        stringExtra.isEmpty();
    }

    @Override // android.app.Activity
    public void onPause() {
        GameCarrerHelper gameCarrerHelper = this.m_helper;
        if (gameCarrerHelper != null) {
            gameCarrerHelper.onPause();
        }
        super.onPause();
    }

    @Override // android.app.Activity
    public void onResume() {
        GameCarrerHelper gameCarrerHelper = this.m_helper;
        if (gameCarrerHelper != null) {
            gameCarrerHelper.onResume();
        }
        super.onResume();
        this.mWorkHandler.post(new Runnable() { // from class: cn.nubia.gamecenter.settings.GameCarrerActivity.1
            @Override // java.lang.Runnable
            public void run() {
                String readNodeValue = GameKeysHelper.getDefault().readNodeValue(GameCarrerActivity.this.mContext);
                if (readNodeValue == null) {
                    readNodeValue = "0";
                }
                if ("0".equals(readNodeValue)) {
                    LogUtil.w(GameCarrerActivity.TAG, "game keys is closed,finish!");
                    GameCarrerActivity.this.finish();
                }
            }
        });
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void updateUI(OneGameTimeAndLaunchTimesInfo oneGameTimeAndLaunchTimesInfo) {
        TextView textView = (TextView) findViewById(R.id.textGameTotalTimeValue);
        String format = String.format(getResources().getString(R.string.gcs_game_carrer_total_time_value), Float.valueOf(adjustTotalTime(oneGameTimeAndLaunchTimesInfo.totalTimeInForeground, oneGameTimeAndLaunchTimesInfo)) + "");
        if (textView != null) {
            textView.setText(format);
        }
        TextView textView2 = (TextView) findViewById(R.id.textGameLaunchTime);
        String format2 = String.format(getResources().getString(R.string.gcs_game_carrer_total_launch_time), Integer.valueOf(oneGameTimeAndLaunchTimesInfo.launchTimes));
        if (textView2 != null) {
            textView2.setText(format2);
        }
        TextView textView3 = (TextView) findViewById(R.id.gcs_game_carrer_total_time_7days);
        String string = getResources().getString(R.string.gcs_game_carrer_total_time_value);
        LogUtil.d(TAG, "info.totalTimeInForeground7Days:" + oneGameTimeAndLaunchTimesInfo.totalTimeInForeground7Days);
        String format3 = String.format(string, Float.valueOf(getHistomChartTotalTime(oneGameTimeAndLaunchTimesInfo)) + "");
        if (textView3 != null) {
            textView3.setText(format3);
        }
        TextView textView4 = (TextView) findViewById(R.id.gcs_game_carrer_total_luanch_times_7days);
        String format4 = String.format(getResources().getString(R.string.gcs_game_carrer_last7days_launch_time), Integer.valueOf(oneGameTimeAndLaunchTimesInfo.launchTimes7Days));
        if (textView4 != null) {
            textView4.setText(format4);
        }
        LogUtil.d(TAG, "updateUI");
        testHistomChartView(oneGameTimeAndLaunchTimesInfo);
    }

    public void updateUI_7DaysGameTime(int i) {
    }
}
