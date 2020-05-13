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



import java.util.ArrayList;
import java.io.Console;
import java.io.FileReader;
import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Double;
import java.io.*;
import java.util.*;

public class NuevaTienda extends Application{

	static ArrayList<String> listaProds = new ArrayList<String>();
	//Compra c;
	String resulid="";
	Label ll=new Label("");
	Scene scene1, scene2, scene3, scene4, scene5;
	Label tiendalabel = new Label("BACO & GARRIDO ALIMENTOS");
	DAOCompra daocompra = new JDBCCompra();
	public static void main(String[] args) throws Exception{
		try {

			//DAOCompra daocompra = new JDBCCompra();//creacion de DAO para lo relacionado con la base de datos
		//a continuacion, el codigo para leer el fichero json, que corresponde al catalogo de productos.	
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

	@Override
	public void start(Stage stage) throws Exception{
		
		stage.setTitle("BACO & GARRIDO ALIMENTOS");

		//Escena 1(Principal)
		Label tiendalabel1 = new Label("BACO & GARRIDO ALIMENTOS");
      	Button proveedor = new Button("PROVEEDOR");
		Button cliente = new Button("CLIENTE");
		Button dueno = new Button("Dueño");

		proveedor.setOnAction(e -> stage.setScene(scene2));
		cliente.setOnAction(e -> stage.setScene(scene3));
		dueno.setOnAction(e -> stage.setScene(scene4));
		

		HBox hbox=new HBox();
		hbox.getChildren().addAll(dueno,proveedor, cliente);
		hbox.setSpacing(15);
		VBox vbox = new VBox();
      	vbox.getChildren().addAll(tiendalabel1,hbox);
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
            entrar.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override public void handle(ActionEvent e) 
            {
            	if(pswt.getText().equals("PaulinoApruebame")){
               stage.setScene(scene4);}
            }
        });
            HBox h =new HBox();
            h.getChildren().addAll(pswl,pswt,entrar);
            VBox vbox2 = new VBox();
      	vbox2.getChildren().addAll(tiendalabel2,bienvenido,h);
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
	  	 String a1="";
	  	  for(int i = 0;i<listaProds.size();i++){
			String[] a = listaProds.get(i).split(":");
			a1=a1+"\n"+(a[0] + " -> " + a[1] + " €");
		}
		Label lprod=new Label(a1);
		VBox vbox3=new VBox();
		vbox3.getChildren().addAll(tiendalabel3,prod,lprod);
		vbox3.setSpacing(15);
      	vbox3.setMinSize(150,100);
	  	vbox3.setStyle("-fx-padding: 10;" +
	                "-fx-border-style: solid inside;" +
	                "-fx-border-width: 2;" +
	                "-fx-border-insets: 5;" +
	                "-fx-border-radius: 5;" +
	                "-fx-border-color: blue;");
	  	scene3=new Scene(vbox3);


	  	//Escena 4(Dueño)
	  	Label tiendalabel4 = new Label("BACO & GARRIDO ALIMENTOS");

	  	 Label ids = new Label(daocompra.consultart());
	  	 
		Label introduzca=new Label("introduzca id de Compra:");
		TextField tf =new TextField();
		Button buscar = new Button("Buscar");
		buscar.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override public void handle(ActionEvent e) 
            {
            
               stage.setScene(scene5);
               resulid=daocompra.consultari(Integer.parseInt(tf.getText()));
               ll.setText(resulid);
               System.out.println(resulid);
            }
        });
		HBox h4=new HBox();
		h4.getChildren().addAll(introduzca,tf,buscar);
		VBox vbox4=new VBox();
		vbox4.getChildren().addAll(tiendalabel4,ids,h4);
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
		vbox5.getChildren().addAll(tiendalabel5,ll);
		vbox5.setSpacing(15);
      	vbox5.setMinSize(150,100);
	  	vbox5.setStyle("-fx-padding: 10;" +
	                "-fx-border-style: solid inside;" +
	                "-fx-border-width: 2;" +
	                "-fx-border-insets: 5;" +
	                "-fx-border-radius: 5;" +
	                "-fx-border-color: blue;");
	  	scene5=new Scene(vbox5);


		stage.setScene(scene1);
      	stage.show();


					

	}
}