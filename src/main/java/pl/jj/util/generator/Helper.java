package pl.jj.util.generator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author JNartowicz
 */
public class Helper {

    private static final Random random = new Random();

    private Helper() throws ClassNotFoundException {
        throw new ClassNotFoundException();
    }



    public static final Set<String> prepareRandomStrings(int elementsQuantity, int elLength){
        Set<String> strings = new HashSet<>();
        while (true){
            String s = getRandomChatSequence(elLength);
            if(!strings.contains(s)){
                strings.add(s);
            }
            if(strings.size() >= elementsQuantity){
                break;
            }
        }
        return strings;
    }

    private static final String getRandomChatSequence(int c){
        Alphabet[] alphabets = Alphabet.values();
        int alphabetCount = alphabets.length;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<c; i++){
            Alphabet letter = alphabets[getRandomIntByMax(alphabetCount - 1)];
            stringBuilder.append(letter.name().toLowerCase());
        }
        return stringBuilder.toString();
    }

    public static final int getRandomInt(int max){
        return getRandomIntByMax(max);
    }

    private static final int getRandomIntByMax(int max){
        return  Math.abs(random.nextInt()) % (max + 1);
    }

    private static final boolean randomBoolean(){
        if((Math.abs(random.nextInt()) % 2) == 0){
            return true;
        } else {
            return false;
        }
    }

}
