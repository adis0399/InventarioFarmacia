package Formularios;

import Modelo.Usuario;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.UIManager;

public class Frm_Principal extends javax.swing.JFrame {

    public static Frm_Login frmLog;
    Usuario mod;

    public Frm_Principal() {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        txt_menu.requestFocus();
    }

    public Frm_Principal(Usuario mod) {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        txt_menu.requestFocus();
        this.mod = mod;

        lblNombre.setText(this.mod.getNombre());
        
        switch (this.mod.getId_tipo()) {
            case 1:                 
                break;
            case 2:
                jbtnMedicamentos.setEnabled(false);
                jbtnProductos.setEnabled(false);
                jbtnEntradas.setEnabled(false);
                jbtnSalidas.setEnabled(false);                             
                break;
            case 3:                
                jbtnMedicamentos.setEnabled(false);
                jbtnProductos.setEnabled(false);
                jbtnEntradas.setEnabled(false);
                jbtnSalidas.setEnabled(false);
                jbtnReportes.setEnabled(false);
                jbtnUsuarios.setEnabled(false);
                break;
            case 4:                
                jbtnUsuarios.setEnabled(false);
                break;                           
            default:
                throw new AssertionError();
        }
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/cheque.png"));

        return retValue;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        contenedor = new javax.swing.JDesktopPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_menu = new javax.swing.JLabel();
        jbtnProductos = new javax.swing.JButton();
        jbtnEntradas = new javax.swing.JButton();
        jbtnSalidas = new javax.swing.JButton();
        jbtnInventario = new javax.swing.JButton();
        jbtnMedicamentos = new javax.swing.JButton();
        jbtnUsuarios = new javax.swing.JButton();
        jbtnReportes = new javax.swing.JButton();
        jbtnLogout = new javax.swing.JButton();
        lblNombre = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Inventario");
        setIconImage(getIconImage());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        contenedor.setBackground(new java.awt.Color(255, 255, 255));
        contenedor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        contenedor.setPreferredSize(new java.awt.Dimension(1041, 551));

        javax.swing.GroupLayout contenedorLayout = new javax.swing.GroupLayout(contenedor);
        contenedor.setLayout(contenedorLayout);
        contenedorLayout.setHorizontalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1038, Short.MAX_VALUE)
        );
        contenedorLayout.setVerticalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );

        jPanel1.add(contenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 1040, 550));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sistema de Inventario");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LOGO.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 120, 100));

        txt_menu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_menu.setForeground(new java.awt.Color(255, 255, 255));
        txt_menu.setText("MENU PRINCIPAL");
        jPanel1.add(txt_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        jbtnProductos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbtnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/paquete.png"))); // NOI18N
        jbtnProductos.setText("Productos");
        jbtnProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnProductosActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 230, 40));

        jbtnEntradas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbtnEntradas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/entrar.png"))); // NOI18N
        jbtnEntradas.setText("Entradas");
        jbtnEntradas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEntradasActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnEntradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 230, 40));

        jbtnSalidas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbtnSalidas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/venta.png"))); // NOI18N
        jbtnSalidas.setText("Salidas");
        jbtnSalidas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnSalidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalidasActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnSalidas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 230, 40));

        jbtnInventario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbtnInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/inventario.png"))); // NOI18N
        jbtnInventario.setText("Inventario");
        jbtnInventario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnInventarioActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 230, 40));

        jbtnMedicamentos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbtnMedicamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ic_medi.png"))); // NOI18N
        jbtnMedicamentos.setText("Medicamentos");
        jbtnMedicamentos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnMedicamentos.setMaximumSize(new java.awt.Dimension(121, 38));
        jbtnMedicamentos.setMinimumSize(new java.awt.Dimension(121, 38));
        jbtnMedicamentos.setPreferredSize(new java.awt.Dimension(121, 38));
        jbtnMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnMedicamentosActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnMedicamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 230, 40));

        jbtnUsuarios.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbtnUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ic_usuario.png"))); // NOI18N
        jbtnUsuarios.setText("Usuarios");
        jbtnUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnUsuarios.setMaximumSize(new java.awt.Dimension(121, 38));
        jbtnUsuarios.setMinimumSize(new java.awt.Dimension(121, 38));
        jbtnUsuarios.setPreferredSize(new java.awt.Dimension(121, 38));
        jbtnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUsuariosActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 230, 40));

        jbtnReportes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbtnReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ic_reporte.png"))); // NOI18N
        jbtnReportes.setText("Reportes");
        jbtnReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnReportes.setMaximumSize(new java.awt.Dimension(121, 38));
        jbtnReportes.setMinimumSize(new java.awt.Dimension(121, 38));
        jbtnReportes.setPreferredSize(new java.awt.Dimension(121, 38));
        jbtnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReportesActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 230, 40));

        jbtnLogout.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbtnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ic_logout.png"))); // NOI18N
        jbtnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 10, -1, -1));

        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNombre.setText("Nombre del Usuario");
        jPanel1.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, 180, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo-azul.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 670));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnProductosActionPerformed
        Frm_Productos f = new Frm_Productos();
        contenedor.add(f);
        f.show();
    }//GEN-LAST:event_jbtnProductosActionPerformed

    private void jbtnEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEntradasActionPerformed
        Frm_Entrada f = new Frm_Entrada();
        contenedor.add(f);
        f.show();
    }//GEN-LAST:event_jbtnEntradasActionPerformed

    private void jbtnSalidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalidasActionPerformed
        Frm_Salida f = new Frm_Salida();
        contenedor.add(f);
        f.show();
    }//GEN-LAST:event_jbtnSalidasActionPerformed

    private void jbtnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnInventarioActionPerformed
        Frm_Inventario f = new Frm_Inventario();
        contenedor.add(f);
        f.show();
    }//GEN-LAST:event_jbtnInventarioActionPerformed

    private void jbtnMedicamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnMedicamentosActionPerformed
        Frm_DescripcionProd f = new Frm_DescripcionProd();
        contenedor.add(f);
        f.show();
    }//GEN-LAST:event_jbtnMedicamentosActionPerformed

    private void jbtnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReportesActionPerformed
        // TODO add your handling code here:
        Frm_Reporte f = new Frm_Reporte();
        contenedor.add(f);
        f.show();
    }//GEN-LAST:event_jbtnReportesActionPerformed

    private void jbtnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUsuariosActionPerformed
        // TODO add your handling code here:
        Frm_Usuario f = new Frm_Usuario();
        contenedor.add(f);
        f.show();
    }//GEN-LAST:event_jbtnUsuariosActionPerformed

    private void jbtnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLogoutActionPerformed
        if (frmLog == null) {
            frmLog = new Frm_Login();
            frmLog.setVisible(true);
            frmLog.show();
            dispose();
        }
    }//GEN-LAST:event_jbtnLogoutActionPerformed

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
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frm_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane contenedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtnEntradas;
    private javax.swing.JButton jbtnInventario;
    private javax.swing.JButton jbtnLogout;
    private javax.swing.JButton jbtnMedicamentos;
    private javax.swing.JButton jbtnProductos;
    private javax.swing.JButton jbtnReportes;
    private javax.swing.JButton jbtnSalidas;
    private javax.swing.JButton jbtnUsuarios;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel txt_menu;
    // End of variables declaration//GEN-END:variables
}
