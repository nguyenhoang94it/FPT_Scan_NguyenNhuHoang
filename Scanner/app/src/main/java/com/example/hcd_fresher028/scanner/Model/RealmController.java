package com.example.hcd_fresher028.scanner.Model;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;


import io.realm.Realm;

/**
 * Created by NGUYENHOANG on 1/15/2017.
 */

public class RealmController {
    public static RealmController realmController;
    public static Realm realm;
    public RealmController(Application app){
        realm=Realm.getDefaultInstance();
    }
    public static RealmController with(Fragment f){
        if(realmController==null){
            realmController=new RealmController(f.getActivity().getApplication());
        }
        return realmController;
    }
    public static RealmController with(Activity activity){
        if(realmController==null){
            realmController=new RealmController(activity.getApplication());
        }
        return realmController;
    }

    public Realm getRealm(){
        return realm;
    }
}
