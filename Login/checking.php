<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "tutorial2";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = "SELECT balance FROM companyregister where username='dlamini '"; //check how much the sender has sent
	$result = $conn->query($sql);
	
			if ($result->num_rows == 1) {
				echo '1 row';
			}				
				else if ($result->num_rows === 0){
				
				echo "this user does not exist";
			}
?>
