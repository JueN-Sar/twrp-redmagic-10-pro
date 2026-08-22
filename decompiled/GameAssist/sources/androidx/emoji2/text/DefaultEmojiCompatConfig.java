package androidx.emoji2.text;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.provider.FontRequest;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.EmojiCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class DefaultEmojiCompatConfig {

    @RestrictTo
    public static class DefaultEmojiCompatConfigFactory {

        /* renamed from: a, reason: collision with root package name */
        private final DefaultEmojiCompatConfigHelper f3692a;

        public DefaultEmojiCompatConfigFactory(DefaultEmojiCompatConfigHelper defaultEmojiCompatConfigHelper) {
            this.f3692a = defaultEmojiCompatConfigHelper == null ? e() : defaultEmojiCompatConfigHelper;
        }

        private EmojiCompat.Config a(Context context, FontRequest fontRequest) {
            if (fontRequest == null) {
                return null;
            }
            return new FontRequestEmojiCompatConfig(context, fontRequest);
        }

        private List b(Signature[] signatureArr) {
            ArrayList arrayList = new ArrayList();
            for (Signature signature : signatureArr) {
                arrayList.add(signature.toByteArray());
            }
            return Collections.singletonList(arrayList);
        }

        private FontRequest d(ProviderInfo providerInfo, PackageManager packageManager) {
            String str = providerInfo.authority;
            String str2 = providerInfo.packageName;
            return new FontRequest(str, str2, "emojicompat-emoji-font", b(this.f3692a.b(packageManager, str2)));
        }

        private static DefaultEmojiCompatConfigHelper e() {
            return new DefaultEmojiCompatConfigHelper_API28();
        }

        private boolean f(ProviderInfo providerInfo) {
            ApplicationInfo applicationInfo;
            return (providerInfo == null || (applicationInfo = providerInfo.applicationInfo) == null || (applicationInfo.flags & 1) != 1) ? false : true;
        }

        private ProviderInfo g(PackageManager packageManager) {
            Iterator it = this.f3692a.c(packageManager, new Intent("androidx.content.action.LOAD_EMOJI_FONT"), 0).iterator();
            while (it.hasNext()) {
                ProviderInfo a2 = this.f3692a.a((ResolveInfo) it.next());
                if (f(a2)) {
                    return a2;
                }
            }
            return null;
        }

        public EmojiCompat.Config c(Context context) {
            return a(context, queryForDefaultFontRequest(context));
        }

        @Nullable
        @RestrictTo
        @VisibleForTesting
        FontRequest queryForDefaultFontRequest(@NonNull Context context) {
            PackageManager packageManager = context.getPackageManager();
            Preconditions.i(packageManager, "Package manager required to locate emoji font provider");
            ProviderInfo g2 = g(packageManager);
            if (g2 == null) {
                return null;
            }
            try {
                return d(g2, packageManager);
            } catch (PackageManager.NameNotFoundException e2) {
                Log.wtf("emoji2.text.DefaultEmojiConfig", e2);
                return null;
            }
        }
    }

    @RestrictTo
    public static class DefaultEmojiCompatConfigHelper {
        public ProviderInfo a(ResolveInfo resolveInfo) {
            throw new IllegalStateException("Unable to get provider info prior to API 19");
        }

        public Signature[] b(PackageManager packageManager, String str) {
            return packageManager.getPackageInfo(str, 64).signatures;
        }

        public List c(PackageManager packageManager, Intent intent, int i2) {
            return Collections.emptyList();
        }
    }

    @RequiresApi
    @RestrictTo
    public static class DefaultEmojiCompatConfigHelper_API19 extends DefaultEmojiCompatConfigHelper {
        @Override // androidx.emoji2.text.DefaultEmojiCompatConfig.DefaultEmojiCompatConfigHelper
        public ProviderInfo a(ResolveInfo resolveInfo) {
            return resolveInfo.providerInfo;
        }

        @Override // androidx.emoji2.text.DefaultEmojiCompatConfig.DefaultEmojiCompatConfigHelper
        public List c(PackageManager packageManager, Intent intent, int i2) {
            return packageManager.queryIntentContentProviders(intent, i2);
        }
    }

    @RequiresApi
    @RestrictTo
    public static class DefaultEmojiCompatConfigHelper_API28 extends DefaultEmojiCompatConfigHelper_API19 {
        @Override // androidx.emoji2.text.DefaultEmojiCompatConfig.DefaultEmojiCompatConfigHelper
        public Signature[] b(PackageManager packageManager, String str) {
            return packageManager.getPackageInfo(str, 64).signatures;
        }
    }

    public static FontRequestEmojiCompatConfig a(Context context) {
        return (FontRequestEmojiCompatConfig) new DefaultEmojiCompatConfigFactory(null).c(context);
    }
}
