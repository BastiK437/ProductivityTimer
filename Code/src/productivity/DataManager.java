package productivity;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class DataManager {

    public void storeTime(String time) {
        File dataDir = new File("data/");

        if( !dataDir.exists() ) {
            if( !dataDir.mkdir() ) {
                System.err.printf("dir not created\n");
            }
        }

        File safeFile = new File(dataDir.getPath() + "/time.txt");
        LocalDate date = LocalDate.now();
        boolean fileOpened = true;

        if( !safeFile.exists() ) {
            try {
                safeFile.createNewFile();
            } catch (IOException e) {
                System.err.printf("cant create File!\n");
                fileOpened = false;
            }
        }

        if(fileOpened) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(safeFile));
                writer.write(time + '\n');
                writer.write(date.toString());

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String restoreTime() {
        File safeFile = new File("data/time.txt");
        String restoredTime = "00:00:00";

        if( safeFile.exists() ) {
            Scanner myReader = null;
            try {
                myReader = new Scanner(safeFile);
                restoredTime = myReader.nextLine();
                LocalDate fileDate = LocalDate.parse(myReader.nextLine());

                if(LocalDate.now().compareTo(fileDate) != 0) {
                    restoredTime = "00:00:00";
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return restoredTime;
    }

    public void storeDiff(StopwatchTime diffTime) {
        File dataDir = new File("data/");

        if( !dataDir.exists() ) {
            if( !dataDir.mkdir() ) {
                System.err.printf("dir not created\n");
            }
        }

        File safeFile = new File(dataDir.getPath() + "/allTime.txt");
        boolean fileOpened = true;

        if( !safeFile.exists() ) {
            try {
                safeFile.createNewFile();
            } catch (IOException e) {
                System.err.printf("cant create File!\n");
                fileOpened = false;
            }
        }

        if(fileOpened) {
            try {
                Scanner myReader = null;
                String restoredTime = "00:00:00";
                try {
                    myReader = new Scanner(safeFile);
                    if(myReader.hasNext()) {
                        restoredTime = myReader.nextLine();
                    }

                    myReader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                BufferedWriter writer = new BufferedWriter(new FileWriter(safeFile));
                StopwatchTime tmpTime = new StopwatchTime(restoredTime);

                tmpTime = tmpTime.add(diffTime);

                writer.write(tmpTime.toString());

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
