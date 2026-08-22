package cn.nubia.gameassist.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToastPresenter;
import cn.nubia.gameassist.GameAssistApplication;
import com.zte.shared.wrapper.VirtualHandleWrapper;

/* loaded from: classes.dex */
public class ToastUtil {
    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(GameAssistApplication.j(), str, 0).show();
    }

    public static void b(String str, int i2) {
        try {
            int identifier = GameAssistApplication.j().getResources().getIdentifier("message", VirtualHandleWrapper.KEY_ID, "android");
            Toast toast = new Toast(GameAssistApplication.j());
            View textToastView = ToastPresenter.getTextToastView(GameAssistApplication.j(), str);
            ((TextView) textToastView.findViewById(identifier)).setMaxLines(i2);
            toast.setDuration(1);
            toast.setView(textToastView);
            toast.show();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
