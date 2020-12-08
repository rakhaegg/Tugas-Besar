/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author zorro
 */
public class Pemesanan {

    private Barang barang = new Barang();
    private Customer cus = new Customer();
    private Kasir kasir = new Kasir();
    private int idpesan;
    private int jumlah;
    private int harga;

    public Pemesanan() {

    }

    public Pemesanan(Kasir kasir, Customer customer, Barang barang, int jumlah, int harga) {
        this.kasir = kasir;
        this.cus = customer;
        this.barang = barang;
        this.jumlah = jumlah;
        this.harga = harga;

    }

    public int getIdpesan() {
        return idpesan;
    }

    public void setIdpesan(int idpesan) {
        this.idpesan = idpesan;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public Customer getCus() {
        return cus;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }

    public Kasir getKasir() {
        return kasir;
    }

    public void setKasir(Kasir kasir) {
        this.kasir = kasir;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public Pemesanan getById(int id) {
        Pemesanan pesan = new Pemesanan();
        ResultSet rs = DBHelper.selectQuery("SELECT *"
                + " FROM pesanan p"
                + " LEFT JOIN barang b ON p.idbarang = a.idbarang"
                + " LEFT JOIN kasir k ON p.idkasir =  k.idkasir "
                + " WHERE p.idpesan = '" + id + "'");

        try {
            while (rs.next()) {
                pesan = new Pemesanan();
                pesan.setIdpesan(rs.getInt("idpesan"));
                pesan.getBarang().setIdbarang(rs.getInt("idbarang"));
                pesan.getKasir().setIdkasir(rs.getInt("idkasir"));
                pesan.setJumlah(rs.getInt("jumlah"));
                pesan.getBarang().setHarga(rs.getInt("harga"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pesan;
    }

    public ArrayList<Pemesanan> getAll() {

        ArrayList<Pemesanan> ListPesan = new ArrayList();

        ResultSet rs = DBHelper.selectQuery("SELECT * "
                + " FROM pesanan p"
                + " LEFT JOIN barang b ON p.idbarang = b.idbarang"
                + " LEFT JOIN kasir k ON p.idkasir =  k.idkasir ");

        try {
            while (rs.next()) {
                Pemesanan pesan = new Pemesanan();
                pesan.setIdpesan(rs.getInt("idpesan"));

                pesan.setIdpesan(rs.getInt("idpesan"));
                pesan.getBarang().setIdbarang(rs.getInt("idbarang"));
                pesan.getKasir().setIdkasir(rs.getInt("idkasir"));
                pesan.setJumlah(rs.getInt("jumlah"));
                pesan.getBarang().setHarga(rs.getInt("harga"));
                ListPesan.add(pesan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListPesan;
    }

    public ArrayList<Pemesanan> search(String keyword) {
        ArrayList<Pemesanan> ListPesan = new ArrayList();

        ResultSet rs = DBHelper.selectQuery("SELECT * "
                + " FROM pesanan p"
                + " LEFT JOIN barang b ON p.idbarang = b.idbarang"
                + " LEFT JOIN kasir k ON p.idkasir = k.idbarang "
                + " WHERE p.idpesan '%" + keyword + "%'");
        try {
            while (rs.next()) {
                Pemesanan pesan = new Pemesanan();
        
                pesan.setIdpesan(rs.getInt("idpesan"));
                pesan.getBarang().setIdbarang(rs.getInt("idbarang"));
                pesan.getKasir().setIdkasir(rs.getInt("idkasir"));
                pesan.setJumlah(rs.getInt("jumlah"));
                pesan.getBarang().setHarga(rs.getInt("harga"));
                ListPesan.add(pesan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListPesan;
    }

}
