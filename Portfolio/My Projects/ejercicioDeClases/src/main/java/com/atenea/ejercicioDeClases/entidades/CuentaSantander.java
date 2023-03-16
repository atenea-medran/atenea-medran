package com.atenea.ejercicioDeClases.entidades;

import java.time.LocalDate;
import java.util.Objects;

public class CuentaSantander extends Cuenta {
	private boolean viveEnSantander;
	private static int numeroCuentasSantander = 0;

	public CuentaSantander() {
		saldo = 0;
		numeroCuentasSantander += 1;
	}
	
	public CuentaSantander(String dniCif, String nombre, LocalDate fechaNacimiento,
			String nacionalidad,int saldo, boolean viveEnSantander) {
		super(dniCif,nombre,fechaNacimiento,nacionalidad,saldo);
		this.viveEnSantander = viveEnSantander;
		numeroCuentasSantander += 1;
	}
	
	public CuentaSantander(CuentaSantander c) {
		super(c);
		viveEnSantander = c.getViveEnSantander();
		numeroCuentasSantander += 1;
	}

	public static int getNumeroCuentasSantander() {
		return numeroCuentasSantander;
	}

	public boolean getViveEnSantander() {
		return viveEnSantander;
	}

	public void setViveEnSantander(boolean viveEnSantander) {
		this.viveEnSantander = viveEnSantander;
	}

	@Override
	public String toString() {
		return "CuentaSantander: dniCif = " + dniCif + ", nombre = " + nombre + ", fechaNacimiento=" +
	fechaNacimiento + ", nacionalidad=" + nacionalidad + ", saldo=" + saldo + ", ¿Vive en Santander? " + viveEnSantander;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(viveEnSantander);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CuentaSantander other = (CuentaSantander) obj;
		return viveEnSantander == other.viveEnSantander;
	}
}
