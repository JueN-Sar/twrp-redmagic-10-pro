package com.google.android.material.dialog;

import android.content.DialogInterface;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;
import android.widget.ListAdapter;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.shape.MaterialShapeDrawable;

/* loaded from: classes.dex */
public class MaterialAlertDialogBuilder extends AlertDialog.Builder {

    /* renamed from: e, reason: collision with root package name */
    private static final int f14556e = R.attr.alertDialogStyle;

    /* renamed from: f, reason: collision with root package name */
    private static final int f14557f = R.style.MaterialAlertDialog_MaterialComponents;

    /* renamed from: g, reason: collision with root package name */
    private static final int f14558g = R.attr.materialAlertDialogTheme;

    /* renamed from: c, reason: collision with root package name */
    private Drawable f14559c;

    /* renamed from: d, reason: collision with root package name */
    private final Rect f14560d;

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public AlertDialog a() {
        AlertDialog a2 = super.a();
        Window window = a2.getWindow();
        View decorView = window.getDecorView();
        Drawable drawable = this.f14559c;
        if (drawable instanceof MaterialShapeDrawable) {
            ((MaterialShapeDrawable) drawable).Z(ViewCompat.r(decorView));
        }
        window.setBackgroundDrawable(MaterialDialogs.a(this.f14559c, this.f14560d));
        decorView.setOnTouchListener(new InsetDialogOnTouchListener(a2, this.f14560d));
        return a2;
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    /* renamed from: o, reason: merged with bridge method [inline-methods] */
    public MaterialAlertDialogBuilder c(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
        return (MaterialAlertDialogBuilder) super.c(listAdapter, onClickListener);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    /* renamed from: p, reason: merged with bridge method [inline-methods] */
    public MaterialAlertDialogBuilder d(View view) {
        return (MaterialAlertDialogBuilder) super.d(view);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    /* renamed from: q, reason: merged with bridge method [inline-methods] */
    public MaterialAlertDialogBuilder e(Drawable drawable) {
        return (MaterialAlertDialogBuilder) super.e(drawable);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    /* renamed from: r, reason: merged with bridge method [inline-methods] */
    public MaterialAlertDialogBuilder f(CharSequence charSequence) {
        return (MaterialAlertDialogBuilder) super.f(charSequence);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    /* renamed from: s, reason: merged with bridge method [inline-methods] */
    public MaterialAlertDialogBuilder g(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
        return (MaterialAlertDialogBuilder) super.g(charSequenceArr, zArr, onMultiChoiceClickListener);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    /* renamed from: t, reason: merged with bridge method [inline-methods] */
    public MaterialAlertDialogBuilder h(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        return (MaterialAlertDialogBuilder) super.h(charSequence, onClickListener);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    /* renamed from: u, reason: merged with bridge method [inline-methods] */
    public MaterialAlertDialogBuilder i(DialogInterface.OnKeyListener onKeyListener) {
        return (MaterialAlertDialogBuilder) super.i(onKeyListener);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    /* renamed from: v, reason: merged with bridge method [inline-methods] */
    public MaterialAlertDialogBuilder j(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        return (MaterialAlertDialogBuilder) super.j(charSequence, onClickListener);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    /* renamed from: w, reason: merged with bridge method [inline-methods] */
    public MaterialAlertDialogBuilder k(ListAdapter listAdapter, int i2, DialogInterface.OnClickListener onClickListener) {
        return (MaterialAlertDialogBuilder) super.k(listAdapter, i2, onClickListener);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    /* renamed from: x, reason: merged with bridge method [inline-methods] */
    public MaterialAlertDialogBuilder l(CharSequence[] charSequenceArr, int i2, DialogInterface.OnClickListener onClickListener) {
        return (MaterialAlertDialogBuilder) super.l(charSequenceArr, i2, onClickListener);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    /* renamed from: y, reason: merged with bridge method [inline-methods] */
    public MaterialAlertDialogBuilder m(CharSequence charSequence) {
        return (MaterialAlertDialogBuilder) super.m(charSequence);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    /* renamed from: z, reason: merged with bridge method [inline-methods] */
    public MaterialAlertDialogBuilder n(View view) {
        return (MaterialAlertDialogBuilder) super.n(view);
    }
}
