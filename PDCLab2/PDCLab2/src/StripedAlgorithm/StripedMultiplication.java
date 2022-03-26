package StripedAlgorithm;
import Shared.Result;
import java.util.Arrays;

public class StripedMultiplication {
    public Result Multiply (double[][] firstMatrix, double[][] secondMatrix, int threads) throws InterruptedException {
        Result result = new Result(firstMatrix.length, firstMatrix.length);
        int rows = (int)Math.ceil((double)firstMatrix.length / threads);
        StripedThread[] multiplyThreads = new StripedThread[threads];
        for (int i = 0; i < threads; i++) {
            int to = Math.min(i * rows + rows, firstMatrix.length);
            double[][] rowsMatrix = Arrays.copyOfRange(firstMatrix, i * rows, to);
            multiplyThreads[i] = new StripedThread(
                    rowsMatrix,
                    secondMatrix,
                    result,
                    i,
                    rows
            );
            multiplyThreads[i].start();
        }
        for (int i = 0; i < threads; i++) {
            multiplyThreads[i].join();
        }
        return result;
    }
}
