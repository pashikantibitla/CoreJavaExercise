/**
 * EcommercePojos.java
 * 
 * All Plain Old Java Objects (POJOs) for the E-Commerce API Testing project.
 * These classes represent the JSON request/response structures for:
 * User Registration, Login, Product, Cart, Order, and Payment entities.
 * 
 * This file demonstrates encapsulation, nested classes, and builders.
 */

import java.util.List;

// ============================================
// 1. USER REGISTRATION POJOs
// ============================================

class UserRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Address address;

    public UserRegistrationRequest() {}

    public UserRegistrationRequest(String firstName, String lastName, String email,
                                    String password, String phone, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public Address getAddress() { return address; }

    // Setters
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(Address address) { this.address = address; }

    @Override
    public String toString() {
        return "UserRegistrationRequest{firstName='" + firstName + "', email='" + email + "'}";
    }

    // Nested Address class
    static class Address {
        private String street;
        private String city;
        private String state;
        private String zipCode;
        private String country;

        public Address() {}

        public Address(String street, String city, String state, String zipCode, String country) {
            this.street = street;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
            this.country = country;
        }

        public String getStreet() { return street; }
        public String getCity() { return city; }
        public String getState() { return state; }
        public String getZipCode() { return zipCode; }
        public String getCountry() { return country; }

        public void setStreet(String street) { this.street = street; }
        public void setCity(String city) { this.city = city; }
        public void setState(String state) { this.state = state; }
        public void setZipCode(String zipCode) { this.zipCode = zipCode; }
        public void setCountry(String country) { this.country = country; }

        @Override
        public String toString() {
            return "Address{city='" + city + "', state='" + state + "'}";
        }
    }
}

class UserRegistrationResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRegistrationRequest.Address address;
    private String createdAt;
    private String status;

    public UserRegistrationResponse() {}

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public UserRegistrationRequest.Address getAddress() { return address; }
    public void setAddress(UserRegistrationRequest.Address address) { this.address = address; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "UserRegistrationResponse{userId='" + userId + "', status='" + status + "'}";
    }
}

// ============================================
// 2. AUTHENTICATION POJOs
// ============================================

class LoginRequest {
    private String email;
    private String password;

    public LoginRequest() {}
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private int expiresIn;
    private UserInfo user;

    public AuthResponse() {}

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }
    public String getTokenType() { return tokenType; }
    public void setTokenType(String tokenType) { this.tokenType = tokenType; }
    public int getExpiresIn() { return expiresIn; }
    public void setExpiresIn(int expiresIn) { this.expiresIn = expiresIn; }
    public UserInfo getUser() { return user; }
    public void setUser(UserInfo user) { this.user = user; }

    @Override
    public String toString() {
        return "AuthResponse{tokenType='" + tokenType + "', expiresIn=" + expiresIn + "}";
    }

    static class UserInfo {
        private String userId;
        private String email;
        private String role;

        public UserInfo() {}
        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }
}

// ============================================
// 3. PRODUCT POJOs
// ============================================

class Product {
    private String productId;
    private String name;
    private String description;
    private String category;
    private double price;
    private String currency;
    private int stockQuantity;
    private double rating;
    private List<String> images;
    private ProductSpecification specifications;

    public Product() {}

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }
    public ProductSpecification getSpecifications() { return specifications; }
    public void setSpecifications(ProductSpecification specifications) { this.specifications = specifications; }

    @Override
    public String toString() {
        return "Product{productId='" + productId + "', name='" + name + "', price=" + price + "}";
    }
}

class ProductSpecification {
    private String batteryLife;
    private String weight;
    private String connectivity;

    public ProductSpecification() {}
    public String getBatteryLife() { return batteryLife; }
    public void setBatteryLife(String batteryLife) { this.batteryLife = batteryLife; }
    public String getWeight() { return weight; }
    public void setWeight(String weight) { this.weight = weight; }
    public String getConnectivity() { return connectivity; }
    public void setConnectivity(String connectivity) { this.connectivity = connectivity; }
}

class ProductPageResponse {
    private List<Product> data;
    private PaginationInfo pagination;
    private Object filters;

    public ProductPageResponse() {}
    public List<Product> getData() { return data; }
    public void setData(List<Product> data) { this.data = data; }
    public PaginationInfo getPagination() { return pagination; }
    public void setPagination(PaginationInfo pagination) { this.pagination = pagination; }
}

class PaginationInfo {
    private int currentPage;
    private int totalPages;
    private int totalItems;
    private int itemsPerPage;
    private boolean hasNextPage;
    private boolean hasPreviousPage;

    public PaginationInfo() {}
    public int getCurrentPage() { return currentPage; }
    public void setCurrentPage(int currentPage) { this.currentPage = currentPage; }
    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }
    public int getTotalItems() { return totalItems; }
    public void setTotalItems(int totalItems) { this.totalItems = totalItems; }
    public int getItemsPerPage() { return itemsPerPage; }
    public void setItemsPerPage(int itemsPerPage) { this.itemsPerPage = itemsPerPage; }
    public boolean isHasNextPage() { return hasNextPage; }
    public void setHasNextPage(boolean hasNextPage) { this.hasNextPage = hasNextPage; }
    public boolean isHasPreviousPage() { return hasPreviousPage; }
    public void setHasPreviousPage(boolean hasPreviousPage) { this.hasPreviousPage = hasPreviousPage; }
}

// ============================================
// 4. CART POJOs
// ============================================

class CartItemRequest {
    private String productId;
    private int quantity;
    private CartVariant variant;

    public CartItemRequest() {}
    public CartItemRequest(String productId, int quantity, CartVariant variant) {
        this.productId = productId;
        this.quantity = quantity;
        this.variant = variant;
    }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public CartVariant getVariant() { return variant; }
    public void setVariant(CartVariant variant) { this.variant = variant; }
}

class CartVariant {
    private String color;
    private String size;

    public CartVariant() {}
    public CartVariant(String color, String size) {
        this.color = color;
        this.size = size;
    }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
}

class CartItemResponse {
    private String cartItemId;
    private String productId;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private CartVariant variant;
    private String addedAt;

    public CartItemResponse() {}
    public String getCartItemId() { return cartItemId; }
    public void setCartItemId(String cartItemId) { this.cartItemId = cartItemId; }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public CartVariant getVariant() { return variant; }
    public void setVariant(CartVariant variant) { this.variant = variant; }
    public String getAddedAt() { return addedAt; }
    public void setAddedAt(String addedAt) { this.addedAt = addedAt; }
}

class CartSummary {
    private double subtotal;
    private double tax;
    private double shipping;
    private double discount;
    private double total;
    private String currency;

    public CartSummary() {}
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public double getTax() { return tax; }
    public void setTax(double tax) { this.tax = tax; }
    public double getShipping() { return shipping; }
    public void setShipping(double shipping) { this.shipping = shipping; }
    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}

class CartResponse {
    private String cartId;
    private String userId;
    private List<CartItemResponse> items;
    private CartSummary summary;
    private int itemCount;
    private String updatedAt;

    public CartResponse() {}
    public String getCartId() { return cartId; }
    public void setCartId(String cartId) { this.cartId = cartId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public List<CartItemResponse> getItems() { return items; }
    public void setItems(List<CartItemResponse> items) { this.items = items; }
    public CartSummary getSummary() { return summary; }
    public void setSummary(CartSummary summary) { this.summary = summary; }
    public int getItemCount() { return itemCount; }
    public void setItemCount(int itemCount) { this.itemCount = itemCount; }
    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}

// ============================================
// 5. ORDER POJOs
// ============================================

class OrderRequest {
    private Address shippingAddress;
    private Address billingAddress;
    private String paymentMethod;
    private String couponCode;
    private String notes;

    public OrderRequest() {}
    public Address getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(Address shippingAddress) { this.shippingAddress = shippingAddress; }
    public Address getBillingAddress() { return billingAddress; }
    public void setBillingAddress(Address billingAddress) { this.billingAddress = billingAddress; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getCouponCode() { return couponCode; }
    public void setCouponCode(String couponCode) { this.couponCode = couponCode; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    static class Address {
        private String street;
        private String city;
        private String state;
        private String zipCode;
        private String country;
        private String phone;

        public Address() {}
        public String getStreet() { return street; }
        public void setStreet(String street) { this.street = street; }
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public String getState() { return state; }
        public void setState(String state) { this.state = state; }
        public String getZipCode() { return zipCode; }
        public void setZipCode(String zipCode) { this.zipCode = zipCode; }
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
    }
}

class OrderPricing {
    private double subtotal;
    private double tax;
    private double shipping;
    private double discount;
    private double total;
    private String currency;

    public OrderPricing() {}
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public double getTax() { return tax; }
    public void setTax(double tax) { this.tax = tax; }
    public double getShipping() { return shipping; }
    public void setShipping(double shipping) { this.shipping = shipping; }
    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}

class OrderPayment {
    private String method;
    private String status;
    private String transactionId;

    public OrderPayment() {}
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
}

class OrderResponse {
    private String orderId;
    private String userId;
    private String status;
    private List<OrderItem> items;
    private OrderRequest.Address shippingAddress;
    private OrderRequest.Address billingAddress;
    private OrderPricing pricing;
    private OrderPayment payment;
    private String createdAt;
    private String estimatedDelivery;

    public OrderResponse() {}
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
    public OrderRequest.Address getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(OrderRequest.Address shippingAddress) { this.shippingAddress = shippingAddress; }
    public OrderRequest.Address getBillingAddress() { return billingAddress; }
    public void setBillingAddress(OrderRequest.Address billingAddress) { this.billingAddress = billingAddress; }
    public OrderPricing getPricing() { return pricing; }
    public void setPricing(OrderPricing pricing) { this.pricing = pricing; }
    public OrderPayment getPayment() { return payment; }
    public void setPayment(OrderPayment payment) { this.payment = payment; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public String getEstimatedDelivery() { return estimatedDelivery; }
    public void setEstimatedDelivery(String estimatedDelivery) { this.estimatedDelivery = estimatedDelivery; }
}

class OrderItem {
    private String productId;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private CartVariant variant;

    public OrderItem() {}
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public CartVariant getVariant() { return variant; }
    public void setVariant(CartVariant variant) { this.variant = variant; }
}

// ============================================
// 6. PAYMENT POJOs
// ============================================

class PaymentRequest {
    private String orderId;
    private String paymentMethod;
    private CardDetails cardDetails;
    private double amount;
    private String currency;

    public PaymentRequest() {}
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public CardDetails getCardDetails() { return cardDetails; }
    public void setCardDetails(CardDetails cardDetails) { this.cardDetails = cardDetails; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    static class CardDetails {
        private String cardNumber;
        private String cardHolder;
        private String expiryMonth;
        private String expiryYear;
        private String cvv;

        public CardDetails() {}
        public String getCardNumber() { return cardNumber; }
        public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
        public String getCardHolder() { return cardHolder; }
        public void setCardHolder(String cardHolder) { this.cardHolder = cardHolder; }
        public String getExpiryMonth() { return expiryMonth; }
        public void setExpiryMonth(String expiryMonth) { this.expiryMonth = expiryMonth; }
        public String getExpiryYear() { return expiryYear; }
        public void setExpiryYear(String expiryYear) { this.expiryYear = expiryYear; }
        public String getCvv() { return cvv; }
        public void setCvv(String cvv) { this.cvv = cvv; }
    }
}

class PaymentResponse {
    private String transactionId;
    private String orderId;
    private String status;
    private double amount;
    private String currency;
    private String paymentMethod;
    private String timestamp;
    private String receiptUrl;

    public PaymentResponse() {}
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public String getReceiptUrl() { return receiptUrl; }
    public void setReceiptUrl(String receiptUrl) { this.receiptUrl = receiptUrl; }
}

// ============================================
// 7. ERROR RESPONSE POJO
// ============================================

class ErrorResponse {
    private ErrorDetails error;

    public ErrorResponse() {}
    public ErrorDetails getError() { return error; }
    public void setError(ErrorDetails error) { this.error = error; }

    static class ErrorDetails {
        private String code;
        private String message;
        private List<ValidationError> details;
        private String timestamp;
        private String requestId;

        public ErrorDetails() {}
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public List<ValidationError> getDetails() { return details; }
        public void setDetails(List<ValidationError> details) { this.details = details; }
        public String getTimestamp() { return timestamp; }
        public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
        public String getRequestId() { return requestId; }
        public void setRequestId(String requestId) { this.requestId = requestId; }
    }

    static class ValidationError {
        private String field;
        private String message;
        private String rejectedValue;

        public ValidationError() {}
        public String getField() { return field; }
        public void setField(String field) { this.field = field; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getRejectedValue() { return rejectedValue; }
        public void setRejectedValue(String rejectedValue) { this.rejectedValue = rejectedValue; }
    }
}

// ============================================
// DEMO MAIN METHOD
// ============================================

public class EcommercePojos {
    public static void main(String[] args) {
        // Demonstrate creating POJOs
        UserRegistrationRequest.Address address = new UserRegistrationRequest.Address(
            "42, MG Road", "Bangalore", "Karnataka", "560001", "India"
        );

        UserRegistrationRequest user = new UserRegistrationRequest(
            "Rahul", "Sharma", "rahul@example.com",
            "SecurePass123!", "+91-9876543210", address
        );

        System.out.println("=== E-Commerce POJOs Demo ===");
        System.out.println("User: " + user);
        System.out.println("Address: " + address);

        // Demonstrate nested objects
        Product p = new Product();
        p.setProductId("prod_12345");
        p.setName("Wireless Headphones");
        p.setPrice(2499.00);
        p.setCategory("ELECTRONICS");
        System.out.println("Product: " + p);

        CartItemRequest cartItem = new CartItemRequest("prod_12345", 2, new CartVariant("Black", "Standard"));
        System.out.println("CartItem: productId=" + cartItem.getProductId() + ", qty=" + cartItem.getQuantity());

        System.out.println("\nAll POJOs created successfully. These represent the JSON structures used in E-Commerce API testing.");
    }
}
