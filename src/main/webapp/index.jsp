<%@ page import="java.util.HashSet" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>index</title>
    <link rel="stylesheet" href="res/css/bootstrap.css"/>
    <link rel="stylesheet" href="res/css/jquery-ui.css" />
    <script src="res/js/jquery.js"></script>
    <script src="res/js/jquery-ui.js"></script>
    <script src="res/js/main.js"></script>

    <style>
        tr:nth-child(even) {background: #88becc}
        th:nth-child(even) {background: #c1d6d3}
    </style>
</head>
<body onload="updateList();">

<script language="javascript">
    function updateList() {
        var xhr = new XMLHttpRequest();
        var doc = document.getElementById('loadingG').innerHTML;
        xhr.open("POST", "/index?updateList=get", true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState < 4){
                document.getElementById('loadingG').innerHTML = '<img src="res/img/loader.gif" > ';
            }else if (xhr.readyState == 4) {
                if(xhr.status == 200) {
                    document.getElementById('loadingG').innerHTML = doc;
                    var data = JSON.parse(xhr.responseText);
                    console.log(data.length);
                    var inf = document.getElementById('performer');
                    for(var i =0;i<data.length;i++){
                        var newOption = document.createElement('option');
                        newOption.value = data[i];
                        newOption.innerHTML = data[i];
//                        console.log(newOption)
                        inf.appendChild(newOption);
                    }
                }

            }
        };
        xhr.send(null);
    }
</script>

<div class="container">
    <div class="row">
        <div class="col-lg-6 col-lg-offset-3">
            <form id="loadingG" class="form-horizontal" action="/index">
                <fieldset>

                    <!-- Form Name -->
                    <legend>Reports</legend>

                    <!-- Text input-->
                    <div class="control-group">
                        <label class="control-label" for="startDate">Start Date</label>

                        <div class="controls">
                            <input class="datepicker" id="startDate" name="startDate" placeholder="" class="input-large" type="text">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="control-group">
                        <label class="control-label" for="endDate">End Date</label>

                        <div class="controls">
                            <input class="datepicker" id="endDate" name="endDate" placeholder="" class="input-large" type="text">

                        </div>
                    </div>


                    <!-- Select Basic -->
                    <div class="control-group">
                        <label class="control-label" for="performer">Performer</label>

                        <div class="controls">
                            <select id="performer" name="performer" class="input-large">
                                <option value="0">All Performers</option>
                                <%--TODO изменить !!!--%>
                                <%--<%--%>
                                    <%--HashSet<String> set =--%>
                                <%--%>--%>
                                <%--<c:forEach items="${performersList}" var="data">--%>
                                    <%--<option value="<c:out value="${data}"/>"> <c:out value="${data}"/> </option>--%>
                                <%--</c:forEach>--%>
                            </select>
                        </div>
                    </div>

                    <!-- Select Basic -->
                    <div class="control-group">
                        <label class="control-label" for="timePeriod">Time Period</label>
                        <div class="controls">
                            <select onchange="dateChange(this.options[this.selectedIndex].value)" id="timePeriod" name="timePeriod" class="input-large">
                                <option value="0">--select--</option>
                                <option value="1">Last Qtr.</option>
                                <option value="2">Last Month</option>
                                <option value="3">Last Calendar Year</option>
                                <option value="4">Current Year to Date</option>
                                <option value="5">Current Qtr to Date</option>
                                <option value="6">Current Month to Date</option>
                            </select>
                        </div>
                    </div>



                    <!-- Button -->
                    <div class="control-group">
                        <label class="control-label" for="submit"></label>

                        <div class="controls">
                            <button id="submit" name="submit" class="btn btn-success">Go</button>
                        </div>
                    </div>

                </fieldset>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Performer</th>
                    <th>Activity</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${data}" var="data">
                    <tr>
                        <td><c:out value="${data.id}" /></td>
                        <td><c:out value="${data.convertedStartDate}" /></td>
                        <td><c:out value="${data.convertedEndDate}" /> </td>
                        <td><c:out value="${data.performer}" /> </td>
                        <td><c:out value="${data.activity}" /></td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>
<%--<jsp:forward page="/data?action=listUser" />--%>
<%--<h2>Enter your name:</h2>--%>
<%--<form action="hello" method="get">--%>
<%--<input type="text" name="username">--%>
<%--<button type="submit">OK</button>--%>
<%--</form>--%>
</body>
</html>