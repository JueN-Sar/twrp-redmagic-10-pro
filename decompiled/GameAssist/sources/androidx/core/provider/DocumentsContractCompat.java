package androidx.core.provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.DocumentsContract;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class DocumentsContractCompat {

    public static final class DocumentCompat {
    }

    @RequiresApi
    private static class DocumentsContractApi21Impl {
        @DoNotInline
        static Uri a(String str, String str2) {
            return DocumentsContract.buildChildDocumentsUri(str, str2);
        }

        @DoNotInline
        static Uri b(Uri uri, String str) {
            return DocumentsContract.buildChildDocumentsUriUsingTree(uri, str);
        }

        @DoNotInline
        static Uri c(Uri uri, String str) {
            return DocumentsContract.buildDocumentUriUsingTree(uri, str);
        }

        @DoNotInline
        public static Uri d(String str, String str2) {
            return DocumentsContract.buildTreeDocumentUri(str, str2);
        }

        @DoNotInline
        static Uri e(ContentResolver contentResolver, Uri uri, String str, String str2) {
            return DocumentsContract.createDocument(contentResolver, uri, str, str2);
        }

        @DoNotInline
        static String f(Uri uri) {
            return DocumentsContract.getTreeDocumentId(uri);
        }

        @DoNotInline
        static Uri g(@NonNull ContentResolver contentResolver, @NonNull Uri uri, @NonNull String str) {
            return DocumentsContract.renameDocument(contentResolver, uri, str);
        }
    }

    @RequiresApi
    private static class DocumentsContractApi24Impl {
        @DoNotInline
        static boolean a(@NonNull Uri uri) {
            return DocumentsContract.isTreeUri(uri);
        }

        @DoNotInline
        static boolean b(ContentResolver contentResolver, Uri uri, Uri uri2) {
            return DocumentsContract.removeDocument(contentResolver, uri, uri2);
        }
    }
}
