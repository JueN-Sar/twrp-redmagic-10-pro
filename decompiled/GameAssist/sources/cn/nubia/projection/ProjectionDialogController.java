package cn.nubia.projection;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.hardware.display.DisplayManager;
import android.provider.Settings;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import cn.nubia.projection.dialog.MouseSensitivitySetting;
import cn.nubia.projection.util.PLog;
import cn.nubia.projection.util.ProjectionUtil;
import com.google.android.material.card.MaterialCardView;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.dialog.GameAssistDialog;
import com.zte.mifavor.widget.AlertDialog;
import com.zte.shared.wrapper.ContextWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class ProjectionDialogController {

    /* renamed from: a, reason: collision with root package name */
    private DisplayManager f8804a;

    /* renamed from: b, reason: collision with root package name */
    private ProjectionUIController f8805b;

    /* renamed from: c, reason: collision with root package name */
    private Context f8806c;

    /* renamed from: d, reason: collision with root package name */
    private Display f8807d;

    /* renamed from: e, reason: collision with root package name */
    private View f8808e;

    /* renamed from: f, reason: collision with root package name */
    AlertDialog f8809f;

    /* renamed from: g, reason: collision with root package name */
    private View f8810g;

    /* renamed from: h, reason: collision with root package name */
    private MouseSensitivitySetting f8811h;

    private void A(Context context, int i2) {
        C(context, this.f8810g, i2);
        this.f8810g = null;
    }

    private void B(View view, Context context) {
        if (view == null || context == null) {
            return;
        }
        try {
            ((WindowManager) context.getSystemService("window")).removeView(view);
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        }
    }

    private void C(Context context, View view, int i2) {
        B(view, context);
        ProjectionManager.o().J(7, i2, 0, null);
    }

    private void k(String str, final Context context) {
        if (Settings.Global.getInt(this.f8806c.getContentResolver(), "monitor_input_prompt_hide", 0) == 1) {
            return;
        }
        if ("0".equals(str)) {
            if (this.f8808e != null) {
                z(context);
                return;
            }
            return;
        }
        if (this.f8808e != null) {
            return;
        }
        View f2 = InflaterHelper.f(R.layout.monitor_input_prompt, null);
        this.f8808e = f2;
        f2.findViewById(R.id.btn_left).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.projection.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProjectionDialogController.this.p(context, view);
            }
        });
        this.f8808e.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.projection.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProjectionDialogController.this.q(context, view);
            }
        });
        this.f8808e.findViewById(R.id.btn_right).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.projection.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProjectionDialogController.this.r(context, view);
            }
        });
        this.f8808e.setSystemUiVisibility(5888);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2);
        layoutParams.setTitle("input_prompt");
        layoutParams.type = 2012;
        layoutParams.format = -2;
        layoutParams.gravity = MaterialCardView.CHECKED_ICON_GRAVITY_BOTTOM_END;
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.flags = 67110184;
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
        layoutParams.width = this.f8806c.getResources().getDimensionPixelOffset(R.dimen.monitor_input_dialog_width);
        layoutParams.height = this.f8806c.getResources().getDimensionPixelOffset(R.dimen.monitor_input_dialog_height);
        layoutParams.windowAnimations = android.R.style.Animation.Translucent;
        ((WindowManager) context.getSystemService("window")).addView(this.f8808e, layoutParams);
    }

    private void m(String str, final Context context) {
        View f2 = InflaterHelper.f(R.layout.monitor_open_app_prompt, null);
        this.f8810g = f2;
        f2.findViewById(R.id.btn_left).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.projection.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProjectionDialogController.this.s(context, view);
            }
        });
        this.f8810g.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.projection.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProjectionDialogController.this.t(context, view);
            }
        });
        this.f8810g.findViewById(R.id.btn_right).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.projection.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProjectionDialogController.this.u(context, view);
            }
        });
        ImageView imageView = (ImageView) this.f8810g.findViewById(R.id.iv_app_icon);
        TextView textView = (TextView) this.f8810g.findViewById(R.id.tv_app_name);
        TextView textView2 = (TextView) this.f8810g.findViewById(R.id.tv_prompt_title);
        PackageInfo b2 = ProjectionUtil.b(this.f8806c, str);
        if (b2 != null) {
            imageView.setImageDrawable(this.f8806c.getPackageManager().getApplicationIcon(b2.applicationInfo));
            CharSequence applicationLabel = this.f8806c.getPackageManager().getApplicationLabel(b2.applicationInfo);
            textView.setText(applicationLabel);
            textView2.setText(this.f8806c.getString(R.string.monitor_open_app_dialog_title, applicationLabel));
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1);
        layoutParams.setTitle("open_app_prompt");
        layoutParams.format = -2;
        layoutParams.type = 2012;
        layoutParams.flags = 296;
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
        this.f8807d.getSize(new Point());
        layoutParams.gravity = MaterialCardView.CHECKED_ICON_GRAVITY_TOP_START;
        ((WindowManager) context.getSystemService("window")).addView(this.f8810g, layoutParams);
    }

    private void n() {
        AlertDialog alertDialog = this.f8809f;
        if (alertDialog == null || !alertDialog.isShowing()) {
            ContextWrapper.updateDisplay(this.f8806c);
            AlertDialog a2 = new AlertDialog.Builder(this.f8806c, com.zte.extres.R.style.Theme_ZTE_Light_Dialog_Alert).d(R.string.phone_open_app_dialog_message).l(R.string.phone_open_app_dialog_title).c(true).i(R.string.monitor_dialog_cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.projection.g
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    ProjectionDialogController.v(dialogInterface, i2);
                }
            }).f(R.string.monitor_dialog_open_app, new DialogInterface.OnClickListener() { // from class: cn.nubia.projection.h
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    ProjectionDialogController.w(dialogInterface, i2);
                }
            }).h(new DialogInterface.OnDismissListener() { // from class: cn.nubia.projection.i
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    ProjectionDialogController.this.x(dialogInterface);
                }
            }).a();
            this.f8809f = a2;
            a2.getWindow().setType(2008);
            this.f8809f.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            this.f8809f.show();
            GameAssistDialog.f(this.f8809f.getWindow());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p(Context context, View view) {
        z(context);
        Settings.Global.putInt(this.f8806c.getContentResolver(), "monitor_input_prompt_hide", 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q(Context context, View view) {
        z(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(Context context, View view) {
        z(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s(Context context, View view) {
        A(context, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t(Context context, View view) {
        A(context, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u(Context context, View view) {
        A(context, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void v(DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        ProjectionManager.o().J(7, 0, 0, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void w(DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        ProjectionManager.o().J(7, 1, 0, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x(DialogInterface dialogInterface) {
        this.f8809f = null;
        ProjectionManager.o().J(7, 0, 0, null);
    }

    private void z(Context context) {
        B(this.f8808e, context);
        this.f8808e = null;
    }

    public void D(Context context, ProjectionUIController projectionUIController) {
        this.f8805b = projectionUIController;
        this.f8806c = context;
        this.f8804a = (DisplayManager) context.getSystemService("display");
    }

    public void j() {
        View view = this.f8808e;
        if (view != null) {
            view.findViewById(R.id.iv_close).callOnClick();
        }
        AlertDialog alertDialog = this.f8809f;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.f8809f = null;
        }
        View view2 = this.f8810g;
        if (view2 != null) {
            view2.findViewById(R.id.iv_close).callOnClick();
        }
        MouseSensitivitySetting mouseSensitivitySetting = this.f8811h;
        if (mouseSensitivitySetting != null) {
            mouseSensitivitySetting.g();
            this.f8811h = null;
        }
    }

    protected void l() {
        Context o2 = o(2008);
        if (o2 != null) {
            j();
            MouseSensitivitySetting mouseSensitivitySetting = new MouseSensitivitySetting(this.f8806c, o2);
            this.f8811h = mouseSensitivitySetting;
            mouseSensitivitySetting.i();
        }
    }

    protected Context o(int i2) {
        ProjectionUIController projectionUIController = this.f8805b;
        int R = projectionUIController != null ? projectionUIController.R() : 0;
        if (R <= 0) {
            PLog.e("not app mirror mode");
            return null;
        }
        Display display = this.f8804a.getDisplay(R);
        this.f8807d = display;
        Context context = this.f8806c;
        if (context == null || display == null || context.createDisplayContext(display) == null) {
            PLog.e(" create display context fail ! displayId: " + R);
            return null;
        }
        PLog.a("initAppMirrorDisplay: " + i2);
        Context createWindowContext = this.f8806c.createDisplayContext(this.f8807d).createWindowContext(i2, null);
        if (createWindowContext != null) {
            return createWindowContext;
        }
        PLog.e("create window context fail ! displayId: " + R);
        return null;
    }

    public void y(String str, String str2) {
        PLog.a("cmd:" + str + ",param:" + str2);
        if ("projection_enter".equals(str)) {
            this.f8805b.L0();
            return;
        }
        Context o2 = o(2012);
        if (o2 == null) {
            return;
        }
        if ("projection_input_prompt".equals(str)) {
            k(str2, o2);
        } else if ("projection_open_app_1".equals(str)) {
            n();
        } else if ("projection_open_app_2".equals(str)) {
            m(str2, o2);
        }
    }
}
