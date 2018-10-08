/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author Dheta
 */
public class pegawai {

    private Connection koneksi;

    public pegawai() {
        koneksi = new koneksi().getKoneksi();
    }

    public String[] nip() {
        String query = "SELECT NIP FROM pegawai";
        String id[] = null;
        try {
            PreparedStatement st = koneksi.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            rs.last();
            id = new String[rs.getRow()];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                id[i] = rs.getString(1);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return id;
    }

    public int login(String nama, String nip) {
        String query = "select * from pegawai where nama = ? and nip =?";
        System.out.println(query);
        int tingkatan = 0;
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

    public String getNama(String nip) {
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
    
      public String getPangkat(String nip) {
        String query = "SELECT pangkat FROM pegawai WHERE nip=?";
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
    
      public String getGolongan(String nip) {
        String query = "SELECT pa.golruang FROM pegawai pe join pangkat pa on pe.pangkat=pa.pangkatgol  WHERE nip=?";
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

    public String[][] getNamaLogin() {
        String query = "select nip,nama from pegawai";
        String jenis[][] = null;

        try {
            PreparedStatement st = koneksi.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery();
            rs.last();
            jenis = new String[2][rs.getRow()];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                jenis[0][i] = rs.getString("nip");
                jenis[1][i] = rs.getString("nama");
                i++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getMessage();
        }
        return jenis;
    }

    public String getNIP(String nip) {
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

    public String getJabatan(String nip) {
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
        String namaKolom[] = {"Tanggal", "Nama", "Kegiatan"};
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

    public DefaultTableModel bacaJurnalNow(String nip) {
        String query = "select j.tanggal, p.nama, j.kegiatan from jurnal j join pegawai p on p.nip=j.pegawai where nip =? and tanggal=current_date ";
        String namaKolom[] = {"Tanggal", "Nama", "Kegiatan"};
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
                + "  FROM pegawai p join jurnal j on p.nip=j.pegawai WHERE p.nip=? and SUBSTRING(j.tanggal,6,2)=? ORDER BY tanggal  asc;";
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

    public DefaultTableModel bacaTabelJurnalTahun(String nip, Date tanggal) throws ParseException {
        String query = "SELECT j.tanggal, p.nama, j.kegiatan"
                + "  FROM pegawai p join jurnal j on p.nip=j.pegawai WHERE p.nip=? and SUBSTRING(j.tanggal,1,4)=? ORDER BY tanggal  desc;";
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
            String tanggal2 = tanggal1.substring(0,4);
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

    public DefaultTableModel bacaTabelJurnalRange(String nip, Date tanggal1, Date tanggal2) throws ParseException {
        String query = "SELECT j.tanggal, p.nama, j.kegiatan"
                + "  FROM pegawai p join jurnal j on p.nip=j.pegawai WHERE p.nip=? and tanggal BETWEEN ? and ?  ORDER BY tanggal desc;";
        String namaKolom[] = {"Tanggal", "Nama", "Kegiatan"};
        DefaultTableModel tabel = new DefaultTableModel(null, namaKolom);
        try {
            PreparedStatement st = koneksi.prepareStatement(query);
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date1 = tanggal1;
            System.out.println(date1);
            java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
            System.out.println(sqlDate1);

            DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date2 = tanggal2;
            System.out.println(date2);
            java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
            System.out.println(sqlDate2);
            System.out.println(tanggal1);
            System.out.println(tanggal2);

            st.setString(1, nip);
            st.setDate(2, sqlDate1);
            st.setDate(3, sqlDate2);
            System.out.println(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object data[] = new Object[4];

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

    public boolean export(String nip, String nama, String jabatan, String tanggal) throws FileNotFoundException, IOException, SQLException {
        String query = "SELECT j.tanggal, p.nama,p.nip, j.kegiatan "
                + "FROM pegawai p JOIN jurnal j on p.nip=j.pegawai where nip=?";

        String query2 = "SELECT SUBSTRING (tanggal,6,2) from jurnal where tanggal=?";

        try {
            PreparedStatement st = koneksi.prepareStatement(query);
            System.out.println(nip);
            st.setString(1, nip);
            ResultSet rs = st.executeQuery();
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Laporan Kegiatan");
            HSSFRow rowhead = sheet.createRow((short) 5);

            CellStyle style = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();

            font.setFontName(Short.toString(HSSFFont.BOLDWEIGHT_BOLD));
            style.setVerticalAlignment(CellStyle.ALIGN_CENTER);
            style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            style.setFont(font);

            rowhead.createCell((short) 0).setCellValue("Tanggal");
            rowhead.createCell((short) 1).setCellValue("Kegiatan");

            Row rowTotal = sheet.createRow(0);
            Cell cellTextTotal = rowTotal.createCell(0);
            cellTextTotal.setCellValue("NIP          : " + nip);
            cellTextTotal.setCellStyle(style);

            Row rowTotal2 = sheet.createRow(1);
            Cell cellTextTotal2 = rowTotal2.createCell(0);
            cellTextTotal2.setCellValue("Nama       :" + nama);

            Row rowTotal3 = sheet.createRow(2);
            Cell cellTextTotal3 = rowTotal3.createCell(0);
            cellTextTotal3.setCellValue("Jabatan    :" + jabatan);

            int i = 6;
            String desa = null;
            while (rs.next()) {
                HSSFRow row = sheet.createRow((short) i);

                row.createCell((short) 0).setCellValue(rs.getString("tanggal"));
                row.createCell((short) 1).setCellValue(rs.getString("kegiatan"));

                nip = rs.getString("nip");
                i++;
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.now();
            String yemi = System.getProperty("user.home") + "/Laporan Kinerja" + nip + " bulan " + tanggal + ".xls";
            FileOutputStream fileOut = new FileOutputStream(yemi);
            File file = new java.io.File(yemi);
//            file.mkdirs();
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }
            workbook.write(fileOut);
            fileOut.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

     public boolean export2(String nip, String nama, String jabatan, Date tanggal1, Date tanggal2) throws FileNotFoundException, IOException, SQLException {
        String query = "SELECT j.tanggal, p.nama,p.nip, j.kegiatan "
                + "FROM pegawai p JOIN jurnal j on p.nip=j.pegawai where nip=? and tanggal between ? and ?";


        try {
            PreparedStatement st = koneksi.prepareStatement(query);
            System.out.println(nip);
            
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date1 = tanggal1;
            System.out.println(date1);
            java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
            System.out.println(sqlDate1);

            DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date2 = tanggal2;
            System.out.println(date2);
            java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
            
            
            st.setString(1, nip);
            st.setDate(2, sqlDate1);
            st.setDate(3, sqlDate2);
            ResultSet rs = st.executeQuery();
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Laporan Kegiatan");
            HSSFRow rowhead = sheet.createRow((short) 5);

            CellStyle style = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();

            font.setFontName(Short.toString(HSSFFont.BOLDWEIGHT_BOLD));
            style.setVerticalAlignment(CellStyle.ALIGN_CENTER);
            style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            style.setFont(font);

            rowhead.createCell((short) 0).setCellValue("Tanggal");
            rowhead.createCell((short) 1).setCellValue("Kegiatan");

            Row rowTotal = sheet.createRow(0);
            Cell cellTextTotal = rowTotal.createCell(0);
            cellTextTotal.setCellValue("NIP          : " + nip);
            cellTextTotal.setCellStyle(style);

            Row rowTotal2 = sheet.createRow(1);
            Cell cellTextTotal2 = rowTotal2.createCell(0);
            cellTextTotal2.setCellValue("Nama       :" + nama);

            Row rowTotal3 = sheet.createRow(2);
            Cell cellTextTotal3 = rowTotal3.createCell(0);
            cellTextTotal3.setCellValue("Jabatan    :" + jabatan);

            int i = 6;
            String desa = null;
            while (rs.next()) {
                HSSFRow row = sheet.createRow((short) i);

                row.createCell((short) 0).setCellValue(rs.getString("tanggal"));
                row.createCell((short) 1).setCellValue(rs.getString("kegiatan"));

                nip = rs.getString("nip");
                i++;
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.now();
            String yemi = System.getProperty("user.home") + "/Laporan Kinerja" + nip +  ".xls";
            FileOutputStream fileOut = new FileOutputStream(yemi);
            File file = new java.io.File(yemi);
//            file.mkdirs();
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }
            workbook.write(fileOut);
            fileOut.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

}
