import java.util.ArrayList;

public class Diamond extends Figure {

    private double a;
    private double h;
    private double d1;
    private double d2;

    public Diamond(double a, double h) throws IllegalArgumentException {
        if (a <= 0 || h <= 0) {
            throw new IllegalArgumentException("Invalid input: All values must be non-negative and not equal 0.");
        }
        this.a = a;
        this.h = h;
    }

    @Override
    double calculateArea() {
        return a * h;
    }

    @Override
    double calculatePerimeter() {
        return 4 * a;
    }

    @Override
    public void print() {
        System.out.println("Area of a diamond: " +  this.calculateArea());
        System.out.println("Perimeter of a diamond: " + this.calculatePerimeter());
    }

    @Override
    public ArrayList getSides() {
        ArrayList<Double> list = new ArrayList<Double>();
        for (int i = 0; i < 4; i++) {
            list.add(a);
        }

        return list;
    }


}
