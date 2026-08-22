package cn.nubia.gamelauncher.controller;

import android.content.ContentProviderClient;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.Renderer;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.helper.Controller;
import cn.nubia.gamelauncher.upgrade.NetworkHelper;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/* loaded from: classes.dex */
public class IndicatorController extends ConstraintLayout implements View.OnClickListener {
    private static final String CALL_DETECT = "call_network_detect";
    private static String DEFAULT_STATUS = null;
    private static final String GLOBAL_INDICATOR = "perf_indicator_network_result";
    private static final String INDICATOR_PROVIDER = "com.zte.performanceindicator.provider.performanceindicatorprovider";
    private static final int INTERVAL = 10000;
    private static final int STATUS_FAIR = 1;
    private static final int STATUS_GOOD = 0;
    private static final int STATUS_JITTER_FAIR = 100;
    private static final int STATUS_JITTER_GOOD = 50;
    private static final int STATUS_LATENCY_FAIR = 120;
    private static final int STATUS_LATENCY_GOOD = 60;
    private static final int STATUS_POOR = 2;
    private static final String TAG = "Indicator";
    private static final Uri URI_INDICATOR_PKG = Uri.parse("content://com.zte.performanceindicator.provider.performanceindicatorprovider");
    private final String[] detectorStatus;
    private boolean isDetecting;
    private boolean isVisible;
    private boolean isWaiting;
    private TextView mCheckTime;
    CountDownTimer mCountDownTimer;
    private int mCurIndex;
    private LocalTime mDetectTime;
    private Button mDetectorButton;
    private TextView mDetectorStatus;
    private TextView mDetectorText;
    private final Handler mHandler;
    private final ContentObserver mIndicatorObserver;
    private TextView mJitterValue;
    private TextView mLatencyValue;
    private TextView mStatus;
    private ImageView mStatusBg;

    public IndicatorController(Context context) {
        super(context);
        this.detectorStatus = new String[]{" ", ".", "..", "..."};
        this.isDetecting = false;
        this.isWaiting = false;
        this.isVisible = false;
        this.mDetectTime = null;
        this.mCurIndex = 0;
        Handler handler = new Handler(Looper.getMainLooper());
        this.mHandler = handler;
        this.mIndicatorObserver = new ContentObserver(handler) { // from class: cn.nubia.gamelauncher.controller.IndicatorController.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                String string = Settings.Global.getString(IndicatorController.this.getContext().getContentResolver(), IndicatorController.GLOBAL_INDICATOR);
                Log.d(IndicatorController.TAG, "onChange() value: " + string);
                IndicatorController.this.doParse(string);
                IndicatorController.this.isDetecting = false;
                IndicatorController.this.isWaiting = true;
                IndicatorController.this.mHandler.removeCallbacksAndMessages(null);
                IndicatorController.this.startCountdown();
            }
        };
        initView();
        registerObserver();
    }

    private void detectFailed() {
        Log.d(TAG, "detectFailed()");
        this.mStatusBg.setBackgroundResource(R.drawable.detector_state_null);
        updateChildView(this.mStatus, getDefaultStatus(), getResources().getColor(R.color.detector_color_null, null));
        updateChildView(this.mLatencyValue, getDefaultStatus() + "ms", getResources().getColor(R.color.detector_color_null, null));
        updateChildView(this.mJitterValue, getDefaultStatus() + "ms", getResources().getColor(R.color.detector_color_null, null));
    }

    private void detectSuccess(int i, int i2, String str) {
        Log.d(TAG, "detectSuccess() latency: " + i + ", jitter: " + i2 + ", time: " + str);
        int status = getStatus(i, 60, 120);
        int status2 = getStatus(i2, 50, 100);
        int i3 = status + status2;
        String string = getContext().getString(i3 > 1 ? R.string.performance_status_poor : i3 == 0 ? R.string.performance_status_good : R.string.performance_status_fair);
        this.mStatusBg.setBackgroundResource(i3 > 1 ? R.drawable.detector_state_poor : i3 == 0 ? R.drawable.detector_state_good : R.drawable.detector_state_fair);
        updateChildView(this.mStatus, string, getTextColor(i3));
        updateChildView(this.mLatencyValue, i + "ms", getTextColor(status));
        updateChildView(this.mJitterValue, i2 + "ms", getTextColor(status2));
        this.mCheckTime.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void detectTimeOut() {
        this.isDetecting = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doParse(String str) {
        if (str == null) {
            detectFailed();
            return;
        }
        String[] split = str.split("_");
        if (split.length < 4) {
            detectFailed();
            return;
        }
        if (split[0].equals("true")) {
            detectFailed();
            return;
        }
        String str2 = split[3];
        if (isTimeInvalid(str2)) {
            detectFailed();
            return;
        }
        detectSuccess(Integer.parseInt(split[1]), Integer.parseInt(split[2]), str2);
    }

    private void doTrack() {
        if (CommonUtil.isInternalVersion()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(NubiaTrackManager.EVENT_NAME, "performance_Indicator_used");
        bundle.putString("app_name", Controller.getInstance().getSelectedItemName());
        bundle.putString("package_name", Controller.getInstance().getSelectedItemPackage());
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
    }

    private String getDefaultStatus() {
        if (DEFAULT_STATUS == null) {
            DEFAULT_STATUS = getContext().getString(R.string.performance_status_null);
        }
        return DEFAULT_STATUS;
    }

    private int getStatus(int i, int i2, int i3) {
        if (i <= i2) {
            return 0;
        }
        return i > i3 ? 2 : 1;
    }

    private int getTextColor(int i) {
        return getResources().getColor(i == 0 ? R.color.detector_color_good : i == 1 ? R.color.detector_color_fair : R.color.detector_color_poor, null);
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.top_state_detector, this);
        this.mCheckTime = (TextView) findViewById(R.id.detector_check_time);
        this.mStatus = (TextView) findViewById(R.id.status);
        this.mJitterValue = (TextView) findViewById(R.id.detector_jitter_value);
        this.mLatencyValue = (TextView) findViewById(R.id.detector_latency_value);
        this.mDetectorButton = (Button) findViewById(R.id.indicator_detector_button);
        this.mStatusBg = (ImageView) findViewById(R.id.detector_status_bg);
        this.mDetectorStatus = (TextView) findViewById(R.id.detector_status);
        this.mDetectorText = (TextView) findViewById(R.id.detector_text);
        this.mDetectorButton.setOnClickListener(this);
    }

    private boolean isTimeInvalid(String str) {
        try {
            Log.d(TAG, "isTimeInvalid() time : " + str + ", mDetectTime : " + this.mDetectTime);
            if (this.mDetectTime == null) {
                return false;
            }
            return LocalTime.parse(str, DateTimeFormatter.ofPattern("HH:mm:ss", Locale.forLanguageTag("ar"))).isBefore(this.mDetectTime);
        } catch (Exception e) {
            Log.w(TAG, "isTimeInvalid() e : " + e.getMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r6v0, types: [cn.nubia.gamelauncher.controller.IndicatorController$2] */
    public void startCountdown() {
        this.mCountDownTimer = new CountDownTimer(Renderer.DEFAULT_DURATION_TO_PROGRESS_US, 1000L) { // from class: cn.nubia.gamelauncher.controller.IndicatorController.2
            @Override // android.os.CountDownTimer
            public void onFinish() {
                IndicatorController.this.isWaiting = false;
                IndicatorController.this.updateDetectButtonEnable();
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                IndicatorController.this.mDetectorText.setText(((Object) IndicatorController.this.getContext().getText(R.string.performance_wait_Interval)) + "" + (j / 1000) + ExifInterface.LATITUDE_SOUTH);
                IndicatorController.this.mDetectorText.setTextColor(1509949439);
                IndicatorController.this.mDetectorStatus.setVisibility(8);
            }
        }.start();
    }

    private void updateChildView(TextView textView, String str, int i) {
        textView.setTextColor(i);
        textView.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDetectButtonEnable() {
        boolean isNetworkConnected = NetworkHelper.isNetworkConnected(getContext());
        Log.d(TAG, "updateDetectButtonEnable() isNetConnected : " + isNetworkConnected);
        this.mDetectorButton.setEnabled((!isNetworkConnected || this.isDetecting || this.isWaiting) ? false : true);
        if (this.isWaiting) {
            return;
        }
        updateDetectStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDetectStatus() {
        Log.d(TAG, "updateDetectButtonText() isDetecting : " + this.isDetecting);
        this.mDetectorStatus.setVisibility(this.isDetecting ? 0 : 8);
        int i = this.isDetecting ? R.string.performance_verifying : R.string.performance_verify;
        if (!NetworkHelper.isNetworkConnected(getContext())) {
            i = R.string.performance_no_network;
        }
        this.mDetectorText.setText(getContext().getText(i));
        this.mDetectorText.setTextColor(this.isDetecting ? 1509949439 : -1);
        if (!this.isVisible) {
            this.mHandler.removeCallbacks(new Runnable() { // from class: cn.nubia.gamelauncher.controller.IndicatorController$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    IndicatorController.this.updateDetectStatus();
                }
            });
            return;
        }
        int i2 = this.mCurIndex + 1;
        String[] strArr = this.detectorStatus;
        int length = i2 % strArr.length;
        this.mCurIndex = length;
        this.mDetectorStatus.setText(strArr[length]);
        if (this.isDetecting) {
            this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.controller.IndicatorController$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    IndicatorController.this.updateDetectStatus();
                }
            }, 500L);
        } else {
            this.mHandler.removeCallbacks(new Runnable() { // from class: cn.nubia.gamelauncher.controller.IndicatorController$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    IndicatorController.this.updateDetectStatus();
                }
            });
            this.mCurIndex = 0;
        }
    }

    public void cancelCountdown() {
        CountDownTimer countDownTimer = this.mCountDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.mCountDownTimer = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void doDetect() {
        Bundle bundle;
        ContentProviderClient acquireUnstableContentProviderClient;
        ContentProviderClient contentProviderClient = null;
        ContentProviderClient contentProviderClient2 = null;
        try {
            try {
                bundle = new Bundle();
                bundle.putString("package", getContext().getPackageName());
                acquireUnstableContentProviderClient = getContext().getContentResolver().acquireUnstableContentProviderClient(URI_INDICATOR_PKG);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        if (acquireUnstableContentProviderClient == null) {
            if (acquireUnstableContentProviderClient != null) {
                acquireUnstableContentProviderClient.close();
                return;
            }
            return;
        }
        try {
            Log.d(TAG, "doDetect() call call_network_detect, result : " + acquireUnstableContentProviderClient.call(CALL_DETECT, null, bundle).getString("result"));
            this.mDetectTime = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            this.isDetecting = true;
            updateDetectButtonEnable();
            Handler handler = this.mHandler;
            Runnable runnable = new Runnable() { // from class: cn.nubia.gamelauncher.controller.IndicatorController$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    IndicatorController.this.detectTimeOut();
                }
            };
            handler.postDelayed(runnable, Renderer.DEFAULT_DURATION_TO_PROGRESS_US);
            acquireUnstableContentProviderClient.close();
            doTrack();
            contentProviderClient = runnable;
            if (acquireUnstableContentProviderClient != null) {
                acquireUnstableContentProviderClient.close();
                contentProviderClient = runnable;
            }
        } catch (Exception e2) {
            e = e2;
            contentProviderClient2 = acquireUnstableContentProviderClient;
            Log.d(TAG, "doDetect() error: " + e);
            contentProviderClient = contentProviderClient2;
            if (contentProviderClient2 != null) {
                contentProviderClient2.close();
                contentProviderClient = contentProviderClient2;
            }
        } catch (Throwable th2) {
            th = th2;
            contentProviderClient = acquireUnstableContentProviderClient;
            if (contentProviderClient != null) {
                contentProviderClient.close();
            }
            throw th;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String string = Settings.Global.getString(getContext().getContentResolver(), GLOBAL_INDICATOR);
        Log.d(TAG, "onAttachedToWindow() value: " + string);
        doParse(string);
        this.isVisible = true;
        updateDetectButtonEnable();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.indicator_detector_button) {
            doDetect();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isVisible = false;
        Log.d(TAG, "onDetachedFromWindow()");
    }

    public void registerObserver() {
        Log.d(TAG, "registerObserver()");
        getContext().getContentResolver().registerContentObserver(Settings.Global.getUriFor(GLOBAL_INDICATOR), true, this.mIndicatorObserver);
    }

    public void unregisterObserver() {
        Log.d(TAG, "unregisterObserver()");
        getContext().getContentResolver().unregisterContentObserver(this.mIndicatorObserver);
    }
}
