package com.example.mienhv1.survey.ui.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mienhv1.survey.R;

/**
 * Created by MienHV1 on 6/28/2017.
 */

public class DoneAnswerDialogFragment extends BaseDialogFragment {
    public void setDoneAnswerDialogFragment(OnDialogFragmentClickListener mListener) {
        this.mListener = mListener;
    }

    OnDialogFragmentClickListener mListener;

    public interface OnDialogFragmentClickListener {
        void onOkClicked(DoneAnswerDialogFragment dialog);

        void onCancelClicked(DoneAnswerDialogFragment dialog);
    }

    // Create an instance of the Dialog with the input
    public static DoneAnswerDialogFragment newInstance(String title, String message) {
        DoneAnswerDialogFragment frag = new DoneAnswerDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("msg", message);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_test, container, false);
        getDialog().setCanceledOnTouchOutside(true);
        return view;
    }

    // Create a Dialog using default AlertDialog builder , if not inflate custom view in onCreateView
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
//                .setTitle(getArguments().getString("title"))
//                .setMessage(getArguments().getString("message"))
//                .setPositiveButton("OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                // Positive button clicked
//                                mListener.onOkClicked(DoneAnswerDialogFragment.this);
//                            }
//                        }
//                );
//        return builder.create();
//    }
}
