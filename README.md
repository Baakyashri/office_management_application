# Office Management System (OMS)

Welcome to the **Office Management System (OMS)** repository. This is a desktop application built with Java and JavaFX, designed to streamline and manage essential office operations. It features a role-based access system with dedicated dashboards for Administrators, Managers, and Employees.

##  Features

* **Role-Based Access Control :** Secure login system directing users to specific dashboards based on their roles.
  * **Administrator Dashboard:** Manage top-level system operations and configurations.
  * **Manager Dashboard:** Oversee department operations and employee activities.
  * **Employee Dashboard:** View personal details, tasks, or schedules (varies by implementation).
* **Department Management:** Add, update, and organize various office departments.
* **Employee Management:** Maintain employee records, roles, and departmental assignments.
* **Database Integration:** Persistent storage using MySQL.

##  Technology Stack

* **Language:** Java 23
* **GUI Framework:** JavaFX 17.0.6 (Controls, FXML)
* **Architecture:** MVC (Model-View-Controller)
* **Build Tool:** Maven
* **Database:** MySQL

##  Project Structure

The codebase is organized following standard Maven directory layouts and MVC design patterns:

- `src/main/java/com/ssn/oms/`
  - `controllers/`: JavaFX controller classes handling user interactions and UI updates.
  - `models/`: Java domain objects representing data entities (`User`, `Admin`, `Manager`, `Employee`, `Department`).
  - `services/`: Business logic layer bridging controllers and database interactions.
  - `database/`: Database connection handler (`DatabaseConnection.java`).
- `src/main/resources/com/ssn/oms/`
  - FXML view files defining the graphical user interface.

##  Prerequisites

Before you begin, ensure you have met the following requirements:

* **Java Development Kit (JDK):** Version 23 or higher.
* **Apache Maven:** Installed and configured in your system.
* **MySQL Server:** Running locally or remotely.

##  Installation and Setup

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd office_management_application-main/OMS
   ```

2. **Database Setup:**
   * Ensure your MySQL server is running.
   * Create a database named `office_management`.
   * *Note:* The application expects the following default MySQL credentials. If your local setup differs, please update `src/main/java/com/ssn/oms/database/DatabaseConnection.java`:
     * **Username:** `root`
     * **Password:** `system1$`
   * *(To construct the necessary tables, execute the provided `schema.sql` file within the repository.)*

3. **Install Dependencies:**
   Ensure you are in the `OMS` directory, then run:
   ```bash
   mvn clean install
   ```
   *(Note: You may need to add the `mysql-connector-j` dependency to your `pom.xml` if it's missing from the project configuration).*

4. **Run the Application:**
   You can run the application using the JavaFX Maven Plugin:
   ```bash
   mvn javafx:run
   ```

##  Contributing

Contributions, issues, and feature requests are welcome! 

1. Fork the project.
2. Create your feature branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a Pull Request.

##  License

This project is licensed under the [MIT License](LICENSE) - see the LICENSE file for details.
