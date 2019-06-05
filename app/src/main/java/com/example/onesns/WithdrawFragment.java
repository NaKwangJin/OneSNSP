package com.example.onesns;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class WithdrawFragment extends Fragment {

    private Button widBtn;
    private EditText widID;
    private EditText widPW;

    public WithdrawFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initComponents(){
        this.widBtn = getView().findViewById(R.id.WithdrawBtn);
        this.widID = getView().findViewById(R.id.WithdrawIDBox);
        this.widPW = getView().findViewById(R.id.WithdrawPWBox);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_withdraw, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.initComponents();

        this.widBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RESTManager restmng = new RESTManager(requireContext());
                restmng.setURL("http://fght7100.dothome.co.kr/profile.php");
                restmng.setMethod("GET");
                restmng.putArgument("mode","withdraw");
                restmng.putArgument("id",EncryptionEncoder.encryptBase64(widID.getText().toString()));
                restmng.putArgument("pw",EncryptionEncoder.encryptMD5(widPW.getText().toString()));
                restmng.execute();

                Intent intent = new Intent(requireContext(),LoginActivity.class);
                requireContext().startActivity(intent);
                getActivity().finish();
            }
        });
    }
}
