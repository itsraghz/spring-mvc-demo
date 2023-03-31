<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp" style="color:yellow;"><i class="fa fa-fw fa-home"></i>Phonebook</a> <span
			class="navbar-toggler-icon"></span>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="nav navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/contacts" style="color:yellow;"><i class="fa fa-fw fa-book"></i>Contacts</a>
				</li>
				
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/add-contact" style="color:yellow;"><i class="fa fa-fw fa-phone"></i>AddContact</a>
				</li>
				
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/logout" style="color:yellow;"><i class="fa fa-sign-out"></i>Logout</a>
				</li>
			</ul>
		</div>
	</div>
</nav>