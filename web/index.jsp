<%-- 
    Document   : index
    Created on : Jul 18, 2016, 10:12:15 AM
    Author     : ngocn
--%>

<%@page import="DAO.UserDAO"%>
<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    </head>
    <body>
        <form method="POST" action="index.jsp">
            <div class="container">
                <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Required Field</strong></div>
                <div class="form-group">
                    <label for="username">Enter Username</label>
                    <div class="input-group">
                        <input type="text" class="form-control" name="username" id="username" placeholder="Enter Username" required>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email">Enter Password</label>
                    <div class="input-group">
                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email">Enter Email</label>
                    <div class="input-group">
                        <input type="email" class="form-control" id="email" name="email" placeholder="Enter Email" required>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <input type="submit" name="submit" id="submit" value="Register" class="btn btn-info pull-right">
                </div>
                <br>
                <br>
                <%
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String email = request.getParameter("email");
                    if (request.getParameter("submit") != null) {
                        if ("".equals(username)) {
                            return;
                        } else if ("".equals(password)) {
                            return;
                        } else if ("".equals(email)) {
                            return;
                        } else {
                            if (UserDAO.getInstance().cheackUsernameExist(username) == true) {
                %>
                <div class="form-group">
                    <div class="alert alert-warning">
                        <strong>Warning!</strong> Username has been exist!
                    </div>
                </div>
                <%
                } else if (UserDAO.getInstance().cheackEmailExist(email) == true) {
                %>
                <div class="form-group">
                    <div class="alert alert-warning">
                        <strong>Warning!</strong> Email has been exist!
                    </div>
                </div>
                <%
                } else {
                    User user = new User(username, password, email);
                    boolean check = UserDAO.getInstance().createAccount(user);
                    if (check == true) {
                %>
                <div class="form-group">
                    <div class="alert alert-success">
                        <strong>Success!</strong> Register successfully!
                    </div>
                </div>
                <%
                } else {
                %>
                <div class="form-group">
                    <div class="alert alert-warning">
                        <strong>Warning!</strong> Register failure!
                    </div>
                </div>
                <%
                                }
                            }
                        }
                    }
                %>
            </div>
        </form>
    </body>
</html>
