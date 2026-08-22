package cn.nubia.multisubscreen.tiles;

import android.content.Context;
import android.os.Looper;
import cn.nubia.gameassist.common.QSTile;
import com.zte.gameassist.common.ThreadManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class MultiSubScreenTileHost implements QSTile.Host {

    /* renamed from: a, reason: collision with root package name */
    private final Context f8087a;

    /* renamed from: c, reason: collision with root package name */
    private List f8089c = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    private final Looper f8088b = ThreadManager.c().f();

    public MultiSubScreenTileHost(Context context) {
        this.f8087a = context;
    }

    @Override // cn.nubia.gameassist.common.QSTile.Host
    public void b() {
    }

    @Override // cn.nubia.gameassist.common.QSTile.Host
    public Looper c() {
        return this.f8088b;
    }

    @Override // cn.nubia.gameassist.common.QSTile.Host
    public Context getContext() {
        return this.f8087a;
    }
}
