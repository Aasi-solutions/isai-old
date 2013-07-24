package com.imayam.music;

import java.io.IOException;
import org.apache.commons.io.output.DeferredFileOutputStream;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.sun.mail.handlers.text_html;

import javax.servlet.ServletRequest;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class FileUploadServlet
 */
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(FileUploadServlet.class);
	private static final String errorPage = "error1.jsp";
	// private static final String UPLOAD_DIRECTORY ="";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	// location to store file uploaded

	// private static final String UPLOAD_DIRECTORY
	// =request.getParameter("fname");

	// upload settings
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	public FileUploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		// checks if the request actually contains upload file
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String action = request.getParameter("action");
		if ("home".equalsIgnoreCase(action)) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/WEB-INF/admin/admin.jsp");
			rd.forward(request, response);

		} else if ("loginaction".equalsIgnoreCase(action)) {
			String user = request.getParameter("user");
			String pass = request.getParameter("pass");
			if ((!user.equals("")) && (!pass.equals(""))) {
				try {

					Class.forName("com.mysql.jdbc.Driver");
//					Connection con = DriverManager.getConnection(
//						"jdbc:mysql://localhost:3306/imayam2_phpbb1",
//							"root", "aasi");
					 Connection con =
					 DriverManager.getConnection("jdbc:mysql://localhost:3306/imayam2_phpbb1",
					 "imayam2_aasi","aasi");

					String sql = "select user_nickname,user_password from music_user where user_nickname='"
							+ user + "' and user_password='" + pass + "'";
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					if (rs.next()) {
						String un = rs.getString("user_nickname");
						String pwd = rs.getString("user_password");
						if (user.equals(un) && pass.equals(pwd))

						{
							request.setAttribute("message", user);
							RequestDispatcher rd = request
									.getRequestDispatcher("/WEB-INF/admin/upload.jsp");
							rd.forward(request, response);
							logger.debug("welcome " + user);

						} else {

							String message = "hello";
							request.setAttribute("message", message);
							RequestDispatcher rd = request
									.getRequestDispatcher("/WEB-INF/admin/admin.jsp");
							rd.forward(request, response);
						}
					} else {

						String message = "Please Enter Correct Username and Password";
						request.setAttribute("message", message);
						RequestDispatcher rd = request
								.getRequestDispatcher("/WEB-INF/admin/admin.jsp");
						rd.forward(request, response);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
					logger.error("exception1",e);
				}

			} else {

				String message = "Please Enter Username and Password";
				request.setAttribute("message", message);
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/admin/admin.jsp");
				rd.forward(request, response);

			}
		}

		else if ("uploadaction".equalsIgnoreCase(action)) {
			if (!ServletFileUpload.isMultipartContent(request)) {
				// if not, we stop here
				PrintWriter writer = response.getWriter();
				writer.println("Error: Form must has enctype=multipart/form-data.");
				writer.flush();
				return;
			}

			// configures upload settings
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// sets memory threshold - beyond which files are stored in disk
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			// sets temporary location to store files
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			ServletFileUpload upload = new ServletFileUpload(factory);

			// sets maximum size of upload file
			upload.setFileSizeMax(MAX_FILE_SIZE);

			// sets maximum size of request (include file + form data)
			upload.setSizeMax(MAX_REQUEST_SIZE);

			// constructs the directory path to store upload file
		//	String uploadPath = "C:/AASI_WORK/2013" + File.separator;

			 String uploadPath =
			 "/home/imayam2/public_html/songs/2013"+File.separator;

			try {
				// parses the request's content to extract file data
				@SuppressWarnings("unchecked")
				List<FileItem> formItems = upload.parseRequest(request);

				if (formItems != null && formItems.size() > 0) {
					File uploadDir = null;

					// iterates over form's fields
					System.out.println();
					for (FileItem item : formItems) {
						String name = item.getFieldName();
						String value = item.getString().toString();

						// processes only fields that are not form fields
						if (!item.isFormField()) {
							System.out.println(item.getSize());
							String fileName = new File(item.getName())
									.getName();

							if (fileName.equals("")) {
								String message = "Please Choose a File";
								request.setAttribute("message", message);
								request.getRequestDispatcher(errorPage)
										.forward(request, response);
							} else if (!fileName.equals("")
									&& !value.equals("")) {
								if (!uploadDir.exists()) {
									uploadDir.mkdir();
									String filePath = uploadDir
											+ File.separator + fileName;
									File storeFile = new File(filePath);
									logger.debug("File name " + fileName
											+ "Fie Path" + filePath
											+ "Store file is" + storeFile);

									// saves the file on disk
									item.write(storeFile);
									request.setAttribute("message",
											"Upload has been done successfully!");
									break;
								} else if(uploadDir.exists()){
									String filePath = uploadDir
											+ File.separator + fileName;
									File storeFile = new File(filePath);
									logger.debug("File name " + fileName
											+ "Fie Path" + filePath
											+ "Store file is" + storeFile);

									// saves the file on disk
									item.write(storeFile);
									request.setAttribute("message",
											"Upload has been done successfully!");
									break;
//									
								}
								else
								{
									String message = "Please Choose a File";
									request.setAttribute("message", message);
									request.getRequestDispatcher(errorPage)
											.forward(request, response);
								}
							} else if (value.equals("") && fileName.equals("")) {
								String message = "Please Enter Folder name and Choose a File";
								request.setAttribute("message", message);
								request.getRequestDispatcher(errorPage)
										.forward(request, response);
							} else if (!value.equals("") && fileName.equals("")) {
								String message = "Please Choose a File";
								request.setAttribute("message", message);
								request.getRequestDispatcher(errorPage)
										.forward(request, response);
							} else if (value.equals("") && !fileName.equals("")) {
								String message = "Please Enter the Folder Name";
								request.setAttribute("message", message);
								request.getRequestDispatcher(errorPage)
										.forward(request, response);
							} else {
								String message = "Please Enter Folder name and Choose a File";
								request.setAttribute("message", message);
								request.getRequestDispatcher(errorPage)
										.forward(request, response);
							}

						}

						
						if(!value.equals(""))
						{
							logger.debug("name and value" + name + value);
							uploadDir = new File(uploadPath + value);
							logger.debug("uploadDir" + uploadDir);
						}else{
							String message = "Please Enter Folder name";
							request.setAttribute("message", message);
							request.getRequestDispatcher(errorPage)
									.forward(request, response);
							break;
						}
						

					}
				}
			}

			catch (Exception ex) {
				logger.error("Exception : " + ex.getMessage(), ex);
				request.getRequestDispatcher(errorPage).forward(request,
						response);

			}

			// redirects client to message page
			getServletContext().getRequestDispatcher("/message.jsp").forward(
					request, response);

		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}
}
