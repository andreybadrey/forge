package com.bodryak.gmod.util.mysql;

public class config {
    //private static final String dbHost = "mysql-pl1.joinserver.xyz:3306";
    private static final String dbHost = "mysql-pl1.joinserver.xyz:3306";
    private static final String dbName = "s137311_mod";
    private static final String dbUser = "u137311_6CtvcBcSKo";
    private static final String dbPass = "yFeB=KtZEB3gyKFoCg0LLPI@";
    private static final String tbPlayers = "players";

    public static String getConnectionString(){
        return "jdbc:mysql://" + dbHost + "/" + dbName;
    }

    public static String getDbUser() {
        return dbUser;
    }

    public static String getDbPass() {
        return dbPass;
    }

    public static String getDbName() {
        return dbName;
    }

    public static String getTbPlayers() {
        return tbPlayers;
    }
}
