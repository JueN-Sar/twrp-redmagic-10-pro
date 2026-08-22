package com.zte.performanceindicator.network;

import android.content.Context;
import android.net.InetAddresses;
import android.net.LinkProperties;
import android.net.Network;
import android.net.TrafficStats;
import android.os.SystemClock;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructTimeval;
import android.util.Log;
import com.zte.performanceindicator.network.NetworkLatencyCheck;
import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import libcore.io.IoUtils;

/* loaded from: classes2.dex */
public class NetworkLatencyCheck {

    /* renamed from: m, reason: collision with root package name */
    private static final InetAddress f17920m = InetAddresses.parseNumericAddress("8.8.8.8");

    /* renamed from: n, reason: collision with root package name */
    private static final InetAddress f17921n = InetAddresses.parseNumericAddress("119.29.29.29");

    /* renamed from: o, reason: collision with root package name */
    private static final InetAddress f17922o = InetAddresses.parseNumericAddress("223.5.5.5");

    /* renamed from: p, reason: collision with root package name */
    private static final InetAddress f17923p = InetAddresses.parseNumericAddress("114.114.114.114");

    /* renamed from: a, reason: collision with root package name */
    private final Network f17924a;

    /* renamed from: b, reason: collision with root package name */
    private final LinkProperties f17925b;

    /* renamed from: c, reason: collision with root package name */
    private final ArrayList f17926c;

    /* renamed from: d, reason: collision with root package name */
    private final Integer f17927d;

    /* renamed from: e, reason: collision with root package name */
    private final long f17928e;

    /* renamed from: f, reason: collision with root package name */
    private final long f17929f;

    /* renamed from: g, reason: collision with root package name */
    private final long f17930g;

    /* renamed from: h, reason: collision with root package name */
    private final Context f17931h;

    /* renamed from: i, reason: collision with root package name */
    private CountDownLatch f17932i;

    /* renamed from: j, reason: collision with root package name */
    private final Map f17933j;

    /* renamed from: k, reason: collision with root package name */
    private final Map f17934k;

    /* renamed from: l, reason: collision with root package name */
    private NetworkLatencyCheckListener f17935l;

    public enum DnsResponseCode {
        NOERROR,
        FORMERR,
        SERVFAIL,
        NXDOMAIN,
        NOTIMP,
        REFUSED
    }

    private class DnsUdpCheck extends SimpleSocketCheck implements Runnable {

        /* renamed from: n, reason: collision with root package name */
        protected final Random f17936n;

        /* renamed from: o, reason: collision with root package name */
        protected final int f17937o;

        public DnsUdpCheck(InetAddress inetAddress, Measurement measurement) {
            super(NetworkLatencyCheck.this, inetAddress, measurement);
            this.f17936n = new Random();
            if (this.f17961i == OsConstants.AF_INET6) {
                this.f17937o = 28;
            } else {
                this.f17937o = 1;
            }
            this.f17962j.f17944b = "DNS UDP dst{" + this.f17960h.getHostAddress() + "}";
            this.f17962j.f17952j = this.f17960h.getHostAddress();
        }

        protected void d(String str, SocketAddress socketAddress) {
            StringBuilder sb = new StringBuilder();
            Measurement measurement = this.f17962j;
            sb.append(measurement.f17944b);
            sb.append(" qtype{");
            sb.append(this.f17937o);
            sb.append("} qname{www.baidu.com}");
            measurement.f17944b = sb.toString();
        }

        protected byte[] e(String str) {
            str.getBytes(StandardCharsets.US_ASCII);
            return new byte[]{(byte) this.f17936n.nextInt(), (byte) this.f17936n.nextInt(), 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 3, 119, 119, 119, 5, 98, 97, 105, 100, 117, 3, 99, 111, 109, 0, 0, (byte) this.f17937o, 0, 1};
        }

        protected String h(int i2) {
            try {
                return DnsResponseCode.values()[i2].toString();
            } catch (IndexOutOfBoundsException unused) {
                return String.valueOf(i2);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            String str;
            if (a()) {
                return;
            }
            try {
                c(OsConstants.SOCK_DGRAM, OsConstants.IPPROTO_UDP, 100L, 500L, 53);
                String valueOf = String.valueOf(this.f17936n.nextInt(900000) + 100000);
                d(valueOf, this.f17964l);
                byte[] e2 = e(valueOf);
                this.f17962j.f17945c = NetworkLatencyCheck.G();
                int i2 = 0;
                while (NetworkLatencyCheck.G() < NetworkLatencyCheck.this.f17930g - 1000) {
                    i2++;
                    try {
                        Os.write(this.f17963k, e2, 0, e2.length);
                        try {
                            ByteBuffer allocate = ByteBuffer.allocate(512);
                            Os.read(this.f17963k, allocate);
                            if (allocate.limit() > 3) {
                                str = " " + h(allocate.get(3) & 15);
                            } else {
                                str = "";
                            }
                            this.f17962j.g(2, "1/" + i2 + str);
                            break;
                        } catch (ErrnoException | InterruptedIOException unused) {
                        }
                    } catch (ErrnoException | InterruptedIOException e3) {
                        this.f17962j.f(e3.toString());
                    }
                }
                Measurement measurement = this.f17962j;
                if (measurement.f17946d == 0) {
                    measurement.f("0/" + i2);
                }
                close();
            } catch (ErrnoException | IOException e4) {
                this.f17962j.f(e4.toString());
                close();
            }
        }
    }

    public class MeasurementStatistic {

        /* renamed from: a, reason: collision with root package name */
        String f17954a;

        /* renamed from: b, reason: collision with root package name */
        long f17955b;

        /* renamed from: c, reason: collision with root package name */
        long f17956c;

        /* renamed from: d, reason: collision with root package name */
        long f17957d;

        protected MeasurementStatistic(String str, Double d2, Measurement measurement, Measurement measurement2) {
            this.f17955b = 0L;
            this.f17956c = 0L;
            this.f17957d = 0L;
            this.f17954a = str;
            this.f17955b = d2.longValue();
            this.f17957d = measurement.c() - measurement2.c();
            this.f17956c = measurement.c();
        }

        public long a() {
            return this.f17955b;
        }

        public long b() {
            return this.f17957d;
        }

        public long c() {
            return this.f17956c;
        }

        public boolean d() {
            return this.f17956c == 0;
        }

        public String toString() {
            if (this.f17956c == 0) {
                return "latency check error";
            }
            return "latency check result: target address is " + this.f17954a + ",avg is " + this.f17955b + "ms,  Jitter is " + this.f17957d + "ms";
        }
    }

    public interface NetworkLatencyCheckListener {
        void a();
    }

    public NetworkLatencyCheck(Network network, LinkProperties linkProperties, long j2, Context context) {
        ArrayList arrayList = new ArrayList();
        this.f17926c = arrayList;
        this.f17933j = new HashMap();
        this.f17934k = new HashMap();
        this.f17924a = network;
        this.f17925b = linkProperties;
        this.f17927d = v(linkProperties.getInterfaceName());
        this.f17928e = j2;
        long G = G();
        this.f17929f = G;
        this.f17930g = G + j2;
        this.f17931h = context;
        arrayList.add(f17920m);
        arrayList.add(f17921n);
        arrayList.add(f17922o);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void A(Map map, Map.Entry entry) {
        map.put((String) entry.getKey(), (Double) entry.getValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void B(Map.Entry entry) {
        Log.d("NetworkLatencyCheck", "ICMP AVG IS " + ((String) entry.getKey()).toString() + " value is " + entry.getValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void C(Measurement measurement) {
        new Thread(measurement.f17950h).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void D(Measurement measurement) {
        new Thread(measurement.f17950h).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long G() {
        return SystemClock.elapsedRealtime();
    }

    private void H(InetAddress inetAddress) {
        for (int i2 = 0; i2 < 5; i2++) {
            Measurement measurement = new Measurement(inetAddress, 2);
            if (this.f17934k.containsKey(inetAddress)) {
                ((ArrayList) this.f17934k.get(inetAddress)).add(measurement);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(measurement);
                this.f17934k.put(inetAddress, arrayList);
            }
        }
    }

    private void I(InetAddress inetAddress) {
        int mtu = this.f17925b.getMtu();
        if (mtu <= 0) {
            mtu = 1500;
        }
        r(inetAddress, 0);
        int t = t(inetAddress);
        r(inetAddress, mtu - t);
        if (!(inetAddress instanceof Inet6Address) || mtu == 1280) {
            return;
        }
        r(inetAddress, 1280 - t);
    }

    private int L() {
        int sum = this.f17934k.values().stream().mapToInt(new ToIntFunction() { // from class: com.zte.performanceindicator.network.d
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                int size;
                size = ((ArrayList) obj).size();
                return size;
            }
        }).sum();
        Log.d("NetworkLatencyCheck", "totalDNSMeasurementCount " + sum);
        return sum;
    }

    private int M() {
        int sum = this.f17933j.values().stream().mapToInt(new ToIntFunction() { // from class: com.zte.performanceindicator.network.e
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                int size;
                size = ((ArrayList) obj).size();
                return size;
            }
        }).sum();
        Log.d("NetworkLatencyCheck", "totalICMPMeasurementCount " + sum);
        return sum;
    }

    private int N() {
        return M() + L();
    }

    private void k() {
        Map map = this.f17933j;
        if (map != null) {
            map.clear();
        }
        Map map2 = this.f17934k;
        if (map2 != null) {
            map2.clear();
        }
    }

    private void q() {
        this.f17926c.clear();
        this.f17926c.add(f17920m);
        this.f17926c.add(f17921n);
        this.f17926c.add(f17922o);
    }

    private void r(InetAddress inetAddress, int i2) {
        if (i2 < 0) {
            return;
        }
        for (int i3 = 0; i3 < 5; i3++) {
            Measurement measurement = new Measurement(inetAddress, i2, 3);
            if (this.f17933j.containsKey(inetAddress)) {
                ((ArrayList) this.f17933j.get(inetAddress)).add(measurement);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(measurement);
                this.f17933j.put(inetAddress, arrayList);
            }
        }
    }

    private static int t(InetAddress inetAddress) {
        try {
            return InetAddress.getByAddress(inetAddress.getAddress()) instanceof Inet6Address ? 48 : 28;
        } catch (UnknownHostException e2) {
            throw new AssertionError("Create InetAddress fail(" + inetAddress + ")", e2);
        }
    }

    private static Integer v(String str) {
        try {
            return Integer.valueOf(NetworkInterface.getByName(str).getIndex());
        } catch (NullPointerException | SocketException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void w(Map map, Map.Entry entry) {
        map.put((String) entry.getKey(), (Double) entry.getValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void x(Map.Entry entry) {
        Log.d("NetworkLatencyCheck", "DNS AVG IS " + ((String) entry.getKey()).toString() + " value is " + entry.getValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean y(Measurement measurement) {
        return measurement.c() > 5 && measurement.f17943a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean z(Measurement measurement) {
        return measurement.c() > 5 && measurement.f17943a;
    }

    public void J(NetworkLatencyCheckListener networkLatencyCheckListener) {
        this.f17935l = networkLatencyCheckListener;
    }

    public void K() {
        Log.d("NetworkLatencyCheck", "startMeasurements");
        k();
        q();
        Iterator it = this.f17926c.iterator();
        while (it.hasNext()) {
            InetAddress inetAddress = (InetAddress) it.next();
            Log.d("NetworkLatencyCheck", "mLinkProperties getDnsServers " + inetAddress.toString());
            I(inetAddress);
            H(inetAddress);
        }
        H(f17923p);
        this.f17932i = new CountDownLatch(N());
        this.f17933j.values().stream().flatMap(new a()).forEach(new Consumer() { // from class: com.zte.performanceindicator.network.f
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NetworkLatencyCheck.C((NetworkLatencyCheck.Measurement) obj);
            }
        });
        this.f17934k.values().stream().flatMap(new a()).forEach(new Consumer() { // from class: com.zte.performanceindicator.network.g
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NetworkLatencyCheck.D((NetworkLatencyCheck.Measurement) obj);
            }
        });
        try {
            try {
                this.f17932i.await();
                if (this.f17935l == null) {
                    return;
                }
            } catch (InterruptedException unused) {
                Log.d("NetworkLatencyCheck", "mCountDownLatch InterruptedException");
                if (this.f17935l == null) {
                    return;
                }
            }
            Log.d("NetworkLatencyCheck", "call onNetworkLatencyCheckFinished on mCountDownLatch.await()");
            this.f17935l.a();
        } catch (Throwable th) {
            if (this.f17935l != null) {
                Log.d("NetworkLatencyCheck", "call onNetworkLatencyCheckFinished on mCountDownLatch.await()");
                this.f17935l.a();
            }
            throw th;
        }
    }

    public void O(NetworkLatencyCheckListener networkLatencyCheckListener) {
        this.f17935l = null;
    }

    public MeasurementStatistic s() {
        List list = (List) ((List) this.f17934k.values().stream().flatMap(new a()).collect(Collectors.toList())).stream().filter(new Predicate() { // from class: com.zte.performanceindicator.network.h
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean y;
                y = NetworkLatencyCheck.y((NetworkLatencyCheck.Measurement) obj);
                return y;
            }
        }).collect(Collectors.toList());
        Log.d("NetworkLatencyCheck", "getDNSMeasurementStatistic dnsList size is " + list.size());
        if (list.size() == 0) {
            return new MeasurementStatistic("", Double.valueOf(0.0d), new Measurement(), new Measurement());
        }
        Map map = (Map) list.stream().collect(Collectors.groupingBy(new i(), Collectors.averagingLong(new j())));
        Map map2 = (Map) list.stream().collect(Collectors.groupingBy(new i(), Collectors.maxBy(Comparator.comparing(new k()))));
        Map map3 = (Map) list.stream().collect(Collectors.groupingBy(new i(), Collectors.minBy(Comparator.comparing(new k()))));
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(new Consumer() { // from class: com.zte.performanceindicator.network.l
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NetworkLatencyCheck.w(linkedHashMap, (Map.Entry) obj);
            }
        });
        linkedHashMap.entrySet().stream().forEach(new Consumer() { // from class: com.zte.performanceindicator.network.m
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NetworkLatencyCheck.x((Map.Entry) obj);
            }
        });
        String str = (String) linkedHashMap.keySet().stream().findFirst().get();
        return new MeasurementStatistic(str, (Double) linkedHashMap.get(str), (Measurement) ((Optional) map2.get(str)).get(), (Measurement) ((Optional) map3.get(str)).get());
    }

    public MeasurementStatistic u() {
        List list = (List) ((List) this.f17933j.values().stream().flatMap(new a()).collect(Collectors.toList())).stream().filter(new Predicate() { // from class: com.zte.performanceindicator.network.n
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean z;
                z = NetworkLatencyCheck.z((NetworkLatencyCheck.Measurement) obj);
                return z;
            }
        }).collect(Collectors.toList());
        Log.d("NetworkLatencyCheck", "getICMPMeasurementStatistic icmpList size is " + list.size());
        if (list.size() == 0) {
            return new MeasurementStatistic("", Double.valueOf(0.0d), new Measurement(), new Measurement());
        }
        Map map = (Map) list.stream().collect(Collectors.groupingBy(new i(), Collectors.averagingLong(new j())));
        Map map2 = (Map) list.stream().collect(Collectors.groupingBy(new i(), Collectors.maxBy(Comparator.comparing(new k()))));
        Map map3 = (Map) list.stream().collect(Collectors.groupingBy(new i(), Collectors.minBy(Comparator.comparing(new k()))));
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(new Consumer() { // from class: com.zte.performanceindicator.network.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NetworkLatencyCheck.A(linkedHashMap, (Map.Entry) obj);
            }
        });
        linkedHashMap.entrySet().stream().forEach(new Consumer() { // from class: com.zte.performanceindicator.network.c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NetworkLatencyCheck.B((Map.Entry) obj);
            }
        });
        String str = (String) linkedHashMap.keySet().stream().findFirst().get();
        return new MeasurementStatistic(str, (Double) linkedHashMap.get(str), (Measurement) ((Optional) map2.get(str)).get(), (Measurement) ((Optional) map3.get(str)).get());
    }

    public class Measurement {

        /* renamed from: a, reason: collision with root package name */
        private boolean f17943a;

        /* renamed from: b, reason: collision with root package name */
        String f17944b;

        /* renamed from: c, reason: collision with root package name */
        long f17945c;

        /* renamed from: d, reason: collision with root package name */
        long f17946d;

        /* renamed from: e, reason: collision with root package name */
        long f17947e;

        /* renamed from: f, reason: collision with root package name */
        String f17948f;

        /* renamed from: g, reason: collision with root package name */
        String f17949g;

        /* renamed from: h, reason: collision with root package name */
        Runnable f17950h;

        /* renamed from: i, reason: collision with root package name */
        Thread f17951i;

        /* renamed from: j, reason: collision with root package name */
        String f17952j;

        protected Measurement() {
            this.f17944b = "";
            this.f17945c = 0L;
            this.f17946d = 0L;
            this.f17947e = 0L;
            this.f17948f = "";
            this.f17949g = "";
            this.f17950h = null;
            this.f17951i = null;
            this.f17952j = "";
            b();
        }

        private void e() {
            if (this.f17946d == 0) {
                this.f17946d = NetworkLatencyCheck.G();
            }
            if (this.f17945c == 0) {
                this.f17945c = this.f17946d;
            }
        }

        void b() {
            this.f17944b = "";
            this.f17945c = 0L;
            this.f17946d = 0L;
            this.f17947e = 0L;
            this.f17948f = "";
            this.f17949g = "";
            this.f17950h = null;
            this.f17951i = null;
            this.f17952j = "";
        }

        long c() {
            return this.f17947e;
        }

        String d() {
            return this.f17948f;
        }

        void f(String str) {
            e();
            this.f17943a = false;
            this.f17949g = "FAILED: " + str;
            Log.d("NetworkLatencyCheck", "recordFailure " + this.f17949g + ",mCountDownLatch " + NetworkLatencyCheck.this.f17932i.getCount() + ", dst is " + this.f17952j);
        }

        void g(int i2, String str) {
            e();
            this.f17943a = true;
            this.f17949g = "SUCCEEDED: " + str;
            this.f17947e = this.f17946d - this.f17945c;
            Log.d("NetworkLatencyCheck", "recordSuccess " + this.f17949g + ", delaytime " + this.f17947e + ",description " + this.f17944b + ",mCountDownLatch " + NetworkLatencyCheck.this.f17932i.getCount());
        }

        public String toString() {
            return this.f17944b + ": " + this.f17949g + " (" + (this.f17946d - this.f17945c) + "ms)";
        }

        protected Measurement(InetAddress inetAddress, int i2) {
            this.f17944b = "";
            this.f17945c = 0L;
            this.f17946d = 0L;
            this.f17947e = 0L;
            this.f17948f = "";
            this.f17949g = "";
            this.f17950h = null;
            this.f17951i = null;
            this.f17952j = "";
            this.f17948f = inetAddress.toString();
            if (i2 == 2) {
                this.f17950h = NetworkLatencyCheck.this.new DnsUdpCheck(inetAddress, this);
            }
        }

        protected Measurement(InetAddress inetAddress, int i2, int i3) {
            this.f17944b = "";
            this.f17945c = 0L;
            this.f17946d = 0L;
            this.f17947e = 0L;
            this.f17948f = "";
            this.f17949g = "";
            this.f17950h = null;
            this.f17951i = null;
            this.f17952j = "";
            this.f17948f = inetAddress.toString();
            this.f17950h = new IcmpCheck(NetworkLatencyCheck.this, inetAddress, i2, this);
        }
    }

    private class IcmpCheck extends SimpleSocketCheck implements Runnable {

        /* renamed from: n, reason: collision with root package name */
        private final int f17939n;

        /* renamed from: o, reason: collision with root package name */
        private final int f17940o;

        /* renamed from: p, reason: collision with root package name */
        private final int f17941p;

        public IcmpCheck(InetAddress inetAddress, InetAddress inetAddress2, int i2, Measurement measurement) {
            super(inetAddress, inetAddress2, measurement);
            if (this.f17961i == OsConstants.AF_INET6) {
                this.f17939n = OsConstants.IPPROTO_ICMPV6;
                this.f17940o = 128;
                this.f17962j.f17944b = "ICMPv6";
            } else {
                this.f17939n = OsConstants.IPPROTO_ICMP;
                this.f17940o = 8;
                this.f17962j.f17944b = "ICMPv4";
            }
            this.f17941p = i2;
            StringBuilder sb = new StringBuilder();
            Measurement measurement2 = this.f17962j;
            sb.append(measurement2.f17944b);
            sb.append(" dst{");
            sb.append(this.f17960h.getHostAddress());
            sb.append("}");
            measurement2.f17944b = sb.toString();
            this.f17962j.f17952j = this.f17960h.getHostAddress();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a()) {
                return;
            }
            try {
                c(OsConstants.SOCK_DGRAM, this.f17939n, 100L, 300L, 0);
                int i2 = this.f17941p;
                int i3 = i2 + 8;
                byte[] bArr = new byte[i3];
                bArr[0] = (byte) this.f17940o;
                this.f17962j.f17945c = NetworkLatencyCheck.G();
                int i4 = 0;
                while (NetworkLatencyCheck.G() < NetworkLatencyCheck.this.f17930g - 400) {
                    i4++;
                    bArr[i2 + 7] = (byte) i4;
                    try {
                        Os.write(this.f17963k, bArr, 0, i3);
                        try {
                            Os.read(this.f17963k, ByteBuffer.allocate(512));
                            this.f17962j.g(3, "1/" + i4);
                            break;
                        } catch (ErrnoException | InterruptedIOException unused) {
                        }
                    } catch (ErrnoException | InterruptedIOException e2) {
                        this.f17962j.f(e2.toString());
                    }
                }
                Measurement measurement = this.f17962j;
                if (measurement.f17946d == 0) {
                    measurement.f("0/" + i4);
                }
                close();
            } catch (ErrnoException | IOException e3) {
                this.f17962j.f(e3.toString());
                close();
            }
        }

        public IcmpCheck(NetworkLatencyCheck networkLatencyCheck, InetAddress inetAddress, int i2, Measurement measurement) {
            this(null, inetAddress, i2, measurement);
        }
    }

    private class SimpleSocketCheck implements Closeable {

        /* renamed from: c, reason: collision with root package name */
        protected final InetAddress f17959c;

        /* renamed from: h, reason: collision with root package name */
        protected final InetAddress f17960h;

        /* renamed from: i, reason: collision with root package name */
        protected final int f17961i;

        /* renamed from: j, reason: collision with root package name */
        protected final Measurement f17962j;

        /* renamed from: k, reason: collision with root package name */
        protected FileDescriptor f17963k;

        /* renamed from: l, reason: collision with root package name */
        protected SocketAddress f17964l;

        protected SimpleSocketCheck(InetAddress inetAddress, InetAddress inetAddress2, Measurement measurement) {
            this.f17962j = measurement;
            if (inetAddress2 instanceof Inet6Address) {
                Inet6Address inet6Address = null;
                if (inetAddress2.isLinkLocalAddress() && NetworkLatencyCheck.this.f17927d != null) {
                    try {
                        inet6Address = Inet6Address.getByAddress((String) null, inetAddress2.getAddress(), NetworkLatencyCheck.this.f17927d.intValue());
                    } catch (UnknownHostException e2) {
                        this.f17962j.f(e2.toString());
                    }
                }
                this.f17960h = inet6Address != null ? inet6Address : inetAddress2;
                this.f17961i = OsConstants.AF_INET6;
            } else {
                this.f17960h = inetAddress2;
                this.f17961i = OsConstants.AF_INET;
            }
            this.f17959c = inetAddress;
        }

        protected boolean a() {
            if (this.f17962j.f17946d == 0) {
                return false;
            }
            NetworkLatencyCheck.this.f17932i.countDown();
            return true;
        }

        protected void c(int i2, int i3, long j2, long j3, int i4) {
            int andSetThreadStatsTag = TrafficStats.getAndSetThreadStatsTag(-127);
            try {
                this.f17963k = Os.socket(this.f17961i, i2, i3);
                TrafficStats.setThreadStatsTag(andSetThreadStatsTag);
                FileDescriptor fileDescriptor = this.f17963k;
                int i5 = OsConstants.SOL_SOCKET;
                Os.setsockoptTimeval(fileDescriptor, i5, OsConstants.SO_SNDTIMEO, StructTimeval.fromMillis(j2));
                Os.setsockoptTimeval(this.f17963k, i5, OsConstants.SO_RCVTIMEO, StructTimeval.fromMillis(j3));
                NetworkLatencyCheck.this.f17924a.bindSocket(this.f17963k);
                InetAddress inetAddress = this.f17959c;
                if (inetAddress != null) {
                    Os.bind(this.f17963k, inetAddress, 0);
                }
                Os.connect(this.f17963k, this.f17960h, i4);
                this.f17964l = Os.getsockname(this.f17963k);
            } catch (Throwable th) {
                TrafficStats.setThreadStatsTag(andSetThreadStatsTag);
                throw th;
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (NetworkLatencyCheck.this.f17932i != null) {
                NetworkLatencyCheck.this.f17932i.countDown();
            }
            IoUtils.closeQuietly(this.f17963k);
        }

        protected SimpleSocketCheck(NetworkLatencyCheck networkLatencyCheck, InetAddress inetAddress, Measurement measurement) {
            this(null, inetAddress, measurement);
        }
    }
}
