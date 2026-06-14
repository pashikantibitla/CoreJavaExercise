public class StringInterviewProblems {
    public static void main(String[] args) {
        // Problem 1: Check if two strings are anagrams
        String a = "listen";
        String b = "silent";
        boolean isAnagram = isAnagram(a, b);
        System.out.println("Is anagram: " + isAnagram);

        // Problem 2: Reverse a string without using reverse()
        String original = "programming";
        String reversed = reverseString(original);
        System.out.println("Reversed: " + reversed);

        // Problem 3: Check palindrome
        String palindrome = "madam";
        System.out.println("Is palindrome: " + isPalindrome(palindrome));

        // Problem 4: Count vowels and consonants
        String text = "Hello World";
        countVowelsConsonants(text);

        // Problem 5: Remove duplicates
        String withDuplicates = "programming";
        String withoutDuplicates = removeDuplicates(withDuplicates);
        System.out.println("Without duplicates: " + withoutDuplicates);

        // Problem 6: First non-repeating character
        String repeat = "swiss";
        System.out.println("First non-repeating: " + firstNonRepeating(repeat));
    }

    static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        java.util.Arrays.sort(c1);
        java.util.Arrays.sort(c2);
        return java.util.Arrays.equals(c1, c2);
    }

    static String reverseString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    static boolean isPalindrome(String s) {
        return s.equals(reverseString(s));
    }

    static void countVowelsConsonants(String s) {
        int vowels = 0, consonants = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                if ("aeiou".indexOf(c) != -1) vowels++;
                else consonants++;
            }
        }
        System.out.println("Vowels: " + vowels + ", Consonants: " + consonants);
    }

    static String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (sb.indexOf(String.valueOf(c)) == -1) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    static char firstNonRepeating(String s) {
        for (char c : s.toCharArray()) {
            if (s.indexOf(c) == s.lastIndexOf(c)) {
                return c;
            }
        }
        return '_';
    }
}
