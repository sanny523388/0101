CREATE DATABASE IF NOT EXISTS library_mysql
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE library_mysql;

CREATE TABLE IF NOT EXISTS categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    sub_name VARCHAR(100) NOT NULL,
    UNIQUE KEY uk_categories_name_sub_name (name, sub_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS books (
    id INT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(100) NOT NULL,
    available BOOLEAN NOT NULL DEFAULT TRUE,
    borrowUser VARCHAR(100) NULL,
    category_id INT NOT NULL,
    CONSTRAINT fk_books_category
        FOREIGN KEY (category_id)
        REFERENCES categories (id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO categories (id, name, sub_name) VALUES
    (1, 'P', 'Java'),
    (2, 'P', 'Python'),
    (3, 'N', '奇幻'),
    (4, 'N', '歷史')
ON DUPLICATE KEY UPDATE
    name = VALUES(name),
    sub_name = VALUES(sub_name);

INSERT INTO books (id, title, author, available, borrowUser, category_id) VALUES
    (1, 'Java入門', '張三', FALSE, 'aa', 1),
    (2, 'Python程式設計', '王五', TRUE, 'NULL', 2),
    (3, '哈利波特', 'J.K.Rowling', TRUE, 'NULL', 3),
    (4, '達文西密碼', '丹布朗', TRUE, 'NULL', 4)
ON DUPLICATE KEY UPDATE
    title = VALUES(title),
    author = VALUES(author),
    available = VALUES(available),
    borrowUser = VALUES(borrowUser),
    category_id = VALUES(category_id);