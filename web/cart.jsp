
<%@ page import="java.text.DecimalFormat" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>AZZAN SHOP</title> 
        <link rel="icon" href="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBe7T4-lra4yTKDO-5v3IsIM467Qw_4pss8A&usqp=CAU">
        <!-- Bootstrap icons-->
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
            rel="stylesheet"
            />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <%@include file="components/navBarComponent.jsp" %>

        <!-- Product section-->
        <section class="py-5">
            <div class="container" style="min-height: 1000px">
                <c:choose>
                    <c:when test="${sessionScope.carts==null||sessionScope.carts.size()==0}">
                        <h1>List Cart is Empty</h1>
                    </c:when>
                    <c:otherwise>
                        <h3>Danh sách sản phẩm</h3>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Tên</th>
                                    <th scope="col">Ảnh</th>
                                    <th scope="col">Giá</th>
                                    <th scope="col">Số lượng</th>
                                    <th scope="col">Tổng tiền</th>
                                    <th scope="col">Chức năng</th>
                                </tr>
                            </thead>
                            <tbody>


                                <c:forEach items="${carts}" var="C">
                                    <c:set var="price" value="${C.value.product.price}"/>
                                    <%
                                                                       //   double originalNumber = (double) pageContext.getAttribute("price"); // Lấy số từ biến trong JSP
                                                                 //      DecimalFormat formatter = new DecimalFormat("###,###"); // Định dạng số
                                                                 //         String formattedNumber = formatter.format(originalNumber); // Áp dụng định dạng     
                                    %>
                                <form action="update-quantity">
                                    <tr>
                                    <input type="hidden" name="productId" value="${C.value.product.id}"/>
                                    <th scope="row">${C.value.product.id}</th>
                                    <td>${C.value.product.name}</td>
                                    <td><img src="${C.value.product.imageUrl}" width="50"/></td>
                                    <td>${C.value.product.price}</td>
                                    <%-- sua format <td><%=formattedNumber%></td> --%> 
                                    <td><input onchange="this.form.submit()" type="number" name="quantity" value="${C.value.quantity}" /></td>
                                        <%--<td>
        <input type="button" value="-" onclick="decreaseValue(this)">
        <input type="text" name="quantity" value="${C.value.quantity}" readonly>
        <input type="button" value="+" onclick="increaseValue(this)">
    </td>

<script>
    function increaseValue(input) {
        var value = parseInt(input.previousElementSibling.value, 10);
        value = isNaN(value) ? 0 : value;
        value++;
        input.previousElementSibling.value = value;
    }

    function decreaseValue(input) {
        var value = parseInt(input.nextElementSibling.value, 10);
        value = isNaN(value) ? 0 : value;
        value--;
        input.nextElementSibling.value = value < 0 ? 0 : value;
    }
</script>
                                        --%>
                                    
                                    <c:set var="tongtien" value="${C.value.product.price*C.value.quantity}"/>
                                                           <%
                                    
                                                              double originalNumber2 = (double) pageContext.getAttribute("tongtien"); // Lấy số từ biến trong JSP
                                                          DecimalFormat formatter = new DecimalFormat("###,###"); // Định dạng số
                                                                String formattedNumber2 = formatter.format(originalNumber2); // Áp dụng định dạng
               
                        %>
                                    <td>${C.value.product.price*C.value.quantity}</td>
                                 <!--   format  <td><%=formattedNumber2%></td> -->
                        
                                    <td><a href="delete-cart?productId=${C.value.product.id}" class="btn btn-outline-danger"><i class="bi bi-trash"></i>Xóa</a></td>
                                    </tr>
                                </form>
                            </c:forEach>
                            </tbody>
                        </table>
                        <c:set var="totalMoney" value="${totalMoney}"/>

                        <%
                                    
                                                         //        double originalNumber1 = (double) pageContext.getAttribute("totalMoney"); // Lấy số từ biến trong JSP
                                                        //      DecimalFormat formatter = new DecimalFormat("###,###"); // Định dạng số
                                                          //       String formattedNumber1 = formatter.format(originalNumber1); // Áp dụng định dạng
               
                        %>
                        <h3>Tổng tiền: ${totalMoney}</h3>
                        <%-- sua format tien--%>
                        <%--<h3>Tổng tiền:<%=formattedNumber1%></h3> --%>
                        <a href="checkout" class="btn btn-success w-25">Thanh toán</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>
        <%@include file="components/footerComponent.jsp" %>
    </body>
</html>
