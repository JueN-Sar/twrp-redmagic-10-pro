package cn.nubia.screensaver.card;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.screensaver.bean.TopTextNoteInfo;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class GameNoteCard extends BaseCard {
    public static final Uri A = Uri.parse("content://cn.nubia.gamenotes/text_set_notes");
    private TopTextNoteInfo v;
    private TextView w;
    private TextView x;
    private View y;
    private int z;

    private TopTextNoteInfo A(Context context) {
        TopTextNoteInfo topTextNoteInfo = new TopTextNoteInfo();
        try {
            Cursor query = context.getContentResolver().query(A, null, null, null, "changeTime DESC LIMIT 1");
            if (query != null) {
                try {
                    if (query.getCount() > 0 && query.moveToFirst()) {
                        int columnIndex = query.getColumnIndex("packageName");
                        int columnIndex2 = query.getColumnIndex("content");
                        String string = query.getString(columnIndex);
                        String string2 = query.getString(columnIndex2);
                        topTextNoteInfo.f8990a = string;
                        topTextNoteInfo.f8991b = string2;
                        if ("cn.nubia.gamenotes.fiber".equals(string)) {
                            topTextNoteInfo.f8992c = this.f8994h.getString(R.string.game_saver_card_fiber_text);
                        } else {
                            topTextNoteInfo.f8992c = z(context, string);
                        }
                    }
                } finally {
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return topTextNoteInfo;
    }

    private String z(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                PackageManager packageManager = context.getPackageManager();
                return packageManager.getApplicationLabel(packageManager.getApplicationInfo(SystemMgr.A(str), 128)).toString();
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void a(View view) {
        this.w = (TextView) view.findViewById(R.id.tv_app_name);
        this.x = (TextView) view.findViewById(R.id.tv_note_content);
        this.y = view.findViewById(R.id.view_note_line);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected String b() {
        return this.f8994h.getString(R.string.nubia_left_game_note);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected int[] d() {
        return new int[]{R.layout.card_game_note};
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void e() {
        super.e();
        this.z = this.f8994h.getResources().getDimensionPixelSize(R.dimen.game_note_card_content_padding_hor);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    public void f() {
        this.v = A(this.f8994h);
        GaLog.e(this.f8993c, "p:" + this.v.f8990a + ",c:" + this.v.f8991b);
        r(false);
        s(TextUtils.isEmpty(this.v.f8991b));
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void w(ViewGroup viewGroup, boolean z) {
        super.w(viewGroup, z);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.y.getLayoutParams();
        if (z) {
            viewGroup.setPadding(this.z, viewGroup.getPaddingTop(), viewGroup.getPaddingEnd(), viewGroup.getPaddingBottom());
            ((LinearLayout) viewGroup).setGravity(8388611);
        } else {
            viewGroup.setPadding(viewGroup.getPaddingStart(), viewGroup.getPaddingTop(), this.z, viewGroup.getPaddingBottom());
            ((LinearLayout) viewGroup).setGravity(8388613);
        }
        this.w.setText(this.v.f8992c);
        this.x.setText(this.v.f8991b);
        this.w.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        marginLayoutParams.width = this.w.getMeasuredWidth();
        this.y.setLayoutParams(marginLayoutParams);
    }
}
