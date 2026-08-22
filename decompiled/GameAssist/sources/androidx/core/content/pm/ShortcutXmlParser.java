package androidx.core.content.pm;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo
/* loaded from: classes.dex */
public class ShortcutXmlParser {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f2859a = new Object();

    private static String a(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str);
        return attributeValue == null ? xmlPullParser.getAttributeValue(null, str) : attributeValue;
    }

    @NonNull
    @VisibleForTesting
    public static List<String> parseShortcutIds(@NonNull XmlPullParser xmlPullParser) {
        String a2;
        ArrayList arrayList = new ArrayList(1);
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1 || (next == 3 && xmlPullParser.getDepth() <= 0)) {
                break;
            }
            int depth = xmlPullParser.getDepth();
            String name = xmlPullParser.getName();
            if (next == 2 && depth == 2 && "shortcut".equals(name) && (a2 = a(xmlPullParser, "shortcutId")) != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }
}
