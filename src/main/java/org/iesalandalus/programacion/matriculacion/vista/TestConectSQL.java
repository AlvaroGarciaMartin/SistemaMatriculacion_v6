package org.iesalandalus.programacion.matriculacion.vista;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConectSQL {
   public static void main(String[] args) throws SQLException {
       Connection conn = MySQL.establecerConexion();
       /*String dni = "12345678Z";
       String query = """
               SELECT a.nombre
               ,a.telefono
               ,a.correo,
               ,a.dni
               ,a.fechaNacimiento
               FROM alumno a
               WHERE a.dni = ?""";

       PreparedStatement pstmt = conn.prepareStatement(query);
       pstmt.setString(1, dni);
       ResultSet rs = pstmt.executeQuery();
       while (rs.next()) {
           System.out.println(rs.getString("nombre"));
           System.out.println(rs.getString("telefono"));
           System.out.println(rs.getString("correo"));
           System.out.println(rs.getString("dni"));
           System.out.println(rs.getDate("fechaNacimiento"));
       }*/
       MySQL.cerrarConexion();
   }
}
