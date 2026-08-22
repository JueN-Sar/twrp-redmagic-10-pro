package cn.nubia.gameassist.common;

import android.view.View;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.view.RotationFrameLayout;
import com.zte.gameassist.utils.GaLog;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public abstract class BaseDoubleViewController<U extends View> extends BaseViewController<View> implements View.OnClickListener, View.OnLongClickListener {

    /* renamed from: q, reason: collision with root package name */
    private View f6114q;

    public BaseDoubleViewController(GameAssistWindowManager gameAssistWindowManager) {
        super(gameAssistWindowManager);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void O() {
        super.O();
        if (this.f6114q != null) {
            for (int i2 : T()) {
                View S = S(i2);
                if (S != null) {
                    S.setOnClickListener(null);
                } else {
                    GaLog.k("GameAssist.ViewController", "initView, not find Id " + this.f6117c.getResources().getResourceName(i2) + " from" + getClass().getSimpleName());
                }
            }
            for (int i3 : U()) {
                View S2 = S(i3);
                if (S2 != null) {
                    S2.setOnLongClickListener(null);
                } else {
                    GaLog.k("GameAssist.ViewController", "initView, not find Id " + this.f6117c.getResources().getResourceName(i3) + " from" + getClass().getSimpleName());
                }
            }
        }
        this.f6114q = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.nubia.gameassist.common.BaseViewController
    public void Q(int i2, int i3) {
        super.Q(i2, i3);
        View S = S(i2);
        if (S != null) {
            S.setVisibility(i3);
        }
    }

    public final View S(int i2) {
        if (i2 == 0) {
            return null;
        }
        View view = this.f6114q;
        return view == null ? i(i2) : view.findViewById(i2);
    }

    public int[] T() {
        return new int[0];
    }

    public int[] U() {
        return new int[0];
    }

    public void V(View view) {
        this.f6114q = view;
        if (view != null) {
            for (int i2 : T()) {
                View S = S(i2);
                if (S != null) {
                    S.setOnClickListener(this);
                } else {
                    GaLog.k("GameAssist.ViewController", "initView, not find Id " + this.f6117c.getResources().getResourceName(i2) + " from" + getClass().getSimpleName());
                }
            }
            for (int i3 : U()) {
                View S2 = S(i3);
                if (S2 != null) {
                    S2.setOnLongClickListener(this);
                } else {
                    GaLog.k("GameAssist.ViewController", "initView, not find Id " + this.f6117c.getResources().getResourceName(i3) + " from" + getClass().getSimpleName());
                }
            }
        }
    }

    protected void W(int i2) {
    }

    protected boolean X(int i2) {
        return false;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void h(PrintWriter printWriter, String str) {
        super.h(printWriter, str);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("  hasClickRootView=");
        sb.append(this.f6114q != null);
        printWriter.println(sb.toString());
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void m(View view) {
        if (!(view instanceof RotationFrameLayout)) {
            super.m(view);
            V(view);
        } else {
            RotationFrameLayout rotationFrameLayout = (RotationFrameLayout) view;
            super.m(rotationFrameLayout.getShowView());
            V(rotationFrameLayout.getClickView());
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view != null) {
            W(view.getId());
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        if (view != null) {
            return X(view.getId());
        }
        return false;
    }
}
