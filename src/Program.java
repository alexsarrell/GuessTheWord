import java.util.*;

public class Program {
    static Operational operational;
    static int maxWordLength = 6;
    static int numberOfMatches = 0;
    static List<List<String>> forbiddenLetters;
    static List<String> exactLetters;
    static List<String> set;
    static Iterator<String> iterator;
    private static void init(){
        forbiddenLetters = new ArrayList<>();
        exactLetters = new ArrayList<>(maxWordLength);
        for(int i = 0; i < maxWordLength; i++){
            forbiddenLetters.add(new ArrayList<>());
            exactLetters.add("");
        }
        operational = (int firstIndex, int lastIndex) -> {
            List<Integer> enumeration = new ArrayList<>();
            for(int i = firstIndex; i < lastIndex; i++)
                enumeration.add(i);
            return enumeration;
        };
    }
    public static void main(String[] args){
        String[] wordList = new String[]{"wichbx", "oahwep", "tpulot", "eqznzs", "vvmplb", "eywinm", "dqefpt", "kmjmxr", "ihkovg", "trbzyb", "xqulhc", "bcsbfw", "rwzslk", "abpjhw", "mpubps", "viyzbc", "kodlta", "ckfzjh", "phuepp", "rokoro", "nxcwmo", "awvqlr", "uooeon", "hhfuzz", "sajxgr", "oxgaix", "fnugyu", "lkxwru", "mhtrvb", "xxonmg", "tqxlbr", "euxtzg", "tjwvad", "uslult", "rtjosi", "hsygda", "vyuica", "mbnagm", "uinqur", "pikenp", "szgupv", "qpxmsw", "vunxdn", "jahhfn", "kmbeok", "biywow", "yvgwho", "hwzodo", "loffxk", "xavzqd", "vwzpfe", "uairjw", "itufkt", "kaklud", "jjinfa", "kqbttl", "zocgux", "ucwjig", "meesxb", "uysfyc", "ccoyyo","kdfvtw", "vizxrv", "rpbdjh", "wynohw", "lhqxvx", "kaadty", "dxxwut", "vjtskm", "yrdswc", "byzjxm", "jeomdc", "saevda", "himevi", "ydltnu", "wrrpoc", "khuopg", "ooxarg", "vcvfry", "thaawc", "bssybb", "ajcwbj", "arwfnl", "nafmtm", "xoaumd", "vbejda", "kaefne", "swcrkh", "reeyhj", "vmcwaf", "chxitv", "qkwjna", "vklpkp", "xfnayl", "ktgmfn", "xrmzzm", "fgtuki",  "zcffuv", "srxuus", "pydgmq"
        };
        set = new ArrayList<>(Arrays.asList(wordList));
        Master master = new Master(set, "ccoyyo");
        findSecretWord(wordList, master);
    }
    public static void findSecretWord(String[] wordlist, Master master) {
        init();
        iterator = set.iterator();
        do{
            if(iterator == null || !iterator.hasNext() || numberOfMatches == 11)break;
        }while(guessMaster(master, iterator.next()) != -1 || guessMaster(master, iterator.next()) != 1);
    }


    public static int guessMaster(Master master, String element){
        int countOfExact = 0;
        for (int letterI = 0; letterI < element.length(); letterI++) {
            List<String> lettersColumn = forbiddenLetters.get(letterI);
            if(String.valueOf(element.charAt(letterI)).equals(exactLetters.get(letterI))){
                countOfExact++;
                continue;
            }
            else if(exactLetters.get(letterI) != "" &&
                    !String.valueOf(element.charAt(letterI)).equals(exactLetters.get(letterI))){
                return 0;
            }
            if(lettersColumn != null){
                for (int i = 0; i < lettersColumn.size(); i++) {
                    if (lettersColumn.get(i).equals(String.valueOf(element.charAt(letterI)))) {
                        return 0;
                    }
                }
            }
        }
        int match = master.guess(element);
        numberOfMatches++;
        if(match == element.length()){
            System.out.println("You win! Secret word " + element + " is founded by " + numberOfMatches + " steps");
            iterator = null;
            return 1;
        }
        if (match == 0) {
            banishLetters(operational.getEnumeration(0, element.length()), element);
            return 0;
        }else if(match > countOfExact){
            compareWords(element, match);
        }
        return 0;
    }
    public static void compareWords(String element, int match){
        List<String> toRemove = new ArrayList<>();
        for(String word : set){
            int matches = 0;
            for(int i = 0; i < Math.min(element.length(), word.length()); i++){
                if(word.charAt(i) == element.charAt(i)){
                    matches++;
                }
            }
            if(match != matches){
                toRemove.add(word);
            }
        }
        set.removeAll(toRemove);
        iterator = set.iterator();
    }
    public static void banishLetters(List<Integer> index, String line){
        for(int i : index){
            if(i < line.length() && i < forbiddenLetters.size()){
                forbiddenLetters.get(i).add(String.valueOf(line.charAt(i)));
            }
        }
    }
}
interface Operational{
    List<Integer> getEnumeration(int start, int end);
}