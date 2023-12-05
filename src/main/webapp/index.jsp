<html>
<body>
<h2>Hello World!</h2>
<%
ServletContext context=getServletContext();
String c=(String)context.getAttribute("NumberOfLinesRead");
%>
Number of Lines read so far : <%=c%>
</body>
</html>
