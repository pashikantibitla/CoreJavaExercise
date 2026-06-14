package java_programs;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * NestedPOJOExample.java
 * 
 * Purpose: Demonstrates nested POJO structures and their JSON mapping.
 *          Shows how Jackson automatically maps nested JSON objects to nested Java objects.
 * 
 * Classes:
 * - Address: Nested POJO representing an employee's address
 * - EmployeeNested: Parent POJO containing an Address field
 * 
 * @author API Testing Study Guide
 * @version 1.0
 */
public class NestedPOJOExample {
    
    /**
     * Address — nested POJO representing a physical address.
     */
    public static class Address {
        private String street;
        private String city;
        private String zipCode;
        private String country;
        
        public Address() {}
        
        public Address(String street, String city, String zipCode, String country) {
            this.street = street;
            this.city = city;
            this.zipCode = zipCode;
            this.country = country;
        }
        
        public String getStreet() { return street; }
        public void setStreet(String street) { this.street = street; }
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public String getZipCode() { return zipCode; }
        public void setZipCode(String zipCode) { this.zipCode = zipCode; }
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
        
        @Override
        public String toString() {
            return "Address{" +
                    "street='" + street + '\'' +
                    ", city='" + city + '\'' +
                    ", zipCode='" + zipCode + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }
    }
    
    /**
     * EmployeeNested — parent POJO containing nested Address and a list of skills.
     */
    public static class EmployeeNested {
        private int id;
        private String name;
        private double salary;
        private Address address;               // Nested object
        private java.util.List<String> skills; // List of primitives
        
        public EmployeeNested() {}
        
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public double getSalary() { return salary; }
        public void setSalary(double salary) { this.salary = salary; }
        public Address getAddress() { return address; }
        public void setAddress(Address address) { this.address = address; }
        public java.util.List<String> getSkills() { return skills; }
        public void setSkills(java.util.List<String> skills) { this.skills = skills; }
        
        @Override
        public String toString() {
            return "EmployeeNested{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", salary=" + salary +
                    ", address=" + address +
                    ", skills=" + skills +
                    '}';
        }
    }
    
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        
        // ============================================================
        // PART 1: Create nested POJO programmatically
        // ============================================================
        
        System.out.println("=== NESTED POJO CREATION ===\n");
        
        EmployeeNested emp = new EmployeeNested();
        emp.setId(1);
        emp.setName("Alice");
        emp.setSalary(75000.0);
        
        // Create nested Address object
        Address addr = new Address("123 Main St", "New York", "10001", "USA");
        emp.setAddress(addr);
        
        // Create list of skills
        java.util.List<String> skills = new java.util.ArrayList<>();
        skills.add("Java");
        skills.add("REST API Testing");
        skills.add("Selenium");
        emp.setSkills(skills);
        
        System.out.println("Employee: " + emp);
        System.out.println("City: " + emp.getAddress().getCity());
        System.out.println("Skills: " + emp.getSkills());
        
        // ============================================================
        // PART 2: Serialize nested POJO to JSON
        // ============================================================
        
        System.out.println("\n=== SERIALIZE NESTED POJO ===\n");
        
        String json = mapper.writeValueAsString(emp);
        System.out.println("JSON:\n" + mapper.enable(com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT).writeValueAsString(emp));
        
        // ============================================================
        // PART 3: Deserialize nested JSON to POJO
        // ============================================================
        
        System.out.println("\n=== DESERIALIZE NESTED JSON ===\n");
        
        String nestedJson = "{\"id\":2,\"name\":\"Bob\",\"salary\":65000.0," +
                "\"address\":{\"street\":\"456 Oak Ave\",\"city\":\"Boston\",\"zipCode\":\"02101\",\"country\":\"USA\"}," +
                "\"skills\":[\"Python\",\"API Testing\",\"Postman\"]}";
        
        EmployeeNested emp2 = mapper.readValue(nestedJson, EmployeeNested.class);
        
        System.out.println("Deserialized: " + emp2);
        System.out.println("City: " + emp2.getAddress().getCity());
        System.out.println("First Skill: " + emp2.getSkills().get(0));
        
        System.out.println("\n=== Nested POJO Example Complete ===");
    }
}
