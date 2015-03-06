import java.util.*;

public class RotateImage {
    public static void main(String[] args) {
        RotateImage ri = new RotateImage();
        int[][] matrix = {{1,2}, {3,4}};
        ri.rotate(matrix);
    }

    public void rotate(int[][] matrix) {
        for(int i=0; i<matrix.length; i++)
        {    
            for(int j=i+1; j<matrix[0].length; j++)
            {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
                System.out.println("matrix[" + i + "]" + "[" + j + "] " + matrix[i][j]);
                System.out.println("matrix[" + j + "]" + "[" + i + "] " + matrix[j][i]);
                System.out.println();
            }
        }
        for(int i=0; i<matrix.length; i++)
            for(int j=0; j<matrix.length; j++)
                System.out.println(matrix[i][j]);

        for(int i=0; i<matrix.length; i++)
        {
            for(int j=0; j<matrix.length/2; j++)
            {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = tmp;
            }
        }

        for(int i=0; i<matrix.length; i++)
            for(int j=0; j<matrix.length; j++)
                System.out.println(matrix[i][j]);
    }
}