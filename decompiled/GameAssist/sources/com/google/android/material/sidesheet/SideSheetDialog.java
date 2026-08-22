package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.material.R;

/* loaded from: classes.dex */
public class SideSheetDialog extends SheetDialog<SideSheetCallback> {
    private static final int v = R.attr.sideSheetDialogTheme;
    private static final int w = R.style.Theme_Material3_Light_SideSheetDialog;

    @Override // com.google.android.material.sidesheet.SheetDialog
    /* renamed from: E, reason: merged with bridge method [inline-methods] */
    public SideSheetBehavior r() {
        Sheet r2 = super.r();
        if (r2 instanceof SideSheetBehavior) {
            return (SideSheetBehavior) r2;
        }
        throw new IllegalStateException("The view is not associated with SideSheetBehavior");
    }

    @Override // com.google.android.material.sidesheet.SheetDialog, android.app.Dialog, android.content.DialogInterface
    public /* bridge */ /* synthetic */ void cancel() {
        super.cancel();
    }

    @Override // com.google.android.material.sidesheet.SheetDialog
    void m(Sheet sheet) {
        sheet.b(new SideSheetCallback() { // from class: com.google.android.material.sidesheet.SideSheetDialog.1
            @Override // com.google.android.material.sidesheet.SheetCallback
            public void a(View view, int i2) {
                if (i2 == 5) {
                    SideSheetDialog.this.cancel();
                }
            }

            @Override // com.google.android.material.sidesheet.SheetCallback
            public void b(View view, float f2) {
            }
        });
    }

    @Override // com.google.android.material.sidesheet.SheetDialog, android.app.Dialog, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // com.google.android.material.sidesheet.SheetDialog, android.app.Dialog, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // com.google.android.material.sidesheet.SheetDialog
    Sheet s(FrameLayout frameLayout) {
        return SideSheetBehavior.e0(frameLayout);
    }

    @Override // com.google.android.material.sidesheet.SheetDialog, android.app.Dialog
    public /* bridge */ /* synthetic */ void setCancelable(boolean z) {
        super.setCancelable(z);
    }

    @Override // com.google.android.material.sidesheet.SheetDialog, android.app.Dialog
    public /* bridge */ /* synthetic */ void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
    }

    @Override // com.google.android.material.sidesheet.SheetDialog, androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public /* bridge */ /* synthetic */ void setContentView(int i2) {
        super.setContentView(i2);
    }

    @Override // com.google.android.material.sidesheet.SheetDialog
    int v() {
        return R.id.m3_side_sheet;
    }

    @Override // com.google.android.material.sidesheet.SheetDialog
    int w() {
        return R.layout.m3_side_sheet_dialog;
    }

    @Override // com.google.android.material.sidesheet.SheetDialog
    int y() {
        return 3;
    }

    @Override // com.google.android.material.sidesheet.SheetDialog, androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public /* bridge */ /* synthetic */ void setContentView(View view) {
        super.setContentView(view);
    }

    @Override // com.google.android.material.sidesheet.SheetDialog, androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public /* bridge */ /* synthetic */ void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
    }
}
