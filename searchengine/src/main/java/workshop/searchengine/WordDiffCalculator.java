package workshop.searchengine;

/**
 * User: nike
 * Date: 12/2/13
 */
public class WordDiffCalculator {

    final private String wordSource;
    final private String wordTarget;
    final private int[][] matrix;

    public WordDiffCalculator(String word1, String word2) {
        wordSource = word1;
        wordTarget = word2;
        matrix = new int[wordSource.length()][wordTarget.length()];
    }

    public int calculate() {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = calculateDiffNumberForCell(i, j);
            }
        }

        return matrix[matrix.length-1][matrix[0].length-1];
    }

    private int calculateDiffNumberForCell(int i, int j) {
        int changesToMatch = Math.max(wordSource.length(), wordTarget.length());

        if( i == 0 && j == 0) {
            changesToMatch = 0;
            if(wordSource.charAt(i) != wordTarget.charAt(j)) {
                changesToMatch++;
            }
        }

        if ( j > 0) {
            changesToMatch = matrix[i][j-1] + 1;
        }
        if ( i > 0) {
            changesToMatch = Math.min(changesToMatch, matrix[i-1][j] + 1);
        }

        if( i > 0 && j > 0) {
            int previousByDiagonal = matrix[i-1][j-1];
            if(wordSource.charAt(i) != wordTarget.charAt(j)) {
                previousByDiagonal++;
            }
            changesToMatch = Math.min(changesToMatch, previousByDiagonal);
        }

        return changesToMatch;
    }
}
