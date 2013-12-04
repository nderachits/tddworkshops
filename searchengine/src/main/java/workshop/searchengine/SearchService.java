package workshop.searchengine;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/9/13
 */
public class SearchService {

    private static final int FIRST_SYMBOL_IN_TEXT = 0;
    public SearchIndex parseText(String text) {
        SearchIndex index =  new SearchIndex();

        int letterIndex;
        int wordEnd = FIRST_SYMBOL_IN_TEXT;
        while((letterIndex = findNextLetter(text, wordEnd))
                < text.length()) {
            wordEnd = findNextNonLetter(text, letterIndex);
            String word = text.substring(letterIndex, wordEnd);
            index.addPair(word, letterIndex);
        }

        return index;
    }

    private boolean isCharSeparator(char ch) {
        return Character.isLetterOrDigit(ch) == false && ch!='_';
    }

    private boolean isCharLetter(char ch) {
        return Character.isLetterOrDigit(ch) || ch == '_';
    }

    private int findNextNonLetter(String text, int startingFrom) {
        int i = startingFrom;
        for(; i < text.length(); ++i) {
            if(isCharSeparator(text.charAt(i)) ) {
                break;
            }
        }
        return i;
    }

    private int findNextLetter(String text, int startingFrom) {
        int i = startingFrom;
        for(; i < text.length(); ++i) {
            if(isCharLetter(text.charAt(i)) ) {
                break;
            }
        }
        return i;
    }
}
