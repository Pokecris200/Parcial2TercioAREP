
package co.edu.escuelaing.proxy;

/**
 *
 * @author cristian.forero-m
 */
public class ProxyService {
    public static void main(String... args) {
        
    }
    public static int getPort(){
        if (System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }
}
