-- PostgreSQL Schema for PeacefulPagesSanctuary
-- All SQL Server syntax removed
-- No triggers
-- No stored procedures

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE customer (
    cus_id BIGSERIAL PRIMARY KEY,
    cus_name VARCHAR(256),
    cus_email VARCHAR(256) UNIQUE,
    cus_phone VARCHAR(10),
    cus_username VARCHAR(50) UNIQUE,
    cus_password VARCHAR(255),
    cus_group VARCHAR(50) DEFAULT 'Silver',
    is_verified BOOLEAN DEFAULT FALSE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    total_spent NUMERIC(15,2) DEFAULT 0
);

CREATE TABLE supplier (
    supply_id BIGSERIAL PRIMARY KEY,
    supply_name VARCHAR(256)
);

CREATE TABLE catalogue (
    cat_id BIGSERIAL PRIMARY KEY,
    cat_name VARCHAR(256)
);

CREATE TABLE products (
    prod_id BIGSERIAL PRIMARY KEY,
    prod_name VARCHAR(256),
    prod_received INTEGER NOT NULL,
    prod_sold INTEGER NOT NULL DEFAULT 0,
    prod_price NUMERIC(15,2) NOT NULL,
    prod_discount NUMERIC(5,2) DEFAULT 0,
    cat_id BIGINT REFERENCES catalogue(cat_id),
    supply_id BIGINT REFERENCES supplier(supply_id),
    prod_description TEXT
);

CREATE TABLE coupon (
    coupon_code VARCHAR(50) PRIMARY KEY,
    discount_type VARCHAR(10) CHECK (discount_type IN ('percentage','fixed')),
    discount_value NUMERIC(15,2) CHECK (discount_value >= 0),
    min_order_value NUMERIC(15,2) DEFAULT 0 CHECK (min_order_value >= 0),
    start_date DATE,
    end_date DATE,
    usage_limit INTEGER,
    used_count INTEGER DEFAULT 0,
    status VARCHAR(10) CHECK (status IN ('active','inactive')) DEFAULT 'active',
    customer_group VARCHAR(50)
);

CREATE TABLE coupon_ship (
    couponship_code VARCHAR(50) PRIMARY KEY,
    discount_type VARCHAR(10) CHECK (discount_type IN ('percentage','fixed')),
    discount_value NUMERIC(15,2) CHECK (discount_value >= 0),
    min_order_value NUMERIC(15,2) DEFAULT 0 CHECK (min_order_value >= 0),
    start_date DATE,
    end_date DATE,
    usage_limit INTEGER,
    used_count INTEGER DEFAULT 0,
    status VARCHAR(10) CHECK (status IN ('active','inactive')) DEFAULT 'active',
    customer_group VARCHAR(50)
);

CREATE TABLE orders (
    orders_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    orders_date DATE DEFAULT CURRENT_DATE,
    cus_id BIGINT REFERENCES customer(cus_id),
    orders_status VARCHAR(20) CHECK (orders_status IN ('pending','shipped','completed','cancelled')),
    orders_total NUMERIC(15,2) DEFAULT 0,
    shipping_fee NUMERIC(15,2) DEFAULT 0,
    coupon_code VARCHAR(50) REFERENCES coupon(coupon_code),
    couponship_code VARCHAR(50) REFERENCES coupon_ship(couponship_code)
);

CREATE TABLE order_details (
    ordersdtl_id BIGSERIAL PRIMARY KEY,
    orders_id UUID REFERENCES orders(orders_id) ON DELETE CASCADE,
    prod_id BIGINT REFERENCES products(prod_id),
    quantity INTEGER NOT NULL,
    price NUMERIC(15,2) NOT NULL
);

CREATE TABLE role_admins (
    role_id BIGSERIAL PRIMARY KEY,
    role_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE admins (
    admin_id BIGSERIAL PRIMARY KEY,
    admin_name VARCHAR(256) NOT NULL,
    admin_username VARCHAR(50) UNIQUE NOT NULL,
    admin_password VARCHAR(255) NOT NULL,
    role_id BIGINT REFERENCES role_admins(role_id)
);

CREATE TABLE product_images (
    image_id BIGSERIAL PRIMARY KEY,
    prod_id BIGINT REFERENCES products(prod_id) ON DELETE CASCADE,
    image_data BYTEA,
    is_primary BOOLEAN DEFAULT FALSE
);

CREATE TABLE customer_groups (
    group_id BIGSERIAL PRIMARY KEY,
    group_description VARCHAR(255),
    min_purchase NUMERIC(15,2) DEFAULT 0
);

CREATE TABLE cart_items (
    cartitem_id BIGSERIAL PRIMARY KEY,
    cus_id BIGINT REFERENCES customer(cus_id) ON DELETE CASCADE,
    prod_id BIGINT REFERENCES products(prod_id),
    quantity INTEGER NOT NULL DEFAULT 1,
    CONSTRAINT uq_cus_prod UNIQUE (cus_id, prod_id)
);