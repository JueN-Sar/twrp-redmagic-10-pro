package cn.nubia.gameassist.utils;

import cn.nubia.gameassist.utils.GameAssistMutex;
import com.zte.gameassist.ext.common.MutableData;
import com.zte.gameassist.ext.utils.RemoteList;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class GameAssistMutex {

    /* renamed from: d, reason: collision with root package name */
    private static volatile GameAssistMutex f7653d;

    /* renamed from: b, reason: collision with root package name */
    private final RemoteList f7655b;

    /* renamed from: a, reason: collision with root package name */
    private List f7654a = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    private final List f7656c = new ArrayList();

    public interface MutexCallback {
        void a(List list);
    }

    private GameAssistMutex() {
        RemoteList remoteList = new RemoteList("GameAssistMutex");
        this.f7655b = remoteList;
        remoteList.monitorRemoteList();
        remoteList.mValues.e(true, new MutableData.Observer() { // from class: cn.nubia.gameassist.utils.a
            @Override // com.zte.gameassist.ext.common.MutableData.Observer
            public final void a(Object obj) {
                GameAssistMutex.this.f((List) obj);
            }
        });
    }

    public static GameAssistMutex d() {
        if (f7653d == null) {
            synchronized (GameAssistMutex.class) {
                try {
                    if (f7653d == null) {
                        f7653d = new GameAssistMutex();
                    }
                } finally {
                }
            }
        }
        return f7653d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(MutexCallback mutexCallback) {
        mutexCallback.a(this.f7654a);
    }

    public synchronized void b(MutexCallback mutexCallback) {
        if (!this.f7656c.contains(mutexCallback)) {
            this.f7656c.add(mutexCallback);
            mutexCallback.a(this.f7654a);
        }
    }

    public GameAssistMutex c() {
        this.f7655b.addValue("GameAssistWindowTag");
        return this;
    }

    public synchronized void f(List list) {
        this.f7654a.clear();
        this.f7654a.addAll(list);
        this.f7656c.forEach(new Consumer() { // from class: cn.nubia.gameassist.utils.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                GameAssistMutex.this.e((GameAssistMutex.MutexCallback) obj);
            }
        });
    }

    public GameAssistMutex g() {
        this.f7655b.removeValue("GameAssistWindowTag");
        return this;
    }
}
