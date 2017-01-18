package com.example.hcd_fresher028.scanner.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hcd_fresher028.scanner.R;

/**
 * Created by HCD-Fresher028 on 1/10/2017.
 */

public class FragmentDetail extends Fragment {
    private TextView text_product;
    ImageView img;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_main, container, false);
        Bundle bundle = getArguments();
        text_product = (TextView) view.findViewById(R.id.text_product);
        img= (ImageView) view.findViewById(R.id.img);
        text_product.setText(bundle.getString("chuoi"));
        return view;
    }

}
