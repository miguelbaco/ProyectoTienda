//interface para las consultas y grabar

public interface DAOCompra {
	void grabar(Compra c);
	String consultart() throws Exception;
	String consultari(int id);
}
