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
import java.time.LocalDate;
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
    String nip, nama, jabatan, pangkat;
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
        pendataan.setNama(mPegawai.getNama(nip));
        pendataan.setJabatan(mPegawai.getJabatan(nip));
        pendataan.setPangkat(mPegawai.getPangkat(nip));
        pendataan.setGol(mPegawai.getGolongan(nip));
        System.out.println("nip login " + nip);
        System.out.println("jabatan 2: " + mPegawai.getJabatan(nip));
        pendataan.pendataan().setEnabled(false);
        pendataan.setLocationRelativeTo(null);
        pendataan.setResizable(false);
        pendataan.resetListener(new resettabel());
        pendataan.cariListener(new caritanggalListener());
        pendataan.cariBulanListener(new caribulanListener());
        pendataan.tahunListener(new caritahunListener());
        pendataan.tambahListener(new tambahjurnalListener());
        pendataan.laporanListener(new laporanListener());
        pendataan.keluarListener(new keluarPendataan());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.now();
        String tanggal = dtf.format(localDate);
        pendataan.setTanggal(tanggal);
        pendataan.tabeljurnal(mPegawai.bacaJurnalNow(nip));
    }

    public controllerUser(String nip, int a) {
        tambahjurnal = new tambahjurnal();
        mPegawai = new pegawai();
        tambahjurnal.setVisible(true);
        System.out.println("di sini + " + nip);
        this.nip = nip;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.now();
        String tanggal = dtf.format(localDate);
        tambahjurnal.setTanggal(tanggal);
        tambahjurnal.tambahListener(new simpanjurnalListener());
        tambahjurnal.submitListener(new sumbit());
        tambahjurnal.pendataan().setEnabled(false);
        tambahjurnal.laporan().setEnabled(false);
        tambahjurnal.kembaliListener(new kembali());
        tambahjurnal.selesaiListener(new selesai());
        tambahjurnal.setResizable(false);
        tambahjurnal.setLocationRelativeTo(null);
        tambahjurnal.addKeterangan(new tambahketerangan());
        tambahjurnal.addObPajak(new tambahObyek());
        tambahjurnal.kegkeg().disable();
    }

    public controllerUser(String nip, int a, int b, String nama) {
        laporan = new laporan();
        mPegawai = new pegawai();
        laporan.setVisible(true);
        System.out.println("di sini + " + nip);
        this.nip = nip;
        this.nama = nama;
        status = 2;
        laporan.setResizable(false);
        laporan.setLocationRelativeTo(null);
        laporan.setNIP(nip);
        laporan.setNama(mPegawai.getNama(nip));
        laporan.setJabatan(mPegawai.getJabatan(nip));
        laporan.setPangkat(mPegawai.getPangkat(nip));
        laporan.setGol(mPegawai.getGolongan(nip));
        laporan.setJabatan(mPegawai.getJabatan(nip));
        laporan.bulan(new caribulan2Listener());
        laporan.range(new carirangeListener());
        laporan.exportListener(new exportLaporan());
        laporan.laporan().setEnabled(false);
        laporan.pendataanListener(new laporankependataan());
        laporan.tabeljurnal(mPegawai.bacaJurnal(nip));
        laporan.export2Listener(new exportRange());
        laporan.export().setEnabled(false);
        laporan.export2().setEnabled(false);
        laporan.keluarListener(new keluarlaporan());

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

    private class sumbit implements ActionListener {

        public sumbit() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            String lokasi = tambahjurnal.getLokasi();
            String kegiatan = tambahjurnal.getKegiatan();
            int obyek = Integer.valueOf(tambahjurnal.getObyek());
            String namanya = tambahjurnal.getNamanya();
            if (lokasi != null && namanya != null && !lokasi.equalsIgnoreCase("") && !namanya.equalsIgnoreCase("")) {
                boolean test = mPegawai.tambahAwal(nip, namanya, lokasi, obyek);
                JOptionPane.showMessageDialog(tambahjurnal, "Nama Wajib Pajak berhasil dimasukkan, Masukkan hasil pendataan.");
                tambahjurnal.lokasi1().setEnabled(false);
                tambahjurnal.nama().setEnabled(false);
                tambahjurnal.add().setEnabled(false);
                tambahjurnal.obyek().disable();
                tambahjurnal.tambahobpajak().setEnabled(false);
                tambahjurnal.kegkeg().enable();
            } else if (lokasi == null && namanya == null && lokasi.equalsIgnoreCase("") && namanya.equalsIgnoreCase("")&&kegiatan!=null &&!kegiatan.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(tambahjurnal, "Nama wajib pajak, lokasi, dan obyek pajak harus terisi terlebih dahulu");
            }
        }
    }

    private class selesai implements ActionListener {

        public selesai() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String namanya = tambahjurnal.getNamanya();
            int pilihan = JOptionPane.showConfirmDialog(tambahjurnal, "Apakah pendataan dengan nama wajib pajak " + namanya + " sudah selesai?", " Konfirmasi", JOptionPane.YES_NO_OPTION);

            if (pilihan == JOptionPane.NO_OPTION) {
                tambahjurnal.tabeljurnal(mPegawai.bacaJurnalNowSatuLokasi(nip, namanya));
            } else if (pilihan == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(tambahjurnal, "Pendataan dengan nama wajib pajak " + namanya + " Selesai");
                tambahjurnal.dispose();
                new controllerUser(nip, mPegawai.getNama(nip));
            } else {
                JOptionPane.showMessageDialog(tambahjurnal, "Kesalahan jaringan");
                tambahjurnal.tabeljurnal(mPegawai.bacaJurnalNowSatuLokasi(nip, namanya));
            }

        }
    }

    private class keluarlaporan implements ActionListener {

        public keluarlaporan() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int pilihan = JOptionPane.showConfirmDialog(laporan, "Anda yakin ingin keluar? ", " Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (pilihan == JOptionPane.YES_OPTION) {
                new controllerUser();
                laporan.dispose();
            }
        }
    }

    private class keluarPendataan implements ActionListener {

        public keluarPendataan() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int pilihan = JOptionPane.showConfirmDialog(pendataan, "Anda yakin ingin keluar? ", " Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (pilihan == JOptionPane.YES_OPTION) {
                new controllerUser();
                pendataan.dispose();
            }
        }
    }

    private class tambahObyek implements ActionListener {

        public tambahObyek() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String ob = "";
            ob = JOptionPane.showInputDialog("Masukkan obyek pajak tambahan");
            System.out.println(ob);
            if (ob != null || !ob.equalsIgnoreCase("")) {
                boolean tambah = mPegawai.tambahobyek(ob);
                String mObyek[][];
                mObyek = mPegawai.getObyekPajak();
                tambahjurnal.setObyek(mObyek);
                tambahjurnal.obbaru(ob);
            } else {
                JOptionPane.showMessageDialog(tambahjurnal, "Tidak ada obyek pajak tambahan yg dimasukkan");
            }
        }
    }

    private class tambahketerangan implements ActionListener {

        public tambahketerangan() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String ket = "";
            ket = JOptionPane.showInputDialog("Masukkan keterangan tambahan");
            System.out.println(ket);
            if (ket != null || !ket.equalsIgnoreCase("")) {
                boolean tambah = mPegawai.tambahketerangan(ket);
                String mKeterangan[][];
                mKeterangan = mPegawai.getKeterangan();
                tambahjurnal.setKet(mKeterangan);
                tambahjurnal.ketbaru(ket);
            } else {
                JOptionPane.showMessageDialog(tambahjurnal, "Tidak ada keterangan tambahan yg dimasukkan");
            }
        }
    }

    private class resettabel implements ActionListener {

        public resettabel() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            pendataan.tabeljurnal(mPegawai.bacaJurnalNow(nip));
            pendataan.tahun().setEnabled(true);
            pendataan.bulanButton().setEnabled(true);
            pendataan.tanggalButton().setEnabled(true);
        }
    }

    private class laporankependataan implements ActionListener {

        public laporankependataan() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            laporan.dispose();
            new controllerUser(nip, nama);
        }
    }

    private class kembali implements ActionListener {

        public kembali() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.valueOf(mPegawai.getID(nip));
            String namanya = tambahjurnal.getNamanya();
            String lokasi = tambahjurnal.getLokasi();
            int pilihan = JOptionPane.showConfirmDialog(tambahjurnal, "Kembali ke beranda? Apabila Anda telah memasukkan data,"
                    + "/n data tidak akan tersimpan", " Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (pilihan == JOptionPane.YES_OPTION && namanya != null || lokasi != null || !namanya.equalsIgnoreCase("") || !lokasi.equalsIgnoreCase("")) {
                boolean hapus = mPegawai.hapusDetail(id);
                boolean hapusjuga = mPegawai.hapusJurnal(id);
                JOptionPane.showMessageDialog(tambahjurnal, "Data tidak tersimpan.");
                tambahjurnal.dispose();
               
                new controllerUser(nip, nama);

            } else if (pilihan == JOptionPane.NO_OPTION && namanya == null && lokasi == null) {
                tambahjurnal.tabeljurnal(mPegawai.bacaJurnalNowSatuLokasi(nip, namanya));
            }

        }
    }

    private class exportLaporan implements ActionListener {

        public exportLaporan() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String nama = laporan.getNama();
            System.out.println("namanya= " + nama);
            String jabatan = laporan.getJabatan();
            Date tanggal = laporan.getTanggal();
            DateFormat dtf = new SimpleDateFormat("dd MM yyyy");
            String lala = dtf.format(tanggal);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String bulan = lala.substring(3, 10);
            try {
                mPegawai.export(nip, nama, jabatan, tanggal);
                JOptionPane.showMessageDialog(laporan, "Laporan Kinerja berhasil di eksport\n" + System.getProperty("user.home") + "/Downloads/bapenda/");
            } catch (IOException ex) {
                Logger.getLogger(controllerUser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(controllerUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class exportRange implements ActionListener {

        public exportRange() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String nama = laporan.getNama();
            String jabatan = laporan.getJabatan();
            Date tanggal1 = laporan.getTanggalAwal();
            Date tanggal2 = laporan.getTanggalAkhir();
            try {
                mPegawai.export2(nip, nama, jabatan, tanggal1, tanggal2);
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
                pendataan.tabeljurnal(mPegawai.bacaTabelJurnalTahun(nip, pendataan.getTanggal()));

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
            laporan.export().setEnabled(false);
            laporan.export2().setEnabled(true);
            try {
                laporan.tabeljurnal(mPegawai.bacaTabelJurnalRange(nip, laporan.getTanggalAwal(), laporan.getTanggalAkhir()));
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
            new controllerUser(nip, 1, 2, nama);
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

    private class caribulan2Listener implements ActionListener {

        public caribulan2Listener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            bacaByMonth();
            laporan.export2().setEnabled(false);
            laporan.export().setEnabled(true);
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
            String lokasi = tambahjurnal.getLokasi();
            int ket = Integer.valueOf(tambahjurnal.getKet());
            int obyek = Integer.valueOf(tambahjurnal.getObyek());
            String namanya = tambahjurnal.getNamanya();
            int id = Integer.valueOf(mPegawai.getID(nip));
            System.out.println("ini id jurnalnya =" + id);

            if (kegiatan.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(login, "Kegiatan tidak boleh kosong");
            } else if(lokasi.equalsIgnoreCase("")&&namanya.equalsIgnoreCase("")&&kegiatan!=null){
                JOptionPane.showMessageDialog(tambahjurnal,"Nama Wajib Pajak dan lokasi harus diisi dan ditambahkan terlebih dulu!");
            } 
            else {
                boolean test = mPegawai.tambahJurnal(id, kegiatan, ket);
                JOptionPane.showMessageDialog(login, "Kegiatan berhasil ditambahkan. Masukkan data kegiatan dengan lokasi yang sama apabila masih ada, kemudian klik simpan.");
                tambahjurnal.tabeljurnal(mPegawai.bacaJurnalNowSatuLokasi(nip, namanya));
                tambahjurnal.keg("");
            }

        }
    }

    private class loginListener implements ActionListener {

        public loginListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            nama = login.getNama();
            nip = login.nip();
            System.out.println(nama);
            System.out.println(nip);
            pangkat = mPegawai.getPangkat(nip);
            if (nama.equalsIgnoreCase("") || nip.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(login, "Nama atau NIP tidak boleh kosong");
            } else {
                int level = mPegawai.login(nama, nip);
                if (level == 0) {
                    JOptionPane.showMessageDialog(login, "NIP tidak sesuai dengan nama yang dipilih");
                } else if (level == 1) {
                    String jabatan1 = mPegawai.getJabatan(login.nip());
                    System.out.println("jabatannya adalah= " + jabatan1);
                    System.out.println(nip);
                    new controllerUser(nip, nama);
                    login.dispose();
                    JOptionPane.showMessageDialog(pendataan, "Selamat datang NIP " + nama);

                }
            }
        }
    }
}
