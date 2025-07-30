<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="static/style.css">
</head>
<body id="particles-js">

<div class="animated bounceInDown">
    <div class="container">
        <span class="error animated tada" id="msg"></span>
        <form action="/login" method="post" name="form1" class="box" onsubmit="return checkStuff()">
            <h4>Admin<span>Dashboard</span></h4>
            <h5>Sign in to your account.</h5>
            <input type="text" name="login" placeholder="login" autocomplete="off">
            <i class="typcn typcn-eye" id="eye"></i>
            <input type="password" name="password" placeholder="Passsword" id="pwd" autocomplete="off">
            <label>
                <input type="checkbox">
                <span></span>
                <small class="rmb">Remember me</small>
            </label>
            <a href="#" class="forgetpass">Forget Password?</a>
            <input type="submit" value="Sign in" class="btn1">
        </form>
        <a href="registration.html" class="dnthave">Don’t have an account? Sign up</a>
    </div>
    <div class="footer">
        <span>Made with <i class="fa fa-heart pulse"></i> <a href="https://www.google.de/maps/place/Augsburger+Puppenkiste/@48.360357,10.903245,17z/data=!3m1!4b1!4m2!3m1!1s0x479e98006610a511:0x73ac6b9f80c4048f"><a href="https://codepen.io/lordgamer2354">Behzod Komilov</a></a></span>
    </div>
</div>

<c:out value="${AuthMessageError}"/>

</body>
</html>