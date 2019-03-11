package com.orlowski

import spock.lang.Specification

class MyImplTest extends Specification {

    def "Everything works when an interface declares abstract equals method - an interface is still a functional"(){
        given:
        MyInterface myImplTest = new MyImpl()

        when:
        def result = myImplTest.doSomething()

        then:
        result == "something"
    }
}
