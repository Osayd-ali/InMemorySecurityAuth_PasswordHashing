Demonstration of application security by providing in memory users through Spring Security. Using BCrypt hashing function for encoding passwords. Adding an additional layer of security.

* Securing Passwords Using BCrypt
* Password hashing is critical, when saving password, saving them in plain text is extremely dangerous.
* Hashing a password transforms it into a fixed length, irreversible string of characters. Best practice is to store only hashed passwords.
* We use BCrypt hashing function specifically designed for spring security.
* While storing the password on first login, we shouldn’t store the raw password. Instead, use a password encoder to hash the password first.
* This way, the database will store only the hashed version.
* When verifying passwords during login, the password they enter is hashed and compared to the stored hashed password.
* You never decode the stored password - you always encode the input and then compare with the hashed version that was stored during first login.
