package cn.nubia.gamelauncher.helper;

import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.common.helper.HideAppsHelper;
import cn.nubia.common.util.BitmapUtils;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.model.AppAddModel;
import cn.nubia.gamelauncher.recycler.BannerCardTransformation;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.systemwrapper.GameKeysWrapper;
import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class CardHelper {
    private static final String TAG = "CardHelper";
    ObjectAnimator mAnimator;
    Context mContext;
    private Dialog mDialog;

    public CardHelper(Context context) {
        this.mContext = context;
    }

    private void clickItem(AppListItemBean appListItemBean) {
        try {
            String componentName = appListItemBean.getComponentName();
            Log.d(TAG, "onAppBeanClick() hasCloneApp() : " + hasCloneApp(componentName));
            if ("cn.nubia.gamelauncher,cn.nubia.gamelauncher.activity.AppAddActivity".equals(componentName)) {
                Intent intent = new Intent();
                intent.setComponent(CommonUtil.createComponentName(componentName));
                this.mContext.startActivity(intent);
            } else if (Util.isZte() || !hasCloneApp(componentName)) {
                startApp(appListItemBean, false, null);
            } else {
                m313lambda$showDialog$0$cnnubiagamelauncherhelperCardHelper(appListItemBean, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startActivityByLaunchIntent(String str, Bundle bundle) {
        Intent launchIntentForPackage = GameLauncherApplication.getAppContext().getPackageManager().getLaunchIntentForPackage(str);
        LogUtil.i(TAG, "startActivityByLaunchIntent() launchIntent : " + launchIntentForPackage);
        try {
            this.mContext.startActivity(launchIntentForPackage, bundle);
        } catch (Exception e) {
            LogUtil.w(TAG, "startActivityByLaunchIntent() exception : " + e.getMessage());
        }
    }

    public Intent buildMainIntent(String str) {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setFlags(270532608);
        intent.putExtra("start_from_heartservice_app_lock", true);
        intent.setComponent(CommonUtil.createComponentName(str));
        return intent;
    }

    public Drawable cropCenterDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        return BitmapUtils.convertBitmapToDrawable(BitmapUtils.bitmapRound(BitmapUtils.getZoomImage(BitmapUtils.convertDrawableToBitmap(drawable), 144.0d, 144.0d, true), 34.0f));
    }

    public void fillCardView(Context context, ImageView imageView, View view, String str, int i, int i2) {
        fillCardView(context, imageView, view, str, i, i2, DiskCacheStrategy.SOURCE);
    }

    public void fillCardView(Context context, ImageView imageView, final View view, final String str, int i, final int i2, DiskCacheStrategy diskCacheStrategy) {
        if (context == null || str == null) {
            return;
        }
        final BannerCardTransformation bannerCardTransformation = new BannerCardTransformation(i2);
        Glide.with(context).load(str).asBitmap().placeholder(i).diskCacheStrategy(diskCacheStrategy).into((BitmapRequestBuilder<String, Bitmap>) new ViewTarget<ImageView, Bitmap>(imageView) { // from class: cn.nubia.gamelauncher.helper.CardHelper.4
            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                String str2;
                if (view.getTag() == null || (str2 = (String) view.getTag()) == null || !str2.equals(str)) {
                    return;
                }
                view.setVisibility(8);
                ((ImageView) this.view).setImageBitmap(bannerCardTransformation.transform(bitmap, i2));
            }

            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                onResourceReady((Bitmap) obj, (GlideAnimation<? super Bitmap>) glideAnimation);
            }
        });
    }

    public final Bundle getActivityLaunchOptionsAsBundle() {
        ActivityOptions makeCustomAnimation = ActivityOptions.makeCustomAnimation(this.mContext, 0, 0);
        try {
            Method[] declaredMethods = makeCustomAnimation.getClass().getDeclaredMethods();
            if (declaredMethods.length > 0) {
                for (int i = 0; i < declaredMethods.length; i++) {
                    if ("setSplashscreenStyle".equals(declaredMethods[i].getName()) || "setSplashScreenStyle".equals(declaredMethods[i].getName())) {
                        declaredMethods[i].setAccessible(true);
                        declaredMethods[i].invoke(makeCustomAnimation, 0);
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.i(TAG, "getActivityLaunchOptionsAsBundle() error = " + e.toString());
        }
        return makeCustomAnimation.toBundle();
    }

    public String getPackageNameByComponetName(String str) {
        if (str == null || !str.contains(",")) {
            return null;
        }
        return str.split(",")[0];
    }

    public String getTotalString(AppListItemBean appListItemBean) {
        Log.d("banner", " getTotalString() gameName = " + appListItemBean.getName() + ", second : " + appListItemBean.getTotalTimeMillisecond() + ", h : " + appListItemBean.getTotalTimeHour());
        return appListItemBean.getTotalTimeHour() == 0 ? this.mContext.getResources().getString(R.string.string_less_than_an_hour) : this.mContext.getResources().getString(R.string.string_playing_for, Long.valueOf(appListItemBean.getTotalTimeHour()));
    }

    public boolean hasCloneApp(String str) {
        String packageNameByComponetName = getPackageNameByComponetName(str);
        if (packageNameByComponetName == null || HideAppsHelper.getInstance().isHideCloneApp(packageNameByComponetName)) {
            return false;
        }
        return GameKeysWrapper.getDefault().isPackageInstalled(this.mContext, packageNameByComponetName, Util.TWIN_PROFILEID);
    }

    public void showDialog(AppListItemBean appListItemBean) {
        m313lambda$showDialog$0$cnnubiagamelauncherhelperCardHelper(appListItemBean, null);
    }

    /* renamed from: showDialog, reason: merged with bridge method [inline-methods] */
    public void m313lambda$showDialog$0$cnnubiagamelauncherhelperCardHelper(final AppListItemBean appListItemBean, final Runnable runnable) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: cn.nubia.gamelauncher.helper.CardHelper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CardHelper.this.m313lambda$showDialog$0$cnnubiagamelauncherhelperCardHelper(appListItemBean, runnable);
                }
            });
            return;
        }
        Dialog dialog = this.mDialog;
        if (dialog == null || !dialog.isShowing()) {
            LogUtil.d(TAG, "showDialog() bean : " + appListItemBean.getName());
            String packageNameByComponetName = getPackageNameByComponetName(appListItemBean.getComponentName());
            Drawable dialogIcon = Util.getDialogIcon(packageNameByComponetName);
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.clone_app_dialog, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R.id.sourtitle);
            textView.setFocusable(true);
            textView.setText(appListItemBean.getName());
            textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, cropCenterDrawable(dialogIcon), (Drawable) null, (Drawable) null);
            textView.setPadding(5, 5, 5, 5);
            textView.requestFocus();
            Button button = (Button) inflate.findViewById(R.id.dialog_cancel_button);
            TextView textView2 = (TextView) inflate.findViewById(R.id.twintitle);
            textView2.setPadding(5, 5, 5, 5);
            textView2.setFocusable(true);
            textView2.setText(appListItemBean.getName());
            textView2.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, cropCenterDrawable(Util.getTwinIcon(packageNameByComponetName, dialogIcon)), (Drawable) null, (Drawable) null);
            textView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.helper.CardHelper.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CardHelper.this.startApp(appListItemBean, false, runnable);
                    CardHelper.this.mDialog.dismiss();
                }
            });
            textView2.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.helper.CardHelper.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CardHelper.this.startApp(appListItemBean, true, runnable);
                    CardHelper.this.mDialog.dismiss();
                }
            });
            this.mDialog = new AlertDialog.Builder(this.mContext, 2131952382).setCustomTitle(inflate).create();
            button.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.helper.CardHelper.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CardHelper.this.mDialog.dismiss();
                }
            });
            Window window = this.mDialog.getWindow();
            if (window != null) {
                window.setType(2047);
                window.setGravity(80);
            }
            this.mDialog.show();
        }
    }

    public void startApp(AppListItemBean appListItemBean, boolean z) {
        startApp(appListItemBean, z, null);
    }

    public void startApp(AppListItemBean appListItemBean, boolean z, Runnable runnable) {
        if (appListItemBean == null || this.mContext == null) {
            return;
        }
        String componentName = appListItemBean.getComponentName();
        LogUtil.i(TAG, "startApp() componetName : " + componentName + ", isCloneApp : " + z);
        Intent buildMainIntent = buildMainIntent(componentName);
        Bundle activityLaunchOptionsAsBundle = getActivityLaunchOptionsAsBundle();
        if (z) {
            PackageInfo packageInfoAsUser = Util.getPackageInfoAsUser(this.mContext.getPackageManager(), appListItemBean.getPackageName(), 0, Util.TWIN_PROFILEID);
            UserHandle userHandleForUid = packageInfoAsUser != null ? UserHandle.getUserHandleForUid(packageInfoAsUser.applicationInfo.uid) : null;
            LogUtil.i(TAG, "startApp() user : " + userHandleForUid);
            Util.startActivityAsUser(this.mContext, buildMainIntent, activityLaunchOptionsAsBundle, userHandleForUid);
        } else {
            try {
                this.mContext.startActivity(buildMainIntent, activityLaunchOptionsAsBundle);
            } catch (Exception e) {
                LogUtil.w(TAG, "startApp() exception : " + e.getMessage());
                startActivityByLaunchIntent(appListItemBean.getPackageName(), activityLaunchOptionsAsBundle);
                AppAddModel.getInstance().m320xe5db99a0(appListItemBean);
            }
        }
        if (runnable != null) {
            runnable.run();
        }
    }
}
