package cn.nubia.gameassist.fold;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class FoldBigDialog {

    /* renamed from: a, reason: collision with root package name */
    private Context f6501a;

    /* renamed from: b, reason: collision with root package name */
    private WindowManager f6502b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f6503c;

    /* renamed from: d, reason: collision with root package name */
    private View f6504d;

    public FoldBigDialog(Context context) {
        this.f6501a = context;
        this.f6502b = (WindowManager) context.getSystemService("window");
    }

    private void c(String str) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2038);
        layoutParams.flags = 67110688;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -2;
        layoutParams.gravity = 81;
        layoutParams.setTitle(str);
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
        this.f6503c = true;
        this.f6502b.addView(this.f6504d, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(String str, View view) {
        d();
        Utils.W(str, "foldgialog_click");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(View view) {
        d();
    }

    public void d() {
        if (this.f6503c) {
            this.f6503c = false;
            this.f6502b.removeView(this.f6504d);
            this.f6504d = null;
        }
    }

    public void g(final String str) {
        if (this.f6503c) {
            return;
        }
        View f2 = InflaterHelper.f(R.layout.gameratio_alert, null);
        this.f6504d = f2;
        TextView textView = (TextView) f2.findViewById(R.id.title);
        textView.setText(com.zte.gameassist.common.R.string.dialog_default_title);
        textView.setVisibility(0);
        TextView textView2 = (TextView) this.f6504d.findViewById(R.id.msg);
        textView2.setText(R.string.bigfold_detect_show_tip);
        textView2.setVisibility(0);
        TextView textView3 = (TextView) this.f6504d.findViewById(R.id.positive);
        textView3.setText(R.string.restart_game);
        textView3.setVisibility(0);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gameassist.fold.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FoldBigDialog.this.e(str, view);
            }
        });
        TextView textView4 = (TextView) this.f6504d.findViewById(R.id.negative);
        textView4.setText(R.string.gameratio_cancel);
        textView4.setVisibility(0);
        textView4.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gameassist.fold.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FoldBigDialog.this.f(view);
            }
        });
        c("FoldBigAlertRestart");
    }
}
