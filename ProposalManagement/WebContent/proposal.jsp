<%@page import="com.Proposal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Proposal Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>

<script>
		
$(document).ready(function() {

	if ($("#alertSuccess").text().trim() == "") {

		$("#alertSuccess").hide();
	}
	$("#alertError").hide();

});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateProposalForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
		//$("#formproposal").submit();
	var type = ($("#hidIdSave").val() == "") ? "POST" : "PUT";
	$.ajax(
		{
			 url : "ProposalAPI",
			 type : type,
			 data : $("#formproposal").serialize(),
			 dataType : "text",
			 complete : function(response, status)
			 {
			 	onItemSaveComplete(response.responseText, status);
			 }
	});
});


	function onItemSaveComplete(response, status) {
		
		if (status == "success") {
			
			var resultSet = JSON.parse(response);
			
			if (resultSet.status.trim() == "success") {
				
				$("#alertSuccess").text("Successfully saved.");
				$("#alertSuccess").show();
				
				$("#divProposalGrid").html(resultSet.data);
				
			} else if (resultSet.status.trim() == "error") {
				
				$("#alertError").text(resultSet.data);
				$("#alertError").show();
			}
			
		}else if (status == "error") {
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
		} else {
			$("#alertError").text("Unknown error while saving..");
			$("#alertError").show();
		}
		
		$("#hidIdSave").val("");
		$("#formproposal")[0].reset();
	}

	// UPDATE==========================================
	$(document).on("click",".btnUpdate",function(event) {
		$("#hidIdSave").val($(this).closest("tr").find('#hidIdProposalUpdate').val());
		$("#pname").val($(this).closest("tr").find('td:eq(0)').text());
		$("#rname").val($(this).closest("tr").find('td:eq(1)').text());
		$("#catagory").val($(this).closest("tr").find('td:eq(2)').text());
		$("#duration").val($(this).closest("tr").find('td:eq(3)').text());
		$("#email").val($(this).closest("tr").find('td:eq(4)').text());
		$("#phone").val($(this).closest("tr").find('td:eq(5)').text());
		$("#budget").val($(this).closest("tr").find('td:eq(6)').text());
		$("#userid").val($(this).closest("tr").find('td:eq(7)').text());
		$("#summery").val($(this).closest("tr").find('td:eq(8)').text());
		$("#status").val($(this).closest("tr").find('td:eq(9)').text());
		
	});
	
	function onItemDeleteComplete(response, status)
	{
		if (status == "success")
		 {
		 var resultSet = JSON.parse(response);
		 
		 if (resultSet.status.trim() == "success")
		 {
				 $("#alertSuccess").text("Successfully deleted.");
				 $("#alertSuccess").show();
				 
				 $("#divProposalGrid").html(resultSet.data);
		 } else if (resultSet.status.trim() == "error")
		 {
				 $("#alertError").text(resultSet.data);
				 $("#alertError").show();
		 }
		 } else if (status == "error")
		 {
				 $("#alertError").text("Error while deleting.");
				 $("#alertError").show();
		 } else
		 {
				 $("#alertError").text("Unknown error while deleting..");
				 $("#alertError").show();
		 }
	}
	
	//DELETE==========================================
	$(document).on("click", ".btnRemove", function(event)
	{
 		$.ajax(
 		{
			 url : "ProposalAPI",
			 type : "DELETE",
			 data : "pid=" + $(this).data("pid"),
			 dataType : "text",
			 complete : function(response, status)
			 {
			 		onItemDeleteComplete(response.responseText, status);
			 }
 		});
	});

	// CLIENT-MODEL================================================================
	function validateProposalForm()
	{
	// PROPOSAL NAME
	if ($("#pname").val().trim() == "")
	{
	return "Insert Proposal Name.";
	}
	// Researcher NAME
	if ($("#rname").val().trim() == "")
	{
	return "Insert Research Name.";
	}

	// CATEGORY-------------------------------
	if ($("#catagory").val().trim() == "")
	{
	return "Insert Project Category.";
	}
	// DURATION
	if ($("#duration").val().trim() == "")
	{
	return "Insert Project Duration.";
	}
	// EMAIL
	if ($("#email").val().trim() == "")
	{
	return "Insert Reseacher Email.";
	}
	// PHONE
	if ($("#phone").val().trim() == "")
	{
	return "Insert Researcher Phone number.";
	}
	// PRICE-------------------------------
	if ($("#budget").val().trim() == "") {
		return "Insert Project Budget.";
	}
	// is numerical value
	var tmpPrice = $("#budget").val().trim();
	if (!$.isNumeric(tmpPrice)) {
		return "Insert a numerical value for Project Budget.";
	}
	
	// convert to decimal price
	$("#budget").val(parseFloat(tmpPrice).toFixed(2));
	
	// UserID
	if ($("#userid").val().trim() == "") {
		return "Insert User ID.";
	}
	
	// Summery
	if ($("#summery").val().trim() == "") {
		return "Insert Progect Summery.";
	}
	if ($("#status").val().trim() == "") {
		return "Insert Progect Status.";
	}
	
		return true;
	}
</script>
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