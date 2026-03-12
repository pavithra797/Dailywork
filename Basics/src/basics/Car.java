package basics;

import java.util.Date;
import java.util.List;

public class Car {
	private String model; // enum //immutable, only getter
	private String color;
	private float price;// mutable,getter+setter
	private Engine engine; // Has-A
	private String fuleType;
	String number;
	boolean insured;
	String transmission;
	int seats;
	float weight;
	String designe;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public String getFuleType() {
		return fuleType;
	}

	public void setFuleType(String fuleType) {
		this.fuleType = fuleType;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isInsured() {
		return insured;
	}

	public void setInsured(boolean insured) {
		this.insured = insured;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getDesigne() {
		return designe;
	}

	public void setDesigne(String designe) {
		this.designe = designe;
	}

	public float getMileage() {
		return mileage;
	}

	public void setMileage(float mileage) {
		this.mileage = mileage;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public boolean isEv() {
		return ev;
	}

	public void setEv(boolean ev) {
		this.ev = ev;
	}

	public Date getManufacturigDate() {
		return manufacturigDate;
	}

	public void setManufacturigDate(Date manufacturigDate) {
		this.manufacturigDate = manufacturigDate;
	}

	public List<Wheel> getWheels() {
		return wheels;
	}

	public void setWheels(List<Wheel> wheels) {
		this.wheels = wheels;
	}

	public String getModel() {
		return model;
	}

	float mileage;
	String brand;
	boolean ev;
	Date manufacturigDate;
	List<Wheel> wheels;

	public static void main(String[] args) {

	}

}
