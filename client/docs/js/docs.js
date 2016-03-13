$(function(){
	$('#calendar2').fullCalendar({ 
	});
    $('[data-toggle="popover"]').popover();
	$('[data-toggle="tooltip"]').tooltip();
	
	var data2 = [
		    {
		        value: 220,
		        color:"#5CB85C",
		        highlight: "rgba(162, 209, 158, 0.9)",
		        label: "Success"
		    },
		    {
		        value: 70,
		        color: "#D9534F",
		        highlight: "rgba(213, 125, 109, 0.9)",
		        label: "Danger"
		    },
		    {
		        value: 100,
		        color: "#3CA2E0",
		        highlight: "rgba(128, 177, 203, 0.9)",
		        label: "Info"
		    }
		]
		var cpie = document.getElementById("cpie").getContext("2d");
        new Chart(cpie).Pie(data2, {
            responsive: true
        });

        var chart2 = c3.generate({
	    bindto: '#piechart',
	    data: {
	         
	        // iris data from R
	        columns: [
	            ['data1', 10],
	            ['data2', 30],
	            ['data3', 5],
	            ['data4', 19],
	            
	        ],
	        type : 'pie',
	        onclick: function (d, i) { console.log("onclick", d, i); },
	        onmouseover: function (d, i) { console.log("onmouseover", d, i); },
	        onmouseout: function (d, i) { console.log("onmouseout", d, i); }
	    },
	    color: {
	        pattern: ['#3CA2E0','#5CB85C','#D9534F','#F0AD4E']
	    }
	});

     $('.warning-growl').click(function(event) {
        return $.growl.warning({
        message: "Warning Notification"
      });
    });

});
