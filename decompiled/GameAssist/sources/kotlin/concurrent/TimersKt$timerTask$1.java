package kotlin.concurrent;

import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class TimersKt$timerTask$1 extends TimerTask {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Function1 f18405c;

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        this.f18405c.c(this);
    }
}
