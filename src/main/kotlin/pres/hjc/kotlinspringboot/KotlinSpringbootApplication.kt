package pres.hjc.kotlinspringboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class KotlinSpringbootApplication

fun main(args: Array<String>) {
	runApplication<KotlinSpringbootApplication>(*args)
}
