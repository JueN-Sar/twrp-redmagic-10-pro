package cn.nubia.componentsdk.pay;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;

/* loaded from: classes.dex */
public class BaseActivity extends Activity {

    /* renamed from: c, reason: collision with root package name */
    protected Timer f5911c;

    protected class Timer extends CountDownTimer {
        public Timer(long j2, long j3) {
            super(j2, j3);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            BaseActivity.this.c();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j2) {
        }
    }

    protected void a() {
        ShowActivity showActivity = ShowActivity.f6021h;
        if (showActivity != null) {
            showActivity.finish();
        }
    }

    protected void b(String str) {
        Intent intent = new Intent(this, (Class<?>) ShowActivity.class);
        intent.putExtra("msg", str);
        startActivity(intent);
    }

    public void c() {
    }
}
