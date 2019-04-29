package com.orlowski

import spock.lang.Specification

import java.util.function.Predicate
import java.util.stream.Stream

class AppTest extends Specification {

	def "Everything works when an interface declares abstract equals method - an interface is still a functional"() {
		given:
		MyInterface myImplTest = new MyImpl()

		when:
		def result = myImplTest.doSomething()

		then:
		result == "something"
	}

	def "filter data using static isEquals predicate method"() {
		expect:
		testedStream.filter(filter).count() == collectionSize

		where:
		testedStream             | filter                                            | collectionSize
		Stream.of("a", "b", "c") | Predicate.isEqual("c")                            | 1
		Stream.of("a", "b", "c") | Predicate.isEqual("c").or(Predicate.isEqual("b")) | 2
	}

	def "That's how to use a flatMap!"() {
		given:
		Map<String, List<List<String>>> listMap = new LinkedHashMap<>()
		listMap.put("A", [["a", "b", "c"], ["d", "e", "f"], ["g", "h", "i"]])
		listMap.put("B", [["a", "b", "c"], ["d", "e", "f"], ["g", "h", "i"]])
		listMap.put("C", [["a", "b", "c"], ["d", "e", "f"], ["g", "h", "i"]])

		when:
		def result = listMap.entrySet()
				.stream()
				.map({ entrySet -> entrySet.getValue() }) //List<List<String>>>
				.flatMap({ listOfStrings -> listOfStrings.stream() }) //Stream<List<String>>
				.flatMap({ streamOfList -> streamOfList.stream() })
				.map({ value -> value.toUpperCase() })
				.count()

		then:
		result == 27
	}
}
