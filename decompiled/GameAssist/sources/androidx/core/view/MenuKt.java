package androidx.core.view;

import android.view.Menu;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class MenuKt {
    public static final Iterator a(Menu menu) {
        return new MenuKt$iterator$1(menu);
    }
}
