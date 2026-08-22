package androidx.core.view;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContentInfo;
import android.view.Display;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.collection.SimpleArrayMap;
import androidx.core.R;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.core.view.autofill.AutofillIdCompat;
import androidx.core.view.contentcapture.ContentCaptureSessionCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;

@SuppressLint({"PrivateConstructorForUtilityClass"})
/* loaded from: classes.dex */
public class ViewCompat {

    /* renamed from: a, reason: collision with root package name */
    private static WeakHashMap f3373a;

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f3374b = {R.id.accessibility_custom_action_0, R.id.accessibility_custom_action_1, R.id.accessibility_custom_action_2, R.id.accessibility_custom_action_3, R.id.accessibility_custom_action_4, R.id.accessibility_custom_action_5, R.id.accessibility_custom_action_6, R.id.accessibility_custom_action_7, R.id.accessibility_custom_action_8, R.id.accessibility_custom_action_9, R.id.accessibility_custom_action_10, R.id.accessibility_custom_action_11, R.id.accessibility_custom_action_12, R.id.accessibility_custom_action_13, R.id.accessibility_custom_action_14, R.id.accessibility_custom_action_15, R.id.accessibility_custom_action_16, R.id.accessibility_custom_action_17, R.id.accessibility_custom_action_18, R.id.accessibility_custom_action_19, R.id.accessibility_custom_action_20, R.id.accessibility_custom_action_21, R.id.accessibility_custom_action_22, R.id.accessibility_custom_action_23, R.id.accessibility_custom_action_24, R.id.accessibility_custom_action_25, R.id.accessibility_custom_action_26, R.id.accessibility_custom_action_27, R.id.accessibility_custom_action_28, R.id.accessibility_custom_action_29, R.id.accessibility_custom_action_30, R.id.accessibility_custom_action_31};

    /* renamed from: c, reason: collision with root package name */
    private static final OnReceiveContentViewBehavior f3375c = new OnReceiveContentViewBehavior() { // from class: androidx.core.view.f
    };

    /* renamed from: d, reason: collision with root package name */
    private static final AccessibilityPaneVisibilityManager f3376d = new AccessibilityPaneVisibilityManager();

    static class AccessibilityPaneVisibilityManager implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {

        /* renamed from: c, reason: collision with root package name */
        private final WeakHashMap f3377c = new WeakHashMap();

        AccessibilityPaneVisibilityManager() {
        }

        private void b(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        private void d(View view) {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }

        void a(View view) {
            this.f3377c.put(view, Boolean.valueOf(view.isShown() && view.getWindowVisibility() == 0));
            view.addOnAttachStateChangeListener(this);
            if (view.isAttachedToWindow()) {
                b(view);
            }
        }

        void c(View view) {
            this.f3377c.remove(view);
            view.removeOnAttachStateChangeListener(this);
            d(view);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            b(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    static abstract class AccessibilityViewProperty<T> {

        /* renamed from: a, reason: collision with root package name */
        private final int f3378a;

        /* renamed from: b, reason: collision with root package name */
        private final Class f3379b;

        /* renamed from: c, reason: collision with root package name */
        private final int f3380c;

        /* renamed from: d, reason: collision with root package name */
        private final int f3381d;

        AccessibilityViewProperty(int i2, Class cls, int i3) {
            this(i2, cls, 0, i3);
        }

        private boolean b() {
            return Build.VERSION.SDK_INT >= this.f3380c;
        }

        boolean a(Boolean bool, Boolean bool2) {
            return (bool != null && bool.booleanValue()) == (bool2 != null && bool2.booleanValue());
        }

        abstract Object c(View view);

        abstract void d(View view, Object obj);

        Object e(View view) {
            if (b()) {
                return c(view);
            }
            Object tag = view.getTag(this.f3378a);
            if (this.f3379b.isInstance(tag)) {
                return tag;
            }
            return null;
        }

        void f(View view, Object obj) {
            if (b()) {
                d(view, obj);
            } else if (g(e(view), obj)) {
                ViewCompat.h(view);
                view.setTag(this.f3378a, obj);
                ViewCompat.R(view, this.f3381d);
            }
        }

        boolean g(Object obj, Object obj2) {
            return !obj2.equals(obj);
        }

        AccessibilityViewProperty(int i2, Class cls, int i3, int i4) {
            this.f3378a = i2;
            this.f3379b = cls;
            this.f3381d = i3;
            this.f3380c = i4;
        }
    }

    @RequiresApi
    static class Api20Impl {
        @DoNotInline
        static WindowInsets a(View view, WindowInsets windowInsets) {
            return view.dispatchApplyWindowInsets(windowInsets);
        }

        @DoNotInline
        static WindowInsets b(View view, WindowInsets windowInsets) {
            return view.onApplyWindowInsets(windowInsets);
        }

        @DoNotInline
        static void c(View view) {
            view.requestApplyInsets();
        }
    }

    @RequiresApi
    private static class Api21Impl {
        @DoNotInline
        static void a(@NonNull WindowInsets windowInsets, @NonNull View view) {
            View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = (View.OnApplyWindowInsetsListener) view.getTag(R.id.tag_window_insets_animation_callback);
            if (onApplyWindowInsetsListener != null) {
                onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsets);
            }
        }

        @DoNotInline
        static WindowInsetsCompat b(@NonNull View view, @NonNull WindowInsetsCompat windowInsetsCompat, @NonNull Rect rect) {
            WindowInsets v = windowInsetsCompat.v();
            if (v != null) {
                return WindowInsetsCompat.x(view.computeSystemWindowInsets(v, rect), view);
            }
            rect.setEmpty();
            return windowInsetsCompat;
        }

        @DoNotInline
        static boolean c(@NonNull View view, float f2, float f3, boolean z) {
            return view.dispatchNestedFling(f2, f3, z);
        }

        @DoNotInline
        static boolean d(@NonNull View view, float f2, float f3) {
            return view.dispatchNestedPreFling(f2, f3);
        }

        @DoNotInline
        static boolean e(View view, int i2, int i3, int[] iArr, int[] iArr2) {
            return view.dispatchNestedPreScroll(i2, i3, iArr, iArr2);
        }

        @DoNotInline
        static boolean f(View view, int i2, int i3, int i4, int i5, int[] iArr) {
            return view.dispatchNestedScroll(i2, i3, i4, i5, iArr);
        }

        @DoNotInline
        static ColorStateList g(View view) {
            return view.getBackgroundTintList();
        }

        @DoNotInline
        static PorterDuff.Mode h(View view) {
            return view.getBackgroundTintMode();
        }

        @DoNotInline
        static float i(View view) {
            return view.getElevation();
        }

        @Nullable
        @DoNotInline
        public static WindowInsetsCompat j(@NonNull View view) {
            return WindowInsetsCompat.Api21ReflectionHolder.a(view);
        }

        @DoNotInline
        static String k(View view) {
            return view.getTransitionName();
        }

        @DoNotInline
        static float l(View view) {
            return view.getTranslationZ();
        }

        @DoNotInline
        static float m(@NonNull View view) {
            return view.getZ();
        }

        @DoNotInline
        static boolean n(View view) {
            return view.hasNestedScrollingParent();
        }

        @DoNotInline
        static boolean o(View view) {
            return view.isImportantForAccessibility();
        }

        @DoNotInline
        static boolean p(View view) {
            return view.isNestedScrollingEnabled();
        }

        @DoNotInline
        static void q(View view, ColorStateList colorStateList) {
            view.setBackgroundTintList(colorStateList);
        }

        @DoNotInline
        static void r(View view, PorterDuff.Mode mode) {
            view.setBackgroundTintMode(mode);
        }

        @DoNotInline
        static void s(View view, float f2) {
            view.setElevation(f2);
        }

        @DoNotInline
        static void t(View view, boolean z) {
            view.setNestedScrollingEnabled(z);
        }

        @DoNotInline
        static void u(@NonNull final View view, @Nullable final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
            if (onApplyWindowInsetsListener == null) {
                view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) view.getTag(R.id.tag_window_insets_animation_callback));
            } else {
                view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: androidx.core.view.ViewCompat.Api21Impl.1

                    /* renamed from: a, reason: collision with root package name */
                    WindowInsetsCompat f3382a = null;

                    @Override // android.view.View.OnApplyWindowInsetsListener
                    public WindowInsets onApplyWindowInsets(View view2, WindowInsets windowInsets) {
                        WindowInsetsCompat x = WindowInsetsCompat.x(windowInsets, view2);
                        this.f3382a = x;
                        return onApplyWindowInsetsListener.a(view2, x).v();
                    }
                });
            }
        }

        @DoNotInline
        static void v(View view, String str) {
            view.setTransitionName(str);
        }

        @DoNotInline
        static void w(View view, float f2) {
            view.setTranslationZ(f2);
        }

        @DoNotInline
        static void x(@NonNull View view, float f2) {
            view.setZ(f2);
        }

        @DoNotInline
        static boolean y(View view, int i2) {
            return view.startNestedScroll(i2);
        }

        @DoNotInline
        static void z(View view) {
            view.stopNestedScroll();
        }
    }

    @RequiresApi
    private static class Api23Impl {
        @Nullable
        public static WindowInsetsCompat a(@NonNull View view) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            if (rootWindowInsets == null) {
                return null;
            }
            WindowInsetsCompat w = WindowInsetsCompat.w(rootWindowInsets);
            w.t(w);
            w.d(view.getRootView());
            return w;
        }

        @DoNotInline
        static int b(@NonNull View view) {
            return view.getScrollIndicators();
        }

        @DoNotInline
        static void c(@NonNull View view, int i2) {
            view.setScrollIndicators(i2);
        }

        @DoNotInline
        static void d(@NonNull View view, int i2, int i3) {
            view.setScrollIndicators(i2, i3);
        }
    }

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static void a(@NonNull View view) {
            view.cancelDragAndDrop();
        }

        @DoNotInline
        static void b(View view) {
            view.dispatchFinishTemporaryDetach();
        }

        @DoNotInline
        static void c(View view) {
            view.dispatchStartTemporaryDetach();
        }

        @DoNotInline
        static void d(@NonNull View view, PointerIcon pointerIcon) {
            view.setPointerIcon(pointerIcon);
        }

        @DoNotInline
        static boolean e(@NonNull View view, @Nullable ClipData clipData, @NonNull View.DragShadowBuilder dragShadowBuilder, @Nullable Object obj, int i2) {
            return view.startDragAndDrop(clipData, dragShadowBuilder, obj, i2);
        }

        @DoNotInline
        static void f(@NonNull View view, @NonNull View.DragShadowBuilder dragShadowBuilder) {
            view.updateDragShadow(dragShadowBuilder);
        }
    }

    @RequiresApi
    static class Api26Impl {
        @DoNotInline
        static void a(@NonNull View view, Collection<View> collection, int i2) {
            view.addKeyboardNavigationClusters(collection, i2);
        }

        @DoNotInline
        public static AutofillId b(View view) {
            return view.getAutofillId();
        }

        @DoNotInline
        static int c(View view) {
            return view.getImportantForAutofill();
        }

        @DoNotInline
        static int d(@NonNull View view) {
            return view.getNextClusterForwardId();
        }

        @DoNotInline
        static boolean e(@NonNull View view) {
            return view.hasExplicitFocusable();
        }

        @DoNotInline
        static boolean f(@NonNull View view) {
            return view.isFocusedByDefault();
        }

        @DoNotInline
        static boolean g(View view) {
            return view.isImportantForAutofill();
        }

        @DoNotInline
        static boolean h(@NonNull View view) {
            return view.isKeyboardNavigationCluster();
        }

        @DoNotInline
        static View i(@NonNull View view, View view2, int i2) {
            return view.keyboardNavigationClusterSearch(view2, i2);
        }

        @DoNotInline
        static boolean j(@NonNull View view) {
            return view.restoreDefaultFocus();
        }

        @DoNotInline
        static void k(@NonNull View view, String... strArr) {
            view.setAutofillHints(strArr);
        }

        @DoNotInline
        static void l(@NonNull View view, boolean z) {
            view.setFocusedByDefault(z);
        }

        @DoNotInline
        static void m(View view, int i2) {
            view.setImportantForAutofill(i2);
        }

        @DoNotInline
        static void n(@NonNull View view, boolean z) {
            view.setKeyboardNavigationCluster(z);
        }

        @DoNotInline
        static void o(View view, int i2) {
            view.setNextClusterForwardId(i2);
        }

        @DoNotInline
        static void p(@NonNull View view, CharSequence charSequence) {
            view.setTooltipText(charSequence);
        }
    }

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static void a(@NonNull View view, @NonNull final OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) view.getTag(R.id.tag_unhandled_key_listeners);
            if (simpleArrayMap == null) {
                simpleArrayMap = new SimpleArrayMap();
                view.setTag(R.id.tag_unhandled_key_listeners, simpleArrayMap);
            }
            Objects.requireNonNull(onUnhandledKeyEventListenerCompat);
            View.OnUnhandledKeyEventListener onUnhandledKeyEventListener = new View.OnUnhandledKeyEventListener() { // from class: androidx.core.view.g
                @Override // android.view.View.OnUnhandledKeyEventListener
                public final boolean onUnhandledKeyEvent(View view2, KeyEvent keyEvent) {
                    return ViewCompat.OnUnhandledKeyEventListenerCompat.this.onUnhandledKeyEvent(view2, keyEvent);
                }
            };
            simpleArrayMap.put(onUnhandledKeyEventListenerCompat, onUnhandledKeyEventListener);
            view.addOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
        }

        @DoNotInline
        static CharSequence b(View view) {
            return view.getAccessibilityPaneTitle();
        }

        @DoNotInline
        static boolean c(View view) {
            return view.isAccessibilityHeading();
        }

        @DoNotInline
        static boolean d(View view) {
            return view.isScreenReaderFocusable();
        }

        @DoNotInline
        static void e(@NonNull View view, @NonNull OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
            View.OnUnhandledKeyEventListener onUnhandledKeyEventListener;
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) view.getTag(R.id.tag_unhandled_key_listeners);
            if (simpleArrayMap == null || (onUnhandledKeyEventListener = (View.OnUnhandledKeyEventListener) simpleArrayMap.get(onUnhandledKeyEventListenerCompat)) == null) {
                return;
            }
            view.removeOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
        }

        @DoNotInline
        static <T> T f(View view, int i2) {
            return (T) view.requireViewById(i2);
        }

        @DoNotInline
        static void g(View view, boolean z) {
            view.setAccessibilityHeading(z);
        }

        @DoNotInline
        static void h(View view, CharSequence charSequence) {
            view.setAccessibilityPaneTitle(charSequence);
        }

        @DoNotInline
        public static void i(View view, AutofillIdCompat autofillIdCompat) {
            view.setAutofillId(autofillIdCompat == null ? null : autofillIdCompat.a());
        }

        @DoNotInline
        static void j(View view, boolean z) {
            view.setScreenReaderFocusable(z);
        }
    }

    @RequiresApi
    private static class Api29Impl {
        @DoNotInline
        static View.AccessibilityDelegate a(View view) {
            return view.getAccessibilityDelegate();
        }

        @DoNotInline
        static ContentCaptureSession b(View view) {
            return view.getContentCaptureSession();
        }

        @DoNotInline
        static List<Rect> c(View view) {
            return view.getSystemGestureExclusionRects();
        }

        @DoNotInline
        static void d(@NonNull View view, @NonNull Context context, @NonNull int[] iArr, @Nullable AttributeSet attributeSet, @NonNull TypedArray typedArray, int i2, int i3) {
            view.saveAttributeDataForStyleable(context, iArr, attributeSet, typedArray, i2, i3);
        }

        @DoNotInline
        static void e(View view, ContentCaptureSessionCompat contentCaptureSessionCompat) {
            view.setContentCaptureSession(contentCaptureSessionCompat == null ? null : contentCaptureSessionCompat.a());
        }

        @DoNotInline
        static void f(View view, List<Rect> list) {
            view.setSystemGestureExclusionRects(list);
        }
    }

    @RequiresApi
    private static class Api30Impl {
        @DoNotInline
        static int a(View view) {
            return view.getImportantForContentCapture();
        }

        @DoNotInline
        static CharSequence b(View view) {
            return view.getStateDescription();
        }

        @Nullable
        public static WindowInsetsControllerCompat c(@NonNull View view) {
            WindowInsetsController windowInsetsController = view.getWindowInsetsController();
            if (windowInsetsController != null) {
                return WindowInsetsControllerCompat.f(windowInsetsController);
            }
            return null;
        }

        @DoNotInline
        static boolean d(View view) {
            return view.isImportantForContentCapture();
        }

        @DoNotInline
        static void e(View view, int i2) {
            view.setImportantForContentCapture(i2);
        }

        @DoNotInline
        static void f(View view, CharSequence charSequence) {
            view.setStateDescription(charSequence);
        }
    }

    @RequiresApi
    private static final class Api31Impl {
        @Nullable
        @DoNotInline
        public static String[] a(@NonNull View view) {
            return view.getReceiveContentMimeTypes();
        }

        @Nullable
        @DoNotInline
        public static ContentInfoCompat b(@NonNull View view, @NonNull ContentInfoCompat contentInfoCompat) {
            ContentInfo h2 = contentInfoCompat.h();
            ContentInfo performReceiveContent = view.performReceiveContent(h2);
            if (performReceiveContent == null) {
                return null;
            }
            return performReceiveContent == h2 ? contentInfoCompat : ContentInfoCompat.i(performReceiveContent);
        }

        @DoNotInline
        public static void c(@NonNull View view, @Nullable String[] strArr, @Nullable OnReceiveContentListener onReceiveContentListener) {
            if (onReceiveContentListener == null) {
                view.setOnReceiveContentListener(strArr, null);
            } else {
                view.setOnReceiveContentListener(strArr, new OnReceiveContentListenerAdapter(onReceiveContentListener));
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface FocusDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface FocusRealDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface FocusRelativeDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface NestedScrollType {
    }

    @RequiresApi
    private static final class OnReceiveContentListenerAdapter implements android.view.OnReceiveContentListener {

        /* renamed from: a, reason: collision with root package name */
        private final OnReceiveContentListener f3385a;

        OnReceiveContentListenerAdapter(OnReceiveContentListener onReceiveContentListener) {
            this.f3385a = onReceiveContentListener;
        }

        @Override // android.view.OnReceiveContentListener
        public ContentInfo onReceiveContent(View view, ContentInfo contentInfo) {
            ContentInfoCompat i2 = ContentInfoCompat.i(contentInfo);
            ContentInfoCompat a2 = this.f3385a.a(view, i2);
            if (a2 == null) {
                return null;
            }
            return a2 == i2 ? contentInfo : a2.h();
        }
    }

    public interface OnUnhandledKeyEventListenerCompat {
        boolean onUnhandledKeyEvent(View view, KeyEvent keyEvent);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface ScrollAxis {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface ScrollIndicators {
    }

    static class UnhandledKeyEventManager {

        /* renamed from: a, reason: collision with root package name */
        private static final ArrayList f3386a = new ArrayList();
    }

    public static ViewParent A(View view) {
        return view.getParentForAccessibility();
    }

    public static void A0(View view, int i2, int i3) {
        Api23Impl.d(view, i2, i3);
    }

    public static WindowInsetsCompat B(View view) {
        return Api23Impl.a(view);
    }

    public static void B0(View view, CharSequence charSequence) {
        G0().f(view, charSequence);
    }

    public static CharSequence C(View view) {
        return (CharSequence) G0().e(view);
    }

    public static void C0(View view, String str) {
        Api21Impl.v(view, str);
    }

    public static String D(View view) {
        return Api21Impl.k(view);
    }

    public static void D0(View view, float f2) {
        Api21Impl.w(view, f2);
    }

    public static float E(View view) {
        return Api21Impl.l(view);
    }

    public static void E0(View view, WindowInsetsAnimationCompat.Callback callback) {
        WindowInsetsAnimationCompat.d(view, callback);
    }

    public static WindowInsetsControllerCompat F(View view) {
        return Api30Impl.c(view);
    }

    public static void F0(View view, float f2) {
        Api21Impl.x(view, f2);
    }

    public static int G(View view) {
        return view.getWindowSystemUiVisibility();
    }

    private static AccessibilityViewProperty G0() {
        return new AccessibilityViewProperty<CharSequence>(R.id.tag_state_description, CharSequence.class, 64, 30) { // from class: androidx.core.view.ViewCompat.3
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            /* renamed from: h, reason: merged with bridge method [inline-methods] */
            public CharSequence c(View view) {
                return Api30Impl.b(view);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            /* renamed from: i, reason: merged with bridge method [inline-methods] */
            public void d(View view, CharSequence charSequence) {
                Api30Impl.f(view, charSequence);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            /* renamed from: j, reason: merged with bridge method [inline-methods] */
            public boolean g(CharSequence charSequence, CharSequence charSequence2) {
                return !TextUtils.equals(charSequence, charSequence2);
            }
        };
    }

    public static float H(View view) {
        return Api21Impl.m(view);
    }

    public static void H0(View view) {
        Api21Impl.z(view);
    }

    public static boolean I(View view) {
        return k(view) != null;
    }

    public static boolean J(View view) {
        return view.hasOnClickListeners();
    }

    public static boolean K(View view) {
        return view.hasTransientState();
    }

    public static boolean L(View view) {
        Boolean bool = (Boolean) a().e(view);
        return bool != null && bool.booleanValue();
    }

    public static boolean M(View view) {
        return view.isAttachedToWindow();
    }

    public static boolean N(View view) {
        return view.isLaidOut();
    }

    public static boolean O(View view) {
        return Api21Impl.p(view);
    }

    public static boolean P(View view) {
        return view.isPaddingRelative();
    }

    public static boolean Q(View view) {
        Boolean bool = (Boolean) h0().e(view);
        return bool != null && bool.booleanValue();
    }

    static void R(View view, int i2) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            boolean z = l(view) != null && view.isShown() && view.getWindowVisibility() == 0;
            if (view.getAccessibilityLiveRegion() != 0 || z) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain();
                obtain.setEventType(z ? 32 : 2048);
                obtain.setContentChangeTypes(i2);
                if (z) {
                    obtain.getText().add(l(view));
                    t0(view);
                }
                view.sendAccessibilityEventUnchecked(obtain);
                return;
            }
            if (i2 == 32) {
                AccessibilityEvent obtain2 = AccessibilityEvent.obtain();
                view.onInitializeAccessibilityEvent(obtain2);
                obtain2.setEventType(32);
                obtain2.setContentChangeTypes(i2);
                obtain2.setSource(view);
                view.onPopulateAccessibilityEvent(obtain2);
                obtain2.getText().add(l(view));
                accessibilityManager.sendAccessibilityEvent(obtain2);
                return;
            }
            if (view.getParent() != null) {
                try {
                    view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i2);
                } catch (AbstractMethodError e2) {
                    Log.e("ViewCompat", view.getParent().getClass().getSimpleName() + " does not fully implement ViewParent", e2);
                }
            }
        }
    }

    public static void S(View view, int i2) {
        view.offsetLeftAndRight(i2);
    }

    public static void T(View view, int i2) {
        view.offsetTopAndBottom(i2);
    }

    public static WindowInsetsCompat U(View view, WindowInsetsCompat windowInsetsCompat) {
        WindowInsets v = windowInsetsCompat.v();
        if (v != null) {
            WindowInsets b2 = Api20Impl.b(view, v);
            if (!b2.equals(v)) {
                return WindowInsetsCompat.x(b2, view);
            }
        }
        return windowInsetsCompat;
    }

    public static void V(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        view.onInitializeAccessibilityNodeInfo(accessibilityNodeInfoCompat.N0());
    }

    private static AccessibilityViewProperty W() {
        return new AccessibilityViewProperty<CharSequence>(R.id.tag_accessibility_pane_title, CharSequence.class, 8, 28) { // from class: androidx.core.view.ViewCompat.2
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            /* renamed from: h, reason: merged with bridge method [inline-methods] */
            public CharSequence c(View view) {
                return Api28Impl.b(view);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            /* renamed from: i, reason: merged with bridge method [inline-methods] */
            public void d(View view, CharSequence charSequence) {
                Api28Impl.h(view, charSequence);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            /* renamed from: j, reason: merged with bridge method [inline-methods] */
            public boolean g(CharSequence charSequence, CharSequence charSequence2) {
                return !TextUtils.equals(charSequence, charSequence2);
            }
        };
    }

    public static boolean X(View view, int i2, Bundle bundle) {
        return view.performAccessibilityAction(i2, bundle);
    }

    public static ContentInfoCompat Y(View view, ContentInfoCompat contentInfoCompat) {
        if (Log.isLoggable("ViewCompat", 3)) {
            Log.d("ViewCompat", "performReceiveContent: " + contentInfoCompat + ", view=" + view.getClass().getSimpleName() + "[" + view.getId() + "]");
        }
        return Api31Impl.b(view, contentInfoCompat);
    }

    public static void Z(View view) {
        view.postInvalidateOnAnimation();
    }

    private static AccessibilityViewProperty a() {
        return new AccessibilityViewProperty<Boolean>(R.id.tag_accessibility_heading, Boolean.class, 28) { // from class: androidx.core.view.ViewCompat.4
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            /* renamed from: h, reason: merged with bridge method [inline-methods] */
            public Boolean c(View view) {
                return Boolean.valueOf(Api28Impl.c(view));
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            /* renamed from: i, reason: merged with bridge method [inline-methods] */
            public void d(View view, Boolean bool) {
                Api28Impl.g(view, bool.booleanValue());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            /* renamed from: j, reason: merged with bridge method [inline-methods] */
            public boolean g(Boolean bool, Boolean bool2) {
                return !a(bool, bool2);
            }
        };
    }

    public static void a0(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static int b(View view, CharSequence charSequence, AccessibilityViewCommand accessibilityViewCommand) {
        int n2 = n(view, charSequence);
        if (n2 != -1) {
            c(view, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(n2, charSequence, accessibilityViewCommand));
        }
        return n2;
    }

    public static void b0(View view, Runnable runnable, long j2) {
        view.postOnAnimationDelayed(runnable, j2);
    }

    private static void c(View view, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat) {
        h(view);
        d0(accessibilityActionCompat.b(), view);
        m(view).add(accessibilityActionCompat);
        R(view, 0);
    }

    public static void c0(View view, int i2) {
        d0(i2, view);
        R(view, 0);
    }

    public static ViewPropertyAnimatorCompat d(View view) {
        if (f3373a == null) {
            f3373a = new WeakHashMap();
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = (ViewPropertyAnimatorCompat) f3373a.get(view);
        if (viewPropertyAnimatorCompat != null) {
            return viewPropertyAnimatorCompat;
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2 = new ViewPropertyAnimatorCompat(view);
        f3373a.put(view, viewPropertyAnimatorCompat2);
        return viewPropertyAnimatorCompat2;
    }

    private static void d0(int i2, View view) {
        List m2 = m(view);
        for (int i3 = 0; i3 < m2.size(); i3++) {
            if (((AccessibilityNodeInfoCompat.AccessibilityActionCompat) m2.get(i3)).b() == i2) {
                m2.remove(i3);
                return;
            }
        }
    }

    public static WindowInsetsCompat e(View view, WindowInsetsCompat windowInsetsCompat, Rect rect) {
        return Api21Impl.b(view, windowInsetsCompat, rect);
    }

    public static void e0(View view, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat, CharSequence charSequence, AccessibilityViewCommand accessibilityViewCommand) {
        if (accessibilityViewCommand == null && charSequence == null) {
            c0(view, accessibilityActionCompat.b());
        } else {
            c(view, accessibilityActionCompat.a(charSequence, accessibilityViewCommand));
        }
    }

    public static WindowInsetsCompat f(View view, WindowInsetsCompat windowInsetsCompat) {
        WindowInsets v = windowInsetsCompat.v();
        if (v != null) {
            WindowInsets a2 = Api20Impl.a(view, v);
            if (!a2.equals(v)) {
                return WindowInsetsCompat.x(a2, view);
            }
        }
        return windowInsetsCompat;
    }

    public static void f0(View view) {
        Api20Impl.c(view);
    }

    static boolean g(View view, KeyEvent keyEvent) {
        return false;
    }

    public static void g0(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i2, int i3) {
        Api29Impl.d(view, context, iArr, attributeSet, typedArray, i2, i3);
    }

    static void h(View view) {
        AccessibilityDelegateCompat j2 = j(view);
        if (j2 == null) {
            j2 = new AccessibilityDelegateCompat();
        }
        i0(view, j2);
    }

    private static AccessibilityViewProperty h0() {
        return new AccessibilityViewProperty<Boolean>(R.id.tag_screen_reader_focusable, Boolean.class, 28) { // from class: androidx.core.view.ViewCompat.1
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            /* renamed from: h, reason: merged with bridge method [inline-methods] */
            public Boolean c(View view) {
                return Boolean.valueOf(Api28Impl.d(view));
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            /* renamed from: i, reason: merged with bridge method [inline-methods] */
            public void d(View view, Boolean bool) {
                Api28Impl.j(view, bool.booleanValue());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
            /* renamed from: j, reason: merged with bridge method [inline-methods] */
            public boolean g(Boolean bool, Boolean bool2) {
                return !a(bool, bool2);
            }
        };
    }

    public static int i() {
        return View.generateViewId();
    }

    public static void i0(View view, AccessibilityDelegateCompat accessibilityDelegateCompat) {
        if (accessibilityDelegateCompat == null && (k(view) instanceof AccessibilityDelegateCompat.AccessibilityDelegateAdapter)) {
            accessibilityDelegateCompat = new AccessibilityDelegateCompat();
        }
        t0(view);
        view.setAccessibilityDelegate(accessibilityDelegateCompat == null ? null : accessibilityDelegateCompat.d());
    }

    public static AccessibilityDelegateCompat j(View view) {
        View.AccessibilityDelegate k2 = k(view);
        if (k2 == null) {
            return null;
        }
        return k2 instanceof AccessibilityDelegateCompat.AccessibilityDelegateAdapter ? ((AccessibilityDelegateCompat.AccessibilityDelegateAdapter) k2).f3309a : new AccessibilityDelegateCompat(k2);
    }

    public static void j0(View view, boolean z) {
        a().f(view, Boolean.valueOf(z));
    }

    private static View.AccessibilityDelegate k(View view) {
        return Api29Impl.a(view);
    }

    public static void k0(View view, int i2) {
        view.setAccessibilityLiveRegion(i2);
    }

    public static CharSequence l(View view) {
        return (CharSequence) W().e(view);
    }

    public static void l0(View view, CharSequence charSequence) {
        W().f(view, charSequence);
        if (charSequence != null) {
            f3376d.a(view);
        } else {
            f3376d.c(view);
        }
    }

    private static List m(View view) {
        ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_accessibility_actions);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        view.setTag(R.id.tag_accessibility_actions, arrayList2);
        return arrayList2;
    }

    public static void m0(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    private static int n(View view, CharSequence charSequence) {
        List m2 = m(view);
        for (int i2 = 0; i2 < m2.size(); i2++) {
            if (TextUtils.equals(charSequence, ((AccessibilityNodeInfoCompat.AccessibilityActionCompat) m2.get(i2)).c())) {
                return ((AccessibilityNodeInfoCompat.AccessibilityActionCompat) m2.get(i2)).b();
            }
        }
        int i3 = -1;
        int i4 = 0;
        while (true) {
            int[] iArr = f3374b;
            if (i4 >= iArr.length || i3 != -1) {
                break;
            }
            int i5 = iArr[i4];
            boolean z = true;
            for (int i6 = 0; i6 < m2.size(); i6++) {
                z &= ((AccessibilityNodeInfoCompat.AccessibilityActionCompat) m2.get(i6)).b() != i5;
            }
            if (z) {
                i3 = i5;
            }
            i4++;
        }
        return i3;
    }

    public static void n0(View view, ColorStateList colorStateList) {
        Api21Impl.q(view, colorStateList);
    }

    public static ColorStateList o(View view) {
        return Api21Impl.g(view);
    }

    public static void o0(View view, PorterDuff.Mode mode) {
        Api21Impl.r(view, mode);
    }

    public static PorterDuff.Mode p(View view) {
        return Api21Impl.h(view);
    }

    public static void p0(View view, Rect rect) {
        view.setClipBounds(rect);
    }

    public static Display q(View view) {
        return view.getDisplay();
    }

    public static void q0(View view, float f2) {
        Api21Impl.s(view, f2);
    }

    public static float r(View view) {
        return Api21Impl.i(view);
    }

    public static void r0(View view, boolean z) {
        view.setFitsSystemWindows(z);
    }

    public static boolean s(View view) {
        return view.getFitsSystemWindows();
    }

    public static void s0(View view, int i2) {
        view.setImportantForAccessibility(i2);
    }

    public static int t(View view) {
        return view.getImportantForAccessibility();
    }

    private static void t0(View view) {
        if (view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
        }
    }

    public static int u(View view) {
        return Api26Impl.c(view);
    }

    public static void u0(View view, int i2) {
        Api26Impl.m(view, i2);
    }

    public static int v(View view) {
        return view.getLayoutDirection();
    }

    public static void v0(View view, int i2) {
        view.setLabelFor(i2);
    }

    public static int w(View view) {
        return view.getMinimumHeight();
    }

    public static void w0(View view, Paint paint) {
        view.setLayerPaint(paint);
    }

    public static int x(View view) {
        return view.getMinimumWidth();
    }

    public static void x0(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        Api21Impl.u(view, onApplyWindowInsetsListener);
    }

    public static int y(View view) {
        return view.getPaddingEnd();
    }

    public static void y0(View view, int i2, int i3, int i4, int i5) {
        view.setPaddingRelative(i2, i3, i4, i5);
    }

    public static int z(View view) {
        return view.getPaddingStart();
    }

    public static void z0(View view, PointerIconCompat pointerIconCompat) {
        Api24Impl.d(view, (PointerIcon) (pointerIconCompat != null ? pointerIconCompat.a() : null));
    }
}
