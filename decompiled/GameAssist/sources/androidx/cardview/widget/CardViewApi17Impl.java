package androidx.cardview.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.RoundRectDrawableWithShadow;

@RequiresApi
/* loaded from: classes.dex */
class CardViewApi17Impl extends CardViewBaseImpl {
    @Override // androidx.cardview.widget.CardViewBaseImpl, androidx.cardview.widget.CardViewImpl
    public void l() {
        RoundRectDrawableWithShadow.f1153r = new RoundRectDrawableWithShadow.RoundRectHelper() { // from class: androidx.cardview.widget.CardViewApi17Impl.1
            @Override // androidx.cardview.widget.RoundRectDrawableWithShadow.RoundRectHelper
            public void a(Canvas canvas, RectF rectF, float f2, Paint paint) {
                canvas.drawRoundRect(rectF, f2, f2, paint);
            }
        };
    }
}
