package com.example.a171y015.testsqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    private EditText editName, editPrice;
    private TextView textView, lblName, lblPrice;
    String text = "sampleData";
    String Name = "sampleName";
    int Price = 0;
    Intent intent = new Intent();

    //textボタンのclickリスナー
    private View.OnClickListener buttonText_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //エディットテキストのテキストを取得
            if(editName.getText() != null && editPrice.getText() != null)
            {
                //Nameの取得
                Name = editName.getText().toString();
                //Priceの取得
                Price = Integer.parseInt(editPrice.getText().toString());
                //正しく取得されているか確認のテキスト
                text = editName.getText().toString() + "|\t";
                text += editPrice.getText().toString() + "円";
            }
            //取得したテキストをTextViewに張り付ける
            textView.setText(text);
            //取得したデータを送る
            intent.putExtra("Name", Name);
            intent.putExtra("Price", Price);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        //text
        //editText
        editName = (EditText) findViewById(R.id.edit_name);
        editPrice = (EditText) findViewById(R.id.edit_price);
        editPrice.setInputType(InputType.TYPE_CLASS_NUMBER);    //インプットタイプを数字のみに指定
        //textView
        textView = (TextView) findViewById(R.id.text_view);
        lblName = (TextView) findViewById(R.id.lblname);
        lblPrice = (TextView) findViewById(R.id.lblprice);
        //button
        Button btntext = (Button) findViewById(R.id.buttonText);
        btntext.setOnClickListener(buttonText_Click);
    }
}
