# Project Name
automationpractice

# Project Description
This project is created to automate http://automationpractice.com/ shopping portal.

# Pre-requisite
1. java 8 or higher version.
2. Maven (3.6.3) (NB: update setting.xml with proxy if required, otherwise it may failed to download maven dependency) 
3. eclipse IDE (This is optional, project can be executed without any IDE as well)
4. chrome browser (provided chrome driver supprt chrome version 84.0.4147.30, 
					If any later version of chrome used then need to replace chromedriver.exe from /src/test/resource/Drivers with compatible chrome driver)
5. chromedriver.exe should place "automationpractice\src\test\resources\Drivers" location after unzipping. As gmail blocked this file for sending attachment, this file was not shipped with the project. 


# Import Project into eclipse IDE
1. Unzip assignment_jayanta.7z file
2. Placed chromedriver.exe in "automationpractice\src\test\resources\Drivers" location.
3. Click File --> Import... in eclipse
4. Select 'Existing Projects into Workspace' under 'General'
5. Click 'Browse' for 'Select root directory' option.
6. Select 'automationpractice' folder under unzipped folder and click 'OK'. 'automationpractice' is the project root directory.
7. Click 'Finish'
8. Right click on the project --> Select 'Maven' --> Click on 'Update Project' --> Click 'OK'. (This steps should happen successfully)

## Execution
Project can be executed from eclipse or command prompt.

### Execute from Eclipse :
1. If execute from eclipse then maven plugin should correctly configured in eclipse.
2. Now right click on the 'pom.xml' file --> Select 'Run As' --> Click 'Maven test'
		
#### NB: 
You might face issue while execute it from eclipse for 1st execution as it is execute with eclipse maven plugin & use default test compiler. Though it is failed in 1st execution, it will update project path in inputFiles.lst.

If it is failed for 1st execution with mention above steps, then after failure refresh the project & follow the same above steps (Execute again). 

### Execute from command prompt :
1. Open command prompt
2. Navigate to project directory where pom.xml file present
3. execute command 'mvn clean install'
		
#### NB:
Recoment to execute from command prompt.
If you face any issue to execute from commnad prompt then verify that maven is correctly install in your system & configured it in 'path' Environment variable.
If you face issue while download maven dependency then check setting.xml file for maven & make sure you used correct proxy details.

## Execution Demo Video:
Please see below execution demo video, it includes execution from eclipse & command prompt both.
Execution_Demo.mp4

		
# Technologies Used
1. POM (Page Object Model) design pattern is followed to implement this hybrid automation framework.
2. Maven use as build tool, all dependencies & build cycle are present inside pom.xml
3. TestNG FW is used 
4. Selenium is used to interact web application through browser
5. java used as programming language  

# Project Structure
```
automationpractice     				  (Project Name)
	src/test/java
		com.automationpractice.pages  (This package include all the page class)
			HomePage.java
			LoginPage.java
			OrderPage.java
			...
			...
		com.automationpractice.Tests  (This package include all test class. Each test class include test method)
			BuyLowestCostDress.java
			...
			...
		com.automationpractice.util   (This package include all utility class, which will use for develop automaton framework)
			CommonUtil.java
			Utilities.java
			WebUtil.java
			...
			...
	src/test/resources  				(It includes all project resources)
		ConfigFiles 					(This folder has all configuration file)   
			EnvironmentConfig.conf		(This configuration file has env related info like app url, user name, pwd etc.)
			...
			...
		Drivers							(This Folder include all drivers like chrome, geko, ie etc)
			chromedriver.exe
			...
		TestData						(This folder include Test data.It will store in .xlsx file. Data provider is used get test data into test function)
			SearchItemWithUser.xlsx
			...
			...
		pom.xml							(this file include all maven dependency & build cycle)
		testng.xml						(this is testng suite, it include all test classes which need to be execute)
	```	
		

