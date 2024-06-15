package code.with.vanilson.productservice;

import io.cucumber.java.DataTableType;

import java.math.BigDecimal;
import java.util.List;

/**
 * ProductDataTableTransformer
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-15
 */
public class ProductDataTableTransformer {

    @DataTableType
    public Product defineProduct(List<String> row) {
        // Assuming your Product class has appropriate constructors or static methods
        // to create instances from the DataTable row data
        return new Product(
                Integer.parseInt(row.get(0)),    // id
                row.get(1),                     // name
                row.get(2),                     // description
                Double.parseDouble(row.get(3)), // price
                BigDecimal.valueOf(Long.parseLong(row.get(4)))  // quantity
        );
    }
}