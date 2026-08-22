package cn.nubia.chatassistant.util;

import kotlin.jvm.internal.CharCompanionObject;

/* loaded from: classes.dex */
public class EmojiFilterUtils {
    public static Boolean containsEmoji(String str) {
        int charAt;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt2 = str.charAt(i);
            if (55296 > charAt2 || charAt2 > 56319) {
                if (8448 <= charAt2 && charAt2 <= 10239 && charAt2 != 9787) {
                    return true;
                }
                if (11013 <= charAt2 && charAt2 <= 11015) {
                    return true;
                }
                if (10548 <= charAt2 && charAt2 <= 10549) {
                    return true;
                }
                if (12951 <= charAt2 && charAt2 <= 12953) {
                    return true;
                }
                if (charAt2 == 169 || charAt2 == 174 || charAt2 == 12349 || charAt2 == 12336 || charAt2 == 11093 || charAt2 == 11036 || charAt2 == 11035 || charAt2 == 11088 || charAt2 == 8986) {
                    return true;
                }
                if (str.length() > 1 && i < str.length() - 1 && str.charAt(i + 1) == 8419) {
                    return true;
                }
            } else if (str.length() > 1 && 118784 <= (charAt = ((charAt2 - 55296) * 1024) + (str.charAt(i + 1) - CharCompanionObject.MIN_LOW_SURROGATE) + 65536) && charAt <= 128895) {
                return true;
            }
        }
        return false;
    }

    public static String filterEmoji(String str) {
        int length = str.length();
        StringBuilder sb = null;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (isEmojiCharacter(charAt).booleanValue()) {
                if (sb == null) {
                    sb = new StringBuilder(str.length());
                }
                sb.append(charAt);
            }
        }
        return (sb == null || sb.length() == length) ? str : sb.toString();
    }

    public static Boolean isEmojiCharacter(char c) {
        return Boolean.valueOf(c == 0 || c == '\t' || c == '\n' || c == '\r' || (c >= ' ' && c <= 55295) || ((c >= 57344 && c <= 65533) || (c >= 0 && c <= 65535)));
    }
}
