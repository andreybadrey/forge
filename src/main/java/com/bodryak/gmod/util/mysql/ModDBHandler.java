package com.bodryak.gmod.util.mysql;

import java.sql.*;

public class ModDBHandler {
    Connection connection;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(config.getConnectionString(), config.getDbUser(), config.getDbPass());
        return connection;
    }

    public void registerPlayer(String name) throws SQLException, ClassNotFoundException {
        String query;
        PreparedStatement statement;
        ResultSet result;

        query = "SELECT count(*) " + "FROM information_schema.tables " + "WHERE table_name = '" + config.getTbPlayers() + "' LIMIT 1;";
        statement = getConnection().prepareStatement(query);
        result = statement.executeQuery();
        result.next();
        if(result.getInt(1) == 0){
            query = "CREATE TABLE `"+ config.getDbName() +"`.`"+ config.getTbPlayers() +"` (`id` INT(15) NOT NULL AUTO_INCREMENT , `name` VARCHAR(30) NULL , `coins` INT(15) NOT NULL DEFAULT '0' , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
            statement = getConnection().prepareStatement(query);
            statement.executeUpdate();
        }
        query = "SELECT count(*) FROM `" + config.getTbPlayers() + "` WHERE `name` = '" + name + "';";
        statement = getConnection().prepareStatement(query);
        result = statement.executeQuery();
        result.next();
        if(result.getInt(1) == 0){
            query = "INSERT INTO `"+ config.getTbPlayers() +"` (`id`, `name`) VALUES (NULL, '" + name + "');";
            statement = getConnection().prepareStatement(query);
            statement.executeUpdate();
        }
        statement.close();
    }

    public void setupQuestCoreForPlayer(String name) throws SQLException, ClassNotFoundException {
        String query;
        PreparedStatement statement;
        ResultSet resultSet;

        String tbName = name + "_quests";

        query = "SELECT count(*) FROM information_schema.tables WHERE table_name = '" + tbName + "' LIMIT 1;";
        statement = getConnection().prepareStatement(query);
        resultSet = statement.executeQuery();
        resultSet.next();

        if(resultSet.getInt(1) == 0){
            query = "CREATE TABLE `"+config.getDbName()+"`.`"+tbName+"` (`id` INT(15) NOT NULL , `type` TEXT NOT NULL , `status` TEXT NOT NULL , `start_stamp` DATE NOT NULL DEFAULT CURRENT_TIMESTAMP , `complite_stamp` DATE NULL DEFAULT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
            statement = getConnection().prepareStatement(query);
            statement.executeUpdate();
        }
        statement.close();

        //CREATE TABLE `s122599_mod`.`Dev_quest` (`id` INT(15) NOT NULL , `type` TEXT NOT NULL , `status` TEXT NOT NULL , `start_stamp` DATE NOT NULL DEFAULT CURRENT_TIMESTAMP , `complite_stamp` DATE NULL DEFAULT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;
    }

    public void setQuest(String name, int qid, String key) throws SQLException, ClassNotFoundException {
        String query;
        PreparedStatement statement;
        ResultSet resultSet;

        String tbName = name + "_quests";

        if (key.equals("new")){
            query = "INSERT INTO `"+tbName+"` (`id`, `type`, `status`) VALUES ('"+qid+"', 'lore', 'progress')";
            statement = getConnection().prepareStatement(query);
            statement.executeUpdate();
            statement.close();
        }

    }
    public boolean checkQuest(String name, int qid) throws SQLException, ClassNotFoundException {
        String query;
        PreparedStatement statement;
        ResultSet resultSet;

        String tbName = name + "_quests";

        query= "SELECT count(*) FROM "+tbName+" WHERE id = '"+qid+"' LIMIT 1;";
        statement = getConnection().prepareStatement(query);
        resultSet = statement.executeQuery();
        resultSet.next();

        if (resultSet.getInt(1) == 0){
            //System.out.println("Квест не найден у игрока");
            statement.close();
            return false;
        }else {
            //System.out.println("Квест уже есть у игрока");
            statement.close();
            return true;
        }

    }

    public int getBalance(String name) throws SQLException, ClassNotFoundException {
        String query;
        PreparedStatement statement;
        ResultSet resultSet;

        query = "SELECT coins FROM players WHERE name = '" + name + "' LIMIT 1;";
        statement = getConnection().prepareStatement(query);
        resultSet = statement.executeQuery();
        resultSet.next();

        int buf = resultSet.getInt(1);
        statement.close();
        return buf;
    }
    public void dropBalance(String name, int balance) throws SQLException, ClassNotFoundException {
        String query = "UPDATE players SET coins = '"+ balance +"' WHERE name = '"+ name +"';";
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }
}
