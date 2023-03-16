package com.atenea.ejercicioJson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.atenea.ejercicioJson.entities.Nave;
import com.atenea.ejercicioJson.entities.Personaje;
import com.atenea.ejercicioJson.utilities.InternetUtils;
import com.atenea.ejercicioJson.utilities.SerializacionUtils;
import com.fasterxml.jackson.core.exc.StreamWriteException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class Methods {
	
	public static Boolean serializarPersonajes(Gson gson) {
		List<Personaje> personajes = new ArrayList<Personaje>();
		for (int i = 1; true; i++) {
			String url = "https://swapi.dev/api/people/?page=" + i + "&format=json";
			JsonObject jsonObject = gson.fromJson(InternetUtils.readUrl(url), JsonObject.class);
			JsonArray jsonArray = jsonObject.getAsJsonArray("results");

			personajes.addAll(Arrays.asList(gson.fromJson(jsonArray, Personaje[].class)));
			if (jsonObject.has("next") && jsonObject.get("next").isJsonNull())
				break;
		}
		return SerializacionUtils.serializarListaObjetos("personajes.dat", personajes);
	}

	public static void menu(Scanner sc, Gson gson, List<Personaje> personajes, List<Personaje> personajesFiltrados) {
		System.out.println("¿Qué desea hacer? Introduzca un número del 1 al 6.");
		System.out.println("1. Peliculas comunes.");
		System.out.println("2. Serializar personajes.");
		System.out.println("3. Deserializar personajes.");
		System.out.println("4. Obtener naves con más capacidad que la CR90.");
		System.out.println("5. Pasar JSON a XML.");
		System.out.println("6. Devolver objeto nave a partir del XML.");
		int opcion = 0;
		do {
			try {
				opcion = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				opcion = -1;
			}
			switch (opcion) {
			case 1:
				personajesFiltrados = Methods.elegirPersonajes(sc, personajes);
				List<String> nombrePeliculas = Methods.peliculasComunes(gson, personajesFiltrados);
				if (nombrePeliculas.isEmpty())
					System.out.println("No comparten peliculas.");
				else
					nombrePeliculas.forEach(e -> System.out.println(e));
				System.out.print("\n");
				menu(sc, gson, personajes, personajesFiltrados);
				break;
			case 2:
				try {
					if (!personajesFiltrados.isEmpty()) {
					SerializacionUtils.serializarListaObjetos("personajesFiltrados.dat", personajesFiltrados);
					System.out.println("Serialización exitosa.\n");
					} else {
						System.out.println("No se puede serializar una lista vacia, filtre personajes antes con la opcion 1.\n");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					System.out.println("Serializacion fallida.\n");
				}
				menu(sc, gson, personajes, personajesFiltrados);
				break;
			case 3:
				personajesFiltrados = SerializacionUtils.desSerializarListaObjetos("personajesFiltrados.dat");
				if(personajesFiltrados == null) System.out.println("\nNo existe el archivo, antes debe serializar los personajes con la opcion 2.");
				try {
					personajesFiltrados.forEach(e -> System.out.println(e));
				} catch (Exception e1) {
					System.out.println("Error. No se puede recorrer una lista vacía.");
				}
				break;
			case 4:
				List<Nave> navesConMasCapacidad = obtenerNaves(sc, gson);
				navesConMasCapacidad.forEach(e -> System.out.println(e.parcialPrint()));
				break;
			case 5:
				convertirJsonAXml(gson, "C:/ficheros/cr90.json", "C:/ficheros/cr90.xml");
				break;
			case 6:
				Nave nave = devolverNaveXml("C:/ficheros/cr90.xml");
				if(nave.getName() != null) System.out.println(nave);
				break;
			default:
				System.out.println("Opción incorrecta, inténtelo de nuevo con un número del 1 al 6.");
			}
		} while (opcion < 1 || opcion > 6);

		sc.close();

	}

	public static List<Personaje> elegirPersonajes(Scanner sc, List<Personaje> personajes) {
		List<Personaje> personajesFiltrados = new ArrayList<Personaje>();
		System.out.println("Introduzca el código del primer personaje. Del 1 al " + personajes.size() + ".");
		int codigo1 = 0;
		do {
			try {
				codigo1 = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				codigo1 = -1;
			}
			if (codigo1 < 1 || codigo1 > personajes.size())
				System.out.println("Código incorrecto. Debe ser del 1 al " + personajes.size() + ".");
		} while (codigo1 < 1 || codigo1 > personajes.size());

		System.out.println("Introduzca el código del segundo personaje. Del 1 al " + personajes.size() + ".");
		int codigo2 = 0;
		do {
			try {
				codigo2 = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				codigo2 = -1;
			}
			
			if (codigo2 < 1 || codigo2> personajes.size())
				System.out.println("Código incorrecto. Debe ser del 1 al " + personajes.size() + ".");
			if (codigo1 == codigo2) System.out.println("No pueden ser personajes repetidos. Pruebe con otro numero.");
		} while (codigo2 < 1 || codigo2 > personajes.size() || codigo1 == codigo2);

		personajesFiltrados.add(personajes.get(codigo1 - 1));
		personajesFiltrados.add(personajes.get(codigo2 - 1));

		return personajesFiltrados;

	}

	public static List<String> peliculasComunes(Gson gson, List<Personaje> personajes) {

		List<String> peliculasComunes = personajes.get(0).getFilms();
		peliculasComunes.retainAll(personajes.get(1).getFilms());
		peliculasComunes = peliculasComunes.stream().map(e -> e + "?format=json").collect(Collectors.toList());
		List<String> nombrePeliculas = new ArrayList<String>();
		for (String pelicula : peliculasComunes) {
			nombrePeliculas
					.add(gson.fromJson(InternetUtils.readUrl(pelicula), JsonObject.class).get("title").getAsString());
		}
		return nombrePeliculas;
	}
	


	public static List<Nave> obtenerNaves(Scanner sc, Gson gson) {
		System.out.println(
				"Introduzca un numero del 1 al 6 que representa a la película para comparar las naves que aparecen con la CR90.");
		int codigo = 0;
		do {
			try {
				codigo = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				codigo = -1;
			}
			if (codigo < 1 || codigo > 6)
				System.out.println("Numero incorrecto, debe ser del 1 al 6");
		} while (codigo < 1 || codigo > 6);

		String url = "https://swapi.dev/api/films/" + codigo + "/?format=json";
		JsonObject jsonObject = gson.fromJson(InternetUtils.readUrl(url), JsonObject.class);
		JsonArray jsonArray = jsonObject.getAsJsonArray("starships");
		List<String> linksNaves = IntStream.range(0, jsonArray.size())
				.mapToObj(i -> jsonArray.get(i).getAsString() + "?format=json").collect(Collectors.toList());

		List<Nave> naves = linksNaves.stream().map(e -> gson.fromJson(InternetUtils.readUrl(e), Nave.class))
				.collect(Collectors.toList());

		Nave cr90 = new Nave();
		try {
			cr90 = gson.fromJson(new FileReader("C:/ficheros/cr90.json"), Nave.class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e1) {
			e1.printStackTrace();
		}

		int capacidadCr90 = cr90.getPassengers();

		return naves.stream().filter(e -> e.getPassengers() > capacidadCr90).collect(Collectors.toList());
	}

	public static Boolean convertirJsonAXml(Gson gson, String ruta, String xmlFilePath) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = null;
		try {
			jsonNode = objectMapper.readTree(new File(ruta));
		} catch (IOException e) {
			e.printStackTrace();
		}

		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

		File xmlFile = new File(xmlFilePath);
		try {
			xmlMapper.writeValue(xmlFile, jsonNode);
		} catch (StreamWriteException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("XML creado en " + xmlFile.getAbsolutePath());
		return true;
	}
	
	public static Nave devolverNaveXml(String ruta) {
		Nave nave = new Nave();
		try {
			File inputFile = new File(ruta);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);  // Comprueba que es un XML valido
			doc.getDocumentElement().normalize();
			
			Node node = doc.getElementsByTagName("ObjectNode").item(0);
			
//			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) node;
				// añado cada asignatura a la lista
//				resultado.add(new Nave(
			nave.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
			nave.setModel(eElement.getElementsByTagName("model").item(0).getTextContent());
			nave.setManufacturer(eElement.getElementsByTagName("manufacturer").item(0).getTextContent());
			nave.setCostInCredits(eElement.getElementsByTagName("cost_in_credits").item(0).getTextContent());
			nave.setLentgh(eElement.getElementsByTagName("length").item(0).getTextContent());				
			nave.setMaxAtmospheringSpeed(eElement.getElementsByTagName("max_atmosphering_speed").item(0).getTextContent());
			nave.setCrew(eElement.getElementsByTagName("crew").item(0).getTextContent());
			nave.setPassengers(eElement.getElementsByTagName("passengers").item(0).getTextContent());
			nave.setCargoCapacity(eElement.getElementsByTagName("cargo_capacity").item(0).getTextContent());
			nave.setConsumables(eElement.getElementsByTagName("consumables").item(0).getTextContent());
			nave.setHyperdriveRating(eElement.getElementsByTagName("hyperdrive_rating").item(0).getTextContent());
			nave.setMGLT(eElement.getElementsByTagName("MGLT").item(0).getTextContent());
			nave.setStarshipClass(eElement.getElementsByTagName("starship_class").item(0).getTextContent());
			
			NodeList filmsNodes = eElement.getElementsByTagName("films");
			List<String> films = new ArrayList<String>();
			for (int i = 0; i < filmsNodes.getLength(); i++) {
				Node filmNode = filmsNodes.item(i);
				films.add(filmNode.getTextContent());
			}
			nave.setFilms(films);
			
			nave.setCreated(eElement.getElementsByTagName("created").item(0).getTextContent());
			nave.setEdited(eElement.getElementsByTagName("edited").item(0).getTextContent());
			nave.setUrl(eElement.getElementsByTagName("url").item(0).getTextContent());
		} catch (Exception e) {
			System.out.println("Error, no existe el archivo XML. Antes debe crearlo con la opcion 5.");
		}
		
		return nave;
		
	}

}
