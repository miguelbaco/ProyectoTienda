import java.util.ArrayList;
import java.util.Date;
import java.lang.Double;

/**
 * Esta clase Compra almacena la persona que realiza la compra
 * el articulo que compra y establece su id y fecha
 */

public class Compra {

  // Variables
  private Person per;
  private Articulo art;
  private Double cant;
  private int id;
  private Date fecha;

  // Constructor por defecto
  public Compra() {
    per = new Person();
    art = new Articulo();
    cant = 0.0;
    id=0;
    fecha = new Date();
  }

  /* Método set de persona
   * @param unPerson Establece la persona que compra
   */
  public void setPer(Person unPerson) {
    per = unPerson;
  }

  /* Método set de articulo
   * @param art El artículo de la compra
   */
  public void setArt(Articulo art) {
    this.art = art;
  }

  /* Método set de cantidad
   * @param cant Cantidad de la compra (kg)
   */
  public void setCant(Double cant) {
    this.cant = cant;
  }

  /* Método set de id
   * @param id ID de la compra
   */
  public void setId(int id){
    this.id=id;
  }

  /* Método get de persona
   * @return per persona de la compra
   */
  public Person getPer() {
    return per;
  }

  /* Método get de artículo
   * @return art artículo de la compra
   */
  public Articulo getArt() {
    return art;
  }

  /* Método get de cantidad
   * @return cant cantidad de la compra
   */
  public Double getCant() {
    return cant;
  }

  /* Método get de ID
   * @return id ID de la compra
   */
  public int getId(){
    return id;
  }

  /* Método set de fecha
   * @param fecha Fecha de la compra
   */
  public void setFecha(Date fecha){
  	this.fecha = fecha;
  }

  /* Método get de fecha
   * @return fecha Fecha de la compra
   */
  public Date getFecha() {
  	return fecha;
  }
}
