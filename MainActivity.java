package com.example.licky.scolldeletebutton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.licky.scolldeletebutton.SlideCutListView.RemoveDirection;
import com.example.licky.scolldeletebutton.SlideCutListView.RemoveListener;

public class MainActivity extends Activity implements RemoveListener{
    private SlideCutListView slideCutListView ;
    ListView opinion_Listview;

    private List<String> dataSourceList = new ArrayList<String>();
    private List<String> dataSourceList_t = new ArrayList<String>();
    private List<String> dataSourceList_c= new ArrayList<String>();

     String[] locations = {"台北火車站", "台南火車站", "台中火車站", "台東火車站", "永康火車站",
            "新市火車站", "善化火車站", "大橋火車站", "新營火車站", "柳營火車站"};
     String[] time = {"約3分鐘", "約2分鐘", "約5分鐘", "約1分鐘", "約1分鐘", "約2分鐘", "約5分鐘", "約4分鐘", "約4分鐘", "約3分鐘"};
     String[] comments = new String[]{"有行李", "有嬰兒", "無", "有輪椅", "開後備箱",
            "無", "有行李", "需要開後備箱", "需要六人車", "有嬰兒"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mAddItemButton = (Button) findViewById(R.id.add_item_button);
        mAddItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        for(int i=0; i<locations.length; i++){
            if(locations[i]!=null) {
                dataSourceList.add(locations[i]);
                dataSourceList_t.add(time[i]);
                dataSourceList_c.add(comments[i]);
            }
        }
        init();
    }
    protected void init(){
        String[] trans_location=new String[dataSourceList.size()];
        String[] trans_time=new String[dataSourceList.size()];
        String[] trans_comments=new String[dataSourceList.size()];
        for(int m=0; m<dataSourceList.size(); m++) {
            trans_location[m]=dataSourceList.get(m);
            trans_time[m]=dataSourceList_t.get(m);
            trans_comments[m]=dataSourceList_c.get(m);
        }
            // setContentView(R.layout.activity_main);

        opinion_Listview = (ListView)findViewById(R.id.slideCutListView);

        //建立自訂的Adapter
        GrabOrderAdapter adapter = new GrabOrderAdapter(getApplicationContext() ,trans_location,trans_time,trans_comments);

        //設定ListView 的資源來源

        opinion_Listview.setAdapter(adapter);

        slideCutListView = (SlideCutListView) findViewById(R.id.slideCutListView);
        slideCutListView.setRemoveListener(this);
        slideCutListView.setAdapter(adapter);
        slideCutListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, locations[position], Toast.LENGTH_SHORT).show();
            }
        });
    }


    //滑动删除之后的回调方法
    @Override
    public void removeItem(RemoveDirection direction, int position) {

        dataSourceList.remove(position);
        dataSourceList_t.remove(position);
        dataSourceList_c.remove(position);

        switch (direction) {
            case RIGHT:
                Toast.makeText(this, "向右删除  "+ position, Toast.LENGTH_SHORT).show();
                break;
            case LEFT:
                Toast.makeText(this, "向左删除  "+ position, Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
        init();
    }


}