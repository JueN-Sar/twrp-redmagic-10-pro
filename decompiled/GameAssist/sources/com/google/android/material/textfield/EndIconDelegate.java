package com.google.android.material.textfield;

import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.internal.CheckableImageButton;

/* loaded from: classes.dex */
abstract class EndIconDelegate {

    /* renamed from: a, reason: collision with root package name */
    final TextInputLayout f15406a;

    /* renamed from: b, reason: collision with root package name */
    final EndCompoundLayout f15407b;

    /* renamed from: c, reason: collision with root package name */
    final Context f15408c;

    /* renamed from: d, reason: collision with root package name */
    final CheckableImageButton f15409d;

    EndIconDelegate(EndCompoundLayout endCompoundLayout) {
        this.f15406a = endCompoundLayout.textInputLayout;
        this.f15407b = endCompoundLayout;
        this.f15408c = endCompoundLayout.getContext();
        this.f15409d = endCompoundLayout.r();
    }

    void a(Editable editable) {
    }

    void b(CharSequence charSequence, int i2, int i3, int i4) {
    }

    int c() {
        return 0;
    }

    int d() {
        return 0;
    }

    View.OnFocusChangeListener e() {
        return null;
    }

    View.OnClickListener f() {
        return null;
    }

    View.OnFocusChangeListener g() {
        return null;
    }

    AccessibilityManagerCompat.TouchExplorationStateChangeListener h() {
        return null;
    }

    boolean i(int i2) {
        return true;
    }

    boolean j() {
        return false;
    }

    boolean k() {
        return false;
    }

    boolean l() {
        return false;
    }

    boolean m() {
        return false;
    }

    void n(EditText editText) {
    }

    void o(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }

    void p(View view, AccessibilityEvent accessibilityEvent) {
    }

    void q(boolean z) {
    }

    final void r() {
        this.f15407b.L(false);
    }

    void s() {
    }

    boolean t() {
        return false;
    }

    void u() {
    }
}
