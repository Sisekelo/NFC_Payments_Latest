<?php
    $con = mysqli_connect("mysql.hostinger.com", "u627368589_nfc", "12345678", "u627368589_nfc");
    
    $username = $_POST["username"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM companyregister WHERE password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $name,$username, $balance , $password);

    $response = array();
    $response["success"] = false;
    $response["failed"] = true;

    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["name"] = $name;
        $response["balance"] = $balance;
        $response["username"] = $username;
        $response["password"] = $password;
    }

    echo json_encode($response);
?>