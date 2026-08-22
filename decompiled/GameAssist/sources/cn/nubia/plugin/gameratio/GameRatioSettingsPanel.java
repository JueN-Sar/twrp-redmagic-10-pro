package cn.nubia.plugin.gameratio;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import cn.nubia.gameassist.R;

/* loaded from: classes.dex */
public class GameRatioSettingsPanel extends RelativeLayout {
    private View mCancelView;
    private View mCloseView;
    private GameRatioData mGameRatioData;
    private View mHelpView;
    private boolean mIsDataSet;
    private View mOkView;
    private View.OnClickListener mOnClickListener;
    private OnOperationListener mOnOperationListener;
    private ChoiceGridView mOriChoiceView;
    private ChoiceGridView mSizeChoiceView;

    public interface OnOperationListener {
        void a(GameRatioData gameRatioData, boolean z);

        void b();

        void c(GameRatioData gameRatioData);
    }

    public GameRatioSettingsPanel(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOnClickListener = new View.OnClickListener() { // from class: cn.nubia.plugin.gameratio.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameRatioSettingsPanel.this.e(view);
            }
        };
    }

    private void b() {
        OnOperationListener onOperationListener = this.mOnOperationListener;
        if (onOperationListener != null) {
            onOperationListener.c(this.mGameRatioData);
        }
    }

    private boolean d(GameRatioData gameRatioData, GameRatioData gameRatioData2) {
        return (gameRatioData.a() == gameRatioData2.a() && gameRatioData.c() == gameRatioData2.c()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(View view) {
        int id = view.getId();
        if (id == R.id.ok) {
            f();
            return;
        }
        if (id == R.id.cancel || id == R.id.close) {
            b();
        } else if (id == R.id.help) {
            c();
        }
    }

    private void f() {
        OnOperationListener onOperationListener = this.mOnOperationListener;
        if (onOperationListener != null) {
            if (!this.mIsDataSet) {
                onOperationListener.c(this.mGameRatioData);
            } else {
                GameRatioData gameRatioData = new GameRatioData(this.mGameRatioData.b(), this.mOriChoiceView.getCheckedId(), this.mSizeChoiceView.getCheckedId());
                this.mOnOperationListener.a(gameRatioData, d(this.mGameRatioData, gameRatioData));
            }
        }
    }

    public void c() {
        OnOperationListener onOperationListener = this.mOnOperationListener;
        if (onOperationListener != null) {
            onOperationListener.b();
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        View findViewById = findViewById(R.id.close);
        this.mCloseView = findViewById;
        findViewById.setOnClickListener(this.mOnClickListener);
        View findViewById2 = findViewById(R.id.ok);
        this.mOkView = findViewById2;
        findViewById2.setOnClickListener(this.mOnClickListener);
        View findViewById3 = findViewById(R.id.cancel);
        this.mCancelView = findViewById3;
        findViewById3.setOnClickListener(this.mOnClickListener);
        View findViewById4 = findViewById(R.id.help);
        this.mHelpView = findViewById4;
        findViewById4.setOnClickListener(this.mOnClickListener);
        this.mOriChoiceView = (ChoiceGridView) findViewById(R.id.orientation_choice);
        if (!GameRatioMgr.f8397s) {
            findViewById(R.id.orientation_title).setVisibility(8);
            this.mOriChoiceView.setVisibility(8);
        }
        this.mSizeChoiceView = (ChoiceGridView) findViewById(R.id.size_choice);
    }

    public void setData(GameRatioData gameRatioData) {
        int[] iArr;
        int[] iArr2;
        this.mIsDataSet = true;
        this.mGameRatioData = gameRatioData;
        if (GameRatioMgr.f8397s) {
            this.mOriChoiceView.b(new int[]{0, 1, 2}, new int[]{R.string.gameratio_ori_system, R.string.gameratio_ori_land, R.string.gameratio_ori_port}, gameRatioData.a());
        }
        if ("com.tencent.tmgp.sgame".equals(gameRatioData.b())) {
            iArr2 = new int[]{0, 1, 2, 3};
            iArr = new int[]{R.string.gameratio_size_original, R.string.gameratio_size_4_3, R.string.gameratio_size_16_9, R.string.gameratio_size_21_9};
        } else {
            iArr = new int[]{R.string.gameratio_size_original, R.string.gameratio_size_4_3, R.string.gameratio_size_16_9, R.string.gameratio_size_21_9, R.string.gameratio_size_32_9};
            iArr2 = new int[]{0, 1, 2, 3, 4};
        }
        this.mSizeChoiceView.b(iArr2, iArr, gameRatioData.c());
    }

    public void setOnOperationListener(OnOperationListener onOperationListener) {
        this.mOnOperationListener = onOperationListener;
    }

    public GameRatioSettingsPanel(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mOnClickListener = new View.OnClickListener() { // from class: cn.nubia.plugin.gameratio.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameRatioSettingsPanel.this.e(view);
            }
        };
    }

    public GameRatioSettingsPanel(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mOnClickListener = new View.OnClickListener() { // from class: cn.nubia.plugin.gameratio.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameRatioSettingsPanel.this.e(view);
            }
        };
    }
}
