import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Master {
    private String secretWord;
    private List<String> set;
    private int numberOfMatch;
    public Master(List<String> set, String secretWord){
        this.set = set;
        this.secretWord = secretWord;
        if(!set.contains(secretWord))set.add(secretWord);
        init();
    }
    private void init(){
        numberOfMatch = 0;
    }
    public int guess(String guess){
        numberOfMatch++;
        if(!set.contains(guess)) return -1;
        int matches = 0;
        for(int i = 0; i < Math.min(secretWord.length(), guess.length()); i++){
            try{
                if(guess.charAt(i) == secretWord.charAt(i)) matches++;
            }catch (IndexOutOfBoundsException ex){
                System.out.println(guess);
            }
        }
        System.out.println("Guess: " + guess + ", match: " + numberOfMatch + ", matches: " + matches);
        return matches;
    }
}
