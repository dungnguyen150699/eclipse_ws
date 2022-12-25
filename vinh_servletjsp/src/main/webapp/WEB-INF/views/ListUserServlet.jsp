<%@ page language="java"
import ="java.util.*,java.sql.*,Model.*"
 contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ListUser1</title>
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
<% 
List<User> arr = (ArrayList) request.getAttribute("allUser");
%>
<h2>HTML Table</h2>
<% if(arr.size()>0)
{  %>
<table>
  <tr>
    <th>ID</th>
    <th>NAME</th>
    <th>Birthday</th>
    <th>Birthplace</th>
  </tr>
  <% for(User i : arr) 
  { %>
  <tr>
    <td><% out.print(i.getID()); %></td>
    <td><% out.print(i.getNAME()); %></td>
    <td><% out.print(i.getBIRTHDAY()); %></td>
    <td><% out.print(i.getBIRTHPLACE()); %></td>
  </tr>
  <% } %>
</table>
<% } 
 	else out.print("Error :b"); %>
</body>
</html>