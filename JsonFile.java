import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFile {
    public void append(String path, String userName, String email, String password) {
        String jsonContent = "{\n" +
                "    \"username\": \"" + userName + "\",\n" +
                "    \"email\": \"" + email + "\",\n" +
                "    \"password\": \"" + password + "\"\n" +
                "}";

        try {
            //check if file exists
            if (Files.exists(Paths.get(path))) {
                appendToObjectArray(path, jsonContent);
            } else {
                createNewFile(path, jsonContent);
            }

            System.out.println("JSON content appended to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendToObjectArray(String path, String jsonContent) throws IOException {
        //read the existing JSON array
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        reader.close();

        //removing the closing bracket from the existing JSON array
        String existingJson = content.toString().trim();
        existingJson = existingJson.substring(0, existingJson.length() - 1);

        //add a comma if the existing JSON array is not empty
        if (!existingJson.isEmpty()) {
            existingJson += ",";
        }

        //append the new JSON object
        String updatedJson = existingJson + jsonContent + "\n]";

        //write the updated JSON back to the file
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(updatedJson);
        }
    }

    private void createNewFile(String path, String jsonContent) throws IOException {
        //reate a new file with the JSON content
        try (FileWriter writer = new FileWriter(path)) {
            writer.write("[\n" + jsonContent + "\n]");
        }
    }
}
