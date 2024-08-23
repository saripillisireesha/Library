package example.micronaut.gorm.domain

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import java.time.LocalDate

@grails.gorm.annotation.Entity
@ToString(includeNames=true, includePackage=false)
@EqualsAndHashCode(includes='id')
class BookDomain {
    String title
    Integer pages
    LocalDate publishedDate
    static belongsTo= [authors:AuthorDomain]

    static mapping = {
        authors fetch:"join"
    }
    static constraints = {
        title nullable: false, blank: false
        pages nullable: false, min: 1
        publishedDate nullable: true
    }


}
