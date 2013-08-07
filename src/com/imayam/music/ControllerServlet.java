package com.imayam.music;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
//import org.jaudiotagger.tag.TagFieldKey;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagTextField;
import org.jaudiotagger.tag.asf.AsfTag;
import org.jaudiotagger.tag.id3.ID3v23Tag;

public class ControllerServlet extends HttpServlet {
	private static final Logger logger = Logger
			.getLogger(ControllerServlet.class);
	private static final String errorPage = "error.jsp";

	public ControllerServlet() {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
		try {			
			HttpSession session = request.getSession();			
			ArrayList<GetMovie> gm = new ArrayList<GetMovie>();
			gm = DataAccess.getImage();
			request.setAttribute("img1", gm);
			request.getRequestDispatcher("/songs/radio/index.jsp").forward(request, response);
			doPost(request, response);
			return;
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
		logger.error(e);
		}

	}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
		//	DataAccess.updatemonthCount();
			String action = request.getParameter("action");
			logger.debug("action : " + action);
		if ("reindex".equalsIgnoreCase(action)) {
				String dir = request.getParameter("dir");
				if (dir != null) {
					File myFiles = new File((new StringBuilder(
					"/home/imayam2/public_html/songs/")).append(dir)
					.toString());
		//File myFiles = new File(new StringBuilder("C:/AASI_WORK/naveen/songs/").append(dir).toString());
					StringBuffer buffer = getFileNames(myFiles);
				    PrintWriter out = response.getWriter();
					response.setContentType("text/plain");
					out.print(buffer.toString());
					out.flush();
				}
			}
		 if ("moviesearch".equalsIgnoreCase(action)) {

				String s = request.getParameter("id");
				//session.setAttribute("artists1", null);
				ArrayList songsList = DataAccess.getmovieSearchList(s);
				session.setAttribute("artists1", songsList);
				// request.getRequestDispatcher("/songs/radio/index.jsp").forward(request,
				// response);
				response.sendRedirect("/isai/songs/radio/index.jsp");

			} else if ("numbmoviesearch".equalsIgnoreCase(action)) {

			//	String s = request.getParameter("id");
				//session.setAttribute("artists1", null);
				ArrayList songsList = DataAccess.getnumbmovieSearchList();
				session.setAttribute("artists1", songsList);
				// request.getRequestDispatcher("/songs/radio/index.jsp").forward(request,
				// response);
				response.sendRedirect("/isai/songs/radio/index.jsp");

			}else if ("artist".equalsIgnoreCase(action)) {
				session.setAttribute("artists1", null);
				ArrayList songsList = DataAccess.getArtistsList();
				session.setAttribute("artists", songsList);
				// request.getRequestDispatcher("/songs/radio/index.jsp").forward(request,
				// response);
				response.sendRedirect("/isai/songs/radio/index.jsp");
			} else if ("movie".equalsIgnoreCase(action)) {
				session.setAttribute("artists1", null);
				ArrayList songsList = DataAccess.getMovieList();
				session.setAttribute("artists", songsList);
				// request.getRequestDispatcher("/songs/radio/index.jsp").forward(request,
				// response);
				response.sendRedirect("/isai/songs/radio/index.jsp");
			} else if ("composer".equalsIgnoreCase(action)) {
				session.setAttribute("artists1", null);
				ArrayList songsList = DataAccess.getComposerList();
				session.setAttribute("artists", songsList);
				// request.getRequestDispatcher("/songs/radio/index.jsp").forward(request,
				// response);
				response.sendRedirect("/isai/songs/radio/index.jsp");
			} else if ("lyrics".equalsIgnoreCase(action)) {
				session.setAttribute("artists1", null);
				ArrayList songsList = DataAccess.getLyricsList();
				session.setAttribute("artists", songsList);
				// request.getRequestDispatcher("/songs/radio/index.jsp").forward(request,
				// response);
				response.sendRedirect("/isai/songs/radio/index.jsp");
			} else if (action != null && action.indexOf("playSong") > -1) {
				getPlayList(action, response);
			} else if (action != null && action.indexOf("search") > -1) {
				session.setAttribute("artists1", null);
				getsearch(action, response);
			} else if (action != null && action.indexOf("playRssSong") > -1) {
				session.setAttribute("artists1", null);
				getPlayListRss(action, response);
			} else if (action != null && action.indexOf("getSongs") > -1) {
				getSongs(action, response);
				session.setAttribute("artists1", null);
			} else if (action != null
					&& action.indexOf("customRssPlayList") > -1) {
				session.setAttribute("artists1", null);
				getcustomRssPlayList(action, response);
			} else {
				request.getRequestDispatcher("/isai/songs/radio/index.jsp")
						.forward(request, response);
			}
		}

		catch (Exception e) {
			logger.error("Exception : " + e.getMessage(), e);
			request.getRequestDispatcher(errorPage).forward(request, response);
		}
	}

	public void getPlayList(String action, HttpServletResponse response)
			throws Exception {
		// String artistTest = request.getParameter("artist");
		// String artist = "Hariharan";
		String artist = action.replaceAll("playSong", "");
		String playList = DataAccess.getSongsList(artist);
		// HttpSession session = request.getSession();
		// session.setAttribute("playList", playList);
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		out.print(playList);
		out.flush();
	}

	public void getSongs(String action, HttpServletResponse response)
			throws Exception {
		// String artistTest = request.getParameter("artist");
		// String artist = "Hariharan";
		String artist = action.replaceAll("getSongs", "");
		String playList = DataAccess.getSongs(artist);
		// HttpSession session = request.getSession();
		// session.setAttribute("playList", playList);
	
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		out.print(playList);
		out.flush();
	}

	public void getcustomRssPlayList(String action, HttpServletResponse response)
			throws Exception {
		// String artistTest = request.getParameter("artist");
		// String artist = "Hariharan";
		String artist = action.replaceAll("customRssPlayList", "");
		String playList = DataAccess.getCustomRssPlayList(artist);
		// HttpSession session = request.getSession();
		// session.setAttribute("playList", playList);
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		out.print(playList);
		out.flush();
	}

	public void getPlayListRss(String action, HttpServletResponse response)
			throws Exception {
		// String artistTest = request.getParameter("artist");
		// String artist = "Hariharan";
		String artist = action.replaceAll("playRssSong", "");
		String playList = DataAccess.getSongsListRss(artist);
		// HttpSession session = request.getSession();
		// session.setAttribute("playList", playList);
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		out.print(playList);
		out.flush();
	}

	public void getsearch(String action, HttpServletResponse response)
			throws Exception {
		// String artistTest = request.getParameter("artist");
		// String artist = "Hariharan";
		String artist = action.replaceAll("search", "");
		String playList = DataAccess.getSearch(artist);
		// HttpSession session = request.getSession();
		// session.setAttribute("playList", playList);
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		out.print(playList);
		out.flush();
	}

	/**
	 * @param folder
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getFileNames(File folder) throws Exception {
		String[] mySystem = folder.list();
		StringBuffer buffer = new StringBuffer();
			
					for (int i = 0; i < mySystem.length; i++) {
					
						for (int j = 0; j < mySystem.length; j++) {
						String extension = mySystem[j].substring(mySystem[j].lastIndexOf(".") + 1, mySystem[j].length());
						String image = "jpg";
							if (extension.equals(image)) {
								File imageFile = new File(folder + "/" + mySystem[j]);
							
					String image1 = "jpg";
					String extension1 = mySystem[i].substring(mySystem[i].lastIndexOf(".") + 1, mySystem[i].length());
				if (extension1.equals(image1)) {
					
				}else{
				
					File newFile = new File(folder + "/" + mySystem[i]);
					if (newFile.isDirectory()) {
						getFileNames(newFile);
				} else {
						buffer.append(getTagValues(newFile,imageFile));
						
				}
						}
							}
							
				
							
				}
					}
		
			return buffer;
		
	}

	public StringBuffer getTagValues(File filename,File imagename ) throws Exception {
		
		AudioFile audioFile = AudioFileIO.read(filename);
		Image img = ImageIO.read(imagename);
		ID3v23Tag tag = (ID3v23Tag) audioFile.getTag();
		StringBuffer buffer = new StringBuffer();
		ArrayList artist = new ArrayList();
		
		String tempArtist=	tag.getFirst(FieldKey.ARTIST);
		String album = tag.getFirst(FieldKey.ALBUM);
		String title = tag.getFirst(FieldKey.TITLE);
		String lyricist=tag.getFirst(FieldKey.LYRICIST);
		String composer = tag.getFirst(FieldKey.COMPOSER);
//		String tempArtist =audioFile.getTag().getFirstArtist();
//		String album = audioFile.getTag().getFirstAlbum();
//		String title = audioFile.getTag().getFirstTitle();
	//	String lyrics = audioFile.getTag().getFirst(TagFieldKey.LYRICS);
	  //String composer = audioFile.getTag().getFirst(TagFieldKey.COMPOSER);

		buffer.append("This is : " + filename.getAbsoluteFile());
		buffer.append("This is : " + imagename.getAbsoluteFile());
		buffer.append("\nArtist : " + tempArtist + "\n");

		String regex = "[&;,]";
		String[] tokens = tempArtist.split(regex);
		for (int a = 0; a < tokens.length; a++) {
			String token = tokens[a];
			buffer.append(token);
			artist.add(token);
		}

		buffer.append("Album : " + album + "\n");
		buffer.append("title : " + title + "\n");
		buffer.append("composer : " + composer + "\n\n");
		DataAccess.saveSongs(album, title, composer, artist, lyricist,
				filename.getAbsolutePath(),imagename.getAbsolutePath());

		return buffer;
	}

}
