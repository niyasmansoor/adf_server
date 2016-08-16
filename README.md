Read Me – ADF Score Sheet Calculator

INTRODUCTION

This is a java based application to do scoring for Automotive Leads . This application consist of 2 parts . A score calculator SOAP based  java web service server web module and a web based client which requests the web service to calculate lead score for an automotive client .
The server module provides a java based web service which accepts an ADF format XML file which can be uploaded through the web service client module as an ajax upload . Server returns score object with scores against each ADF parameter / criteria as well as a cumulative total score which is converted to a HTML table and presented in the client page through an ajax based request – response method.

SOFTWARE / HARDWARE REQUIREMENTS

To run the application the following is required.

OS : Windows / Linux Java Run time Environment [ Java 7 and above ] Client and server deployment [war] files Application server A client browser

Version used to run / test the solution :
Java : JDK 1.7.0_51 Application Server : Tomcat 7.0.52 Browser : Firefox 40.0.3 / Chrome 45.0.2454.93 OS : Windows 10

To develop the solution , Eclipse IDE [Eclipse Kepler] is used. To build , maven is used.
PRE-REQUISITES

Before running the application , please make sure the below mentioned items are set properly.

JAVA_HOME : Set the JAVA_HOME variable to the installation location of the JRE / JDK installation . For this application to run , java version 7 and above is required.

CATALINA_HOME : This variable should be set to the installation folder of the tomcat server

BUILD APPLICATION

Download adf_server nd adf_client eclipse projects from git [https://github.com/niyasmansoor/] . To build the war files from the eclipse project , please follow the below steps.

Import the adfserver and adfclient projects in eclipse . Go to Run → adfserver to build the adfserver project and generate adfserver.war file Go to Run → adfclient to build the adfserver project and generate adfclient.war file

STEPS TO EXECUTE APPLICATION
Copy adfserver.war to /webapps folder Copy adfclient.war to /webapps folder Start Tomcat by running startup.bat in /bin folder To test if the server is deployed properly , run http://:8080/adfserver . If deployment is done in local host , the url is http://localhost:8080/adfserver. The message “Hello World” will be displayed on successful deployment. To run the application , type the URL : http://:8080/adfclient. [http://localhost:8080/adfclient]. A web page will appear with an option to upload file to calculate the ADF lead score. Upload any XML data file with ADF format [ Reference format used is mentioned in the Attachments section ]. The result score will be displayed in the same page.

APPLICATION EXPLAINED

The ADF Score calculator application has 2 parts. A soap based java web service server      to calculate the        score and a web client to request for the score. Process flow is as follows
User uploads ADF formatted xml using the client application by means of an ajax upload . The uploaded file will be received by a servlet based controller in the client application The file will be converted to a binary stream in the client side by the controller. Controller will then access the web service proxy and pass the uploaded file as binary stream to the proxy. Proxy will call the actual web service in the server side and pass the file as binary stream parameter. Web service calculates the score and returns the score model object to proxy which in turn will return the object to the controller. Controller transforms the score object to html table and sends it back to be received by and ajax response and displays it to the web page.

MAIN SOURCE FILES / FOLDERS – SERVER
ScoreLeadWS.java : This is the web service implementation class which does the adf lead score calculation. LeadScore.java :- It is the model java pojo object that stores the scores of the test criteria

ADFConstants.java : Class which contains all constant values used in the application.

CSVUtils.java : This class parses the csv file data sources to check against the criteria like first name , last name etc

XMLUtils.java : This class parses the xml file to check against the criteria like timeframe, preferred contact type etc

datasources/names.csv : The csv datasource which contains the names of customers .

datasources/zipcodes.csv : The csv datasource which contains the zipcode,city,state information of customers .

MAIN SOURCE FILES / FOLDERS – CLIENT
UploadFile.java : The controller servlet which accepts the client request with the uploaded xml file and calls the proxy to do the score calculation.

index.jsp : the client home webpage where the xml file will be uploaded and the result score will be displayed.

Wsserver folder : IT contains all the webservice client related java files.

ScoreLeadWSProxy.java : The webservice proxy client which is called by the controller servlet and which calls the actual web service in the server.

Webapp/adf : All the files uploaded through the client is stored in this folder

webapp/scripts : Folder which cotains jquery files for the ajax file upload

CONSTRAINTS
Only xml files can be uploaded. The xml file should have the same format as the reference file in the attachment section.

ATTACHMENTS

The reference ADF format XML file [sample.xml] is available as part of the git hub eclipse project .
