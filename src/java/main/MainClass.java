package main;

import utils.ConnectionSQL;

public class MainClass {

    public static void main(String[] args) {
        ConnectionSQL.getInstance().openConnection();
    }
    
}
