use javaanishop;

CREATE TABLE products (
    ProductId INT PRIMARY KEY AUTO_INCREMENT,
    ProductName VARCHAR(50) NOT NULL,
    ProductDescription VARCHAR(250) NOT NULL,
    ProductStock INT NOT NULL CHECK (ProductStock >= 0),
    ProductPrice DECIMAL(10, 2) NOT NULL CHECK (ProductPrice >= 0),
    CreatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

select * from products