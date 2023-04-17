package com.prgrms.thecatapi.cat;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "breed", indexes = @Index(name = "idx_name", columnList = "name"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Breed {

	@Id
	private String id;

	@Embedded
	private Weight weight;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String temperament;

	@Column(nullable = false)
	private String origin;

	public Breed(String id, Weight weight, String name, String temperament, String origin) {
		this.id = id;
		this.weight = weight;
		this.name = name;
		this.temperament = temperament;
		this.origin = origin;
	}
}
