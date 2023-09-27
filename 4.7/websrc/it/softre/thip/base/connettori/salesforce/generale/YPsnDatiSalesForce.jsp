<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///K:/Thip/4.7.0/websrcsvil/dtd/xhtml1-transitional.dtd">
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
  BODataCollector YPsnDatiSalesForceBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YPsnDatiSalesForceForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YPsnDatiSalesForceForm", "YPsnDatiSalesForce", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/softre/thip/base/connettori/salesforce/generale/YPsnDatiSalesForce.js"); 
  YPsnDatiSalesForceForm.setServletEnvironment(se); 
  YPsnDatiSalesForceForm.setJSTypeList(jsList); 
  YPsnDatiSalesForceForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YPsnDatiSalesForceForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YPsnDatiSalesForceForm.setWebFormModifierClass("it.softre.thip.base.connettori.salesforce.generale.web.YPsnDatiSalesForceFormModifier"); 
  YPsnDatiSalesForceForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YPsnDatiSalesForceForm.getMode(); 
  String key = YPsnDatiSalesForceForm.getKey(); 
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
        YPsnDatiSalesForceForm.outTraceInfo(getClass().getName()); 
        String collectorName = YPsnDatiSalesForceForm.findBODataCollectorName(); 
                YPsnDatiSalesForceBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YPsnDatiSalesForceBODC instanceof WebDataCollector) 
            ((WebDataCollector)YPsnDatiSalesForceBODC).setServletEnvironment(se); 
        YPsnDatiSalesForceBODC.initialize("YPsnDatiSalesForce", true, 0); 
        YPsnDatiSalesForceForm.setBODataCollector(YPsnDatiSalesForceBODC); 
        int rcBODC = YPsnDatiSalesForceForm.initSecurityServices(); 
        mode = YPsnDatiSalesForceForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YPsnDatiSalesForceForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YPsnDatiSalesForceBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YPsnDatiSalesForceForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YPsnDatiSalesForceForm); 
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
  myToolBarTB.setParent(YPsnDatiSalesForceForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YPsnDatiSalesForceForm.getBodyOnBeforeUnload()%>" onload="<%=YPsnDatiSalesForceForm.getBodyOnLoad()%>" onunload="<%=YPsnDatiSalesForceForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YPsnDatiSalesForceForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YPsnDatiSalesForceForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YPsnDatiSalesForceBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YPsnDatiSalesForceForm.getServlet()%>" method="post" name="YPsnDatiSalesForceForm" style="height:100%"><%
  YPsnDatiSalesForceForm.writeFormStartElements(out); 
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
  WebTextInput YPsnDatiSalesForceIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YPsnDatiSalesForce", "IdAzienda"); 
  YPsnDatiSalesForceIdAzienda.setParent(YPsnDatiSalesForceForm); 
%>
<input class="<%=YPsnDatiSalesForceIdAzienda.getClassType()%>" id="<%=YPsnDatiSalesForceIdAzienda.getId()%>" maxlength="<%=YPsnDatiSalesForceIdAzienda.getMaxLength()%>" name="<%=YPsnDatiSalesForceIdAzienda.getName()%>" size="<%=YPsnDatiSalesForceIdAzienda.getSize()%>" type="hidden"><% 
  YPsnDatiSalesForceIdAzienda.write(out); 
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
  mytabbed.setParent(YPsnDatiSalesForceForm); 
 mytabbed.addTab("tab1", "it.softre.thip.base.connettori.salesforce.generale.resources.YPsnDatiSalesForce", "tab1", "YPsnDatiSalesForce", null, null, null, null); 
  mytabbed.write(out); 
%>

     </td>
   </tr>
   <tr>
     <td height="100%"><div class="tabbed_pagine" id="tabbedPagine" style="position: relative; width: 100%; height: 100%;">
              <div class="tabbed_page" id="<%=mytabbed.getTabPageId("tab1")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tab1"); %>
                <table style="width: 100%;">
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPsnDatiSalesForce", "InstanceUrl", null); 
   label.setParent(YPsnDatiSalesForceForm); 
%><label class="<%=label.getClassType()%>" for="InstanceUrl"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YPsnDatiSalesForceInstanceUrl =  
     new com.thera.thermfw.web.WebTextArea("YPsnDatiSalesForce", "InstanceUrl"); 
  YPsnDatiSalesForceInstanceUrl.setParent(YPsnDatiSalesForceForm); 
%>
<textarea class="<%=YPsnDatiSalesForceInstanceUrl.getClassType()%>" cols="60" id="<%=YPsnDatiSalesForceInstanceUrl.getId()%>" maxlength="<%=YPsnDatiSalesForceInstanceUrl.getMaxLength()%>" name="<%=YPsnDatiSalesForceInstanceUrl.getName()%>" rows="5" size="<%=YPsnDatiSalesForceInstanceUrl.getSize()%>"></textarea><% 
  YPsnDatiSalesForceInstanceUrl.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPsnDatiSalesForce", "Token", null); 
   label.setParent(YPsnDatiSalesForceForm); 
%><label class="<%=label.getClassType()%>" for="Token"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YPsnDatiSalesForceToken =  
     new com.thera.thermfw.web.WebTextArea("YPsnDatiSalesForce", "Token"); 
  YPsnDatiSalesForceToken.setParent(YPsnDatiSalesForceForm); 
%>
<textarea class="<%=YPsnDatiSalesForceToken.getClassType()%>" cols="60" id="<%=YPsnDatiSalesForceToken.getId()%>" maxlength="<%=YPsnDatiSalesForceToken.getMaxLength()%>" name="<%=YPsnDatiSalesForceToken.getName()%>" rows="5" size="<%=YPsnDatiSalesForceToken.getSize()%>"></textarea><% 
  YPsnDatiSalesForceToken.write(out); 
%>

                    </td>
                  </tr>
                </table>
              <% mytabbed.endTab(); %> 
</div>
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
  errorList.setParent(YPsnDatiSalesForceForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YPsnDatiSalesForceForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YPsnDatiSalesForceForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YPsnDatiSalesForceBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YPsnDatiSalesForceForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YPsnDatiSalesForceBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YPsnDatiSalesForceBODC.getErrorList().getErrors()); 
           if(YPsnDatiSalesForceBODC.getConflict() != null) 
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
     if(YPsnDatiSalesForceBODC != null && !YPsnDatiSalesForceBODC.close(false)) 
        errors.addAll(0, YPsnDatiSalesForceBODC.getErrorList().getErrors()); 
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
     String errorPage = YPsnDatiSalesForceForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YPsnDatiSalesForceBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YPsnDatiSalesForceForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
