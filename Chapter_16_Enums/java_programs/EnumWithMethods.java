public class EnumWithMethods {
    enum Operation {
        PLUS {
            public double apply(double x, double y) {
                return x + y;
            }
        },
        MINUS {
            public double apply(double x, double y) {
                return x - y;
            }
        },
        TIMES {
            public double apply(double x, double y) {
                return x * y;
            }
        },
        DIVIDE {
            public double apply(double x, double y) {
                return x / y;
            }
        };

        public abstract double apply(double x, double y);

        public static Operation fromSymbol(String symbol) {
            switch (symbol) {
                case "+": return PLUS;
                case "-": return MINUS;
                case "*": return TIMES;
                case "/": return DIVIDE;
                default: throw new IllegalArgumentException("Unknown symbol: " + symbol);
            }
        }
    }

    public static void main(String[] args) {
        double a = 10, b = 5;
        for (Operation op : Operation.values()) {
            System.out.println(a + " " + op.name() + " " + b + " = " + op.apply(a, b));
        }

        Operation parsed = Operation.fromSymbol("*");
        System.out.println("Parsed '*' -> " + parsed + " = " + parsed.apply(a, b));
    }
}
