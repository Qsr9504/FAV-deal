package com.example.qsr.fav_deal.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.EditText;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.Address;

/**************************************
 * FileName : com.example.qsr.fav_deal.ui
 * Author : qsr
 * Time : 2016/8/2 22:29
 * Description : 自定义dialog
 **************************************/
public class AddressDialog extends Dialog {
    private EditText name;
    private EditText phone;
    private EditText detail;
    private Button submit;
    private Button cancle;

    public void setOnDialogListener(OnDialogListener onDialogListener) {
        this.onDialogListener = onDialogListener;
    }
    public void setData(Address address) {
        if(address != null) {
            name.setText(address.getA_receiver());
            phone.setText(address.getA_phone());
            detail.setText(address.getA_detail());
        }
    }
    public Address getData() {
        Address address = new Address();
        address.setA_receiver(name.getText().toString());
        address.setA_phone(phone.getText().toString());
        address.setA_detail(detail.getText().toString());
        return address;
    }

    private OnDialogListener onDialogListener;

    public AddressDialog(Context context) {
        super(context);
        initView(context);
    }

    public AddressDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    protected AddressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.address_dialog,null);
        setContentView(view);
        setTitle("地址管理");
        setCanceledOnTouchOutside(false);//设置对话框点击空白部分不消失
        name = (EditText) view.findViewById(R.id.add_name);
        phone = (EditText) view.findViewById(R.id.add_phone);
        detail = (EditText) view.findViewById(R.id.add_detail);
        submit = (Button) view.findViewById(R.id.submit);
        cancle = (Button) view.findViewById(R.id.cancle);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogListener.onSubmit();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogListener.onCancle();
                dismiss();
            }
        });
    }
}
