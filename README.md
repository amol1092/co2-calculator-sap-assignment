# Project Description
  
This project is implemented for **SAP** as an assignment project. It's a Java project with maven structure.

## Build & Run

Firstly, Extract the co2-calculator.zip. Inside the co2-calculator folder, you will have the maven project structure with pom.xml and the source code and a script used for execution.

### Running the application
Go to the location of pom.xml in the folder. Then execute below command,

```
mvn clean package

After the jar is generated successfully, you can use the tool like

./co2-calculator --transportation-method medium-diesel-car --distance 15

```

1. When you run the mvn clean package, the tests are automatically run

2. I have stored the CO2 data in a .csv file and it is placed under resources folder, please do not move that file.

3. The CO2 data from the .csv file is loaded at the start-up in a singleton instance object called as Co2DataContainer

4. Printer class is implemented for the purpose of printing the output to the console

5. For CommandLine arguments parsing, I have used commonscli library. For csv parsing, I have used opencsv and for tests, I have used junit5


Author : Amol Ekande
