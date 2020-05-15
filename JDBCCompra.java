import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

//Implementamos la clase DataSource
import org.sqlite.SQLiteDataSource;
import javax.sql.DataSource;

/**
 * Esta clase JDBCCompra contiene los métodos de consulta, el método conectar y grabar
 */
public class JDBCCompra implements DAOCompra{

        // Variable dataSource
        private SQLiteDataSource dataSource = new SQLiteDataSource();

        /* Método conectar con sqlite
         * @return conn Objeto Connection que contecta con sqlite
         */
        private Connection conectar() throws SQLException {
               
                dataSource.setUrl("jdbc:sqlite:compra.db");
               
                Connection conn = null;
                try {
                        conn = dataSource.getConnection();
                } catch (SQLException e) {
                        System.out.println(e.getMessage());
                }
                return conn;
        }

        /* Método grabar
         * @param c Compra a grabar
         */
        public void grabar(Compra c) {
                java.sql.Timestamp sqlDate = new java.sql.Timestamp(c.getFecha().getTime());
                String sql = "INSERT INTO compra (Cliente,Producto,Cantidad,Precio,ID,Fecha) VALUES(?,?,?,?,?,?)";
                try (Connection conn = this.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, c.getPer().getName());
                        pstmt.setString(2, c.getArt().getNombre());
                        pstmt.setDouble(3, c.getCant());
                        pstmt.setDouble(4, c.getArt().getPrecio());
                        pstmt.setInt(5, c.getId());
                        pstmt.setTimestamp(6, sqlDate);
                        pstmt.executeUpdate();
                } catch (SQLException o) {
                        System.out.println(o.getMessage());
                }
        }

        //Métodos de consulta

        /* Método de consulta de todas las compras guardadas en base de datos
         * @return aa String con todas las compras
         */
        public String consultart() {
                String aa="";
                try  {
                        
                        String sql1 = "SELECT DISTINCT(ID),Cliente FROM compra";
                        Connection conn = this.conectar();
                        PreparedStatement pstmt = conn.prepareStatement(sql1);
                        ResultSet rs = pstmt.executeQuery();
                        int idd = -1;
                        while (rs.next()) {
                                    if (rs.getInt("ID") != idd) {
                                        aa=aa+(rs.getString("Cliente")+" --> "+rs.getInt("ID")+"\n");
                                        idd = rs.getInt("ID");
                                     }
                                }
                } catch (SQLException o) {
                        System.out.println(o.getMessage());
                }return aa;
        }

        /* Método de consulta por busqueda de ID 
         * @param id ID de compras a buscar
         * @return aa String con todas las compras hechas asignadas a ese ID
         */
        public String consultari(int id){//aqui te muestra solo aquellos con la ID introducida.
                String aa="";
                try{
                        String sql2 = "SELECT * FROM compra WHERE ID ="+id;
                        Connection conn = this.conectar();
                        PreparedStatement pstmt = conn.prepareStatement(sql2);
                        ResultSet rs = pstmt.executeQuery();
                        while (rs.next()) {     
                                 aa=aa+(rs.getDouble("Cantidad")+" Kg. de "+rs.getString("Producto")+"\n"+rs.getDouble("Precio") + " €" +"\n"+ "Fecha: " + rs.getTimestamp("Fecha")+"\n\n");
                         }     
                }
                catch (SQLException o) {
                        System.out.println(o.getMessage());
                }
                return aa;
        }
}