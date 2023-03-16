package com.atenea.ejercicioFicherosFechasColecciones.entities;

import java.util.Objects;

public class Producto {
	int edadMinima;
	int edadMaxima;
	int saldoMinimo;
	int saldoMaximo;
	String nombre;
	
	public Producto() {
		this.saldoMinimo = 0;
	}
	
	public Producto(int edadMinima, int edadMaxima, int saldoMinimo, int saldoMaximo, String nombre) {
		super();
		this.edadMinima = edadMinima;
		this.edadMaxima = edadMaxima;
		this.saldoMinimo = saldoMinimo;
		this.saldoMaximo = saldoMaximo;
		this.nombre = nombre;
	}

	public Producto(Producto p) {
		super();
		this.edadMinima = p.edadMinima;
		this.edadMaxima = p.edadMaxima;
		this.saldoMinimo = p.saldoMinimo;
		this.saldoMaximo = p.saldoMaximo;
		this.nombre = p.nombre;
	}

	public int getEdadMinima() {
		return edadMinima;
	}

	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}

	public int getEdadMaxima() {
		return edadMaxima;
	}

	public void setEdadMaxima(int edadMaxima) {
		this.edadMaxima = edadMaxima;
	}

	public int getSaldoMinimo() {
		return saldoMinimo;
	}

	public void setSaldoMinimo(int saldoMinimo) {
		this.saldoMinimo = saldoMinimo;
	}

	public int getSaldoMaximo() {
		return saldoMaximo;
	}

	public void setSaldoMaximo(int saldoMaximo) {
		this.saldoMaximo = saldoMaximo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Producto [edadMinima=" + edadMinima + ", edadMaxima=" + edadMaxima + ", saldoMinimo=" + saldoMinimo
				+ ", saldoMaximo=" + saldoMaximo + ", nombre=" + nombre + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(nombre, other.nombre);
	}
}
