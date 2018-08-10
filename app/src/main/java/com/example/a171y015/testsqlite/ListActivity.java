package com.example.a171y015.testsqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ListActivity extends android.app.ListActivity {


    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        if(savedInstanceState == null){
            adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
            //listView = (ListView)findViewById(R.id.sample_list);
            //textday = (TextView)findViewById(R.id.day);
            //textprice = (TextView)findViewById(R.id.price);
            //textname = (TextView)findViewById(R.id.name);
        }

        //ListAdapterセット
        setListAdapter(adapter);

/*
        //Buttonオブジェクト取得
       Button btn=(Button)findViewById(R.id.btn);

        //クリックイベントの通知先指定
        btn.setOnClickListener(new OnClickListener() {
            //クリックイベント
            @Override
            public void onClick(View v) {
                //要素追加
                addStringData();
            }
        });
*/
    }

    /*
    //要素追加処理
    private void addStringData(){

        //EditTextオブジェクト取得
        EditText edit=(EditText)findViewById(R.id.edit_text);


        //EditText(テキスト)を取得し、アダプタに追加
        adapter.add(edit.getText().toString());
    }
    */

    static class SampleListItemClickListener implements  ListView.OnItemClickListener{

        private final TextView textView;

        SampleListItemClickListener(TextView textname){
            this.textView = textname;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            ListView listView = (ListView) parent;
            String item = (String) listView.getItemAtPosition(position);
            textView.setText(item);
        }
    }

    long id;
    private String name;
    private int price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price=price;
    }

}
