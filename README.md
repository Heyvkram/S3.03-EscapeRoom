
# IT ACADEMY BACKEND JAVA SPECIALIZATION

# SPRINT 3 TASK 3 - Developers Team

## Summary

This exercise involves developing an application to manage a virtual Escape Room. The application will allow the creation and management of themed rooms, intriguing clues, unique decoration items, and maintain an up-to-date inventory. Additionally, functionalities for tracking income, and user notifications will be implemented. Data persistence will be achieved using MySQL.

## Technologies

* Java
* Maven
* MySQL
* Java Lombok
* Git
* GitHub

## How to run it

1.  **Clone the repository:**
	* Clone the repository to your local machine using Git.
2.  **Configure the database:**
	* Ensure you have a running MySQL server.
	* Create a database for the virtual Escape Room application.
	* Configure the database connection credentials (username, password, URL) in the application's configuration file.
3.  **Build the project:**
	* Navigate to the project directory and build it using Maven.
	* For example: `mvn clean install`
4.  **Run the application:**
	* Execute the main class of the application to start the program.
5.  **Interact with the program (via console):**
	* The application will display a menu or allow interaction through the console to perform the following actions:
		* Create a new virtual Escape Room.
		* Add new rooms with their respective difficulty levels.
		* Incorporate themed clues into the rooms.
		* Introduce decoration objects to uniquely set the atmosphere of the rooms.
		* Display the updated inventory of rooms, clues, and decoration objects.
		* Visualize the total value of the inventory.
		* Allow the removal of items from the inventory.
		* Generate sales tickets for players.
		* Calculate and display the total income generated from ticket sales.
		* Register users to receive notifications about important events.
		* Simulate the notification of important events.

## Project Structure

* The project is built using Maven.
* The Java code is located in the `src/main/java` directory.
* The `pom.xml` file contains the project's dependencies and build configurations.
* Packages are expected to be created for different functionalities.