	var $border_color = "#efefef";
	var $grid_color = "#ddd";
	var $default_black = "#666";
	var $green = "#8ecf67";
	var $yellow = "#fac567";
	var $orange = "#F08C56";
	var $blue = "#1e91cf";
	var $red = "#f74e4d";
	var $teal = "#28D8CA";
	var $grey = "#999999";
	var $dark_blue = "#0D4F8B";
$(function () {

	var chartOptions;
	var data=new Array();
	data[1] = [
		{ label: "ONLINE", data: Math.floor (Math.random() * 100 + 190) }, 
		{ label: "OFFLINE", data: Math.floor (Math.random() * 100 + 220) }
	];
	data[2] = [
	   		{ label: "SW001", data: Math.floor (Math.random() * 100 + 190) }, 
	   		{ label: "SW006", data: Math.floor (Math.random() * 100 + 220) }, 
	   		{ label: "SW002", data: Math.floor (Math.random() * 100 + 370) }, 
	   		{ label: "SW003", data: Math.floor (Math.random() * 100 + 120) },
	   		{ label: "SW004", data: Math.floor (Math.random() * 100 + 430) }
	   	];
	data[3] = [
	   		{ label: "R002 GigabitEthernet4/1", data: Math.floor (Math.random() * 100 + 190) }, 
	   		{ label: "R005 GigabitEthernet4/1", data: Math.floor (Math.random() * 100 + 220) }, 
	   		{ label: "R001 GigabitEthernet4/1", data: Math.floor (Math.random() * 100 + 370) }, 
	   		{ label: "SW001 GigabitEthernet4/1", data: Math.floor (Math.random() * 100 + 120) },
	   		{ label: "SW002 GigabitEthernet4/1", data: Math.floor (Math.random() * 100 + 430) }
	   	];
	data[4] = [
		   		{ label: "SW001", data: Math.floor (Math.random() * 100 + 190) }, 
		   		{ label: "SW006", data: Math.floor (Math.random() * 100 + 220) }, 
		   		{ label: "SW002", data: Math.floor (Math.random() * 100 + 370) }, 
		   		{ label: "SW003", data: Math.floor (Math.random() * 100 + 120) },
		   		{ label: "SW004", data: Math.floor (Math.random() * 100 + 430) }
	   	];
	data[5] = [
		   		{ label: "GigabitEthernet4/1", data: Math.floor (Math.random() * 100 + 190) }, 
		   		{ label: "GigabitEthernet4/1", data: Math.floor (Math.random() * 100 + 220) }, 
		   		{ label: "GigabitEthernet4/1", data: Math.floor (Math.random() * 100 + 370) }, 
		   		{ label: " GigabitEthernet4/1", data: Math.floor (Math.random() * 100 + 120) },
		   		{ label: "GigabitEthernet4/1", data: Math.floor (Math.random() * 100 + 430) }
		   	];
	data[6] = [
		   		{ label: "GigabitEthernet4/1", data: Math.floor (Math.random() * 100 + 190) }, 
		   		{ label: "GigabitEthernet4/1", data: Math.floor (Math.random() * 100 + 220) }, 
		   		{ label: "GigabitEthernet4/1", data: Math.floor (Math.random() * 100 + 370) }, 
		   		{ label: " GigabitEthernet4/1", data: Math.floor (Math.random() * 100 + 120) },
		   		{ label: "GigabitEthernet4/1", data: Math.floor (Math.random() * 100 + 430) }
		   	];
	chartOptions = {		
		series: {
			pie: {
				show: true,  
				innerRadius: 0, 
				stroke: {
					width: 1
				}
			}
		},
		grid:{
      hoverable: true,
      clickable: false,
      borderWidth: 1,
			tickColor: $border_color,
      borderColor: $grid_color,
    },
		legend: {
			position: 'nw'
		},
		shadowSize: 0,
		tooltip: true,
		
		tooltipOpts: {
			content: '%s: %y'
		},
		colors: [$green, $blue, $yellow, $teal, $yellow, $green],
	};

	for(var i=0;i<7;i++){
		var holder = $('#pie-chart'+i);

		  if (holder.length) {
		      $.plot(holder, data[i], chartOptions );
		  }
	}
			
});

var gg1 = new JustGage({
	  id: "gg1",
	  value : 4960,
	  min: 100,
	  max: 9999,
	  gaugeWidthScale: 1,
	  counter: true,
	  formatNumber: true,
	  gaugeColor: $grid_color,
	  levelColors: [$red],
	  label: "30 seconds"
	});
	setInterval(function() {
	  gg1.refresh(getRandomInt(10, 9999));
	}, 2000);
	
var gg2 = new JustGage({
	id: "gg2",
		  value : 2650,
		  min: 100,
		  max: 9999,
		  gaugeWidthScale: 1,
		  counter: true,
		  formatNumber: true,
		  gaugeColor: $grid_color,
		  levelColors: [$red],
		  label: "30 seconds"
		});
		setInterval(function() {
		  gg2.refresh(getRandomInt(10, 9999));
		}, 2000);
		
		var gg3 = new JustGage({
			  id: "gg3",
			  value : 5623,
			  min: 100,
			  max: 9999,
			  gaugeWidthScale: 1,
			  counter: true,
			  formatNumber: true,
			  gaugeColor: $grid_color,
			  levelColors: [$red],
			  label: "30 seconds"
			});
			setInterval(function() {
			  gg3.refresh(getRandomInt(10, 9999));
			}, 2000);