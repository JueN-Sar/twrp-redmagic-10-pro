package cn.nubia.projection.dialog;

import android.content.Context;
import android.database.ContentObserver;
import android.hardware.input.InputManager;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import cn.nubia.projection.R;
import cn.nubia.projection.util.PLog;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.shared.wrapper.InputManagerWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class MouseSensitivitySetting implements SeekBar.OnSeekBarChangeListener {

    /* renamed from: c, reason: collision with root package name */
    private final Context f8861c;

    /* renamed from: h, reason: collision with root package name */
    private final Context f8862h;

    /* renamed from: i, reason: collision with root package name */
    private final InputManager f8863i;

    /* renamed from: j, reason: collision with root package name */
    private SeekBar f8864j;

    /* renamed from: k, reason: collision with root package name */
    private View f8865k;

    /* renamed from: l, reason: collision with root package name */
    private boolean f8866l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f8867m;

    /* renamed from: n, reason: collision with root package name */
    private final ContentObserver f8868n;

    public MouseSensitivitySetting(Context context, Context context2) {
        ContentObserver contentObserver = new ContentObserver(new Handler()) { // from class: cn.nubia.projection.dialog.MouseSensitivitySetting.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                MouseSensitivitySetting.this.f();
            }
        };
        this.f8868n = contentObserver;
        this.f8861c = context;
        this.f8862h = context2;
        this.f8863i = (InputManager) context.getSystemService("input");
        context.getContentResolver().registerContentObserver(Settings.System.getUriFor("pointer_speed"), true, contentObserver);
        d();
    }

    private void d() {
        View f2 = InflaterHelper.f(R.layout.monitor_mouse_sensitivity_setting, null);
        this.f8865k = f2;
        this.f8864j = (SeekBar) f2.findViewById(R.id.sb_mouse_sensitivity);
        this.f8865k.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.projection.dialog.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MouseSensitivitySetting.this.e(view);
            }
        });
        this.f8864j.setOnSeekBarChangeListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(View view) {
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.f8864j.setProgress(c(this.f8861c) + 7);
    }

    public int c(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "pointer_speed", 0);
    }

    public void g() {
        Context context;
        if (this.f8867m) {
            this.f8867m = false;
            h(this.f8861c, this.f8864j.getProgress() - 7);
            this.f8861c.getContentResolver().unregisterContentObserver(this.f8868n);
            if (this.f8865k == null || (context = this.f8862h) == null) {
                PLog.a("dialog or context is null");
                return;
            }
            try {
                ((WindowManager) context.getSystemService("window")).removeView(this.f8865k);
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void h(Context context, int i2) {
        if (i2 < -7 || i2 > 7) {
            throw new IllegalArgumentException("speed out of range");
        }
        Settings.System.putInt(context.getContentResolver(), "pointer_speed", i2);
    }

    public void i() {
        if (this.f8867m) {
            return;
        }
        this.f8867m = true;
        this.f8864j.setProgress(c(this.f8861c) + 7);
        this.f8865k.setSystemUiVisibility(5888);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2);
        layoutParams.setTitle("mouse_sensitivity_prompt");
        layoutParams.format = -2;
        layoutParams.gravity = 17;
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.type = 2008;
        layoutParams.flags = 67110146;
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
        layoutParams.width = this.f8861c.getResources().getDimensionPixelOffset(R.dimen.monitor_dialog_width);
        layoutParams.height = this.f8861c.getResources().getDimensionPixelOffset(R.dimen.monitor_dialog_height);
        layoutParams.dimAmount = 0.6f;
        ((WindowManager) this.f8862h.getSystemService("window")).addView(this.f8865k, layoutParams);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
        if (this.f8866l) {
            return;
        }
        InputManagerWrapper.tryPointerSpeed(this.f8863i, i2 - 7);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        this.f8866l = true;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        this.f8866l = false;
        InputManagerWrapper.tryPointerSpeed(this.f8863i, seekBar.getProgress() - 7);
    }
}
