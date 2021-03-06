package utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class HashPassword {
    public static String getHash(String password){
        return Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
    }
}
