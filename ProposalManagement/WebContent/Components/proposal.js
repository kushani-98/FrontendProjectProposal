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
	    var status = validateProposalForm();
		if (status != true)
		{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
		}
		
		 // If valid------------------------
		$("#formproposal").submit();
});
	
	
		
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
	// Status
	if ($("#status").val().trim() == "") {
		return "Insert Progect Status.";
	}
	
	return true;
}





