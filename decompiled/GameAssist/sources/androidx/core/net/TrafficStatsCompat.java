package androidx.core.net;

import android.net.TrafficStats;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import java.net.DatagramSocket;

/* loaded from: classes.dex */
public final class TrafficStatsCompat {

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static void a(DatagramSocket datagramSocket) {
            TrafficStats.tagDatagramSocket(datagramSocket);
        }

        @DoNotInline
        static void b(DatagramSocket datagramSocket) {
            TrafficStats.untagDatagramSocket(datagramSocket);
        }
    }
}
