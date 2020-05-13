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
	Scene scene0, scene1, scene2, scene3;
	Label tiendalabel = new Label("BACO & GARRIDO ALIMENTOS");
	Label bienvenido = new Label("¡BIENVENIDO!");
	Label pswl = new Label("Introduce contraseña:");
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
      	Button productor = new Button("PRODUCTOR");
		Button cliente = new Button("CLIENTE");
		Button cd = new Button("CONSULTAR COMPRAS");

		productor.setOnAction(e -> stage.setScene(scene2));
		cliente.setOnAction(e -> stage.setScene(scene3));
		cd.setOnAction(e -> stage.setScene(scene0));

		HBox hbox=new HBox();
		hbox.getChildren().addAll(productor, cliente);
		hbox.setSpacing(15);
		VBox vbox = new VBox();
      	vbox.getChildren().addAll(tiendalabel,hbox,cd);
      	vbox.setSpacing(15);
      	vbox.setMinSize(150,100);
	  	vbox.setStyle("-fx-padding: 10;" +
	                "-fx-border-style: solid inside;" +
	                "-fx-border-width: 2;" +
	                "-fx-border-insets: 5;" +
	                "-fx-border-radius: 5;" +
	                "-fx-border-color: blue;");
      	
      	scene1 = new Scene(vbox);

      	//Escena 0(Consulta Datos)
      	
        
      	TextField pswt0 = new TextField();
        HBox h0 =new HBox();
        h0.getChildren().addAll(pswl,pswt0);
        VBox vbox0 = new VBox();
      	vbox0.getChildren().addAll(tiendalabel,bienvenido,h0);
      	vbox0.setSpacing(15);
      	vbox0.setMinSize(150,100);
	  	vbox0.setStyle("-fx-padding: 10;" +
	                "-fx-border-style: solid inside;" +
	                "-fx-border-width: 2;" +
	                "-fx-border-insets: 5;" +
	                "-fx-border-radius: 5;" +
	                "-fx-border-color: blue;");

	  	 scene0 = new Scene(vbox0);

		//Escena 2(Productor)
            
            TextField pswt = new TextField();
            HBox h =new HBox();
            h.getChildren().addAll(pswl,pswt);
            VBox vbox2 = new VBox();
      	vbox2.getChildren().addAll(tiendalabel,bienvenido,h);
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

	  	 Label prod = new Label("Productos disponibles:");
	  	 String a1="";
	  	  for(int i = 0;i<listaProds.size();i++){
			String[] a = listaProds.get(i).split(":");
			a1=a1+"\n"+(a[0] + " -> " + a[1] + " €");
		}
		Label lprod=new Label(a1);
		VBox vbox3=new VBox();
		vbox3.getChildren().addAll(prod,lprod);
		vbox3.setSpacing(15);
      	vbox3.setMinSize(150,100);
	  	vbox3.setStyle("-fx-padding: 10;" +
	                "-fx-border-style: solid inside;" +
	                "-fx-border-width: 2;" +
	                "-fx-border-insets: 5;" +
	                "-fx-border-radius: 5;" +
	                "-fx-border-color: blue;");
	  	scene3=new Scene(vbox3);




		stage.setScene(scene1);
      	stage.show();


					

	}
}