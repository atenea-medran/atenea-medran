package com.atenea.ejercicioProgramacionFuncional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Comparator;

import com.atenea.ejercicioProgramacionFuncional.entities.Cliente;

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

	public static List<Cliente> crearClientes(Path rutaClientes) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		File folder = new File(rutaClientes.toString());
		File[] files = folder.listFiles();
		for (File file : files) {
			try {
				List<String> lineas = Files.readAllLines(file.toPath());
				for (String linea : lineas) {
					Cliente cliente = new Cliente();
					String[] lineaArray = linea.split(";");

					cliente.setDniCif(lineaArray[0]);
					if (!clientes.contains(cliente)) {
						cliente.setNombre(lineaArray[1]);
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						cliente.setFechaNacimiento(LocalDate.parse(lineaArray[2], formatter));
						cliente.setNacionalidad(lineaArray[3]);
						cliente.setSaldo(Integer.parseInt(lineaArray[4]));
						clientes.add(cliente);
					} else {
						clientes.stream().filter(e -> e.getDniCif().equals(lineaArray[0]))
								.forEach(e -> e.actualizarSaldo(Integer.parseInt(lineaArray[4])));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return clientes;
	}

	public static int obtenerSaldoCliente(Scanner sc, List<Cliente> clientes) {
		System.out.println("Introduzca un DNI, por favor.");
		Optional<Cliente> clienteOpcional;
		Cliente cliente = null;
		do {
			String dniCif = sc.nextLine().toUpperCase();
			clienteOpcional = clientes.stream().filter(e -> e.getDniCif().equals(dniCif)).findFirst();
			if (clienteOpcional.isPresent()) {
				cliente = clienteOpcional.get();

			} else {
				System.out.println("No existe ningún cliente asociado a ese DNI-CIF, pruebe otra vez.");
			}
		} while (!clienteOpcional.isPresent());

		return cliente.getSaldo();

	}

	public static List<String> filtrarTrueMorososFalseNoMorosos(List<Cliente> clientes, Boolean morosos) {
		if (morosos) {
			return clientes.stream().filter(e -> e.getSaldo() < 0).map(e -> e.getNombre()).collect(Collectors.toList());
		} else {
			return clientes.stream().filter(e -> e.getSaldo() >= 0).map(e -> e.getNombre())
					.collect(Collectors.toList());
		}
	}

	public static String clientePreferido(List<Cliente> clientes) {
		Optional<String> nombreClientePreferido = clientes.stream().max(Comparator.comparing(Cliente::getSaldo))
				.map(Cliente::getNombre);

		return nombreClientePreferido.get();

	}

	public static List<Cliente> obtenerClientesPorNombre(Scanner sc, List<Cliente> clientes) {
		System.out.println("Introduzca una cadena de texto para buscar clientes.");
		String nombre = sc.nextLine();
		List<Cliente> clientesFiltrados = clientes.stream()
				.filter(e -> e.getNombre().toLowerCase().contains(nombre.toLowerCase())).collect(Collectors.toList());
		if (clientesFiltrados.isEmpty())
			System.out.println("No hay clientes que coincidan con la búsqueda.");
		return clientesFiltrados;
	}

	public static Cliente clientesQueMasGanan(Scanner sc, List<Cliente> clientes) {
		System.out.println("Introduzca un número del 1 al " + clientes.size()
				+ " para conocer los datos del cliente que más gana en esa posición.");
		int indice;
		do {
			try {
				indice = sc.nextInt();
			} catch (NumberFormatException e) {
				indice = -1;
			}

			if (indice < 1 || indice > clientes.size())
				System.out.println("Posición incorrecta, introduzca un número del 1 al " + clientes.size() + ".");
		} while (indice < 1 || indice > clientes.size());

		return clientes.stream().sorted(Comparator.comparing(Cliente::getSaldo).reversed()).collect(Collectors.toList())
				.get(indice - 1);
	}
}
