package com.example.covidtracker;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Corona implements Serializable{
	@Id
	@Column(name="id", updatable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String combinedKey;
	Long deaths;
	Long deathChanges;
	Long active;
	Long recovered;
	Long confirmed;
	Long confirmedChanges;
	LocalDateTime lastUpdate;
	
}
