/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.pegawai;
import view.Pendataan;
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
    private tambahjurnal tambahjurnal;
    String nip, nama, jabatan, alamat;

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
        pendataan.setNIP(nip);
        pendataan.setNama(nama);
        pendataan.setJabatan(mPegawai.getJabatan(nip));
        System.out.println("nip login " + nip);
        System.out.println("jabatan 2: " + mPegawai.getJabatan(nip));
        System.out.println(jabatan);
        pendataan.setLocationRelativeTo(null);
        pendataan.setResizable(false);
        pendataan.tabeljurnal(mPegawai.bacaJurnal(nip));
        pendataan.cariListener(new caritanggalListener());
        pendataan.cariBulanListener(new caribulanListener());
        pendataan.tambahListener(new tambahjurnalListener());

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

    private void bacaByDate() {
        try {
            pendataan.tabeljurnal(mPegawai.bacaTabelJurnalTanggal(nip, pendataan.getTanggal()));
        } catch (ParseException ex) {
            Logger.getLogger(controllerUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void bacaByMonth() {
        try {
            pendataan.tabeljurnal(mPegawai.bacaTabelJurnalBulan(nip, pendataan.getTanggal()));
        } catch (ParseException ex) {
            Logger.getLogger(controllerUser.class.getName()).log(Level.SEVERE, null, ex);
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
            if (!kegiatan.equalsIgnoreCase("")) {
                boolean test=mPegawai.tambahJurnal(nip, kegiatan);
                tambahjurnal.dispose();
                new controllerUser(nip, nama);
            }else   {
                System.out.println("ada yang salah");
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
