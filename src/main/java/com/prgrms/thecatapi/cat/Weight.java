package com.prgrms.thecatapi.cat;

import javax.persistence.Embeddable;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@Table(name = "cat_weight")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Weight {
	private String imperial;

	private String metric;

	public Weight(String imperial, String metric) {
		this.imperial = imperial;
		this.metric = metric;
	}
}
