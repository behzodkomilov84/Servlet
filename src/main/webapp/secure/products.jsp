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
                ImageUrl: <input type="text" name="image-url" placeholder="image-url" autocomplete="off" required>
            </label>

            <button type="submit">Add product</button>

        </form>
    </div>
</div>

<c:forEach var="product" items="${products}">
    <!--- product qo'shish imkoniyati bu yerdan boshlanadi --->
    <p>${product.name}</p>
    <img src="${product.imageUrl}">
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