package app;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main {
    static int passed=0;
    static int failed=0;
    static int found=0;
    static List<String> classes = new ArrayList<>();
    public static Class<?> load(String name, URLClassLoader urlClassLoader, File file){
        Class<?> temp=null;
        try {
            temp=Class.forName(name,true,urlClassLoader);
        } catch(NoClassDefFoundError e){
            String name1 = e.getMessage().split(": ")[1]
                    .replace(")","")
                    .replace("/","\\");
            File file1 = new File(file.getAbsolutePath().replace(name1+".class",""));
            try {
                URL url = file1.getAbsoluteFile().toURI().toURL();
                name1 = name1.replace("\\",".");
                urlClassLoader = new URLClassLoader(new URL[]{url});
                Class.forName(name1,true,urlClassLoader);
                temp = Class.forName(name1,true,urlClassLoader);
            } catch (MalformedURLException | ClassNotFoundException malformedURLException) {
                malformedURLException.printStackTrace();
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }
    public static void invoke1(Class<?> clazz){
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods){
            if(method.isAnnotationPresent(Test.class)){
                int modifiers = method.getModifiers();
                if(Modifier.isStatic(modifiers)) {
                    Parameter[] parameters = method.getParameters();
                    if(parameters.length==0) {
                        try {
                            found++;
                            System.out.println("Running "+method.getName()+"...");
                            method.invoke(null);
                            passed++;
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        catch (InvocationTargetException e){
                            System.out.printf("Test %s failed: %s %n", method, e.getCause());
                            classes.add(method.toString());
                            failed++;
                        }
                    }
                }
            }
        }
    }
    public static void invoke2(Class<?> clazz){
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods){
            if(method.isAnnotationPresent(Test.class)) {
                Class<?>[] parameters = method.getParameterTypes();
                Object[] objs = new Object[parameters.length];
                int i = 0;
                for (Class<?> parameter : parameters) {
                    if (parameter.equals(int.class)) {
                        objs[i] = (int) (Math.random() * 100);
                        i++;
                    }
                    if (parameter.equals(String.class)) {
                        objs[i] = "string";
                        i++;
                    }
                    if (!parameter.equals(int.class) && !parameter.equals(String.class))
                        break;
                }
                if (i != parameters.length)
                    continue;
                try{
                    int modifiers = clazz.getModifiers();
                    if (Modifier.isStatic(modifiers)) {
                        found++;
                        System.out.println("Running "+method.getName()+"...");
                        method.invoke(objs);
                        passed++;
                    } else {
                        found++;
                        System.out.println("Running "+method.getName()+"...");
                        method.invoke(clazz.newInstance(), objs);
                        passed++;
                    }
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
                catch(InvocationTargetException e){
                    System.out.printf("Test %s failed: %s %n", method, e.getCause());
                    classes.add(method.toString());
                    failed++;
                }
            }
        }
    }
    public static void explore(File fil) throws MalformedURLException {
        if(fil.getName().endsWith(".jar")){
            JarFile jar = null;
            try {
                jar = new JarFile(fil);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                for (Enumeration<?> list = jar.entries(); list.hasMoreElements(); ) {
                    JarEntry entry = (JarEntry) list.nextElement();
                    if(entry.getName().endsWith(".class")){
                        String className = entry.getName().split("\\.")[0];
                        className = className.replace('/','.');
                        URLClassLoader cl = URLClassLoader.newInstance(new URL[]{fil.getAbsoluteFile().toURI().toURL()});
                        try {
                            Class<?> clazz = Class.forName(className,true,cl);
//                                invoke1(clazz);
                            if(clazz.isAnnotationPresent(Test.class)){
                                prototype(clazz);
                                int modifiers = clazz.getModifiers();
                                if(Modifier.isPublic(modifiers))
                                    invoke2(clazz);
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (NoClassDefFoundError ignored){}
                    }

                }
            }
            finally {
                try {
                    jar.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(fil.exists()){
            if (fil.isDirectory()) {
                for (File file : Objects.requireNonNull(fil.listFiles())) {
                    explore(file);
                }
            }
            else{
                if(fil.getName().endsWith(".class")){
                    URL url = fil.getAbsoluteFile().getParentFile().toURI().toURL();
                    String className = fil.getName().split("\\.")[0];
                    URLClassLoader cl = URLClassLoader.newInstance(new URL[]{url});
                    Class<?> clazz = load(className,cl,fil);
                    prototype(clazz);
//                    invoke1(clazz);
                    if(clazz.isAnnotationPresent(Test.class)){
                        int modifiers = clazz.getModifiers();
                        if(Modifier.isPublic(modifiers)){
                            invoke2(clazz);

                        }
                    }
                }
            }
        }
    }
    public static void info(Class<?> clazz){
        if(clazz.getPackage()!=null)
            System.out.println("\nPackage: "+clazz.getPackage().getName());
        else{
            System.out.println("\nThe class is not in a package.");
        }
        if(clazz.getDeclaredFields().length!=0)
            System.out.println("\nFields:");
        for(Field field : clazz.getDeclaredFields()){
            System.out.println(field.toString());
        }
        if(clazz.getDeclaredConstructors().length!=0)
            System.out.println("\nConstructors:");
        for(Constructor<?> constructor : clazz.getDeclaredConstructors()){
            System.out.println(constructor.toString());
        }
        if(clazz.getDeclaredMethods().length!=0)
           System.out.println("\nMethods:");
        for(Method method : clazz.getDeclaredMethods()){
            System.out.println(method.toString());
        }
        System.out.println();
    }
    public static void statistics(){
        System.out.println("\nTotal tests: "+found);
        System.out.println("Tests passed: "+passed);
        System.out.println("Tests failed: "+failed);
        if(classes.size()!=0)
            System.out.println("Methods that failed: ");
        for(String str : classes){
            System.out.println(str);
        }
    }
    public static void prototype(Class<?> clazz){
        System.out.println();
        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation annotation : annotations) {
            System.out.println("@" +annotation.annotationType().getSimpleName());
        }
        System.out.print(Modifier.toString(clazz.getModifiers())+ " class "+clazz.getSimpleName());
        if(clazz.getSuperclass()!=null && !clazz.getSuperclass().getSimpleName().equals("Object"))
            System.out.print( " extends " + clazz.getSuperclass().getSimpleName());
        if(clazz.getInterfaces().length!=0){
            System.out.print(" implements ");
            int i=0;
            for(Class<?> clas : clazz.getInterfaces()){
                System.out.print(clas.getSimpleName());
                i++;
                if(i!=clazz.getInterfaces().length)
                    System.out.print(", ");
            }
        }
        System.out.println("{");
        for(Field field : clazz.getFields()){
            annotations = field.getAnnotations();
            for(Annotation annotation : annotations) {
                System.out.println(" @" +annotation.annotationType().getSimpleName()+";");
            }
            System.out.println(" "+Modifier.toString(field.getModifiers()) +" "+
                    field.getType().getSimpleName()+" "+ field.getName());
        }
        for(Constructor<?> constructor : clazz.getDeclaredConstructors()){
            annotations = constructor.getAnnotations();
            for(Annotation annotation : annotations) {
                System.out.println(" @" +annotation.annotationType().getSimpleName()+";");
            }
            System.out.print(" "+Modifier.toString(constructor.getModifiers()) +" "+ clazz.getSimpleName()+"(");
            int i=0;
            for(Parameter parameter: constructor.getParameters()) {
                System.out.print(parameter.getType().getSimpleName());
                i++;
                if(i!=constructor.getParameterCount())
                    System.out.print(", ");
            }

            System.out.println(");");
        }
        for(Method method : clazz.getDeclaredMethods()){
            annotations = method.getAnnotations();
            for(Annotation annotation : annotations) {
                System.out.println(" @" +annotation.annotationType().getSimpleName());
            }
            System.out.print(" "+Modifier.toString(method.getModifiers()) +" "+
                    method.getReturnType().getSimpleName()+" "+ method.getName()+"(");
            int i=0;
            for(Parameter parameter: method.getParameters()) {
                System.out.print(parameter.getType().getSimpleName());
                i++;
                if(i!=method.getParameterCount())
                    System.out.print(", ");
            }
            System.out.println(");");
        }
        System.out.println("}");
        info(clazz);
    }
    public static void main(String[] args) throws MalformedURLException {
        String file;
        if(args.length==0){
            Scanner scanner=new Scanner(System.in);
            System.out.println("Please specify the file/directory/jar: ");
            file=scanner.nextLine();
            scanner.close();
        }
        else{
            file = args[0];
        }
        File file1 = new File(file);
        explore(file1);
        statistics();
    }
}
