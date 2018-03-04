<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des personnages</title>
</head>
<body>
	<p>Personnages</p>
	
	<spring:url value="/figure/list" var="selectUrl" />
	
	<form:form method="post" modelAttribute="selectFigureForm" action="${selectUrl}" >
		
		<form:label path="category" >Catégorie : </form:label>
		<form:select path="category">
			<form:option value="" label="-----"/>
			<form:options items="${categoryList}" itemValue="name" itemLabel="name" />
		</form:select>
		&nbsp;&nbsp;&nbsp;&nbsp;      
		<form:label path="role" >Rôle : </form:label>
		<form:select path="role">
			<form:option value="" label="-----"/>
			<form:options items="${roleList}" itemValue="name" itemLabel="name" />
		</form:select>
		&nbsp;&nbsp;&nbsp;&nbsp; 
		<input type="submit" value="Filtrer" />
	</form:form>
	<br />

	<TABLE BORDER="1">
		<TR>
			<TH>Prénom</TH>
			<TH>Nom</TH>
			<TH>Date de Naissance</TH>
			<TH>Date de Mort</TH>
			<TH>Opérations</TH>
		</TR>
		<c:forEach items="${figures}" var="figure">
			<TR>
				<TD><c:out value="${figure.firstName}"></c:out></TD>
				<TD><c:out value="${figure.lastName}"></c:out></TD>
				<TD><c:if test="${figure.birthDate!=null}"> <c:out value="${figure.birthDate.toString()}"></c:out> </c:if></TD>
				<TD><c:if test="${figure.deathDate!=null}"> <c:out value="${figure.deathDate.toString()}"></c:out> </c:if></TD>
				<TD>
					<spring:url value="/figure/${figure.idFigure}/delete" var="deleteUrl" />
					<spring:url value="/figure/${figure.idFigure}/update" var="updateUrl" />
					<button onclick="location.href='${updateUrl}'">Mettre à jour</button>
				  	<form:form method="post" action="${deleteUrl}" style="display: inline;"> <input type="submit" value="Supprimer" /> </form:form>
				 </TD>
			</TR>
		</c:forEach>
	</TABLE>
</body>
</html>