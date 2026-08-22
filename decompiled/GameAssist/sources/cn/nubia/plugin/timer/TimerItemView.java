package cn.nubia.plugin.timer;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.PathInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.plugin.timer.TimerCount;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.NubiaTrackManager;
import java.text.DecimalFormat;

/* loaded from: classes.dex */
public class TimerItemView extends RelativeLayout implements TimerCount.TimerCountCallback {
    private Runnable mAlphaRunnable;
    private int mCurTime;
    private TimerItemData mData;
    private Handler mHandler;
    private boolean mSettingShow;
    private boolean mStartShow;
    private TextView mText;
    private TimerCount mTimerCount;

    public TimerItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private int getDuration() {
        return getMax() - this.mData.f8733e;
    }

    private int getMax() {
        TimerItemData timerItemData = this.mData;
        if (timerItemData.f8730b) {
            return 3600;
        }
        return timerItemData.f8731c;
    }

    private void i(boolean z, int i2) {
        String str = this.mData.f8730b ? "timing" : "countdown";
        String str2 = z ? "manual" : "time_up";
        Bundle bundle = new Bundle();
        bundle.putString("app_name", SystemMgr.t());
        bundle.putString("stype", str);
        bundle.putString("reason_for_ending", str2);
        bundle.putString("duration", i2 + "");
        NubiaTrackManager.p().x("cn.nubia.gamelauncher", "game_stopwatch_used", bundle);
    }

    private void j() {
        TimerItemData timerItemData = this.mData;
        int i2 = timerItemData.f8733e;
        if (!timerItemData.f8730b) {
            i2 = getDuration();
        }
        String format = new DecimalFormat("00").format((i2 % 3600) / 60);
        String format2 = new DecimalFormat("00").format(i2 % 60);
        this.mText.setText(format + ":" + format2);
        n();
    }

    private void l(View view) {
        PathInterpolator pathInterpolator = new PathInterpolator(0.42f, 0.0f, 0.58f, 1.0f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(pathInterpolator);
        animationSet.setDuration(200L);
        animationSet.addAnimation(new ScaleAnimation(1.0f, 1.185f, 1.0f, 1.185f, view.getWidth() / 2.0f, view.getHeight() / 2.0f));
        animationSet.addAnimation(new AlphaAnimation(0.5f, 1.0f));
        view.startAnimation(animationSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        TimerItemData timerItemData = this.mData;
        if (timerItemData == null) {
            return;
        }
        if (timerItemData.f8730b) {
            if (this.mSettingShow) {
                setBackgroundResource(R.drawable.timer_inc_select);
                this.mText.setAlpha(0.85f);
                return;
            } else if (timerItemData.f8732d) {
                setBackgroundResource(R.drawable.timer_inc_running);
                this.mText.setAlpha(0.85f);
                return;
            } else if (this.mStartShow) {
                setBackgroundResource(R.drawable.timer_inc_select);
                this.mText.setAlpha(0.85f);
                return;
            } else {
                setBackgroundResource(R.drawable.timer_inc_normal);
                this.mText.setAlpha(0.3f);
                return;
            }
        }
        if (this.mSettingShow) {
            setBackgroundResource(R.drawable.timer_uninc_select);
            this.mText.setAlpha(0.85f);
        } else if (timerItemData.f8732d) {
            setBackgroundResource(R.drawable.timer_uninc_running);
            this.mText.setAlpha(0.85f);
        } else if (this.mStartShow) {
            setBackgroundResource(R.drawable.timer_uninc_select);
            this.mText.setAlpha(0.85f);
        } else {
            setBackgroundResource(R.drawable.timer_uninc_normal);
            this.mText.setAlpha(0.3f);
        }
    }

    private void o() {
        ((Vibrator) getContext().getSystemService("vibrator")).vibrate(500L);
    }

    @Override // cn.nubia.plugin.timer.TimerCount.TimerCountCallback
    public void a(int i2) {
        this.mCurTime = i2;
        TimerItemData timerItemData = this.mData;
        timerItemData.f8732d = false;
        i(false, timerItemData.f8731c);
        this.mData.f8733e = 0;
        j();
        invalidate();
        TimerMgr.r().z(this.mData.f8729a);
        l(this.mText);
        o();
    }

    @Override // cn.nubia.plugin.timer.TimerCount.TimerCountCallback
    public void b(int i2) {
        this.mCurTime = i2;
        this.mData.f8733e = i2;
        j();
        n();
        invalidate();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public void e() {
        if (this.mData.f8732d) {
            this.mTimerCount.c();
            i(true, getDuration());
            this.mData.f8733e = 0;
            j();
        } else {
            this.mTimerCount.b(getMax());
        }
        TimerItemData timerItemData = this.mData;
        timerItemData.f8732d = true ^ timerItemData.f8732d;
        n();
        TimerMgr.r().z(this.mData.f8729a);
    }

    public void f(TimerItemData timerItemData) {
        this.mData = timerItemData;
        if (timerItemData.f8732d) {
            timerItemData.f8733e = this.mCurTime;
        } else {
            this.mCurTime = 0;
        }
        j();
    }

    public boolean g() {
        return this.mData.f8732d;
    }

    public void h() {
        if (this.mData.f8732d) {
            e();
        }
    }

    public void k(boolean z) {
        if (this.mSettingShow == z) {
            return;
        }
        this.mSettingShow = z;
        j();
    }

    public void m() {
        this.mHandler.removeCallbacks(this.mAlphaRunnable);
        this.mHandler.postDelayed(this.mAlphaRunnable, 3000L);
        this.mStartShow = true;
        n();
        invalidate();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mTimerCount = new TimerCount(this);
        this.mText = (TextView) findViewById(R.id.plugin_timer_item_text);
        n();
    }

    public TimerItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mSettingShow = false;
        this.mStartShow = false;
        this.mHandler = new Handler();
        this.mAlphaRunnable = new Runnable() { // from class: cn.nubia.plugin.timer.TimerItemView.1
            @Override // java.lang.Runnable
            public void run() {
                TimerItemView.this.mStartShow = false;
                TimerItemView.this.n();
                TimerItemView.this.invalidate();
            }
        };
    }
}
