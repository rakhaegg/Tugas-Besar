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
public class Kasir implements IWithDB{
    private String nama;
    private int idkasir;
    
    public Kasir(){
        
    }
    public Kasir(String nama){
        this.nama = nama;
     
    }
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getIdkasir() {
        return idkasir;
    }

    public void setIdkasir(int idkasir) {
        this.idkasir = idkasir;
    }
    
     public Kasir getById(int id) {
        Kasir kasir = new Kasir();
        ResultSet rs = DBHelper.selectQuery("SELECT *"
                + " FROM kasir k"  
                + " WHERE k.idkasir = '" + id + "'");

        try {
            while (rs.next()) {
            
                kasir = new Kasir();
                kasir.setIdkasir(rs.getInt("idkasir"));
                kasir.setNama(rs.getString("nama"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kasir;
    }

    public ArrayList<Kasir> getAll() {
        
        ArrayList<Kasir> ListKasir = new ArrayList();

        ResultSet rs = DBHelper.selectQuery("SELECT * "
                + " FROM kasir "  
                );

        try {
            while (rs.next()) {
                Kasir kasir = new Kasir();
                kasir.setNama(rs.getString("nama"));
                ListKasir.add(kasir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListKasir;
    }
    
    public ArrayList<Kasir> search(String keyword) {
        ArrayList<Kasir> ListKasir = new ArrayList();

        ResultSet rs = DBHelper.selectQuery("SELECT * "
                + " FROM kasir k"  
                + " WHERE k.idkasir '%" + keyword + "%'"
        );
        try {
            while (rs.next()) {
                Kasir kasir = new Kasir();
                kasir.setNama(rs.getString("nama"));
               
               
                ListKasir.add(kasir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListKasir;
    }
    @Override
    public void save() {
         if (getById(idkasir).idkasir == 0) {
            String SQL = "INSERT INTO kasir (nama) VALUES ("     
                    + " '" + this.nama + "'"
                    + " )";
            this.idkasir = DBHelper.insertQueryGetId(SQL);

        } else {
            String SQL = "UPDATE kasir SET"
                    + " nama = '" + this.nama + "'"
                    + " WHERE idkasir =  '" + this.idkasir + "'";
            DBHelper.executeQuery(SQL);

        }
    }

    @Override
    public void delete() {
        String SQL = "DELETE FROM kasir WHERE idkasir = '" + this.idkasir + "'";
        DBHelper.executeQuery(SQL);
    }
    
    
    
}
