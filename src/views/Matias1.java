/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import controler.LeonelControler;
import controler.Utilidades;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.lang.String;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import views.tablas.ModeloTablaArchivo;

/**
 *
 * @author nicof
 */
public class Matias1 extends javax.swing.JFrame {

    private Utilidades u = new Utilidades();
    private ModeloTablaArchivo mtl = new ModeloTablaArchivo();
    private LeonelControler lc = new LeonelControler();
    //Vista1 vi = new Vista1();

    /**
     * Creates new form Matias1
     */
    public Matias1() {
        initComponents();
        tabla();
    }

    private void tabla(){
        DefaultTableModel inicio = new DefaultTableModel();
        inicio.setRowCount(0);
        inicio.addColumn("Nro Archivo");
        inicio.addColumn("Nombre");
        inicio.addColumn("Direccion");
        inicio.addColumn("Usuario");
        tbltabla.setModel(inicio);
    }
    public static String labelname;
    public String getlbl(String name){
        System.out.println("nombre es "+name);
        labelname = name;
        System.out.println("cambios: "+labelname);
        setlbl(labelname);
        return labelname;
    }
    public void setlbl(String label){
        System.out.println("labelname es: "+label);
        this.lblnombre.setText(label);
        System.out.println(lblnombre.getText());
        filtrarxusuario();
    }
    
    private void cargarTabla() {
        if (u.listar() != null && u.listar().length > 0) {
            System.out.println("tabla");
            if(lblnombre.getText().isBlank()){
                mtl.setData(u.listar());
                tbltabla.setModel(mtl);
                tbltabla.updateUI();
                System.out.println("filtro no aceptado");
            } else {
                mtl.setData(u.listar());
                tbltabla.setModel(mtl);
                tbltabla.updateUI();
                filtrarxusuario();
                System.out.println("filtro aceptado");
            }
            
        }
    }

    private void cargarcbx() {
        cbxabrir.removeAllItems();
        if (tbltabla.getColumnCount() == 4) {
            if(tbltabla.getRowCount() == 1){
            cbxabrir.addItem((String) tbltabla.getValueAt(0, 1));
            } else {
            cbxabrir.addItem((String) tbltabla.getValueAt(0, 1));
            for (int i = 1; i < tbltabla.getRowCount(); i++) {
                cbxabrir.addItem((String) tbltabla.getValueAt(i, 1));
            }
            }
        }
    }

    private void guardar() {
        if (txtnombre.getText().isEmpty() || txtdireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Faltan datos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (u.guardar_archivo(txtnombre.getText(), txtdireccion.getText(), lblnombre.getText())) {
                JOptionPane.showMessageDialog(null, "Se ha registrado correctamente", "Mensaje de exito", JOptionPane.INFORMATION_MESSAGE);
                Limpiar1();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void Limpiar1() {
        txtnombre.setText("");
        txtdireccion.setText("");
        txtnombrebuscar.setText("");
        cargarTabla();
        cargarcbx();
            
        
    }

    private void AbrirArchivo() {
        int indice = cbxabrir.getSelectedIndex();
        String abrir = (String) tbltabla.getValueAt(indice, 2);
        File archivo = new File(abrir);
        try {
            Desktop.getDesktop().open(archivo);
        } catch (IOException ex) {
            Logger.getLogger(Matias1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Leonel
    private void buscarDocumento() {
        if (txtnombrebuscar.getText().isEmpty()) {
            System.out.println("Esto es un mensaje de prueba");
        }
        String busqueda = txtnombrebuscar.getText().toLowerCase();
        tbltabla.setModel(mtl);
        filtrarxusuario();
        int filas = tbltabla.getRowCount();
        DefaultTableModel cargar = new DefaultTableModel();
        cargar.setRowCount(0);
        cargar.addColumn(tbltabla.getColumnName(0));
        cargar.addColumn(tbltabla.getColumnName(1));
        cargar.addColumn(tbltabla.getColumnName(2));
        cargar.addColumn(tbltabla.getColumnName(3));
        for (int i = 0; i <= filas - 1; i++) {
            String nombre = tbltabla.getValueAt(i, 1).toString().toLowerCase();
            if (nombre.startsWith(busqueda)) {
                String[] cargartabla = {tbltabla.getValueAt(i, 0).toString(), tbltabla.getValueAt(i, 1).toString(),
                    tbltabla.getValueAt(i, 2).toString(), tbltabla.getValueAt(i, 3).toString(),};
                cargar.addRow(cargartabla);
            }
        }
        tbltabla.setModel(cargar);
        cargarcbx();
        if(cbxtipos.getSelectedItem().toString().equals("TODOS")){
            System.out.println("Mira si esta cargando el cbx");
        } else{
           cbxfiltro();
        }
    }
//Leonel
//darwin
    private void filtrarxusuario(){
        String nombre = lblnombre.getText();
        int filas = tbltabla.getRowCount();
        DefaultTableModel cargar = new DefaultTableModel();
        cargar.setRowCount(0);
        cargar.addColumn(tbltabla.getColumnName(0));
        cargar.addColumn(tbltabla.getColumnName(1));
        cargar.addColumn(tbltabla.getColumnName(2));
        cargar.addColumn(tbltabla.getColumnName(3));
        String usuario;
        for (int i = 0; i <= filas - 1; i++) {
            usuario = tbltabla.getValueAt(i, 3).toString();
            if (nombre.startsWith(usuario)) {
                String[] cargartabla = {tbltabla.getValueAt(i, 0).toString(), tbltabla.getValueAt(i, 1).toString(),
                    tbltabla.getValueAt(i, 2).toString(), tbltabla.getValueAt(i, 3).toString(),};
                cargar.addRow(cargartabla);
            } else {
                System.out.println("No se ha cargado en la fila: "+i);
            }
        }
        tbltabla.setModel(cargar);
        tbltabla.updateUI();
    }
//darwin
//cristian
    private void cbxfiltro() {
        if(txtnombrebuscar.getText().isEmpty()){
            System.out.println("No esta haciendo nada");
            cargarTabla();
            filtrarxusuario();
        } else{
            System.out.println("Primero busqueda luego cargar");
        }
        int index = cbxtipos.getSelectedIndex();
        String combobox = cbxtipos.getItemAt(index);
        int filas = tbltabla.getRowCount();
        DefaultTableModel cargar = new DefaultTableModel();
        if (combobox.equals("TODOS")) {
            buscarDocumento();
        } else if (combobox.equals("PDF")) {
            cargar.setRowCount(0);
            cargar.addColumn(tbltabla.getColumnName(0));
            cargar.addColumn(tbltabla.getColumnName(1));
            cargar.addColumn(tbltabla.getColumnName(2));
            cargar.addColumn(tbltabla.getColumnName(3));
            for (int i = 0; i <= filas - 1; i++) {
                String nombre = tbltabla.getValueAt(i, 2).toString();
                if (nombre.endsWith(combobox.toLowerCase())) {
                    String[] filtrarpdf = {tbltabla.getValueAt(i, 0).toString(), tbltabla.getValueAt(i, 1).toString(),
                        tbltabla.getValueAt(i, 2).toString(), tbltabla.getValueAt(i, 3).toString(),};
                    cargar.addRow(filtrarpdf);
                }
            }
        } else if (combobox.equals("WORD")) {
            cargar.setRowCount(0);
            cargar.addColumn(tbltabla.getColumnName(0));
            cargar.addColumn(tbltabla.getColumnName(1));
            cargar.addColumn(tbltabla.getColumnName(2));
            cargar.addColumn(tbltabla.getColumnName(3));
            
            combobox = "docx";
            for (int i = 0; i <= filas - 1; i++) {
                String nombre = tbltabla.getValueAt(i, 2).toString();
                if (nombre.endsWith(combobox.toLowerCase())) {
                    String[] filtrarword = {tbltabla.getValueAt(i, 0).toString(), tbltabla.getValueAt(i, 1).toString(),
                        tbltabla.getValueAt(i, 2).toString(), tbltabla.getValueAt(i, 3).toString(),};
                    cargar.addRow(filtrarword);
                }
            }
        } else if (combobox.equals("C")) {
            cargar.setRowCount(0);
            cargar.addColumn(tbltabla.getColumnName(0));
            cargar.addColumn(tbltabla.getColumnName(1));
            cargar.addColumn(tbltabla.getColumnName(2));
            cargar.addColumn(tbltabla.getColumnName(3));
            combobox = "c";
            for (int i = 0; i <= filas - 1; i++) {
                String nombre = tbltabla.getValueAt(i, 2).toString();
                if (nombre.endsWith(combobox.toLowerCase())) {
                    String[] filtrarc = {tbltabla.getValueAt(i, 0).toString(), tbltabla.getValueAt(i, 1).toString(),
                        tbltabla.getValueAt(i, 2).toString(), tbltabla.getValueAt(i, 3).toString(),};
                    cargar.addRow(filtrarc);
                    
                }
            }
        }
        if(combobox.equals("TODOS")){
            cargarcbx();
        } else {
            tbltabla.setModel(cargar);
            cargarcbx();
        }
//cristian
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Busqueda = new javax.swing.JLabel();
        btnagregar = new javax.swing.JButton();
        javax.swing.JPanel Lista = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltabla = new javax.swing.JTable();
        cbxabrir = new javax.swing.JComboBox<>();
        btnabrir = new javax.swing.JButton();
        txtnombre = new javax.swing.JTextField();
        JLabel5 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblnombre = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtnombrebuscar = new javax.swing.JTextField();
        btnrefrescar = new javax.swing.JButton();
        cbxtipos = new javax.swing.JComboBox<>();
        btnbuscar = new javax.swing.JButton();
        bienvenida2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(null);

        Busqueda.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Busqueda.setForeground(new java.awt.Color(0, 0, 0));
        Busqueda.setText("Buscar archivos:");
        jPanel1.add(Busqueda);
        Busqueda.setBounds(500, 30, 251, 26);

        btnagregar.setBackground(new java.awt.Color(255, 255, 255));
        btnagregar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnagregar.setForeground(new java.awt.Color(0, 0, 0));
        btnagregar.setText("Agregar Archivo");
        btnagregar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnagregar);
        btnagregar.setBounds(280, 130, 135, 33);

        Lista.setBackground(new java.awt.Color(255, 255, 255));
        Lista.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tus archivos guardados:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        Lista.setForeground(new java.awt.Color(0, 0, 0));
        Lista.setAutoscrolls(true);
        Lista.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                ListaComponentAdded(evt);
            }
        });
        Lista.setLayout(null);

        tbltabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbltabla);

        Lista.add(jScrollPane1);
        jScrollPane1.setBounds(20, 30, 452, 234);

        cbxabrir.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Lista.add(cbxabrir);
        cbxabrir.setBounds(530, 30, 110, 22);

        btnabrir.setBackground(new java.awt.Color(255, 255, 255));
        btnabrir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnabrir.setForeground(new java.awt.Color(0, 0, 0));
        btnabrir.setText("Abrir archivo");
        btnabrir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnabrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnabrirActionPerformed(evt);
            }
        });
        Lista.add(btnabrir);
        btnabrir.setBounds(530, 90, 120, 30);

        jPanel1.add(Lista);
        Lista.setBounds(10, 210, 770, 280);

        txtnombre.setBackground(new java.awt.Color(255, 255, 255));
        txtnombre.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtnombre);
        txtnombre.setBounds(140, 102, 100, 30);

        JLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLabel5.setForeground(new java.awt.Color(0, 0, 0));
        JLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabel5.setText("Tipo");
        jPanel1.add(JLabel5);
        JLabel5.setBounds(480, 160, 60, 20);

        txtdireccion.setBackground(new java.awt.Color(255, 255, 255));
        txtdireccion.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtdireccion);
        txtdireccion.setBounds(140, 152, 100, 30);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Direccion de carpeta:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 160, 120, 20);

        jPanel2.setLayout(null);
        jPanel1.add(jPanel2);
        jPanel2.setBounds(459, 0, 10, 210);

        lblnombre.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lblnombre.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(lblnombre);
        lblnombre.setBounds(230, 30, 160, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nombre del archivo");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 110, 110, 20);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Nombre");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(480, 110, 70, 20);

        txtnombrebuscar.setBackground(new java.awt.Color(255, 255, 255));
        txtnombrebuscar.setForeground(new java.awt.Color(0, 0, 0));
        txtnombrebuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombrebuscarActionPerformed(evt);
            }
        });
        jPanel1.add(txtnombrebuscar);
        txtnombrebuscar.setBounds(550, 102, 90, 30);

        btnrefrescar.setBackground(new java.awt.Color(255, 255, 255));
        btnrefrescar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnrefrescar.setForeground(new java.awt.Color(0, 0, 0));
        btnrefrescar.setText("Refrescar");
        btnrefrescar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnrefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefrescarActionPerformed(evt);
            }
        });
        jPanel1.add(btnrefrescar);
        btnrefrescar.setBounds(680, 160, 83, 30);

        cbxtipos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS", "PDF", "WORD", "C" }));
        cbxtipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxtiposActionPerformed(evt);
            }
        });
        jPanel1.add(cbxtipos);
        cbxtipos.setBounds(550, 160, 90, 22);

        btnbuscar.setBackground(new java.awt.Color(255, 255, 255));
        btnbuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnbuscar.setForeground(new java.awt.Color(0, 0, 0));
        btnbuscar.setText("Buscar");
        btnbuscar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnbuscar);
        btnbuscar.setBounds(680, 100, 83, 30);

        bienvenida2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        bienvenida2.setForeground(new java.awt.Color(0, 0, 0));
        bienvenida2.setText("Bienvenido de vuelta,");
        jPanel1.add(bienvenida2);
        bienvenida2.setBounds(20, 30, 210, 26);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        guardar();
    }//GEN-LAST:event_btnagregarActionPerformed

    private void ListaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_ListaComponentAdded

    }//GEN-LAST:event_ListaComponentAdded

    private void txtnombrebuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombrebuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombrebuscarActionPerformed

    private void btnabrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnabrirActionPerformed
        AbrirArchivo();
    }//GEN-LAST:event_btnabrirActionPerformed

    private void btnrefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefrescarActionPerformed
        cargarTabla();
        cargarcbx();
    }//GEN-LAST:event_btnrefrescarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        buscarDocumento();
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void cbxtiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxtiposActionPerformed
        cbxfiltro();
    }//GEN-LAST:event_cbxtiposActionPerformed

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
            java.util.logging.Logger.getLogger(Matias1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Matias1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Matias1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Matias1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Matias1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Busqueda;
    private javax.swing.JLabel JLabel5;
    private javax.swing.JLabel bienvenida2;
    private javax.swing.JButton btnabrir;
    private javax.swing.JButton btnagregar;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnrefrescar;
    private javax.swing.JComboBox<String> cbxabrir;
    private javax.swing.JComboBox<String> cbxtipos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JLabel lblnombre;
    private javax.swing.JTable tbltabla;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnombrebuscar;
    // End of variables declaration//GEN-END:variables
}
