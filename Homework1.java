package com.company;

public class Homework {
    public static void main
            (String[] args)
    {
        if(args.length < 4) {
            System.out.println("Not enough arguments!");
            System.exit(-1);
        }
        Homework obj = new Homework();

        int n = Integer.parseInt(args[0]);
        int p = Integer.parseInt(args[1]);
        int length = 0;
        int alphabetSize = args.length-2;
        char[] alphabet = new char[alphabetSize];

        for(int i = 2; i< args.length;i++)
            alphabet[length++] = args[i].charAt(0);

        System.out.println( n + " " + p + alphabet[0]);

        String[] words = obj.generate(n,p,alphabet);
        for (String word : words) System.out.println(word);

        boolean[][] matrix = new boolean[n][n];

        for(int i = 0; i < words.length;i++)
        {
            for(int j=i+1 ; j<words.length;j++)
            {
                if(obj.neighbours(words[i], words[j]))
                {
                    matrix[i][j]=matrix[j][i]=true;
                    System.out.println(words[i] +" "+words[j]);
                }
            }
        }

        String[][] vecini = new String[n][];
        int k;
        for(int i = 0; i < words.length;i++) {
            k = 0;
            for (int j =0; j < words.length; j++)
                if (matrix[i][j])
                    k++;
            vecini[i]= new String[k];
        }

        for(int i = 0; i < words.length;i++) {
            k = 0;
            for (int j =0; j < words.length; j++) {
                if (matrix[i][j]) {
                    vecini[i][k]=words[j];
                    k++;
                }
            }
        }
        for(int i = 0; i < vecini.length;i++) {
            System.out.println("Vecinii lui " + words[i]);
            for (int j = 0; j < vecini[i].length; j++) {
                System.out.println(vecini[i][j]);
            }
        }
    }

    public String[] generate(int n, int p, char[] alphabet) {
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 1 ; j <= p; j++) {
                int pos = (int)(Math.random() * (alphabet.length + 1)) - 1;
                while(pos < 0) {
                    pos = (int) (Math.random() * (alphabet.length + 1)) - 1;
                }
                sb.append(alphabet[pos]);

            }
            words[i] = sb.toString();
        }
        return words;
    }

    public boolean neighbours(String v1, String v2)
    {
        for(int i = 0; i < v1.length();i++)
        {
            for(int j = 0; j < v2.length();j++)
                if(v1.charAt(i)==v2.charAt(j))
                    return true;
        }
        return false;
    }
}


