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
public class Cls_BuscarProductoPorEntrada {
    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_SELECT_PRODUCTOS_X_ENTRADA = "SELECT * FROM VW_PRODUCTOS_EN_STOCK"; 
    
    public Cls_BuscarProductoPorEntrada(){
        PS = null;
        CN = new Conectar();
    }
    
    private DefaultTableModel setTitulosProductosPorEntrada(){
        DT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        DT.addColumn("Entrada #");
        DT.addColumn("Código");
        DT.addColumn("Descripción");                
        DT.addColumn("Fecha de Entrada");
        DT.addColumn("Precio de Venta");
        DT.addColumn("Caducidad");
        DT.addColumn("Stock");
        
        return DT;
    }
    
    public DefaultTableModel getDatosProductosPorEntrada(){
        try {
            setTitulosProductosPorEntrada();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_PRODUCTOS_X_ENTRADA);
            RS = PS.executeQuery();
            Object[] fila = new Object[8];
            while(RS.next()){
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);                
                fila[3] = RS.getDate(4);
                fila[4] = RS.getFloat(5);
                fila[5] = RS.getDate(6);
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
            SQL = "SELECT * FROM VW_PRODUCTOS_EN_STOCK WHERE pro_codigo like '"+inf+"%'";
        }
        else {           
            SQL = "SELECT * FROM VW_PRODUCTOS_EN_STOCK WHERE Descripcion like '%"+inf+"%'";
        }
        try {
            setTitulosProductosPorEntrada();
            PS = CN.getConnection().prepareStatement(SQL);
            RS = PS.executeQuery();
            Object[] fila = new Object[8];
            while(RS.next()){
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);                
                fila[3] = RS.getDate(4);
                fila[4] = RS.getFloat(5);
                fila[5] = RS.getDate(6);
                fila[6] = RS.getInt(7);
                DT.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos por."+e.getMessage());
        } finally{
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT;
    }
    
}
