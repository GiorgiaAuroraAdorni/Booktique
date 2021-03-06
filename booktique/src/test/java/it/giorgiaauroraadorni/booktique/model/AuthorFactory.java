package it.giorgiaauroraadorni.booktique.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AuthorFactory implements EntityFactory<Author> {

    @Override
    public Author createValidEntity(int idx) {
        var author = new Author();

        // mandatory attributes (inherit from person)
        author.setFiscalCode("CGNNMO00T00L00" + idx + "A");
        author.setName("Nome" + idx);
        author.setSurname("Cognome" + idx);

        // other attributes
        author.setDateOfBirth(LocalDate.now().minusYears(35 + idx));
        author.setEmail(author.getName() + author.getSurname() + "@author-mail.com");
        author.setMobilePhone("333333333" + idx);
        author.setWebSiteURL("https://www." + author.getName() + author.getSurname() + ".org");
        author.setBiography(author.getName() + " is a friendly government politician and has a post-graduate degree " +
                "in philosophy, politics and economics. To learn more about " + author.getName() + " " +
                author.getSurname() + " visit the website " + author.getWebSiteURL() + " !");

        return author;
    }
}
