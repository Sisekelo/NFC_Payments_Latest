<?php

global $sender,$receiver;

CheckSender();
CheckSenderAgain();
CheckReceiver();
CheckReceiverAgain();
getInitialBalance();
getInitialBalance2();


	
function CheckSender(){
	
		global  $sender, $sumSender, $sumReceiver;
		
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
		
		$sql = "SELECT amount FROM transactions where sender='$sender'and id=(SELECT MAX(id) FROM `transactions`)"; //gets the last sent transaction by sender.
		$result = $conn->query($sql);

				if ($result->num_rows > 0) {
				// output data of each row
				while($row = $result->fetch_assoc()) {
					$sumSender += $row['amount'];
					}
				} 
				else {
					$sumSender = 0;
				}
						//echo "$sender's last sending was: ";
						//echo </br>$sumSender;
						//echo " rands in total";*/
						
						return $sumSender;
					
}

function CheckSenderAgain(){

		global  $sender, $sumSender, $sumSender2;				
						
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
		
		$sql = "SELECT amount FROM transactions where receiver='$sender'and id=(SELECT MAX(id) FROM `transactions`)"; //checks all transactions when sender has received
		$result = $conn->query($sql);

				if ($result->num_rows > 0) {
				// output data of each row
				while($row = $result->fetch_assoc()) {
					$sumSender2 += $row['amount'];
					}
				} 
				else {
					$sumSender2 = 0;
				}
						//echo "<br>$sender has sent: ";
						//echo </br>$sumSender2;
						//echo " rands in total";*/
						
						return $sumSender2;
		}

function CheckReceiver(){
		global  $receiver, $sumSender, $sumReceiver;
		
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
		
		$sql = "SELECT amount FROM transactions where receiver='$receiver' and id=(SELECT MAX(id) FROM `transactions`)"; //check how much the receiver has received
		$result = $conn->query($sql);

				if ($result->num_rows > 0) {
				// output data of each row
				while($row = $result->fetch_assoc()) {
					$sumReceiver += $row['amount'];
					}
				} 
				else {
					$sumReceiver = 0;
				}
						//echo "<br>$receiver has received: ";
						//echo <br>$sumReceiver;
						//echo " rands in total";*/
						
						return $sumReceiver;					
}
	
function CheckReceiverAgain(){
		global  $receiver, $sumSender, $sumReceiver2;
		
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
		
		$sql = "SELECT amount FROM transactions where sender='$receiver'and id=(SELECT MAX(id) FROM `transactions`)"; //check how much the receiver has sender
		$result = $conn->query($sql);

				if ($result->num_rows > 0) {
				// output data of each row
				while($row = $result->fetch_assoc()) {
					$sumReceiver2 += $row['amount'];
					}
				} 
				else {
					$sumReceiver2 = 0;
				}
						//echo "<br>$receiver has sent: ";
						//echo <br>$sumReceiver2;
						//echo " rands in total";*/
						
						return $sumReceiver2;						
}
	
function getInitialBalance(){
	 
	global $sender, $initialBalance;
	 
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
	
	$sql = "SELECT balance FROM companyregister where username='$sender'"; //How much does the sender have now
	$initial = $conn->query($sql);
			if ($initial->num_rows > 0) {
    
				while($row = $initial->fetch_assoc()) {
				$initialBalance = $row["balance"];
				//echo <br>$initialBalance;
				
				
					
				}
	}
	return $initialBalance;
}

	
	
function getInitialBalance2(){
	global $receiver, $initialBalance2;
	 
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
	
	$sql = "SELECT balance FROM companyregister where username='$receiver'"; //check how much the sender has sent
	$initial2 = $conn->query($sql);
	
			if ($initial2-> num_rows > 0) {
    
				while($row = $initial2->fetch_assoc()) {
				$initialBalance2 = $row["balance"];
				//echo <br>$initialBalance2;
				
				
					
				}
	}
	return $initialBalance2;
}
	
$senderBalance = ($initialBalance-$sumSender)+$sumSender2 ; //initial amount-last sent+all received
$receiverBalance = ($initialBalance2+$sumReceiver) - $sumReceiver2;

UpdateSender();

global $senderBalance;

function UpdateSender(){
	global  $receiver, $sender, $senderBalance, $receiverBalance;
		
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
		
		$sql = "UPDATE companyregister SET balance='$senderBalance' where username = '$sender'"; //Let's update sender

		if ($conn->query($sql) === TRUE) {
		//echo "<br>Record updated successfully";
		} else {
		//echo "<br>Error updating record: " . $conn->error;
			}
$conn->close();
}	

UpdateReceiver();

global $receiverBalance;

function UpdateReceiver(){
	
	global  $receiver, $sender, $senderBalance, $receiverBalance;
		
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
		
		$sql = "UPDATE companyregister SET balance='$receiverBalance' where username = '$receiver'"; //Let's update receiver

		if ($conn->query($sql) === TRUE) {
		//echo "<br>Record updated successfully";
		} else {
		//echo "<br>Error updating record: " . $conn->error;
			}
$conn->close();
	
}
			
?>