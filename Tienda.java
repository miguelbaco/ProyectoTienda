// Imports de JavaFX
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

// Imports que se utilizan
import java.util.ArrayList;
import java.io.Console;
import java.io.FileReader;
import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Double;
import java.io.*;
import java.util.*;

/**
 * Esta clase Tienda contiene el método main principal a compilar y ejecutar
 */
public class Tienda extends Application{

	// Variables principales

	// Lista con todos los productos que estan en la tienda
	static ArrayList<String> listaProds = new ArrayList<String>();

    DAOCompra daocompra = new JDBCCompra();
	Compra c;
	String resulid="";
	int idcompra=0;
	int contadorarticulos = 0;
	String cantart= "";
    String nomper = "";
    String artcom = "";

	// Todas las escenas que se utilizan y labels
	Scene scene1, scene2, scene3, scene4, scene5, scene6, scenefactura;
	Label tiendalabel = new Label("BACO & GARRIDO ALIMENTOS");
	Label errormssg = new Label("");
	Label ll=new Label("");
	Label ll2 = new Label("");
	Label lprod6=new Label("");
	Label lprod3=new Label("");
	Label ids=new Label("");

	// Stage de factura cuando compras
	Stage stage2 = new Stage();

	/**
      * Esta clase main es el método main principal
      */
	public static void main(String[] args) throws Exception{
		try {

			String lin = null;
			
			BufferedReader br = new BufferedReader(new FileReader("productos.txt"));		
			while((lin = br.readLine()) != null) {
        	  	listaProds.add(lin);
        	}

        	Application.launch(args);
      	} catch (Exception e) {
        	e.printStackTrace();
      	}
	}

	/**
      * Esta clase start establece como funciona todo el programa y muestra el stage y escenas
      * @param stage Ventana/Escenario principal 
      */
	@Override
	public void start(Stage stage) throws Exception{
		
		// Titulo de la ventana/stage/escenario
		stage.setTitle("BACO & GARRIDO ALIMENTOS");


		// Escena 1(Principal)

		// Labels y botones  
		Label tiendalabel1 = new Label("BACO & GARRIDO ALIMENTOS");
      	Button proveedor = new Button("PROVEEDOR");
		Button cliente = new Button("CLIENTE");
		Button dueno = new Button("Dueño");
		Button exit1 = new Button("Salir");

		// Acciones de botones principal
		exit1.setOnAction(e -> {stage.close();});
		proveedor.setOnAction(e -> stage.setScene(scene2));
		cliente.setOnAction(e -> stage.setScene(scene3));
		dueno.setOnAction(e -> stage.setScene(scene4));
		

		// Presentación visual inicial
		HBox hbox=new HBox();
		hbox.getChildren().addAll(dueno,proveedor, cliente);
		hbox.setSpacing(15);
		VBox vbox = new VBox();
      	vbox.getChildren().addAll(tiendalabel1,hbox,exit1);
      	vbox.setSpacing(15);
      	vbox.setMinSize(150,100);
	  	vbox.setStyle("-fx-padding: 10;" +
	                "-fx-border-style: solid inside;" +
	                "-fx-border-width: 2;" +
	                "-fx-border-insets: 5;" +
	                "-fx-border-radius: 5;" +
	                "-fx-border-color: blue;");
      	scene1 = new Scene(vbox);


		//Escena 2(Proveedor)

		Label tiendalabel2 = new Label("BACO & GARRIDO ALIMENTOS");
        Label bienvenido = new Label("¡BIENVENIDO!");
        Label pswl = new Label("Introduce contraseña:");
        Button entrar = new Button("Entrar");
        TextField pswt = new TextField();
        Button exit2 = new Button("Salir");
        Button volver2 = new Button("Volver");


        // Acciones de botones de proveedor
        volver2.setOnAction(e -> stage.setScene(scene1));
		exit2.setOnAction(e -> { stage.close(); });

		// Boton para verificar la contraseña de proveedor
		entrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	if(pswt.getText().equals("PaulinoApruebame")){
               		stage.setScene(scene6);
               	}
           	}
        });

        // Presentación visual de contraseña de proveedor
        HBox h2 =new HBox();
        h2.getChildren().addAll(pswl,pswt,entrar);
        HBox h22 = new HBox();
        h22.getChildren().addAll(volver2,exit2);
        VBox vbox2 = new VBox();
      	vbox2.getChildren().addAll(tiendalabel2,bienvenido,h2,h22);
      	vbox2.setSpacing(15);
      	vbox2.setMinSize(150,100);
	  	vbox2.setStyle("-fx-padding: 10;" +
	                "-fx-border-style: solid inside;" +
	                "-fx-border-width: 2;" +
	                "-fx-border-insets: 5;" +
	                "-fx-border-radius: 5;" +
	                "-fx-border-color: blue;");
		scene2 = new Scene(vbox2);
      	

		//Escena 3(Cliente)

	  	Label tiendalabel3 = new Label("BACO & GARRIDO ALIMENTOS");
	  	Label prod = new Label("Productos disponibles:");
	  	Button exit3 = new Button("Salir");
	  	Button volver3 = new Button("Volver");
	  	Label lnombre= new Label("Ingresa tu nombre aqui:");
	  	TextField tnombre=new TextField();
	  	Label lprodcompra = new Label("Producto a comprar:");
	  	TextField tprodcompra= new TextField();
	  	Label lcantidadcompra=new Label("Cantidad:");
	  	TextField tcantidadcompra= new TextField();
	  	Button bcomprar = new Button("Comprar");
	  	Button banadirprod = new Button("Añadir al carrito");

	  	Compra c = new Compra();
	  	Person p = new Person();

	  	// Boton para comprar el producto introducido
	  	banadirprod.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	Boolean b1 = false;

            	try {

            		// Si es la primera compra, se lee el id siguiente y se asigna a la compra del cliente
            		if(contadorarticulos == 0) {
            			contadorarticulos++;
            			BufferedReader brid = new BufferedReader(new FileReader("idcompra.txt"));		
						idcompra=Integer.parseInt(brid.readLine())+1; // Guarda el id necesario para la compra
						String stringidcompra=String.valueOf(idcompra);
          				brid.close();
          				BufferedWriter bwid= new BufferedWriter(new FileWriter("idcompra.txt"));
          				bwid.write(stringidcompra);  // Guarda el id por el que va
          				bwid.close();
            			Compra c = new Compra();
            			Person p = new Person();
            	

            			artcom = tprodcompra.getText();
               			cantart= tcantidadcompra.getText();
               			nomper = tnombre.getText();

               			// Recorre la lista
               			for(int i = 0;i<listaProds.size();i++){//aqui esta la informacion
							String[] a = listaProds.get(i).split(":");
					
							// Si el nombre introducido se encuentra en la lista guarda datos
							if(artcom.equals(a[0])){
								b1=true;
								c.getArt().setNombre(artcom); // Almacena el nombre del artículo en la compra
								Double preciop=Double.parseDouble(a[1]);
								if(cantart.equals("")){
									cantart="0";
								}
								if (nomper.equals("")){
          							p.setName("Anonimo");
          						}

          						p.setName(nomper); // Guarda el nombre de persona en la clase persona
          						c.setId(idcompra); // Guarda el id de compra en la clase compra
								Double cant = Double.parseDouble(cantart);
								c.getArt().setPrecio(preciop * cant); // Guarda el precio por cantidad (precio final por producto)
								c.getPer().setName(p.getName()); // Guarda el nombre de persona en la clase compra
								c.setCant(cant); // Guarda la cantidad que se compra de un artículo
								daocompra.grabar(c); // Guarda la compra en la base de datos
							}
						}
					// En el caso de que ya se haya añadido un artículo en la cesta, se sigue guardando bajo el mismo id
            		} else {
               			contadorarticulos++;
               			Compra c= new Compra();
               			artcom = tprodcompra.getText();
               			cantart= tcantidadcompra.getText();

               			// Recorre la lista
               			for(int i = 0;i<listaProds.size();i++){
							String[] a = listaProds.get(i).split(":");
					
							if(artcom.equals(a[0])){
								b1=true;
								c.getArt().setNombre(artcom); // Si el artículo esta en la tienda guarda su nombre
								Double preciop=Double.parseDouble(a[1]);
								if(cantart.equals("")){
									cantart="0";
								}
								nomper = tnombre.getText();
								if (nomper.equals("")){
          							p.setName("Anonimo");
          						}

          						p.setName(nomper); // Guarda el nombre de la persona en la clase persona
          						c.setId(idcompra); // Guarda el id en la clase compra

								Double cant = Double.parseDouble(cantart);
								c.getArt().setPrecio(preciop * cant); // Guarda el precio por cantidad (precio final por producto)
								c.setCant(cant); // Guarda la cantidad que se compra de un artículo
								c.getPer().setName(p.getName()); // Guarda el nombre de persona en la clase compra
          						Date fechaFactura = new Date(); // Variable que guarda la fecha y hora actual
          						c.setFecha(fechaFactura); // Guarda la fecha de compra
								daocompra.grabar(c); // Guarda la compra en la base de datos

							}
						}
						// Una vez recorrida la lista al darle al botón, si no está en la lista lo que se busca b1 sera falso
						if (b1 == false) {
							errormssg.setText("Introduzca un producto que esté en la lista");
						}
            		}
              	} catch (Exception e1) {
         			e1.printStackTrace();
      			}
            }
        });

		// Botón que muestra la compra realizada en base a las compras hechas bajo un id
	  	bcomprar.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	try{          			

          			ids.setText(daocompra.consultart());

          			ll.setText(daocompra.consultari(idcompra) + "\nTotal de articulos: " + contadorarticulos);

          				contadorarticulos = 0;						
            			stage2.setTitle("Factura");
            			stage2.setScene(scenefactura);
            			stage2.show();


     			}catch (Exception e1) {
         			e1.printStackTrace();
      			}
            }
        });

        volver3.setOnAction(e -> stage.setScene(scene1));
		exit3.setOnAction(e -> {stage.close();});


		String a1="";
	  	for(int i = 0;i<listaProds.size();i++){
			String[] a = listaProds.get(i).split(":");
			a1=a1+"\n"+(a[0] + " -> " + a[1] + " €");
		}

		// Presentación visual de cliente
		lprod3.setText(a1);
		HBox h3 = new HBox();
        h3.getChildren().addAll(volver3,exit3);
        HBox hnombre = new HBox();
        hnombre.getChildren().addAll(lnombre,tnombre);
        HBox hcompra=new HBox();
        hcompra.getChildren().addAll(lprodcompra,tprodcompra,lcantidadcompra,tcantidadcompra);
        HBox fcompra = new HBox();
        fcompra.getChildren().addAll(banadirprod, bcomprar);
		VBox vbox3=new VBox();
		vbox3.getChildren().addAll(tiendalabel3,prod,lprod3,hnombre,hcompra,fcompra,errormssg,h3);
		vbox3.setSpacing(15);
      	vbox3.setMinSize(150,150);
	  	vbox3.setStyle("-fx-padding: 10;" +
	                "-fx-border-style: solid inside;" +
	                "-fx-border-width: 2;" +
	                "-fx-border-insets: 5;" +
	                "-fx-border-radius: 5;" +
	                "-fx-border-color: blue;");
	  	scene3=new Scene(vbox3);


	  	//Escena 4(Dueño)

	  	Label tiendalabel4 = new Label("BACO & GARRIDO ALIMENTOS");
		ids.setText(daocompra.consultart());
	  	Label introduzca=new Label("introduzca id de Compra:");
		TextField tf =new TextField();
		Button buscar = new Button("Buscar");
		Button exit4 = new Button("Salir");
		Button volver4 = new Button("Volver");

		// Acción de los botones
        volver4.setOnAction(e -> stage.setScene(scene1));
		exit4.setOnAction(e -> {stage.close();});

		// Este botón busca por ID de compra
		buscar.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               stage.setScene(scene5);
               resulid=daocompra.consultari(Integer.parseInt(tf.getText()));
               ll2.setText(resulid);
            }
        });

        // Presentación visual de Dueño
		HBox h4=new HBox();
		h4.getChildren().addAll(introduzca,tf,buscar);
		HBox h44 = new HBox();
            h44.getChildren().addAll(volver4,exit4);
		VBox vbox4=new VBox();
		vbox4.getChildren().addAll(tiendalabel4,ids,h4,h44);
		vbox4.setSpacing(15);
      	vbox4.setMinSize(150,100);
	  	vbox4.setStyle("-fx-padding: 10;" +
	                "-fx-border-style: solid inside;" +
	                "-fx-border-width: 2;" +
	                "-fx-border-insets: 5;" +
	                "-fx-border-radius: 5;" +
	                "-fx-border-color: blue;");
	  	scene4=new Scene(vbox4);


	  	//Escena 5(Datos compra)

	  	Label tiendalabel5 = new Label("BACO & GARRIDO ALIMENTOS");
		VBox vbox5=new VBox();
		Button exit5 = new Button("Salir");
		Button volver5 = new Button("Volver");

		// Acciones de los botones
        volver5.setOnAction(e -> stage.setScene(scene4));
		exit5.setOnAction(e -> {stage.close();});

		// Presentación visual de la busqueda del dueño
		HBox h5 = new HBox();
        h5.getChildren().addAll(volver5,exit5);
		vbox5.getChildren().addAll(tiendalabel5,ll2,h5);
		vbox5.setSpacing(15);
      	vbox5.setMinSize(500,400);
	  	vbox5.setStyle("-fx-padding: 10;" +
	                "-fx-border-style: solid inside;" +
	                "-fx-border-width: 2;" +
	                "-fx-border-insets: 5;" +
	                "-fx-border-radius: 5;" +
	                "-fx-border-color: blue;");
	  	scene5=new Scene(vbox5);


		//Escena 6(Introducir o eliminar productos con proveedor)

		Label tiendalabel6 = new Label("BACO & GARRIDO ALIMENTOS");
		Label prod6 = new Label("Productos disponibles:");
	  	Button exit6 = new Button("Salir");
	  	Button volver6 = new Button("Volver");
	  	Label anadirnombre = new Label("Producto a añadir:");
	  	TextField tanadirnombre = new TextField();
	  	Label anadirprecio = new Label("Precio:");
	  	TextField tanadirprecio = new TextField(); 
	  	Button banadir = new Button("Añadir");
	  	Label eliminar = new Label("Producto a eliminar:");
	  	TextField teliminar = new TextField();
	  	Button beliminar = new Button("Eliminar");

	  	// Acciones de botones

	  	// Boton de añadir producto
	  	banadir.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	boolean stock = true;

				try {
					for(int i = 0;i<listaProds.size();i++){
						String[] a = listaProds.get(i).split(":");
						if (a[0].equalsIgnoreCase(tanadirnombre.getText())){
							System.out.println("Este producto ya se encuentra en stock");
							stock =false;
						}
					}
					if (stock) {
						Double precionuevo = Double.parseDouble(tanadirprecio.getText());
						BufferedWriter buf3 = new BufferedWriter(new FileWriter("productos.txt", true));		
						buf3.write(tanadirnombre.getText() + ":" + tanadirprecio.getText() + "\n");
						buf3.close();
				 		listaProds.add(tanadirnombre.getText()+":"+tanadirprecio.getText());
					}
					String a2="";
	  	  			for(int i = 0;i<listaProds.size();i++){
						String[] a = listaProds.get(i).split(":");
						a2=a2+a[0] + " -> " + a[1] + " €"+"\n";
					}
		 			lprod6.setText(a2);
		  			lprod3.setText(a2);
			
				}catch (Exception e1) {
        			e1.printStackTrace();
      			}    
            }
        });

        // Botón de eliminar producto
	  	beliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               boolean stock = false;
				try{
					for(int i = 0;i<listaProds.size();i++){
						String[] a = listaProds.get(i).split(":");
						if (a[0].equalsIgnoreCase(teliminar.getText())){
							stock =true;
						}
					}

					if (stock) {		
						BufferedWriter buf6 = new BufferedWriter(new FileWriter("productos.txt"));		
						for(int i=0; i<listaProds.size(); i++) {
							String[] a1=listaProds.get(i).split(":");
							if(!a1[0].equals(teliminar.getText())){
								buf6.write(listaProds.get(i)+"\n");
							}
						}
       					buf6.close();


       					listaProds.clear();
       					String lin = null;

						BufferedReader br6 = new BufferedReader(new FileReader("productos.txt"));		
						while((lin = br6.readLine()) != null) {
          					listaProds.add(lin);
          				}
          				br6.close();

          				String a4="";
	  	  				for(int i = 0;i<listaProds.size();i++){
							String[] a = listaProds.get(i).split(":");
							a4=a4+a[0] + " -> " + a[1] + " €"+"\n";
						}
					 	lprod6.setText(a4);
					 	lprod3.setText(a4);
          			}

         		}catch (Exception e2) {
         			e2.printStackTrace();
     			}
               stage.setScene(scene6);
              
        	}
            
        });

	  	// Accion de botones de proveedor
        volver6.setOnAction(e -> stage.setScene(scene1));
		exit6.setOnAction(e -> {stage.close();});
	  	a1="";
	  	for(int i = 0;i<listaProds.size();i++){
			String[] a = listaProds.get(i).split(":");
			a1=a1+a[0] + " -> " + a[1] + " €"+"\n";
		}

		// Presentación visual de proveedor
		lprod6=new Label(a1);
		HBox h6 = new HBox();
        h6.getChildren().addAll(volver6,exit6);
        HBox hanadir = new HBox();
        hanadir.getChildren().addAll(anadirnombre,tanadirnombre,anadirprecio,tanadirprecio,banadir);
        HBox heliminar = new HBox();
        heliminar.getChildren().addAll(eliminar,teliminar,beliminar);
		VBox vbox6=new VBox();
		vbox6.getChildren().addAll(tiendalabel6,prod6,lprod6,hanadir,heliminar,h6);
		vbox6.setSpacing(15);
      	vbox6.setMinSize(150,400);
	  	vbox6.setStyle("-fx-padding: 10;" +
	                "-fx-border-style: solid inside;" +
	                "-fx-border-width: 2;" +
	                "-fx-border-insets: 5;" +
	                "-fx-border-radius: 5;" +
	                "-fx-border-color: blue;");
	  	scene6=new Scene(vbox6);




	  	// Escena factura 

		Label tiendalabelfac = new Label("BACO & GARRIDO ALIMENTOS");
		VBox vboxfac=new VBox();
		Button exitfac = new Button("Salir");

		//Acción de botones
		exitfac.setOnAction(e -> {stage2.close();});
		

		// Representación visual de la factura
		vboxfac.getChildren().addAll(tiendalabelfac,ll,exitfac);
		vboxfac.setSpacing(15);
      	vboxfac.setMinSize(500,900);
	  	vboxfac.setStyle("-fx-padding: 10;" +
	                "-fx-border-style: solid inside;" +
	                "-fx-border-width: 2;" +
	                "-fx-border-insets: 5;" +
	                "-fx-border-radius: 5;" +
	                "-fx-border-color: blue;");
	  	scenefactura=new Scene(vboxfac);

	  	stage.setScene(scene1);
      	stage.show();
	}
	
}
