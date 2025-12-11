package fr.ensitech.biblio.repository;

import fr.ensitech.biblio.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface IBookRepository extends JpaRepository<Book, Long> {

    List<Book> findByPublished(boolean published);
    List<Book> findByTitleIgnoreCase(String title);
    List<Book> findByTitleContainingIgnoreCase(String text);
    Book findByIsbnIgnoreCase(String isbn);
    List<Book> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
    List<Book> findByPublicationDateBetween(Date startDate, Date endDate);

	//@Query(value = "SELECT COUNT(*) FROM book WHERE publication_date BETWEEN 1? AND 2?", nativeQuery = true)  // SQL
    @Query(value = "SELECT COUNT(b) FROM Book b WHERE b.publicationDate BETWEEN ?1 AND ?2", nativeQuery = false)  // JPQL
    long countBooksByPublicationDate(Date startDate, Date endDate);
	
	@Query(value = "select a from Author a where a.firstname" +
                  " like concat ('%', '?1', '%') or a.firstname like concat ('%', '?1', '%')" +
                  " order by a.firstname, a.lastname", nativeQuery = false)
    List<Book> findBooksByAuthorName(String name);
}
