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

        boolean charMatched = false;
        for (int i = 0; i < matrix.length; i++) {
            if(wordSource.charAt(i) == wordTarget.charAt(0)) {
                charMatched = true;
            }
            matrix[i][0] = charMatched ? i : i + 1;
        }

        charMatched = false;
        for (int j = 1; j < matrix[0].length; j++) {
            if(wordSource.charAt(0) == wordTarget.charAt(j)) {
                charMatched = true;
            }
            matrix[0][j] = charMatched ? j : j + 1;
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                matrix[i][j] = calculateDiffNumberForCell(i, j);
            }
        }

        return matrix[matrix.length-1][matrix[0].length-1];
    }

    private int calculateDiffNumberForCell(int i, int j) {
        int changesToMatch;

        changesToMatch = Math.min(matrix[i][j-1], matrix[i-1][j]);
        changesToMatch = Math.min(changesToMatch, matrix[i-1][j-1]);

        if(wordSource.charAt(i) != wordTarget.charAt(j)) {
            changesToMatch++;
        }

        return changesToMatch;
    }
}
