/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import javax.swing.table.DefaultTableModel;
import dba.Mysql;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author User
 */
public class CapD extends javax.swing.JFrame {
  
    FondoPanel fondo = new FondoPanel();
    
    private int cantidad;
    private int i;
    private boolean codViv;
    
    DefaultTableModel model;
    Connection conn;
    Statement sent;
    /**
     * Creates new form CapD
     */
    public CapD() {
        this.setContentPane(fondo);
        initComponents();
        
        conn = Mysql.getConnection();
        Llenar();
        i = 1;
        cantidad = 0;
    }

    private void Llenar(){
        try {
            conn = Mysql.getConnection();
            String[]titulos = {"codVivienda","personaMuerta","cantMuertos"}; 
            String sql = "select * from cap_d";
            model = new DefaultTableModel(null, titulos);
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            
            String[]fila = new String[3];
            while(rs.next()){
                fila[0]=rs.getString("codVivienda");
                fila[1]=rs.getString("personaMuerta");
                fila[2]=rs.getString("cantMuertos");
                model.addRow(fila);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void LlenarPersonasMuertas(){
        try {
            conn = Mysql.getConnection();
            String[]titulos = {"codVivienda","numPersona","nomPersona","edadPersona"
                              ,"sexoPersona","causaMuerte"}; 
            String sql = "select * from cap_dmuertos";
            model = new DefaultTableModel(null, titulos);
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            
            String[]fila = new String[6];
            while(rs.next()){
                fila[0]=rs.getString("codVivienda");
                fila[1]=rs.getString("numPersona");
                fila[2]=rs.getString("nomPersona");
                fila[3]=rs.getString("edadPersona");
                fila[4]=rs.getString("sexoPersona");
                fila[5]=rs.getString("causaMuerte");
                
                model.addRow(fila);
            }
        }
        catch (Exception e) {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JtxfCodV = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        JrbSi = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        JtxfCantP = new javax.swing.JTextField();
        JrbNo = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        JtxfEdadMuerte = new javax.swing.JTextField();
        JrbHombre = new javax.swing.JRadioButton();
        JrbEmbarazo = new javax.swing.JRadioButton();
        JrbParto = new javax.swing.JRadioButton();
        JrbSobreparto = new javax.swing.JRadioButton();
        JrbOtraCausa = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        JtxfNomP = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        JtxfNumero = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        JrbMujer = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1154, 750));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 621, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        jLabel1.setText("CAPÍTULO D. MORTALIDAD");
        jLabel1.setToolTipText("");

        jLabel2.setText("Codigo Vivienda");

        JtxfCodV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtxfCodVActionPerformed(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(966, 585));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel3.setText("21. De enero a diciembre del año pasado,¿murio alguna persona que vivia con usted en este hogar?");

        buttonGroup1.add(JrbSi);
        JrbSi.setText("Si");
        JrbSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JrbSiActionPerformed(evt);
            }
        });

        jLabel4.setText("¿cuantas personas?");

        JtxfCantP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtxfCantPActionPerformed(evt);
            }
        });

        buttonGroup1.add(JrbNo);
        JrbNo.setText("No");

        jButton1.setText("Pase a la pregunta 22");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setText("(Anote en el siguiente cuadro, los datos de cada una de las personas que fallecieron. ");

        jLabel7.setText("No olvide a niñas, niños, recién nacidos, ancianas y ancianos)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 811, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 811, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(JrbSi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JtxfCantP, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(203, 203, 203)
                        .addComponent(JrbNo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 811, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JrbSi, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(JtxfCantP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JrbNo)
                    .addComponent(jButton1))
                .addGap(30, 30, 30)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(22, 22, 22))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        buttonGroup3.add(JrbHombre);
        JrbHombre.setText("Hombre");

        buttonGroup2.add(JrbEmbarazo);
        JrbEmbarazo.setText("el embarazo");

        buttonGroup2.add(JrbParto);
        JrbParto.setText("el parto");
        JrbParto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JrbPartoActionPerformed(evt);
            }
        });

        buttonGroup2.add(JrbSobreparto);
        JrbSobreparto.setText("el sobreparto(hasta dos meses despues del parto)");
        JrbSobreparto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JrbSobrepartoActionPerformed(evt);
            }
        });

        buttonGroup2.add(JrbOtraCausa);
        JrbOtraCausa.setText("otra causa");
        JrbOtraCausa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JrbOtraCausaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel8.setText("Nombre de la persona");

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel9.setText("¿Que edad tenia al morir?");

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel10.setText("(Anote 0 para menores de 1 año)");

        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel11.setText("La persona era:");

        jLabel12.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel12.setText("Si era mujer de 15 años o mas de edad, murio por");

        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel13.setText("alguna causa relacionada con...");

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel5.setText("N°");

        JtxfNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtxfNumeroActionPerformed(evt);
            }
        });

        jButton4.setText("Guardar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        buttonGroup3.add(JrbMujer);
        JrbMujer.setText("Mujer");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(JtxfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(59, 59, 59)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(jLabel11)))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(JtxfNomP, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(JtxfEdadMuerte, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JrbHombre, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JrbMujer, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JrbOtraCausa, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JrbSobreparto)
                                    .addComponent(JrbEmbarazo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JrbParto, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addGap(30, 30, 30))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addGap(12, 12, 12))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(JrbEmbarazo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JrbParto)
                            .addComponent(JrbMujer))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JrbSobreparto)
                            .addComponent(JrbHombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JrbOtraCausa))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JtxfNomP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JtxfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JtxfEdadMuerte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)))
                .addGap(10, 10, 10)
                .addComponent(jButton4)
                .addGap(50, 50, 50))
        );

        jButton2.setText("Atras");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Siguiente");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(51, 51, 51)
                        .addComponent(jButton3)))
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(117, 117, 117))
        );

        jTabbedPane1.addTab("Capítulo D", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(403, 403, 403)
                        .addComponent(jLabel1)
                        .addGap(221, 221, 221)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(JtxfCodV, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 988, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(JtxfCodV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(742, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JtxfCodVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtxfCodVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JtxfCodVActionPerformed

    private void JrbSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JrbSiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JrbSiActionPerformed

    private void JtxfCantPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtxfCantPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JtxfCantPActionPerformed

    private void JrbSobrepartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JrbSobrepartoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JrbSobrepartoActionPerformed

    private void JrbOtraCausaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JrbOtraCausaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JrbOtraCausaActionPerformed

    private void JtxfNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtxfNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JtxfNumeroActionPerformed
 private String seleccionado(){
        String sel;
        if(JrbSi.isSelected()){
            sel = "Si";
        }else if(JrbNo.isSelected()){
            sel = "No";
        }else{
            sel = "";
        }
        return sel;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        // BOTON PASAR A LA PREGUNTA 22
        codViv = JtxfCodV.getText().isEmpty(); // Poner codVivienda
        
        if(JrbNo.isSelected() && (codViv != true)){
            this.setVisible(false);
            new CapE().setVisible(true);
            // Guardar datos en bd
            try {
            String sql = "insert into cap_d values(?,?,?)";
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, JtxfCodV.getText());
            ps.setString(2, seleccionado());
            ps.setString(3, "0");
 
            int n = ps.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Datos guardados correctamente");
            }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error"+ e.getMessage());
            }
            Llenar();
        }else{
            String mensaje = "No puede pasar a la pregunta 22";
            JOptionPane.showMessageDialog(null, mensaje);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        // BOTON GUARDAR datos en tabla Personas Muertas en bd
        
        codViv = JtxfCodV.getText().isEmpty();
        boolean hayCantidadP = JtxfCantP.getText().isEmpty();  // verifica si esta vacio en campo de cantidad de personas
        
        if(JrbSi.isSelected() && (hayCantidadP != true) && (codViv != true)){
            cantidad = Integer.parseInt(JtxfCantP.getText()); // cantidad de personas fuera del pais
            if(i <= cantidad){
                // Guardar datos en bd
                    try {
                        String sql = "insert into cap_dmuertos values(?,?,?,?,?,?)";
                        PreparedStatement ps = conn.prepareCall(sql);
                        ps.setString(1, JtxfCodV.getText());       
                        ps.setString(2, JtxfNumero.getText()); 
                        ps.setString(3, JtxfNomP.getText());                   
                        ps.setString(4, JtxfEdadMuerte.getText());
                        ps.setString(5, getSexo());
                       // ps.setString(6, getCausaMuerte());
                       
                        if(getSexo().equals("Mujer")){
                        ps.setString(6, getCausaMuerte());
                        }else{
                        ps.setString(6, "");
                        }

                        int n = ps.executeUpdate();
                        if(n>0){
                            JOptionPane.showMessageDialog(null, "Datos guardados correctamente");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,"Error"+ e.getMessage());
                    }
                    LlenarPersonasMuertas();
                    limpiar();  // limpiar datos 
                    i++;
            }else{
                JOptionPane.showMessageDialog(null, "ya no puede llenar mas");
            }
            
        }else{
            if(codViv){
                JOptionPane.showMessageDialog(null, "Por favor ingrese Codigo de vivienda");
            }else{
                JOptionPane.showMessageDialog(null, "Por favor seleccione la opcion (si) y ingrese la cantidad");
            }
            
        }                                    
    
    }//GEN-LAST:event_jButton4ActionPerformed
    
    
    private void limpiar(){
        
        JtxfNumero.setText("");
        JtxfNomP.setText("");
        JtxfEdadMuerte.setText("");
        
    }
    
    private String getSexo(){
        String  sexo;
        if(JrbHombre.isSelected()){
            sexo = "Hombre";
        }else if(JrbMujer.isSelected()){
            sexo = "Mujer";
        }else{
            sexo = " ";
        }
        return sexo;
    }
    
    private String getCausaMuerte(){
        String causaMuerte;
        if(JrbEmbarazo.isSelected()){
            causaMuerte = "el embarazo";
        }else if(JrbParto.isSelected()){
            causaMuerte = "el parto";
        }else if(JrbSobreparto.isSelected()){
            causaMuerte = "el sobreparto ";
        }else if(JrbOtraCausa.isSelected()){
            causaMuerte = "otra causa";
        }else{
            causaMuerte = " ";
        }
    return causaMuerte;
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
           // TODO add your handling code here:
        this.setVisible(false);
        new CapC().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void JrbPartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JrbPartoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JrbPartoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(JtxfCodV.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese Codigo de Vivienda porfavor");
        }else{
            this.setVisible(false);
            new CapE().setVisible(true);
            // Guardar datos en bd
            try {
            String sql = "insert into cap_d values(?,?,?)";
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, JtxfCodV.getText());
            ps.setString(2, seleccionado());
            ps.setString(3, JtxfCantP.getText());
 
            int n = ps.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Datos guardados correctamente");
            }

            } catch (Exception e) {
                //JOptionPane.showMessageDialog(null,"Error ");
                JOptionPane.showMessageDialog(null,"Error "+ e.getMessage());
            }
            Llenar();
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(CapD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CapD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CapD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CapD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CapD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton JrbEmbarazo;
    private javax.swing.JRadioButton JrbHombre;
    private javax.swing.JRadioButton JrbMujer;
    private javax.swing.JRadioButton JrbNo;
    private javax.swing.JRadioButton JrbOtraCausa;
    private javax.swing.JRadioButton JrbParto;
    private javax.swing.JRadioButton JrbSi;
    private javax.swing.JRadioButton JrbSobreparto;
    private javax.swing.JTextField JtxfCantP;
    private javax.swing.JTextField JtxfCodV;
    private javax.swing.JTextField JtxfEdadMuerte;
    private javax.swing.JTextField JtxfNomP;
    private javax.swing.JTextField JtxfNumero;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables


}
class FondoPanel extends JPanel{
        private Image imagen;
        @Override
        public void paint(Graphics g){
            imagen = new ImageIcon(getClass().getResource("/imagenes/fondoFrame.jpg")).getImage();
            
            g.drawImage(imagen, 0, 0, getWidth(),getHeight(), this);
            
            setOpaque(false);
            
            super.paint(g);
        }
    }