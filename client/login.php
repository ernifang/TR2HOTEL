<?php
session_start();
include "koneksi.php";
if(isset($_SESSION['id_user'])){
	header("Location:index.php");
}
?>
<html>
	<title>Hotel ReG</title>
	<head>
		<style type="text/css">
		body {
			background-color: #7a58ff;
			font-family: "Segoe UI";
		}
		#wrapper {
			background-color: #fff;
			width: 400px;
			height: 430px;
			margin-top: 120px;
			margin-left: auto;
			margin-right: auto;
			padding: 20px;
			border-radius: 4px;
		}
		input[type=text], input[type=password] {
			border: 1px solid #ddd;
			padding: 10px;
			width: 95%;
			border-radius: 2px;
			outline: none;
			margin-top: 10px;
			margin-bottom: 20px;
		}
		label, h1, h5 {
			text-transform: uppercase;
			font-weight: bold;
		}
		h1 {
			text-align: center;
			font-size: 40px;
			color: #7a58ff;
		}
		button {
			border-radius: 2px;
			padding: 10px;
			width: 120px;
			background-color: #7a58ff;
			border: none;
			color: #fff;
			font-weight: bold;
		}
		.error {
			background-color: #f72a68;
			width: 400px;
			height: auto;
			margin-top: 20px;
			margin-left: auto;
			margin-right: auto;
			padding: 20px;
			border-radius: 4px;
			color: #fff;

		}
		</style>
	</head>
<body>
	<div id="wrapper">
		<h1><br>Hotel ReG</br></h1>
			<div class="kotak_login">
				<h4><center><p class="tulisan_login">Login</p></center></h4>
				<form action="" method="post">
					<label>Username</label>
					<input type="text" name="nama" class="form_login" placeholder="Masukkan Nama anda">
					<label>Password</label>
					<input type="password" name="password" class="form_login" placeholder="Masukkan Password anda">
					<button type="submit">LOGIN</button>
				</div>
			</div>
		</form>
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
		//proses client request(VIA URL)
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

?>

