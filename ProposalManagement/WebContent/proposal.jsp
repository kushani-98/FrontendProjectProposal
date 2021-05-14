<%@page import="com.Proposal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Proposal Management</title>
<link rel="stylesheet" href="Views/bootstrap.css">
<script src="jQuery-3.2.1.min.js"></script>
<script src="proposal.js"></script>


		

</head>
<body>
<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Project Proposal Management</h1>
					<form id="formproposal" name="formproposal" method="post" action="proposal.jsp">
 						 ProposalName:
						<input id="pname" name="pname" type="text"
 						class="form-control form-control-sm">
						<br> ResearcherName:
						<input id="rname" name="rname" type="text"
 						class="form-control form-control-sm">
						<br> Catagory:
						<input id="catagory" name="catagory" type="text"
 						class="form-control form-control-sm">
						<br>Duration:
						<input id="duration" name="duration" type="text"
						 class="form-control form-control-sm">
						<br>Email:
						<input id="email" name="email" type="text"
 						class="form-control form-control-sm">
						<br>Phone:
						<input id="phone" name="phone" type="text"
 						class="form-control form-control-sm">
						<br>Budget:
						<input id="budget" name="budget" type="text"
 						class="form-control form-control-sm">
						<br>Userid:
						<input id="userid" name="userid" type="text"
 						class="form-control form-control-sm">
						<br>Summery:
						<input id="summery" name="summery" type="text"
 						class="form-control form-control-sm">
						<br>Status:
						<input id="status" name="status" type="text"
 						class="form-control form-control-sm">
						<br>
						
 						


						<input id="btnSave" name="btnSave" type="button" value="Save"
 						class="btn btn-primary">
						<input type="hidden" id="hidIDSave" name="hidIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divProposalGrid">
					<%
									Proposal proposal = new Proposal();
								    out.print(proposal.viewProposals());
					%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>