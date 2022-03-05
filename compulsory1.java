package com.company;

public class Compulsory1 {
    public static void main(String[] args) {
        Main object = new Main();
        object.run();
    }

    private void run() {
        int a;
        System.out.println("Hello World");
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int k = (int) (Math.random() * 1_000_000);
        System.out.println(k);
        k = (k + 3 + 0b10101 + 0xFF)*6;
        while(k>9)
        {
            a = 0;
            while(k != 0)
            {
                a = a + k % 10;
                k = k / 10;
            }
            k = a;
        }
        System.out.println(k);
        System.out.println("Willy-nilly, this semester I will learn " + languages[k]);
    }

}
