package utils;

import java.time.LocalTime;

public class IdGenerator {

    public static long generateId(){
        String time = LocalTime.now().toString();
        time = time.replace(':',' ');
        time = time.replace('.',' ');
        long id = RandomNumber.getRandom();
        for(String str: time.split(" ")){
            id *= Math.pow(10, str.length());
            id += Integer.parseInt(str);
        }
        id = (id*10) + RandomNumber.getRandom();
        return id;
    }

}
