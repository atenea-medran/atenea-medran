package com.atenea.ejercicioJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.atenea.ejercicioJson.entities.Personaje;
import com.atenea.ejercicioJson.utilities.SerializacionUtils;
import com.google.gson.Gson;

public class App {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Gson gson = new Gson();
//		He serializado porque a veces tardaba mucho en coger los personajes.
//		Se puede borrar personajes.dat y comprobar que funciona.
//		Methods.serializarPersonajes(gson);
		List<Personaje> personajes = SerializacionUtils.desSerializarListaObjetos("personajes.dat");
		List<Personaje> personajesFiltrados = new ArrayList<Personaje>();
		Methods.menu(sc, gson, personajes, personajesFiltrados);
	}
}