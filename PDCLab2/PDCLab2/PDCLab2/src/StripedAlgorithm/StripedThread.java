package StripedAlgorithm;
import Shared.Result;

public class StripedThread extends Thread{
    private final double[][] rowsMatrix;
    private final double[][] secondMatrix;
    private final Result result;
    private final int rows;
    private final int size;
    private final int i;
    private final int step;

    public StripedThread (
            double[][] firstMatrix,
            double[][] secondMatrix,
            Result resultMatrix,
            int i,
            int step
    ) {
        this.rowsMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.result = resultMatrix;
        this.i = i;
        this.step = step;
        this.rows = firstMatrix.length;
        this.size = secondMatrix.length;
    }

    @Override
    public void run() {
        for (int j = 0; j < rows; j++) {
            for (int k = 0; k < size; k++) {
                double accumulator = 0;
                for (int l = 0; l < size; l++) {
                    accumulator += this.rowsMatrix[j][l] * this.secondMatrix[l][k];
                }
                this.result.SetValue(this.i * this.step + j, k, accumulator);
            }
        }
    }
}
