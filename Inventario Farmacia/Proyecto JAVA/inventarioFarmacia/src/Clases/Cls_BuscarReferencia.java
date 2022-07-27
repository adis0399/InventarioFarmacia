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
public class Cls_BuscarReferencia {
    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_SELECT_REFERENCIA = "SELECT * FROM referencia ORDER BY referencia_nombre ASC";
    private int referencia;

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }
    
    
    
    
    public Cls_BuscarReferencia(){
        PS = null;
        CN = new Conectar();
    }
    private DefaultTableModel setReferencias(){
        DT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        DT.addColumn("Código");
        DT.addColumn("Referencia");                
        return DT;
    }
    public DefaultTableModel getDatosReferencias(){
        try {
            setReferencias();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_REFERENCIA);
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
        String SQL = "SELECT * FROM referencia where referencia_nombre like '" +inf + "%'";                
        try {
            setReferencias();
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
