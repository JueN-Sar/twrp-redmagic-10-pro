package cn.nubia.multisubscreen.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.theme.Theme;
import cn.nubia.gameassist.theme.ThemeWidget;
import cn.nubia.gameassist.view.YouSheTextView;
import cn.nubia.multisubscreen.mgr.MultiSubScreenThemeMgr;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.zte.gameassist.utils.GaLog;
import java.util.List;

/* loaded from: classes.dex */
public class SinkTitleView extends RelativeLayout implements ThemeWidget, MultiSubScreenUtils.GameStatusCallback {
    private static final String NUM_INVALID = "--";
    private static final String TAG = "MultiSubScreen_SinkTitleView";
    private static final String TIME_INVALID = "--:--";
    private BatteryView mBatteryView;
    private YouSheTextView mCPS;
    private Button mDisconnectBtn;
    private YouSheTextView mFPS;
    private MarqueeYouSheTextView mGameDuration;
    private int mGameDurationTime;
    private YouSheTextView mMPM;
    private YouSheTextView mNET;
    private YouSheTextView mTimeDisplay;

    public SinkTitleView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private String a(float f2) {
        return (Float.isInfinite(f2) || Float.isNaN(f2)) ? "0.00 KB/S" : f2 < ((float) 10240) ? String.format("%.2fKB/S", Float.valueOf(f2 / 1024)) : f2 < ((float) 102400) ? String.format("%.1fKB/S", Float.valueOf(f2 / 1024)) : f2 < ((float) 1024000) ? String.format("%.0fKB/S", Float.valueOf(f2 / 1024)) : f2 < ((float) 10485760) ? String.format("%.2fMB/S", Float.valueOf(f2 / 1048576)) : f2 < ((float) 104857600) ? String.format("%.1fMB/S", Float.valueOf(f2 / 1048576)) : f2 < ((float) 1048576000) ? String.format("%.0fMB/S", Float.valueOf(f2 / 1048576)) : String.format("%.2fGB/S", Float.valueOf(f2 / 1073741824));
    }

    public String b(int i2) {
        int i3 = i2 / 3600;
        int i4 = (i2 % 3600) / 60;
        StringBuilder sb = new StringBuilder();
        if (i3 < 10) {
            sb.append("0" + i3 + ":");
        } else {
            sb.append(i3 + ":");
        }
        if (i3 == 0 && i4 == 0) {
            sb.append("01");
        } else if (i4 < 10) {
            sb.append("0" + i4);
        } else {
            sb.append(i4);
        }
        return sb.toString();
    }

    @Override // cn.nubia.gameassist.theme.ThemeWidget
    public void d(Theme theme) {
        this.mGameDuration.setTextColor(theme.f7437d);
        this.mFPS.setTextColor(theme.f7437d);
        this.mNET.setTextColor(theme.f7437d);
        this.mCPS.setTextColor(theme.f7437d);
        this.mMPM.setTextColor(theme.f7437d);
    }

    @Override // cn.nubia.multisubscreen.utils.MultiSubScreenUtils.GameStatusCallback
    public void e(boolean z) {
        if (z) {
            setCurrentTime(TIME_INVALID);
            setBatteryLevel(NUM_INVALID);
            setGameDuration(TIME_INVALID);
            setFps(NUM_INVALID);
            setCps(NUM_INVALID);
            setNet(NUM_INVALID);
            setMpm(NUM_INVALID);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MultiSubScreenThemeMgr.e().b(this);
        MultiSubScreenUtils.B(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        MultiSubScreenThemeMgr.e().g(this);
        MultiSubScreenUtils.M(this);
        this.mGameDuration.setSelected(false);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mTimeDisplay = (YouSheTextView) findViewById(R.id.sink_time_display);
        this.mBatteryView = (BatteryView) findViewById(R.id.sink_level_remain);
        this.mFPS = (YouSheTextView) findViewById(R.id.sink_fps_value);
        this.mCPS = (YouSheTextView) findViewById(R.id.sink_cps_value);
        this.mNET = (YouSheTextView) findViewById(R.id.sink_net_value);
        this.mMPM = (YouSheTextView) findViewById(R.id.sink_mpm_value);
        MarqueeYouSheTextView marqueeYouSheTextView = (MarqueeYouSheTextView) findViewById(R.id.sink_play_time_value);
        this.mGameDuration = marqueeYouSheTextView;
        marqueeYouSheTextView.setSingleLine(true);
        this.mGameDuration.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        this.mGameDuration.setSelected(true);
        this.mGameDuration.setFocusable(true);
        this.mDisconnectBtn = (Button) findViewById(R.id.sink_disconnect);
    }

    @Override // cn.nubia.multisubscreen.utils.MultiSubScreenUtils.GameStatusCallback
    public void s(boolean z) {
        if (z) {
            return;
        }
        setGameDuration(TIME_INVALID);
    }

    public void setBatteryLevel(String str) {
        if (MultiSubScreenUtils.f8183m) {
            this.mBatteryView.setText(NUM_INVALID);
            return;
        }
        this.mBatteryView.setText(str + "%");
        this.mBatteryView.a(Integer.parseInt(str));
    }

    public void setCps(String str) {
        YouSheTextView youSheTextView = this.mCPS;
        if (MultiSubScreenUtils.f8183m) {
            str = NUM_INVALID;
        }
        youSheTextView.setText(str);
    }

    public void setCurrentTime(String str) {
        if (MultiSubScreenUtils.f8183m) {
            this.mTimeDisplay.setText(TIME_INVALID);
        } else {
            this.mTimeDisplay.setText(str);
        }
    }

    public void setFps(String str) {
        GaLog.a(TAG, "setFps fps = " + str + ", S_SOURCE_IS_STOP_SEND_DATA = " + MultiSubScreenUtils.f8183m);
        YouSheTextView youSheTextView = this.mFPS;
        if (MultiSubScreenUtils.f8183m) {
            str = NUM_INVALID;
        }
        youSheTextView.setText(str);
    }

    public void setGameDuration(String str) {
        if (!MultiSubScreenUtils.f8182l) {
            this.mGameDuration.setText(R.string.source_not_in_game);
        } else {
            if (TIME_INVALID.equals(str)) {
                return;
            }
            int parseLong = (int) (Long.parseLong(str) / 1000);
            this.mGameDurationTime = parseLong;
            setGameDurationInner(parseLong);
        }
    }

    public void setGameDurationInner(int i2) {
        this.mGameDuration.setText(b(i2));
    }

    public void setKeys(List<String> list) {
    }

    public void setMpm(String str) {
        String str2;
        YouSheTextView youSheTextView = this.mMPM;
        if (MultiSubScreenUtils.f8183m) {
            str2 = NUM_INVALID;
        } else {
            str2 = str + "%";
        }
        youSheTextView.setText(str2);
    }

    public void setNet(String str) {
        boolean z = MultiSubScreenUtils.f8183m;
        String str2 = NUM_INVALID;
        if (z) {
            this.mNET.setText(NUM_INVALID);
            return;
        }
        try {
            float parseFloat = Float.parseFloat(str);
            YouSheTextView youSheTextView = this.mNET;
            if (!MultiSubScreenUtils.f8183m) {
                str2 = a(parseFloat);
            }
            youSheTextView.setText(str2);
        } catch (NumberFormatException unused) {
        }
    }

    public void setOnDisconnectClickListener(View.OnClickListener onClickListener) {
        this.mDisconnectBtn.setOnClickListener(onClickListener);
    }

    public SinkTitleView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public SinkTitleView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }
}
