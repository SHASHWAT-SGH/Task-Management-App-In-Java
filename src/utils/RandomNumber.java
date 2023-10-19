package utils;

import java.util.Random;
class RandomNumber {

    public static int getRandom(){
        return getRandom(1,9);
    }
    public static int getRandom(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
