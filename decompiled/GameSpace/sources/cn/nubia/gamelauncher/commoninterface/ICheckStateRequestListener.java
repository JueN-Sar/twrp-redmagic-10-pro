package cn.nubia.gamelauncher.commoninterface;

import cn.nubia.gamelauncher.bean.CheckStateResponse;

/* loaded from: classes.dex */
public interface ICheckStateRequestListener {
    void responseError(String str);

    void responseInfo(CheckStateResponse checkStateResponse);
}
