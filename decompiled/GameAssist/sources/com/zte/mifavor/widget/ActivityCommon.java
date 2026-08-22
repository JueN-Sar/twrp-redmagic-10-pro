package com.zte.mifavor.widget;

import android.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.android.internal.app.ToolbarActionBar;
import com.android.internal.app.WindowDecorActionBar;
import com.android.internal.widget.ToolbarWidgetWrapper;
import com.google.android.material.card.MaterialCardView;
import com.zte.mifavor.utils.UIUtils;
import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
public class ActivityCommon implements MfvActivity {

    /* renamed from: c, reason: collision with root package name */
    private Activity f17514c;

    /* renamed from: h, reason: collision with root package name */
    private Drawable f17515h;

    /* renamed from: j, reason: collision with root package name */
    private int f17517j;

    /* renamed from: k, reason: collision with root package name */
    private int f17518k;

    /* renamed from: l, reason: collision with root package name */
    private boolean f17519l;

    /* renamed from: i, reason: collision with root package name */
    private boolean f17516i = true;

    /* renamed from: m, reason: collision with root package name */
    private TextView f17520m = null;

    /* renamed from: n, reason: collision with root package name */
    private ImageView f17521n = null;

    /* renamed from: o, reason: collision with root package name */
    private ImageView f17522o = null;

    /* renamed from: p, reason: collision with root package name */
    private ImageView f17523p = null;

    /* renamed from: q, reason: collision with root package name */
    private ViewTreeObserver.OnGlobalLayoutListener f17524q = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.zte.mifavor.widget.ActivityCommon.1
        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (ActivityCommon.this.f17514c == null) {
                return;
            }
            ViewTreeObserver viewTreeObserver = ActivityCommon.this.f17514c.getWindow().getDecorView().getViewTreeObserver();
            if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnGlobalLayoutListener(ActivityCommon.this.f17524q);
                ActivityCommon.this.f17524q = null;
            }
            ActivityCommon.this.m("onGlobalLayout...");
            ActivityCommon activityCommon = ActivityCommon.this;
            activityCommon.t(activityCommon.f17515h);
        }
    };

    public ActivityCommon(Activity activity) {
        this.f17519l = false;
        this.f17514c = activity;
        this.f17519l = UIUtils.i(activity);
        m("ActivityCommon mIsCardTheme = " + this.f17519l);
        q();
        j();
    }

    private void j() {
        TypedArray obtainStyledAttributes = this.f17514c.getTheme().obtainStyledAttributes(new int[]{R.attr.statusBarColor, com.zte.extres.R.attr.mifavorStatusBar});
        this.f17517j = obtainStyledAttributes.getColor(0, 0);
        this.f17516i = obtainStyledAttributes.getBoolean(1, true);
        obtainStyledAttributes.recycle();
        if (this.f17516i) {
            int color = this.f17519l ? this.f17514c.getColor(com.zte.extres.R.color.statusbar_color_card) : this.f17514c.getColor(com.zte.extres.R.color.statusbar_color);
            this.f17516i = color == this.f17517j;
            m("init statusBarColor: " + Integer.toHexString(color) + ", mIsMifavorStatusBar=" + this.f17516i);
        }
        if (this.f17519l) {
            this.f17518k = this.f17514c.getColor(com.zte.extres.R.color.mfv_common_card_sk_bg);
        } else {
            this.f17518k = this.f17514c.getColor(com.zte.extres.R.color.mfv_common_sk_divider_line);
        }
    }

    private boolean k(Context context) {
        boolean z = (context.getResources().getConfiguration().uiMode & 48) == 32;
        m("night: " + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ WindowInsetsCompat l(boolean z, View view, WindowInsetsCompat windowInsetsCompat) {
        try {
            Insets f2 = windowInsetsCompat.f(WindowInsetsCompat.Type.e());
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.leftMargin = f2.f2920a;
            marginLayoutParams.rightMargin = f2.f2922c;
            marginLayoutParams.topMargin = f2.f2921b;
            if (z) {
                marginLayoutParams.bottomMargin = 0;
            } else {
                marginLayoutParams.bottomMargin = f2.f2923d;
            }
            Log.e("ActivityCommon", "set Layout Margin. topMargin=" + marginLayoutParams.topMargin + ", bottomMargin=" + marginLayoutParams.bottomMargin + ", isBottom=" + z);
            view.setLayoutParams(marginLayoutParams);
        } catch (Exception e2) {
            Log.e("ActivityCommon", "set Layout Margin error.e=", e2);
        }
        return WindowInsetsCompat.f3439b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(String str) {
    }

    private void s(Drawable drawable) {
        Object obj;
        ActionBar actionBar = this.f17514c.getActionBar();
        m("set NavUp Background in. acb=" + actionBar);
        if (actionBar == null) {
            m("acb == null");
            return;
        }
        try {
            if (actionBar instanceof WindowDecorActionBar) {
                Field declaredField = actionBar.getClass().getDeclaredField("mDecorToolbar");
                declaredField.setAccessible(true);
                obj = declaredField.get(actionBar);
            } else {
                if (!(actionBar instanceof ToolbarActionBar)) {
                    m("unknown actionbar type:" + actionBar.getClass().getName());
                    return;
                }
                Field declaredField2 = actionBar.getClass().getDeclaredField("mDecorToolbar");
                declaredField2.setAccessible(true);
                obj = declaredField2.get(actionBar);
            }
            if (obj instanceof ToolbarWidgetWrapper) {
                Field declaredField3 = obj.getClass().getDeclaredField("mToolbar");
                declaredField3.setAccessible(true);
            } else {
                m("unknown decorToolbar type:" + obj.getClass().getName());
            }
        } catch (Exception unused) {
            Log.w("ActivityCommon", "Failed to set Nav up background.");
        }
    }

    private void u() {
        m("update NavBar Fore Color in. mIsCardTheme = " + this.f17519l);
        if (k(this.f17514c)) {
            return;
        }
        this.f17514c.getColor(com.zte.extres.R.color.mfv_common_sk_bg);
        int color = this.f17519l ? this.f17514c.getColor(com.zte.extres.R.color.mfv_common_card_sk_bg) : this.f17514c.getColor(com.zte.extres.R.color.mfv_common_sk_bg);
        int d2 = Utils.d(color);
        View decorView = this.f17514c.getWindow().getDecorView();
        if (d2 >= 190) {
            m("NavBar background is light color:" + Integer.toHexString(color));
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 16);
            return;
        }
        m("NavBar background is dark color:" + Integer.toHexString(color));
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & (~16));
    }

    private void v() {
        Drawable drawable;
        int e2;
        Log.d("ActivityCommon", "update StatusBar Fore Color in. mIsCardTheme=" + this.f17519l);
        if (k(this.f17514c)) {
            Log.d("ActivityCommon", "mIsMifavorStatusBar: is Night mode.");
            return;
        }
        int statusBarColor = this.f17514c.getWindow().getStatusBarColor();
        Log.d("ActivityCommon", "mIsMifavorStatusBar=" + this.f17516i + ", mStatusBarColorInTheme=" + Integer.toHexString(this.f17517j) + ", getStatusBarColor=" + Integer.toHexString(statusBarColor));
        if (!this.f17516i || (this.f17517j != statusBarColor && statusBarColor != 0)) {
            Log.d("ActivityCommon", "updateStatusBarForeColor error return.");
            return;
        }
        if (this.f17519l) {
            drawable = this.f17514c.getDrawable(com.zte.extres.R.drawable.statusbar_bg_card);
            Log.d("ActivityCommon", "updateStatusBarForeColor drawable.statusbar_bg_card");
        } else {
            drawable = this.f17514c.getDrawable(com.zte.extres.R.drawable.statusbar_bg);
            Log.d("ActivityCommon", "updateStatusBarForeColor drawable.statusbar_bg");
        }
        if (drawable instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) drawable;
            e2 = Utils.d(colorDrawable.getColor());
            Log.d("ActivityCommon", "StatusBar background color drawable=" + Integer.toHexString(colorDrawable.getColor()) + ", statusBarBgBright=" + e2);
        } else if (this.f17519l) {
            e2 = Utils.e(BitmapFactory.decodeResource(this.f17514c.getResources(), com.zte.extres.R.drawable.statusbar_bg_card));
            Log.d("ActivityCommon", "updateStatusBarForeColor get Brightness drawable.statusbar_bg_card. statusBarBgBright = " + e2);
        } else {
            e2 = Utils.e(BitmapFactory.decodeResource(this.f17514c.getResources(), com.zte.extres.R.drawable.statusbar_bg));
            Log.d("ActivityCommon", "updateStatusBarForeColor get Brightness drawable.statusbar_bg. statusBarBgBright = " + e2);
        }
        View decorView = this.f17514c.getWindow().getDecorView();
        if (e2 >= 190) {
            Log.d("ActivityCommon", "StatusBar background is light color.statusBarBgBright = " + e2);
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 8192);
            return;
        }
        Log.d("ActivityCommon", "StatusBar background is dark color. statusBarBgBright = " + e2);
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & (-8193));
    }

    public void g() {
        View h2 = h();
        if (h2 instanceof FrameLayout) {
            int dimensionPixelSize = this.f17514c.getResources().getDimensionPixelSize(com.zte.extres.R.dimen.mfv_common_action_bar_height_outsrcreen);
            View findViewById = h2.findViewById(R.id.content);
            findViewById.setPaddingRelative(findViewById.getPaddingStart(), findViewById.getPaddingTop() + dimensionPixelSize, findViewById.getPaddingRight(), findViewById.getPaddingBottom());
            View inflate = LayoutInflater.from(this.f17514c).inflate(com.zte.extres.R.layout.action_bar_title_outscreen, (ViewGroup) null);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
            layoutParams.gravity = MaterialCardView.CHECKED_ICON_GRAVITY_TOP_START;
            ((FrameLayout) h2).addView(inflate, layoutParams);
            this.f17520m = (TextView) inflate.findViewById(com.zte.extres.R.id.primary_title);
            this.f17521n = (ImageView) inflate.findViewById(R.id.home);
            this.f17522o = (ImageView) inflate.findViewById(com.zte.extres.R.id.delete);
            this.f17523p = (ImageView) inflate.findViewById(com.zte.extres.R.id.edit);
        }
    }

    public View h() {
        return this.f17514c.getWindow().getDecorView();
    }

    public TextView i() {
        return this.f17520m;
    }

    public void n() {
        m("onCreate in. mIsCardTheme=" + this.f17519l);
        if (this.f17519l) {
            m("onCreate setStatusBackground drawable.statusbar_bg_card.");
            t(this.f17514c.getDrawable(com.zte.extres.R.drawable.statusbar_bg_card));
        } else {
            m("onCreate setStatusBackground drawable.statusbar_bg");
            t(this.f17514c.getDrawable(com.zte.extres.R.drawable.statusbar_bg));
        }
        this.f17514c.getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this.f17524q);
        m("customStatusBarColor");
        v();
        u();
        s(this.f17514c.getDrawable(com.zte.extres.R.drawable.image_button_ripple_bg));
        Log.d("ActivityCommon", "onCreate out.");
    }

    public void o() {
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
        ViewTreeObserver viewTreeObserver = this.f17514c.getWindow().getDecorView().getViewTreeObserver();
        if (viewTreeObserver != null && viewTreeObserver.isAlive() && (onGlobalLayoutListener = this.f17524q) != null) {
            viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
            this.f17524q = null;
        }
        this.f17514c = null;
    }

    protected void p() {
        ViewGroup viewGroup;
        View childAt;
        View h2 = h();
        if (h2 == null || (viewGroup = (ViewGroup) h2.findViewById(R.id.content)) == null || (childAt = viewGroup.getChildAt(0)) == null) {
            return;
        }
        r(childAt, Utils.o(childAt.getContext()));
    }

    public void q() {
        int themeResId = this.f17514c.getThemeResId();
        m("setLayoutController themeResId=" + themeResId + ", Theme card=" + com.zte.extres.R.style.Theme_ZTE_Light_Card + ", sink Theme card=" + com.zte.extres.R.style.Theme_ZTE_Light_Base_Sink_Card);
        if (com.zte.extres.R.style.Theme_ZTE_Light != themeResId) {
            return;
        }
        LayoutAnimationController loadLayoutAnimation = AnimationUtils.loadLayoutAnimation(this.f17514c, com.zte.extres.R.anim.item_anim_commoncontrols);
        View findViewById = this.f17514c.getWindow().getDecorView().findViewById(R.id.content);
        View findViewById2 = this.f17514c.findViewById(this.f17514c.getResources().getIdentifier("action_bar_container", VirtualHandleWrapper.KEY_ID, "android"));
        if (findViewById == null || !(findViewById instanceof ViewGroup) || findViewById2 == null || !(findViewById2 instanceof ViewGroup) || loadLayoutAnimation == null) {
            return;
        }
        ((ViewGroup) findViewById).setLayoutAnimation(loadLayoutAnimation);
        ((ViewGroup) findViewById2).setLayoutAnimation(loadLayoutAnimation);
    }

    protected void r(View view, final boolean z) {
        Log.d("ActivityCommon", "set Layout Margin in. rootContent=" + view + ", isBottom=" + z);
        if (view == null) {
            Log.e("ActivityCommon", "set Layout Margin. do nothing.");
            return;
        }
        try {
            ViewCompat.x0(view, new OnApplyWindowInsetsListener() { // from class: com.zte.mifavor.widget.a
                @Override // androidx.core.view.OnApplyWindowInsetsListener
                public final WindowInsetsCompat a(View view2, WindowInsetsCompat windowInsetsCompat) {
                    WindowInsetsCompat l2;
                    l2 = ActivityCommon.l(z, view2, windowInsetsCompat);
                    return l2;
                }
            });
        } catch (Exception e2) {
            Log.e("ActivityCommon", "set On Apply Window Insets Listener error. e=", e2);
        }
    }

    public void t(Drawable drawable) {
        m("set Status Background in.");
        if (!this.f17516i) {
            m("customed statusbar Bg.");
            return;
        }
        if (drawable == null) {
            return;
        }
        this.f17515h = drawable;
        View findViewById = this.f17514c.getWindow().getDecorView().findViewById(R.id.statusBarBackground);
        if (findViewById == null) {
            m("statusBar not found");
            return;
        }
        if (this.f17517j != this.f17514c.getWindow().getStatusBarColor()) {
            this.f17516i = false;
            m("app custom status bg:" + Integer.toHexString(this.f17514c.getWindow().getStatusBarColor()));
            return;
        }
        Drawable drawable2 = this.f17515h;
        if (drawable2 instanceof ColorDrawable) {
            m("statusBar:" + Integer.toHexString(this.f17514c.getWindow().getStatusBarColor()) + "-->" + Integer.toHexString(((ColorDrawable) drawable2).getColor()));
        }
        findViewById.setBackground(this.f17515h.getConstantState().newDrawable().mutate());
        m("set Status Background out.");
    }
}
