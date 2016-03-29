package Laba4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ihor on 28.02.2016.
 */
public class MyFileReader {

    private String path;
    private FileReader fileReader;
    private BufferedReader bufferedReader;

    private ArrayList<ArrayList<Double>> doubleMatrix = new ArrayList<>();

    public ArrayList<ArrayList<Double>> getDoubleMatrix() {
        return doubleMatrix;
    }

    public MyFileReader(String path) {
        this.path = path;

        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            System.out.println("Помилка! Файл не знайдено!");
        }

        bufferedReader = new BufferedReader(fileReader);

    }

    public void readDoubleMatrix() {
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitLine = line.trim().split(" ");
                ArrayList<Double> doubleArray = new ArrayList<>();
                for (String s: splitLine) {
                    doubleArray.add(Double.parseDouble(s));
                }
                doubleMatrix.add(doubleArray);
            }
        } catch (IOException e) {
            System.out.println("Помилка зчитування!");
        } catch (Exception e) {
            System.out.println("Помилка при обробці зчитаних даних!");
        }
    }

    public void close() {
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPath() {
        return path;
    }

}
