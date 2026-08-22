package androidx.core.view.inputmethod;

import android.annotation.SuppressLint;
import android.content.ClipDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

@SuppressLint({"PrivateConstructorForUtilityClass"})
/* loaded from: classes.dex */
public final class InputConnectionCompat {

    /* renamed from: androidx.core.view.inputmethod.InputConnectionCompat$1, reason: invalid class name */
    class AnonymousClass1 extends InputConnectionWrapper {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ OnCommitContentListener f3519a;

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean commitContent(InputContentInfo inputContentInfo, int i2, Bundle bundle) {
            if (this.f3519a.a(InputContentInfoCompat.a(inputContentInfo), i2, bundle)) {
                return true;
            }
            return super.commitContent(inputContentInfo, i2, bundle);
        }
    }

    /* renamed from: androidx.core.view.inputmethod.InputConnectionCompat$2, reason: invalid class name */
    class AnonymousClass2 extends InputConnectionWrapper {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ OnCommitContentListener f3520a;

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean performPrivateCommand(String str, Bundle bundle) {
            if (InputConnectionCompat.a(str, bundle, this.f3520a)) {
                return true;
            }
            return super.performPrivateCommand(str, bundle);
        }
    }

    @RequiresApi
    static class Api25Impl {
        @DoNotInline
        static boolean a(InputConnection inputConnection, InputContentInfo inputContentInfo, int i2, Bundle bundle) {
            return inputConnection.commitContent(inputContentInfo, i2, bundle);
        }
    }

    public interface OnCommitContentListener {
        boolean a(InputContentInfoCompat inputContentInfoCompat, int i2, Bundle bundle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    static boolean a(String str, Bundle bundle, OnCommitContentListener onCommitContentListener) {
        boolean z;
        ResultReceiver resultReceiver;
        ?? r0 = 0;
        r0 = 0;
        if (bundle == null) {
            return false;
        }
        if (TextUtils.equals("androidx.core.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", str)) {
            z = false;
        } else {
            if (!TextUtils.equals("android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", str)) {
                return false;
            }
            z = true;
        }
        try {
            ResultReceiver resultReceiver2 = (ResultReceiver) bundle.getParcelable(z ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER");
            try {
                Uri uri = (Uri) bundle.getParcelable(z ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_URI");
                ClipDescription clipDescription = (ClipDescription) bundle.getParcelable(z ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION");
                Uri uri2 = (Uri) bundle.getParcelable(z ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI");
                int i2 = bundle.getInt(z ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS");
                Bundle bundle2 = (Bundle) bundle.getParcelable(z ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_OPTS");
                if (uri != null && clipDescription != null) {
                    r0 = onCommitContentListener.a(new InputContentInfoCompat(uri, clipDescription, uri2), i2, bundle2);
                }
                if (resultReceiver2 != 0) {
                    resultReceiver2.send(r0, null);
                }
                return r0;
            } catch (Throwable th) {
                th = th;
                resultReceiver = resultReceiver2;
                if (resultReceiver != null) {
                    resultReceiver.send(0, null);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            resultReceiver = null;
        }
    }
}
