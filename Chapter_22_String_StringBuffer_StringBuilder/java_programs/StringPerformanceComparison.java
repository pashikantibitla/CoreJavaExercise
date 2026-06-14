public class StringPerformanceComparison {
    public static void main(String[] args) {
        int iterations = 100000;

        // String concatenation
        long startString = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < iterations; i++) {
            s += "a";
        }
        long endString = System.currentTimeMillis();
        System.out.println("String time: " + (endString - startString) + " ms");

        // StringBuffer
        long startBuffer = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append("a");
        }
        long endBuffer = System.currentTimeMillis();
        System.out.println("StringBuffer time: " + (endBuffer - startBuffer) + " ms");

        // StringBuilder
        long startBuilder = System.currentTimeMillis();
        StringBuilder sbuilder = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sbuilder.append("a");
        }
        long endBuilder = System.currentTimeMillis();
        System.out.println("StringBuilder time: " + (endBuilder - startBuilder) + " ms");

        System.out.println("\nConclusion: StringBuilder is fastest for single-threaded use.");
        System.out.println("String is slowest due to immutability.");
    }
}
