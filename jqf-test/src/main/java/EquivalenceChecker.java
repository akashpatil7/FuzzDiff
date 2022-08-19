import benchmarks.Bess.bessi.Eq.newV;
import generators.GenericGenerator;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EquivalenceChecker {

    private static final File destFilePath = new File("src/main/java/classes/");
    private static final File destGeneratorPath = new File("src/main/java/generators/");

    public static String original = null;
    public static String refactored = null;
    public static String methodToBeFuzzed = null;

    public static int parameterCount = 0;

    public static void main(String[] args) throws Exception {

        List<String> argsList = Arrays.asList(args);

        if(argsList.size() != 3){
            throw new Exception("Invalid number of arguments");
        }

        if(argsList.get(0) != null && argsList.get(1) != null && argsList.get(2) != null) {
            System.out.println("Original class: "+argsList.get(0));
            System.out.println("Refactored class: "+argsList.get(1));
            System.out.println("Method to be tested: "+argsList.get(2));
            original = argsList.get(0);
            refactored = argsList.get(1);
            methodToBeFuzzed = argsList.get(2);

            Class<?> c1 = null, c2 = null;
            try{
               c1 = Class.forName("classes."+argsList.get(0));
               c2 = Class.forName("classes."+argsList.get(1));
            }
            catch(ClassNotFoundException e){
                System.out.println("CLASS_NOT_FOUND");
                System.exit(0);
            }

            Method[] m1 = c1.getDeclaredMethods();
            Method[] m2 = c2.getDeclaredMethods();
            if(!Arrays.stream(m1).map(Method::getName).collect(Collectors.toList()).contains(methodToBeFuzzed) ||
                    !Arrays.stream(m2).map(Method::getName).collect(Collectors.toList()).contains(methodToBeFuzzed)) {
                System.out.println("METHOD_NOT_FOUND");
                System.exit(0);
                //throw new Exception("Method not found");
            }
            else{
                Method m = Arrays.stream(m1).filter(e -> e.getName().equals(methodToBeFuzzed))
                        .findFirst().get();
                parameterCount = m.getParameterCount();
            }
        }
        System.out.println("------Properties set---------");


//        if(argsList.get(0).endsWith(".java") && argsList.get(1).endsWith(".java")) {
//
////            File sourceFile1 = new File(argsList.get(0));
////            File sourceFile2 = new File(argsList.get(1));
////            FileUtils.copyFileToDirectory(sourceFile1, destFilePath);
////            FileUtils.copyFileToDirectory(sourceFile2, destFilePath);
//        }
//        else{
//            throw new Exception("Invalid class paths");
//        }


        //Set other parameters to the generator class
        setOtherConfigurationFromArguments(argsList);

        //After copying target classes, change package names to the new path
        //refactorPackageNames(argsList);

        //Generate Test class for the two test programs
        //List<Class<?>> inputObjects = new ArrayList<>();
        //inputObjects.add(Person.class);

        //List<Class<?>> generatorClasses = new ArrayList<>();

        //Class<?> outputClass = null;

        //String testClassName = generateTestClass(inputObjects, generatorClasses, outputClass);

        System.out.println("\n------------Starting fuzzing---------------");

        Process p = null;
        try{
            if(parameterCount != 0) {
                //TODO update path to maven installation directory for your machine
                p = Runtime.getRuntime().
                        exec("C:/Maven/apache-maven-3.8.3/bin/mvn.cmd jqf:fuzz -Dclass=ProgramEquivalenceTest -Dmethod=fuzzTestForEquivalence -Dtime=10s -Doriginal=" + original + " -Drefactored=" + refactored + " -DmethodName=" + methodToBeFuzzed);
            }
            else{
                //TODO update path to maven installation directory for your machine
                p = Runtime.getRuntime().
                        exec("C:/Maven/apache-maven-3.8.3/bin/mvn.cmd test -Dtest=ProgramEquivalenceTest#fuzzTestForEquivalenceWithNoArgs -Doriginal=" + original + " -Drefactored=" + refactored + " -DmethodName=" + methodToBeFuzzed);
            }
        } catch(IOException e){
            System.err.println("Error on exec() method");
            e.printStackTrace();
        }

        String op = copy(p.getInputStream());
        System.out.println(op);
        p.waitFor();
        System.out.println("Fuzzing Complete.......");

        if (op.contains("BUILD SUCCESS")) {
            System.out.println("NO FAILURES");
        } else if (op.contains("BUILD FAILURE")) {
            System.out.println("ASSERTION FAILURES");
        }

        //------------------------------------------------------------------------------------------------
//        Process p2 = null;
//        System.out.println("Checking for Equivalence......");
//        try{
//            p2 = Runtime.getRuntime().
//                    exec("C:/Maven/apache-maven-3.8.3/bin/mvn.cmd test -Dtest=AdditionalChecksTest");
//
//        } catch(IOException e){
//            System.err.println("Error on exec() method");
//            e.printStackTrace();
//        }
//
//        String output = copy(p2.getInputStream());
//
//        if(output.contains("BUILD SUCCESS")){
//            System.out.println("RESULT: The two programs are equivalent");
//        }
//        else{
//            System.out.println("RESULT: The two programs are not equivalent");
//            System.out.println(output);
//        }

    }

    static String copy(InputStream in) throws IOException {
        String sysout = "";
        while (true) {
            int c = in.read();
            if (c == -1)
                break;
            //out.write((char) c);
            sysout = sysout.concat(Character.toString((char) c));
        }
        return sysout;
    }

    private static void setOtherConfigurationFromArguments(List<String> argsList) throws IOException {
        int index;
        if(argsList.contains("-i")){
            index = argsList.indexOf("-i") + 1;
            GenericGenerator.setIterations(Integer.parseInt(argsList.get(index)));
        } else if(argsList.contains("-customGen")) {
            index = argsList.indexOf("-customGen") + 1;
            File customGenerator = new File(argsList.get(index));
            FileUtils.copyFileToDirectory(customGenerator, destFilePath);
        } else if (argsList.contains("-intMax")) {
            index = argsList.indexOf("-intMax") + 1;
            GenericGenerator.setMaxInt(Integer.parseInt(argsList.get(index)));
        } else if (argsList.contains("-intMin")) {
            index = argsList.indexOf("-intMin") + 1;
            GenericGenerator.setMinInt(Integer.parseInt(argsList.get(index)));
        } else if (argsList.contains("-stringLength")) {
            index = argsList.indexOf("-stringLength") + 1;
            GenericGenerator.setStringLength(Integer.parseInt(argsList.get(index)));
        }
    }

}
