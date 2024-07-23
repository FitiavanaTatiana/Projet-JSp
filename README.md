# JSP and Servlets Project

This project is a simple web application developed using JavaServer Pages (JSP) and Servlets. The application is deployed on an Apache Tomcat server.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Requirements](#requirements)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Introduction

This project demonstrates the use of JSP and Servlets to create a dynamic web application. It includes examples of form handling, session management, and basic CRUD operations.

## Features

- Dynamic web pages using JSP
- Backend processing using Servlets
- Session management
- Basic CRUD operations
- Deployment on Apache Tomcat

## Requirements

- Java Development Kit (JDK) 11 or higher
- Apache Tomcat 9 or higher
- Maven (for build automation)
- Git (for version control)

## Setup and Installation

1. **Clone the repository**:
    ```sh
    git clone https://github.com/your-username/your-repository.git
    cd your-repository
    ```

2. **Build the project using Maven**:
    ```sh
    mvn clean install
    ```

3. **Deploy the application to Tomcat**:
    - Copy the generated `WAR` file from the `target` directory to the `webapps` directory of your Tomcat installation.
    - Alternatively, configure Tomcat to deploy the `WAR` file directly from the `target` directory.

4. **Start the Tomcat server**:
    - Navigate to the `bin` directory of your Tomcat installation.
    - Run the startup script:
        - On Windows: `startup.bat`
        - On Unix-based systems: `./startup.sh`

5. **Access the application**:
    - Open your web browser and go to `http://localhost:8080/ProjetJSP/EmployeeList`.

## Usage

1. **Home Page**:

    YOu'll find all employee list with all requirend details




2. **CRUD Operations**:
    - The application includes basic Create, Read, Update, and Delete operations using Servlets and JSP.

## Project Structure

The project follows a standard Maven directory structure:

