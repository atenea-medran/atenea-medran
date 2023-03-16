package com.atenea.ejercicioProgramacionFuncional;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import com.atenea.ejercicioProgramacionFuncional.entities.Cliente;

/* He considerado que los archivos están en una carpeta ficheros en la raíz del sistema,
 * sirviendo para Linux, Windows y Mac.
 * C:\ficheros\clientes
 */

public class App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final Path RUTACLIENTES = Methods.elegirOS("clientes");

		System.out.println("¡Bienvenido/a a FinTech!");
		List<Cliente> clientes = Methods.crearClientes(RUTACLIENTES);
		List<String> morosos = Methods.filtrarTrueMorososFalseNoMorosos(clientes, true);
		List<String> noMorosos = Methods.filtrarTrueMorososFalseNoMorosos(clientes, false);

		System.out.println("¿Qué desea hacer? Introduzca un número del 1 al 5.");
		System.out.println("1. Obtener saldo de un cliente.");
		System.out.println("2. Obtener lista de morosos y no morosos.");
		System.out.println("3. Obtener cliente preferido.");
		System.out.println("4. Obtener clientes por nombre.");
		System.out.println("5. Obtener cliente que más gana en cierta posición.");
		int opcion = 0;
		do {
			try {
				opcion = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				opcion = -1;
			}
			switch (opcion) {
			case 1:
				System.out.println("El saldo del cliente es: " + Methods.obtenerSaldoCliente(sc, clientes));
				break;
			case 2:
				System.out.println("Morosos:");
				morosos.forEach(e -> System.out.println("-" + e));
				System.out.println("No morosos:");
				noMorosos.forEach(e -> System.out.println("-" + e));
				break;
			case 3:
				System.out.println("El nombre del cliente preferido es: " + Methods.clientePreferido(clientes) + ".");
				break;
			case 4:
				Methods.obtenerClientesPorNombre(sc, clientes).forEach(
						e -> System.out.println("Nombre: " + e.getNombre() + ", Saldo: " + e.getSaldo() + "."));
				break;
			case 5:
				System.out.println(Methods.clientesQueMasGanan(sc, clientes));
				break;
			default:
				System.out.println("Opción incorrecta, inténtelo de nuevo con un número del 1 al 5.");
			}
		} while (opcion < 1 || opcion > 5);

		sc.close();
	}
}
