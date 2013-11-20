<%@page import="org.activiti.engine.task.Task"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
	</head>
	<body>
	<p>xxxxxx</p>
		<% List tsk = (List)request.getAttribute("list");
		for(Object obj : tsk){
		    Task ts = (Task)obj;
		    %>
		    <br/><p><%=ts.getName() %></p>
		    <% 
		}
		%>
	</body>
</html>