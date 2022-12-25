<%@ page language="java"
import ="java.util.*,java.sql.*,Model.*"
 contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ListUser2</title>
</head>
<body>
<h1>ListUser</h1>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
</head>
<body>
<c:set var="arr" value='${requestScope["allUser"]}' />

<h2>HTML Table</h2>
<table>
  <tr>
    <th>ID</th>
    <th>NAME</th>
    <th>Birthday</th>
    <th>Birthplace</th>
  </tr>
<c:forEach items="${arr}" var ="j">  
  <tr>
    <td><c:out value="${j.ID}" /></td>
    <td><c:out value="${j.NAME}" /></td>
    <td><c:out value="${j.BIRTHDAY}" /></td>
    <td><c:out value="${j.BIRTHPLACE}" /></td>
  </tr>
</c:forEach>  
</table>
</body>
</html>