package exercise;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.FutureTask;

class App {

    public static CompletableFuture<String> unionFiles(String path1,String path2, String pathResult){
        CompletableFuture<String> task1=readFile(path1);
        CompletableFuture<String> task2=readFile(path2);
        CompletableFuture<String> writeTask=task1.thenCombine(task2,  (cont1,cont2 )-> writeFile(pathResult,cont1+cont2))
                .exceptionally(ex-> {System.out.println(ex.getMessage()); return  null;} );
        return writeTask;

    }

    public static void main(String[] args) throws Exception {

    }

    public static CompletableFuture<String> readFile(String filePath){

        CompletableFuture future= CompletableFuture.supplyAsync(()->{
        List<String> fileContent;
        Path fpath=Path.of(filePath);

        try {
            fileContent=Files.readAllLines( fpath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return String.join("",fileContent );

    });
        return future;
    }

    public static CompletableFuture<Long> getFolderSize(String fpath) throws Exception {
        Path fPath=Path.of(fpath);
        if (!Files.isDirectory(fPath)){
            throw new  Exception("Directory not Exists");
        }
        return CompletableFuture.supplyAsync(()-> {
            try {
             return   Files.list(fPath).map((f)-> {File x= new File(f.toUri());return x.length();  } ).reduce(0L,(x1,x2)->x1+x2 );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
//        return Files.list(fPath).map((f)-> {File x= new File(f.toUri());return x.length();  } ).reduce(0L,(x1,x2)->x1+x2 );

        }




    public static String writeFile(String filePath,String fileContent){
            Path fpath=Path.of(filePath);
            if(Files.isDirectory(fpath))
                fpath=Paths.get(filePath,"temp");

            try {
                Files.writeString(fpath,fileContent,StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return String.join("\n",fileContent );
    }
}

