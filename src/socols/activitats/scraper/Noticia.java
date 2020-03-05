
package socols.activitats.scraper;

class Noticia {

     private String titol;
     private String autor;
     private String data;


    public Noticia(String titol, String autor, String data) {
       this.titol = titol;
       this.autor = autor;
       this.data = data;
    }

    public String getTitol() {
        return titol;
    }
    
    @Override
    public String toString() {
        return "Títol: " + titol + "\nAutor: " + autor + "\nData: " + data +"\n";
    }
    
}