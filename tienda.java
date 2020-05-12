import java.util.ArrayList;
import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Double;

public class tienda{
	public static void main(String args[]) throws Exception {
		DAOCompra daocompra = new JDBCCompra();//creacion de DAO para lo relacionado con la base de datos
		//a continuacion, el codigo para leer el fichero json, que corresponde al catalogo de productos.	

		Console console = System.console();
		String lin = null;
		ArrayList<String> listaProds = new ArrayList<String>();	
		BufferedReader br = new BufferedReader(new FileReader("productos.txt"));		
		while((lin = br.readLine()) != null) {
          	listaProds.add(lin);
        }br.close();

        for(int i = 0;i<listaProds.size();i++){
			String[] a = listaProds.get(i).split(":");
			System.out.println(a[0] + " -> " + a[1] + " €");
		}
		System.out.println("\n --Como desea entrar? Proveedor: P | Cliente: C | Dueño: D");
		String tipoEntrante = console.readLine();

		if (tipoEntrante.equalsIgnoreCase("P")) {
			System.out.println("¿Que desea hacer con el stock? Añadir A | Eliminar N");
			String respuesta = console.readLine();

			if(respuesta.equalsIgnoreCase("a")) {
				System.out.println("Producto a añadir: ");
				String nuevoproducto = console.readLine();
				boolean stock = true;

				for(int i = 0;i<listaProds.size();i++){
					String[] a = listaProds.get(i).split(":");
					if (a[0].equalsIgnoreCase(nuevoproducto)){
						System.out.println("Este producto ya se encuentra en stock");
						stock =false;
					}
				}
				if (stock) {
					System.out.println("Introduce precio de venta al publico (PVP): ");
					String nuevoprecio = console.readLine();
					Double precionuevo = Double.parseDouble(nuevoprecio);

					BufferedWriter buf = new BufferedWriter(new FileWriter("productos.txt", true));		
					buf.write(nuevoproducto + ":" + precionuevo);
					buf.close();
				}


			} else if(respuesta.equalsIgnoreCase("n")) {
				System.out.println("Producto a eliminar: ");
				String nuevoproducto = console.readLine();
				boolean stock = false;

				for(int i = 0;i<listaProds.size();i++){
					String[] a = listaProds.get(i).split(":");
					if (a[0].equalsIgnoreCase(nuevoproducto)){
						stock =true;
					}
				}
				if (stock) {
					
					
					BufferedWriter buf2 = new BufferedWriter(new FileWriter("productos.txt"));		
					for(int i=0; i<listaProds.size(); i++) {
						String[] a1=listaProds.get(i).split(":");
						System.out.println(a1[0]);
						if(!a1[0].equals(nuevoproducto)){
							buf2.write(listaProds.get(i)+"\n");
						}

       				}buf2.close();
       				
				}
			}


		} else if (tipoEntrante.equalsIgnoreCase("D")) {
			while(true){//aqui es donde esta la parte de consultas.
			System.out.println("Quieres consultar datos? S|N");
			String respuesta = console.readLine();
			if (respuesta.equalsIgnoreCase("s")){
				daocompra.consultart();//te muestra todos los datos guardados
				System.out.println("Para consultar por persona: N | Para mostrar por ID: I");
				String respuesta2 = console.readLine();
				if(respuesta2.equalsIgnoreCase("n")){
					System.out.println("Introduce nombre");
					String r = console.readLine();
					daocompra.consultarn(r);//te muestra los datos que contienen el nombre introducido
				}
				
				else if(respuesta2.equalsIgnoreCase("i")){
					System.out.println("Introduce id");
					String r = console.readLine();
					int r2=Integer.parseInt(r);
					daocompra.consultari(r2);//te muestra la compra de una persona
				}
				else{//si la opcion que se introdujo no es n, p, i que salte este error y que pregunte si desea consultar o no.
					System.out.println("Lo sentimos! Esa opcion no esta disponible");
				}
			}
			else{//Mensaje despedida cuando el cliente se vaya
				System.out.println("Vuelva pronto.");
				break;
			}
		}

		} else if (tipoEntrante.equalsIgnoreCase("C")) {
        
        //una vez leidos y guardados, se empieza con la compra
		Compra c;
		ArrayList<Compra> lista1 = new ArrayList<Compra>();
		console = System.console();
		while(true){//bucle para crear compras, con sus respectivos datos (persona, articulos, cantidad y precio)
			c = new Compra();
			Person p = new Person();
			System.out.println("Nombre persona: ");//nombre de la persona
			String persona = console.readLine();
			if(persona.equals("")){
					break;
				}
			p.setName(persona);//setteo nombre de la persona
			System.out.println("ID de compra: ");
			String idcomp = console.readLine();
			int idcom=Integer.parseInt(idcomp);
			
			while(true){//este while es para que una persona pueda comprar mas de un articulo en una compra
				c.setId(idcom);//setteo del id
				System.out.println("Articulos: ");//articulo
				String articulo = console.readLine();
				if(articulo.equals("")){
					break;
				}
				for(int i = 0;i<listaProds.size();i++){//aqui esta la informacion del json
					String[] a = listaProds.get(i).split(":");
					
					if(articulo.equals(a[0])){
						c.getArt().setNombre(articulo);//si el articulo está en la lista de productos disponibles, setteo del articulo

						Double preciop=Double.parseDouble(a[1]);
						System.out.println("Cantidad: ");//cantidad de articulos
						String canti = console.readLine();
						if(canti.equals("")){
							break;
						}
						Double cant = Double.parseDouble(canti);
						c.getArt().setPrecio(preciop * cant);//setteo de precio por cantidad (precio final por producto)
						c.setCant(cant);//setteo de cantidad
						c.getPer().setName(p.getName());//setteo de la persona dentro de la clase Compra
						Date fechaFactura = new Date();//Creación de fecha
						c.setFecha(fechaFactura);//setteo a la compra de fecha/hora
						lista1.add(c);//añadido de la compra a la lista
						daocompra.grabar(c);//se guarda en la base de datos
					}
				}
				System.out.println("Mas articulos? S|N ");//Para agregar más articulos a la misma persona
				String masart = console.readLine();
				if(masart.equalsIgnoreCase("n")){
					c.setArticulos(lista1);
					break;
				}
				else{
					c = new Compra();//si queremos agregar más articulos crea una nueva compra y se le añaden articulos, al no haber creado una persona nueva
					//se le agrega la ultima persona creada, asi no crea conflictos de persona de compra = null
					//ni agregar articulos de otras personas.
				}
			}
			System.out.println("Mas entradas? S|N ");//Para agregar más entradas (personas y articulos)
			String masper = console.readLine();
			if(masper.equalsIgnoreCase("n")){
				break;
			}
		}
		//Confirmación de registro en la base de datos, con la fecha de la factura.
		System.out.println("Los datos han quedado guardados en la base de datos. A fecha de "+ new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(c.getFecha()));
		//Se le cambia el formato de la fecha de Compra para que la muestre como nosotros la visualizamos dia-mes-año y hora:min:seg
		System.out.println("Vuelva pronto.");
	}
}}