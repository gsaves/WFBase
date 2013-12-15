<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
	</head>
	<body>
	<s:form action="sampleSearch">
	<table>
		<tr>
			<td>
			<s:submit value="back"></s:submit>
			</td>
		</tr>
		<tr>
			<td>
				<s:label name="sampleModel.content" label="content"/>
				<s:label name="sampleModel.comment" label="comment"/>
			</td>
		</tr>
	</table>
	
	<s:hidden name="sampleModel.userId"></s:hidden>
	</s:form>
	</body>
</html>