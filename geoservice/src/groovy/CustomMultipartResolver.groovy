package geoservice

import org.springframework.web.multipart.commons.*
import javax.servlet.http.HttpServletRequest
import org.apache.commons.fileupload.servlet.ServletRequestContext
import org.apache.commons.fileupload.FileUploadBase

class CustomMultipartResolver extends CommonsMultipartResolver {

/* The standard implementation for isMultipart() calls ServletFileUpload.isMultipartContent() which returns false if request method is not POST.

    In order for HTTP PUT to work with multipart form data, we need to override isMultipart() to call a custom isMultipartContent() method rather than ServletFileUpload.isMultipartContent()
    *
    */

@Override
public boolean isMultipart(HttpServletRequest request)
{ def isMultipart = (request != null && (this.isMultipartContent(request)))} // replaces call to ServletFileUpload.isMultipartContent() return isMultipart }

private boolean isMultipartContent(HttpServletRequest request) {
if (!"POST".equalsIgnoreCase(request.getMethod()) && !"PUT".equalsIgnoreCase(request.getMethod()))
{ return false; }

return FileUploadBase.isMultipartContent(new ServletRequestContext(request));
}

}