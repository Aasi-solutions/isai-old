package com.imayam.music;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUploadServlet
 */
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			System.out.println("Enters into adminaction if loop");
			RequestDispatcher rd = request
					.getRequestDispatcher("/WEB-INF/admin/admin.jsp");
			rd.forward(request, response);
		}

		else if ("loginaction".equalsIgnoreCase(action)) {
			System.out.println("Enters into loginaction if loop");
			RequestDispatcher rd = request
					.getRequestDispatcher("/WEB-INF/admin/upload.jsp");
			rd.forward(request, response);
		}

		else if ("uploadaction".equalsIgnoreCase(action)) {
			System.out.println("Enters into uploadaction if loop");
		

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
			String uploadPath = "c://temp" + File.separator;

			try {
				// parses the request's content to extract file data
				@SuppressWarnings("unchecked")
				List<FileItem> formItems = upload.parseRequest(request);

				if (formItems != null && formItems.size() > 0) {
					File uploadDir = null;
					// iterates over form's fields
					for (FileItem item : formItems) {
						System.out.println(item.getSize());
						// processes only fields that are not form fields
						if (!item.isFormField()) {
							System.out.println(item.getSize());
							String fileName = new File(item.getName())
									.getName();
							String filePath = uploadDir + File.separator
									+ fileName;
							File storeFile = new File(filePath);
							System.out.println("File name " + fileName
									+ "Fie Path" + filePath + "Store file is"
									+ storeFile);
							// saves the file on disk
							item.write(storeFile);
							request.setAttribute("message",
									"Upload has been done successfully!");
							break;
						}

						String name = item.getFieldName();
						String value = item.getString();
						System.out.println("name and value" + name + value);
						uploadDir = new File(uploadPath + value);
						System.out.println("uploadDir" + uploadDir);
						if (!uploadDir.exists()) {
							uploadDir.mkdir();
						}
					}
				}
			}

			catch (Exception ex) {
				System.out.println("Exception" + ex.toString());
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
