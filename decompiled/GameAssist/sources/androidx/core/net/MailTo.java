package androidx.core.net;

import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class MailTo {

    /* renamed from: a, reason: collision with root package name */
    private HashMap f3098a;

    public String toString() {
        StringBuilder sb = new StringBuilder("mailto:");
        sb.append('?');
        for (Map.Entry entry : this.f3098a.entrySet()) {
            sb.append(Uri.encode((String) entry.getKey()));
            sb.append('=');
            sb.append(Uri.encode((String) entry.getValue()));
            sb.append('&');
        }
        return sb.toString();
    }
}
