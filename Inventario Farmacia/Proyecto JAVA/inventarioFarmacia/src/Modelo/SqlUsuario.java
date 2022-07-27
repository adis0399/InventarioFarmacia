/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Conexion.Conectar;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author adis0
 */
public class SqlUsuario extends Conectar {

    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_SELECT_USUARIOS = "SELECT u.id, u.usuario, u.password, u.nombre, t.nombre_tipo_usuario FROM usuarios u "
            + "INNER JOIN tipo_usuario t ON u.id_tipo = t.tipo_usuario_id "
            + "WHERE u.estado = 1;";

    public SqlUsuario() {
        PS = null;
        CN = new Conectar();
    }

    private DefaultTableModel setUsuarios() {
        DT = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        DT.addColumn("Id");
        DT.addColumn("Usuario");
        DT.addColumn("Password");
        DT.addColumn("Nombre");
        DT.addColumn("Tipo de Usuario");

        return DT;
    }

    public DefaultTableModel getUsuarios() {
        try {
            setUsuarios();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_USUARIOS);
            RS = PS.executeQuery();
            Object[] fila = new Object[5];
            while (RS.next()) {
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
                DT.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los Usuarios." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT;
    }

    public boolean registrar(Usuario usr) {
        String sql = "INSERT INTO usuarios (usuario, password, nombre, id_tipo, estado) VALUES (?,?,?,?,'1')";

        try {
            PS = CN.getConnection().prepareStatement(sql);
            PS.setString(1, usr.getUsuario());
            PS.setString(2, usr.getPassword());
            PS.setString(3, usr.getNombre());
            PS.setInt(4, usr.getId_tipo());
            PS.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            PS = null;
            CN.desconectar();
        }
    }

    public boolean actualizarUsuario(Usuario usr) {
        String sql = "UPDATE usuarios SET usuario = '" +usr.getUsuario()+"', password = '"+usr.getPassword()+"', nombre = '"+usr.getNombre()+"', id_tipo = '"+usr.getId_tipo()+"' WHERE id = '"+usr.getId()+"'";
        int res = 0;
        boolean resul = false;
        try {
            PS = CN.getConnection().prepareStatement(sql);            
            res = PS.executeUpdate();
            if (res > 0) {
                resul = true;
            } 
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            PS = null;
            CN.desconectar();
        }
        return resul;
    }
    
    public int bajaUsuario(String id){
        String SQL = "UPDATE usuarios SET estado = '0' WHERE id='"+id+"'";
        int res = 0;        
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Usuario dado de baja con éxito");                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No es posible dar de baja al usuario.");
            System.err.println("Error al dar de baja usuario." +e.getMessage());            
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }       

    public boolean login(Usuario usr) {        

        String sql = "SELECT u.id, u.usuario, u.password, u.nombre, u.id_tipo, t.nombre_tipo_usuario FROM usuarios u "
                + "INNER JOIN tipo_usuario t WHERE u.usuario=? AND u.estado ='1'";        
        try {
            PS = CN.getConnection().prepareStatement(sql);
            PS.setString(1, usr.getUsuario());
            RS = PS.executeQuery();
 
            if (RS.next()) {
                if (usr.getPassword().equals(RS.getString(3))) {
                    String sqlUpdate = "UPDATE usuarios SET last_session = ? WHERE id = ?";
                    PS = CN.getConnection().prepareStatement(sqlUpdate);
                    PS.setString(1, usr.getLast_session());
                    PS.setInt(2, RS.getInt(1));
                    PS.execute();

                    usr.setId(RS.getInt(1));
                    usr.setNombre(RS.getString(4));
                    usr.setId_tipo(RS.getInt(5));
                    usr.setNombre_tipo_usuario(RS.getString(6));

                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            PS = null;
            CN.desconectar();
        }
        return false;
    }

    public String buscarTipoUsuario(String nombre_tipo_usuario) {
        String val = "";
        String sql = "SELECT tipo_usuario_id FROM tipo_usuario WHERE nombre_tipo_usuario = ?";

        try {
            PS = CN.getConnection().prepareStatement(sql);
            PS.setString(1, nombre_tipo_usuario);
            RS = PS.executeQuery();
            while (RS.next()) {
                val = String.valueOf(RS.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            PS = null;
            CN.desconectar();
        }
        return val;
    }

}
