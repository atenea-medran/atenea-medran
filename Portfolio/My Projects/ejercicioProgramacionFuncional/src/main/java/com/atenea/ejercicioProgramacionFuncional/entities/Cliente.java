package com.atenea.ejercicioProgramacionFuncional.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;

public class Cliente {
	private String dniCif;
	private String nombre;
	private HashSet<LocalDate> hashSetFechasNacimiento;
	private LocalDate fechaNacimiento;
	private String nacionalidad;
	private int saldo;

	public Cliente() {
		this.saldo = 0;
		this.hashSetFechasNacimiento = new HashSet<LocalDate>();
	}

	public Cliente(String dniCif, String nombre, LocalDate fechaNacimiento, String nacionalidad, int saldo) {
		super();
		this.dniCif = dniCif;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.saldo = saldo;
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

	public HashSet<LocalDate> getHashSetFechasNacimiento() {
		return hashSetFechasNacimiento;
	}

	public void setHashSetFechasNacimiento(HashSet<LocalDate> hashSetFechasNacimiento) {
		this.hashSetFechasNacimiento = hashSetFechasNacimiento;
	}

	public void addFechaNacimiento(LocalDate fechaNacimiento) {
		this.getHashSetFechasNacimiento().add(fechaNacimiento);
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

	public void actualizarSaldo(int saldo) {
		this.saldo += saldo;
	}

	@Override
	public String toString() {
		return "Cliente [dniCif=" + dniCif + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento
				+ ", nacionalidad=" + nacionalidad + ", saldo=" + saldo + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dniCif, other.dniCif);
	}

}
