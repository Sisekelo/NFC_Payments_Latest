<?php
     
    
    $name = $_POST["name"];
    $username = $_POST["username"];
    $password = $_POST["password"];
    $type = $_POST["type"];

    $connect = mysqli_connect("localhost","root","","tutorial2");//connect to databse
    $sql ="SELECT name FROM companyregister where username= '$username'";//sql code for what we are looking for
    $answer = $connect->query($sql);//connect code to database

                    if ($answer->num_rows > 0) {//if rows greater than zero
                        //echo 'try different username';
                        //$response["success"] = false; 

                        $response = array();
                        $response["success"] = false; 
                        echo json_encode($response);
                    } 
                    else {
                        
                        $con = mysqli_connect("localhost", "root", "", "tutorial2");
                        $statement = mysqli_prepare($con, "INSERT INTO companyregister (name, username, password,type) VALUES (?, ?, ?,?)");
                        mysqli_stmt_bind_param($statement, "ssss", $name, $username, $password,$type);
                        mysqli_stmt_execute($statement);
                        
                        $response = array();
                        $response["success"] = true; 
                        //echo json_encode($response);

                        $con = mysqli_connect("localhost", "root", "", "tutorial2");

                        $result = mysqli_query($con,"SELECT * FROM companyregister where type = '$type'");

                        $num_rows = mysqli_num_rows($result);

                        $response["counter"] = $num_rows;

        
                        echo json_encode($response);

                                        }   

    
?>