INSERT INTO app_user (id, first_name, last_name, email, password) VALUES
    (1, 'John', 'Doe', 'john.doe@example.com', 'password123'),
    (2, 'Jane', 'Smith', 'jane.smith@example.com', 'password456'),
    (3, 'Bob', 'Johnson', 'bob.johnson@example.com', 'password789');

INSERT INTO post (id, content, user_id, likes_count) VALUES
    (1, 'Hello world! This is my first post.', 1, 5),
    (2, 'Spring Boot is awesome!', 1, 12),
    (3, 'Learning JPA and Hibernate today.', 2, 8),
    (4, 'Having a great day coding!', 2, 3),
    (5, 'Just deployed my first API!', 3, 15);