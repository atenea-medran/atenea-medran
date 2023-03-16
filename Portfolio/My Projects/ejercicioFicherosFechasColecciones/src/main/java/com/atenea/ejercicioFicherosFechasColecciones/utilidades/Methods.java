package com.atenea.ejercicioFicherosFechasColecciones.utilidades;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.IntStream;

import com.atenea.ejercicioFicherosFechasColecciones.entities.Cliente;
import com.atenea.ejercicioFicherosFechasColecciones.entities.Producto;

public class Methods {
	
	public static Path elegirOS(String cadena) {
    	String oS = System.getProperty("os.name").toLowerCase();
    	Path ruta;
    	if (oS.contains("win")) {
    		ruta = Paths.get("C:", "ficheros", cadena).toAbsolutePath();
    	} else {
    		ruta = Paths.get("/", "ficheros", cadena).toAbsolutePath();
    	}
    	return ruta;
	}

	public static Cliente crearCliente(Path rutaClientes, String dniCif) {
		Cliente cliente = new Cliente();
		File folder = new File(rutaClientes.toString());
		File[] files = folder.listFiles();
		for (File file: files) {
			try {
				List<String> lineas = Files.readAllLines(file.toPath());
				for (String linea : lineas) {
					String[] lineaArray = linea.split(";");
					if (lineaArray[0].equals(dniCif)) {
						if (cliente.getDniCif( ) == null) cliente.setDniCif(lineaArray[0]);
						if (cliente.getNombre() == null) cliente.setNombre(lineaArray[1]);
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						if (cliente.getFechaNacimiento() == null) cliente.addFechaNacimiento(LocalDate.parse(lineaArray[2], formatter));
						if (cliente.getNacionalidad() == null) cliente.setNacionalidad(lineaArray[3]);
						cliente.actualizarSaldo(Integer.parseInt(lineaArray[4]));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cliente;
	}
	
	public static LocalDate corregirFechaNacimiento(Cliente cliente,ResourceBundle messages, Scanner sc) {
		HashSet<LocalDate> hashSetFechasNacimiento = cliente.getHashSetFechasNacimiento();
		if (cliente.getHashSetFechasNacimiento().size() > 1) {
			int indice;
			System.out.println(messages.getString("confirmarFecha") + hashSetFechasNacimiento.size()+".");
			do {
				IntStream.range(0, hashSetFechasNacimiento.size())
					.forEach(i -> {
				        String fechaFormateada = ((LocalDate) hashSetFechasNacimiento.toArray()[i]).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
						System.out.println(i+1 + ". " + fechaFormateada);
					});
					try {
						indice = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException e) {
						indice = -1;
					}
					if (indice < 1 || indice > hashSetFechasNacimiento.size()) {
						System.out.println(messages.getString("errorFecha") + hashSetFechasNacimiento.size()+".");
					}
			} while (indice < 1 || indice > hashSetFechasNacimiento.size());
			System.out.println(messages.getString("fechaConfirmada"));
			return (LocalDate)hashSetFechasNacimiento.toArray()[indice-1];

		} else {
			return (LocalDate)hashSetFechasNacimiento.toArray()[0];
		}
	}

	public static String saludar(Cliente cliente, ResourceBundle messages) {
		if (cliente.getNacionalidad().equals("ES")) {
			return messages.getString("saludo") + cliente.getNombre() + ".";
		} else {
			return messages.getString("saludo") + cliente.getNombre() + ".";
		}
	}
	
    public static String mostrarHora(Cliente cliente, Locale locale, ResourceBundle messages) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(messages.getString("fechayhora"), locale);
    	return LocalDateTime.now().format(formatter);
    }
    
	public static HashSet<Producto> crearProductos(Path rutaProductos) {
		HashSet<Producto> productos = new HashSet<Producto>();
		File folder = new File(rutaProductos.toString());
		File[] files = folder.listFiles();
		for (File file: files) {
			try {
				List<String> lineas = Files.readAllLines(file.toPath());
				for (String linea : lineas) {
					Producto producto = new Producto();
					String[] lineaArray = linea.split(";");
					producto.setEdadMinima(Integer.parseInt(lineaArray[0]));
					producto.setEdadMaxima(Integer.parseInt(lineaArray[1]));
					producto.setSaldoMinimo(Integer.parseInt(lineaArray[2]));
					producto.setSaldoMaximo(Integer.parseInt(lineaArray[3]));
					producto.setNombre(lineaArray[4]);
					productos.add(producto);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return productos;
	}
	
	public static Producto decidirProducto(Cliente cliente, HashSet<Producto> productos) {
		List<Producto> productosFiltrados = productos.stream().filter(e -> cliente.getEdad() >= e.getEdadMinima() 
																&& cliente.getEdad() <= e.getEdadMaxima() 
																&& cliente.getSaldo() >= e.getSaldoMinimo() 
																&& cliente.getSaldo() <= e.getSaldoMaximo()).toList();
		Producto productoRecomendado = new Producto();
		for (Producto producto: productosFiltrados) {
			if (producto.getSaldoMinimo() > productoRecomendado.getSaldoMinimo()) productoRecomendado = producto;
		}
		return productoRecomendado;
	}
}
