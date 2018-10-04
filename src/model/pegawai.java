/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dheta
 */
public class pegawai {
    private Connection koneksi;
    
    public pegawai(){
        koneksi= new koneksi().getKoneksi();
    }
    
     
      public String[] nip() {
        String query = "SELECT NIP FROM pegawai";
        String id[] = null;
        try {
            PreparedStatement st = koneksi.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            rs.last();
            id=new String[rs.getRow()];
            rs.beforeFirst();
            int i=0;
            while  (rs.next()){
                id[i]=rs.getString(1);
                i++;
            } 
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return id;
    }
      
    public int login(String nama, String nip) {
        String query ="select * from pegawai where nama = ? and nip =?";
            System.out.println(query);
        int tingkatan=0;
        try {
            PreparedStatement st = koneksi.prepareStatement(query);
            st.setString(1, nama);
            st.setString(2, nip);
            ResultSet rs = st.executeQuery();
             
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return tingkatan;
    }
    
      public String getNama(String nip ) {
        String query = "SELECT nama FROM pegawai WHERE nip=?";
          System.out.println(query);
        String id = "kosong";
        try {
            PreparedStatement st = koneksi.prepareStatement(query);
            st.setString(1, nip);
//            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getString(1);
                System.out.println(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return id;
    }
      
        public String getNIP(String nip ) {
        String query = "SELECT nip FROM pegawai WHERE nip=?";
        String id = "kosong";
        try {
            PreparedStatement st = koneksi.prepareStatement(query);
            st.setString(1, nip);
//            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getString(1);
                System.out.println(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return id;
    }
        
          public String getJabatan(String nip ) {
        String query = "SELECT j.jabatan FROM pegawai p join jabatan j on p.jabatan=j.idjabatan WHERE nip=?";
        String id = "kosong";
        try {
            PreparedStatement st = koneksi.prepareStatement(query);
            st.setString(1, nip);
//            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getString(1);
                System.out.println(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return id;
    }

    public DefaultTableModel bacaJurnal(String nip) {
        String query = "select j.tanggal, p.nama, j.kegiatan from jurnal j join pegawai p on p.nip=j.pegawai where nip =? ";
        String namaKolom[] = {"Tanggal",  "Nama","Kegiatan"};
        DefaultTableModel tabel = new DefaultTableModel(null, namaKolom);
        try {
            PreparedStatement st = koneksi.prepareStatement(query);
            st.setString(1, nip);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object data[] = new Object[3];
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                tabel.addRow(data);
            }

        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return tabel;
    }

    public DefaultTableModel bacaTabelJurnalTanggal(String nip, Date tanggal) throws ParseException {
        String query = "SELECT j.tanggal, p.nama, j.kegiatan"
                + "  FROM pegawai p join jurnal j on p.nip=j.pegawai WHERE p.nip=? and j.tanggal=? ORDER BY j.tanggal desc;";
        String namaKolom[] = {"Tangga;", "Nama", "Kegiatan"};
        DefaultTableModel tabel = new DefaultTableModel(null, namaKolom);
        try {
            PreparedStatement st = koneksi.prepareStatement(query);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = tanggal;
            System.out.println(date);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            System.out.println(sqlDate);
            st.setString(1, nip);
            st.setDate(2, sqlDate);
            System.out.println(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object data[] = new Object[3];

                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                tabel.addRow(data);
            }

        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return tabel;
    }

public DefaultTableModel bacaTabelJurnalBulan(String nip, Date tanggal) throws ParseException {
        String query = "SELECT j.tanggal, p.nama, j.kegiatan"
                + "  FROM pegawai p join jurnal j on p.nip=j.pegawai WHERE p.nip=? and SUBSTRING(j.tanggal,6,2)=? ORDER BY tanggal  desc;";
        String namaKolom[] = {"Tanggal", "Nama", "Kegiatan"};
        DefaultTableModel tabel = new DefaultTableModel(null, namaKolom);
        try {
            PreparedStatement st = koneksi.prepareStatement(query);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = tanggal;
            System.out.println(date);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            System.out.println(sqlDate + " ayam ");
            String tanggal1 = format.format(sqlDate);
            String tanggal2 = tanggal1.substring(5, 7);
            System.out.println(tanggal2 + " ayam22 ");
            st.setString(1, nip);
            st.setString(2, tanggal2);
            System.out.println(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object data[] = new Object[3];
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                tabel.addRow(data);
            }

        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return tabel;
    }
    
    public boolean tambahJurnal(String nip, String kegiatan) {
        String query = "insert into `jurnal` (`tanggal`,`pegawai`,`kegiatan`)VALUES(CURRENT_DATE,?,?)";
        System.out.println(query);
        try {
            PreparedStatement st = koneksi.prepareStatement(query);
            st.setString(1, nip);
            st.setString(2, kegiatan);
            int status = st.executeUpdate();
            if (status > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return false;
    }
}
