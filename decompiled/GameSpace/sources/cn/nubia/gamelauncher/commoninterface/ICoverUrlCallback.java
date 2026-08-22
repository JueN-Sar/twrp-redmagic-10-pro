package cn.nubia.gamelauncher.commoninterface;

import cn.nubia.gamelauncher.bean.AtmosphereBean;
import java.util.ArrayList;

/* loaded from: classes.dex */
public interface ICoverUrlCallback {
    void responseError(String str);

    void responseInfo(ArrayList<AtmosphereBean> arrayList);
}
