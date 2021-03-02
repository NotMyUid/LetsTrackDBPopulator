package populator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class populator {
	
	///////////////////////// INPUT ////////////////////////////////
	
	private String namePath = "path to name.txt";
	private String latPath = "path to lat.txt";
	private String lonPath = "path to lon.txt";
	
	private String mailPath = "path to mail.txt";
	private String usernamePath = "path to username.txt";
	private String passwordPath = "path to password.txt";
	
	private String timesPath = "path to times.txt";
	private String distancesPath = "path to distances.txt";
	
	private String timeHistPath = "path to timeHist.txt";
	private String dateHistPath = "path to dateHist.txt";
	private String hubHistPath = "path to hubHist.txt";
	
	////////////////////////////////////////////////////////////////
	
	
	//////////////////////// OUTPUT ////////////////////////////////
	
	private String CityPath = "path to City.txt";
	private String CustomerPath = "path to Customer.txt";
	private String Sub_RoutePath = "path to Sub_Route.txt";
	private String ConnectionPath = "path to Connection.txt";
	private String RoutePath = "path to Route.txt";
	private String OrdersPath = "path to Orders.txt";
	private String HistoryPath = "path to History.txt";
	
	////////////////////////////////////////////////////////////////
	

	private void city() throws IOException {

		Path file = Paths.get(namePath);
		List<String> lines = Files.readAllLines(file, Charset.defaultCharset());
		Path file1 = Paths.get(latPath);
		List<String> lines1 = Files.readAllLines(file1, Charset.defaultCharset());
		Path file2 = Paths.get(lonPath);
		List<String> lines2 = Files.readAllLines(file2, Charset.defaultCharset());
		PrintWriter writer = new PrintWriter(CityPath, "UTF-8");
		
		for(int i = 0; i<lines.size(); ++i) {
			int num = i+1;
			writer.println( num + "," + lines.get(i) + "," + "\"Point(" + lines1.get(i) + "," + lines2.get(i) + ")\"" );//System.out.println("INSERT INTO APP_LETSTRACK_City VALUES (" + num + ",'" + lines.get(i) + "','" + "Point(" + lines1.get(i) + "," + lines2.get(i) + ")'" + ");");
		}
	
		writer.close();
		
	}
	
	private void customer() throws IOException {

		Path file = Paths.get(mailPath);
		List<String> lines = Files.readAllLines(file, Charset.defaultCharset());
		Path file1 = Paths.get(usernamePath);
		List<String> lines1 = Files.readAllLines(file1, Charset.defaultCharset());
		Path file2 = Paths.get(passwordPath);
		List<String> lines2 = Files.readAllLines(file2, Charset.defaultCharset());
		PrintWriter writer = new PrintWriter(CustomerPath, "UTF-8");
		
		for(int i = 0; i<lines.size(); ++i) {
			writer.println( lines.get(i) + "," + lines1.get(i) + "," + lines2.get(i) );//System.out.println("INSERT INTO APP_LETSTRACK_Customer VALUES ('" + lines.get(i) + "','" + lines1.get(i) + "','" + lines2.get(i) + "');");
		}
	
		writer.close();
		
	}
	
	private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
	      double theta = lon1 - lon2;
	      double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	      dist = Math.acos(dist);
	      dist = rad2deg(dist);
	      dist = dist * 60 * 1.1515;
	      if (unit == 'K') {
	        dist = dist * 1.609344;
	      } else if (unit == 'N') {
	        dist = dist * 0.8684;
	        }
	      return (dist);
	    }
	    
	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    /*::  This function converts decimal degrees to radians             :*/
	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    private double deg2rad(double deg) {
	      return (deg * Math.PI / 180.0);
	    }
	    
	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    /*::  This function converts radians to decimal degrees             :*/
	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    private double rad2deg(double rad) {
	      return (rad * 180.0 / Math.PI);
	    }
	
	private void sub_route() throws IOException {
		Path file1 = Paths.get(latPath);
		List<String> lines1 = Files.readAllLines(file1, Charset.defaultCharset());
		Path file2 = Paths.get(lonPath);
		List<String> lines2 = Files.readAllLines(file2, Charset.defaultCharset());
		PrintWriter writer = new PrintWriter(Sub_RoutePath, "UTF-8");
		
			int cont = 1;
			int arrival = 2;
			for(int j = 0; j<30; ++j) {
				int departure = 1;
				for(int k = 0; k<5; ++k) {
					int distance = (int)distance( Double.parseDouble(lines1.get(departure-1)), Double.parseDouble(lines2.get(departure-1)), Double.parseDouble(lines1.get(arrival-1)), Double.parseDouble(lines2.get(arrival-1)),'K');
					int time = (int)(distance/0.83);
					writer.println( cont + "," + departure + "," + arrival + "," + time + "," + distance );
					departure = arrival;
					arrival += 1; 
					++cont;
				}
			}
		   
			arrival = 4;
			for(int j = 0; j<37; ++j) {
				int departure = 3;
				for(int k = 0; k<4; ++k) {
					int distance = (int)distance( Double.parseDouble(lines1.get(departure-1)), Double.parseDouble(lines2.get(departure-1)), Double.parseDouble(lines1.get(arrival-1)), Double.parseDouble(lines2.get(arrival-1)),'K');
					int time = (int)(distance/0.83);
					writer.println( cont + "," + departure + "," + arrival + "," + time + "," + distance );
					departure = arrival;
					arrival += 1; 
					++cont;
				}
			}
			
			arrival = 5;
			for(int j = 0; j<21; ++j) {
				int departure = 4;
				for(int k = 0; k<7; ++k) {
					int distance = (int)distance( Double.parseDouble(lines1.get(departure-1)), Double.parseDouble(lines2.get(departure-1)), Double.parseDouble(lines1.get(arrival-1)), Double.parseDouble(lines2.get(arrival-1)),'K');
					int time = (int)(distance/0.83);
					writer.println( cont + "," + departure + "," + arrival + "," + time + "," + distance );//System.out.println("INSERT INTO APP_LETSTRACK_Sub_Route VALUES (" + cont + "," + departure + "," + arrival + "," + time + "," + distance + ");");
					departure = arrival;
					arrival += 1; 
					++cont;
				}
			}
			
			writer.close();
			
	}
	
	private void connection() throws FileNotFoundException, UnsupportedEncodingException {
		
		PrintWriter writer = new PrintWriter(ConnectionPath, "UTF-8");
		int route = 1;
		int sub = 1;
		for(int j=0; j<30; ++j) {
			for(int i = 0; i<5; ++i) {
				writer.println( route + "," + sub );
				++sub;
			}
			++route;
		}
		
		
		for(int j=0; j<37; ++j) {
			for(int i = 0; i<4; ++i) {
				writer.println( route + "," + sub );
				++sub;
			}
			++route;
		}
		
		
		for(int j=0; j<21; ++j) {
			for(int i = 0; i<7; ++i) {
				writer.println( route + "," + sub );//System.out.println("INSERT INTO APP_LETSTRACK_Connection VALUES ( " +  route + "," + sub +");" );
				++sub;
			}
			++route;
		}
		
		writer.close();
		
	}
	
	private void route() throws IOException {
		Path file1 = Paths.get(timesPath);
		List<String> lines1 = Files.readAllLines(file1, Charset.defaultCharset());
		Path file2 = Paths.get(distancesPath);
		List<String> lines2 = Files.readAllLines(file2, Charset.defaultCharset());
		PrintWriter writer = new PrintWriter(RoutePath, "UTF-8");

		
		int cont = 1;
		int sub = 0;
		int time = 0;
		int distance = 0;
		for(int j=0; j<30; ++j) {
			for(int i = 0; i<5; ++i) {
				time += Integer. parseInt(lines1.get(sub));
				distance += Integer. parseInt(lines2.get(sub));
				++sub;
			}
			writer.println( cont + "," + time + "," + distance );
			time = 0;
			distance = 0;
			++cont;
		}
		
		time = 0;
		distance = 0;
		for(int j=0; j<37; ++j) {
			for(int i = 0; i<4; ++i) {
				time += Integer. parseInt(lines1.get(sub));
				distance += Integer. parseInt(lines2.get(sub));
				++sub;
			}
			writer.println( cont + "," + time + "," + distance );
			time = 0;
			distance = 0;
			++cont;
		}
		
		time = 0;
		distance = 0;
		for(int j=0; j<21; ++j) {
			for(int i = 0; i<7; ++i) {
				time += Integer. parseInt(lines1.get(sub));
				distance += Integer. parseInt(lines2.get(sub));
				++sub;
			}
			writer.println( cont + "," + time + "," + distance );//System.out.println("INSERT INTO APP_LETSTRACK_Route VALUES ( " + cont + "," + time + "," + distance +");" );
			time = 0;
			distance = 0;
			++cont;
		}
		
		writer.close();
		
	}
	
	//RND
	
	private String time() {
		int hour = ThreadLocalRandom.current().nextInt(1, 23 + 1);
		int minute = ThreadLocalRandom.current().nextInt(1, 59 + 1);
		int second = ThreadLocalRandom.current().nextInt(1, 59 + 1);
		String time = hour + ":" + minute + ":" + second ;
		return time;
	}
	
	private String date() {
		int year = ThreadLocalRandom.current().nextInt(2009, 2021 + 1);
		int month = ThreadLocalRandom.current().nextInt(1, 12 + 1);
		int day = ThreadLocalRandom.current().nextInt(1, 28 + 1);
		String date = year + "-" + month + "-" + day ;
		return date;
	}
	
	private int route(int hub) {
		if (hub == 1) return ThreadLocalRandom.current().nextInt(1, 30 + 1);
		if (hub == 2) return ThreadLocalRandom.current().nextInt(31, 67 + 1);
		if (hub == 3) return ThreadLocalRandom.current().nextInt(68, 88+ 1);
		else return -1;
	}
	
	private void order() throws IOException {
		Path file = Paths.get(mailPath);
		List<String> lines = Files.readAllLines(file, Charset.defaultCharset());
		PrintWriter writer = new PrintWriter(OrdersPath, "UTF-8");
		int id = 1;
		for(int i=0; i<1000; ++i) {
			for(int j=0; j<1000; ++j) {
				int hub = ThreadLocalRandom.current().nextInt(1, 3 + 1);
				int route = route(hub); 
				int carrier = ThreadLocalRandom.current().nextInt(1, 3 + 1);
				String time = time();
				String date = date();
				writer.println(/*"INSERT INTO Orders VALUES (" +*/ id + "," + lines.get(i) + "," + hub + "," + route + "," + carrier + "," + time + "," + date/* + ");"*/);
				++id;
			}
		}
		writer.close();
	}
	
	private void firstCity() throws IOException {
		Path file = Paths.get(dateHistPath);
		List<String> lines = Files.readAllLines(file, Charset.defaultCharset());
		Path file1 = Paths.get(timeHistPath);
		List<String> lines1 = Files.readAllLines(file1, Charset.defaultCharset());
		Path file2 = Paths.get(hubHistPath);
		List<String> lines2 = Files.readAllLines(file2, Charset.defaultCharset());
		PrintWriter writer = new PrintWriter(HistoryPath, "UTF-8");
		int id = 1;
		int order = 1;
		for(int i=0; i<1000000; ++i) {
			String date = lines.get(i);
			String time = lines1.get(i);
			int city = Integer.parseInt(lines2.get(i));
			if(city==1) city = 1;
			else if (city==2) city = 3;
			else if (city==3) city =4;
			else city = -1;
			writer.println( id + "," + order + "," + city + "," + time + "," + date );
			++order;
		}
		writer.close();
	}
	
	public static void main(String[] args) throws IOException {
		new populator().city();
		new populator().customer();
		new populator().sub_route();
		new populator().connection();
		new populator().route();
		new populator().order();
		new populator().firstCity();
	}
	
}
