/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Conexion.Conectar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author adis0
 */
public class Cls_DescripcionProducto {
    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_DES_PRODUCTOS = "INSERT INTO descripcion_producto (descripcion_nombre) values (?)";
    private final String SQL_SELECT_DES_PRODUCTOS = "SELECT * FROM descripcion_producto ORDER BY descripcion_nombre ASC";
    
    
    public Cls_DescripcionProducto(){
        PS = null;
        CN = new Conectar();
    }
    
    private DefaultTableModel setTitulosDescripcionProductos(){
        DT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        DT.addColumn("Código");
        DT.addColumn("Descripción Medicina");
        return DT;
    }
    
    public DefaultTableModel getDatosDescripcionProductos(){
        try {
            setTitulosDescripcionProductos();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_DES_PRODUCTOS);
            RS = PS.executeQuery();
            Object[] fila = new Object[2];
            while(RS.next()){
                fila[0] = RS.getString(1);
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
    
    public int registrarDescripcionProducto(String descripcion){
        int res=0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_DES_PRODUCTOS);
            PS.setString(1, descripcion);            
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Producto registrado con éxito.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar el producto.");
            System.err.println("Error al registrar el producto." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
        
    
    public int actualizarDescripcionProducto(int codigo, String descripcion_nombre_New){
        String SQL = "UPDATE descripcion_producto SET descripcion_nombre='"+descripcion_nombre_New+"' WHERE descripcion_id='"+codigo+"'";
        int res=0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Producto actualizado con éxito");
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar los datos del cliente." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
    public int eliminarDescripcionProducto(String codigo){
        String SQL = "DELETE from descripcion_producto WHERE descripcion_id ='"+codigo+"'";
        int res=0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Producto eliminado con éxito");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No es posible eliminar el producto.");
            System.err.println("Error al eliminar producto." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
}
