package androidx.core.graphics.drawable;

import android.graphics.Outline;
import android.graphics.Rect;
import android.view.Gravity;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
class RoundedBitmapDrawable21 extends RoundedBitmapDrawable {
    @Override // androidx.core.graphics.drawable.RoundedBitmapDrawable
    void b(int i2, int i3, int i4, Rect rect, Rect rect2) {
        Gravity.apply(i2, i3, i4, rect, rect2, 0);
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        e();
        outline.setRoundRect(this.f2989g, a());
    }
}
