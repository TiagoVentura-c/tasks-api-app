package expertostech.autenticacao.jwt;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Ex {
    public static void main(String[] args) {



        System.out.println(LocalDateTime.now());
        Scanner in = new Scanner(System.in);
        int qnt = in.nextInt();

        for (int i = 0; i < qnt; i++) {
            String s = in.nextLine();
            encriptar(s);
        }

    }

    public static void encriptar(String s){

        char ch;
        String newString ="";
        for (int i = 0; i < s.length(); i++){
            int value = (int) s.charAt(i);

            if((value >= 65 && value <=90) || (value >= 97 && value <= 122)){
                value+=3;
                ch = (char) value;
                newString+= ch;
            }else
                newString+= s.charAt(i);
        }

        s="";

        for (int i = newString.length()-1; i >= 0; i-- ){
            s+=newString.charAt(i);
        }

        char sc[] = new char[s.length()];
        sc = s.toCharArray();

        for (int i = (s.length()/2); i < s.length(); i++){
            int value = (int) s.charAt(i);

                value-=1;
                ch = (char) value;
                sc[i] = ch;
        }


        for (int i = 0; i < s.length(); i++) {
            System.out.print(sc[i]);
        }

    }
}
