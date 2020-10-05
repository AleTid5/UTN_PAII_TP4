package com.example.UTN.src.Database;

import android.os.Handler;
import android.os.Looper;

import com.example.UTN.src.Activities.tabs.view_models.AddTabViewModel;
import com.example.UTN.src.Activities.tabs.view_models.UpdateTabViewModel;
import com.example.UTN.src.Activities.tabs.view_models.WithCategoryViewModel;
import com.example.UTN.src.Models.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public abstract class CategoryDB {
    public static void findCategories() {
        Executors.newFixedThreadPool(1).submit(() -> {
            try {
                List<Category> categories = new ArrayList<>();

                String query = "SELECT * FROM categoria;";

                Connection connection = DatabaseManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    categories.add(new Category(resultSet.getInt("id"), resultSet.getString("descripcion")));
                }

                new Handler(Looper.getMainLooper()).post(() -> categories.forEach(WithCategoryViewModel::addCategory));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
