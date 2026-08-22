package androidx.appcompat.graphics.drawable;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;
import androidx.appcompat.graphics.drawable.DrawableContainerCompat;
import androidx.appcompat.graphics.drawable.StateListDrawableCompat;
import androidx.appcompat.resources.Compatibility;
import androidx.appcompat.resources.R;
import androidx.appcompat.widget.ResourceManagerInternal;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.drawable.TintAwareDrawable;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.zte.distbus.basetransfer.Constants;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class AnimatedStateListDrawableCompat extends StateListDrawableCompat implements TintAwareDrawable {
    private AnimatedStateListState u;
    private Transition v;
    private int w;
    private int x;
    private boolean y;

    private static class AnimatableTransition extends Transition {

        /* renamed from: a, reason: collision with root package name */
        private final Animatable f364a;

        AnimatableTransition(Animatable animatable) {
            super();
            this.f364a = animatable;
        }

        @Override // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public void c() {
            this.f364a.start();
        }

        @Override // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public void d() {
            this.f364a.stop();
        }
    }

    static class AnimatedStateListState extends StateListDrawableCompat.StateListState {
        LongSparseArray K;
        SparseArrayCompat L;

        AnimatedStateListState(AnimatedStateListState animatedStateListState, AnimatedStateListDrawableCompat animatedStateListDrawableCompat, Resources resources) {
            super(animatedStateListState, animatedStateListDrawableCompat, resources);
            if (animatedStateListState != null) {
                this.K = animatedStateListState.K;
                this.L = animatedStateListState.L;
            } else {
                this.K = new LongSparseArray();
                this.L = new SparseArrayCompat();
            }
        }

        private static long F(int i2, int i3) {
            return i3 | (i2 << 32);
        }

        int D(int[] iArr, Drawable drawable, int i2) {
            int B = super.B(iArr, drawable);
            this.L.i(B, Integer.valueOf(i2));
            return B;
        }

        int E(int i2, int i3, Drawable drawable, boolean z) {
            int a2 = super.a(drawable);
            long F = F(i2, i3);
            long j2 = z ? 8589934592L : 0L;
            long j3 = a2;
            this.K.a(F, Long.valueOf(j3 | j2));
            if (z) {
                this.K.a(F(i3, i2), Long.valueOf(4294967296L | j3 | j2));
            }
            return a2;
        }

        int G(int i2) {
            if (i2 < 0) {
                return 0;
            }
            return ((Integer) this.L.f(i2, 0)).intValue();
        }

        int H(int[] iArr) {
            int C = super.C(iArr);
            return C >= 0 ? C : super.C(StateSet.WILD_CARD);
        }

        int I(int i2, int i3) {
            return (int) ((Long) this.K.g(F(i2, i3), -1L)).longValue();
        }

        boolean J(int i2, int i3) {
            return (((Long) this.K.g(F(i2, i3), -1L)).longValue() & 4294967296L) != 0;
        }

        boolean K(int i2, int i3) {
            return (((Long) this.K.g(F(i2, i3), -1L)).longValue() & 8589934592L) != 0;
        }

        @Override // androidx.appcompat.graphics.drawable.StateListDrawableCompat.StateListState, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new AnimatedStateListDrawableCompat(this, null);
        }

        @Override // androidx.appcompat.graphics.drawable.StateListDrawableCompat.StateListState, androidx.appcompat.graphics.drawable.DrawableContainerCompat.DrawableContainerState
        void t() {
            this.K = this.K.clone();
            this.L = this.L.clone();
        }

        @Override // androidx.appcompat.graphics.drawable.StateListDrawableCompat.StateListState, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new AnimatedStateListDrawableCompat(this, resources);
        }
    }

    private static class AnimatedVectorDrawableTransition extends Transition {

        /* renamed from: a, reason: collision with root package name */
        private final AnimatedVectorDrawableCompat f365a;

        AnimatedVectorDrawableTransition(AnimatedVectorDrawableCompat animatedVectorDrawableCompat) {
            super();
            this.f365a = animatedVectorDrawableCompat;
        }

        @Override // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public void c() {
            this.f365a.start();
        }

        @Override // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public void d() {
            this.f365a.stop();
        }
    }

    private static class AnimationDrawableTransition extends Transition {

        /* renamed from: a, reason: collision with root package name */
        private final ObjectAnimator f366a;

        /* renamed from: b, reason: collision with root package name */
        private final boolean f367b;

        AnimationDrawableTransition(AnimationDrawable animationDrawable, boolean z, boolean z2) {
            super();
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            int i2 = z ? numberOfFrames - 1 : 0;
            int i3 = z ? 0 : numberOfFrames - 1;
            FrameInterpolator frameInterpolator = new FrameInterpolator(animationDrawable, z);
            ObjectAnimator ofInt = ObjectAnimator.ofInt(animationDrawable, "currentIndex", i2, i3);
            ofInt.setAutoCancel(true);
            ofInt.setDuration(frameInterpolator.a());
            ofInt.setInterpolator(frameInterpolator);
            this.f367b = z2;
            this.f366a = ofInt;
        }

        @Override // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public boolean a() {
            return this.f367b;
        }

        @Override // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public void b() {
            this.f366a.reverse();
        }

        @Override // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public void c() {
            this.f366a.start();
        }

        @Override // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.Transition
        public void d() {
            this.f366a.cancel();
        }
    }

    private static class FrameInterpolator implements TimeInterpolator {

        /* renamed from: a, reason: collision with root package name */
        private int[] f368a;

        /* renamed from: b, reason: collision with root package name */
        private int f369b;

        /* renamed from: c, reason: collision with root package name */
        private int f370c;

        FrameInterpolator(AnimationDrawable animationDrawable, boolean z) {
            b(animationDrawable, z);
        }

        int a() {
            return this.f370c;
        }

        int b(AnimationDrawable animationDrawable, boolean z) {
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            this.f369b = numberOfFrames;
            int[] iArr = this.f368a;
            if (iArr == null || iArr.length < numberOfFrames) {
                this.f368a = new int[numberOfFrames];
            }
            int[] iArr2 = this.f368a;
            int i2 = 0;
            for (int i3 = 0; i3 < numberOfFrames; i3++) {
                int duration = animationDrawable.getDuration(z ? (numberOfFrames - i3) - 1 : i3);
                iArr2[i3] = duration;
                i2 += duration;
            }
            this.f370c = i2;
            return i2;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            int i2 = (int) ((f2 * this.f370c) + 0.5f);
            int i3 = this.f369b;
            int[] iArr = this.f368a;
            int i4 = 0;
            while (i4 < i3) {
                int i5 = iArr[i4];
                if (i2 < i5) {
                    break;
                }
                i2 -= i5;
                i4++;
            }
            return (i4 / i3) + (i4 < i3 ? i2 / this.f370c : 0.0f);
        }
    }

    private static abstract class Transition {
        private Transition() {
        }

        public boolean a() {
            return false;
        }

        public void b() {
        }

        public abstract void c();

        public abstract void d();
    }

    public AnimatedStateListDrawableCompat() {
        this(null, null);
    }

    public static AnimatedStateListDrawableCompat l(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        String name = xmlPullParser.getName();
        if (name.equals("animated-selector")) {
            AnimatedStateListDrawableCompat animatedStateListDrawableCompat = new AnimatedStateListDrawableCompat();
            animatedStateListDrawableCompat.m(context, resources, xmlPullParser, attributeSet, theme);
            return animatedStateListDrawableCompat;
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid animated-selector tag " + name);
    }

    private void n(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int depth = xmlPullParser.getDepth() + 1;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            int depth2 = xmlPullParser.getDepth();
            if (depth2 < depth && next == 3) {
                return;
            }
            if (next == 2 && depth2 <= depth) {
                if (xmlPullParser.getName().equals(Constants.EXTRA_ITEM)) {
                    p(context, resources, xmlPullParser, attributeSet, theme);
                } else if (xmlPullParser.getName().equals("transition")) {
                    q(context, resources, xmlPullParser, attributeSet, theme);
                }
            }
        }
    }

    private void o() {
        onStateChange(getState());
    }

    private int p(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int next;
        TypedArray s2 = TypedArrayUtils.s(resources, theme, attributeSet, R.styleable.AnimatedStateListDrawableItem);
        int resourceId = s2.getResourceId(R.styleable.AnimatedStateListDrawableItem_android_id, 0);
        int resourceId2 = s2.getResourceId(R.styleable.AnimatedStateListDrawableItem_android_drawable, -1);
        Drawable i2 = resourceId2 > 0 ? ResourceManagerInternal.g().i(context, resourceId2) : null;
        s2.recycle();
        int[] j2 = j(attributeSet);
        if (i2 == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
            }
            i2 = xmlPullParser.getName().equals("vector") ? VectorDrawableCompat.c(resources, xmlPullParser, attributeSet, theme) : Compatibility.Api21Impl.a(resources, xmlPullParser, attributeSet, theme);
        }
        if (i2 != null) {
            return this.u.D(j2, i2, resourceId);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
    }

    private int q(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int next;
        TypedArray s2 = TypedArrayUtils.s(resources, theme, attributeSet, R.styleable.AnimatedStateListDrawableTransition);
        int resourceId = s2.getResourceId(R.styleable.AnimatedStateListDrawableTransition_android_fromId, -1);
        int resourceId2 = s2.getResourceId(R.styleable.AnimatedStateListDrawableTransition_android_toId, -1);
        int resourceId3 = s2.getResourceId(R.styleable.AnimatedStateListDrawableTransition_android_drawable, -1);
        Drawable i2 = resourceId3 > 0 ? ResourceManagerInternal.g().i(context, resourceId3) : null;
        boolean z = s2.getBoolean(R.styleable.AnimatedStateListDrawableTransition_android_reversible, false);
        s2.recycle();
        if (i2 == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
            }
            i2 = xmlPullParser.getName().equals("animated-vector") ? AnimatedVectorDrawableCompat.b(context, resources, xmlPullParser, attributeSet, theme) : Compatibility.Api21Impl.a(resources, xmlPullParser, attributeSet, theme);
        }
        if (i2 == null) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
        }
        if (resourceId != -1 && resourceId2 != -1) {
            return this.u.E(resourceId, resourceId2, i2, z);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires 'fromId' & 'toId' attributes");
    }

    private boolean r(int i2) {
        int c2;
        int I;
        Transition animatableTransition;
        Transition transition = this.v;
        if (transition == null) {
            c2 = c();
        } else {
            if (i2 == this.w) {
                return true;
            }
            if (i2 == this.x && transition.a()) {
                transition.b();
                this.w = this.x;
                this.x = i2;
                return true;
            }
            c2 = this.w;
            transition.d();
        }
        this.v = null;
        this.x = -1;
        this.w = -1;
        AnimatedStateListState animatedStateListState = this.u;
        int G = animatedStateListState.G(c2);
        int G2 = animatedStateListState.G(i2);
        if (G2 == 0 || G == 0 || (I = animatedStateListState.I(G, G2)) < 0) {
            return false;
        }
        boolean K = animatedStateListState.K(G, G2);
        f(I);
        Object current = getCurrent();
        if (current instanceof AnimationDrawable) {
            animatableTransition = new AnimationDrawableTransition((AnimationDrawable) current, animatedStateListState.J(G, G2), K);
        } else {
            if (!(current instanceof AnimatedVectorDrawableCompat)) {
                if (current instanceof Animatable) {
                    animatableTransition = new AnimatableTransition((Animatable) current);
                }
                return false;
            }
            animatableTransition = new AnimatedVectorDrawableTransition((AnimatedVectorDrawableCompat) current);
        }
        animatableTransition.c();
        this.v = animatableTransition;
        this.x = c2;
        this.w = i2;
        return true;
    }

    private void s(TypedArray typedArray) {
        AnimatedStateListState animatedStateListState = this.u;
        animatedStateListState.f388d |= Compatibility.Api21Impl.b(typedArray);
        animatedStateListState.z(typedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_variablePadding, animatedStateListState.f393i));
        animatedStateListState.v(typedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_constantSize, animatedStateListState.f396l));
        animatedStateListState.w(typedArray.getInt(R.styleable.AnimatedStateListDrawableCompat_android_enterFadeDuration, animatedStateListState.A));
        animatedStateListState.x(typedArray.getInt(R.styleable.AnimatedStateListDrawableCompat_android_exitFadeDuration, animatedStateListState.B));
        setDither(typedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_dither, animatedStateListState.x));
    }

    @Override // androidx.appcompat.graphics.drawable.StateListDrawableCompat, androidx.appcompat.graphics.drawable.DrawableContainerCompat
    void clearMutated() {
        super.clearMutated();
        this.y = false;
    }

    @Override // androidx.appcompat.graphics.drawable.StateListDrawableCompat, androidx.appcompat.graphics.drawable.DrawableContainerCompat
    void g(DrawableContainerCompat.DrawableContainerState drawableContainerState) {
        super.g(drawableContainerState);
        if (drawableContainerState instanceof AnimatedStateListState) {
            this.u = (AnimatedStateListState) drawableContainerState;
        }
    }

    @Override // androidx.appcompat.graphics.drawable.StateListDrawableCompat, androidx.appcompat.graphics.drawable.DrawableContainerCompat, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat, android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        Transition transition = this.v;
        if (transition != null) {
            transition.d();
            this.v = null;
            f(this.w);
            this.w = -1;
            this.x = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.appcompat.graphics.drawable.StateListDrawableCompat
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public AnimatedStateListState b() {
        return new AnimatedStateListState(this.u, this, null);
    }

    public void m(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        TypedArray s2 = TypedArrayUtils.s(resources, theme, attributeSet, R.styleable.AnimatedStateListDrawableCompat);
        setVisible(s2.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_visible, true), true);
        s(s2);
        h(resources);
        s2.recycle();
        n(context, resources, xmlPullParser, attributeSet, theme);
        o();
    }

    @Override // androidx.appcompat.graphics.drawable.StateListDrawableCompat, androidx.appcompat.graphics.drawable.DrawableContainerCompat, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.y && super.mutate() == this) {
            this.u.t();
            this.y = true;
        }
        return this;
    }

    @Override // androidx.appcompat.graphics.drawable.StateListDrawableCompat, androidx.appcompat.graphics.drawable.DrawableContainerCompat, android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        int H = this.u.H(iArr);
        boolean z = H != c() && (r(H) || f(H));
        Drawable current = getCurrent();
        return current != null ? z | current.setState(iArr) : z;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainerCompat, android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Transition transition = this.v;
        if (transition != null && (visible || z2)) {
            if (z) {
                transition.c();
            } else {
                jumpToCurrentState();
            }
        }
        return visible;
    }

    AnimatedStateListDrawableCompat(AnimatedStateListState animatedStateListState, Resources resources) {
        super(null);
        this.w = -1;
        this.x = -1;
        g(new AnimatedStateListState(animatedStateListState, this, resources));
        onStateChange(getState());
        jumpToCurrentState();
    }
}
