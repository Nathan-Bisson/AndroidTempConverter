/**
 * The Tempurature Model
 * Here we will create the variables which we will use in the main activity
 * also will do the math for the celsius and fahrenheit conversion
 * Updates and notifies the observers
 * 
 * @author Nathan Bisson (biss0180)
 * @version 1.0 
 * 
 */

package model;

import java.util.Observable;

public class TempuratureModel extends Observable {
	
	public static final Double MAX_CELSIUS = 90.0;
	public static final Double MAX_FAHRENHEIT = 162.0;
	public static final Double MIN_CELSIUS = -40.0;
	public static final Double MIN_FAHRENHEIT = -40.0;
	public static final Double ROOMTEMP_CELSIUS = 23.0;
	public static final Double ROOMTEMP_FAHRENHEIT = 73.0;
	
	private Double celsius;
	private Double fahrenheit;
	
	public TempuratureModel() {
		// TODO Auto-generated constructor stub
		this(ROOMTEMP_CELSIUS, ROOMTEMP_FAHRENHEIT);
	}

	public TempuratureModel(Double celsius1, Double fahrenheit1) {
		this.celsius = celsius1;
		this.fahrenheit = fahrenheit1;
	}
	
	public Double getCelsius() {
		return celsius;
	}
	
	public Double getFahrenheit() {
		return fahrenheit;
	}
	
	public void setCelsius(Double celsius) {
		this.celsius = celsius;
		fahrenheit = (celsius*9/5+32);
		this.updateObservers();
	}
	
	public void setFahrenheit(Double fahrenheit) {
		this.fahrenheit = fahrenheit;
		celsius = ((fahrenheit-32)*5/9);
		this.updateObservers();
	}
	
	private void updateObservers() {
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
