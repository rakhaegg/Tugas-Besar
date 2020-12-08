/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author zorro
 */
import java.sql.ResultSet;
import java.util.ArrayList;

public class Barang implements IWithDB {
    
    private int idbarang;
    private String makanan;
    private int harga;
    
    public Barang(){
        
    }
    public Barang(String makanan , int harga){
       this.makanan = makanan;
       this.harga = harga;
    }

    public int getIdbarang() {
        return idbarang;
    }

    public void setIdbarang(int idbarang) {
        this.idbarang = idbarang;
    }

    public String getMakanan() {
        return makanan;
    }

    public void setMakanan(String makanan) {
        this.makanan = makanan;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    
    public Barang getById(int id) {
        
        Barang barang = new Barang();
        ResultSet rs = DBHelper.selectQuery("SELECT *"
                + " FROM barang p"  
                + " WHERE p.idbarang = '" + id + "'");

        try {
            while (rs.next()) {
            
                barang = new Barang();
                barang.setIdbarang(rs.getInt("idbarang"));
                barang.setMakanan(rs.getString("makanan"));
                barang.setHarga(rs.getInt("harga"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return barang;
    }

    public ArrayList<Barang> getAll() {
        
        ArrayList<Barang> ListBarang = new ArrayList();

        ResultSet rs = DBHelper.selectQuery("SELECT * "
                + " FROM barang "  
                );

        try {
            while (rs.next()) {
                Barang barang = new Barang();
                barang.setIdbarang(rs.getInt("idbarang"));
                barang.setMakanan(rs.getString("makanan"));
                barang.setHarga(rs.getInt("harga"));
                ListBarang.add(barang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListBarang;
    }
    
    public ArrayList<Barang> search(String keyword) {
        
        ArrayList<Barang> ListBarang = new ArrayList();

        ResultSet rs = DBHelper.selectQuery("SELECT * "
                + " FROM barang b"  
                + " WHERE p.idbarang '%" + keyword + "%'"
        );
        try {
            while (rs.next()) {
                Barang barang = new Barang();
                barang.setIdbarang(rs.getInt("idbarang"));
                barang.setMakanan(rs.getString("makanan"));
                barang.setHarga(rs.getInt("harga"));
                ListBarang.add(barang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListBarang;
    }
    
    @Override
    public void save() {
         if (getById(idbarang).getIdbarang() == 0) {
            String SQL = "INSERT INTO barang (makanan , harga) VALUES ("     
                    + " '" + this.makanan + "',"
                    + " '" + this.harga + "'"    
                    + " )";
            this.idbarang = DBHelper.insertQueryGetId(SQL);

        } else {
            String SQL = "UPDATE barang SET"
                    + " makanan = '" + this.makanan + "',"
                    + " harga = '" + this.harga + "'"
                    + " WHERE idbarang =  '" + this.idbarang + "'";
            DBHelper.executeQuery(SQL);

        }
    }

    @Override
    public void delete() {
        String SQL = "DELETE FROM barang WHERE idbarang = '" + this.idbarang + "'";
        DBHelper.executeQuery(SQL);
    }
    
    
    
    
}
