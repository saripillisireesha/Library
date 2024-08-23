package example.micronaut.gorm.service

import example.micronaut.gorm.domain.AuthorDomain
import example.micronaut.gorm.domain.BookDomain
import example.micronaut.gorm.model.AuthorModel
import example.micronaut.gorm.model.BookModel
import grails.gorm.transactions.Transactional

import javax.inject.Inject
import javax.inject.Singleton
import java.awt.print.Book

@Singleton
class AuthorService {
    @Transactional
    def saveAuthor(AuthorModel authorModel) {
        AuthorDomain authorDomain = new AuthorDomain()
        authorDomain.firstName=authorModel.firstName
        authorDomain.lastName=authorModel.lastName
        authorDomain.birthDate=authorModel.birthDate
        authorDomain.save()
        authorDomain.books = new HashSet<>()
        authorModel.books.each {bookModel->
            BookDomain bookDomain = new BookDomain()
            bookDomain.title=bookModel.title
            bookDomain.pages=bookModel.pages

            bookDomain.publishedDate=bookModel.publishedDate

bookDomain.authors=authorDomain
            authorDomain.addToBooks(bookDomain)
            bookDomain.save()

        }
        return authorDomain
    }
    @Transactional
    def getData(Long id){
        AuthorDomain authorDomain =AuthorDomain.get(id)
        AuthorModel authorModel1=new AuthorModel()
        authorModel1.id=authorDomain.id
        authorModel1.firstName=authorDomain.firstName
        authorModel1.lastName=authorDomain.lastName
        authorModel1.birthDate=authorDomain.birthDate

        authorModel1.books = authorDomain.books.collect { BookDomain bookDomain ->
            BookModel bookModel = new BookModel()
            bookModel.id=bookDomain.id
            bookModel.title = bookDomain.title
            bookModel.pages = bookDomain.pages
            bookModel.publishedDate = bookDomain.publishedDate
            return bookModel
        }
        return authorModel1
    }

    @Transactional
    def deleteBook(Long id){
        AuthorDomain authorDomain= AuthorDomain.findById(id)
        if(authorDomain){
            authorDomain.delete()
            return "Book with ID ${id} deleted successfully"

        }
        else{
            return "book with ID ${id} not found"
        }
    }
    @Transactional
    def updateBooks(Long id, AuthorModel updatedauthorModel){
            AuthorDomain authorDomain=AuthorDomain.get(id)
        if(authorDomain) {
            authorDomain.firstName = updatedauthorModel.firstName
            authorDomain.lastName=updatedauthorModel.lastName
            authorDomain.birthDate=updatedauthorModel.birthDate
            updatedauthorModel.books.each { bookModel ->
                BookDomain bookDomain=new BookDomain()
                bookDomain.title=bookModel.title
                bookDomain.pages=bookModel.pages
                bookDomain.publishedDate=bookModel.publishedDate
                bookDomain.authors=authorDomain
                authorDomain.addToBooks(bookDomain)
                bookDomain.save()
            }
            return authorDomain
        }
        else{
            return false
        }
    }
    @Transactional
    List<AuthorModel> getAllBooks(){
        List<AuthorDomain> authorDomainList=AuthorDomain.list()
        return authorDomainList.collect{authorDomain->
            new AuthorModel(
                    id:authorDomain.id,
                    firstName: authorDomain.firstName,
                    lastName: authorDomain.lastName,
                    birthDate: authorDomain.birthDate,
                    books:authorDomain.books.collect{bookDomain->
                        new BookModel(
                                id:bookDomain.id,
                                title:bookDomain.title,
                                pages: bookDomain.pages,
                                publishedDate: bookDomain.publishedDate
                        )

                    }
            )
        }

    }
}
