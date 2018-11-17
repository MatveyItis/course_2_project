package ru.itis.maletskov.testing;

public class Main {
    public static void main(String[] args) {
        /*Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int danis = 62;
        int matvey = 79;
        int first;
        int second;
        boolean chose = false;
        while (!s.equals("q")) {
            if (s.equals("")) {
                first = (int)(1 + Math.random() * 6);
                second = (int)(1 + Math.random() * 6);
                System.out.println(first + " & " + second);
                if (chose) {
                    matvey += (first * second);
                } else {
                    danis += (first * second);
                }
                chose = !chose;
            }
            if (s.equals("get")) {
                System.out.println("Danis: " + danis + " & " + "Matvey: " + matvey + " Different: " + (danis - matvey));
            }
            s = sc.nextLine();
        }*/

        String s = "Matvey";
        System.out.println(s.contains("Mat"));
    }
}
