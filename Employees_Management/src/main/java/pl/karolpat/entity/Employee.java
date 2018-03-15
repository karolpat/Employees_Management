package pl.karolpat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String firstName;
	
	private String lastName;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="position_id")
	@JsonManagedReference
	private Position position;
	
	@Email
	@Column(unique=true)
	private String email;
	
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean active;
	
}
