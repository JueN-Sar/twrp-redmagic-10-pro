package androidx.appcompat.app;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.app.ActionBar;

/* loaded from: classes.dex */
class NavItemSelectedListener implements AdapterView.OnItemSelectedListener {

    /* renamed from: c, reason: collision with root package name */
    private final ActionBar.OnNavigationListener f293c;

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView adapterView, View view, int i2, long j2) {
        ActionBar.OnNavigationListener onNavigationListener = this.f293c;
        if (onNavigationListener != null) {
            onNavigationListener.onNavigationItemSelected(i2, j2);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView adapterView) {
    }
}
