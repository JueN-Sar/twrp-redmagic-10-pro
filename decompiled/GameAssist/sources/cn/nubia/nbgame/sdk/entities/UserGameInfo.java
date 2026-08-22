package cn.nubia.nbgame.sdk.entities;

import java.io.Serializable;

/* loaded from: classes.dex */
public class UserGameInfo implements Serializable {
    public int orientation;
    public String pkgName;

    public UserGameInfo(String str, int i2) {
        this.pkgName = str;
        this.orientation = i2;
    }
}
