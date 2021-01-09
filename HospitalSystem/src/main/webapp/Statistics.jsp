<%-- 
    Document   : Statistics
    Created on : Jan 8, 2021, 6:34:59 PM
    Author     : George
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <link href="css/Charts.css" rel="stylesheet" type="text/css">



        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script type="text/javascript" src="js/Chart_controller.js"></script>



    </head>

    <body>
        <!-- <div class="container-fluid">
                <div class="row justify-content-center">
                    <div class="chart"id="chart_div"></div>
                </div>
                <div class="row mt-2 justify-content-center">
                    <div class="chart"id="piechart_3d"></div>
                </div>
        </div>-->

        <div class="container">
            <div class="row  w-100  justify-content-center">
                <div class="date-chooser">
                    <input type="month" id="month" name="month" value="">
                    <button type="button" class="btn  btn-primary" onclick="GetMonthlyDutyStats()">Submit</button>
                </div>
                <div class="clearfix"></div>
                <div class="col-md-12 col-lg-12">
                    <div id="chart_div" class="chart"></div>
                </div>
                <div class="col-md-12 col-lg-12">
                    <div id="chart2_div" class="chart"></div>
                </div>

            </div>
        </div>
        <div class="row Covid-header justify-content-center"style="text-align: center;">
            <h1> Covid 19 Report</h1>
        </div>
        <table class="container" id="covid-table">
            <thead>
                <tr>
                    <th>
                        <h1>VisitID</h1>
                    </th>
                    <th>
                        <h1>Name</h1>
                    </th>
                    <th>
                        <h1>Surname</h1>
                    </th>
                    <th>
                        <h1>Address</h1>
                    </th>
                    <th>
                        <h1>Phone</h1>
                    </th>
                    <th>
                        <h1>Birth_date</h1>
                    </th>
                    <th>
                        <h1>Chronical Diseases</h1>
                    </th>
                </tr>
            </thead>
            <tbody>

            </tbody>

        </table>

        <div class="footer" Style="color:rgb(0, 0, 0)">All Rights Reserved &copy; George Kokolakis</div>

    </body>

</html>