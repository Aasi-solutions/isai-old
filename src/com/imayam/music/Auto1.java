package com.imayam.music;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.google.gson.Gson;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







/**
 * Servlet implementation class Auto
 */
public class Auto1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auto1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		try{
		String s[]=null;
		 String query = (String)request.getParameter("q");
	     Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/imayam2_phpbb1",
//					"root", "aasi");
			 Connection con =
			DriverManager.getConnection("jdbc:mysql://localhost:3306/imayam2_phpbb1",
			"imayam2_aasi","aasi");
	     Statement st=con.createStatement();
	     ResultSet rs = st.executeQuery("select distinct movie from music_catalog where movie like '"+query+"%'");
	     List<String> li = new ArrayList<String>();
	    //   List li = new ArrayList();
	 
	       while(rs.next())
	       {
	           li.add(rs.getString(1));
	       }
	     
	       String[] str = new String[li.size()];
	       
	       Iterator it = li.iterator();
	 
	       int i = 0;
	       while(it.hasNext())
	       {
	           String p = (String)it.next();
	           str[i] = p;
	           i++;
	       }
	 
	    //jQuery related start
	      
	 
	       int cnt=1;
	       for(int j=0;j<str.length;j++)
	       {
	           if(str[j].toUpperCase().startsWith(query.toUpperCase()))
	           {
	             out.print(str[j]+"\n");
	              if(cnt>=10)// 5=How many results have to show while we are typing(auto suggestions)
	              break;
	              cnt++;
	          //   request.setAttribute("json",str[j]);
			        request.getRequestDispatcher("/isai/index.jsp");
		       }
	            }
	           
	    //jQuery related end
	      
	rs.close();
	st.close();
	con.close();
	
		
	}catch(Exception e){
e.printStackTrace();
}

}
}
