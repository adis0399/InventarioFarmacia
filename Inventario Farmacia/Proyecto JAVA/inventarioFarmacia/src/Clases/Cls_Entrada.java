package Clases;

import Conexion.Conectar;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cls_Entrada {
    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_ENTRADA = "INSERT INTO entrada (ent_factura, ent_pro_codigo, ent_fecha, ent_cantidad, ent_precio_compra, ent_precio_venta, ent_caducidad, stock) "
            + "values (?,?,?,?,?,?,?,?)";
    private final String SQL_SELECT_ENTRADA = "SELECT e.ent_factura, e.ent_pro_codigo, d.descripcion_nombre, "
            + "r.referencia_nombre, pre.presentacion_nombre, e.ent_fecha, e.ent_cantidad, e.ent_precio_compra, e.ent_precio_venta, e.ent_caducidad, e.stock "
            + "FROM entrada e INNER JOIN producto p ON e.ent_pro_codigo = p.pro_codigo INNER JOIN descripcion_producto d ON p.pro_descripcion_id = d.descripcion_id "
            + "INNER JOIN referencia r ON p.pro_referencia_id = r.referencia_id INNER JOIN presentacion pre ON p.pro_presentacion_id = pre.presentacion_id "
            + "ORDER BY e.ent_fecha DESC LIMIT 30";
    
    public Cls_Entrada(){
        PS = null;
        CN = new Conectar();
    }
    
    private DefaultTableModel setTitulosEntrada(){
        DT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        DT.addColumn("N° de Factura");
        DT.addColumn("Código de Producto");
        DT.addColumn("Descripcion");
        DT.addColumn("Referencia");
        DT.addColumn("Presentación");
        DT.addColumn("Fecha");        
        DT.addColumn("Cantidad");
        DT.addColumn("Precio Compra");
        DT.addColumn("Precio Venta");
        DT.addColumn("Caducidad");
        DT.addColumn("Stock");
        
        return DT;
    }
    
    public DefaultTableModel getDatosEntradas(){
        try {
            setTitulosEntrada();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_ENTRADA);
            RS = PS.executeQuery();
            Object[] fila = new Object[11];
            while(RS.next()){
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
                fila[5] = RS.getDate(6);
                fila[6] = RS.getInt(7);                
                fila[7] = RS.getFloat(8);
                fila[8] = RS.getFloat(9);
                fila[9] = RS.getDate(10);
                fila[10] = RS.getInt(11);
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
    
    public int registrarEntrada(String nfactura, String codigo, Date fecha, int cantidad, float precio_compra, float precio_venta, Date caducidad){
        int res=0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_ENTRADA);
            PS.setString(1, nfactura);
            PS.setString(2, codigo);
            PS.setDate(3, fecha);
            PS.setInt(4, cantidad);
            PS.setFloat(5, precio_compra);
            PS.setFloat(6, precio_venta);
            PS.setDate(7, caducidad);
            PS.setInt(8, cantidad);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Entrada realizada con éxito.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar la entrada.");
            System.err.println("Error al registrar la entrada." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
}
