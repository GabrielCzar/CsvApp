package com.persistencia.csv.util;

import com.persistencia.csv.model.User;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileSystems.getDefault;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

@Component
public class CsvViewResolver {
    private final String CSVfile = "data.csv";
    private final String delimiter = ",";
    private String line = "";
    private Path path;

    CsvViewResolver() {
        this.path = getDefault().getPath(System.getenv("HOME"));
        //this.is = CsvViewResolver.class.getClassLoader().getResourceAsStream(CSVfile);
    }

    public List<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(
                                    new InputStreamReader(
                                        new FileInputStream(path.resolve(CSVfile).toString())));
            while ((line = br.readLine()) != null) {
                String [] usr = line.split(delimiter);
                list.add(getUser(usr));
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return list;
        }
    }

    public boolean addUser(User user) {
        try {
            PrintStream pt = new PrintStream(
                                new FileOutputStream(
                                        path.resolve(CSVfile).toString(), true));
            pt.println(user.toString());
            pt.close();
            return true;
        } catch (IOException e) {
            File file = new File(path.toAbsolutePath().toString());
            printStackTrace("Error to find file");
            return false;
        }
    }

    private User getUser(String [] line) {
        return new User(line[0], line[1], line[2], line[3]);
    }
}