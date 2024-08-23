package example.micronaut.gorm.model

import example.micronaut.gorm.domain.AuthorDomain

import java.time.LocalDate

class BookModel{
    Long id
    String title
    Integer pages
    LocalDate publishedDate
    AuthorDomain authors


}
