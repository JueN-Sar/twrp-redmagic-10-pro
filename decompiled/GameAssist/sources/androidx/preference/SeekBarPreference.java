package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.preference.Preference;

/* loaded from: classes.dex */
public class SeekBarPreference extends Preference {
    int S;
    int T;
    private int U;
    private int V;
    boolean W;
    SeekBar X;
    private TextView Y;
    boolean Z;
    private boolean a0;
    private SeekBar.OnSeekBarChangeListener b0;
    private View.OnKeyListener c0;

    public SeekBarPreference(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.b0 = new SeekBar.OnSeekBarChangeListener() { // from class: androidx.preference.SeekBarPreference.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i4, boolean z) {
                if (z) {
                    SeekBarPreference seekBarPreference = SeekBarPreference.this;
                    if (seekBarPreference.W) {
                        return;
                    }
                    seekBarPreference.D0(seekBar);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                SeekBarPreference.this.W = true;
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                SeekBarPreference.this.W = false;
                int progress = seekBar.getProgress();
                SeekBarPreference seekBarPreference = SeekBarPreference.this;
                if (progress + seekBarPreference.T != seekBarPreference.S) {
                    seekBarPreference.D0(seekBar);
                }
            }
        };
        this.c0 = new View.OnKeyListener() { // from class: androidx.preference.SeekBarPreference.2
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i4, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 0) {
                    return false;
                }
                SeekBarPreference seekBarPreference = SeekBarPreference.this;
                if ((!seekBarPreference.Z && (i4 == 21 || i4 == 22)) || i4 == 23 || i4 == 66) {
                    return false;
                }
                SeekBar seekBar = seekBarPreference.X;
                if (seekBar != null) {
                    return seekBar.onKeyDown(i4, keyEvent);
                }
                Log.e("SeekBarPreference", "SeekBar view is null and hence cannot be adjusted.");
                return false;
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SeekBarPreference, i2, i3);
        this.T = obtainStyledAttributes.getInt(R.styleable.SeekBarPreference_min, 0);
        A0(obtainStyledAttributes.getInt(R.styleable.SeekBarPreference_android_max, 100));
        B0(obtainStyledAttributes.getInt(R.styleable.SeekBarPreference_seekBarIncrement, 0));
        this.Z = obtainStyledAttributes.getBoolean(R.styleable.SeekBarPreference_adjustable, true);
        this.a0 = obtainStyledAttributes.getBoolean(R.styleable.SeekBarPreference_showSeekBarValue, true);
        obtainStyledAttributes.recycle();
    }

    private void C0(int i2, boolean z) {
        int i3 = this.T;
        if (i2 < i3) {
            i2 = i3;
        }
        int i4 = this.U;
        if (i2 > i4) {
            i2 = i4;
        }
        if (i2 != this.S) {
            this.S = i2;
            TextView textView = this.Y;
            if (textView != null) {
                textView.setText(String.valueOf(i2));
            }
            d0(i2);
            if (z) {
                N();
            }
        }
    }

    public final void A0(int i2) {
        int i3 = this.T;
        if (i2 < i3) {
            i2 = i3;
        }
        if (i2 != this.U) {
            this.U = i2;
            N();
        }
    }

    public final void B0(int i2) {
        if (i2 != this.V) {
            this.V = Math.min(this.U - this.T, Math.abs(i2));
            N();
        }
    }

    void D0(SeekBar seekBar) {
        int progress = this.T + seekBar.getProgress();
        if (progress != this.S) {
            if (c(Integer.valueOf(progress))) {
                C0(progress, false);
            } else {
                seekBar.setProgress(this.S - this.T);
            }
        }
    }

    @Override // androidx.preference.Preference
    public void R(PreferenceViewHolder preferenceViewHolder) {
        super.R(preferenceViewHolder);
        preferenceViewHolder.f5252a.setOnKeyListener(this.c0);
        this.X = (SeekBar) preferenceViewHolder.N(R.id.seekbar);
        TextView textView = (TextView) preferenceViewHolder.N(R.id.seekbar_value);
        this.Y = textView;
        if (this.a0) {
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
            this.Y = null;
        }
        SeekBar seekBar = this.X;
        if (seekBar == null) {
            Log.e("SeekBarPreference", "SeekBar view is null in onBindViewHolder.");
            return;
        }
        seekBar.setOnSeekBarChangeListener(this.b0);
        this.X.setMax(this.U - this.T);
        int i2 = this.V;
        if (i2 != 0) {
            this.X.setKeyProgressIncrement(i2);
        } else {
            this.V = this.X.getKeyProgressIncrement();
        }
        this.X.setProgress(this.S - this.T);
        TextView textView2 = this.Y;
        if (textView2 != null) {
            textView2.setText(String.valueOf(this.S));
        }
        this.X.setEnabled(J());
    }

    @Override // androidx.preference.Preference
    protected Object V(TypedArray typedArray, int i2) {
        return Integer.valueOf(typedArray.getInt(i2, 0));
    }

    @Override // androidx.preference.Preference
    protected void Y(Parcelable parcelable) {
        if (!parcelable.getClass().equals(SavedState.class)) {
            super.Y(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.Y(savedState.getSuperState());
        this.S = savedState.f4762c;
        this.T = savedState.f4763h;
        this.U = savedState.f4764i;
        N();
    }

    @Override // androidx.preference.Preference
    protected Parcelable Z() {
        Parcelable Z = super.Z();
        if (K()) {
            return Z;
        }
        SavedState savedState = new SavedState(Z);
        savedState.f4762c = this.S;
        savedState.f4763h = this.T;
        savedState.f4764i = this.U;
        return savedState;
    }

    private static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: androidx.preference.SeekBarPreference.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: c, reason: collision with root package name */
        int f4762c;

        /* renamed from: h, reason: collision with root package name */
        int f4763h;

        /* renamed from: i, reason: collision with root package name */
        int f4764i;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f4762c = parcel.readInt();
            this.f4763h = parcel.readInt();
            this.f4764i = parcel.readInt();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f4762c);
            parcel.writeInt(this.f4763h);
            parcel.writeInt(this.f4764i);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.seekBarPreferenceStyle);
    }
}
