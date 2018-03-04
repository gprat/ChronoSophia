<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des positions</title>
</head>
<body>
	<p>Position</p>
	<TABLE BORDER="1">
		<TR>
			<TH>Nom</TH>
		</TR>
		<c:forEach items="${roles}" var="role">
			<TR>
				<TD><c:out value="${role.name}"></c:out></TD>
			</TR>
		</c:forEach>
	</TABLE>
</body>
</html>