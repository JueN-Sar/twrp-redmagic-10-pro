package cn.nubia.gamecenter.settings;

import android.view.View;
import androidx.core.view.ViewCompat;
import cn.nubia.gamecenter.settings.summary.entities.GameAppInfo;
import cn.nubia.gamecenter.settings.summary.entities.GameTimeInfo;
import cn.nubia.gamecenter.settings.summary.entities.OneGameTimeAndLaunchTimesInfo;
import cn.nubia.gamecenter.settings.summary.presenter.GameParmsPresenterImpl;
import cn.nubia.gamecenter.settings.summary.presenter.ICallback;
import cn.nubia.gamecenter.settings.summary.presenter.IGameParmsPresenter;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class GameCarrerHelper implements ICallback {
    private static final String TAG = "GameCarrerHelper";
    private IGameParmsPresenter mGameParmsPresenter;
    private String mGamePkgName;
    private final GameCarrerActivity m_activity;
    private final int m_idMainContent;
    private View.OnClickListener m_titleClickListener = new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.GameCarrerHelper.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            GameCarrerHelper.this.m_activity.finish();
        }
    };
    private View m_titleView;

    public GameCarrerHelper(GameCarrerActivity gameCarrerActivity, String str, int i) {
        this.m_activity = gameCarrerActivity;
        this.m_idMainContent = i;
        this.mGameParmsPresenter = new GameParmsPresenterImpl(gameCarrerActivity.getApplicationContext(), str, this);
        this.mGamePkgName = str;
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
        getTitleView();
        reloadData();
    }

    private void reloadData() {
        IGameParmsPresenter iGameParmsPresenter = this.mGameParmsPresenter;
        if (iGameParmsPresenter != null) {
            iGameParmsPresenter.loadGameParms();
        }
    }

    private void setContentBackground(boolean z) {
        View findViewById = findViewById(R.id.gcs_main_content);
        if (findViewById == null) {
            return;
        }
        if (z) {
            findViewById.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        } else {
            findViewById.setBackground(null);
        }
    }

    public <T extends View> T findViewById(int i) {
        return (T) this.m_activity.findViewById(i);
    }

    @Override // cn.nubia.gamecenter.settings.summary.presenter.ICallback
    public void gameParms(GameTimeInfo[] gameTimeInfoArr, List<List<GameAppInfo>> list) {
        int i = 0;
        for (GameTimeInfo gameTimeInfo : gameTimeInfoArr) {
            LogUtil.d(TAG, "gameParms GameTimeInfo:" + gameTimeInfo.toString());
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            LogUtil.d(TAG, "gameParms GameAppInfo:" + list.get(i2).toString());
        }
        for (List<GameAppInfo> list2 : list) {
            if (i == 1) {
                Iterator<GameAppInfo> it = list2.iterator();
                while (it.hasNext() && !it.next().pkgName.equals(this.mGamePkgName)) {
                }
                return;
            }
            i++;
        }
    }

    @Override // cn.nubia.gamecenter.settings.summary.presenter.ICallback
    public void gameParmsOneGame(OneGameTimeAndLaunchTimesInfo oneGameTimeAndLaunchTimesInfo) {
        this.m_activity.updateUI(oneGameTimeAndLaunchTimesInfo);
    }

    public GameCarrerActivity getActivity() {
        return this.m_activity;
    }

    public void onDestroy() {
    }

    public void onPause() {
        stopLoadGameParms();
    }

    public void onResume() {
        reloadData();
    }

    public void stopLoadGameParms() {
        this.mGameParmsPresenter.stopLoadGameParms();
    }
}
