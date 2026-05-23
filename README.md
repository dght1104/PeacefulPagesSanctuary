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
        │   │
        │   ├── pom.xml
        │   │
        │   └── src/
        │       └── main/
        │           └── java/
        │            	├── com/
        │            	│	└── peacefulpagessanctuary/
        │            	│           ├── config/                
        │               │           │   └── SecurityConfig.java
        │            	│           │
        │            	│           ├── controller/            
        │        		│           │   ├── AdminController.java
        │        		│           │   ├── CatalogueController.java
        │            	│           │   └── ProductController.java
		│				│           │
        │              	│          	├── model/                                          
        │              	│           │   ├── Admin.java
        │              	│           │   ├── BaseEntity.java
        │              	│           │   ├── CartItem.java
        │              	│           │   ├── Catalogue.java
        │              	│           │   ├── Coupon.java
        │              	│           │   ├── Customer.java
        │               │       	│   ├── CustomerGroup.java
        │               │       	│   ├── Order.java
        │               │			│   ├── OrderDetail.java
        │               │          	│   ├── Product.java
        │               │          	│   ├── ProductImage.java
        │               │          	│   ├── RoleAdmin.java
        │               │          	│   └── Supplier.java
        │               │           │
        │               │          	├── enums/               
        │               │          	│   ├── CouponStatus.java
        │               │          	│   ├── CouponType.java
        │               │          	│   ├── DiscountType.java
        │               │          	│   └── OrderStatus.java
        │               │           │
        │               │          	├── exception/               
        │               │          	│   ├── AccessDeniedException.java
        │               │          	│   ├── CouponInvalidException.java
        │               │          	│   ├── GlobalExceptionHandler.java
        │               │          	│   ├── InsufficientStockException.java
        │               │          	│   ├── InvalidOperationException.java
        │               │          	│   └── ResourceNotFoundException.java
		│				│          	│
		│				│          	├── mapper/

		│				│          	├── dto/
		│				│          	│   ├── request/
		│				│          	│   │   ├── auth/
		│				│          	│   │   │   ├── LoginRequest.java
		│				│          	│   │   │   └── RegisterRequest.java
		│				│          	│   │   ├── cart/
		│				│          	│   │   │   └── CartItemRequest.java
		│				│          	│   │   ├── coupon/
		│				│          	│   │   │   └── CouponRequest.java
		│				│          	│   │   ├── order/
		│				│          	│   │   │   └── CheckoutRequest.java
		│				│          	│   │   └── product/
		│				│          	│   │       └── ProductRequest.java
		│				│          	│   │
		│				│          	│   ├── response/
		│				│          	│   │   ├── admin/
		│				│          	│   │   │   └── AdminResponse.java
		│				│          	│   │   ├── auth/
		│				│          	│   │   │   ├── CustomerResponse.java
		│				│          	│   │   │   └── JwtResponse.java
		│				│          	│   │   ├── cart/
		│				│          	│   │   │   ├── CartItemResponse.java
		│				│          	│   │   │   └── CartResponse.java
		│				│          	│   │   ├── catalogue/
		│				│          	│   │   │   └── CatalogueResponse.java
		│				│          	│   │   ├── coupon/
		│				│          	│   │   │   └── CouponResponse.java
		│				│          	│   │   ├── order/
		│				│          	│   │   │   ├── OrderDetailResponse.java
		│				│          	│   │   │   └── OrderResponse.java
		│				│          	│   │   ├── product/
		│				│          	│   │   │   ├── ProductResponse.java
		│				│          	│   │   │   └── ProductSummaryResponse.java
		│				│          	│   │   └── ApiResponse.java
        │               │          	├── repository/          
        │               │          	│   ├── AdminRepository.java                
        │               │          	│   ├── CartItemRepository.java 
        │               │          	│   ├── CatalogueRepository.java 
        │               │          	│   ├── CouponRepository.java 
        │               │          	│   ├── CustomerGroupRepository.java
        │               │          	│   ├── CustomerRepository.java
        │               │          	│   ├── OrderDetailRepository.java
        │               │          	│   ├── OrderRepository.java
        │               │          	│   ├── ProductImageRepository.java
        │               │          	│   ├── ProductRepository.java
        │               │          	│   ├── RoleAdminRepository.java                 
        │               │          	│   └── SupplierRepository.java
		│				│           │
        │               │          	├── security/               
        │               │          	│   ├── JwtAuthenticationEntryPoint.java
        │               │          	│   ├── JwtAuthenticationFilter.java
        │               │          	│   └── JwtUtils.java
		│				│		   	│
        │               │          	├── service/               
        │               │         	│	├── AdminService.java
        │               │           │	├── CartService.java
        │               │           │  	└── ProductService.java
		│	        	│           │
        │               │           └── PeacefulPagesSanctuaryApplication.java
        │               │ 
		│	           	└── resources/
		│	            	├── application.yml
		│	            	└── schema.sql
        │
        ├── frontend/                     
        │   ├── src/
        │   │   ├── api/       
		│   │	│	└── axios.js               
        │   │   ├── context/ 
		│   │	│	└── AuthContext.jsx       
		│   │   ├── pages/  
		│   │	│	├── CartPage.jsx
		│   │	│	├── CheckoutPage.jsx
		│   │	│	├── OrderHistoryPage.jsx
		│   │	│	└── ProductList.jsx 
        │   │   ├── App.jsx
        │   │   └── main.jsx
        │   │
        │   ├── .env
        │   ├── index.html    
        │   ├── package.json
        │   └── vite.config.js
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

1. Repository
2. DTO
3. Mapper
4. Service Interface
5. Service Implementation
6. Controller
7. Test API