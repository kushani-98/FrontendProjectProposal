package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;




import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProposalAPI
 */
@WebServlet("/ProposalAPI")
public class ProposalAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Proposal proposal = new Proposal();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProposalAPI() {
      super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = proposal.addProposals(request.getParameter("pname"),
				request.getParameter("rname"),
				request.getParameter("catagory"), 
				request.getParameter("duration"),
				request.getParameter("email"),
				request.getParameter("phone"),
				request.getParameter("budget"),
				request.getParameter("userid"),
				request.getParameter("summery"),
				request.getParameter("status"));
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = proposal.updateProposals(paras.get("hidIDSave").toString(), 
				paras.get("pname").toString(),
				paras.get("rname").toString(), 
				paras.get("catagory").toString(), 
				paras.get("duration").toString(), 
				paras.get("email").toString(), 
				paras.get("phone").toString(), 
				paras.get("budget").toString(), 
				paras.get("userid").toString(), 
				paras.get("summery").toString(), 
				paras.get("status").toString());
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = proposal.deleteProposal(paras.get("pid").toString());
		response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
	try
	 {
		Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		String queryString = scanner.hasNext() ?
				scanner.useDelimiter("\\A").next() : "";
				
		scanner.close();
	 
		String[] params = queryString.split("&");
		for (String param : params)
		{ 
			String[] p = param.split("=");
			map.put(p[0], p[1]);
		}
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}
}