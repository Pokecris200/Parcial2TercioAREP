package co.edu.escuelaing.palindromoservice;

import static spark.Spark.*;

/**
 *
 * @author cristian.forero-m
 */
public class PalindromoService {

    public static void main(String[] args) {
        port(getPort());
        get("/palindrome",(req,res)->{
            String s = req.queryParams("input");
            return palindrome(s);
        });
        System.out.println("123".length()/2);
    }
    
    public static String palindrome(String s){
        boolean pal = true;
        for(int i=0;i<(s.length()/2)-1;i++){
            pal = (s.charAt(i)== s.charAt(s.length()-i-1));
        }
        return "{"
                + "\"operation\":\"palindromo\","
                + "\"input\":\"" + s + "\","
                + "\"output\":\""+ (pal? "Si es palindromo" :"No es palindromo") + "\""
                + "}";
    }
            
    public static int getPort(){
        if (System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }
}
