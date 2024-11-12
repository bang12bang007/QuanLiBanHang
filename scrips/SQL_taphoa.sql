-- Tạo cơ sở dữ liệu 'taphoa'
CREATE DATABASE  taphoa;
GO

USE taphoa;
go

-- Bảng sản phẩm (items)
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

-- Bảng khách hàng (customers)
CREATE TABLE  customers (
    id NVARCHAR(50) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) UNIQUE NOT NULL,
    phone NVARCHAR(15),
    address NVARCHAR(255),
    created_at DATETIME DEFAULT GETDATE()
);
GO

-- Bảng đơn hàng (orders)
CREATE TABLE  orders (
    id NVARCHAR(50) PRIMARY KEY,
    customer_id NVARCHAR(50) NOT NULL,
    order_date DATE NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status NVARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES customers(id) 
);
GO

-- Bảng chi tiết đơn hàng (order_details)
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


INSERT INTO items (id, name, price, quantity, category, created_at, expiredate)
VALUES 
('I001', N'Bánh Kem', 100000, 10, N'Bánh ngọt', GETDATE(), '2024-12-31'),
('I002', N'Mì', 15000, 50, N'Thực phẩm khô', GETDATE(), '2025-06-30'),
('I003', N'Bánh Mì', 20000, 30, N'Bánh ngọt', GETDATE(), '2024-11-15');
GO

INSERT INTO customers (id, name, email, phone, address, created_at)
VALUES 
('C001', N'Nguyễn Văn A', 'a.nguyen@example.com', '0123456789', N'Hà Nội', GETDATE()),
('C002', N'Trần Thị B', 'b.tran@example.com', '0987654321', N'TP HCM', GETDATE()),
('C003', N'Lê Văn C', 'c.le@example.com', '0912345678', N'Đà Nẵng', GETDATE());
GO

INSERT INTO orders (id, customer_id, order_date, total_amount, status)
VALUES 
('O001', 'C001', '2024-11-10', 115000, N'Đang xử lý'),
('O002', 'C002', '2024-11-09', 45000, N'Hoàn thành'),
('O003', 'C003', '2024-11-08', 60000, N'Đang giao hàng');
GO
INSERT INTO order_details (id, order_id, item_id, quantity, price, created_at)
VALUES 
('OD001', 'O001', 'I001', 1, 100000, GETDATE()),
('OD002', 'O001', 'I002', 1, 15000, GETDATE()),
('OD003', 'O002', 'I003', 2, 20000, GETDATE());
GO
