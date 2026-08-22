package com.airbnb.lottie;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class LottieAnimationView extends AppCompatImageView {
    private static final LottieListener<Throwable> DEFAULT_FAILURE_LISTENER = new LottieListener() { // from class: com.airbnb.lottie.c
        @Override // com.airbnb.lottie.LottieListener
        public final void onResult(Object obj) {
            LottieAnimationView.s((Throwable) obj);
        }
    };
    private static final String TAG = "LottieAnimationView";
    private String animationName;

    @RawRes
    private int animationResId;
    private boolean autoPlay;
    private boolean cacheComposition;

    @Nullable
    private LottieTask<LottieComposition> compositionTask;

    @Nullable
    private LottieListener<Throwable> failureListener;

    @DrawableRes
    private int fallbackResource;
    private boolean ignoreUnschedule;
    private final LottieListener<LottieComposition> loadedListener;
    private final LottieDrawable lottieDrawable;
    private final Set<LottieOnCompositionLoadedListener> lottieOnCompositionLoadedListeners;
    private final Set<UserActionTaken> userActionsTaken;
    private final LottieListener<Throwable> wrappedFailureListener;

    /* renamed from: com.airbnb.lottie.LottieAnimationView$1, reason: invalid class name */
    class AnonymousClass1 extends LottieValueCallback<Object> {

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ SimpleLottieValueCallback f9251d;

        @Override // com.airbnb.lottie.value.LottieValueCallback
        public Object a(LottieFrameInfo lottieFrameInfo) {
            return this.f9251d.a(lottieFrameInfo);
        }
    }

    private static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.airbnb.lottie.LottieAnimationView.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: c, reason: collision with root package name */
        String f9252c;

        /* renamed from: h, reason: collision with root package name */
        int f9253h;

        /* renamed from: i, reason: collision with root package name */
        float f9254i;

        /* renamed from: j, reason: collision with root package name */
        boolean f9255j;

        /* renamed from: k, reason: collision with root package name */
        String f9256k;

        /* renamed from: l, reason: collision with root package name */
        int f9257l;

        /* renamed from: m, reason: collision with root package name */
        int f9258m;

        /* synthetic */ SavedState(Parcel parcel, AnonymousClass1 anonymousClass1) {
            this(parcel);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeString(this.f9252c);
            parcel.writeFloat(this.f9254i);
            parcel.writeInt(this.f9255j ? 1 : 0);
            parcel.writeString(this.f9256k);
            parcel.writeInt(this.f9257l);
            parcel.writeInt(this.f9258m);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f9252c = parcel.readString();
            this.f9254i = parcel.readFloat();
            this.f9255j = parcel.readInt() == 1;
            this.f9256k = parcel.readString();
            this.f9257l = parcel.readInt();
            this.f9258m = parcel.readInt();
        }
    }

    private enum UserActionTaken {
        SET_ANIMATION,
        SET_PROGRESS,
        SET_REPEAT_MODE,
        SET_REPEAT_COUNT,
        SET_IMAGE_ASSETS,
        PLAY_OPTION
    }

    private static class WeakFailureListener implements LottieListener<Throwable> {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference f9259a;

        public WeakFailureListener(LottieAnimationView lottieAnimationView) {
            this.f9259a = new WeakReference(lottieAnimationView);
        }

        @Override // com.airbnb.lottie.LottieListener
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th) {
            LottieAnimationView lottieAnimationView = (LottieAnimationView) this.f9259a.get();
            if (lottieAnimationView == null) {
                return;
            }
            if (lottieAnimationView.fallbackResource != 0) {
                lottieAnimationView.setImageResource(lottieAnimationView.fallbackResource);
            }
            (lottieAnimationView.failureListener == null ? LottieAnimationView.DEFAULT_FAILURE_LISTENER : lottieAnimationView.failureListener).onResult(th);
        }
    }

    private static class WeakSuccessListener implements LottieListener<LottieComposition> {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference f9260a;

        public WeakSuccessListener(LottieAnimationView lottieAnimationView) {
            this.f9260a = new WeakReference(lottieAnimationView);
        }

        @Override // com.airbnb.lottie.LottieListener
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(LottieComposition lottieComposition) {
            LottieAnimationView lottieAnimationView = (LottieAnimationView) this.f9260a.get();
            if (lottieAnimationView == null) {
                return;
            }
            lottieAnimationView.setComposition(lottieComposition);
        }
    }

    public LottieAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.loadedListener = new WeakSuccessListener(this);
        this.wrappedFailureListener = new WeakFailureListener(this);
        this.fallbackResource = 0;
        this.lottieDrawable = new LottieDrawable();
        this.ignoreUnschedule = false;
        this.autoPlay = false;
        this.cacheComposition = true;
        this.userActionsTaken = new HashSet();
        this.lottieOnCompositionLoadedListeners = new HashSet();
        o(attributeSet, R.attr.lottieAnimationViewStyle);
    }

    private void j() {
        LottieTask<LottieComposition> lottieTask = this.compositionTask;
        if (lottieTask != null) {
            lottieTask.k(this.loadedListener);
            this.compositionTask.j(this.wrappedFailureListener);
        }
    }

    private void k() {
        this.lottieDrawable.t();
    }

    private LottieTask m(final String str) {
        return isInEditMode() ? new LottieTask(new Callable() { // from class: com.airbnb.lottie.b
            @Override // java.util.concurrent.Callable
            public final Object call() {
                LottieResult q2;
                q2 = LottieAnimationView.this.q(str);
                return q2;
            }
        }, true) : this.cacheComposition ? LottieCompositionFactory.j(getContext(), str) : LottieCompositionFactory.k(getContext(), str, null);
    }

    private LottieTask n(final int i2) {
        return isInEditMode() ? new LottieTask(new Callable() { // from class: com.airbnb.lottie.d
            @Override // java.util.concurrent.Callable
            public final Object call() {
                LottieResult r2;
                r2 = LottieAnimationView.this.r(i2);
                return r2;
            }
        }, true) : this.cacheComposition ? LottieCompositionFactory.s(getContext(), i2) : LottieCompositionFactory.t(getContext(), i2, null);
    }

    private void o(AttributeSet attributeSet, int i2) {
        String string;
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.LottieAnimationView, i2, 0);
        this.cacheComposition = obtainStyledAttributes.getBoolean(R.styleable.LottieAnimationView_lottie_cacheComposition, true);
        boolean hasValue = obtainStyledAttributes.hasValue(R.styleable.LottieAnimationView_lottie_rawRes);
        boolean hasValue2 = obtainStyledAttributes.hasValue(R.styleable.LottieAnimationView_lottie_fileName);
        boolean hasValue3 = obtainStyledAttributes.hasValue(R.styleable.LottieAnimationView_lottie_url);
        if (hasValue && hasValue2) {
            throw new IllegalArgumentException("lottie_rawRes and lottie_fileName cannot be used at the same time. Please use only one at once.");
        }
        if (hasValue) {
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.LottieAnimationView_lottie_rawRes, 0);
            if (resourceId != 0) {
                setAnimation(resourceId);
            }
        } else if (hasValue2) {
            String string2 = obtainStyledAttributes.getString(R.styleable.LottieAnimationView_lottie_fileName);
            if (string2 != null) {
                setAnimation(string2);
            }
        } else if (hasValue3 && (string = obtainStyledAttributes.getString(R.styleable.LottieAnimationView_lottie_url)) != null) {
            setAnimationFromUrl(string);
        }
        setFallbackResource(obtainStyledAttributes.getResourceId(R.styleable.LottieAnimationView_lottie_fallbackRes, 0));
        if (obtainStyledAttributes.getBoolean(R.styleable.LottieAnimationView_lottie_autoPlay, false)) {
            this.autoPlay = true;
        }
        if (obtainStyledAttributes.getBoolean(R.styleable.LottieAnimationView_lottie_loop, false)) {
            this.lottieDrawable.a1(-1);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.LottieAnimationView_lottie_repeatMode)) {
            setRepeatMode(obtainStyledAttributes.getInt(R.styleable.LottieAnimationView_lottie_repeatMode, 1));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.LottieAnimationView_lottie_repeatCount)) {
            setRepeatCount(obtainStyledAttributes.getInt(R.styleable.LottieAnimationView_lottie_repeatCount, -1));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.LottieAnimationView_lottie_speed)) {
            setSpeed(obtainStyledAttributes.getFloat(R.styleable.LottieAnimationView_lottie_speed, 1.0f));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.LottieAnimationView_lottie_clipToCompositionBounds)) {
            setClipToCompositionBounds(obtainStyledAttributes.getBoolean(R.styleable.LottieAnimationView_lottie_clipToCompositionBounds, true));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.LottieAnimationView_lottie_clipTextToBoundingBox)) {
            setClipTextToBoundingBox(obtainStyledAttributes.getBoolean(R.styleable.LottieAnimationView_lottie_clipTextToBoundingBox, false));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.LottieAnimationView_lottie_defaultFontFileExtension)) {
            setDefaultFontFileExtension(obtainStyledAttributes.getString(R.styleable.LottieAnimationView_lottie_defaultFontFileExtension));
        }
        setImageAssetsFolder(obtainStyledAttributes.getString(R.styleable.LottieAnimationView_lottie_imageAssetsFolder));
        y(obtainStyledAttributes.getFloat(R.styleable.LottieAnimationView_lottie_progress, 0.0f), obtainStyledAttributes.hasValue(R.styleable.LottieAnimationView_lottie_progress));
        l(obtainStyledAttributes.getBoolean(R.styleable.LottieAnimationView_lottie_enableMergePathsForKitKatAndAbove, false));
        if (obtainStyledAttributes.hasValue(R.styleable.LottieAnimationView_lottie_colorFilter)) {
            i(new KeyPath("**"), LottieProperty.K, new LottieValueCallback(new SimpleColorFilter(AppCompatResources.a(getContext(), obtainStyledAttributes.getResourceId(R.styleable.LottieAnimationView_lottie_colorFilter, -1)).getDefaultColor())));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.LottieAnimationView_lottie_renderMode)) {
            int i3 = R.styleable.LottieAnimationView_lottie_renderMode;
            RenderMode renderMode = RenderMode.AUTOMATIC;
            int i4 = obtainStyledAttributes.getInt(i3, renderMode.ordinal());
            if (i4 >= RenderMode.values().length) {
                i4 = renderMode.ordinal();
            }
            setRenderMode(RenderMode.values()[i4]);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.LottieAnimationView_lottie_asyncUpdates)) {
            int i5 = R.styleable.LottieAnimationView_lottie_asyncUpdates;
            AsyncUpdates asyncUpdates = AsyncUpdates.AUTOMATIC;
            int i6 = obtainStyledAttributes.getInt(i5, asyncUpdates.ordinal());
            if (i6 >= RenderMode.values().length) {
                i6 = asyncUpdates.ordinal();
            }
            setAsyncUpdates(AsyncUpdates.values()[i6]);
        }
        setIgnoreDisabledSystemAnimations(obtainStyledAttributes.getBoolean(R.styleable.LottieAnimationView_lottie_ignoreDisabledSystemAnimations, false));
        if (obtainStyledAttributes.hasValue(R.styleable.LottieAnimationView_lottie_useCompositionFrameRate)) {
            setUseCompositionFrameRate(obtainStyledAttributes.getBoolean(R.styleable.LottieAnimationView_lottie_useCompositionFrameRate, false));
        }
        obtainStyledAttributes.recycle();
        this.lottieDrawable.e1(Boolean.valueOf(Utils.f(getContext()) != 0.0f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ LottieResult q(String str) {
        return this.cacheComposition ? LottieCompositionFactory.l(getContext(), str) : LottieCompositionFactory.m(getContext(), str, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ LottieResult r(int i2) {
        return this.cacheComposition ? LottieCompositionFactory.u(getContext(), i2) : LottieCompositionFactory.v(getContext(), i2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void s(Throwable th) {
        if (!Utils.k(th)) {
            throw new IllegalStateException("Unable to parse composition", th);
        }
        Logger.d("Unable to load composition.", th);
    }

    private void setCompositionTask(LottieTask<LottieComposition> lottieTask) {
        LottieResult e2 = lottieTask.e();
        LottieDrawable lottieDrawable = this.lottieDrawable;
        if (e2 != null && lottieDrawable == getDrawable() && lottieDrawable.I() == e2.b()) {
            return;
        }
        this.userActionsTaken.add(UserActionTaken.SET_ANIMATION);
        k();
        j();
        this.compositionTask = lottieTask.d(this.loadedListener).c(this.wrappedFailureListener);
    }

    private void x() {
        boolean p2 = p();
        setImageDrawable(null);
        setImageDrawable(this.lottieDrawable);
        if (p2) {
            this.lottieDrawable.z0();
        }
    }

    private void y(float f2, boolean z) {
        if (z) {
            this.userActionsTaken.add(UserActionTaken.SET_PROGRESS);
        }
        this.lottieDrawable.Y0(f2);
    }

    public AsyncUpdates getAsyncUpdates() {
        return this.lottieDrawable.D();
    }

    public boolean getAsyncUpdatesEnabled() {
        return this.lottieDrawable.E();
    }

    public boolean getClipTextToBoundingBox() {
        return this.lottieDrawable.G();
    }

    public boolean getClipToCompositionBounds() {
        return this.lottieDrawable.H();
    }

    @Nullable
    public LottieComposition getComposition() {
        Drawable drawable = getDrawable();
        LottieDrawable lottieDrawable = this.lottieDrawable;
        if (drawable == lottieDrawable) {
            return lottieDrawable.I();
        }
        return null;
    }

    public long getDuration() {
        LottieComposition composition = getComposition();
        if (composition != null) {
            return (long) composition.d();
        }
        return 0L;
    }

    public int getFrame() {
        return this.lottieDrawable.L();
    }

    @Nullable
    public String getImageAssetsFolder() {
        return this.lottieDrawable.N();
    }

    public boolean getMaintainOriginalImageBounds() {
        return this.lottieDrawable.P();
    }

    public float getMaxFrame() {
        return this.lottieDrawable.R();
    }

    public float getMinFrame() {
        return this.lottieDrawable.S();
    }

    @Nullable
    public PerformanceTracker getPerformanceTracker() {
        return this.lottieDrawable.T();
    }

    @FloatRange
    public float getProgress() {
        return this.lottieDrawable.U();
    }

    public RenderMode getRenderMode() {
        return this.lottieDrawable.V();
    }

    public int getRepeatCount() {
        return this.lottieDrawable.W();
    }

    public int getRepeatMode() {
        return this.lottieDrawable.X();
    }

    public float getSpeed() {
        return this.lottieDrawable.Y();
    }

    public void i(KeyPath keyPath, Object obj, LottieValueCallback lottieValueCallback) {
        this.lottieDrawable.q(keyPath, obj, lottieValueCallback);
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        Drawable drawable = getDrawable();
        if ((drawable instanceof LottieDrawable) && ((LottieDrawable) drawable).V() == RenderMode.SOFTWARE) {
            this.lottieDrawable.invalidateSelf();
        }
    }

    @Override // android.widget.ImageView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable drawable2 = getDrawable();
        LottieDrawable lottieDrawable = this.lottieDrawable;
        if (drawable2 == lottieDrawable) {
            super.invalidateDrawable(lottieDrawable);
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    public void l(boolean z) {
        this.lottieDrawable.y(z);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode() || !this.autoPlay) {
            return;
        }
        this.lottieDrawable.w0();
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        int i2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.animationName = savedState.f9252c;
        Set<UserActionTaken> set = this.userActionsTaken;
        UserActionTaken userActionTaken = UserActionTaken.SET_ANIMATION;
        if (!set.contains(userActionTaken) && !TextUtils.isEmpty(this.animationName)) {
            setAnimation(this.animationName);
        }
        this.animationResId = savedState.f9253h;
        if (!this.userActionsTaken.contains(userActionTaken) && (i2 = this.animationResId) != 0) {
            setAnimation(i2);
        }
        if (!this.userActionsTaken.contains(UserActionTaken.SET_PROGRESS)) {
            y(savedState.f9254i, false);
        }
        if (!this.userActionsTaken.contains(UserActionTaken.PLAY_OPTION) && savedState.f9255j) {
            u();
        }
        if (!this.userActionsTaken.contains(UserActionTaken.SET_IMAGE_ASSETS)) {
            setImageAssetsFolder(savedState.f9256k);
        }
        if (!this.userActionsTaken.contains(UserActionTaken.SET_REPEAT_MODE)) {
            setRepeatMode(savedState.f9257l);
        }
        if (this.userActionsTaken.contains(UserActionTaken.SET_REPEAT_COUNT)) {
            return;
        }
        setRepeatCount(savedState.f9258m);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f9252c = this.animationName;
        savedState.f9253h = this.animationResId;
        savedState.f9254i = this.lottieDrawable.U();
        savedState.f9255j = this.lottieDrawable.d0();
        savedState.f9256k = this.lottieDrawable.N();
        savedState.f9257l = this.lottieDrawable.X();
        savedState.f9258m = this.lottieDrawable.W();
        return savedState;
    }

    public boolean p() {
        return this.lottieDrawable.c0();
    }

    public void setAnimation(@RawRes int i2) {
        this.animationResId = i2;
        this.animationName = null;
        setCompositionTask(n(i2));
    }

    @Deprecated
    public void setAnimationFromJson(String str) {
        w(str, null);
    }

    public void setAnimationFromUrl(String str) {
        setCompositionTask(this.cacheComposition ? LottieCompositionFactory.w(getContext(), str) : LottieCompositionFactory.x(getContext(), str, null));
    }

    public void setApplyingOpacityToLayersEnabled(boolean z) {
        this.lottieDrawable.B0(z);
    }

    public void setAsyncUpdates(AsyncUpdates asyncUpdates) {
        this.lottieDrawable.C0(asyncUpdates);
    }

    public void setCacheComposition(boolean z) {
        this.cacheComposition = z;
    }

    public void setClipTextToBoundingBox(boolean z) {
        this.lottieDrawable.D0(z);
    }

    public void setClipToCompositionBounds(boolean z) {
        this.lottieDrawable.E0(z);
    }

    public void setComposition(@NonNull LottieComposition lottieComposition) {
        if (L.f9241a) {
            Log.v(TAG, "Set Composition \n" + lottieComposition);
        }
        this.lottieDrawable.setCallback(this);
        this.ignoreUnschedule = true;
        boolean F0 = this.lottieDrawable.F0(lottieComposition);
        if (this.autoPlay) {
            this.lottieDrawable.w0();
        }
        this.ignoreUnschedule = false;
        if (getDrawable() != this.lottieDrawable || F0) {
            if (!F0) {
                x();
            }
            onVisibilityChanged(this, getVisibility());
            requestLayout();
            Iterator<LottieOnCompositionLoadedListener> it = this.lottieOnCompositionLoadedListeners.iterator();
            while (it.hasNext()) {
                it.next().a(lottieComposition);
            }
        }
    }

    public void setDefaultFontFileExtension(String str) {
        this.lottieDrawable.G0(str);
    }

    public void setFailureListener(@Nullable LottieListener<Throwable> lottieListener) {
        this.failureListener = lottieListener;
    }

    public void setFallbackResource(@DrawableRes int i2) {
        this.fallbackResource = i2;
    }

    public void setFontAssetDelegate(FontAssetDelegate fontAssetDelegate) {
        this.lottieDrawable.H0(fontAssetDelegate);
    }

    public void setFontMap(@Nullable Map<String, Typeface> map) {
        this.lottieDrawable.I0(map);
    }

    public void setFrame(int i2) {
        this.lottieDrawable.J0(i2);
    }

    public void setIgnoreDisabledSystemAnimations(boolean z) {
        this.lottieDrawable.K0(z);
    }

    public void setImageAssetDelegate(ImageAssetDelegate imageAssetDelegate) {
        this.lottieDrawable.L0(imageAssetDelegate);
    }

    public void setImageAssetsFolder(String str) {
        this.lottieDrawable.M0(str);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        this.animationResId = 0;
        this.animationName = null;
        j();
        super.setImageBitmap(bitmap);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        this.animationResId = 0;
        this.animationName = null;
        j();
        super.setImageDrawable(drawable);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i2) {
        this.animationResId = 0;
        this.animationName = null;
        j();
        super.setImageResource(i2);
    }

    public void setMaintainOriginalImageBounds(boolean z) {
        this.lottieDrawable.N0(z);
    }

    public void setMaxFrame(int i2) {
        this.lottieDrawable.O0(i2);
    }

    public void setMaxProgress(@FloatRange float f2) {
        this.lottieDrawable.Q0(f2);
    }

    public void setMinAndMaxFrame(String str) {
        this.lottieDrawable.S0(str);
    }

    public void setMinFrame(int i2) {
        this.lottieDrawable.T0(i2);
    }

    public void setMinProgress(float f2) {
        this.lottieDrawable.V0(f2);
    }

    public void setOutlineMasksAndMattes(boolean z) {
        this.lottieDrawable.W0(z);
    }

    public void setPerformanceTrackingEnabled(boolean z) {
        this.lottieDrawable.X0(z);
    }

    public void setProgress(@FloatRange float f2) {
        y(f2, true);
    }

    public void setRenderMode(RenderMode renderMode) {
        this.lottieDrawable.Z0(renderMode);
    }

    public void setRepeatCount(int i2) {
        this.userActionsTaken.add(UserActionTaken.SET_REPEAT_COUNT);
        this.lottieDrawable.a1(i2);
    }

    public void setRepeatMode(int i2) {
        this.userActionsTaken.add(UserActionTaken.SET_REPEAT_MODE);
        this.lottieDrawable.b1(i2);
    }

    public void setSafeMode(boolean z) {
        this.lottieDrawable.c1(z);
    }

    public void setSpeed(float f2) {
        this.lottieDrawable.d1(f2);
    }

    public void setTextDelegate(TextDelegate textDelegate) {
        this.lottieDrawable.f1(textDelegate);
    }

    public void setUseCompositionFrameRate(boolean z) {
        this.lottieDrawable.g1(z);
    }

    public void t() {
        this.autoPlay = false;
        this.lottieDrawable.v0();
    }

    public void u() {
        this.userActionsTaken.add(UserActionTaken.PLAY_OPTION);
        this.lottieDrawable.w0();
    }

    @Override // android.view.View
    public void unscheduleDrawable(Drawable drawable) {
        LottieDrawable lottieDrawable;
        if (!this.ignoreUnschedule && drawable == (lottieDrawable = this.lottieDrawable) && lottieDrawable.c0()) {
            t();
        } else if (!this.ignoreUnschedule && (drawable instanceof LottieDrawable)) {
            LottieDrawable lottieDrawable2 = (LottieDrawable) drawable;
            if (lottieDrawable2.c0()) {
                lottieDrawable2.v0();
            }
        }
        super.unscheduleDrawable(drawable);
    }

    public void v(InputStream inputStream, String str) {
        setCompositionTask(LottieCompositionFactory.n(inputStream, str));
    }

    public void w(String str, String str2) {
        v(new ByteArrayInputStream(str.getBytes()), str2);
    }

    public void setMaxFrame(String str) {
        this.lottieDrawable.P0(str);
    }

    public void setMinFrame(String str) {
        this.lottieDrawable.U0(str);
    }

    public void setAnimation(String str) {
        this.animationName = str;
        this.animationResId = 0;
        setCompositionTask(m(str));
    }

    public LottieAnimationView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.loadedListener = new WeakSuccessListener(this);
        this.wrappedFailureListener = new WeakFailureListener(this);
        this.fallbackResource = 0;
        this.lottieDrawable = new LottieDrawable();
        this.ignoreUnschedule = false;
        this.autoPlay = false;
        this.cacheComposition = true;
        this.userActionsTaken = new HashSet();
        this.lottieOnCompositionLoadedListeners = new HashSet();
        o(attributeSet, i2);
    }
}
