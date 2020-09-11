import java.util.ArrayList;
import java.util.List;

public class WordChecker {
    private final WordList wordList;
    private List<String> suggestions;
    private final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     * Constructor that initializes a new WordChecker with a given WordList.
     *
     * @param wordList Initial word list to check against.
     * @see WordList
     */
    public WordChecker(WordList wordList) {
        this.wordList = wordList;
    }


    /**
     * Returns true if the given word is in the WordList passed to the
     * constructor, false otherwise.
     *
     * @param word Word to chack against the internal word list
     * @return bollean indicating if the word was found or not.
     */
    public boolean wordExists(String word) {
        return this.wordList.lookup(word);
    }


    /**
     * Returns an ArrayList of Strings containing the suggestions for the
     * given word.  If there are no suggestions for the given word, an empty
     * ArrayList of Strings (not null!) should be returned.
     *
     * @param word String to check against
     * @return A list of plausible matches
     */
    public List<String> getSuggestions(String word) {
        if (!wordExists(word)) {
            this.suggestions = new ArrayList<>();
            checkSwappingChars(word);
            checkReplacingChars(word);
            checkDeletingChar(word);
            checkInsertingChar(word);
            checkSplitting(word);
        }
        return this.suggestions;
    }

    private void checkSplitting(String word) {
        for (int i = 1; i < word.length() - 1; i++) {
            StringBuilder sb = new StringBuilder(word);
            sb.insert(i, " ");
            String[] possibleWords = sb.toString().split(" ", 2);
            String possibleWord1 = possibleWords[0];
            String possibleWord2 = possibleWords[1];
            if (wordExists(possibleWord1) && wordExists(possibleWord2)) {
                String suggestion = String.join(" ", possibleWords);
                this.suggestions.add(suggestion);
            }
        }
    }

    private void checkInsertingChar(String word) {
        for (int i = 0; i < word.length() + 1; i++) {

            for (char c : this.alphabet) {
                StringBuilder sb = new StringBuilder(word);
                sb.insert(i, c);
                String possibleWord = sb.toString().toUpperCase();
                if (wordExists(possibleWord) && !this.suggestions.contains(possibleWord)) {
                    this.suggestions.add(possibleWord);
                }
            }
        }
    }

    private void checkDeletingChar(String word) {
        for (int i = 0; i < word.length(); i++) {
            StringBuilder sb = new StringBuilder(word);
            sb.deleteCharAt(i);
            String possibleWord = sb.toString().toUpperCase();
            if (wordExists(possibleWord) && !this.suggestions.contains(possibleWord)) {
                this.suggestions.add(possibleWord);
            }
        }
    }

    private void checkReplacingChars(String word) {
        for (int i = 0; i < word.length(); i++) {
            for (char c : this.alphabet) {
                char[] wordArray = word.toCharArray();
                wordArray[i] = c;

                String possibleWord = new String(wordArray).toUpperCase();
                if (wordExists(possibleWord) && !this.suggestions.contains(possibleWord)) {
                    this.suggestions.add(possibleWord);
                }
            }
        }
    }

    private void checkSwappingChars(String word) {
        for (int i = 0; i < word.length() - 1; i++) {
            char[] wordArray = word.toCharArray();
            char temp = wordArray[i];
            wordArray[i] = wordArray[i + 1];
            wordArray[i + 1] = temp;

            String possibleWord = new String(wordArray).toUpperCase();
            if (wordExists(possibleWord) && !this.suggestions.contains(possibleWord)) {
                this.suggestions.add(possibleWord);
            }
        }
    }
}
