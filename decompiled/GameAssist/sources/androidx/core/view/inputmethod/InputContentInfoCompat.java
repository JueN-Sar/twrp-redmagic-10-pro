package androidx.core.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.view.inputmethod.InputContentInfo;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class InputContentInfoCompat {

    /* renamed from: a, reason: collision with root package name */
    private final InputContentInfoCompatImpl f3521a;

    private static final class InputContentInfoCompatBaseImpl implements InputContentInfoCompatImpl {
    }

    private interface InputContentInfoCompatImpl {
    }

    public InputContentInfoCompat(Uri uri, ClipDescription clipDescription, Uri uri2) {
        this.f3521a = new InputContentInfoCompatApi25Impl(uri, clipDescription, uri2);
    }

    public static InputContentInfoCompat a(Object obj) {
        if (obj == null) {
            return null;
        }
        return new InputContentInfoCompat(new InputContentInfoCompatApi25Impl(obj));
    }

    @RequiresApi
    private static final class InputContentInfoCompatApi25Impl implements InputContentInfoCompatImpl {

        /* renamed from: a, reason: collision with root package name */
        final InputContentInfo f3522a;

        InputContentInfoCompatApi25Impl(Object obj) {
            this.f3522a = (InputContentInfo) obj;
        }

        InputContentInfoCompatApi25Impl(Uri uri, ClipDescription clipDescription, Uri uri2) {
            this.f3522a = new InputContentInfo(uri, clipDescription, uri2);
        }
    }

    private InputContentInfoCompat(InputContentInfoCompatImpl inputContentInfoCompatImpl) {
        this.f3521a = inputContentInfoCompatImpl;
    }
}
