package com.boram.account.model;

import java.util.Arrays;

public class codingTest {

    public static void main(String[] args) {

        String [] phone_book = new String[3];
        phone_book[0] = "119";
        phone_book[1] = "97674223";
        phone_book[2] = "1195524421";
        Arrays.sort(phone_book);

        for(int i=0; i < phone_book.length-1 ; i++){
            if (phone_book[i+1].startsWith(phone_book[i])){
                System.out.println("false");
                return;
            }
        }
        System.out.println("true");

    }
}
