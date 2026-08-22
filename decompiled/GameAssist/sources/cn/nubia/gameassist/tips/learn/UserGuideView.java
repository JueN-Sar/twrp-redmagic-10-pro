package cn.nubia.gameassist.tips.learn;

import android.content.Context;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.utils.ToastUtil;
import com.zte.gameassist.common.InflaterHelper;

/* loaded from: classes.dex */
public class UserGuideView implements View.OnClickListener {

    /* renamed from: c, reason: collision with root package name */
    private Context f7635c;

    /* renamed from: h, reason: collision with root package name */
    private UserGuideController f7636h;

    /* renamed from: i, reason: collision with root package name */
    private UserGuideResource f7637i;

    /* renamed from: j, reason: collision with root package name */
    private View f7638j;

    /* renamed from: k, reason: collision with root package name */
    private View f7639k;

    /* renamed from: l, reason: collision with root package name */
    private TextView f7640l;

    /* renamed from: m, reason: collision with root package name */
    private TextView f7641m;

    /* renamed from: n, reason: collision with root package name */
    private ImageView f7642n;

    /* renamed from: o, reason: collision with root package name */
    private LinearLayout f7643o;

    /* renamed from: p, reason: collision with root package name */
    private ImageView f7644p;

    /* renamed from: q, reason: collision with root package name */
    private ImageView f7645q;

    /* renamed from: r, reason: collision with root package name */
    private Button f7646r;

    /* renamed from: s, reason: collision with root package name */
    private Button f7647s;

    public interface UserGuideResource {
        int[] a();

        int[] b();

        int[] c();
    }

    public UserGuideView(Context context, UserGuideResource userGuideResource, UserGuideController userGuideController) {
        this.f7635c = context;
        this.f7636h = userGuideController;
        this.f7637i = userGuideResource;
        View f2 = InflaterHelper.f(R.layout.layout_user_guide, null);
        this.f7638j = f2;
        TextView textView = (TextView) f2.findViewById(R.id.tip_user_guide_title);
        this.f7641m = textView;
        textView.setText(userGuideResource.b()[0]);
        TextView textView2 = (TextView) this.f7638j.findViewById(R.id.tip_user_guide_message);
        this.f7640l = textView2;
        textView2.setText(userGuideResource.c()[0]);
        ImageView imageView = (ImageView) this.f7638j.findViewById(R.id.image_user_guide);
        this.f7642n = imageView;
        imageView.setImageResource(userGuideResource.a()[0]);
        Button button = (Button) this.f7638j.findViewById(R.id.btn_skip);
        this.f7646r = button;
        button.setOnClickListener(this);
        Button button2 = (Button) this.f7638j.findViewById(R.id.btn_open);
        this.f7647s = button2;
        button2.setOnClickListener(this);
        this.f7639k = this.f7638j.findViewById(R.id.layout_btn_user_guide);
        this.f7643o = (LinearLayout) this.f7638j.findViewById(R.id.layout_mark_guide);
        ImageView imageView2 = (ImageView) this.f7638j.findViewById(R.id.image_mark1_guide);
        this.f7644p = imageView2;
        imageView2.setImageResource(R.drawable.user_guide_mark_red);
        ImageView imageView3 = (ImageView) this.f7638j.findViewById(R.id.image_mark2_guide);
        this.f7645q = imageView3;
        imageView3.setImageResource(R.drawable.user_guide_mark_dark);
    }

    public View a() {
        return this.f7638j;
    }

    public void b() {
        this.f7639k.setVisibility(8);
        this.f7643o.setVisibility(0);
        this.f7641m.setText(this.f7637i.b()[0]);
        this.f7640l.setText(this.f7637i.c()[0]);
        this.f7640l.setVisibility(0);
        this.f7642n.setImageResource(this.f7637i.a()[0]);
    }

    public void c() {
        this.f7639k.setVisibility(0);
        this.f7643o.setVisibility(8);
        this.f7641m.setText(this.f7637i.b()[1]);
        this.f7640l.setText(this.f7637i.c()[1]);
        if (this.f7636h.i()) {
            this.f7640l.setVisibility(0);
        } else {
            this.f7640l.setVisibility(4);
        }
        this.f7642n.setImageResource(this.f7637i.a()[1]);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_skip || id == R.id.btn_open) {
            this.f7636h.m();
            if (id != R.id.btn_open) {
                this.f7636h.n(false);
                return;
            }
            Settings.Global.putInt(this.f7635c.getContentResolver(), "zte_learned_behavior_enable", 1);
            ToastUtil.a(this.f7635c.getString(R.string.toast_action_learn_open));
            this.f7636h.n(true);
        }
    }
}
