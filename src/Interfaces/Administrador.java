/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import dba.Mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARMAGEDON
 */
public class Administrador extends javax.swing.JFrame {
    Connection conn;
    DefaultTableModel model;
    Statement sent;
    
    public String usuario;
    public String zona;
    
    public Administrador() {
        initComponents();
        
        conn=Mysql.getConnection();
        
        DeshabilitarTablas();
        LlenarTablas();
        setNombreAdm();
    }
    
    private void DeshabilitarTablas(){
        DeshabilitarTablaReporte();
        DeshabilitarTablaJefeZona();
        DeshabilitarTablaEncuestadores();
        DeshabilitarTablaJefFam();
    }
    
    private void LlenarTablas(){
        llenarTabla("Jefe de Zona", tablaJefeZona);
        llenarTabla("Encuestador", tablaEncuestador);
        llenarTabla("Jefe de Familia", TablaJefeFamilia);
        
        llenarTablaCenso();
        llenarTablaReporte();
        
        setNumViviendas();
        setNumJefesZ();
        setNumJefFam();
        setNumEnc();
    }
    
    private void nuevo(){
    }
    private void guardar(String usr, javax.swing.JTextField nombre, javax.swing.JTextField dir, 
        javax.swing.JTextField telef, javax.swing.JTextField correo, 
        javax.swing.JTextField zona, javax.swing.JTextField contra, javax.swing.JTable tabla){
        // BOTON GUARDAR 
        try {
            String sql = "INSERT into usuario (nombreUsr,dirUsr,telfUsr,correoUsr,tipoUsr, contrUsr,ZonaCensal)"+
            "values(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, nombre.getText());
            ps.setString(2, dir.getText());
            ps.setString(3, telef.getText());
            ps.setString(4, correo.getText());
            ps.setString(5, usr); // usuario a gurdar
            ps.setString(6, zona.getText());
            ps.setString(7, contra.getText());

            int n = ps.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Registro guardado correctamente");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error"+ e.getMessage());
        }
        llenarTabla(usr, tabla);
        LimpiarTablas();
    }
  
    private void cerrar(){
        int r =JOptionPane.showOptionDialog(this, "¿Está seguro de cerrar la sesión?", "Cerrar sesión", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Si", "No"}, "No");
        if (r == 0){
            this.setVisible(false);
            new InicioSesion().setVisible(true);
        }
    }
    
    
    private void llenarTabla(String usr, javax.swing.JTable tabla){
        try {
            conn = Mysql.getConnection();
            String[]titulos = {"codUsr", "nombreUsr", "dirUsr", "telfUsr", "correoUsr", "ZonaCensal"}; // colocar mismos nombres de la tabla
            String sql = "select codUsr,nombreUsr,dirUsr,telfUsr,correoUsr,ZonaCensal from usuario " // Consulta
                    + "where tipoUsr = '" + usr + "'";
            model = new DefaultTableModel(null, titulos);
            sent = conn.createStatement(); //sent = estado para ejecutar sql
            ResultSet rs = sent.executeQuery(sql); //  resultados
            
            String[]fila = new String[6];
            while(rs.next()){
                fila[0]=rs.getString("codUsr"); //No colocar repetidos
                fila[1]=rs.getString("nombreUsr");
                fila[2]=rs.getString("dirUsr");
                fila[3]=rs.getString("telfUsr");
                fila[4]=rs.getString("correoUsr");
                fila[5]=rs.getString("ZonaCensal");
                model.addRow(fila);
            }
            tabla.setModel(model);// setea los datos recuperados a la tablaJesfes

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void setNumViviendas(){
        String total = "" ;
        try {
                String sql = "SELECT COUNT(codVivienda) as cantVivienda FROM cap_a";
                sent = conn.createStatement();
                ResultSet rs = sent.executeQuery(sql);
                rs.next(); // SI O SI PONER PARA LO RESULTADOS
                total = rs.getString("cantVivienda");

            } catch (Exception e) {
                e.printStackTrace();
            }
        textNumViviendas.setText(total);
        textNumViviendas.setEditable(false);
    }
    
    private void setNumEnc(){
        String total = setNumUsr("Encuestador");
        textNumEnc.setText(total);
        textNumEnc.setEditable(false);
    }
    
    private void setNumJefesZ(){
        String total = setNumUsr("Jefe de Zona");
        textNumJefesZona.setText(total);
        textNumJefesZona.setEditable(false);
    }
    
    private void setNumJefFam(){
        String total = setNumUsr("Jefe de Familia");
        txtNumJefeFam.setText(total);
        txtNumJefeFam.setEditable(false);
    }
    
    public String setNumUsr(String tipoUsr){
        String total = "" ;
        try {
                String sql = "SELECT COUNT(codUsr) as cant FROM censo.usuario Where tipoUsr = " + "'"+tipoUsr+"'";
                sent = conn.createStatement();
                ResultSet rs = sent.executeQuery(sql);
                rs.next(); // SI O SI PONER PARA LO RESULTADOS
                total = rs.getString("cant");

            } catch (Exception e) {
                e.printStackTrace();
            }
        return total;
    }
    
    private void setNombreAdm(){
        textNombre.setText(InicioSesion.nombreUrs); // RECUPERA EL NOMBRE DEL ADM DESDE EL FRAME INICIO SESION
        textNombre.setEditable(false);
    }
    
    private void llenarTablaCenso(){
        try {
            
            conn = Mysql.getConnection();
            String[]titulos = {"codVivienda", "comunidad", "localidad", "barrio", "calle", "numPuerta", "totalPersonas"}; // colocar mismos nombres de la tabla
            String sql = "select codVivienda, comunidad, localidad, barrio, calle, numPuerta, totalPersonas from censo.cap_a";
            model = new DefaultTableModel(null, titulos);
            sent = conn.createStatement(); //sent = estado para ejecutar sql
            ResultSet rs = sent.executeQuery(sql); // 
            
            String[]fila = new String[7];
            while(rs.next()){
                fila[0]=rs.getString("codVivienda"); //No colocar repetidos
                fila[1]=rs.getString("comunidad");
                fila[2]=rs.getString("localidad");
                fila[3]=rs.getString("barrio");
                fila[4]=rs.getString("calle");
                fila[5]=rs.getString("numPuerta");
                fila[6]=rs.getString("totalPersonas");
                model.addRow(fila);
            }
            tablaCenso.setModel(model);
            tablaCenso.setEnabled(false); // para que no pueda ser modificado, solo lectura
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void llenarTablaReporte(){
        try {
            conn = Mysql.getConnection();
            String[]titulos = {"codUsr", "reporte"}; // colocar mismos nombres de la tabla
            String sql = "select codUsr , reporte from reporte  ";
                    
            model = new DefaultTableModel(null, titulos);
            sent = conn.createStatement(); //sent = estado para ejecutar sql
            ResultSet rs = sent.executeQuery(sql); // 
            
            String[]fila = new String[2];
            while(rs.next()){
                fila[0]=rs.getString("codUsr"); //No colocar repetidos
                fila[1]=rs.getString("reporte");
                
                model.addRow(fila);
            }
            TablaReporte.setModel(model);//la tabla sql adoptará el modelo "model", establecido anteriormente
            //tablaJefes.setEnabled(false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
    private void LimpiarTablas(){
        // JEFE ZONA
        txtNombreJZ.setText("");
        txtDirJZ.setText("");
        txtTelfJZ.setText("");
        txtCorreoJZ.setText("");
        txtZonaJZ.setText("");
        txtContraJZ.setText("");
        
        // JEFE FAMILIA
        txtNombreJF.setText("");
        txtDirJF.setText("");
        txtTelfJF.setText("");
        txtCorreoJF.setText("");
        txtZonaJF.setText("");
        txtContraJF.setText("");
        
        // ECUESTADORES
        txtNombreEnc.setText("");
        txtDirEnc.setText("");
        txtTelfEnc.setText("");
        txtCorreoEnc.setText("");
        txtZonaEnc.setText("");
        txtContrEnc.setText("");
    }
    
    void DeshabilitarTablaJefeZona(){
       txtNombreJZ.setEditable(false);
       txtDirJZ.setEditable(false);
       txtTelfJZ.setEditable(false);
       txtCorreoJZ.setEditable(false);
       txtZonaJZ.setEditable(false);
       txtContraJZ.setEditable(false);
    }
    void DeshabilitarTablaEncuestadores(){
       txtNombreEnc.setEditable(false);
       txtDirEnc.setEditable(false);
       txtTelfEnc.setEditable(false);
       txtCorreoEnc.setEditable(false);
       txtZonaEnc.setEditable(false);
       txtContrEnc.setEditable(false);
    }
    void DeshabilitarTablaReporte(){
       jTextArea1.setEditable(false);
       txtCodUsr.setEditable(false);
       
    }
    void DeshabilitarTablaJefFam(){
       txtNombreJF.setEditable(false);
       txtDirJF.setEditable(false);
       txtTelfJF.setEditable(false);
       txtCorreoJF.setEditable(false);
       txtZonaJF.setEditable(false);
       txtContraJF.setEditable(false);
    }
    void HabilitarTablaJefeZona(boolean modContra){
       txtNombreJZ.setEditable(true);
       txtDirJZ.setEditable(true);
       txtTelfJZ.setEditable(true);
       txtCorreoJZ.setEditable(true);
       txtZonaJZ.setEditable(true);
       if (modContra) txtContraJZ.setEditable(true);
       txtNombreJZ.requestFocus();
    }
    void HabilitarTablaJefFam(boolean modContra){
       txtNombreJF.setEditable(true);
       txtDirJF.setEditable(true);
       txtTelfJF.setEditable(true);
       txtCorreoJF.setEditable(true);
       txtZonaJF.setEditable(true);
       txtContraJF.setEditable(true);
       if (modContra) txtContraJZ.setEditable(true);
       txtNombreJF.requestFocus();
    }
    void HabilitarTablaEncuestadores(boolean modPassword){
       txtNombreEnc.setEditable(true);
       txtDirEnc.setEditable(true);
       txtTelfEnc.setEditable(true);
       txtCorreoEnc.setEditable(true);
       txtZonaEnc.setEditable(true);
       if (modPassword) txtContrEnc.setEditable(true);
       txtNombreEnc.requestFocus();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PanelNavegador = new javax.swing.JPanel();
        BotonEncuestador = new javax.swing.JButton();
        BotonCenso = new javax.swing.JButton();
        BotonJefes = new javax.swing.JButton();
        BotonAtras = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelJefe = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNombreJZ = new javax.swing.JTextField();
        txtDirJZ = new javax.swing.JTextField();
        txtTelfJZ = new javax.swing.JTextField();
        txtCorreoJZ = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaJefeZona = new javax.swing.JTable();
        textNumJefesZona = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtZonaJZ = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtContraJZ = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        panelEncuestador = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        textNumEnc = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtNombreEnc = new javax.swing.JTextField();
        txtDirEnc = new javax.swing.JTextField();
        txtTelfEnc = new javax.swing.JTextField();
        txtCorreoEnc = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaEncuestador = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        txtZonaEnc = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtContrEnc = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        PanelViviendas = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaCenso = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        textNumViviendas = new javax.swing.JTextField();
        PanelReportes = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaReporte = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        txtCodUsr = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        PanelJefesFam = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNombreJF = new javax.swing.JTextField();
        txtDirJF = new javax.swing.JTextField();
        txtTelfJF = new javax.swing.JTextField();
        txtCorreoJF = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaJefeFamilia = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        txtZonaJF = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtContraJF = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtNumJefeFam = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelNavegador.setBackground(new java.awt.Color(0, 102, 255));
        PanelNavegador.setForeground(new java.awt.Color(51, 102, 255));
        PanelNavegador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BotonEncuestador.setText("ENCUENTADORES");
        BotonEncuestador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEncuestadorActionPerformed(evt);
            }
        });
        PanelNavegador.add(BotonEncuestador, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 260, 70));

        BotonCenso.setText("VIVIENDAS CENSADAS");
        BotonCenso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCensoActionPerformed(evt);
            }
        });
        PanelNavegador.add(BotonCenso, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 260, 70));

        BotonJefes.setText("JEFES DE ZONA");
        BotonJefes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonJefesActionPerformed(evt);
            }
        });
        PanelNavegador.add(BotonJefes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 260, 70));

        BotonAtras.setText("Atras");
        BotonAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAtrasActionPerformed(evt);
            }
        });
        PanelNavegador.add(BotonAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 660, -1, -1));

        jButton4.setText("VER REPORTES");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        PanelNavegador.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 260, 70));

        jButton5.setText("JEFES DE FAMILIA");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        PanelNavegador.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 260, 70));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("NOMBRE ADMINISTRADOR:");
        PanelNavegador.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        textNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textNombre.setBorder(null);
        PanelNavegador.add(textNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 250, 28));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel1.setText("ADMINISTRADOR");
        PanelNavegador.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel1.add(PanelNavegador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 760));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setToolTipText("");

        panelJefe.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setText("Actualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton6.setText("Eliminar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton7.setText("Cerrar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Nombre");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Dirección");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Teléfono");

        txtNombreJZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreJZActionPerformed(evt);
            }
        });

        txtDirJZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDirJZActionPerformed(evt);
            }
        });

        txtCorreoJZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoJZActionPerformed(evt);
            }
        });

        tablaJefeZona.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaJefeZona.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaJefeZona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaJefeZonaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaJefeZona);

        textNumJefesZona.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textNumJefesZona.setBorder(null);
        textNumJefesZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNumJefesZonaActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Correo");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("Zona Censal");

        txtContraJZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraJZActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel26.setText("Contraseña");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel28.setText("TOTAL JEFES DE ZONA:");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel30.setText("JEFES DE ZONA");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel31.setText("DATOS USUARIO");

        javax.swing.GroupLayout panelJefeLayout = new javax.swing.GroupLayout(panelJefe);
        panelJefe.setLayout(panelJefeLayout);
        panelJefeLayout.setHorizontalGroup(
            panelJefeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJefeLayout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addGroup(panelJefeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJefeLayout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(jLabel30))
                    .addGroup(panelJefeLayout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(jLabel31))
                    .addGroup(panelJefeLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6)
                        .addGap(44, 44, 44)
                        .addComponent(txtNombreJZ, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelJefeLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addGap(37, 37, 37)
                        .addComponent(txtDirJZ, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelJefeLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(txtZonaJZ, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelJefeLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(31, 31, 31)
                        .addComponent(txtContraJZ, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelJefeLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(6, 6, 6)
                        .addComponent(jButton6)
                        .addGap(10, 10, 10)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelJefeLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel28)
                        .addGap(35, 35, 35)
                        .addComponent(textNumJefesZona, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelJefeLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelJefeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelJefeLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(56, 56, 56)
                                .addComponent(txtCorreoJZ, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelJefeLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(41, 41, 41)
                                .addComponent(txtTelfJZ, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(201, Short.MAX_VALUE))
        );
        panelJefeLayout.setVerticalGroup(
            panelJefeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJefeLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel30)
                .addGap(12, 12, 12)
                .addComponent(jLabel31)
                .addGap(15, 15, 15)
                .addGroup(panelJefeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJefeLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6))
                    .addComponent(txtNombreJZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(panelJefeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtDirJZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(panelJefeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtTelfJZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(panelJefeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCorreoJZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(21, 21, 21)
                .addGroup(panelJefeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtZonaJZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(21, 21, 21)
                .addGroup(panelJefeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(txtContraJZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(panelJefeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addGap(27, 27, 27)
                .addGroup(panelJefeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(textNumJefesZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("JZ", panelJefe);

        panelEncuestador.setBackground(new java.awt.Color(255, 255, 255));
        panelEncuestador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel5.setText("ENCUESTADORES");
        panelEncuestador.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        textNumEnc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textNumEnc.setBorder(null);
        panelEncuestador.add(textNumEnc, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 660, 150, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("DATOS USUARIO");
        panelEncuestador.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, -1, -1));

        jButton13.setText("Nuevo");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        panelEncuestador.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 610, 100, -1));

        jButton14.setText("Guardar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        panelEncuestador.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 610, 100, -1));

        jButton15.setText("Actualizar");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        panelEncuestador.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 610, -1, -1));

        jButton16.setText("Eliminar");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        panelEncuestador.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 610, 100, -1));

        jButton17.setText("Cerrar");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        panelEncuestador.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 610, 110, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Nombre");
        panelEncuestador.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Dirección");
        panelEncuestador.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Teléfono");
        panelEncuestador.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, -1, -1));
        panelEncuestador.add(txtNombreEnc, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 490, -1));
        panelEncuestador.add(txtDirEnc, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 490, -1));
        panelEncuestador.add(txtTelfEnc, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 490, -1));

        txtCorreoEnc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoEncActionPerformed(evt);
            }
        });
        panelEncuestador.add(txtCorreoEnc, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 490, -1));

        tablaEncuestador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaEncuestador.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaEncuestador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEncuestadorMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaEncuestador);

        panelEncuestador.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 630, 160));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel24.setText("Correo");
        panelEncuestador.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, -1, -1));

        txtZonaEnc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtZonaEncActionPerformed(evt);
            }
        });
        panelEncuestador.add(txtZonaEnc, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 490, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setText("Zona Censal");
        panelEncuestador.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, -1, -1));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel27.setText("Contraseña");
        panelEncuestador.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 110, 30));
        panelEncuestador.add(txtContrEnc, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 500, -1));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel29.setText("TOTAL ENCUESTADORES:");
        panelEncuestador.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 660, 210, 30));

        jTabbedPane1.addTab("ENC", panelEncuestador);

        PanelViviendas.setBackground(new java.awt.Color(255, 255, 255));
        PanelViviendas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaCenso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "codVivienda", "comunidad", "localidad", "barrio", "calle", "numPuerta", "totalPersonas"
            }
        ));
        jScrollPane3.setViewportView(tablaCenso);

        PanelViviendas.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 840, 610));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("VIVIENDAS CENSADAS:");
        PanelViviendas.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 200, -1));

        textNumViviendas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textNumViviendas.setBorder(null);
        textNumViviendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNumViviendasActionPerformed(evt);
            }
        });
        PanelViviendas.add(textNumViviendas, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 132, 29));

        jTabbedPane1.addTab("VIV", PanelViviendas);

        PanelReportes.setBackground(new java.awt.Color(255, 255, 255));
        PanelReportes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel19.setText("REPORTES");
        PanelReportes.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 190, 50));

        TablaReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TablaReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaReporteMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TablaReporte);

        PanelReportes.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, 540, 250));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane6.setViewportView(jTextArea1);

        PanelReportes.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 540, 260));

        txtCodUsr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodUsrActionPerformed(evt);
            }
        });
        PanelReportes.add(txtCodUsr, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 100, -1));

        jLabel2.setText("codUsr:");
        PanelReportes.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, -1, -1));

        jTabbedPane1.addTab("REP", PanelReportes);

        PanelJefesFam.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel17.setText("JEFES DE FAMILIA");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("DATOS USUARIO");

        jButton8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton8.setText("Nuevo");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton9.setText("Guardar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton10.setText("Actualizar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton11.setText("Eliminar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton12.setText("Cerrar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Nombre");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Direccion");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Telefono");

        txtNombreJF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreJFActionPerformed(evt);
            }
        });

        txtCorreoJF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoJFActionPerformed(evt);
            }
        });

        TablaJefeFamilia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TablaJefeFamilia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "codUsr", "Title 2", "Title 3", "Title 4"
            }
        ));
        TablaJefeFamilia.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TablaJefeFamilia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaJefeFamiliaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TablaJefeFamilia);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("Correo");

        txtZonaJF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtZonaJFActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setText("Zona Censal");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Contraseña");

        txtContraJF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraJFActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel32.setText("TOTAL JEFES DE FAMILIA :");

        txtNumJefeFam.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNumJefeFam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumJefeFamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelJefesFamLayout = new javax.swing.GroupLayout(PanelJefesFam);
        PanelJefesFam.setLayout(PanelJefesFamLayout);
        PanelJefesFamLayout.setHorizontalGroup(
            PanelJefesFamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(PanelJefesFamLayout.createSequentialGroup()
                .addContainerGap(207, Short.MAX_VALUE)
                .addGroup(PanelJefesFamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelJefesFamLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(280, 280, 280))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelJefesFamLayout.createSequentialGroup()
                        .addGroup(PanelJefesFamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelJefesFamLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(27, 27, 27)
                                .addComponent(txtDirJF, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelJefesFamLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(31, 31, 31)
                                .addComponent(txtTelfJF, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelJefesFamLayout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(txtCorreoJF, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelJefesFamLayout.createSequentialGroup()
                                .addGroup(PanelJefesFamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelJefesFamLayout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addGap(9, 9, 9))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelJefesFamLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(PanelJefesFamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtContraJF, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtZonaJF, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelJefesFamLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(35, 35, 35)
                                .addComponent(txtNombreJF, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelJefesFamLayout.createSequentialGroup()
                                .addGroup(PanelJefesFamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelJefesFamLayout.createSequentialGroup()
                                        .addComponent(jButton8)
                                        .addGap(20, 20, 20)
                                        .addComponent(jButton9)
                                        .addGap(9, 9, 9))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelJefesFamLayout.createSequentialGroup()
                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)))
                                .addGroup(PanelJefesFamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNumJefeFam, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(PanelJefesFamLayout.createSequentialGroup()
                                        .addComponent(jButton10)
                                        .addGap(26, 26, 26)
                                        .addComponent(jButton11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(157, 157, 157))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelJefesFamLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(358, 358, 358))))
        );
        PanelJefesFamLayout.setVerticalGroup(
            PanelJefesFamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelJefesFamLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(PanelJefesFamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtNombreJF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(PanelJefesFamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelJefesFamLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel11))
                    .addComponent(txtDirJF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(PanelJefesFamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelJefesFamLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel12))
                    .addComponent(txtTelfJF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(PanelJefesFamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCorreoJF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(21, 21, 21)
                .addGroup(PanelJefesFamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(txtZonaJF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(PanelJefesFamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtContraJF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelJefesFamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8)
                    .addComponent(jButton9)
                    .addComponent(jButton10)
                    .addGroup(PanelJefesFamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton11)
                        .addComponent(jButton12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(PanelJefesFamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumJefeFam, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("JF", PanelJefesFam);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 980, 750));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BotonEncuestadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEncuestadorActionPerformed
        jTabbedPane1.setSelectedIndex(1);
        jTabbedPane1.getSelectedIndex();
    }//GEN-LAST:event_BotonEncuestadorActionPerformed

    private void BotonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAtrasActionPerformed
        //Boton atras
           new InicioSesion().setVisible(true);
           this.setVisible(false);
    }//GEN-LAST:event_BotonAtrasActionPerformed

    private void BotonCensoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCensoActionPerformed
        jTabbedPane1.setSelectedIndex(2);
        jTabbedPane1.getSelectedIndex();
    }//GEN-LAST:event_BotonCensoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // BOTON NUEVO JEFE ZONA
        LimpiarTablas();
        HabilitarTablaJefeZona(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // BOTON GUARDAR JEFE DE ZONA
        guardar("Jefe de Zona", txtNombreJZ, txtDirJZ, txtTelfJZ, txtCorreoJZ,
                txtZonaJZ, txtContraJZ, tablaJefeZona);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // BOTON ACTUALIZAR JEFE DE ZONA
        try {
            String sql = "UPDATE usuario set nombreUsr=?, dirUsr=?, telfUsr=?, correoUsr=?, ZonaCensal=?"
            + "where codUsr=?";
            int fila = tablaJefeZona.getSelectedRow();
            String id = (String)tablaJefeZona.getValueAt(fila, 0);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtNombreJZ.getText());
            ps.setString(2, txtDirJZ.getText());
            ps.setString(3, txtTelfJZ.getText());
            ps.setString(4, txtCorreoJZ.getText());
            ps.setString(5, txtZonaJZ.getText());
            ps.setString(6, id);
            int n = ps.executeUpdate();
            if(n>0){ // Si se ejecuto
                JOptionPane.showMessageDialog(null,"Registro modificado");
                LimpiarTablas();
                LlenarTablas();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error"+ e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // BOTON ELIMINAR JEFE DE ZONA
        try {
            int fila = tablaJefeZona.getSelectedRow();
            String sql = "DELETE from usuario where codUsr="+tablaJefeZona.getValueAt(fila, 0);
            sent=conn.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                //llenarTablaJefeZona();
                LlenarTablas();
                LimpiarTablas();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error"+ e.getMessage());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // BOTON CERRAR SESIÓN JEFE DE ZONA
        cerrar();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tablaJefeZonaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaJefeZonaMouseClicked
        // MOUSE CLICKED DE TABLA JEFE DE ZONA
        if (evt.getButton()==1){
            int fila = tablaJefeZona.getSelectedRow();
            try {
                HabilitarTablaJefeZona(false);
                String sql = "SELECT codUsr,nombreUsr,dirUsr,telfUsr,correoUsr,contrUsr,ZonaCensal from usuario where codUsr="+tablaJefeZona.getValueAt(fila,0);
                sent = conn.createStatement();
                ResultSet rs = sent.executeQuery(sql);
                rs.next(); // SI O SI PONER PARA LO RESULTADOS
                txtNombreJZ.setText(rs.getString("nombreUsr"));
                txtDirJZ.setText(rs.getString("dirUsr"));
                txtTelfJZ.setText(rs.getString("telfUsr"));
                txtCorreoJZ.setText(rs.getString("correoUsr"));
                txtZonaJZ.setText(rs.getString("ZonaCensal"));
                txtContraJZ.setText(rs.getString("contrUsr"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tablaJefeZonaMouseClicked

    private void txtCorreoJZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoJZActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoJZActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // BOTON NUEVO ENCUESTADOR
        LimpiarTablas();
        HabilitarTablaEncuestadores(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // BOTON GUARDAR ENCUESTADOR
        guardar("Encuestador", txtNombreEnc, txtDirEnc, txtTelfEnc, txtCorreoEnc,
                txtZonaEnc, txtContrEnc, tablaEncuestador);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // BOTON ACTUALIZAR ENCUESTADOR
        try {
            String sql = "UPDATE usuario set nombreUsr=?, dirUsr=?, telfUsr=?, correoUsr=?, ZonaCensal=?"
            + "where codUsr=?";
            int fila = tablaEncuestador.getSelectedRow();
            String id = (String)tablaEncuestador.getValueAt(fila, 0);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtNombreEnc.getText());
            ps.setString(2, txtDirEnc.getText());
            ps.setString(3, txtTelfEnc.getText());
            ps.setString(4, txtCorreoEnc.getText());
            ps.setString(5, txtZonaEnc.getText());
            ps.setString(6, id);
            int n = ps.executeUpdate();
            if(n>0){ // Si se ejecuto
                JOptionPane.showMessageDialog(null,"Registro modificado");
                LimpiarTablas();
                LlenarTablas();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error"+ e.getMessage());
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        //BOTÓN ELIMINAR ENCUESTADOR
        try {
            int fila = tablaEncuestador.getSelectedRow();
            String sql = "DELETE from usuario where codUsr="+tablaEncuestador.getValueAt(fila, 0);
            sent=conn.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                LlenarTablas();
                LimpiarTablas();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error"+ e.getMessage());
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // BOTON CERRAR SESIÓN ENCUESTADOR
        cerrar();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void txtCorreoEncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoEncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoEncActionPerformed

    private void tablaEncuestadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEncuestadorMouseClicked
       // MOUSE CLICKED DE TABLA ENCUESTADOR
        if (evt.getButton()==1){
            int fila = tablaEncuestador.getSelectedRow();
            try {
                HabilitarTablaEncuestadores(false);
                String sql = "SELECT codUsr,nombreUsr,dirUsr,telfUsr,correoUsr,contrUsr,ZonaCensal from usuario where codUsr="+tablaEncuestador.getValueAt(fila,0);
                sent = conn.createStatement();
                ResultSet rs = sent.executeQuery(sql);
                rs.next(); // SI O SI PONER PARA LO RESULTADOS
                txtNombreEnc.setText(rs.getString("nombreUsr"));
                txtDirEnc.setText(rs.getString("dirUsr"));
                txtTelfEnc.setText(rs.getString("telfUsr"));
                txtCorreoEnc.setText(rs.getString("correoUsr"));
                txtZonaEnc.setText(rs.getString("ZonaCensal"));
                txtContrEnc.setText(rs.getString("contrUsr"));
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tablaEncuestadorMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jTabbedPane1.setSelectedIndex(3);
        jTabbedPane1.getSelectedIndex();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void BotonJefesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonJefesActionPerformed
        jTabbedPane1.setSelectedIndex(0);
        jTabbedPane1.getSelectedIndex();
    }//GEN-LAST:event_BotonJefesActionPerformed

    private void textNumJefesZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNumJefesZonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNumJefesZonaActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jTabbedPane1.setSelectedIndex(4);
        jTabbedPane1.getSelectedIndex();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void TablaJefeFamiliaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaJefeFamiliaMouseClicked
if (evt.getButton()==1){
            int fila = TablaJefeFamilia.getSelectedRow();
            try {
                HabilitarTablaJefFam(false);
                String sql = "SELECT codUsr,nombreUsr,dirUsr,telfUsr,correoUsr,contrUsr,ZonaCensal from usuario where  codUsr="+TablaJefeFamilia.getValueAt(fila,0);
                sent = conn.createStatement();
                ResultSet rs = sent.executeQuery(sql);
                rs.next(); // SI O SI PONER PARA LO RESULTADOS
                txtNombreJF.setText(rs.getString("nombreUsr"));
                txtDirJF.setText(rs.getString("dirUsr"));
                txtTelfJF.setText(rs.getString("telfUsr"));
                txtCorreoJF.setText(rs.getString("correoUsr"));
                txtZonaJF.setText(rs.getString("ZonaCensal"));
                txtContraJF.setText(rs.getString("contrUsr"));
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_TablaJefeFamiliaMouseClicked

    private void txtCorreoJFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoJFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoJFActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // CERRAR:
        cerrar();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // // ELIMINAR JEFE FAMILIA
        try {
            int fila = TablaJefeFamilia.getSelectedRow();
            String sql = "DELETE from usuario where codUsr="+TablaJefeFamilia.getValueAt(fila, 0);
            sent=conn.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                LlenarTablas();
                LimpiarTablas();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error"+ e.getMessage());
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // ACTULIZAR JEFE FAMILIA
        try {
            String sql = "UPDATE usuario set nombreUsr=?, dirUsr=?, telfUsr=?, correoUsr=?, ZonaCensal=? ,contrUsr=?"
            + "where codUsr=?";
            int fila = TablaJefeFamilia.getSelectedRow();
            String id = (String)TablaJefeFamilia.getValueAt(fila, 0);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtNombreJF.getText());
            ps.setString(2, txtDirJF.getText());
            ps.setString(3, txtTelfJF.getText());
            ps.setString(4, txtCorreoJF.getText());
            ps.setString(5, txtZonaJF.getText());
            ps.setString(6, txtContraJF.getText());
            ps.setString(7, id);
            int n = ps.executeUpdate();
            if(n>0){ // Si se ejecuto
                JOptionPane.showMessageDialog(null,"Registro modificado");
                LimpiarTablas();
                LlenarTablas();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error"+ e.getMessage());
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // BOTON GUARDAR JEFE FAMILIA
        guardar("Jefe de Familia", txtNombreJF, txtDirJF, txtTelfJF, txtCorreoJF,
                txtZonaJF, txtContraJF, TablaJefeFamilia);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // BOTON NUEVO JEFE DE FAMILIA
        LimpiarTablas();
        HabilitarTablaJefFam(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void txtZonaEncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtZonaEncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtZonaEncActionPerformed

    private void TablaReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaReporteMouseClicked
 
        if (evt.getButton()==1){
            int fila = TablaReporte.getSelectedRow();
            try {
               
                String sql = "SELECT codUsr,reporte from reporte where codUsr ="+TablaReporte.getValueAt(fila,0);
                sent = conn.createStatement();
                ResultSet rs = sent.executeQuery(sql);
                rs.next(); // SI O SI PONER PARA LO RESULTADOS
                jTextArea1.setText(rs.getString("reporte"));
                txtCodUsr.setText(rs.getString ("codUsr"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
                                                     // TODO add your handling code here:
    }//GEN-LAST:event_TablaReporteMouseClicked

    private void txtCodUsrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodUsrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodUsrActionPerformed

    private void txtNombreJZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreJZActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreJZActionPerformed

    private void txtNombreJFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreJFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreJFActionPerformed

    private void txtContraJFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraJFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraJFActionPerformed

    private void txtDirJZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDirJZActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDirJZActionPerformed

    private void txtContraJZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraJZActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraJZActionPerformed

    private void txtZonaJFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtZonaJFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtZonaJFActionPerformed

    private void txtNumJefeFamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumJefeFamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumJefeFamActionPerformed

    private void textNumViviendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNumViviendasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNumViviendasActionPerformed

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
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAtras;
    private javax.swing.JButton BotonCenso;
    private javax.swing.JButton BotonEncuestador;
    private javax.swing.JButton BotonJefes;
    private javax.swing.JPanel PanelJefesFam;
    private javax.swing.JPanel PanelNavegador;
    private javax.swing.JPanel PanelReportes;
    private javax.swing.JPanel PanelViviendas;
    private javax.swing.JTable TablaJefeFamilia;
    private javax.swing.JTable TablaReporte;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel panelEncuestador;
    private javax.swing.JPanel panelJefe;
    private javax.swing.JTable tablaCenso;
    private javax.swing.JTable tablaEncuestador;
    private javax.swing.JTable tablaJefeZona;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textNumEnc;
    private javax.swing.JTextField textNumJefesZona;
    private javax.swing.JTextField textNumViviendas;
    private javax.swing.JTextField txtCodUsr;
    private javax.swing.JTextField txtContrEnc;
    private javax.swing.JTextField txtContraJF;
    private javax.swing.JTextField txtContraJZ;
    private javax.swing.JTextField txtCorreoEnc;
    private javax.swing.JTextField txtCorreoJF;
    private javax.swing.JTextField txtCorreoJZ;
    private javax.swing.JTextField txtDirEnc;
    private javax.swing.JTextField txtDirJF;
    private javax.swing.JTextField txtDirJZ;
    private javax.swing.JTextField txtNombreEnc;
    private javax.swing.JTextField txtNombreJF;
    private javax.swing.JTextField txtNombreJZ;
    private javax.swing.JTextField txtNumJefeFam;
    private javax.swing.JTextField txtTelfEnc;
    private javax.swing.JTextField txtTelfJF;
    private javax.swing.JTextField txtTelfJZ;
    private javax.swing.JTextField txtZonaEnc;
    private javax.swing.JTextField txtZonaJF;
    private javax.swing.JTextField txtZonaJZ;
    // End of variables declaration//GEN-END:variables
}
