package org.apache.tapestry5.ioc

import spock.lang.Specification

class OrderedConstraintBuilderSpec extends Specification {

  /** Any unrecognized methods are evaluated against {@link OrderConstraintBuilder}. */
  def methodMissing(String name, args) {
    OrderConstraintBuilder."$name"(* args)
  }

  def "constraint ordering"() {
    expect:

    constraint.build() == values

    where:

    constraint || values

    after("A") || ["after:A"]
    afterAll() || ["after:*"]
    before("B") || ["before:B"]
    beforeAll() || ["before:*"]

    before("A").after("B") || ["before:A", "after:B"]
  }


}
