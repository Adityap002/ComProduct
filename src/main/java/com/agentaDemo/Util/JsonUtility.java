package com.agentaDemo.Util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.agentaDemo.bean.TestData;
import com.agentaDemo.config.Configuration.jsonSetUp;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class JsonUtility {

	public static void main(String[] args) {
		getTestData(jsonSetUp.jsonLocation, jsonSetUp.fileName, "TestData");
	}

	public static List<TestData> getTestData(String path, String fileName,
			String testName) {
		JsonElement jsonData;
		try {
			jsonData = new JsonParser().parse(new FileReader(path + fileName));
			JsonElement dataSet = jsonData.getAsJsonObject();
			List<TestData> testData = new Gson().fromJson(dataSet,
					new TypeToken<List<TestData>>() {
					}.getType());
			System.out.println(testData.get(0));
			return testData;

		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
