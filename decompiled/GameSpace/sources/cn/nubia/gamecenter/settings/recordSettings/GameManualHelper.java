package cn.nubia.gamecenter.settings.recordSettings;

import android.view.View;
import android.widget.TextView;
import cn.nubia.gamecenter.settings.GameCenterTester;
import cn.nubia.gamecenter.settings.R;

/* loaded from: classes.dex */
public class GameManualHelper implements GameCenterTester.TestModeFunctions {
    private static final String TAG = "GameManualHelper";
    private final GameManualActivity m_activity;
    private final String m_title;
    private View m_titleView;
    private View.OnClickListener m_titleClickListener = new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.recordSettings.GameManualHelper.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            GameManualHelper.this.m_activity.finish();
        }
    };
    private boolean m_testMode = false;

    public GameManualHelper(GameManualActivity gameManualActivity, String str) {
        this.m_activity = gameManualActivity;
        this.m_title = str;
        init();
    }

    private View getTitleView() {
        if (this.m_titleView == null) {
            View findViewById = findViewById(R.id.left_name);
            this.m_titleView = findViewById;
            if (findViewById != null) {
                findViewById.setClickable(true);
                this.m_titleView.setOnClickListener(this.m_titleClickListener);
            }
        }
        return this.m_titleView;
    }

    private void init() {
        init_testMode();
        getTitleView();
        setTitle();
    }

    private void init_testMode() {
        new GameCenterTester(this.m_activity, this);
    }

    private void setTitle() {
        TextView textView = (TextView) findViewById(R.id.titlebar_text);
        if (textView != null) {
            textView.setText(this.m_title);
        }
    }

    public <T extends View> T findViewById(int i) {
        return (T) this.m_activity.findViewById(i);
    }

    public GameManualActivity getActivity() {
        return this.m_activity;
    }

    public boolean isTestMode() {
        return this.m_testMode;
    }

    public void onPause() {
    }

    public void onResume() {
    }

    @Override // cn.nubia.gamecenter.settings.GameCenterTester.TestModeFunctions
    public void setTestMode() {
        this.m_testMode = true;
    }
}
