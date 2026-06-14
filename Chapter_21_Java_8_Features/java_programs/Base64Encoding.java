import java.util.Base64;

public class Base64Encoding {
    public static void main(String[] args) {
        String original = "Hello, Java 8!";
        System.out.println("Original: " + original);

        // Basic encoding
        String encoded = Base64.getEncoder().encodeToString(original.getBytes());
        System.out.println("Encoded: " + encoded);

        // Basic decoding
        byte[] decoded = Base64.getDecoder().decode(encoded);
        String decodedString = new String(decoded);
        System.out.println("Decoded: " + decodedString);

        // URL encoding
        String urlSafe = Base64.getUrlEncoder().encodeToString(original.getBytes());
        System.out.println("URL Encoded: " + urlSafe);

        // MIME encoding
        String mimeSafe = Base64.getMimeEncoder().encodeToString(original.getBytes());
        System.out.println("MIME Encoded: " + mimeSafe);

        // Verify round-trip
        String roundTrip = new String(Base64.getDecoder().decode(encoded));
        System.out.println("Round trip: " + roundTrip);
        System.out.println("Match: " + original.equals(roundTrip));
    }
}
