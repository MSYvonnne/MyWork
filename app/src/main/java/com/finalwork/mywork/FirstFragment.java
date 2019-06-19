package com.finalwork.mywork;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

  /*  @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);
        LinearLayout linear = getActivity().findViewById(R.id.first_frag);
        ScrollView scroller = new ScrollView(getActivity());
        TextView text = new TextView(getActivity());

        scroller.addView(text);//将文本对象添加到滚动视图
        linear.addView(scroller);//将滚动视图添加到线性布局管理器
    } */
}
