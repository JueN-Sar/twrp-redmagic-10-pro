package cn.nubia.chatassistant.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import cn.nubia.gamelauncher.R;

/* loaded from: classes.dex */
public class ToastUtils {
    public static void showToast(Context context, String str, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.toast_layout, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.toast_text)).setText(str);
        Toast toast = new Toast(context);
        toast.setView(inflate);
        toast.setDuration(i);
        toast.show();
    }
}
