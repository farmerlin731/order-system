-- 初始化商品
INSERT INTO products (id, name, price) VALUES (1, 'iPhone 16', 39999);
INSERT INTO products (id, name, price) VALUES (2, 'MacBook Pro 14', 69999);
INSERT INTO products (id, name, price) VALUES (3, 'AirPods Pro 3', 8999);

-- 初始化訂單
INSERT INTO orders (id, customer_name, created_at) VALUES (1, 'Alice', NOW());

-- 訂單項目 (Alice 買了一台 iPhone 16)
INSERT INTO order_items (id, order_id, product_id, quantity) VALUES (1, 1, 1, 1);
