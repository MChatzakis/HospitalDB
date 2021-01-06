



/*
window.onbeforeunload = function (e) {
    $.post("http://localhost:8080/HospitalSystem/LogoutServlet",
            {
                data: {username: "omorfos", password: "sasa"}
            },
            function (data, status) {

                alert(data);
            }
    );
}*/

function Logout()
{
    console.log("xaxa");
    console.log("xaxa2");
    console.log("xaxa3");


    $.post("http://localhost:8080/HospitalSystem/LogoutServlet",
            {
                data: {username: "omorfos", password: "sasa"}
            },
            function (data, status) {

                alert(data);
            }
    );

}
function ShowPatients()
{
    console.log("lalala");

    if ($('#table-id').hasClass('d-none')) {
        $("#table-id").removeClass('d-none');

    } else
    {
        $("#table-id").addClass('d-none');

    }
}
function lala()
{
    console.log("called me ");
    /*
     var xhr = new XMLHttpRequest();
     xhr.open("POST", "http://localhost:8080/HospitalSystem/LogoutServlet",true);
     xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
     
     xhr.send(null);*/
}
