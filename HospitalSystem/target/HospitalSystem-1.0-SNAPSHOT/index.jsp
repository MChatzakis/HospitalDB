<!DOCTYPE html>
<html>

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/Login_controller.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src='https://www.google.com/recaptcha/api.js'></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/Login.css">


        <title>Log In</title>
        <meta charset="UTF-8">
    </head>

    <body>
        <div id="MainLoginDiv">
            <div class="login-container container h-100">
                <div class="row  align-items-center h-100">
                    <div class="login-align  col-lg-6 col-md-8 col-sm-12 col-xs-12  mx-auto " style="height: auto;" ;>
                        <div class="card card-signin text-white " style="background-color: rgb(14, 10, 10); height: 80vh;">
                            <div class="card-body">
                                <div class="form-label-group col-sm-10 col-md-10 col-lg-10   mt-lg-5 mx-auto"
                                     id="welcome-form">
                                    <h1 class="card-title mt-5" style=" text-align: left;">Welcome</h1>
                                </div>
                                <form method="post" action="http://localhost:8080/HospitalSystem/LoginServlet">

                                    <div class="form-label-group col-sm-10 col-md-10 col-lg-10 mt-lg-5  mx-auto">
                                        <label for="username" id="username-box">Username</label>
                                        <input type="text" id="username" class="form-control" name="username"
                                               placeholder="Username">
                                        <label for="password" id="password-box" class="mt-4">Password</label>
                                        <input type="password" id="password" class="form-control" name="password"
                                               placeholder="Password">

                                        <input type="submit" class="btn btn-primary btn-md mt-4 "  
                                               id="button-login" value="login">
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>

                </div>


            </div>


        </div>

    </body>

</html>