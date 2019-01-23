<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">

<title>RPS Game</title>

<script src="static/js/jquery-1.11.1.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>

<style>
.body {
	background-color: lightBlue;
}
</style>

<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">

</head>
<body>

	<!-- TITLE -->
	<div class="container text-center">
		<a href="#" class="navbar-brand"
			style="font-size: 2.25rem; padding-bottom: 1.3125rem;">ROCK -
			PAPER - SCISSORS</a>
		<div class="container text-center">
			<img id='smilePic'
				src="https://i0.wp.com/www.teampedia.net/wiki/images/0/0b/Rock.png"
				style="width: 35%; height: 35%" />
		</div>
	</div>

	<!-- BUTTONS -->
	<div class="row">
		<div class="col-md-4" style="text-align: right;">
			<form class="form-horizontal" action="/rps/play" method="GET">
				<div class="form-group">
					<input id="playRoundBtn" type="submit"
						class="btn btn-success btn-lg" value="Play Round" />
				</div>
			</form>
		</div>
		<div class="col-md-4" style="text-align: center">
			<form class="form-horizontal">
				<div class="form-group">
					<input id="smileBtn" class="btn btn-primary"
						value="Rock, Paper, Scissors..." />
				</div>
			</form>
		</div>
		<div class="col-md-4">
			<form class="form-horizontal" action="/rps/reset" method="GET">
				<div class="form-group">
					<input id="resetBtn" type="submit" class="btn btn-secondary btn-lg"
						value="Restart Game" />
				</div>
			</form>
		</div>
	</div>

	<!-- ROUNDS RESULTS -->
	<div class="container text-center" style="margin-top: 10px">
		<h3 style="color: #64ab3a">Rounds Results</h3>
		<hr>

		<!-- PLAYER ROUNDS PLAYED FIELD -->
		<div class="row">
			<div class="form-group col-md-8">
				<div class="form-group col-md-4" style="margin-bottom: -5px">
					<label class="control-label3"
						style="font-weight: bold; color: #64ab3a;">Player Rounds
						Played</label>
				</div>
				<div class="form-group col-md-4">
					<input type="text" class="form-control" placeholder="0"
						value="${playerRounds.player.rounds}" readonly />
				</div>
			</div>
		</div>

		<!-- RESULTS TABLES -->
		<div class="row" style="margin-top: -15px;">

			<!-- PLAYER ROUNDS RESULTS TABLE -->
			<div class="table-responsive col-md-6">
				<table class="table table-striped table-bordered text-left"
					style="width: 100%">
					<thead>
						<tr>
							<th>Player 1</th>
							<th>Player 2</th>
							<th>Result</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="round" items="${playerRounds.player.roundsInfo}">
							<tr>
								<td>${round.playerOneShape}</td>
								<td>${round.playerTwoShape}</td>
								<td>${round.result}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<!-- TOTAL ROUNDS RESULTS TABLE -->
			<div class="table-responsive col-md-6">
				<table class="table table-striped table-bordered text-left"
					style="width: 100%">
					<thead>
						<tr>
							<th>Total Rounds Played</th>
							<th>Total Wins Player 1</th>
							<th>Total Wins Player 2</th>
							<th>Total Draws</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${playerRounds.roundsCounter.totalRounds}</td>
							<td>${playerRounds.roundsCounter.totalWinsPlayerOne}</td>
							<td>${playerRounds.roundsCounter.totalWinsPlayerTwo}</td>
							<td>${playerRounds.roundsCounter.totalDraws}</td>
						</tr>
					</tbody>
				</table>
			</div>

		</div>
	</div>

	<script>
		jQuery(function($) {
			$('#smileBtn').click(function($) {
				alert("1, 2, 3! (^_^)");
			});
		});
	</script>

</body>
</html>