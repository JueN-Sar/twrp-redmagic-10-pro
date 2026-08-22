package androidx.core.view.accessibility;

import android.R;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class AccessibilityNodeInfoCompat {

    /* renamed from: a, reason: collision with root package name */
    private final AccessibilityNodeInfo f3479a;

    /* renamed from: b, reason: collision with root package name */
    public int f3480b = -1;

    /* renamed from: c, reason: collision with root package name */
    private int f3481c = -1;

    public static class AccessibilityActionCompat {
        public static final AccessibilityActionCompat A;
        public static final AccessibilityActionCompat B;
        public static final AccessibilityActionCompat C;
        public static final AccessibilityActionCompat D;
        public static final AccessibilityActionCompat E;
        public static final AccessibilityActionCompat F;
        public static final AccessibilityActionCompat G;
        public static final AccessibilityActionCompat H;
        public static final AccessibilityActionCompat I;
        public static final AccessibilityActionCompat J;
        public static final AccessibilityActionCompat K;
        public static final AccessibilityActionCompat L;
        public static final AccessibilityActionCompat M;
        public static final AccessibilityActionCompat N;
        public static final AccessibilityActionCompat O;
        public static final AccessibilityActionCompat P;
        public static final AccessibilityActionCompat Q;
        public static final AccessibilityActionCompat R;
        public static final AccessibilityActionCompat S;
        public static final AccessibilityActionCompat T;
        public static final AccessibilityActionCompat U;
        public static final AccessibilityActionCompat V;

        /* renamed from: e, reason: collision with root package name */
        public static final AccessibilityActionCompat f3482e = new AccessibilityActionCompat(1, null);

        /* renamed from: f, reason: collision with root package name */
        public static final AccessibilityActionCompat f3483f = new AccessibilityActionCompat(2, null);

        /* renamed from: g, reason: collision with root package name */
        public static final AccessibilityActionCompat f3484g = new AccessibilityActionCompat(4, null);

        /* renamed from: h, reason: collision with root package name */
        public static final AccessibilityActionCompat f3485h = new AccessibilityActionCompat(8, null);

        /* renamed from: i, reason: collision with root package name */
        public static final AccessibilityActionCompat f3486i = new AccessibilityActionCompat(16, null);

        /* renamed from: j, reason: collision with root package name */
        public static final AccessibilityActionCompat f3487j = new AccessibilityActionCompat(32, null);

        /* renamed from: k, reason: collision with root package name */
        public static final AccessibilityActionCompat f3488k = new AccessibilityActionCompat(64, null);

        /* renamed from: l, reason: collision with root package name */
        public static final AccessibilityActionCompat f3489l = new AccessibilityActionCompat(128, null);

        /* renamed from: m, reason: collision with root package name */
        public static final AccessibilityActionCompat f3490m = new AccessibilityActionCompat(256, (CharSequence) null, AccessibilityViewCommand.MoveAtGranularityArguments.class);

        /* renamed from: n, reason: collision with root package name */
        public static final AccessibilityActionCompat f3491n = new AccessibilityActionCompat(512, (CharSequence) null, AccessibilityViewCommand.MoveAtGranularityArguments.class);

        /* renamed from: o, reason: collision with root package name */
        public static final AccessibilityActionCompat f3492o = new AccessibilityActionCompat(1024, (CharSequence) null, AccessibilityViewCommand.MoveHtmlArguments.class);

        /* renamed from: p, reason: collision with root package name */
        public static final AccessibilityActionCompat f3493p = new AccessibilityActionCompat(2048, (CharSequence) null, AccessibilityViewCommand.MoveHtmlArguments.class);

        /* renamed from: q, reason: collision with root package name */
        public static final AccessibilityActionCompat f3494q = new AccessibilityActionCompat(4096, null);

        /* renamed from: r, reason: collision with root package name */
        public static final AccessibilityActionCompat f3495r = new AccessibilityActionCompat(8192, null);

        /* renamed from: s, reason: collision with root package name */
        public static final AccessibilityActionCompat f3496s = new AccessibilityActionCompat(16384, null);
        public static final AccessibilityActionCompat t = new AccessibilityActionCompat(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FORCE_DRAW_BAR_BACKGROUNDS, null);
        public static final AccessibilityActionCompat u = new AccessibilityActionCompat(65536, null);
        public static final AccessibilityActionCompat v = new AccessibilityActionCompat(131072, (CharSequence) null, AccessibilityViewCommand.SetSelectionArguments.class);
        public static final AccessibilityActionCompat w = new AccessibilityActionCompat(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_OVERRIDE_LAYOUT_IN_DISPLAY_CUTOUT_MODE, null);
        public static final AccessibilityActionCompat x = new AccessibilityActionCompat(WindowManagerWrapper.LayoutParams.SYSTEM_FLAG_HIDE_NON_SYSTEM_OVERLAY_WINDOWS, null);
        public static final AccessibilityActionCompat y = new AccessibilityActionCompat(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_IS_ROUNDED_CORNERS_OVERLAY, null);
        public static final AccessibilityActionCompat z = new AccessibilityActionCompat(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_EXCLUDE_FROM_SCREEN_MAGNIFICATION, (CharSequence) null, AccessibilityViewCommand.SetTextArguments.class);

        /* renamed from: a, reason: collision with root package name */
        final Object f3497a;

        /* renamed from: b, reason: collision with root package name */
        private final int f3498b;

        /* renamed from: c, reason: collision with root package name */
        private final Class f3499c;

        /* renamed from: d, reason: collision with root package name */
        protected final AccessibilityViewCommand f3500d;

        static {
            int i2 = Build.VERSION.SDK_INT;
            A = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN, R.id.accessibilityActionShowOnScreen, null, null, null);
            B = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION, R.id.accessibilityActionScrollToPosition, null, null, AccessibilityViewCommand.ScrollToPositionArguments.class);
            C = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP, R.id.accessibilityActionScrollUp, null, null, null);
            D = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT, R.id.accessibilityActionScrollLeft, null, null, null);
            E = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN, R.id.accessibilityActionScrollDown, null, null, null);
            F = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT, R.id.accessibilityActionScrollRight, null, null, null);
            G = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP, R.id.accessibilityActionPageUp, null, null, null);
            H = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN, R.id.accessibilityActionPageDown, null, null, null);
            I = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT, R.id.accessibilityActionPageLeft, null, null, null);
            J = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT, R.id.accessibilityActionPageRight, null, null, null);
            K = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK, R.id.accessibilityActionContextClick, null, null, null);
            L = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS, R.id.accessibilityActionSetProgress, null, null, AccessibilityViewCommand.SetProgressArguments.class);
            M = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW, R.id.accessibilityActionMoveWindow, null, null, AccessibilityViewCommand.MoveWindowArguments.class);
            N = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP, R.id.accessibilityActionShowTooltip, null, null, null);
            O = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP, R.id.accessibilityActionHideTooltip, null, null, null);
            P = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD, R.id.accessibilityActionPressAndHold, null, null, null);
            Q = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER, R.id.accessibilityActionImeEnter, null, null, null);
            R = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_START, R.id.accessibilityActionDragStart, null, null, null);
            S = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_DROP, R.id.accessibilityActionDragDrop, null, null, null);
            T = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_CANCEL, R.id.accessibilityActionDragCancel, null, null, null);
            U = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TEXT_SUGGESTIONS, R.id.accessibilityActionShowTextSuggestions, null, null, null);
            V = new AccessibilityActionCompat(i2 >= 34 ? Api34Impl.a() : null, R.id.accessibilityActionScrollInDirection, null, null, null);
        }

        public AccessibilityActionCompat(int i2, CharSequence charSequence) {
            this(null, i2, charSequence, null, null);
        }

        public AccessibilityActionCompat a(CharSequence charSequence, AccessibilityViewCommand accessibilityViewCommand) {
            return new AccessibilityActionCompat(null, this.f3498b, charSequence, accessibilityViewCommand, this.f3499c);
        }

        public int b() {
            return ((AccessibilityNodeInfo.AccessibilityAction) this.f3497a).getId();
        }

        public CharSequence c() {
            return ((AccessibilityNodeInfo.AccessibilityAction) this.f3497a).getLabel();
        }

        public boolean d(View view, Bundle bundle) {
            AccessibilityViewCommand.CommandArguments commandArguments;
            if (this.f3500d == null) {
                return false;
            }
            Class cls = this.f3499c;
            AccessibilityViewCommand.CommandArguments commandArguments2 = null;
            if (cls != null) {
                try {
                    commandArguments = (AccessibilityViewCommand.CommandArguments) cls.getDeclaredConstructor(null).newInstance(null);
                } catch (Exception e2) {
                    e = e2;
                }
                try {
                    commandArguments.a(bundle);
                    commandArguments2 = commandArguments;
                } catch (Exception e3) {
                    e = e3;
                    commandArguments2 = commandArguments;
                    Class cls2 = this.f3499c;
                    Log.e("A11yActionCompat", "Failed to execute command with argument class ViewCommandArgument: " + (cls2 == null ? "null" : cls2.getName()), e);
                    return this.f3500d.a(view, commandArguments2);
                }
            }
            return this.f3500d.a(view, commandArguments2);
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof AccessibilityActionCompat)) {
                return false;
            }
            AccessibilityActionCompat accessibilityActionCompat = (AccessibilityActionCompat) obj;
            Object obj2 = this.f3497a;
            return obj2 == null ? accessibilityActionCompat.f3497a == null : obj2.equals(accessibilityActionCompat.f3497a);
        }

        public int hashCode() {
            Object obj = this.f3497a;
            if (obj != null) {
                return obj.hashCode();
            }
            return 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("AccessibilityActionCompat: ");
            String h2 = AccessibilityNodeInfoCompat.h(this.f3498b);
            if (h2.equals("ACTION_UNKNOWN") && c() != null) {
                h2 = c().toString();
            }
            sb.append(h2);
            return sb.toString();
        }

        public AccessibilityActionCompat(int i2, CharSequence charSequence, AccessibilityViewCommand accessibilityViewCommand) {
            this(null, i2, charSequence, accessibilityViewCommand, null);
        }

        AccessibilityActionCompat(Object obj) {
            this(obj, 0, null, null, null);
        }

        private AccessibilityActionCompat(int i2, CharSequence charSequence, Class cls) {
            this(null, i2, charSequence, null, cls);
        }

        AccessibilityActionCompat(Object obj, int i2, CharSequence charSequence, AccessibilityViewCommand accessibilityViewCommand, Class cls) {
            this.f3498b = i2;
            this.f3500d = accessibilityViewCommand;
            if (obj == null) {
                this.f3497a = new AccessibilityNodeInfo.AccessibilityAction(i2, charSequence);
            } else {
                this.f3497a = obj;
            }
            this.f3499c = cls;
        }
    }

    @RequiresApi
    private static class Api21Impl {
        @DoNotInline
        public static CollectionItemInfoCompat a(int i2, int i3, int i4, int i5, boolean z, boolean z2) {
            return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(i2, i3, i4, i5, z, z2));
        }
    }

    @RequiresApi
    private static class Api30Impl {
        @DoNotInline
        public static Object a(int i2, float f2, float f3, float f4) {
            return new AccessibilityNodeInfo.RangeInfo(i2, f2, f3, f4);
        }

        @DoNotInline
        public static CharSequence b(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.getStateDescription();
        }

        @DoNotInline
        public static void c(AccessibilityNodeInfo accessibilityNodeInfo, CharSequence charSequence) {
            accessibilityNodeInfo.setStateDescription(charSequence);
        }
    }

    @RequiresApi
    private static class Api33Impl {
        @DoNotInline
        public static CollectionItemInfoCompat a(boolean z, int i2, int i3, int i4, int i5, boolean z2, String str, String str2) {
            return new CollectionItemInfoCompat(new AccessibilityNodeInfo.CollectionItemInfo.Builder().setHeading(z).setColumnIndex(i2).setRowIndex(i3).setColumnSpan(i4).setRowSpan(i5).setSelected(z2).setRowTitle(str).setColumnTitle(str2).build());
        }

        @DoNotInline
        public static AccessibilityNodeInfoCompat b(AccessibilityNodeInfo accessibilityNodeInfo, int i2, int i3) {
            return AccessibilityNodeInfoCompat.P0(accessibilityNodeInfo.getChild(i2, i3));
        }

        @DoNotInline
        public static String c(Object obj) {
            return ((AccessibilityNodeInfo.CollectionItemInfo) obj).getColumnTitle();
        }

        @DoNotInline
        public static String d(Object obj) {
            return ((AccessibilityNodeInfo.CollectionItemInfo) obj).getRowTitle();
        }

        @DoNotInline
        public static AccessibilityNodeInfo.ExtraRenderingInfo e(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.getExtraRenderingInfo();
        }

        @DoNotInline
        public static AccessibilityNodeInfoCompat f(AccessibilityNodeInfo accessibilityNodeInfo, int i2) {
            return AccessibilityNodeInfoCompat.P0(accessibilityNodeInfo.getParent(i2));
        }

        @DoNotInline
        public static String g(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.getUniqueId();
        }

        @DoNotInline
        public static boolean h(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.isTextSelectable();
        }

        @DoNotInline
        public static void i(AccessibilityNodeInfo accessibilityNodeInfo, boolean z) {
            accessibilityNodeInfo.setTextSelectable(z);
        }

        @DoNotInline
        public static void j(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
            accessibilityNodeInfo.setUniqueId(str);
        }
    }

    @RequiresApi
    private static class Api34Impl {
        @DoNotInline
        public static AccessibilityNodeInfo.AccessibilityAction a() {
            return AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_IN_DIRECTION;
        }

        @DoNotInline
        public static void b(AccessibilityNodeInfo accessibilityNodeInfo, Rect rect) {
            accessibilityNodeInfo.getBoundsInWindow(rect);
        }

        @DoNotInline
        public static CharSequence c(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.getContainerTitle();
        }

        @DoNotInline
        public static long d(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.getMinDurationBetweenContentChanges().toMillis();
        }

        @DoNotInline
        public static boolean e(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.hasRequestInitialAccessibilityFocus();
        }

        @DoNotInline
        public static boolean f(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.isAccessibilityDataSensitive();
        }

        @DoNotInline
        public static void g(AccessibilityNodeInfo accessibilityNodeInfo, boolean z) {
            accessibilityNodeInfo.setAccessibilityDataSensitive(z);
        }

        @DoNotInline
        public static void h(AccessibilityNodeInfo accessibilityNodeInfo, Rect rect) {
            accessibilityNodeInfo.setBoundsInWindow(rect);
        }

        @DoNotInline
        public static void i(AccessibilityNodeInfo accessibilityNodeInfo, CharSequence charSequence) {
            accessibilityNodeInfo.setContainerTitle(charSequence);
        }

        @DoNotInline
        public static void j(AccessibilityNodeInfo accessibilityNodeInfo, long j2) {
            accessibilityNodeInfo.setMinDurationBetweenContentChanges(Duration.ofMillis(j2));
        }

        @DoNotInline
        public static void k(AccessibilityNodeInfo accessibilityNodeInfo, View view, boolean z) {
            accessibilityNodeInfo.setQueryFromAppProcessEnabled(view, z);
        }

        @DoNotInline
        public static void l(AccessibilityNodeInfo accessibilityNodeInfo, boolean z) {
            accessibilityNodeInfo.setRequestInitialAccessibilityFocus(z);
        }
    }

    public static class CollectionInfoCompat {

        /* renamed from: a, reason: collision with root package name */
        final Object f3501a;

        CollectionInfoCompat(Object obj) {
            this.f3501a = obj;
        }

        public static CollectionInfoCompat a(int i2, int i3, boolean z) {
            return new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(i2, i3, z));
        }

        public static CollectionInfoCompat b(int i2, int i3, boolean z, int i4) {
            return new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(i2, i3, z, i4));
        }
    }

    public static class CollectionItemInfoCompat {

        /* renamed from: a, reason: collision with root package name */
        final Object f3502a;

        public static final class Builder {
        }

        CollectionItemInfoCompat(Object obj) {
            this.f3502a = obj;
        }

        public static CollectionItemInfoCompat a(int i2, int i3, int i4, int i5, boolean z, boolean z2) {
            return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(i2, i3, i4, i5, z, z2));
        }
    }

    public static class RangeInfoCompat {

        /* renamed from: a, reason: collision with root package name */
        final Object f3503a;

        RangeInfoCompat(Object obj) {
            this.f3503a = obj;
        }

        public static RangeInfoCompat a(int i2, float f2, float f3, float f4) {
            return new RangeInfoCompat(AccessibilityNodeInfo.RangeInfo.obtain(i2, f2, f3, f4));
        }
    }

    public static final class TouchDelegateInfoCompat {
    }

    public AccessibilityNodeInfoCompat(Object obj) {
        this.f3479a = (AccessibilityNodeInfo) obj;
    }

    private boolean C() {
        return !f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty();
    }

    public static AccessibilityNodeInfoCompat O0(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new AccessibilityNodeInfoCompat(accessibilityNodeInfo);
    }

    static AccessibilityNodeInfoCompat P0(Object obj) {
        if (obj != null) {
            return new AccessibilityNodeInfoCompat(obj);
        }
        return null;
    }

    public static AccessibilityNodeInfoCompat V() {
        return O0(AccessibilityNodeInfo.obtain());
    }

    public static AccessibilityNodeInfoCompat W(View view) {
        return O0(AccessibilityNodeInfo.obtain(view));
    }

    public static AccessibilityNodeInfoCompat X(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return O0(AccessibilityNodeInfo.obtain(accessibilityNodeInfoCompat.f3479a));
    }

    private List f(String str) {
        ArrayList<Integer> integerArrayList = this.f3479a.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.f3479a.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    static String h(int i2) {
        if (i2 == 1) {
            return "ACTION_FOCUS";
        }
        if (i2 == 2) {
            return "ACTION_CLEAR_FOCUS";
        }
        switch (i2) {
            case 4:
                return "ACTION_SELECT";
            case 8:
                return "ACTION_CLEAR_SELECTION";
            case 16:
                return "ACTION_CLICK";
            case 32:
                return "ACTION_LONG_CLICK";
            case 64:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case 512:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case 1024:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case 8192:
                return "ACTION_SCROLL_BACKWARD";
            case 16384:
                return "ACTION_COPY";
            case WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FORCE_DRAW_BAR_BACKGROUNDS /* 32768 */:
                return "ACTION_PASTE";
            case 65536:
                return "ACTION_CUT";
            case 131072:
                return "ACTION_SET_SELECTION";
            case WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_OVERRIDE_LAYOUT_IN_DISPLAY_CUTOUT_MODE /* 262144 */:
                return "ACTION_EXPAND";
            case WindowManagerWrapper.LayoutParams.SYSTEM_FLAG_HIDE_NON_SYSTEM_OVERLAY_WINDOWS /* 524288 */:
                return "ACTION_COLLAPSE";
            case WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_EXCLUDE_FROM_SCREEN_MAGNIFICATION /* 2097152 */:
                return "ACTION_SET_TEXT";
            case R.id.accessibilityActionMoveWindow:
                return "ACTION_MOVE_WINDOW";
            case R.id.accessibilityActionScrollInDirection:
                return "ACTION_SCROLL_IN_DIRECTION";
            default:
                switch (i2) {
                    case R.id.accessibilityActionShowOnScreen:
                        return "ACTION_SHOW_ON_SCREEN";
                    case R.id.accessibilityActionScrollToPosition:
                        return "ACTION_SCROLL_TO_POSITION";
                    case R.id.accessibilityActionScrollUp:
                        return "ACTION_SCROLL_UP";
                    case R.id.accessibilityActionScrollLeft:
                        return "ACTION_SCROLL_LEFT";
                    case R.id.accessibilityActionScrollDown:
                        return "ACTION_SCROLL_DOWN";
                    case R.id.accessibilityActionScrollRight:
                        return "ACTION_SCROLL_RIGHT";
                    case R.id.accessibilityActionContextClick:
                        return "ACTION_CONTEXT_CLICK";
                    case R.id.accessibilityActionSetProgress:
                        return "ACTION_SET_PROGRESS";
                    default:
                        switch (i2) {
                            case R.id.accessibilityActionShowTooltip:
                                return "ACTION_SHOW_TOOLTIP";
                            case R.id.accessibilityActionHideTooltip:
                                return "ACTION_HIDE_TOOLTIP";
                            case R.id.accessibilityActionPageUp:
                                return "ACTION_PAGE_UP";
                            case R.id.accessibilityActionPageDown:
                                return "ACTION_PAGE_DOWN";
                            case R.id.accessibilityActionPageLeft:
                                return "ACTION_PAGE_LEFT";
                            case R.id.accessibilityActionPageRight:
                                return "ACTION_PAGE_RIGHT";
                            case R.id.accessibilityActionPressAndHold:
                                return "ACTION_PRESS_AND_HOLD";
                            default:
                                switch (i2) {
                                    case R.id.accessibilityActionImeEnter:
                                        return "ACTION_IME_ENTER";
                                    case R.id.accessibilityActionDragStart:
                                        return "ACTION_DRAG_START";
                                    case R.id.accessibilityActionDragDrop:
                                        return "ACTION_DRAG_DROP";
                                    case R.id.accessibilityActionDragCancel:
                                        return "ACTION_DRAG_CANCEL";
                                    default:
                                        return "ACTION_UNKNOWN";
                                }
                        }
                }
        }
    }

    private boolean j(int i2) {
        Bundle t = t();
        return t != null && (t.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & i2) == i2;
    }

    public static ClickableSpan[] p(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            return (ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class);
        }
        return null;
    }

    public String A() {
        return Api33Impl.g(this.f3479a);
    }

    public void A0(View view, int i2) {
        this.f3480b = i2;
        this.f3479a.setParent(view, i2);
    }

    public String B() {
        return this.f3479a.getViewIdResourceName();
    }

    public void B0(RangeInfoCompat rangeInfoCompat) {
        this.f3479a.setRangeInfo((AccessibilityNodeInfo.RangeInfo) rangeInfoCompat.f3503a);
    }

    public void C0(CharSequence charSequence) {
        this.f3479a.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", charSequence);
    }

    public boolean D() {
        return Build.VERSION.SDK_INT >= 34 ? Api34Impl.f(this.f3479a) : j(64);
    }

    public void D0(boolean z) {
        this.f3479a.setScreenReaderFocusable(z);
    }

    public boolean E() {
        return this.f3479a.isAccessibilityFocused();
    }

    public void E0(boolean z) {
        this.f3479a.setScrollable(z);
    }

    public boolean F() {
        return this.f3479a.isCheckable();
    }

    public void F0(boolean z) {
        this.f3479a.setSelected(z);
    }

    public boolean G() {
        return this.f3479a.isChecked();
    }

    public void G0(boolean z) {
        this.f3479a.setShowingHintText(z);
    }

    public boolean H() {
        return this.f3479a.isClickable();
    }

    public void H0(View view) {
        this.f3481c = -1;
        this.f3479a.setSource(view);
    }

    public boolean I() {
        return this.f3479a.isContextClickable();
    }

    public void I0(View view, int i2) {
        this.f3481c = i2;
        this.f3479a.setSource(view, i2);
    }

    public boolean J() {
        return this.f3479a.isEnabled();
    }

    public void J0(CharSequence charSequence) {
        Api30Impl.c(this.f3479a, charSequence);
    }

    public boolean K() {
        return this.f3479a.isFocusable();
    }

    public void K0(CharSequence charSequence) {
        this.f3479a.setText(charSequence);
    }

    public boolean L() {
        return this.f3479a.isFocused();
    }

    public void L0(View view) {
        this.f3479a.setTraversalAfter(view);
    }

    public boolean M() {
        return j(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_OPT_OUT_EDGE_TO_EDGE);
    }

    public void M0(boolean z) {
        this.f3479a.setVisibleToUser(z);
    }

    public boolean N() {
        return this.f3479a.isImportantForAccessibility();
    }

    public AccessibilityNodeInfo N0() {
        return this.f3479a;
    }

    public boolean O() {
        return this.f3479a.isLongClickable();
    }

    public boolean P() {
        return this.f3479a.isPassword();
    }

    public boolean Q() {
        return this.f3479a.isScrollable();
    }

    public boolean R() {
        return this.f3479a.isSelected();
    }

    public boolean S() {
        return this.f3479a.isShowingHintText();
    }

    public boolean T() {
        return Api33Impl.h(this.f3479a);
    }

    public boolean U() {
        return this.f3479a.isVisibleToUser();
    }

    public boolean Y(int i2, Bundle bundle) {
        return this.f3479a.performAction(i2, bundle);
    }

    public void Z() {
    }

    public void a(int i2) {
        this.f3479a.addAction(i2);
    }

    public boolean a0(AccessibilityActionCompat accessibilityActionCompat) {
        return this.f3479a.removeAction((AccessibilityNodeInfo.AccessibilityAction) accessibilityActionCompat.f3497a);
    }

    public void b(AccessibilityActionCompat accessibilityActionCompat) {
        this.f3479a.addAction((AccessibilityNodeInfo.AccessibilityAction) accessibilityActionCompat.f3497a);
    }

    public void b0(boolean z) {
        this.f3479a.setAccessibilityFocused(z);
    }

    public void c(View view) {
        this.f3479a.addChild(view);
    }

    public void c0(Rect rect) {
        this.f3479a.setBoundsInParent(rect);
    }

    public void d(View view, int i2) {
        this.f3479a.addChild(view, i2);
    }

    public void d0(Rect rect) {
        this.f3479a.setBoundsInScreen(rect);
    }

    public void e(CharSequence charSequence, View view) {
    }

    public void e0(boolean z) {
        this.f3479a.setCanOpenPopup(z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AccessibilityNodeInfoCompat)) {
            return false;
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = this.f3479a;
        if (accessibilityNodeInfo == null) {
            if (accessibilityNodeInfoCompat.f3479a != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(accessibilityNodeInfoCompat.f3479a)) {
            return false;
        }
        return this.f3481c == accessibilityNodeInfoCompat.f3481c && this.f3480b == accessibilityNodeInfoCompat.f3480b;
    }

    public void f0(boolean z) {
        this.f3479a.setCheckable(z);
    }

    public List g() {
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = this.f3479a.getActionList();
        if (actionList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int size = actionList.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(new AccessibilityActionCompat(actionList.get(i2)));
        }
        return arrayList;
    }

    public void g0(boolean z) {
        this.f3479a.setChecked(z);
    }

    public void h0(CharSequence charSequence) {
        this.f3479a.setClassName(charSequence);
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f3479a;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public int i() {
        return this.f3479a.getActions();
    }

    public void i0(boolean z) {
        this.f3479a.setClickable(z);
    }

    public void j0(Object obj) {
        this.f3479a.setCollectionInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionInfo) ((CollectionInfoCompat) obj).f3501a);
    }

    public void k(Rect rect) {
        this.f3479a.getBoundsInParent(rect);
    }

    public void k0(Object obj) {
        this.f3479a.setCollectionItemInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionItemInfo) ((CollectionItemInfoCompat) obj).f3502a);
    }

    public void l(Rect rect) {
        this.f3479a.getBoundsInScreen(rect);
    }

    public void l0(CharSequence charSequence) {
        this.f3479a.setContentDescription(charSequence);
    }

    public void m(Rect rect) {
        if (Build.VERSION.SDK_INT >= 34) {
            Api34Impl.b(this.f3479a, rect);
            return;
        }
        Rect rect2 = (Rect) this.f3479a.getExtras().getParcelable("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOUNDS_IN_WINDOW_KEY");
        if (rect2 != null) {
            rect.set(rect2.left, rect2.top, rect2.right, rect2.bottom);
        }
    }

    public void m0(boolean z) {
        this.f3479a.setDismissable(z);
    }

    public int n() {
        return this.f3479a.getChildCount();
    }

    public void n0(boolean z) {
        this.f3479a.setEnabled(z);
    }

    public CharSequence o() {
        return this.f3479a.getClassName();
    }

    public void o0(CharSequence charSequence) {
        this.f3479a.setError(charSequence);
    }

    public void p0(boolean z) {
        this.f3479a.setFocusable(z);
    }

    public CharSequence q() {
        return Build.VERSION.SDK_INT >= 34 ? Api34Impl.c(this.f3479a) : this.f3479a.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.CONTAINER_TITLE_KEY");
    }

    public void q0(boolean z) {
        this.f3479a.setFocused(z);
    }

    public CharSequence r() {
        return this.f3479a.getContentDescription();
    }

    public void r0(boolean z) {
        this.f3479a.setHeading(z);
    }

    public CharSequence s() {
        return this.f3479a.getError();
    }

    public void s0(CharSequence charSequence) {
        this.f3479a.setHintText(charSequence);
    }

    public Bundle t() {
        return this.f3479a.getExtras();
    }

    public void t0(View view) {
        this.f3479a.setLabelFor(view);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        k(rect);
        sb.append("; boundsInParent: " + rect);
        l(rect);
        sb.append("; boundsInScreen: " + rect);
        m(rect);
        sb.append("; boundsInWindow: " + rect);
        sb.append("; packageName: ");
        sb.append(w());
        sb.append("; className: ");
        sb.append(o());
        sb.append("; text: ");
        sb.append(y());
        sb.append("; error: ");
        sb.append(s());
        sb.append("; maxTextLength: ");
        sb.append(u());
        sb.append("; stateDescription: ");
        sb.append(x());
        sb.append("; contentDescription: ");
        sb.append(r());
        sb.append("; tooltipText: ");
        sb.append(z());
        sb.append("; viewIdResName: ");
        sb.append(B());
        sb.append("; uniqueId: ");
        sb.append(A());
        sb.append("; checkable: ");
        sb.append(F());
        sb.append("; checked: ");
        sb.append(G());
        sb.append("; focusable: ");
        sb.append(K());
        sb.append("; focused: ");
        sb.append(L());
        sb.append("; selected: ");
        sb.append(R());
        sb.append("; clickable: ");
        sb.append(H());
        sb.append("; longClickable: ");
        sb.append(O());
        sb.append("; contextClickable: ");
        sb.append(I());
        sb.append("; enabled: ");
        sb.append(J());
        sb.append("; password: ");
        sb.append(P());
        sb.append("; scrollable: " + Q());
        sb.append("; containerTitle: ");
        sb.append(q());
        sb.append("; granularScrollingSupported: ");
        sb.append(M());
        sb.append("; importantForAccessibility: ");
        sb.append(N());
        sb.append("; visible: ");
        sb.append(U());
        sb.append("; isTextSelectable: ");
        sb.append(T());
        sb.append("; accessibilityDataSensitive: ");
        sb.append(D());
        sb.append("; [");
        List g2 = g();
        for (int i2 = 0; i2 < g2.size(); i2++) {
            AccessibilityActionCompat accessibilityActionCompat = (AccessibilityActionCompat) g2.get(i2);
            String h2 = h(accessibilityActionCompat.b());
            if (h2.equals("ACTION_UNKNOWN") && accessibilityActionCompat.c() != null) {
                h2 = accessibilityActionCompat.c().toString();
            }
            sb.append(h2);
            if (i2 != g2.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public int u() {
        return this.f3479a.getMaxTextLength();
    }

    public void u0(boolean z) {
        this.f3479a.setLongClickable(z);
    }

    public int v() {
        return this.f3479a.getMovementGranularities();
    }

    public void v0(int i2) {
        this.f3479a.setMaxTextLength(i2);
    }

    public CharSequence w() {
        return this.f3479a.getPackageName();
    }

    public void w0(int i2) {
        this.f3479a.setMovementGranularities(i2);
    }

    public CharSequence x() {
        return Api30Impl.b(this.f3479a);
    }

    public void x0(CharSequence charSequence) {
        this.f3479a.setPackageName(charSequence);
    }

    public CharSequence y() {
        if (!C()) {
            return this.f3479a.getText();
        }
        List f2 = f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        List f3 = f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        List f4 = f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        List f5 = f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        SpannableString spannableString = new SpannableString(TextUtils.substring(this.f3479a.getText(), 0, this.f3479a.getText().length()));
        for (int i2 = 0; i2 < f2.size(); i2++) {
            spannableString.setSpan(new AccessibilityClickableSpanCompat(((Integer) f5.get(i2)).intValue(), this, t().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), ((Integer) f2.get(i2)).intValue(), ((Integer) f3.get(i2)).intValue(), ((Integer) f4.get(i2)).intValue());
        }
        return spannableString;
    }

    public void y0(CharSequence charSequence) {
        this.f3479a.setPaneTitle(charSequence);
    }

    public CharSequence z() {
        return this.f3479a.getTooltipText();
    }

    public void z0(View view) {
        this.f3480b = -1;
        this.f3479a.setParent(view);
    }

    private AccessibilityNodeInfoCompat(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f3479a = accessibilityNodeInfo;
    }
}
