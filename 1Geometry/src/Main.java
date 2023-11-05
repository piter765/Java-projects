import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Triangle triangle = new Triangle(2,3,4,5);
//        triangle.print();
//
//        Rectangle rectangle = new Rectangle(2,3);
//        rectangle.print();
//
//        Diamond diamond = new Diamond(2, 3,4);
//        diamond.print();


        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("Choose a figure (triangle, rectangle, diamond, prism): (press q to quit)");
            String figure = scanner.nextLine();
            if (figure.equals("q")) return;
            double a, b, c, h;

            switch (figure) {
                case "rectangle":
                    System.out.print("a: ");
                    a = scanner.nextDouble();
                    System.out.print("b: ");
                    b = scanner.nextDouble();

                    scanner.nextLine();

                    Rectangle rec = new Rectangle(a, b);
                    rec.print();
                    break;

                case "triangle":
                    System.out.print("a: ");
                    a = scanner.nextDouble();
                    System.out.print("b: ");
                    b = scanner.nextDouble();
                    System.out.print("c: ");
                    c = scanner.nextDouble();

                    scanner.nextLine();

                    Triangle tri = new Triangle(a, b, c);
                    tri.print();
                    break;

                case "diamond":
                    System.out.print("a: ");
                    a = scanner.nextDouble();
                    System.out.print("h: ");
                    h = scanner.nextDouble();

                    scanner.nextLine();

                    Diamond dia = new Diamond(a, h);
                    dia.print();
                    break;

                case "prism":
                    System.out.println("Provide height of a Prism: ");
                    double H = scanner.nextDouble();

                    System.out.println("Choose a base (triangle, rectangle, diamond): ");

                    scanner.nextLine();
                    String option = scanner.nextLine();

                    if (option.equals("triangle")) {
                        System.out.print("a: ");
                        a = scanner.nextDouble();
                        System.out.print("b: ");
                        b = scanner.nextDouble();
                        System.out.print("c: ");
                        c = scanner.nextDouble();

                        scanner.nextLine();

                        Triangle t = new Triangle(a, b, c);
                        ThreeDim prism = new ThreeDim(t, H);
                        prism.calculateArea();
                        prism.calculateVolume();
                        prism.print();
                    } else if  (option.equals("rectangle")) {
                        System.out.print("a: ");
                        a = scanner.nextDouble();
                        System.out.print("b: ");
                        b = scanner.nextDouble();

                        scanner.nextLine();

                        Rectangle r = new Rectangle(a, b);
                        ThreeDim prism = new ThreeDim(r, H);
                        prism.calculateArea();
                        prism.calculateVolume();
                        prism.print();
                    } else if  (option.equals("diamond")) {
                        System.out.print("a: ");
                        a = scanner.nextDouble();
                        System.out.print("h: ");
                        h = scanner.nextDouble();

                        scanner.nextLine();

                        Diamond d = new Diamond(a, h);
                        ThreeDim prism = new ThreeDim(d, H);
                        prism.calculateArea();
                        prism.calculateVolume();
                        prism.print();
                    } else {
                        System.out.println("Invalid base input. Try again.");
                    }
                break;

                default:
                    System.out.println("Invalid input. Try again.");
            }

        }
    }
}
