package cn.nubia.gameassist.tips.learn;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.tips.learn.UserGuideDialog;

/* loaded from: classes.dex */
public class UserGuideDialog extends Dialog {

    /* renamed from: c, reason: collision with root package name */
    private final Context f7633c;

    /* renamed from: h, reason: collision with root package name */
    private final Intent f7634h;

    public UserGuideDialog(Context context, Intent intent) {
        super(context);
        this.f7633c = context;
        this.f7634h = intent;
        setContentView(R.layout.layout_dialog_user_guide);
        c();
    }

    private void c() {
        findViewById(R.id.btn_view_manual).setOnClickListener(new View.OnClickListener() { // from class: j.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserGuideDialog.this.d(view);
            }
        });
        findViewById(R.id.btn_view_now).setOnClickListener(new View.OnClickListener() { // from class: j.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserGuideDialog.this.e(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(View view) {
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(View view) {
        this.f7633c.startService(this.f7634h);
        dismiss();
    }
}
