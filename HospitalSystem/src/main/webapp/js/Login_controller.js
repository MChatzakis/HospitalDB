/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function SendData() {
    
    /*var xhttp = new XMLHttpRequest();
     var id = 10;
     let formData = new FormData(); // creates an object, optionally fill from <form>
     formData.append("username", "omorfos"); // appends a field
     formData.append("password", "lala"); // appends a field
     var data = "username=omorfos&password=lala";
     //"&password="encodeURIComponent("lala");
     
     console.log(formData.get("username"));
     
     //var data='doctor';
     xhttp.open("POST", "http://localhost:8080/HospitalSystem/LoginServlet");
     xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
     // xhttp.setRequestHeader('Content-Type', 'text/html; charset=UTF-8');
     //xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
     xhttp.onreadystatechange = function () {
     if (this.readyState == 4 && this.status == 200) {
     
     // Response
     var response = this.responseText;
     
     }
     };
     xhttp.send(data);*/
    /* var xhr = new XMLHttpRequest();
     xhr.open("POST", 'http://localhost:8080/HospitalSystem/LoginServlet', true);
     
     console.log("im hereee");
     xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8" );
     xhr.onreadystatechange = function () { // Call a function when the state changes.
     if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
     // Request finished. Do processing here.
     }
     }
     xhr.send("foo=bar&lorem=ipsum");*/
   /* $.post("http://localhost:8080/HospitalSystem/LoginServlet",
            {
                data: {username: "omorfos", password: "sasa"}
            },
            function (data, status) {

                document.open();
                document.write(data);
                document.close();
            });*/
  /*  $.ajax({
        type: 'POST',
        url: "http://localhost:8080/HospitalSystem/LoginServlet",
        data: {username: "omorfos", password: "sasa"},
        async: false
    });*/

}