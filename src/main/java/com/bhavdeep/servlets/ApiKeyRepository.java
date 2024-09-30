//package com.bhavdeep.servlets;
////import model.ApiKey;
//
//import java.io.IOException;
//import java.util.List;
//
//import com.bhavdeep.model.ApiKey;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//
//
///**
// * Servlet implementation class ApiKeyRepository
// */
//public class ApiKeyRepository extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public ApiKeyRepository() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
//            throws ServletException, IOException {
//        String username = request.getParameter("username"); // Get the username from request
//
//        ApiKeyRepository apiKeyRepository = new ApiKeyRepository();
//        List<ApiKey> apiKeys = apiKeyRepository.getApiKeysForUser(username); // Fetch API keys
//
//        // Set the list of API keys as a request attribute
//        request.setAttribute("apiKeys", apiKeys);
//
//        // Forward the request to the JSP page
//        RequestDispatcher dispatcher = request.getRequestDispatcher("apiKeys.jsp"); // Adjust the JSP name
//        dispatcher.forward(request, response);
//    }
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
