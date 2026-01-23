-- Tabla de Clientes
CREATE TABLE client (
    id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    name NVARCHAR(50) NOT NULL,
    identification NVARCHAR(10) NOT NULL,
    address NVARCHAR(250) NOT NULL,
    phone NVARCHAR(10) NOT NULL,
    gender NVARCHAR(6) CHECK (gender_check IN ('MALE', 'FEMALE')) NOT NULL,
    password NVARCHAR(250) NOT NULL,
    status NVARCHAR(10) CHECK (status_check IN ('ACTIVATE', 'INACTIVATE')) NOT NULL
);

-- Tabla de Cuentas
CREATE TABLE account (
    id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    clientId UNIQUEIDENTIFIER NOT NULL,
    number NVARCHAR(20) NOT NULL,
    accountType NVARCHAR(7) CHECK (accountType_check IN ('SAVINGS', 'CURRENT')) NOT NULL,
    initialBalance DECIMAL(12, 2) NOT NULL,
    balance DECIMAL(12, 2) NOT NULL,
    status NVARCHAR(10) CHECK (status_check IN ('ACTIVATE', 'INACTIVATE')) NOT NULL,
    FOREIGN KEY (clientId) REFERENCES client(id) ON DELETE CASCADE
);

-- Tabla de Movimientos
CREATE TABLE movement (
    id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    accountId UNIQUEIDENTIFIER NOT NULL,
    date DATETIME NOT NULL DEFAULT GETDATE(),
    movementType NVARCHAR(6) CHECK (movementType_check IN ('CREDIT', 'DEBIT')) NOT NULL,
    value DECIMAL(12, 2) NOT NULL,
    balance DECIMAL(12, 2) NOT NULL,
    FOREIGN KEY (accountId) REFERENCES account(id) ON DELETE CASCADE
);
);