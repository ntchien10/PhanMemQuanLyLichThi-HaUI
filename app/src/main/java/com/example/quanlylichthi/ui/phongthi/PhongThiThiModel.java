package com.example.quanlylichthi.ui.phongthi;

import java.util.List;

public class PhongThiThiModel {
    String NgayBatDau;
    String NgayKetThuc;
    String TenPhong;
    String SucChua;

    public PhongThiThiModel(){}

    public PhongThiThiModel(String ngayBatDau, String ngayKetThuc, String tenPhong, String sucChua) {
        NgayBatDau = ngayBatDau;
        NgayKetThuc = ngayKetThuc;
        TenPhong = tenPhong;
        SucChua = sucChua;
    }

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        NgayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        NgayKetThuc = ngayKetThuc;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String tenPhong) {
        TenPhong = tenPhong;
    }

    public String getSucChua() {
        return SucChua;
    }

    public void setSucChua(String sucChua) {
        SucChua = sucChua;
    }

    @Override
    public String toString() {
        return "PhongThiThiModel{" +
                "NgayBatDau='" + NgayBatDau + '\'' +
                ", NgayKetThuc='" + NgayKetThuc + '\'' +
                ", TenPhong='" + TenPhong + '\'' +
                ", SucChua='" + SucChua + '\'' +
                '}';
    }
}
