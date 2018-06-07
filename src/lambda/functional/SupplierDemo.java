package lambda.functional;

import java.util.function.Supplier;

class Vehicle {
	public void drive() {
		System.out.println("Driving vehicle ...");
	}
}

class Car extends Vehicle {
	@Override
	public void drive() {
		System.out.println("Driving car...");
	}
}

public class SupplierDemo {
	static void driveVehicle(Supplier<? extends Vehicle> supplier) {
		Vehicle vehicle = supplier.get();
		vehicle.drive();
	}

	public static void main(String[] args) {
		// Using Lambda expression
		driveVehicle(() -> new Vehicle());
		driveVehicle(() -> new Car());
		// Using Method Reference
		driveVehicle(Vehicle::new);  // 객체 생성일 경우에는 new 
		driveVehicle(Car::new);
	}
}