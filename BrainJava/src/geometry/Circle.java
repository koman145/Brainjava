package geometry;

public class Circle {
	int radius;
	Circle(int radius) {
		this.radius = radius;
	}
	double getArea() {
		return radius * radius * 3.14;
	}

}
