package cn.nubia.gamelauncher.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.wallpaper.WallpaperManager;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.adapter.GuildPageAdapter;
import cn.nubia.gamelauncher.gamehandle.NubiaCTAPermissionUtils;
import cn.nubia.gamelauncher.guide.GuideBean;
import cn.nubia.gamelauncher.model.AppAddModel;
import cn.nubia.gamelauncher.util.BuildDeviceUtil;
import cn.nubia.gamelauncher.util.Util;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class SplashFragment extends Fragment {
    public static final int FLAG_ANIM = 2;
    public static final int FLAG_CTA = 1;
    public static final int FLAG_END = 8;
    public static final int FLAG_GUIDE = 4;
    public static final String SPLASH_FLAG = "splash_flag";
    private static final String TAG = "Splash";
    Toast mCancelToast;
    Runnable mDismissCallback;
    View mView;
    View mWelcome;
    private int mFlag = 0;
    boolean isDoingStartAnim = false;
    Handler mHandler = new Handler(Looper.getMainLooper());
    ClickableSpan clickableSpan = new ClickableSpan() { // from class: cn.nubia.gamelauncher.fragment.SplashFragment.1
        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            SplashFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(SplashFragment.this.getString(R.string.welcome_link_url))));
        }
    };

    private void dismissCancelToast() {
        Toast toast = this.mCancelToast;
        if (toast != null) {
            toast.cancel();
        }
    }

    private void doStep(int i) {
        if (i == 1) {
            checkFlag(i, 2, new Runnable() { // from class: cn.nubia.gamelauncher.fragment.SplashFragment$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    SplashFragment.this.showCtaView();
                }
            });
            return;
        }
        if (i == 2) {
            checkFlag(i, 4, new Runnable() { // from class: cn.nubia.gamelauncher.fragment.SplashFragment$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    SplashFragment.this.showStartAnim();
                }
            });
        } else if (i == 4) {
            checkFlag(i, 8, new Runnable() { // from class: cn.nubia.gamelauncher.fragment.SplashFragment$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    SplashFragment.this.m242lambda$doStep$0$cnnubiagamelauncherfragmentSplashFragment();
                }
            });
        } else {
            if (i != 8) {
                return;
            }
            end();
        }
    }

    private void removeAnimEndListener() {
        WallpaperManager.getInstance().removeStartAnimCallback();
    }

    private void replaceWithHttpsIfNeed(TextView textView) {
        if (CommonUtil.isNubiaChina() && BuildDeviceUtil.isAndroidU()) {
            String string = getString(R.string.welcome_annotation);
            String string2 = getString(R.string.welcome_link_text);
            if (string.contains(string2)) {
                SpannableString spannableString = new SpannableString(HtmlCompat.fromHtml(string, 0));
                int indexOf = spannableString.toString().indexOf(string2);
                spannableString.setSpan(this.clickableSpan, indexOf, string2.length() + indexOf, 33);
                textView.setText(spannableString);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCtaView() {
        Log.d("Splash", "showCtaView()");
        View inflate = ((ViewStub) this.mView.findViewById(R.id.welcome_view)).inflate();
        this.mWelcome = inflate;
        TextView textView = (TextView) inflate.findViewById(R.id.welcome_link);
        replaceWithHttpsIfNeed(textView);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setAutoLinkMask(0);
        this.mWelcome.findViewById(R.id.welcome_accept).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.fragment.SplashFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SplashFragment.this.clickAccept(view);
            }
        });
        this.mWelcome.findViewById(R.id.welcome_cancel).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.fragment.SplashFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SplashFragment.this.clickCancel(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showGuide, reason: merged with bridge method [inline-methods] */
    public void m242lambda$doStep$0$cnnubiagamelauncherfragmentSplashFragment() {
        Log.d("Splash", "showGuide()");
        ViewStub viewStub = (ViewStub) this.mView.findViewById(R.id.guide_panel);
        if (viewStub != null) {
            showGuide(getContext(), viewStub.inflate());
        } else {
            Log.d("Splash", "showGuide() error stub is null, findViewById : " + this.mView.findViewById(R.id.guide_panel));
            end();
        }
    }

    private void showGuide(Context context, View view) {
        if (context == null) {
            return;
        }
        RecyclerView recyclerView = view instanceof RecyclerView ? (RecyclerView) view : (RecyclerView) view.findViewById(R.id.guide_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(0);
        recyclerView.setLayoutManager(linearLayoutManager);
        new PagerSnapHelper().attachToRecyclerView(recyclerView);
        ArrayList arrayList = new ArrayList();
        GuideBean guideBean = new GuideBean(context.getString(R.string.guild_page1_text), context.getString(R.string.guild_page1_explain), Integer.valueOf(R.mipmap.game_space_guide_page_1));
        GuideBean guideBean2 = new GuideBean(context.getString(R.string.guild_page2_text), context.getString(R.string.guild_page2_explain), Integer.valueOf(R.mipmap.game_space_guide_page_2));
        GuideBean guideBean3 = new GuideBean(context.getString(R.string.guild_page3_text), context.getString(R.string.guild_page3_explain), Integer.valueOf(R.mipmap.game_space_guide_page_3));
        arrayList.add(guideBean);
        if (GameSpaceConfig.supportBase()) {
            arrayList.add(guideBean2);
        }
        arrayList.add(guideBean3);
        recyclerView.setAdapter(new GuildPageAdapter(arrayList, new Runnable() { // from class: cn.nubia.gamelauncher.fragment.SplashFragment$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SplashFragment.this.end();
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showStartAnim() {
        this.isDoingStartAnim = true;
        WallpaperManager.getInstance().switchToStartAnim();
        WallpaperManager.getInstance().setStartAnimCallback(new Runnable() { // from class: cn.nubia.gamelauncher.fragment.SplashFragment$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SplashFragment.this.playEnd();
            }
        });
        this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.fragment.SplashFragment$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SplashFragment.this.playEnd();
            }
        }, 2600L);
    }

    protected void checkFlag(int i, int i2, Runnable runnable) {
        int i3 = this.mFlag & i;
        Log.d("Splash", "splash - checkFlag(" + this.mFlag + " & " + i + ") result : " + i3);
        if (i3 != 0) {
            runnable.run();
        } else {
            doStep(i2);
        }
        Log.d("Splash", "splash - checkFlag(end) mFlag : " + this.mFlag);
    }

    public void clickAccept(View view) {
        NubiaCTAPermissionUtils.agreeCtaPermission(getContext());
        AppAddModel.getInstance().updateGameBeanImageUrlAgain();
        this.mWelcome.setVisibility(8);
        doStep(2);
    }

    public void clickCancel(View view) {
        if (Util.supportVirtualGameKey() || Util.isSwitchGameKeyToOtherFunctions()) {
            NubiaCTAPermissionUtils.rejectCtaPermission(getContext());
            return;
        }
        dismissCancelToast();
        Toast makeText = Toast.makeText(getContext(), getResources().getString(R.string.welcome_click_cancel_toast), 0);
        this.mCancelToast = makeText;
        makeText.show();
    }

    public void end() {
        Runnable runnable = this.mDismissCallback;
        if (runnable == null) {
            return;
        }
        runnable.run();
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("wallpaper", "onAttach(splash)");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mView = layoutInflater.inflate(R.layout.splash_screen, viewGroup, false);
        this.mFlag = getArguments().getInt(SPLASH_FLAG);
        Log.d("Splash", "splash - onCreateView() mFlag : " + this.mFlag);
        doStep(1);
        return this.mView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Splash", "onDestroyView()");
        removeAnimEndListener();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        Log.d("wallpaper", "onDetach(splash)");
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        dismissCancelToast();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    protected void onStartAnimEnd() {
        Trace.beginSection("onStartAnimEnd");
        Log.d("Splash", "onStartAnimEnd() mFlag : " + this.mFlag);
        this.mHandler.removeCallbacksAndMessages(null);
        doStep(4);
        Trace.endSection();
    }

    public void playEnd() {
        Log.d("Splash", "playEnd() isDoingStartAnim : " + this.isDoingStartAnim);
        if (this.isDoingStartAnim) {
            this.isDoingStartAnim = false;
            removeAnimEndListener();
            this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.fragment.SplashFragment$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    SplashFragment.this.onStartAnimEnd();
                }
            }, 50L);
        }
    }

    public void setDismissCallback(Runnable runnable) {
        this.mDismissCallback = runnable;
    }
}
