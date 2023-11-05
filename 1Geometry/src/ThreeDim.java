import java.util.ArrayList;

public class ThreeDim extends Figure {

    private Figure figure;

    private double H;

    public ThreeDim(Figure figure, double H) throws IllegalArgumentException {
        this.figure = figure;
        this.H = H;
    }

    @Override
    double calculatePerimeter() {
        return 0;
    }

    @Override
    double calculateArea() {
        double area = 2 * figure.calculateArea();

        ArrayList<Double> sides = figure.getSides();
        for (Double side: sides) {
            area += side * H;
        }
        return area;
    }

    double calculateVolume() {
        return figure.calculateArea() * H;
    }

    @Override
    public void print() {
        System.out.println("Area of a prism: " +  this.calculateArea());
        System.out.println("Volume of a prism: " + this.calculateVolume());
    }

    @Override
    public ArrayList getSides() {
        return null;
    }
}
