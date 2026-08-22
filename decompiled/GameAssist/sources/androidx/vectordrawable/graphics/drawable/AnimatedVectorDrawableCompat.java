package androidx.vectordrawable.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.annotation.RequiresApi;
import androidx.collection.ArrayMap;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class AnimatedVectorDrawableCompat extends VectorDrawableCommon implements Animatable2Compat {

    /* renamed from: h, reason: collision with root package name */
    private AnimatedVectorDrawableCompatState f5634h;

    /* renamed from: i, reason: collision with root package name */
    private Context f5635i;

    /* renamed from: j, reason: collision with root package name */
    private android.animation.ArgbEvaluator f5636j;

    /* renamed from: k, reason: collision with root package name */
    AnimatedVectorDrawableDelegateState f5637k;

    /* renamed from: l, reason: collision with root package name */
    private Animator.AnimatorListener f5638l;

    /* renamed from: m, reason: collision with root package name */
    ArrayList f5639m;

    /* renamed from: n, reason: collision with root package name */
    final Drawable.Callback f5640n;

    private static class AnimatedVectorDrawableCompatState extends Drawable.ConstantState {

        /* renamed from: a, reason: collision with root package name */
        int f5643a;

        /* renamed from: b, reason: collision with root package name */
        VectorDrawableCompat f5644b;

        /* renamed from: c, reason: collision with root package name */
        AnimatorSet f5645c;

        /* renamed from: d, reason: collision with root package name */
        ArrayList f5646d;

        /* renamed from: e, reason: collision with root package name */
        ArrayMap f5647e;

        public AnimatedVectorDrawableCompatState(Context context, AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState, Drawable.Callback callback, Resources resources) {
            if (animatedVectorDrawableCompatState != null) {
                this.f5643a = animatedVectorDrawableCompatState.f5643a;
                VectorDrawableCompat vectorDrawableCompat = animatedVectorDrawableCompatState.f5644b;
                if (vectorDrawableCompat != null) {
                    Drawable.ConstantState constantState = vectorDrawableCompat.getConstantState();
                    if (resources != null) {
                        this.f5644b = (VectorDrawableCompat) constantState.newDrawable(resources);
                    } else {
                        this.f5644b = (VectorDrawableCompat) constantState.newDrawable();
                    }
                    VectorDrawableCompat vectorDrawableCompat2 = (VectorDrawableCompat) this.f5644b.mutate();
                    this.f5644b = vectorDrawableCompat2;
                    vectorDrawableCompat2.setCallback(callback);
                    this.f5644b.setBounds(animatedVectorDrawableCompatState.f5644b.getBounds());
                    this.f5644b.h(false);
                }
                ArrayList arrayList = animatedVectorDrawableCompatState.f5646d;
                if (arrayList != null) {
                    int size = arrayList.size();
                    this.f5646d = new ArrayList(size);
                    this.f5647e = new ArrayMap(size);
                    for (int i2 = 0; i2 < size; i2++) {
                        Animator animator = (Animator) animatedVectorDrawableCompatState.f5646d.get(i2);
                        Animator clone = animator.clone();
                        String str = (String) animatedVectorDrawableCompatState.f5647e.get(animator);
                        clone.setTarget(this.f5644b.d(str));
                        this.f5646d.add(clone);
                        this.f5647e.put(clone, str);
                    }
                    a();
                }
            }
        }

        public void a() {
            if (this.f5645c == null) {
                this.f5645c = new AnimatorSet();
            }
            this.f5645c.playTogether(this.f5646d);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f5643a;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }
    }

    AnimatedVectorDrawableCompat() {
        this(null, null, null);
    }

    public static AnimatedVectorDrawableCompat a(Context context, int i2) {
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat(context);
        Drawable e2 = ResourcesCompat.e(context.getResources(), i2, context.getTheme());
        animatedVectorDrawableCompat.f5653c = e2;
        e2.setCallback(animatedVectorDrawableCompat.f5640n);
        animatedVectorDrawableCompat.f5637k = new AnimatedVectorDrawableDelegateState(animatedVectorDrawableCompat.f5653c.getConstantState());
        return animatedVectorDrawableCompat;
    }

    public static AnimatedVectorDrawableCompat b(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat(context);
        animatedVectorDrawableCompat.inflate(resources, xmlPullParser, attributeSet, theme);
        return animatedVectorDrawableCompat;
    }

    private static void d(AnimatedVectorDrawable animatedVectorDrawable, Animatable2Compat.AnimationCallback animationCallback) {
        animatedVectorDrawable.registerAnimationCallback(animationCallback.a());
    }

    private void e() {
        Animator.AnimatorListener animatorListener = this.f5638l;
        if (animatorListener != null) {
            this.f5634h.f5645c.removeListener(animatorListener);
            this.f5638l = null;
        }
    }

    private void f(String str, Animator animator) {
        animator.setTarget(this.f5634h.f5644b.d(str));
        AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState = this.f5634h;
        if (animatedVectorDrawableCompatState.f5646d == null) {
            animatedVectorDrawableCompatState.f5646d = new ArrayList();
            this.f5634h.f5647e = new ArrayMap();
        }
        this.f5634h.f5646d.add(animator);
        this.f5634h.f5647e.put(animator, str);
    }

    private static boolean h(AnimatedVectorDrawable animatedVectorDrawable, Animatable2Compat.AnimationCallback animationCallback) {
        return animatedVectorDrawable.unregisterAnimationCallback(animationCallback.a());
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            DrawableCompat.a(drawable, theme);
        }
    }

    public void c(Animatable2Compat.AnimationCallback animationCallback) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            d((AnimatedVectorDrawable) drawable, animationCallback);
            return;
        }
        if (animationCallback == null) {
            return;
        }
        if (this.f5639m == null) {
            this.f5639m = new ArrayList();
        }
        if (this.f5639m.contains(animationCallback)) {
            return;
        }
        this.f5639m.add(animationCallback);
        if (this.f5638l == null) {
            this.f5638l = new AnimatorListenerAdapter() { // from class: androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    ArrayList arrayList = new ArrayList(AnimatedVectorDrawableCompat.this.f5639m);
                    int size = arrayList.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((Animatable2Compat.AnimationCallback) arrayList.get(i2)).b(AnimatedVectorDrawableCompat.this);
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    ArrayList arrayList = new ArrayList(AnimatedVectorDrawableCompat.this.f5639m);
                    int size = arrayList.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((Animatable2Compat.AnimationCallback) arrayList.get(i2)).c(AnimatedVectorDrawableCompat.this);
                    }
                }
            };
        }
        this.f5634h.f5645c.addListener(this.f5638l);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            return DrawableCompat.b(drawable);
        }
        return false;
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        this.f5634h.f5644b.draw(canvas);
        if (this.f5634h.f5645c.isStarted()) {
            invalidateSelf();
        }
    }

    public boolean g(Animatable2Compat.AnimationCallback animationCallback) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            h((AnimatedVectorDrawable) drawable, animationCallback);
        }
        ArrayList arrayList = this.f5639m;
        if (arrayList == null || animationCallback == null) {
            return false;
        }
        boolean remove = arrayList.remove(animationCallback);
        if (this.f5639m.size() == 0) {
            e();
        }
        return remove;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f5653c;
        return drawable != null ? DrawableCompat.d(drawable) : this.f5634h.f5644b.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        return this.f5634h.f5643a | super.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f5653c;
        return drawable != null ? DrawableCompat.e(drawable) : this.f5634h.f5644b.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.f5653c != null) {
            return new AnimatedVectorDrawableDelegateState(this.f5653c.getConstantState());
        }
        return null;
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f5653c;
        return drawable != null ? drawable.getIntrinsicHeight() : this.f5634h.f5644b.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f5653c;
        return drawable != null ? drawable.getIntrinsicWidth() : this.f5634h.f5644b.getIntrinsicWidth();
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f5653c;
        return drawable != null ? drawable.getOpacity() : this.f5634h.f5644b.getOpacity();
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            DrawableCompat.g(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if ("animated-vector".equals(name)) {
                    TypedArray s2 = TypedArrayUtils.s(resources, theme, attributeSet, AndroidResources.f5624e);
                    int resourceId = s2.getResourceId(0, 0);
                    if (resourceId != 0) {
                        VectorDrawableCompat b2 = VectorDrawableCompat.b(resources, resourceId, theme);
                        b2.h(false);
                        b2.setCallback(this.f5640n);
                        VectorDrawableCompat vectorDrawableCompat = this.f5634h.f5644b;
                        if (vectorDrawableCompat != null) {
                            vectorDrawableCompat.setCallback(null);
                        }
                        this.f5634h.f5644b = b2;
                    }
                    s2.recycle();
                } else if ("target".equals(name)) {
                    TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, AndroidResources.f5625f);
                    String string = obtainAttributes.getString(0);
                    int resourceId2 = obtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.f5635i;
                        if (context == null) {
                            obtainAttributes.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                        f(string, AnimatorInflaterCompat.a(context, resourceId2));
                    }
                    obtainAttributes.recycle();
                } else {
                    continue;
                }
            }
            eventType = xmlPullParser.next();
        }
        this.f5634h.a();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f5653c;
        return drawable != null ? DrawableCompat.h(drawable) : this.f5634h.f5644b.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        Drawable drawable = this.f5653c;
        return drawable != null ? ((AnimatedVectorDrawable) drawable).isRunning() : this.f5634h.f5645c.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        Drawable drawable = this.f5653c;
        return drawable != null ? drawable.isStateful() : this.f5634h.f5644b.isStateful();
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.f5634h.f5644b.setBounds(rect);
        }
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i2) {
        Drawable drawable = this.f5653c;
        return drawable != null ? drawable.setLevel(i2) : this.f5634h.f5644b.setLevel(i2);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f5653c;
        return drawable != null ? drawable.setState(iArr) : this.f5634h.f5644b.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            drawable.setAlpha(i2);
        } else {
            this.f5634h.f5644b.setAlpha(i2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            DrawableCompat.j(drawable, z);
        } else {
            this.f5634h.f5644b.setAutoMirrored(z);
        }
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i2) {
        super.setChangingConfigurations(i2);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i2, PorterDuff.Mode mode) {
        super.setColorFilter(i2, mode);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i2, int i3, int i4, int i5) {
        super.setHotspotBounds(i2, i3, i4, i5);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i2) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            DrawableCompat.n(drawable, i2);
        } else {
            this.f5634h.f5644b.setTint(i2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            DrawableCompat.o(drawable, colorStateList);
        } else {
            this.f5634h.f5644b.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            DrawableCompat.p(drawable, mode);
        } else {
            this.f5634h.f5644b.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        this.f5634h.f5644b.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
        } else {
            if (this.f5634h.f5645c.isStarted()) {
                return;
            }
            this.f5634h.f5645c.start();
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.f5634h.f5645c.end();
        }
    }

    private AnimatedVectorDrawableCompat(Context context) {
        this(context, null, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f5634h.f5644b.setColorFilter(colorFilter);
        }
    }

    @RequiresApi
    private static class AnimatedVectorDrawableDelegateState extends Drawable.ConstantState {

        /* renamed from: a, reason: collision with root package name */
        private final Drawable.ConstantState f5648a;

        public AnimatedVectorDrawableDelegateState(Drawable.ConstantState constantState) {
            this.f5648a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f5648a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f5648a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat();
            Drawable newDrawable = this.f5648a.newDrawable();
            animatedVectorDrawableCompat.f5653c = newDrawable;
            newDrawable.setCallback(animatedVectorDrawableCompat.f5640n);
            return animatedVectorDrawableCompat;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat();
            Drawable newDrawable = this.f5648a.newDrawable(resources);
            animatedVectorDrawableCompat.f5653c = newDrawable;
            newDrawable.setCallback(animatedVectorDrawableCompat.f5640n);
            return animatedVectorDrawableCompat;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat();
            Drawable newDrawable = this.f5648a.newDrawable(resources, theme);
            animatedVectorDrawableCompat.f5653c = newDrawable;
            newDrawable.setCallback(animatedVectorDrawableCompat.f5640n);
            return animatedVectorDrawableCompat;
        }
    }

    private AnimatedVectorDrawableCompat(Context context, AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState, Resources resources) {
        this.f5636j = null;
        this.f5638l = null;
        this.f5639m = null;
        Drawable.Callback callback = new Drawable.Callback() { // from class: androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat.1
            @Override // android.graphics.drawable.Drawable.Callback
            public void invalidateDrawable(Drawable drawable) {
                AnimatedVectorDrawableCompat.this.invalidateSelf();
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
                AnimatedVectorDrawableCompat.this.scheduleSelf(runnable, j2);
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
                AnimatedVectorDrawableCompat.this.unscheduleSelf(runnable);
            }
        };
        this.f5640n = callback;
        this.f5635i = context;
        if (animatedVectorDrawableCompatState != null) {
            this.f5634h = animatedVectorDrawableCompatState;
        } else {
            this.f5634h = new AnimatedVectorDrawableCompatState(context, animatedVectorDrawableCompatState, callback, resources);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        inflate(resources, xmlPullParser, attributeSet, null);
    }
}
