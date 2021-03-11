package com.store.repository;

import com.store.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class BookRepository {

    private static final String SQL_SELECT_ORDER_INFO =
            "SELECT * FROM book WHERE isbn = :isbn";

    private static final String SQL_UPDATE_AVAILABLE_QUANTITY =
            "UPDATE book SET available_quantity = available_quantity - :quantity WHERE id = :bookId";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Optional<Book> getBook(String isbn) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    SQL_SELECT_ORDER_INFO,
                    new MapSqlParameterSource()
                            .addValue("isbn", isbn),
                    getRowMapper()
            ));
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public void subtractAvailableQuantityByBookId(long bookId, long quantity) {
        jdbcTemplate.update(SQL_UPDATE_AVAILABLE_QUANTITY, new MapSqlParameterSource()
                        .addValue("bookId", bookId)
                        .addValue("quantity", quantity)
        );
    }

    private RowMapper<Book> getRowMapper() {
        return (rs, rowNum) -> new Book(
                rs.getLong("id"),
                rs.getString("isbn"),
                rs.getBigDecimal("price"),
                rs.getInt("available_quantity"));
    }
}
