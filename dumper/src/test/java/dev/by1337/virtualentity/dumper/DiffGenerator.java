package dev.by1337.virtualentity.dumper;

import org.junit.Test;

import java.io.File;

import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import com.google.common.base.Predicate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class DiffGenerator {

    @Test
    public void run() throws IOException {
        Path home = Path.of("./diff");
        Path newFolder =  home.resolve("actual");
        Path oldFolder =  home.resolve("old");
        Path diffFolder = home.resolve("result");
        Files.createDirectories(diffFolder);

        List<Path> filesOld = findFiles(f -> f.toString().endsWith(".java") || f.toString().endsWith(".json"), oldFolder);
        List<Path> filesNew = findFiles(f -> f.toString().endsWith(".java") || f.toString().endsWith(".json"), newFolder);
        for (Path path : filesNew) {
            if (path.getFileName().toString().contains("Sheep")) {
                System.out.println(path);
            }
        }

        System.out.println("Files in old: " + filesOld.size());
        System.out.println("Files in new: " + filesNew.size());

        Map<String, Path> filesOldMap = new HashMap<>();
        for (Path file : filesOld) {
            String relativePath = oldFolder.relativize(file).toString();
            filesOldMap.put(file.toFile().getName(), file);
        }

        Map<String, Path> filesNewMap = new HashMap<>();
        for (Path file : filesNew) {
            String relativePath = newFolder.relativize(file).toString();
            filesNewMap.put(file.toFile().getName(), file);
        }


        Files.createDirectories(diffFolder);

        for (String s : filesNewMap.keySet()) {
            Path newFile = filesNewMap.get(s);
            Path oldFile = filesOldMap.remove(s);
            if (oldFile == null) {
                List<String> lines = Files.readAllLines(newFile);
                StringBuilder sb = new StringBuilder();
                for (String line : lines) {
                    sb.append("++ ").append(line).append("\n");
                }
                File out = new File(diffFolder.toFile(), s);
                out.getParentFile().mkdirs();
                Files.writeString(out.toPath(), sb.toString());
            } else {
             //   if (Files.size(newFile) == Files.size(oldFile)) continue;
                String diffResult = diffGenerator(
                        Files.readAllLines(oldFile),
                        Files.readAllLines(newFile)
                );
                if (!diffResult.isEmpty()) {
                    File out = new File(diffFolder.toFile(), s);
                    out.getParentFile().mkdirs();
                    Files.writeString(out.toPath(), diffResult);
                }
            }
        }

        for (String s : filesOldMap.keySet()) {
            Path oldFile = filesOldMap.get(s);
            List<String> lines = Files.readAllLines(oldFile);
            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                sb.append("-- ").append(line).append("\n");
            }
            File out = new File(diffFolder.toFile(), s);
            out.getParentFile().mkdirs();
            Files.writeString(out.toPath(), sb.toString());
        }
    }

    private static List<Path> findFiles(java.util.function.Predicate<Path> predicate, Path folder) throws IOException {
        List<Path> result = new ArrayList<>();
        Files.walk(folder)
                .filter(predicate)
                .forEach(result::add);
        return result;
    }

    private static String diffGenerator(List<String> old, List<String> newLines) {
        DiffRowGenerator generator = DiffRowGenerator.create()
                .mergeOriginalRevised(true)
                .oldTag(f -> "")
                .newTag(f -> "")
                .lineNormalizer(s -> s)
                .build();

        List<DiffRow> rows = generator.generateDiffRows(old, newLines);

        StringBuilder sb = new StringBuilder();
        boolean hasChanged = false;
        for (DiffRow row : rows) {
            if (row.getTag() == DiffRow.Tag.EQUAL) {
                sb.append(row.getOldLine()).append("\n");
            } else if (row.getTag() == DiffRow.Tag.DELETE) {
                sb.append("-- ").append(row.getOldLine()).append("\n");
                hasChanged = true;
            } else if (row.getTag() == DiffRow.Tag.INSERT) {
                sb.append("++ ").append(row.getNewLine()).append("\n");
                hasChanged = true;
            } else if (row.getTag() == DiffRow.Tag.CHANGE) {
                sb.append("-- ").append(row.getOldLine()).append("\n");
                sb.append("++ ").append(row.getNewLine()).append("\n");
                hasChanged = true;
            }
        }
        if (!hasChanged) return "";
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}