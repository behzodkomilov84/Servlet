<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<! doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Products</title>
    <link rel="stylesheet" href="/static/style.css">
</head>
<body>
<div class="animated bounceInDown">
    <div class="container">
        <form action="/secure/products" method="post" name="form1" class="box" onsubmit="return checkStuff()">

            <h4>Product<span>Dashboard</span></h4>
            <h5>Products</h5>
            <label>
                Name: <input type="text" name="product-name" placeholder="product name" autocomplete="off" required>
            </label>
            <i class="typcn typcn-eye" id="eye"></i>
            <label>
                ImageUrl: <input type="text" name="image-url" placeholder="image-url" autocomplete="off">
            </label>

            <button type="submit">Add product</button>

        </form>
    </div>
</div>

<c:forEach var="product" items="${products}">
    <!--- product qo'shish imkoniyati bu yerdan boshlanadi --->
    <p>${product.name}</p>
    <c:choose>
        <c:when test="${not empty product.imageUrl}">
            <img src="${product.imageUrl}">
        </c:when>
        <c:otherwise>
            <img src="data:image/png;base64,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">

        </c:otherwise>
    </c:choose>


    <!--- product qo'shish imkoniyati bu yerda tugaydi --->

    <!--- product o'chirish imkoniyati bu yerda boshlanadi --->
    <form action="/secure/products" method="post">
        <input type="hidden" name="_method" value="DELETE"/>
        <input type="hidden" name="product-id" value="${product.id}"/>
        <button type="submit" name="remove-button" style="color: red">Remove</button>
    </form>

    <!--- product o'chirish imkoniyati bu yerda tugaydi --->

</c:forEach>

</body>
</html>