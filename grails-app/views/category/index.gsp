<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Service Provider Categories</title>
</head>

<body>
<h1>Categories</h1>

<g:each in="${categoryList}">
    <g:link controller="serviceProvider" action="bycategory" params="[id: it.id]">
        <div>
            <img src="${it.iconPath}"/>&nbsp;<strong>${it.name}</strong>
        </div>
    </g:link>
</g:each>

</body>
</html>
