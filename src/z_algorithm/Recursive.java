package z_algorithm;

import java.io.File;

public class Recursive {
    public static void catFile(File file, int deep) {
        for (int i = 0; i < deep; i++) {
            System.out.print("-");
        }
        System.out.println(file.getName());
        if (file.isDirectory()) {
            deep++;
            for (File dFile : file.listFiles()
            ) {
                catFile(dFile, deep);
            }

        }

    }

    public static void main(String[] args) {
        File files = new File("");
        catFile(files, 0);
    }
}
