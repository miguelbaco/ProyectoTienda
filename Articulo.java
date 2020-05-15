import java.lang.Double;

/**
 * Esta clase Articulo guarda el nombre y el precio del mismo
 */

class Articulo {

	// Variables
    private String nombre;
    private Double precio;

    // Constructor por defecto
    public Articulo(){
    	nombre = "";
        precio = 0.0;
    }

    /* Método set de nombre
     * @param nombre El nombre del artículo
     */
    public void setNombre(String nombre){
      this.nombre = nombre;
    }

    /* Método get de nombre
 	 * @return nombre del artículo
 	 */
    public String getNombre(){
      return nombre;
    }

    /* Método set de precio
	 * @param precio El precio del artículo
     */
    public void setPrecio(Double precio){
        this.precio=precio;
    }

    /* Método get de precio
 	 * @return precio del artículo
 	 */
    public Double getPrecio(){
        return precio;
    }
}
