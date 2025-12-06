am4core.ready(function() {

  // Themes begin
  am4core.useTheme(am4themes_animated);
  // Themes end
  
  
  
  // create chart
  var chart = am4core.create("cloud", am4charts.GaugeChart);
  chart.innerRadius = -10;
  
  var axis = chart.xAxes.push(new am4charts.ValueAxis());
  axis.min = 0;
  axis.max = 100;
  axis.strictMinMax = true;
  
  var colorSet = new am4core.ColorSet();
  
  var gradient = new am4core.LinearGradient();
  gradient.stops.push({color:am4core.color("green")})
  gradient.stops.push({color:am4core.color("yellow")})
  gradient.stops.push({color:am4core.color("red")})
  
  axis.renderer.line.stroke = gradient;
  axis.renderer.line.strokeWidth = 13;
  axis.renderer.line.strokeOpacity = 1;
  
  axis.renderer.grid.template.disabled = true;
  chart.logo.disabled="true";
  
  var hand = chart.hands.push(new am4charts.ClockHand());
  hand.radius = am4core.percent(95);
  
  setInterval(function() {
    // Set the desired fixed value (e.g., 75)
    hand.showValue(80, 1000, am4core.ease.cubicOut);
}, 2000);
  
  }); // end am4core.ready()