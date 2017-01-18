package com.example.hcd_fresher028.scanner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by NGUYENHOANG on 1/15/2017.
 */

public class DetailScan extends AppCompatActivity {
    Button bt1;
    Button bt2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_main);
        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("bundle");
        TextView text_product = (TextView) findViewById(R.id.text_product);
        final String chuoi = bundle.getString("chuoi");
        text_product.setText(chuoi);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.google.com/#q=" + chuoi);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                finish();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "I want to share code " + chuoi;
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, message);

                startActivity(Intent.createChooser(share, "Chia sẻ với"));
                finish();

            }
        });
    }
}
