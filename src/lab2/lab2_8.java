package lab2;

public class lab2_8 {
    abstract static class Animal {

        private String name;
        private int    age;

        public Animal(String name, int age) {
            this.name = name;
            this.age  = age;
        }

        public String getName() { return name; }
        public int    getAge()  { return age;  }
        public void   setName(String name) { this.name = name; }
        public void   setAge(int age) {
            if (age >= 0) this.age = age;
            else System.out.println("Возраст не может быть отрицательным!");
        }

        public abstract String makeSound();

        public void eat() {
            System.out.println(name + " кушает.");
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() +
                    " { имя='" + name + "', возраст=" + age + " }";
        }
    }

    static class Dog extends Animal {

        private String breed; // порода

        public Dog(String name, int age, String breed) {
            super(name, age);
            this.breed = breed;
        }

        public String getBreed() { return breed; }

        @Override
        public String makeSound() { return "Гав-гав!"; }

        public void fetch() {
            System.out.println(getName() + " приносит мяч!");
        }

        @Override
        public String toString() {
            return super.toString().replace(" }", ", порода='" + breed + "' }");
        }
    }

    static class Cat extends Animal {

        private String furType; // тип шерсти

        public Cat(String name, int age, String furType) {
            super(name, age);
            this.furType = furType;
        }

        public String getFurType() { return furType; }

        @Override
        public String makeSound() { return "Мяу!"; }

        // Уникальный метод
        public void purr() {
            System.out.println(getName() + " мурлычет... Мррр~");
        }

        @Override
        public String toString() {
            return super.toString().replace(" }", ", шерсть='" + furType + "' }");
        }
    }

    static class Bird extends Animal {

        private boolean canFly;  // способность летать
        private String  foodType; // тип корма

        public Bird(String name, int age, boolean canFly, String foodType) {
            super(name, age);
            this.canFly   = canFly;
            this.foodType = foodType;
        }

        public boolean isCanFly()   { return canFly;   }
        public String  getFoodType() { return foodType; }

        @Override
        public String makeSound() { return "Чирик-чирик!"; }

        public void fly() {
            if (canFly) System.out.println(getName() + " летит высоко в небо!");
            else        System.out.println(getName() + " не умеет летать.");
        }

        @Override
        public String toString() {
            return super.toString().replace(" }",
                    ", летает=" + canFly + ", корм='" + foodType + "' }");
        }
    }

    abstract static class Shape {

        private String color;

        public Shape(String color) {
            this.color = color;
        }

        public String getColor() { return color; }
        public void   setColor(String color) { this.color = color; }

        public abstract double getArea();
        public abstract double getPerimeter();
        public abstract String getName();

        public void printInfo() {
            System.out.printf("%-12s | цвет: %-8s | площадь: %7.2f | периметр: %7.2f%n",
                    getName(), color, getArea(), getPerimeter());
        }

        @Override
        public String toString() {
            return getName() + " { цвет='" + color + "' }";
        }
    }

    static class Circle extends Shape {

        private double radius;

        public Circle(String color, double radius) {
            super(color);
            if (radius <= 0) throw new IllegalArgumentException("Радиус должен быть положительным!");
            this.radius = radius;
        }

        public double getRadius() { return radius; }
        public void   setRadius(double radius) {
            if (radius <= 0) throw new IllegalArgumentException("Радиус должен быть положительным!");
            this.radius = radius;
        }

        @Override public double getArea()      { return Math.PI * radius * radius; }
        @Override public double getPerimeter() { return 2 * Math.PI * radius;      }
        @Override public String getName()      { return "Круг";                    }

        @Override
        public String toString() {
            return "Circle { цвет='" + getColor() + "', радиус=" + radius + " }";
        }
    }

    static class Square extends Shape {

        private double side;

        public Square(String color, double side) {
            super(color);
            if (side <= 0) throw new IllegalArgumentException("Сторона должна быть положительной!");
            this.side = side;
        }

        public double getSide() { return side; }
        public void   setSide(double side) {
            if (side <= 0) throw new IllegalArgumentException("Сторона должна быть положительной!");
            this.side = side;
        }

        @Override public double getArea()      { return side * side; }
        @Override public double getPerimeter() { return 4 * side;    }
        @Override public String getName()      { return "Квадрат";   }

        @Override
        public String toString() {
            return "Square { цвет='" + getColor() + "', сторона=" + side + " }";
        }
    }

    static class Triangle extends Shape {

        private double a, b, c;
        private double height; // высота

        public Triangle(String color, double a, double b, double c, double height) {
            super(color);
            if (a <= 0 || b <= 0 || c <= 0)
                throw new IllegalArgumentException("Стороны должны быть положительными!");
            if (a + b <= c || a + c <= b || b + c <= a)
                throw new IllegalArgumentException("Такой треугольник не существует!");
            this.a = a; this.b = b; this.c = c;
            this.height = height;
        }

        public double getHeight() { return height; }

        @Override
        public double getArea() {
            double s = getPerimeter() / 2;
            return Math.sqrt(s * (s - a) * (s - b) * (s - c));
        }

        @Override public double getPerimeter() { return a + b + c;    }
        @Override public String getName()      { return "Треугольник"; }

        @Override
        public String toString() {
            return "Triangle { цвет='" + getColor() +
                    "', a=" + a + ", b=" + b + ", c=" + c +
                    ", высота=" + height + " }";
        }
    }


    public static class Main {

        public static void main(String[] args) {

            System.out.println("╔══════════════════════════════╗");
            System.out.println("║         ЖИВОТНЫЕ             ║");
            System.out.println("╚══════════════════════════════╝");

            Dog  dog  = new Dog("Шарик", 3, "Лабрадор");
            Cat  cat  = new Cat("Мурка", 5, "Длинная");
            Bird bird = new Bird("Кеша", 2, true, "Зерно");
            Bird penguin = new Bird("Пингви", 4, false, "Рыба");

            Animal[] animals = { dog, cat, bird, penguin };

            for (Animal a : animals) {
                System.out.println(a);
                System.out.println("  Звук: " + a.makeSound());
                a.eat();

                if (a instanceof Dog  d) d.fetch();
                if (a instanceof Cat  c) c.purr();
                if (a instanceof Bird b) b.fly();

                System.out.println();
            }

            System.out.println("╔══════════════════════════════╗");
            System.out.println("║    ГЕОМЕТРИЧЕСКИЕ ФИГУРЫ    ║");
            System.out.println("╚══════════════════════════════╝");

            Circle   circle   = new Circle  ("Красный", 5.0);
            Square   square   = new Square  ("Синий",   4.0);
            Triangle triangle = new Triangle("Зелёный", 3.0, 4.0, 5.0, 4.0);

            System.out.println(circle);
            System.out.println(square);
            System.out.println(triangle);
            System.out.println();

            Shape[] shapes = { circle, square, triangle };
            System.out.printf("%-12s | %-8s | %-15s | %-15s%n",
                    "Фигура", "Цвет", "Площадь", "Периметр");
            System.out.println("-".repeat(58));
            for (Shape s : shapes) {
                s.printInfo();
            }

            System.out.println("\n--- Проверка валидации ---");
            try {
                Triangle bad = new Triangle("Жёлтый", 1.0, 2.0, 10.0, 1.0);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
