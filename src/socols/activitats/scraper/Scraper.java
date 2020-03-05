
package socols.activitats.scraper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class Scraper {
    
    private ArrayList<Noticia> noticies;

    public Scraper(){
        noticies = new ArrayList<>();
    }
    
    private String download(String link){
        System.out.println("Downloading " + link + "...");
        String html = "";
        try {
            URL url = new URL(link);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String linia;
            while((linia=in.readLine())!=null){
                html += linia;
            }
        } catch (Exception ex) {
            System.out.println("Error descarregant el contingut");
        }
        return html;
    }
    
    private void scrape_link(String html){
        Document doc = Jsoup.parse(html);
        Elements ns_article = doc.select("article");
        
        for (Element n_article:ns_article){
            String titol = n_article.select("h2[class='entry-title']").text();
            String autor = n_article.select("span[class='entry-author']").text();
            String data = n_article.select("span[class='entry-date']").text();
            Noticia noticia = new Noticia(titol, autor, data);
            if (!noticia.getTitol().equals("")){
                noticies.add(noticia);
                System.out.println(noticia.toString());
            }
        }
    }
    
    public void scrape(String url, int pagina){
        String html = download(url);
        scrape_link(html);
        
        for (int i=2; i<=pagina; i++){
            try {
                String link = url + "page/" + i + "/";
                html = download(link);
                scrape_link(html);
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                System.out.println("Error d'interrupció");
            }
        }
    }  
}