package com.gopivotal.multibuildpack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
public class MultiBuildpackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiBuildpackApplication.class, args);
	}
}

@RestController
class SimpleController {

	@GetMapping("/")
	public String[] index() {
		String[] result = new String[2];
		try {
			Process process = Runtime.getRuntime().exec("python --version");
			process.waitFor();

			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
			result[0] = "stdout: " + stdout.readLine();

			BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			result[1] = "stderr: " + stderr.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}

