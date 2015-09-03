
<%@ page import="geoservice.Geocatch" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'geocatch.label', default: 'Geocatch')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-geocatch" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-geocatch" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list geocatch">
			
				<g:if test="${geocatchInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="geocatch.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${geocatchInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${geocatchInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="geocatch.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${geocatchInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${geocatchInstance?.address}">
				<li class="fieldcontain">
					<span id="address-label" class="property-label"><g:message code="geocatch.address.label" default="Address" /></span>
					
						<span class="property-value" aria-labelledby="address-label"><g:fieldValue bean="${geocatchInstance}" field="address"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${geocatchInstance?.picture}">
				<li class="fieldcontain">
					<span id="picture-label" class="property-label"><g:message code="geocatch.picture.label" default="Picture" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${geocatchInstance?.author}">
				<li class="fieldcontain">
					<span id="author-label" class="property-label"><g:message code="geocatch.author.label" default="Author" /></span>
					
						<span class="property-value" aria-labelledby="author-label"><g:link controller="user" action="show" id="${geocatchInstance?.author?.id}">${geocatchInstance?.author?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${geocatchInstance?.visitors}">
				<li class="fieldcontain">
					<span id="visitors-label" class="property-label"><g:message code="geocatch.visitors.label" default="Visitors" /></span>
					
						<g:each in="${geocatchInstance.visitors}" var="v">
						<span class="property-value" aria-labelledby="visitors-label"><g:link controller="user" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${geocatchInstance?.lat}">
				<li class="fieldcontain">
					<span id="lat-label" class="property-label"><g:message code="geocatch.lat.label" default="Lat" /></span>
					
						<span class="property-value" aria-labelledby="lat-label"><g:fieldValue bean="${geocatchInstance}" field="lat"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${geocatchInstance?.lon}">
				<li class="fieldcontain">
					<span id="lon-label" class="property-label"><g:message code="geocatch.lon.label" default="Lon" /></span>
					
						<span class="property-value" aria-labelledby="lon-label"><g:fieldValue bean="${geocatchInstance}" field="lon"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:geocatchInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${geocatchInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					<g:link action="markAsVisited"><input type="button" value="Mark as visited" class="button"/></g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
