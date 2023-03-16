package com.atenea.ejercicioDeClases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.atenea.ejercicioDeClases.entidades.Cuenta;
import com.atenea.ejercicioDeClases.entidades.CuentaSantander;

/* Igualmente he considerado que los archivos están en la raiz del sistema
 * C:\ficheros\clientes
 * He fingido que se introducía el nivel de catalán de La Caixa mal.
 */
public class App {
	
    public static void main( String[] args ) {
    	    	
    	List<Cuenta> cuentasCaixa = Methods.crearListaCuentas(Methods.elegirOS("caixa.txt"));
    	List<Cuenta> cuentasSabadell = Methods.crearListaCuentas(Methods.elegirOS("sabadell.txt"));
    	List<Cuenta> cuentasSantander = Methods.crearListaCuentas(Methods.elegirOS("santander.txt"));
    	
    	List<Cuenta> cuentas = new ArrayList<>();
    	cuentas.addAll(cuentasCaixa);
    	cuentas.addAll(cuentasSabadell);
    	cuentas.addAll(cuentasSantander);
    	
    	cuentas.forEach(e->System.out.println(e));
    	cuentas = Methods.borrarElemento(cuentas);
    	List<List<Cuenta>> listaDeListas = Arrays.asList(cuentasCaixa,cuentasSabadell,cuentasSantander);
    	
    	
    	Cuenta cuentaEliminada = null;
    	for (List<Cuenta> lista : listaDeListas) {
    		cuentaEliminada = Methods.buscarEliminadoEnLista(lista, cuentas);
    		if (cuentaEliminada != null) break;
    	}
    	
    	System.out.println("Cuenta eliminada: " + cuentaEliminada);
    	
    	System.out.println("El saldo total de las cuentas del Banco Santander es: " + Methods.sumarBancoSantander(cuentasSantander));
    	System.out.println("El número de cuentas en el banco Santander es: " + CuentaSantander.getNumeroCuentasSantander());
    	
    	Cuenta cuentaMaxSaldoSantander = Methods.maxSaldo(cuentasSantander);
    	System.out.println("La cuenta con el mayor saldo del banco Santander es la de " + cuentaMaxSaldoSantander.getNombre() + ", con DNI " +
    			cuentaMaxSaldoSantander.getDniCif() + ", y su saldo es: " + cuentaMaxSaldoSantander.getSaldo());
    }
}