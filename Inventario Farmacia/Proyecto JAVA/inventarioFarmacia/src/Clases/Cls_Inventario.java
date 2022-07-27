package Clases;

import Conexion.Conectar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Cls_Inventario {
    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    /*private final String SQL_SELECT_INVENTARIO = "SELECT i.inv_pro_codigo, dp.descripcion_nombre, r.referencia_nombre, pre.presentacion_nombre, i.inv_entradas, i.inv_salidas, i.inv_stock "
            + "FROM inventario i INNER JOIN producto p ON i.inv_pro_codigo = p.pro_codigo "
            + "INNER JOIN descripcion_producto dp ON p.pro_descripcion_id = dp.descripcion_id "
            + "INNER JOIN referencia r ON p.pro_referencia_id = r.referencia_id "
            + "INNER JOIN presentacion pre ON p.pro_presentacion_id = pre.presentacion_id "
            + "WHERE i.inv_stock > 0 ORDER BY dp.descripcion_nombre ASC;";*/
    private final String SQL_SELECT_INVENTARIO_STOCK = "SELECT * FROM VIEW_INVENTARIO_ESPECIFICO_STOCK";
    
    public Cls_Inventario(){
        PS = null;
        CN = new Conectar();
    }
    
    private DefaultTableModel setTitulosInventario(){
        DT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        DT.addColumn("Código");
        DT.addColumn("Descripción");
        DT.addColumn("Referencia");
        DT.addColumn("Presentación");
        DT.addColumn("Entrada");
        DT.addColumn("Salida");
        DT.addColumn("Stock");
        return DT;
    }
    
    public DefaultTableModel getDatosInventario(){
        try {
            setTitulosInventario();
            //PS = CN.getConnection().prepareStatement(SQL_SELECT_INVENTARIO);
            PS = CN.getConnection().prepareStatement(SQL_SELECT_INVENTARIO_STOCK);
            RS = PS.executeQuery();
            Object[] fila = new Object[7];
            while(RS.next()){
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getInt(5);
                fila[5] = RS.getInt(6);
                fila[6] = RS.getInt(7);
                DT.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos."+e.getMessage());
        } finally{
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT;
    }
    
    public DefaultTableModel getDatoP(int crt, String inf){
        String SQL;
        if (crt==2){
            //SQL = "SELECT pro_codigo, pro_descripcion_id, inv_stock FROM producto INNER JOIN inventario ON pro_codigo = inv_pro_codigo where pro_codigo like '"+inf+"'";
            SQL = "SELECT * FROM VIEW_INVENTARIO_ESPECIFICO_STOCK WHERE inv_pro_codigo like '"+inf+"%'";
        }
        else {
            //SQL = "SELECT pro_codigo, pro_descripcion_id, inv_stock FROM producto INNER JOIN inventario ON pro_codigo = inv_pro_codigo where pro_descripcion like '" +inf + "%'";
            SQL = "SELECT * FROM VIEW_INVENTARIO_ESPECIFICO_STOCK WHERE descripcion_nombre like '%"+inf+"%'";
        }
        try {
            setTitulosInventario();
            PS = CN.getConnection().prepareStatement(SQL);
            RS = PS.executeQuery();
            Object[] fila = new Object[7];
            while(RS.next()){
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getInt(5);
                fila[5] = RS.getInt(6);
                fila[6] = RS.getInt(7);
                DT.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos."+e.getMessage());
        } finally{
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT;
    }
    
}
