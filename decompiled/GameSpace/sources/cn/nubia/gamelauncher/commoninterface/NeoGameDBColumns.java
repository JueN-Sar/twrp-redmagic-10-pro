package cn.nubia.gamelauncher.commoninterface;

import cn.nubia.gamelauncher.util.Util;

/* loaded from: classes.dex */
public final class NeoGameDBColumns {
    public static final String ACTION;
    public static final String APP_ID = "app_id";
    public static final String ICON;
    public static final String ID;
    public static final String PACKAGENAME;
    public static final String PROGRESS;
    public static final String REQUEST_ID = "request_id";
    public static final String STATUS = "status";
    public static final String STATUS_CONNECT = "STATUS_CONNECT";
    public static final String STATUS_DOWNLOADING = "STATUS_DOWNLOADING";
    public static final String STATUS_IN_INSTALLTION = "STATUS_IN_INSTALLTION";
    public static final String STATUS_PAUSE = "STATUS_PAUSE";
    public static final String STATUS_SUCCESS = "STATUS_SUCCESS";
    public static final String TITLE;
    public static final String TYPE = "type";
    public static final String VERSION_CODE = "version_code";

    static {
        ID = Util.isMyOs() ? "_id" : "id";
        TITLE = Util.isMyOs() ? "app_name" : "title";
        PACKAGENAME = Util.isMyOs() ? "package_name" : "pkgname";
        PROGRESS = Util.isMyOs() ? "progress" : "process";
        ACTION = Util.isMyOs() ? "ACTION" : "action";
        ICON = Util.isMyOs() ? "app_icon" : "icon";
    }
}
