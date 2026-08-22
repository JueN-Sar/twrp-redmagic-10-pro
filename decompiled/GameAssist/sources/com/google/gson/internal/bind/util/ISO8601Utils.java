package com.google.gson.internal.bind.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes.dex */
public class ISO8601Utils {
    private static final String UTC_ID = "UTC";
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);

    private static boolean checkOffset(String str, int i2, char c2) {
        return i2 < str.length() && str.charAt(i2) == c2;
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    private static int indexOfNonDigit(String str, int i2) {
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            if (charAt < '0' || charAt > '9') {
                return i2;
            }
            i2++;
        }
        return str.length();
    }

    private static void padInt(StringBuilder sb, int i2, int i3) {
        String num = Integer.toString(i2);
        for (int length = i3 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }

    public static Date parse(String str, ParsePosition parsePosition) {
        String str2;
        int i2;
        int i3;
        int i4;
        int i5;
        int length;
        TimeZone timeZone;
        char charAt;
        try {
            int index = parsePosition.getIndex();
            int i6 = index + 4;
            int parseInt = parseInt(str, index, i6);
            if (checkOffset(str, i6, '-')) {
                i6 = index + 5;
            }
            int i7 = i6 + 2;
            int parseInt2 = parseInt(str, i6, i7);
            if (checkOffset(str, i7, '-')) {
                i7 = i6 + 3;
            }
            int i8 = i7 + 2;
            int parseInt3 = parseInt(str, i7, i8);
            boolean checkOffset = checkOffset(str, i8, 'T');
            if (!checkOffset && str.length() <= i8) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(parseInt, parseInt2 - 1, parseInt3);
                parsePosition.setIndex(i8);
                return gregorianCalendar.getTime();
            }
            if (checkOffset) {
                int i9 = i7 + 5;
                int parseInt4 = parseInt(str, i7 + 3, i9);
                if (checkOffset(str, i9, ':')) {
                    i9 = i7 + 6;
                }
                int i10 = i9 + 2;
                int parseInt5 = parseInt(str, i9, i10);
                if (checkOffset(str, i10, ':')) {
                    i10 = i9 + 3;
                }
                if (str.length() <= i10 || (charAt = str.charAt(i10)) == 'Z' || charAt == '+' || charAt == '-') {
                    i3 = parseInt5;
                    i4 = 0;
                    i5 = 0;
                    i8 = i10;
                    i2 = parseInt4;
                } else {
                    int i11 = i10 + 2;
                    i5 = parseInt(str, i10, i11);
                    if (i5 > 59 && i5 < 63) {
                        i5 = 59;
                    }
                    if (checkOffset(str, i11, '.')) {
                        int i12 = i10 + 3;
                        int indexOfNonDigit = indexOfNonDigit(str, i10 + 4);
                        int min = Math.min(indexOfNonDigit, i10 + 6);
                        int parseInt6 = parseInt(str, i12, min);
                        int i13 = min - i12;
                        if (i13 == 1) {
                            parseInt6 *= 100;
                        } else if (i13 == 2) {
                            parseInt6 *= 10;
                        }
                        i2 = parseInt4;
                        i8 = indexOfNonDigit;
                        i3 = parseInt5;
                        i4 = parseInt6;
                    } else {
                        i2 = parseInt4;
                        i8 = i11;
                        i3 = parseInt5;
                        i4 = 0;
                    }
                }
            } else {
                i2 = 0;
                i3 = 0;
                i4 = 0;
                i5 = 0;
            }
            if (str.length() <= i8) {
                throw new IllegalArgumentException("No time zone indicator");
            }
            char charAt2 = str.charAt(i8);
            if (charAt2 == 'Z') {
                timeZone = TIMEZONE_UTC;
                length = i8 + 1;
            } else {
                if (charAt2 != '+' && charAt2 != '-') {
                    throw new IndexOutOfBoundsException("Invalid time zone indicator '" + charAt2 + "'");
                }
                String substring = str.substring(i8);
                if (substring.length() < 5) {
                    substring = substring + "00";
                }
                length = i8 + substring.length();
                if (!"+0000".equals(substring) && !"+00:00".equals(substring)) {
                    String str3 = "GMT" + substring;
                    TimeZone timeZone2 = TimeZone.getTimeZone(str3);
                    String id = timeZone2.getID();
                    if (!id.equals(str3) && !id.replace(":", "").equals(str3)) {
                        throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str3 + " given, resolves to " + timeZone2.getID());
                    }
                    timeZone = timeZone2;
                }
                timeZone = TIMEZONE_UTC;
            }
            GregorianCalendar gregorianCalendar2 = new GregorianCalendar(timeZone);
            gregorianCalendar2.setLenient(false);
            gregorianCalendar2.set(1, parseInt);
            gregorianCalendar2.set(2, parseInt2 - 1);
            gregorianCalendar2.set(5, parseInt3);
            gregorianCalendar2.set(11, i2);
            gregorianCalendar2.set(12, i3);
            gregorianCalendar2.set(13, i5);
            gregorianCalendar2.set(14, i4);
            parsePosition.setIndex(length);
            return gregorianCalendar2.getTime();
        } catch (IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException e2) {
            if (str == null) {
                str2 = null;
            } else {
                str2 = '\"' + str + '\"';
            }
            String message = e2.getMessage();
            if (message == null || message.isEmpty()) {
                message = "(" + e2.getClass().getName() + ")";
            }
            ParseException parseException = new ParseException("Failed to parse date [" + str2 + "]: " + message, parsePosition.getIndex());
            parseException.initCause(e2);
            throw parseException;
        }
    }

    private static int parseInt(String str, int i2, int i3) {
        int i4;
        int i5;
        if (i2 < 0 || i3 > str.length() || i2 > i3) {
            throw new NumberFormatException(str);
        }
        if (i2 < i3) {
            i5 = i2 + 1;
            int digit = Character.digit(str.charAt(i2), 10);
            if (digit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i2, i3));
            }
            i4 = -digit;
        } else {
            i4 = 0;
            i5 = i2;
        }
        while (i5 < i3) {
            int i6 = i5 + 1;
            int digit2 = Character.digit(str.charAt(i5), 10);
            if (digit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i2, i3));
            }
            i4 = (i4 * 10) - digit2;
            i5 = i6;
        }
        return -i4;
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i2 = offset / 60000;
            int abs = Math.abs(i2 / 60);
            int abs2 = Math.abs(i2 % 60);
            sb.append(offset >= 0 ? '+' : '-');
            padInt(sb, abs, 2);
            sb.append(':');
            padInt(sb, abs2, 2);
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }
}
