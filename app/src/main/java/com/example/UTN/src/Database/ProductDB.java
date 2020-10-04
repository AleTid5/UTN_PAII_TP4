package com.example.UTN.src.Database;

import android.os.Handler;
import android.os.Looper;

import com.example.UTN.src.Activities.tabs.view_models.AddTabViewModel;
import com.example.UTN.src.Activities.tabs.view_models.ListTabViewModel;
import com.example.UTN.src.Activities.tabs.view_models.UpdateTabViewModel;
import com.example.UTN.src.Models.Category;
import com.example.UTN.src.Models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public abstract class ProductDB {
    public static void addProduct(Product product) {
        Executors.newFixedThreadPool(1).submit(() -> {
            try {
                String query = "INSERT INTO articulo (id, nombre, stock, idCategoria) VALUES (?, ?, ?, ?);";

                Connection connection = DatabaseManager.getConnection();
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, product.getId());
                preparedStmt.setString(2, product.getName());
                preparedStmt.setInt(3, product.getStock());
                preparedStmt.setInt(4, product.getCategory().getId());
                preparedStmt.execute();

                new Handler(Looper.getMainLooper()).post(() -> {
                    ListTabViewModel.addProduct(product);
                    AddTabViewModel.productAdded(true);
                });
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> AddTabViewModel.productAdded(false));
            }
        });
    }

    public static void updateProduct(Product product) {
        Executors.newFixedThreadPool(1).submit(() -> {
            try {
                String query = "UPDATE articulo SET nombre = ?, stock = ?, idCategoria = ? WHERE id = ?;";

                Connection connection = DatabaseManager.getConnection();
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, product.getName());
                preparedStmt.setInt(2, product.getStock());
                preparedStmt.setInt(3, product.getCategory().getId());
                preparedStmt.setInt(4, product.getId());
                preparedStmt.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public static void removeProduct(Product product) {
        Executors.newFixedThreadPool(1).submit(() -> {
            try {
                String query = "UPDATE articulo SET status = FALSE WHERE id = ?;";

                Connection connection = DatabaseManager.getConnection();
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, product.getId());
                preparedStmt.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public static void findProducts() {
        Executors.newFixedThreadPool(1).submit(() -> {
            try {
                List<Product> products = new ArrayList<>();

                String query = "SELECT A.id, A.nombre, A.stock, A.idcategoria, C.descripcion " +
                        "FROM articulo A " +
                        "INNER JOIN categoria C ON A.idcategoria = C.id " +
                        "WHERE A.status = TRUE " +
                        "ORDER BY A.id";

                Connection connection = DatabaseManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    products.add(new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre"),
                            resultSet.getInt("stock"),
                            new Category(resultSet.getInt("idcategoria"), resultSet.getString("descripcion"))
                    ));
                }

                new Handler(Looper.getMainLooper()).post(() -> products.forEach(ListTabViewModel::addProduct));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void findProduct(Integer productId) {
        Executors.newFixedThreadPool(1).submit(() -> {
            try {
                String query = String.format("SELECT A.id, A.nombre, A.stock, A.idcategoria, A.status, C.descripcion " +
                        "FROM articulo A " +
                        "INNER JOIN categoria C ON A.idcategoria = C.id " +
                        "WHERE A.id = %s;", productId);

                Connection connection = DatabaseManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                if (!resultSet.next()) {
                    throw new Exception();
                }

                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("stock"),
                        new Category(resultSet.getInt("idcategoria"), resultSet.getString("descripcion"))
                );

                product.setIsActive(resultSet.getBoolean("status"));

                new Handler(Looper.getMainLooper()).post(() -> UpdateTabViewModel.productFound(product));
            } catch (Exception e) {
                e.printStackTrace();
                new Handler(Looper.getMainLooper()).post(() -> UpdateTabViewModel.productFound(null));
            }
        });
    }
}
