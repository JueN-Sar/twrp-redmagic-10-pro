package androidx.legacy.app;

import android.R;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;

@Deprecated
/* loaded from: classes.dex */
public class ActionBarDrawerToggle implements DrawerLayout.DrawerListener {

    /* renamed from: g, reason: collision with root package name */
    private static final int[] f4251g = {R.attr.homeAsUpIndicator};

    /* renamed from: a, reason: collision with root package name */
    final Activity f4252a;

    /* renamed from: b, reason: collision with root package name */
    private final Delegate f4253b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f4254c;

    /* renamed from: d, reason: collision with root package name */
    private SlideDrawable f4255d;

    /* renamed from: e, reason: collision with root package name */
    private final int f4256e;

    /* renamed from: f, reason: collision with root package name */
    private final int f4257f;

    @Deprecated
    public interface Delegate {
        void a(int i2);
    }

    @Deprecated
    public interface DelegateProvider {
    }

    private static class SetIndicatorInfo {
    }

    private class SlideDrawable extends InsetDrawable implements Drawable.Callback {

        /* renamed from: c, reason: collision with root package name */
        private final boolean f4258c;

        /* renamed from: h, reason: collision with root package name */
        private final Rect f4259h;

        /* renamed from: i, reason: collision with root package name */
        private float f4260i;

        /* renamed from: j, reason: collision with root package name */
        private float f4261j;

        /* renamed from: k, reason: collision with root package name */
        final /* synthetic */ ActionBarDrawerToggle f4262k;

        public float a() {
            return this.f4260i;
        }

        public void b(float f2) {
            this.f4260i = f2;
            invalidateSelf();
        }

        @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            copyBounds(this.f4259h);
            canvas.save();
            boolean z = ViewCompat.v(this.f4262k.f4252a.getWindow().getDecorView()) == 1;
            int i2 = z ? -1 : 1;
            float width = this.f4259h.width();
            canvas.translate((-this.f4261j) * width * this.f4260i * i2, 0.0f);
            if (z && !this.f4258c) {
                canvas.translate(width, 0.0f);
                canvas.scale(-1.0f, 1.0f);
            }
            super.draw(canvas);
            canvas.restore();
        }
    }

    private void e(int i2) {
        Delegate delegate = this.f4253b;
        if (delegate != null) {
            delegate.a(i2);
            return;
        }
        ActionBar actionBar = this.f4252a.getActionBar();
        if (actionBar != null) {
            actionBar.setHomeActionContentDescription(i2);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void a(View view) {
        this.f4255d.b(1.0f);
        if (this.f4254c) {
            e(this.f4257f);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void b(View view) {
        this.f4255d.b(0.0f);
        if (this.f4254c) {
            e(this.f4256e);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void c(int i2) {
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void d(View view, float f2) {
        float a2 = this.f4255d.a();
        this.f4255d.b(f2 > 0.5f ? Math.max(a2, Math.max(0.0f, f2 - 0.5f) * 2.0f) : Math.min(a2, f2 * 2.0f));
    }
}
