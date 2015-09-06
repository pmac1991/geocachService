<%@ page import="geoservice.Geocatch" %>



<div class="fieldcontain ${hasErrors(bean: geocatchInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="geocatch.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${geocatchInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: geocatchInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="geocatch.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${geocatchInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: geocatchInstance, field: 'address', 'error')} ">
	<label for="address">
		<g:message code="geocatch.address.label" default="Address" />
		
	</label>
	<g:textField name="address" value="${geocatchInstance?.address}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: geocatchInstance, field: 'picture', 'error')} ">
	<label for="picture">
		<g:message code="geocatch.picture.label" default="Picture" />
		
	</label>
	<input type="file" id="picture" name="picture" />
</div>

<div class="fieldcontain ${hasErrors(bean: geocatchInstance, field: 'radius', 'error')} required">
	<label for="radius">
		<g:message code="geocatch.radius.label" default="Radius" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="radius" type="number" min="1" max="350" value="${geocatchInstance.radius}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: geocatchInstance, field: 'lat', 'error')} required">
	<label for="lat">
		<g:message code="geocatch.lat.label" default="Lat" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="lat" value="${fieldValue(bean: geocatchInstance, field: 'lat')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: geocatchInstance, field: 'lon', 'error')} required">
	<label for="lon">
		<g:message code="geocatch.lon.label" default="Lon" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="lon" value="${fieldValue(bean: geocatchInstance, field: 'lon')}" required=""/>
</div>

