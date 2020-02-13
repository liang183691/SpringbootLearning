package org.spring.springboot.example.auth;

public class MainTest {
    public static void main(String[] args) {

        /*String secret = GoogleAuthenticator.generateSecretKey ();
        String url = GoogleAuthenticator.getQRBarcodeURL("testuser", "testhost", secret);
        System.out.println("Please register " + url);
        System.out.println("Secret key is " + secret);*/

        String code = "728093";
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        boolean r = ga.check_code("J5M7QQTBS7ABISTO", code, t);
        System.out.println("Check code = " + r);
    }
}
