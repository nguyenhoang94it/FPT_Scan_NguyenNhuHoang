package com.example.hcd_fresher028.scanner.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by NGUYENHOANG on 1/15/2017.
 */

public class FragmentLike extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Uri uri = Uri.parse("https://www.facebook.com/cafesport.108/?fref=ts");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
      
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
