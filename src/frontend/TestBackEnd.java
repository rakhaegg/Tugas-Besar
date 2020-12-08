/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.*;

/**
 *
 * @author zorro
 */
public class TestBackEnd {
    
    public static void main(String[] args) {
       
       
        Kasir kasir1 = new Kasir().getById(1);
        Customer cu1 = new Customer().getById(1);
        Barang barang1 = new Barang().getById(1);
        
        Pesan pesan1 = new Pesan(kasir1 ,cu1 , barang1 , 2 );
        pesan1.save();
    }
}
