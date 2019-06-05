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

 /*           //测试数据库
            RateItem item1 = new RateItem("aaa","123");
            RateManager manager = new RateManager(this);
            manager.add(item1);
            manager.add(new RateItem("bbb","789"));
            Log.i(TAG, "onOptionsItemSelected: 写入数据完毕");

            //查询所有数据
            List<RateItem> testList = manager.listAll();
            for(RateItem i : testList){
                Log.i(TAG, "onOptionsItemSelected: 取出数据id="+i.getId()+",name="+i.getCurName()+",rate="+i.getCurRate());
            } */
        }
        return super.onOptionsItemSelected(item);
    }
}
