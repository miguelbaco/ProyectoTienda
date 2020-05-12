import java.sql.*;
import javax.sql.DataSource;
import org.sqlite.SQLiteDataSource;
//esta clase es para crear la tabla. para poder hacerlo, primero hay que poseer un archivo con extension .db
public class JDBC {
        private SQLiteDataSource dataSource;  // sería interesante inyectar mediante setter
        public void setDataSource(SQLiteDataSource d) {
                dataSource = d;
        }
        public SQLiteDataSource getDataSource() {
                return dataSource;
        }
        public JDBC() {
                dataSource = new SQLiteDataSource();
        }
        public static void main(String[] args) {
                JDBC obj = new JDBC();
                (obj.getDataSource()).setUrl("jdbc:sqlite:compra.db");
                Connection conn = null;
                try{
                        String sql = "CREATE TABLE IF NOT EXISTS compra (Cliente TEXT,Producto TEXT,Cantidad Double,Precio DOUBLE,ID INTEGER, Fecha TIMESTAMP);";
                        conn = obj.getDataSource().getConnection();
                        Statement statement = conn.createStatement();
                        statement.executeUpdate(sql);
                }
                catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        }
}