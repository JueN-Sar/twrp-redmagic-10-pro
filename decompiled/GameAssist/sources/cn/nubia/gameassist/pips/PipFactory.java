package cn.nubia.gameassist.pips;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.widget.ImageView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.common.TileHost;
import cn.nubia.gameassist.pips.PipFactory;
import cn.nubia.gameassist.utils.AppsHelper;
import cn.nubia.gameassist.utils.CircleDrawable;
import cn.nubia.gameassist.utils.TimeUtil;
import com.zte.gameassist.utils.GaLog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

/* loaded from: classes.dex */
public class PipFactory {

    public static class LazyDrawable {

        /* renamed from: e, reason: collision with root package name */
        private static final Map f7147e = new ArrayMap();

        /* renamed from: a, reason: collision with root package name */
        private final String f7148a;

        /* renamed from: b, reason: collision with root package name */
        private WeakReference f7149b;

        /* renamed from: c, reason: collision with root package name */
        private Drawable f7150c;

        /* renamed from: d, reason: collision with root package name */
        private long f7151d;

        public LazyDrawable(String str, Drawable drawable) {
            this.f7148a = str;
            this.f7150c = drawable;
        }

        public static void f() {
            Map map = f7147e;
            if (map != null) {
                map.clear();
            }
        }

        public static void g(final ArrayList arrayList) {
            if (arrayList == null || arrayList.isEmpty()) {
                return;
            }
            f7147e.entrySet().removeIf(new Predicate() { // from class: cn.nubia.gameassist.pips.a
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean j2;
                    j2 = PipFactory.LazyDrawable.j(arrayList, (Map.Entry) obj);
                    return j2;
                }
            });
        }

        public static LazyDrawable h(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return i(str, null);
        }

        public static LazyDrawable i(String str, Drawable drawable) {
            Map map = f7147e;
            if (!map.containsKey(str)) {
                map.put(str, new LazyDrawable(str, drawable));
            }
            LazyDrawable lazyDrawable = (LazyDrawable) map.get(str);
            if (lazyDrawable == null || !"com.android.calendar".equals(str) || TimeUtil.a(lazyDrawable.f7151d)) {
                return lazyDrawable;
            }
            LazyDrawable lazyDrawable2 = new LazyDrawable(str, drawable);
            lazyDrawable2.m(System.currentTimeMillis());
            map.put(str, lazyDrawable2);
            return lazyDrawable2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ boolean j(ArrayList arrayList, Map.Entry entry) {
            AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            int i2 = 0;
            while (true) {
                if (i2 >= arrayList.size()) {
                    break;
                }
                if (((QSTile) arrayList.get(i2)).O().equals(entry.getKey())) {
                    atomicBoolean.set(false);
                    break;
                }
                i2++;
            }
            return atomicBoolean.get();
        }

        private void k(final Context context) {
            new AsyncTask<Void, Void, Drawable>() { // from class: cn.nubia.gameassist.pips.PipFactory.LazyDrawable.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public Drawable doInBackground(Void... voidArr) {
                    Context context2;
                    Drawable f2 = AppsHelper.f(context, LazyDrawable.this.f7148a);
                    if (f2 == null || (context2 = context) == null) {
                        return null;
                    }
                    int dimensionPixelSize = context2.getResources().getDimensionPixelSize(R.dimen.pip_icon_width);
                    Bitmap createBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
                    createBitmap.setDensity(context.getResources().getDisplayMetrics().densityDpi);
                    Canvas canvas = new Canvas(createBitmap);
                    f2.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                    f2.draw(canvas);
                    return new CircleDrawable(context, createBitmap);
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onPostExecute(Drawable drawable) {
                    ImageView imageView;
                    super.onPostExecute(drawable);
                    LazyDrawable.this.f7150c = drawable;
                    if (LazyDrawable.this.f7149b == null || LazyDrawable.this.f7148a == null || (imageView = (ImageView) LazyDrawable.this.f7149b.get()) == null || !LazyDrawable.this.f7148a.equals(imageView.getTag())) {
                        return;
                    }
                    imageView.setImageDrawable(LazyDrawable.this.f7150c);
                }
            }.execute(new Void[0]);
        }

        public ImageView l(ImageView imageView) {
            if (imageView != null) {
                imageView.setTag(this.f7148a);
                this.f7149b = new WeakReference(imageView);
                Drawable drawable = this.f7150c;
                if (drawable != null) {
                    imageView.setImageDrawable(drawable);
                } else if (this.f7148a != null) {
                    k(imageView.getContext());
                } else {
                    GaLog.k("TAG", "mDrawable=NULL, mPackageName=NULL ");
                }
            }
            return imageView;
        }

        public void m(long j2) {
            this.f7151d = j2;
        }

        public String toString() {
            if (this.f7150c == null) {
                return this.f7148a;
            }
            return this.f7148a + ":" + this.f7150c.toString();
        }
    }

    public static QSTile a(String str, String str2, TileHost tileHost) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return null;
        }
        return new PipTiles(tileHost, str, str2);
    }
}
