package studio.coon.practice_10;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomDialogFragment extends DialogFragment{
    @BindView(R.id.btn_cancel) ImageView btnCancel;
    @BindView(R.id.btn_ok) Button btnOK;

    public CustomDialogFragment() {
        // необходим: пустой паблик конструктор
    }

    public static CustomDialogFragment newInstance(Bundle bundle) {
        CustomDialogFragment fragment = new CustomDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        Log.e("dialog", "onCreate");
        // убрать toolbar у диалога - v.1
        //this.setStyle(STYLE_NO_TITLE, 0);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("dialog", "onCreateView");
        View view = inflater.inflate(R.layout.dialog_fragment_custom, container, false);
        ButterKnife.bind(this, view);
        // убрать toolbar у диалога - v.2 (также используется для yy_dialog_round_orner)
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        return view;
    }

    // нажатие Cancel (крест справа вверху)
    @OnClick(R.id.btn_cancel)
    public void onClickCancel() {
        dismiss();
    }

    // нажатие на ОК
    @OnClick(R.id.btn_ok)
    public void onClickOK() {
        if (SharedPrefsHelper.contains(getActivity(), "Login")){
            SharedPrefsHelper.put(getActivity(), "Welcome", "done");
        }
        dismiss();
    }



}
