package com.example.catpicturesearch.application.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.catpicturesearch.application.domain.Breed;
import com.example.catpicturesearch.application.domain.CatBreed;
import com.example.catpicturesearch.application.domain.CatImage;

public class TheCatProvider {

	private TheCatProvider() {
	}

	public static List<CatBreed> getRandom50() {
		List<CatBreed> catBreeds = new ArrayList<>(getCatBreeds());
		Collections.shuffle(catBreeds);
		return catBreeds;
	}

	public static List<CatBreed> getCatImagesByBreedId(String breedId) {
		return getCatBreeds().stream()
				.filter(catBreed -> catBreed.getBreed().getId().equals(breedId))
				.toList();
	}

	public static CatBreed getCatImagesByCatImageId(String catImageId) {
		return getCatBreeds().stream()
				.filter(catBreed -> catBreed.getCatImage().getId().equals(catImageId))
				.findFirst()
				.orElseThrow();
	}

	public static List<CatBreed> getCatBreeds() {
		return List.of(
				CatBreed.builder()
						.breed(getEBUR())
						.catImage(new CatImage("DZzcGANt5", "https://cdn2.thecatapi.com/images/DZzcGANt5.jpg", 2048, 1365))
						.build(),
				CatBreed.builder()
						.breed(getEBUR())
						.catImage(new CatImage("d8sbdRtLJ", "https://cdn2.thecatapi.com/images/d8sbdRtLJ.jpg", 1050, 1126))
						.build(),
				CatBreed.builder()
						.breed(getEBUR())
						.catImage(new CatImage("5hmYjVhib", "https://cdn2.thecatapi.com/images/5hmYjVhib.jpg", 935, 1000))
						.build(),
				CatBreed.builder()
						.breed(getEBUR())
						.catImage(new CatImage("oLtx9gsxx", "https://cdn2.thecatapi.com/images/oLtx9gsxx.jpg", 4027, 2680))
						.build(),
				CatBreed.builder()
						.breed(getEBUR())
						.catImage(new CatImage("YOjBThApG", "https://cdn2.thecatapi.com/images/YOjBThApG.jpg", 2838, 4518))
						.build(),
				CatBreed.builder()
						.breed(getEBUR())
						.catImage(new CatImage("daHxeTPgZ", "https://cdn2.thecatapi.com/images/daHxeTPgZ.jpg", 800, 547))
						.build(),
				CatBreed.builder()
						.breed(getEBUR())
						.catImage(new CatImage("uvyjW5KIG", "https://cdn2.thecatapi.com/images/uvyjW5KIG.jpg", 3008, 2000))
						.build(),
				CatBreed.builder()
						.breed(getEBUR())
						.catImage(new CatImage("ATYs2BetM", "https://cdn2.thecatapi.com/images/ATYs2BetM.jpg", 1920, 1440))
						.build(),
				CatBreed.builder()
						.breed(getEBUR())
						.catImage(new CatImage("GI062lGTf", "https://cdn2.thecatapi.com/images/GI062lGTf.png", 680, 656))
						.build(),
				CatBreed.builder()
						.breed(getEBUR())
						.catImage(new CatImage("pK_t-KYIi", "https://cdn2.thecatapi.com/images/pK_t-KYIi.jpg", 637, 421))
						.build(),
				CatBreed.builder()
						.breed(getBENG())
						.catImage(new CatImage("J2PmlIizw", "https://cdn2.thecatapi.com/images/J2PmlIizw.jpg", 1080, 1350))
						.build(),
				CatBreed.builder()
						.breed(getBENG())
						.catImage(new CatImage("LSaDk6OjY", "https://cdn2.thecatapi.com/images/LSaDk6OjY.jpg", 1080, 1080))
						.build(),
				CatBreed.builder()
						.breed(getBENG())
						.catImage(new CatImage("8pCFG7gCV", "https://cdn2.thecatapi.com/images/8pCFG7gCV.jpg", 750, 937))
						.build(),
				CatBreed.builder()
						.breed(getBENG())
						.catImage(new CatImage("VZ3qFLIe3", "https://cdn2.thecatapi.com/images/VZ3qFLIe3.jpg", 750, 937))
						.build(),
				CatBreed.builder()
						.breed(getBENG())
						.catImage(new CatImage("iWyIaja-G", "https://cdn2.thecatapi.com/images/iWyIaja-G.jpg", 1080, 769))
						.build(),
				CatBreed.builder()
						.breed(getBENG())
						.catImage(new CatImage("GAmy2bg8G", "https://cdn2.thecatapi.com/images/GAmy2bg8G.jpg", 750, 750))
						.build(),
				CatBreed.builder()
						.breed(getBENG())
						.catImage(new CatImage("Rl39SPjDO", "https://cdn2.thecatapi.com/images/Rl39SPjDO.png", 1198, 1379))
						.build(),
				CatBreed.builder()
						.breed(getBENG())
						.catImage(new CatImage("8RsP7Xt3h", "https://cdn2.thecatapi.com/images/8RsP7Xt3h.jpg", 1024, 817))
						.build(),
				CatBreed.builder()
						.breed(getBENG())
						.catImage(new CatImage("byQhFO7iV", "https://cdn2.thecatapi.com/images/byQhFO7iV.jpg", 1795, 2397))
						.build(),
				CatBreed.builder()
						.breed(getBENG())
						.catImage(new CatImage("4-5SzDNIL", "https://cdn2.thecatapi.com/images/4-5SzDNIL.jpg", 880, 1100))
						.build()
		);
	}

	public static Breed getEBUR() {
		return new Breed("ebur", "European Burmese", "Sweet, Affectionate, Loyal", "Burma");
	}

	public static Breed getBENG() {
		return new Breed("beng", "Bengal", "Alert, Agile, Energetic, Demanding, Intelligent", "United States");
	}

}
