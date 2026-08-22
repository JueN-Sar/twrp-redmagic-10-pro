package cn.nubia.gamelauncher.gamecontrolpanel.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import cn.nubia.gamecenter.settings.helper.AnimatorHelper;
import cn.nubia.gamelauncher.R;

/* loaded from: classes.dex */
public class AnimationUtil {
    private static final String TAG = "AnimationUtil";

    public interface DialogAnimationEndCallBack {
        void dismissDialogAnimationEnd();
    }

    public static void setCenterControlViewAlpha(View view, int i) {
        ObjectAnimator ofFloat = i == 0 ? ObjectAnimator.ofFloat(view, AnimatorHelper.Item.ALPHA, 0.0f, 1.0f) : i == 1 ? ObjectAnimator.ofFloat(view, AnimatorHelper.Item.ALPHA, 1.0f, 0.0f) : ObjectAnimator.ofFloat(view, AnimatorHelper.Item.ALPHA, 1.0f, 1.0f);
        ofFloat.setDuration(300L);
        ofFloat.setRepeatCount(0);
        ofFloat.start();
    }

    public static void setDoublePxTranslationY(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, AnimatorHelper.Item.TRANSLATIONY, 200.0f, 0.0f);
        ofFloat.setDuration(300L);
        ofFloat.setRepeatCount(0);
        ofFloat.start();
    }

    public static void setGcsItemBgTranslationX(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, AnimatorHelper.Item.TRANSLATIONX, -675.0f, 0.0f);
        ofFloat.setDuration(300L);
        ofFloat.setRepeatCount(0);
        ofFloat.start();
    }

    public static void setGcsRedItemAlpha(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, AnimatorHelper.Item.ALPHA, 0.0f, 1.0f);
        ofFloat.setDuration(300L);
        ofFloat.setRepeatCount(0);
        ofFloat.start();
    }

    public static void setGpuTranslationY(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, AnimatorHelper.Item.TRANSLATIONY, 100.0f, 0.0f);
        ofFloat.setDuration(300L);
        ofFloat.setRepeatCount(0);
        ofFloat.start();
    }

    public static void setMenuTranslationYBTT(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, AnimatorHelper.Item.TRANSLATIONY, 978.0f, 0.0f);
        ofFloat.setDuration(300L);
        ofFloat.setRepeatCount(0);
        ofFloat.start();
    }

    public static void setMenuTranslationYTTB(View view, final DialogAnimationEndCallBack dialogAnimationEndCallBack) {
        Context context = view.getContext();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, AnimatorHelper.Item.TRANSLATIONY, 0.0f, context.getResources().getDimension(R.dimen.gamecontrol_view_home_height_size) + context.getResources().getDimension(R.dimen.nubia_game_strengthen_close_dialog_height));
        ofFloat.addListener(new Animator.AnimatorListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.utils.AnimationUtil.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                LogUtil.d(AnimationUtil.TAG, " setMenuTranslationYTTB ---- onAnimationEnd ");
                DialogAnimationEndCallBack.this.dismissDialogAnimationEnd();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        ofFloat.setDuration(300L);
        ofFloat.setRepeatCount(0);
        ofFloat.start();
    }

    public static void setResourceItemTranslationX(View view, int i) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, AnimatorHelper.Item.TRANSLATIONX, (i + 1) * 100, 0.0f);
        ofFloat.setDuration(300L);
        ofFloat.setRepeatCount(0);
        ofFloat.start();
    }
}
