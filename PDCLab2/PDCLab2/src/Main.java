import FoxAlgorithm.FoxMultiplication;
import Shared.Result;
import StripedAlgorithm.StripedMultiplication;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ProofOfWork();
        System.out.println("\n");
        System.out.println("Matrix size tests");
        Testing(100, 4, 10);
        Testing(500, 4, 10);
        Testing(1000, 4, 10);
        Testing(1500, 4, 10);
        Testing(2000, 4, 10);
        Testing(2500, 4, 10);
        Testing(3000, 4, 10);

        System.out.println("\n");
        System.out.println("Threads count tests");
        Testing(3000, 4, 10);
        Testing(3000, 16, 10);
        Testing(3000, 32, 10);
        Testing(3000, 49, 10);
        Testing(3000, 64, 10);
        Testing(3000, 100, 10);
    }

    public static void ProofOfWork() throws InterruptedException {

        System.out.println("Перша матриця");
        double[][] matrix1 = new double[4][4];
        matrix1[0][0] = 1;
        matrix1[0][1] = 8;
        matrix1[0][2] = 4;
        matrix1[0][3] = -5;
        matrix1[1][0] = 2;
        matrix1[1][1] = -4;
        matrix1[1][2] = 2;
        matrix1[1][3] = -2;
        matrix1[2][0] = 6;
        matrix1[2][1] = 1;
        matrix1[2][2] = 1;
        matrix1[2][3] = -1;
        matrix1[3][0] = 7;
        matrix1[3][1] = -6;
        matrix1[3][2] = 5;
        matrix1[3][3] = 5;
        System.out.println(Arrays.deepToString(matrix1).replace("], [","\n")
                .replace("[[","")
                .replace("]]",""));
        System.out.println("\n");
        System.out.println("Друга матриця");
        double[][] matrix2 = new double[4][4];
        matrix2[0][0] = 6;
        matrix2[0][1] = -3;
        matrix2[0][2] = 6;
        matrix2[0][3] = -1;
        matrix2[1][0] = 7;
        matrix2[1][1] = -6;
        matrix2[1][2] = 3;
        matrix2[1][3] = -2;
        matrix2[2][0] = 9;
        matrix2[2][1] = 3;
        matrix2[2][2] = -6;
        matrix2[2][3] = -1;
        matrix2[3][0] = 1;
        matrix2[3][1] = 1;
        matrix2[3][2] = -5;
        matrix2[3][3] = 6;
        System.out.println(Arrays.deepToString(matrix2).replace("], [","\n")
                .replace("[[","")
                .replace("]]",""));
        StripedMultiplication stripedMult = new StripedMultiplication();
        FoxMultiplication foxMult = new FoxMultiplication();
        Result x = stripedMult.Multiply(matrix1, matrix2, 4);
        Result y = foxMult.Multiply(matrix1, matrix2, 4);
        System.out.println("\n");
        System.out.println("Результат множення стрічковим алгоритмом");
        x.Print();
        System.out.println("\n");
        System.out.println("Результат множення алгоритмом Фокса");
        y.Print();
    }

    public static void Testing(int matrixDimension, int threadCount, int testRepetition) throws InterruptedException {
        StripedMultiplication stripedMult = new StripedMultiplication();
        FoxMultiplication foxMult = new FoxMultiplication();
        double[][] a = createRandomMatrix(matrixDimension, 1, 50);
        double[][] b = createRandomMatrix(matrixDimension, 1, 50);
        double[] stripedTime = new double[testRepetition];
        double[] foxTime = new double[testRepetition];
        for(int i = 0; i < testRepetition; i++){
            long start = System.currentTimeMillis();
            stripedMult.Multiply(a, b, threadCount);
            long end = System.currentTimeMillis();
            stripedTime[i] = convertToSeconds(start, end);
        }
        for(int i = 0; i < testRepetition; i++){
            long start = System.currentTimeMillis();
            foxMult.Multiply(a, b, threadCount);
            long end = System.currentTimeMillis();
            foxTime[i] = convertToSeconds(start, end);
        }
        double averageStriped = 0;
        if (stripedTime.length > 0)
        {
            double sum = 0;
            for (double v : stripedTime) {
                sum += v;
            }
            averageStriped = sum / stripedTime.length;
        }
        double averageFox = 0;
        if (foxTime.length > 0)
        {
            double sum = 0;
            for (double v : foxTime) {
                sum += v;
            }
            averageFox = sum / foxTime.length;
        }
        System.out.println("Matrix dimension: " + matrixDimension + ". Thread count: " + threadCount);
        System.out.println("Striped average time: " + averageStriped + ". Repetition count " + testRepetition);
        System.out.println("Fox average time: " + averageFox + ". Repetition count " + testRepetition);
        System.out.println("\n");
    }

    public static double[][] createRandomMatrix(int matrixDimension, int from, int to)
    {
        Random random = new Random();
        double[][] data = new double[matrixDimension][matrixDimension];
        for (int i = 0; i < matrixDimension; i++)
        {
            data[i] = new double[matrixDimension];
            for (int j = 0; j < matrixDimension; j++)
            {
                data[i][j] = random.nextInt(to - from) + from;
            }
        }
        return data;
    }

    private static double convertToSeconds(long start, long end)
    {
        return (end - start) / 1000.0;
    }
}
