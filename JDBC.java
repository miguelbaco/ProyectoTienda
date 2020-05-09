import java.sql.*;
//esta clase es para crear la tabla. para poder hacerlo, primero hay que poseer un archivo con extension .db

public class JDBC {
	public static void main(String[] args) {
		Connection conn = null;
		try{
			String url = "jdbc:sqlite:compra.db";
			String sql = "CREATE TABLE IF NOT EXISTS compra (Cliente TEXT,Producto TEXT,Cantidad Double,Precio DOUBLE,ID INTEGER, Fecha TIMESTAMP);";
			conn = DriverManager.getConnection(url);
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		}
		catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
}
