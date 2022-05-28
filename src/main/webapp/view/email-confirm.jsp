<%@ page language="java" contentType="text/html;"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="color-scheme" content="light only">
    <title>BusyMan - Email Confirmation</title>
    <link id="theme" rel="stylesheet" href="/todolist/css/theme-1.css">
    <link rel="stylesheet" href="/todolist/css/style.css">
    <script type="text/javascript">
        $(document).ready(function() {
            var selItem = localStorage.getItem("locales");
            $('#locales').val(selItem ? selItem : 'en');
            $("#locales").change(function() {
                var selectedOption = $('#locales').val();
                if (selectedOption) {
                    localStorage.setItem("locales", selectedOption);
                    window.location.replace('?lang=' + selectedOption);

                }
            });
        });
    </script>
</head>

<body class="calendar__body">
<div class="wrapper">

    <main class="page">
        <div class="container container__message">
            <div class="about-us__block">
                <div class="about-us__title">
                    Email Verification
                </div>
                <div class="about-us__main">
                    <div class="about-us__img">
                        <img src="../images/main.png" alt="main photo">
                    </div>
                    <div class="about-us__info">
                        <div class="about-us__text">
                            <span>A verification email has been sent to ${email}</span>
                            <div>Message may be delayed, please wait...</div>
                        </div>
                        <div class="about-us__mail"><span>Thank you for registration!</span></div>
                    </div>
                </div>
            </div>
        </div>
    </main>

</div>
<script src="/todolist/js/theme.js"></script>
</body>

</html>