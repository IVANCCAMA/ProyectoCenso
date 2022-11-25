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
public class CapC extends javax.swing.JFrame {

    /**
     * Creates new form CapC
     */
    fondoPanel fondo = new fondoPanel();
    
    private int cantidad;
    private int i;
    private boolean codViv;
    
    DefaultTableModel model;
    Connection conn;
    Statement sent;
    
    public CapC() {
        this.setContentPane(fondo);
        
        initComponents();
        
        conn = Mysql.getConnection();
        
        i = 1;
        cantidad = 0;
    }
    
    private void LlenarPersonasFuera(){
        try {
            conn = Mysql.getConnection();
            String[]titulos = {"codVivienda","numPersona","nomPersona","sexo"
                              ,"añoSalida","edadSalida","paisActual"}; 
            String sql = "select * from cap_cpersfuera";
            model = new DefaultTableModel(null, titulos);
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            
            String[]fila = new String[7];
            while(rs.next()){
                fila[0]=rs.getString("codVivienda");
                fila[1]=rs.getString("numPersona");
                fila[2]=rs.getString("nomPersona");
                fila[3]=rs.getString("sexo");
                fila[4]=rs.getString("añoSalida");
                fila[5]=rs.getString("edadSalida");
                fila[6]=rs.getString("paisActual");
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        JrbSi = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        JtxfCanP = new javax.swing.JTextField();
        JrbNo = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        JtxfNumero = new javax.swing.JTextField();
        Jtxf2NomP = new javax.swing.JTextField();
        JtxfAnioSalida = new javax.swing.JTextField();
        JtxfEdad = new javax.swing.JTextField();
        JtxfPais = new javax.swing.JTextField();
        JrbHombre = new javax.swing.JRadioButton();
        JrbMujer = new javax.swing.JRadioButton();
        jButton4 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JtxfCodV = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup1.add(JrbSi);
        JrbSi.setText("Si");
        jPanel3.add(JrbSi, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, -1, -1));

        jLabel3.setText("¿Cuántas personas?");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));
        jPanel3.add(JtxfCanP, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 83, -1));

        buttonGroup1.add(JrbNo);
        JrbNo.setText("No");
        jPanel3.add(JrbNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, -1, -1));

        jButton1.setText("Pase a la pregunta 21 ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel10.setText("20. Desde 2001 a la fecha, alguna persona que vivía con ustedes en este hogar, ¿actualmente vive en otro país?");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 1084, 118));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setText("N°");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 86, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel5.setText("Nombre de la Persona");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 86, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel6.setText("La persona es:");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel7.setText("Año de salida del país");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel8.setText("A qué edad se fue");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel9.setText("País donde vive actualmente");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 90, -1, -1));

        JtxfNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtxfNumeroActionPerformed(evt);
            }
        });
        jPanel4.add(JtxfNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 119, 60, -1));

        Jtxf2NomP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jtxf2NomPActionPerformed(evt);
            }
        });
        jPanel4.add(Jtxf2NomP, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 119, 180, -1));
        jPanel4.add(JtxfAnioSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 151, -1));
        jPanel4.add(JtxfEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 125, -1));
        jPanel4.add(JtxfPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 120, 199, -1));

        buttonGroup2.add(JrbHombre);
        JrbHombre.setText("Hombre");
        JrbHombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JrbHombreActionPerformed(evt);
            }
        });
        jPanel4.add(JrbHombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, -1, -1));

        buttonGroup2.add(JrbMujer);
        JrbMujer.setText("Mujer");
        JrbMujer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JrbMujerActionPerformed(evt);
            }
        });
        jPanel4.add(JrbMujer, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 85, -1));

        jButton4.setText("Guardar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 160, 100, -1));

        jLabel11.setText("(Anote en el siguiente cuadro, los datos de cada una de las personas que actualmente viven en otro pais)");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 1084, 367));

        jButton3.setText("Siguiente");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 570, -1, -1));

        jTabbedPane1.addTab("Capítulo C", jPanel1);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1200, 670));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("CAPÍTULO C. EMIGRACIÓN INTERNACIONAL ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setText("Codigo Vivienda");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(922, 33, -1, -1));
        getContentPane().add(JtxfCodV, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 30, 110, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Jtxf2NomPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jtxf2NomPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Jtxf2NomPActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // BOTON SIGUIENTE
        
        if(JtxfCodV.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese Codigo de Vivienda porfavor");
        }else{
            this.setVisible(false);
            new CapD().setVisible(true);
            // Guardar datos en bd
            try {
            String sql = "insert into cap_c values(?,?,?)";
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, JtxfCodV.getText());
            ps.setString(2, seleccionado());
            ps.setString(3, JtxfCanP.getText());
 
            int n = ps.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Datos guardados correctamente");
            }

            } catch (Exception e) {
                //JOptionPane.showMessageDialog(null,"Error ");
                JOptionPane.showMessageDialog(null,"Error "+ e.getMessage());
            }
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

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
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // BOTON GUARDAR datos en tabla PersonasFuera en bd

        codViv = JtxfCodV.getText().isEmpty();
        
        boolean hayCantidadP = JtxfCanP.getText().isEmpty();  // verifica si esta vacio en campo de cantidad de personas
        
        if(JrbSi.isSelected() && (hayCantidadP != true) && (codViv != true)){
            cantidad = Integer.parseInt(JtxfCanP.getText()); // cantidad de personas fuera del pais
            if(i <= cantidad){
                // Guardar datos en bd
                    try {
                        String sql = "insert into cap_cpersfuera values(?,?,?,?,?,?,?)";
                        PreparedStatement ps = conn.prepareCall(sql);
                        ps.setString(1, JtxfCodV.getText());       
                        ps.setString(2, JtxfNumero.getText()); 
                        ps.setString(3, Jtxf2NomP.getText()); 
                        ps.setString(4, getSexo());                  
                        ps.setString(5, JtxfAnioSalida.getText()); 
                        ps.setString(6, JtxfEdad.getText());
                        ps.setString(7, JtxfPais.getText()); 

                        int n = ps.executeUpdate();
                        if(n>0){
                            JOptionPane.showMessageDialog(null, "Datos guardados correctamente");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,"Error"+ e.getMessage());
                    }
                    LlenarPersonasFuera();
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
        Jtxf2NomP.setText("");
        JtxfAnioSalida.setText("");
        JtxfEdad.setText("");
        JtxfPais.setText("");
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
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // BOTON PASAR A LA PREGUNTA 21
        codViv = JtxfCodV.getText().isEmpty(); // Poner codVivienda
        
        if(JrbNo.isSelected() && (codViv != true)){
            this.setVisible(false);
            new CapD().setVisible(true);
            // Guardar datos en bd
            try {
            String sql = "insert into cap_c values(?,?,?)";
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
        }else{
            String mensaje;
            if(codViv){
                mensaje = "Ingrese codigo vivienda por favor";
            }else{
                mensaje = "selecciones opcion (no) para pasar a pregunta 23";
            }
            JOptionPane.showMessageDialog(null, mensaje);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void JtxfNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtxfNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JtxfNumeroActionPerformed

    private void JrbMujerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JrbMujerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JrbMujerActionPerformed

    private void JrbHombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JrbHombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JrbHombreActionPerformed

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
            java.util.logging.Logger.getLogger(CapC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CapC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CapC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CapC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CapC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton JrbHombre;
    private javax.swing.JRadioButton JrbMujer;
    private javax.swing.JRadioButton JrbNo;
    private javax.swing.JRadioButton JrbSi;
    private javax.swing.JTextField Jtxf2NomP;
    private javax.swing.JTextField JtxfAnioSalida;
    private javax.swing.JTextField JtxfCanP;
    private javax.swing.JTextField JtxfCodV;
    private javax.swing.JTextField JtxfEdad;
    private javax.swing.JTextField JtxfNumero;
    private javax.swing.JTextField JtxfPais;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
    class fondoPanel extends JPanel{
        private Image imagen;
        
        @Override
        public void paint(Graphics g){
            imagen = new ImageIcon(getClass().getResource("/imagenes/fondoFrame.jpg")).getImage();
            g.drawImage(imagen, 0, 0,getWidth() , getHeight(),this);
            setOpaque(false);
            
            super.paint(g);
        }
    }
}

