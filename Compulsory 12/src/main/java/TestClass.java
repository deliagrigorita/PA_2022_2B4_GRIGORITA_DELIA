import org.junit.Test;

import java.lang.annotation.Annotation;

//sunt definite metodele de testare aplicate asupra unei clase
//implementeaza interfata creata pentru a putea folosi functionalitatea de testare

public class TestClass implements TestInterface {
    @Test
    public static void metoda1() {
        // TODO document why this method is empty
    }

    @Test
    public void metoda2() {
        throw new RuntimeException("A crapat");
    }

    @Test
    public void metoda3() {
        throw new RuntimeException("Segmentation fault");
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}

//Annotations are used for:
//– Information for the compiler
//– Compile-time and deployment-time processing
//– Runtime processing