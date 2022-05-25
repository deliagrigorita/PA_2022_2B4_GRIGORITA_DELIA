import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException { //când clasa URL încorporată întâlnește o adresă URL nevalidă;
        int Passed = 0, Failed = 0;
        String path = "C:\\Users\\40736\\OneDrive\\Desktop\\PA_anul2\\PA_anul2\\PA_2022_2B4_GRIGORITA_DELIA\\Homework 10 + Compulsory 11\\Compulsory 12\\src\\main\\java\\TestClass";
        ClassLoader classLoader = new ClassLoader();
        // Adding dynmically a JAR to the classpath
        File pathClass = new File(path);////am declarat un path pentru o clasa ce va fi testata
        URL urlClasa = null;
        if (pathClass.exists()) { //am verificat daca intr-adevar exista clasa la acea adresa
            urlClasa = pathClass.toURI().toURL();
        }
        classLoader.addURL(urlClasa);

        //framework
        for (Method metodaInClasa : classLoader.loadClass("TestClass").getMethods()) { //parcurg toate metodele din clasa TestClass
            if (metodaInClasa.isAnnotationPresent(TestClass.class)) {
                try { //aplic metoda curenta pe clasa si in functie de rezultat se va incrementa testPassed sau testFailed
                    metodaInClasa.invoke(null);
                    Passed++;
                } catch (Throwable ex) {
                    System.out.printf("Test %s failed: %s %n", metodaInClasa, ex.getCause());
                    Failed++;
                }
            } //afisez cate teste au fost trecute si cate picate din cele aplicate pe acea clasa.
        }
        System.out.printf("Passed: %d\nFailed %d\n", Passed, Failed);
    }
}