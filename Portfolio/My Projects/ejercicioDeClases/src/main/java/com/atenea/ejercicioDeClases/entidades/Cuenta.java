package com.atenea.ejercicioDeClases.entidades;

import java.time.LocalDate;
import java.util.Objects;


public abstract class Cuenta {
	protected String dniCif;
	protected String nombre;
	protected LocalDate fechaNacimiento;
	protected String nacionalidad;
	protected int saldo;
	
	public Cuenta() {
		
	}

	public Cuenta(String dniCif, String nombre, LocalDate fechaNacimiento, String nacionalidad,
			int saldo) {
		super();
		this.dniCif = dniCif;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.saldo = saldo;
	}
	
	public Cuenta(Cuenta c) {
		super();
		this.dniCif = c.dniCif;
		this.nombre = c.nombre;
		this.fechaNacimiento = c.fechaNacimiento;
		this.nacionalidad = c.nacionalidad;
		this.saldo = c.saldo;
	}

	public String getDniCif() {
		return dniCif;
	}

	public void setDniCif(String dniCif) {
		this.dniCif = dniCif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Cuenta [dniCif=" + dniCif + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", nacionalidad=" + nacionalidad + ", saldo=" + saldo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dniCif, fechaNacimiento, nacionalidad, nombre, saldo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		return Objects.equals(dniCif, other.dniCif) && Objects.equals(fechaNacimiento, other.fechaNacimiento)
				&& Objects.equals(nacionalidad, other.nacionalidad) && Objects.equals(nombre, other.nombre)
				&& saldo == other.saldo;
	}
}