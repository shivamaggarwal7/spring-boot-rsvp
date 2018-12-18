<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


Ready for rsvp ${user.firstName} ${user.lastName} ${user.dob} ${user.phoneNo} ${user.city}	`
<br>
<form method="post" action="/reserveRSVP">
<input type="hidden" name="userId" value="${user.userId}">
<select name="cityId" onchange="javascript:getDates($(this).val());">
<option value="">Select</option>
<c:forEach items="${cities}" var="city">
<option value="${city.cityId}">${city.cityName}</option>
</c:forEach>
</select>
<div id="datesel"></div><br>
<div id="timeslotsel"></div>
<div id="rsvp"></div>
</form>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
 var dateArr = [];
 var timeslotarr = [];
 
function getDates(cityid)
	{
	$.ajax({
	    type : "GET",
	    url : "${pageContext.request.contextPath}/dates",
	    data : {
	    "cityId" : cityid
	    },
	    success: function(data){
	    parseDateRes(data);
	    }
	});
	}

function getTimeSlots(dateid)
{
$.ajax({
    type : "GET",
    url : "${pageContext.request.contextPath}/time",
    data : {
    "dateId" : dateid
    },
    success: function(data){
    parseTimeRes(data);
    }
});
}

function parseDateRes(data)
{
	for(var i=0;i<data.length;i++)
		{
			dateArr.push({"key":data[i].dateId,"value":data[i].date});
		}
	createSelect(dateArr,'#datesel');
}

function parseTimeRes(data)
	{
		for(var i=0;i<data.length;i++)
			{
				timeslotarr.push({"key":data[i].timeId,"value":data[i].time});
			}
		createSelect(timeslotarr,'#timeslotsel');
		$('<input type="submit">').appendTo('#timeslotsel')
	}

function createSelect(valArr,element)
{
	var sel = $('<select>').appendTo(element);
	if(element == '#datesel')
		{
		$(element+' select').attr("onchange","javascript:getTimeSlots($(this).val());");
	    $(element+' select').attr("name","dateId")
		}
	else
		$(element+' select').attr("name","timeId")
	
	sel.append($("<option>").attr('value','').text('Select'));	
	$(valArr).each(function() {
	 sel.append($("<option>").attr('value',this.key).text(this.value));
	});		
	}

</script>