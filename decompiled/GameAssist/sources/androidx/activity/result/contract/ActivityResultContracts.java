package androidx.activity.result.contract;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt___RangesKt;

@Metadata
/* loaded from: classes.dex */
public final class ActivityResultContracts {

    @Metadata
    public static class CaptureVideo extends ActivityResultContract<Uri, Boolean> {
        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, Uri input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            Intent putExtra = new Intent("android.media.action.VIDEO_CAPTURE").putExtra("output", input);
            Intrinsics.d(putExtra, "Intent(MediaStore.ACTION…tore.EXTRA_OUTPUT, input)");
            return putExtra;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public final ActivityResultContract.SynchronousResult b(Context context, Uri input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            return null;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public final Boolean c(int i2, Intent intent) {
            return Boolean.valueOf(i2 == -1);
        }
    }

    @Metadata
    @RequiresApi
    @SourceDebugExtension
    public static class CreateDocument extends ActivityResultContract<String, Uri> {

        /* renamed from: a, reason: collision with root package name */
        private final String f129a;

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, String input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            Intent putExtra = new Intent("android.intent.action.CREATE_DOCUMENT").setType(this.f129a).putExtra("android.intent.extra.TITLE", input);
            Intrinsics.d(putExtra, "Intent(Intent.ACTION_CRE…ntent.EXTRA_TITLE, input)");
            return putExtra;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public final ActivityResultContract.SynchronousResult b(Context context, String input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            return null;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public final Uri c(int i2, Intent intent) {
            if (i2 != -1) {
                intent = null;
            }
            if (intent != null) {
                return intent.getData();
            }
            return null;
        }
    }

    @Metadata
    @SourceDebugExtension
    public static class GetContent extends ActivityResultContract<String, Uri> {
        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, String input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            Intent type = new Intent("android.intent.action.GET_CONTENT").addCategory("android.intent.category.OPENABLE").setType(input);
            Intrinsics.d(type, "Intent(Intent.ACTION_GET…          .setType(input)");
            return type;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public final ActivityResultContract.SynchronousResult b(Context context, String input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            return null;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public final Uri c(int i2, Intent intent) {
            if (i2 != -1) {
                intent = null;
            }
            if (intent != null) {
                return intent.getData();
            }
            return null;
        }
    }

    @Metadata
    @RequiresApi
    public static class GetMultipleContents extends ActivityResultContract<String, List<Uri>> {

        /* renamed from: a, reason: collision with root package name */
        public static final Companion f130a = new Companion(null);

        @RequiresApi
        @Metadata
        public static final class Companion {
            private Companion() {
            }

            public final List a(Intent intent) {
                Intrinsics.e(intent, "<this>");
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                Uri data = intent.getData();
                if (data != null) {
                    linkedHashSet.add(data);
                }
                ClipData clipData = intent.getClipData();
                if (clipData == null && linkedHashSet.isEmpty()) {
                    return CollectionsKt__CollectionsKt.g();
                }
                if (clipData != null) {
                    int itemCount = clipData.getItemCount();
                    for (int i2 = 0; i2 < itemCount; i2++) {
                        Uri uri = clipData.getItemAt(i2).getUri();
                        if (uri != null) {
                            linkedHashSet.add(uri);
                        }
                    }
                }
                return new ArrayList(linkedHashSet);
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, String input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            Intent putExtra = new Intent("android.intent.action.GET_CONTENT").addCategory("android.intent.category.OPENABLE").setType(input).putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
            Intrinsics.d(putExtra, "Intent(Intent.ACTION_GET…TRA_ALLOW_MULTIPLE, true)");
            return putExtra;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public final ActivityResultContract.SynchronousResult b(Context context, String input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            return null;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public final List c(int i2, Intent intent) {
            List a2;
            if (i2 != -1) {
                intent = null;
            }
            return (intent == null || (a2 = f130a.a(intent)) == null) ? CollectionsKt__CollectionsKt.g() : a2;
        }
    }

    @Metadata
    @RequiresApi
    @SourceDebugExtension
    public static class OpenDocument extends ActivityResultContract<String[], Uri> {
        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, String[] input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            Intent type = new Intent("android.intent.action.OPEN_DOCUMENT").putExtra("android.intent.extra.MIME_TYPES", input).setType("*/*");
            Intrinsics.d(type, "Intent(Intent.ACTION_OPE…          .setType(\"*/*\")");
            return type;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public final ActivityResultContract.SynchronousResult b(Context context, String[] input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            return null;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public final Uri c(int i2, Intent intent) {
            if (i2 != -1) {
                intent = null;
            }
            if (intent != null) {
                return intent.getData();
            }
            return null;
        }
    }

    @Metadata
    @RequiresApi
    @SourceDebugExtension
    public static class OpenDocumentTree extends ActivityResultContract<Uri, Uri> {
        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, Uri uri) {
            Intrinsics.e(context, "context");
            Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
            if (uri != null) {
                intent.putExtra("android.provider.extra.INITIAL_URI", uri);
            }
            return intent;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public final ActivityResultContract.SynchronousResult b(Context context, Uri uri) {
            Intrinsics.e(context, "context");
            return null;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public final Uri c(int i2, Intent intent) {
            if (i2 != -1) {
                intent = null;
            }
            if (intent != null) {
                return intent.getData();
            }
            return null;
        }
    }

    @Metadata
    @RequiresApi
    public static class OpenMultipleDocuments extends ActivityResultContract<String[], List<Uri>> {
        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, String[] input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            Intent type = new Intent("android.intent.action.OPEN_DOCUMENT").putExtra("android.intent.extra.MIME_TYPES", input).putExtra("android.intent.extra.ALLOW_MULTIPLE", true).setType("*/*");
            Intrinsics.d(type, "Intent(Intent.ACTION_OPE…          .setType(\"*/*\")");
            return type;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public final ActivityResultContract.SynchronousResult b(Context context, String[] input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            return null;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public final List c(int i2, Intent intent) {
            List a2;
            if (i2 != -1) {
                intent = null;
            }
            return (intent == null || (a2 = GetMultipleContents.f130a.a(intent)) == null) ? CollectionsKt__CollectionsKt.g() : a2;
        }
    }

    @Metadata
    @SourceDebugExtension
    public static final class PickContact extends ActivityResultContract<Void, Uri> {
        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, Void r2) {
            Intrinsics.e(context, "context");
            Intent type = new Intent("android.intent.action.PICK").setType("vnd.android.cursor.dir/contact");
            Intrinsics.d(type, "Intent(Intent.ACTION_PIC…ct.Contacts.CONTENT_TYPE)");
            return type;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public Uri c(int i2, Intent intent) {
            if (i2 != -1) {
                intent = null;
            }
            if (intent != null) {
                return intent.getData();
            }
            return null;
        }
    }

    @Metadata
    @RequiresApi
    public static class PickMultipleVisualMedia extends ActivityResultContract<PickVisualMediaRequest, List<Uri>> {

        /* renamed from: b, reason: collision with root package name */
        public static final Companion f131b = new Companion(null);

        /* renamed from: a, reason: collision with root package name */
        private final int f132a;

        @Metadata
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, PickVisualMediaRequest input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            PickVisualMedia.Companion companion = PickVisualMedia.f133a;
            if (companion.f()) {
                Intent intent = new Intent("android.provider.action.PICK_IMAGES");
                intent.setType(companion.c(input.a()));
                if (this.f132a > MediaStore.getPickImagesMaxLimit()) {
                    throw new IllegalArgumentException("Max items must be less or equals MediaStore.getPickImagesMaxLimit()".toString());
                }
                intent.putExtra("android.provider.extra.PICK_IMAGES_MAX", this.f132a);
                return intent;
            }
            if (companion.e(context)) {
                ResolveInfo b2 = companion.b(context);
                if (b2 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                ActivityInfo activityInfo = b2.activityInfo;
                Intent intent2 = new Intent("androidx.activity.result.contract.action.PICK_IMAGES");
                intent2.setClassName(activityInfo.applicationInfo.packageName, activityInfo.name);
                intent2.setType(companion.c(input.a()));
                intent2.putExtra("com.google.android.gms.provider.extra.PICK_IMAGES_MAX", this.f132a);
                return intent2;
            }
            if (companion.d(context)) {
                ResolveInfo a2 = companion.a(context);
                if (a2 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                ActivityInfo activityInfo2 = a2.activityInfo;
                Intent intent3 = new Intent("com.google.android.gms.provider.action.PICK_IMAGES");
                intent3.setClassName(activityInfo2.applicationInfo.packageName, activityInfo2.name);
                intent3.putExtra("com.google.android.gms.provider.extra.PICK_IMAGES_MAX", this.f132a);
                return intent3;
            }
            Intent intent4 = new Intent("android.intent.action.OPEN_DOCUMENT");
            intent4.setType(companion.c(input.a()));
            intent4.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
            if (intent4.getType() != null) {
                return intent4;
            }
            intent4.setType("*/*");
            intent4.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/*", "video/*"});
            return intent4;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public final ActivityResultContract.SynchronousResult b(Context context, PickVisualMediaRequest input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            return null;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public final List c(int i2, Intent intent) {
            List a2;
            if (i2 != -1) {
                intent = null;
            }
            return (intent == null || (a2 = GetMultipleContents.f130a.a(intent)) == null) ? CollectionsKt__CollectionsKt.g() : a2;
        }
    }

    @Metadata
    @RequiresApi
    @SourceDebugExtension
    public static class PickVisualMedia extends ActivityResultContract<PickVisualMediaRequest, Uri> {

        /* renamed from: a, reason: collision with root package name */
        public static final Companion f133a = new Companion(null);

        @Metadata
        public static final class Companion {
            private Companion() {
            }

            public final ResolveInfo a(Context context) {
                Intrinsics.e(context, "context");
                return context.getPackageManager().resolveActivity(new Intent("com.google.android.gms.provider.action.PICK_IMAGES"), 1114112);
            }

            public final ResolveInfo b(Context context) {
                Intrinsics.e(context, "context");
                return context.getPackageManager().resolveActivity(new Intent("androidx.activity.result.contract.action.PICK_IMAGES"), 1114112);
            }

            public final String c(VisualMediaType input) {
                Intrinsics.e(input, "input");
                if (input instanceof ImageOnly) {
                    return "image/*";
                }
                if (input instanceof VideoOnly) {
                    return "video/*";
                }
                if (input instanceof SingleMimeType) {
                    return ((SingleMimeType) input).a();
                }
                if (input instanceof ImageAndVideo) {
                    return null;
                }
                throw new NoWhenBranchMatchedException();
            }

            public final boolean d(Context context) {
                Intrinsics.e(context, "context");
                return a(context) != null;
            }

            public final boolean e(Context context) {
                Intrinsics.e(context, "context");
                return b(context) != null;
            }

            public final boolean f() {
                return true;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        @Metadata
        public static final class ImageAndVideo implements VisualMediaType {

            /* renamed from: a, reason: collision with root package name */
            public static final ImageAndVideo f134a = new ImageAndVideo();

            private ImageAndVideo() {
            }
        }

        @Metadata
        public static final class ImageOnly implements VisualMediaType {

            /* renamed from: a, reason: collision with root package name */
            public static final ImageOnly f135a = new ImageOnly();

            private ImageOnly() {
            }
        }

        @Metadata
        public static final class SingleMimeType implements VisualMediaType {

            /* renamed from: a, reason: collision with root package name */
            private final String f136a;

            public final String a() {
                return this.f136a;
            }
        }

        @Metadata
        public static final class VideoOnly implements VisualMediaType {

            /* renamed from: a, reason: collision with root package name */
            public static final VideoOnly f137a = new VideoOnly();

            private VideoOnly() {
            }
        }

        @Metadata
        public interface VisualMediaType {
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, PickVisualMediaRequest input) {
            Intent intent;
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            Companion companion = f133a;
            if (companion.f()) {
                Intent intent2 = new Intent("android.provider.action.PICK_IMAGES");
                intent2.setType(companion.c(input.a()));
                return intent2;
            }
            if (companion.e(context)) {
                ResolveInfo b2 = companion.b(context);
                if (b2 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                ActivityInfo activityInfo = b2.activityInfo;
                intent = new Intent("androidx.activity.result.contract.action.PICK_IMAGES");
                intent.setClassName(activityInfo.applicationInfo.packageName, activityInfo.name);
                intent.setType(companion.c(input.a()));
            } else {
                if (!companion.d(context)) {
                    Intent intent3 = new Intent("android.intent.action.OPEN_DOCUMENT");
                    intent3.setType(companion.c(input.a()));
                    if (intent3.getType() != null) {
                        return intent3;
                    }
                    intent3.setType("*/*");
                    intent3.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/*", "video/*"});
                    return intent3;
                }
                ResolveInfo a2 = companion.a(context);
                if (a2 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                ActivityInfo activityInfo2 = a2.activityInfo;
                intent = new Intent("com.google.android.gms.provider.action.PICK_IMAGES");
                intent.setClassName(activityInfo2.applicationInfo.packageName, activityInfo2.name);
                intent.setType(companion.c(input.a()));
            }
            return intent;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public final ActivityResultContract.SynchronousResult b(Context context, PickVisualMediaRequest input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            return null;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public final Uri c(int i2, Intent intent) {
            Object z;
            if (i2 != -1) {
                intent = null;
            }
            if (intent == null) {
                return null;
            }
            Uri data = intent.getData();
            if (data == null) {
                z = CollectionsKt___CollectionsKt.z(GetMultipleContents.f130a.a(intent));
                data = (Uri) z;
            }
            return data;
        }
    }

    @Metadata
    @SourceDebugExtension
    public static final class RequestMultiplePermissions extends ActivityResultContract<String[], Map<String, Boolean>> {

        /* renamed from: a, reason: collision with root package name */
        public static final Companion f138a = new Companion(null);

        @Metadata
        public static final class Companion {
            private Companion() {
            }

            public final Intent a(String[] input) {
                Intrinsics.e(input, "input");
                Intent putExtra = new Intent("androidx.activity.result.contract.action.REQUEST_PERMISSIONS").putExtra("androidx.activity.result.contract.extra.PERMISSIONS", input);
                Intrinsics.d(putExtra, "Intent(ACTION_REQUEST_PE…EXTRA_PERMISSIONS, input)");
                return putExtra;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, String[] input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            return f138a.a(input);
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public ActivityResultContract.SynchronousResult b(Context context, String[] input) {
            int c2;
            int a2;
            Map f2;
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            if (input.length == 0) {
                f2 = MapsKt__MapsKt.f();
                return new ActivityResultContract.SynchronousResult(f2);
            }
            for (String str : input) {
                if (ContextCompat.a(context, str) != 0) {
                    return null;
                }
            }
            c2 = MapsKt__MapsJVMKt.c(input.length);
            a2 = RangesKt___RangesKt.a(c2, 16);
            LinkedHashMap linkedHashMap = new LinkedHashMap(a2);
            for (String str2 : input) {
                Pair a3 = TuplesKt.a(str2, Boolean.TRUE);
                linkedHashMap.put(a3.c(), a3.d());
            }
            return new ActivityResultContract.SynchronousResult(linkedHashMap);
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public Map c(int i2, Intent intent) {
            Map f2;
            List z;
            List O;
            Map i3;
            Map f3;
            Map f4;
            if (i2 != -1) {
                f4 = MapsKt__MapsKt.f();
                return f4;
            }
            if (intent == null) {
                f3 = MapsKt__MapsKt.f();
                return f3;
            }
            String[] stringArrayExtra = intent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
            int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
            if (intArrayExtra == null || stringArrayExtra == null) {
                f2 = MapsKt__MapsKt.f();
                return f2;
            }
            ArrayList arrayList = new ArrayList(intArrayExtra.length);
            for (int i4 : intArrayExtra) {
                arrayList.add(Boolean.valueOf(i4 == 0));
            }
            z = ArraysKt___ArraysKt.z(stringArrayExtra);
            O = CollectionsKt___CollectionsKt.O(z, arrayList);
            i3 = MapsKt__MapsKt.i(O);
            return i3;
        }
    }

    @Metadata
    @SourceDebugExtension
    public static final class RequestPermission extends ActivityResultContract<String, Boolean> {
        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, String input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            return RequestMultiplePermissions.f138a.a(new String[]{input});
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public ActivityResultContract.SynchronousResult b(Context context, String input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            if (ContextCompat.a(context, input) == 0) {
                return new ActivityResultContract.SynchronousResult(Boolean.TRUE);
            }
            return null;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public Boolean c(int i2, Intent intent) {
            if (intent == null || i2 != -1) {
                return Boolean.FALSE;
            }
            int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
            boolean z = false;
            if (intArrayExtra != null) {
                int length = intArrayExtra.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    if (intArrayExtra[i3] == 0) {
                        z = true;
                        break;
                    }
                    i3++;
                }
            }
            return Boolean.valueOf(z);
        }
    }

    @Metadata
    public static final class StartActivityForResult extends ActivityResultContract<Intent, ActivityResult> {

        /* renamed from: a, reason: collision with root package name */
        public static final Companion f139a = new Companion(null);

        @Metadata
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, Intent input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            return input;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public ActivityResult c(int i2, Intent intent) {
            return new ActivityResult(i2, intent);
        }
    }

    @Metadata
    public static final class StartIntentSenderForResult extends ActivityResultContract<IntentSenderRequest, ActivityResult> {

        /* renamed from: a, reason: collision with root package name */
        public static final Companion f140a = new Companion(null);

        @Metadata
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, IntentSenderRequest input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            Intent putExtra = new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", input);
            Intrinsics.d(putExtra, "Intent(ACTION_INTENT_SEN…NT_SENDER_REQUEST, input)");
            return putExtra;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public ActivityResult c(int i2, Intent intent) {
            return new ActivityResult(i2, intent);
        }
    }

    @Metadata
    public static class TakePicture extends ActivityResultContract<Uri, Boolean> {
        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, Uri input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            Intent putExtra = new Intent("android.media.action.IMAGE_CAPTURE").putExtra("output", input);
            Intrinsics.d(putExtra, "Intent(MediaStore.ACTION…tore.EXTRA_OUTPUT, input)");
            return putExtra;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public final ActivityResultContract.SynchronousResult b(Context context, Uri input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            return null;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public final Boolean c(int i2, Intent intent) {
            return Boolean.valueOf(i2 == -1);
        }
    }

    @Metadata
    @SourceDebugExtension
    public static class TakePicturePreview extends ActivityResultContract<Void, Bitmap> {
        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, Void r2) {
            Intrinsics.e(context, "context");
            return new Intent("android.media.action.IMAGE_CAPTURE");
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public final ActivityResultContract.SynchronousResult b(Context context, Void r2) {
            Intrinsics.e(context, "context");
            return null;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public final Bitmap c(int i2, Intent intent) {
            if (i2 != -1) {
                intent = null;
            }
            if (intent != null) {
                return (Bitmap) intent.getParcelableExtra("data");
            }
            return null;
        }
    }

    @Metadata
    @Deprecated
    @SourceDebugExtension
    public static class TakeVideo extends ActivityResultContract<Uri, Bitmap> {
        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, Uri input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            Intent putExtra = new Intent("android.media.action.VIDEO_CAPTURE").putExtra("output", input);
            Intrinsics.d(putExtra, "Intent(MediaStore.ACTION…tore.EXTRA_OUTPUT, input)");
            return putExtra;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public final ActivityResultContract.SynchronousResult b(Context context, Uri input) {
            Intrinsics.e(context, "context");
            Intrinsics.e(input, "input");
            return null;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public final Bitmap c(int i2, Intent intent) {
            if (i2 != -1) {
                intent = null;
            }
            if (intent != null) {
                return (Bitmap) intent.getParcelableExtra("data");
            }
            return null;
        }
    }
}
