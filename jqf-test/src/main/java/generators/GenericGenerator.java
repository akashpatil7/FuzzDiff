package generators;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class GenericGenerator extends Generator<Object> {

    //Input to be taken from user
    private static int MAX_INT = Integer.MAX_VALUE;
    private static int MIN_INT = 0;
    private static int ITERATIONS = 10;
    //MIN ITERATION
    //MAX ITERATION
    //MIN STRING LENGTH
    //MAX STRING LENGTH

    public static void setMaxInt(int maxInt) {
        MAX_INT = maxInt;
    }

    public static void setMinInt(int minInt) {
        MIN_INT = minInt;
    }

    public static void setIterations(int iterations) {
        GenericGenerator.ITERATIONS = iterations;
    }

    public static void setStringLength(int stringLength) {
        STRING_LENGTH = stringLength;
    }

    private static int STRING_LENGTH = 5;

    Class<Object> cls;
    public GenericGenerator(Class<Object> cls){
        super(cls);
        this.cls = cls;
    }

    @Override
    public Object generate(SourceOfRandomness random, GenerationStatus __ignore__){
        Class<?> cls = this.cls;

        Object instance = null;
        try {
            if(cls.isPrimitive()){
                return getRandomValueForType(random, cls);
            }
            else if(cls.isArray()){
                return createArrayInstance(cls, random);
            }
            else {
                //Update Constructor based on the Constructor defined for the class
                Constructor<?> c = cls.getDeclaredConstructor(int.class, int.class);
                instance = c.newInstance(getRandomValueForType(random, int.class), getRandomValueForType(random, int.class));
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        Field[] fieldList = cls.getDeclaredFields();
        for (Field field : fieldList) {
            Class<?> type = field.getType();
            field.setAccessible(true);
            try {
                if(ClassUtils.isPrimitiveOrWrapper(type) || type.equals(String.class)){
                    field.set(instance, getRandomValueForType(random, type));
                }
                else if(type.isArray()){
                    Object o = createArrayInstance(type, random);
                    field.set(instance, o);
                }
                else if(Collection.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type)){
                    field.set(instance, Collections.nCopies(ITERATIONS, getRandomValueForType(random, type)));
                }
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
            //System.out.println(type);
        }
        return instance;
    }

    private Object createArrayInstance(Class<?> type, SourceOfRandomness random) {
        Object o = Array.newInstance(type.getComponentType(), ITERATIONS);
        for (int i = 0; i < ITERATIONS; i++) {
            Array.set(o, i, getRandomValueForType(random, type.getComponentType()));
        }
        return o;
    }


    private Object[] getRandomArray(SourceOfRandomness random, Class<?> type) {
        Object[] objArray = (Object[]) Array.newInstance(type);
        for(int i = 0; i < ITERATIONS; i++){
            objArray[i] = getRandomValueForType(random, type);
        }
        return objArray;
    }

    private Object getRandomValueForType(SourceOfRandomness random, Class<?> type){
        if (type.equals(Integer.class) || type.equals(int.class)) {
                return random.nextInt(MIN_INT, MAX_INT);
        } else if(type.equals(Double.class) || type.equals(double.class)) {
                return random.nextDouble(MIN_INT, MAX_INT);
        } else if(type.equals(Float.class) || type.equals(float.class)) {
                return random.nextFloat(MIN_INT, MAX_INT);
        } else if (type.equals(Long.class) || type.equals(long.class)) {
                return random.nextLong(MIN_INT, MAX_INT);
        } else if (type.equals(Short.class) || type.equals(short.class)) {
                return random.nextShort(Short.MIN_VALUE, Short.MAX_VALUE);
        } else if (type.equals(Character.class) || type.equals(char.class)) {
                return random.nextChar(Character.MIN_VALUE, Character.MAX_VALUE);
        } else if(type.equals(String.class) ) {
                return getRandomAlphaNumericString(STRING_LENGTH);
        } else if(type.equals(Boolean.class) || type.equals(boolean.class))
                return random.nextBoolean();
        return null;
    }

    static String getRandomAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int)(AlphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

}
