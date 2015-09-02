
<%@ page import="geoservice.Geocatch" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'geocatch.label', default: 'Geocatch')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-geocatch" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-geocatch" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'geocatch.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'geocatch.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="address" title="${message(code: 'geocatch.address.label', default: 'Address')}" />
					
						<g:sortableColumn property="picture" title="${message(code: 'geocatch.picture.label', default: 'Picture')}" />
					
						<th><g:message code="geocatch.author.label" default="Author" /></th>
					
						<g:sortableColumn property="lat" title="${message(code: 'geocatch.lat.label', default: 'Lat')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${geocatchInstanceList}" status="i" var="geocatchInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${geocatchInstance.id}">${fieldValue(bean: geocatchInstance, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: geocatchInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: geocatchInstance, field: "address")}</td>
					
						<td>${fieldValue(bean: geocatchInstance, field: "picture")}</td>
					
						<td>${fieldValue(bean: geocatchInstance, field: "author")}</td>
					
						<td>${fieldValue(bean: geocatchInstance, field: "lat")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${geocatchInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
