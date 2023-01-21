package com.greatlearning.tickettracker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

// Entity class to create table with the columns predefined 
@Table(name = "ticket")
public class Tickets {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "ticket_title", nullable = false)
	private String ticketTitle;

	@Column(name = "ticket_short_description")
	private String ticketShortDescription;

	@Temporal(TemporalType.DATE)
	@Column(name = "ticket_created_on")
	private Date ticketCreatedOn;

	@PrePersist
	private void onCreate() {
		ticketCreatedOn = new Date();
	}

	@Column(name = "content")
	private String ticketContent;

}
