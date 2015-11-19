package BaseRelacional;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author oracle
 */
public class BaseRelacionalG {

   Connection conexion;
   ResultSet rs;
   CallableStatement cst;

    public static void main(String[] args) {
        BaseRelacionalG brg = new BaseRelacionalG().conectar();
        brg.funcion("p2");
        
    }

    public BaseRelacionalG conectar() {
        try {
            String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:orcl";
            conexion = DriverManager.getConnection(BaseDeDatos, "hr", "hr");
            if (conexion != null) {
                System.out.println("Conexion exitosa!");
            } else {
                System.out.println("Conexion fallida!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
    public Connection getConexion() {
        return conexion;
    }
    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    public void funcion(String a) {
        try {
            cst = conexion.prepareCall("{? = call prezo_produto (?)}");
            cst.setString(2, a);
            cst.registerOutParameter(1, Types.VARCHAR);
            cst.execute();
            System.out.println("Codigo elegido: " + a);
            System.out.println("Precio del producto: " + cst.getInt(1));
            cst.close();
        } catch (SQLException ex) {
            System.out.println("SQLException "+ ex);
        }
    }
}

