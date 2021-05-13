/**
 * 
 */
$(document).ready(function()
	{
	if ($("#alertSuccess").text().trim() == "")
	{
	$("#alertSuccess").hide();
	}
	$("#alertError").hide();
	});
	
// SAVE ============================================
	$(document).on("click", "#btnSave", function(event)
	{
		// Clear alerts---------------------
		$("#alertSuccess").text("");
		$("#alertSuccess").hide();
		$("#alertError").text("");
		$("#alertError").hide();
		
		// Form validation-------------------
	    var status = validateItemForm();
		if (status != true)
		{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
		}
		
		 // If valid------------------------
		 var type = ($("#hidIDSave").val() == "") ? "POST" : "PUT"; 
		 $.ajax( 
		 { 
		 url : "ProposalAPI", 
		 type : type, 
		 data : $("#formItem").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onItemSaveComplete(response.responseText, status); 
		 } 
 	}); 
});
	
	function onItemSaveComplete(response, status) 
	{
		if (status == "success") {
			
			var resultSet = JSON.parse(response);
			if (resultSet.status.trim() == "success") {
				$("#alertSuccess").text("Successfully saved.");
				$("#alertSuccess").show();
				$("#divItemsGrid").html(resultSet.data);
			} 
			else if (resultSet.status.trim() == "error") {
				$("#alertError").text(resultSet.data);
				$("#alertError").show();
			}
		} else if (status == "error") {
			
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
		} else {
			
			$("#alertError").text("Unknown error while saving..");
			$("#alertError").show();
		}

		$("#hidpIDSave").val("");
		$("#formItem")[0].reset();
	}

	$(document).on("click", ".btnRemove", function(event) {
		$.ajax(
			{
				url: "ProposalsAPI",
				type: "DELETE",
				data: "pid=" + $(this).data("pid"),
				dataType: "text",
				complete: function(response, status) {
					onItemDeleteComplete(response.responseText, status);
				}
			});
	});

	function onItemDeleteComplete(response, status) 
	{
		if (status == "success") {
			
			var resultSet = JSON.parse(response);
			
			if (resultSet.status.trim() == "success") {
				$("#alertSuccess").text("Successfully deleted.");
				$("#alertSuccess").show();
				$("#divItemsGrid").html(resultSet.data);
			} 
			else if (resultSet.status.trim() == "error") {
				$("#alertError").text(resultSet.data);
				$("#alertError").show();
			}
		} else if (status == "error") {
			
			$("#alertError").text("Error while deleting.");
			$("#alertError").show();
		} else {
			
			$("#alertError").text("Unknown error while deleting..");
			$("#alertError").show();
		}
	}
		
// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
	$("#hidIDSave").val($(this).data("pid"));
	$("#pname").val($(this).closest("tr").find('td:eq(1)').text());
	$("#rname").val($(this).closest("tr").find('td:eq(2)').text());
	$("#catagory").val($(this).closest("tr").find('td:eq(3)').text());
	$("#duration").val($(this).closest("tr").find('td:eq(4)').text());
	$("#email").val($(this).closest("tr").find('td:eq(5)').text());
	$("#phone").val($(this).closest("tr").find('td:eq(6)').text());
	$("#budget").val($(this).closest("tr").find('td:eq(7)').text());
	$("#userid").val($(this).closest("tr").find('td:eq(8)').text());
	$("#summery").val($(this).closest("tr").find('td:eq(9)').text());
	$("#status").val($(this).closest("tr").find('td:eq(10)').text());
	});
	

// CLIENT-MODEL================================================================
function validateItemForm()
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
	// Status------------------------
	if ($("#status").val().trim() == "")
	{
	return "Insert Proposal Status.";
	}
	return true;
}





