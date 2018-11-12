<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<jsp:include page="common/header.jsp"/>
<section class="Hui-article-box">
    <nav class="breadcrumb"><i class="Hui-iconfont"></i> <a href="/" class="maincolor">首页</a> <span class="c-999 en">&gt;</span><span class="c-666">空白页</span></nav>
    <div class="Hui-article">
        <article class="cl pd-20">
            <shiro:hasPermission name="会员列表">
                <input class="btn btn-success radius" type="button" value="成功">
            </shiro:hasPermission>
        </article>
    </div>
</section>
<jsp:include page="common/footer.jsp"/>