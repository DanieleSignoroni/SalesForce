<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///W:\PthDev\Projects\Panthera\SalesForce\WebContent\dtd/xhtml1-transitional.dtd">
<html>
<!-- WIZGEN Therm 2.0.0 as Form - multiBrowserGen = true -->
<%=WebGenerator.writeRuntimeInfo()%>
  <head>
<%@ page contentType="text/html; charset=Cp1252"%>
<%@ page import= " 
  java.sql.*, 
  java.util.*, 
  java.lang.reflect.*, 
  javax.naming.*, 
  com.thera.thermfw.common.*, 
  com.thera.thermfw.type.*, 
  com.thera.thermfw.web.*, 
  com.thera.thermfw.security.*, 
  com.thera.thermfw.base.*, 
  com.thera.thermfw.ad.*, 
  com.thera.thermfw.persist.*, 
  com.thera.thermfw.gui.cnr.*, 
  com.thera.thermfw.setting.*, 
  com.thera.thermfw.collector.*, 
  com.thera.thermfw.batch.web.*, 
  com.thera.thermfw.batch.*, 
  com.thera.thermfw.pref.* 
"%> 
<%
  ServletEnvironment se = (ServletEnvironment)Factory.createObject("com.thera.thermfw.web.ServletEnvironment"); 
  BODataCollector YClientiInseritiBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YClientiInseritiForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YClientiInseritiForm", "YClientiInseriti", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/softre/thip/base/connettori/salesforce/tabelle/YClientiInseriti.js"); 
  YClientiInseritiForm.setServletEnvironment(se); 
  YClientiInseritiForm.setJSTypeList(jsList); 
  YClientiInseritiForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YClientiInseritiForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YClientiInseritiForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YClientiInseritiForm.getMode(); 
  String key = YClientiInseritiForm.getKey(); 
  String errorMessage; 
  boolean requestIsValid = false; 
  boolean leftIsKey = false; 
  boolean conflitPresent = false; 
  String leftClass = ""; 
  try 
  {
     se.initialize(request, response); 
     if(se.begin()) 
     { 
        YClientiInseritiForm.outTraceInfo(getClass().getName()); 
        String collectorName = YClientiInseritiForm.findBODataCollectorName(); 
                YClientiInseritiBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YClientiInseritiBODC instanceof WebDataCollector) 
            ((WebDataCollector)YClientiInseritiBODC).setServletEnvironment(se); 
        YClientiInseritiBODC.initialize("YClientiInseriti", true, 0); 
        YClientiInseritiForm.setBODataCollector(YClientiInseritiBODC); 
        int rcBODC = YClientiInseritiForm.initSecurityServices(); 
        mode = YClientiInseritiForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YClientiInseritiForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YClientiInseritiBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YClientiInseritiForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YClientiInseritiForm); 
   request.setAttribute("menuBar", menuBar); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="menuBar"/> 
</jsp:include> 
<% 
  menuBar.write(out); 
  menuBar.writeChildren(out); 
%> 
<% 
  WebToolBar myToolBarTB = new com.thera.thermfw.web.WebToolBar("myToolBar", "24", "24", "16", "16", "#f7fbfd","#C8D6E1"); 
  myToolBarTB.setParent(YClientiInseritiForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YClientiInseritiForm.getBodyOnBeforeUnload()%>" onload="<%=YClientiInseritiForm.getBodyOnLoad()%>" onunload="<%=YClientiInseritiForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YClientiInseritiForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YClientiInseritiForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YClientiInseritiBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YClientiInseritiForm.getServlet()%>" method="post" name="YClientiInseritiForm" style="height:100%"><%
  YClientiInseritiForm.writeFormStartElements(out); 
%>

      <table cellpadding="0" cellspacing="0" height="100%" id="emptyborder" width="100%">
        <tr>
          <td style="height:0">
            <% menuBar.writeElements(out); %> 

          </td>
        </tr>
        <tr>
          <td style="height:0">
            <% myToolBarTB.writeChildren(out); %> 

          </td>
        </tr>
        <tr>
          <td>
            <% 
  WebTextInput YClientiInseritiIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YClientiInseriti", "IdAzienda"); 
  YClientiInseritiIdAzienda.setParent(YClientiInseritiForm); 
%>
<input class="<%=YClientiInseritiIdAzienda.getClassType()%>" id="<%=YClientiInseritiIdAzienda.getId()%>" maxlength="<%=YClientiInseritiIdAzienda.getMaxLength()%>" name="<%=YClientiInseritiIdAzienda.getName()%>" size="<%=YClientiInseritiIdAzienda.getSize()%>" type="hidden"><% 
  YClientiInseritiIdAzienda.write(out); 
%>

          </td>
        </tr>
        <tr>
          <td>
            <% 
  WebTextInput YClientiInseritiRCliente =  
     new com.thera.thermfw.web.WebTextInput("YClientiInseriti", "RCliente"); 
  YClientiInseritiRCliente.setParent(YClientiInseritiForm); 
%>
<input class="<%=YClientiInseritiRCliente.getClassType()%>" id="<%=YClientiInseritiRCliente.getId()%>" maxlength="<%=YClientiInseritiRCliente.getMaxLength()%>" name="<%=YClientiInseritiRCliente.getName()%>" size="<%=YClientiInseritiRCliente.getSize()%>" type="hidden"><% 
  YClientiInseritiRCliente.write(out); 
%>

          </td>
        </tr>
        <tr>
          <td height="100%">
            <!--<span class="tabbed" id="mytabbed">-->
<table width="100%" height="100%" cellpadding="0" cellspacing="0" style="padding-right:1px">
   <tr valign="top">
     <td><% 
  WebTabbed mytabbed = new com.thera.thermfw.web.WebTabbed("mytabbed", "100%", "100%"); 
  mytabbed.setParent(YClientiInseritiForm); 
  mytabbed.write(out); 
%>

     </td>
   </tr>
   <tr>
     <td height="100%"><div class="tabbed_pagine" id="tabbedPagine" style="position: relative; width: 100%; height: 100%;">
            </div><% mytabbed.endTabbed();%> 

     </td>
   </tr>
</table><!--</span>-->
          </td>
        </tr>
        <tr>
          <td style="height:0">
            <% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(YClientiInseritiForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YClientiInseritiForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YClientiInseritiForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YClientiInseritiBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YClientiInseritiForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YClientiInseritiBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YClientiInseritiBODC.getErrorList().getErrors()); 
           if(YClientiInseritiBODC.getConflict() != null) 
                conflitPresent = true; 
     } 
     else 
        errors.add(new ErrorMessage("BAS0000010")); 
  } 
  catch(NamingException e) { 
     errorMessage = e.getMessage(); 
     errors.add(new ErrorMessage("CBS000025", errorMessage));  } 
  catch(SQLException e) {
     errorMessage = e.getMessage(); 
     errors.add(new ErrorMessage("BAS0000071", errorMessage));  } 
  catch(Throwable e) {
     e.printStackTrace(Trace.excStream);
  }
  finally 
  {
     if(YClientiInseritiBODC != null && !YClientiInseritiBODC.close(false)) 
        errors.addAll(0, YClientiInseritiBODC.getErrorList().getErrors()); 
     try 
     { 
        se.end(); 
     }
     catch(IllegalArgumentException e) { 
        e.printStackTrace(Trace.excStream); 
     } 
     catch(SQLException e) { 
        e.printStackTrace(Trace.excStream); 
     } 
  } 
  if(!errors.isEmpty())
  { 
      if(!conflitPresent)
  { 
     request.setAttribute("ErrorMessages", errors); 
     String errorPage = YClientiInseritiForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YClientiInseritiBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YClientiInseritiForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
