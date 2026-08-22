package cn.nubia.plugin.superresolution;

import cn.nubia.gameassist.GameAssistApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/* loaded from: classes.dex */
public class SuperResolutionTypeDataManager {

    /* renamed from: c, reason: collision with root package name */
    private static volatile SuperResolutionTypeDataManager f8693c;

    /* renamed from: a, reason: collision with root package name */
    private volatile List f8694a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    private Gson f8695b = new Gson();

    public static SuperResolutionTypeDataManager c() {
        if (f8693c == null) {
            synchronized (SuperResolutionTypeDataManager.class) {
                try {
                    if (f8693c == null) {
                        f8693c = new SuperResolutionTypeDataManager();
                    }
                } finally {
                }
            }
        }
        return f8693c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean e(String str, SuperResolutionTypeItemData superResolutionTypeItemData) {
        return str.equals(superResolutionTypeItemData.c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean f(String str, SuperResolutionTypeItemData superResolutionTypeItemData) {
        return str.equals(superResolutionTypeItemData.c());
    }

    public String d(final String str, String str2) {
        GaLog.a("SuperResolutionTypeDataManager", "getItem: packageName = " + str);
        Optional findFirst = this.f8694a.stream().filter(new Predicate() { // from class: cn.nubia.plugin.superresolution.f
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean e2;
                e2 = SuperResolutionTypeDataManager.e(str, (SuperResolutionTypeItemData) obj);
                return e2;
            }
        }).findFirst();
        if (findFirst.isPresent()) {
            SuperResolutionTypeItemData superResolutionTypeItemData = (SuperResolutionTypeItemData) findFirst.get();
            str2.hashCode();
            if (str2.equals("frameRate")) {
                return superResolutionTypeItemData.a();
            }
            if (str2.equals("imageQuality")) {
                return superResolutionTypeItemData.b();
            }
        }
        return "imageQuality".equals(str2) ? "origin" : "frameRate_origin";
    }

    public void g() {
        String C = SharedPreferencesUtil.k(GameAssistApplication.j()).C();
        if (C != null) {
            this.f8694a = (List) this.f8695b.fromJson(C, new TypeToken<List<SuperResolutionTypeItemData>>(this) { // from class: cn.nubia.plugin.superresolution.SuperResolutionTypeDataManager.1
            }.getType());
        }
        GaLog.a("SuperResolutionTypeDataManager", "loadList: mTypeItemDataList = " + this.f8694a);
    }

    public void h(final String str, String str2, String str3) {
        Optional findFirst = this.f8694a.stream().filter(new Predicate() { // from class: cn.nubia.plugin.superresolution.e
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean f2;
                f2 = SuperResolutionTypeDataManager.f(str, (SuperResolutionTypeItemData) obj);
                return f2;
            }
        }).findFirst();
        if (findFirst.isPresent()) {
            SuperResolutionTypeItemData superResolutionTypeItemData = (SuperResolutionTypeItemData) findFirst.get();
            str2.hashCode();
            if (str2.equals("frameRate")) {
                superResolutionTypeItemData.d(str3);
            } else if (str2.equals("imageQuality")) {
                superResolutionTypeItemData.e(str3);
            }
        } else {
            String str4 = "imageQuality".equals(str2) ? str3 : null;
            if (!"frameRate".equals(str2)) {
                str3 = null;
            }
            this.f8694a.add(new SuperResolutionTypeItemData(str, str4, str3));
        }
        i();
    }

    public void i() {
        SharedPreferencesUtil.k(GameAssistApplication.j()).I(this.f8695b.toJson(this.f8694a));
        GaLog.a("SuperResolutionTypeDataManager", "saveList mSuperResolutionTypeItemDataList = " + this.f8694a);
    }
}
