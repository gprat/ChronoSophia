<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajouter une catégorie</title>
</head>
<body>
 <h1>Ajouter une catégorie</h1>
 <spring:url value="/category/save" var="categoryActionUrl" />
	<form:form method="post" modelAttribute="categoryForm" action="${categoryActionUrl}">
		<form:label path="CategoryName" >Nom de la  catégorie : </form:label>
		<form:input path="CategoryName"/>
		<br />
		<input type="submit" value="Sauver" />
		<input type="reset" value="Reinitialiser" />
	</form:form>
</body>
</html>