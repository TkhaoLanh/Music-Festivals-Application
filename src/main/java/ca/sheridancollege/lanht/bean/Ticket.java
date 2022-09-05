package ca.sheridancollege.lanht.bean;


import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ticket {
	private int id;
	private String name;
	private String dates;
	private double times;
	private double price;
	private int gate;
	private int seat;

}
