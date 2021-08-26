package com.example.quanlylichthi.ui.chonmonthi;

public class LichThiModel {
    String BacDaoTao;
    String Khoa;
    String HocKy;
    String MaHP;
    String TenHP;
    String PhongThi;
    String CaThi;
    String NgayThi;
    String CB1;
    String CB2;

    public LichThiModel() { }

    public LichThiModel(String bacDaoTao, String khoa, String hocKy, String maHP, String tenHP, String phongThi, String caThi, String ngayThi, String CB1, String CB2) {
        BacDaoTao = bacDaoTao;
        Khoa = khoa;
        HocKy = hocKy;
        MaHP = maHP;
        TenHP = tenHP;
        PhongThi = phongThi;
        CaThi = caThi;
        NgayThi = ngayThi;
        this.CB1 = CB1;
        this.CB2 = CB2;
    }

    public String getBacDaoTao() {
        return BacDaoTao;
    }

    public void setBacDaoTao(String bacDaoTao) {
        BacDaoTao = bacDaoTao;
    }

    public String getKhoa() {
        return Khoa;
    }

    public void setKhoa(String khoa) {
        Khoa = khoa;
    }

    public String getHocKy() {
        return HocKy;
    }

    public void setHocKy(String hocKy) {
        HocKy = hocKy;
    }

    public String getMaHP() {
        return MaHP;
    }

    public void setMaHP(String maHP) {
        MaHP = maHP;
    }

    public String getTenHP() {
        return TenHP;
    }

    public void setTenHP(String tenHP) {
        TenHP = tenHP;
    }

    public String getPhongThi() {
        return PhongThi;
    }

    public void setPhongThi(String phongThi) {
        PhongThi = phongThi;
    }

    public String getCaThi() {
        return CaThi;
    }

    public void setCaThi(String caThi) {
        CaThi = caThi;
    }

    public String getNgayThi() {
        return NgayThi;
    }

    public void setNgayThi(String ngayThi) {
        NgayThi = ngayThi;
    }

    public String getCB1() {
        return CB1;
    }

    public void setCB1(String CB1) {
        this.CB1 = CB1;
    }

    public String getCB2() {
        return CB2;
    }

    public void setCB2(String CB2) {
        this.CB2 = CB2;
    }

    @Override
    public String toString() {
        return "LichThiModel{" +
                "BacDaoTao='" + BacDaoTao + '\'' +
                ", Khoa='" + Khoa + '\'' +
                ", HocKy='" + HocKy + '\'' +
                ", MaHP='" + MaHP + '\'' +
                ", TenHP='" + TenHP + '\'' +
                ", PhongThi='" + PhongThi + '\'' +
                ", CaThi='" + CaThi + '\'' +
                ", NgayThi='" + NgayThi + '\'' +
                ", CB1='" + CB1 + '\'' +
                ", CB2='" + CB2 + '\'' +
                '}';
    }
}
