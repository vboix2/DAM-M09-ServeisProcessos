
package criptografia;

public class Xifrat_Rotacio {
    // Xifrats per desplaçament
    
    private static char rotacio(char c, int n){
        // Rotació de caràcters ASCII
        int codi=(int) c;
        while(n>=26){
            n-=26;
        }
        //Majúscula
        if (codi>64 && codi<91) {
            codi+=n;
            if (codi>90) codi-=26;
            if (codi<65) codi+=26;
        }
        //Minúscula
        if (codi>96 && codi<123) {
            codi+=n;
            if (codi>122) codi-=26;
            if (codi<97) codi+=26;
        }
        return (char) codi;
    }
    
    private static String xifrat(String t, int n) {
        // Xifra el text t amb el desplaçament n
        char[] text=t.toCharArray();
        StringBuilder xifrat=new StringBuilder();

        for (int i=0;i<text.length;i++) {
            xifrat.append(rotacio(text[i],n));
        }
        return xifrat.toString();
    }
    
    public static String Cesar(String text){
        return xifrat(text,3);
    }
    
    public static String desCesar(String text){
        return xifrat(text,-3);
    }
    
    public static String rot13(String text){
        return xifrat(text,13);
    }
    
    public static String desRot13(String text){
        return xifrat(text,-13);
    }
    
    public static String vigenere(String text, String pass){
        // Xifra el text utilitzant els desplaçaments de pass
        char[] t=text.toCharArray();
        char[] p=pass.toLowerCase().toCharArray();
        StringBuilder xifrat=new StringBuilder();
        int j=0;
        for (int i=0;i<t.length;i++){
            if (j>=p.length) j=0;
            xifrat.append(rotacio(t[i],(int)p[j]-96));
            j++;
        }
        return xifrat.toString();
    }
    
    public static String desVigenere(String text, String pass){
        char[] t=text.toCharArray();
        char[] p=pass.toLowerCase().toCharArray();
        StringBuilder xifrat=new StringBuilder();
        int j=0;
        for (int i=0;i<t.length;i++){
            if (j>=p.length) j=0;
            xifrat.append(rotacio(t[i],0-((int)p[j]-96)));
            j++;
        }
        return xifrat.toString();
    }
    
}
