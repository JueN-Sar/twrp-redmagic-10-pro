package cn.nubia.screensaver.view;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import cn.nubia.gameassist.R;
import cn.nubia.screensaver.util.DefaultUtil;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class KeyguardPresentation extends GamePresentation {

    /* renamed from: k, reason: collision with root package name */
    private CardParentView f9190k;

    public KeyguardPresentation(Context context, Display display) {
        super(context, display, R.style.KeyguardPresentation, 2027);
        setCancelable(false);
    }

    @Override // cn.nubia.screensaver.view.GamePresentation, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        CardParentView cardParentView = this.f9190k;
        if (cardParentView != null) {
            cardParentView.w();
        }
        super.dismiss();
    }

    @Override // cn.nubia.screensaver.view.GamePresentation
    public void e(FileDescriptor fileDescriptor, PrintWriter printWriter, String str) {
        printWriter.println(str + "Presentation");
        String str2 = str + "  ";
        printWriter.println(str2 + "display=" + f());
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        printWriter.println(str2 + "pw=" + (displayMetrics.widthPixels / displayMetrics.xdpi));
        printWriter.println(str2 + "ph=" + (((float) displayMetrics.heightPixels) / displayMetrics.ydpi));
        printWriter.println(str2 + "density=" + displayMetrics.density);
        printWriter.println(str2 + "w=" + displayMetrics.widthPixels);
        printWriter.println(str2 + "h=" + displayMetrics.heightPixels);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fl_card_content);
        if (frameLayout != null) {
            printWriter.println(str2 + "wCard=" + frameLayout.getWidth());
            printWriter.println(str2 + "hCard=" + frameLayout.getHeight());
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DefaultUtil.d(getContext());
        CardParentView cardParentView = (CardParentView) LayoutInflater.from(getContext()).inflate(R.layout.card_container, (ViewGroup) null);
        this.f9190k = cardParentView;
        cardParentView.setShowMonitor(true);
        setContentView(this.f9190k);
    }
}
