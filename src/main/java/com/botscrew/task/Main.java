package com.botscrew.task;

import com.botscrew.task.view.Starter;

import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {
        try {
            new Starter();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
