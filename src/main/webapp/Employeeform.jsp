<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.Employee" %>
<%@ page import="DAO.EmployeeDAO" %>

<%
    String action = request.getParameter("action");
    Employee employee = (Employee) request.getAttribute("employee");
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter Employées </title>
    <style>
    body{

     background-size: cover;
        display: flex;
        align-items: center;
        justify-content: center;
        overflow: hidden;
        backdrop-filter: blur(10px); /* Ajustez la valeur de flou selon vos préférences */
            margin: 0;
            padding: 0;

               z-index: -1;
    }

    .cli{
    background-color: #85C1E9 ;
    width:50%;
    heigth:100%;
    border-radius:20px;
    margin-top:-5%;
     z-index: 56;




    }
    .cli form{
    padding:2%;
    }
    input{
    width:70%;
    padding:3%;
    margin-left:5%;


    border-radius:20px;
    border:1px solid #D0E2CF;

    }
    label{
      display:flex;
        flex-direction:column;
        justify-content:space-around;
        pading-left:15%;
        margin-top:5%;
    }
    img{
    vertical-align: middle;
    margin-left:5%;
    }
    .boutton{
    background-color:#EEB708;
    border:none;
    border-radius:30px;
    width:15%;
    padding:2%;
    margin-left:85%;
    margin-top:5%;

 }
 .bouttonajout{
 background-color:transparent;
 border:none;
 margin-left:90%;
 margin-top:5%;
 }
    </style>

</head>
<body>

<div class="cli">
<button class="bouttonajout" ><a href="<%= request.getContextPath()%>/listEmployee" > <img src="icone/xx.png" width="40" height="40"/></a></button>

<%
    if (employee != null) {
%>
    <form action="update" method="post">
         <input type="hidden" name="numEmp" value="<%= employee.getNumEmp() %>">
<%
    } else {
%>
    <form action="insert" method="post">
        <label for="numEmp"></label>
        <img src="icone/id.png" width="40" height="40"/>
        <input type="text" name="numEmp" class="inputform" value="<%= (employee != null) ? employee.getNumEmp() : "" %>" placeholder="Numeros" required="required" />
<%
    }
%>

    <p>
        <label for="nom"> </label>
        <img src="icone/employe.png" width="40" height="40"/>
        <input type="text" name="nom" class="inputform" value="<%= (employee != null) ? employee.getNom() : "" %>" required="required" placeholder="Nom"/>
    </p>
 <p>
    <label for="nombrejour"> </label>
    <img src="icone/calendar.png" width="40" height="40"/>
    <input type="text" name="nombrejour" class="inputform" value="<%= (employee != null) ? String.valueOf(employee.getNombrejour()) : "" %>" required="required"placeholder="Nombre de jour" />
</p>
    <label for="taux_jounaliere"> </label>
    <img src="icone/accounting.png" width="40" height="40"/>
    <input type="text" name="taux_jounaliere" class="inputform" value="<%= (employee != null) ? String.valueOf(employee.getTaux_jounaliere()) : "" %>" required="required" placeholder="Taux journalier"/>

    <button type="submit" class="boutton" >Enregistrer</button>
</form>

</div>
</body>
</html>
