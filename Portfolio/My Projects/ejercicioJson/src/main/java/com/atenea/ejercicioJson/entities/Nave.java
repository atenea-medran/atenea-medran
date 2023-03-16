package com.atenea.ejercicioJson.entities;

import java.util.List;
import java.util.Objects;

public class Nave {
	private String name;
	private String model;
	private String manufacturer;
	private String costInCredits;
	private String lentgh;
	private String maxAtmospheringSpeed;
	private String crew;
	private String passengers;
	private String cargoCapacity;
	private String consumables;
	private String hyperdriveRating;
	private String MGLT;
	private String starshipClass;
	private List<String> pilots;
	private List<String> films;
	private String created;
	private String edited;
	private String url;
	
	public Nave() {
	}

	public Nave(String name, String model, String manufacturer, String costInCredits, String lentgh,
			String maxAtmospheringSpeed, String crew, String passengers, String cargoCapacity, String consumables,
			String hyperdriveRating, String mGLT, String starshipClass, List<String> pilots, List<String> films,
			String created, String edited, String url) {
		super();
		this.name = name;
		this.model = model;
		this.manufacturer = manufacturer;
		this.costInCredits = costInCredits;
		this.lentgh = lentgh;
		this.maxAtmospheringSpeed = maxAtmospheringSpeed;
		this.crew = crew;
		this.passengers = passengers;
		this.cargoCapacity = cargoCapacity;
		this.consumables = consumables;
		this.hyperdriveRating = hyperdriveRating;
		this.MGLT = mGLT;
		this.starshipClass = starshipClass;
		this.pilots = pilots;
		this.films = films;
		this.created = created;
		this.edited = edited;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCostInCredits() {
		return costInCredits;
	}

	public void setCostInCredits(String costInCredits) {
		this.costInCredits = costInCredits;
	}

	public String getLentgh() {
		return lentgh;
	}

	public void setLentgh(String lentgh) {
		this.lentgh = lentgh;
	}

	public String getMaxAtmospheringSpeed() {
		return maxAtmospheringSpeed;
	}

	public void setMaxAtmospheringSpeed(String maxAtmospheringSpeed) {
		this.maxAtmospheringSpeed = maxAtmospheringSpeed;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public int getPassengers() {
		passengers = passengers.replace(",","");
		if (passengers.equals("unknown") || passengers.equals("n/a")) return -1;
		return Integer.parseInt(passengers);
	}

	public void setPassengers(String passengers) {
		this.passengers = passengers;
	}

	public String getCargoCapacity() {
		return cargoCapacity;
	}

	public void setCargoCapacity(String cargoCapacity) {
		this.cargoCapacity = cargoCapacity;
	}

	public String getConsumables() {
		return consumables;
	}

	public void setConsumables(String consumables) {
		this.consumables = consumables;
	}

	public String getHyperdriveRating() {
		return hyperdriveRating;
	}

	public void setHyperdriveRating(String hyperdriveRating) {
		this.hyperdriveRating = hyperdriveRating;
	}

	public String getMGLT() {
		return MGLT;
	}

	public void setMGLT(String mGLT) {
		MGLT = mGLT;
	}

	public String getStarshipClass() {
		return starshipClass;
	}

	public void setStarshipClass(String starshipClass) {
		this.starshipClass = starshipClass;
	}

	public List<String> getPilots() {
		return pilots;
	}

	public void setPilots(List<String> pilots) {
		this.pilots = pilots;
	}

	public List<String> getFilms() {
		return films;
	}

	public void setFilms(List<String> films) {
		this.films = films;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getEdited() {
		return edited;
	}

	public void setEdited(String edited) {
		this.edited = edited;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		return Objects.hash(url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nave other = (Nave) obj;
		return Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		return "Nave [name=" + name + ", model=" + model + ", manufacturer=" + manufacturer + ", costInCredits="
				+ costInCredits + ", lentgh=" + lentgh + ", maxAtmospheringSpeed=" + maxAtmospheringSpeed + ", crew="
				+ crew + ", passengers=" + passengers + ", cargoCapacity=" + cargoCapacity + ", consumables="
				+ consumables + ", hyperdriveRating=" + hyperdriveRating + ", MGLT=" + MGLT + ", starshipClass="
				+ starshipClass + ", pilots=" + pilots + ", films=" + films + ", created=" + created + ", edited="
				+ edited + ", url=" + url + "]";
	}
	
	public String parcialPrint() {
		return "Nave: " + name + ", pasajeros: " + passengers;
	}

}
