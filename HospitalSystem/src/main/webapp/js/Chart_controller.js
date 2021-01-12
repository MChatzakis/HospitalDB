/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var MONTHLY_DUTY_STATS = 1;
var DAILY_DUTY_STATS = 2;
var COVID_19_REPORT = 3;
var DRUGS_MONTH = 4;
var ILLNESS_MONTH = 5;

var DRUGS_DAILY = 6;
var ILLNESS_DAILY = 7;


var url = "http://localhost:8080/HospitalSystem/Statistics";



$(document).ready(function () {
    google.charts.load('current', {'packages': ['corechart']});

    google.setOnLoadCallback(function () {
     GetDailyDutyStats();
     GetMonthlyDutyStats();
     
     });
    GetCovidReport();
});

function GetCovidReport()
{
    formData = "requestID=" + COVID_19_REPORT;

    console.log("get Covid Report");
    sendXmlForm(url, formData, COVID_19_REPORT)
}
/*
 function GetDailyDutyStats()
 {
 formData = "requestID=" + DAILY_DUTY_STATS;
 
 console.log("get MONTHLY_DUTY_STATS");
 sendXmlForm(url, formData, DAILY_DUTY_STATS)
 }*/



function GetDailyDutyStats()
{
    formData = "requestID=" + DAILY_DUTY_STATS;
    var date = $('input[name=day]').val();
    // month += "-01";
    formData += "&date=" + date;

    console.log("get Daily stats", DAILY_DUTY_STATS);
    sendXmlForm(url, formData, DAILY_DUTY_STATS)
}
function GetMonthlyDutyStats()
{
    formData = "requestID=" + MONTHLY_DUTY_STATS;
    var month = $('input[name=month]').val();
    month += "-01";
    formData += "&month=" + month;

    console.log("get MONTHLY_DUTY_STATS", MONTHLY_DUTY_STATS);
    sendXmlForm(url, formData, MONTHLY_DUTY_STATS)
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
                //  console.log("returned from month")
                CallBackMonthlyDutyStats(request);
                CallBackMonthlyDrugStats(request);
                CallBackMonthlyIllnessStats(request);


            } else if (reqID === DAILY_DUTY_STATS)
            {
                CallBackDailyyDutyStats(request);
                CallBackDailyyDrugStats(request);
                CallBackDailyyIllnessStats(request);



            } else if (reqID === COVID_19_REPORT)
            {
                CallBackCovidReport(request);

            }

        }
    };


    request.send(formData);

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

/*
 function CallBackMonthlyDutyStats(data)
 {
 console.log("MonthlyDutystats respone");
 
 //var data = JSON.parse(data.responseText);
 
 google.setOnLoadCallback(function () {
 drawVisualization(data);
 });
 }*/

function CallBackDailyyDutyStats(resp)
{
    console.log("called Daily duty stats")
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
        console.log("incidents and examinations " + array[0])
        var values = resp.response;
        //   console.log(values)
        data.addColumn('string', 'Day');
        //  data.addColumn('number', 'drugs');
        data.addColumn('number', 'incidents');
        // data.addColumn('number', 'diseases');
        data.addColumn('number', 'examinations');
        data.addRows([array[0]


        ]);

        var options = {
            title: 'Daily Statistics about duty',
            backgroundColor: {fill: 'transparent'},

            vAxis: {title: ''},
            hAxis: {title: 'Month'},
            seriesType: 'bars',
            colors: ['7D086D', 'FF0000', '0000F5', '1B7C4C'],
            bar: {groupWidth: "100%"},

            series: {5: {type: 'line'}},

        };

        var chart = new google.visualization.ComboChart(document.getElementById('chart_daily_div'));
        chart.draw(data, options);
    }
}

function   CallBackDailyyDrugStats(resp)
{
    //  console.log("lala")
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
        console.log("drugs " + array[1])
        var values = resp.response;
        data.addColumn('string', 'day');
        //  data.addColumn('number', 'disease1');
        data.addColumn('number', 'Dexamethasone');
        data.addColumn('number', 'Flecainide');
        data.addColumn('number', 'Tamiflu');
        data.addColumn('number', 'Splint');
        data.addColumn('number', 'Amlodipine');
        data.addRows([array[1]


        ]);

        var options = {
            title: 'Daily Statistics about Drugs',
            backgroundColor: {fill: 'transparent'},

            vAxis: {title: ''},
            hAxis: {title: 'Day'},
            seriesType: 'bars',
            colors: ['#e6194B', '#f58231', '#3cb44b', '#911eb4', '#f032e6'],
            bar: {groupWidth: "100%"},

            series: {5: {type: 'line'}},

        };

        var chart = new google.visualization.ComboChart(document.getElementById('chart_daily_drugs_div'));
        chart.draw(data, options);
    }
}
function CallBackDailyyIllnessStats(resp)
{
    // console.log("lala")
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
        console.log("Diseases " + array[2])
        var values = resp.response;
        //   console.log(values)
        data.addColumn('string', 'day');
        //  data.addColumn('number', 'drugs');
        //  data.addColumn('number', 'incidents');
        data.addColumn('number', 'Covid-19');
        data.addColumn('number', 'Arrhythmia');
        data.addColumn('number', 'Influenza');
        data.addColumn('number', 'Bone_Break');
        data.addColumn('number', 'Hypertension');
        data.addRows([array[2]


        ]);
        var options = {
            title: 'Daily Statistics about Diseases',
            backgroundColor: {fill: 'transparent'},

            vAxis: {title: ''},
            hAxis: {title: 'Day'},
            seriesType: 'bars',
            colors: ['#e6194B', '#f58231', '#3cb44b', '#911eb4', '#f032e6'],

            bar: {groupWidth: "100%"},

            series: {5: {type: 'line'}},

        };

        var chart = new google.visualization.ComboChart(document.getElementById('chart_daily_diseases_div'));
        chart.draw(data, options);
    }
}

function CallBackMonthlyDutyStats(resp)
{

    var data = new google.visualization.DataTable();

    if (resp !== undefined)
    {
        var values = resp.response;
        if (values !== undefined)
            var array = JSON.parse(values);
    }

    if (array !== undefined)
    {
        // console.log(array[0])
        var values = resp.response;
        //   console.log(values)
        data.addColumn('string', 'Month');
        //  data.addColumn('number', 'drugs');
        data.addColumn('number', 'incidents');
        // data.addColumn('number', 'diseases');
        data.addColumn('number', 'examinations');
        data.addRows([array[0]


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


function CallBackMonthlyDrugStats(resp)
{
    // console.log("lala")
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
        // console.log(array[1])
        temp2 = resp.response;
        var values = resp.response;
        //   console.log(values)
        data.addColumn('string', 'Month');
        //  data.addColumn('number', 'disease1');
        data.addColumn('number', 'Dexamethasone');
        data.addColumn('number', 'Flecainide');
        data.addColumn('number', 'Tamiflu');
        data.addColumn('number', 'Splint');
        data.addColumn('number', 'Amlodipine');
        // data.addColumn('number', 'drugs');
        //
//data.addColumn('number', 'incidents');
        // data.addColumn('number', 'diseases');
        //data.addColumn('number', 'examinations');
        data.addRows([array[1]


        ]);

        var options = {
            title: 'Monthly Statistics about Drugs',
            backgroundColor: {fill: 'transparent'},

            vAxis: {title: ''},
            hAxis: {title: 'Month'},
            seriesType: 'bars',
            colors: ['#e6194B', '#f58231', '#3cb44b', '#911eb4', '#f032e6'],

            bar: {groupWidth: "100%"},

            series: {5: {type: 'line'}},

        };

        var chart = new google.visualization.ComboChart(document.getElementById('chart_drugs_div'));
        chart.draw(data, options);
    }
}
function CallBackMonthlyIllnessStats(resp)
{
    //  console.log("lala")
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
        // console.log(array[2])
        temp2 = resp.response;
        var values = resp.response;
        //   console.log(values)
        data.addColumn('string', 'Month');
        //  data.addColumn('number', 'drugs');
        //  data.addColumn('number', 'incidents');
        data.addColumn('number', 'Covid-19');
        data.addColumn('number', 'Arrhythmia');
        data.addColumn('number', 'Influenza');
        data.addColumn('number', 'Bone_Break');
        data.addColumn('number', 'Hypertension');

// data.addColumn('number', 'examinations');
        data.addRows([array[2]


        ]);

        var options = {
            title: 'Monthly Statistics about Diseases',
            backgroundColor: {fill: 'transparent'},

            vAxis: {title: ''},
            hAxis: {title: 'Month'},
            seriesType: 'bars',
            colors: ['#e6194B', '#f58231', '#3cb44b', '#911eb4', '#f032e6'],

            bar: {groupWidth: "100%"},

            series: {5: {type: 'line'}},

        };

        var chart = new google.visualization.ComboChart(document.getElementById('chart_diseases_div'));
        chart.draw(data, options);
    }
}




$(window).resize(function () {
    //  CallBackMonthlyDutyStats(temp);
    //CallBackDailyyDutyStats(temp2)
    //drawChart_3d();
});