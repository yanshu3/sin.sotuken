package com.example.a171y015.testsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 171y015 on 2018/05/30.
 */

public class CustomOpenHelper extends SQLiteOpenHelper {

    //データベース自体の名前(テーブル名ではない)
    private static final  String DBName = "TEST_DB";
    //データベースのバージョン(2,3と挙げていくとonUpgradeメソッドが実行される)
    private static final   int VERSION = 1;

    //コンストラクタ 以下のように呼ぶこと
    public CustomOpenHelper(Context context) {
        super(context, DBName, null, VERSION);
    }

    //データベースが作成されたときに実行される処理
    @Override
    public void onCreate(SQLiteDatabase db){
        /**
         * テーブルを作成する
         * execSQLメソッドにCREATE TABLE命令を文字列として渡すことで実行される
         * 引数で指定されているものの意味は以下の通り
         * 引数１・・・id:列名, INTEGER:数値型, PRIMATY KEY:テーブル内の行で重複なし, AUTOINCREMENT:１から順に振っていく
         * 引数２・・・name:列名, TEXT:文字列型
         * 引数３・・・price:列名, INTEGER:数値型
         */
        db.execSQL("CREATE TABLE FOOD_TABLE(" + "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," + "price INTEGER)");

        /**
         * FOOD_TABLEに行を追加する
         * VALUESの前と後に()があるが、前の()に列名,後の()に実際入れる値を指定する
         * idは自動で振られるため指定しない
         */
        String sql_tbl = "";

        db.execSQL("INSERT INTO FOOD_TABLE(name, price) VALUES('ラーメン', 700)");

        sql_tbl += "INSERT INTO FOOD_TABLE(name,price) VALUES('まぜそば', 830)";
        db.execSQL(sql_tbl);
    }

    //データベースをバージョンアップした時に実行される処理
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        /**
         * テーブルを削除する
         */
        db.execSQL("DROP TABLE IF EXISTS FOOD_TABLE");

        //新しくテーブルを作成する
        onCreate(db);
    }

    //データベースが開かれた時に実行される処理
    @Override
    public void  onOpen(SQLiteDatabase db){
        super.onOpen(db);
    }
}
