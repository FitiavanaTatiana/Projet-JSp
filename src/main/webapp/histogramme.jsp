<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.Base64" %>
<html>
<head>
    <title>Histogramme de Salaire</title>
    <style>

    .b{
      background-color:transparent;
        border:none;
        margin-left:93%;
        margin-top:3%;

    }
    .n{
    margin-left:40%;

    }
    .F{
    margin-top:-35%;
    }
    </style>
</head>
<body>
<button class="b"><a href="<%= request.getContextPath()%>/listEmployee" class="bouttonajout" ><img src="icone/xx.png" width="40" height="40" /></a></button>

<%
    String base64Image = (String) request.getAttribute("base64Image");
    if (base64Image != null && !base64Image.isEmpty()) {
%>
        <img src="data:image/png;base64,<%= base64Image %>" alt="Histogramme de Salaire" class="n"  width="800" height="600">
<%
    } else {
%>
        <p>Aucune image disponible</p>
<%
    }
%>
<div>
<img src="icone/analyse.jpg" class="F" width="500" height="400">
</div>

</body>
</html>
