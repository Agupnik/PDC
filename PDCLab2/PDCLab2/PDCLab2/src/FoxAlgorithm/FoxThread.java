package FoxAlgorithm;
import Shared.Result;

public class FoxThread extends Thread {
    private final double[][] firstBlock;
    private final double[][] secondBlock;
    private final Result result;
    private final int i;
    private final int j;
    private final int rows;

    public FoxThread (
            double[][] blockedFirstMatrix,
            double[][] blockedSecondMatrix,
            Result result,
            int i,
            int j
    ) {
        this.firstBlock = blockedFirstMatrix;
        this.secondBlock = blockedSecondMatrix;
        this.result = result;
        this.rows = blockedFirstMatrix.length;
        this.i = i;
        this.j = j;
    }

    @Override
    public void run() {
        for (int k = 0; k < rows; k++) {
            for (int l = 0; l < rows; l++) {
                double val = this.result.GetValue(this.i * rows + k, this.j * rows + l);
                int accumulator = 0;
                for (int m = 0; m < rows; m++) {
                    accumulator += this.firstBlock[k][m] * this.secondBlock[m][l];
                }
                this.result.SetValue(this.i * rows + k, this.j * rows + l, val + accumulator);
            }
        }
    }
}
