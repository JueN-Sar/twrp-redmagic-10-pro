package cn.nubia.gameassist.bright;

import android.animation.ValueAnimator;
import android.view.View;
import android.widget.SeekBar;
import cn.nubia.componentcenter.api.dessert.IAppBrightnessProxy;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.bright.BrightSeekbarViewController;
import cn.nubia.gameassist.common.BaseViewController;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.performance.PerformanceViewController;
import cn.nubia.gameassist.search.GlobalSearchUtil;
import cn.nubia.gameassist.view.StairSeekBar;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.IModuleProxy;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.NubiaTrackManager;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class BrightSeekbarViewController extends BaseViewController<View> implements PerformanceViewController.PerformanceViewCallback, SeekBar.OnSeekBarChangeListener, IModuleProxy.ICallback<IAppBrightnessProxy> {

    /* renamed from: q, reason: collision with root package name */
    private final List f6103q;

    /* renamed from: r, reason: collision with root package name */
    private IAppBrightnessProxy f6104r;

    /* renamed from: s, reason: collision with root package name */
    private StairSeekBar f6105s;
    private ValueAnimator t;
    private Runnable u;

    public interface BrightViewCallback {
        default void onTrackingTouch(View view, boolean z) {
        }
    }

    public BrightSeekbarViewController(GameAssistWindowManager gameAssistWindowManager) {
        super(gameAssistWindowManager);
        this.f6103q = new ArrayList();
        this.u = new Runnable() { // from class: c.f
            @Override // java.lang.Runnable
            public final void run() {
                BrightSeekbarViewController.this.Y();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Y() {
        StairSeekBar stairSeekBar = this.f6105s;
        if (stairSeekBar != null) {
            stairSeekBar.setMax(this.f6104r.getMax());
            this.f6105s.setProgress(this.f6104r.getProgress(), true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Z(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        View view = this.f6123m;
        if (view != null) {
            view.setVisibility(0);
            this.f6123m.setAlpha(floatValue);
            if (floatValue == 0.0f) {
                this.f6123m.setVisibility(8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a0(BrightViewCallback brightViewCallback) {
        brightViewCallback.onTrackingTouch(this.f6123m, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b0(BrightViewCallback brightViewCallback) {
        brightViewCallback.onTrackingTouch(this.f6123m, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c0(BrightViewCallback brightViewCallback) {
        brightViewCallback.onTrackingTouch(this.f6123m, false);
    }

    private void e0() {
        if (this.f6105s != null) {
            this.f6125o.removeCallbacks(this.u);
            this.f6125o.post(this.u);
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public int C() {
        return R.id.game_assist_bright_seekbar_group;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    protected void D(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if (this.f6104r == null) {
            this.f6104r = (IAppBrightnessProxy) this.f6118h.S().a(IAppBrightnessProxy.class);
        }
        str.hashCode();
        switch (str) {
            case "game_set_brightness_max":
                this.f6104r.startTrackingTouch(this);
                this.f6104r.setProgress(this.f6104r.getMax(), true);
                this.f6104r.stopTrackingTouch(this);
                GameAgentUtil.k(this.f6117c, iGameAssistClientCallback, inMsg);
                e0();
                break;
            case "game_set_brightness_min":
                this.f6104r.startTrackingTouch(this);
                this.f6104r.setProgress(0, true);
                this.f6104r.stopTrackingTouch(this);
                GameAgentUtil.k(this.f6117c, iGameAssistClientCallback, inMsg);
                e0();
                break;
            case "game_set_brightness_up":
                this.f6104r.startTrackingTouch(this);
                int max = this.f6104r.getMax();
                this.f6104r.setProgress(Math.min(this.f6104r.getProgress() + (max / 10), max), true);
                this.f6104r.stopTrackingTouch(this);
                GameAgentUtil.k(this.f6117c, iGameAssistClientCallback, inMsg);
                e0();
                break;
            case "game_set_brightness_down":
                this.f6104r.startTrackingTouch(this);
                this.f6104r.setProgress(Math.max(this.f6104r.getProgress() - (this.f6104r.getMax() / 10), 0), true);
                this.f6104r.stopTrackingTouch(this);
                GameAgentUtil.k(this.f6117c, iGameAssistClientCallback, inMsg);
                e0();
                break;
            case "game_set_brightness_mode":
                GameAgentUtil.k(this.f6117c, iGameAssistClientCallback, inMsg);
                this.f6104r.changeBrightnessMode();
                break;
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void P() {
        this.f6104r.setListening(false, this);
        this.f6103q.forEach(new Consumer() { // from class: c.i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BrightSeekbarViewController.this.c0((BrightSeekbarViewController.BrightViewCallback) obj);
            }
        });
        ValueAnimator valueAnimator = this.t;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.t.cancel();
        }
        StairSeekBar stairSeekBar = this.f6105s;
        if (stairSeekBar != null) {
            stairSeekBar.setOnSeekBarChangeListener(null);
            this.f6105s = null;
        }
        ((PerformanceViewController) k(PerformanceViewController.class)).w0(this);
        this.f6123m.setVisibility(0);
    }

    public void X(BrightViewCallback brightViewCallback) {
        if (this.f6103q.contains(brightViewCallback)) {
            return;
        }
        this.f6103q.add(brightViewCallback);
    }

    @Override // cn.nubia.gameassist.performance.PerformanceViewController.PerformanceViewCallback
    public void a(boolean z, boolean z2) {
        ValueAnimator valueAnimator = this.t;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.t.cancel();
        }
        View view = this.f6123m;
        if (view != null) {
            this.t = null;
            if (!z2) {
                view.setVisibility(z ? 8 : 0);
                return;
            }
            if (z) {
                this.t = ValueAnimator.ofFloat(1.0f, 0.0f);
            } else {
                this.t = ValueAnimator.ofFloat(0.0f, 1.0f);
            }
            this.t.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: c.j
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    BrightSeekbarViewController.this.Z(valueAnimator2);
                }
            });
            this.t.setDuration(300L);
            this.t.start();
        }
    }

    @Override // com.zte.gameassist.common.IModuleProxy.ICallback
    /* renamed from: d0, reason: merged with bridge method [inline-methods] */
    public void onChanged(IAppBrightnessProxy iAppBrightnessProxy) {
        e0();
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void h(PrintWriter printWriter, String str) {
        super.h(printWriter, str);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void o(View view) {
        if (this.f6104r == null) {
            this.f6104r = (IAppBrightnessProxy) this.f6118h.S().a(IAppBrightnessProxy.class);
        }
        StairSeekBar stairSeekBar = (StairSeekBar) i(R.id.game_assist_bright_seekbar);
        this.f6105s = stairSeekBar;
        stairSeekBar.setMax(this.f6104r.getMax());
        this.f6105s.setProgress(this.f6104r.getProgress(), false);
        this.f6105s.setOnSeekBarChangeListener(this);
        GlobalSearchUtil.r(this.f6105s, "game_assist_bright_seekbar");
        ((PerformanceViewController) k(PerformanceViewController.class)).l0(this);
        this.f6123m.setVisibility(0);
        this.f6123m.setAlpha(1.0f);
        this.f6104r.setListening(true, this);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
        if (SystemMgr.H()) {
            this.f6104r.setProgress(i2, z);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        NubiaTrackManager.p().k("brightness");
        this.f6104r.startTrackingTouch(this);
        this.f6103q.forEach(new Consumer() { // from class: c.g
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BrightSeekbarViewController.this.a0((BrightSeekbarViewController.BrightViewCallback) obj);
            }
        });
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        this.f6104r.stopTrackingTouch(this);
        this.f6103q.forEach(new Consumer() { // from class: c.h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BrightSeekbarViewController.this.b0((BrightSeekbarViewController.BrightViewCallback) obj);
            }
        });
    }
}
