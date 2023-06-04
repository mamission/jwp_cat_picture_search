package com.ys.cat_picture.support;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.generator.DefaultPropertyGenerator;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;

public class MonkeyUtils {
	private MonkeyUtils() {
	}

	public static FixtureMonkey byConstructorProperties(boolean isNonNull) {
		return FixtureMonkey.builder()
			.objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
			.defaultNotNull(isNonNull)
			.build();
	}

	public static FixtureMonkey byFieldReflection(boolean isNonNull) {
		return FixtureMonkey.builder()
			.objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
			.defaultNotNull(isNonNull)
			.build();
	}


}
