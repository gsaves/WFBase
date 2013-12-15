<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="include/header.jsp" />
<html>
	<head>
	<SCRIPT type="text/javascript">
	function getDetail(ProcessInstanceId){
		document.getElementsByName("sampleModel.id")[0].value = ProcessInstanceId;
		document.forms[0].submit();
	}
	</SCRIPT>
	</head>
	<body>
	<s:form action="sampleDetail">
	<table>
		<tr>
			<td>
			<s:select name="sampleModel.userId" list="sampleModel.usrList" listKey="id" listValue="name"></s:select>
			<s:submit value="search task" action="sampleSearch"></s:submit>
			<s:textarea name="sampleModel.content"></s:textarea>
			<s:textarea name="sampleModel.comment"></s:textarea>
			<s:submit value="draft a task by fozzie" action="draft"></s:submit>
			</td>
		</tr>
		<tr>
			<td>
				<table>
				<s:iterator value="sampleModel.tskList" var="task">
				<tr>
				<td>
					<s:property value="createTime" />
				</td>
				<td>
					<A href="javascript: getDetail(<s:property value='processInstanceId' />);"><s:property value="name" /></A>
				</td>
				<td>
					<s:property value="assignee" />
				</td>
				</tr>
				</s:iterator>
				</table>
			</td>
		</tr>
	</table>
	
	<s:hidden name="sampleModel.id"></s:hidden>
	</s:form>
	</body>
</html>