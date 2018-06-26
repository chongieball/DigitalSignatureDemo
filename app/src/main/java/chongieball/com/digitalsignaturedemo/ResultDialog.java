package chongieball.com.digitalsignaturedemo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Created by chongieball on 26/06/18.
 * Name : Muhammad Iqbal
 * Email : chongg1243@gmail.com
 */

public class ResultDialog extends DialogFragment {
    private static final String TAG = ResultDialog.class.getSimpleName();

    private Bitmap signResult;
    private ImageView ivResult;
    private Button btnSave;
    private Button btnCancel;

    OnResultDialogListener onResultDialogListener;

    public interface OnResultDialogListener {
        void onBackToSignActivity();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setRetainInstance(true);
        onResultDialogListener = (OnResultDialogListener) getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();

        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        signResult = getArguments().getParcelable("result_bitmap");
        ivResult.setImageBitmap(signResult);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_dialog, container, false);

        ivResult = view.findViewById(R.id.iv_sign_result);
        btnCancel = view.findViewById(R.id.btn_cancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                onResultDialogListener.onBackToSignActivity();
            }
        });

        return view;
    }
}
