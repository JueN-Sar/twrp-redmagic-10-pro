package cn.nubia.gameassist.search;

import android.net.Uri;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class GlobalSearchConstants {

    /* renamed from: a, reason: collision with root package name */
    public static final Uri f7383a = Uri.parse("content://cn.nubia.gameassist.globalsearch");

    /* renamed from: b, reason: collision with root package name */
    public static final Uri f7384b = Uri.parse("content://cn.nubia.globalsearch.globalsearchprovider");

    /* renamed from: c, reason: collision with root package name */
    public static final Uri f7385c = Uri.parse("content://cn.nubia.virtualgamehandle/app_game_handle");

    /* renamed from: d, reason: collision with root package name */
    static final ArrayList f7386d;

    static {
        ArrayList arrayList = new ArrayList();
        f7386d = arrayList;
        arrayList.add("name");
        arrayList.add("help");
        arrayList.add("start_type");
        arrayList.add("app_label");
        arrayList.add("view_id");
        arrayList.add("action");
        arrayList.add("category");
        arrayList.add("intent_flag");
        arrayList.add("package_name");
        arrayList.add("class_name");
        arrayList.add("feature");
        arrayList.add("authorities");
        arrayList.add("package_list");
        arrayList.add("param_string");
        arrayList.add("param_string1");
        arrayList.add("param_string2");
        arrayList.add("param_int");
        arrayList.add("param_int1");
        arrayList.add("param_boolean");
        arrayList.add("param_boolean1");
    }
}
