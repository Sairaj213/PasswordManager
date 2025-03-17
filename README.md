# PasswordManager_JAVA

## Overview

This is a basic, password manager written in Java. It enables users to create strong, random passwords and save their credentials (name, username, and encrypted password).

## Features

*   **Password Generation:** Creates random passwords according to user's criteria:
    *   Length
    *   Lowercase letters
    *   Uppercase letters
    *   Digits
    *   Special characters
    *   Password Strength Check
*   **Credential Management:**
*   Input new credentials (name, username, password).
    *   Display existing credentials (name, username).
    *   Option to decrypt and display passwords (with user confirmation).
    *   Modify existing Credentials.
    *   Remove existing Credentials.

## Project Structure
```markdown-tree
src/
 ├── model/
 │     └── Credential.java (Represents a single credential)
 ├── utils/
 │     ├── PasswordUtils.java (Handles password generation)
 │     └── CryptoUtils.java (Handles encryption/decryption)
 ├── controller/
 │     └── PasswordController.java (Handles application logic)
 └── view/
       └── PasswordUI.java (Overall User-Interface)
```
## How to Run (VS Code)

1.  **Prerequisites:**
    *   Java Development Kit (JDK) 8 or later.
    *   VS Code with the "Extension Pack for Java" installed.

2.  **Setup:**
    *   Open the project folder in VS Code.

3.  **Execution:**
    *   Open `PasswordUI.java`.
    *   Click the "Run" icon (green play button) above the `main` method or right-click in the file and select "Run Java". Alternatively, use the integrated terminal.
