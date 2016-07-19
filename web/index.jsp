<%-- 
    Document   : index
    Created on : Jul 18, 2016, 10:12:15 AM
    Author     : ngocn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--Jquery-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
      integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" 
      crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" 
      integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" 
      crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" 
        integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" 
crossorigin="anonymous"></script>


<!--CSS file-->
<link rel="stylesheet" type="text/css" href="css/myStyle.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container-fluid">
            <jsp:include page="categories-sidebar.jsp"/>
            <div class="main">
                <div class="write_question">
                    <div class="div_question_content text-center">
                        <textarea class="textarea_question_content" title="Bạn muốn hỏi gì?" placeholder="Bạn muốn hỏi gì?"></textarea>
                    </div>
                    <div class="submit_question">
                        <div class="dropdown dropdown_categories">
                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Thể loại
                                <span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li><a href="#">Ăn - uống</a></li>
                                <li><a href="#">Sức khỏe</a></li>
                                <li><a href="#">Thời trang</a></li>
                                <li><a href="#">Làm đẹp</a></li>
                                <li><a href="#">Du lịch</a></li>
                                <li><a href="#">Âm nhạc - Giải trí</a></li>
                                <li><a href="#">Thể thao</a></li>
                                <li><a href="#">Máy tính - Internet</a></li>
                                <li><a href="#">Khoa học tự nhiên</a></li>
                                <li><a href="#">Khoa học xã hội</a></li>
                            </ul>
                        </div>
                        <div class="div_submit">
                            <button type="button" class="btn btn-primary btn_submit">Đăng</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
