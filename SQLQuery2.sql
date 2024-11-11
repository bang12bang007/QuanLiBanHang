-- T?o c? s? d? li?u 'taphoa'
CREATE DATABASE  taphoa;
GO

USE taphoa;
go

-- B?ng s?n ph?m (items)
CREATE TABLE  items (
    id NVARCHAR(50) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    category NVARCHAR(50),
    created_at DATETIME DEFAULT GETDATE(),
	expiredate Date
);
GO

-- B?ng khách hàng (customers)
CREATE TABLE  customers (
    id NVARCHAR(50) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) UNIQUE NOT NULL,
    phone NVARCHAR(15),
    address NVARCHAR(255),
    created_at DATETIME DEFAULT GETDATE()
);
GO

-- B?ng ??n hàng (orders)
CREATE TABLE  orders (
    id NVARCHAR(50) PRIMARY KEY,
    customer_id NVARCHAR(50) NOT NULL,
    order_date DATE NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status NVARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES customers(id) 
);
GO

-- B?ng chi ti?t ??n hàng (order_details)
CREATE TABLE order_details (
    id NVARCHAR(50) PRIMARY KEY,
    order_id NVARCHAR(50) NOT NULL,
    item_id NVARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    total AS (quantity * price) PERSISTED,
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (order_id) REFERENCES orders(id) ,
    FOREIGN KEY (item_id) REFERENCES items(id) 
);
GO


INSERT INTO customers (id, name, email, phone, address)
VALUES ('KH000', N'Kha?ch Va?ng Lai', 'null@example.com', '1234567890', '123 Main St');


-- Thêm d? li?u m?u vào b?ng items
INSERT INTO items (id, name, price, quantity, category, expiredate)
VALUES 
    ('I001', 'Bánh quy', 15.50, 100, 'Th?c ph?m', '2024-12-31'),
    ('I002', 'S?a t??i', 12.00, 200, '?? u?ng', '2024-11-30'),
    ('I003', 'N??c ng?t', 8.50, 150, '?? u?ng', '2025-01-15'),
    ('I004', 'Bánh mì', 5.00, 50, 'Th?c ph?m', '2024-11-25');

-- Thêm d? li?u m?u vào b?ng customers
INSERT INTO customers (id, name, email, phone, address)
VALUES 
    ('C001', 'Nguy?n V?n A', 'nguyenvana@example.com', '0123456789', '123 ???ng ABC, Hà N?i'),
    ('C002', 'Tr?n Th? B', 'tranthib@example.com', '0987654321', '456 ???ng XYZ, TP.HCM'),
    ('C003', 'Lê V?n C', 'levanc@example.com', '0912345678', '789 ???ng DEF, ?à N?ng');

-- Thêm d? li?u m?u vào b?ng orders
INSERT INTO orders (id, customer_id, order_date, total_amount, status)
VALUES 
    ('O001', 'C001', '2024-11-01', 31.00, '?ã giao'),
    ('O002', 'C002', '2024-11-05', 24.00, '?ang x? lý'),
    ('O003', 'C003', '2024-11-07', 40.50, '?ã giao');

-- Thêm d? li?u m?u vào b?ng order_details
INSERT INTO order_details (id, order_id, item_id, quantity, price)
VALUES 
    ('OD001', 'O001', 'I001', 2, 15.50),
    ('OD002', 'O001', 'I004', 1, 5.00),
    ('OD003', 'O002', 'I002', 2, 12.00),
    ('OD004', 'O003', 'I003', 3, 8.50),
    ('OD005', 'O003', 'I001', 1, 15.50);


EXEC sp_rename 'items.expiredate', 'expiredDate', 'COLUMN';
