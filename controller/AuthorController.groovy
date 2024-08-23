package example.micronaut.gorm.controller

import example.micronaut.gorm.model.AuthorModel
import example.micronaut.gorm.service.AuthorService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put

import javax.inject.Inject

@Controller("/books")
class AuthorController {
    @Inject
    AuthorService authorService
    @Post
    def saveAuthor(@Body AuthorModel authorModel) {
        authorService.saveAuthor(authorModel)
        return authorModel
    }
    @Get("/id/{id}")
    def getData(@PathVariable Long id){
        return authorService.getData(id)
    }
    @Get
    def getAllBooks(){
        return authorService.getAllBooks()
    }
    @Delete("/{id}")
    def deleteData(@PathVariable Long id){
        authorService.deleteBook(id)
        return "deleted successfully"
    }
    @Put("/id/{id}")
    def updated(@PathVariable Long id, @Body AuthorModel authorModel){
        authorService.updateBooks(id, authorModel)
        return "updated successfully"
    }

}
