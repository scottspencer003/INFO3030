package com.mycompany.app;

public class FuelCost {

	private double costPerGallon = 2.67;
	private double gallons = 13.0;
	private double fillCount = 0.0;
	private double totalCost = 0.0;
	
	public FuelCost(int fillCount) {
		this.fillCount = fillCount;
	}
	
	public FuelCost(double costPerGallon, double fillCount) {
		this.costPerGallon = costPerGallon;
		this.fillCount = fillCount;
	}
	
	public FuelCost(double costPerGallon, double fillCount, double gallons ) {
		this.costPerGallon = costPerGallon;
		this.fillCount = fillCount;
		this.gallons = gallons;
	}
	
	public double calculateCost() {
		totalCost += fillCount * gallons * costPerGallon;
		return totalCost;
	}
	
			
	
}
