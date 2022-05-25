import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Am creat interfata TestInterface (nu contine metode, insa creaza bean-urile pentru clasa de testare)

//bean = clase care încapsulează multe obiecte într-un singur obiect
//Vom folosi această adnotare pentru a marca metode.
// Metodele marcate vor fi supuse testării.

@Retention(RetentionPolicy.RUNTIME)  // CLASS, SOURCE (Indicates how long annotations with the annotated type are to be retained. If no Retention annotation is present on an annotation type declaration, the retention policy defaults to RetentionPolicy.)
@Target(ElementType.METHOD)  ////CONSTRUCTOR, FIELD, METHOD, PACKAGE, PARAMETER, TYPE
public @interface TestInterface {
}