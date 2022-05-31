package com.example.tourdefrance.configuration;

import com.example.tourdefrance.model.Country;
import com.example.tourdefrance.model.Rider;
import com.example.tourdefrance.model.Team;
import com.example.tourdefrance.repositories.RidersRepository;
import com.example.tourdefrance.repositories.TeamsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Klasse til at fylde data ude med indledende data
@Configuration
public class preloadData implements CommandLineRunner {

    TeamsRepository teamsRepository;
    RidersRepository ridersRepository;

    public preloadData(TeamsRepository teamsRepository, RidersRepository ridersRepository) {
        this.teamsRepository = teamsRepository;
        this.ridersRepository = ridersRepository;
    }

    @Override
    public void run(String ...args) throws Exception{
        //'tourDeFranceData.txt' filens indhold er bygget efter kilde reference til de givne cykel teams fra: https://www.procyclingstats.com/race/tour-de-france/2022/startlist

        //Kilde reference til opsætningen inspireret ved : https://stackoverflow.com/questions/10257981/read-text-file-into-an-array
        BufferedReader preparedData = new BufferedReader(new FileReader(Paths.get("src/main/resources/static","tourDeFranceDataCSV.txt").toString()));
        String temporaryHolder = "";

        while((temporaryHolder = preparedData.readLine()) != null) {
            if (temporaryHolder.contains("0")) {
                Team team = new Team();
                team.setTeamName(temporaryHolder.replace("0", "").replace(";", "").trim());
                System.out.println(team.getTeamName());
                teamsRepository.save(team);
            }
            if (!temporaryHolder.contains("0")) {
                String[] newRiderData = temporaryHolder.split(";");
                Rider newRider = new Rider();
                newRider.setLastName(newRiderData[0]);
                newRider.setFirstName(newRiderData[1].trim());
                System.out.println(newRider.getFirstName() +" "+ newRider.getLastName());
                ridersRepository.save(newRider);
            }
        }
        preparedData.close();
    }
}
