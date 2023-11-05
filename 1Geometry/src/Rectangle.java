import java.util.ArrayList;

public class Rectangle extends Figure {

    private double a;
    private double b;

    public Rectangle(double a, double b) throws IllegalArgumentException {
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException("Invalid input: All values must be non-negative and not equal 0.");
        }
        this.a = a;
        this.b = b;
    }

    @Override
    double calculateArea() {
        return a * b;
    }

    @Override
    double calculatePerimeter() {
        return 2 * a + 2* b;
    }

    @Override
    public void print() {
        System.out.println("Area of a rectangle: " +  this.calculateArea());
        System.out.println("Perimeter of a rectangle: " + this.calculatePerimeter());
    }

    @Override
    public ArrayList getSides() {
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(a);
        list.add(b);
        list.add(a);
        list.add(b);

        return list;
    }


}
