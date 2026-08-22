package com.zte.mifavor.widget;

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
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Space;
import android.widget.TextView;
import com.zte.mifavor.utils.UIUtils;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public class AlertController {
    private static int q0 = -1;
    private static int r0 = 16842868;
    private View.OnClickListener A;
    private ImageView B;
    private ImageView C;
    private Space D;
    private Space E;
    private Space F;
    private Space G;
    private Drawable J;
    private ImageView K;
    private ImageView L;
    private TextViewZTE M;
    private TextView P;
    private TextView Q;
    private View R;
    private boolean S;
    private ListAdapter T;
    private int V;
    private int W;
    private int X;
    private int Y;
    private int Z;

    /* renamed from: a, reason: collision with root package name */
    private final Context f17533a;
    private int a0;

    /* renamed from: b, reason: collision with root package name */
    private final DialogInterface f17534b;

    /* renamed from: c, reason: collision with root package name */
    private final Window f17535c;
    private Handler c0;

    /* renamed from: d, reason: collision with root package name */
    private CharSequence f17536d;

    /* renamed from: e, reason: collision with root package name */
    private CharSequence f17537e;

    /* renamed from: f, reason: collision with root package name */
    private CharSequence f17538f;

    /* renamed from: g, reason: collision with root package name */
    private ListView f17539g;

    /* renamed from: h, reason: collision with root package name */
    private View f17540h;

    /* renamed from: i, reason: collision with root package name */
    private View f17541i;

    /* renamed from: j, reason: collision with root package name */
    private int f17542j;

    /* renamed from: k, reason: collision with root package name */
    private int f17543k;
    private boolean k0;

    /* renamed from: l, reason: collision with root package name */
    private int f17544l;

    /* renamed from: m, reason: collision with root package name */
    private int f17545m;
    private int m0;

    /* renamed from: n, reason: collision with root package name */
    private int f17546n;
    private int n0;

    /* renamed from: p, reason: collision with root package name */
    public Button f17548p;

    /* renamed from: q, reason: collision with root package name */
    private CharSequence f17549q;

    /* renamed from: r, reason: collision with root package name */
    private Message f17550r;

    /* renamed from: s, reason: collision with root package name */
    public Button f17551s;
    private CharSequence t;
    private Message u;
    public Button v;
    private CharSequence w;
    private Message x;
    private TextView y;
    private CharSequence z;

    /* renamed from: o, reason: collision with root package name */
    private boolean f17547o = false;
    private android.widget.ScrollView H = null;
    private int I = 0;
    private boolean N = false;
    private boolean O = false;
    private int U = -1;
    private int b0 = 0;
    private boolean d0 = false;
    private boolean e0 = false;
    private boolean f0 = false;
    private int g0 = 0;
    private boolean h0 = false;
    private boolean i0 = false;
    private int j0 = 0;
    private final View.OnClickListener l0 = new View.OnClickListener() { // from class: com.zte.mifavor.widget.AlertController.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Message obtain;
            AlertController alertController = AlertController.this;
            if (view != alertController.f17548p || alertController.f17550r == null) {
                AlertController alertController2 = AlertController.this;
                if (view != alertController2.f17551s || alertController2.u == null) {
                    AlertController alertController3 = AlertController.this;
                    obtain = (view != alertController3.v || alertController3.x == null) ? null : Message.obtain(AlertController.this.x);
                } else {
                    obtain = Message.obtain(AlertController.this.u);
                }
            } else {
                obtain = Message.obtain(AlertController.this.f17550r);
            }
            if (obtain != null) {
                obtain.sendToTarget();
            }
            AlertController.this.c0.obtainMessage(1, AlertController.this.f17534b).sendToTarget();
        }
    };
    View.OnLayoutChangeListener o0 = new View.OnLayoutChangeListener() { // from class: com.zte.mifavor.widget.AlertController.4
        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            Log.d("Z#AlertController", "onLayoutChange mSubTitleView. isMore=" + ((DialogMessage) AlertController.this.Q).c());
        }
    };
    View.OnLayoutChangeListener p0 = new View.OnLayoutChangeListener() { // from class: com.zte.mifavor.widget.AlertController.5
        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            Log.d("Z#AlertController", "onLayoutChange mMessageView. isMore=" + ((DialogMessage) AlertController.this.P).c());
            AlertController.this.i0();
        }
    };

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
        public boolean N;
        public AdapterView.OnItemSelectedListener O;
        public OnPrepareListViewListener P;

        /* renamed from: a, reason: collision with root package name */
        public final Context f17558a;

        /* renamed from: b, reason: collision with root package name */
        public final LayoutInflater f17559b;

        /* renamed from: d, reason: collision with root package name */
        public Drawable f17561d;

        /* renamed from: f, reason: collision with root package name */
        public CharSequence f17563f;

        /* renamed from: g, reason: collision with root package name */
        public View f17564g;

        /* renamed from: h, reason: collision with root package name */
        public CharSequence f17565h;

        /* renamed from: i, reason: collision with root package name */
        public CharSequence f17566i;

        /* renamed from: j, reason: collision with root package name */
        public CharSequence f17567j;

        /* renamed from: k, reason: collision with root package name */
        public DialogInterface.OnClickListener f17568k;

        /* renamed from: l, reason: collision with root package name */
        public CharSequence f17569l;

        /* renamed from: m, reason: collision with root package name */
        public DialogInterface.OnClickListener f17570m;

        /* renamed from: n, reason: collision with root package name */
        public CharSequence f17571n;

        /* renamed from: o, reason: collision with root package name */
        public View.OnClickListener f17572o;

        /* renamed from: p, reason: collision with root package name */
        public CharSequence f17573p;

        /* renamed from: q, reason: collision with root package name */
        public DialogInterface.OnClickListener f17574q;

        /* renamed from: s, reason: collision with root package name */
        public DialogInterface.OnCancelListener f17576s;
        public DialogInterface.OnDismissListener t;
        public DialogInterface.OnKeyListener u;
        public CharSequence[] v;
        public ListAdapter w;
        public DialogInterface.OnClickListener x;
        public int y;
        public View z;

        /* renamed from: c, reason: collision with root package name */
        public int f17560c = 0;

        /* renamed from: e, reason: collision with root package name */
        public int f17562e = 0;
        public boolean E = false;
        public int I = -1;
        public boolean Q = true;
        public int R = -1;
        public boolean S = false;
        public boolean T = false;
        public boolean U = false;
        public int V = 0;
        public boolean W = false;
        public boolean X = false;
        public boolean Y = false;
        public boolean Z = false;
        public int a0 = 0;

        /* renamed from: r, reason: collision with root package name */
        public boolean f17575r = true;

        public interface OnPrepareListViewListener {
            void a(ListView listView);
        }

        public AlertParams(Context context) {
            this.f17558a = context;
            this.f17559b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        private void b(final AlertController alertController) {
            ListAdapter simpleCursorAdapter;
            final RecycleListView recycleListView = (RecycleListView) this.f17559b.inflate(alertController.X, (ViewGroup) null);
            Log.d("Z#AlertController", "create List View mIsMultiChoice=" + this.G + ", mIsSingleChoice=" + this.H + ", listView=" + recycleListView);
            if (this.G) {
                simpleCursorAdapter = this.K == null ? new CheckedItemAdapter(this.f17558a, alertController.Y, R.id.text1, this.v) { // from class: com.zte.mifavor.widget.AlertController.AlertParams.1
                    @Override // com.zte.mifavor.widget.AlertController.CheckedItemAdapter, android.widget.ArrayAdapter, android.widget.Adapter
                    public View getView(int i2, View view, ViewGroup viewGroup) {
                        View view2 = super.getView(i2, view, viewGroup);
                        boolean[] zArr = AlertParams.this.F;
                        if (zArr != null && zArr[i2]) {
                            recycleListView.setItemChecked(i2, true);
                        }
                        return view2;
                    }
                } : new CursorAdapter(this.f17558a, this.K, false) { // from class: com.zte.mifavor.widget.AlertController.AlertParams.2

                    /* renamed from: c, reason: collision with root package name */
                    private final int f17579c;

                    /* renamed from: h, reason: collision with root package name */
                    private final int f17580h;

                    {
                        Cursor cursor = getCursor();
                        this.f17579c = cursor.getColumnIndexOrThrow(AlertParams.this.L);
                        this.f17580h = cursor.getColumnIndexOrThrow(AlertParams.this.M);
                    }

                    @Override // android.widget.CursorAdapter
                    public void bindView(View view, Context context, Cursor cursor) {
                        ((CheckedTextView) view.findViewById(R.id.text1)).setText(cursor.getString(this.f17579c));
                        recycleListView.setItemChecked(cursor.getPosition(), cursor.getInt(this.f17580h) == 1);
                    }

                    @Override // android.widget.CursorAdapter
                    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                        return AlertParams.this.f17559b.inflate(alertController.Y, viewGroup, false);
                    }
                };
            } else {
                int i2 = this.H ? alertController.Z : alertController.a0;
                if (this.K == null) {
                    ListAdapter listAdapter = this.w;
                    if (listAdapter == null) {
                        listAdapter = new CheckedItemAdapter(this.f17558a, i2, R.id.text1, this.v);
                    }
                    simpleCursorAdapter = listAdapter;
                } else {
                    simpleCursorAdapter = new SimpleCursorAdapter(this.f17558a, i2, this.K, new String[]{this.L}, new int[]{R.id.text1});
                }
            }
            OnPrepareListViewListener onPrepareListViewListener = this.P;
            if (onPrepareListViewListener != null) {
                onPrepareListViewListener.a(recycleListView);
            }
            alertController.T = simpleCursorAdapter;
            alertController.U = this.I;
            if (this.x != null) {
                recycleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.zte.mifavor.widget.AlertController.AlertParams.3
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public void onItemClick(AdapterView adapterView, View view, int i3, long j2) {
                        AlertParams.this.x.onClick(alertController.f17534b, i3);
                        if (AlertParams.this.H) {
                            return;
                        }
                        alertController.f17534b.dismiss();
                    }
                });
            } else if (this.J != null) {
                recycleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.zte.mifavor.widget.AlertController.AlertParams.4
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public void onItemClick(AdapterView adapterView, View view, int i3, long j2) {
                        boolean[] zArr = AlertParams.this.F;
                        if (zArr != null) {
                            zArr[i3] = recycleListView.isItemChecked(i3);
                        }
                        AlertParams.this.J.onClick(alertController.f17534b, i3, recycleListView.isItemChecked(i3));
                    }
                });
            }
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.O;
            if (onItemSelectedListener != null) {
                recycleListView.setOnItemSelectedListener(onItemSelectedListener);
            }
            if (this.H) {
                recycleListView.setChoiceMode(1);
            } else if (this.G) {
                recycleListView.setChoiceMode(2);
            }
            recycleListView.mRecycleOnMeasure = this.Q;
            alertController.f17539g = recycleListView;
            this.W = true;
            Log.d("Z#AlertController", "create List out. mIsMultiChoice=" + this.G + ", mIsSingleChoice=" + this.H + ", mIsShowTitle=" + this.W + ", listView=" + recycleListView);
        }

        public void a(AlertController alertController) {
            View view = this.f17564g;
            if (view != null) {
                alertController.I(view);
            } else {
                CharSequence charSequence = this.f17563f;
                if (charSequence != null) {
                    alertController.V(charSequence);
                }
                int i2 = this.R;
                if (i2 != -1) {
                    alertController.W(i2);
                }
                Drawable drawable = this.f17561d;
                if (drawable != null) {
                    alertController.K(drawable);
                }
                int i3 = this.f17560c;
                if (i3 >= 0) {
                    alertController.J(i3);
                }
                int i4 = this.f17562e;
                if (i4 > 0) {
                    alertController.J(alertController.y(i4));
                }
            }
            CharSequence charSequence2 = this.f17565h;
            if (charSequence2 != null) {
                alertController.O(charSequence2);
            }
            CharSequence charSequence3 = this.f17566i;
            if (charSequence3 != null) {
                alertController.U(charSequence3);
            }
            CharSequence charSequence4 = this.f17567j;
            if (charSequence4 != null) {
                alertController.G(-1, charSequence4, this.f17568k, null);
            }
            CharSequence charSequence5 = this.f17569l;
            if (charSequence5 != null) {
                alertController.G(-2, charSequence5, this.f17570m, null);
            }
            CharSequence charSequence6 = this.f17573p;
            if (charSequence6 != null) {
                alertController.G(-3, charSequence6, this.f17574q, null);
            }
            if (this.S) {
                alertController.N();
                alertController.T();
            }
            if (this.T) {
                alertController.S(this.V);
            }
            if (this.Y) {
                alertController.Q(this.a0);
            }
            if (this.U) {
                alertController.R();
            }
            if (this.Z) {
                alertController.P();
            }
            if (this.N) {
                alertController.M(true);
            }
            if (this.v != null || this.K != null || this.w != null) {
                AlertController.q0 = 0;
                if (!TextUtils.isEmpty(this.f17567j)) {
                    AlertController.q0 |= 1;
                }
                if (!TextUtils.isEmpty(this.f17569l)) {
                    AlertController.q0 |= 2;
                }
                if (!TextUtils.isEmpty(this.f17573p)) {
                    AlertController.q0 |= 4;
                }
                if (this.v != null && AlertController.q0 == 0) {
                    AlertController.r0 = com.zte.extres.R.attr.recycleListViewStyle;
                }
                b(alertController);
            }
            View view2 = this.z;
            if (view2 == null) {
                int i5 = this.y;
                if (i5 != 0) {
                    alertController.X(i5);
                }
            } else if (this.E) {
                alertController.Z(view2, this.A, this.B, this.C, this.D);
            } else {
                alertController.Y(view2);
            }
            CharSequence charSequence7 = this.f17571n;
            if (charSequence7 != null) {
                alertController.L(charSequence7, this.f17572o);
            }
            if (this.W) {
                alertController.N();
            }
            if (this.X) {
                alertController.v();
            }
        }
    }

    private static final class ButtonHandler extends Handler {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference f17589a;

        public ButtonHandler(DialogInterface dialogInterface) {
            this.f17589a = new WeakReference(dialogInterface);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == -3 || i2 == -2 || i2 == -1) {
                ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) this.f17589a.get(), message.what);
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

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i2, view, viewGroup);
            if (view2 != null && AlertController.q0 == 0 && getCount() > 0 && AlertController.r0 == com.zte.extres.R.attr.recycleListViewStyle) {
                if (i2 == getCount() - 1) {
                    view2.setBackgroundResource(com.zte.extres.R.drawable.dialog_list_bottom_item_bg);
                } else {
                    view2.setBackgroundResource(com.zte.extres.R.drawable.dialog_list_item_bg);
                }
            }
            return view2;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    public static class RecycleListView extends ListView {
        boolean mRecycleOnMeasure;

        public RecycleListView(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, AlertController.r0);
        }

        protected boolean recycleOnMeasure() {
            return this.mRecycleOnMeasure;
        }

        public RecycleListView(Context context, AttributeSet attributeSet, int i2) {
            this(context, attributeSet, i2, 0);
        }

        public RecycleListView(Context context, AttributeSet attributeSet, int i2, int i3) {
            super(context, attributeSet, i2, i3);
            this.mRecycleOnMeasure = true;
        }
    }

    public AlertController(Context context, DialogInterface dialogInterface, Window window) {
        this.k0 = false;
        this.m0 = 0;
        this.n0 = com.zte.extres.R.attr.alertDialogStyleMfv;
        this.f17533a = context;
        this.k0 = UIUtils.j(context);
        try {
            this.m0 = context.getDisplay().getType();
        } catch (Exception e2) {
            Log.e("Z#AlertController", "get Type or get Display error, e = ", e2);
        }
        Log.d("Z#AlertController", "AlertController in. mDisplayType = " + this.m0 + ", mIsOutScreen=" + this.k0);
        this.f17534b = dialogInterface;
        this.f17535c = window;
        this.c0 = new ButtonHandler(dialogInterface);
        r0 = R.attr.listViewStyle;
        q0 = -1;
        if (this.m0 == 3) {
            this.n0 = com.zte.extres.R.attr.alertDialogPCModeStyle;
        } else {
            this.n0 = com.zte.extres.R.attr.alertDialogStyleMfv;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, com.zte.extres.R.styleable.AlertDialogMfv, this.n0, 0);
        this.V = obtainStyledAttributes.getResourceId(com.zte.extres.R.styleable.AlertDialogMfv_layout, com.zte.extres.R.layout.alert_dialog_zte_light);
        this.W = obtainStyledAttributes.getResourceId(com.zte.extres.R.styleable.AlertDialogMfv_buttonPanelSideLayout, 0);
        this.X = obtainStyledAttributes.getResourceId(com.zte.extres.R.styleable.AlertDialogMfv_listLayout, com.zte.extres.R.layout.select_dialog_material);
        this.Y = obtainStyledAttributes.getResourceId(com.zte.extres.R.styleable.AlertDialogMfv_multiChoiceItemLayout, com.zte.extres.R.layout.select_dialog_multichoice_material_mfs);
        this.Z = obtainStyledAttributes.getResourceId(com.zte.extres.R.styleable.AlertDialogMfv_singleChoiceItemLayout, com.zte.extres.R.layout.select_dialog_singlechoice_material_mfs);
        this.a0 = obtainStyledAttributes.getResourceId(com.zte.extres.R.styleable.AlertDialogMfv_listItemLayout, com.zte.extres.R.layout.select_dialog_item_material_mfs);
        Log.d("Z#AlertController", "AlertController out. mIsShowTitle=" + this.N);
        obtainStyledAttributes.recycle();
        if (this.m0 != 3) {
            F();
        } else {
            window.setGravity(17);
        }
    }

    private int D() {
        int i2 = this.W;
        return (i2 != 0 && this.b0 == 1) ? i2 : this.V;
    }

    private void E(TypedArray typedArray, View view, View view2, View view3, View view4, boolean z, boolean z2, boolean z3) {
        int i2;
        ListAdapter listAdapter;
        typedArray.getBoolean(com.zte.extres.R.styleable.AlertDialogMfv_needsDefaultBackgrounds, true);
        int resourceId = typedArray.getResourceId(com.zte.extres.R.styleable.AlertDialogMfv_topBright, 0);
        int resourceId2 = typedArray.getResourceId(com.zte.extres.R.styleable.AlertDialogMfv_topDark, 0);
        int resourceId3 = typedArray.getResourceId(com.zte.extres.R.styleable.AlertDialogMfv_centerBright, 0);
        int resourceId4 = typedArray.getResourceId(com.zte.extres.R.styleable.AlertDialogMfv_centerDark, 0);
        View[] viewArr = new View[4];
        boolean[] zArr = new boolean[4];
        if (z) {
            viewArr[0] = view;
            zArr[0] = false;
            i2 = 1;
        } else {
            i2 = 0;
        }
        View view5 = null;
        viewArr[i2] = view2.getVisibility() == 8 ? null : view2;
        zArr[i2] = this.f17539g != null;
        int i3 = i2 + 1;
        if (z2) {
            viewArr[i3] = view3;
            zArr[i3] = this.S;
            i3 = i2 + 2;
        }
        if (z3) {
            viewArr[i3] = view4;
            zArr[i3] = true;
        }
        boolean z4 = false;
        boolean z5 = false;
        for (int i4 = 0; i4 < 4; i4++) {
            View view6 = viewArr[i4];
            if (view6 != null) {
                if (view5 != null) {
                    if (z4) {
                        view5.setBackgroundResource(z5 ? resourceId3 : resourceId4);
                    } else {
                        view5.setBackgroundResource(z5 ? resourceId : resourceId2);
                    }
                    z4 = true;
                }
                z5 = zArr[i4];
                view5 = view6;
            }
        }
        if (view5 != null) {
            if (z4) {
                int resourceId5 = typedArray.getResourceId(com.zte.extres.R.styleable.AlertDialogMfv_bottomBright, 0);
                int resourceId6 = typedArray.getResourceId(com.zte.extres.R.styleable.AlertDialogMfv_bottomMedium, 0);
                int resourceId7 = typedArray.getResourceId(com.zte.extres.R.styleable.AlertDialogMfv_bottomDark, 0);
                if (!z5) {
                    resourceId5 = resourceId7;
                } else if (z3) {
                    resourceId5 = resourceId6;
                }
                view5.setBackgroundResource(resourceId5);
            } else {
                int resourceId8 = typedArray.getResourceId(com.zte.extres.R.styleable.AlertDialogMfv_fullBright, 0);
                int resourceId9 = typedArray.getResourceId(com.zte.extres.R.styleable.AlertDialogMfv_fullDark, 0);
                if (!z5) {
                    resourceId8 = resourceId9;
                }
                view5.setBackgroundResource(resourceId8);
            }
        }
        ListView listView = this.f17539g;
        if (listView == null || (listAdapter = this.T) == null) {
            return;
        }
        listView.setAdapter(listAdapter);
        int i5 = this.U;
        if (i5 > -1) {
            listView.setItemChecked(i5, true);
            listView.setSelection(i5);
        }
    }

    private void F() {
        Log.d("Z#AlertController", "setBottomOnZteTheme setGravity BOTTOM.");
        this.f17535c.setGravity(80);
    }

    private boolean a0() {
        TextView textView = (TextView) this.f17535c.findViewById(com.zte.extres.R.id.button_info);
        this.y = textView;
        textView.setOnClickListener(this.A);
        if (TextUtils.isEmpty(this.z)) {
            this.y.setVisibility(8);
            return false;
        }
        this.y.setVisibility(0);
        this.y.setText(this.z);
        return true;
    }

    private boolean b0() {
        int i2;
        Button button;
        Button button2;
        this.f17548p = (Button) this.f17535c.findViewById(R.id.button1);
        this.f17551s = (Button) this.f17535c.findViewById(R.id.button2);
        this.v = (Button) this.f17535c.findViewById(R.id.button3);
        this.f17548p.setOnClickListener(this.l0);
        if (TextUtils.isEmpty(this.f17549q)) {
            this.f17548p.setVisibility(8);
            i2 = 0;
        } else {
            this.f17548p.setText(this.f17549q);
            this.f17548p.setVisibility(0);
            i2 = 1;
        }
        Log.d("Z#AlertController", "setup Buttons, mIsRecommendTxt=" + this.e0 + ", mRecommendTxtColor=" + this.g0 + ", mIsRecommendBackground=" + this.f0 + " mIsNegativeTxt=" + this.h0 + ", mNegativeTxtColor=" + this.j0 + ", mIsNegativeBackground=" + this.i0);
        if (this.e0 && (button2 = this.f17548p) != null) {
            int i3 = this.g0;
            if (i3 != 0) {
                button2.setTextColor(i3);
            } else {
                button2.setTextColor(this.f17533a.getColor(com.zte.extres.R.color.mfvc_dialog_recommend_text_color));
            }
            if (Utils.f17815b) {
                this.f17548p.setForceDarkAllowed(false);
            }
        }
        if (this.h0 && (button = this.f17551s) != null) {
            int i4 = this.j0;
            if (i4 != 0) {
                button.setTextColor(i4);
            } else {
                button.setTextColor(this.f17533a.getColor(com.zte.extres.R.color.mfvc_dialog_recommend_text_color));
            }
            if (Utils.f17815b) {
                this.f17551s.setForceDarkAllowed(false);
            }
        }
        Button button3 = (Button) this.f17535c.findViewById(R.id.button2);
        this.f17551s = button3;
        button3.setOnClickListener(this.l0);
        if (this.f0) {
            Button button4 = this.f17548p;
            if (button4 != null) {
                button4.setTextAppearance(this.f17533a, com.zte.extres.R.style.mfvc_dark_btn_font);
                this.f17548p.setBackgroundResource(com.zte.extres.R.drawable.btn_default_color_bg_zte);
                if (Utils.f17815b) {
                    this.f17548p.setForceDarkAllowed(false);
                }
            } else {
                Log.w("Z#AlertController", "setup Buttons, mButtonPositive is null.");
            }
            Button button5 = this.f17551s;
            if (button5 != null) {
                button5.setBackgroundResource(com.zte.extres.R.drawable.btn_default_light_mfv50_negative);
                if (Utils.f17815b) {
                    this.f17551s.setForceDarkAllowed(false);
                }
            } else {
                Log.w("Z#AlertController", "setup Buttons, mButtonNegative is null.");
            }
        }
        if (this.i0) {
            Button button6 = this.f17551s;
            if (button6 != null) {
                button6.setTextAppearance(this.f17533a, com.zte.extres.R.style.mfvc_dark_btn_font);
                this.f17551s.setBackgroundResource(com.zte.extres.R.drawable.btn_default_color_bg_zte);
                if (Utils.f17815b) {
                    this.f17551s.setForceDarkAllowed(false);
                }
            } else {
                Log.w("Z#AlertController", "setup Buttons, mButtonNegative is null.");
            }
        }
        if (TextUtils.isEmpty(this.t)) {
            this.f17551s.setVisibility(8);
        } else {
            this.f17551s.setText(this.t);
            this.f17551s.setVisibility(0);
            i2 |= 2;
        }
        Button button7 = (Button) this.f17535c.findViewById(R.id.button3);
        this.v = button7;
        button7.setOnClickListener(this.l0);
        if (TextUtils.isEmpty(this.w)) {
            this.v.setVisibility(8);
        } else {
            this.v.setText(this.w);
            this.v.setVisibility(0);
            i2 |= 4;
        }
        q0 = i2;
        if (h0(this.f17533a) && this.m0 != 3) {
            if (i2 == 1) {
                u(this.f17548p);
            } else if (i2 == 2) {
                u(this.f17551s);
            } else if (i2 == 4) {
                u(this.v);
            }
        }
        this.B = (ImageView) this.f17535c.findViewById(com.zte.extres.R.id.divider1);
        this.C = (ImageView) this.f17535c.findViewById(com.zte.extres.R.id.divider2);
        int i5 = this.m0;
        if (i5 != 3) {
            if (i2 == 3 || i2 == 6) {
                this.B.setVisibility(0);
                this.C.setVisibility(8);
            } else if (i2 == 5) {
                this.B.setVisibility(8);
                this.C.setVisibility(0);
            } else if (i2 == 7) {
                this.B.setVisibility(0);
                this.C.setVisibility(0);
            }
            if (i2 != 1 && i2 != 2 && i2 != 4) {
                Space space = (Space) this.f17535c.findViewById(com.zte.extres.R.id.leftpadding);
                this.D = space;
                space.setVisibility(0);
                Space space2 = (Space) this.f17535c.findViewById(com.zte.extres.R.id.rightpadding);
                this.E = space2;
                space2.setVisibility(0);
            }
        } else if (i5 == 3) {
            ImageView imageView = (ImageView) this.f17535c.findViewById(com.zte.extres.R.id.divider1);
            this.B = imageView;
            imageView.setVisibility(8);
            ImageView imageView2 = (ImageView) this.f17535c.findViewById(com.zte.extres.R.id.divider2);
            this.C = imageView2;
            imageView2.setVisibility(8);
            this.F = (Space) this.f17535c.findViewById(com.zte.extres.R.id.buttonpadding23);
            this.G = (Space) this.f17535c.findViewById(com.zte.extres.R.id.buttonpadding31);
            if (i2 == 1 || i2 == 2 || i2 == 4) {
                this.F.setVisibility(8);
                this.G.setVisibility(8);
            } else if (i2 == 6) {
                this.F.setVisibility(0);
                this.G.setVisibility(8);
            } else if (i2 == 5 || i2 == 3) {
                this.F.setVisibility(8);
                this.G.setVisibility(0);
            } else if (i2 == 7) {
                this.F.setVisibility(0);
                this.G.setVisibility(0);
            }
        }
        Log.d("Z#AlertController", "hide divider mIsRecommendBackground = " + this.f0);
        if (this.f0) {
            this.B.setVisibility(4);
            this.C.setVisibility(4);
            if (this.k0) {
                this.B.setVisibility(8);
                this.C.setVisibility(8);
                w();
            }
        }
        return i2 != 0;
    }

    private void c0(LinearLayout linearLayout) {
        android.widget.ScrollView scrollView = (android.widget.ScrollView) this.f17535c.findViewById(com.zte.extres.R.id.scrollView);
        this.H = scrollView;
        if (scrollView != null) {
            scrollView.setFocusable(false);
        }
        TextView textView = (TextView) this.f17535c.findViewById(R.id.message);
        this.P = textView;
        if (textView == null) {
            Log.e("Z#AlertController", "setup Content. mMessageView is null.");
            return;
        }
        CharSequence charSequence = this.f17537e;
        if (charSequence != null) {
            textView.setText(charSequence);
        } else {
            textView.setVisibility(8);
            android.widget.ScrollView scrollView2 = this.H;
            if (scrollView2 != null) {
                scrollView2.removeView(this.P);
            }
            Log.d("Z#AlertController", "setup Content. addView mListView=" + this.f17539g);
            if (this.f17539g != null) {
                linearLayout.removeView(this.f17535c.findViewById(com.zte.extres.R.id.scrollView));
                linearLayout.addView(this.f17539g, new LinearLayout.LayoutParams(-1, -1));
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 1.0f));
            } else {
                linearLayout.setVisibility(8);
            }
        }
        TextView textView2 = (TextView) this.f17535c.findViewById(com.zte.extres.R.id.subTitle);
        this.Q = textView2;
        if (textView2 == null) {
            Log.w("Z#AlertController", "setup Content. mSubTitleView is null.");
            return;
        }
        CharSequence charSequence2 = this.f17538f;
        if (charSequence2 != null) {
            textView2.setText(charSequence2);
        } else {
            textView2.setVisibility(8);
            android.widget.ScrollView scrollView3 = this.H;
            if (scrollView3 != null) {
                scrollView3.removeView(this.Q);
            }
        }
        this.Q.addOnLayoutChangeListener(this.o0);
        this.P.addOnLayoutChangeListener(this.p0);
    }

    private void d0() {
        View decorView = this.f17535c.getDecorView();
        final View findViewById = this.f17535c.findViewById(com.zte.extres.R.id.parentPanel);
        if (findViewById == null || decorView == null) {
            return;
        }
        decorView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.zte.mifavor.widget.AlertController.2
            @Override // android.view.View.OnApplyWindowInsetsListener
            public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                if (windowInsets.isRound()) {
                    int dimensionPixelOffset = AlertController.this.f17533a.getResources().getDimensionPixelOffset(com.zte.extres.R.dimen.alert_dialog_round_padding);
                    findViewById.setPadding(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
                }
                return windowInsets.consumeSystemWindowInsets();
            }
        });
        decorView.setFitsSystemWindows(true);
        decorView.requestApplyInsets();
    }

    private boolean f0(LinearLayout linearLayout) {
        boolean z = false;
        if (this.R != null) {
            linearLayout.addView(this.R, 0, new LinearLayout.LayoutParams(-1, -2));
            this.f17535c.findViewById(com.zte.extres.R.id.title_template).setVisibility(8);
        } else {
            this.K = (ImageView) this.f17535c.findViewById(com.zte.extres.R.id.icon);
            if (this.m0 == 3) {
                ImageView imageView = (ImageView) this.f17535c.findViewById(com.zte.extres.R.id.close_icon);
                this.L = imageView;
                if (imageView != null) {
                    imageView.setOnClickListener(new View.OnClickListener() { // from class: com.zte.mifavor.widget.AlertController.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            AlertController.this.c0.obtainMessage(1, AlertController.this.f17534b).sendToTarget();
                        }
                    });
                }
            }
            if (!(!TextUtils.isEmpty(this.f17536d)) || !this.N) {
                this.f17535c.findViewById(com.zte.extres.R.id.title_template).setVisibility(8);
                this.K.setVisibility(8);
                Log.d("Z#AlertController", "setup Title out. hasTitle=" + z + ", mIsShowTitle=" + this.N);
                return z;
            }
            TextViewZTE textViewZTE = (TextViewZTE) this.f17535c.findViewById(com.zte.extres.R.id.alertTitle);
            this.M = textViewZTE;
            textViewZTE.setText(this.f17536d);
            int i2 = this.I;
            if (i2 != 0) {
                this.K.setImageResource(i2);
            } else {
                Drawable drawable = this.J;
                if (drawable != null) {
                    this.K.setImageDrawable(drawable);
                } else {
                    this.M.setPadding(this.K.getPaddingLeft(), this.K.getPaddingTop(), this.K.getPaddingRight(), this.K.getPaddingBottom());
                    this.K.setVisibility(8);
                }
            }
            if (linearLayout != null) {
                linearLayout.setMinimumHeight((int) this.f17533a.getResources().getDimension(com.zte.extres.R.dimen.mfvc_alert_dialog_title_height));
            }
        }
        z = true;
        Log.d("Z#AlertController", "setup Title out. hasTitle=" + z + ", mIsShowTitle=" + this.N);
        return z;
    }

    private void g0() {
        LinearLayout linearLayout = (LinearLayout) this.f17535c.findViewById(com.zte.extres.R.id.contentPanel);
        c0(linearLayout);
        boolean b0 = b0();
        a0();
        LinearLayout linearLayout2 = (LinearLayout) this.f17535c.findViewById(com.zte.extres.R.id.topPanel);
        TypedArray obtainStyledAttributes = this.f17533a.obtainStyledAttributes(null, com.zte.extres.R.styleable.AlertDialogMfv, this.n0, 0);
        boolean f0 = f0(linearLayout2);
        View findViewById = this.f17535c.findViewById(com.zte.extres.R.id.buttonPanel);
        this.f17541i = findViewById;
        if (!b0) {
            findViewById.setVisibility(8);
            View findViewById2 = this.f17535c.findViewById(com.zte.extres.R.id.textSpacerNoButtons);
            if (findViewById2 != null) {
                findViewById2.setVisibility(0);
            }
            this.f17535c.setCloseOnTouchOutsideIfNotSet(true);
        }
        FrameLayout frameLayout = (FrameLayout) this.f17535c.findViewById(com.zte.extres.R.id.customPanel);
        View view = this.f17540h;
        if (view == null) {
            view = this.f17542j != 0 ? LayoutInflater.from(this.f17533a).inflate(this.f17542j, (ViewGroup) frameLayout, false) : null;
        }
        boolean z = view != null;
        if (!z || !t(view)) {
            this.f17535c.setFlags(131072, 131072);
        }
        if (z) {
            FrameLayout frameLayout2 = (FrameLayout) this.f17535c.findViewById(com.zte.extres.R.id.custom);
            frameLayout2.addView(view, new ViewGroup.LayoutParams(-1, -1));
            if (this.f17547o) {
                frameLayout2.setPadding(this.f17543k, this.f17544l, this.f17545m, this.f17546n);
            }
            if (this.f17539g != null) {
                ((LinearLayout.LayoutParams) frameLayout.getLayoutParams()).weight = 0.0f;
            }
        } else {
            frameLayout.setVisibility(8);
        }
        if (f0) {
            View findViewById3 = this.f17539g != null ? this.f17535c.findViewById(com.zte.extres.R.id.titleDivider) : null;
            if (findViewById3 != null) {
                findViewById3.setVisibility(8);
            }
        }
        E(obtainStyledAttributes, linearLayout2, linearLayout, frameLayout, this.f17541i, f0, z, b0);
        obtainStyledAttributes.recycle();
    }

    private static boolean h0(Context context) {
        return false;
    }

    static boolean t(View view) {
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
            if (t(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    private void u(Button button) {
        this.f17541i = this.f17535c.findViewById(com.zte.extres.R.id.buttonPanel);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 17;
        layoutParams.weight = 0.0f;
        layoutParams.width = -2;
        button.setLayoutParams(layoutParams);
        ((LinearLayout) this.f17541i).setGravity(17);
    }

    private void w() {
        View findViewById = this.f17535c.findViewById(com.zte.extres.R.id.buttonPanel);
        this.f17541i = findViewById;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) findViewById.getLayoutParams();
        layoutParams.height = this.f17533a.getResources().getDimensionPixelSize(com.zte.extres.R.dimen.mfvc_alert_dialog_button_panel_height_outscreen);
        this.f17541i.setLayoutParams(layoutParams);
        View view = this.f17541i;
        view.setPadding(view.getPaddingLeft(), this.f17541i.getPaddingTop() - 6, this.f17541i.getPaddingRight() + 6, this.f17541i.getPaddingBottom());
        int dimensionPixelSize = this.f17533a.getResources().getDimensionPixelSize(com.zte.extres.R.dimen.mfvc_alert_dialog_button_panel_padding_min);
        Space space = (Space) this.f17535c.findViewById(com.zte.extres.R.id.leftpadding);
        this.D = space;
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) space.getLayoutParams();
        layoutParams2.width = dimensionPixelSize;
        this.D.setLayoutParams(layoutParams2);
        int dimensionPixelSize2 = this.f17533a.getResources().getDimensionPixelSize(com.zte.extres.R.dimen.mfvc_alert_dialog_button_height);
        int dimensionPixelSize3 = this.f17533a.getResources().getDimensionPixelSize(com.zte.extres.R.dimen.mfvc_alert_dialog_button_width);
        Button button = this.f17551s;
        if (button != null) {
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) button.getLayoutParams();
            layoutParams3.height = dimensionPixelSize2;
            layoutParams3.width = dimensionPixelSize3 - 1;
            layoutParams3.weight = 0.0f;
            this.f17551s.setLayoutParams(layoutParams3);
            this.f17551s.setTextSize(0, this.f17533a.getResources().getDimensionPixelSize(com.zte.extres.R.dimen.mfvc_alert_dialog_button_text_size));
        }
        Button button2 = this.f17548p;
        if (button2 != null) {
            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) button2.getLayoutParams();
            layoutParams4.height = dimensionPixelSize2;
            layoutParams4.width = dimensionPixelSize3 - 1;
            layoutParams4.weight = 0.0f;
            this.f17548p.setLayoutParams(layoutParams4);
            this.f17548p.setTextSize(0, this.f17533a.getResources().getDimensionPixelSize(com.zte.extres.R.dimen.mfvc_alert_dialog_button_text_size));
            View view2 = (View) this.f17548p.getParent();
            if (view2 != null) {
                LinearLayout.LayoutParams layoutParams5 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                layoutParams5.width = dimensionPixelSize3 * 2;
                layoutParams5.weight = 0.0f;
                view2.setLayoutParams(layoutParams5);
            }
        }
        Space space2 = (Space) this.f17535c.findViewById(com.zte.extres.R.id.rightpadding);
        this.E = space2;
        LinearLayout.LayoutParams layoutParams6 = (LinearLayout.LayoutParams) space2.getLayoutParams();
        layoutParams6.width = dimensionPixelSize;
        this.E.setLayoutParams(layoutParams6);
    }

    public void A() {
        try {
            this.f17535c.requestFeature(1);
            this.f17535c.setContentView(D());
            g0();
            d0();
        } catch (Exception e2) {
            Log.w("Z#AlertController", "install Content error, e = ", e2);
        }
    }

    public boolean B(int i2, KeyEvent keyEvent) {
        android.widget.ScrollView scrollView = this.H;
        return scrollView != null && scrollView.executeKeyEvent(keyEvent);
    }

    public boolean C(int i2, KeyEvent keyEvent) {
        android.widget.ScrollView scrollView = this.H;
        return scrollView != null && scrollView.executeKeyEvent(keyEvent);
    }

    public void G(int i2, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message) {
        if (message == null && onClickListener != null) {
            message = this.c0.obtainMessage(i2, onClickListener);
        }
        if (i2 == -3) {
            this.w = charSequence;
            this.x = message;
        } else if (i2 == -2) {
            this.t = charSequence;
            this.u = message;
        } else {
            if (i2 != -1) {
                throw new IllegalArgumentException("Button does not exist");
            }
            this.f17549q = charSequence;
            this.f17550r = message;
        }
    }

    public void H(int i2) {
        this.b0 = i2;
    }

    public void I(View view) {
        this.R = view;
    }

    public void J(int i2) {
        this.J = null;
        this.I = i2;
        ImageView imageView = this.K;
        if (imageView != null) {
            if (i2 != 0) {
                imageView.setImageResource(i2);
            } else {
                imageView.setVisibility(8);
            }
        }
    }

    public void K(Drawable drawable) {
        this.J = drawable;
        this.I = 0;
        ImageView imageView = this.K;
        if (imageView != null) {
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
            } else {
                imageView.setVisibility(8);
            }
        }
    }

    public void L(CharSequence charSequence, View.OnClickListener onClickListener) {
        this.z = charSequence;
        this.A = onClickListener;
    }

    public void M(boolean z) {
        this.S = z;
    }

    public void N() {
        TextViewZTE textViewZTE = this.M;
        if (textViewZTE != null) {
            textViewZTE.setVisibility(0);
        }
        this.N = true;
        Log.w("Z#AlertController", "set Is Show Title out. mIsShowTitle=" + this.N + ", mTitleView=" + this.M);
    }

    public void O(CharSequence charSequence) {
        this.f17537e = charSequence;
        TextView textView = this.P;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void P() {
        Log.d("Z#AlertController", "set Negative Button Background in, mButtonNegative=" + this.f17551s);
        Button button = this.f17551s;
        if (button != null) {
            button.setBackgroundResource(com.zte.extres.R.drawable.btn_default_color_bg_zte);
            if (Utils.f17815b) {
                this.f17551s.setForceDarkAllowed(false);
            }
        }
        ImageView imageView = this.B;
        if (imageView != null) {
            imageView.setVisibility(4);
        }
        ImageView imageView2 = this.C;
        if (imageView2 != null) {
            imageView2.setVisibility(4);
        }
        this.i0 = true;
    }

    public void Q(int i2) {
        Log.d("Z#AlertController", "set Negative Button Text Color in. mButtonNegative=" + this.f17551s + ", color=" + i2);
        Button button = this.f17551s;
        if (button != null) {
            if (i2 != 0) {
                button.setTextColor(i2);
            } else {
                button.setTextColor(this.f17533a.getColor(com.zte.extres.R.color.mfvc_dialog_recommend_text_color));
            }
            if (Utils.f17815b) {
                this.f17551s.setForceDarkAllowed(false);
            }
        }
        this.h0 = true;
        this.j0 = i2;
    }

    public void R() {
        Log.d("Z#AlertController", "set Recommend Button Background in. mButtonPositive=" + this.f17548p + ", mButtonNegative=" + this.f17551s + ", mButtonNegative=" + this.f17551s);
        Button button = this.f17548p;
        if (button != null) {
            button.setTextAppearance(this.f17533a, com.zte.extres.R.style.mfvc_dark_btn_font);
            this.f17548p.setBackgroundResource(com.zte.extres.R.drawable.btn_default_color_bg_zte);
            if (Utils.f17815b) {
                this.f17548p.setForceDarkAllowed(false);
            }
        }
        Button button2 = this.f17551s;
        if (button2 != null) {
            button2.setBackgroundResource(com.zte.extres.R.drawable.btn_default_light_mfv50_negative);
            if (Utils.f17815b) {
                this.f17551s.setForceDarkAllowed(false);
            }
        }
        ImageView imageView = this.B;
        if (imageView != null) {
            imageView.setVisibility(4);
        }
        ImageView imageView2 = this.C;
        if (imageView2 != null) {
            imageView2.setVisibility(4);
        }
        this.f0 = true;
    }

    public void S(int i2) {
        Log.d("Z#AlertController", "set Recommend Button Text Color in ... mButtonPositive = " + this.f17548p);
        Button button = this.f17548p;
        if (button != null) {
            if (i2 != 0) {
                button.setTextColor(i2);
            } else {
                button.setTextColor(this.f17533a.getColor(com.zte.extres.R.color.mfvc_dialog_recommend_text_color));
            }
            if (Utils.f17815b) {
                this.f17548p.setForceDarkAllowed(false);
            }
        }
        this.e0 = true;
        this.g0 = i2;
    }

    public void T() {
        Log.d("Z#AlertController", "set Service Dialog. do nothing.");
    }

    public void U(CharSequence charSequence) {
        this.f17538f = charSequence;
        TextView textView = this.Q;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void V(CharSequence charSequence) {
        this.f17536d = charSequence;
        TextViewZTE textViewZTE = this.M;
        if (textViewZTE != null) {
            textViewZTE.setText(charSequence);
        }
    }

    public void W(int i2) {
        TextViewZTE textViewZTE = this.M;
        if (textViewZTE != null) {
            textViewZTE.setTextColor(i2);
        }
    }

    public void X(int i2) {
        this.f17540h = null;
        this.f17542j = i2;
        this.f17547o = false;
    }

    public void Y(View view) {
        this.f17540h = view;
        this.f17542j = 0;
        this.f17547o = false;
    }

    public void Z(View view, int i2, int i3, int i4, int i5) {
        this.f17540h = view;
        this.f17542j = 0;
        this.f17547o = true;
        this.f17543k = i2;
        this.f17544l = i3;
        this.f17545m = i4;
        this.f17546n = i5;
    }

    public void e0() {
        Window window = this.f17535c;
        if (window == null) {
            Log.d("Z#AlertController", "setup Title in. mWindow=null.");
            return;
        }
        LinearLayout linearLayout = (LinearLayout) window.findViewById(com.zte.extres.R.id.topPanel);
        boolean z = !TextUtils.isEmpty(this.f17536d);
        if (this.N && z) {
            TextViewZTE textViewZTE = (TextViewZTE) this.f17535c.findViewById(com.zte.extres.R.id.alertTitle);
            this.M = textViewZTE;
            if (textViewZTE != null) {
                Log.v("Z#AlertController", "setup Title. set Text and visible.");
                this.M.setText(this.f17536d);
                this.M.setVisibility(0);
                this.M.setPadding(this.K.getPaddingLeft(), this.K.getPaddingTop(), this.K.getPaddingRight(), this.K.getPaddingBottom());
            }
            View findViewById = this.f17535c.findViewById(com.zte.extres.R.id.title_template);
            if (findViewById != null) {
                Log.v("Z#AlertController", "setup Title. set titleTemplate visible.");
                findViewById.setVisibility(0);
            }
        } else {
            Log.w("Z#AlertController", "setup Title warning. hasTextTitle=" + z);
        }
        if (linearLayout != null) {
            linearLayout.setMinimumHeight((int) this.f17533a.getResources().getDimension(com.zte.extres.R.dimen.mfvc_alert_dialog_title_height));
        }
        Log.v("Z#AlertController", "setup Title out. mIsShowTitle=" + this.N + ", mTitle=" + ((Object) this.f17536d));
    }

    public void i0() {
        CharSequence charSequence;
        Log.d("Z#AlertController", "sync Gravity in. mCloseSync=" + this.O);
        if (this.O || (charSequence = this.f17538f) == null || charSequence.isEmpty()) {
            Log.d("Z#AlertController", "sync Gravity do nothing. mSubTitle=" + ((Object) this.f17538f));
            return;
        }
        int textAlignment = this.Q.getTextAlignment();
        int textAlignment2 = this.P.getTextAlignment();
        Log.d("Z#AlertController", "sync Gravity. need to set Multiple lines. isMultipleTitle=" + ((DialogMessage) this.Q).c() + ", isMultipleMessage=" + ((DialogMessage) this.P).c() + ", alignSubtitle=" + textAlignment + ", alignMessage=" + textAlignment2);
        if (textAlignment == textAlignment2) {
            Log.w("Z#AlertController", "sync Gravity. the same gravity and do nothing.");
        } else {
            if (textAlignment != 2) {
                Log.w("Z#AlertController", "sync Gravity. set mSubTitleView to TEXT_ALIGNMENT_TEXT_START.");
                ((DialogMessage) this.Q).setIsMultiplelines(true);
                this.Q.setTextAlignment(2);
                this.Q.setGravity(8388611);
            }
            if (textAlignment2 != 2) {
                Log.w("Z#AlertController", "sync Gravity. set mMessageView to TEXT_ALIGNMENT_TEXT_START.");
                ((DialogMessage) this.P).setIsMultiplelines(true);
                this.P.setTextAlignment(2);
                this.P.setGravity(8388611);
            }
        }
        this.Q.removeOnLayoutChangeListener(this.o0);
        this.P.removeOnLayoutChangeListener(this.p0);
    }

    public void v() {
        this.O = true;
        Log.w("Z#AlertController", "close Sync Gravity out. mCloseSync=" + this.O);
    }

    public Button x(int i2) {
        if (i2 == -3) {
            return this.v;
        }
        if (i2 == -2) {
            return this.f17551s;
        }
        if (i2 != -1) {
            return null;
        }
        return this.f17548p;
    }

    public int y(int i2) {
        TypedValue typedValue = new TypedValue();
        this.f17533a.getTheme().resolveAttribute(i2, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView z() {
        return this.f17539g;
    }
}
