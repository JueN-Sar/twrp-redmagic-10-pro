package cn.nubia.gameassist.centerinfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.BaseViewController;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.config.ZteFeature;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes.dex */
public class CentreInfoViewController extends BaseViewController implements Handler.Callback {
    private static final DateFormat D = new SimpleDateFormat("HH:mm");
    private long A;
    private float B;
    private boolean C;

    /* renamed from: q, reason: collision with root package name */
    protected TextView f6106q;

    /* renamed from: r, reason: collision with root package name */
    protected TextView f6107r;

    /* renamed from: s, reason: collision with root package name */
    protected TextView f6108s;
    protected ImageView t;
    protected LinearLayout u;
    private final BroadcastReceiver v;
    private final IntentFilter w;
    private final Handler x;
    private final Handler y;
    private long z;

    public CentreInfoViewController(GameAssistWindowManager gameAssistWindowManager) {
        super(gameAssistWindowManager);
        IntentFilter intentFilter = new IntentFilter();
        this.w = intentFilter;
        this.x = new Handler(Looper.getMainLooper(), this);
        this.y = new Handler(ThreadManager.c().b(), this);
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        this.v = new BroadcastReceiver() { // from class: cn.nubia.gameassist.centerinfo.CentreInfoViewController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
                    int intExtra = (int) ((intent.getIntExtra("level", 0) * 100.0f) / intent.getIntExtra("scale", 100));
                    TextView textView = CentreInfoViewController.this.f6107r;
                    if (textView != null) {
                        textView.setText(context.getString(R.string.battery_level_template, Integer.valueOf(intExtra)));
                    }
                }
            }
        };
    }

    private String S(float f2) {
        return f2 < ((float) 10240) ? String.format("%.2f KB/S", Float.valueOf(f2 / 1024)) : f2 < ((float) 102400) ? String.format("%.1f KB/S", Float.valueOf(f2 / 1024)) : f2 < ((float) 1024000) ? String.format("%.0f KB/S", Float.valueOf(f2 / 1024)) : f2 < ((float) 10485760) ? String.format("%.2f MB/S", Float.valueOf(f2 / 1048576)) : f2 < ((float) 104857600) ? String.format("%.1f MB/S", Float.valueOf(f2 / 1048576)) : f2 < ((float) 1048576000) ? String.format("%.0f MB/S", Float.valueOf(f2 / 1048576)) : String.format("%.2f GB/S", Float.valueOf(f2 / 1073741824));
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public int C() {
        return R.id.game_assist_middle_info;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void P() {
        this.y.removeMessages(4097);
        this.x.removeMessages(4099);
        if (this.C) {
            this.f6117c.unregisterReceiver(this.v);
            this.C = false;
        }
        this.u = null;
        this.t = null;
        this.f6106q = null;
        this.f6107r = null;
        this.f6108s = null;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void h(PrintWriter printWriter, String str) {
        super.h(printWriter, str);
        if (this.f6106q != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("  mTimeInfo=");
            sb.append((Object) this.f6106q.getText());
            sb.append("-");
            DateFormat dateFormat = D;
            sb.append(dateFormat.format(new Date()));
            sb.append("-");
            sb.append(dateFormat.format(Calendar.getInstance().getTime()));
            printWriter.println(sb.toString());
        }
        if (this.f6107r != null) {
            printWriter.println(str + "  mBatteryInfo=" + ((Object) this.f6107r.getText()));
        }
        if (this.f6108s != null) {
            printWriter.println(str + "  mNetworkSpeedInfo=" + ((Object) this.f6108s.getText()));
        }
        printWriter.println(str + "  mSpeed=" + this.B);
        printWriter.println(str + "  mLastByte=" + this.A);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 4097:
                this.y.removeMessages(4097);
                long currentTimeMillis = System.currentTimeMillis();
                long totalRxBytes = TrafficStats.getTotalRxBytes() + TrafficStats.getTotalTxBytes();
                long j2 = this.A;
                this.B = (totalRxBytes - j2) / ((currentTimeMillis - this.z) / 1000.0f);
                if (totalRxBytes != j2) {
                    this.x.sendEmptyMessage(4098);
                }
                this.A = totalRxBytes;
                this.z = currentTimeMillis;
                this.y.sendEmptyMessageDelayed(4097, 1000L);
                break;
            case 4098:
                TextView textView = this.f6108s;
                if (textView != null) {
                    textView.setText(this.f6117c.getString(R.string.net_speed_template, S(this.B)));
                    break;
                }
                break;
            case 4099:
                this.x.removeMessages(4099);
                TextView textView2 = this.f6106q;
                if (textView2 != null) {
                    textView2.setText(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm").withZone(ZoneId.systemDefault())));
                }
                this.x.sendEmptyMessageDelayed(4099, 1000L);
                break;
        }
        return true;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void o(View view) {
        this.u = (LinearLayout) i(R.id.game_assist_middle_info);
        this.t = (ImageView) i(R.id.game_assist_icon_info);
        this.f6106q = (TextView) i(R.id.game_assist_time_info);
        this.f6107r = (TextView) i(R.id.game_assist_battery_info);
        this.f6108s = (TextView) i(R.id.game_assist_network_speed_info);
        if (!this.C) {
            this.f6117c.registerReceiver(this.v, this.w, 2);
            this.C = true;
        }
        this.x.sendEmptyMessage(4098);
        this.x.sendEmptyMessage(4099);
        this.y.sendEmptyMessage(4097);
        if (ZteFeature.isRedMagicProduct()) {
            return;
        }
        this.t.setVisibility(8);
    }
}
