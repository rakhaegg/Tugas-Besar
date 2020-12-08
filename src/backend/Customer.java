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

public class Customer implements  IWithDB{
    private int idcustomer;
    private String nama;
    private int jumlah;
   

    public Customer(){
        
    }
    public Customer(String nama){
        this.nama = nama;
    }
    
    public int getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(int idcustomer) {
        this.idcustomer = idcustomer;
    }
    
   
    
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    public Customer getById(int id) {
        Customer customer = new Customer();
        ResultSet rs = DBHelper.selectQuery("SELECT *"
                + " FROM customer p"  
                + " WHERE p.idcustomer = '" + id + "'");

        try {
            while (rs.next()) {
            
                customer = new Customer();
                customer.setIdcustomer(rs.getInt("idcustomer"));
                customer.setNama(rs.getString("nama"));             
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    public ArrayList<Customer> getAll() {
        
        ArrayList<Customer> ListCustomer = new ArrayList();

        ResultSet rs = DBHelper.selectQuery("SELECT * "
                + " FROM customer "  
                );

        try {
            while (rs.next()) {
                Customer customer = new Customer();
                
                customer.setNama(rs.getString("nama"));
                ListCustomer.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListCustomer;
    }
    
    public ArrayList<Customer> search(String keyword) {
        ArrayList<Customer> ListCustomer = new ArrayList();

        ResultSet rs = DBHelper.selectQuery("SELECT * "
                + " FROM customer p"  
                + " WHERE p.idcustomer '%" + keyword + "%'"
        );
        try {
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setNama(rs.getString("nama"));
               
                ListCustomer.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListCustomer;
    }
    @Override
    public void save() {
         if (getById(idcustomer).getIdcustomer() == 0) {
            String SQL = "INSERT INTO customer (nama) VALUES ("     
                    + " '" + this.nama + "'"
                    + " )";
            this.idcustomer = DBHelper.insertQueryGetId(SQL);

        } else {
            String SQL = "UPDATE customer SET"
                    + " nama = '" + this.nama + "'"
                    + " WHERE idcustomer =  '" + this.idcustomer + "'";
            DBHelper.executeQuery(SQL);

        }
    }

    @Override
    public void delete() {
        String SQL = "DELETE FROM customer WHERE idcustomer = '" + this.idcustomer + "'";
        DBHelper.executeQuery(SQL);
    }
    
    
    
}
