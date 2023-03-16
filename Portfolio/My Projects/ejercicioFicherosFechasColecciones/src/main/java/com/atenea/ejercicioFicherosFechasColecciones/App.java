package com.atenea.ejercicioFicherosFechasColecciones;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.atenea.ejercicioFicherosFechasColecciones.entities.Cliente;
import com.atenea.ejercicioFicherosFechasColecciones.entities.Producto;
import com.atenea.ejercicioFicherosFechasColecciones.utilidades.Methods;

/* He considerado que los archivos están en una carpeta ficheros en la raíz del sistema,
 * sirviendo para Linux, Windows y Mac.
 * C:\ficheros\clientes
 * C:\ficheros\productos
 */

public class App {
	
    public static void main( String[] args ) {    	
    	Scanner sc = new Scanner(System.in);
    	final Path RUTACLIENTES = Methods.elegirOS("clientes");
    	final Path RUTAPRODUCTOS = Methods.elegirOS("productos");
    	
    	System.out.println("¡Bienvenido/a a FinTech! // Welcome to FinTech");
    	Cliente cliente;
		System.out.println("Introduzca su DNI o CIF, por favor. // Type your ID NUMBER, please." );
		
    	do {
    		String dniCif = sc.nextLine().toUpperCase();
    		cliente = Methods.crearCliente(RUTACLIENTES,dniCif);
    		if (cliente.getNombre() == null) {
    			System.out.println("No existe ningún cliente asociado a ese DNI-CIF, pruebe otra vez. // No client associated to that ID NUMBER, try again.");
    		}
    	} while (cliente.getNombre() == null);
    	
    	Locale locale;
    	if (cliente.getNacionalidad().equals("ES")) {
    		 locale = new Locale("es","ES");
    	} else {
    		locale = Locale.ENGLISH;
    	}
    	
    	ResourceBundle messages = ResourceBundle.getBundle("resources/messages",locale);
    	System.out.println(Methods.saludar(cliente,messages));
    	System.out.println(Methods.mostrarHora(cliente,locale,messages));
		cliente.setFechaNacimiento(Methods.corregirFechaNacimiento(cliente,messages,sc));
		cliente.setEdad();
		
    	
    	HashSet<Producto> productos = Methods.crearProductos(RUTAPRODUCTOS);
    	Producto producto = Methods.decidirProducto(cliente, productos);
    	
    	if (producto.getNombre() != null) {
    		System.out.println(messages.getString("recomendacion") + producto.getNombre() + ".");
    	} else {
    		System.out.println(messages.getString("ningunaRecomendacion"));
    	}

    	sc.close();
    }
}
