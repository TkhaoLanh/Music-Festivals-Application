package ca.sheridancollege.lanht.database;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.lanht.bean.Ticket;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class TicketRepository {
	protected NamedParameterJdbcTemplate jdbc;
	
	public void addTicket(Ticket ticket) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO ticket (ticket_name, dates, times, price, gate, seat)"
				+ " VALUES (:name, :dates, :times, :price, :gate, :seat)";
		parameters.addValue("name", ticket.getName());
		parameters.addValue("dates", ticket.getDates());
		parameters.addValue("times", ticket.getTimes());
		parameters.addValue("price", ticket.getPrice());
		parameters.addValue("gate", ticket.getGate());
		parameters.addValue("seat", ticket.getSeat());
		jdbc.update(query, parameters);
	}
	
	public ArrayList<Ticket> getTickets(){
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM ticket";
	    List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
	    for (Map<String, Object> row : rows) {
	    	Ticket t = new Ticket();
	    	t.setId((Integer)row.get("id"));
	    	t.setName((String)row.get("ticket_name"));
	    	t.setDates((String)row.get("dates"));
	    	t.setTimes((Double)row.get("times"));
	    	t.setPrice((Double)row.get("price"));
	    	t.setGate((Integer)row.get("gate"));
	    	t.setSeat((Integer)row.get("seat"));
	    	tickets.add(t);
	    }
	    return tickets;
	}
	
	public Ticket getTicketById(int id){
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM ticket where id=:id";
		parameters.addValue("id", id);
	    List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
	    for (Map<String, Object> row : rows) {
	    	Ticket t = new Ticket();
	    	t.setId((Integer)row.get("id"));
	    	t.setName((String)row.get("ticket_name"));
	    	t.setDates((String)row.get("dates"));
	    	t.setTimes((Double)row.get("times"));
	    	t.setPrice((Double)row.get("price"));
	    	t.setGate((Integer)row.get("gate"));
	    	t.setSeat((Integer)row.get("seat"));
	    	tickets.add(t);
	    	
	    }
	    
	    if(tickets.size()>0) {
	    	return tickets.get(0);
	    }else {
	    	return null;
	    }
	}
	
	public void editTicket(Ticket ticket) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "UPDATE ticket SET ticket_name=:name, dates=:dates, times=:times,"
				+ "price=:price, gate=:gate, seat=:seat WHERE id=:id ";
		parameters.addValue("id", ticket.getId());
		parameters.addValue("name", ticket.getName());
		parameters.addValue("dates", ticket.getDates());
		parameters.addValue("times", ticket.getTimes());
		parameters.addValue("price", ticket.getPrice());
		parameters.addValue("gate", ticket.getGate());
		parameters.addValue("seat", ticket.getSeat());
		jdbc.update(query, parameters);
	}
	
	public void deleteTicketById(int id){
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE FROM ticket where id=:id";
		parameters.addValue("id", id);
	    jdbc.update(query, parameters);
	}
	
	public Double maxPrice() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "Select MAX(price) from ticket";
		 List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		    for (Map<String, Object> row : rows) {
		    	
		    	double d = (Double)row.get("MAX(PRICE)");
		    			    	
		    	return d;
		    }
		 return 0d;
	     
	}
	
	public Double minPrice() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "Select MIN(price) from ticket";
		 List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		    for (Map<String, Object> row : rows) {
		    	
		    	double d = (Double)row.get("MIN(PRICE)");
		    			    	
		    	return d;
		    }
		 return 0d;
	     
	}
	

	public Long totalFestivals() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "Select COUNT(ID) from ticket";
		 List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		    for (Map<String, Object> row : rows) {
		    	
		    	long d = (Long)row.get("COUNT(ID)");
		    			    	
		    	return d;
		    }
		 return null;
	     
	}


	public BigDecimal averagePrice() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "Select ROUND(AVG(price)) from ticket";
		 List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		 BigDecimal a = new BigDecimal(0.0);
		    for (Map<String, Object> row : rows) {
		    	
		    	BigDecimal d = (BigDecimal)row.get("ROUND(AVG(PRICE))");
		    			    	
		    	return d;
		    	
		    }
		 return a;
	     
	}
	
	
	public BigDecimal ticketAmount() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "select SUM(price) from ticket";
		 List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		 BigDecimal a = new BigDecimal(0.0);
		    for (Map<String, Object> row : rows) {
		    	
		    	BigDecimal d = (BigDecimal)row.get("SUM(PRICE)");
		    			    	
		    	return d;
		    }
		 return a;
	     
	}
}
