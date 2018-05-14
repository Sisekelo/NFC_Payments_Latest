<?php

// Create connection
$con = mysqli_connect("localhost", "id4416034_sisekelo", "12345678", "id4416034_nfctrial");
    // Check connection
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    };

/*$name = $_POST["name"];
$username = $_POST["username"];
$password = 0;
$type = 'Visitor';*/

$name = 'sisekelo';
$username = 'Dlamini';
$password = 0;
$type = 'Visitor';


//needs to check if that person has been there before

$statement = mysqli_prepare($con, "INSERT INTO companyregister (name, username, password, type) VALUES (?, ?, ?, ?)");

mysqli_stmt_bind_param($statement, "ssis", $name, $username, $password, $type);
mysqli_stmt_execute($statement);

$response = array();
$response["success"] = true;  

echo json_encode($response);
?>