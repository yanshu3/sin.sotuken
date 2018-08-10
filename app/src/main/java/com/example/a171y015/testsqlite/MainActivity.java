package com.example.a171y015.testsqlite;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText deleteText, updateText;
    private TextView textView;
    final Intent intent = new Intent();
    final Intent intentlist = new Intent();
    int rqCode = 101;   //遷移先から返却されてくる際の識別コード
    String strname;
    int strprice;
    int frg;

    // Insertボタンのclickリスナー
    private View.OnClickListener buttonInsert_ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buttonInsert_Click(v);
        }
    };
    //Updateボタンのclickリスナー
    private View.OnClickListener buttonUpdate_ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            frg = 2;
            startActivityForResult(intent, rqCode);
           // buttonUpdate_Click(v);
        }
    };
    //Deleteボタンのclickリスナー
    private View.OnClickListener buttonDelete_ClickLintener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buttonDelete_Click(v);
        }
    };
    //Queryボタンのclickリスナー
    private View.OnClickListener buttonQuery_OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buttonQuery_OnClick(v);
        }
    };

    //warpボタンのclickのリスナー
    private  View.OnClickListener buttonWarp_ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            frg = 1;
            startActivityForResult(intent, rqCode);
          //  buttonWarp_Onclick(v);
        }
    };
    //Listボタンのclickリスナー
    private  View.OnClickListener buttonList_ClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            buttonList_OnClick(v);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ボタンにClickリスナーを設定する
        Button buttonInsert = (Button) this.findViewById(R.id.buttonInsert);
        buttonInsert.setOnClickListener(buttonInsert_ClickListener);
        Button buttonUpdate = (Button) this.findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(buttonUpdate_ClickListener);
        Button buttonDelete = (Button) this.findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(buttonDelete_ClickLintener);
        Button btnQuery = (Button) this.findViewById(R.id.buttonQuery);
        btnQuery.setOnClickListener(buttonQuery_OnClickListener);
        intent.setClassName("com.example.a171y015.testsqlite","com.example.a171y015.testsqlite.SubActivity");
        intentlist.setClassName("com.example.a171y015.testsqlite","com.example.a171y015.testsqlite.ListActivity");
        Button buttonwarp = (Button)this.findViewById(R.id.buttonwarp);
        buttonwarp.setOnClickListener(buttonWarp_ClickListener);
        Button buttonlist = (Button)this.findViewById(R.id.buttonList);
        buttonlist.setOnClickListener(buttonList_ClickListener);

        //edittextの定義
        updateText = (EditText)this.findViewById(R.id.upd_num);
        updateText.setInputType(InputType.TYPE_CLASS_NUMBER);
        deleteText = (EditText)this.findViewById(R.id.del_num);
        deleteText.setInputType(InputType.TYPE_CLASS_NUMBER);

        /*
        //CustomOpenHelperのインスタンスを作成する（まだデータベースはできない）
        CustomOpenHelper dbHelper = new CustomOpenHelper(this);
        //データベースオブジェクトの取得(データベースに接続すると作成される)
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //データベースを閉じる
        db.close();
        */
    }

    //InsertボタンClick処理
    private void buttonInsert_Click(View v) {
        ContentValues values = new ContentValues();

        values.put("name", "冷麺");
        values.put("price", 680);

        CustomOpenHelper dbHelper = new CustomOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long ret;
        try {
            ret = db.insert("FOOD_TABLE", null, values);
        } finally {
            db.close();
        }
        if (ret == -1) {
            Toast.makeText(this, "Insert失敗", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Insert成功", Toast.LENGTH_SHORT).show();
        }
    }

    //Updateボタンclick処理
    private void buttonUpdate_Click(View v) {
        String upd_num = updateText.getText().toString();

        ContentValues values = new ContentValues();
        values.put("price", 114);
        String whereClause = "id = ?";
        String whereArgs[] = {upd_num};

        CustomOpenHelper dbHelper = new CustomOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int ret;
        try {
            ret = db.update("FOOD_TABLE", values, whereClause, whereArgs);
        } finally {
            db.close();
        }
        if (ret == -1) {
            Toast.makeText(this, "Update失敗", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Update成功", Toast.LENGTH_SHORT).show();
        }
    }

    //DeleteボタンClick処理
    private void buttonDelete_Click(View v) {
        String del_num = deleteText.getText().toString();
        String whereClause = "id = ?";
        String whereArgs[] = {del_num};

        CustomOpenHelper dbHelper = new CustomOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int ret;
        try {
            ret = db.delete("FOOD_TABLE", whereClause, whereArgs);
        } finally {
            db.close();
        }
        if (ret == -1) {
            Toast.makeText(this, "Delete失敗", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Delete成功", Toast.LENGTH_SHORT).show();
        }
    }

    //QueryボタンClick処理
    private void buttonQuery_OnClick(View v) {
        //Queryメソッドでデータを取得
        String[] cols = {"id", "name", "price"};
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        CustomOpenHelper dbHelper = new CustomOpenHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        try {
            Cursor cursor = db.query("FOOD_TABLE", cols, selection, selectionArgs, groupBy, having, orderBy);

            //TextViewに表示
            StringBuilder text = new StringBuilder();
            while (cursor.moveToNext()) {
                text.append(cursor.getInt(0));
                text.append("\t|" + cursor.getString(1));
                text.append("\t\t|" + cursor.getString(2) + "円");
                text.append("\n");
            }
            TextView lblList = (TextView) this.findViewById(R.id.labelList);
            lblList.setText(text);
        } finally {
            db.close();
        }
    }

    //WarpボタンClick処理
//    private void buttonWarp_Onclick(View v) {
 //       startActivityForResult(intent, rqCode);
 //   }

    private void buttonList_OnClick(View v){
        startActivity(intentlist);
    }

    public void Insert() {
        ContentValues values = new ContentValues();

        values.put("name", strname);
        values.put("price", strprice);

        CustomOpenHelper dbHelper = new CustomOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long ret;
        try {
            ret = db.insert("FOOD_TABLE", null, values);
        } finally {
            db.close();
        }
        if (ret == -1) {
            Toast.makeText(this, "Insert失敗", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Insert成功", Toast.LENGTH_SHORT).show();
        }
    }

    public void Update(){
        String upd_num = updateText.getText().toString();

        ContentValues values = new ContentValues();

        values.put("name", strname);
        values.put("price", strprice);
        String whereClause = "id = ?";
        String whereArgs[] = {upd_num};

        CustomOpenHelper dbHelper = new CustomOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int ret;
        try {
            ret = db.update("FOOD_TABLE", values, whereClause, whereArgs);
        } finally {
            db.close();
        }
        if (ret == -1) {
            Toast.makeText(this, "Update失敗", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Update成功", Toast.LENGTH_SHORT).show();
        }
    }

    public void onActivityResult( int requestCode, int resultCode, Intent intent )
    {
        // startActivityForResult()の際に指定した識別コードとの比較
        if( requestCode == 101 ){
            // 返却結果ステータスとの比較
            if( resultCode == Activity.RESULT_OK ){
                // 返却されてきたintentから値を取り出す
                strname = intent.getStringExtra( "Name" );  //Nameはsubactivityのputextraで定義した名称
                strprice = intent.getIntExtra( "Price", 0 );    //データがない場合0が返る

                if(frg == 1)
                    Insert();
                else if(frg == 2)
                    Update();
            }
        }
    }

}
