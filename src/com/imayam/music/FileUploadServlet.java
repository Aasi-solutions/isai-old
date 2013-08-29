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
import java.util.ArrayList;
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
		if ("adminedit".equalsIgnoreCase(action)) {
			ArrayList<SongVo> sv = new ArrayList<SongVo>();

			try {
				String getmovie = request.getParameter("searchartist");
				sv = DataAccess.getsongfields(getmovie);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// return;

			request.setAttribute("song", sv);
			request.getRequestDispatcher("/WEB-INF/admin/edit.jsp").forward(
					request, response);
			return;
		} else if ("addartist".equalsIgnoreCase(action)) {
			System.out.println("Entered into addartist action");
			String artistname = request.getParameter("txt");
			String id = request.getParameter("id");
			String movie = request.getParameter("movie");
			String artist_id = request.getParameter("aid");
			try {
				
				DataAccess.getArtistname(id, movie, artist_id, artistname);
				out.println("Inserted Artistname "+artistname+" catalog_id"+ id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else if ("Delete".equalsIgnoreCase(action)) {
			
			try {
				String arid=request.getParameter("aid");
			String cid = request.getParameter("id");
			
				DataAccess.deleteArtist(arid,cid);
				out.println("Deleted !!!"+ " Artist Id "+arid+"Catalog_id "+cid);
			}
			 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		else if ("update".equalsIgnoreCase(action)) {
			String id = request.getParameter("id");
			String artist_id = request.getParameter("aid");
			String movie = request.getParameter("movie");
			String song = request.getParameter("song");
			String composer = request.getParameter("composer");
			String lyrics = request.getParameter("lyrics");
			String artist_name = request.getParameter("artist");

			try {
				DataAccess.getDetails(id, artist_id, movie, song, composer,
						lyrics, artist_name);
				out.println("Changes Updated!!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if ("home".equalsIgnoreCase(action)) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/WEB-INF/admin/admin.jsp");
			rd.forward(request, response);

		} else if ("loginaction".equalsIgnoreCase(action)) {
			String user = request.getParameter("user");
			String pass = request.getParameter("pass");
			if ((!user.equals("")) && (!pass.equals(""))) {
				try {

					Class.forName("com.mysql.jdbc.Driver");
//				Connection con = DriverManager.getConnection(
//						"jdbc:mysql://localhost:3306/imayam2_phpbb1",
//						"root", "aasi");
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
			logger.debug("Line No:152");
			try {
			if (!ServletFileUpload.isMultipartContent(request)) {
				logger.debug("Line No:154");
				// if not, we stop here
				PrintWriter writer = response.getWriter();
				writer.println("Error: Form must has enctype=multipart/form-data.");
				writer.flush();
				return;
			}
			logger.debug("Line no:162");
			// configures upload settings
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// sets memory threshold - beyond which files are stored in disk
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			// sets temporary location to store files
			logger.debug("Temp Dir"+System.getProperty("java.io.tmpdir"));
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			ServletFileUpload upload = new ServletFileUpload(factory);

			// sets maximum size of upload file
			upload.setFileSizeMax(MAX_FILE_SIZE);

			// sets maximum size of request (include file + form data)
			upload.setSizeMax(MAX_REQUEST_SIZE);

			// constructs the directory path to store upload file
	//		String uploadPath = "C:/AASI_WORK/2013" + File.separator;

			 String uploadPath ="/home/imayam2/public_html/songs/2013";

			
				// parses the request's content to extract file data
			//	@SuppressWarnings("unchecked")
				List<FileItem> formItems = upload.parseRequest(request);
				logger.debug(formItems.size());
				if (formItems != null && formItems.size() > 0) {
					logger.debug("Line No:187");
					File uploadDir = null;

					// iterates over form's fields
					for (FileItem item : formItems) {
						logger.debug("Line No:192");
						String name = item.getFieldName();
						String value = item.getString().toString();

						// processes only fields that are not form fields
						if (!item.isFormField()) {
							logger.debug("Line No:198");
						//	System.out.println(item.getSize());
							String fileName = new File(item.getName())
									.getName();

							if (fileName.equals("")) {
								logger.debug("Line No:204");
								String message = "Please Choose a File";
								request.setAttribute("message", message);
								request.getRequestDispatcher(errorPage)
										.forward(request, response);
							} else if (!fileName.equals("")
									&& !value.equals("")) {
								logger.debug("Line No:211");
								if (!uploadDir.exists()) {
									logger.debug("Line No:213");
									uploadDir.mkdir();
									String filePath = uploadDir
											+ "/" + fileName;
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
									logger.debug("Line No:228");
									String filePath = uploadDir
											+ "/" + fileName;
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
									logger.debug("Line No:245");
									String message = "Please Choose a File";
									request.setAttribute("message", message);
									request.getRequestDispatcher(errorPage)
											.forward(request, response);
								}
							} else if (value.equals("") && fileName.equals("")) {
								logger.debug("Line No:252");
								String message = "Please Enter Folder name and Choose a File";
								request.setAttribute("message", message);
								request.getRequestDispatcher(errorPage)
										.forward(request, response);
							} else if (!value.equals("") && fileName.equals("")) {
								logger.debug("Line No:258");
								String message = "Please Choose a File";
								request.setAttribute("message", message);
								request.getRequestDispatcher(errorPage)
										.forward(request, response);
							} else if (value.equals("") && !fileName.equals("")) {
								logger.debug("Line No:264");
								String message = "Please Enter the Folder Name";
								request.setAttribute("message", message);
								request.getRequestDispatcher(errorPage)
										.forward(request, response);
							} else {
								logger.debug("Line No:270");
								String message = "Please Enter Folder name and Choose a File";
								request.setAttribute("message", message);
								request.getRequestDispatcher(errorPage)
										.forward(request, response);
							}

						}

						
						if(!value.equals(""))
						{
							logger.debug("Line No:282");
							logger.debug("name and value" + name + value);
							uploadDir = new File(uploadPath + value);
							logger.debug("uploadDir" + uploadDir);
						}else{
							logger.debug("Line No:287");
							String message = "Please Enter Folder name";
							request.setAttribute("message", message);
							request.getRequestDispatcher(errorPage)
									.forward(request, response);
							break;
						}
						

					}
				}
			}catch (Exception ex)  {
				logger.debug("Line no:301");
				logger.error("Exception : " + ex.getMessage(), ex);
				request.setAttribute("message", ex.getMessage());
				request.getRequestDispatcher(errorPage).forward(request,
						response);

			}catch (Throwable ex)  {
				logger.debug("Line no:308");
				logger.error("Throwable : " + ex.getMessage(), ex);
				request.setAttribute("message", ex.getMessage());
				request.getRequestDispatcher(errorPage).forward(request,
						response);
			}
			logger.debug("Line no:314");
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
