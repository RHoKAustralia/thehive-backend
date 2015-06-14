<%@ page import="au.com.directoryservice.Category" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home</title>
</head>

<body>
<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div id="page-body" role="main">
    <div class="page-title  col-xs-12">How can we help you today?</div>

    <div class="row">
        <g:link controller="serviceProvider" action="index" class="col-xs-6">
            <div class="box">
                <g:img dir="assets" file="icon-search.png"/><br/>
                <span>Search</span>
            </div>
        </g:link>

        <g:link controller="serviceProvider" action="whatsontoday" class="col-xs-6">
            <div class="box">
                <g:img dir="assets" file="icon-calendar.png"/><br/>
                <span>What's On Today</span>
            </div>
        </g:link>
    </div>
    <br/>

    <div class="row">
        <div class="col-xs-6">
            <div class="box">
                <g:img dir="assets" file="icon-question.png"/><br/>
                <span>Ask a Question</span>
            </div>
        </div>

        <g:link controller="serviceProvider" action="bycategory"
                params="[id: au.com.directoryservice.Category.findByName('Kiosk')]"
                class="col-xs-6">
            <div class="box">
                <g:img dir="assets" file="icon-kiosk.png"/><br/>
                <span>Find a Kiosk</span>
            </div>
        </g:link>
    </div>
    <br/>

    <div class="row">
        <div class="col-xs-6">
            <div class="box">
                <g:img dir="assets" file="icon-sponsor.png"/><br/>
                <span>Sponsored</span>
            </div>
        </div>

        <div class="col-xs-6">
            <div class="box">
                <g:img dir="assets" file="icon-finder.png"/><br/>
                <span>Fact Finder</span>
            </div>
        </div>
    </div>

</div>
</body>
</html>
