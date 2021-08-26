package com.example.quanlylichthi.ui.monthi;

public class MonThiModel {
    String BacDaoTao;
    String KiHoc;
    String Khoa;
    String MaHocPhan;
    String TenHocPhan;
    String SoTC;
    String ThoiLuong;

    public MonThiModel(){}

    public MonThiModel(String bacDaoTao, String kiHoc, String khoa, String maHocPhan, String tenHocPhan, String soTC, String thoiLuong) {
        BacDaoTao = bacDaoTao;
        KiHoc = kiHoc;
        Khoa = khoa;
        MaHocPhan = maHocPhan;
        TenHocPhan = tenHocPhan;
        SoTC = soTC;
        ThoiLuong = thoiLuong;
    }

    public String getBacDaoTao() {
        return BacDaoTao;
    }

    public void setBacDaoTao(String bacDaoTao) {
        BacDaoTao = bacDaoTao;
    }

    public String getKiHoc() {
        return KiHoc;
    }

    public void setKiHoc(String kiHoc) {
        KiHoc = kiHoc;
    }

    public String getKhoa() {
        return Khoa;
    }

    public void setKhoa(String khoa) {
        Khoa = khoa;
    }

    public String getMaHocPhan() {
        return MaHocPhan;
    }

    public void setMaHocPhan(String maHocPhan) {
        MaHocPhan = maHocPhan;
    }

    public String getTenHocPhan() {
        return TenHocPhan;
    }

    public void setTenHocPhan(String tenHocPhan) {
        TenHocPhan = tenHocPhan;
    }

    public String getSoTC() {
        return SoTC;
    }

    public void setSoTC(String soTC) {
        SoTC = soTC;
    }

    public String getThoiLuong() {
        return ThoiLuong;
    }

    public void setThoiLuong(String thoiLuong) {
        ThoiLuong = thoiLuong;
    }

    @Override
    public String toString() {
        return "MonThiModel{" +
                "BacDaoTao='" + BacDaoTao + '\'' +
                ", KiHoc='" + KiHoc + '\'' +
                ", Khoa='" + Khoa + '\'' +
                ", MaHocPhan='" + MaHocPhan + '\'' +
                ", TenHocPhan='" + TenHocPhan + '\'' +
                ", SoTC='" + SoTC + '\'' +
                ", ThoiLuong='" + ThoiLuong + '\'' +
                '}';
    }
}
