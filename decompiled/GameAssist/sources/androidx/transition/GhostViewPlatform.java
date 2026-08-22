package androidx.transition;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
class GhostViewPlatform implements GhostView {

    /* renamed from: c, reason: collision with root package name */
    private final View f5491c;

    @Override // androidx.transition.GhostView
    public void a(ViewGroup viewGroup, View view) {
    }

    @Override // androidx.transition.GhostView
    public void setVisibility(int i2) {
        this.f5491c.setVisibility(i2);
    }
}
