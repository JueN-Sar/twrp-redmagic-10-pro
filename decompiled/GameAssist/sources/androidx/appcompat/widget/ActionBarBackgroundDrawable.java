package androidx.appcompat.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
class ActionBarBackgroundDrawable extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    final ActionBarContainer f639a;

    @RequiresApi
    private static class Api21Impl {
        public static void a(Drawable drawable, Outline outline) {
            drawable.getOutline(outline);
        }
    }

    public ActionBarBackgroundDrawable(ActionBarContainer actionBarContainer) {
        this.f639a = actionBarContainer;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        ActionBarContainer actionBarContainer = this.f639a;
        if (actionBarContainer.mIsSplit) {
            Drawable drawable = actionBarContainer.mSplitBackground;
            if (drawable != null) {
                drawable.draw(canvas);
                return;
            }
            return;
        }
        Drawable drawable2 = actionBarContainer.mBackground;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        ActionBarContainer actionBarContainer2 = this.f639a;
        Drawable drawable3 = actionBarContainer2.mStackedBackground;
        if (drawable3 == null || !actionBarContainer2.mIsStacked) {
            return;
        }
        drawable3.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        ActionBarContainer actionBarContainer = this.f639a;
        if (actionBarContainer.mIsSplit) {
            if (actionBarContainer.mSplitBackground != null) {
                Api21Impl.a(actionBarContainer.mBackground, outline);
            }
        } else {
            Drawable drawable = actionBarContainer.mBackground;
            if (drawable != null) {
                Api21Impl.a(drawable, outline);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
