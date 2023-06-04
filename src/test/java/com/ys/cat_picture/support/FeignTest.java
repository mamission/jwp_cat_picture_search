package com.ys.cat_picture.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
	classes = {FeignTestContext.class}
)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FeignTest {

}