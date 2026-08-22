package com.zte.gameassist.common;

import android.os.Bundle;
import android.os.Handler;
import com.zte.gameassist.AbsGameAssistToken;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class CommanderList implements AbsGameAssistToken.ICommander {

    /* renamed from: c, reason: collision with root package name */
    private final Handler f16459c;

    /* renamed from: h, reason: collision with root package name */
    private final List f16460h = new ArrayList();

    public CommanderList(Handler handler) {
        this.f16459c = handler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(IGameAssistCommander iGameAssistCommander) {
        if (this.f16460h.contains(iGameAssistCommander)) {
            return;
        }
        this.f16460h.add(iGameAssistCommander);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(final String str, final Bundle bundle, final AbsGameAssistToken.Callback callback) {
        this.f16460h.forEach(new Consumer() { // from class: com.zte.gameassist.common.f
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((IGameAssistCommander) obj).executive(str, bundle, callback);
            }
        });
    }

    public void d(final IGameAssistCommander iGameAssistCommander) {
        this.f16459c.post(new Runnable() { // from class: com.zte.gameassist.common.e
            @Override // java.lang.Runnable
            public final void run() {
                CommanderList.this.e(iGameAssistCommander);
            }
        });
    }

    @Override // com.zte.gameassist.AbsGameAssistToken.ICommander
    public void executive(final String str, final Bundle bundle, final AbsGameAssistToken.Callback callback) {
        if (str == null || str.length() <= 0) {
            return;
        }
        this.f16459c.post(new Runnable() { // from class: com.zte.gameassist.common.d
            @Override // java.lang.Runnable
            public final void run() {
                CommanderList.this.g(str, bundle, callback);
            }
        });
    }
}
