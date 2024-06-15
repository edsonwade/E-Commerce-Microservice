package code.with.vanilson.productservice.config;

/**
 * CucumberSpringConfiguration
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-14
 */

import code.with.vanilson.productservice.Product;
import io.cucumber.java.DataTableType;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import code.with.vanilson.productservice.ProductServiceApplication; // Import your main application class
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Map;

@SpringBootTest(classes = ProductServiceApplication.class)
@CucumberContextConfiguration
@AutoConfigureMockMvc
public class CucumberSpringConfiguration {
    @Autowired
    private MockMvc mockMvc;


    @DataTableType
    public Product defineProduct(Map<String, String> row) {
        try {
            int id = Integer.parseInt(row.get("id"));
            String name = row.get("name");
            String description = row.get("description");
            double price = Double.parseDouble(row.get("price"));
            BigDecimal quantity = new BigDecimal(row.get("quantity"));

            return new Product(id, name, description, price, quantity);
        } catch (NumberFormatException | NullPointerException e) {
            throw new IllegalArgumentException("Error parsing row: " + row, e);
        }
    }

}