-- =========================
-- CUSTOMER GROUPS
-- =========================
INSERT INTO customer_groups (group_description, min_purchase) VALUES
('New Member', 0),
('Silver', 500000),
('Gold', 2000000);

-- =========================
-- ROLE ADMIN
-- =========================
INSERT INTO role_admins (role_name) VALUES
('ADMIN'),
('STAFF');

-- =========================
-- SUPPLIER
-- =========================
INSERT INTO supplier (supply_name) VALUES
('NXB Tre'),
('NXB Kim Dong'),
('NXB Giao Duc');

-- =========================
-- CATALOGUE
-- =========================
INSERT INTO catalogue (cat_name) VALUES
('Programming'),
('Novel'),
('Education'),
('Science'),
('History'),
('Children'),
('Business'),
('Self Help');


-- =========================
-- CUSTOMER
-- =========================
INSERT INTO customer (cus_name, cus_email, cus_phone, cus_username, cus_password, group_id, is_verified, is_active, cus_img, cus_address, cus_dob)
VALUES
('Nguyen Van A', 'a@gmail.com', '0900000001', 'vana', '123456', 1, true, true, NULL, 'HCM', '2003-10-14'),
('Tran Thi B', 'b@gmail.com', '0900000002', 'thib', '123456', 2, true, true, NULL, 'Hanoi', '2002-05-10'),
('Nguyen Minh H', 'h@gmail.com', '0900000008', 'minhh', '123456', 1, true, true, NULL, 'HCM', '2001-02-11'),
('Tran Quoc I', 'i@gmail.com', '0900000009', 'quoci', '123456', 2, true, true, NULL, 'Ha Noi', '1998-04-09'),
('Pham Gia K', 'k@gmail.com', '0900000010', 'giak', '123456', 3, true, true, NULL, 'Da Nang', '2000-08-14'),
('Le Thi L', 'l@gmail.com', '0900000011', 'thil', '123456', 1, true, true, NULL, 'Can Tho', '2003-06-22'),
('Vo Thanh M', 'm@gmail.com', '0900000012', 'thanhm', '123456', 2, true, true, NULL, 'Hue', '1999-10-10'),
('Dang Hoang N', 'n@gmail.com', '0900000013', 'hoangn', '123456', 1, true, true, NULL, 'Hai Phong', '2004-01-19'),
('Bui Anh O', 'o@gmail.com', '0900000014', 'anho', '123456', 2, true, true, NULL, 'HCM', '2002-09-15'),
('Do Van P', 'p@gmail.com', '0900000015', 'vanp', '123456', 3, true, true, NULL, 'Vung Tau', '1997-11-03');


-- =========================
-- ADMINS
-- =========================
INSERT INTO admins (admin_name, admin_username, admin_password, role_id)
VALUES
('Admin 1', 'admin', '123456', 1),
('Staff 1', 'staff', '123456', 2);

-- =========================
-- PRODUCTS
-- =========================
INSERT INTO products (prod_name, prod_received, prod_sold, prod_price, prod_discount, cat_id, supply_id, prod_description)
VALUES
('Java Basics', 100, 10, 120000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Programming'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Learn Java from scratch'),

('Spring Boot Guide', 80, 5, 200000, 15,
 (SELECT cat_id FROM catalogue WHERE cat_name='Programming'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Spring Boot for beginners'),

('Harry Potter 1', 50, 20, 150000, 5,
 (SELECT cat_id FROM catalogue WHERE cat_name='Novel'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Kim Dong'),
 'Fantasy novel'),

('Clean Code', 70, 8, 180000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Programming'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'Programming best practices'),

('Harry Potter 2', 45, 15, 155000, 5,
 (SELECT cat_id FROM catalogue WHERE cat_name='Novel'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Kim Dong'),
 'Harry Potter continues'),

('The Hobbit', 40, 10, 170000, 0,
 (SELECT cat_id FROM catalogue WHERE cat_name='Novel'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Kim Dong'),
 'Fantasy adventure book'),

('Lord of the Rings', 35, 18, 350000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Novel'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Kim Dong'),
 'Epic fantasy trilogy'),

('Math Grade 12', 100, 30, 90000, 0,
 (SELECT cat_id FROM catalogue WHERE cat_name='Education'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'High school math textbook'),

('Physics Basics', 80, 25, 95000, 5,
 (SELECT cat_id FROM catalogue WHERE cat_name='Education'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'Basic physics concepts'),

('Chemistry Handbook', 70, 20, 110000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Education'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'Chemistry reference book'),
-- Programming
('C Programming', 80, 15, 130000, 5,
 (SELECT cat_id FROM catalogue WHERE cat_name='Programming'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Learn C language'),

('Advanced Java', 60, 12, 250000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Programming'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Advanced Java concepts'),

('Mastering Spring', 50, 8, 320000, 15,
 (SELECT cat_id FROM catalogue WHERE cat_name='Programming'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Spring framework deep dive'),

('NodeJS API Design', 45, 7, 210000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Programming'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'REST API development'),

('Effective Python', 55, 11, 240000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Programming'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Best practices in Python'),

('Machine Learning Intro', 40, 6, 350000, 20,
 (SELECT cat_id FROM catalogue WHERE cat_name='Programming'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'ML basics'),

('Linux Command Line', 70, 10, 180000, 5,
 (SELECT cat_id FROM catalogue WHERE cat_name='Programming'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'Linux for beginners'),

('SQL Mastery', 75, 13, 190000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Programming'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Database querying'),

-- Novel
('Naruto Vol 1', 100, 35, 85000, 5,
 (SELECT cat_id FROM catalogue WHERE cat_name='Novel'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Kim Dong'),
 'Manga story'),

('Naruto Vol 2', 100, 30, 85000, 5,
 (SELECT cat_id FROM catalogue WHERE cat_name='Novel'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Kim Dong'),
 'Manga story'),

('Doraemon Vol 1', 120, 40, 70000, 0,
 (SELECT cat_id FROM catalogue WHERE cat_name='Children'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Kim Dong'),
 'Kids manga'),

('Doraemon Vol 2', 110, 33, 70000, 0,
 (SELECT cat_id FROM catalogue WHERE cat_name='Children'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Kim Dong'),
 'Kids manga'),

('Detective Conan 1', 95, 28, 90000, 5,
 (SELECT cat_id FROM catalogue WHERE cat_name='Novel'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Kim Dong'),
 'Mystery manga'),

('Your Name', 50, 10, 160000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Novel'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Kim Dong'),
 'Romance novel'),

('The Alchemist', 60, 14, 175000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Novel'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Inspirational novel'),

('Atomic Habits', 80, 25, 220000, 15,
 (SELECT cat_id FROM catalogue WHERE cat_name='Self Help'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Habit improvement'),

('Think and Grow Rich', 70, 17, 200000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Business'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Business mindset'),

('Rich Dad Poor Dad', 90, 29, 210000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Business'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Financial education'),

('Deep Work', 55, 12, 195000, 5,
 (SELECT cat_id FROM catalogue WHERE cat_name='Self Help'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'Focus improvement'),

('Psychology of Money', 65, 20, 230000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Business'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Money psychology'),

-- Education
('English Vocabulary', 100, 30, 95000, 0,
 (SELECT cat_id FROM catalogue WHERE cat_name='Education'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'English learning'),

('TOEIC Preparation', 75, 16, 180000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Education'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'TOEIC practice'),

('IELTS Writing', 60, 9, 250000, 15,
 (SELECT cat_id FROM catalogue WHERE cat_name='Education'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'IELTS writing guide'),

('Math Grade 10', 90, 24, 85000, 0,
 (SELECT cat_id FROM catalogue WHERE cat_name='Education'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'Grade 10 textbook'),

('Math Grade 11', 85, 21, 88000, 0,
 (SELECT cat_id FROM catalogue WHERE cat_name='Education'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'Grade 11 textbook'),

('Chemistry Grade 11', 70, 15, 92000, 5,
 (SELECT cat_id FROM catalogue WHERE cat_name='Education'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'Chemistry textbook'),

('Biology Grade 12', 65, 13, 97000, 5,
 (SELECT cat_id FROM catalogue WHERE cat_name='Education'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'Biology textbook'),

-- Science
('Universe Explained', 40, 8, 280000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Science'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Astronomy basics'),

('Quantum Physics', 30, 4, 390000, 15,
 (SELECT cat_id FROM catalogue WHERE cat_name='Science'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Quantum mechanics'),

('Human Anatomy', 50, 11, 310000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Science'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'Human body study'),

('Artificial Intelligence', 45, 9, 420000, 20,
 (SELECT cat_id FROM catalogue WHERE cat_name='Science'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'AI fundamentals'),

-- History
('Vietnam History', 70, 18, 150000, 5,
 (SELECT cat_id FROM catalogue WHERE cat_name='History'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'History of Vietnam'),

('World War II', 40, 7, 260000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='History'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'WW2 events'),

('Ancient Civilizations', 35, 5, 290000, 15,
 (SELECT cat_id FROM catalogue WHERE cat_name='History'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Ancient world'),

('Samurai History', 45, 8, 210000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='History'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Kim Dong'),
 'Japanese samurai'),

-- Extra books to reach ~50
('Startup Basics', 50, 10, 220000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Business'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Startup guide'),

('Marketing 101', 60, 14, 190000, 5,
 (SELECT cat_id FROM catalogue WHERE cat_name='Business'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Marketing concepts'),

('Time Management', 70, 20, 160000, 5,
 (SELECT cat_id FROM catalogue WHERE cat_name='Self Help'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'Manage your time'),

('Mindset', 55, 12, 175000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Self Help'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Growth mindset'),

('Meditation Daily', 40, 6, 145000, 0,
 (SELECT cat_id FROM catalogue WHERE cat_name='Self Help'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'Meditation practice'),

('Fairy Tales', 100, 26, 80000, 0,
 (SELECT cat_id FROM catalogue WHERE cat_name='Children'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Kim Dong'),
 'Children stories'),

('Kids English', 90, 22, 95000, 5,
 (SELECT cat_id FROM catalogue WHERE cat_name='Children'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'English for kids'),

('Space for Kids', 60, 11, 120000, 5,
 (SELECT cat_id FROM catalogue WHERE cat_name='Children'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Kim Dong'),
 'Space learning for children'),
 ('Python for Beginners', 90, 12, 140000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Programming'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Learn Python easily'),

('Data Structures in Java', 60, 9, 220000, 15,
 (SELECT cat_id FROM catalogue WHERE cat_name='Programming'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Java data structures and algorithms'),

('One Piece Vol 1', 120, 40, 85000, 5,
 (SELECT cat_id FROM catalogue WHERE cat_name='Novel'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Kim Dong'),
 'Popular manga series'),

('Sherlock Holmes', 55, 14, 160000, 10,
 (SELECT cat_id FROM catalogue WHERE cat_name='Novel'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Kim Dong'),
 'Detective mystery stories'),

('English Grammar', 100, 35, 95000, 0,
 (SELECT cat_id FROM catalogue WHERE cat_name='Education'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'Grammar learning book'),

('Biology Basics', 75, 22, 105000, 5,
 (SELECT cat_id FROM catalogue WHERE cat_name='Education'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Giao Duc'),
 'Introduction to biology'),

('Docker & Kubernetes', 50, 6, 280000, 20,
 (SELECT cat_id FROM catalogue WHERE cat_name='Programming'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Containerization and orchestration'),

('ReactJS Mastery', 65, 11, 240000, 15,
 (SELECT cat_id FROM catalogue WHERE cat_name='Programming'),
 (SELECT supply_id FROM supplier WHERE supply_name='NXB Tre'),
 'Frontend development with React');
-- =========================
-- COUPON
-- =========================
INSERT INTO coupon (coupon_code, coupon_type, discount_type, discount_value, min_order_value, usage_limit, used_count, status, group_id)
VALUES
('BOOK10', 'product', 'percentage', 10, 0, 100, 0, 'active', 1),
('SHIP20K', 'shipping', 'fixed', 20000, 100000, 50, 0, 'active', 2);

-- =========================
-- ORDERS
-- =========================
INSERT INTO orders (cus_id, orders_status, orders_total, shipping_fee, coupon_code, couponship_code)
VALUES
(1, 'completed', 300000, 20000, 'BOOK10', 'SHIP20K'),
(2, 'pending', 150000, 20000, NULL, NULL),
(3,  'completed', 420000, 20000, 'BOOK10', NULL),
(4,  'pending',   175000, 15000, NULL, NULL),
(5,  'shipped',  560000, 20000, 'BOOK10', 'SHIP20K'),
(6,  'completed', 310000, 15000, NULL, NULL),
(7,  'completed', 680000, 20000, 'BOOK10', NULL),
(8,  'cancelled', 150000, 15000, NULL, NULL),
(9,  'completed', 230000, 20000, NULL, NULL),
(10, 'pending',   490000, 20000, 'BOOK10', NULL),
(1, 'shipped',  275000, 15000, NULL, NULL),
(2, 'completed', 720000, 25000, 'BOOK10', 'SHIP20K'),
(3, 'completed', 360000, 20000, NULL, NULL),
(4, 'pending',   180000, 15000, NULL, NULL),
(5, 'shipped',  520000, 20000, 'BOOK10', NULL),
(6, 'completed', 295000, 15000, NULL, NULL),
(7, 'completed', 810000, 25000, 'BOOK10', 'SHIP20K'),
(8, 'pending',   145000, 15000, NULL, NULL),
(9, 'completed', 430000, 20000, NULL, NULL),
(10, 'shipped',  610000, 20000, 'BOOK10', NULL),
(4, 'completed', 550000, 20000, NULL, NULL);

-- =========================
-- ORDER DETAILS
-- =========================
INSERT INTO order_details (orders_id, prod_id, quantity, price)
VALUES
(1, (SELECT prod_id FROM products WHERE prod_name='Java Basics'), 1, 120000),
(1, (SELECT prod_id FROM products WHERE prod_name='Spring Boot Guide'), 1, 200000),
(2, (SELECT prod_id FROM products WHERE prod_name='Harry Potter 1'), 1, 150000),
-- Order 3
(3, (SELECT prod_id FROM products WHERE prod_name='Clean Code'), 1, 180000),
(3, (SELECT prod_id FROM products WHERE prod_name='Python for Beginners'), 1, 140000),

-- Order 4
(4, (SELECT prod_id FROM products WHERE prod_name='Harry Potter 2'), 1, 155000),
(4, (SELECT prod_id FROM products WHERE prod_name='One Piece Vol 1'), 1, 85000),

-- Order 5
(5, (SELECT prod_id FROM products WHERE prod_name='Docker & Kubernetes'), 1, 280000),
(5, (SELECT prod_id FROM products WHERE prod_name='ReactJS Mastery'), 1, 240000),

-- Order 6
(6, (SELECT prod_id FROM products WHERE prod_name='Math Grade 12'), 2, 90000),

-- Order 7
(7, (SELECT prod_id FROM products WHERE prod_name='Lord of the Rings'), 1, 350000),
(7, (SELECT prod_id FROM products WHERE prod_name='Physics Basics'), 1, 95000),
-- Order 8
(8, (SELECT prod_id FROM products WHERE prod_name='Clean Code'), 1, 180000),
(8, (SELECT prod_id FROM products WHERE prod_name='Atomic Habits'), 1, 220000),

-- Order 9
(9, (SELECT prod_id FROM products WHERE prod_name='Doraemon Vol 1'), 2, 70000),

-- Order 10
(10, (SELECT prod_id FROM products WHERE prod_name='Artificial Intelligence'), 1, 420000),
(10, (SELECT prod_id FROM products WHERE prod_name='English Vocabulary'), 1, 95000),

-- Order 11
(11, (SELECT prod_id FROM products WHERE prod_name='Rich Dad Poor Dad'), 1, 210000),
(11, (SELECT prod_id FROM products WHERE prod_name='Time Management'), 1, 160000),

-- Order 12
(12, (SELECT prod_id FROM products WHERE prod_name='Mastering Spring'), 1, 320000),
(12, (SELECT prod_id FROM products WHERE prod_name='ReactJS Mastery'), 1, 240000),
(12, (SELECT prod_id FROM products WHERE prod_name='SQL Mastery'), 1, 190000),

-- Order 13
(13, (SELECT prod_id FROM products WHERE prod_name='The Alchemist'), 1, 175000),
(13, (SELECT prod_id FROM products WHERE prod_name='Mindset'), 1, 175000),

-- Order 14
(14, (SELECT prod_id FROM products WHERE prod_name='Fairy Tales'), 2, 80000),

-- Order 15
(15, (SELECT prod_id FROM products WHERE prod_name='Machine Learning Intro'), 1, 350000),
(15, (SELECT prod_id FROM products WHERE prod_name='Linux Command Line'), 1, 180000),

-- Order 16
(16, (SELECT prod_id FROM products WHERE prod_name='Vietnam History'), 1, 150000),
(16, (SELECT prod_id FROM products WHERE prod_name='Space for Kids'), 1, 120000),

-- Order 17
(17, (SELECT prod_id FROM products WHERE prod_name='Quantum Physics'), 1, 390000),
(17, (SELECT prod_id FROM products WHERE prod_name='Human Anatomy'), 1, 310000),
(17, (SELECT prod_id FROM products WHERE prod_name='Biology Grade 12'), 1, 97000),

-- Order 18
(18, (SELECT prod_id FROM products WHERE prod_name='Kids English'), 1, 95000),

-- Order 19
(19, (SELECT prod_id FROM products WHERE prod_name='Psychology of Money'), 1, 230000),
(19, (SELECT prod_id FROM products WHERE prod_name='Deep Work'), 1, 195000),

-- Order 20
(20, (SELECT prod_id FROM products WHERE prod_name='Advanced Java'), 1, 250000),
(20, (SELECT prod_id FROM products WHERE prod_name='Docker & Kubernetes'), 1, 280000),

-- Order 21
(21, (SELECT prod_id FROM products WHERE prod_name='World War II'), 1, 260000),
(21, (SELECT prod_id FROM products WHERE prod_name='Ancient Civilizations'), 1, 290000);
-- =========================
-- CART ITEMS
-- =========================
INSERT INTO cart_items (cus_id, prod_id, quantity)
VALUES
(1, (SELECT prod_id FROM products WHERE prod_name='Harry Potter 1'), 2),
(1, (SELECT prod_id FROM products WHERE prod_name='Clean Code'), 1),
(2, (SELECT prod_id FROM products WHERE prod_name='Java Basics'), 1);

-- =========================
-- PRODUCT IMAGES (SAFE)
-- =========================
INSERT INTO product_images (prod_id, image_url, is_primary)
VALUES
((SELECT prod_id FROM products WHERE prod_name='Java Basics'), 'java.jpg', true),
((SELECT prod_id FROM products WHERE prod_name='Spring Boot Guide'), 'spring.jpg', true),
((SELECT prod_id FROM products WHERE prod_name='Clean Code'), 'cleancode.jpg', true),
((SELECT prod_id FROM products WHERE prod_name='Harry Potter 1'), 'hp1.jpg', true),
((SELECT prod_id FROM products WHERE prod_name='Harry Potter 2'), 'hp2.jpg', true),
((SELECT prod_id FROM products WHERE prod_name='The Hobbit'), 'hobbit.jpg', true),
((SELECT prod_id FROM products WHERE prod_name='Lord of the Rings'), 'lotr.jpg', true),
((SELECT prod_id FROM products WHERE prod_name='Math Grade 12'), 'math12.jpg', true),
((SELECT prod_id FROM products WHERE prod_name='Physics Basics'), 'physics.jpg', true),
((SELECT prod_id FROM products WHERE prod_name='Chemistry Handbook'), 'chemistry.jpg', true),
((SELECT prod_id FROM products WHERE prod_name='Python for Beginners'), 'python.jpg', true),
((SELECT prod_id FROM products WHERE prod_name='Data Structures in Java'), 'dsjava.jpg', true),
((SELECT prod_id FROM products WHERE prod_name='One Piece Vol 1'), 'onepiece1.jpg', true),
((SELECT prod_id FROM products WHERE prod_name='Sherlock Holmes'), 'sherlock.jpg', true),
((SELECT prod_id FROM products WHERE prod_name='English Grammar'), 'grammar.jpg', true),
((SELECT prod_id FROM products WHERE prod_name='Biology Basics'), 'biology.jpg', true),
((SELECT prod_id FROM products WHERE prod_name='Docker & Kubernetes'), 'dockerk8s.jpg', true),
((SELECT prod_id FROM products WHERE prod_name='ReactJS Mastery'), 'reactjs.jpg', true);
