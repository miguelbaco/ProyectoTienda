import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.sqlite.SQLiteDataSource;
//Implementamos la clase DataSource
import javax.sql.DataSource;
import javax.annotation.Resource;
//aqui esta el codigo para grabar y consultar
public class JDBCCompra implements DAOCompra{
        private SQLiteDataSource dataSource = new SQLiteDataSource();
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
/*
        ArrayList<Integer> listaIDs= new ArrayList<Integer>();
        public void idexistente(int ids){
                try  {
                        String sql1 = "SELECT ID FROM compra";
                        Connection conn = this.conectar();
                        PreparedStatement pstmt = conn.prepareStatement(sql1);
                        ResultSet rs = pstmt.executeQuery();
                        while (rs.next()) {
                                listaIDs.add(rs.getInt("ID"));
                        }
                        if(listaIDs.contains(ids)){
                                System.out.println("Esta ID ya existe.");
                        }
                } catch (SQLException o) {
                        System.out.println(o.getMessage());
                }
        }
*/
        public void grabar(Compra c) {//codigo para grabar
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
        //a partir de aqui, el codigo es para consultar.
        public String consultart() {//aqui te muestra todo lo que hay almacenado en la base de datos
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
        public void consultarn(String nombre){//aqui te muestra solo aquellos con el nombre introducido
                try{
                        String sql2 = "SELECT * FROM compra WHERE CLIENTE ='"+nombre+"'";
                        Connection conn = this.conectar();
                        PreparedStatement pstmt = conn.prepareStatement(sql2);
                        ResultSet rs = pstmt.executeQuery();
                        System.out.println("Cliente: "+rs.getString("Cliente"));
                        while (rs.next()) {     
                                 System.out.println(rs.getDouble("Cantidad")+" Kg. de "+rs.getString("Producto")+" por "+rs.getDouble("Precio") + " €, con ID " + rs.getInt("ID") + " a fecha de " + rs.getTimestamp("Fecha"));
                         }     
                }
                catch (SQLException o) {
                        System.out.println(o.getMessage());
                }
        }
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