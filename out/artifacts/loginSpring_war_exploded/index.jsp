<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 11/3/2016
  Time: 11:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@include file="include.jsp"%>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form name="myForm1" action="login" method="post" modelAttribute="user">
  <table>
    <tr>
      <td><font face="verdana" size="2px">Name:</font></td>
      <td><input type="text" name="userName" onblur = "validateName(1)"></td>
      <td id = "nameRemind1"></td>
    </tr>
    <tr>
      <td><font face="verdana" size="2px">Password:</font></td>
      <td><input type="password" name="userPassword" onblur = "validatePassword(1)"></td>
      <td id = "passwordRemind1"></td>
    </tr>
  </table>
  <input type="submit" value="Login">
</form>
<%--<form name="myForm2" action="signup" method="post">--%>
  <%--<table>--%>
    <%--<tr>--%>
      <%--<td><font face="verdana" size="2px">Name:</font></td>--%>
      <%--<td><input type="text" name="userName" onblur = "validateName(2)"></td>--%>
      <%--<td id = "nameRemind2"></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
      <%--<td><font face="verdana" size="2px">Password:</font></td>--%>
      <%--<td><input type="password" name="userPassword" onblur = "validatePassword(2)"></td>--%>
      <%--<td id = "passwordRemind2"></td>--%>
    <%--</tr>--%>
  <%--</table>--%>
  <%--<input type="submit" value="Sign Up">--%>
<%--</form>--%>
<script>
  var isNameInput = false;
  var isPasswordInput = false;

  function validateName(x) {
    var name = document.forms["myForm" + x]["userName"].value;
    if (name == null || name == "") {
      //alert("Name must be filled out");
      document.getElementById('nameRemind' + x).innerHTML = "Name must be filled out";
      //isNameInput = false;
    }else{
      document.getElementById('nameRemind' + x).innerHTML = "";
      //isNameInput = true;
    }
  }

  function validatePassword(x) {
    var password = document.forms["myForm" + x]["userPassword"].value;
    if (password == null || password == "") {
      //alert("Password must be filled out");
      document.getElementById('passwordRemind' + x).innerHTML = "Password must be filled out";
      //isPasswordInput = false;
    }else{
      document.getElementById('passwordRemind' + x).innerHTML = "";
      //isPasswordInput = true;
    }
  }
  /*
   function validateForm() {
   //return isNameInput && isPasswordInput;
   if(!isNameInput || !isPasswordInput){
   alert("All fields must be filled out");
   return false;
   }
   }
   */
</script>
