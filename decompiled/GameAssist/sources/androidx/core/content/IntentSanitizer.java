package androidx.core.content;

import android.content.ClipData;
import android.content.Intent;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.core.util.Consumer;

/* loaded from: classes.dex */
public class IntentSanitizer {

    @RequiresApi
    private static class Api29Impl {
        @DoNotInline
        static String a(Intent intent) {
            return intent.getIdentifier();
        }

        @DoNotInline
        static Intent b(Intent intent, String str) {
            return intent.setIdentifier(str);
        }
    }

    @RequiresApi
    private static class Api31Impl {
        @DoNotInline
        static void a(int i2, ClipData.Item item, Consumer<String> consumer) {
            if (item.getHtmlText() == null && item.getIntent() == null && item.getTextLinks() == null) {
                return;
            }
            consumer.accept("ClipData item at position " + i2 + " contains htmlText, textLinks or intent: " + item);
        }
    }

    public static final class Builder {
    }
}
