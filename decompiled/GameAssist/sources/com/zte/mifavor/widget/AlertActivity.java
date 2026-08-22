package com.zte.mifavor.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.zte.extres.R;
import com.zte.mifavor.widget.AlertController;

/* loaded from: classes2.dex */
public abstract class AlertActivity extends Activity implements DialogInterface {

    /* renamed from: c, reason: collision with root package name */
    protected AlertController f17530c;

    /* renamed from: h, reason: collision with root package name */
    protected AlertController.AlertParams f17531h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f17532i = false;

    public static boolean c(Activity activity, AccessibilityEvent accessibilityEvent) {
        accessibilityEvent.setClassName(Dialog.class.getName());
        accessibilityEvent.setPackageName(activity.getPackageName());
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        accessibilityEvent.setFullScreen(((ViewGroup.LayoutParams) attributes).width == -1 && ((ViewGroup.LayoutParams) attributes).height == -1);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ WindowInsetsCompat d(View view, WindowInsetsCompat windowInsetsCompat) {
        try {
            Insets f2 = windowInsetsCompat.f(WindowInsetsCompat.Type.e());
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.leftMargin = f2.f2920a;
            marginLayoutParams.rightMargin = f2.f2922c;
            marginLayoutParams.topMargin = f2.f2921b;
            marginLayoutParams.bottomMargin = 0;
            Log.d("AlertActivity", "set Layout Margin. topMargin=" + marginLayoutParams.topMargin + ", bottomMargin=" + marginLayoutParams.bottomMargin);
            view.setLayoutParams(marginLayoutParams);
        } catch (Exception e2) {
            Log.e("AlertActivity", "set Layout Margin error.e=", e2);
        }
        return WindowInsetsCompat.f3439b;
    }

    @Override // android.content.DialogInterface
    public void cancel() {
        finish();
    }

    @Override // android.content.DialogInterface
    public void dismiss() {
        if (isFinishing()) {
            return;
        }
        finish();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return c(this, accessibilityEvent);
    }

    protected void e() {
        View decorView = getWindow().getDecorView();
        Log.d("AlertActivity", "set Layout Margin. rootContent=" + decorView);
        if (decorView == null) {
            Log.e("AlertActivity", "set Layout Margin. do nothing.");
            return;
        }
        try {
            ViewCompat.x0(decorView, new OnApplyWindowInsetsListener() { // from class: com.zte.mifavor.widget.b
                @Override // androidx.core.view.OnApplyWindowInsetsListener
                public final WindowInsetsCompat a(View view, WindowInsetsCompat windowInsetsCompat) {
                    WindowInsetsCompat d2;
                    d2 = AlertActivity.d(view, windowInsetsCompat);
                    return d2;
                }
            });
        } catch (Exception e2) {
            Log.e("AlertActivity", "set On Apply Window Insets Listener error. e=", e2);
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_hold, R.anim.dialog_exit_material);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(R.anim.dialog_enter_material, R.anim.fade_hold);
        AlertController alertController = new AlertController(this, this, getWindow());
        this.f17530c = alertController;
        if (this.f17532i) {
            alertController.N();
        }
        this.f17531h = new AlertController.AlertParams(this);
        e();
        Utils.z(getWindow());
        try {
            getWindow().setNavigationBarContrastEnforced(false);
        } catch (Exception e2) {
            Log.e("AlertActivity", "set Navigation Bar Contrast Enforced.e=", e2);
        }
        setTitle("");
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (this.f17530c.B(i2, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (this.f17530c.C(i2, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    @Override // android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        Utils.C(getWindow());
    }
}
