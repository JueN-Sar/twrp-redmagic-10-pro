package com.zte.timeutil.enums;

/* loaded from: classes2.dex */
public enum ZoneIdEnum {
    ACT("Australia/Darwin", "澳洲/达尔文"),
    AET("Australia/Sydney", "澳洲/悉尼"),
    AGT("America/Argentina/Buenos_Aires", "美洲/阿根廷/布宜诺斯艾利斯"),
    ART("Africa/Cairo", "非洲/开罗"),
    AST("America/Anchorage", "美洲/安克雷奇"),
    BET("America/Sao_Paulo", "美洲/圣保罗"),
    BST("Asia/Dhaka", "亚洲/达卡"),
    CAT("Africa/Harare", "非洲/哈拉雷"),
    CNT("America/St_Johns", "美洲/圣约翰"),
    CST("America/Chicago", "美洲/芝加哥"),
    CTT("Asia/Shanghai", "亚洲/上海"),
    EAT("Africa/Addis_Ababa", "非洲/亚的斯亚贝巴"),
    ECT("Europe/Paris", "欧洲/巴黎"),
    IET("America/Indiana/Indianapolis", "美洲/印第安纳州/印第安纳波利斯"),
    IST("Asia/Kolkata", "亚洲/加尔各答"),
    JST("Asia/Tokyo", "亚洲/东京"),
    MIT("Pacific/Apia", "太平洋/阿皮亚"),
    NET("Asia/Yerevan", "亚洲/埃里温"),
    NST("Pacific/Auckland", "太平洋/奥克兰"),
    PLT("Asia/Karachi", "亚洲/卡拉奇"),
    PNT("America/Phoenix", "美洲/凤凰城"),
    PRT("America/Puerto_Rico", "美洲/波多黎各"),
    PST("America/Los_Angeles", "美洲/洛杉矶"),
    SST("Pacific/Guadalcanal", "太平洋/瓜达尔卡纳尔岛"),
    VST("Asia/Ho_Chi_Minh", "亚洲/胡志明市"),
    EST("-05:00", "东部标准时间"),
    MST("-07:00", "山地标准时间"),
    HST("-10:00", "夏威夷-阿留申标准时区");

    private final String zoneIdName;
    private final String zoneIdNameCn;

    ZoneIdEnum(String str, String str2) {
        this.zoneIdName = str;
        this.zoneIdNameCn = str2;
    }

    public String d() {
        return this.zoneIdName;
    }
}
