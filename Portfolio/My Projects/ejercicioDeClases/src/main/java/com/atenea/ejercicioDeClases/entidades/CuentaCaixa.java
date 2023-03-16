package com.atenea.ejercicioDeClases.entidades;

import java.time.LocalDate;
import java.util.Objects;

import com.atenea.ejercicioDeClases.enumerados.NivelCatalan;

public final class CuentaCaixa extends Cuenta {
	private NivelCatalan nivelCatalan;
	
	public CuentaCaixa() {
		saldo = 0;
		nivelCatalan = NivelCatalan.Alto;
	}
	
	public CuentaCaixa(String dniCif, String nombre, 
			LocalDate fechaNacimiento, String nacionalidad,int saldo) {
		super(dniCif,nombre,fechaNacimiento,nacionalidad,saldo);
		nivelCatalan = NivelCatalan.Alto;
	}
	
	public CuentaCaixa(CuentaCaixa c) {
		super(c);
		nivelCatalan = NivelCatalan.Alto;
	}

	public NivelCatalan getNivelCatalan() {
		return nivelCatalan;
	}

	public void setNivelCatalan(NivelCatalan nivelCatalan) {
		this.nivelCatalan = nivelCatalan;
	}

	@Override
	public String toString() {
		return "CuentaCaixa [dniCif=" + dniCif + ", nombre=" + nombre + ", fechaNacimiento=" +
	fechaNacimiento + ", nacionalidad=" + nacionalidad + ", saldo=" + saldo + ", nivelCatalan=" + nivelCatalan + "]";
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
		CuentaCaixa other = (CuentaCaixa) obj;
		return Objects.equals(nivelCatalan, other.nivelCatalan);
	}
	
	

}
