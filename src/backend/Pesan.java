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
public class Pesan implements IWithDB {
    
   
    private int idpesan;
    private Kasir kasir = new Kasir();
    private Customer customer = new Customer();
    private Barang barang = new Barang();
    private int harga;
    private int jumlah;

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    
    public Pesan(){
        
    }
    
    public Pesan(Kasir kasir , Customer customer , Barang barang  , int jumlah ){
        
        this.kasir = kasir;
        this.customer = customer;
        this.barang = barang;
        this.jumlah = jumlah;
        calculate();
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
    

    public int getIdpesan() {
        return idpesan;
    }

    public void setIdpesan(int idpesan) {
        this.idpesan = idpesan;
    }

    public Kasir getKasir() {
        return kasir;
    }

    public void setKasir(Kasir kasir) {
        this.kasir = kasir;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public Pesan getById(int id) {
        Pesan pesan = new Pesan();
        ResultSet rs = DBHelper.selectQuery("SELECT *"
                + " FROM pesan p"  
                + " WHERE p.idpesan = '" + id + "'");

        try {
            while (rs.next()) {
                pesan = new Pesan();
                pesan.setIdpesan(rs.getInt("idpesan"));
                pesan.getKasir().setIdkasir(rs.getInt("idkasir"));
                pesan.getCustomer().setIdcustomer(rs.getInt("idcustomer"));
                pesan.getBarang().setIdbarang(rs.getInt("idbarang"));
                pesan.setHarga(rs.getInt("totalharga"));
                pesan.setJumlah(rs.getInt("jumlah"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pesan;
    }

    public ArrayList<Pesan> getAll() {
        
        ArrayList<Pesan> ListPesan = new ArrayList();

        ResultSet rs = DBHelper.selectQuery("SELECT * "
                + " FROM pesan  p "
                + " LEFT JOIN kasir k ON p.idkasir = k.idkasir"
                + " LEFT JOIN customer c ON p.idcustomer = c.idcustomer"
                + " LEFT JOIN barang b ON p.idbarang = b.idbarang"
                );

        try {
            while (rs.next()) {
                Pesan pesan = new Pesan();
                pesan.setIdpesan(rs.getInt("idpesan"));
                pesan.getKasir().setIdkasir(rs.getInt("idkasir"));
                pesan.getCustomer().setIdcustomer(rs.getInt("idcustomer"));
                pesan.getBarang().setIdbarang(rs.getInt("idbarang"));
                pesan.getBarang().setMakanan(rs.getString("makanan"));
                pesan.setHarga(rs.getInt("totalharga"));
                pesan.setJumlah(rs.getInt("jumlah"));
                ListPesan.add(pesan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListPesan;
    }
    
    public ArrayList<Pesan> search(String keyword) {
        ArrayList<Pesan> ListPesan = new ArrayList();

        ResultSet rs = DBHelper.selectQuery("SELECT * "
                + " FROM pesan p"  
                + " WHERE p.idpesan '%" + keyword + "%'"
        );
        try {
            while (rs.next()) {
                Pesan pesan = new Pesan();
                pesan.setIdpesan(rs.getInt("idpesan"));
                pesan.getKasir().setIdkasir(rs.getInt("idkasir"));
                pesan.getCustomer().setIdcustomer(rs.getInt("idcustomer"));
                pesan.getBarang().setIdbarang(rs.getInt("idbarang"));
                pesan.setHarga(rs.getInt("totalharga"));
               
                ListPesan.add(pesan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListPesan;
    }
    
    public int calculate(){
        harga  = barang.getHarga() * jumlah;
        return harga;
    }
    @Override
    public void save() {
        
         if (getById(idpesan).getIdpesan() == 0) {
            String SQL = "INSERT INTO pesan (idkasir , idcustomer ,idbarang ,  totalharga , jumlah) VALUES ("     
                    + " '" + this.getKasir().getIdkasir() + "',"
                    + " '" + this.getCustomer().getIdcustomer() + "',"
                    + " '" + this.getBarang().getIdbarang() + "',"
                    + " '" + this.getHarga() + "',"
                    + " '" + this.getJumlah() + "'"
                    + " )";
            this.idpesan = DBHelper.insertQueryGetId(SQL);

        } else {
            String SQL = "UPDATE pesan SET"
                    + " idkasir = '" + this.getKasir().getIdkasir() + "',"
                    + " idcustomer = '" + this.getCustomer().getIdcustomer() + "',"
                    + " idbarang = '" + this.getBarang().getIdbarang() + "',"
                    + " totalharga = '" + this.harga + "',"
                    + " jumlah = '" + this.jumlah + "'"
                    + " WHERE idpesan =  '" + this.idpesan + "'";
            DBHelper.executeQuery(SQL);

        }
    } 
    @Override
    public void delete() {
    }

  
    
}
