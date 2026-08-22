package androidx.appcompat.app;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
class AlertController {
    NestedScrollView A;
    private Drawable C;
    private ImageView D;
    private TextView E;
    private TextView F;
    private View G;
    ListAdapter H;
    private int J;
    private int K;
    int L;
    int M;
    int N;
    int O;
    private boolean P;
    Handler R;

    /* renamed from: a, reason: collision with root package name */
    private final Context f156a;

    /* renamed from: b, reason: collision with root package name */
    final AppCompatDialog f157b;

    /* renamed from: c, reason: collision with root package name */
    private final Window f158c;

    /* renamed from: d, reason: collision with root package name */
    private final int f159d;

    /* renamed from: e, reason: collision with root package name */
    private CharSequence f160e;

    /* renamed from: f, reason: collision with root package name */
    private CharSequence f161f;

    /* renamed from: g, reason: collision with root package name */
    ListView f162g;

    /* renamed from: h, reason: collision with root package name */
    private View f163h;

    /* renamed from: i, reason: collision with root package name */
    private int f164i;

    /* renamed from: j, reason: collision with root package name */
    private int f165j;

    /* renamed from: k, reason: collision with root package name */
    private int f166k;

    /* renamed from: l, reason: collision with root package name */
    private int f167l;

    /* renamed from: m, reason: collision with root package name */
    private int f168m;

    /* renamed from: o, reason: collision with root package name */
    Button f170o;

    /* renamed from: p, reason: collision with root package name */
    private CharSequence f171p;

    /* renamed from: q, reason: collision with root package name */
    Message f172q;

    /* renamed from: r, reason: collision with root package name */
    private Drawable f173r;

    /* renamed from: s, reason: collision with root package name */
    Button f174s;
    private CharSequence t;
    Message u;
    private Drawable v;
    Button w;
    private CharSequence x;
    Message y;
    private Drawable z;

    /* renamed from: n, reason: collision with root package name */
    private boolean f169n = false;
    private int B = 0;
    int I = -1;
    private int Q = 0;
    private final View.OnClickListener S = new View.OnClickListener() { // from class: androidx.appcompat.app.AlertController.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Message message;
            Message message2;
            Message message3;
            AlertController alertController = AlertController.this;
            Message obtain = (view != alertController.f170o || (message3 = alertController.f172q) == null) ? (view != alertController.f174s || (message2 = alertController.u) == null) ? (view != alertController.w || (message = alertController.y) == null) ? null : Message.obtain(message) : Message.obtain(message2) : Message.obtain(message3);
            if (obtain != null) {
                obtain.sendToTarget();
            }
            AlertController alertController2 = AlertController.this;
            alertController2.R.obtainMessage(1, alertController2.f157b).sendToTarget();
        }
    };

    /* renamed from: androidx.appcompat.app.AlertController$2, reason: invalid class name */
    class AnonymousClass2 implements NestedScrollView.OnScrollChangeListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f176a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f177b;

        @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
        public void a(NestedScrollView nestedScrollView, int i2, int i3, int i4, int i5) {
            AlertController.f(nestedScrollView, this.f176a, this.f177b);
        }
    }

    /* renamed from: androidx.appcompat.app.AlertController$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f178c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ View f179h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ AlertController f180i;

        @Override // java.lang.Runnable
        public void run() {
            AlertController.f(this.f180i.A, this.f178c, this.f179h);
        }
    }

    /* renamed from: androidx.appcompat.app.AlertController$4, reason: invalid class name */
    class AnonymousClass4 implements AbsListView.OnScrollListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f181a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f182b;

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            AlertController.f(absListView, this.f181a, this.f182b);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
        }
    }

    /* renamed from: androidx.appcompat.app.AlertController$5, reason: invalid class name */
    class AnonymousClass5 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f183c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ View f184h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ AlertController f185i;

        @Override // java.lang.Runnable
        public void run() {
            AlertController.f(this.f185i.f162g, this.f183c, this.f184h);
        }
    }

    public static class AlertParams {
        public int A;
        public int B;
        public int C;
        public int D;
        public boolean[] F;
        public boolean G;
        public boolean H;
        public DialogInterface.OnMultiChoiceClickListener J;
        public Cursor K;
        public String L;
        public String M;
        public AdapterView.OnItemSelectedListener N;
        public OnPrepareListViewListener O;

        /* renamed from: a, reason: collision with root package name */
        public final Context f186a;

        /* renamed from: b, reason: collision with root package name */
        public final LayoutInflater f187b;

        /* renamed from: d, reason: collision with root package name */
        public Drawable f189d;

        /* renamed from: f, reason: collision with root package name */
        public CharSequence f191f;

        /* renamed from: g, reason: collision with root package name */
        public View f192g;

        /* renamed from: h, reason: collision with root package name */
        public CharSequence f193h;

        /* renamed from: i, reason: collision with root package name */
        public CharSequence f194i;

        /* renamed from: j, reason: collision with root package name */
        public Drawable f195j;

        /* renamed from: k, reason: collision with root package name */
        public DialogInterface.OnClickListener f196k;

        /* renamed from: l, reason: collision with root package name */
        public CharSequence f197l;

        /* renamed from: m, reason: collision with root package name */
        public Drawable f198m;

        /* renamed from: n, reason: collision with root package name */
        public DialogInterface.OnClickListener f199n;

        /* renamed from: o, reason: collision with root package name */
        public CharSequence f200o;

        /* renamed from: p, reason: collision with root package name */
        public Drawable f201p;

        /* renamed from: q, reason: collision with root package name */
        public DialogInterface.OnClickListener f202q;

        /* renamed from: s, reason: collision with root package name */
        public DialogInterface.OnCancelListener f204s;
        public DialogInterface.OnDismissListener t;
        public DialogInterface.OnKeyListener u;
        public CharSequence[] v;
        public ListAdapter w;
        public DialogInterface.OnClickListener x;
        public int y;
        public View z;

        /* renamed from: c, reason: collision with root package name */
        public int f188c = 0;

        /* renamed from: e, reason: collision with root package name */
        public int f190e = 0;
        public boolean E = false;
        public int I = -1;
        public boolean P = true;

        /* renamed from: r, reason: collision with root package name */
        public boolean f203r = true;

        public interface OnPrepareListViewListener {
            void a(ListView listView);
        }

        public AlertParams(Context context) {
            this.f186a = context;
            this.f187b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        private void b(final AlertController alertController) {
            ListAdapter listAdapter;
            final RecycleListView recycleListView = (RecycleListView) this.f187b.inflate(alertController.L, (ViewGroup) null);
            if (this.G) {
                listAdapter = this.K == null ? new ArrayAdapter<CharSequence>(this.f186a, alertController.M, R.id.text1, this.v) { // from class: androidx.appcompat.app.AlertController.AlertParams.1
                    @Override // android.widget.ArrayAdapter, android.widget.Adapter
                    public View getView(int i2, View view, ViewGroup viewGroup) {
                        View view2 = super.getView(i2, view, viewGroup);
                        boolean[] zArr = AlertParams.this.F;
                        if (zArr != null && zArr[i2]) {
                            recycleListView.setItemChecked(i2, true);
                        }
                        return view2;
                    }
                } : new CursorAdapter(this.f186a, this.K, false) { // from class: androidx.appcompat.app.AlertController.AlertParams.2

                    /* renamed from: c, reason: collision with root package name */
                    private final int f207c;

                    /* renamed from: h, reason: collision with root package name */
                    private final int f208h;

                    {
                        Cursor cursor = getCursor();
                        this.f207c = cursor.getColumnIndexOrThrow(AlertParams.this.L);
                        this.f208h = cursor.getColumnIndexOrThrow(AlertParams.this.M);
                    }

                    @Override // android.widget.CursorAdapter
                    public void bindView(View view, Context context, Cursor cursor) {
                        ((CheckedTextView) view.findViewById(R.id.text1)).setText(cursor.getString(this.f207c));
                        recycleListView.setItemChecked(cursor.getPosition(), cursor.getInt(this.f208h) == 1);
                    }

                    @Override // android.widget.CursorAdapter
                    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                        return AlertParams.this.f187b.inflate(alertController.M, viewGroup, false);
                    }
                };
            } else {
                int i2 = this.H ? alertController.N : alertController.O;
                if (this.K != null) {
                    listAdapter = new SimpleCursorAdapter(this.f186a, i2, this.K, new String[]{this.L}, new int[]{R.id.text1});
                } else {
                    listAdapter = this.w;
                    if (listAdapter == null) {
                        listAdapter = new CheckedItemAdapter(this.f186a, i2, R.id.text1, this.v);
                    }
                }
            }
            OnPrepareListViewListener onPrepareListViewListener = this.O;
            if (onPrepareListViewListener != null) {
                onPrepareListViewListener.a(recycleListView);
            }
            alertController.H = listAdapter;
            alertController.I = this.I;
            if (this.x != null) {
                recycleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: androidx.appcompat.app.AlertController.AlertParams.3
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public void onItemClick(AdapterView adapterView, View view, int i3, long j2) {
                        AlertParams.this.x.onClick(alertController.f157b, i3);
                        if (AlertParams.this.H) {
                            return;
                        }
                        alertController.f157b.dismiss();
                    }
                });
            } else if (this.J != null) {
                recycleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: androidx.appcompat.app.AlertController.AlertParams.4
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public void onItemClick(AdapterView adapterView, View view, int i3, long j2) {
                        boolean[] zArr = AlertParams.this.F;
                        if (zArr != null) {
                            zArr[i3] = recycleListView.isItemChecked(i3);
                        }
                        AlertParams.this.J.onClick(alertController.f157b, i3, recycleListView.isItemChecked(i3));
                    }
                });
            }
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.N;
            if (onItemSelectedListener != null) {
                recycleListView.setOnItemSelectedListener(onItemSelectedListener);
            }
            if (this.H) {
                recycleListView.setChoiceMode(1);
            } else if (this.G) {
                recycleListView.setChoiceMode(2);
            }
            alertController.f162g = recycleListView;
        }

        public void a(AlertController alertController) {
            View view = this.f192g;
            if (view != null) {
                alertController.l(view);
            } else {
                CharSequence charSequence = this.f191f;
                if (charSequence != null) {
                    alertController.q(charSequence);
                }
                Drawable drawable = this.f189d;
                if (drawable != null) {
                    alertController.n(drawable);
                }
                int i2 = this.f188c;
                if (i2 != 0) {
                    alertController.m(i2);
                }
                int i3 = this.f190e;
                if (i3 != 0) {
                    alertController.m(alertController.c(i3));
                }
            }
            CharSequence charSequence2 = this.f193h;
            if (charSequence2 != null) {
                alertController.o(charSequence2);
            }
            CharSequence charSequence3 = this.f194i;
            if (charSequence3 != null || this.f195j != null) {
                alertController.k(-1, charSequence3, this.f196k, null, this.f195j);
            }
            CharSequence charSequence4 = this.f197l;
            if (charSequence4 != null || this.f198m != null) {
                alertController.k(-2, charSequence4, this.f199n, null, this.f198m);
            }
            CharSequence charSequence5 = this.f200o;
            if (charSequence5 != null || this.f201p != null) {
                alertController.k(-3, charSequence5, this.f202q, null, this.f201p);
            }
            if (this.v != null || this.K != null || this.w != null) {
                b(alertController);
            }
            View view2 = this.z;
            if (view2 != null) {
                if (this.E) {
                    alertController.t(view2, this.A, this.B, this.C, this.D);
                    return;
                } else {
                    alertController.s(view2);
                    return;
                }
            }
            int i4 = this.y;
            if (i4 != 0) {
                alertController.r(i4);
            }
        }
    }

    private static final class ButtonHandler extends Handler {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference f217a;

        public ButtonHandler(DialogInterface dialogInterface) {
            this.f217a = new WeakReference(dialogInterface);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == -3 || i2 == -2 || i2 == -1) {
                ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) this.f217a.get(), message.what);
            } else {
                if (i2 != 1) {
                    return;
                }
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    private static class CheckedItemAdapter extends ArrayAdapter<CharSequence> {
        public CheckedItemAdapter(Context context, int i2, int i3, CharSequence[] charSequenceArr) {
            super(context, i2, i3, charSequenceArr);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    public static class RecycleListView extends ListView {
        private final int mPaddingBottomNoButtons;
        private final int mPaddingTopNoTitle;

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.appcompat.R.styleable.RecycleListView);
            this.mPaddingBottomNoButtons = obtainStyledAttributes.getDimensionPixelOffset(androidx.appcompat.R.styleable.RecycleListView_paddingBottomNoButtons, -1);
            this.mPaddingTopNoTitle = obtainStyledAttributes.getDimensionPixelOffset(androidx.appcompat.R.styleable.RecycleListView_paddingTopNoTitle, -1);
        }

        public void a(boolean z, boolean z2) {
            if (z2 && z) {
                return;
            }
            setPadding(getPaddingLeft(), z ? getPaddingTop() : this.mPaddingTopNoTitle, getPaddingRight(), z2 ? getPaddingBottom() : this.mPaddingBottomNoButtons);
        }
    }

    public AlertController(Context context, AppCompatDialog appCompatDialog, Window window) {
        this.f156a = context;
        this.f157b = appCompatDialog;
        this.f158c = window;
        this.R = new ButtonHandler(appCompatDialog);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, androidx.appcompat.R.styleable.AlertDialog, androidx.appcompat.R.attr.alertDialogStyle, 0);
        this.J = obtainStyledAttributes.getResourceId(androidx.appcompat.R.styleable.AlertDialog_android_layout, 0);
        this.K = obtainStyledAttributes.getResourceId(androidx.appcompat.R.styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.L = obtainStyledAttributes.getResourceId(androidx.appcompat.R.styleable.AlertDialog_listLayout, 0);
        this.M = obtainStyledAttributes.getResourceId(androidx.appcompat.R.styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.N = obtainStyledAttributes.getResourceId(androidx.appcompat.R.styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.O = obtainStyledAttributes.getResourceId(androidx.appcompat.R.styleable.AlertDialog_listItemLayout, 0);
        this.P = obtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.AlertDialog_showTitle, true);
        this.f159d = obtainStyledAttributes.getDimensionPixelSize(androidx.appcompat.R.styleable.AlertDialog_buttonIconDimen, 0);
        obtainStyledAttributes.recycle();
        appCompatDialog.k(1);
    }

    static boolean a(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (a(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    private void b(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }

    static void f(View view, View view2, View view3) {
        if (view2 != null) {
            view2.setVisibility(view.canScrollVertically(-1) ? 0 : 4);
        }
        if (view3 != null) {
            view3.setVisibility(view.canScrollVertically(1) ? 0 : 4);
        }
    }

    private ViewGroup i(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    private int j() {
        int i2 = this.K;
        return (i2 != 0 && this.Q == 1) ? i2 : this.J;
    }

    private void p(ViewGroup viewGroup, View view, int i2, int i3) {
        View findViewById = this.f158c.findViewById(androidx.appcompat.R.id.scrollIndicatorUp);
        View findViewById2 = this.f158c.findViewById(androidx.appcompat.R.id.scrollIndicatorDown);
        ViewCompat.A0(view, i2, i3);
        if (findViewById != null) {
            viewGroup.removeView(findViewById);
        }
        if (findViewById2 != null) {
            viewGroup.removeView(findViewById2);
        }
    }

    private void u(ViewGroup viewGroup) {
        int i2;
        Button button = (Button) viewGroup.findViewById(R.id.button1);
        this.f170o = button;
        button.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.f171p) && this.f173r == null) {
            this.f170o.setVisibility(8);
            i2 = 0;
        } else {
            this.f170o.setText(this.f171p);
            Drawable drawable = this.f173r;
            if (drawable != null) {
                int i3 = this.f159d;
                drawable.setBounds(0, 0, i3, i3);
                this.f170o.setCompoundDrawables(this.f173r, null, null, null);
            }
            this.f170o.setVisibility(0);
            i2 = 1;
        }
        Button button2 = (Button) viewGroup.findViewById(R.id.button2);
        this.f174s = button2;
        button2.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.t) && this.v == null) {
            this.f174s.setVisibility(8);
        } else {
            this.f174s.setText(this.t);
            Drawable drawable2 = this.v;
            if (drawable2 != null) {
                int i4 = this.f159d;
                drawable2.setBounds(0, 0, i4, i4);
                this.f174s.setCompoundDrawables(this.v, null, null, null);
            }
            this.f174s.setVisibility(0);
            i2 |= 2;
        }
        Button button3 = (Button) viewGroup.findViewById(R.id.button3);
        this.w = button3;
        button3.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.x) && this.z == null) {
            this.w.setVisibility(8);
        } else {
            this.w.setText(this.x);
            Drawable drawable3 = this.z;
            if (drawable3 != null) {
                int i5 = this.f159d;
                drawable3.setBounds(0, 0, i5, i5);
                this.w.setCompoundDrawables(this.z, null, null, null);
            }
            this.w.setVisibility(0);
            i2 |= 4;
        }
        if (z(this.f156a)) {
            if (i2 == 1) {
                b(this.f170o);
            } else if (i2 == 2) {
                b(this.f174s);
            } else if (i2 == 4) {
                b(this.w);
            }
        }
        if (i2 != 0) {
            return;
        }
        viewGroup.setVisibility(8);
    }

    private void v(ViewGroup viewGroup) {
        NestedScrollView nestedScrollView = (NestedScrollView) this.f158c.findViewById(androidx.appcompat.R.id.scrollView);
        this.A = nestedScrollView;
        nestedScrollView.setFocusable(false);
        this.A.setNestedScrollingEnabled(false);
        TextView textView = (TextView) viewGroup.findViewById(R.id.message);
        this.F = textView;
        if (textView == null) {
            return;
        }
        CharSequence charSequence = this.f161f;
        if (charSequence != null) {
            textView.setText(charSequence);
            return;
        }
        textView.setVisibility(8);
        this.A.removeView(this.F);
        if (this.f162g == null) {
            viewGroup.setVisibility(8);
            return;
        }
        ViewGroup viewGroup2 = (ViewGroup) this.A.getParent();
        int indexOfChild = viewGroup2.indexOfChild(this.A);
        viewGroup2.removeViewAt(indexOfChild);
        viewGroup2.addView(this.f162g, indexOfChild, new ViewGroup.LayoutParams(-1, -1));
    }

    private void w(ViewGroup viewGroup) {
        View view = this.f163h;
        if (view == null) {
            view = this.f164i != 0 ? LayoutInflater.from(this.f156a).inflate(this.f164i, viewGroup, false) : null;
        }
        boolean z = view != null;
        if (!z || !a(view)) {
            this.f158c.setFlags(131072, 131072);
        }
        if (!z) {
            viewGroup.setVisibility(8);
            return;
        }
        FrameLayout frameLayout = (FrameLayout) this.f158c.findViewById(androidx.appcompat.R.id.custom);
        frameLayout.addView(view, new ViewGroup.LayoutParams(-1, -1));
        if (this.f169n) {
            frameLayout.setPadding(this.f165j, this.f166k, this.f167l, this.f168m);
        }
        if (this.f162g != null) {
            ((LinearLayout.LayoutParams) ((LinearLayoutCompat.LayoutParams) viewGroup.getLayoutParams())).weight = 0.0f;
        }
    }

    private void x(ViewGroup viewGroup) {
        if (this.G != null) {
            viewGroup.addView(this.G, 0, new ViewGroup.LayoutParams(-1, -2));
            this.f158c.findViewById(androidx.appcompat.R.id.title_template).setVisibility(8);
            return;
        }
        this.D = (ImageView) this.f158c.findViewById(R.id.icon);
        if (!(!TextUtils.isEmpty(this.f160e)) || !this.P) {
            this.f158c.findViewById(androidx.appcompat.R.id.title_template).setVisibility(8);
            this.D.setVisibility(8);
            viewGroup.setVisibility(8);
            return;
        }
        TextView textView = (TextView) this.f158c.findViewById(androidx.appcompat.R.id.alertTitle);
        this.E = textView;
        textView.setText(this.f160e);
        int i2 = this.B;
        if (i2 != 0) {
            this.D.setImageResource(i2);
            return;
        }
        Drawable drawable = this.C;
        if (drawable != null) {
            this.D.setImageDrawable(drawable);
        } else {
            this.E.setPadding(this.D.getPaddingLeft(), this.D.getPaddingTop(), this.D.getPaddingRight(), this.D.getPaddingBottom());
            this.D.setVisibility(8);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void y() {
        View findViewById;
        ListAdapter listAdapter;
        View findViewById2;
        View findViewById3 = this.f158c.findViewById(androidx.appcompat.R.id.parentPanel);
        View findViewById4 = findViewById3.findViewById(androidx.appcompat.R.id.topPanel);
        View findViewById5 = findViewById3.findViewById(androidx.appcompat.R.id.contentPanel);
        View findViewById6 = findViewById3.findViewById(androidx.appcompat.R.id.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) findViewById3.findViewById(androidx.appcompat.R.id.customPanel);
        w(viewGroup);
        View findViewById7 = viewGroup.findViewById(androidx.appcompat.R.id.topPanel);
        View findViewById8 = viewGroup.findViewById(androidx.appcompat.R.id.contentPanel);
        View findViewById9 = viewGroup.findViewById(androidx.appcompat.R.id.buttonPanel);
        ViewGroup i2 = i(findViewById7, findViewById4);
        ViewGroup i3 = i(findViewById8, findViewById5);
        ViewGroup i4 = i(findViewById9, findViewById6);
        v(i3);
        u(i4);
        x(i2);
        boolean z = viewGroup.getVisibility() != 8;
        boolean z2 = (i2 == null || i2.getVisibility() == 8) ? 0 : 1;
        boolean z3 = (i4 == null || i4.getVisibility() == 8) ? false : true;
        if (!z3 && i3 != null && (findViewById2 = i3.findViewById(androidx.appcompat.R.id.textSpacerNoButtons)) != null) {
            findViewById2.setVisibility(0);
        }
        if (z2 != 0) {
            NestedScrollView nestedScrollView = this.A;
            if (nestedScrollView != null) {
                nestedScrollView.setClipToPadding(true);
            }
            View findViewById10 = (this.f161f == null && this.f162g == null) ? null : i2.findViewById(androidx.appcompat.R.id.titleDividerNoCustom);
            if (findViewById10 != null) {
                findViewById10.setVisibility(0);
            }
        } else if (i3 != null && (findViewById = i3.findViewById(androidx.appcompat.R.id.textSpacerNoTitle)) != null) {
            findViewById.setVisibility(0);
        }
        ListView listView = this.f162g;
        if (listView instanceof RecycleListView) {
            ((RecycleListView) listView).a(z2, z3);
        }
        if (!z) {
            View view = this.f162g;
            if (view == null) {
                view = this.A;
            }
            if (view != null) {
                p(i3, view, z2 | (z3 ? 2 : 0), 3);
            }
        }
        ListView listView2 = this.f162g;
        if (listView2 == null || (listAdapter = this.H) == null) {
            return;
        }
        listView2.setAdapter(listAdapter);
        int i5 = this.I;
        if (i5 > -1) {
            listView2.setItemChecked(i5, true);
            listView2.setSelection(i5);
        }
    }

    private static boolean z(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(androidx.appcompat.R.attr.alertDialogCenterButtons, typedValue, true);
        return typedValue.data != 0;
    }

    public int c(int i2) {
        TypedValue typedValue = new TypedValue();
        this.f156a.getTheme().resolveAttribute(i2, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView d() {
        return this.f162g;
    }

    public void e() {
        this.f157b.setContentView(j());
        y();
    }

    public boolean g(int i2, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A;
        return nestedScrollView != null && nestedScrollView.t(keyEvent);
    }

    public boolean h(int i2, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A;
        return nestedScrollView != null && nestedScrollView.t(keyEvent);
    }

    public void k(int i2, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        if (message == null && onClickListener != null) {
            message = this.R.obtainMessage(i2, onClickListener);
        }
        if (i2 == -3) {
            this.x = charSequence;
            this.y = message;
            this.z = drawable;
        } else if (i2 == -2) {
            this.t = charSequence;
            this.u = message;
            this.v = drawable;
        } else {
            if (i2 != -1) {
                throw new IllegalArgumentException("Button does not exist");
            }
            this.f171p = charSequence;
            this.f172q = message;
            this.f173r = drawable;
        }
    }

    public void l(View view) {
        this.G = view;
    }

    public void m(int i2) {
        this.C = null;
        this.B = i2;
        ImageView imageView = this.D;
        if (imageView != null) {
            if (i2 == 0) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                this.D.setImageResource(this.B);
            }
        }
    }

    public void n(Drawable drawable) {
        this.C = drawable;
        this.B = 0;
        ImageView imageView = this.D;
        if (imageView != null) {
            if (drawable == null) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                this.D.setImageDrawable(drawable);
            }
        }
    }

    public void o(CharSequence charSequence) {
        this.f161f = charSequence;
        TextView textView = this.F;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void q(CharSequence charSequence) {
        this.f160e = charSequence;
        TextView textView = this.E;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void r(int i2) {
        this.f163h = null;
        this.f164i = i2;
        this.f169n = false;
    }

    public void s(View view) {
        this.f163h = view;
        this.f164i = 0;
        this.f169n = false;
    }

    public void t(View view, int i2, int i3, int i4, int i5) {
        this.f163h = view;
        this.f164i = 0;
        this.f169n = true;
        this.f165j = i2;
        this.f166k = i3;
        this.f167l = i4;
        this.f168m = i5;
    }
}
