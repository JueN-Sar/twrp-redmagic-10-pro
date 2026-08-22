package cn.nubia.gamelauncher.gamecontrolpanel;

import cn.nubia.gamelauncher.gamecontrolpanel.TouchOperationBean;

/* loaded from: classes.dex */
public interface IGameStrengthSelectedListener {
    void onAdjustOperationDataChanged(TouchOperationBean.OperationTypeParams operationTypeParams);

    void onGameStrengthIndicatorSelected(int i);

    void onGameStrengthSelected(int i, int i2, int[] iArr);
}
