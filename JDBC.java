import java.sql.*;
import javax.annotation.Resource;
import javax.sql.DataSource;
//esta clase es para crear la tabla. para poder hacerlo, primero hay que poseer un archivo con extension .db

public class JDBC {
	public static void main(String[] args) {
		Connection conn = null;

		@Resource(name = "jd")
		private DataSource dataSource;

		try{
			String sql = "CREATE TABLE IF NOT EXISTS compra (Cliente TEXT,Producto TEXT,Cantidad Double,Precio DOUBLE,ID INTEGER, Fecha TIMESTAMP);";
			conn = dataSource.getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		}
		catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
}
