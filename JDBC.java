import java.sql.*;

//Implementamos la clase DataSource
import javax.sql.DataSource;
import org.sqlite.SQLiteDataSource;

/**
 * Esta clase JDBC crea la tabla, para ello primero debemos tener un archivo de extensión .db
 */
public class JDBC {
        //Variable dataSource
        private SQLiteDataSource dataSource;

        // Constructor por defecto
        public JDBC() {
                dataSource = new SQLiteDataSource();
        }

        /* Método set de DataSource
         * @param d Objeto datasource
         */
        public void setDataSource(SQLiteDataSource d) {
                dataSource = d;
        }

        /* Método get de DataSource
         * @return dataSource Objeto datasource
         */
        public SQLiteDataSource getDataSource() {
                return dataSource;
        }

        /* 
         *  Método main de JDBC que crea la tabla
         */
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