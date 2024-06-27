

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <style>
            img{
                width: 200px;
                height: 120px;
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
                        <div class="col-sm-6">
                            <h2>Quản Lý <b>Đơn Hàng</b></h2>
                        </div>

                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr> 
                            <th><span onclick="change('${sort}')">ID</span></th>
                            <th>Tên khách</th>
                            <th>Tổng tiền</th>
                            <th>Ngày tạo</th>
                            <th>Lưu ý</th>
                            <th>Tình trạng</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listOrder}" var="p">
                            <tr> 
                                <td>${p.getId()}</td>
                                <td>${p.shipping.name}</td>

                                <td>${p.getTotalPrice()} $</td>
                                <td>${p.getCreatedDate()}</td>
                                <td>${p.note}</td>
                                <td>${p.status== true?'Đã giao':'Chưa giao'}</td>
                                
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="#">
                <button type="button" class="btn btn-primary" onclick="back()">Về trang chủ</button>

        </div>
      
        <script src="js/ManagerProduct.js" type="text/javascript"></script>
        <script>
            function change(t){
                window.location.replace('/AzanShop/OrderUserManager?sort='+t);
            }
        </script>
    </body>
</html>


