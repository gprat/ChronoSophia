<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
			</TR>
		</c:forEach>
	</TABLE>
</body>
</html>
