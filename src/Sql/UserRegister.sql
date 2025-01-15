CREATE TABLE UserRegister (
    Id INT AUTO_INCREMENT PRIMARY KEY, -- Automatically increments for each new user
    EmailId VARCHAR(50) NOT NULL UNIQUE, -- Extended length for modern email use
    Password VARCHAR(255) NOT NULL, -- Increased length to support hashed passwords
    Name VARCHAR(50), -- Increased length to accommodate full names
    Gender CHAR(1) CHECK (Gender IN ('M', 'F', 'O')), -- Added a CHECK constraint for standardization
    Age TINYINT UNSIGNED, -- Optimized for age range (0 to 255) since it's unlikely to exceed 120
    PhoneNumber VARCHAR(15) UNIQUE -- Extended to handle international formats with country code
);


