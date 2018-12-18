<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form method="post" action="/validateEmail">
 <label>Enter email id</label><br>
 <input type="text" name="email" autocomplete="true"><br>
 <input type="submit">
</form>
<c:if test="${not empty error}">
   Error: ${error}
</c:if>
<c:if test="${not empty success}">
   Success : ${success}
</c:if>
<c:if test="${not empty failure}">
   Failure : ${failure}
</c:if>