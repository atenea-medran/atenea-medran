package com.atenea.ejercicioDeClases.entidades;

import java.time.LocalDate;
import java.util.Objects;

import com.atenea.ejercicioDeClases.enumerados.NivelCatalan;

public final class CuentaSabadell extends Cuenta {
	private NivelCatalan nivelCatalan;

	public CuentaSabadell() {
		saldo = 0;
		nivelCatalan = NivelCatalan.Medio;
	}
	
	public CuentaSabadell(String dniCif, String nombre, 
			LocalDate fechaNacimiento, String nacionalidad,int saldo) {
		super(dniCif,nombre,fechaNacimiento,nacionalidad,saldo);
		nivelCatalan = NivelCatalan.Medio;
	}
	
	public CuentaSabadell(CuentaSabadell c) {
		super(c);
		nivelCatalan = NivelCatalan.Medio;
	}
	
	public NivelCatalan getNivelCatalan() {
		return nivelCatalan;
	}

	public void setNivelCatalan(NivelCatalan nivelCatalan) {
		this.nivelCatalan = nivelCatalan;
	}
	
	@Override
	public String toString() {
		return "CuentaSabadell (dniCif = " + dniCif + ", nombre = " + nombre + ", fechaNacimiento = " +
	fechaNacimiento + ", nacionalidad = " + nacionalidad + ", saldo = " + saldo + ", nivelCatalan = " + nivelCatalan + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(nivelCatalan);
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
		CuentaSabadell other = (CuentaSabadell) obj;
		return Objects.equals(nivelCatalan, other.nivelCatalan);
	}
}
