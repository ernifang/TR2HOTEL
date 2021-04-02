<?php
session_start();
if(isset($_SESSION['id_user'])){
	header("Location:login.php");
}
$sumber = "http://localhost:8080/TR2Hotel/webresources/ClientController/getdata";
$konten = file_get_contents($sumber);
$data = json_decode($konten, true);
?>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" type="text/css" href="style.css">
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body style="background-color: #f5f5f5;">
	<nav class="navbar navbar-light" style="background-color: #1db954;">
		<a class="navbar-brand" style="color: white;">
<a class="col-1" style="text-align: center; color: white;"></a>
<a class="col-8" style="text-align: center; color: white;">Hotel ReG</a>
<a href="logout.php" class="col-2" style="text-align: center; color: white;"></a>	
<i class="fa fa-sign-out" aria-hidden="true"></i>
</a>	
	</nav>
</head>
<body>
<h1><br/>Hotel ReG</h1>

<div class="kotak_login">
<p class="tulisan_login">Login Client</p>

<form action="" method="post">
<label>Username</label>
<input type="text" name="nama" class="form_login" placeholder="...">
<label>Password</label>
<input type="password" name="password" class="form_login" placeholder="...">
<input type="submit" name="login" class="tombol_login">
</br>
</br>
</form>
</div>
</body>
</html>
<?php
if(isset($_POST["login"])){
	$user = $_POST["nama"];
	$pass = $_POST["password"];

	$sumber = "http://localhost:8080/TR2Hotel/webresources/ClientController/login";
	$data_array = array(
"nama" => $user,
"password" => $pass
	);
	$data = json_encode($data_array);
	$curl = curl_init();
	curl_setopt_array($curl, array(
		CURLOPT_URL => $sumber,
		CURLOPT_RETURNTRANSFER => true,
		CURLOPT_ENCODING =>"",
		CURLOPT_MAXREDIRS => 10,
		CURLOPT_TIMEOUT => 0,
		CURLOPT_FOLLOWLOCATION => true,
		CURLOPT_HTTP_VERSION => CURLOPT_HTTP_VERSION_1_1,
		CURLOPT_CUSTOMREQUEST => "POST",
		CURLOPT_POSTFIELDS => $data,
		CURLOPT_HTTPHEADER => array(
			"Content-Type: application/json"
		),
	));
	$response = curl_exec($curl);

	curl_close($curl);
	echo $response;

	if($response == "Tidak ada" || $response == "0"){
		header("Location: login.php");
	}else{
		session_start();
		$_SESSION['id_user'] = $response;
		header("Location:index.php");
	}
}

	
	//}
//}
?>