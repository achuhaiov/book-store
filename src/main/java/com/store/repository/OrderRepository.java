package com.store.repository;

import com.store.model.Order;
import com.store.model.OrderInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private static final String SQL_INSERT_INTO_ORDER =
            "INSERT INTO order_book (book_id, username, quantity) " +
                    "VALUES(:bookId, :username, :quantity)";

    private static final String SQL_SELECT_ORDER_INFO =
            "SELECT b.isbn, b.price, ob.id, ob.quantity, ob.username " +
                    "FROM book b INNER JOIN order_book ob ON b.id=ob.book_id";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Long insert(Order order) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(SQL_INSERT_INTO_ORDER, new MapSqlParameterSource()
                .addValue("bookId", order.getBookId())
                .addValue("username", order.getUsername())
                .addValue("quantity", order.getQuantity()),
                holder
        );
        return holder.getKey().longValue();
    }

    public List<OrderInfo> getOrders() {
        return jdbcTemplate.query(SQL_SELECT_ORDER_INFO, getRowMapper());
    }

    private RowMapper<OrderInfo> getRowMapper() {
        return (rs, rowNum) -> new OrderInfo(
                rs.getLong("id"),
                rs.getString("isbn"),
                rs.getString("username"),
                rs.getInt("quantity"),
                rs.getBigDecimal("price")
                        .multiply(BigDecimal.valueOf(rs.getInt("quantity")),
                                MathContext.DECIMAL64));
    }

}
