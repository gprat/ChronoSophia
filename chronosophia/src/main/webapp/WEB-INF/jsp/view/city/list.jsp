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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des Villes</title>
</head>
<body>
	<p>Villes</p>
	<TABLE BORDER="1">
		<TR>
			<TH>Nom</TH>
			<TH>Pays</TH>
		</TR>
		<c:forEach items="${cities}" var="city">
			<TR>
				<TD><c:out value="${city.name }"></c:out></TD>
				<TD><c:out value="${city.country.name }"></c:out></TD>
				<TD>
					<spring:url value="/city/${city.idCity}/delete" var="deleteUrl" />
					<spring:url value="/city/${city.idCity}/update" var="updateUrl" />
					<button onclick="location.href='${updateUrl}'">Mettre à jour</button>
				  	<form:form method="post" action="${deleteUrl}" style="display: inline;"> <input type="submit" value="Supprimer" /> </form:form>
				 </TD>
			</TR>
		</c:forEach>
	</TABLE>
</body>
</html>
