        PeacefulPagesSanctuary/
        │
        ├── backend/                      # Spring Boot Backend
        │   │
        │   ├── pom.xml
        │   │
        │   └── src/
        │       └── main/
        │           ├── java/
        │           │   └── peaceful/
        │           │       │
        │           │       ├── PeacefulApplication.java
        │           │       │
        │           │       ├── config/                # Security & configuration
        │           │       │   ├── SecurityConfig.java
        │           │       │   ├── JwtFilter.java
        │           │       │   ├── JwtUtil.java
        │           │       │   └── CorsConfig.java
        │           │       │
        │           │       ├── controller/            # REST Controllers
        │           │       │   ├── AuthController.java
        │           │       │   ├── ProductController.java
        │           │       │   ├── CartController.java
        │           │       │   ├── OrderController.java
        │           │       │   └── CouponController.java
        │           │       │
        │           │       ├── service/               # Business Logic Layer
        │           │       │   ├── AuthService.java
        │           │       │   ├── ProductService.java
        │           │       │   ├── CartService.java
        │           │       │   ├── OrderService.java
        │           │       │   └── CouponService.java
        │           │       │
        │           │       ├── repository/            # Data Access Layer (JPA)
        │           │       │   ├── CustomerRepository.java
        │           │       │   ├── ProductRepository.java
        │           │       │   ├── CartRepository.java
        │           │       │   ├── OrderRepository.java
        │           │       │   └── CouponRepository.java
        │           │       │
        │           │       ├── model/                 # Entity Models
        │           │       │   ├── Customer.java
        │           │       │   ├── Product.java
        │           │       │   ├── CartItem.java
        │           │       │   ├── Order.java
        │           │       │   ├── OrderDetail.java
        │           │       │   └── Coupon.java
        │           │       │
        │           │       └── dto/                   # Data Transfer Objects
        │           │           ├── LoginRequest.java
        │           │           ├── RegisterRequest.java
        │           │           └── CheckoutRequest.java
        │           │
        │           └── resources/
        │               ├── application.yml
        │               └── static/
        │
        ├── frontend/                     # React + Vite Frontend
        │   │
        │   ├── public/
        │   │
        │   ├── src/
        │   │   ├── assets/               # Images, styles, static files
        │   │   ├── components/           # Reusable UI components
        │   │   ├── pages/                # Page-level components
        │   │   ├── services/             # API calls
        │   │   ├── hooks/                # Custom React hooks
        │   │   ├── context/              # Global state (Auth, Cart, etc.)
        │   │   ├── layouts/              # Layout wrappers
        │   │   ├── routes/               # Route definitions
        │   │   ├── utils/                # Helper functions
        │   │   │
        │   │   ├── App.jsx
        │   │   └── main.jsx
        │   │
        │   ├── .env
        │   ├── package.json
        │   └── vite.config.js
        │
        └── README.md
