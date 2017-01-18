package com.example.hcd_fresher028.scanner.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hcd_fresher028.scanner.R;
import com.google.zxing.integration.android.IntentIntegrator;

import static com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE;

/**
 * Created by HCD-Fresher028 on 1/6/2017.
 */

public class FragmentScan extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.scan,container,false);
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan Now");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
        getActivity().startActivityForResult(integrator.createScanIntent(), REQUEST_CODE);
        return view;
    }


}
