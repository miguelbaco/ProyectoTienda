public interface DAOCompra {
	void grabar(Compra c);
	String consultart() throws Exception;
	void consultarn(String nombre);
	String consultari(int id);
	// void idexistente(int ids); 
}
//interface para las consultas
