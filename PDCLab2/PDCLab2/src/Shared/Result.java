package Shared;

public class Result {
    public double[][] matrix;
    int row;
    int col;
    public Result (int firstWidth, int secondLength) {
        this.row = secondLength;
        this.col = firstWidth;
        this.matrix = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                this.matrix[i][j] = 0;
            }
        }
    }
    public void SetValue(int i, int j, double value){
        this.matrix[i][j] = value;
    }
    public double GetValue(int i, int j){
        return this.matrix[i][j];
    }

    public void Print() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                System.out.print(this.matrix[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}