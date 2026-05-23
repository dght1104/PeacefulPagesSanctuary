-- =========================
-- CUSTOMER GROUPS
-- =========================
CREATE TABLE IF NOT EXISTS customer_groups (
    group_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    group_description VARCHAR(255),
    min_purchase DECIMAL(15,2) DEFAULT 0
);

-- =========================
-- CUSTOMER
-- =========================
CREATE TABLE IF NOT EXISTS customer (
    cus_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cus_name VARCHAR(256),
    cus_email VARCHAR(256) UNIQUE,
    cus_phone VARCHAR(10),
    cus_username VARCHAR(50) UNIQUE,
    cus_password VARCHAR(255),

    group_id BIGINT,

    is_verified BOOLEAN DEFAULT FALSE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    cus_img VARCHAR(256),
    cus_address VARCHAR(256),
    cus_dob DATE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_customer_group
        FOREIGN KEY (group_id)
        REFERENCES customer_groups(group_id)
);

-- =========================
-- SUPPLIER
-- =========================
CREATE TABLE IF NOT EXISTS supplier (
    supply_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    supply_name VARCHAR(256)
);

-- =========================
-- CATALOGUE
-- =========================
CREATE TABLE IF NOT EXISTS catalogue (
    cat_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cat_name VARCHAR(256)
);

-- =========================
-- PRODUCTS
-- =========================
CREATE TABLE IF NOT EXISTS products (
    prod_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    prod_name VARCHAR(256),
    prod_received INT NOT NULL,
    prod_sold INT NOT NULL DEFAULT 0,
    prod_price DECIMAL(15,2) NOT NULL,
    prod_discount DECIMAL(5,2) DEFAULT 0,

    cat_id BIGINT,
    supply_id BIGINT,

    prod_description TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_products_catalogue
        FOREIGN KEY (cat_id) REFERENCES catalogue(cat_id),

    CONSTRAINT fk_products_supplier
        FOREIGN KEY (supply_id) REFERENCES supplier(supply_id)
);

-- =========================
-- COUPON (FIXED - NO coupon_ship)
-- =========================
CREATE TABLE IF NOT EXISTS coupon (
    coupon_code VARCHAR(50) PRIMARY KEY,

    coupon_type ENUM('product','shipping') NOT NULL,

    discount_type ENUM('percentage','fixed') NOT NULL,
    discount_value DECIMAL(15,2) NOT NULL,

    min_order_value DECIMAL(15,2) DEFAULT 0,

    start_date DATE,
    end_date DATE,

    usage_limit INT,
    used_count INT DEFAULT 0,

    status ENUM('active','inactive') DEFAULT 'active',

    group_id BIGINT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_coupon_group
        FOREIGN KEY (group_id)
        REFERENCES customer_groups(group_id)
);

-- =========================
-- ORDERS
-- =========================
CREATE TABLE IF NOT EXISTS orders (
    orders_id BIGINT AUTO_INCREMENT PRIMARY KEY,

    orders_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    cus_id BIGINT NOT NULL,

    orders_status ENUM('pending','shipped','completed','cancelled') DEFAULT 'pending',

    orders_total DECIMAL(15,2) DEFAULT 0,
    shipping_fee DECIMAL(15,2) DEFAULT 0,

    coupon_code VARCHAR(50) NULL,
    couponship_code VARCHAR(50) NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_orders_customer
        FOREIGN KEY (cus_id) REFERENCES customer(cus_id),

    CONSTRAINT fk_orders_coupon
        FOREIGN KEY (coupon_code) REFERENCES coupon(coupon_code),

    CONSTRAINT fk_orders_coupon_ship
        FOREIGN KEY (couponship_code) REFERENCES coupon(coupon_code)
);

-- =========================
-- ORDER DETAILS
-- =========================
CREATE TABLE IF NOT EXISTS order_details (
    ordersdtl_id BIGINT AUTO_INCREMENT PRIMARY KEY,

    orders_id BIGINT NOT NULL,
    prod_id BIGINT NOT NULL,

    quantity INT NOT NULL,
    price DECIMAL(15,2) NOT NULL,

    CONSTRAINT fk_order_details_orders
        FOREIGN KEY (orders_id)
        REFERENCES orders(orders_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_order_details_products
        FOREIGN KEY (prod_id)
        REFERENCES products(prod_id)
);

-- =========================
-- ROLE ADMIN
-- =========================
CREATE TABLE IF NOT EXISTS role_admins (
    role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) UNIQUE NOT NULL
);

-- =========================
-- ADMINS
-- =========================
CREATE TABLE IF NOT EXISTS admins (
    admin_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    admin_name VARCHAR(256) NOT NULL,
    admin_username VARCHAR(50) UNIQUE NOT NULL,
    admin_password VARCHAR(255) NOT NULL,

    role_id BIGINT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_admins_role
        FOREIGN KEY (role_id)
        REFERENCES role_admins(role_id)
);

-- =========================
-- PRODUCT IMAGES
-- =========================
CREATE TABLE IF NOT EXISTS product_images (
    image_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    prod_id BIGINT NOT NULL,

    image_url VARCHAR(512) NOT NULL,
    is_primary BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_product_images_products
        FOREIGN KEY (prod_id)
        REFERENCES products(prod_id)
        ON DELETE CASCADE
);

-- =========================
-- CART ITEMS
-- =========================
CREATE TABLE IF NOT EXISTS cart_items (
    cartitem_id BIGINT AUTO_INCREMENT PRIMARY KEY,

    cus_id BIGINT NOT NULL,
    prod_id BIGINT NOT NULL,

    quantity INT NOT NULL DEFAULT 1,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_cart_items_customer
        FOREIGN KEY (cus_id)
        REFERENCES customer(cus_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_cart_items_products
        FOREIGN KEY (prod_id)
        REFERENCES products(prod_id),

    CONSTRAINT uq_cus_prod UNIQUE (cus_id, prod_id)
);

