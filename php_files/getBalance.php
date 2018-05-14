<?php
    
    $con = mysqli_connect("localhost", "root", "", "tutorial2");

    $result = mysqli_query($con,"SELECT * FROM companyregister");

    $num_rows = mysqli_num_rows($result);

    echo $num_rows;
?>