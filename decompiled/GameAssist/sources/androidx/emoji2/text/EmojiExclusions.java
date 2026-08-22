package androidx.emoji2.text;

import android.os.Build;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
class EmojiExclusions {

    @RequiresApi
    private static class EmojiExclusions_Api34 {
        @NonNull
        @DoNotInline
        static Set<int[]> a() {
            return EmojiExclusions_Reflections.a();
        }
    }

    private static class EmojiExclusions_Reflections {
        static Set a() {
            try {
                Object invoke = Class.forName("android.text.EmojiConsistency").getMethod("getEmojiConsistencySet", null).invoke(null, null);
                if (invoke == null) {
                    return Collections.emptySet();
                }
                Set set = (Set) invoke;
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    if (!(it.next() instanceof int[])) {
                        return Collections.emptySet();
                    }
                }
                return set;
            } catch (Throwable unused) {
                return Collections.emptySet();
            }
        }
    }

    static Set a() {
        return Build.VERSION.SDK_INT >= 34 ? EmojiExclusions_Api34.a() : EmojiExclusions_Reflections.a();
    }
}
