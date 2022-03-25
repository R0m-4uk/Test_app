package com.project;
import java.lang.Object;

public class Test {
    public static void main(String[] args) {
        int i = 1;
        Object obj = i;
        ++i;
        //Object obj2 = (short) obj;
        System.out.println(i);
        System.out.println(obj);
        //System.out.println(obj2);
        System.out.println((short)((int) obj));
    }
}
