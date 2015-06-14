<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Services By Category</title>
</head>

<body>
<h1>Service Provider Categories</h1>

<g:each in="${serviceProviderList}">
    <div>
        ${it.name} <img src="${it.imagePath}"/><br>
        ${it.website}
        Description: ${it.description} <br>
        Locations:<br>
        <ul>
            <g:each in="${it.locations}" var="location">
                <li>${location.name}</li>
            </g:each>
        </ul>
    </div>
</g:each>

</body>
</html>
