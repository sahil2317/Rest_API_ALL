package files;

public class payLoad {
	
	public static String addBook(String isbn, String aisle) {
		
		
		String bookAdd = "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Encryption Automation\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"MB\"\r\n"
				+ "}\r\n"
				+ "";
	
	   return bookAdd;
	
	
	
	
	}

}
