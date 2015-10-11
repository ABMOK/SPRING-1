# Spring
## Setting up the simple page using Spring MVC pattern
## Development tools:
1. Java Development Kit: https://docs.oracle.com/javase/7/docs/webnotes/install/windows/jdk-installation-windows.html
2. MAVEN: https://maven.apache.org/
3. Eclipse: http://wiki.eclipse.org/Eclipse/Installation

## STEPS TO REPRODUCE:
1. Either from IDE or CMD create maven-archetype-webapp,
2. In POM.xml file Specify Spring version and required dependencies,
3. Create folder under WEB-INF folder and put an empty XML configuration file with the name root-context.xml inside this folder,
4. Create folder under WEB-INF folder and put an empty XML configuration file with the name servlet-context.xml inside this folder,
5. Edit web.xml file define Spring Application ContextLoaderListener and to point root-context.xml, and DispatcherServlet to point servlet-context.xml
6. Prepare all the xml files as in the example to contain all the required annotations
7. Create Java model and controller, and place jsp View file under views folder
8. Deploy application to Tomcat. 
http://localhost:8080/1-SpringAppXML/ shows index.jsp
http://localhost:8080/1-SpringAppXML/welcome shows welcome.jsp with a message from JAVA controller