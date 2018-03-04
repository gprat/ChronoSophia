<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chronologie</title>

<spring:url value="/resources/css/jquery-ui-1.10.3.custom.css" var="jqueryCss" />
<spring:url value="/resources/js/jquery.js" var="jqueryJs" />
<spring:url value="/resources/js/jquery-ui.js" var="jqueryUiJs" />
<spring:url value="/resources/js/underscore-min.js" var="underScore" />
<spring:url value="/resources/js/backbone-min.js" var="backbone" />
<spring:url value="/resources/js/json2.js" var="json2" />
<spring:url value="/resources/js/jquery.tmpl.js" var="jquerytmpl" />
<spring:url value="/resources/js/ba-tinyPubSub.js" var="tinyPub" />
<spring:url value="/resources/js/jquery.mousewheel.js" var="mousewheel" />
<spring:url value="/resources/js/jquery.ui.ipad.js" var="jqueryipad" />
<spring:url value="/resources/js/globalize.js" var="globalize" />
<spring:url value="/resources/js/ba-debug.min.js" var="badebug" />
<spring:url value="/resources/timeglider/Timeglider.css" var="timegliderCss" />
<spring:url value="/resources/timeglider/timeglider.datepicker.css" var="timegliderDateCss" />
<spring:url value="/resources/timeglider/TG_Date.js" var="TG_Date" />
<spring:url value="/resources/timeglider/TG_Org.js" var="TG_Org" />
<spring:url value="/resources/timeglider/TG_Timeline.js" var="TG_Timeline" />
<spring:url value="/resources/timeglider/TG_TimelineView.js" var="TG_TimelineView" />
<spring:url value="/resources/timeglider/TG_Mediator.js" var="TG_Mediator" />
<spring:url value="/resources/timeglider/timeglider.timeline.widget.js" var="timelineWidget" />
<spring:url value="/resources/timeglider/timeglider.datepicker.js" var="timegliderPicker" />
<spring:url value="/resources/timeglider/icons/" var="icons" />

<link rel="stylesheet" href="${jqueryCss}" type="text/css" charset="utf-8">
<link rel="stylesheet" href="${timegliderCss}" type="text/css" charset="utf-8">
<link rel="stylesheet" href="${timegliderDateCss}" type="text/css" charset="utf-8">

</head>
<body>


<script src="${jqueryJs}" type="text/javascript"></script>
<script src="${jqueryUiJs}" type="text/javascript" charset="utf-8"></script>
<script src="${underScore}" type="text/javascript" charset="utf-8"></script>
<script src="${backbone}" type="text/javascript" charset="utf-8"></script>
<script src="${json2}" type="text/javascript" charset="utf-8"></script>
<script src="${jquerytmpl}" type="text/javascript" charset="utf-8"></script>
<script src="${tinyPub}" type="text/javascript" charset="utf-8"></script>
<script src="${mousewheel}" type="text/javascript" charset="utf-8"></script>
<script src="${jqueryipad}" type="text/javascript" charset="utf-8"></script>
<script src="${globalize}" type="text/javascript" charset="utf-8"></script>	
<script src="${badebug}" type="text/javascript" charset="utf-8"></script>


<script src="${TG_Date}" type="text/javascript" charset="utf-8"></script>
<script src="${TG_Org}" type="text/javascript" charset="utf-8"></script>
<script src="${TG_Timeline}" type="text/javascript" charset="utf-8"></script>
<script src="${TG_TimelineView}" type="text/javascript" charset="utf-8"></script>
<script src="${TG_Mediator}" type="text/javascript" charset="utf-8"></script>
<script src="${timelineWidget}" type="text/javascript" charset="utf-8"></script>
<script src="${timegliderPicker}" type="text/javascript" charset="utf-8"></script>

<div class='header'>
<h1>${titre}</h1>
</div>



<div id='placement'></div>

<style type='text/css'>
	/* defining the frame for the widget */
		#placement {
			margin:32px;
			height:550px;
		}
</style>

<script type='text/javascript'>


    var chronology = ${chronologieJSON};
	var tg1 = {};
	
	
	$(function () { 
		
		// jQuery widget implementation
		// with some basic options
		
		tg1 = $("#placement").timeline({
			
				"min_zoom":15, 
				"max_zoom":55, 
				"image_lane_height":100,
				"icon_folder":"${icons}",
				"data_source": chronology,
				"constrain_to_data": true,
				"display_zoom_level": true
		});
		
		
		
    }); // end document-ready
	
</script>



</body>
</html>