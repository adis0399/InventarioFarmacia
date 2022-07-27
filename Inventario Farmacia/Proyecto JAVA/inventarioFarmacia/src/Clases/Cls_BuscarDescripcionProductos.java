/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Conexion.Conectar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author adis0
 */
public class Cls_BuscarDescripcionProductos {
    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_SELECT_DESCRIPCIONES = "SELECT * FROM descripcion_producto ORDER BY descripcion_nombre ASC";
    private int des_pro_cod;
    //private final String SQL_SELECT_PRODUCTOS_LOTE = "SELECT p.pro_codigo, p.pro_descripcion, e.precio_venta FROM producto p INNER JOIN entrada e ON p.pro_codigo = e.ent_pro_codigo ORDER BY p.pro_descripcion ASC";

    public int getDes_pro_cod() {
        return des_pro_cod;
    }

    public void setDes_pro_cod(int des_pro_cod) {
        this.des_pro_cod = des_pro_cod;
    }
    
    
    
    public Cls_BuscarDescripcionProductos(){
        PS = null;
        CN = new Conectar();
    }
    
    private DefaultTableModel setDescripcionesProductos(){
        DT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        DT.addColumn("Código");
        DT.addColumn("Medicina");                
        return DT;
    }
    public DefaultTableModel getDatosDescricionProductos(){
        try {
            setDescripcionesProductos();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_DESCRIPCIONES);
            RS = PS.executeQuery();
            Object[] fila = new Object[3];
            while(RS.next()){
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);                
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
            SQL = "SELECT pro_codigo, pro_descripcion, inv_stock FROM producto INNER JOIN inventario ON pro_codigo = inv_pro_codigo where pro_codigo like '"+inf+"'";
        }
        else {
            SQL = "SELECT descripcion_id, descripcion_nombre FROM descripcion_producto where descripcion_nombre like '%"+inf+"%'";
        }
        try {
            setDescripcionesProductos();
            PS = CN.getConnection().prepareStatement(SQL);
            RS = PS.executeQuery();
            Object[] fila = new Object[3];
            while(RS.next()){
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2); 
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
