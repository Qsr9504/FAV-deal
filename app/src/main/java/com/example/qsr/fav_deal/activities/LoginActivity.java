package com.example.qsr.fav_deal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qsr.fav_deal.MainActivity;
import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.User;
import com.example.qsr.fav_deal.globle.AppConstants;
import com.example.qsr.fav_deal.utils.MySPUtil;
import com.example.qsr.fav_deal.utils.TextUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.login_text)
    TextView loginText;
    @Bind(R.id.login_register)
    TextView loginRegister;
    @Bind(R.id.login_account)
    EditText loginAccount;
    @Bind(R.id.login_pwd)
    EditText loginPwd;
    @Bind(R.id.login_submit)
    Button loginSubmit;
    @Bind(R.id.ignore)
    TextView ignore;
    @Bind(R.id.login_layout)
    LinearLayout loginLayout;
    @Bind(R.id.t_left)
    ImageView tLeft;
    @Bind(R.id.register_text)
    TextView registerText;
    @Bind(R.id.register_account)
    EditText registerAccount;
    @Bind(R.id.register_pwd)
    EditText registerPwd;
    @Bind(R.id.register_pwd2)
    EditText registerPwd2;
    @Bind(R.id.register_submit)
    Button registerSubmit;
    @Bind(R.id.register_layout)
    LinearLayout registerLayout;
    private Intent intent;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    //登录按钮提交
    @OnClick(R.id.login_submit)
    public void login_submit(View v) {
        user = new User();
        //获取界面上账户和密码信息
        if (TextUtil.isEmpty(loginAccount.getText().toString(), loginPwd.getText().toString())) {
            Toast.makeText(LoginActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //从服务器验证，
            //成功后将其信息保存至本地
            user.setU_id(1);
            user.setU_account(loginAccount.getText().toString());
            user.setU_pwd(loginPwd.getText().toString());
            saveUser(user);
        }
    }
    //注册按钮提交
    @OnClick(R.id.register_submit)
    public void register_submit(View v) {
        //获取界面上账户和密码信息
        if (TextUtil.isEmpty(registerAccount.getText().toString(),
                registerPwd.getText().toString(),
                registerPwd2.getText().toString())){
            Toast.makeText(LoginActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
        }
        else{
            if (!(registerPwd.getText().toString()).equals((registerPwd2.getText().toString()))) {
                Toast.makeText(LoginActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            } else {
                //从服务器验证账号是否已经注册
                //成功后将其信息保存至本地
                //页面跳转至主界面,并销毁当前页面
                user.setU_id(1);
                user.setU_account(registerAccount.getText().toString());
                user.setU_pwd(registerPwd.getText().toString());
                saveUser(user);
            }
        }
    }

    //点击显示出注册界面
    @OnClick(R.id.login_register)
    public void login_register(View v) {
        loginLayout.setVisibility(View.GONE);
    }
    @OnClick(R.id.t_left)
    public void t_left(View v) {
        loginLayout.setVisibility(View.VISIBLE);
    }
    @OnClick(R.id.ignore)
    public void ignore(View v) {
        intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void saveUser(User user){
        //保存当前用户信息
        MySPUtil.save(AppConstants.CONFIG.USER_ID,user.getU_id());//保存当前用户id
        MySPUtil.save(AppConstants.CONFIG.USER_ACCOUNT,user.getU_account());//保存当前用户账户
        MySPUtil.save(AppConstants.CONFIG.USER_PWD,user.getU_pwd());//保存当前用户密码

        //跳转到主界面
        intent = new Intent(LoginActivity.this, SpalshActivity.class);
        startActivity(intent);
        finish();
    }
}
