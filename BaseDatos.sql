CREATE TABLE person_customer (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50) NOT NULL,
    gender VARCHAR(3) NOT NULL,
    age INT NOT NULL,
    identification VARCHAR(10) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    password VARCHAR(50) NOT NULL,
    status  BOOLEAN NOT NULL
);

CREATE TABLE account (
	id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    account_number VARCHAR(50) NOT NULL,
    account_type VARCHAR(50) NOT NULL,
    initial_balance DECIMAL(15, 2) NOT NULL,
    status  BOOLEAN NOT null,
    client_id BIGINT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES person_customer(id)
);

CREATE TABLE transactions (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    date TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    transaction_type VARCHAR(50) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    balance DECIMAL(15, 2) NOT null,
    account_id BIGINT NOT NULL,
    FOREIGN KEY (account_id) REFERENCES account(id)
);