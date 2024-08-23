package example.micronaut.gorm.model

import example.micronaut.gorm.domain.BookDomain


class AuthorModel{
    Long id
    String firstName
    String lastName
    String birthDate
    Set<BookDomain> books



}
