package dyds.catalog.alpha.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLDatabase implements Database {

	public SQLDatabase() {
		createDatabase();
	}     
        
    private void createDatabase() {
    	try {     
        	Connection connection = createConnection();
            if (connection != null) {            	
                Statement statement = createStatement(connection);
                if (!databaseExists(connection)) {
                	statement.executeUpdate("create table catalog (id INTEGER, title string PRIMARY KEY, extract string, source integer)");
                }
                connection.close();
            }
        } catch (SQLException e) {             
        }		
	}
    
    private Connection createConnection() throws SQLException {
    	String url = "jdbc:sqlite:./dictionary.db";
    	Connection connection = DriverManager.getConnection(url);
    	
    	return connection;
    }
    
    private Statement createStatement(Connection connection) throws SQLException {    	
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);           
    	
    	
    	return statement;
    }

	private boolean databaseExists(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select count(*) from (select name from sqlite_master where name='catalog')");
		boolean databaseExists = resultSet.getInt(1) == 1;
		
		return databaseExists;
	}

	public List<String> getTitles() throws DatabaseException { 
        List<String> titles = new ArrayList<>();
        try {    
            Connection connection = createConnection();
            if (connection != null) {
            	Statement statement = createStatement(connection);
            	ResultSet rs = statement.executeQuery("select * from catalog order by title asc");
                while(rs.next()) {
                	titles.add(rs.getString("title"));
                }              
                connection.close();
            }
            
        } catch(SQLException e) {
            throw new DatabaseException("Failed to access the local list of articles");
        }       
      
        return titles;
    }
    
    public void saveEntry(String title, String extract) throws DatabaseException {
    	String replacedTitle = title.replace("'", "`");
    	String replacedExtract = extract.replace("'", "`");
	    try {
	        Connection connection = createConnection();
            if (connection != null) {
            	Statement statement = createStatement(connection);
            	statement.executeUpdate("replace into catalog values(null, '"+ replacedTitle + "', '"+ replacedExtract + "', 1)");
            	connection.close();
            }  
	    } catch(SQLException e) {
	        throw new DatabaseException("Failed to save the article");
	    }
    }    

    public String getExtract(String title) throws InvalidTitleException, DatabaseException {  
	    String extract = null;
	    try {
	    	Connection connection = createConnection();
            if (connection != null) {
            	Statement statement = createStatement(connection);
            	ResultSet rs = statement.executeQuery("select * from catalog WHERE title = '" + title + "'" );
    	        if (rs.next()) {
    	        	extract = rs.getString("extract");	
    	        }
    	        else {
    	        	throw new InvalidTitleException("Invalid title: the entry does not exists");
    	        }
    	        connection.close();
            }	          
	    } catch(SQLException e) {
		    throw new DatabaseException("Failed to select the article");
	    }
	  
	    return extract;
  	}

    public void deleteEntry(String title) throws InvalidTitleException, DatabaseException {
	    try {
	    	Connection connection = createConnection();
            if (connection != null) {
            	Statement statement = createStatement(connection);
            	int rowCount = statement.executeUpdate("DELETE FROM catalog WHERE title = '" + title + "'" );
            	boolean deleteWorked = rowCount > 0;
            	if (!deleteWorked) {
            		throw new InvalidTitleException("Invalid title: the entry does not exists");
            	}
            	connection.close();
            }
	    }
	    catch(SQLException e) {
	    	throw new DatabaseException("Failed to delete the article");
	    }
    }
}
