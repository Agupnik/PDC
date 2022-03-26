package FoxAlgorithm;
import Shared.Result;
import java.util.Arrays;

public class FoxMultiplication {
    public int rows, threadsSize;

    public Result Multiply (double[][] firstMatrix, double[][] secondMatrix, int threads) throws InterruptedException {
        this.threadsSize = (int) Math.sqrt(threads);
        this.rows = Math.min(firstMatrix.length, firstMatrix.length/threadsSize);
        double[][][][] blockedFirstMatrix = getBlockedMatrix(firstMatrix);
        double[][][][] blockedSecondMatrix = getBlockedMatrix(secondMatrix);
        FoxThread[][] multiplyThreads = new FoxThread[threadsSize][threadsSize];
        Result result = new Result(firstMatrix.length, firstMatrix.length);
        for (int stage = 0; stage < threadsSize; stage++ ) {
            for (int i = 0; i < threadsSize; i++) {
                for (int j = 0; j < threadsSize; j++) {
                    multiplyThreads[i][j] = new FoxThread(
                            blockedFirstMatrix[i][i + stage >= threadsSize ? i + stage - threadsSize : i + stage],
                            blockedSecondMatrix[i + stage >= threadsSize ? i + stage - threadsSize : i + stage][j],
                            result,
                            i,
                            j
                    );
                    multiplyThreads[i][j].start();
                }
            }
        }
        for (int i = 0; i < threadsSize; i++) {
            for (int j = 0; j < threadsSize; j++) {
                multiplyThreads[i][j].join();
            }
        }
        return result;
    }

    private double[][][][] getBlockedMatrix(double[][] matrix) {
        double[][][][] blockedMatrix = new double[threadsSize][threadsSize][rows][rows];
        for (int i = 0; i < threadsSize; i++) {
            for (int j = 0; j < threadsSize; j++) {
                double[][] block = new double[rows][rows];
                for (int k = 0; k < rows; k++) {
                    int to = i == threadsSize - 1 ? matrix[i * rows + k].length : j * rows + rows;
                    block[k] = Arrays.copyOfRange(matrix[i * rows + k], j * rows, to);
                }
                blockedMatrix[i][j] = block;
            }
        }
        return blockedMatrix;
    }
}