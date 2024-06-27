
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
              rel="stylesheet" />
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>

        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <style>
            body{
                background-color: #D8D9DB
            }
        </style>
    </head>
    <body>
        <%@include file="components/navBarComponent.jsp" %>
        <!-- Header-->

        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row">
                    <div class="col-md-3 mb-5">
                        <h3>Danh sách thể loại</h3>
                        <ul class="list-group category_block">
                            <c:forEach items="${listCategories}" var="C">
                                <li class="list-group-item text-white ${tag == C.cid? "active":""}"><a class="x-vip" href="category?categoryId=${C.cid}"><h5>${C.cname}</h5></a></li>
                                        </c:forEach>
                        </ul>
                    </div>
                    <div class="col-md-9">
                        <h3>Danh sách sản phẩm</h3>
                        <c:choose>
                            <c:when test="${listProducts==null || listProducts.size()==0}">
                                Không có sản phẩm
                            </c:when>
                            <c:otherwise>
                                <nav aria-label="Page navigation example" class="d-flex justify-content-center">
                                    <ul class="pagination">
                                        <li class="page-item"><a class="page-link" href="home?page=${page-1}">Previous</a></li>
                                            <c:forEach begin="1" end="${totalPage}" var="i">
                                            <li class="page-item ${i == page?"active":""}"><a class="page-link" href="home?page=${i}">${i}</a></li>
                                            </c:forEach>
                                        <li class="page-item"><a class="page-link" href="home?page=${page+1}">Next</a></li>
                                    </ul>
                                </nav>
                            </c:otherwise>
                        </c:choose>

                        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 justify-content-center" >
                            <c:forEach items="${listProducts}" var="P">
                                <div class="col mb-5">
                                    <!--${P.id}-->
                                    <div class="card h-100 card-vip">
                                        <!-- Product image-->
                                        <a href="detail?productId=${P.id}">
                                            <img class="card-img-top object-fit-cover aspect-ratio-1x1"
                                                 src="${P.imageUrl}"
                                                 alt="..."
                                                 />
                                        </a>
                                        <!-- Product details-->
                                        <div class="card-body p-4">
                                            <div class="text-center">
                                                <!-- Product name-->
                                                <h5 class="fw-bolder">${P.name}</h5>
                                                <c:if test="${sessionScope.acc != null}">
                                                    <c:set var="isSelected" value="false" />
                                                    <c:forEach var="item2" items="${listGroupFavorite}">
                                                        <c:if test="${item2.product.id == P.id}"> 
                                                            <c:set var="isSelected" value="true" />
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${isSelected}">
                                                        <a href="./FavoriteController?idProduct=${P.id}&idUser=${sessionScope.acc.uid}&fava=0">  <ion-icon style="font-size: xx-large; color: red;" name="heart"></ion-icon>
                                                        </a></c:if>
                                                    <c:if test="${not isSelected}">
                                                        <a href="./FavoriteController?idProduct=${P.id}&idUser=${sessionScope.acc.uid}&fava=1">
                                                            <ion-icon  style="font-size: xx-large" name="heart-outline" > </ion-icon>
                                                        </a> </c:if>


                                                </c:if>



                                                <!-- Product reviews-->
                                                <div
                                                    class="d-flex justify-content-center small text-warning mb-2"
                                                    >
                                                    <div class="bi-star-fill">${P.tiltle}</div>

                                                </div>
                                                <!-- Product price-->

                                                $${P.price}
                                            </div>
                                        </div>
                                        <!-- Product actions-->
                                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                            <div class="text-center">
                                                <a class="btn btn-outline-dark mt-auto" href="add-to-cart?productId=${P.id}"
                                                   >Thêm vào giỏ hàng</a
                                                > 

                                            </div>  

                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="components/footerComponent.jsp" %>
    </body>
</html>
