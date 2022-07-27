/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Conexion.Conectar;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
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
public class Cls_Report {
    public boolean crearReporte(String nombreArchivo, Date fechaInicio, Date fechaFin, int tipoMed, String ruta){
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Entradas y Salidas");
        sheet.setColumnWidth(3, 25 * 256);
        PreparedStatement PS = null;
        ResultSet RS;
        Conectar CN = new Conectar();
        String tipMed = "";
        
        
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
            
            if (tipoMed == 1) {
                tipMed = " AND ent_pro_codigo LIKE '%MC%'";
            } else if (tipoMed == 2) {
                tipMed = " AND ent_pro_codigo LIKE '%MD%'";
            } else if (tipoMed == 3) {
                tipMed = " AND ent_pro_codigo LIKE '%MM%'";
            } else {
                tipMed = "";
            }
            
            String SQL_ENTRADAS = "SELECT * FROM vw_entradas WHERE (ent_fecha BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"') "+tipMed+"";
            
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
            
            String SQL_SALIDAS = "SELECT * FROM vw_salidas WHERE (sal_fecha BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"')"+tipMed+"";
            
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
            
            //String rutaEscritorio = System.getProperty("user.home") + File.separator + "Escritorio";
            //FileOutputStream fileoutt = new FileOutputStream(rutaEscritorio + File.separator + "Reporte Alumnos.pdf");
            
            FileOutputStream fileout = new FileOutputStream(ruta + File.separator + nombreArchivo + ".xlsx");               
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
        return true;
    }
    
}
