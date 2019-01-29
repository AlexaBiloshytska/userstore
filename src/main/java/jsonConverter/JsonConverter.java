package jsonConverter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.User;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class JsonConverter {
    public static String toJson(List<User> list){
        JsonArray jsonArray = new JsonArray();
        for (User user :list){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("age", user.getAge());
            jsonObject.addProperty("name", user.getFirstName());

        }
        return jsonArray.getAsString();
    }


    public static String readJson(HttpServletRequest req) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();

        while (bufferedReader.readLine() != null) {
            stringBuilder.append(bufferedReader.readLine());
        }
        return stringBuilder.toString();
    }
}
