package androidx.appcompat.widget;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;

/* loaded from: classes.dex */
final class AppCompatTextClassifierHelper {

    /* renamed from: a, reason: collision with root package name */
    private TextView f817a;

    @RequiresApi
    private static final class Api26Impl {
        @NonNull
        @DoNotInline
        static TextClassifier a(@NonNull TextView textView) {
            TextClassificationManager textClassificationManager = (TextClassificationManager) textView.getContext().getSystemService(TextClassificationManager.class);
            return textClassificationManager != null ? textClassificationManager.getTextClassifier() : TextClassifier.NO_OP;
        }
    }

    AppCompatTextClassifierHelper(TextView textView) {
        this.f817a = (TextView) Preconditions.h(textView);
    }
}
