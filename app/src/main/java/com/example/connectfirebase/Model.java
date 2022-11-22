package com.example.connectfirebase;

public class Model {
    String tenKH, tenThuong, dacTinh, mauSac;
    int url;

    public Model() {
    }


    public Model(String tenKH, String tenThuong, String dacTinh, String mauSac, int url) {
        this.tenKH = tenKH;
        this.tenThuong = tenThuong;
        this.dacTinh = dacTinh;
        this.mauSac = mauSac;
        this.url = url;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenThuong() {
        return tenThuong;
    }

    public void setTenThuong(String tenThuong) {
        this.tenThuong = tenThuong;
    }

    public String getDacTinh() {
        return dacTinh;
    }

    public void setDacTinh(String dacTinh) {
        this.dacTinh = dacTinh;
    }

    public String getMauLa() {
        return mauSac;
    }

    public void setMauLa(String mauLa) {
        this.mauSac = mauLa;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }
}
