package com.finalwork.mywork;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListActivity extends Activity implements OnScrollListener,
        OnItemClickListener, OnItemLongClickListener {

    private Context mContext;
    private ListView listview;
    private SimpleAdapter simp_adapter;
    private List<Map<String, Object>> dataList;
    private Button addNote;
    private TextView tv_content;
    private NotesDB DB;
    private SQLiteDatabase dbread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        tv_content = findViewById(R.id.tv_content);
        listview = findViewById(R.id.listView);
        dataList = new ArrayList<Map<String, Object>>();
        addNote = findViewById(R.id.btn_mine);
        mContext = this;

        addNote.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                NoteActivity.ENTER_STATE = 0;
                Intent intent = new Intent(mContext,NoteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("info", "");
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }
        });

        DB = new NotesDB(mContext);
        dbread = DB.getReadableDatabase();
        RefreshNotesList();
        listview.setOnItemClickListener(this);
        listview.setOnItemLongClickListener(this);
        listview.setOnScrollListener(this);
    }

    public void RefreshNotesList() {
        int size = dataList.size();
        if (size > 0) {
            dataList.removeAll(dataList);
            simp_adapter.notifyDataSetChanged();
            listview.setAdapter(simp_adapter);
        }
        simp_adapter = new SimpleAdapter(mContext, getData(), R.layout.list_note,
                new String[]{"tv_content","tv_date"},new int[]{R.id.tv_content, R.id.tv_date });
        listview.setAdapter(simp_adapter);

    }

    private List<Map<String, Object>> getData() {
        Cursor cursor = dbread.query("note", null, "content!=\"\"",
                null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("content"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("tv_content", name);
            map.put("tv_date", date);
            dataList.add(map);
        }
        cursor.close();
        return dataList;
    }

    @Override
    public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {

    }

    // 滑动listview监听事件
    @Override
    public void onScrollStateChanged(AbsListView arg0, int arg1) {
        switch (arg1) {
            case SCROLL_STATE_FLING:
                Log.i("mine", "用户在手指离开屏幕之前，由于用力的滑了一下，视图能依靠惯性继续滑动");
            case SCROLL_STATE_IDLE:
                Log.i("mine", "视图已经停止滑动");
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("mine", "手指没有离开屏幕，试图正在滑动");
        }
    }

    // 点击listview中某一项的监听事件
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        NoteActivity.ENTER_STATE = 1;
        String content = listview.getItemAtPosition(arg2) + "";
        String content1 = content.substring(content.indexOf("=") + 1,content.indexOf(","));
        Log.d("CONTENT", content1);
        Cursor c = dbread.query("note", null,
                "content=" + "'" + content1 + "'", null, null, null, null);
        while (c.moveToNext()) {
            String No = c.getString(c.getColumnIndex("_id"));
            Log.d("TEXT", No);
            Intent myIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("info", content1);
            NoteActivity.id = Integer.parseInt(No);
            myIntent.putExtras(bundle);
            myIntent.setClass(mContext, NoteActivity.class);
            startActivityForResult(myIntent, 1);
        }
    }

    @Override
    // 接受上一个页面返回的数据，并刷新页面
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            RefreshNotesList();
        }
    }

    // 点击listview中某一项长时间的点击事件
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        Log.i("ListActivity", "onItemLongClick: 长按position = " + position);

        //构造对话框，进行确认操作
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除当前影评").
                setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("ListActivity", "onClick: 对话框事件处理");
                        //删除操作
                        dataList.remove(position); //删除数据
                        simp_adapter.notifyDataSetChanged(); //刷新
                    }
                }).setNegativeButton("否",null);

        builder.create().show();

        Log.i("ListActivity", "onItemLongClick:size = " + dataList.size());
        return true;
    }

}
