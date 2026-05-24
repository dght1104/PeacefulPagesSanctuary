System Architecture

Peaceful Pages Sanctuary is a full-stack online bookstore built using a Modular Monolithic Architecture.

Technology Stack
Frontend: React
Backend: Spring Boot
Database: Microsoft SQL Server
Authentication: JWT Authentication
ORM: Spring Data JPA / Hibernate
Architecture Style

The backend is implemented as a single deployable Spring Boot application, organized into domain-based modules:

Authentication
Product Management
Catalogue Management
Shopping Cart
Order Processing
Coupon Management
Admin Management

This architectural style is known as a Modular Monolith, which combines the simplicity of a monolithic application with the maintainability of well-separated modules.

PeacefulPagesSanctuary/
│
├── backend/
│ │
│ ├── pom.xml
│ │
│ └── src/
│ └── main/
│ ├── java/
│ │ └── com/
│ │ └── peacefulpagessanctuary/
│ │ │
│ │ ├── config/
│ │ │ └── SecurityConfig.java
│ │ │
│ │ ├── controller/
│ │ │ ├── AdminController.java
│ │ │ ├── CatalogueController.java
│ │ │ └── ProductController.java
│ │ │
│ │ ├── model/
│ │ │ ├── Admin.java
│ │ │ ├── BaseEntity.java
│ │ │ ├── CartItem.java
│ │ │ ├── Catalogue.java
│ │ │ ├── Coupon.java
│ │ │ ├── Customer.java
│ │ │ ├── CustomerGroup.java
│ │ │ ├── Order.java
│ │ │ ├── OrderDetail.java
│ │ │ ├── Product.java
│ │ │ ├── ProductImage.java
│ │ │ ├── RoleAdmin.java
│ │ │ └── Supplier.java
│ │ │
│ │ ├── enums/
│ │ │ ├── CouponStatus.java
│ │ │ ├── CouponType.java
│ │ │ ├── DiscountType.java
│ │ │ └── OrderStatus.java
│ │ │
│ │ ├── exception/
│ │ │ ├── AccessDeniedException.java
│ │ │ ├── CouponInvalidException.java
│ │ │ ├── GlobalExceptionHandler.java
│ │ │ ├── InsufficientStockException.java
│ │ │ ├── InvalidOperationException.java
│ │ │ └── ResourceNotFoundException.java
│ │ │
│ │ ├── mapper/
│ │ │ ├── AdminMapper.java
│ │ │ ├── AuthMapper.java
│ │ │ ├── CartItemMapper.java
│ │ │ ├── CatalogueMapper.java
│ │ │ ├── CouponMapper.java
│ │ │ ├── CustomerMapper.java
│ │ │ ├── CustomerGroupMapper.java
│ │ │ ├── OrderMapper.java
│ │ │ ├── OrderDetailMapper.java
│ │ │ ├── ProductMapper.java
│ │ │ ├── ProductImageMapper.java
│ │ │ └── SupplierMapper.java
│ │ │  
│ │ ├── dto/
│ │ │ ├── request/
│ │ │ │ ├── admin/
│ │ │ │ │ └── AdminRequest.java
│ │ │ │ │
│ │ │ │ ├── auth/
│ │ │ │ │ ├── LoginRequest.java
│ │ │ │ │ ├── RegisterRequest.java
│ │ │ │ │ ├── RefreshTokenRequest.java
│ │ │ │ │ └── ChangePasswordRequest.java
│ │ │ │ │
│ │ │ │ ├── cart/
│ │ │ │ │ ├── CartItemRequest.java
│ │ │ │ │ ├── AddToCartRequest.java
│ │ │ │ │ └── UpdateCartItemRequest.java
│ │ │ │ │
│ │ │ │ ├── catalogue/
│ │ │ │ │ └── CatalogueRequest.java
│ │ │ │ │
│ │ │ │ ├── coupon/
│ │ │ │ │ └── CouponRequest.java
│ │ │ │ │
│ │ │ │ ├── customer/
│ │ │ │ │ ├── ResetPasswordRequest.java
│ │ │ │ │ ├── ChangeEmailRequest.java
│ │ │ │ │ ├── ForgotPasswordRequest.java
│ │ │ │ │ └── UpdateProfileRequest.java
│ │ │ │ │
│ │ │ │ ├── customergroup/
│ │ │ │ │ └── CustomerGroupRequest.java
│ │ │ │ │
│ │ │ │ ├── order/
│ │ │ │ │ ├── CheckoutRequest.java
│ │ │ │ │ └── UpdateOrderStatusRequest.java
│ │ │ │ │
│ │ │ │ ├── product/
│ │ │ │ │ ├── ProductImageRequest.java
│ │ │ │ │ ├── ProductRequest.java
│ │ │ │ │ └── UpdateProductRequest.java
│ │ │ │ │
│ │ │ │ └── supplier/
│ │ │ │ └── SupplierRequest.java
│ │ │ │
│ │ │ └── response/
│ │ │ ├── admin/
│ │ │ │ └── AdminResponse.java
│ │ │ │
│ │ │ ├── auth/
│ │ │ │ └── JwtResponse.java
│ │ │ │
│ │ │ ├── cart/
│ │ │ │ ├── CartItemResponse.java
│ │ │ │ └── CartResponse.java
│ │ │ │
│ │ │ ├── catalogue/
│ │ │ │ └── CatalogueResponse.java
│ │ │ │
│ │ │ ├─ common/
│ │ │ │ ├── ApiResponse.java
│ │ │ │ ├── ErrorResponse.java
│ │ │ │ └── PaginationResponse.java
│ │ │ │
│ │ │ ├── coupon/
│ │ │ │ └── CouponResponse.java
│ │ │ │
│ │ │ ├── customer/
│ │ │ │ ├── CustomerSummaryResponse.java
│ │ │ │ └── CustomerResponse.java
│ │ │ │
│ │ │ ├── customergroup/
│ │ │ │ └── CustomerGroupResponse.java
│ │ │ │
│ │ │ ├── order/
│ │ │ │ ├── OrderDetailResponse.java
│ │ │ │ └── OrderResponse.java
│ │ │ │
│ │ │ ├── product/
│ │ │ │ ├── ProductResponse.java
│ │ │ │ ├── ProductImageResponse.java
│ │ │ │ └── ProductSummaryResponse.java
│ │ │ │
│ │ │ └── supplier/
│ │ │ └── SupplierResponse.java
│ │ │
│ │ ├── repository/
│ │ │ ├── AdminRepository.java
│ │ │ ├── CartItemRepository.java
│ │ │ ├── CatalogueRepository.java
│ │ │ ├── CouponRepository.java
│ │ │ ├── CustomerGroupRepository.java
│ │ │ ├── CustomerRepository.java
│ │ │ ├── OrderDetailRepository.java
│ │ │ ├── OrderRepository.java
│ │ │ ├── ProductImageRepository.java
│ │ │ ├── ProductRepository.java
│ │ │ ├── RoleAdminRepository.java
│ │ │ └── SupplierRepository.java
│ │ │
│ │ ├── security/
│ │ │ ├── JwtAuthenticationEntryPoint.java
│ │ │ ├── JwtAuthenticationFilter.java
│ │ │ └── JwtUtils.java
│ │ │
│ │ ├── service/
│ │ │ ├── AdminService.java
│ │ │ ├── CartService.java
│ │ │ └── ProductService.java
│ │ │
│ │ └── PeacefulPagesSanctuaryApplication.java
│ │
│ └── resources/
│ ├── application.yml
│ └── schema.sql
│
├── frontend/
│ │
│ ├── src/
│ │ ├── api/
│ │ │ └── axios.js
│ │ │
│ │ ├── context/
│ │ │ └── AuthContext.jsx
│ │ │
│ │ ├── pages/
│ │ │ ├── CartPage.jsx
│ │ │ ├── CheckoutPage.jsx
│ │ │ ├── OrderHistoryPage.jsx
│ │ │ └── ProductList.jsx
│ │ │
│ │ ├── App.jsx
│ │ └── main.jsx
│ │
│ ├── .env
│ ├── index.html
│ ├── package.json
│ └── vite.config.js
│
├── .gitignore
│
└── README.md

cd frontend

npm install

npm run dev

npm run build

npx serve -s build

https://peacefulpagessanctuary-fe.onrender.com

cd backend
.\set-env.ps1
mvn clean install
java -jar target\backend-0.0.1-SNAPSHOT.jar

4. Service Interface
5. Service Implementation
6. Controller
7. Test API
