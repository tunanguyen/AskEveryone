<%-- 
    Document   : categories-sidebar
    Created on : Jul 19, 2016, 6:29:57 PM
    Author     : Dell
--%>

<%@page import="cache.CategoriesCache"%>
<%@page import="entity.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="categories_sidebar text-center">
    <p><h3 style="color:#9933ff"><strong>Chủ Đề</strong></h3></p>
    <%
        for (Category category : CategoriesCache.CATEGORIES) {
            int categoryId = category.getCategoryId();
            String categoryName = category.getCategoryName();
    %>
            <a href="index.jsp?categoryId=<%=categoryId%>" class="a_category"><%=categoryName%></a>
    <%
        }
    %>
</div>
