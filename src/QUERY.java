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
    private JButton borrarButton;
    private JTextField info;
    private JLabel mostrar;
    private JButton insertarButton;
    private JButton actualizarButton;
    static final String DB_URL = "jdbc:mysql://localhost/POO"; // esta es la conexion con la base de datos
    static final String USER = "root"; //usuario
    static final String PASS = "root_bas3"; // contraseña
    static final String QUERY = "select * from estudiantes"; // sentencia de sql (query)

    public QUERY() {
        QUERYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try(
                        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); // necesitamos colocar la conexion, usuario, contraseña
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
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);){
                    String query_delete = "delete from estudiantes where id =?";

                    try(PreparedStatement pstmt = conn.prepareStatement(query_delete)){
                        pstmt.setInt(1, Integer.parseInt(id.getText()));
                        int filasE=pstmt.executeUpdate();
                        if (filasE>0){
                            mostrar.setText("Estudiante eliminado");
                        }else{
                            mostrar.setText("No se encontro ninguno estudiante con ese ID!!! ");
                        }
                    }

                }catch (SQLException k){
                    k.printStackTrace();
                }
            }
        });
        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);){
                    String query_insert = "insert into estudiantes (id,nombre,edad,ciudad,cedula,contraseña) values (?,?,?,?,?,?) ";

                    try(PreparedStatement pstmt = conn.prepareStatement(query_insert)){
                        pstmt.setInt(1, Integer.parseInt(id.getText()));
                        pstmt.setString(2,nombre.getText());
                        pstmt.setInt(3,Integer.parseInt(edad.getText()));
                        pstmt.setString(4,ciudad.getText());
                        pstmt.setInt(5,Integer.parseInt(cedula.getText()));
                        pstmt.setString(6,contra.getText());
                        int filasI=pstmt.executeUpdate();
                        if (filasI>0){
                            mostrar.setText("Estudiante insertado");
                        }else{
                            mostrar.setText("No se pudo insertart el usuario!!! ");
                        }
                    }

                }catch (SQLException k){
                    k.printStackTrace();
                }
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);){
                    String query_delete = "update estudiantes set ciudad=? where id=?";

                    try(PreparedStatement pstmt = conn.prepareStatement(query_delete)){
                        pstmt.setInt(1, Integer.parseInt(id.getText()));
                        int filasE=pstmt.executeUpdate();
                        if (filasE>0){
                            mostrar.setText("Informacion actualizada");
                        }else{
                            mostrar.setText("No se encontro ninguno estudiante con ese ID!!! ");
                        }
                    }

                }catch (SQLException k){
                    k.printStackTrace();
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
