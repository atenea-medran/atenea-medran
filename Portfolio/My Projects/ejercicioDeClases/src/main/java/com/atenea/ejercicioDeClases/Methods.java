package com.atenea.ejercicioDeClases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.atenea.ejercicioDeClases.entidades.Cuenta;
import com.atenea.ejercicioDeClases.entidades.CuentaCaixa;
import com.atenea.ejercicioDeClases.entidades.CuentaSabadell;
import com.atenea.ejercicioDeClases.entidades.CuentaSantander;

public class Methods {
	
	public static Path elegirOS(String cadena) {
    	String oS = System.getProperty("os.name").toLowerCase();
    	Path ruta;
    	if (oS.contains("win")) {
    		ruta = Paths.get("C:", "ficheros", "clientes", cadena).toAbsolutePath();
    	} else {
    		ruta = Paths.get("/", "ficheros", "clientes", cadena).toAbsolutePath();
    	}
    	return ruta;
	}
	
	
	public static List<Cuenta> crearListaCuentas(Path file) {
		List<Cuenta> lista = new ArrayList<Cuenta>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			List<String> lineas = Files.readAllLines(file);
			for (String linea : lineas) {
				String[] lineaArray = linea.split(";");
				String[] myFileStringSplit = file.toString().split("\\\\");
				Cuenta cuenta = null;
				switch (myFileStringSplit[myFileStringSplit.length-1]) {
					case "caixa.txt":
						CuentaCaixa cuentaCaixa = new CuentaCaixa();
						cuenta = cuentaCaixa;
						break;
					case "sabadell.txt":
						CuentaSabadell cuentaSabadell = new CuentaSabadell();
						cuenta = cuentaSabadell;
						break;
					case "santander.txt":
						CuentaSantander cuentaSantander = new CuentaSantander();
						cuentaSantander.setViveEnSantander(Boolean.parseBoolean(lineaArray[5]));
						cuenta = cuentaSantander;
						break;
				}
					
				cuenta.setDniCif(lineaArray[0]);
				cuenta.setNombre(lineaArray[1]);
				cuenta.setFechaNacimiento(LocalDate.parse(lineaArray[2], formatter));
				cuenta.setNacionalidad(lineaArray[3]);
				cuenta.setSaldo(Integer.parseInt(lineaArray[4]));
				lista.add(cuenta);				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public static List<Cuenta> borrarElemento(List<Cuenta> lista) {
		Random rand = new Random();
		lista.remove(rand.nextInt(0,lista.size()-1));		
		return lista;
	}
	
	public static Cuenta buscarEliminadoEnLista(List<Cuenta> listaBanco, List<Cuenta> listaCompleta) {
		Optional<Cuenta> result = listaBanco.stream()
				.filter(e->!listaCompleta.contains(e))
				.findFirst();
		return result.orElse(null);
	}

	public static long sumarBancoSantander(List<Cuenta> cuentasSantander) {
		return cuentasSantander.stream().mapToInt(e->e.getSaldo()).sum();
	}
	
	public static Cuenta maxSaldo(List<Cuenta> cuentas) {
		Cuenta cuentaMaxSaldo = new CuentaSantander();
		for (Cuenta cuenta : cuentas) {
			if (cuenta.getSaldo() > cuentaMaxSaldo.getSaldo()) cuentaMaxSaldo = cuenta;
		}
		return cuentaMaxSaldo;
	}
}
