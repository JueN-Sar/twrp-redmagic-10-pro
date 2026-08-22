package com.zte.mifavor.androidx.widget.sink;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.AppBarLayoutSpringBehavior;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.zte.extres.R;
import com.zte.mifavor.androidx.behavior.BaseSinkTitleBehavior;
import com.zte.mifavor.utils.SinkUtils;
import com.zte.mifavor.utils.UIUtils;
import com.zte.mifavor.widget.ActivityCommon;
import com.zte.mifavor.widget.Utils;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public class BaseSinkActivity extends AppCompatActivity {
    protected CoordinatorLayout H;
    protected Toolbar I;
    protected AppBarLayout J;
    private CollapsingToolbarLayout K;
    protected TextView L;
    protected TextView M;
    private ActivityCommon N;
    private WeakReference Q;
    private boolean O = false;
    protected boolean P = false;
    private RecyclerView R = null;
    private boolean S = true;
    private int T = 0;
    private boolean U = false;
    private int V = 0;
    private int W = 0;
    private MyBroadcastReceiver X = null;
    protected TextView Y = null;
    protected ImageView Z = null;
    protected ImageView a0 = null;
    protected ImageView b0 = null;

    /* renamed from: com.zte.mifavor.androidx.widget.sink.BaseSinkActivity$1, reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ BaseSinkActivity f17184c;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f17184c.onBackPressed();
        }
    }

    /* renamed from: com.zte.mifavor.androidx.widget.sink.BaseSinkActivity$6, reason: invalid class name */
    class AnonymousClass6 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ BaseSinkActivity f17189c;

        @Override // java.lang.Runnable
        public void run() {
            int childCount = this.f17189c.I.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = this.f17189c.I.getChildAt(i2);
                if (childAt != null && (childAt instanceof ActionMenuView)) {
                    this.f17189c.R0(childAt);
                    return;
                }
            }
        }
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        public MyBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (BaseSinkActivity.this.R == null || !BaseSinkActivity.this.R.canScrollVertically(-1)) {
                return;
            }
            BaseSinkActivity.this.R.s1(0);
        }
    }

    private static class OneHandedStateMonitorContentObserver extends ContentObserver {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference f17197a;

        public OneHandedStateMonitorContentObserver(BaseSinkActivity baseSinkActivity) {
            super(new Handler());
            this.f17197a = new WeakReference(baseSinkActivity);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            BaseSinkActivity baseSinkActivity = (BaseSinkActivity) this.f17197a.get();
            if (baseSinkActivity != null) {
                baseSinkActivity.A0();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A0() {
        int i2 = Settings.System.getInt(getApplicationContext().getContentResolver(), "one_handed_state", -1);
        boolean c2 = SinkUtils.c(getResources());
        boolean isInMultiWindowMode = isInMultiWindowMode();
        Log.i("BS#BaseSinkActivity", "Judge The System Mode Then Update Sink State STRING_ONEHAND_STATE=" + i2 + ", bIsLand=" + c2 + ", bIsMulWinMode= " + isInMultiWindowMode);
        if (1 == i2 || c2 || isInMultiWindowMode) {
            this.J.postDelayed(new Runnable() { // from class: com.zte.mifavor.androidx.widget.sink.BaseSinkActivity.9
                @Override // java.lang.Runnable
                public void run() {
                    BaseSinkActivity.this.J0(false);
                    BaseSinkActivity.this.C0(true);
                }
            }, 100L);
        } else {
            C0(false);
        }
    }

    private void B0(final TextView textView) {
        if (textView != null && this.H.indexOfChild(textView) >= 0) {
            textView.post(new Runnable() { // from class: com.zte.mifavor.androidx.widget.sink.BaseSinkActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    CoordinatorLayout.Behavior f2;
                    ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                    if (layoutParams == null || !(layoutParams instanceof CoordinatorLayout.LayoutParams) || (f2 = ((CoordinatorLayout.LayoutParams) layoutParams).f()) == null) {
                        return;
                    }
                    BaseSinkActivity baseSinkActivity = BaseSinkActivity.this;
                    f2.m(baseSinkActivity.H, textView, baseSinkActivity.J);
                }
            });
        }
    }

    private RecyclerView D0(ViewGroup viewGroup) {
        RecyclerView D0;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof RecyclerView) {
                return (RecyclerView) childAt;
            }
            if ((childAt instanceof ViewGroup) && (D0 = D0((ViewGroup) childAt)) != null) {
                return D0;
            }
        }
        return null;
    }

    private void H0() {
        try {
            Toolbar toolbar = (Toolbar) findViewById(R.id.base_sink_toolbar);
            this.I = toolbar;
            q0(toolbar);
            g0().m(false);
        } catch (Exception e2) {
            Log.e("BS#BaseSinkActivity", "init Action Bar error, e = ", e2);
        }
    }

    private void I0() {
        A0();
        WeakReference weakReference = this.Q;
        if (weakReference == null || weakReference.get() == null) {
            OneHandedStateMonitorContentObserver oneHandedStateMonitorContentObserver = new OneHandedStateMonitorContentObserver(this);
            this.Q = new WeakReference(oneHandedStateMonitorContentObserver);
            getApplicationContext().getContentResolver().registerContentObserver(Settings.System.getUriFor("one_handed_state"), true, oneHandedStateMonitorContentObserver);
        }
    }

    private void K0() {
        this.H = (CoordinatorLayout) findViewById(R.id.base_sink_root_coordinator_layout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.base_sink_app_bar_layout);
        this.J = appBarLayout;
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        Context context = this.J.getContext();
        if (context == null) {
            context = getApplicationContext();
        }
        layoutParams.o(new AppBarLayoutSpringBehavior(context));
        this.J.setLayoutParams(layoutParams);
        this.K = (CollapsingToolbarLayout) findViewById(R.id.base_sink_collapsing_toolbar_layout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ WindowInsetsCompat L0(boolean z, View view, WindowInsetsCompat windowInsetsCompat) {
        try {
            Insets f2 = windowInsetsCompat.f(WindowInsetsCompat.Type.e());
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.leftMargin = f2.f2920a;
            marginLayoutParams.rightMargin = f2.f2922c;
            int i2 = f2.f2921b;
            marginLayoutParams.topMargin = i2;
            if (z) {
                marginLayoutParams.bottomMargin = 0;
            } else {
                marginLayoutParams.bottomMargin = f2.f2923d;
            }
            this.V = i2;
            this.W = marginLayoutParams.bottomMargin;
            int i3 = getResources().getConfiguration().orientation;
            String h2 = Utils.h();
            if (i3 == 2 && UIUtils.k(h2)) {
                marginLayoutParams.leftMargin = UIUtils.e();
                marginLayoutParams.rightMargin = UIUtils.f(this);
                marginLayoutParams.bottomMargin = 0;
            }
            Log.i("BS#BaseSinkActivity", "set Layout Margin. leftMargin=" + marginLayoutParams.leftMargin + ", topMargin=" + marginLayoutParams.topMargin + ", rightMargin=" + marginLayoutParams.rightMargin + ", bottomMargin=" + marginLayoutParams.bottomMargin + ", clear the Window Insets Listener. orientation=" + i3);
            view.setLayoutParams(marginLayoutParams);
        } catch (Exception e2) {
            Log.e("BS#BaseSinkActivity", "set Layout Margin error.e=", e2);
        }
        return WindowInsetsCompat.f3439b;
    }

    private void M0() {
        this.X = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("zte.intent.action.status_bar_taped");
        registerReceiver(this.X, intentFilter, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N0(boolean z) {
        CoordinatorLayout.Behavior f2;
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) this.J.getLayoutParams();
        if (layoutParams == null || (f2 = layoutParams.f()) == null || !(f2 instanceof AppBarLayoutSpringBehavior)) {
            return;
        }
        ((AppBarLayoutSpringBehavior) f2).Z0(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O0(AppBarLayout.Behavior.DragCallback dragCallback) {
        CoordinatorLayout.Behavior f2;
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) this.J.getLayoutParams();
        if (layoutParams == null || (f2 = layoutParams.f()) == null || !(f2 instanceof AppBarLayout.Behavior)) {
            return;
        }
        ((AppBarLayout.Behavior) f2).z0(dragCallback);
    }

    private void Q0() {
        MyBroadcastReceiver myBroadcastReceiver = this.X;
        if (myBroadcastReceiver != null) {
            unregisterReceiver(myBroadcastReceiver);
            this.X = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R0(final View view) {
        if (!ViewCompat.N(view)) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.zte.mifavor.androidx.widget.sink.BaseSinkActivity.7
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int width = view.getWidth() + BaseSinkActivity.this.I.getPaddingEnd() + BaseSinkActivity.this.I.getPaddingStart();
                    BaseSinkActivity baseSinkActivity = BaseSinkActivity.this;
                    baseSinkActivity.S0(baseSinkActivity.L, width);
                    BaseSinkActivity baseSinkActivity2 = BaseSinkActivity.this;
                    baseSinkActivity2.S0(baseSinkActivity2.M, width);
                }
            });
            return;
        }
        int width = view.getWidth() + this.I.getPaddingEnd() + this.I.getPaddingStart();
        S0(this.L, width);
        S0(this.M, width);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S0(final TextView textView, final int i2) {
        if (textView != null && this.H.indexOfChild(textView) >= 0) {
            textView.post(new Runnable() { // from class: com.zte.mifavor.androidx.widget.sink.BaseSinkActivity.8
                @Override // java.lang.Runnable
                public void run() {
                    CoordinatorLayout.Behavior f2;
                    ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                    if (layoutParams == null || !(layoutParams instanceof CoordinatorLayout.LayoutParams) || (f2 = ((CoordinatorLayout.LayoutParams) layoutParams).f()) == null || !(f2 instanceof BaseSinkTitleBehavior)) {
                        return;
                    }
                    ((BaseSinkTitleBehavior) f2).J(i2);
                    BaseSinkActivity baseSinkActivity = BaseSinkActivity.this;
                    f2.m(baseSinkActivity.H, textView, baseSinkActivity.J);
                }
            });
        }
    }

    protected void C0(boolean z) {
        if (z == this.O) {
            return;
        }
        if (z) {
            this.O = true;
            if (!ViewCompat.N(this.J)) {
                this.J.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.zte.mifavor.androidx.widget.sink.BaseSinkActivity.3
                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                    public void onGlobalLayout() {
                        BaseSinkActivity.this.J.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        BaseSinkActivity.this.O0(new AppBarLayout.Behavior.DragCallback(this) { // from class: com.zte.mifavor.androidx.widget.sink.BaseSinkActivity.3.1
                            @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior.BaseDragCallback
                            public boolean a(AppBarLayout appBarLayout) {
                                return false;
                            }
                        });
                        BaseSinkActivity.this.N0(false);
                    }
                });
                return;
            } else {
                O0(new AppBarLayout.Behavior.DragCallback(this) { // from class: com.zte.mifavor.androidx.widget.sink.BaseSinkActivity.2
                    @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior.BaseDragCallback
                    public boolean a(AppBarLayout appBarLayout) {
                        return false;
                    }
                });
                N0(false);
                return;
            }
        }
        this.O = false;
        if (!ViewCompat.N(this.J)) {
            this.J.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.zte.mifavor.androidx.widget.sink.BaseSinkActivity.4
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    BaseSinkActivity.this.J.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    BaseSinkActivity.this.O0(null);
                    BaseSinkActivity.this.N0(true);
                }
            });
        } else {
            O0(null);
            N0(true);
        }
    }

    public int E0() {
        int top = this.J.getTop();
        int totalScrollRange = this.J.getTotalScrollRange();
        if (top == 0) {
            return this.J.getBottom() > totalScrollRange + getResources().getDimensionPixelSize(R.dimen.mfvc_appbar_height) ? 4 : 1;
        }
        return Math.abs(top) == totalScrollRange ? 2 : 3;
    }

    public boolean F0() {
        return this.O;
    }

    public View G0() {
        return D0((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content));
    }

    protected void J0(boolean z) {
        this.J.z(z, false);
        B0(this.L);
        B0(this.M);
    }

    protected void P0(final boolean z) {
        View findViewById = findViewById(R.id.base_sink_root_coordinator_layout);
        Log.d("BS#BaseSinkActivity", "set Layout Margin. isBottom=" + z + ", rootContent=" + findViewById);
        if (findViewById == null) {
            Log.e("BS#BaseSinkActivity", "set Layout Margin. do nothing.");
            return;
        }
        try {
            ViewCompat.x0(findViewById, new OnApplyWindowInsetsListener() { // from class: com.zte.mifavor.androidx.widget.sink.a
                @Override // androidx.core.view.OnApplyWindowInsetsListener
                public final WindowInsetsCompat a(View view, WindowInsetsCompat windowInsetsCompat) {
                    WindowInsetsCompat L0;
                    L0 = BaseSinkActivity.this.L0(z, view, windowInsetsCompat);
                    return L0;
                }
            });
        } catch (Exception e2) {
            Log.e("BS#BaseSinkActivity", "set On Apply Window Insets Listener error. e=", e2);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        if (this.P) {
            finish();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        A0();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.activity_base_sink_layout);
        H0();
        K0();
        ActivityCommon activityCommon = new ActivityCommon(this);
        this.N = activityCommon;
        activityCommon.n();
        boolean j2 = UIUtils.j(this);
        this.P = j2;
        if (j2 || UIUtils.g(this)) {
            C0(true);
        }
        boolean o2 = Utils.o(this);
        this.U = o2;
        if (this.S) {
            P0(o2);
        }
        try {
            if (!this.U) {
                getWindow().setNavigationBarContrastEnforced(true);
            } else {
                Utils.z(getWindow());
                getWindow().setNavigationBarContrastEnforced(false);
            }
        } catch (Exception e2) {
            Log.e("BS#BaseSinkActivity", "set Navigation Bar Contrast Enforced.e=", e2);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.N.o();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        super.onMultiWindowModeChanged(z, configuration);
        A0();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        Q0();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        View G0 = G0();
        if (G0 instanceof RecyclerView) {
            this.R = (RecyclerView) G0;
        }
        if (this.R != null) {
            M0();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        if (UIUtils.g(this)) {
            return;
        }
        I0();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        try {
            try {
                WeakReference weakReference = this.Q;
                OneHandedStateMonitorContentObserver oneHandedStateMonitorContentObserver = weakReference != null ? (OneHandedStateMonitorContentObserver) weakReference.get() : null;
                if (oneHandedStateMonitorContentObserver != null) {
                    getApplicationContext().getContentResolver().unregisterContentObserver(oneHandedStateMonitorContentObserver);
                }
            } catch (Exception e2) {
                Log.e("BS#BaseSinkActivity", "unregisterContentObserver error.e=", e2);
            }
            this.Q = null;
        } catch (Throwable th) {
            this.Q = null;
            throw th;
        }
    }
}
