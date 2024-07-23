<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<%@ page import="com.Employee" %>
<%@ page import="com.Salaire" %>
<%@ page import="DAO.EmployeeDAO" %>

<html>
<head>
    <title>Employee List</title>
    <style>
    .bouttonajout{

    width:10%;
    heigth:15%;
    font-size:18px;
     border: none;
     background-color: rgba(255, 255, 255, 0.5);
    background-color: none;
    margin:2%;
    margin-top:-2%;

     color:black;

     margin-bottom:2%;

     padding:0.5%;
    margin-left:-10%;


    }
    a{
    text-decoration: none;
             color: black;
             text-align:center;
    }
    body{
         text-decoration: none;
         color: black;
         margin-left:1%;

     }
     .tableau {
    width:70%;
    margin-top:2%;
     margin-left:25%;
     border:3px solid black;
     font-size:20px;
     padding:2%;
      border-collapse: collapse;
         border-spacing: 0;
            border-left:none;
            border-right:none;
        border-bottom:none;
     }
     thead th {
        padding:2%;
        border-bottom:3px solid black;
     }
     tbody td{
     border-bottom:2px solid #A28279;
     text-align:center;
     padding:1%;
      }
      .bouttoneddit{
      border-radius:10px;
      margin:1%;
      width:30%;
      text-align:center;
           background-color: rgba(255, 255, 255, 0.5);

      border:none;
      }

       .div {
       display:flex;
         justify-content: space-around;
         flex-direction:row;
         font-size:20px;
         margin-top:2%;
         margin-left:20%;
       }
        .bouttonhaut{
         display:flex;
        margin-left:20%;
        margin-right:20%;
        padding:-5%;
        margin-top:-5%;
                 flex-direction:row; justify-content: space-around;


        }
        .all{
         display:flex;
                justify-content: space-around;
                         flex-direction:column;
                         margin-left:15%;
                         margin-top:-35%;
        }
        h3{
        margin-top:0.4%;
        }
        .o{
        margin-top:-7%;
        }
        h2{
        font-size:30px;
        margin-left:10%;
        margin-top:5%;
        }
    </style>
</head>
<body>
<div class="video">
<h2>Liste des employés</h2>
<img src="icone/fff.jpg"  width="500" height="500">


</div>
<div class="all">
<div class="bouttonhaut">
<button class="bouttonajout" ><a href="<%= request.getContextPath()%>/new" > <img src="ajouter-le-dossier.png" alt="ajouter"  width="80" height="80">
</a></button> <button class="bouttonajout"><a href="<%= request.getContextPath()%>/histogramme"><img src="icone/histogramme.png" alt="ajouter"  width="80" height="80"></a></button>
</div>

<table  class="tableau">
    <thead>
        <tr>
            <th><img src="icone/cli.png"  width="40" height="40"></th>
            <th class="tete">Nom</th>
            <th class="tete">Nombrejour</th>
            <th class="tete">Taux jounaliere</th>
            <th class="tete">Salaire</th>
             <th class="tete">Action</th>
        </tr>
    </thead>
    <tbody >

<% List<Employee> listEmployee = (List<Employee>) request.getAttribute("listEmployee");

    for (Employee employee : listEmployee) {
%>
        <tr>
            <td><img src="icone/client.png"  width="40" height="40"></td>
            <td><%= employee.getNom() %></td>
            <td><%= employee.getNombrejour() %></td>
            <td><%= employee.getTaux_jounaliere() %></td>
            <td><%= employee.getSalaire() %></td>
            <td><button class="bouttoneddit"><a href="edit?numEmp=<%= employee.getNumEmp() %>"><img src="icone/editer.png"  width="40" height="40"></a></button>
            <button class="bouttoneddit"><a href="delete?numEmp=<%= employee.getNumEmp() %>"><img src="icone/supprimer.png"  width="40" height="40"></a></button></td>
        </tr>
<%
    }


 %>


    </tbody>
</table>
<div class="div">
<%
    Salaire salaire = (Salaire) request.getAttribute("salaire");
    if (salaire != null) {
%>
    <p><img src="icone/limite.png"  width="50" height="50" class="o"> Minimum: <%= salaire.getMin() %></p>
    <p><img src="icone/limite1.png"  width="50" height="50" class="o"> Maximum: <%= salaire.getMax() %></p>
    <p><img src="icone/somme.png"  width="40" height="40" class="o"> Somme: <%= salaire.getSomme() %></p>
<%
    }
%>
</div>
</div>


</body>
</html>
