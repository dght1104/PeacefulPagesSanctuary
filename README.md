        PeacefulPagesSanctuary/
        │
        ├── backend/                      # Spring Boot Backend
        │   │
        │   ├── pom.xml
        │   │
        │   └── src/
        │       └── main/
        │           └── java/
        │            	├── com/
        │            	│	└── peacefulpagessanctuary/
        │        		│			├── PeacefulApplication.java
        │    	        │           │
        │            	│           ├── config/                
        │               │           │   └── SecurityConfig.java
        │            	│           │
        │            	│           ├── controller/            
        │        		│           │   ├── AdminController.java
        │            	│           │   ├── AuthController.java
        │        		│           │   ├── CartController.java
        │            	│           │   ├── OrderController.java
        │            	│           │   └── ProductController.java
		│				│           │
        │              	│          	├── model/                                          
        │              	│           │   ├── Admin.java
        │              	│           │   ├── BaseEntity.java
        │              	│           │   ├── CartItem.java
        │              	│           │   ├── Catalogue.java
        │              	│           │   ├── Coupon.java
        │              	│           │   ├── CouponShip.java
        │              	│           │   ├── Customer.java
        │               │       	│   ├── CustomerGroup.java
        │               │       	│   ├── Order.java
        │               │			│   ├── OrderDetail.java
        │               │          	│   ├── Product.java
        │               │          	│   ├── ProductImage.java
        │               │          	│   ├──  RoleAdmin.java
        │               │          	│   └── Supplier.java
		│				│           │	
        │               │          	├── exception/               
        │               │          	│   ├── AccessDeniedException.java
        │               │          	│   ├── CouponInvalidException.java
        │               │          	│   ├── GlobalExceptionHandler.java
        │               │          	│   ├── InsufficientStockException.java
        │               │          	│   ├── InvalidOperationException.java
        │               │          	│   └── ResourceNotFoundException.java
		│				│          	│
        │               │          	├── payload/               
        │               │          	│   └── ApiResponse.java
		│ 				│		   	│
        │               │          	├── repository/          
        │               │          	│   ├── AdminRepository.java                
        │               │          	│   ├── CartItemRepository.java 
        │               │          	│   ├── CatalogueRepository.java 
        │               │          	│   ├── CouponRepository.java 
        │               │          	│   ├── CouponShipRepository.java
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
        │               │          	└── service/               # Business Logic Layer
        │               │         		├── AdminService.java
        │               │            	├── CartService.java
        │               │             	├── CouponService.java
        │               │           	├── CustomerService.java
        │               │             	├── OrderService.java
        │               │             	└── ProductService.java
		│	        	│
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
