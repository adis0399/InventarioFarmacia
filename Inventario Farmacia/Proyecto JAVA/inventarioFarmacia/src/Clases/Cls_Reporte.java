/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Conexion.Conectar;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author adis0
 */
public class Cls_Reporte {
    //private static PreparedStatement PS;
    //private static ResultSet RS;
    //private static Conectar CN;
    /*private final String SQL_ENTRADAS = "SELECT e.ent_factura, e.ent_pro_codigo, d.descripcion_nombre, r.referencia_nombre, pre.presentacion_nombre, e.ent_fecha, "
            + "e.ent_cantidad, e.ent_precio_compra, e.ent_precio_venta, e.ent_caducidad FROM entrada e INNER JOIN producto p ON e.ent_pro_codigo = p.pro_codigo "
            + "INNER JOIN descripcion_producto d ON p.pro_descripcion_id = d.descripcion_id "
            + "INNER JOIN referencia r ON p.pro_referencia_id = r.referencia_id "
            + "INNER JOIN presentacion pre ON p.pro_presentacion_id = pre.presentacion_id ORDER BY e.ent_fecha DESC;";*/
    
    /*public Cls_Reporte(){
        //PS = null;
        //CN = new Conectar();
    }*/
    
    public static void main(String[] args) {
        reporte();
    }
    
    public static void reporte(){
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Entradas");
        sheet.setColumnWidth(3, 25 * 256);
        PreparedStatement PS = null;
        ResultSet RS;
        Conectar CN = new Conectar();
        String SQL_ENTRADAS = "SELECT * FROM vw_entradas;";
        
        String SQL_SALIDAS = "SELECT * FROM vw_salidas";
        try {
            //ENCABEZADO IMAGEN
            InputStream is = new FileInputStream("src\\Imagenes\\Casa_del_alfarero.jpg");
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
            is.close();
            
            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();
            
            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(4,10);
            
            //ESTILO DEL TITULO
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short)14);
            tituloEstilo.setFont(fuenteTitulo);
            
            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(4);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Reporte de Entradas y Salidas");
            
            //LOCALIZACIÓN DEL TITULO
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 7));
            
            //ENCABEZADOS DE ENTRADAS
            String[] cabeceraEntradas = new String[]{"Factura","Codigo Producto","Descripción","Referencia","Presentación","Fecha de Entrada","Cantidad","Precio Compra","Precio Venta","Caducidad"};
            //ESTILO DEL ENCABEZADOS
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);            
            
            Font fontEncabezado = book.createFont();
            fontEncabezado.setFontName("Arial");
            fontEncabezado.setBold(true);
            fontEncabezado.setColor(IndexedColors.WHITE.getIndex());
            fontEncabezado.setFontHeightInPoints((short)12);
            headerStyle.setFont(fontEncabezado);
            
            //CREACIÓN DE LA LISTA DE ENCABEZADOS DE ENTRADAS
            Row filaEncabezadosEntradas = sheet.createRow(12);
            
            for (int i = 0; i < cabeceraEntradas.length; i++) {
                Cell celdaEncabezadoEntrada = filaEncabezadosEntradas.createCell(i);
                celdaEncabezadoEntrada.setCellStyle(headerStyle);
                celdaEncabezadoEntrada.setCellValue(cabeceraEntradas[i]);
            }    
            
            //CREACIÓN DE LAS ENTRADAS
            int numFilaEntradas = 13;
            
            //ESTILO DE LOS DATOS
            CellStyle datosEstilo = book.createCellStyle();            
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);             
            
            PS = CN.getConnection().prepareStatement(SQL_ENTRADAS);
            RS = PS.executeQuery();
            int numCol = RS.getMetaData().getColumnCount();
            
            while (RS.next()) {
                Row filaDatos = sheet.createRow(numFilaEntradas);
                
                for (int a = 0; a < numCol; a++) {
                    Cell celdaDatos = filaDatos.createCell(a);
                    celdaDatos.setCellStyle(datosEstilo);
                    
                    if (a==7 || a==8) {
                        celdaDatos.setCellValue(RS.getDouble(a+1));
                    } else {
                        celdaDatos.setCellValue(RS.getString(a+1));
                    }
                }
                //Cell celdaImporte = filaDatos.createCell(4);
                //celdaImporte.setCellStyle(datosEstilo);
                //celdaImporte.setCellFormula(String.format("C%d+D%d",numfilaDatos+1, numfilaDatos+1));
                numFilaEntradas++;
                
            }
            
            //ENCABEZADOS DE ENTRADAS
            String[] cabeceraSalidas = new String[]{"Factura","Código de Producto","Descripción","Referencia","Presentación","Fecha De Salida","Cantidad","Precio Venta","Tipo Salida","Total"};
            
            //CREACIÓN DE LA LISTA DE ENCABEZADOS DE ENTRADAS
            Row filaEncabezadosSalidas = sheet.createRow(numFilaEntradas+2);
            
            for (int i = 0; i < cabeceraSalidas.length; i++) {
                Cell celdaEncabezadoSalida = filaEncabezadosSalidas.createCell(i);
                celdaEncabezadoSalida.setCellStyle(headerStyle);
                celdaEncabezadoSalida.setCellValue(cabeceraSalidas[i]);
            }
            //CREACIÓN DE LAS SALIDAS
            int numFilaSalidas = numFilaEntradas+3;                                   
            
            PS = CN.getConnection().prepareStatement(SQL_SALIDAS);
            RS = PS.executeQuery();
            int numCol2 = RS.getMetaData().getColumnCount();
            
            while (RS.next()) {
                Row filaDatos = sheet.createRow(numFilaSalidas);
                
                for (int a = 0; a < numCol2; a++) {
                    Cell celdaDatos = filaDatos.createCell(a);
                    celdaDatos.setCellStyle(datosEstilo);
                    
                    if (a==7) {
                        celdaDatos.setCellValue(RS.getDouble(a+1));
                    } else {
                        celdaDatos.setCellValue(RS.getString(a+1));
                    }
                }
                Cell celdaImporte = filaDatos.createCell(9);
                celdaImporte.setCellStyle(datosEstilo);
                celdaImporte.setCellFormula(String.format("G%d*H%d",numFilaSalidas+1, numFilaSalidas+1));
                numFilaSalidas++;
                
            }
            
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);
            sheet.setZoom(100);
            
            FileOutputStream fileout = new FileOutputStream("ReporteProductos.xlsx");
            book.write(fileout);
            fileout.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cls_Reporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cls_Reporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Cls_Reporte.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            PS = null;
            RS = null;
            CN.desconectar();
        }
        
    }
    
}
