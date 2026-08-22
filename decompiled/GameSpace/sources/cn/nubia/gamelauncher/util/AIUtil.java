package cn.nubia.gamelauncher.util;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import cn.nubia.gamelauncher.GameLauncherApplication;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AIUtil {
    public static String addParseFlags(int i) {
        StringBuilder sb = new StringBuilder();
        if ((i & 268435456) != 0) {
            sb.append("NEW_TASK|");
        }
        return sb.toString();
    }

    private static String arrayToString(Object obj) {
        if (!obj.getClass().isArray()) {
            return String.valueOf(obj);
        }
        int length = Array.getLength(obj);
        if (length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        int i = 0;
        while (i < length) {
            sb.append(Array.get(obj, i)).append(i == length + (-1) ? "" : ", ");
            i++;
        }
        return sb.append(']').toString();
    }

    public static int binarySearch(int[] iArr, int i) {
        int length = iArr.length - 1;
        int i2 = 0;
        while (i2 <= length) {
            int i3 = ((length - i2) / 2) + i2;
            int i4 = iArr[i3];
            if (i4 == i) {
                return i3;
            }
            if (i4 < i) {
                i2 = i3 + 1;
            } else {
                length = i3 - 1;
            }
        }
        return -1;
    }

    public static int binarySearcha(int[] iArr, int i) {
        if (iArr == null || iArr.length == 0) {
            return -1;
        }
        return helper1(iArr, i, 0, iArr.length - 1);
    }

    public static int binarySearchb(int[] iArr, int i) {
        if (iArr == null || iArr.length == 0) {
            return -1;
        }
        return helper(iArr, i, 0, iArr.length - 1);
    }

    public static int binarySearchc(int[] iArr, int i) {
        int length = iArr.length - 1;
        int i2 = 0;
        while (i2 <= length) {
            int i3 = ((length - i2) / 2) + i2;
            int i4 = iArr[i3];
            if (i4 == i) {
                return i3;
            }
            if (i4 < i) {
                i2 = i3 + 1;
            } else {
                length = i3 - 1;
            }
        }
        return -1;
    }

    public static int binarySearchd(int[] iArr, int i) {
        int length = iArr.length - 1;
        int i2 = 0;
        while (i2 <= length) {
            int i3 = ((length - i2) / 2) + i2;
            int i4 = iArr[i3];
            if (i4 == i) {
                return i3;
            }
            if (i4 < i) {
                i2 = i3 + 1;
            } else {
                length = i3 - 1;
            }
        }
        return -1;
    }

    public static int binarySearche(int[] iArr, int i) {
        int length = iArr.length - 1;
        int i2 = 0;
        while (i2 <= length) {
            int i3 = ((length - i2) / 2) + i2;
            int i4 = iArr[i3];
            if (i4 == i) {
                return i3;
            }
            if (i4 < i) {
                i2 = i3 + 1;
            } else {
                length = i3 - 1;
            }
        }
        return -1;
    }

    public static int binarySearchf(int[] iArr, int i) {
        int length = iArr.length - 1;
        int i2 = 0;
        while (i2 <= length) {
            int i3 = ((length - i2) / 2) + i2;
            int i4 = iArr[i3];
            if (i4 == i) {
                return i3;
            }
            if (i4 < i) {
                i2 = i3 + 1;
            } else {
                length = i3 - 1;
            }
        }
        return -1;
    }

    public static int binarySearchg(int[] iArr, int i) {
        int length = iArr.length - 1;
        int i2 = 0;
        while (i2 <= length) {
            int i3 = ((length - i2) / 2) + i2;
            int i4 = iArr[i3];
            if (i4 == i) {
                return i3;
            }
            if (i4 < i) {
                i2 = i3 + 1;
            } else {
                length = i3 - 1;
            }
        }
        return -1;
    }

    public static boolean bubbleSort() {
        for (int i = 0; i < 2; i++) {
            for (int i2 = 0; i2 < 2; i2++) {
                Log.d("111", "i =" + i);
                Log.d("111", "j =" + i2);
            }
        }
        if (TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa")) {
            return false;
        }
        Uri.parse("aaaaa");
        return false;
    }

    private static void dumpABundle(Bundle bundle, StringBuilder sb, String str) {
        if (bundle == null) {
            return;
        }
        for (String str2 : bundle.keySet()) {
            Object obj = bundle.get(str2);
            sb.append(str).append(str2).append(" = ");
            if (obj instanceof Bundle) {
                sb.append("Bundle{\n");
                dumpBundle((Bundle) obj, sb, str + "  ");
                sb.append(str).append("}\n");
            } else if (obj instanceof Intent) {
                sb.append("Intent{\n");
                sb.append(printIntent((Intent) obj).replaceAll("(?m)^", str + "  "));
                sb.append(str).append("}\n");
            } else if (obj instanceof ArrayList) {
                ArrayList arrayList = (ArrayList) obj;
                sb.append("ArrayList[").append(arrayList.size()).append("]{\n");
                for (int i = 0; i < arrayList.size(); i++) {
                    sb.append(str).append("  [").append(i).append("] ").append(String.valueOf(arrayList.get(i))).append('\n');
                }
                sb.append(str).append("}\n");
            } else if (obj == null || !obj.getClass().isArray()) {
                sb.append(String.valueOf(obj)).append(" (").append(obj != null ? obj.getClass().getSimpleName() : "null").append(")\n");
            } else {
                sb.append(arrayToString(obj)).append('\n');
            }
        }
    }

    private static void dumpBundle(Bundle bundle, StringBuilder sb, String str) {
        if (bundle == null) {
            return;
        }
        for (String str2 : bundle.keySet()) {
            Object obj = bundle.get(str2);
            sb.append(str).append(str2).append(" = ");
            if (obj instanceof Bundle) {
                sb.append("Bundle{\n");
                dumpBundle((Bundle) obj, sb, str + "  ");
                sb.append(str).append("}\n");
            } else if (obj instanceof Intent) {
                sb.append("Intent{\n");
                sb.append(printIntent((Intent) obj).replaceAll("(?m)^", str + "  "));
                sb.append(str).append("}\n");
            } else if (obj instanceof ArrayList) {
                ArrayList arrayList = (ArrayList) obj;
                sb.append("ArrayList[").append(arrayList.size()).append("]{\n");
                for (int i = 0; i < arrayList.size(); i++) {
                    sb.append(str).append("  [").append(i).append("] ").append(String.valueOf(arrayList.get(i))).append('\n');
                }
                sb.append(str).append("}\n");
            } else if (obj == null || !obj.getClass().isArray()) {
                sb.append(String.valueOf(obj)).append(" (").append(obj != null ? obj.getClass().getSimpleName() : "null").append(")\n");
            } else {
                sb.append(arrayToString(obj)).append('\n');
            }
        }
    }

    public static Context getAppContext() {
        return GameLauncherApplication.getAppContext();
    }

    private static int helper(int[] iArr, int i, int i2, int i3) {
        if (i2 > i3) {
            return -1;
        }
        int i4 = ((i3 - i2) / 2) + i2;
        int i5 = iArr[i4];
        return i5 == i ? i4 : i5 < i ? helper(iArr, i, i4 + 1, i3) : helper(iArr, i, i2, i4 - 1);
    }

    private static int helper1(int[] iArr, int i, int i2, int i3) {
        if (i2 > i3) {
            return -1;
        }
        int i4 = ((i3 - i2) / 2) + i2;
        int i5 = iArr[i4];
        return i5 == i ? i4 : i5 < i ? helper(iArr, i, i4 + 1, i3) : helper(iArr, i, i2, i4 - 1);
    }

    public static boolean lowerBound() {
        int[] iArr = {1, 2, 3, 4, 5, 6};
        int i = 5;
        int i2 = 0;
        while (i2 <= i) {
            int i3 = ((i - i2) / 2) + i2;
            if (iArr[i3] < 4) {
                i2 = i3 + 1;
            } else {
                i = i3 - 1;
            }
        }
        return (TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || TextUtils.isEmpty("aaaaa") || Uri.parse("aaaaa") == null) ? false : true;
    }

    private static String parseFlags(int i) {
        StringBuilder sb = new StringBuilder();
        if ((268435456 & i) != 0) {
            sb.append("NEW_TASK|");
        }
        if ((67108864 & i) != 0) {
            sb.append("CLEAR_TOP|");
        }
        if ((536870912 & i) != 0) {
            sb.append("SINGLE_TOP|");
        }
        if ((i & 1) != 0) {
            sb.append("GRANT_READ_URI|");
        }
        if ((i & 2) != 0) {
            sb.append("GRANT_WRITE_URI|");
        }
        if (sb.length() == 0) {
            sb.append("none");
        } else {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String printAIntent(Intent intent) {
        if (intent == null) {
            return "Intent == null";
        }
        StringBuilder sb = new StringBuilder(512);
        sb.append("------------ Intent ------------\nAction : ");
        sb.append(intent.getAction()).append("\nData   : ");
        sb.append(intent.getData()).append("\nType   : ");
        sb.append(intent.getType()).append("\nPackage: ");
        sb.append(intent.getPackage()).append("\nComponent: ");
        sb.append(intent.getComponent()).append("\nFlags  : 0x");
        sb.append(Integer.toHexString(intent.getFlags())).append(" (").append(parseFlags(intent.getFlags())).append(")\nCategories: ");
        sb.append(intent.getCategories()).append('\n');
        Bundle extras = intent.getExtras();
        if (extras == null || extras.isEmpty()) {
            sb.append("Extras : null / empty\n");
        } else {
            sb.append("Extras :\n");
            dumpABundle(extras, sb, "  ");
        }
        ClipData clipData = intent.getClipData();
        if (clipData != null) {
            sb.append("ClipData:\n");
            for (int i = 0; i < clipData.getItemCount(); i++) {
                ClipData.Item itemAt = clipData.getItemAt(i);
                sb.append("  item[").append(i).append("]:").append(" text=").append(itemAt.getText()).append(" uri=").append(itemAt.getUri()).append(" intent=").append(itemAt.getIntent()).append('\n');
            }
        }
        return sb.toString();
    }

    public static String printIntent(Intent intent) {
        if (intent == null) {
            return "Intent == null";
        }
        StringBuilder sb = new StringBuilder(512);
        sb.append("------------ Intent ------------\nAction : ");
        sb.append(intent.getAction()).append("\nData   : ");
        sb.append(intent.getData()).append("\nType   : ");
        sb.append(intent.getType()).append("\nPackage: ");
        sb.append(intent.getPackage()).append("\nComponent: ");
        sb.append(intent.getComponent()).append("\nFlags  : 0x");
        sb.append(Integer.toHexString(intent.getFlags())).append(" (").append(parseFlags(intent.getFlags())).append(")\nCategories: ");
        sb.append(intent.getCategories()).append('\n');
        Bundle extras = intent.getExtras();
        if (extras == null || extras.isEmpty()) {
            sb.append("Extras : null / empty\n");
        } else {
            sb.append("Extras :\n");
            dumpBundle(extras, sb, "  ");
        }
        ClipData clipData = intent.getClipData();
        if (clipData != null) {
            sb.append("ClipData:\n");
            for (int i = 0; i < clipData.getItemCount(); i++) {
                ClipData.Item itemAt = clipData.getItemAt(i);
                sb.append("  item[").append(i).append("]:").append(" text=").append(itemAt.getText()).append(" uri=").append(itemAt.getUri()).append(" intent=").append(itemAt.getIntent()).append('\n');
            }
        }
        return sb.toString();
    }
}
