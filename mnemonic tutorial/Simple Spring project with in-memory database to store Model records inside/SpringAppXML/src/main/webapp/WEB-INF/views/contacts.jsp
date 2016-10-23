<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>New Contact</h2>
 <form:form commandName="contact" method="POST" action="contacts/contactlist.html">
           <table> 
            <tr>  
               <td><form:label path="id">ID:</form:label>  
               <td><form:input path="id"/></td>  
           </tr>   
           <tr>  
               <td><form:label path="firstName">Name:</form:label>  
               <td><form:input path="firstName"/></td>  
           </tr>  
            <tr>  
               <td><form:label path="lastName">Last Name:</form:label>  
               <td><form:input path="lastName"/></td>  
           </tr> 
            <tr>  
               <td><form:label path="phoneNo">Phone number:</form:label></td>  
               <td><form:input path="phoneNo"/></td>  
           </tr>  
              <tr>  
               <td><form:label path="email">E-mail:</form:label></td>  
               <td><form:input path="email"/></td>  
           </tr>  
           <tr>  
           
            <td>&nbsp;</td>  
             <td><input type="submit" value="SAVE"/></td>  
             </tr>  
       </table>   
          </form:form>
     
    
 <c:if test="${!empty contactList}">  
 <table align="center" border="1">  
<tr>
	<td>Id</td>
    <th>Name</th>
    <th>Last name</th>
    <th>Phone</th>
    <th>Mail</th>
</tr>

<c:forEach items="${contactList}" var="contact">
    <tr>
		<td>${contact.id}</td>
        <td>${contact.firstName}</td>
        <td>${contact.lastName}</td>
        <td>${contact.phoneNo}</td>        
        <td>${contact.email}</td>
         <td align="center"><a href="${pageContext.request.contextPath}/contactlist/edit/${contact.id}.html">EDIT</a><br>|<a href="${pageContext.request.contextPath}/contactlist/delete/${contact.id}.html">DELETE</a><br></td> 
         </tr>
</c:forEach>
</table>
</c:if>
 



</body>
</html>