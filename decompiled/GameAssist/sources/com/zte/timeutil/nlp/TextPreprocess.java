package com.zte.timeutil.nlp;

import com.zte.timeutil.enums.RegexEnum;
import com.zte.timeutil.utils.RegexCache;
import java.util.HashSet;
import java.util.regex.Matcher;

/* loaded from: classes2.dex */
public class TextPreprocess {
    private static String a(String str) {
        Matcher matcher = RegexEnum.TextPreprocessDelDecimalStrSeparator.d().matcher(str);
        HashSet hashSet = new HashSet();
        while (matcher.find()) {
            hashSet.add(Integer.valueOf(matcher.start()));
        }
        Matcher matcher2 = RegexEnum.TextPreprocessDelDecimalStr.d().matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        for (boolean find = matcher2.find(); find; find = matcher2.find()) {
            if (!hashSet.contains(Integer.valueOf(matcher2.end()))) {
                matcher2.appendReplacement(stringBuffer, "");
            }
        }
        matcher2.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public static String b(String str, String str2) {
        Matcher matcher = RegexCache.a(str2).matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        for (boolean find = matcher.find(); find; find = matcher.find()) {
            matcher.appendReplacement(stringBuffer, "");
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public static String c(String str) {
        int i2;
        int parseInt;
        Matcher matcher = RegexEnum.TextPreprocessNumberTranslatorOne.d().matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        boolean find = matcher.find();
        while (true) {
            int i3 = 0;
            if (!find) {
                break;
            }
            String[] split = matcher.group().split("万");
            if (split.length == 2) {
                i3 = (e(split[0]) * 10000) + (e(split[1]) * 1000);
            }
            matcher.appendReplacement(stringBuffer, Integer.toString(i3));
            find = matcher.find();
        }
        matcher.appendTail(stringBuffer);
        Matcher matcher2 = RegexEnum.TextPreprocessNumberTranslatorTwo.d().matcher(stringBuffer.toString());
        StringBuffer stringBuffer2 = new StringBuffer();
        for (boolean find2 = matcher2.find(); find2; find2 = matcher2.find()) {
            String[] split2 = matcher2.group().split("千");
            matcher2.appendReplacement(stringBuffer2, Integer.toString(split2.length == 2 ? (e(split2[0]) * 1000) + (e(split2[1]) * 100) : 0));
        }
        matcher2.appendTail(stringBuffer2);
        Matcher matcher3 = RegexEnum.TextPreprocessNumberTranslatorThree.d().matcher(stringBuffer2.toString());
        StringBuffer stringBuffer3 = new StringBuffer();
        for (boolean find3 = matcher3.find(); find3; find3 = matcher3.find()) {
            String[] split3 = matcher3.group().split("百");
            matcher3.appendReplacement(stringBuffer3, Integer.toString(split3.length == 2 ? (e(split3[0]) * 100) + (e(split3[1]) * 10) : 0));
        }
        matcher3.appendTail(stringBuffer3);
        Matcher matcher4 = RegexEnum.TextPreprocessNumberTranslatorFour.d().matcher(stringBuffer3.toString());
        StringBuffer stringBuffer4 = new StringBuffer();
        for (boolean find4 = matcher4.find(); find4; find4 = matcher4.find()) {
            matcher4.appendReplacement(stringBuffer4, Integer.toString(e(matcher4.group())));
        }
        matcher4.appendTail(stringBuffer4);
        Matcher matcher5 = RegexEnum.TextPreprocessNumberTranslatorFive.d().matcher(stringBuffer4.toString());
        StringBuffer stringBuffer5 = new StringBuffer();
        for (boolean find5 = matcher5.find(); find5; find5 = matcher5.find()) {
            matcher5.appendReplacement(stringBuffer5, Integer.toString(e(matcher5.group())));
        }
        matcher5.appendTail(stringBuffer5);
        Matcher matcher6 = RegexEnum.TextPreprocessNumberTranslatorSix.d().matcher(stringBuffer5.toString());
        StringBuffer stringBuffer6 = new StringBuffer();
        for (boolean find6 = matcher6.find(); find6; find6 = matcher6.find()) {
            String[] split4 = matcher6.group().split("十");
            if (split4.length != 0) {
                if (split4.length == 1) {
                    int parseInt2 = Integer.parseInt(split4[0]);
                    if (parseInt2 != 0) {
                        i2 = parseInt2 * 10;
                    }
                } else if (split4.length == 2) {
                    i2 = Integer.parseInt(split4[1]) + ((split4[0].equals("") || (parseInt = Integer.parseInt(split4[0])) == 0) ? 10 : parseInt * 10);
                } else {
                    i2 = 0;
                }
                matcher6.appendReplacement(stringBuffer6, Integer.toString(i2));
            }
            i2 = 10;
            matcher6.appendReplacement(stringBuffer6, Integer.toString(i2));
        }
        matcher6.appendTail(stringBuffer6);
        Matcher matcher7 = RegexEnum.TextPreprocessNumberTranslatorSeven.d().matcher(stringBuffer6.toString());
        StringBuffer stringBuffer7 = new StringBuffer();
        for (boolean find7 = matcher7.find(); find7; find7 = matcher7.find()) {
            String[] split5 = matcher7.group().split("百");
            matcher7.appendReplacement(stringBuffer7, Integer.toString(split5.length == 1 ? Integer.parseInt(split5[0]) * 100 : split5.length == 2 ? Integer.parseInt(split5[1]) + (Integer.parseInt(split5[0]) * 100) : 0));
        }
        matcher7.appendTail(stringBuffer7);
        Matcher matcher8 = RegexEnum.TextPreprocessNumberTranslatorEight.d().matcher(stringBuffer7.toString());
        StringBuffer stringBuffer8 = new StringBuffer();
        for (boolean find8 = matcher8.find(); find8; find8 = matcher8.find()) {
            String[] split6 = matcher8.group().split("千");
            matcher8.appendReplacement(stringBuffer8, Integer.toString(split6.length == 1 ? Integer.parseInt(split6[0]) * 1000 : split6.length == 2 ? Integer.parseInt(split6[1]) + (Integer.parseInt(split6[0]) * 1000) : 0));
        }
        matcher8.appendTail(stringBuffer8);
        Matcher matcher9 = RegexEnum.TextPreprocessNumberTranslatorNine.d().matcher(stringBuffer8.toString());
        StringBuffer stringBuffer9 = new StringBuffer();
        for (boolean find9 = matcher9.find(); find9; find9 = matcher9.find()) {
            String[] split7 = matcher9.group().split("万");
            matcher9.appendReplacement(stringBuffer9, Integer.toString(split7.length == 1 ? Integer.parseInt(split7[0]) * 10000 : split7.length == 2 ? Integer.parseInt(split7[1]) + (Integer.parseInt(split7[0]) * 10000) : 0));
        }
        matcher9.appendTail(stringBuffer9);
        return stringBuffer9.toString();
    }

    public static String d(String str) {
        return c(a(b(str.trim(), RegexEnum.TextPreprocessSeparator.e())));
    }

    private static int e(String str) {
        if (str.equals("零") || str.equals("0")) {
            return 0;
        }
        if (str.equals("一") || str.equals("1")) {
            return 1;
        }
        if (str.equals("二") || str.equals("两") || str.equals("2")) {
            return 2;
        }
        if (str.equals("三") || str.equals("3")) {
            return 3;
        }
        if (str.equals("四") || str.equals("4")) {
            return 4;
        }
        if (str.equals("五") || str.equals("5")) {
            return 5;
        }
        if (str.equals("六") || str.equals("6")) {
            return 6;
        }
        if (str.equals("七") || str.equals("天") || str.equals("日") || str.equals("末") || str.equals("7")) {
            return 7;
        }
        if (str.equals("八") || str.equals("8")) {
            return 8;
        }
        return (str.equals("九") || str.equals("9")) ? 9 : -1;
    }
}
