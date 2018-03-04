<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCKEJD4clumNiWGYa0nShr6KETRUbdNwSE&signed_in=true&libraries=places&callback=initAutocomplete"
        async defer></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajouter une ville</title>
</head>
<body>
 <h1>Ajouter une ville</h1>
	<form:form method="post" modelAttribute="cityForm">
		<div id="locationField">
			<input id="autocomplete" placeholder="Enter your address"
				onFocus="geolocate()" type="text"></input>
		</div>

		<form:label path="cityname" >Nom de la ville : </form:label>
		<form:input path="cityname" id="locality"/>
		<br />
		<form:label path="countryname" >Nom du pays : </form:label>
		<form:input path="countryname" id="country"/>
		<br />
		<form:label path="longitude" >Longitude : </form:label>
		<form:input path="longitude" id="longitude"/>
		<br />
		<form:label path="latitude" >Latitude : </form:label>
		<form:input path="latitude" id="latitude"/>
		<br />

		<input type="submit" value="Sauver" />
		<input type="reset" value="Réinitialiser" />
	</form:form>

	<script>
		// This example displays an address form, using the autocomplete feature
		// of the Google Places API to help users fill in the information.

		var placeSearch, autocomplete;
		var componentForm = {
			locality : 'long_name',
			country : 'long_name',
		};

		function initAutocomplete() {
			// Create the autocomplete object, restricting the search to geographical
			// location types.
			autocomplete = new google.maps.places.Autocomplete(
			/** @type {!HTMLInputElement} */
			(document.getElementById('autocomplete')), {
				types : [ 'geocode' ]
			});

			// When the user selects an address from the dropdown, populate the address
			// fields in the form.
			autocomplete.addListener('place_changed', fillInAddress);
		}

		// [START region_fillform]
		function fillInAddress() {
			// Get the place details from the autocomplete object.
			var place = autocomplete.getPlace();

			for ( var component in componentForm) {
				document.getElementById(component).value = '';
				document.getElementById(component).disabled = false;
			}

			// Get each component of the address from the place details
			// and fill the corresponding field on the form.
			for (var i = 0; i < place.address_components.length; i++) {
				var addressType = place.address_components[i].types[0];
				if (componentForm[addressType]) {
					var val = place.address_components[i][componentForm[addressType]];
					document.getElementById(addressType).value = val;
				}
			}
			
			document.getElementById("latitude").value = place.geometry.location.lat();
			document.getElementById("longitude").value = place.geometry.location.lng();
		}
		// [END region_fillform]

		// [START region_geolocation]
		// Bias the autocomplete object to the user's geographical location,
		// as supplied by the browser's 'navigator.geolocation' object.
		function geolocate() {
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function(position) {
					var geolocation = {
						lat : position.coords.latitude,
						lng : position.coords.longitude
					};
					var circle = new google.maps.Circle({
						center : geolocation,
						radius : position.coords.accuracy
					});
					autocomplete.setBounds(circle.getBounds());
				});
			}
		}
		// [END region_geolocation]
	</script>

</body>
</html>