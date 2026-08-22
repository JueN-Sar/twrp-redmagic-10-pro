package com.zte.gameassist.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.zte.gameassist.common.R;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.dialog.GameAssistDialog;
import com.zte.gameassist.utils.GaLog;
import com.zte.mifavor.widget.AlertDialog;
import com.zte.mifavor.widget.DialogTitle;

/* loaded from: classes2.dex */
public class GameAssistDialog extends AlertDialog implements RotationMgr.Callback {

    /* renamed from: c, reason: collision with root package name */
    private int f16629c;

    public GameAssistDialog(Context context) {
        super(context);
    }

    public static void f(final Window window) {
        if (window == null) {
            return;
        }
        window.getDecorView().post(new Runnable() { // from class: o.a
            @Override // java.lang.Runnable
            public final void run() {
                GameAssistDialog.h(window);
            }
        });
    }

    public static GameAssistDialog g(Context context) {
        int identifier = context.getResources().getIdentifier("versionType", "attr", context.getPackageName());
        if (identifier != 0) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(identifier, typedValue, true);
            GaLog.a("GameAssistDialog", "theme " + ((String) typedValue.string));
            if ("mifavor".equalsIgnoreCase((String) typedValue.string)) {
                return new GameAssistDialog(context);
            }
        }
        Context createWindowContext = context.createWindowContext(2008, null);
        createWindowContext.getTheme().applyStyle(R.style.GameAssist_Theme_ZTE_Light, true);
        return new GameAssistDialog(createWindowContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void h(Window window) {
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = RotationMgr.k() ? -1 : -2;
        window.setAttributes(attributes);
    }

    public void i(int i2, DialogInterface.OnClickListener onClickListener) {
        setButton(-1, getContext().getString(i2), onClickListener);
        this.f16629c = R.color.dialog_btn_text_warn_color;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!ZteFeature.isTabletProduct()) {
            RotationMgr.e(getContext()).c(this);
        }
        Window window = getWindow();
        if (window != null) {
            View decorView = getWindow().getDecorView();
            WindowInsetsCompat B = ViewCompat.B(decorView);
            decorView.findViewById(com.zte.extres.R.id.buttonPanel).setPadding(0, 0, 0, B != null ? B.f(WindowInsetsCompat.Type.d()).f2923d : 0);
            window.findViewById(com.zte.extres.R.id.navigation_key_area).setVisibility(8);
        }
    }

    @Override // com.zte.mifavor.widget.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window == null) {
            dismiss();
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.setFitInsetsTypes(0);
        window.setAttributes(attributes);
        window.setType(2008);
        View decorView = window.getDecorView();
        decorView.findViewById(com.zte.extres.R.id.parentPanel).setBackground(ContextCompat.e(getContext(), R.drawable.ga_dialog_background_material));
        View findViewById = decorView.findViewById(com.zte.extres.R.id.alertTitle);
        if (findViewById instanceof DialogTitle) {
            ((DialogTitle) findViewById).setTextColor(getContext().getColor(R.color.ga_common_pop_title));
        }
        TextView textView = (TextView) decorView.findViewById(android.R.id.message);
        if (textView != null) {
            textView.setTextColor(getContext().getColor(R.color.ga_common_pop_primary_txt));
        }
        View findViewById2 = decorView.findViewById(com.zte.extres.R.id.divider1);
        if (findViewById2 != null) {
            findViewById2.setVisibility(8);
        }
        Button button = (Button) decorView.findViewById(android.R.id.button2);
        if (button != null) {
            button.setTextColor(getContext().getColor(R.color.dialog_btn_text_default_color));
            button.setBackground(ContextCompat.e(getContext(), R.drawable.ga_dialog_button_bg));
        }
        Button button2 = (Button) decorView.findViewById(android.R.id.button1);
        if (button2 != null) {
            Context context = getContext();
            int i2 = this.f16629c;
            if (i2 <= 0) {
                i2 = R.color.dialog_btn_text_default_color;
            }
            button2.setTextColor(context.getColor(i2));
            button2.setBackground(ContextCompat.e(getContext(), R.drawable.ga_dialog_button_bg));
            button2.setMaxLines(2);
        }
        if (ZteFeature.isTabletProduct()) {
            return;
        }
        f(getWindow());
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (ZteFeature.isTabletProduct()) {
            return;
        }
        RotationMgr.e(getContext()).p(this);
    }

    @Override // com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        f(getWindow());
    }
}
