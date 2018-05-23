<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB2NOLXK45QWyN41-tNDdzb35EqpXGS0nQ"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Cities</title>
</head>
<body>
    <style>
        #map-canvas {
          height: 600px;
          height: 600px;
          margin: 0px;
          padding: 0px
        }
    </style>
<div id="map-canvas"></div>
<script type="text/javascript">
var json = ${citiesJSON}

var map;


	function initialize() {

		// Giving the map som options
		var mapOptions = {
			zoom : 4,
			center : new google.maps.LatLng(49.021660, 22.543945)
		};

		// Creating the map
		map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);

		// Looping through all the entries from the JSON data
		for (var i = 0; i < json.length; i++) {

			// Current object
			var obj = json[i];

			// Adding a new marker for the object
			var marker = new google.maps.Marker({
				position : new google.maps.LatLng(obj.latitude, obj.longitude),
				map : map,
				title : obj.title
			// this works, giving the marker a title with the correct title
			});

			// Adding a new info window for the object
			var clicker = addClicker(marker, obj.title);

		} // end loop

		// Adding a new click event listener for the object
		function addClicker(marker, content) {
			google.maps.event.addListener(marker, 'click', function() {

				if (infowindow) {
					infowindow.close();
				}
				infowindow = new google.maps.InfoWindow({
					content : content
				});
				infowindow.open(map, marker);

			});
		}

	}

	//Initialize the map
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</body>
</html>