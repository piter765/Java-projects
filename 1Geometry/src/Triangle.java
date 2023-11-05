import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class Triangle extends Figure {

    private double a;
    private double b;
    private double c;
    private double h;

    public Triangle(double a, double b, double c) throws IllegalArgumentException {
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Invalid triangle: The sum of the lengths of any two sides must be greater than the length of the third side.");
        } else if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Invalid input: All sides must be non-negative and not equal 0.");
        }

        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Triangle(double a, double b, double c, double h) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.h = h;
    }

    public Triangle(double a, double h) {
        this.a = a;
        this.h = h;
    }

    @Override
    double calculateArea() {
        double p = this.calculatePerimeter() / 2;
        return sqrt(p * (p-a) * (p-b) * (p-c));
    }

    @Override
    double calculatePerimeter() {
        return a + b + c;
    }

    @Override
    public void print() {
        System.out.println("Area of a triangle: " +  this.calculateArea());
        System.out.println("Perimeter of a triangle: " + this.calculatePerimeter());
    }

    @Override
    public ArrayList getSides() {
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(a);
        list.add(b);
        list.add(c);

        return list;
    }

}
