package com.example.quanlylichthi.ui.canbo;

public class CanBoModel {
    String MaCanBo;
    String TenCanBo;
    String Khoa;
    int SDT;

    public CanBoModel(){}
    public CanBoModel(String maCanBo, String tenCanBo, String khoa, int SDT) {
        MaCanBo = maCanBo;
        TenCanBo = tenCanBo;
        Khoa = khoa;
        this.SDT = SDT;
    }

    public String getMaCanBo() {
        return MaCanBo;
    }

    public void setMaCanBo(String maCanBo) {
        MaCanBo = maCanBo;
    }

    public String getTenCanBo() {
        return TenCanBo;
    }

    public void setTenCanBo(String tenCanBo) {
        TenCanBo = tenCanBo;
    }

    public String getKhoa() {
        return Khoa;
    }

    public void setKhoa(String khoa) {
        Khoa = khoa;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    @Override
    public String toString() {
        return "CanBoModel{" +
                "MaCanBo='" + MaCanBo + '\'' +
                ", TenCanBo='" + TenCanBo + '\'' +
                ", Khoa='" + Khoa + '\'' +
                ", SDT=" + SDT +
                '}';
    }
}
