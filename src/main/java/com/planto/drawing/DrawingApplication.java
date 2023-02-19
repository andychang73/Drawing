package com.planto.drawing;

import com.planto.drawing.aggregations.DrawingClientAggService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.planto.drawing.*")
public class DrawingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DrawingApplication.class, args);
	}

	private final DrawingClientAggService drawingClientAggService;

	@Autowired
	public DrawingApplication(DrawingClientAggService drawingClientAggService) {
		this.drawingClientAggService = drawingClientAggService;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println();
		System.out.println();
		System.out.println("Starting Drawing Program...............");
		drawingClientAggService.draw();
	}
}
