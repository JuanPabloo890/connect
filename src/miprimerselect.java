import javax.print.attribute.standard.OrientationRequested;
import java.sql.*;
public class miprimerselect {
    static final String DB_URL = "jdbc:mysql://localhost/POO";
    static final String USER = "root";
    static final String PASS = "root_bas3";
    static final String QUERY = "select * from estudiantes";

    public static void main(String[] args) {

        try(
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(QUERY);
        ){
            while (rs.next()){
                System.out.println("Id: "+rs.getInt("id"));
                System.out.println("Nombre: "+rs.getString("nombre"));
                System.out.println("Edad: "+rs.getInt("edad"));
                System.out.println("Ciudad: "+rs.getString("ciudad"));
                System.out.println("Cedula: "+rs.getInt("cedula"));
                System.out.println("Constraseña: "+rs.getString("contraseña"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
