package com.imayam.music;
import java.util.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.TimerTask;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.smartcardio.ATR;
import javax.swing.JOptionPane;

import org.apache.catalina.connector.Response;
import org.apache.log4j.Logger;

public final class DataAccess  {


	static Logger logger = Logger.getLogger(DataAccess.class);

	public static void saveSongs(String movie, String song, String composer,
			ArrayList artist, String lyricist, String fileName,String imagename) throws Exception {

		Connection conn = getConnection();

		String sql = "INSERT INTO music_catalog(movie, song, composer, lyrics, file_name,month_hitcount,create_time,image_file_name) VALUES (?, ?, ?, ?, ?,0,sysdate(),?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, movie.trim());
		ps.setString(2, song.trim());
		ps.setString(3, composer.trim());
		ps.setString(4, lyricist);
		ps.setString(5, fileName);
		ps.setString(6, imagename);
		ps.execute();

		updateartist((ArrayList) artist, song, movie);

		ps.close();

		conn.close();
	}

	
	public static void updateartist(ArrayList artist, String song, String movie)
			throws Exception {

		
		Connection conn = getConnection();
		for (int i = 0; i < artist.size(); i++) {
			int artist_id =0;
			// System.out.println("name is"+artist.get(i).toString() );
			logger.debug(artist.get(i).toString());
			String sql = "select  artist_id from music_artist where artist_name='"
					+ artist.get(i).toString() + "'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				artist_id = rs.getInt("artist_id");
				if(artist_id!=0){
				
				String sql2 = "select id,artist_id from music_catalog a ,music_artist b where a.song='"
						+ song
						+ "'and a.movie='"
						+ movie
						+ "'  and b.artist_name='"
						+ artist.get(i).toString()
						+ "'";
				Statement stmt2 = conn.createStatement();
				ResultSet rs2 = stmt2.executeQuery(sql2);
				while (rs2.next()) {
					int id = rs2.getInt("id");
					int aid = rs2.getInt("artist_id");
					String sql3 = "insert into music_catalog_m2m_artist values("
							+ id + "," + aid + ")";
					Statement stmt3 = conn.createStatement();
					stmt3.execute(sql3);
				}

			}
			}
			 if(artist_id==0){
				// 1.insert a new artist in music_artist table
				String sql1 = "insert into music_artist values(null,'"
						+ artist.get(i).toString() + "',0)";
				Statement stmt1 = conn.createStatement();
				stmt1.execute(sql1);

				// 2.get the artist_id
				String sql2 = "select id,artist_id from music_catalog a ,music_artist b where a.song='"
						+ song
						+ "' and a.movie='"
						+ movie
						+ "'  and b.artist_name='"
						+ artist.get(i).toString()
						+ "'";
				Statement stmt2 = conn.createStatement();
				ResultSet rs2 = stmt2.executeQuery(sql2);
				while (rs2.next()) {
					int id = rs2.getInt("id");// movie_id
					int aid = rs2.getInt("artist_id");// artist_id
					String sql3 = "insert into music_catalog_m2m_artist values("
							+ id + "," + aid + ")";
					Statement stmt3 = conn.createStatement();
					stmt3.execute(sql3);
				}
				// 3.create m2m artist table(artist_id,catalog_id) select
				// catalog id using song and movie

			}
		}
		
		
		conn.close();
	}

	public static ArrayList getArtistsList() throws Exception {
		Connection conn = getConnection();
		int i = 0;
		ArrayList songsList = new ArrayList();
		String sql = "SELECT DISTINCT artist_name FROM music_artist ORDER BY hitcount desc";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			String artist = rs.getString("artist_name");
			logger.debug("Artist " + i++ + " : " + artist);
			songsList.add(artist);
		}
		conn.close();
		return songsList;
	}

	public static ArrayList getmovieSearchList(String s) throws Exception {
		Connection conn = getConnection();
		int i = 0;
		ArrayList songsList1 = new ArrayList();
		String sql = "SELECT DISTINCT movie FROM music_catalog where movie like '"
				+ s + "%' order by movie";

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			String movie = rs.getString("movie");
			logger.debug("movie " + i++ + " : " + movie);
			songsList1.add(movie);
		}
		conn.close();
		return songsList1;
	}
	
	public static ArrayList getnumbmovieSearchList() throws Exception {
		Connection conn = getConnection();
		int i = 0;
		ArrayList songsList1 = new ArrayList();
		String sql = "SELECT DISTINCT movie FROM music_catalog WHERE movie regexp '^[0-9]+' order by movie";

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			String movie = rs.getString("movie");
			logger.debug("movie " + i++ + " : " + movie);
			songsList1.add(movie);
		}
		conn.close();
		return songsList1;
	}

	public static ArrayList getMovieList() throws Exception {
		Connection conn = getConnection();
		int i = 0;
		ArrayList songsList = new ArrayList();
		String sql = "SELECT DISTINCT movie FROM music_catalog ORDER BY movie";

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			String movie = rs.getString("movie");
			logger.debug("movie " + i++ + " : " + movie);
			songsList.add(movie);
		}
		conn.close();
		return songsList;
	}

	public static ArrayList getComposerList() throws Exception {
		Connection conn = getConnection();
		int i = 0;
		ArrayList songsList = new ArrayList();
		String sql = "SELECT DISTINCT composer, sum(hitcount) as total FROM music_catalog group by composer ORDER BY total desc";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			String composer = rs.getString("composer");
			logger.debug("composer " + i++ + " : " + composer);
			songsList.add(composer);
		}
		conn.close();
		return songsList;
	}

	public static ArrayList getLyricsList() throws Exception {
		Connection conn = getConnection();
		int i = 0;
		ArrayList songsList = new ArrayList();
		String sql = "SELECT DISTINCT lyrics, sum(hitcount) as total FROM music_catalog group by lyrics ORDER BY total desc";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			String lyrics = rs.getString("lyrics");
			logger.debug("lyrics " + i++ + " : " + lyrics);
			songsList.add(lyrics);
		}
		conn.close();
		return songsList;
	}

	public static String getSongsList(String artist) throws Exception {
		Connection conn = getConnection();
		StringBuffer myPlayList = new StringBuffer();
		String sql = null;
		ResultSet rs = null;
		if ("all".equalsIgnoreCase(artist)) {
			sql = "SELECT distinct song, movie, file_name FROM music_catalog order by hitcount desc limit 0, 100";
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} else if ("Monthly".equalsIgnoreCase(artist)) {
			sql = "SELECT distinct song, movie, file_name FROM music_catalog order by month_hitcount desc limit 0, 100";
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} else if ("NewAdds".equalsIgnoreCase(artist)) {
			sql = "SELECT distinct song, movie, file_name FROM music_catalog order by create_time desc limit 0, 100";
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} else {
			sql = "SELECT distinct song, movie, file_name FROM music_catalog a, music_artist b, music_catalog_m2m_artist c, music_playlist d, music_catalog_m2m_playlist e WHERE (artist_name = ? or movie = ? or composer = ? or lyrics = ? or playlist_name = ?) and a.id = c.catalog_id and b.artist_id = c.artist_id and d.playlist_id = e.playlist_id and e.catalog_id = a.id order by a.hitcount desc limit 0, 100";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, artist);
			ps.setString(2, artist);
			ps.setString(3, artist);
			ps.setString(4, artist);
			ps.setString(5, artist);
			rs = ps.executeQuery();
		}

		myPlayList
				.append("<playlist version='1' xmlns='http://xspf.org/ns/0/'>\n");
		myPlayList.append("<trackList>\n");
		while (rs.next()) {
			myPlayList.append("\t<track>\n");
			myPlayList.append("\t\t<title>" + rs.getString("movie") + ":"
					+ rs.getString("song") + "</title>\n");
			myPlayList.append("\t\t<location>");
		
			myPlayList.append(rs.getString("file_name").replaceAll(
					"/home/imayam2/public_html", "http://www.imayam.org"));
			myPlayList.append("</location>\n");
			myPlayList.append("\t</track>\n");
		}
		myPlayList.append("</trackList>\n");
		myPlayList.append("</playlist>\n");
		conn.close();
		return myPlayList.toString();
	}

	public static String getSongsListRss(String artist) throws Exception {
		Connection conn = getConnection();
		StringBuffer myPlayList = new StringBuffer();
		String sql = null;
		ResultSet rs = null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		if ("all".equalsIgnoreCase(artist)) {
			sql = "SELECT distinct id, song, movie, composer, lyrics, file_name, image_file_name FROM music_catalog order by hitcount desc limit 0, 100";
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} else if ("Monthly".equalsIgnoreCase(artist)) {
			sql = "SELECT distinct id, song, movie, composer, lyrics, file_name, image_file_name FROM music_catalog order by month_hitcount desc limit 0, 100";
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} else if ("NewAdds".equalsIgnoreCase(artist)) {
			sql = "SELECT distinct id, song, movie, composer, lyrics, file_name, image_file_name FROM music_catalog order by create_time desc limit 0, 100";
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} else {
			sql = "SELECT distinct a.id, a.song, a.movie, a.composer, a.lyrics, a.file_name, a.image_file_name,a.hitcount FROM music_catalog a, music_artist b, music_catalog_m2m_artist c WHERE (artist_name = ? or movie = ? or composer = ? or lyrics = ?) and a.id = c.catalog_id and b.artist_id = c.artist_id order by hitcount desc limit 0, 100";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, artist);
			ps.setString(2, artist);
			ps.setString(3, artist);
			ps.setString(4, artist);
			rs = ps.executeQuery();
			String sql1="Select artist_id from music_artist where artist_name='"+artist+"'";
			Statement stmt = conn.createStatement();
		 rs2 = stmt.executeQuery(sql1);
		 while(rs2.next()){
				int artist_id =rs2.getInt("artist_id");
				updateartistCount(artist_id);
			}
					}
		myPlayList
				.append("<rss version='2.0' xmlns:media='http://search.yahoo.com/mrss/' xmlns:jwplayer='http://developer.longtailvideo.com/trac/'>\n");
		myPlayList.append("<channel>\n");
		
		myPlayList.append("<title>imayam.org playlist</title>");
		for (; rs.next(); updateCount(rs.getInt("id"))) {
			
			myPlayList.append("\t<item>\n");
			myPlayList.append((new StringBuilder("\t\t<title>"))
					.append(rs.getString("movie")).append(" : ")
					.append(rs.getString("song")).append("</title>\n")
					.toString());
			myPlayList.append((new StringBuilder("\t\t<description>"))
					.append(rs.getString("composer")).append(" : ")
					.append(rs.getString("lyrics")).append("\n"));
			
			int id = rs.getInt("id");
			String sql1 ="select b.artist_name,b.artist_id from music_catalog a, music_artist b, music_catalog_m2m_artist c WHERE id='"+id+"' and a.id = c.catalog_id and b.artist_id = c.artist_id ; ";
			Statement stmt = conn.createStatement();
			 rs1 = stmt.executeQuery(sql1);
					while(rs1.next()){
						
				String artist1 = rs1.getString("artist_name");
				//int artist_id =rs1.getInt("artist_id");
				//updateartistCount(artist_id);
				if(rs1.isLast())
				{
					myPlayList.append(artist1);
				}else
				{
					myPlayList.append(artist1).append(",");
				}
				
			}
					myPlayList.append("</description>\n").toString();
					
			myPlayList.append("\t\t<media:content url='");
			myPlayList.append(rs.getString("file_name").replaceAll(
					"/home/imayam2/public_html", "http://www.imayam.org"));
			myPlayList.append("' />\n");
			myPlayList.append("<media:thumbnail url='");
			myPlayList.append(rs.getString("image_file_name").replaceAll(
					"/home/imayam2/public_html", "http://www.imayam.org"));
			myPlayList.append("' />\n");
			myPlayList.append("\t</item>\n");
			
		
		}
		myPlayList.append("</channel>\n");
		myPlayList.append("</rss>\n");
		
		conn.close();
		return myPlayList.toString();
	}

	private static StringBuffer append(String artist1) {
		// TODO Auto-generated method stub
		return null;
	}


	public static String getSearch(String artist) throws Exception {
		Connection conn = getConnection();
		StringBuffer myPlayList = new StringBuffer();
		String sql = null;
		ResultSet rs = null;
		String per = "%";
		artist = artist.concat(per);
		sql = "SELECT distinct song,movie,file_name,image_file_name,composer,lyrics,id FROM music_catalog where  movie sounds like ? or song sounds like ? ;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, artist);
		ps.setString(2, artist);
		rs = ps.executeQuery();

		myPlayList
				.append("<rss version='2.0' xmlns:media='http://search.yahoo.com/mrss/' xmlns:jwplayer='http://developer.longtailvideo.com/trac/'>\n");
		myPlayList.append("<channel>\n");
		myPlayList.append("<title>imayam.org playlist</title>");

		

		for (; rs.next(); updateCount(rs.getInt("id"))) {
		
			myPlayList.append("\t<item>\n");
			myPlayList.append("\t\t<title>" + rs.getString("movie") + ":"
					+ rs.getString("song") + "</title>\n");
			myPlayList.append((new StringBuilder("\t\t<description>"))
					.append(rs.getString("composer")).append(" : ")
					.append(rs.getString("lyrics")).append("\n"));
				
			int id = rs.getInt("id");
			String sql1 ="select b.artist_name,b.artist_id from music_catalog a, music_artist b, music_catalog_m2m_artist c WHERE id='"+id+"' and a.id = c.catalog_id and b.artist_id = c.artist_id ; ";
			Statement stmt = conn.createStatement();
			ResultSet rs1 = stmt.executeQuery(sql1);
					while(rs1.next()){
						
				String artist1 = rs1.getString("artist_name");
				int artist_id =rs1.getInt("artist_id");
				updateartistCount(artist_id);
				if(rs1.isLast())
				{
					myPlayList.append(artist1);
				}else
				{
					myPlayList.append(artist1).append(",");
				}
				
				
			}
					myPlayList.append("</description>\n").toString();
			myPlayList.append("\t\t<media:content url='");
		
			myPlayList.append(rs.getString("file_name").replaceAll(
					"/home/imayam2/public_html", "http://www.imayam.org"));
			myPlayList.append("' />\n");
			myPlayList.append("<media:thumbnail url='");
			myPlayList.append(rs.getString("image_file_name").replaceAll(
					"/home/imayam2/public_html", "http://www.imayam.org"));
			myPlayList.append("' />\n");
			myPlayList.append("\t</item>\n");
		}

		myPlayList.append("</channel>\n");
		myPlayList.append("</rss>\n");
		conn.close();
		return myPlayList.toString();
	}

	public static String getSongs(String artist) throws Exception {
		Connection conn = getConnection();
		StringBuffer myPlayList = new StringBuffer();
		String sql = null;
		ResultSet rs = null;
	//sql="SELECT DISTINCT a.song,a.movie,a.file_name,a.image_file_name,a.composer,a.lyrics,a.id,b.artist_name FROM music_catalog a ,music_artist b,music_catalog_m2m_artist c  where movie = ? and  a.id = c.catalog_id and b.artist_id=c.artist_id";
		sql = "SELECT DISTINCT song,movie,file_name,image_file_name,composer,lyrics,id FROM music_catalog where movie= ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, artist);
		rs = ps.executeQuery();

		myPlayList
				.append("<rss version='2.0' xmlns:media='http://search.yahoo.com/mrss/' xmlns:jwplayer='http://developer.longtailvideo.com/trac/'>\n");
		myPlayList.append("<channel>\n");
		myPlayList.append("<title>imayam.org playlist</title>");
		for (; rs.next(); updateCount(rs.getInt("id"))) {
		
			myPlayList.append("\t<item>\n");
			myPlayList.append("\t\t<title>" + rs.getString("movie") + ":"
					+ rs.getString("song") + "</title>\n");
			myPlayList.append((new StringBuilder("\t\t<description>"))
					.append(rs.getString("composer")).append(" : ")
					.append(rs.getString("lyrics")).append("\n"));
			int id = rs.getInt("id");
			String sql1 ="select b.artist_name,b.artist_id from music_catalog a, music_artist b, music_catalog_m2m_artist c WHERE id='"+id+"' and a.id = c.catalog_id and b.artist_id = c.artist_id ; ";
			Statement stmt = conn.createStatement();
			ResultSet rs1 = stmt.executeQuery(sql1);
					while(rs1.next()){
						
				String artist1 = rs1.getString("artist_name");
				int artist_id =rs1.getInt("artist_id");
				updateartistCount(artist_id);
				if(rs1.isLast())
				{
					myPlayList.append(artist1);
				}else
				{
					myPlayList.append(artist1).append(",");
				}
			}
					myPlayList.append("</description>\n").toString();
			myPlayList.append("\t\t<media:content url='");
		
			myPlayList.append(rs.getString("file_name").replaceAll(
					"/home/imayam2/public_html", "http://www.imayam.org"));
			myPlayList.append("' />\n");
			myPlayList.append("<media:thumbnail url='");
			myPlayList.append(rs.getString("image_file_name").replaceAll(
					"/home/imayam2/public_html", "http://www.imayam.org"));
			myPlayList.append("' />\n");
			myPlayList.append("\t</item>\n");
		}
		myPlayList.append("</channel>\n");
		myPlayList.append("</rss>\n");
		conn.close();
		return myPlayList.toString();
	}

	public static String getCustomRssPlayList(String artist) throws Exception {
		Connection conn = getConnection();
		StringBuffer myPlayList = new StringBuffer();
		String sql = null;
		ResultSet rs = null;
		sql = "SELECT distinct id, song, movie, composer, lyrics, file_name, image_file_name FROM music_catalog a, music_playlist d, music_catalog_m2m_playlist e WHERE playlist_name = ? and d.playlist_id = e.playlist_id and e.catalog_id = a.id order by a.hitcount desc limit 0, 100";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, artist);
		rs = ps.executeQuery();
		myPlayList
				.append("<rss version='2.0' xmlns:media='http://search.yahoo.com/mrss/' xmlns:jwplayer='http://developer.longtailvideo.com/trac/'>\n");
		myPlayList.append("<channel>\n");
		myPlayList.append("<title>imayam.org playlist</title>");
		for (; rs.next(); updateCount(rs.getInt("id"))) {
			myPlayList.append("\t<item>\n");
			myPlayList.append((new StringBuilder("\t\t<title>"))
					.append(rs.getString("movie")).append(" : ")
					.append(rs.getString("song")).append("</title>\n")
					.toString());
			myPlayList.append((new StringBuilder("\t\t<description>"))
					.append(rs.getString("composer")).append(" : ")
					.append(rs.getString("lyrics")).append("\n"));
					
			int id = rs.getInt("id");
			String sql1 ="select b.artist_name,b.artist_id from music_catalog a, music_artist b, music_catalog_m2m_artist c WHERE id='"+id+"' and a.id = c.catalog_id and b.artist_id = c.artist_id ; ";
			Statement stmt = conn.createStatement();
			ResultSet rs1 = stmt.executeQuery(sql1);
					while(rs1.next()){
						
				String artist1 = rs1.getString("artist_name");
				int artist_id =rs1.getInt("artist_id");
				updateartistCount(artist_id);
				if(rs1.isLast())
				{
					myPlayList.append(artist1);
				}else
				{
					myPlayList.append(artist1).append(",");
				}
			}
					myPlayList.append("</description>\n").toString();
			
			myPlayList.append("\t\t<media:content url='");
			myPlayList.append(rs.getString("file_name").replaceAll(
					"/home/imayam2/public_html", "http://www.imayam.org"));
			myPlayList.append("' />\n");
			myPlayList.append("<media:thumbnail url='");
			myPlayList.append(rs.getString("image_file_name").replaceAll(
					"/home/imayam2/public_html", "http://www.imayam.org"));
			myPlayList.append("' />\n");
			myPlayList.append("\t</item>\n");
		}

		myPlayList.append("</channel>\n");
		myPlayList.append("</rss>\n");
		conn.close();
		return myPlayList.toString();
	}

	public static void updateCount(int id) throws Exception {
		Connection conn = getConnection();
		String sql = "UPDATE music_catalog SET hitcount=hitcount+1, month_hitcount=month_hitcount+1 where id = ?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();
		ps.close();
		conn.close();
	}
	public static void updateartistCount(int artist_id) throws Exception {
		Connection conn = getConnection();
		String sql = "UPDATE music_artist SET hitcount=hitcount+1 where artist_id = ?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, artist_id);
		ps.execute();
		ps.close();
		conn.close();
	}
	public static void updatemonthCount() throws Exception {
		Connection conn = getConnection();
		String sql = "UPDATE music_catalog SET month_hitcount=0";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.execute();
		ps.close();
		conn.close();
	}
	
	public static ArrayList<GetMovie> getImage() throws Exception {
		Connection conn = getConnection();	
		ArrayList<GetMovie>getimage = new ArrayList<GetMovie>();
		String sql = "select movie as m,image_file_name as i,sum(month_hitcount) as a,count(song) as b ,sum(month_hitcount)/count(song) as c from music_catalog group by movie order by c desc limit 0, 3";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			String image = rs.getString("i");
			String str1=image.replace("/home/imayam2/public_html/", "http://www.imayam.org/");
			String movie = rs.getString("m");
			GetMovie gv= new GetMovie();
			gv.setMoviename(movie);
			gv.setImage(str1);
			getimage.add(gv);
			}
		conn.close();
		return getimage;
	}
	
	public static void getArtistname(String id,String movie,String artist_id,String artist_name) throws Exception {
		Connection conn = getConnection();
		
		String sql="select artist_id from music_artist where artist_name='"+artist_name+"'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {	
			String aid=rs.getString("artist_id");
			String sql1="select artist_id,catalog_id from music_catalog_m2m_artist where catalog_id='"+id+"'";
			Statement stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
			if (rs1.next()) {
				String arid=rs1.getString("artist_id");
				if(!arid.equals(aid))
				{
					String sql3="insert into music_catalog_m2m_artist values ('"+id+"','"+aid+"')";
					Statement stmt3 = conn.createStatement();
					stmt3.execute(sql3);
					
				}
				
			}	
			else{
			String sql3="insert into music_catalog_m2m_artist values ('"+id+"','"+aid+"')";
			Statement stmt3 = conn.createStatement();
			stmt3.execute(sql3);
			System.out.println("Inserted");
			}
		}else{
			String sql1 = "insert into music_artist values(null,'"
					+ artist_name + "',0)";
			Statement stmt1 = conn.createStatement();
			stmt1.execute(sql1);
			DataAccess.getArtistname(id, movie, artist_id, artist_name);
		}
		conn.close();
		}
	
	public static void deleteArtist(String arid, String cid) throws Exception {
		Connection conn = getConnection();
		String sql="delete from music_catalog_m2m_artist where artist_id='"+arid+"' and catalog_id='"+cid+"'" ;
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.execute();
		System.out.println("Deleted"+arid+cid);
		ps.close();		
		
		}
	
	 public static void getDetails(String id,String artist_id,String movie,String song,String composer,String lyrics,String artist_name) throws Exception {
		Connection conn = getConnection();		
		String sql1 = "update music_catalog a set a.movie='"+movie+"',a.song='"+song+"',a.composer='"+composer+"',a.lyrics='"+lyrics+"' where a.id='"+id+"'";
		//String sql="update music_artist set artist_name='"+artist_name+"' where music_catalog.catalog_id=music_catalog_m2m_artist.catalog_id and music_artist.artist_id=music_catalog_m2m_artist.artist_id;"
		PreparedStatement ps = conn.prepareStatement(sql1);
		ps.execute();
		ps.close();
		}
	 
	 public static ArrayList<SongVo> getsongfields(String getmovie) throws Exception {
			Connection conn = getConnection();
			ArrayList<SongVo> songlist = new ArrayList<SongVo>();
			//select * from music_catalog a left join music_catalog_m2m_artist b on(a.id = b.catalog_id) where a.movie='3'; and b.artist_id=c.artist_id
			//select distinct a.id,a.movie,a.song,a.composer,a.lyrics,c.artist_id,c.artist_name from music_catalog a left join music_catalog_m2m_artist b on (a.id=b.catalog_id)  left join  music_artist c on( c.artist_id=b.artist_id) where  a.movie='3';
			String sql = "select distinct a.id,a.movie,a.song,a.composer,a.lyrics,c.artist_id,c.artist_name from music_catalog a left join music_catalog_m2m_artist b on (a.id=b.catalog_id) left join music_artist c on( c.artist_id=b.artist_id) where a.movie='"+getmovie+"'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {	
				String id = rs.getString("id");
				String movie=rs.getString("movie");
				String song = rs.getString("song");
				String composer = rs.getString("composer");
				String lyrics = rs.getString("lyrics");
				String artist = rs.getString("artist_name");
				String artist_id=rs.getString("artist_id");
			
				SongVo av=new SongVo();
				av.setId(id);
				av.setMovieName(movie);
				av.setsongName(song);
				av.setComposerName(composer);
				av.setLyricistName(lyrics);
				av.setArtistName(artist);
				av.setArtistId(artist_id);
				songlist.add(av);
				
		}								
			conn.close();
			return songlist;
		}
	
	private static Connection getConnection() throws Exception {

		// Register the JDBC driver for MySQL.
		Class.forName("com.mysql.jdbc.Driver");
//	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imayam2_phpbb1", "root","aasi");// local connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imayam2_phpbb1", "imayam2_aasi","aasi");//Server connection
	// Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/imayam77_phpbb1","imayam77_phpbb1", "");

		return con;
	}

	
		 
}
