/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public class Conexion {
    
    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    
    private static void iniciarConexion(){
        try{ 
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                .getConnection("jdbc:mysql://localhost/proyectolsm?"
                    + "user=root&password=1234");
        } catch(ClassNotFoundException e){
            System.out.println("No se ha podido encontrar el JDBC");
            System.out.println(e.getMessage());
        } catch (SQLException e){
            System.out.println("No se ha podido abrir la conexión");
            System.out.println(e.getMessage());
        }
    }
    
    public static List<Senia> leer() throws SQLException{
        iniciarConexion();
        List<Senia> senias;
        senias = new ArrayList<>();
        Senia nuevaSenia;
        statement = connect.createStatement();
        resultSet = statement.executeQuery("select * from palabras");
        while(resultSet.next()){
            nuevaSenia = new Senia();
            nuevaSenia.setId_palabra(Integer.parseInt(resultSet.getString(1)));
            nuevaSenia.setPalabra(resultSet.getString(2));
            nuevaSenia.setMorfema(resultSet.getString(3));
            nuevaSenia.setImagen(resultSet.getString(4));
            senias.add(nuevaSenia);
        }
        cerrarConexion();
        return senias;
    }
    
    private static void cerrarConexion() {
        try{
            if (resultSet != null) {
            resultSet.close();
            }

            if (statement != null) {
            statement.close();
            }

            if (connect != null) {
            connect.close();
            }
        } catch(SQLException e){
            System.out.println("No se ha podido cerrar la conexión");
            System.out.println(e.getMessage());
        }
    }
    
}
