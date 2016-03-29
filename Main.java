package Laba4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {

    private static void printMatrix(ArrayList<ArrayList<Double>> matrix) {
        for (ArrayList<Double> arrayList: matrix) {
            System.out.print("|");
            for (double num: arrayList) {
                System.out.print(num + " |");
            }
            System.out.println();
        }
    }

    private static double possibilityDiagnosis(ArrayList<ArrayList<Double>> matrix, int d, ArrayList<Integer> w) {

        double up;
        double down = 0;

        ArrayList<Double> arrayList = matrix.get(d - 1);
        int len = arrayList.size();

        up = arrayList.get(len - 1);
        for (int i = 0; i < len - 1; i++) {
            if (w.contains(i + 1))
                 up *= arrayList.get(i);
            else
                up *= (1 - arrayList.get(i));
        }

        if (up == 0)
            return 0;

        for (ArrayList<Double> array: matrix) {
            double it = array.get(len - 1);
            for (int i = 0; i < len - 1; i++) {
                if (w.contains(i + 1))
                    it *= array.get(i);
                else
                    it *= (1 - array.get(i));
            }
            down += it;
        }
        return up / down;
    }

    public static void main(String[] args) throws IOException {
        MyFileReader myFileReader = new MyFileReader("laba4_input.txt");
        myFileReader.readDoubleMatrix();
        myFileReader.close();

        ArrayList<ArrayList<Double>> matrix = myFileReader.getDoubleMatrix();

        System.out.println("Діагностична матриця Байєса:");
        printMatrix(matrix);

        System.out.println();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введіть номер стану діагнозу:");
        int d = Integer.parseInt(bufferedReader.readLine());

        System.out.println("Введіть номери ознак, які спостерігаються:");
        ArrayList<Integer> w = new ArrayList<>();
        String line;
        while (!(line = bufferedReader.readLine()).isEmpty()) {
            w.add(Integer.parseInt(line));
        }

        System.out.println("Результат обчислення імовірності діагнозу при наявності конкретних ознак:");
        System.out.println(possibilityDiagnosis(matrix, d, w));

    }

}
