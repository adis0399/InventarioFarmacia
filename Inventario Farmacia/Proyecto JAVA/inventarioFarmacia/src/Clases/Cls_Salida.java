package Clases;

import Conexion.Conectar;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cls_Salida {
    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_SALIDA = "INSERT INTO salida (sal_ent_id, sal_factura, sal_fecha, sal_cantidad, tipo_sal_id) values (?,?,?,?,?)";
    private final String SQL_SELECT_SALIDA = "SELECT s.sal_factura, s.sal_fecha, e.ent_pro_codigo, dp.descripcion_nombre, r.referencia_nombre, pre.presentacion_nombre, s.sal_cantidad, tp.tipo_salida FROM salida s \n" +
"INNER JOIN tipo_salida tp ON s.tipo_sal_id = tp.tipo_sal_id\n" +
"INNER JOIN entrada e ON s.sal_ent_id = e.ent_id\n" +
"INNER JOIN producto p ON e.ent_pro_codigo = p.pro_codigo\n" +
"INNER JOIN descripcion_producto dp ON p.pro_descripcion_id = dp.descripcion_id\n" +
"INNER JOIN referencia r ON p.pro_referencia_id = r.referencia_id\n" +
"INNER JOIN presentacion pre ON p.pro_presentacion_id = pre.presentacion_id\n" +
"ORDER BY s.sal_fecha ASC LIMIT 30";
    
    public Cls_Salida(){
        PS = null;
        CN = new Conectar();
    }
    
    private DefaultTableModel setTitulosSalida(){
        DT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        DT.addColumn("N° de Factura");
        DT.addColumn("Fecha");
        DT.addColumn("Código de Producto");
        DT.addColumn("Descripción");
        DT.addColumn("Referencia");
        DT.addColumn("Presentación");
        DT.addColumn("Cantidad");
        DT.addColumn("Tipo De Salida");        
        return DT;
    }
    
    public DefaultTableModel getDatosSalida(){
        try {
            setTitulosSalida();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_SALIDA);
            RS = PS.executeQuery();
            Object[] fila = new Object[9];
            while(RS.next()){
                fila[0] = RS.getString(1);
                fila[1] = RS.getDate(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
                fila[5] = RS.getString(6);
                fila[6] = RS.getInt(7);
                fila[7] = RS.getString(8);
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
    
    public int registrarSalida(int ent_id, String nfactura, Date fecha, int cantidad, int tipo_salida){
        int res=0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_SALIDA);
            PS.setInt(1, ent_id);
            PS.setString(2, nfactura);
            PS.setDate(3, fecha);
            PS.setInt(4, cantidad);
            PS.setInt(5, tipo_salida);            
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Salida realizada con éxito.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar la salida.");
            System.err.println("Error al registrar la salida: " +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
    public int verificarStock(String codigo){
        int res=0;
        try {
            PS = CN.getConnection().prepareStatement("SELECT inv_stock from inventario where inv_pro_codigo='"+codigo+"'");
            RS = PS.executeQuery();
            
            while(RS.next()){
                res = RS.getInt(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al devolver cantidad de registros." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
    public int verificarStockLote(String codigo){
        int res=0;
        try {
            PS = CN.getConnection().prepareStatement("SELECT stock from entrada where ent_pro_codigo='"+codigo+"'");
            RS = PS.executeQuery();
            
            while(RS.next()){
                res = RS.getInt(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al devolver cantidad de registros." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
}
