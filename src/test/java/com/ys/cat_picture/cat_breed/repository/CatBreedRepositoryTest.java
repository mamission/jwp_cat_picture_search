package com.ys.cat_picture.cat_breed.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.ys.cat_picture.cat_breed.domain.CatBreed;
import com.ys.cat_picture.cat_breed.domain.Weight;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
class CatBreedRepositoryTest {

	@Autowired
	private CatBreedRepository catBreedRepository;

	@DisplayName("existsData - 존재하지 않으면 false를 반환한다.")
	@Test
	void existsData_false() {
		assertThat(catBreedRepository.existsData()).isFalse();
	}

	@DisplayName("existsData - 존재하면 true를 반환한다.")
	@Test
	void existsData_true() {
		//given
		catBreedRepository.saveAll(breeds);

		//when & then
		assertThat(catBreedRepository.existsData()).isTrue();
	}

	private List<CatBreed> breeds =
		List.of(
			CatBreed.builder()
				.weight(new Weight("6 - 12", "3 - 7"))
				.externalId("beng")
				.name("Bengal")
				.cfaUrl("http://cfa.org/Breeds/BreedsAB/Bengal.aspx")
				.vetstreetUrl("http://www.vetstreet.com/cats/bengal")
				.vcahospitalsUrl("https://vcahospitals.com/know-your-pet/cat-breeds/bengal")
				.temperament("Alert, Agile, Energetic, Demanding, Intelligent")
				.origin("United States")
				.countryCodes("US")
				.countryCode("US")
				.description("Bengals are a lot of fun to live with, but they're definitely not the cat for everyone, or for first-time cat owners. Extremely intelligent, curious and active, they demand a lot of interaction and woe betide the owner who doesn't provide it.")
				.lifeSpan("12 - 15")
				.indoor(0)
				.lap(0)
				.adaptability(5)
				.affectionLevel(5)
				.childFriendly(4)
				.catFriendly(4)
				.dogFriendly(5)
				.energyLevel(5)
				.grooming(1)
				.healthIssues(3)
				.intelligence(5)
				.sheddingLevel(3)
				.socialNeeds(5)
				.strangerFriendly(3)
				.vocalisation(5)
				.bidability(3)
				.experimental(0)
				.hairless(0)
				.natural(0)
				.rare(0)
				.rex(0)
				.suppressedTail(0)
				.shortLegs(0)
				.wikipediaUrl("https://en.wikipedia.org/wiki/Bengal_(cat)")
				.hypoallergenic(1)
				.referenceImageId("O3btzLlsO")
				.build(),
			CatBreed.builder()
				.weight(new Weight("8 - 13", "4 - 6"))
				.externalId("cymr")
				.name("Cymric")
				.vetstreetUrl("http://www.vetstreet.com/cats/cymric")
				.temperament("Gentle, Loyal, Intelligent, Playful")
				.origin("Canada")
				.countryCodes("CA")
				.countryCode("CA")
				.description("The Cymric is a placid, sweet cat. They do not get too upset about anything that happens in their world. They are loving companions and adore people. They are smart and dexterous, capable of using his paws to get into cabinets or to open doors.")
				.lifeSpan("8 - 14")
				.indoor(0)
				.lap(1)
				.altNames("Spangle")
				.adaptability(5)
				.affectionLevel(5)
				.childFriendly(4)
				.dogFriendly(5)
				.energyLevel(5)
				.grooming(3)
				.healthIssues(3)
				.intelligence(5)
				.sheddingLevel(5)
				.socialNeeds(5)
				.strangerFriendly(3)
				.vocalisation(3)
				.experimental(0)
				.hairless(0)
				.natural(0)
				.rare(0)
				.rex(0)
				.suppressedTail(1)
				.shortLegs(0)
				.wikipediaUrl("https://en.wikipedia.org/wiki/Cymric_(cat)")
				.hypoallergenic(0)
				.referenceImageId("3dbtapCWM")
				.build()

		);
}