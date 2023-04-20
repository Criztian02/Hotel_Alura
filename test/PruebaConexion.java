
import java.sql.Connection;
import java.sql.SQLException;

import com.alura.hotel.factory.ConexionFactory;


public class PruebaConexion {

    public static void main(String[] args) throws SQLException {
        ConexionFactory factory = new ConexionFactory();
        Connection conn = factory.getConexion();
        System.out.println("Exito!!");
        conn.close();
    }
    
}
