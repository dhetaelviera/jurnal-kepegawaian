/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.pegawai;
import view.Pendataan;
import view.laporan;
import view.login;
import view.tambahjurnal;

/**
 *
 * @author Dheta
 */
public class controllerUser {

    private login login;
    private pegawai mPegawai;
    private Pendataan pendataan;
    private laporan laporan;
    private tambahjurnal tambahjurnal;
    String nip, nama, jabatan, alamat;
    int status;

    public controllerUser() {
        mPegawai = new pegawai();
        login = new login();
        login.setVisible(true);
        login.setLocationRelativeTo(null);
        login.setResizable(false);
        login.loginListener(new loginListener());

    }

    public controllerUser(String nip, String nama) {
        pendataan = new Pendataan();
        mPegawai = new pegawai();
        pendataan.setVisible(true);
        this.nip = nip;
        this.nama = nama;
        status = 1;
        pendataan.setNIP(nip);
        pendataan.setNama(nama);
        pendataan.setJabatan(mPegawai.getJabatan(nip));
        System.out.println("nip login " + nip);
        System.out.println("jabatan 2: " + mPegawai.getJabatan(nip));
        pendataan.pendataan().setEnabled(false);
        pendataan.setLocationRelativeTo(null);
        pendataan.setResizable(false);
        pendataan.tabeljurnal(mPegawai.bacaJurnalNow(nip));
        pendataan.cariListener(new caritanggalListener());
        pendataan.cariBulanListener(new caribulanListener());
        pendataan.tambahListener(new tambahjurnalListener());
        pendataan.laporanListener(new laporanListener());
    }

    public controllerUser(String nip, int a) {
        tambahjurnal = new tambahjurnal();
        mPegawai = new pegawai();
        tambahjurnal.setVisible(true);
        System.out.println("di sini + " + nip);
        this.nip = nip;
        tambahjurnal.setTanggal();
        tambahjurnal.setNIP(nip);
        tambahjurnal.setJabatan(mPegawai.getJabatan(nip));
        tambahjurnal.setNama(mPegawai.getNama(nip));
        tambahjurnal.setJabatan(mPegawai.getJabatan(nip));
        tambahjurnal.tambahListener(new simpanjurnalListener());
        tambahjurnal.setResizable(false);
        tambahjurnal.setLocationRelativeTo(null);
    }

    public controllerUser(String nip, int a, int b) {
        laporan = new laporan();
        mPegawai = new pegawai();
        laporan.setVisible(true);
        System.out.println("di sini + " + nip);
        this.nip = nip;
        status = 2;
        laporan.setResizable(false);
        laporan.setLocationRelativeTo(null);
        laporan.setNIP(nip);
        laporan.setNama(mPegawai.getNama(nip));
        laporan.setJabatan(mPegawai.getJabatan(nip));
        laporan.bulan(new caribulanListener());
        laporan.tahun(new caritahunListener());
        laporan.range(new carirangeListener());
        laporan.exportListener(new exportLaporan());
    }

    private void bacaByDate() {
        try {
            pendataan.tabeljurnal(mPegawai.bacaTabelJurnalTanggal(nip, pendataan.getTanggal()));
        } catch (ParseException ex) {
            Logger.getLogger(controllerUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void bacaByMonth() {
        try {
            if (status == 1) {
                pendataan.tabeljurnal(mPegawai.bacaTabelJurnalBulan(nip, pendataan.getTanggal()));
            } else if (status == 2) {
                laporan.tabeljurnal(mPegawai.bacaTabelJurnalBulan(nip, laporan.getTanggal()));
            }
        } catch (ParseException ex) {
            Logger.getLogger(controllerUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private class exportLaporan implements ActionListener {

        public exportLaporan() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String nama = laporan.getNama();
            String jabatan = laporan.getJabatan();
            Date tanggal = laporan.getTanggal();
            DateFormat dtf = new SimpleDateFormat("dd MM yyyy");
            String lala = dtf.format(tanggal);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String bulan = lala.substring(3, 10);
            try {
                mPegawai.export(nip, nama, jabatan, bulan);
                JOptionPane.showMessageDialog(laporan, "Laporan Kinerja berhasil di eksport\n" + System.getProperty("user.home") + "/Downloads/bapenda/");
            } catch (IOException ex) {
                Logger.getLogger(controllerUser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(controllerUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class caritahunListener implements ActionListener {

        public caritahunListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                laporan.tabeljurnal(mPegawai.bacaTabelJurnalTahun(nip, laporan.getTanggal()));
            } catch (ParseException ex) {
                Logger.getLogger(controllerUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class carirangeListener implements ActionListener {

        public carirangeListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                laporan.tabeljurnal(mPegawai.bacaTabelJurnalRange(nip,laporan.getTanggalAwal(),laporan.getTanggalAkhir()));
            } catch (ParseException ex) {
                Logger.getLogger(controllerUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private class laporanListener implements ActionListener {

        public laporanListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            pendataan.dispose();
            new controllerUser(nip, 1, 2);
        }
    }

    private class tambahjurnalListener implements ActionListener {

        public tambahjurnalListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            pendataan.dispose();
            new controllerUser(nip, 2);
        }
    }

    private class caribulanListener implements ActionListener {

        public caribulanListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            bacaByMonth();
        }
    }

    private class caritanggalListener implements ActionListener {

        public caritanggalListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            bacaByDate();
        }
    }

    private class simpanjurnalListener implements ActionListener {

        public simpanjurnalListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            String kegiatan = tambahjurnal.getKegiatan();
            if (kegiatan.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(login, "Kegiatan tidak boleh kosong");
            } else {
                boolean test = mPegawai.tambahJurnal(nip, kegiatan);
                JOptionPane.showMessageDialog(login, "Jurnal hari ini berhasil ditambahkan");
                tambahjurnal.dispose();
                new controllerUser(nip, nama);
            }

        }
    }

    private class loginListener implements ActionListener {

        public loginListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            nama = login.nama();
            nip = login.nip();
            System.out.println(nama);
            System.out.println("ini 1 " + nama);
            if (nama.equalsIgnoreCase("") || nip.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(login, "Nama atau NIP tidak boleh kosong");
            } else {
                int level = mPegawai.login(nama, nip);
                String jabatan1 = mPegawai.getJabatan(nip);
                System.out.println(nip);
                new controllerUser(nip, nama);
                login.dispose();
                JOptionPane.showMessageDialog(pendataan, "Selamat datang NIP " + nama);

            }
        }
    }

}
