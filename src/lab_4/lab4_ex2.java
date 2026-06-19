package lab_4;

public class lab4_ex2 {
    public static void main(String[] args) {
        try {
            System.out.println("0");
            throw new RuntimeException("Непроверяемая ошибка");
        } catch (Exception e) { // перехват по предку (RuntimeException — потомок Exception)
            System.out.println("2 " + e);
        }
        System.out.println("3");
    }
}
