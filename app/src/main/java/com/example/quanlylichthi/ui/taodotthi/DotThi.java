package com.example.quanlylichthi.ui.taodotthi;

import androidx.annotation.NonNull;

public class DotThi {
    private int id;

    private String bacDaoTao;

    private String khoa;

    private  String hocKy;

    private String ngayBatDau;

    private String ngayKetThuc;

    public String getBacDaoTao() {
        return bacDaoTao;
    }

    public void setBacDaoTao(String bacDaoTao) {
        this.bacDaoTao = bacDaoTao;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getHocKy() {
        return hocKy;
    }

    public void setHocKy(String hocKy) {
        this.hocKy = hocKy;
    }


    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public DotThi(){
        this.bacDaoTao = "";
        this.khoa = "";
        this.hocKy = "";
        this.ngayBatDau = "";
        this.ngayKetThuc = "";
    }
    //Contructor
    public DotThi(String bac, String khoa, String hocKy, String ngayBD, String ngayKT){
        this.bacDaoTao = bac;
        this.khoa = khoa;
        this.hocKy = hocKy;
        this.ngayBatDau = ngayBD;
        this.ngayKetThuc = ngayKT;
    }

    @Override
    public String toString() {
        return
                "Bậc đào tạo: " + bacDaoTao + "\n" +
                "khoa : " + khoa + "\n" +
                "Học kì: " + hocKy + "\n" +
                "Ngày bắt đầu: " + ngayBatDau + "\n" +
                "Ngày kết thúc: " + ngayKetThuc;
    }
}
