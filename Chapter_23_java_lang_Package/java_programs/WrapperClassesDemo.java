public class WrapperClassesDemo {
    public static void main(String[] args) {
        // Manual boxing (legacy)
        Integer i1 = new Integer(10); // deprecated

        // Auto-boxing
        Integer i2 = 20;
        Double d = 3.14;
        Boolean b = true;
        Character c = 'A';

        // Auto-unboxing
        int primitiveInt = i2;
        double primitiveDouble = d;
        boolean primitiveBool = b;
        char primitiveChar = c;

        System.out.println("Auto-boxed: " + i2);
        System.out.println("Auto-unboxed: " + primitiveInt);

        // Parsing
        String numStr = "100";
        int parsed = Integer.parseInt(numStr);
        double parsedDouble = Double.parseDouble("3.14");
        boolean parsedBool = Boolean.parseBoolean("true");
        System.out.println("Parsed int: " + parsed);
        System.out.println("Parsed double: " + parsedDouble);
        System.out.println("Parsed boolean: " + parsedBool);

        // valueOf
        Integer fromValue = Integer.valueOf("50");
        System.out.println("valueOf: " + fromValue);

        // toString
        String str = Integer.toString(200);
        System.out.println("toString: " + str);

        // Integer caching
        Integer a = 127;
        Integer b2 = 127;
        System.out.println("127 == 127 (cached): " + (a == b2));

        Integer c2 = 128;
        Integer d2 = 128;
        System.out.println("128 == 128 (not cached): " + (c2 == d2));
        System.out.println("128.equals(128): " + c2.equals(d2));

        // NullPointerException risk
        Integer nullInt = null;
        try {
            int x = nullInt; // auto-unboxing null
        } catch (NullPointerException e) {
            System.out.println("NPE on auto-unboxing null");
        }
    }
}
