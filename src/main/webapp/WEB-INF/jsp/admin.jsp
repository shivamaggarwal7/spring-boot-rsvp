<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Admin View</h2>

<c:forEach items="${users}" var="user">
${user.firstName} ${user.lastName} ${user.dob}${user.rsvpFlag}
</c:forEach>