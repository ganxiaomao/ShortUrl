var $grid_color = "#eee";
var $border_color = "#e1e8ed";
var $default_black = "#666";
var $red = "#3693cf";

$(function () {
    
  var d1, data, chartOptions;

  var d1 = [[1262304000000, 6], [1264982400000, 3057], [1267401600000, 20434], [1270080000000, 31982], [1272672000000, 26602], [1275350400000, 27826], [1277942400000, 24302], [1280620800000, 24237], [1283299200000, 21004], [1285891200000, 12144], [1288569600000, 10577], [1291161600000, 10295]];

  data1 = [{ 
    label: "CPU", 
    data: d1
  }];
  
  data2 = [{ 
	    label: "内存", 
	    data: d1
	  }];

  chartOptions = {
    xaxis: {
      min: (new Date(2009, 12, 1)).getTime(),
      max: (new Date(2010, 11, 2)).getTime(),
      mode: "time",
      tickSize: [1, "month"],
      monthNames: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
      tickLength: 0
    },
    yaxis: {

    },
    series: {
      lines: {
        show: true, 
        fill: true,
        fill: 0.1,
        lineWidth: 2
      },
      points: {
        show: true,
        radius: 5,
        fill: true,
        fillColor: "#ffffff",
        lineWidth: 2,
      }
    },
    grid:{
      hoverable: true,
      clickable: false,
      borderWidth: 0,
      tickColor: "#eee",
      borderColor: "#ccc",
    },
    legend: {
      show: true,
      position: 'nw'
    },
    tooltip: true,
    tooltipOpts: {
      content: '%s: %y'
    },
    shadowSize: 0,
    colors: ['#058DC7', '#666666', '#333333', '#CCCCCC'],
  };

  var holder1 = $('#area-chart1');
  var holder2 = $('#area-chart2');

  if (holder1.length) {
    $.plot(holder1, data1, chartOptions);
  }
  if (holder2.length) {
	    $.plot(holder2, data2, chartOptions);
	  }
});
//Vertical Chart
$(function () {

  var d1, d2, data, chartOptions;

  d1 = [
    [1325376000000, 1200], [1328054400000, 700], [1330560000000, 1000], [1333238400000, 600],
    [1335830400000, 350]
  ];

  d2 = [
    [1325376000000, 800], [1328054400000, 1200], [1330560000000, 600], [1333238400000, 250],
    [1335830400000, 300]
  ];

  data = [{
    label: 'Male',
    data: d1
  },{
    label: 'Female',
    data: d2
  }];

  chartOptions = {
    xaxis: {
      min: (new Date(2011, 11, 15)).getTime(),
      max: (new Date(2012, 04, 18)).getTime(),
      mode: "time",
      tickSize: [2, "month"],
      monthNames: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
      tickLength: 0
    },
    grid:{
      hoverable: true,
      clickable: false,
      borderWidth: 1,
      tickColor: $border_color,
      borderColor: $grid_color,
    },
    bars: {
      show: true,
      barWidth: 24*24*60*60*300,
      fill: true,
      lineWidth: 1,
      order: true,
      lineWidth: 0,
      fillColor: { colors: [ { opacity: 1 }, { opacity: 1 } ] }
    },
    shadowSize: 0,
    tooltip: true,
    tooltipOpts: {
      content: '%s: %y'
    },
    colors: ['#058DC7', '#f782aa'],
  }

  var holder = $('#mob-desktop1');

  if (holder.length) {
    $.plot(holder, data, chartOptions );
  }

});