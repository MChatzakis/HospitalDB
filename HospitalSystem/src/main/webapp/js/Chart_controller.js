/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var MONTHLY_DUTY_STATS = 1;
var DAILY_DUTY_STATS = 2;
var COVID_19_REPORT = 3;

var url = "http://localhost:8080/HospitalSystem/Statistics";



$(document).ready(function () {

    GetDailyDutyStats();
    GetCovidReport();

});

function GetCovidReport()
{
    formData = "requestID=" + COVID_19_REPORT;

    console.log("get Covid Report");
    sendXmlForm(url, formData, COVID_19_REPORT)
}
function GetDailyDutyStats()
{
    formData = "requestID=" + DAILY_DUTY_STATS;

    console.log("get MONTHLY_DUTY_STATS");
    sendXmlForm(url, formData, DAILY_DUTY_STATS)
}

function GetMonthlyDutyStats()
{
    formData = "requestID=" + MONTHLY_DUTY_STATS;
    var month = $('input[name=month]').val();
    month += "-01";
    formData += "&month=" + month;

    console.log("get MONTHLY_DUTY_STATS", MONTHLY_DUTY_STATS);
    sendXmlForm(url, formData,MONTHLY_DUTY_STATS)
}



function sendXmlForm(url, formData, reqID)
{
    var request = new XMLHttpRequest();
    request.open("POST", url, true);
    if (reqID === MONTHLY_DUTY_STATS)
        console.log("send month request")

    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;');
    //  console.log("lalla");
    request.onreadystatechange = function ()
    {
        if (this.readyState === 4 && this.status === 200)
        {
            if (reqID === MONTHLY_DUTY_STATS)
            {
                console.log("returned from month")
                CallBackMonthlyDutyStats(request);
            } else if (reqID === DAILY_DUTY_STATS)
            {
                CallBackDailyyDutyStats(request);

            } else if (reqID === COVID_19_REPORT)
            {
                CallBackCovidReport(request);

            }

        }
    };
    // console.log("lalla3");


    request.send(formData);

}
function CallBackDailyyDutyStats(data)
{
    // console.log("DialyDUtyStats respone");
    google.charts.load('current', {'packages': ['corechart']});
    google.setOnLoadCallback(function () {
        drawDutyDay(data);
    });
}

function  CallBackCovidReport(request)
{
    //console.log("covid data : " +request.responseText);
    var data = JSON.parse(request.responseText);
    var table = document.getElementById('covid-table');
    // console.log("covid data : " + data.name0);
    var counter = 0;


    var dataTable = ["visit", "name", "surname", "address", "phone", "birth_date", "diseases"]
    var i = 0;
    for (i = 0; i < data.len; i++) {

        row = table.insertRow(i + 1);
        for (j = 0; j < 7; j++)
        {
            var cell = row.insertCell(j);
            cell.innerHTML = data[dataTable[j] + counter];

        }
        counter++;



    }



}
google.charts.load('current', {'packages': ['corechart']});

/*
 function CallBackMonthlyDutyStats(data)
 {
 console.log("MonthlyDutystats respone");
 
 //var data = JSON.parse(data.responseText);
 
 google.setOnLoadCallback(function () {
 drawVisualization(data);
 });
 }*/

var temp2;

function CallBackMonthlyDutyStats(resp)
{
    console.log("im drawing")
    // Some raw data (not necessarily accurate)
       var data = new google.visualization.DataTable();
     
     if (resp !== undefined)
     {
     var values = resp.response;
     if (values !== undefined)
     var array = JSON.parse(values);     
     }
     
     if (array !== undefined)
     {
     temp2 = resp.response;
     var values = resp.response;
     //   console.log(values)
     data.addColumn('string', 'Month');
     data.addColumn('number', 'drugs');
     data.addColumn('number', 'incidents');
     data.addColumn('number', 'diseases');
     data.addColumn('number', 'examinations');
     data.addRows([array
     
     
     ]);
     
     var options = {
     title: 'Monthly Statistics about duty',
     backgroundColor: {fill: 'transparent'},
     
     vAxis: {title: ''},
     hAxis: {title: 'Month'},
     seriesType: 'bars',
     colors: ['7D086D', 'FF0000', '0000F5', '1B7C4C'],
     bar: {groupWidth: "100%"},
     
     series: {5: {type: 'line'}},
     
     };
     
     var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
     chart.draw(data, options);
     }
}
var temp;
function drawDutyDay(info) {
    var data = new google.visualization.DataTable();

    if (info !== undefined)
    {
        var values = info.response;
        if (values !== undefined)
            var array = JSON.parse(values);
    }

    if (array !== undefined)
    {

        temp = info;
        //  console.log(values)
        data.addColumn('string', 'Month');
        data.addColumn('number', 'drugs');
        data.addColumn('number', 'incidents');
        data.addColumn('number', 'diseases');
        data.addColumn('number', 'examinations');
        var obj = ['2020/01', 165, 938, 522, 998];
        data.addRows([array


        ]);

        var options = {
            title: 'Daily Statistics about duty',
            backgroundColor: {fill: 'transparent'},

            vAxis: {title: ''},
            hAxis: {title: 'Day'},
            seriesType: 'bars',
            colors: ['7D086D', 'FF0000', '0000F5', '1B7C4C'],
            bar: {groupWidth: "100%"},

            series: {5: {type: 'line'}},

        };

        var chart = new google.visualization.ComboChart(document.getElementById('chart2_div'));
        chart.draw(data, options);
    }
}

$(window).resize(function () {
  //  CallBackMonthlyDutyStats(temp);
    CallBackDailyyDutyStats(temp2)
    //drawChart_3d();
});