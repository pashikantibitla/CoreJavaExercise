public class EnumWithConstructor {
    enum Status {
        PENDING(1, "Waiting for approval"),
        APPROVED(2, "Request approved"),
        REJECTED(3, "Request rejected");

        private final int code;
        private final String description;

        Status(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    public static void main(String[] args) {
        for (Status s : Status.values()) {
            System.out.println(s.name() + " -> Code: " + s.getCode() +
                    ", Description: " + s.getDescription());
        }
    }
}
