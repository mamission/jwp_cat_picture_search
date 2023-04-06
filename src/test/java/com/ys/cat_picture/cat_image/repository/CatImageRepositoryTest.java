package com.ys.cat_picture.cat_image.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.ys.cat_picture.cat_breed.domain.CatBreed;
import com.ys.cat_picture.cat_breed.domain.Weight;
import com.ys.cat_picture.cat_image.domain.CatImage;
import com.ys.cat_picture.support.RepositoryTest;
@Sql(scripts = {"/sql/breed_sample.sql", "/sql/cat_image_sample.sql"})
@RepositoryTest
class CatImageRepositoryTest {

	@Autowired
	private CatImageRepository catImageRepository;

	@DisplayName("이미 존재하는 데이터는 저장하지 않는다.")
	@Test
	void saveAllIgnore_existsDataNotInsert() {
	    //given
		List<CatImage> catImages = List.of(
			new CatImage("36v", "https://cdn2.thecatapi.com/images/36v.jpg", 540, 720,
				existsBreeds.get(0)),
			new CatImage("24u", "https://cdn2.thecatapi.com/images/24u.png", 564, 400,
				existsBreeds.get(1)));
		int exitingCatImageDataSize = catImageRepository.findAll().size();

		//when
		catImageRepository.saveAllIgnore(catImages);

		//then
		assertThat(catImageRepository.findAll()).hasSize(exitingCatImageDataSize);
	}

	@DisplayName("Breed 가 이미 존재하면 외래키를 맺는다.")
	@Test
	void saveAllIgnoreTest() {
		//given
		List<CatImage> catImages = List.of(
			new CatImage("aa", "https://cdn2.thecatapi.com/images/36v.jpg", 540, 720,
				existsBreeds.get(0)),
			new CatImage("bb", "https://cdn2.thecatapi.com/images/24u.png", 564, 400,
				existsBreeds.get(1)));

		//when
		List<CatImage> saveCatImages = catImageRepository.saveAllIgnore(catImages);

		//then
		assertThat(saveCatImages)
			.extracting(it -> it.getBreed().getId())
			.isNotNull();
	}

	private List<CatBreed> existsBreeds =
		List.of(
			CatBreed.builder()
				.weight(new Weight("6 - 12", "3 - 7"))
				.externalId("bamb")
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
				.externalId("bali")
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