var myJson={};
var timeJson={};
var detailedJson={};
var pass=[];
var fail=[];
var imProgress=[];
var testStepPassCount=0;
var testStepFailCount=0;

function fontColor(varID){
	document.getElementById(varID).style.color="blue";
}

function fontColorless(varID){
	document.getElementById(varID).style.color="";
}

function createEle(){
	var newEle = document.createElement("button");
	var div = document.getElementById("myDiv");
	div.appendChild(newEle);
	var txt = document.createTextNode("New Button Element has been added");
	newEle.appendChild(txt);
	newEle.style.color="blue";
}

function read(x,y,z,a){
	var count=0;
	var list = document.getElementsByTagName("li");
	$.getJSON('JSON/TotalDetails.json',function(data){
		if(x==true){
			list[0].innerHTML = list[0].innerHTML+data[0].OverallTotalCase;
			list[0].style.color="black";
			list[0].style.fontWeight='1000';
		}
		if(y==true){
			list[1].innerHTML = list[1].innerHTML+data[0].OverallPassedCase;
			list[1].style.color="green";
			list[1].style.fontWeight='1000';
		}
		if(z==true){
			list[2].innerHTML = list[2].innerHTML+data[0].OverallFailedCase;
			list[2].style.color="red";
			list[2].style.fontWeight='1000';
		}
		if(a==true){
			list[3].innerHTML = list[3].innerHTML+count;
			list[3].style.color="blue";
			list[3].style.fontWeight='1000';
		}
	});
}

function DeleteAndroidTable(){
	var detailedTableEle = document.getElementById('myTable');
	var count = detailedTableEle.rows.length;
	for(var x=count-1;x>0;x--){
		detailedTableEle.deleteRow(x);
	}
}

function DeleteIosTable(){
	var detailedTableEle = document.getElementById('myTable');
	var count = detailedTableEle.rows.length;
	for(var x=count-1;x>0;x--){
		detailedTableEle.deleteRow(x);
	}
}

function dynamicTable(platform){
	var module="";
	var table=document.getElementById("myTable");
	if(platform == 'android')
		module="ANDROID";
	else if(platform == 'ios')
		module="IOS";
	$.getJSON('JSON/'+module+'.json',function(data){
		data.forEach(appendMainPageRow);
		});
}

function getData(){
	var ele = document.getElementsByClassName('tablinks')[1];
	ele.style.display="block";
}

function functionality(fltyORdetail){
	if(fltyORdetail == "myTable"){
		var pieEle = document.getElementById('pieChartID');
		pieEle.style.backgroundColor="";
		var pie1 = document.getElementById('chartContainer');
		pie1.style.display="none";
		var pie2 = document.getElementById('iOSchartContainer');
		pie2.style.display="none";
		var ele = document.getElementById("detailedTable");
		ele.style.display="none";
	
		var ele1 = document.getElementById(fltyORdetail);
		ele1.style.display="block";
		var tablink = document.getElementsByClassName('tablinks')[0].style.backgroundColor="Orange";
		//var tablink1 = document.getElementsByClassName('tablinks')[1].style.backgroundColor="";
	}
	else if(fltyORdetail == "detailedTable"){
		var pieEle = document.getElementById('pieChartID');
		pieEle.style.backgroundColor="";
		var pie1 = document.getElementById('chartContainer');
		pie1.style.display="none";
		var pie2 = document.getElementById('iOSchartContainer');
		pie2.style.display="none"; 
		var ele = document.getElementById("myTable");
		ele.style.display="none";
		
		var ele1 = document.getElementById(fltyORdetail);
		ele1.style.display="block";
		var tablink = document.getElementsByClassName('tablinks')[0].style.backgroundColor="";
		//var tablink1 = document.getElementsByClassName('tablinks')[1].style.backgroundColor="Orange";
	}
}

function appendRow(values) {
    $("#tablebody").append("<tr><td>"+values.sno+"</td><td>" + values.Description + "</td><td>" + values.Expected +"</td><td style='white-space: nowrap; overflow: scroll;height: 100px; max-width: 100px;'>"+values.Actual+"</td><td>"
    		+values.stepStatus+"</td></tr>");
}

function appendMainPageRow(values) {
    $("#tableMainBody").append("<tr><td>"+values.TestCaseID+"</td><td>" + values.testcasename + "</td><td>" + values.platform +"</td><td>"+values.totalSteps+"</td><td>"
    		+values.passCount+"</td><td>"+values.failCount+"</td><td>"+values.status+"</td></tr>");
}

function readTable(){
	$(document).ready(function (){
		$('#myTable').on('click','tr',function(){
			var dataComplete = $(this).children('td').map(function(){
				return $(this).text();
			}).get();
			var selectedTitle = 'JSON/'+dataComplete[1]+'.json';
			$.getJSON(selectedTitle,function(data){
				var ele = document.getElementById('myTable');
				ele.style.display="none";
				var ele1 = document.getElementById('detailedTable');
				ele1.style.display="block";
				var tablink = document.getElementsByClassName('tablinks')[0].style.backgroundColor="Orange";
				//var tablink1 = document.getElementsByClassName('tablinks')[1].style.backgroundColor="Orange";
				var detailedTableEle = document.getElementById('detailedTable');
				data.forEach(appendRow);
				});
		});
	});
}

window.onclick=function(event){
	var modal = document.getElementById('myModal');
	if(event.target==modal){
		modal.style.display="none";
	}
}

function DeleteTable(){
	var detailedTableEle = document.getElementById('detailedTable');
	var count = detailedTableEle.rows.length;
	for(var x=count-1;x>0;x--){
		detailedTableEle.deleteRow(x);
	}
}

function executionDetails(start,end){
	var listDetails = document.getElementsByTagName('li');
	$.getJSON('JSON/executionTime.json',function(values){
		timeJson = values;
		if(start == true){
			listDetails[4].innerHTML=listDetails[4].innerHTML+timeJson[0].startTime;
			listDetails[4].style.color="rgba(128,0,128,1)";
			listDetails[4].style.fontWeight='1000';
		}
		if(end == true){
			listDetails[5].innerHTML=listDetails[5].innerHTML+timeJson[0].endTime;
			listDetails[5].style.color="rgba(128,0,128,1)";
			listDetails[5].style.fontWeight='1000';
		}
	});
}

function pieChartReport(){
	var ele =document.getElementById('myTable');
	ele.style.display="none";
	document.getElementsByClassName('tablinks')[0].style.backgroundColor="";
	var ele1 = document.getElementById('detailedTable');
	ele1.style.display="none";
	document.getElementsByClassName('tablinks')[1].style.backgroundColor="";
	var pieEle =document.getElementById('pieChartID');
	pieEle.style.backgroundColor="Orange";
	var pie1 = document.getElementById('chartContainer');
	pie1.style.display="block";
	var pie2 = document.getElementById('iOSchartContainer');
	pie2.style.display="block";
	var myJson={};
	$.getJSON('JSON/TotalDetails.json',function(data){
		myJson = data;
		var chart = new CanvasJS.Chart("chartContainer",{
			animationEnabled: true,
			backgroundColor: "rgba(210,206,200,1)",
			title:{
				text:"ANDROID",
				fontFamily: "TimesNewRoman",
				fontColor: "Blue",
				fontStyle: "italics",
				fontWeight: "bold",
			},
			data: [{
				type: "pie",
				startAngle: 240,
				indexLabelFontSize: 14,
				indexLabel: "{label} {y}",
				dataPoints:[
					{y:data[0].passedcase, label:"Passed Cases",color:"DarkGreen"},
					{y:data[0].failedcase, label:"Failed Cases",color:"#CC1844"},
					{y:data[0].pass, label:"Total Test Step Passed",color:"#67B01A"},
					{y:data[0].fail, label:"Total Test Step Failed",color:"#1E90FF"},
				]
			}]
		});
		chart.render();
	});
}
