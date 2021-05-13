package com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Proposal {
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paf", "root", "kushani1998#");

			System.out.print("Successfully connected");
		} catch (Exception e) {
			System.out.print("Connection Failed");
			e.printStackTrace();
			System.out.print(e);
		}

		return con;
	}
	
	    //View Proposal Details
	
	    public String viewProposals() {
		
		String output = "";
		
		
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>ProposalName</th><th>ResearcherName</th> "+" <th>Catagory</th> "+"<th>Duration</th> "+" <th>Email</th> "+" <th>Phone</th> "+" <th>Budget</th> "+" <th>Userid</th> "+" <th>Summery</th>"+"<th>Status</th>"+"<th>Update</th>"+"<th>Delete</th></tr>";

			String query = "select * from proposals";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String pid = Integer.toString(rs.getInt("pid"));
				String pname = rs.getString("pname");
				String rname = rs.getString("rname");
				String catagory = rs.getString("catagory");
				String duration = rs.getString("duration");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String budget = Double.toString(rs.getDouble("budget"));
				String userid = rs.getString("userid");
				String summery = rs.getString("summery");
				String status = rs.getString("status");
				
				output += "<tr><td><input id='hidIdProposalUpdate' name='hidIdProposalUpdate' type='hidden' value='" + pid + "'>" + pname + "</td>";
				// Add into the html table
				//output += "<tr><td>" + pid + "</td>";
				//output += "<td>" + pname + "</td>";
				output += "<td>" + rname + "</td>";
				output += "<td>" + catagory + "</td>";
				output += "<td>" + duration + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + budget + "</td>";
				output += "<td>" + userid + "</td>";
				output += "<td>" + summery + "</td>";
				output += "<td>" + status + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-pid='" + pid + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-pid='" + pid + "'></td></tr>";
	
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while viewing proposal details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	    //Insert Proposal  Details
	
		public String addProposals(String pname, String rname, String catagory, String duration ,String email , String phone , String budget , String userid , String summery , String status ) {
			
			String output = "";

			try {

				Connection con = connect();

				if (con == null) {

					return "Error while connecting to the database";
				}

				// insert data

				String query = " insert into proposals (pname,rname,catagory,duration,email,phone,budget,userid,summery,status) values (?, ?, ?, ?,?,?,?,?,?,?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
	
				preparedStmt.setString(1, pname);
				preparedStmt.setString(2, rname);
				preparedStmt.setString(3, catagory);
				preparedStmt.setString(4, duration);
				preparedStmt.setString(5, email);
				preparedStmt.setString(6, phone);
				preparedStmt.setDouble(7, Double.parseDouble(budget));
				preparedStmt.setString(8, userid);
				preparedStmt.setString(9, summery);
				preparedStmt.setString(10, status);

				// execute the statement
				preparedStmt.execute();
				con.close();
				String newProposal = viewProposals();
				
				output = "{\"status\":\"success\", \"data\": \"" + newProposal + "\"}";
				
			} catch (Exception e) {

				output = "{\"status\":\"error\", \"data\":\"Error while inserting the Proposal.\"}";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		
		//Update Proposal Details

		public String updateProposals(String pid,String pname, String rname, String catagory, String duration ,String email , String phone, String budget, String userid, String summery, String status) {

			String output = "";

			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE proposals SET pname=?,rname=?,catagory=?,duration=?,email=?,phone=?,budget=?,userid=?,summery=?,status=? WHERE pid =?";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values

				preparedStmt.setString(1, pname);
				preparedStmt.setString(2, rname);
				preparedStmt.setString(3, catagory);
				preparedStmt.setString(4, duration);
				preparedStmt.setString(5, email);
				preparedStmt.setString(6, phone);
				preparedStmt.setDouble(7, Double.parseDouble(budget));
				preparedStmt.setString(8,userid);
				preparedStmt.setString(9,summery);
				preparedStmt.setString(10,status);
				preparedStmt.setInt(11, Integer.parseInt(pid));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newItems = viewProposals();
				output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
				
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\":\"Error while updating the proposal.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		//Delete QUERY //Not coded
		
		public String deleteProposal(String pid) {
			String output = "";
			try {

				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}

				// create a prepared statement
				String query = "DELETE FROM proposals WHERE pid=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				  preparedStmt.setInt(1, Integer.parseInt(pid));
				  
				// execute the statement
				preparedStmt.execute();
				con.close();
				 
			     String newItems = viewProposals();
			     output = "{\"status\":\"success\", \"data\": \"" +
			     newItems + "\"}";

			} catch (Exception e) {

				output = "{\"status\":\"error\", \"data\":\"Error while deleting the proposal.\"}";
				System.err.println(e.getMessage());
			}

			return output;
		}
	

}
