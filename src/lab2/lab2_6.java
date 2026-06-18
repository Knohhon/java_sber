package lab2;

public class lab2_6 {

    interface Shape {
        double getArea();        // Площадь
        double getPerimeter();   // Периметр
        String getName();        // Название фигуры
    }


    static class Circle implements Shape {

        private double radius;

        public Circle(double radius) {
            if (radius <= 0) throw new IllegalArgumentException("Радиус должен быть положительным!");
            this.radius = radius;
        }

        public double getRadius() { return radius; }
        public void setRadius(double radius) {
            if (radius <= 0) throw new IllegalArgumentException("Радиус должен быть положительным!");
            this.radius = radius;
        }

        @Override
        public double getArea() {
            return Math.PI * radius * radius;
        }

        @Override
        public double getPerimeter() {
            return 2 * Math.PI * radius;
        }

        @Override
        public String getName() { return "Круг"; }

        @Override
        public String toString() {
            return "Circle { радиус=" + radius + " }";
        }
    }

    static class Square implements Shape {

        private double side;

        public Square(double side) {
            if (side <= 0) throw new IllegalArgumentException("Сторона должна быть положительной!");
            this.side = side;
        }

        public double getSide() { return side; }
        public void setSide(double side) {
            if (side <= 0) throw new IllegalArgumentException("Сторона должна быть положительной!");
            this.side = side;
        }

        @Override
        public double getArea() {
            return side * side;
        }

        @Override
        public double getPerimeter() {
            return 4 * side;
        }

        @Override
        public String getName() { return "Квадрат"; }

        @Override
        public String toString() {
            return "Square { сторона=" + side + " }";
        }
    }

    static class Triangle implements Shape {

        private double a, b, c; // стороны

        public Triangle(double a, double b, double c) {
            if (a <= 0 || b <= 0 || c <= 0)
                throw new IllegalArgumentException("Стороны должны быть положительными!");
            if (a + b <= c || a + c <= b || b + c <= a)
                throw new IllegalArgumentException("Такой треугольник не существует!");
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public double getA() { return a; }
        public double getB() { return b; }
        public double getC() { return c; }

        @Override
        public double getArea() {
            // Формула Герона
            double s = getPerimeter() / 2;
            return Math.sqrt(s * (s - a) * (s - b) * (s - c));
        }

        @Override
        public double getPerimeter() {
            return a + b + c;
        }

        @Override
        public String getName() { return "Треугольник"; }

        @Override
        public String toString() {
            return "Triangle { a=" + a + ", b=" + b + ", c=" + c + " }";
        }
    }

    public static class Shapes {

        static void printShapeInfo(Shape shape) {
            System.out.println("Фигура:    " + shape);
            System.out.printf("Площадь:   %.2f%n", shape.getArea());
            System.out.printf("Периметр:  %.2f%n", shape.getPerimeter());
            System.out.println();
        }

        public static void main(String[] args) {

            Circle   circle   = new Circle(5.0);
            Square   square   = new Square(4.0);
            Triangle triangle = new Triangle(3.0, 4.0, 5.0);

            printShapeInfo(circle);
            printShapeInfo(square);
            printShapeInfo(triangle);

            System.out.println("=== Все фигуры через массив ===");
            Shape[] shapes = { circle, square, triangle };
            for (Shape shape : shapes) {
                System.out.printf("%-12s | площадь: %6.2f | периметр: %6.2f%n",
                        shape.getName(), shape.getArea(), shape.getPerimeter());
            }

            System.out.println("\n=== Проверка валидации ===");
            try {
                Triangle invalid = new Triangle(1.0, 2.0, 10.0);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
