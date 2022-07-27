/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author adis0
 */
public class Producto {
    private String pro_codigo;
    private String pro_descripcion_id;
    private String pro_referencia_id;
    private String pro_presentacion_id;

    public Producto() {
    }
        
    public Producto(String pro_codigo, String pro_descripcion_id, String pro_referencia_id, String pro_presentacion_id) {
        this.pro_codigo = pro_codigo;
        this.pro_descripcion_id = pro_descripcion_id;
        this.pro_referencia_id = pro_referencia_id;
        this.pro_presentacion_id = pro_presentacion_id;
    }

    public String getPro_codigo() {
        return pro_codigo;
    }

    public void setPro_codigo(String pro_codigo) {
        this.pro_codigo = pro_codigo;
    }

    public String getPro_descripcion_id() {
        return pro_descripcion_id;
    }

    public void setPro_descripcion_id(String pro_descripcion_id) {
        this.pro_descripcion_id = pro_descripcion_id;
    }

    public String getPro_referencia_id() {
        return pro_referencia_id;
    }

    public void setPro_referencia_id(String pro_referencia_id) {
        this.pro_referencia_id = pro_referencia_id;
    }

    public String getPro_presentacion_id() {
        return pro_presentacion_id;
    }

    public void setPro_presentacion_id(String pro_presentacion_id) {
        this.pro_presentacion_id = pro_presentacion_id;
    }
    
    
    
}
