package com.example.hcd_fresher028.scanner.Model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by NGUYENHOANG on 1/15/2017.
 */

public class Scan extends RealmObject {
    @PrimaryKey
    private int idSacn;

    private String chuoiScan;

    private Date dateScan;

    public int getIdSacn() {
        return idSacn;
    }

    public void setIdSacn(int idSacn) {
        this.idSacn = idSacn;
    }

    public String getChuoiScan() {
        return chuoiScan;
    }

    public void setChuoiScan(String chuoiScan) {
        this.chuoiScan = chuoiScan;
    }

    public Date getDateScan() {
        return dateScan;
    }

    public void setDateScan(Date dateScan) {
        this.dateScan = dateScan;
    }

    public Scan(int idSacn, String chuoiScan, Date dateScan) {
        this.idSacn = idSacn;
        this.chuoiScan = chuoiScan;
        this.dateScan = dateScan;
    }
    public Scan(){

    }
}
