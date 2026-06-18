package lab2;

public class lab2_5 {
    public static class Rectangle {

        private double length;
        private double width;

        public Rectangle(double length, double width) {
            setLength(length);
            setWidth(width);
        }

        public double getLength() {
            return length;
        }

        public double getWidth() {
            return width;
        }

        public void setLength(double length) {
            if (length > 0) {
                this.length = length;
            } else {
                System.out.println("Длина должна быть положительным числом!");
            }
        }

        public void setWidth(double width) {
            if (width > 0) {
                this.width = width;
            } else {
                System.out.println("Ширина должна быть положительным числом!");
            }
        }

        public double getArea() {
            return length * width;
        }

        public double getPerimeter() {
            return 2 * (length + width);
        }

        @Override
        public String toString() {
            return "Rectangle { длина=" + length + ", ширина=" + width + " }";
        }

        public static void main(String[] args) {

            Rectangle rect1 = new Rectangle(5.0, 3.0);
            Rectangle rect2 = new Rectangle(10.0, 4.5);

            System.out.println(rect1);
            System.out.println("Площадь:   " + rect1.getArea());
            System.out.println("Периметр:  " + rect1.getPerimeter());

            System.out.println();

            System.out.println(rect2);
            System.out.println("Площадь:   " + rect2.getArea());
            System.out.println("Периметр:  " + rect2.getPerimeter());

            System.out.println();

            rect1.setLength(8.0);
            rect1.setWidth(2.5);
            System.out.println("После изменения: " + rect1);
            System.out.println("Площадь:   " + rect1.getArea());
            System.out.println("Периметр:  " + rect1.getPerimeter());

            System.out.println();

            rect2.setLength(-3.0);
            rect2.setWidth(0);
        }
    }
}
