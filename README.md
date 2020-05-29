# Currency Calculator
Application which allows user to convert entered amount in EUR into chosen currency.

The currency rate is loaded from website = https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml.

To run calculator on command prompt user has to:
 - Make sure your have installed java on your computer, in order to check it run `java -version` 
 - Make sure you have installed maven, to check it run `mvn --version` 
 - Run commands in command prompt (user should be in project folder location):
    - `mvn package`
    - `java -jar target/recruitment-task-0.0.1.jar`
    
 To use Currency Calculator properly please follow the instructions which appears in command prompt.
 
 **CAUTION:** Enter amount in calculator with **dot** as separator!
 
    Example: 235.35 