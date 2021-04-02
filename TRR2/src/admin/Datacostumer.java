/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
//import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author asus
 */
public class Datacostumer extends javax.swing.JFrame {

    /**
     * Creates new form Datacostumer
     */
    public Datacostumer() {

        initComponents();
    }

    
    void update(int id_cos, String name_cos, String alamat_cos, String birthdate_cos, String nohp_cos, String kelamin_cos, String cekin_cos, String cekout_cos, String type_cos, String kamar_cos, String price_cos) {

        try {
            URL url = new URL("http://localhost:8080/TR2Hotel/webresources/AdminController/updatecostumer");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String input = "{\"id_cos\":\"" + id_cos + "\",\"name_cos\":\"" + name_cos + "\",\"alamat_cos\":\"" + alamat_cos 
                    + "\",\"birthdate_cos\":\"" + birthdate_cos + "\",\"nohp_cos\":\"" + nohp_cos+ "\",\"kelamin_cos\":\"" + kelamin_cos
                    + "\",\"cekin_cos\":\"" + cekin_cos + "\",\"cekout_cos\":\"" + cekout_cos + "\",\"type_cos\":\"" + type_cos 
                    + "\",\"kamar_cos\":\"" + kamar_cos + "\",\"price_cos\":\"" + price_cos + "\"}";
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Gagal => HTTP error code: " + conn.getResponseCode());
            }

            JOptionPane.showMessageDialog(null, "Data Berhasil Diinputkan!");

            conn.disconnect();
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    void delete(int id) {

        try {
            URL url = new URL("http://localhost:8080/TR2Hotel/webresources/AdminController/deletecostumer");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String input = "{\"id_cos\":" + id + "}";
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Gagal => HTTP error code: " + conn.getResponseCode());
            }

            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus!");

            conn.disconnect();
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    void insert(int id_cos, String name_cos, String alamat_cos, String birthdate_cos, String nohp_cos, String kelamin_cos, String cekin_cos, String cekout_cos, String type_cos, String kamar_cos, String price_cos) {

        try {
            URL url = new URL("http://localhost:8080/TR2Hotel/webresources/AdminController/inputcostumer");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String input = "{\"id_cos\":\"" + id_cos + "\",\"name_cos\":\"" + name_cos + "\",\"alamat_cos\":\"" + alamat_cos 
                    + "\",\"birthdate_cos\":\"" + birthdate_cos + "\",\"nohp_cos\":\"" + nohp_cos+ "\",\"kelamin_cos\":\"" + kelamin_cos
                    + "\",\"cekin_cos\":\"" + cekin_cos + "\",\"cekout_cos\":\"" + cekout_cos + "\",\"type_cos\":\"" + type_cos 
                    + "\",\"kamar_cos\":\"" + kamar_cos + "\",\"price_cos\":\"" + price_cos + "\"}";
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Gagal => HTTP error code: " + conn.getResponseCode());
            }

            JOptionPane.showMessageDialog(null, "Data Berhasil Diinputkan!");

            conn.disconnect();
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    void tampildata() {
        DefaultTableModel dt;
        Object[] baris = {"ID COSTUMER", "USERNAME", "ALAMAT", "Tanggal Lahir", "No.Hp", "Jenis Kelamin", "Tanggal Checkin","Tanggal Checkout", "Type Kamar", "No. Kamar", "Harga"};
        dt = new DefaultTableModel(null, baris);
        tabel_pemesan.setModel(dt);
        try {
            URL url = new URL("http://localhost:8080/TR2Hotel/webresources/AdminController/getdatacostumer");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Gagal => HTTP error code: " + conn.getResponseCode());
            }

            JSONParser parser = new JSONParser();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String opt;

            while ((opt = br.readLine()) != null) {
                try {
                    Object obj = parser.parse(opt);
                    JSONArray s_data = (JSONArray) obj;

                    for (int i = 0; i < s_data.size(); i++) {
                        JSONObject s = (JSONObject) s_data.get(i);
                        String ID_COS = (String) s.get("id_cos").toString();
                        String NAME_COS = (String) s.get("name_cos");
                        String ALAMAT_COS = (String) s.get("alamat_cos");
                        String BIRTHDATE_COS = (String) s.get("birthdate_cos").toString();
                        String NOHP_COS = (String) s.get("nohp_cos");
                        String KELAMIN_COS = (String) s.get("kelamin_cos");
                        String CEKIN_COS = (String) s.get("cekin_cos").toString();
                        String CEKOUT_COS = (String) s.get("cekout_cos").toString();
                        String TYPE_COS = (String) s.get("type_cos");
                        String KAMAR_COS = (String) s.get("kamar_cos");
                        String PRICE_COS = (String) s.get("price_cos").toString();
                        String[] data = {ID_COS, NAME_COS, ALAMAT_COS, BIRTHDATE_COS, NOHP_COS, KELAMIN_COS, CEKIN_COS, CEKOUT_COS, TYPE_COS, KAMAR_COS, PRICE_COS };
                        dt.addRow(data);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            conn.disconnect();
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
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
        tabel_pemesan = new javax.swing.JTable();
        cekin = new javax.swing.JLabel();
        jkelamin = new javax.swing.JLabel();
        nohp = new javax.swing.JLabel();
        tlahir = new javax.swing.JLabel();
        txt_harga = new javax.swing.JTextField();
        dlt = new javax.swing.JButton();
        Kembali = new javax.swing.JButton();
        Reset = new javax.swing.JButton();
        insrt = new javax.swing.JButton();
        price = new javax.swing.JLabel();
        txt_tykamar = new javax.swing.JComboBox();
        txt_kelamin = new javax.swing.JComboBox();
        txt_nokamar = new javax.swing.JComboBox();
        typekamar = new javax.swing.JLabel();
        kamar = new javax.swing.JLabel();
        txt_hp = new javax.swing.JTextField();
        updt = new javax.swing.JButton();
        idk1 = new javax.swing.JLabel();
        price1 = new javax.swing.JLabel();
        cekout = new javax.swing.JLabel();
        txt_checkout = new com.toedter.calendar.JDateChooser();
        txt_birthdate = new com.toedter.calendar.JDateChooser();
        txt_checkin = new com.toedter.calendar.JDateChooser();
        txt_id = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        iduser = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        username1 = new javax.swing.JLabel();
        alamt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabel_pemesan.setBackground(new java.awt.Color(153, 153, 153));
        tabel_pemesan.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        tabel_pemesan.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        tabel_pemesan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Costumer", "username", "alamat", "tanggal lahir", "No.Hp", "Jenis Kelamin", "Tanggal Checkin", "Tanggal Checkout", "Type kamar", "No. Kamar", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabel_pemesan.setGridColor(new java.awt.Color(0, 0, 0));
        tabel_pemesan.setSelectionBackground(new java.awt.Color(255, 102, 102));
        tabel_pemesan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pemesanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabel_pemesanMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_pemesan);

        cekin.setFont(new java.awt.Font("News706 BT", 0, 12)); // NOI18N
        cekin.setText("Check In :");

        jkelamin.setFont(new java.awt.Font("News706 BT", 0, 12)); // NOI18N
        jkelamin.setText("Jenis Kelamin :");

        nohp.setFont(new java.awt.Font("News706 BT", 0, 12)); // NOI18N
        nohp.setText("No. HP :");

        tlahir.setFont(new java.awt.Font("News706 BT", 0, 12)); // NOI18N
        tlahir.setText("Tanggal Lahir :");

        txt_harga.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        txt_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hargaActionPerformed(evt);
            }
        });

        dlt.setFont(new java.awt.Font("Arial Black", 3, 12)); // NOI18N
        dlt.setText("Delete");
        dlt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dltMouseClicked(evt);
            }
        });
        dlt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dltActionPerformed(evt);
            }
        });

        Kembali.setFont(new java.awt.Font("Arial Black", 3, 14)); // NOI18N
        Kembali.setText("Kembali");
        Kembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliMouseClicked(evt);
            }
        });
        Kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KembaliActionPerformed(evt);
            }
        });

        Reset.setFont(new java.awt.Font("Arial Black", 3, 12)); // NOI18N
        Reset.setText("Reset");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });

        insrt.setFont(new java.awt.Font("Arial Black", 3, 12)); // NOI18N
        insrt.setText("Insert");
        insrt.setBorder(null);
        insrt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                insrtMouseClicked(evt);
            }
        });
        insrt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insrtActionPerformed(evt);
            }
        });

        price.setFont(new java.awt.Font("News706 BT", 0, 10)); // NOI18N
        price.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        price.setText("Rp");

        txt_tykamar.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        txt_tykamar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Type Kamar", "Single", "Double", "Family" }));
        txt_tykamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tykamarActionPerformed(evt);
            }
        });

        txt_kelamin.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        txt_kelamin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Jenis Kelamin", "Perempuan", "Laki-laki" }));
        txt_kelamin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kelaminActionPerformed(evt);
            }
        });

        txt_nokamar.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        txt_nokamar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih No. Kamar", "001", "002", "003", "004", "005", "006", "007", "008", "009", "010", "011", "012", "013", "014", "015", "016", "017", "018", "019", "020", "021", "022", "023", "024", "025", "026", "027", "028", "029", "030", "031", "032", "033", "034", "035", "036", "037", "038", "039", "040", "041", "042", "043", "044", "045", "046", "047", "048", "049", "050" }));
        txt_nokamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nokamarActionPerformed(evt);
            }
        });

        typekamar.setFont(new java.awt.Font("News706 BT", 0, 12)); // NOI18N
        typekamar.setText("Type Kamar :");

        kamar.setFont(new java.awt.Font("News706 BT", 0, 12)); // NOI18N
        kamar.setText("No. Kamar :");

        txt_hp.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        txt_hp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hpActionPerformed(evt);
            }
        });

        updt.setFont(new java.awt.Font("Arial Black", 3, 12)); // NOI18N
        updt.setText("Update");
        updt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updtMouseClicked(evt);
            }
        });
        updt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updtActionPerformed(evt);
            }
        });

        idk1.setFont(new java.awt.Font("DFPOP1-W9", 1, 36)); // NOI18N
        idk1.setForeground(new java.awt.Color(0, 102, 102));
        idk1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        idk1.setText("Data Costumer di Hotel ReG");

        price1.setFont(new java.awt.Font("News706 BT", 0, 12)); // NOI18N
        price1.setText("Harga :");

        cekout.setFont(new java.awt.Font("News706 BT", 0, 12)); // NOI18N
        cekout.setText("Check Out :");

        txt_id.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });

        txt_alamat.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        txt_alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_alamatActionPerformed(evt);
            }
        });

        txt_name.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });

        iduser.setFont(new java.awt.Font("News706 BT", 0, 12)); // NOI18N
        iduser.setText("ID :");

        username.setFont(new java.awt.Font("News706 BT", 0, 12)); // NOI18N
        username.setText("Username");

        username1.setFont(new java.awt.Font("News706 BT", 0, 12)); // NOI18N
        username1.setText("Username");

        alamt.setFont(new java.awt.Font("News706 BT", 0, 12)); // NOI18N
        alamt.setText("Alamat");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tlahir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iduser, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alamt, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_id)
                    .addComponent(txt_birthdate, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(txt_alamat, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_name))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nohp, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jkelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cekin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cekout, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_hp)
                            .addComponent(txt_kelamin, 0, 161, Short.MAX_VALUE)
                            .addComponent(txt_checkin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_checkout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(109, 109, 109)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(typekamar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kamar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(price1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_tykamar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_harga)
                                .addComponent(txt_nokamar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(196, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(322, 322, 322)
                            .addComponent(idk1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(246, 246, 246)
                            .addComponent(insrt, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(updt, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(14, 14, 14)
                            .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(8, 8, 8)
                            .addComponent(dlt, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(28, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(602, 602, 602)
                    .addComponent(username1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(603, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(typekamar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(kamar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_nokamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_tykamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(price1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(10, 10, 10)
                        .addComponent(txt_birthdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cekout, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_checkout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nohp, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_hp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(iduser, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jkelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_kelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(25, 25, 25)
                                        .addComponent(cekin))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txt_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(alamt, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(txt_checkin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tlahir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(486, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(idk1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(insrt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dlt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(updt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(353, 353, 353)
                    .addComponent(username1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(354, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabel_pemesanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pemesanMouseClicked
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) tabel_pemesan.getModel();

        // get the selected row index
        int selectedRowIndex = tabel_pemesan.getSelectedRow();

        txt_id.setText(model.getValueAt(selectedRowIndex,0).toString());
        txt_name.setText(model.getValueAt(selectedRowIndex,1).toString());
        txt_alamat.setText(model.getValueAt(selectedRowIndex,2).toString());
        txt_birthdate.setDateFormatString(model.getValueAt(selectedRowIndex,3).toString());
        txt_hp.setText(model.getValueAt(selectedRowIndex,4).toString());
        txt_kelamin.setSelectedItem(model.getValueAt(selectedRowIndex,5).toString());
        txt_checkin.setDateFormatString(model.getValueAt(selectedRowIndex,6).toString());
        txt_checkout.setDateFormatString(model.getValueAt(selectedRowIndex,7).toString());
        txt_tykamar.setSelectedItem(model.getValueAt(selectedRowIndex,8).toString());
        txt_nokamar.setSelectedItem(model.getValueAt(selectedRowIndex,9).toString());
        txt_harga.setText(model.getValueAt(selectedRowIndex,10).toString());
    }//GEN-LAST:event_tabel_pemesanMouseClicked

    private void txt_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hargaActionPerformed

    private void dltMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dltMouseClicked
        int id = Integer.parseInt(txt_id.getText());
        delete(id);
        tampildata();
    }//GEN-LAST:event_dltMouseClicked

    private void dltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dltActionPerformed

    }//GEN-LAST:event_dltActionPerformed

    private void KembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KembaliActionPerformed
       
    }//GEN-LAST:event_KembaliActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed

       // clearInput();
    }//GEN-LAST:event_ResetActionPerformed

    private void insrtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insrtActionPerformed
        //Membuat format tanggal di Java sama dengan Mysql
        
    }//GEN-LAST:event_insrtActionPerformed

    private void txt_tykamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tykamarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tykamarActionPerformed

    private void txt_kelaminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kelaminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kelaminActionPerformed

    private void txt_nokamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nokamarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nokamarActionPerformed

    private void txt_hpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hpActionPerformed

    private void updtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updtActionPerformed
        //Membuat format tanggal di Java sama dengan Mysql
    }//GEN-LAST:event_updtActionPerformed

    private void KembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembaliMouseClicked
        admin lgn = new admin();
        lgn.setVisible(true);
        this.dispose();    }//GEN-LAST:event_KembaliMouseClicked

    private void insrtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insrtMouseClicked
String tampilan ="yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            int id_cos =Integer.parseInt(txt_id.getText());
            String name_cos = txt_name.getText();
            String alamat_cos = txt_alamat.getText();
            String birthdate_cos = String.valueOf(fm.format(txt_birthdate.getDate()));
            String nohp_cos=txt_hp.getText();
            String kelamin_cos=txt_kelamin.getSelectedItem().toString();
            String cekin_cos = String.valueOf(fm.format(txt_checkin.getDate()));
            String cekout_cos = String.valueOf(fm.format(txt_checkout.getDate()));
            String type_cos=txt_tykamar.getSelectedItem().toString();
            String kamar_cos=txt_nokamar.getSelectedItem().toString();
            String price_cos=txt_harga.getText();

            insert(id_cos, name_cos, alamat_cos, birthdate_cos, nohp_cos, kelamin_cos, cekin_cos, cekout_cos, type_cos, kamar_cos, price_cos);
            //Menampilkan data hasil inputan
            tampildata();
            //Menghapus inputan
           // clearInput();
                    }//GEN-LAST:event_insrtMouseClicked

    private void updtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updtMouseClicked
        String tampilan ="yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            int id_cos =Integer.parseInt(txt_id.getText());
            String name_cos = txt_name.getText();
            String alamat_cos = txt_alamat.getText();
            String birthdate_cos = String.valueOf(fm.format(txt_birthdate.getDate()));
            String nohp_cos=txt_hp.getText();
            String kelamin_cos=txt_kelamin.getSelectedItem().toString();
            String cekin_cos = String.valueOf(fm.format(txt_checkin.getDate()));
            String cekout_cos = String.valueOf(fm.format(txt_checkout.getDate()));
            String type_cos=txt_tykamar.getSelectedItem().toString();
            String kamar_cos=txt_nokamar.getSelectedItem().toString();
            String price_cos=txt_harga.getText();
            update(id_cos, name_cos, alamat_cos, birthdate_cos, nohp_cos, kelamin_cos, cekin_cos, cekout_cos, type_cos, kamar_cos, price_cos);

            //Menampilkan data hasil inputan
            tampildata();
            //Menampilkan feedback dari server
            //JOptionPane.showMessageDialog(null, result,"Sukses",JOptionPane.INFORMATION_MESSAGE);
            //Menghapus inputan
            //clearInput();
            
    }//GEN-LAST:event_updtMouseClicked

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void txt_alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_alamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_alamatActionPerformed

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameActionPerformed

    private void tabel_pemesanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pemesanMouseEntered
        tampildata();
    }//GEN-LAST:event_tabel_pemesanMouseEntered

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
            java.util.logging.Logger.getLogger(Datacostumer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Datacostumer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Datacostumer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Datacostumer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Datacostumer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Kembali;
    public javax.swing.JButton Reset;
    public javax.swing.JLabel alamt;
    public javax.swing.JLabel cekin;
    public javax.swing.JLabel cekout;
    public javax.swing.JButton dlt;
    public javax.swing.JLabel idk1;
    public javax.swing.JLabel iduser;
    public javax.swing.JButton insrt;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel jkelamin;
    public javax.swing.JLabel kamar;
    public javax.swing.JLabel nohp;
    public javax.swing.JLabel price;
    public javax.swing.JLabel price1;
    public javax.swing.JTable tabel_pemesan;
    public javax.swing.JLabel tlahir;
    public javax.swing.JTextField txt_alamat;
    public com.toedter.calendar.JDateChooser txt_birthdate;
    public com.toedter.calendar.JDateChooser txt_checkin;
    public com.toedter.calendar.JDateChooser txt_checkout;
    public javax.swing.JTextField txt_harga;
    public javax.swing.JTextField txt_hp;
    public javax.swing.JTextField txt_id;
    public javax.swing.JComboBox txt_kelamin;
    public javax.swing.JTextField txt_name;
    public javax.swing.JComboBox txt_nokamar;
    public javax.swing.JComboBox txt_tykamar;
    public javax.swing.JLabel typekamar;
    public javax.swing.JButton updt;
    public javax.swing.JLabel username;
    public javax.swing.JLabel username1;
    // End of variables declaration//GEN-END:variables

    
}
