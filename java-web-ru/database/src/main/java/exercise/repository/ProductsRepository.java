package exercise.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import com.zaxxer.hikari.HikariDataSource;
import exercise.dto.BasePage;
import exercise.model.Product;

import java.sql.SQLException;
import java.sql.Statement;

public class ProductsRepository extends BaseRepository {
    public static void save(Product product) {
        String req="Insert into products(title,price) values (?,?)";
        try(Connection con=dataSource.getConnection();
        PreparedStatement stmt=con.prepareStatement(req,Statement.RETURN_GENERATED_KEYS )) {
            stmt.setString(1,product.getTitle() );
            stmt.setInt(2,product.getPrice());
            stmt.executeUpdate();
            ResultSet keys=stmt.getGeneratedKeys();
            if(keys.next()) {
                product.setId(keys.getLong(1));
            }
            else{
                throw new SQLException("exception");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Product> getEntities() {
        String req = "Select * from products";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(req)) {

            ResultSet res = stmt.executeQuery();
            List<Product> products = new ArrayList<>();
            while (res.next()) {
                String title = res.getString("title");
                Integer price = res.getInt("price");
                Long id = res.getLong("id");
                Product product = new Product(title, price);
                product.setId(id);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Optional<Product> find(Long id) {
        String req = "Select * from products where id=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(req)) {
            stmt.setLong(1,id);
            ResultSet res = stmt.executeQuery();
            if(res.next()) {
                String title = res.getString("title");
                Integer price = res.getInt("price");
                Product product = new Product(title, price);
                product.setId(id);
                return Optional.of(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    // BEGIN

    // END
}
