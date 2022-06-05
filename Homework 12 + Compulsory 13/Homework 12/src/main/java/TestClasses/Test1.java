package TestClasses;

import app.Test;

@Test
public class Test1 {
    @Test
    public void test1(int integer, String string){
        System.out.println(integer+" "+string);
    }
    @Test
    public static void test2(){
        System.out.println("merge");
    }
    @Test
    public void test3(boolean bol){
        System.out.println("ads" + bol);
    }
    @Test public static void m1() { }
    public static void m2() { }
    @Test public static void m3() {
        throw new RuntimeException("Boom");
    }
    public static void m4() { }
    @Test public static void m5() { }
    public static void m6() { }
    @Test public static void m7() {
        throw new RuntimeException("Crash");
    }
    public static void m8() { }
}

