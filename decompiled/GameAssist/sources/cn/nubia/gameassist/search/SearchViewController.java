package cn.nubia.gameassist.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.BaseViewController;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import com.airbnb.lottie.LottieAnimationView;
import com.zte.gameassist.ai.AIFlickerTips;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class SearchViewController extends BaseViewController implements View.OnClickListener {
    public static final boolean v;
    public static final boolean w;

    /* renamed from: q, reason: collision with root package name */
    private final SearchWindowManager f7399q;

    /* renamed from: r, reason: collision with root package name */
    private ImageButton f7400r;

    /* renamed from: s, reason: collision with root package name */
    private LottieAnimationView f7401s;
    private Handler t;
    private boolean u;

    static {
        boolean z = false;
        v = ZteFeature.isSupportAIChat() && !ZteFeature.IS_INTER_VERSION;
        if (ZteFeature.isSupportAIChatAbroad() && ZteFeature.IS_INTER_VERSION) {
            z = true;
        }
        w = z;
    }

    public SearchViewController(GameAssistWindowManager gameAssistWindowManager) {
        super(gameAssistWindowManager);
        this.t = new Handler();
        this.u = true;
        this.f7399q = SearchWindowManager.i(this.f6117c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String V(String str) {
        ApplicationInfo applicationInfo;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        PackageManager packageManager = j().getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e2) {
            GaLog.c("Search", "getApplicationName = ", e2);
            applicationInfo = null;
        }
        if (applicationInfo == null) {
            return "";
        }
        CharSequence applicationLabel = packageManager.getApplicationLabel(applicationInfo);
        if (TextUtils.isEmpty(applicationLabel)) {
            return "";
        }
        GaLog.a("Search", "getApplicationName = " + applicationLabel.toString());
        return applicationLabel.toString();
    }

    private void W() {
        new Handler(ThreadManager.c().a()).post(new Runnable() { // from class: cn.nubia.gameassist.search.SearchViewController.1
            @Override // java.lang.Runnable
            public void run() {
                Bundle bundle = new Bundle();
                bundle.putString("app_name", SearchViewController.this.V(SystemMgr.z()));
                NubiaTrackManager.p().x("cn.nubia.gamelauncher", "game_search_used", bundle);
            }
        });
    }

    private void X() {
        Y();
        this.f7401s.setImageAssetsFolder("images/");
        this.f7400r.setImageDrawable(this.f6117c.getDrawable(R.drawable.icon_search));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y() {
        boolean z = this.u;
        final View view = z ? this.f7401s : this.f7400r;
        final View view2 = z ? this.f7400r : this.f7401s;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "rotationY", 0.0f, 90.0f);
        ofFloat.setDuration(300L);
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: cn.nubia.gameassist.search.SearchViewController.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(8);
                if (view == SearchViewController.this.f7401s) {
                    SearchViewController.this.f7401s.t();
                }
                view2.setVisibility(0);
                if (view2 == SearchViewController.this.f7401s) {
                    SearchViewController.this.f7401s.u();
                }
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view2, "rotationY", -90.0f, 0.0f);
                ofFloat2.setDuration(300L);
                ofFloat2.start();
            }
        });
        ofFloat.start();
        this.u = !this.u;
        this.t.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.search.a
            @Override // java.lang.Runnable
            public final void run() {
                SearchViewController.this.Y();
            }
        }, this.u ? 8000L : 5000L);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public int C() {
        return R.id.game_assist_middle_search_layout;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void P() {
        ImageButton imageButton = this.f7400r;
        if (imageButton != null) {
            imageButton.setOnClickListener(null);
            this.f7400r = null;
        }
        LottieAnimationView lottieAnimationView = this.f7401s;
        if (lottieAnimationView != null) {
            lottieAnimationView.setOnClickListener(null);
            this.f7401s = null;
        }
        this.t.removeCallbacksAndMessages(null);
        AIFlickerTips.x();
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void h(PrintWriter printWriter, String str) {
        super.h(printWriter, str);
        printWriter.println(str + "  mSearchView =" + this.f7400r);
        this.f7399q.h(printWriter, str);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void o(View view) {
        ImageButton imageButton = (ImageButton) i(R.id.game_assist_middle_search);
        this.f7400r = imageButton;
        if (v || w) {
            GlobalSearchUtil.r(imageButton, "game_assist_middle_search");
            LottieAnimationView lottieAnimationView = (LottieAnimationView) i(R.id.lottie_animation);
            this.f7401s = lottieAnimationView;
            lottieAnimationView.setOnClickListener(this);
            X();
        } else if (!ZteFeature.isTabletProduct() || ZteFeature.isRedMagicProduct()) {
            this.f7400r.setImageDrawable(this.f6117c.getDrawable(R.drawable.game_assist_middle_search));
        } else {
            this.f7400r.setImageDrawable(this.f6117c.getDrawable(R.drawable.game_assist_middle_search));
        }
        this.f7400r.setContentDescription(this.f6117c.getString(R.string.game_search_desc));
        this.f7400r.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.game_assist_middle_search || view.getId() == R.id.lottie_animation) {
            if (v || w) {
                this.f6117c.startForegroundService(new Intent("cn.zte.gameaiasst.GAMEAIASSTSERVICE").setPackage("cn.zte.gameaiasst").putExtra("pagetype", 0).putExtra("packageName", SystemMgr.v()));
                GaLog.a("Search", "onClick AICHAT");
            } else {
                W();
                this.f7399q.y("fromSearchView");
                GaLog.a("Search", "onClick search");
            }
        }
    }
}
