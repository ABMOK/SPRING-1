<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="content">
  <h4><input type="button" class="btn btn-primary" onclick="location.href='administration/newuser'" value="new"></h4>
        
<table class="table table-striped table-bordered table-hover table-condensed"> 
<tr>
	<td>usFirstName</td>
    <th>usLastName</th>
    <th>usLogin</th>
</tr>
<c:forEach items="${users}" var="user">
    <tr>
	<td>${user.name}</td>
        <td>${user.surname}</td>
        <td>${user.regdate}</td>
        <td><div  type="button" class="btn  btn-default btn-xs">
                <a href='<c:url value="/administration/delete/${user.iduser}"/>'>delete</a>
            </div></td>
        </tr>
</c:forEach>
</table>


    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">

        </div>
        <div class="panel-body">
            <p>Lista uzytkownikow</p>
            <table
                    class="table table-striped table-bordered table-hover table-condensed">
                <tr>
                    <td>Id</td>
                    <td>First Name</td>
                    <th>Last Name</th>
                    <th>Login</th>
                    <th>Mail</th>
                    <th>Details</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.iduser}</td>
                        <td>${user.name}</td>
                        <td>${user.surname}</td>
                        <td>${user.mail}</td>
                        <td>${user.login}</td>
                        <td>${user.password}</td>
                        <td><a
                                href='<c:url value="/administration/user/details/${user.iduser}" />'
                                type="button" class="btn btn-info btn-xs">DETAILS</a></td>
                        <td><a
                                href='<c:url value="/administration/user/delete/${user.iduser}" />'
                                type="button" class="btn btn-danger btn-xs">DELETE</a>
                            <a
                                    href='<c:url value="/administration/user/edit/${user.iduser}" />'
                                    type="button" class="btn btn-warning btn-xs">EDIT</a>
                            <a
                                    href='<c:url value="/administration/role/assign/${user.iduser}" />'
                                    type="button" class="btn btn-success btn-xs">ADD ROLE</a>

                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</div>