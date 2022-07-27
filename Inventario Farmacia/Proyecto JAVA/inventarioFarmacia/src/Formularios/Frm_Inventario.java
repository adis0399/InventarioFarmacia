package Formularios;

import Clases.Cls_Inventario;
import javax.swing.table.TableColumnModel;


public class Frm_Inventario extends javax.swing.JInternalFrame {
    private final Cls_Inventario CP;
    TableColumnModel columnModel;
    public static int enviar = 0;
    int num = 0;
    
    public Frm_Inventario() {
        initComponents();
        CP = new Cls_Inventario();
        columnModel = jtb_inventario.getColumnModel();
        listar();
    }
    
    private void listar(){
        jtb_inventario.setModel(CP.getDatosInventario());
        columnModel.getColumn(1).setPreferredWidth(400);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_busqueda = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtb_inventario = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jrb_nombre = new javax.swing.JRadioButton();
        jrb_codigo = new javax.swing.JRadioButton();
        txt_busqueda = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Productos");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jtb_inventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtb_inventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtb_inventarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtb_inventario);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Inventario de Productos");

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel7.setText("Entrada, Salida y Stock de Productos.");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Búsqueda por:");

        jrb_nombre.setBackground(new java.awt.Color(255, 255, 255));
        bg_busqueda.add(jrb_nombre);
        jrb_nombre.setText("Descripción");

        jrb_codigo.setBackground(new java.awt.Color(255, 255, 255));
        bg_busqueda.add(jrb_codigo);
        jrb_codigo.setText("Código");

        txt_busqueda.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_busquedaCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrb_codigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrb_nombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_busqueda))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1)
                    .addComponent(jrb_nombre)
                    .addComponent(jrb_codigo)
                    .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

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

    private void jtb_inventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_inventarioMouseClicked

    }//GEN-LAST:event_jtb_inventarioMouseClicked

    private void txt_busquedaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_busquedaCaretUpdate
        if(jrb_nombre.isSelected()){
            String inf = txt_busqueda.getText();
            jtb_inventario.setModel(CP.getDatoP(1,inf));
            columnModel.getColumn(1).setPreferredWidth(400);
        }

        if(jrb_codigo.isSelected()){
            String inf = txt_busqueda.getText();
            jtb_inventario.setModel(CP.getDatoP(2,inf));
            columnModel.getColumn(1).setPreferredWidth(400);
        }

        if(txt_busqueda.getText().isEmpty()){
            jtb_inventario.setModel(CP.getDatosInventario());
            columnModel.getColumn(1).setPreferredWidth(400);
        }
    }//GEN-LAST:event_txt_busquedaCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bg_busqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jrb_codigo;
    private javax.swing.JRadioButton jrb_nombre;
    private javax.swing.JTable jtb_inventario;
    private javax.swing.JTextField txt_busqueda;
    // End of variables declaration//GEN-END:variables
}
