<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///K:/Thip/4.7.0/websrcsvil/dtd/xhtml1-transitional.dtd">
<html>
<!-- WIZGEN Therm 2.0.0 as Batch form - multiBrowserGen = true -->
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
  BODataCollector YEsportatoreClientiBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YEsportatoreClientiForm =  
     new com.thera.thermfw.web.WebFormForBatchForm(request, response, "YEsportatoreClientiForm", "YEsportatoreClienti", "Arial,10", "com.thera.thermfw.batch.web.BatchFormActionAdapter", false, false, false, true, true, true, null, 0, true, null); 
  YEsportatoreClientiForm.setServletEnvironment(se); 
  YEsportatoreClientiForm.setJSTypeList(jsList); 
  YEsportatoreClientiForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YEsportatoreClientiForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  ((WebFormForBatchForm)  YEsportatoreClientiForm).setGenerateThReportId(true); 
  ((WebFormForBatchForm)  YEsportatoreClientiForm).setGenerateSSDEnabled(true); 
  YEsportatoreClientiForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YEsportatoreClientiForm.getMode(); 
  String key = YEsportatoreClientiForm.getKey(); 
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
        YEsportatoreClientiForm.outTraceInfo(getClass().getName()); 
        String collectorName = YEsportatoreClientiForm.findBODataCollectorName(); 
				 YEsportatoreClientiBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YEsportatoreClientiBODC instanceof WebDataCollector) 
            ((WebDataCollector)YEsportatoreClientiBODC).setServletEnvironment(se); 
        YEsportatoreClientiBODC.initialize("YEsportatoreClienti", true, 0); 
        int rcBODC; 
        if (YEsportatoreClientiBODC.getBo() instanceof BatchRunnable) 
          rcBODC = YEsportatoreClientiBODC.initSecurityServices("RUN", mode, true, false, true); 
        else 
          rcBODC = YEsportatoreClientiBODC.initSecurityServices(mode, true, true, true); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YEsportatoreClientiForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YEsportatoreClientiBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YEsportatoreClientiForm.setBODataCollector(YEsportatoreClientiBODC); 
              YEsportatoreClientiForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 

	<title>Esportatore prodotti - Sales Force</title>
<% 
  WebToolBar myToolBarTB = new com.thera.thermfw.web.WebToolBar("myToolBar", "24", "24", "16", "16", "#f7fbfd","#C8D6E1"); 
  myToolBarTB.setParent(YEsportatoreClientiForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/com/thera/thermfw/batch/BatchRunnableMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
<% 
  WebLink link_0 =  
   new com.thera.thermfw.web.WebLink(); 
 link_0.setHttpServletRequest(request); 
 link_0.setHRefAttribute("thermweb/css/thermGrid.css"); 
 link_0.setRelAttribute("STYLESHEET"); 
 link_0.setTypeAttribute("text/css"); 
  link_0.write(out); 
%>
<!--<link href="thermweb/css/thermGrid.css" rel="STYLESHEET" type="text/css">-->
<body bottommargin="0" leftmargin="0" onbeforeunload="<%=YEsportatoreClientiForm.getBodyOnBeforeUnload()%>" onload="<%=YEsportatoreClientiForm.getBodyOnLoad()%>" onunload="<%=YEsportatoreClientiForm.getBodyOnUnload()%>" rightmargin="0" topmargin="0"><%
   YEsportatoreClientiForm.writeBodyStartElements(out); 
%> 

	<table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YEsportatoreClientiForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YEsportatoreClientiBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YEsportatoreClientiForm.getServlet()%>" method="post" name="YEsportatoreClienti" style="height:100%"><%
  YEsportatoreClientiForm.writeFormStartElements(out); 
%>

		<table cellpadding="2" cellspacing="2" height="100%" width="100%">
			<tr><td style="height:0"><% myToolBarTB.writeChildren(out); %> 
</td></tr>
			<tr>
				<td>
					<table width="100%">	
            			<tr>	
            				<td>
            				</td>
            			</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="height:0"><% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(YEsportatoreClientiForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>--></td>
			</tr>
		</table>
	<%
  YEsportatoreClientiForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YEsportatoreClientiForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YEsportatoreClientiBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YEsportatoreClientiForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YEsportatoreClientiBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YEsportatoreClientiBODC.getErrorList().getErrors()); 
           if(YEsportatoreClientiBODC.getConflict() != null) 
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
     if(YEsportatoreClientiBODC != null && !YEsportatoreClientiBODC.close(false)) 
        errors.addAll(0, YEsportatoreClientiBODC.getErrorList().getErrors()); 
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
     String errorPage = YEsportatoreClientiForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YEsportatoreClientiBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YEsportatoreClientiForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
