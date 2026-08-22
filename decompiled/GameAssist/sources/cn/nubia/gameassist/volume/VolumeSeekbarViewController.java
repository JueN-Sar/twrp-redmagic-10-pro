package cn.nubia.gameassist.volume;

import android.animation.ValueAnimator;
import android.view.View;
import android.widget.SeekBar;
import cn.nubia.componentcenter.api.volume.IVolumeController;
import cn.nubia.componentcenter.api.volume.VolumeListener;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.componentcenter.service.GameAssistComService;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.BaseViewController;
import cn.nubia.gameassist.meditationmode.MeditationModeViewController;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.search.GlobalSearchUtil;
import cn.nubia.gameassist.view.StairSeekBar;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.utils.GaLog;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class VolumeSeekbarViewController extends BaseViewController implements SeekBar.OnSeekBarChangeListener, MeditationModeViewController.MeditationViewCallback, VolumeListener {

    /* renamed from: q, reason: collision with root package name */
    private ValueAnimator f7748q;

    /* renamed from: r, reason: collision with root package name */
    private StairSeekBar f7749r;

    /* renamed from: s, reason: collision with root package name */
    private final IVolumeController f7750s;
    private MeditationModeViewController t;

    public VolumeSeekbarViewController(GameAssistWindowManager gameAssistWindowManager) {
        super(gameAssistWindowManager);
        this.f7750s = (IVolumeController) ((GameAssistComService) Router.getInstance().getService(GameAssistComService.class.getSimpleName())).a(IVolumeController.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void T(ValueAnimator valueAnimator) {
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

    @Override // cn.nubia.gameassist.common.BaseViewController
    public int C() {
        return R.id.game_assist_volume_seekbar_group;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    protected void D(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        str.hashCode();
        switch (str) {
            case "game_set_volume_down":
                GameAgentUtil.k(this.f6117c, iGameAssistClientCallback, inMsg);
                this.f7750s.setVolume(Math.max(this.f7750s.getVolume() - (this.f7750s.getMaxVolume() / 10), 0));
                break;
            case "game_set_volume_up":
                GameAgentUtil.k(this.f6117c, iGameAssistClientCallback, inMsg);
                int maxVolume = this.f7750s.getMaxVolume();
                this.f7750s.setVolume(Math.min(this.f7750s.getVolume() + (maxVolume / 10), maxVolume));
                break;
            case "game_set_volume_max":
                this.f7750s.setVolume(this.f7750s.getMaxVolume());
                GameAgentUtil.k(this.f6117c, iGameAssistClientCallback, inMsg);
                break;
            case "game_set_volume_min":
                GameAgentUtil.k(this.f6117c, iGameAssistClientCallback, inMsg);
                this.f7750s.setVolume(0);
                break;
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void P() {
        StairSeekBar stairSeekBar = this.f7749r;
        if (stairSeekBar != null) {
            stairSeekBar.setOnSeekBarChangeListener(null);
            this.f7749r = null;
        }
        MeditationModeViewController meditationModeViewController = this.t;
        if (meditationModeViewController != null) {
            meditationModeViewController.s0(this);
        }
        this.f6123m.setVisibility(0);
        this.f7750s.setListening(false, this);
    }

    @Override // cn.nubia.gameassist.meditationmode.MeditationModeViewController.MeditationViewCallback
    public void a(boolean z, boolean z2) {
        ValueAnimator valueAnimator = this.f7748q;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.f7748q.cancel();
        }
        View view = this.f6123m;
        if (view != null) {
            this.f7748q = null;
            if (!z2) {
                view.setVisibility(z ? 8 : 0);
                return;
            }
            if (z) {
                this.f7748q = ValueAnimator.ofFloat(1.0f, 0.0f);
            } else {
                this.f7748q = ValueAnimator.ofFloat(0.0f, 1.0f);
            }
            this.f7748q.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.gameassist.volume.f
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    VolumeSeekbarViewController.this.T(valueAnimator2);
                }
            });
            this.f7748q.setDuration(300L);
            this.f7748q.start();
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void h(PrintWriter printWriter, String str) {
        super.h(printWriter, str);
        this.f7750s.dump(printWriter, str);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void o(View view) {
        StairSeekBar stairSeekBar = (StairSeekBar) i(R.id.game_assist_volume_seekbar);
        this.f7749r = stairSeekBar;
        stairSeekBar.setOnSeekBarChangeListener(this);
        GlobalSearchUtil.r(this.f7749r, "game_assist_volume_seekbar");
        MeditationModeViewController meditationModeViewController = (MeditationModeViewController) k(MeditationModeViewController.class);
        this.t = meditationModeViewController;
        meditationModeViewController.i0(this);
        this.f6123m.setVisibility(0);
        this.f6123m.setAlpha(1.0f);
        this.f7750s.setListening(true, this);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
        GaLog.j("VolumeSeekbar", "onProgressChanged volume = " + i2 + " fromUser = " + z);
        if (z) {
            this.f7750s.setVolume(i2);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        this.f7750s.startTrackingTouch(this);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        this.f7750s.stopTrackingTouch(this);
    }

    @Override // cn.nubia.componentcenter.api.volume.VolumeListener
    public void onVolumeChanged(int i2, int i3) {
        StairSeekBar stairSeekBar = this.f7749r;
        if (stairSeekBar != null) {
            stairSeekBar.setMax(i3);
            this.f7749r.setProgress(i2, true);
            GaLog.j("VolumeSeekbar", "updateVolumeSeekbar mVolume=" + i2 + " mMaxVolume=" + i3 + " device:" + this.f7750s.getHeadsetType());
        }
    }
}
