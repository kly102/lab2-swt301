/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.17
 * Generated at: 2024-02-29 12:09:40 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;

public final class list_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/lib/jakarta.servlet.jsp.jstl-2.0.0.jar", Long.valueOf(1709147287651L));
    _jspx_dependants.put("jar:file:/C:/PRJ301/SE1841/build/web/WEB-INF/lib/jakarta.servlet.jsp.jstl-2.0.0.jar!/META-INF/c.tld", Long.valueOf(1602848772000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public boolean getErrorOnELNotFound() {
    return false;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        <script>\n");
      out.write("            function deleteByID(productID){\n");
      out.write("            if (confirm(\"Are you sure delete Products with ProductID= \" + productID + \" ?\")){\n");
      out.write("                window.location = \"deleteServlet?productID=\" + productID;\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("            function updateByID(productID){\n");
      out.write("            if (confirm(\"Are you sure update Products with ProductID= \" + productID + \" ?\")){\n");
      out.write("                 window.location = \"updateServlet?productID=\" + productID;\n");
      out.write("             }\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("        <h2 style=\"color: black\"><a href=\"add.jsp\">AddNew</a></h2>\n");
      out.write("        <h2 style=\"color: deeppink\"><a href=\"list\">ViewList</a></h2>\n");
      out.write("        <table border=\"1px\"> \n");
      out.write("            <tr>\n");
      out.write("                <th>ProductID</th>\n");
      out.write("                <th>ProductName</th>\n");
      out.write("                <th>SupplierID</th>\n");
      out.write("                <th>Color</th>\n");
      out.write("                <th>PackageType</th>\n");
      out.write("                <th>Size</th>\n");
      out.write("                <th>TaxRate</th>\n");
      out.write("                <th>UnitPrice</th>\n");
      out.write("                <th>RecommendedRetailPrice</th>\n");
      out.write("                <th>TypicalWeightPerUnit</th>\n");
      out.write("                <th>Action</th>    \n");
      out.write("            </tr>\n");
      out.write("            <!-- \n");
      out.write("            Lay du lieu tu sql dung thu vien jsp\n");
      out.write("            -->\n");
      out.write("            ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("        </table>\n");
      out.write("\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fforEach_005f0(jakarta.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    jakarta.servlet.jsp.PageContext pageContext = _jspx_page_context;
    jakarta.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    boolean _jspx_th_c_005fforEach_005f0_reused = false;
    try {
      _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f0.setParent(null);
      // /list.jsp(51,12) name = items type = jakarta.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/list.jsp(51,12) '${requestScope.vector}'",_jsp_getExpressionFactory().createValueExpression(_jspx_page_context.getELContext(),"${requestScope.vector}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
      // /list.jsp(51,12) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setVar("c");
      int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
        if (_jspx_eval_c_005fforEach_005f0 != jakarta.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\n");
            out.write("                <tr>\n");
            out.write("                    <td>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${c.productID}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</td>\n");
            out.write("                    <td>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${c.productName}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</td>\n");
            out.write("                    <td>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${c.supplierID}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</td>\n");
            out.write("                    <td>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${c.color}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</td>\n");
            out.write("                    <td>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${c.packageType}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</td>\n");
            out.write("                    <td>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${c.size}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</td>\n");
            out.write("                    <td>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${c.taxRate}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</td>\n");
            out.write("                    <td>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${c.unitPrice}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</td>\n");
            out.write("                    <td>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${c.recommendedRetailPrice}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</td>\n");
            out.write("                    <td>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${c.typicalWeightPerUnit}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</td>\n");
            out.write("                    <td> \n");
            out.write("                        <a href=\"#\" onclick=\"deleteByID(");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${c.productID}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write(")\">Delete</a>&nbsp;&nbsp;&nbsp;&nbsp;\n");
            out.write("                        <a href=\"#\" onclick=\"updateByID(");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${c.productID}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write(")\">Update</a>&nbsp;&nbsp;&nbsp;&nbsp;\n");
            out.write("                    </td>\n");
            out.write("                </tr>\n");
            out.write("            ");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
            if (evalDoAfterBody != jakarta.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f0.doEndTag() == jakarta.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f0.doFinally();
      }
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
      _jspx_th_c_005fforEach_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fforEach_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fforEach_005f0_reused);
    }
    return false;
  }
}
