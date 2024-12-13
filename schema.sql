CREATE TABLE client (
    id UNIQUEIDENTIFIER PRIMARY KEY,
    name NVARCHAR(50) NOT NULL,
    identification NVARCHAR(10) NOT NULL,
    address NVARCHAR(250) NOT NULL,
    phone NVARCHAR(10) NOT NULL,
    gender NVARCHAR(6) CHECK (gender IN ('MALE', 'FEMALE')) NOT NULL,
    password NVARCHAR(250) NOT NULL,
    status NVARCHAR(10) CHECK (gender IN ('ACTIVATE', 'INACTIVATE')) NOT NULL
);

CREATE TABLE account (
    id UNIQUEIDENTIFIER PRIMARY KEY,
    clientId UNIQUEIDENTIFIER NOT NULL,
    number NVARCHAR(20) NOT NULL,
    accountType NVARCHAR(7) CHECK (gender IN ('SAVINGS', 'CURRENT')) NOT NULL,
    initialBalance NUMBER(12, 2) NOT NULL,
    balance NUMBER(12, 2) NOT NULL,
    status NVARCHAR(10) CHECK (gender IN ('ACTIVATE', 'INACTIVATE')) NOT NULL,
    FOREIGN KEY (clientId) REFERENCES client(id)
);

CREATE TABLE movement (
    id UNIQUEIDENTIFIER PRIMARY,
    accountId UNIQUEIDENTIFIER NOT NULL,
    'date' DATETIME NOT NULL,
    movementType NVARCHAR(6) CHECK (gender IN ('CREDIT', 'DEBIT')) NOT NULL,
    'value' NUMBER(12, 2) NOT NULL,
    balance NUMBER(12, 2) NOT NULL,
    FOREIGN KEY (accountId) REFERENCES account(id)
);