/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import model.pegawai;

/**
 *
 * @author Dheta
 */
public class Pendataan extends javax.swing.JFrame {

    /**
     * Creates new form Pendataan
     */
    Connection koneksi;
    pegawai mPegawai;
    DateFormat date;

    public Pendataan() {
        mPegawai = new pegawai();

        date = new SimpleDateFormat("yyyy-MM-dd");
        this.date = DateFormat.getDateInstance();
        initComponents();
    }

    public Date getTanggal() {
        Date tanggal = (calendar.getDate());
        return tanggal;
    }
    
    public void setTanggal(String tanggal){
        
            this.tanggalnow.setText(tanggal);
    }

    public void tambahListener(ActionListener a) {
        tambahJurnal.addActionListener(a);
    }

    public void cariListener(ActionListener a) {
        cari.addActionListener(a);
    }

    public void cariBulanListener(ActionListener a) {
        cariBulan.addActionListener(a);
    }
    
    public void laporanListener(ActionListener a){
        laporan.addActionListener(a);
    }
    
    public void tahunListener(ActionListener a){
        tahun.addActionListener(a);
    }
    
    public void resetListener(ActionListener a){
        reset.addActionListener(a);
    }

    public JButton tanggalButton() {
        return cari;
    }
    
    public JButton reset(){
        return reset;
    }

    public JButton bulanButton() {
        return cariBulan;
    }
    
    public JButton tahun(){
        return tahun;
    }

    public JButton pendataan() {
        return pendataan;
    }

    public JButton laporan() {
        return laporan;
    }

    public JLabel getNama() {
        return nama;
    }

    public JLabel getNIP() {
        return nip;
    }

    public JLabel getJabatan() {
        return jabatan;
    }

    public JLabel getPangkat() {
        return pangkat;
    }

    public void tabeljurnal(DefaultTableModel t) {
        jurnal.setModel(t);
    }

    public void setNama(String a) {
        this.nama.setText(a);
    }

    public void setNIP(String a) {
        this.nip.setText(a);
    }

    public void setJabatan(String a) {
        this.jabatan.setText(a);
    }

    public void setPangkat(String a) {
        this.pangkat.setText(a);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jurnal = new javax.swing.JTable();
        tambahJurnal = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cariBulan = new javax.swing.JButton();
        cari = new javax.swing.JButton();
        calendar = new org.jdesktop.swingx.JXDatePicker();
        pangkat = new javax.swing.JLabel();
        jabatan = new javax.swing.JLabel();
        nip = new javax.swing.JLabel();
        nama = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        laporan = new javax.swing.JButton();
        pendataan = new javax.swing.JButton();
        tahun = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        tanggalnow = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jurnal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tanggal", "Nama", "Kegiatan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jurnal);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 460, 230));

        tambahJurnal.setText("tambah jurnal");
        getContentPane().add(tambahJurnal, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, -1, -1));

        jLabel2.setText("Tanggal:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        cariBulan.setText("cari bulan");
        getContentPane().add(cariBulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, -1, -1));

        cari.setText("cari tanggal");
        getContentPane().add(cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, -1, -1));

        calendar.setBackground(new java.awt.Color(255, 75, 109));
        calendar.setForeground(new java.awt.Color(255, 255, 255));
        calendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calendarActionPerformed(evt);
            }
        });
        getContentPane().add(calendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 160, -1));
        getContentPane().add(pangkat, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 160, 20));
        getContentPane().add(jabatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 160, 20));
        getContentPane().add(nip, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 160, 20));
        getContentPane().add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 160, 20));

        jLabel5.setText("NIP:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel6.setText("Jabatan:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel7.setText("Pangkat:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel8.setText("Nama:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        laporan.setText("Laporan");
        getContentPane().add(laporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        pendataan.setText("Pendataan");
        getContentPane().add(pendataan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        tahun.setText("cari tahun");
        getContentPane().add(tahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 200, -1, -1));

        reset.setText("reset tabel");
        getContentPane().add(reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, -1, -1));
        getContentPane().add(tanggalnow, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 160, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void calendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calendarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_calendarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pendataan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pendataan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pendataan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pendataan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pendataan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker calendar;
    private javax.swing.JButton cari;
    private javax.swing.JButton cariBulan;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jabatan;
    private javax.swing.JTable jurnal;
    private javax.swing.JButton laporan;
    private javax.swing.JLabel nama;
    private javax.swing.JLabel nip;
    private javax.swing.JLabel pangkat;
    private javax.swing.JButton pendataan;
    private javax.swing.JButton reset;
    private javax.swing.JButton tahun;
    private javax.swing.JButton tambahJurnal;
    private javax.swing.JLabel tanggalnow;
    // End of variables declaration//GEN-END:variables
}
