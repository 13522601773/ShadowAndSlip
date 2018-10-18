package com.shadowandslip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.rv);
        GalleryLayoutManager  mLayoutManager = new GalleryLayoutManager(GalleryLayoutManager.HORIZONTAL);
        mLayoutManager.setItemTransformer(new ScaleTransformer());
        mLayoutManager.attach(mRecyclerView, 0);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
        mRecyclerView.setItemViewCacheSize(5);
        mRecyclerView.setDrawingCacheEnabled(true);
        if (mRecyclerView.getRecycledViewPool() != null) {
            mRecyclerView.getRecycledViewPool().setMaxRecycledViews(0, 10);
        }
        ArrayList<TestBean> testBeans = new ArrayList<>();
        for (int i = 0; i <6 ; i++) {
            TestBean testBean = new TestBean();
            testBean.setI(i);
            testBeans.add(testBean);
        }
        GameListAdapter  mGameListAdapter = new GameListAdapter(testBeans);
        mRecyclerView.setAdapter(mGameListAdapter);
    }
}
