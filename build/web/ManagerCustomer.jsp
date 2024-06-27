

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
       if (request.getSession().getAttribute("role_admin") == null) {
           response.sendRedirect("home");
       }
%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>AZZAN SHOP</title> 
        <link rel="icon" href="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBe7T4-lra4yTKDO-5v3IsIM467Qw_4pss8A&usqp=CAU">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/ManagerProduct.css" rel="stylesheet" type="text/css"/>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
            rel="stylesheet"
            />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <style>
            body{
                font-size: medium;
            }
        </style>
        <script>
            function back() {
                window.location.href = "home";
            }
            function doDelete(id)
            {
                var c = confirm("Are you sure?");
                if (c)
                {
                    window.location.href = "delete?pid=" + id;
                }
            }
        </script>

    <body>

        <div class="container">
            <div class="table-wrapper">
                <div class="">
                    <div class="row">
                        <h2>Quản Lý <b>Tài Khoản</b></h2>   
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tài khoản</th>
                            <th>Mật khẩu</th>
                            <th>Vai trò</th>
                            <th>Trạng thái</th>
                            <th>Chức năng</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${accounts}" var="p">
                            <tr>
                                <td>${p.uid}</td>
                                <td>${p.user}</td>
                                <td>${p.pass}</td>
                                <td>${p.isSell==0?'người bán':'admin'}</td>
                                <td>

                                    <c:choose>
                                        <c:when test="${!p.active}">
                                            <p style="color: red">Bị Khóa</p>
                                        </c:when>    
                                        <c:otherwise>
                                            <p style="color: green">Hoạt động</p>
                                        </c:otherwise>
                                    </c:choose>

                                <td>
                                    <a href="loadAccount?pid=${p.uid}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="clearfix"> 
                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="managerAccount?page=${page-1}">Trước</a></li>
                            <c:forEach begin="1" end="${totalPage}" var="i">
                            <li class="page-item ${i == page?"active":""}"><a class="page-link" href="managerAccount?page=${i}">${i}</a></li>
                            </c:forEach>
                        <li class="page-item"><a class="page-link" href="managerAccount?page=${page+1}">Sau</a></li>
                    </ul>
                </div>
            </div>
            <a href="#">
                <button type="button" class="btn btn-primary" onclick="back()">Quay về trang chủ</button>



                <script src="js/ManagerProduct.js" type="text/javascript"></script>
                </body>
                </html>
