package com.finalwork.mywork;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Main2Activity extends AppCompatActivity {

    private Fragment mFragments[];
    private RadioGroup radioGroup;
    private FragmentManager fragmentManager; //管理切换的管理器
    private FragmentTransaction fragmentTransaction; //事务管理
    private RadioButton rbtClas,rbtHome,rbtMyNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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


    }
}
