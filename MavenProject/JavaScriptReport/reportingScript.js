var myJson={};
var timeJson={};
var detailedJson={};
var countJson={};

function fontColor(varID){
	document.getElementById(varID).style.color="red";
}

function fontColorless(varID){
	document.getElementById(varID).style.color="";
}

function read(x,y,z,a){
	var count=0;
	var list=document.getElementsByTagName("li");
	$.getJSON('JSONfolder/Module.json', function(data){
		countJson = data;
		for(var x=0;x<countJson.length;x++){
			if(x==true)
				count = countJson.length;
			if(y == true){
				if(countJson[i].status=='Passed')
					count = count+1;
			}
			if(z == true){
				if(countJson[i].status=='Failed')
					count = count+1;
			}
			if(a == true){
				if(countJson[i].status=='In-Progress')
					count = count+1;
			}	
		}
		if(x==true){
			list[0].innerHTML = list[0].innerHTML+count;
			list[0].style.color="black"
		}
		if(y==true){
			list[1].innerHTML = list[1].innerHTML+count;
			list[1].style.color="green"
		}
		if(z==true){
			list[2].innerHTML = list[2].innerHTML+count;
			list[2].style.color="red"
		}
		if(z==true){
			list[3].innerHTML = list[3].innerHTML+count;
			list[3].style.color="blue"
		}
	});
}

function dynamicTable(){
	var table= document.getElementById("myTable");
	$.getJSON('JSONfolder/Module.json',function(data){
		myJson = data;
		for(var i=0;i<myJson.length;i++){
			
			var tr = document.createElement('tr');
			var td1 = document.createElement('td');
			var td2 = document.createElement('td');
			var td3 = document.createElement('td');
			var td4 = document.createElement('td');
			var td5 = document.createElement('td');
			var td6 = document.createElement('td');
			var td7 = document.createElement('td');
			
			var text1 = document.createTextNode(myJson[i].TestCaseID);
			var text2 = document.createTextNode(myJson[i].testcasename);
			var text3 = document.createTextNode(myJson[i].browser);
			var text4 = document.createTextNode(myJson[i].totalsteps);
			var text5 = document.createTextNode(myJson[i].passcount);
			var text6 = document.createTextNode(myJson[i].failcount);
			var text7 = document.createTextNode(myJson[i].status);
			
			td1.appendChild(text1);
			td1.style.textAlign='center';
			td2.appendChild(text2);
			td2.style.textAlign='center';
			td3.appendChild(text3);
			td3.style.textAlign='center';
			td4.appendChild(text4);
			td4.style.textAlign='center';
			td5.appendChild(text5);
			td5.style.textAlign='center';
			td6.appendChild(text6);
			td6.style.textAlign='center';
			td7.appendChild(text7);
			td7.style.textAlign='center';
			if(td7.innerHTML == 'Passed')
				td7.style.color="Green";
			else if(td7.innerHTML == 'Failed')
				td7.style.color="Red";
			
			tr.appendChild(td1);
			tr.appendChild(td2);
			tr.appendChild(td3);
			tr.appendChild(td4);
			tr.appendChild(td5);
			tr.appendChild(td6);
			tr.appendChild(td7);
			
			table.appendChild(tr).style.cursor="pointer";
		}
	});
}

function getData(){
	var ele = document.getElementsByClassName('tablinks')[1];
	ele.style.display="block";
}

function functionality(fltyORdetail){
	if(fltyORdetail == "myTable"){
		var ele = document.getElementById("detailedTable");
		ele.style.display="none";
		var ele1 = document.getElementById(fltyORdetail);
		ele1.style.display="block";
		var tablink = document.getElementsByClassName('tablinks')[0].style.backgroundColor="Orange";
		var tablink1 = document.getElementsByClassName('tablinks')[1].style.backgroundColor="";
	}
	else if(fltyORdetail == "detailedTable"){
		var ele = document.getElementById("myTable");
		ele.style.display="none";
		var ele1 = document.getElementById(fltyORdetail);
		ele1.style.display="block";
		var tablink = document.getElementsByClassName('tablinks')[0].style.backgroundColor="";
		var tablink1 = document.getElementsByClassName('tablinks')[1].style.backgroundColor="Orange";
	}
}

function readTable(){
	let result="";var tr=null;var td1 = null;var text1=null;
	var descriptionEle=null;var descriptionText=null;var descriptionCell=null;var SnoCell=null;
	var ExpResultEle=null;var ExpResultText=null;var ExpResultCell=null;
	var StepStatusEle=null;var StepStatusText=null;var StepStatusCell=null;
	var imageCell=null;
	var ActualResultEle=null;var ActualResultText=null;var ActualResultCell=null;
	$(document).ready(function (){
		$('#myTable').on('click','tr',function(){
			var dataComplete = $(this).children('td').map(function(){
				return $(this).text();
			}).get();
			var selectedTitle = 'JSONfolder/'+dataComplete[1]+'.json';
			$.getJSON(selectedTitle,function(data){
				var ele = document.getElementById('myTable');
				ele.style.display="none";
				var ele1 = document.getElementById('detailedTable');
				ele1.style.display="block";
				var tablink = document.getElementsByClassName('tablinks')[0].style.backgroundColor="";
				var tablink1 = document.getElementsByClassName('tablinks')[1].style.backgroundColor="Orange";
				var detailedTableEle = document.getElementById('detailedTable');
				for(var q=1;q<=data.length;q++){
					var firstRow = detailedTableEle.getElementsByTagName('tr')[0];
					for(var j=0;j<firstRow.getElementsByTagName('td').length;j++){
						if(firstRow.getElementsByTagName('td')[j].innerHTML == 'S.NO'){
							if(detailedTableEle.getElementsByTagName('tr')[q] == null)
								tr = detailedTableEle.insertRow(q);
							else
								tr = detailedTableEle.getElementsByTagName('tr')[q];
							if(td1 == null)
								td1 = document.createElement('td')[j];
							if(text1 == null)
								text1 = document.createTextNode(data[q-1].sno);
							else
								text1.nodeValue = data[q-1].sno;
							SnoCell = tr.insertCell(j);
							SnoCell.innerHTML = text1.nodeValue;
							detailedTableEle.appendChild(tr);
						}
						else if(firstRow.getElementsByTagName('td')[j].innerHTML == 'Description'){
							if(descriptionEle == null)
								descriptionEle = document.createElement('td')[j];
							if(descriptionText == null)
								descriptionText = document.createTextNode(data[q-1].Description);
							else
								descriptionText.nodeValue = data[q-1].Description;
							descriptionCell = tr.insertCell(j);
							descriptionCell.innerHTML = descriptionText.nodeValue;
							detailedTableEle.appendChild(tr);
						}
						else if(firstRow.getElementsByTagName('td')[j].innerHTML == 'Expected Result'){
							if(ExpResultEle == null)
								ExpResultEle = document.createElement('td')[j];
							if(ExpResultText == null)
								ExpResultText = document.createTextNode(data[q-1].Expected);
							else
								ExpResultText.nodeValue = data[q-1].Expected;
							ExpResultCell = tr.insertCell(j);
							ExpResultCell.innerHTML = ExpResultText.nodeValue;
							detailedTableEle.appendChild(tr);
						}
						else if(firstRow.getElementsByTagName('td')[j].innerHTML == 'Actual Result'){
							if(ActualResultEle == null)
								ActualResultEle = document.createElement('td')[j];
							if(ActualResultText == null)
								ActualResultText = document.createTextNode(data[q-1].Actual);
							else
								ActualResultText.nodeValue = data[q-1].Actual;
							ActualResultCell = tr.insertCell(j);
							ActualResultCell.innerHTML = ActualResultText.nodeValue;
							detailedTableEle.appendChild(tr);
						}
						else if(firstRow.getElementsByTagName('td')[j].innerHTML == 'Step Status'){
							if(StepStatusEle == null)
								StepStatusEle = document.createElement('td')[j];
							if(StepStatusText == null)
								StepStatusText = document.createTextNode(data[q-1].stepStatus);
							else
								StepStatusText.nodeValue = data[q-1].stepStatus;
							StepStatusCell = tr.insertCell(j);
							StepStatusCell.innerHTML = StepStatusText.nodeValue;
							if(StepStatusText.innerHTML == 'Passed')
								StepStatusCell.style.color="Green";
							if(StepStatusText.innerHTML == 'Failed')
								StepStatusCell.style.color="Red";
							detailedTableEle.appendChild(tr);
						}
						else if(firstRow.getElementsByTagName('td')[j].innerHTML == 'Screenshot'){
							var x1=null;
							var image1 = document.createElement('img');
							image1.setAttribute('class','Images')
							image1.src = data[q-1].screenshot;
							image1.style.height="100px";
							image1.style.width="100px";
							x1 = tr.insertCell(j);
							x1.appendChild(image1);
							detailedTableEle.appendChild(tr);
							var modal = document.getElementById('myModal');
							var imgExpand = document.getElementsByClassName('Images');
							var modalImg = document.getElementById('img01');
							var captionText = document.getElementById('caption');
							$('img.Images').hover(function(){
								$(this).css('cursor','pointer');
							});
							$('img.Images').on('click',function(){
								modal.style.display="block";
								modalImg.src=this.src;
								captionText.innerHTML = this.alt;
								$('span.close').on('click',function(){
									modal.style.display="none";
								});
							});
						}
					}
				}
			});
		});
	});
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
	$.getJSON('JSONfolder/executionTime.json',function(values){
		timeJson = values;
		if(start == true){
			listDetails[4].innerHTML=listDetails[4].innerHTML+timeJson[0].startTime;
			listDetails[4].style.color="rgba(128,0,128,1)";
		}
		if(end == true){
			listDetails[5].innerHTML=listDetails[5].innerHTML+timeJson[0].endTime;
			listDetails[5].style.color="rgba(128,0,128,1)";
		}
	});
}
