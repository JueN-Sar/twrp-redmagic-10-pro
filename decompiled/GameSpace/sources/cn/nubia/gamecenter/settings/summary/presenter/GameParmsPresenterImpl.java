package cn.nubia.gamecenter.settings.summary.presenter;

import android.content.Context;
import android.util.Log;
import cn.nubia.gamecenter.settings.summary.entities.GameAppInfo;
import cn.nubia.gamecenter.settings.summary.entities.GameTimeInfo;
import cn.nubia.gamecenter.settings.summary.entities.OneGameTimeAndLaunchTimesInfo;
import cn.nubia.gamecenter.settings.summary.model.GameTimeModeImpl;
import cn.nubia.gamecenter.settings.summary.model.IGameTimeMode;
import java.util.List;

/* loaded from: classes.dex */
public class GameParmsPresenterImpl implements IGameParmsPresenter, IGameParmsCallback {
    private static final int SUGGEST_FATIGUE_END_DAY_BEFORE_SEVEN = 42;
    private static final int SUGGEST_FATIGUE_END_TODAY = 6;
    private static final int SUGGEST_FATIGUE_START_DAY_BEFORE_SEVEN = 21;
    private static final int SUGGEST_FATIGUE_START_TODAY = 3;
    private static final int SUGGEST_HEALTH_END_DAY_BEFORE_SEVEN = 21;
    private static final int SUGGEST_HEALTH_END_TODAY = 3;
    private static final int SUGGEST_HEALTH_START_DAY_BEFORE_SEVEN = 0;
    private static final int SUGGEST_HEALTH_START_TODAY = 0;
    private static final String TAG = "GameParmsPresenterImpl";
    private ICallback mCallback;
    private IGameTimeMode mGameTimeMode;

    public GameParmsPresenterImpl(Context context, ICallback iCallback) {
        this.mGameTimeMode = new GameTimeModeImpl(context, this);
        this.mCallback = iCallback;
    }

    public GameParmsPresenterImpl(Context context, String str, ICallback iCallback) {
        this.mGameTimeMode = new GameTimeModeImpl(context, str, this);
        this.mCallback = iCallback;
    }

    private int millisToHour(long j) {
        long j2 = 86400000;
        long j3 = j / j2;
        return (int) (((j - (j2 * j3)) / 3600000) + (j3 * 24));
    }

    private void setSuggestTypeDayBeforeSeven(GameTimeInfo gameTimeInfo) {
        if (gameTimeInfo != null) {
            int millisToHour = millisToHour(gameTimeInfo.mGameTotalTime);
            Log.d(TAG, "setSuggestTypeDayBeforeSeven, totalTime = " + millisToHour);
            if (millisToHour >= 0 && millisToHour < 21) {
                gameTimeInfo.mSuggestType = 0;
            } else if (millisToHour < 21 || millisToHour >= 42) {
                gameTimeInfo.mSuggestType = 2;
            } else {
                gameTimeInfo.mSuggestType = 1;
            }
        }
    }

    private void setSuggestTypeToday(GameTimeInfo gameTimeInfo) {
        if (gameTimeInfo != null) {
            int millisToHour = millisToHour(gameTimeInfo.mGameTotalTime);
            Log.d(TAG, "setSuggestTypeToday, totalTime = " + millisToHour);
            if (millisToHour >= 0 && millisToHour < 3) {
                gameTimeInfo.mSuggestType = 0;
            } else if (millisToHour < 3 || millisToHour >= 6) {
                gameTimeInfo.mSuggestType = 2;
            } else {
                gameTimeInfo.mSuggestType = 1;
            }
        }
    }

    @Override // cn.nubia.gamecenter.settings.summary.presenter.IGameParmsCallback
    public void gameParmsCallback(GameTimeInfo[] gameTimeInfoArr, List<List<GameAppInfo>> list) {
        if (gameTimeInfoArr == null || 2 != gameTimeInfoArr.length) {
            Log.e(TAG, "gameParmsCallback, invalid infos !!!");
            return;
        }
        setSuggestTypeToday(gameTimeInfoArr[0]);
        setSuggestTypeDayBeforeSeven(gameTimeInfoArr[1]);
        this.mCallback.gameParms(gameTimeInfoArr, list);
    }

    @Override // cn.nubia.gamecenter.settings.summary.presenter.IGameParmsPresenter
    public void loadGameParms() {
        this.mGameTimeMode.startLoadGameTimeParms();
    }

    @Override // cn.nubia.gamecenter.settings.summary.presenter.IGameParmsCallback
    public void oneGameParmsCallback(OneGameTimeAndLaunchTimesInfo oneGameTimeAndLaunchTimesInfo) {
        this.mCallback.gameParmsOneGame(oneGameTimeAndLaunchTimesInfo);
    }

    @Override // cn.nubia.gamecenter.settings.summary.presenter.IGameParmsPresenter
    public void stopLoadGameParms() {
        this.mGameTimeMode.cancelLoadTask();
    }
}
