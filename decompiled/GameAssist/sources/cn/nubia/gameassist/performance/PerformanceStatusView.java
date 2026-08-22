package cn.nubia.gameassist.performance;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.net.TrafficStats;
import android.os.BatteryManager;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class PerformanceStatusView extends TextView {
    private static final String TAG = "PerformanceStatusView";
    private boolean isBatteryLevelType;
    private Handler mBackHandler;
    private BroadcastReceiver mBatteryChangeReceiver;
    private Context mContext;
    public long mLastByte;
    public long mLastTime;
    private Handler mMainHandler;
    private Runnable mRefreshRunnable;
    private float mSpeed;

    public PerformanceStatusView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private String c(float f2) {
        return (Float.isInfinite(f2) || Float.isNaN(f2)) ? "0.00 KB/S" : f2 < ((float) 10240) ? String.format("%.2f KB/S", Float.valueOf(f2 / 1024)) : f2 < ((float) 102400) ? String.format("%.1f KB/S", Float.valueOf(f2 / 1024)) : f2 < ((float) 1024000) ? String.format("%.0f KB/S", Float.valueOf(f2 / 1024)) : f2 < ((float) 10485760) ? String.format("%.2f MB/S", Float.valueOf(f2 / 1048576)) : f2 < ((float) 104857600) ? String.format("%.1f MB/S", Float.valueOf(f2 / 1048576)) : f2 < ((float) 1048576000) ? String.format("%.0f MB/S", Float.valueOf(f2 / 1048576)) : String.format("%.2f GB/S", Float.valueOf(f2 / 1073741824));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d() {
        setText(this.mContext.getString(R.string.net_speed_template, c(this.mSpeed)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e() {
        long currentTimeMillis = System.currentTimeMillis();
        long totalRxBytes = TrafficStats.getTotalRxBytes() + TrafficStats.getTotalTxBytes();
        this.mSpeed = (totalRxBytes - this.mLastByte) / ((currentTimeMillis - this.mLastTime) / 1000.0f);
        GaLog.a(TAG, "monitorNetSpeed mSpeed = " + this.mSpeed + ", curByte = " + totalRxBytes + ", mLastByte = " + this.mLastByte);
        if (totalRxBytes != this.mLastByte) {
            this.mMainHandler.post(new Runnable() { // from class: cn.nubia.gameassist.performance.L
                @Override // java.lang.Runnable
                public final void run() {
                    PerformanceStatusView.this.d();
                }
            });
        }
        this.mBackHandler.postDelayed(this.mRefreshRunnable, 1000L);
        this.mLastByte = totalRxBytes;
        this.mLastTime = currentTimeMillis;
    }

    private void f() {
        if (this.mRefreshRunnable == null) {
            this.mRefreshRunnable = new Runnable() { // from class: cn.nubia.gameassist.performance.K
                @Override // java.lang.Runnable
                public final void run() {
                    PerformanceStatusView.this.e();
                }
            };
        }
        this.mBackHandler.post(this.mRefreshRunnable);
    }

    private void g() {
        if (this.mBatteryChangeReceiver == null) {
            this.mBatteryChangeReceiver = new BroadcastReceiver() { // from class: cn.nubia.gameassist.performance.PerformanceStatusView.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
                        int intExtra = (int) ((intent.getIntExtra("level", 0) * 100.0f) / intent.getIntExtra("scale", 100));
                        GaLog.a(PerformanceStatusView.TAG, "registerReceiver level = " + intExtra);
                        PerformanceStatusView.this.setText(context.getString(R.string.battery_level_template, Integer.valueOf(intExtra)));
                    }
                }
            };
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        this.mContext.registerReceiver(this.mBatteryChangeReceiver, intentFilter, 2);
    }

    public void h() {
        if (!this.isBatteryLevelType) {
            f();
            return;
        }
        int intProperty = ((BatteryManager) getContext().getSystemService("batterymanager")).getIntProperty(4);
        GaLog.a(TAG, "start batteryLevel = " + intProperty);
        setText(getContext().getString(R.string.battery_level_template, Integer.valueOf(intProperty)));
        g();
    }

    public void i() {
        if (this.isBatteryLevelType) {
            g();
        } else {
            f();
        }
    }

    public void j() {
        BroadcastReceiver broadcastReceiver = this.mBatteryChangeReceiver;
        if (broadcastReceiver != null) {
            try {
                this.mContext.unregisterReceiver(broadcastReceiver);
            } catch (Exception e2) {
                GaLog.b(TAG, "unregisterReceiver exception, e  = " + e2);
            }
            this.mBatteryChangeReceiver = null;
        }
        Handler handler = this.mBackHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mRefreshRunnable);
        }
    }

    public PerformanceStatusView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public PerformanceStatusView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.performanceView);
        this.isBatteryLevelType = obtainStyledAttributes.getBoolean(R.styleable.performanceView_isBatteryLevelType, false);
        obtainStyledAttributes.recycle();
        if (this.isBatteryLevelType) {
            return;
        }
        this.mBackHandler = new Handler(ThreadManager.c().b());
        this.mMainHandler = new Handler(ThreadManager.c().e());
    }
}
