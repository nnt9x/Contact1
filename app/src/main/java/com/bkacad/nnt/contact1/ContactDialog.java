package com.bkacad.nnt.contact1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

public abstract class ContactDialog extends Dialog {

    public ContactDialog(@NonNull Context context) {
        super(context);
    }

    private Button btnSave, btnCancel;
    private EditText edtName, edtPhone;


    private void initUI() {
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
    }

    public abstract void sendData(String contact);

    @Override
    public void show() {
        super.show();
        // Reset dữ liệu
        edtPhone.setText("");
        edtName.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_contact);
        setCancelable(false);
        initUI();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu ở edt -> gửi mainActivity
                String name = edtName.getText().toString();
                if(name.isEmpty()){
                    edtName.setError("Hãy nhập họ tên");
                    return;
                }
                String phone = edtPhone.getText().toString();
                if(phone.isEmpty()){
                    edtPhone.setError("Hãy nhập sdt");
                    return;
                }
                String contact = name + " - " + phone;
                sendData(contact);
                dismiss();
            }
        });


    }
}
