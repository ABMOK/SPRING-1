# Spring, Hibernate, HSQL Database

## Simple Spring project with internal database to store Model records inside.

## Project contains Spring Aplication with XML configuration performing CRUD actions within internal HSQL database with Hibernate ORM. 
## Steps to reproduce:
1. Use SpringXMLConfiguration project as a base project for the application. 
2. Add required dependencies to POM.xml file (refer to: SpringXMLConfigHSQL - POM.xml file)
3. Specify Apache and Hibernate required Beans (refer to: servlet-contaxt.xml ):BasicDataSource, sessionFactory, transactionManager, DAO
4. Specify Hibernate configuration: hibernate.cfg.xml and model mappings: ContactDto.hbm.xml
5. Add Hibernate annotations to the model class to map object with database table
6. Create dao to perform CRUD actions
7. Create controller and view for CRUD operations
8. <prop key="hibernate.hbm2ddl.auto">create</prop> will tell session factory to create table based on Model mapping while the first app run