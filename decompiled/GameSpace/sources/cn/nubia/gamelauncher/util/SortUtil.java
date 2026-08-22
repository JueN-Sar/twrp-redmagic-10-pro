package cn.nubia.gamelauncher.util;

import android.icu.text.Collator;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class SortUtil {

    public static class CollatorComparator implements Comparator {
        Collator collator = Collator.getInstance(Locale.CHINA);

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if (obj == null || obj2 == null) {
                return 0;
            }
            return this.collator.getCollationKey(((AppListItemBean) obj).getName()).compareTo(this.collator.getCollationKey(((AppListItemBean) obj2).getName()));
        }
    }

    public static class SortByStartTime implements Comparator {
        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return Long.valueOf(((AppListItemBean) obj2).getLastStartTime()).compareTo(Long.valueOf(((AppListItemBean) obj).getLastStartTime()));
        }
    }

    public static ArrayList<AppListItemBean> sortByPinYinFirstChar(ArrayList<AppListItemBean> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            Collections.sort(arrayList, new CollatorComparator());
        }
        return arrayList;
    }

    public static ArrayList<AppListItemBean> sortByStartTime(ArrayList<AppListItemBean> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            Collections.sort(arrayList, new SortByStartTime());
        }
        return arrayList;
    }

    public static CopyOnWriteArrayList<AppListItemBean> sortByStartTime(CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList) {
        if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() > 0) {
            Collections.sort(copyOnWriteArrayList, new SortByStartTime());
        }
        return copyOnWriteArrayList;
    }
}
