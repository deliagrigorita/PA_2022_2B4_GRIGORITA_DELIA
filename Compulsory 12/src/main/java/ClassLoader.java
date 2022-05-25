import java.net.URL;
import java.net.URLClassLoader;

//Această clasă este folosită pentru a încărca clase și resurse dintr-o cale de căutare a adreselor URL care se referă la ambele Fișiere și directoare JAR.
//getUrls() returnează calea de căutare a URL-urilor pentru încărcarea claselor și resurselor.
// Aceasta include lista originală a URL-urile specificate pentru constructor, împreună cu oricare URL-uri adăugate ulterior de metoda  addURL()
//predefineste functii ajutatoare pentru definirea path-ului clasei ce va i testate
public class ClassLoader extends URLClassLoader {
    public ClassLoader() {
        super(new URL[0], ClassLoader.getSystemClassLoader());
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

}