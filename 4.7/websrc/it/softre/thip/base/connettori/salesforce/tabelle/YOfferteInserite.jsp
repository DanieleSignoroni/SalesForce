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
  BODataCollector YOfferteInseriteBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YOfferteInseriteForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YOfferteInseriteForm", "YOfferteInserite", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/softre/thip/base/connettori/salesforce/tabelle/YOfferteInserite.js"); 
  YOfferteInseriteForm.setServletEnvironment(se); 
  YOfferteInseriteForm.setJSTypeList(jsList); 
  YOfferteInseriteForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YOfferteInseriteForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YOfferteInseriteForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YOfferteInseriteForm.getMode(); 
  String key = YOfferteInseriteForm.getKey(); 
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
        YOfferteInseriteForm.outTraceInfo(getClass().getName()); 
        String collectorName = YOfferteInseriteForm.findBODataCollectorName(); 
                YOfferteInseriteBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YOfferteInseriteBODC instanceof WebDataCollector) 
            ((WebDataCollector)YOfferteInseriteBODC).setServletEnvironment(se); 
        YOfferteInseriteBODC.initialize("YOfferteInserite", true, 0); 
        YOfferteInseriteForm.setBODataCollector(YOfferteInseriteBODC); 
        int rcBODC = YOfferteInseriteForm.initSecurityServices(); 
        mode = YOfferteInseriteForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YOfferteInseriteForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YOfferteInseriteBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YOfferteInseriteForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YOfferteInseriteForm); 
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
  myToolBarTB.setParent(YOfferteInseriteForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YOfferteInseriteForm.getBodyOnBeforeUnload()%>" onload="<%=YOfferteInseriteForm.getBodyOnLoad()%>" onunload="<%=YOfferteInseriteForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YOfferteInseriteForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YOfferteInseriteForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YOfferteInseriteBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YOfferteInseriteForm.getServlet()%>" method="post" name="YOfferteInseriteForm" style="height:100%"><%
  YOfferteInseriteForm.writeFormStartElements(out); 
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
  WebTextInput YOfferteInseriteIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YOfferteInserite", "IdAzienda"); 
  YOfferteInseriteIdAzienda.setParent(YOfferteInseriteForm); 
%>
<input class="<%=YOfferteInseriteIdAzienda.getClassType()%>" id="<%=YOfferteInseriteIdAzienda.getId()%>" maxlength="<%=YOfferteInseriteIdAzienda.getMaxLength()%>" name="<%=YOfferteInseriteIdAzienda.getName()%>" size="<%=YOfferteInseriteIdAzienda.getSize()%>" type="hidden"><% 
  YOfferteInseriteIdAzienda.write(out); 
%>

          </td>
        </tr>
        <tr>
          <td>
            <% 
  WebTextInput YOfferteInseriteRAnnoOff =  
     new com.thera.thermfw.web.WebTextInput("YOfferteInserite", "RAnnoOff"); 
  YOfferteInseriteRAnnoOff.setParent(YOfferteInseriteForm); 
%>
<input class="<%=YOfferteInseriteRAnnoOff.getClassType()%>" id="<%=YOfferteInseriteRAnnoOff.getId()%>" maxlength="<%=YOfferteInseriteRAnnoOff.getMaxLength()%>" name="<%=YOfferteInseriteRAnnoOff.getName()%>" size="<%=YOfferteInseriteRAnnoOff.getSize()%>" type="hidden"><% 
  YOfferteInseriteRAnnoOff.write(out); 
%>

          </td>
        </tr>
        <tr>
          <td>
            <% 
  WebTextInput YOfferteInseriteRNumeroOff =  
     new com.thera.thermfw.web.WebTextInput("YOfferteInserite", "RNumeroOff"); 
  YOfferteInseriteRNumeroOff.setParent(YOfferteInseriteForm); 
%>
<input class="<%=YOfferteInseriteRNumeroOff.getClassType()%>" id="<%=YOfferteInseriteRNumeroOff.getId()%>" maxlength="<%=YOfferteInseriteRNumeroOff.getMaxLength()%>" name="<%=YOfferteInseriteRNumeroOff.getName()%>" size="<%=YOfferteInseriteRNumeroOff.getSize()%>" type="hidden"><% 
  YOfferteInseriteRNumeroOff.write(out); 
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
  mytabbed.setParent(YOfferteInseriteForm); 
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
  errorList.setParent(YOfferteInseriteForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YOfferteInseriteForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YOfferteInseriteForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YOfferteInseriteBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YOfferteInseriteForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YOfferteInseriteBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YOfferteInseriteBODC.getErrorList().getErrors()); 
           if(YOfferteInseriteBODC.getConflict() != null) 
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
     if(YOfferteInseriteBODC != null && !YOfferteInseriteBODC.close(false)) 
        errors.addAll(0, YOfferteInseriteBODC.getErrorList().getErrors()); 
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
     String errorPage = YOfferteInseriteForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YOfferteInseriteBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YOfferteInseriteForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
