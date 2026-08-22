package com.zte.gameassist.lowsugar.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.dialog.GameAssistDialog;
import com.zte.gameassist.lowsugar.R;
import com.zte.gameassist.lowsugar.provider.LowSugarColumn;
import com.zte.gameassist.lowsugar.utils.LowSugarUtils;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.WechatHelper;
import com.zte.mifavor.widget.AlertDialog;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class LowSugarItem extends LinearLayout implements RotationMgr.Callback {
    private static final String TAG = "LowSugarItem";
    private Map<String, Drawable> iconCache;
    private Dialog mDialog;
    private long mId;
    private TextView mLowSugarDateView;
    private ImageView mLowSugarDeleteView;
    private ImageView mLowSugarIconView;
    private TextView mLowSugarTimeView;
    private LowSugarMarqueeText mLowSugarTitleView;

    public LowSugarItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.iconCache = new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.mId > 0) {
            getContext().getContentResolver().delete(LowSugarColumn.f16922a, "_id=?", new String[]{Long.toString(this.mId)});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(View view) {
        m(new Runnable() { // from class: com.zte.gameassist.lowsugar.ui.b
            @Override // java.lang.Runnable
            public final void run() {
                LowSugarItem.this.f();
            }
        });
    }

    private void k() {
        ImageView imageView = this.mLowSugarDeleteView;
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.zte.gameassist.lowsugar.ui.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LowSugarItem.this.g(view);
                }
            });
        }
    }

    private void l(String str, boolean z) {
        if (TextUtils.isEmpty(str) || !z) {
            this.mLowSugarIconView.setImageResource(R.drawable.low_sugar__uninstall_record_icon);
            return;
        }
        Drawable drawable = this.iconCache.get(str);
        if (drawable == null) {
            try {
                drawable = WechatHelper.i(str) ? WechatHelper.a().c(str, true) : getContext().getPackageManager().getApplicationIcon(str);
                this.iconCache.put(str, drawable);
            } catch (PackageManager.NameNotFoundException unused) {
                GaLog.a(TAG, "setIconView getIcon exception and packageName = " + str);
            }
        }
        ImageView imageView = this.mLowSugarIconView;
        if (imageView != null) {
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
            } else {
                imageView.setImageResource(R.drawable.low_sugar__uninstall_record_icon);
            }
        }
    }

    private void m(final Runnable runnable) {
        Dialog dialog = this.mDialog;
        if ((dialog == null || !dialog.isShowing()) && Settings.Global.getInt(getContext().getContentResolver(), "nubia_low_sugar_gameplay_pkg_open", 0) == 1) {
            AlertDialog a2 = new AlertDialog.Builder(getContext(), com.zte.extres.R.style.Theme_ZTE_Light_Dialog_Alert).m(getContext().getString(com.zte.gameassist.common.R.string.dialog_default_title)).c(true).d(R.string.ic_qs_low_sugar_delete_propose_message).i(com.zte.gameassist.common.R.string.single_ok, new DialogInterface.OnClickListener() { // from class: com.zte.gameassist.lowsugar.ui.c
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    runnable.run();
                }
            }).f(com.zte.gameassist.common.R.string.single_cancel, new DialogInterface.OnClickListener() { // from class: com.zte.gameassist.lowsugar.ui.d
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                }
            }).a();
            this.mDialog = a2;
            Window window = a2.getWindow();
            if (window != null) {
                window.setType(2008);
                window.setBackgroundDrawable(new ColorDrawable(0));
                window.getDecorView().setSystemUiVisibility(6);
            }
            this.mDialog.show();
            GameAssistDialog.f(this.mDialog.getWindow());
        }
    }

    private void setDateTimeView(long j2) {
        TextView textView = this.mLowSugarDateView;
        if (textView != null) {
            textView.setText(LowSugarUtils.i(j2));
        }
        if (this.mLowSugarTimeView != null) {
            if (LowSugarUtils.r(j2)) {
                this.mLowSugarTimeView.setText(LowSugarUtils.m(j2));
            } else {
                this.mLowSugarTimeView.setText(LowSugarUtils.k(j2));
            }
        }
    }

    private void setTitleView(String str) {
        LowSugarMarqueeText lowSugarMarqueeText = this.mLowSugarTitleView;
        if (lowSugarMarqueeText != null) {
            lowSugarMarqueeText.setText(str);
        }
    }

    public void e(long j2, String str, String str2, long j3, boolean z) {
        this.mId = j2;
        setTitleView(str2);
        setDateTimeView(j3);
        l(str, z);
    }

    public void j() {
        Dialog dialog = this.mDialog;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.mDialog.dismiss();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        RotationMgr.e(getContext()).c(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        RotationMgr.e(getContext()).p(this);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mLowSugarIconView = (ImageView) findViewById(R.id.low_sugar_event_icon);
        this.mLowSugarTitleView = (LowSugarMarqueeText) findViewById(R.id.low_sugar_title);
        this.mLowSugarDeleteView = (ImageView) findViewById(R.id.low_sugar_delete);
        this.mLowSugarTimeView = (TextView) findViewById(R.id.low_sugar_time);
        this.mLowSugarDateView = (TextView) findViewById(R.id.low_sugar_date);
        k();
    }

    @Override // com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        Dialog dialog = this.mDialog;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        GameAssistDialog.f(this.mDialog.getWindow());
    }
}
