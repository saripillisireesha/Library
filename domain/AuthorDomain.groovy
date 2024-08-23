package example.micronaut.gorm.domain

import grails.gorm.annotation.Entity
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@Entity
@ToString(includeNames=true, includePackage=false)
@EqualsAndHashCode(includes='id')
class AuthorDomain {
    Long id
    String firstName
    String lastName
    String birthDate
    static hasMany = [books:BookDomain]
    static mapping = {
        books fetch:"join"
    }

    static constraints= {
        firstName nullable: false
        lastName nullable: false
        birthDate nullable: false

    }

}
