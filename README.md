Coverage: 80%
# Project Title
Project goal is to build a inventory management system with crud functionality in the CLI using technologies like 

Java
Maven
JUnit
MySQL

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

IDE - Eclipse (https://www.eclipse.org/downloads/)
JAVA - Java SE Development Kit (https://www.oracle.com/uk/java/technologies/javase/javase-jdk8-downloads.html)
Database - MySQL (https://dev.mysql.com/downloads/mysql/5.7.html)
Maven - Apache Maven (https://maven.apache.org/download.cgi?Preferred=ftp://ftp.osuosl.org/pub/apache/)

### Installing

HOW TO RUN THE APP:
After setting up JAVA, you need to create a database on your local MySQL server on your machine.
Follow the following steps
1. Open MySQL Workbench
2. Use local database instance and enter your username and pasword
3. Type "CREATE DATABASE ims;"

To run the system navigate to the directory where the pom.xml file is located.
Then run the command:"mvn clean package"

This  creates  a directory  which  contains a jar file.
Then run the jar file by navigating to target file and run the command:
"java -jar ims-jar-with-dependencies.jar"

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
Jira Link-https://ktorkgokot.atlassian.net/jira/software/projects/IMS/boards/2/backlog
