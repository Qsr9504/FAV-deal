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

import com.example.qsr.fav_deal.bmobUtil.MesEventForBmob;
import com.example.qsr.fav_deal.bmobUtil.UserTools;
import com.example.qsr.fav_deal.MainActivity;
import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.User;
import com.example.qsr.fav_deal.globle.AppConstants;
import com.example.qsr.fav_deal.utils.LogUtil;
import com.example.qsr.fav_deal.utils.MySPUtil;
import com.example.qsr.fav_deal.utils.TextUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

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
    private UserTools userTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        EventBus.getDefault().register(this);
        ButterKnife.bind(LoginActivity.this);
        //实例化与bmob后端云的用户操作工具类
        userTools = UserTools.getInstance(LoginActivity.this);
    }

    //登录按钮提交
    @OnClick(R.id.login_submit)
    public void login_submit(View v) {
        user = new User();
        //获取界面上账户和密码信息
        if (TextUtil.isEmpty(loginAccount.getText().toString(), loginPwd.getText().toString())) {
            Toast.makeText(LoginActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            user.setU_account(loginAccount.getText().toString());
            user.setU_pwd(loginPwd.getText().toString());
            userTools.loginUser(user);
        }
    }


    //注册按钮提交
    @OnClick(R.id.register_submit)
    public void register_submit(View v) {
        //获取界面上账户和密码信息
        if (TextUtil.isEmpty(registerAccount.getText().toString(),
                registerPwd.getText().toString(),
                registerPwd2.getText().toString())) {
            Toast.makeText(LoginActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            if (!(registerPwd.getText().toString()).equals((registerPwd2.getText().toString()))) {
                Toast.makeText(LoginActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            } else {
                user = new User();

                user.setU_account(registerAccount.getText().toString());
                user.setU_pwd(registerPwd.getText().toString());
                //从服务器验证账号是否已经注册
                userTools.registerUser(user);
            }
        }
    }

    //登录注册的返回结果
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void creatUser(MesEventForBmob eventForBmob) {
        int code = eventForBmob.getStateCode();
        if (UserTools.REGISTER_SUCCESS == code) {
            //注册成功
            saveUser();
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        }else if(UserTools.REGISTER_FAIL == code){
            Toast.makeText(this, "注册失败" + eventForBmob.getString(), Toast.LENGTH_SHORT).show();
        }else if(UserTools.LOGIN_SUCCESS == code){
            //登录成功
            saveUser();
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        }else if(UserTools.LOGIN_FAIL == code){
            Toast.makeText(this, "登录失败" + eventForBmob.getString(), Toast.LENGTH_SHORT).show();
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

    /**
     * 成功后将其信息保存至本地
     * 页面跳转至主界面,并销毁当前页面
     */
    public void saveUser() {
        //保存当前用户信息
        MySPUtil.save(AppConstants.CONFIG.USER_ID, BmobUser.getCurrentUser(this).getObjectId());//保存当前用户id
        MySPUtil.save(AppConstants.CONFIG.USER_ACCOUNT, BmobUser.getCurrentUser(this).getUsername());//保存当前用户账户
        MySPUtil.save(AppConstants.CONFIG.OPEN_UPDATE,true);//默认开启版本检测
        //跳转到登录注册界面
        intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
