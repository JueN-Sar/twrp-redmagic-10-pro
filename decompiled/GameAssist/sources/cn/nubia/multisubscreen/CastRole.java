package cn.nubia.multisubscreen;

import com.google.android.gms.common.api.Api;

/* loaded from: classes.dex */
public enum CastRole {
    SOURCE(1),
    SINK(0),
    UN_KNOW(Api.BaseClientBuilder.API_PRIORITY_OTHER);

    private int role;

    CastRole(int i2) {
        this.role = i2;
    }
}
