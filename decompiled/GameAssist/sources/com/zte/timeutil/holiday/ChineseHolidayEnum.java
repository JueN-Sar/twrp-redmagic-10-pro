package com.zte.timeutil.holiday;

import com.zte.timeutil.LunarDate;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public enum ChineseHolidayEnum implements Holiday {
    CHUNJIE("春节", "0101"),
    DaNian("大年", "0101"),
    YUANXIAOJIE("元宵节", "0115"),
    YUANXIAO("元宵", "0115"),
    LONGTAITOU("龙抬头", "0202"),
    DUANWUJIE("端午节", "0505"),
    DUANWU("端午", "0505"),
    QIXIJIE("七夕节", "0707"),
    QIXI("七夕", "0707"),
    ZHONGQIUJIE("中秋节", "0815"),
    ZHONGQIU("中秋", "0815"),
    CHONGYANGJIE("重阳节", "0909"),
    CHONGYANG("重阳", "0909"),
    LABAJIE("腊八节", "1208"),
    LABA("腊八", "1208"),
    XIAONIANNORTH("北方小年", "1223"),
    XIAONIANSOUTH("南方小年", "1224"),
    CHUXINight("除夕夜", "CHUXI"),
    CHUXIJIE("除夕节", "CHUXI"),
    CHUXI("除夕", "CHUXI"),
    DEFAULT_HOLIDAY("", "");

    private final String name;
    private final String pattern;

    ChineseHolidayEnum(String str, String str2) {
        this.name = str;
        this.pattern = str2;
    }

    public static Map f() {
        HashMap hashMap = new HashMap();
        for (ChineseHolidayEnum chineseHolidayEnum : values()) {
            hashMap.put(chineseHolidayEnum.o(), chineseHolidayEnum.j());
        }
        return hashMap;
    }

    public static Map h() {
        HashMap hashMap = new HashMap();
        for (ChineseHolidayEnum chineseHolidayEnum : values()) {
            hashMap.put(chineseHolidayEnum.j(), chineseHolidayEnum.o());
        }
        return hashMap;
    }

    public static LocalDate l(int i2, int i3) {
        LunarDate g2 = LunarDate.g(LocalDate.now());
        int t = g2.t();
        LunarDate B = LunarDate.B(t, i2, i3);
        return B.m().compareTo(g2.m()) > 0 ? LunarDate.x(B) : LunarDate.x(LunarDate.B(t + 1, i2, i3));
    }

    public static LocalDate n() {
        int t = LunarDate.g(LocalDate.now()).t();
        return LunarDate.x(LunarDate.B(t, 12, LunarDate.A(t, 12)));
    }

    public String j() {
        return this.name;
    }

    public String o() {
        return this.pattern;
    }
}
