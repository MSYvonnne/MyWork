package com.finalwork.mywork;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ViewPager viewPager = findViewById(R.id.viewPager);
        com.finalwork.mywork.MyPageAdapter pageAdapter = new com.finalwork.mywork.MyPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);

        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager); //将ViewPager和TabLayout连接起来
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加入菜单
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //点击菜单
        if(item.getItemId()==R.id.menu_set){
            //打开列表窗口
            Intent list= new Intent(this,MyNoteActivity.class);
            startActivity(list);

        }
        return super.onOptionsItemSelected(item);
    }
}
