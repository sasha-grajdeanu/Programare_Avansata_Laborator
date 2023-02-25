public class Homework {

    /**
     * functie de afisare a liniilor sau a coloanelor ca string-uri (in functie de valaorea booleana
     * din sense) intr-o matrice data ca parametru
     * prin aceasta functie rezolvam cerinta nr. 3
     */
    public static void printMatrix(int[][] latinMatrix, boolean sense, int n) {

        for (int i = 0; i < n; i++) {
            StringBuffer lines = new StringBuffer();
            for (int j = 0; j < n; j++) {
                //caz linii
                if (sense == true) {
                    lines.append(latinMatrix[i][j]);
                }
                //caz coloane
                else {
                    lines.append(latinMatrix[j][i]);
                }
                lines.append(' ');
            }
            //tot pentru cerinta nr. 4
            if (n <= 30000) {
                System.out.println(lines);
            }
        }
    }

    /**
     * In main rezolvam cerintele date
     */
    public static void main(String[] args) {

        long startExecution = System.currentTimeMillis();
        //cerinta nr. 1
        int n = Integer.parseInt(args[0]);
        //cerinta nr. 2
        int[][] latinSquare = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                latinSquare[i][j] = ((i + j) % n) + 1;
            }
        }
        //cerinta nr. 3
        printMatrix(latinSquare, true, n);
        System.out.println("SPACE PLEASE");
        printMatrix(latinSquare, false, n);
        //cerinta nr. 4
        long finishExecution = System.currentTimeMillis();
        System.out.println(finishExecution - startExecution);
    }
}