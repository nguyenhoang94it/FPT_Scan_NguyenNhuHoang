package com.example.hcd_fresher028.scanner.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hcd_fresher028.scanner.Model.RealmController;
import com.example.hcd_fresher028.scanner.Model.Scan;
import com.example.hcd_fresher028.scanner.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by HCD-Fresher028 on 1/6/2017.
 */

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.MyViewHolder> {
    ArrayList<Scan> listProduct;
    Context context;

    public AdapterProduct(ArrayList<Scan> listProduct, Context context) {
        this.listProduct = listProduct;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Scan product = listProduct.get(position);
        holder.tvResult.setText(product.getChuoiScan());
        holder.date.setText(product.getDateScan().toString());
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvResult, date;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvResult = (TextView) itemView.findViewById(R.id.tvProduct);
            date = (TextView) itemView.findViewById(R.id.date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myOnClickListener.onClick(getAdapterPosition());

                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    final Realm realm = RealmController.with((Activity) context).getRealm();
                    //final RealmResults<Scan> moi=realm.where(Scan.class).endsWith()
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Bạn có chắc chắc muốn xóa Scan");
                    builder.setTitle("Xóa Scan");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            int n = getAdapterPosition();
                            RealmResults<Scan> moi = realm.where(Scan.class).equalTo("idSacn", listProduct.get(n).getIdSacn()).findAll();
                            realm.beginTransaction();
                            moi.clear();
                            realm.commitTransaction();
                            dialogInterface.dismiss();
                            notifyDataSetChanged();
                            listProduct.remove(n);
                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                    return true;
                }
            });

        }
    }

    MyOnClickListener myOnClickListener;

    public void setMyOnClickListener(MyOnClickListener myOnClickListener) {
        this.myOnClickListener = myOnClickListener;
    }

    public interface MyOnClickListener {
        void onClick(int potion);
    }
}
