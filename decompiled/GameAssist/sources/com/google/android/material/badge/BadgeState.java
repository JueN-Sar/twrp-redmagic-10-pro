package com.google.android.material.badge;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.annotation.RestrictTo;
import com.google.android.material.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import java.util.Locale;

@RestrictTo
/* loaded from: classes.dex */
public final class BadgeState {

    /* renamed from: a, reason: collision with root package name */
    private final State f13924a;

    /* renamed from: b, reason: collision with root package name */
    private final State f13925b;

    /* renamed from: c, reason: collision with root package name */
    final float f13926c;

    /* renamed from: d, reason: collision with root package name */
    final float f13927d;

    /* renamed from: e, reason: collision with root package name */
    final float f13928e;

    /* renamed from: f, reason: collision with root package name */
    final float f13929f;

    /* renamed from: g, reason: collision with root package name */
    final float f13930g;

    /* renamed from: h, reason: collision with root package name */
    final float f13931h;

    /* renamed from: i, reason: collision with root package name */
    final int f13932i;

    /* renamed from: j, reason: collision with root package name */
    final int f13933j;

    /* renamed from: k, reason: collision with root package name */
    int f13934k;

    BadgeState(Context context, int i2, int i3, int i4, State state) {
        State state2 = new State();
        this.f13925b = state2;
        state = state == null ? new State() : state;
        if (i2 != 0) {
            state.f13935c = i2;
        }
        TypedArray a2 = a(context, state.f13935c, i3, i4);
        Resources resources = context.getResources();
        this.f13926c = a2.getDimensionPixelSize(R.styleable.Badge_badgeRadius, -1);
        this.f13932i = context.getResources().getDimensionPixelSize(R.dimen.mtrl_badge_horizontal_edge_offset);
        this.f13933j = context.getResources().getDimensionPixelSize(R.dimen.mtrl_badge_text_horizontal_edge_offset);
        this.f13927d = a2.getDimensionPixelSize(R.styleable.Badge_badgeWithTextRadius, -1);
        this.f13928e = a2.getDimension(R.styleable.Badge_badgeWidth, resources.getDimension(R.dimen.m3_badge_size));
        this.f13930g = a2.getDimension(R.styleable.Badge_badgeWithTextWidth, resources.getDimension(R.dimen.m3_badge_with_text_size));
        this.f13929f = a2.getDimension(R.styleable.Badge_badgeHeight, resources.getDimension(R.dimen.m3_badge_size));
        this.f13931h = a2.getDimension(R.styleable.Badge_badgeWithTextHeight, resources.getDimension(R.dimen.m3_badge_with_text_size));
        boolean z = true;
        this.f13934k = a2.getInt(R.styleable.Badge_offsetAlignmentMode, 1);
        state2.f13943o = state.f13943o == -2 ? 255 : state.f13943o;
        if (state.f13945q != -2) {
            state2.f13945q = state.f13945q;
        } else if (a2.hasValue(R.styleable.Badge_number)) {
            state2.f13945q = a2.getInt(R.styleable.Badge_number, 0);
        } else {
            state2.f13945q = -1;
        }
        if (state.f13944p != null) {
            state2.f13944p = state.f13944p;
        } else if (a2.hasValue(R.styleable.Badge_badgeText)) {
            state2.f13944p = a2.getString(R.styleable.Badge_badgeText);
        }
        state2.u = state.u;
        state2.v = state.v == null ? context.getString(R.string.mtrl_badge_numberless_content_description) : state.v;
        state2.w = state.w == 0 ? R.plurals.mtrl_badge_content_description : state.w;
        state2.x = state.x == 0 ? R.string.mtrl_exceed_max_badge_number_content_description : state.x;
        if (state.z != null && !state.z.booleanValue()) {
            z = false;
        }
        state2.z = Boolean.valueOf(z);
        state2.f13946r = state.f13946r == -2 ? a2.getInt(R.styleable.Badge_maxCharacterCount, -2) : state.f13946r;
        state2.f13947s = state.f13947s == -2 ? a2.getInt(R.styleable.Badge_maxNumber, -2) : state.f13947s;
        state2.f13939k = Integer.valueOf(state.f13939k == null ? a2.getResourceId(R.styleable.Badge_badgeShapeAppearance, R.style.ShapeAppearance_M3_Sys_Shape_Corner_Full) : state.f13939k.intValue());
        state2.f13940l = Integer.valueOf(state.f13940l == null ? a2.getResourceId(R.styleable.Badge_badgeShapeAppearanceOverlay, 0) : state.f13940l.intValue());
        state2.f13941m = Integer.valueOf(state.f13941m == null ? a2.getResourceId(R.styleable.Badge_badgeWithTextShapeAppearance, R.style.ShapeAppearance_M3_Sys_Shape_Corner_Full) : state.f13941m.intValue());
        state2.f13942n = Integer.valueOf(state.f13942n == null ? a2.getResourceId(R.styleable.Badge_badgeWithTextShapeAppearanceOverlay, 0) : state.f13942n.intValue());
        state2.f13936h = Integer.valueOf(state.f13936h == null ? H(context, a2, R.styleable.Badge_backgroundColor) : state.f13936h.intValue());
        state2.f13938j = Integer.valueOf(state.f13938j == null ? a2.getResourceId(R.styleable.Badge_badgeTextAppearance, R.style.TextAppearance_MaterialComponents_Badge) : state.f13938j.intValue());
        if (state.f13937i != null) {
            state2.f13937i = state.f13937i;
        } else if (a2.hasValue(R.styleable.Badge_badgeTextColor)) {
            state2.f13937i = Integer.valueOf(H(context, a2, R.styleable.Badge_badgeTextColor));
        } else {
            state2.f13937i = Integer.valueOf(new TextAppearance(context, state2.f13938j.intValue()).h().getDefaultColor());
        }
        state2.y = Integer.valueOf(state.y == null ? a2.getInt(R.styleable.Badge_badgeGravity, MaterialCardView.CHECKED_ICON_GRAVITY_TOP_END) : state.y.intValue());
        state2.A = Integer.valueOf(state.A == null ? a2.getDimensionPixelSize(R.styleable.Badge_badgeWidePadding, resources.getDimensionPixelSize(R.dimen.mtrl_badge_long_text_horizontal_padding)) : state.A.intValue());
        state2.B = Integer.valueOf(state.B == null ? a2.getDimensionPixelSize(R.styleable.Badge_badgeVerticalPadding, resources.getDimensionPixelSize(R.dimen.m3_badge_with_text_vertical_padding)) : state.B.intValue());
        state2.C = Integer.valueOf(state.C == null ? a2.getDimensionPixelOffset(R.styleable.Badge_horizontalOffset, 0) : state.C.intValue());
        state2.D = Integer.valueOf(state.D == null ? a2.getDimensionPixelOffset(R.styleable.Badge_verticalOffset, 0) : state.D.intValue());
        state2.E = Integer.valueOf(state.E == null ? a2.getDimensionPixelOffset(R.styleable.Badge_horizontalOffsetWithText, state2.C.intValue()) : state.E.intValue());
        state2.F = Integer.valueOf(state.F == null ? a2.getDimensionPixelOffset(R.styleable.Badge_verticalOffsetWithText, state2.D.intValue()) : state.F.intValue());
        state2.I = Integer.valueOf(state.I == null ? a2.getDimensionPixelOffset(R.styleable.Badge_largeFontVerticalOffsetAdjustment, 0) : state.I.intValue());
        state2.G = Integer.valueOf(state.G == null ? 0 : state.G.intValue());
        state2.H = Integer.valueOf(state.H == null ? 0 : state.H.intValue());
        state2.J = Boolean.valueOf(state.J == null ? a2.getBoolean(R.styleable.Badge_autoAdjustToWithinGrandparentBounds, false) : state.J.booleanValue());
        a2.recycle();
        if (state.t == null) {
            state2.t = Locale.getDefault(Locale.Category.FORMAT);
        } else {
            state2.t = state.t;
        }
        this.f13924a = state;
    }

    private static int H(Context context, TypedArray typedArray, int i2) {
        return MaterialResources.a(context, typedArray, i2).getDefaultColor();
    }

    private TypedArray a(Context context, int i2, int i3, int i4) {
        AttributeSet attributeSet;
        int i5;
        if (i2 != 0) {
            attributeSet = DrawableUtils.k(context, i2, "badge");
            i5 = attributeSet.getStyleAttribute();
        } else {
            attributeSet = null;
            i5 = 0;
        }
        return ThemeEnforcement.i(context, attributeSet, R.styleable.Badge, i3, i5 == 0 ? i4 : i5, new int[0]);
    }

    int A() {
        return this.f13925b.f13938j.intValue();
    }

    int B() {
        return this.f13925b.F.intValue();
    }

    int C() {
        return this.f13925b.D.intValue();
    }

    boolean D() {
        return this.f13925b.f13945q != -1;
    }

    boolean E() {
        return this.f13925b.f13944p != null;
    }

    boolean F() {
        return this.f13925b.J.booleanValue();
    }

    boolean G() {
        return this.f13925b.z.booleanValue();
    }

    void I(int i2) {
        this.f13924a.G = Integer.valueOf(i2);
        this.f13925b.G = Integer.valueOf(i2);
    }

    void J(int i2) {
        this.f13924a.H = Integer.valueOf(i2);
        this.f13925b.H = Integer.valueOf(i2);
    }

    void K(int i2) {
        this.f13924a.f13943o = i2;
        this.f13925b.f13943o = i2;
    }

    int b() {
        return this.f13925b.G.intValue();
    }

    int c() {
        return this.f13925b.H.intValue();
    }

    int d() {
        return this.f13925b.f13943o;
    }

    int e() {
        return this.f13925b.f13936h.intValue();
    }

    int f() {
        return this.f13925b.y.intValue();
    }

    int g() {
        return this.f13925b.A.intValue();
    }

    int h() {
        return this.f13925b.f13940l.intValue();
    }

    int i() {
        return this.f13925b.f13939k.intValue();
    }

    int j() {
        return this.f13925b.f13937i.intValue();
    }

    int k() {
        return this.f13925b.B.intValue();
    }

    int l() {
        return this.f13925b.f13942n.intValue();
    }

    int m() {
        return this.f13925b.f13941m.intValue();
    }

    int n() {
        return this.f13925b.x;
    }

    CharSequence o() {
        return this.f13925b.u;
    }

    CharSequence p() {
        return this.f13925b.v;
    }

    int q() {
        return this.f13925b.w;
    }

    int r() {
        return this.f13925b.E.intValue();
    }

    int s() {
        return this.f13925b.C.intValue();
    }

    int t() {
        return this.f13925b.I.intValue();
    }

    int u() {
        return this.f13925b.f13946r;
    }

    int v() {
        return this.f13925b.f13947s;
    }

    int w() {
        return this.f13925b.f13945q;
    }

    Locale x() {
        return this.f13925b.t;
    }

    State y() {
        return this.f13924a;
    }

    String z() {
        return this.f13925b.f13944p;
    }

    public static final class State implements Parcelable {
        public static final Parcelable.Creator<State> CREATOR = new Parcelable.Creator<State>() { // from class: com.google.android.material.badge.BadgeState.State.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public State createFromParcel(Parcel parcel) {
                return new State(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public State[] newArray(int i2) {
                return new State[i2];
            }
        };
        private Integer A;
        private Integer B;
        private Integer C;
        private Integer D;
        private Integer E;
        private Integer F;
        private Integer G;
        private Integer H;
        private Integer I;
        private Boolean J;

        /* renamed from: c, reason: collision with root package name */
        private int f13935c;

        /* renamed from: h, reason: collision with root package name */
        private Integer f13936h;

        /* renamed from: i, reason: collision with root package name */
        private Integer f13937i;

        /* renamed from: j, reason: collision with root package name */
        private Integer f13938j;

        /* renamed from: k, reason: collision with root package name */
        private Integer f13939k;

        /* renamed from: l, reason: collision with root package name */
        private Integer f13940l;

        /* renamed from: m, reason: collision with root package name */
        private Integer f13941m;

        /* renamed from: n, reason: collision with root package name */
        private Integer f13942n;

        /* renamed from: o, reason: collision with root package name */
        private int f13943o;

        /* renamed from: p, reason: collision with root package name */
        private String f13944p;

        /* renamed from: q, reason: collision with root package name */
        private int f13945q;

        /* renamed from: r, reason: collision with root package name */
        private int f13946r;

        /* renamed from: s, reason: collision with root package name */
        private int f13947s;
        private Locale t;
        private CharSequence u;
        private CharSequence v;
        private int w;
        private int x;
        private Integer y;
        private Boolean z;

        public State() {
            this.f13943o = 255;
            this.f13945q = -2;
            this.f13946r = -2;
            this.f13947s = -2;
            this.z = Boolean.TRUE;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f13935c);
            parcel.writeSerializable(this.f13936h);
            parcel.writeSerializable(this.f13937i);
            parcel.writeSerializable(this.f13938j);
            parcel.writeSerializable(this.f13939k);
            parcel.writeSerializable(this.f13940l);
            parcel.writeSerializable(this.f13941m);
            parcel.writeSerializable(this.f13942n);
            parcel.writeInt(this.f13943o);
            parcel.writeString(this.f13944p);
            parcel.writeInt(this.f13945q);
            parcel.writeInt(this.f13946r);
            parcel.writeInt(this.f13947s);
            CharSequence charSequence = this.u;
            parcel.writeString(charSequence != null ? charSequence.toString() : null);
            CharSequence charSequence2 = this.v;
            parcel.writeString(charSequence2 != null ? charSequence2.toString() : null);
            parcel.writeInt(this.w);
            parcel.writeSerializable(this.y);
            parcel.writeSerializable(this.A);
            parcel.writeSerializable(this.B);
            parcel.writeSerializable(this.C);
            parcel.writeSerializable(this.D);
            parcel.writeSerializable(this.E);
            parcel.writeSerializable(this.F);
            parcel.writeSerializable(this.I);
            parcel.writeSerializable(this.G);
            parcel.writeSerializable(this.H);
            parcel.writeSerializable(this.z);
            parcel.writeSerializable(this.t);
            parcel.writeSerializable(this.J);
        }

        State(Parcel parcel) {
            this.f13943o = 255;
            this.f13945q = -2;
            this.f13946r = -2;
            this.f13947s = -2;
            this.z = Boolean.TRUE;
            this.f13935c = parcel.readInt();
            this.f13936h = (Integer) parcel.readSerializable();
            this.f13937i = (Integer) parcel.readSerializable();
            this.f13938j = (Integer) parcel.readSerializable();
            this.f13939k = (Integer) parcel.readSerializable();
            this.f13940l = (Integer) parcel.readSerializable();
            this.f13941m = (Integer) parcel.readSerializable();
            this.f13942n = (Integer) parcel.readSerializable();
            this.f13943o = parcel.readInt();
            this.f13944p = parcel.readString();
            this.f13945q = parcel.readInt();
            this.f13946r = parcel.readInt();
            this.f13947s = parcel.readInt();
            this.u = parcel.readString();
            this.v = parcel.readString();
            this.w = parcel.readInt();
            this.y = (Integer) parcel.readSerializable();
            this.A = (Integer) parcel.readSerializable();
            this.B = (Integer) parcel.readSerializable();
            this.C = (Integer) parcel.readSerializable();
            this.D = (Integer) parcel.readSerializable();
            this.E = (Integer) parcel.readSerializable();
            this.F = (Integer) parcel.readSerializable();
            this.I = (Integer) parcel.readSerializable();
            this.G = (Integer) parcel.readSerializable();
            this.H = (Integer) parcel.readSerializable();
            this.z = (Boolean) parcel.readSerializable();
            this.t = (Locale) parcel.readSerializable();
            this.J = (Boolean) parcel.readSerializable();
        }
    }
}
