# CSCIX370 Term Project

**AUTHORS:**
- Cameron Hubbard
- Savannah Steele

** Database Setup **
The script 'setup.py' will create a schema named 'term_project' and insert the data from the included .csv files.
This script requires pandas and mysql-connector to run, which can be easily downloaded using npm:
- "npm install pandas"
- "npm install mysql"
- Note: If npm itself is needed, it is included as part of downloading Node JS.
- Run the script with "py setup.py"
- Note: The database is quite large and it may take some time to complete the script.
 	Messages are printed displaying the script's progress.         
 
 

**TO COMPILE/RUN**
- This is a Spring Boot project and requires Maven and JDK 1.8+ to run. (likely already installed)
- "npm install maven"
- JDK can be found at "https://www.oracle.com/java/technologies/javase-downloads.html"
- run the project with "mvnw spring-boot:run" on windows or "mvn spring-boot:run" on mac/linux from cmd/terminal
- './' may need to be prepended to the commands above
- Alternatively, an environment like IntelliJ IDEA could be used.

- Successfuly execution results in a message similar to the following:
 "Started TermprojectApplication in X.XXX seconds (JVM running for X.XXX)"

- Once the program is running, visit "http://localhost:8081/" or "localhost:8081" in your browser.
