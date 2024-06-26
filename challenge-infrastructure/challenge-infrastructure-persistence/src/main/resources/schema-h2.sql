CREATE TABLE BRANDS (
    BRAND_ID INT PRIMARY KEY,
    BRAND_NAME VARCHAR(255)
);

CREATE TABLE PRODUCTS (
    PRODUCT_ID INT PRIMARY KEY,
    PRODUCT_NAME VARCHAR(255)
);

CREATE TABLE PRICES (
    PRICE_ID INT PRIMARY KEY,
    PRICE_BRAND_ID INT,
    PRICE_START_DATE TIMESTAMP,
    PRICE_END_DATE TIMESTAMP,
    PRICE_PRODUCT_ID INT,
    PRICE_PRIORITY INT,
    PRICE_PVP DECIMAL(10, 2),
    PRICE_CURRENCY VARCHAR(3),
    FOREIGN KEY (PRICE_BRAND_ID)   REFERENCES BRANDS(BRAND_ID),
    FOREIGN KEY (PRICE_PRODUCT_ID) REFERENCES PRODUCTS(PRODUCT_ID)
);

CREATE INDEX IDX_PRICES_SEARCH ON PRICES
    (PRICE_BRAND_ID, PRICE_PRODUCT_ID, PRICE_START_DATE, PRICE_END_DATE, PRICE_PRIORITY DESC);
