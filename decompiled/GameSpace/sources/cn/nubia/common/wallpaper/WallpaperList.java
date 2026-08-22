package cn.nubia.common.wallpaper;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class WallpaperList {
    public static ArrayList<String> LOCAL_WALLPAPER_LIST = null;
    public static final String RES_URL_PREFIX = "android.resource://cn.nubia.gamelauncher/mipmap/";

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        LOCAL_WALLPAPER_LIST = arrayList;
        arrayList.add("android.resource://cn.nubia.gamelauncher/mipmap/wallpaper_0");
        LOCAL_WALLPAPER_LIST.add("android.resource://cn.nubia.gamelauncher/mipmap/wallpaper_1");
        LOCAL_WALLPAPER_LIST.add("android.resource://cn.nubia.gamelauncher/mipmap/wallpaper_2");
        LOCAL_WALLPAPER_LIST.add("android.resource://cn.nubia.gamelauncher/mipmap/wallpaper_3");
    }
}
