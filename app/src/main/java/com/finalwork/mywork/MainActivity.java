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
import android.widget.TextView;
import android.widget.Toast;
//登录主页面，将用户数据保存在SharedPreferences，及注册页面相关
public class MainActivity extends AppCompatActivity{

    private TextView tv_register,tv_find_psw;//显示的注册，找回密码
    private Button btn_login;//登录按钮
    private String userName,psw,spPsw;//获取的用户名，密码，加密密码
    private EditText user_name,user_psw;//编辑框
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init() {
        tv_register = findViewById(R.id.tv_register);
        tv_find_psw = findViewById(R.id.tv_find_psw);
        btn_login = findViewById(R.id.btn_log);
        user_name = findViewById(R.id.user_name);
        user_psw = findViewById(R.id.user_password);

        //立即注册
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        //找回密码
        tv_find_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到找回密码界面（此页面暂未创建）
            }
        });

        //登录
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = user_name.getText().toString().trim();
                psw = user_psw.getText().toString().trim();
                String Psw= psw;
                // 定义方法 readPsw为了读取用户名，得到密码
                spPsw = readPsw(userName);
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(MainActivity.this, "请输入昵称", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(MainActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(Psw.equals(spPsw)){
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    //登录成功后关闭此页面进入主页
                    Intent data=new Intent();
                    data.putExtra("isLogin",true);
                    setResult(RESULT_OK,data);
                    //销毁登录界面
                  //  MainActivity.this.finish();

                    //跳转到主界面，登录成功的状态传递到 Main2Activity 中
                    startActivity(new Intent(MainActivity.this, Main2Activity.class));
                    return;
                }else if((spPsw!=null&&!TextUtils.isEmpty(spPsw)&&!Psw.equals(spPsw))){
                    Toast.makeText(MainActivity.this, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(MainActivity.this, "此用户名不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

     //从SharedPreferences中根据用户名读取密码
    private String readPsw(String userName){
        //MODE_PRIVATE表示可以继续写入
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sp.getString(userName , "");
    }

    //注册成功的数据返回至此
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            //是获取注册界面回传过来的用户名
            String userName=data.getStringExtra("userName");
            if(!TextUtils.isEmpty(userName)){
                user_name.setText(userName);
                //setSelection()方法来设置光标位置
                user_name.setSelection(userName.length());
            }
        }
    }

}