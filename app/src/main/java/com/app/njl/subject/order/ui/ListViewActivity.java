package com.app.njl.subject.order.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.app.njl.R;

/**
 * @author  jiaowenzheng
 */
public class ListViewActivity extends AppCompatActivity {

    private LinearLayout linearLayout;

    private boolean isSelect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_comment_layout);
        linearLayout = (LinearLayout)this.findViewById(R.id.order_comment_item);

        findViewById(R.id.order_comment_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isSelect) {
                    linearLayout.setVisibility(View.VISIBLE);
                    isSelect = true;
                }else {
                    linearLayout.setVisibility(View.GONE);
                    isSelect = false;
                }
            }
        });
    }

}
