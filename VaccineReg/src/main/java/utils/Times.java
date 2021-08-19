package utils;

import java.util.ArrayList;
import java.util.List;

public class Times {
    public static List<String> generate(){
        List<String> result = new ArrayList<>();
        StringBuilder str = new StringBuilder("00:00:00");

        for(int i=9; i<18; i++){
            if (i==9) str.setCharAt(1, String.valueOf(i).charAt(0));
            else str.replace(0,2, String.valueOf(i));
            for(int j=0; j<6; j++){
                str.setCharAt(3, String.valueOf(j).charAt(0));
                result.add(str.toString());
            }
        }
        return result;
    }
}
