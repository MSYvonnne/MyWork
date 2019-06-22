package com.finalwork.mywork;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//注册，完成判断功能，成功后返回登录页面
public class RegisterActivity extends AppCompatActivity {
    private Button btn_register;//注册按钮
    private EditText user_name,user_psw,user_psw_again; //用户名，密码，再次输入的密码
    private String userName,psw,pswAgain;//用户名，密码，再次输入的密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init() {
        btn_register=findViewById(R.id.btn_register);
        user_name=findViewById(R.id.user_name);
        user_psw=findViewById(R.id.user_psw);
        user_psw_again=findViewById(R.id.user_psw_again);

        //注册按钮
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString(); //自定义了一个方法
                //判断输入框内容
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(RegisterActivity.this, "请输入昵称", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(pswAgain)){
                    Toast.makeText(RegisterActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!psw.equals(pswAgain)){
                    Toast.makeText(RegisterActivity.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                    return;
                //从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名

                }else if(isExistUserName(userName)){
                    Toast.makeText(RegisterActivity.this, "此账户名已经存在", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    //把账号、密码和账号标识保存到sp里面

                    saveRegisterInfo(userName, psw);

                    // 返回值到MainActivity显示，不必重复输入用户名
                    Intent data = new Intent();
                    data.putExtra("userName", userName);
                    setResult(RESULT_OK, data);

                    RegisterActivity.this.finish();
                }
            }
        });
    }

    private void getEditString(){
        userName = user_name.getText().toString().trim();
        psw = user_psw.getText().toString().trim();
        pswAgain = user_psw_again.getText().toString().trim();
    }

    //从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否已经存在此用户名
    private boolean isExistUserName(String userName){
        boolean has_userName=false;
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);//获取密码
        String spPsw=sp.getString(userName, "");//传入用户名获取密码
        //如果密码不为空则确实保存过这个用户名
        if(!TextUtils.isEmpty(spPsw)) {
            has_userName=true;
        }
        return has_userName;
    }

    //保存账号和密码到SharedPreferences中
    private void saveRegisterInfo(String userName,String psw){
        String Psw = psw;
        //loginInfo表示文件名
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        //以用户名为key，密码为value保存在SharedPreferences中，键值对
        editor.putString(userName, Psw);
        editor.commit();
    }
}