<?php
     
    $sender = $_POST["sender"];
    $receiver = $_POST["receiver"];
    $amount = $_POST["amount"];
	
		
	global $amount;
	global $senderBalance;
	global $sender;
	global $receiver;
	
	//global $amount, $senderBalance,$sender;
	
	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "tutorial2";

	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	};
	
	$search = "SELECT balance FROM companyregister where username='$receiver'"; //check if receiver exists
	$answer = $conn->query($search);
	
	if ($answer->num_rows != 1) {
		
		echo 'receiver does not exist';
	}
	
	else{
	
	$sql = "SELECT balance FROM companyregister where username='$sender'"; //check how much the sender has sent
	$result = $conn->query($sql);
	
	
	
			if ($result->num_rows == 1) {
    
				while($row = $result->fetch_assoc()) {
				$row["balance"];
				
					if($row["balance"]	< $amount){
						
						//echo 'not enough money';
						$response["not_enough"] = true;
						echo json_encode($response);

						die();
					}
					
					
					else{
						
						$con = mysqli_connect("localhost", "root", "", "tutorial2");
						
						$statement = mysqli_prepare($con, "INSERT INTO transactions (sender, receiver, amount) VALUES (?, ?, ?)");
						mysqli_stmt_bind_param($statement, "ssi", $sender, $receiver, $amount);
						mysqli_stmt_execute($statement);
						
						include 'ProjectLisa.php';
						
						$response = array();
						$response["success"] = true; 
						$response["balance"] = $senderBalance;
						$response["balance2"] = $receiverBalance;
				
				
				echo json_encode($response);
						
					}
				}
				} 
				
			else if ($result->num_rows == 0){
				
				echo "this sender does not exist";
			}
			
			
	}

					
    
?>