<?php
    $con = mysqli_connect("localhost", "root", "", "tutorial2");
    
    $username = $_POST["username"];
    //$password = $_POST["password"];
    //$name = $_POST["name"];
    //$type = $_POST["type"];
    
    $statement = mysqli_prepare($con, "SELECT type FROM companyregister WHERE username = '$username'");
    mysqli_stmt_bind_param("s", $username);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement,$type);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["type"] = $type;
    }
    
    echo json_encode($response);
?>