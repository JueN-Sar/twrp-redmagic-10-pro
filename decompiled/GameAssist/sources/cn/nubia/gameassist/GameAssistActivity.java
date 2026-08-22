package cn.nubia.gameassist;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.multisubscreen.view.PathImageView;
import com.zte.gameassist.drawable.linechart.LineChart;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class GameAssistActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.game_assist_activity);
        ImageView imageView = (ImageView) findViewById(R.id.iv);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Point(0, 100));
        arrayList.add(new Point(200, 300));
        arrayList.add(new Point(400, 150));
        arrayList.add(new Point(600, 400));
        imageView.setImageBitmap(Utils.e(800, 800, arrayList));
        Drawable drawable = getResources().getDrawable(R.drawable.purple_gradient);
        Bitmap createBitmap = Bitmap.createBitmap(900, 900, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        ((PathImageView) findViewById(R.id.cropped_left)).setImageBitmap(createBitmap);
        PathImageView pathImageView = (PathImageView) findViewById(R.id.cropped_right);
        pathImageView.setFlag(2);
        pathImageView.setImageBitmap(createBitmap);
        final LineChart lineChart = (LineChart) getResources().getDrawable(R.drawable.test_chart, null);
        final Handler handler = new Handler(Looper.getMainLooper());
        ImageView imageView2 = (ImageView) findViewById(R.id.test_chart);
        imageView2.setVisibility(0);
        imageView2.setImageDrawable(lineChart);
        handler.postDelayed(new Runnable(this) { // from class: cn.nubia.gameassist.GameAssistActivity.1
            @Override // java.lang.Runnable
            public void run() {
                lineChart.a((float) ((Math.random() * 5.0d) + 1.0d));
                handler.postDelayed(this, 1000L);
            }
        }, 1000L);
    }
}
