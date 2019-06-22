package com.finalwork.mywork;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
//3 fragment，并完成home页面获取句子功能
public class Main2Activity extends AppCompatActivity implements Runnable {

    private Fragment mFragments[];
    private RadioGroup radioGroup;
    private FragmentManager fragmentManager; //管理切换的管理器
    private FragmentTransaction fragmentTransaction; //事务管理
    private RadioButton rbtClas,rbtHome,rbtMyNote;

    Handler handler;
    String senten;
    TextView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        show = findViewById(R.id.show);

        mFragments = new Fragment[3];
        fragmentManager = getSupportFragmentManager();
        mFragments[0] = fragmentManager.findFragmentById(R.id.frag_classify);
        mFragments[1] = fragmentManager.findFragmentById(R.id.frag_home);
        mFragments[2] = fragmentManager.findFragmentById(R.id.frag_myNote);
        fragmentTransaction =
                fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
        fragmentTransaction.show(mFragments[1]).commit();

        rbtClas = findViewById(R.id.radio_classify);
        rbtHome = findViewById(R.id.radio_home);
        rbtMyNote = findViewById(R.id.radio_myNote);

        rbtHome.setBackgroundResource(R.drawable.shape2);

        radioGroup = findViewById(R.id.bottom_Group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fragmentTransaction =
                        fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);

                rbtClas.setBackgroundResource(R.drawable.shape);
                rbtHome.setBackgroundResource(R.drawable.shape);
                rbtMyNote.setBackgroundResource(R.drawable.shape);

                switch (checkedId){
                    case R.id.radio_classify:
                        fragmentTransaction.show(mFragments[0]).commit();
                        rbtClas.setBackgroundResource(R.drawable.shape2);
                        break;
                    case R.id.radio_home:
                        fragmentTransaction.show(mFragments[1]).commit();
                        rbtHome.setBackgroundResource(R.drawable.shape2);
                        break;
                    case R.id.radio_myNote:
                        fragmentTransaction.show(mFragments[2]).commit();
                        rbtMyNote.setBackgroundResource(R.drawable.shape2);
                        break;
                    default:
                        break;
                }
            }
        });

        //获取保存句子
        SharedPreferences sharedPreferences = getSharedPreferences("my_stn",Activity.MODE_PRIVATE);
        senten = sharedPreferences.getString("stn","");

        Thread t = new Thread(this);
        t.start();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                    if (msg.what==5){
                        Bundle bdl = (Bundle) msg.obj;
                        senten = bdl.getString("sentence");
                        Log.i("home页面","带回句子"+senten);
                        //保存今日句子
                        SharedPreferences sharedPreferences = getSharedPreferences("mystn",Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Stodaystn",senten);
                        editor.apply();
                        Log.i("home页面","已保存句子"+senten);
                        show.setText(senten);
                        Toast.makeText(Main2Activity.this,"句子已更新",Toast.LENGTH_LONG).show();
                    }
                super.handleMessage(msg);
            }
        };
    }

    @Override
    public void run() {
        Bundle bundle = getFromNET();
        //获取msg对象，用于返回主线程
        Message msg = handler.obtainMessage(5);
        msg.obj = bundle;
        handler.sendMessage(msg);
    }

    private Bundle getFromNET() {
        Bundle bundle = new Bundle();
        Document doc = null;
        try{
            java.util.Random r = new java.util.Random();
            int i = r.nextInt(154)+3;
            doc = Jsoup.connect("http://www.sennate.com/jdyl/106278/").get();
            Log.i("home页面","run:"+doc.title());
            Elements ps = doc.getElementsByTag("p");
            Element p=ps.get(i);
            String pt = p.text();
            bundle.putString("sentence",pt);
            Log.i("home页面","获取到新句子"+pt);
        }catch (IOException e){
            e.printStackTrace();
        }
        return bundle;
    }
}
