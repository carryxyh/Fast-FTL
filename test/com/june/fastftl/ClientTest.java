package com.june.fastftl;

/**
 * Created by june on 2017/1/22.
 */
public class ClientTest {
    public static void main(String[] args) {
        int i = 0;
        while (i++ < 100) {
            new Client().request(8005, "{\"template\":\"test.ftl\",\"data\":{\"a\": \"Hello\"}}");
        }
    }
}