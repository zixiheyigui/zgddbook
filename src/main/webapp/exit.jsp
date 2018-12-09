<%
    session.removeAttribute("hasLongined");
    response.sendRedirect("login.jsp");
%>
