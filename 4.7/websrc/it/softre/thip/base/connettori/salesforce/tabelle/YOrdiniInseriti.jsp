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
  BODataCollector YOrdiniInseritiBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YOrdiniInseritiForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YOrdiniInseritiForm", "YOrdiniInseriti", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/softre/thip/base/connettori/salesforce/tabelle/YOrdiniInseriti.js"); 
  YOrdiniInseritiForm.setServletEnvironment(se); 
  YOrdiniInseritiForm.setJSTypeList(jsList); 
  YOrdiniInseritiForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YOrdiniInseritiForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YOrdiniInseritiForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YOrdiniInseritiForm.getMode(); 
  String key = YOrdiniInseritiForm.getKey(); 
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
        YOrdiniInseritiForm.outTraceInfo(getClass().getName()); 
        String collectorName = YOrdiniInseritiForm.findBODataCollectorName(); 
                YOrdiniInseritiBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YOrdiniInseritiBODC instanceof WebDataCollector) 
            ((WebDataCollector)YOrdiniInseritiBODC).setServletEnvironment(se); 
        YOrdiniInseritiBODC.initialize("YOrdiniInseriti", true, 0); 
        YOrdiniInseritiForm.setBODataCollector(YOrdiniInseritiBODC); 
        int rcBODC = YOrdiniInseritiForm.initSecurityServices(); 
        mode = YOrdiniInseritiForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YOrdiniInseritiForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YOrdiniInseritiBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YOrdiniInseritiForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YOrdiniInseritiForm); 
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
  myToolBarTB.setParent(YOrdiniInseritiForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YOrdiniInseritiForm.getBodyOnBeforeUnload()%>" onload="<%=YOrdiniInseritiForm.getBodyOnLoad()%>" onunload="<%=YOrdiniInseritiForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YOrdiniInseritiForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YOrdiniInseritiForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YOrdiniInseritiBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YOrdiniInseritiForm.getServlet()%>" method="post" name="YOrdiniInseritiForm" style="height:100%"><%
  YOrdiniInseritiForm.writeFormStartElements(out); 
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
  WebTextInput YOrdiniInseritiIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YOrdiniInseriti", "IdAzienda"); 
  YOrdiniInseritiIdAzienda.setParent(YOrdiniInseritiForm); 
%>
<input class="<%=YOrdiniInseritiIdAzienda.getClassType()%>" id="<%=YOrdiniInseritiIdAzienda.getId()%>" maxlength="<%=YOrdiniInseritiIdAzienda.getMaxLength()%>" name="<%=YOrdiniInseritiIdAzienda.getName()%>" size="<%=YOrdiniInseritiIdAzienda.getSize()%>" type="hidden"><% 
  YOrdiniInseritiIdAzienda.write(out); 
%>

          </td>
        </tr>
        <tr>
          <td>
            <% 
  WebTextInput YOrdiniInseritiRAnnoOrd =  
     new com.thera.thermfw.web.WebTextInput("YOrdiniInseriti", "RAnnoOrd"); 
  YOrdiniInseritiRAnnoOrd.setParent(YOrdiniInseritiForm); 
%>
<input class="<%=YOrdiniInseritiRAnnoOrd.getClassType()%>" id="<%=YOrdiniInseritiRAnnoOrd.getId()%>" maxlength="<%=YOrdiniInseritiRAnnoOrd.getMaxLength()%>" name="<%=YOrdiniInseritiRAnnoOrd.getName()%>" size="<%=YOrdiniInseritiRAnnoOrd.getSize()%>" type="hidden"><% 
  YOrdiniInseritiRAnnoOrd.write(out); 
%>

          </td>
        </tr>
        <tr>
          <td>
            <% 
  WebTextInput YOrdiniInseritiRNumeroOrd =  
     new com.thera.thermfw.web.WebTextInput("YOrdiniInseriti", "RNumeroOrd"); 
  YOrdiniInseritiRNumeroOrd.setParent(YOrdiniInseritiForm); 
%>
<input class="<%=YOrdiniInseritiRNumeroOrd.getClassType()%>" id="<%=YOrdiniInseritiRNumeroOrd.getId()%>" maxlength="<%=YOrdiniInseritiRNumeroOrd.getMaxLength()%>" name="<%=YOrdiniInseritiRNumeroOrd.getName()%>" size="<%=YOrdiniInseritiRNumeroOrd.getSize()%>" type="hidden"><% 
  YOrdiniInseritiRNumeroOrd.write(out); 
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
  mytabbed.setParent(YOrdiniInseritiForm); 
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
  errorList.setParent(YOrdiniInseritiForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YOrdiniInseritiForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YOrdiniInseritiForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YOrdiniInseritiBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YOrdiniInseritiForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YOrdiniInseritiBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YOrdiniInseritiBODC.getErrorList().getErrors()); 
           if(YOrdiniInseritiBODC.getConflict() != null) 
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
     if(YOrdiniInseritiBODC != null && !YOrdiniInseritiBODC.close(false)) 
        errors.addAll(0, YOrdiniInseritiBODC.getErrorList().getErrors()); 
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
     String errorPage = YOrdiniInseritiForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YOrdiniInseritiBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YOrdiniInseritiForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
