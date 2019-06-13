package com.finalwork.mywork;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    private static final String TAG ="HomeFragment" ;
    String todayStn;
    Handler handler;
  //  @RequiresApi(api = Build.VERSION_CODES.N)
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what==5){
                    Bundle bdl = (Bundle) msg.obj;
                    todayStn = bdl.getString("sentence");
                    //显示句子

                    Toast.makeText(HomeFragment.this,"更新",Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }
        };
        //开启子线程
        Thread t = new Thread(this);
        t.start();
    }
    //爬取句子
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
            int i = r.nextInt(70)+1;
            doc = Jsoup.connect("http://www.buhuiwan.com/juzi/4508.html").get();
            Log.i("第一个页面","run:"+doc.title());
            Elements ps = doc.getElementsByTag("p");
            Element p=ps.get(i);
            String pt = p.text();
            bundle.putString("sentence",pt);
            Log.i("第一个页面","获取到今日句子"+pt);
        }catch (IOException e){
            e.printStackTrace();
        }
        return bundle;
    } */
}
