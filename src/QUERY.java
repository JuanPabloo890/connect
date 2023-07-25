import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class QUERY {
    private JTextField id;
    private JTextField nombre;
    private JTextField edad;
    private JTextField ciudad;
    private JTextField cedula;
    private JButton QUERYButton;
    private JPanel marco;
    private JTextField contra;
    static final String DB_URL = "jdbc:mysql://localhost/POO";
    static final String USER = "root";
    static final String PASS = "root_bas3";
    static final String QUERY = "select * from estudiantes";

    public QUERY() {
        QUERYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

                        id.setText(String.valueOf(rs.getInt( "id")));
                        nombre.setText(String.valueOf(rs.getString( "nombre")));
                        edad.setText(String.valueOf(rs.getInt("edad")));
                        ciudad.setText(rs.getString("ciudad"));
                        cedula.setText(String.valueOf(rs.getInt("cedula")));
                        contra.setText(rs.getString("contraseña"));
                    }

                } catch (SQLException x) {
                    throw new RuntimeException(x);
                }

            }
        });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("QUERY");
        frame.setContentPane(new QUERY().marco);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);



    }
}
