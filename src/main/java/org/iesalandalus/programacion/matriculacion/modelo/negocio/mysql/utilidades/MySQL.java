package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    // Constantes para la configuración de la base de datos
    private static final String HOST = "infothot.ddns.net" /*"URL DEL SERVIDOR"*/; //poner url de AWS
    private static final String ESQUEMA = "sistemamatriculacion";
    private static final String USUARIO = "sistemamatriculacion"; //cambiar a admin
    private static final String CONTRASENA = "sistemamatriculacion-2025";
    // Variable de conexión estática
    private static Connection conexion;

    // Constructor privado para evitar instanciación directa
    private MySQL() {}

    // Método para establecer la conexión (Singleton)
    public static Connection establecerConexion() {
        if (conexion == null) {
            try {
                // Cargar el driver de MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");

                // URL de conexión
                String url = "jdbc:mysql://" + HOST + ":3306/" + ESQUEMA;

                // Establecer conexión
                conexion = DriverManager.getConnection(url, USUARIO, CONTRASENA);
                System.out.println("Conexión establecida con éxito.");
            } catch (ClassNotFoundException e) {
                System.out.println("Error: No se encontró el driver de MySQL.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos.");
                e.printStackTrace();
            }
        }
        return conexion;
    }

    // Método para cerrar la conexión
    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }
}
