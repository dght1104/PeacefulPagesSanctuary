# PeacefulPagesSanctuary
    
    backend/
    │
    ├── pom.xml
    │
    └── src/
        └── main/
            ├── java/
            │   └── peaceful/
            │       ├── PeacefulApplication.java
            │       │
            │       ├── config/
            │       │   ├── SecurityConfig.java
            │       │   ├── JwtFilter.java
            │       │   ├── JwtUtil.java
            │       │   └── CorsConfig.java
            │       │
            │       ├── controller/
            │       │   ├── AuthController.java
            │       │   ├── ProductController.java
            │       │   ├── CartController.java
            │       │   ├── OrderController.java
            │       │   └── CouponController.java
            │       │
            │       ├── service/
            │       │   ├── AuthService.java
            │       │   ├── ProductService.java
            │       │   ├── CartService.java
            │       │   ├── OrderService.java
            │       │   └── CouponService.java
            │       │
            │       ├── repository/
            │       │   ├── CustomerRepository.java
            │       │   ├── ProductRepository.java
            │       │   ├── CartRepository.java
            │       │   ├── OrderRepository.java
            │       │   └── CouponRepository.java
            │       │
            │       ├── model/
            │       │   ├── Customer.java
            │       │   ├── Product.java
            │       │   ├── CartItem.java
            │       │   ├── Order.java
            │       │   ├── OrderDetail.java
            │       │   └── Coupon.java
            │       │
            │       └── dto/
            │           ├── LoginRequest.java
            │           ├── RegisterRequest.java
            │           └── CheckoutRequest.java
            │
            └── resources/
                ├── application.yml
                └── (static/)
    
