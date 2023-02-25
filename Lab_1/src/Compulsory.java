public class Compulsory {
    /**
     * Aici avem clasa
     * Avem functia compute ce va realiza suma cifrelor dintr-un numar dat ca parametru
     * Apoi in main rezolvam cerintele date.
     */
    public static int compute(int number) {
        int auxiliar = number;
        int result = 0;
        while (auxiliar / 10 != 0) {
            result = result + (auxiliar % 10);
            auxiliar = auxiliar / 10;
        }
        return result;
    }

    /**
     * In main am rezolvat cerintele date.
     */
    public static void main(String[] args) {

        System.out.println("Hello World!"); //Cerinta nr. 1
        String[] language = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"}; //Cerinta nr. 2
        int n = (int) (Math.random() * 1_000_000); //Cerinta nr. 3
        int sum = (n * 3 + 0b10101 + 0xFF) * 6; // Cerinta nr. 4
        int number = compute(sum);
        while (number / 10 != 0) {
            number = compute(number);
        }//Cerinta nr. 5
        System.out.println("Willy-nilly, this semester I will learn " + language[number]); //Cerinta nr. 6
    }

}