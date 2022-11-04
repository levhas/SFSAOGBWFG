import java.util.*;
import java.util.regex.Pattern;

public class Solver {

    private final List<String> wordList;
    List<Letter> letters = new ArrayList<>();
    private Stack<Letter> currentWordStack;
    private int[] indexes = new int[9];
    private String tempWord;
    private LinkedList<String> wordsFound;

    public Solver(String[] letters, List wordList){
        this.wordList = wordList;
        Arrays.stream(letters).forEachOrdered(k -> this.letters.add(new Letter(k)));
        this.letters.forEach(kirjain -> kirjain.setNeighbours(4));
        currentWordStack = new Stack<>();
        tempWord = "";
        wordsFound = new LinkedList<>();
    }

    /**
     * Tries to find all possible words from the word list
     * @return LinkedList<String> including found words
     */
    public LinkedList<String> solve(){
        int startIndex = 0;
        currentWordStack.add(letters.get(startIndex));
        startIndex++;
        tempWord = tempWord + currentWordStack.peek().get();
        while(!currentWordStack.isEmpty()){


            while(isPartOfWord(tempWord)) {
                if(tempWord.length() != currentWordStack.size()){

                    System.out.println("rikki" + " " + tempWord);
                    currentWordStack.forEach(System.out::print);
                    System.out.println();
                }
                var nextIndex = currentWordStack.peek().getNextIndex();
                if (nextIndex == -1) {

                    break;
                }
                if (!currentWordStack.contains(letters.get(nextIndex))) {
                    currentWordStack.add(letters.get(nextIndex));
                    tempWord = tempWord + currentWordStack.peek().get();
                }
                if(isWord(tempWord) && tempWord.length() > 2 && !wordsFound.contains(tempWord)){
                    wordsFound.add(tempWord);

                }


            }
            currentWordStack.pop();
            if(currentWordStack.isEmpty() && startIndex < letters.size()){
                currentWordStack.add(letters.get(startIndex));
                startIndex++;
            }
            tempWord = "";
            currentWordStack.forEach(e -> tempWord += e.get());
        }
        return wordsFound;
    }
    private boolean isWord(String word){
        var p = Pattern.compile(word);
        return wordList.parallelStream().filter(sana -> p.matcher(sana).matches()).toList().size() == 1;
    }
    private boolean isPartOfWord(String currentWordPart){
        currentWordPart = currentWordPart + ".*";
        Pattern p =  Pattern.compile(currentWordPart);
        var partOfWord = wordList.parallelStream().filter(sana -> p.matcher(sana).matches()).toList();
        if(!partOfWord.isEmpty()){
            return true;
        }
        return false;
    }
}
