package com.example.hcd_fresher028.scanner.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hcd_fresher028.scanner.Adapter.AdapterProduct;
import com.example.hcd_fresher028.scanner.DetailScan;
import com.example.hcd_fresher028.scanner.MainActivity;
import com.example.hcd_fresher028.scanner.Model.RealmController;
import com.example.hcd_fresher028.scanner.Model.Scan;
import com.example.hcd_fresher028.scanner.R;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by HCD-Fresher028 on 1/6/2017.
 */

public class FragmentHistory extends Fragment implements AdapterProduct.MyOnClickListener{

    RecyclerView recyclerView;
    ArrayList<Scan> data;
    AdapterProduct adapterProduct;
    Context context;
    @Override
    //gán fragment cho activiti bạn muốn
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        Realm r= RealmController.with(this).getRealm();
        data = new ArrayList<>(r.where(Scan.class).findAllSorted("idSacn",false));
        adapterProduct = new AdapterProduct(data,context);
        recyclerView.setAdapter(adapterProduct);
        //đăng kí sự kiện onclick
        adapterProduct.setMyOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;

    }

    @Override
    public void onClick(int potion) {
        Intent i=new Intent(getActivity(), DetailScan.class);
        Bundle bundle=new Bundle();
        bundle.putString("chuoi",data.get(potion).getChuoiScan());
        i.putExtra("bundle",bundle);
        startActivity(i);
    }
}
