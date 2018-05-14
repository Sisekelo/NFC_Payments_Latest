<?php
    $con = mysqli_connect("localhost", "root", "", "tutorial2");
    
    $username = $_POST["username"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM companyregister WHERE username = ? ");
    mysqli_stmt_bind_param($statement, "s", $username);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $name,$username, $balance , $password,$type);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["name"] = $name;
        $response["balance"] = $balance;
    }
    
    echo json_encode($response);
?>