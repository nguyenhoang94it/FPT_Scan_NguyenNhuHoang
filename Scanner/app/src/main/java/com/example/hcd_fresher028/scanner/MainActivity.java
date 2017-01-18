package com.example.hcd_fresher028.scanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.hcd_fresher028.scanner.Fragment.FragmentAbout;
import com.example.hcd_fresher028.scanner.Fragment.FragmentHistory;
import com.example.hcd_fresher028.scanner.Fragment.FragmentLike;
import com.example.hcd_fresher028.scanner.Fragment.FragmentScan;
import com.example.hcd_fresher028.scanner.Fragment.FragmentShare;
import com.example.hcd_fresher028.scanner.Model.RealmController;
import com.example.hcd_fresher028.scanner.Model.Scan;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Date;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        FragmentManager fr = getSupportFragmentManager();

        fr.beginTransaction().replace(R.id.frame_body, new FragmentHistory()).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {

                Realm realm = RealmController.with(this).getRealm();
                Scan scan = new Scan();
                scan.setChuoiScan(result.getContents());
                int moi = realm.where(Scan.class).findAll().size() + 1;
                scan.setIdSacn(moi);
                scan.setDateScan(new Date());
                realm.beginTransaction();
                realm.copyToRealm(scan);
                realm.commitTransaction();

                Intent intent = new Intent(this, DetailScan.class);
                Bundle bundle = new Bundle();
                bundle.putString("chuoi", result.getContents());
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_scan) {
            // Handle the camera action
            callFragment(new FragmentScan());
        } else if (id == R.id.nav_history) {

            callFragment(new FragmentHistory());

        } else if (id == R.id.nav_like) {
            callFragment(new FragmentLike());
        } else if (id == R.id.nav_about) {
            callFragment(new FragmentAbout());

        } else if (id == R.id.nav_share) {
            callFragment(new FragmentShare());
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void callFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.frame_body, fragment);
        transaction.commit();
    }


}
