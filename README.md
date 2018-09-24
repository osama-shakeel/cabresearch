# Program Design/Features
- The application's REST API details are provided in the **swagger.yaml** file located at the root folder of the project.
- The project is **Gradle** based **Spring Boot** project.
- Spring modules used are mainly Spring MVC, Spring JPA and Spring Cache.
- For both Development and Test environments, the in-memory database H2 has been used.
	- In Development environment, the schema and actual data are in **schema.sql** and **data.sql** respectively.
	- In Test environment, the schema and actual data are in **schema-h2.sql** and **data-h2.sql** respectively.
- For caching, EhCache has been used, configured with a single cache for storing Cab trip counts with expiry time of 5 minutes.  In the application, Spring Cache abstraction has been used over Ehcache.


# Steps to build
- Please clone and download this repository locally.
- The project is a **Gradle **based **Spring Boot** project.
- To build the project please run the following GRADLE command on command-line interface/terminal from the root folder:
*If in Windows:*
    `gradlew.bat clean build`
*If in Unix/OSX:*
    `./gradlew clean build`

	where the jar file is created in **build/lib** folder.


# Steps to run program
- To run the application using Spring Boot's gradle plugin, please run the following GRADLE command on command-line interface/terminal from the root folder:
*If in Windows:*
    `gradlew.bat clean bootRun`
*If in Unix/OSX:*
    `./gradlew clean bootRun`

- To run the application using the Jar file navigate into **build/lib** folder.
- Please run the following command on command-line interface/terminal:
`java -jar cab-1.0.jar`