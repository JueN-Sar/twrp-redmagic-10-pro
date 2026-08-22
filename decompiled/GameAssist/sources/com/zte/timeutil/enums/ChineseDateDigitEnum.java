package com.zte.timeutil.enums;

import java.util.HashMap;
import java.util.function.Supplier;

/* loaded from: classes2.dex */
public enum ChineseDateDigitEnum {
    ZERO("〇"),
    ONE("一"),
    TWO("二"),
    THREE("三"),
    FOUR("四"),
    FIVE("五"),
    SIX("六"),
    SEVEN("七"),
    EIGHT("八"),
    NINE("九"),
    TEN("十"),
    ELEVEN("十一"),
    TWELVE("十二"),
    THIRTEEN("十三"),
    FOURTEEN("十四"),
    FIFTEEN("十五"),
    SIXTEEN("十六"),
    SEVENTEEN("十七"),
    EIGHTEEN("十八"),
    NINETEEN("十九"),
    TWENTY("二十"),
    TWENTYONE("二十一"),
    TWENTYTWO("二十二"),
    TWENTYTHREE("二十三"),
    TWENTYFOUR("二十四"),
    TWENTYFIVE("二十五"),
    TWENTYSIX("二十六"),
    TWENTYSEVEN("二十七"),
    TWENTYEIGHT("二十八"),
    TWENTYNINE("二十九"),
    THIRTY("三十"),
    THIRTYONE("三十一");

    public static final String CHINESE_DATE_DIGIT_MAP = "CHINESE_DATE_DIGIT_MAP";
    public static final ChineseDateDigitEnum[] ENUMS = values();
    private String chineseDigit;

    /* renamed from: com.zte.timeutil.enums.ChineseDateDigitEnum$1, reason: invalid class name */
    class AnonymousClass1 implements Supplier<Object> {
        @Override // java.util.function.Supplier
        public Object get() {
            HashMap hashMap = new HashMap();
            for (ChineseDateDigitEnum chineseDateDigitEnum : ChineseDateDigitEnum.ENUMS) {
                hashMap.put(chineseDateDigitEnum.d(), Integer.valueOf(chineseDateDigitEnum.ordinal()));
            }
            return hashMap;
        }
    }

    ChineseDateDigitEnum(String str) {
        this.chineseDigit = str;
    }

    public String d() {
        return this.chineseDigit;
    }
}
