<%@include file="WEB-INF/views/inc/header.jsp"%>

<div class="row">
	<div class="col-12" align="center">
		<div class="alert alert-success" role="alert">
			<h3>
				Welcome <b>${name}!</b>
			</h3><br>
				Your last successful login was at : <b>${lastSuccessfulLogin}</b>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-12">
		<div class="jumbotron">
			<h2 class="mt-5" style="color: blue;">WELCOME TO PHONEBOOK WEB
				APPLICATION</h2>
			<p class="lead text-center" style="color: grey;">
				A simple Spring based Web Application for Phonebook.<br> Where
				User can <b>View , Update , Delete </b> his contacts & <b>Add</b> a new contact.
			</p>
		</div>
	</div>
<%@include file="WEB-INF/views/inc/footer.jsp"%>